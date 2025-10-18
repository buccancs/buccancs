package com.buccancs.ui.shimmer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.SHIMMER_PRIMARY_DEVICE_ID
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.usecase.DeviceManagementUseCase
import com.buccancs.domain.usecase.HardwareConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Shimmer device screen.
 * Manages device connection, streaming, configuration, and real-time data display.
 */
@HiltViewModel
class ShimmerScreenViewModel @Inject constructor(
    private val hardwareConfiguration: HardwareConfigurationUseCase,
    private val deviceManagement: DeviceManagementUseCase,
    private val sensorRepository: SensorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShimmerScreenUiState.initial())
    val uiState: StateFlow<ShimmerScreenUiState> = _uiState.asStateFlow()

    private val deviceId = SHIMMER_PRIMARY_DEVICE_ID

    init {
        observeDeviceState()
    }

    private fun observeDeviceState() {
        viewModelScope.launch {
            combine(
                deviceManagement.devices,
                sensorRepository.streamStatuses
            ) { devices, streamStatuses ->
                val shimmerDevice = devices.find { it.id == deviceId }
                val streamingActive = streamStatuses.any { status ->
                    status.deviceId == deviceId &&
                            status.isStreaming &&
                            status.streamType != com.buccancs.domain.model.SensorStreamType.PREVIEW
                }
                shimmerDevice to streamingActive
            }.collect { (device, streamingActive) ->
                if (device == null) {
                    _uiState.value = ShimmerScreenUiState.initial()
                    return@collect
                }
                val isConnected = device.connectionStatus is ConnectionStatus.Connected
                val isConnecting = device.connectionStatus is ConnectionStatus.Connecting
                _uiState.value = _uiState.value.copy(
                    isConnected = isConnected,
                    isConnecting = isConnecting,
                    isStreaming = streamingActive,
                    deviceName = if (isConnected) device.displayName else null,
                    deviceAddress = if (isConnected) {
                        device.attributes["shimmer.selected"]
                            ?: device.attributes["last_bonded_mac"]
                    } else null
                )
            }
        }
    }

    /**
     * Initiates connection to the Shimmer device.
     * Sets connecting state during the process.
     */
    fun connectDevice() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(errorMessage = null)
            val result = deviceManagement.connectDevice(deviceId)
            if (result.isFailure) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = result.exceptionOrNull()?.message ?: "Failed to connect"
                )
            }
        }
    }

    /**
     * Disconnects from the Shimmer device and clears all streaming data.
     */
    fun disconnectDevice() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(errorMessage = null)
            val result = deviceManagement.disconnectDevice(deviceId)
            if (result.isFailure) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = result.exceptionOrNull()?.message ?: "Failed to disconnect"
                )
            }
            _uiState.value = _uiState.value.copy(
                timestamp = null,
                accelX = null,
                accelY = null,
                accelZ = null,
                gsrData = null
            )
        }
    }

    /**
     * Scans for available Shimmer devices via BLE.
     */
    fun scanForDevices() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isScanning = true, errorMessage = null)
            val result = deviceManagement.refreshInventory()
            _uiState.value = _uiState.value.copy(isScanning = false)
            if (result.isFailure) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = result.exceptionOrNull()?.message ?: "Failed to scan for devices"
                )
            }
        }
    }

    /**
     * Updates the GSR sensor range configuration.
     * @param rangeIndex Index of the selected range (0-4)
     */
    fun updateGsrRange(rangeIndex: Int) {
        viewModelScope.launch {
            val result = hardwareConfiguration.configureShimmerGsrRange(deviceId, rangeIndex)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(gsrRangeIndex = rangeIndex)
            } else {
                _uiState.value = _uiState.value.copy(
                    errorMessage = result.exceptionOrNull()?.message ?: "Failed to update GSR range"
                )
            }
        }
    }

    /**
     * Updates the sampling rate of the Shimmer device.
     * @param sampleRate Sampling rate in Hz
     */
    fun updateSampleRate(sampleRate: Double) {
        viewModelScope.launch {
            val result = hardwareConfiguration.configureShimmerSampleRate(deviceId, sampleRate)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(sampleRate = sampleRate)
            } else {
                _uiState.value = _uiState.value.copy(
                    errorMessage = result.exceptionOrNull()?.message
                        ?: "Failed to update sample rate"
                )
            }
        }
    }

    /**
     * Clears the current error message from the UI state.
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

/**
 * UI state for the Shimmer device screen.
 * Contains all connection, streaming, configuration, and sensor data state.
 */
data class ShimmerScreenUiState(
    val isConnected: Boolean,
    val isConnecting: Boolean,
    val isStreaming: Boolean,
    val isScanning: Boolean,
    val deviceName: String?,
    val deviceAddress: String?,
    val gsrRangeIndex: Int,
    val gsrRangeOptions: List<String>,
    val sampleRate: Double,
    val sampleRateOptions: List<Double>,
    val timestamp: Double?,
    val accelX: Double?,
    val accelY: Double?,
    val accelZ: Double?,
    val gsrData: Double?,
    val errorMessage: String?
) {
    val gsrRangeLabel: String
        get() = gsrRangeOptions.getOrNull(gsrRangeIndex) ?: gsrRangeOptions.first()

    companion object {
        fun initial(): ShimmerScreenUiState = ShimmerScreenUiState(
            isConnected = false,
            isConnecting = false,
            isStreaming = false,
            isScanning = false,
            deviceName = null,
            deviceAddress = null,
            gsrRangeIndex = 0,
            gsrRangeOptions = listOf(
                "10 - 56 kOhm",
                "56 - 220 kOhm",
                "220 - 680 kOhm",
                "680 - 4700 kOhm",
                "Auto Range"
            ),
            sampleRate = 64.0,
            sampleRateOptions = listOf(8.0, 16.0, 32.0, 64.0, 128.0, 256.0),
            timestamp = null,
            accelX = null,
            accelY = null,
            accelZ = null,
            gsrData = null,
            errorMessage = null
        )
    }
}
