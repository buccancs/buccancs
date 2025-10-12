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
    val videoFrames: Long = 0,
    val thermalFrames: Long = 0,
    val audioSamples: Long = 0
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
