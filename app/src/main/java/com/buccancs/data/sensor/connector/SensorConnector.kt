package com.buccancs.data.sensor.connector

import com.buccancs.domain.model.*
import kotlinx.coroutines.flow.StateFlow

interface SensorConnector {
    val deviceId: DeviceId
    val device: StateFlow<SensorDevice>
    val streamStatuses: StateFlow<List<SensorStreamStatus>>
    suspend fun refreshInventory()
    suspend fun applySimulation(enabled: Boolean)
    suspend fun connect(): DeviceCommandResult
    suspend fun disconnect(): DeviceCommandResult
    suspend fun configure(options: Map<String, String>): DeviceCommandResult =
        DeviceCommandResult.Rejected("Configuration unsupported for ${deviceId.value}.")

    suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult
    suspend fun stopStreaming(): DeviceCommandResult
    suspend fun collectArtifacts(sessionId: String): List<SessionArtifact> = emptyList()
}
