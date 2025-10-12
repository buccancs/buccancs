package com.buccancs.data.sensor
import com.buccancs.control.SensorStreamAck
import com.buccancs.control.SensorStreamServiceGrpcKt
import com.buccancs.control.sensorSample
import com.buccancs.control.sensorSampleBatch
import com.buccancs.control.sensorSampleValue
import com.buccancs.control.sessionIdentifier
import com.buccancs.data.orchestration.GrpcChannelFactory
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.repository.OrchestratorConfigRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton
interface SensorStreamEmitter {
    suspend fun emit(timestampEpochMs: Long, values: Map<String, Double>)
    suspend fun close()
}
interface SensorStreamClient {
    suspend fun openStream(
        sessionId: String,
        deviceId: DeviceId,
        streamId: String,
        sampleRateHz: Double
    ): SensorStreamEmitter
}
@Singleton
class SensorStreamUploader @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val configRepository: OrchestratorConfigRepository,
    private val channelFactory: GrpcChannelFactory
) : SensorStreamClient {
    override suspend fun openStream(
        sessionId: String,
        deviceId: DeviceId,
        streamId: String,
        sampleRateHz: Double
    ): SensorStreamEmitter {
        require(sessionId.isNotBlank()) { "sessionId cannot be blank" }
        require(deviceId.value.isNotBlank()) { "deviceId cannot be blank" }
        val config = configRepository.config.value
        val channel = channelFactory.channel(config)
        val stub = SensorStreamServiceGrpcKt.SensorStreamServiceCoroutineStub(channel)
        val emitter = StreamEmitterImpl(
            scope = scope,
            stub = stub,
            sessionId = sessionId,
            deviceId = deviceId.value,
            streamId = streamId.ifBlank { "sensor" },
            sampleRateHz = sampleRateHz.coerceAtLeast(0.0)
        )
        emitter.start()
        return emitter
    }
    private class StreamEmitterImpl(
        private val scope: CoroutineScope,
        private val stub: SensorStreamServiceGrpcKt.SensorStreamServiceCoroutineStub,
        private val sessionId: String,
        private val deviceId: String,
        private val streamId: String,
        private val sampleRateHz: Double
    ) : SensorStreamEmitter {
        private val samples = Channel<QueuedSample>(Channel.BUFFERED)
        private val closed = AtomicBoolean(false)
        private val ack = CompletableDeferred<SensorStreamAck>()
        private lateinit var senderJob: Job
        fun start() {
            senderJob = scope.launch(Dispatchers.IO) {
                val session = sessionIdentifier { id = sessionId }
                val requestFlow = flow {
                    val buffer = mutableListOf<QueuedSample>()
                    try {
                        for (sample in samples) {
                            buffer += sample
                            if (buffer.size >= BATCH_CAPACITY) {
                                emit(buildBatch(buffer, session, endOfStream = false))
                                buffer.clear()
                            }
                        }
                    } finally {
                        if (buffer.isNotEmpty()) {
                            emit(buildBatch(buffer, session, endOfStream = true))
                        } else {
                            emit(buildBatch(emptyList(), session, endOfStream = true))
                        }
                    }
                }
                try {
                    val response = stub.stream(requestFlow)
                    ack.complete(response)
                } catch (t: Throwable) {
                    ack.completeExceptionally(t)
                    throw t
                }
            }
        }
        override suspend fun emit(timestampEpochMs: Long, values: Map<String, Double>) {
            if (closed.get()) return
            samples.send(QueuedSample(timestampEpochMs, values))
        }
        override suspend fun close() {
            if (!closed.compareAndSet(false, true)) return
            samples.close()
            runCatching { senderJob.join() }
            val response = ack.await()
            if (!response.success) {
                throw IOException(response.errorMessage.ifBlank { "Sensor stream rejected" })
            }
        }
        private fun buildBatch(
            queued: List<QueuedSample>,
            session: com.buccancs.control.SessionIdentifier,
            endOfStream: Boolean
        ) = sensorSampleBatch {
            this.session = session
            deviceId = this@StreamEmitterImpl.deviceId
            streamId = this@StreamEmitterImpl.streamId
            sampleRateHz = this@StreamEmitterImpl.sampleRateHz
            this.endOfStream = endOfStream
            if (queued.isNotEmpty()) {
                queued.forEach { sample ->
                    samples += sensorSample {
                        timestampEpochMs = sample.timestamp
                        sample.values.forEach { (key, value) ->
                            values += sensorSampleValue {
                                this.key = key
                                this.value = value
                            }
                        }
                    }
                }
            }
        }
        private data class QueuedSample(
            val timestamp: Long,
            val values: Map<String, Double>
        )
        companion object {
            private const val BATCH_CAPACITY = 64
        }
    }
}
