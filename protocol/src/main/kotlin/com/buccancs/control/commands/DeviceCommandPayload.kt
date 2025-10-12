package com.buccancs.control.commands
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
@Serializable
sealed interface DeviceCommandPayload {
    val commandId: String
    val sessionId: String
    val issuedEpochMs: Long
    val executeEpochMs: Long
}
@Serializable
@SerialName("sync_signal")
data class SyncSignalCommandPayload(
    override val commandId: String,
    override val sessionId: String,
    override val issuedEpochMs: Long,
    override val executeEpochMs: Long,
    val signalType: String,
    val initiator: String? = null
) : DeviceCommandPayload
@Serializable
@SerialName("event_marker")
data class EventMarkerCommandPayload(
    override val commandId: String,
    override val sessionId: String,
    override val issuedEpochMs: Long,
    override val executeEpochMs: Long,
    val markerId: String,
    val description: String,
    val stimulusId: String? = null
) : DeviceCommandPayload
@Serializable
@SerialName("start_recording")
data class StartRecordingCommandPayload(
    override val commandId: String,
    override val sessionId: String,
    override val issuedEpochMs: Long,
    override val executeEpochMs: Long,
    val anchorEpochMs: Long,
    val scheduledEpochMs: Long? = null
) : DeviceCommandPayload
@Serializable
@SerialName("stop_recording")
data class StopRecordingCommandPayload(
    override val commandId: String,
    override val sessionId: String,
    override val issuedEpochMs: Long,
    override val executeEpochMs: Long
) : DeviceCommandPayload
@Serializable
@SerialName("stimulus")
data class StimulusCommandPayload(
    override val commandId: String,
    override val sessionId: String,
    override val issuedEpochMs: Long,
    override val executeEpochMs: Long,
    val stimulusId: String,
    val action: String,
    val metadata: Map<String, String> = emptyMap()
) : DeviceCommandPayload
object CommandSerialization {
    val json: Json = Json {
        encodeDefaults = false
        ignoreUnknownKeys = true
        classDiscriminator = "type"
    }
}
