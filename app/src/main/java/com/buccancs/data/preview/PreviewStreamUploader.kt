package com.buccancs.data.preview

import com.buccancs.control.PreviewServiceGrpcKt
import com.buccancs.control.previewFrame
import com.buccancs.data.orchestration.GrpcChannelFactory
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.google.protobuf.ByteString
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

interface PreviewStreamEmitter {
    suspend fun emit(frameTimestampEpochMs: Long, payload: ByteArray)
    suspend fun close()
}

interface PreviewStreamClient {
    suspend fun openStream(
        deviceId: DeviceId,
        cameraId: String,
        mimeType: String,
        width: Int,
        height: Int
    ): PreviewStreamEmitter
}

@Singleton
class PreviewStreamUploader @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val configRepository: OrchestratorConfigRepository,
    private val channelFactory: GrpcChannelFactory
) : PreviewStreamClient {
    override suspend fun openStream(
        deviceId: DeviceId,
        cameraId: String,
        mimeType: String,
        width: Int,
        height: Int
    ): PreviewStreamEmitter {
        val config = configRepository.config.value
        val channel = channelFactory.channel(config)
        val stub = PreviewServiceGrpcKt.PreviewServiceCoroutineStub(channel)
        val emitter = StreamEmitter(
            scope = scope,
            stub = stub,
            deviceId = deviceId.value,
            cameraId = cameraId.ifBlank { "camera" },
            mimeType = mimeType.ifBlank { DEFAULT_MIME_TYPE },
            width = width.coerceAtLeast(1),
            height = height.coerceAtLeast(1)
        )
        emitter.start()
        return emitter
    }

    private class StreamEmitter(
        private val scope: CoroutineScope,
        private val stub: PreviewServiceGrpcKt.PreviewServiceCoroutineStub,
        private val deviceId: String,
        private val cameraId: String,
        private val mimeType: String,
        private val width: Int,
        private val height: Int
    ) : PreviewStreamEmitter {
        private val frames = Channel<QueuedFrame>(Channel.BUFFERED)
        private val closed = AtomicBoolean(false)
        private val ack = CompletableDeferred<com.buccancs.control.PreviewAck>()
        private lateinit var job: Job

        fun start() {
            job = scope.launch(Dispatchers.IO) {
                val requestFlow = flow {
                    for (frame in frames) {
                        emit(
                            previewFrame {
                                this.deviceId = this@StreamEmitter.deviceId
                                cameraId = this@StreamEmitter.cameraId
                                frameTimestampEpochMs = frame.timestampEpochMs
                                mimeType = this@StreamEmitter.mimeType
                                encodedFrame = ByteString.copyFrom(frame.payload)
                                width = this@StreamEmitter.width
                                height = this@StreamEmitter.height
                            }
                        )
                    }
                }
                try {
                    val response = stub.streamPreview(requestFlow)
                    ack.complete(response)
                } catch (t: Throwable) {
                    ack.completeExceptionally(t)
                    throw t
                }
            }
        }

        override suspend fun emit(frameTimestampEpochMs: Long, payload: ByteArray) {
            if (closed.get()) {
                return
            }
            frames.send(QueuedFrame(frameTimestampEpochMs, payload.copyOf()))
        }

        override suspend fun close() {
            if (!closed.compareAndSet(false, true)) {
                return
            }
            frames.close()
            runCatching { job.join() }
            @OptIn(ExperimentalCoroutinesApi::class)
            val response = if (ack.isCompleted) {
                runCatching { ack.getCompleted() }.getOrNull()
            } else {
                runCatching { ack.await() }.getOrNull()
            }
            if (response != null && !response.received) {
                throw IOException(response.info.ifBlank { "Preview stream rejected." })
            }
        }

        private data class QueuedFrame(
            val timestampEpochMs: Long,
            val payload: ByteArray
        )
    }

    private companion object {
        private const val DEFAULT_MIME_TYPE = "image/jpeg"
    }
}


