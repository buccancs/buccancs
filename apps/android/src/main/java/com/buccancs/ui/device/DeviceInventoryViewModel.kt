package com.buccancs.ui.device

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.buccancs.domain.repository.TopdonDeviceRepository
import com.buccancs.domain.ui.DeviceUiMapper
import com.buccancs.domain.usecase.DeviceManagementUseCase
import com.buccancs.ui.DeviceUiModel
import com.buccancs.ui.InventoryUiModel
import com.buccancs.ui.toInventoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for device inventory, connection management, and simulation control.
 * Extracted from MainViewModel to focus on device management concerns.
 */
@HiltViewModel
class DeviceInventoryViewModel @Inject constructor(
    private val deviceManagement: DeviceManagementUseCase,
    private val sensorRepository: SensorRepository,
    private val hardwareConfigRepository: SensorHardwareConfigRepository,
    private val topdonDeviceRepository: TopdonDeviceRepository,
    private val shimmerSettingsRepository: ShimmerSettingsRepository,
    private val deviceUiMapper: DeviceUiMapper
) : ViewModel() {

    private val inventoryState =
        combine(
            hardwareConfigRepository.config,
            sensorRepository.devices,
            topdonDeviceRepository.activeDeviceId
        ) { config, devices, activeTopdon ->
            config.toInventoryUiModel(
                devices,
                activeTopdon
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                5_000
            ),
            initialValue = InventoryUiModel.empty()
        )

    val uiState: StateFlow<DeviceInventoryUiState> =
        combine(
            deviceManagement.simulationEnabled,
            deviceManagement.devices,
            sensorRepository.streamStatuses,
            shimmerSettingsRepository.settings,
            inventoryState
        ) { simulation, devices, streams, shimmerSettings, inventory ->
            val sortedDevices =
                devices
                    .sortedWith(
                        compareByDescending<SensorDevice> { it.connectionStatus is ConnectionStatus.Connected }
                            .thenBy { it.id.value })
                    .map {
                        deviceUiMapper.toUiModel(
                            it,
                            streams,
                            shimmerSettings
                        )
                    }

            DeviceInventoryUiState(
                simulationEnabled = simulation,
                devices = sortedDevices,
                inventory = inventory,
                errorMessage = null
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                5_000
            ),
            initialValue = DeviceInventoryUiState.initial()
        )

    fun toggleSimulation() {
        viewModelScope.launch {
            deviceManagement.toggleSimulation()
        }
    }

    fun connectDevice(
        id: DeviceId
    ) {
        viewModelScope.launch {
            deviceManagement.connectDevice(
                id
            )
        }
    }

    fun disconnectDevice(
        id: DeviceId
    ) {
        viewModelScope.launch {
            deviceManagement.disconnectDevice(
                id
            )
        }
    }

    fun refreshInventory() {
        viewModelScope.launch {
            deviceManagement.refreshInventory()
        }
    }
}

data class DeviceInventoryUiState(
    val simulationEnabled: Boolean,
    val devices: List<DeviceUiModel>,
    val inventory: InventoryUiModel,
    val errorMessage: String?
) {
    companion object {
        fun initial(): DeviceInventoryUiState =
            DeviceInventoryUiState(
                simulationEnabled = false,
                devices = emptyList(),
                inventory = InventoryUiModel.empty(),
                errorMessage = null
            )
    }
}
