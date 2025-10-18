package com.buccancs.data.sensor.connector

import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.flow.StateFlow

interface MultiDeviceConnector {
    val devices: StateFlow<Map<DeviceId, SensorDevice>>
    val streamStatuses: StateFlow<Map<DeviceId, List<SensorStreamStatus>>>
    suspend fun refreshInventory()
    suspend fun applySimulation(
        enabled: Boolean
    )

    suspend fun connect(
        deviceId: DeviceId
    ): DeviceCommandResult

    suspend fun disconnect(
        deviceId: DeviceId
    ): DeviceCommandResult

    suspend fun configure(
        deviceId: DeviceId,
        options: Map<String, String>
    ): DeviceCommandResult

    suspend fun startStreaming(
        deviceId: DeviceId,
        anchor: RecordingSessionAnchor
    ): DeviceCommandResult

    suspend fun stopStreaming(
        deviceId: DeviceId
    ): DeviceCommandResult

    suspend fun collectArtifacts(
        deviceId: DeviceId,
        sessionId: String
    ): List<SessionArtifact>
}
