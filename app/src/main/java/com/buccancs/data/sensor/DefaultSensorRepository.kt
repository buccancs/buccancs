package com.buccancs.data.sensor

import com.buccancs.util.nowInstant
import com.buccancs.data.sensor.connector.SensorConnector
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.repository.SensorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSensorRepository @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    connectors: List<SensorConnector>
) : SensorRepository {
    private val orderedConnectors = connectors.sortedBy { it.deviceId.value }
    private val connectorsById = orderedConnectors.associateBy { it.deviceId }
    private val devicesCache = orderedConnectors.associate { it.deviceId to it.device.value }.toMutableMap()
    private val devicesMutex = Mutex()
    private val statusesCache =
        orderedConnectors.associate { it.deviceId to emptyList<SensorStreamStatus>() }.toMutableMap()
    private val statusMutex = Mutex()
    private val _simulationEnabled = MutableStateFlow(false)
    override val simulationEnabled: StateFlow<Boolean> = _simulationEnabled.asStateFlow()
    private val _devices = MutableStateFlow(devicesSnapshot())
    override val devices: StateFlow<List<SensorDevice>> = _devices.asStateFlow()
    private val _streamStatuses = MutableStateFlow(emptyList<SensorStreamStatus>())
    override val streamStatuses: StateFlow<List<SensorStreamStatus>> = _streamStatuses.asStateFlow()
    private val _recordingState = MutableStateFlow(
        RecordingState(
            lifecycle = RecordingLifecycleState.Idle,
            anchor = null,
            updatedAt = nowInstant()
        )
    )
    override val recordingState: StateFlow<RecordingState> = _recordingState.asStateFlow()

    init {
        orderedConnectors.forEach { connector ->
            scope.launch {
                connector.device.collect { device ->
                    devicesMutex.withLock {
                        devicesCache[device.id] = device
                        _devices.value = devicesSnapshot()
                    }
                }
            }
            scope.launch {
                connector.streamStatuses.collect { statuses ->
                    statusMutex.withLock {
                        statusesCache[connector.deviceId] = statuses
                        _streamStatuses.value = statusesCache.values.flatten()
                    }
                }
            }
        }
    }

    override suspend fun refreshInventory() {
        orderedConnectors.forEach { connector ->
            connector.refreshInventory()
        }
    }

    override suspend fun connect(deviceId: DeviceId) {
        connectorsById[deviceId]?.connect()
    }

    override suspend fun disconnect(deviceId: DeviceId) {
        connectorsById[deviceId]?.disconnect()
    }

    override suspend fun setSimulationEnabled(enabled: Boolean) {
        if (_simulationEnabled.value == enabled) return
        _simulationEnabled.value = enabled
        orderedConnectors.forEach { connector ->
            connector.applySimulation(enabled)
        }
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor) {
        val now = nowInstant()
        _recordingState.value = RecordingState(
            lifecycle = RecordingLifecycleState.Starting,
            anchor = anchor,
            updatedAt = now
        )
        orderedConnectors.forEach { connector ->
            val snapshot = devicesCache[connector.deviceId]
            val connected = snapshot?.connectionStatus is ConnectionStatus.Connected
            if (connected) {
                when (val result = connector.startStreaming(anchor)) {
                    is DeviceCommandResult.Failed -> throw result.error
                    else -> Unit
                }
            }
        }
        _recordingState.value = RecordingState(
            lifecycle = RecordingLifecycleState.Recording,
            anchor = anchor,
            updatedAt = nowInstant()
        )
    }

    override suspend fun stopStreaming(): RecordingSessionAnchor? {
        val anchor = _recordingState.value.anchor
        _recordingState.value = RecordingState(
            lifecycle = RecordingLifecycleState.Stopping,
            anchor = anchor,
            updatedAt = nowInstant()
        )
        orderedConnectors.forEach { connector ->
            when (val result = connector.stopStreaming()) {
                is DeviceCommandResult.Failed -> throw result.error
                else -> Unit
            }
        }
        statusMutex.withLock {
            statusesCache.replaceAll { _, _ -> emptyList() }
            _streamStatuses.value = emptyList()
        }
        _recordingState.value = RecordingState(
            lifecycle = RecordingLifecycleState.Idle,
            anchor = null,
            updatedAt = nowInstant()
        )
        return anchor
    }

    override suspend fun collectSessionArtifacts(sessionId: String): List<SessionArtifact> {
        val collected = mutableListOf<SessionArtifact>()
        orderedConnectors.forEach { connector ->
            val artifacts = runCatching { connector.collectArtifacts(sessionId) }
                .getOrElse { emptyList() }
            collected.addAll(artifacts)
        }
        return collected
    }

    private fun devicesSnapshot(): List<SensorDevice> =
        orderedConnectors.mapNotNull { connector ->
            devicesCache[connector.deviceId]
        }
}
