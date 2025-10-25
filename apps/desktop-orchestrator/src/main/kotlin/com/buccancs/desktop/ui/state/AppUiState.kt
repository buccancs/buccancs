package com.buccancs.desktop.ui.state

import com.buccancs.desktop.domain.model.PreviewModality
import java.time.Instant

data class AppUiState(
    val session: SessionSummary?,
    val devices: List<DeviceListItem>,
    val retention: RetentionState,
    val previews: List<PreviewStreamState>,
    val transfers: List<TransferStatusItem>,
    val alerts: List<String>,
    val control: ControlPanelState,
    val events: List<EventTimelineItem>,
    val historicalSessions: List<SessionArchiveItem>,
    val capture: UnifiedCaptureState
)

data class SessionSummary(
    val id: String,
    val status: String,
    val startedAt: Instant?,
    val createdAt: Instant,
    val totalBytes: Long,
    val durationMs: Long?,
    val subjectIds: List<String>,
    val elapsedMillis: Long,
    val metrics: SessionMetricsState
)

data class DeviceListItem(
    val id: String,
    val model: String,
    val platform: String,
    val connected: Boolean,
    val recording: Boolean,
    val batteryPercent: Double?,
    val previewLatencyMs: Double?,
    val clockOffsetMs: Double?,
    val lastHeartbeat: Instant?,
    val sessionId: String?
)

data class RetentionState(
    val perSessionBytes: Map<String, Long>,
    val perDeviceBytes: Map<String, Long>,
    val perSessionDeviceBytes: Map<String, Map<String, Long>>,
    val totalBytes: Long,
    val breaches: List<String>
)

data class PreviewStreamState(
    val deviceId: String,
    val cameraId: String,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val latencyMs: Double,
    val receivedAt: Instant,
    val payload: ByteArray,
    val modality: PreviewModality,
    val averageFps: Double,
    val dropCount: Long,
    val maxGapMs: Long,
    val totalFrames: Long
)

data class TransferStatusItem(
    val sessionId: String,
    val deviceId: String,
    val fileName: String,
    val streamType: String?,
    val state: String,
    val attempt: Int,
    val bytesTransferred: Long,
    val bytesTotal: Long,
    val errorMessage: String?
)

data class ControlPanelState(
    val operatorId: String = "",
    val subjectIds: String = "",
    val syncSignalType: String = "flash",
    val syncDelayMs: String = "0",
    val syncTargets: String = "",
    val eventMarkerId: String = "",
    val eventDescription: String = "",
    val eventTargets: String = "",
    val stimulusId: String = "",
    val stimulusAction: String = "",
    val stimulusTargets: String = "",
    val subjectEraseId: String = "",
    val clockOffsetsPreview: Map<String, Double> = emptyMap()
)

data class EventTimelineItem(
    val eventId: String,
    val label: String,
    val timestamp: Instant,
    val deviceIds: List<String>
)

data class SessionArchiveItem(
    val id: String,
    val status: String,
    val createdAt: Instant,
    val startedAt: Instant?,
    val stoppedAt: Instant?,
    val totalBytes: Long,
    val durationMs: Long?,
    val subjects: List<String>,
    val eventCount: Int,
    val deviceCount: Int
)

data class SessionMetricsState(
    val gsrSamples: Long,
    val gsrSampleDrops: Long,
    val gsrOutOfRangeSamples: Long,
    val gsrAverageHz: Double,
    val gsrMaxGapMs: Long,
    val gsrMinValue: Double?,
    val gsrMaxValue: Double?,
    val videoFrames: Long,
    val thermalFrames: Long,
    val audioSamples: Long,
    val videoFrameDrops: Long,
    val thermalFrameDrops: Long,
    val videoAverageFps: Double,
    val thermalAverageFps: Double,
    val videoMaxGapMs: Long,
    val thermalMaxGapMs: Long,
    val updatedAt: Instant?
)

data class UnifiedCaptureState(
    val thermalStreams: List<CaptureStreamState>,
    val rgbStreams: List<CaptureStreamState>,
    val shimmer: ShimmerCaptureState,
    val recordingActive: Boolean,
    val lastUpdated: Instant?
) {
    companion object {
        val EMPTY =
            UnifiedCaptureState(
                thermalStreams = emptyList(),
                rgbStreams = emptyList(),
                shimmer = ShimmerCaptureState.EMPTY,
                recordingActive = false,
                lastUpdated = null
            )
    }
}

data class CaptureStreamState(
    val deviceId: String,
    val cameraId: String,
    val modality: PreviewModality,
    val resolution: String,
    val fps: Double?,
    val latencyMs: Double,
    val receivedAt: Instant,
    val recording: Boolean,
    val dropCount: Long,
    val maxGapMs: Long,
    val totalFrames: Long
)

data class ShimmerCaptureState(
    val connected: Boolean,
    val streaming: Boolean,
    val sampleRateHz: Double?,
    val sampleDrops: Long,
    val maxGapMs: Long,
    val minValue: Double?,
    val maxValue: Double?,
    val updatedAt: Instant?,
    val waveform: List<Double>
) {
    companion object {
        val EMPTY =
            ShimmerCaptureState(
                connected = false,
                streaming = false,
                sampleRateHz = null,
                sampleDrops = 0,
                maxGapMs = 0,
                minValue = null,
                maxValue = null,
                updatedAt = null,
                waveform = emptyList()
            )
    }
}
