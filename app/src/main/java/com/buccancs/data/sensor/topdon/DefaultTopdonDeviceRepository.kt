package com.buccancs.data.sensor.topdon

import com.buccancs.data.sensor.connector.topdon.TopdonConnectorManager
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.TOPDON_TC001_DEVICE_ID
import com.buccancs.domain.model.TopdonDeviceState
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.TopdonDeviceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultTopdonDeviceRepository @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val sensorRepository: SensorRepository,
    private val connectorManager: TopdonConnectorManager,
    private val hardwareConfigRepository: SensorHardwareConfigRepository
) : TopdonDeviceRepository {
    private val errors = MutableStateFlow<String?>(null)
    private val scanning = MutableStateFlow(false)
    private val deviceStateFlow = MutableStateFlow(
        TopdonDeviceState(
            device = null,
            statuses = emptyList(),
            previewActive = false,
            lastPreviewTimestamp = null,
            scanning = false,
            lastError = null
        )
    )

    private val _activeDeviceId = MutableStateFlow(TOPDON_TC001_DEVICE_ID)
    override val activeDeviceId: StateFlow<DeviceId> = _activeDeviceId.asStateFlow()

    private val previewFrameFlow: StateFlow<TopdonPreviewFrame?> = activeDeviceId
        .flatMapLatest { id -> connectorManager.previewFrame(id) ?: flowOf(null) }
        .stateIn(scope, SharingStarted.Eagerly, null)

    private val previewRunningFlow: StateFlow<Boolean> = activeDeviceId
        .flatMapLatest { id -> connectorManager.previewRunning(id) ?: flowOf(false) }
        .stateIn(scope, SharingStarted.Eagerly, false)

    override val deviceState: StateFlow<TopdonDeviceState> = deviceStateFlow.asStateFlow()
    override val previewFrame: StateFlow<TopdonPreviewFrame?> = previewFrameFlow

    private data class TopdonSnapshot(
        val deviceId: DeviceId,
        val devices: List<SensorDevice>,
        val statuses: List<SensorStreamStatus>,
        val previewActive: Boolean,
        val frame: TopdonPreviewFrame?
    )

    init {
        val snapshotFlow = combine(
            sensorRepository.devices,
            sensorRepository.streamStatuses,
            previewRunningFlow,
            previewFrameFlow,
            activeDeviceId
        ) { devices, statuses, previewActive, frame, deviceId ->
            TopdonSnapshot(
                deviceId = deviceId,
                devices = devices,
                statuses = statuses,
                previewActive = previewActive,
                frame = frame
            )
        }
        scope.launch(Dispatchers.Default) {
            snapshotFlow
                .combine(scanning) { snapshot, scanningFlag -> snapshot to scanningFlag }
                .combine(errors) { (snapshot, scanningFlag), errorMessage ->
                    Triple(snapshot, scanningFlag, errorMessage)
                }
                .collect { (snapshot, scanningFlag, errorMessage) ->
                    val device = snapshot.devices.firstOrNull { it.id == snapshot.deviceId }
                    val streamStatuses = snapshot.statuses.filter { it.deviceId == snapshot.deviceId }
                    deviceStateFlow.value = TopdonDeviceState(
                        device = device,
                        statuses = streamStatuses,
                        previewActive = snapshot.previewActive,
                        lastPreviewTimestamp = snapshot.frame?.timestamp,
                        scanning = scanningFlag,
                        lastError = errorMessage
                    )
                }
        }
    }

    init {
        scope.launch(Dispatchers.Default) {
            hardwareConfigRepository.config.collect { config ->
                val available = config.topdon.mapNotNull { entry ->
                    entry.id.takeIf { it.isNotBlank() }?.let(::DeviceId)
                }
                if (available.isEmpty()) {
                    return@collect
                }
                val current = _activeDeviceId.value
                if (available.none { it == current }) {
                    _activeDeviceId.value = available.first()
                }
            }
        }
    }

    override suspend fun refresh() {
        scanning.value = true
        try {
            sensorRepository.refreshInventory()
        } catch (t: Throwable) {
            errors.value = t.message ?: "Topdon refresh failed."
        } finally {
            scanning.value = false
        }
    }

    override suspend fun connect() {
        errors.value = null
        val deviceId = _activeDeviceId.value
        runCatching { sensorRepository.connect(deviceId) }
            .onFailure { t -> errors.value = t.message ?: "Unable to connect to Topdon device." }
    }

    override suspend fun disconnect() {
        errors.value = null
        val deviceId = _activeDeviceId.value
        runCatching { sensorRepository.disconnect(deviceId) }
            .onFailure { t -> errors.value = t.message ?: "Unable to disconnect Topdon device." }
    }

    override suspend fun startPreview() {
        val result = connectorManager.startPreview(_activeDeviceId.value)
        when (result) {
            is DeviceCommandResult.Rejected -> errors.value = result.reason
            is DeviceCommandResult.Failed -> errors.value = result.error.message
            else -> errors.value = null
        }
    }

    override suspend fun stopPreview() {
        val result = connectorManager.stopPreview(_activeDeviceId.value)
        if (result is DeviceCommandResult.Failed) {
            errors.value = result.error.message
        } else {
            errors.value = null
        }
    }

    override suspend fun setActiveDevice(deviceId: DeviceId) {
        _activeDeviceId.value = deviceId
        hardwareConfigRepository.updateConfig { config ->
            val reordered = config.topdon.toMutableList()
            val index = reordered.indexOfFirst { it.id == deviceId.value }
            if (index > 0) {
                val selected = reordered.removeAt(index)
                reordered.add(0, selected)
                config.copy(topdon = reordered)
            } else {
                config
            }
        }
    }

    override suspend fun clearError() {
        errors.value = null
    }
}

