package com.buccancs.domain.repository

import com.buccancs.domain.model.*
import kotlinx.coroutines.flow.StateFlow

interface SensorRepository {
    val devices: StateFlow<List<SensorDevice>>
    val streamStatuses: StateFlow<List<SensorStreamStatus>>
    val recordingState: StateFlow<RecordingState>
    val simulationEnabled: StateFlow<Boolean>
    val throttleLevel: StateFlow<PerformanceThrottleLevel>
    suspend fun refreshInventory()
    suspend fun connect(deviceId: DeviceId)
    suspend fun disconnect(deviceId: DeviceId)
    suspend fun configure(deviceId: DeviceId, options: Map<String, String>)
    suspend fun setSimulationEnabled(enabled: Boolean)
    suspend fun startStreaming(anchor: RecordingSessionAnchor)
    suspend fun stopStreaming(): RecordingSessionAnchor?
    suspend fun collectSessionArtifacts(sessionId: String): List<SessionArtifact>
    suspend fun applyPerformanceThrottle(level: PerformanceThrottleLevel)
}
