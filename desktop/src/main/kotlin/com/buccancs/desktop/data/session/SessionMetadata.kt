package com.buccancs.desktop.data.session

import kotlinx.serialization.Serializable

@Serializable
data class SessionMetadata(
    val sessionId: String,
    val createdAtEpochMs: Long,
    val status: SessionStatusDto,
    val operatorId: String? = null,
    val subjectIds: List<String> = emptyList(),
    val startedAtEpochMs: Long? = null,
    val stoppedAtEpochMs: Long? = null,
    val totalDurationMs: Long? = null,
    val events: List<EventLog> = emptyList(),
    val files: List<RecordedFile> = emptyList(),
    val metrics: MetadataMetrics = MetadataMetrics(),
    val retention: RetentionSnapshot? = null
)

@Serializable
data class EventLog(
    val eventId: String,
    val label: String,
    val timestampEpochMs: Long,
    val deviceIds: List<String> = emptyList()
)

@Serializable
data class RecordedFile(
    val deviceId: String,
    val path: String,
    val bytes: Long,
    val checksumSha256: String,
    val mimeType: String,
    val createdEpochMs: Long,
    val streamType: String? = null
)

@Serializable
data class MetadataMetrics(
    val gsrSamples: Long = 0,
    val gsrSampleDrops: Long = 0,
    val gsrOutOfRangeSamples: Long = 0,
    val gsrAverageHz: Double = 0.0,
    val gsrMaxGapMs: Long = 0,
    val gsrMinValue: Double? = null,
    val gsrMaxValue: Double? = null,
    val videoFrames: Long = 0,
    val thermalFrames: Long = 0,
    val audioSamples: Long = 0,
    val videoFrameDrops: Long = 0,
    val thermalFrameDrops: Long = 0,
    val videoMaxGapMs: Long = 0,
    val thermalMaxGapMs: Long = 0,
    val videoAverageFps: Double = 0.0,
    val thermalAverageFps: Double = 0.0
)

@Serializable
data class RetentionSnapshot(
    val perDeviceBytes: Map<String, Long>,
    val perSessionBytes: Long,
    val globalBytes: Long
)

@Serializable
enum class SessionStatusDto {
    IDLE,
    ACTIVE,
    STOPPING,
    COMPLETED
}
