package com.buccancs.desktop.data.grpc.services

import com.buccancs.control.PreviewAck
import com.buccancs.control.PreviewFrame
import com.buccancs.control.PreviewServiceGrpcKt
import com.buccancs.control.previewAck
import com.buccancs.desktop.data.metrics.FrameRateTracker
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.domain.model.PreviewModality
import com.buccancs.desktop.domain.model.SessionStatus
import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*
import kotlin.math.max

class PreviewServiceImpl(
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val previewRepository: PreviewRepository
) : PreviewServiceGrpcKt.PreviewServiceCoroutineImplBase() {
    private val logger =
        LoggerFactory.getLogger(
            PreviewServiceImpl::class.java
        )
    private val frameTrackers =
        mutableMapOf<StreamTrackerKey, FrameRateTracker>()
    private val dropCounters =
        mutableMapOf<StreamTrackerKey, Long>()
    private var trackerSessionId: String? =
        null

    override suspend fun streamPreview(
        requests: Flow<PreviewFrame>
    ): PreviewAck {
        requests.collect { frame ->
            val now =
                Instant.now()
                    .toEpochMilli()
            val latency =
                (now - frame.frameTimestampEpochMs).coerceAtLeast(
                    0L
                )
                    .toDouble()
            val kind =
                classifyFrame(
                    frame
                )
            val cadence =
                recordCadence(
                    frame.deviceId,
                    kind,
                    frame.frameTimestampEpochMs
                )
            maybeLogDrop(
                frame,
                kind,
                cadence
            )
            previewRepository.update(
                deviceId = frame.deviceId,
                cameraId = frame.cameraId,
                frameTimestampEpochMs = frame.frameTimestampEpochMs,
                encodedFrame = frame.encodedFrame.toByteArray(),
                mimeType = frame.mimeType.ifBlank { "application/octet-stream" },
                width = frame.width.toInt(),
                height = frame.height.toInt(),
                latencyMs = latency,
                modality = kind.toModality(),
                averageFps = cadence.averageFps,
                dropCount = cadence.dropCount,
                maxGapMs = cadence.maxGapMs,
                totalFrames = cadence.totalFrames
            )
            deviceRepository.updatePreviewLatency(
                frame.deviceId,
                latency,
                Instant.ofEpochMilli(
                    now
                )
            )
            if (frame.encodedFrame.size() > 0) {
                updateMetrics(
                    frame,
                    kind,
                    cadence
                )
            }
        }
        logger.info(
            "Preview stream closed"
        )
        return previewAck {
            received =
                true
            info =
                "Preview stream closed"
        }
    }

    private fun recordCadence(
        deviceId: String,
        kind: FrameKind,
        frameTimestampMs: Long
    ): FrameRateTracker.FrameRateSnapshot {
        val activeSessionId =
            sessionRepository.activeSession.value?.id
        if (activeSessionId != trackerSessionId) {
            trackerSessionId =
                activeSessionId
            frameTrackers.clear()
            dropCounters.clear()
        }
        val key =
            StreamTrackerKey(
                deviceId,
                kind
            )
        val tracker =
            frameTrackers.getOrPut(
                key
            ) {
                FrameRateTracker(
                    targetFpsFor(
                        kind
                    )
                )
            }
        return tracker.record(
            frameTimestampMs
        )
    }

    private suspend fun maybeLogDrop(
        frame: PreviewFrame,
        kind: FrameKind,
        cadence: FrameRateTracker.FrameRateSnapshot
    ) {
        val key =
            StreamTrackerKey(
                frame.deviceId,
                kind
            )
        val previous =
            dropCounters[key]
                ?: 0L
        if (cadence.dropCount > previous) {
            val delta =
                cadence.dropCount - previous
            val label =
                when (kind) {
                    FrameKind.RGB -> "video"
                    FrameKind.THERMAL -> "thermal"
                }
            logger.warn(
                "Detected {} {} frame drop(s) for device {} ; total {} (max gap {} ms, avg fps {})",
                delta,
                label,
                frame.deviceId,
                cadence.dropCount,
                cadence.maxGapMs,
                String.format(
                    Locale.ROOT,
                    "%.1f",
                    cadence.averageFps
                )
            )
            dropCounters[key] =
                cadence.dropCount
            val session =
                sessionRepository.activeSession.value
            val threshold =
                dropEventThreshold(
                    kind
                )
            if (session != null &&
                session.status == SessionStatus.ACTIVE &&
                delta >= threshold
            ) {
                val kindToken =
                    kind.name.lowercase(
                        Locale.ROOT
                    )
                val eventId =
                    "frame-drop-${kindToken}-${frame.deviceId}-${cadence.dropCount}-${frame.frameTimestampEpochMs}"
                val eventLabel =
                    "frame-drop:${kindToken}:${frame.deviceId}:${cadence.dropCount}"
                runCatching {
                    sessionRepository.registerEvent(
                        eventId = eventId,
                        label = eventLabel,
                        timestampMs = frame.frameTimestampEpochMs,
                        deviceIds = listOf(
                            frame.deviceId
                        )
                    )
                }.onFailure { ex ->
                    logger.debug(
                        "Unable to register frame drop event for {} ({})",
                        frame.deviceId,
                        ex.message
                    )
                }
            }
        } else if (!dropCounters.containsKey(
                key
            )
        ) {
            dropCounters[key] =
                cadence.dropCount
        }
    }

    private suspend fun updateMetrics(
        frame: PreviewFrame,
        kind: FrameKind,
        cadence: FrameRateTracker.FrameRateSnapshot
    ) {
        sessionRepository.registerMetrics { metrics ->
            when (kind) {
                FrameKind.RGB ->
                    metrics.copy(
                        videoFrames = metrics.videoFrames + 1,
                        videoFrameDrops = cadence.dropCount,
                        videoMaxGapMs = max(
                            metrics.videoMaxGapMs,
                            cadence.maxGapMs
                        ),
                        videoAverageFps = cadence.averageFps
                    )

                FrameKind.THERMAL ->
                    metrics.copy(
                        thermalFrames = metrics.thermalFrames + 1,
                        thermalFrameDrops = cadence.dropCount,
                        thermalMaxGapMs = max(
                            metrics.thermalMaxGapMs,
                            cadence.maxGapMs
                        ),
                        thermalAverageFps = cadence.averageFps
                    )
            }
        }
    }

    private fun targetFpsFor(
        kind: FrameKind
    ): Double =
        when (kind) {
            FrameKind.RGB -> 30.0
            FrameKind.THERMAL -> 15.0
        }

    private fun dropEventThreshold(
        kind: FrameKind
    ): Long =
        when (kind) {
            FrameKind.RGB -> 10L
            FrameKind.THERMAL -> 5L
        }

    private fun classifyFrame(
        frame: PreviewFrame
    ): FrameKind {
        val cameraId =
            frame.cameraId.lowercase(
                Locale.ROOT
            )
        val mime =
            frame.mimeType.lowercase(
                Locale.ROOT
            )
        return when {
            cameraId.contains(
                "thermal"
            ) ||
                    cameraId.contains(
                        "ir"
                    ) ||
                    mime.contains(
                        "thermal"
                    ) ||
                    mime.contains(
                        "infrared"
                    ) ->
                FrameKind.THERMAL

            mime.startsWith(
                "image/"
            ) ||
                    mime.startsWith(
                        "video/"
                    ) ||
                    cameraId.contains(
                        "rgb"
                    ) ||
                    cameraId.contains(
                        "rear"
                    ) ||
                    cameraId.contains(
                        "front"
                    ) ->
                FrameKind.RGB

            else -> FrameKind.RGB
        }
    }

    private enum class FrameKind {
        RGB,
        THERMAL
    }

    private fun FrameKind.toModality(): PreviewModality =
        when (this) {
            FrameKind.RGB -> PreviewModality.RGB
            FrameKind.THERMAL -> PreviewModality.THERMAL
        }

    private data class StreamTrackerKey(
        val deviceId: String,
        val kind: FrameKind
    )
}
