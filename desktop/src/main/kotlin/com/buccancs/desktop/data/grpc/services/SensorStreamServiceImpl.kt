package com.buccancs.desktop.data.grpc.services

import com.buccancs.control.SensorSampleBatch
import com.buccancs.control.SensorStreamAck
import com.buccancs.control.SensorStreamServiceGrpcKt
import com.buccancs.control.sensorStreamAck
import com.buccancs.desktop.data.recording.SensorRecordingManager
import com.buccancs.desktop.data.repository.SessionRepository
import io.grpc.Status
import io.grpc.StatusException
import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory

class SensorStreamServiceImpl(
    private val sessionRepository: SessionRepository,
    private val recordingManager: SensorRecordingManager
) : SensorStreamServiceGrpcKt.SensorStreamServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(SensorStreamServiceImpl::class.java)

    override suspend fun stream(requests: Flow<SensorSampleBatch>): SensorStreamAck {
        var activeKey: StreamKey? = null
        var totalSamples = 0L
        return try {
            requests.collect { batch ->
                val sessionId = batch.session.id
                if (sessionId.isBlank()) {
                    throw Status.INVALID_ARGUMENT
                        .withDescription("Missing session identifier")
                        .asRuntimeException()
                }
                if (!sessionRepository.isSessionActive(sessionId)) {
                    throw Status.FAILED_PRECONDITION
                        .withDescription("Session $sessionId is not active")
                        .asRuntimeException()
                }
                val deviceId = batch.deviceId
                if (deviceId.isBlank()) {
                    throw Status.INVALID_ARGUMENT
                        .withDescription("Missing device identifier")
                        .asRuntimeException()
                }
                val streamId = batch.streamId.ifBlank { "sensor" }
                if (activeKey == null) {
                    logger.info(
                        "Sensor stream started (session={}, device={}, stream={})",
                        sessionId,
                        deviceId,
                        streamId
                    )
                }
                activeKey = StreamKey(sessionId, deviceId, streamId)
                val appended = recordingManager.append(batch)
                totalSamples += appended
                if (batch.endOfStream) {
                    recordingManager.finalizeStream(sessionId, deviceId, streamId)
                    logger.info(
                        "Sensor stream completed (session={}, device={}, stream={}, samples={})",
                        sessionId,
                        deviceId,
                        streamId,
                        totalSamples
                    )
                    activeKey = null
                }
            }
            activeKey?.let {
                recordingManager.finalizeStream(it.sessionId, it.deviceId, it.streamId)
                logger.info(
                    "Sensor stream completed without explicit end flag (session={}, device={}, stream={}, samples={})",
                    it.sessionId,
                    it.deviceId,
                    it.streamId,
                    totalSamples
                )
            }
            sensorStreamAck {
                success = true
                totalSamples = totalSamples.coerceAtLeast(0)
            }
        } catch (statusEx: StatusException) {
            activeKey?.let { recordingManager.abortStream(it.sessionId, it.deviceId, it.streamId) }
            throw statusEx
        } catch (throwable: Throwable) {
            activeKey?.let { recordingManager.abortStream(it.sessionId, it.deviceId, it.streamId) }
            logger.error("Sensor stream failed: ${throwable.message}", throwable)
            throw Status.INTERNAL
                .withDescription(throwable.message ?: "Sensor stream failure")
                .withCause(throwable)
                .asRuntimeException()
        }
    }

    private data class StreamKey(
        val sessionId: String,
        val deviceId: String,
        val streamId: String
    )
}
