package com.buccancs.data.sensor

import com.buccancs.data.sensor.connector.MultiDeviceConnector
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
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSensorRepository @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    singleConnectorSet: Set<@JvmSuppressWildcards SensorConnector>,
    multiConnectorSet: Set<@JvmSuppressWildcards MultiDeviceConnector>
) : SensorRepository {
    private val singleConnectors = singleConnectorSet.sortedBy { it.deviceId.value }
    private val multiConnectors = multiConnectorSet.toList()
    private val devicesCache = mutableMapOf<DeviceId, SensorDevice>()
    private val statusesCache = mutableMapOf<DeviceId, List<SensorStreamStatus>>()
    private val devicesMutex = Mutex()
    private val statusMutex = Mutex()
    private val connectorHandles = mutableMapOf<DeviceId, ConnectorHandle>()
    private val multiConnectorDevices = mutableMapOf<MultiDeviceConnector, MutableSet<DeviceId>>()
    private val _simulationEnabled = MutableStateFlow(false)
    override val simulationEnabled: StateFlow<Boolean> = _simulationEnabled.asStateFlow()
    private val _devices = MutableStateFlow<List<SensorDevice>>(emptyList())
    override val devices: StateFlow<List<SensorDevice>> = _devices.asStateFlow()
    private val _streamStatuses = MutableStateFlow<List<SensorStreamStatus>>(emptyList())
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
        singleConnectors.forEach { attachSingleConnector(it) }
        multiConnectors.forEach { attachMultiConnector(it) }
        publishInitialSnapshots()
    }

    override suspend fun refreshInventory() {
        singleConnectors.forEach { connector ->
            connector.refreshInventory()
        }
        multiConnectors.forEach { connector ->
            connector.refreshInventory()
        }
    }

    override suspend fun connect(deviceId: DeviceId) {
        when (val handle = connectorHandles[deviceId]) {
            is ConnectorHandle.Single -> handle.connector.connect()
            is ConnectorHandle.Multi -> handle.connector.connect(deviceId)
            null -> Unit
        }
    }

    override suspend fun disconnect(deviceId: DeviceId) {
        when (val handle = connectorHandles[deviceId]) {
            is ConnectorHandle.Single -> handle.connector.disconnect()
            is ConnectorHandle.Multi -> handle.connector.disconnect(deviceId)
            null -> Unit
        }
    }

    override suspend fun configure(deviceId: DeviceId, options: Map<String, String>) {
        val result = when (val handle = connectorHandles[deviceId]) {
            is ConnectorHandle.Single -> handle.connector.configure(options)
            is ConnectorHandle.Multi -> handle.connector.configure(deviceId, options)
            null -> return
        }
        if (result is DeviceCommandResult.Failed) {
            throw result.error
        }
    }

    override suspend fun setSimulationEnabled(enabled: Boolean) {
        if (_simulationEnabled.value == enabled) return
        _simulationEnabled.value = enabled
        singleConnectors.forEach { connector -> connector.applySimulation(enabled) }
        multiConnectors.forEach { connector -> connector.applySimulation(enabled) }
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor) {
        val now = nowInstant()
        _recordingState.value = RecordingState(
            lifecycle = RecordingLifecycleState.Starting,
            anchor = anchor,
            updatedAt = now
        )
        devicesMutex.withLock {
            singleConnectors.forEach { connector ->
                val snapshot = devicesCache[connector.deviceId]
                if (snapshot?.connectionStatus is ConnectionStatus.Connected) {
                    val result = connector.startStreaming(anchor)
                    if (result is DeviceCommandResult.Failed) {
                        throw result.error
                    }
                }
            }
            multiConnectors.forEach { connector ->
                val deviceIds = multiConnectorDevices[connector].orEmpty()
                deviceIds.forEach { deviceId ->
                    val snapshot = devicesCache[deviceId]
                    if (snapshot?.connectionStatus is ConnectionStatus.Connected) {
                        val result = connector.startStreaming(deviceId, anchor)
                        if (result is DeviceCommandResult.Failed) {
                            throw result.error
                        }
                    }
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
        singleConnectors.forEach { connector ->
            val result = connector.stopStreaming()
            if (result is DeviceCommandResult.Failed) {
                throw result.error
            }
        }
        multiConnectors.forEach { connector ->
            val deviceIds = multiConnectorDevices[connector].orEmpty()
            deviceIds.forEach { deviceId ->
                val result = connector.stopStreaming(deviceId)
                if (result is DeviceCommandResult.Failed) {
                    throw result.error
                }
            }
        }
        statusMutex.withLock {
            statusesCache.keys.forEach { key -> statusesCache[key] = emptyList() }
            publishStatusesLocked()
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
        singleConnectors.forEach { connector ->
            val artifacts = runCatching { connector.collectArtifacts(sessionId) }
                .getOrElse { emptyList() }
            collected.addAll(artifacts)
        }
        multiConnectors.forEach { connector ->
            val deviceIds = multiConnectorDevices[connector].orEmpty()
            deviceIds.forEach { deviceId ->
                val artifacts = runCatching { connector.collectArtifacts(deviceId, sessionId) }
                    .getOrElse { emptyList() }
                collected.addAll(artifacts)
            }
        }
        return collected
    }

    private fun attachSingleConnector(connector: SensorConnector) {
        devicesCache[connector.deviceId] = connector.device.value
        statusesCache.putIfAbsent(connector.deviceId, emptyList())
        connectorHandles[connector.deviceId] = ConnectorHandle.Single(connector)
        scope.launch {
            connector.device.collect { device ->
                devicesMutex.withLock {
                    devicesCache[device.id] = device
                    publishDevicesLocked()
                }
            }
        }
        scope.launch {
            connector.streamStatuses.collect { statuses ->
                statusMutex.withLock {
                    statusesCache[connector.deviceId] = statuses
                    publishStatusesLocked()
                }
            }
        }
    }

    private fun attachMultiConnector(connector: MultiDeviceConnector) {
        val known = multiConnectorDevices.getOrPut(connector) { mutableSetOf() }
        connector.devices.value.forEach { (deviceId, device) ->
            known.add(deviceId)
            devicesCache[deviceId] = device
            connectorHandles[deviceId] = ConnectorHandle.Multi(connector)
        }
        connector.streamStatuses.value.forEach { (deviceId, statuses) ->
            statusesCache[deviceId] = statuses
        }
        scope.launch {
            connector.devices.collect { snapshot ->
                val removed: Set<DeviceId>
                devicesMutex.withLock {
                    val incomingIds = snapshot.keys.toSet()
                    removed = known.toSet() - incomingIds
                    removed.forEach { deviceId ->
                        known.remove(deviceId)
                        devicesCache.remove(deviceId)
                        connectorHandles.remove(deviceId)
                    }
                    snapshot.forEach { (deviceId, device) ->
                        if (known.add(deviceId)) {
                            connectorHandles[deviceId] = ConnectorHandle.Multi(connector)
                        }
                        devicesCache[deviceId] = device
                    }
                    publishDevicesLocked()
                }
                if (removed.isNotEmpty()) {
                    statusMutex.withLock {
                        removed.forEach { statusesCache.remove(it) }
                        publishStatusesLocked()
                    }
                }
            }
        }
        scope.launch {
            connector.streamStatuses.collect { statusMap ->
                statusMutex.withLock {
                    statusMap.forEach { (deviceId, statuses) ->
                        statusesCache[deviceId] = statuses
                    }
                    val knownIds = known.toSet()
                    val missing = knownIds - statusMap.keys
                    missing.forEach { deviceId -> statusesCache[deviceId] = emptyList() }
                    publishStatusesLocked()
                }
            }
        }
    }

    private fun publishInitialSnapshots() {
        _devices.value = devicesCache.values.sortedBy { it.id.value }
        _streamStatuses.value = statusesCache.entries
            .sortedBy { it.key.value }
            .flatMap { it.value }
    }

    private fun publishDevicesLocked() {
        _devices.value = devicesCache.values.sortedBy { it.id.value }
    }

    private fun publishStatusesLocked() {
        _streamStatuses.value = statusesCache.entries
            .sortedBy { it.key.value }
            .flatMap { it.value }
    }

    private sealed interface ConnectorHandle {
        data class Single(val connector: SensorConnector) : ConnectorHandle
        data class Multi(val connector: MultiDeviceConnector) : ConnectorHandle
    }
}
