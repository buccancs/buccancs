package com.buccancs.desktop.data.grpc.services

import com.buccancs.control.PreviewAck
import com.buccancs.control.PreviewFrame
import com.buccancs.control.PreviewServiceGrpcKt
import com.buccancs.control.previewAck
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*

class PreviewServiceImpl(
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val previewRepository: PreviewRepository
) : PreviewServiceGrpcKt.PreviewServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(PreviewServiceImpl::class.java)

    override suspend fun streamPreview(requests: Flow<PreviewFrame>): PreviewAck {
        requests.collect { frame ->
            val now = Instant.now().toEpochMilli()
            val latency = (now - frame.frameTimestampEpochMs).coerceAtLeast(0L).toDouble()
            previewRepository.update(
                deviceId = frame.deviceId,
                cameraId = frame.cameraId,
                frameTimestampEpochMs = frame.frameTimestampEpochMs,
                encodedFrame = frame.encodedFrame.toByteArray(),
                mimeType = frame.mimeType.ifBlank { "application/octet-stream" },
                width = frame.width.toInt(),
                height = frame.height.toInt(),
                latencyMs = latency
            )
            deviceRepository.updatePreviewLatency(frame.deviceId, latency, Instant.ofEpochMilli(now))
            if (frame.encodedFrame.size() > 0) {
                updateMetrics(frame)
            }
        }
        logger.info("Preview stream closed")
        return previewAck {
            received = true
            info = "Preview stream closed"
        }
    }

    private suspend fun updateMetrics(frame: PreviewFrame) {
        val kind = classifyFrame(frame)
        sessionRepository.registerMetrics { metrics ->
            when (kind) {
                FrameKind.RGB ->
                    metrics.copy(videoFrames = metrics.videoFrames + 1)

                FrameKind.THERMAL ->
                    metrics.copy(thermalFrames = metrics.thermalFrames + 1)
            }
        }
    }

    private fun classifyFrame(frame: PreviewFrame): FrameKind {
        val cameraId = frame.cameraId.lowercase(Locale.ROOT)
        val mime = frame.mimeType.lowercase(Locale.ROOT)
        return when {
            cameraId.contains("thermal") ||
                    cameraId.contains("ir") ||
                    mime.contains("thermal") ||
                    mime.contains("infrared") ->
                FrameKind.THERMAL

            mime.startsWith("image/") ||
                    mime.startsWith("video/") ||
                    cameraId.contains("rgb") ||
                    cameraId.contains("rear") ||
                    cameraId.contains("front") ->
                FrameKind.RGB

            else -> FrameKind.RGB
        }
    }

    private enum class FrameKind {
        RGB,
        THERMAL
    }
}
