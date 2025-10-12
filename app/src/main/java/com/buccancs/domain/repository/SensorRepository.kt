package com.buccancs.domain.repository
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.flow.StateFlow
interface SensorRepository {
    val devices: StateFlow<List<SensorDevice>>
    val streamStatuses: StateFlow<List<SensorStreamStatus>>
    val recordingState: StateFlow<RecordingState>
    val simulationEnabled: StateFlow<Boolean>
    suspend fun refreshInventory()
    suspend fun connect(deviceId: DeviceId)
    suspend fun disconnect(deviceId: DeviceId)
    suspend fun setSimulationEnabled(enabled: Boolean)
    suspend fun startStreaming(anchor: RecordingSessionAnchor)
    suspend fun stopStreaming(): RecordingSessionAnchor?
    suspend fun collectSessionArtifacts(sessionId: String): List<SessionArtifact>
}
