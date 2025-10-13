package com.buccancs.data.recording.manifest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionManifest(
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("started_at")
    val startedAt: String,
    @SerialName("started_at_epoch_ms")
    val startedAtEpochMs: Long,
    @SerialName("simulation")
    val simulation: Boolean,
    @SerialName("orchestrator_offset_ms")
    val orchestratorOffsetMillis: Long,
    @SerialName("devices")
    val devices: List<DeviceManifest>,
    @SerialName("events")
    val events: List<EventEntry> = emptyList(),
    @SerialName("artifacts")
    val artifacts: List<ArtifactEntry> = emptyList(),
    @SerialName("ended_at")
    val endedAt: String? = null,
    @SerialName("ended_at_epoch_ms")
    val endedAtEpochMs: Long? = null,
    @SerialName("duration_ms")
    val durationMillis: Long? = null,
    @SerialName("notes")
    val notes: List<String> = emptyList()
)

@Serializable
data class DeviceManifest(
    @SerialName("device_id")
    val deviceId: String,
    @SerialName("display_name")
    val displayName: String,
    @SerialName("type")
    val type: String,
    @SerialName("capabilities")
    val capabilities: List<String>,
    @SerialName("attributes")
    val attributes: Map<String, String>,
    @SerialName("simulated")
    val simulated: Boolean
)

@Serializable
data class ArtifactEntry(
    @SerialName("device_id")
    val deviceId: String,
    @SerialName("stream_type")
    val streamType: String,
    @SerialName("relative_path")
    val relativePath: String,
    @SerialName("mime_type")
    val mimeType: String,
    @SerialName("size_bytes")
    val sizeBytes: Long,
    @SerialName("checksum_sha256")
    val checksumSha256: String,
    @SerialName("captured_epoch_ms")
    val capturedEpochMs: Long
)

@Serializable
data class EventEntry(
    @SerialName("event_id")
    val eventId: String,
    @SerialName("type")
    val type: String,
    @SerialName("label")
    val label: String?,
    @SerialName("scheduled_epoch_ms")
    val scheduledEpochMs: Long?,
    @SerialName("received_epoch_ms")
    val receivedEpochMs: Long?
)
