package com.buccancs.data.sensor.connector

import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.flow.StateFlow

interface SensorConnector {
    val deviceId: DeviceId
    val device: StateFlow<SensorDevice>
    val streamStatuses: StateFlow<List<SensorStreamStatus>>
    suspend fun refreshInventory()
    suspend fun applySimulation(enabled: Boolean)
    suspend fun connect(): DeviceCommandResult
    suspend fun disconnect(): DeviceCommandResult
    suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult
    suspend fun stopStreaming(): DeviceCommandResult
    suspend fun collectArtifacts(sessionId: String): List<SessionArtifact> = emptyList()
}
