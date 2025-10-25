package com.buccancs.ui.shimmer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.core.serialization.JsonConfig
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
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

    private val json =
        JsonConfig.standard
    private val candidateSerializer =
        ListSerializer(
            com.buccancs.domain.model.ShimmerDeviceCandidate.serializer()
        )

    private val liveDataCache =
        ArrayDeque<SampleEntry>()

    private val _uiState =
        MutableStateFlow(
            ShimmerScreenUiState.initial()
        )
    val uiState: StateFlow<ShimmerScreenUiState> =
        _uiState.asStateFlow()

    private val deviceId =
        SHIMMER_PRIMARY_DEVICE_ID

    init {
        observeDeviceState()
    }

    private fun observeDeviceState() {
        viewModelScope.launch {
            combine(
                deviceManagement.devices,
                sensorRepository.streamStatuses
            ) { devices, streamStatuses ->
                val shimmerDevice =
                    devices.find { it.id == deviceId }
                val streamingActive =
                    streamStatuses.any { status ->
                        status.deviceId == deviceId &&
                                status.isStreaming &&
                        status.streamType != com.buccancs.domain.model.SensorStreamType.PREVIEW
                    }
                shimmerDevice to streamingActive
            }.collect { (device, streamingActive) ->
                if (device == null) {
                    _uiState.value =
                        ShimmerScreenUiState.initial()
                    return@collect
                }
                val isConnected =
                    device.connectionStatus is ConnectionStatus.Connected
                val isConnecting =
                    device.connectionStatus is ConnectionStatus.Connecting
                val attributes =
                    device.attributes
                val candidates =
                    attributes["shimmer.candidates"]?.let { encoded ->
                        runCatching {
                            json.decodeFromString(
                                candidateSerializer,
                                encoded
                            )
                        }.getOrElse { emptyList() }
                    } ?: emptyList()
                val deviceOptions =
                    candidates.sortedWith(
                        compareBy<com.buccancs.domain.model.ShimmerDeviceCandidate> {
                            it.name?.lowercase() ?: it.mac.lowercase()
                        }.thenBy { it.mac }
                    ).map {
                        ShimmerDeviceOption(
                            mac = it.mac,
                            name = it.name,
                            rssi = it.rssi
                        )
                    }
                val selectedMac =
                    (attributes["shimmer.selected"]
                        ?: attributes["shimmer.last_bonded_mac"])
                        ?.takeIf { it.isNotBlank() }
                val selectedName =
                    deviceOptions.firstOrNull { option ->
                        option.mac.equals(
                            selectedMac,
                            ignoreCase = true
                        )
                    }?.name
                val lastSampleTimestamp =
                    attributes[ATTR_LAST_SAMPLE_TIMESTAMP]?.toDoubleOrNull()
                        ?: _uiState.value.timestamp
                val lastResistanceOhms =
                    attributes[ATTR_LAST_RESISTANCE]?.toDoubleOrNull()
                val lastConductanceMicrosiemens =
                    attributes[ATTR_LAST_CONDUCTANCE]?.toDoubleOrNull()
                val resistanceKOhm =
                    lastResistanceOhms?.div(
                        1000.0
                    )
                updateLiveDataCache(
                    timestampEpochMs = lastSampleTimestamp,
                    conductanceMicrosiemens = lastConductanceMicrosiemens,
                    resistanceKOhm = resistanceKOhm
                )
                _uiState.value =
                    _uiState.value.copy(
                        isConnected = isConnected,
                        isConnecting = isConnecting,
                        isStreaming = streamingActive,
                        deviceName = if (isConnected) device.displayName else null,
                        deviceAddress = if (isConnected) {
                            attributes["shimmer.selected"]
                                ?: attributes["last_bonded_mac"]
                        } else null,
                        deviceOptions = deviceOptions,
                        selectedDeviceMac = selectedMac,
                        selectedDeviceName = selectedName,
                        timestamp = lastSampleTimestamp,
                        gsrData = resistanceKOhm,
                        gsrConductance = lastConductanceMicrosiemens,
                        history = liveDataCache.toList()
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
            _uiState.value =
                _uiState.value.copy(
                    errorMessage = null
                )
            val result =
                deviceManagement.connectDevice(
                    deviceId
                )
            if (result.isFailure) {
                _uiState.value =
                    _uiState.value.copy(
                        errorMessage = result.exceptionOrNull()?.message
                            ?: "Failed to connect"
                    )
            }
        }
    }

    /**
     * Persist the chosen Shimmer MAC address so subsequent connects target the selected device.
     */
    fun selectDevice(
        macAddress: String
    ) {
        val normalized =
            macAddress.trim().uppercase()
        viewModelScope.launch {
            val result =
                hardwareConfiguration.configureShimmerMacAddress(
                    deviceId,
                    normalized
                )
            _uiState.update { current ->
                if (result.isSuccess) {
                    current.copy(
                        selectedDeviceMac = normalized,
                        selectedDeviceName = current.deviceOptions.firstOrNull { option ->
                            option.mac.equals(
                                normalized,
                                ignoreCase = true
                            )
                        }?.name
                    )
                } else {
                    current.copy(
                        errorMessage = result.exceptionOrNull()?.message
                            ?: "Failed to update device selection"
                    )
                }
            }
        }
    }

    /**
     * Disconnects from the Shimmer device and clears all streaming data.
     */
    fun disconnectDevice() {
        viewModelScope.launch {
            _uiState.value =
                _uiState.value.copy(
                    errorMessage = null
                )
            val result =
                deviceManagement.disconnectDevice(
                    deviceId
                )
            if (result.isFailure) {
                _uiState.value =
                    _uiState.value.copy(
                        errorMessage = result.exceptionOrNull()?.message
                            ?: "Failed to disconnect"
                    )
            }
            _uiState.value =
                _uiState.value.copy(
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
            _uiState.value =
                _uiState.value.copy(
                    isScanning = true,
                    errorMessage = null
                )
            val result =
                deviceManagement.refreshInventory()
            _uiState.value =
                _uiState.value.copy(
                    isScanning = false
                )
            if (result.isFailure) {
                _uiState.value =
                    _uiState.value.copy(
                        errorMessage = result.exceptionOrNull()?.message
                            ?: "Failed to scan for devices"
                    )
            }
        }
    }

    /**
     * Updates the GSR sensor range configuration.
     * @param rangeIndex Index of the selected range (0-4)
     */
    fun updateGsrRange(
        rangeIndex: Int
    ) {
        viewModelScope.launch {
            val result =
                hardwareConfiguration.configureShimmerGsrRange(
                    deviceId,
                    rangeIndex
                )
            if (result.isSuccess) {
                _uiState.value =
                    _uiState.value.copy(
                        gsrRangeIndex = rangeIndex
                    )
            } else {
                _uiState.value =
                    _uiState.value.copy(
                        errorMessage = result.exceptionOrNull()?.message
                            ?: "Failed to update GSR range"
                    )
            }
        }
    }

    /**
     * Updates the sampling rate of the Shimmer device.
     * @param sampleRate Sampling rate in Hz
     */
    fun updateSampleRate(
        sampleRate: Double
    ) {
        viewModelScope.launch {
            val result =
                hardwareConfiguration.configureShimmerSampleRate(
                    deviceId,
                    sampleRate
                )
            if (result.isSuccess) {
                _uiState.value =
                    _uiState.value.copy(
                        sampleRate = sampleRate
                    )
            } else {
                _uiState.value =
                    _uiState.value.copy(
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
        _uiState.value =
            _uiState.value.copy(
                errorMessage = null
            )
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
    val deviceOptions: List<ShimmerDeviceOption>,
    val selectedDeviceMac: String?,
    val selectedDeviceName: String?,
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
    val gsrConductance: Double?,
    val history: List<SampleEntry>,
    val errorMessage: String?
) {
    val gsrRangeLabel: String
        get() = gsrRangeOptions.getOrNull(
            gsrRangeIndex
        )
            ?: gsrRangeOptions.first()

    companion object {
        fun initial(): ShimmerScreenUiState =
            ShimmerScreenUiState(
                isConnected = false,
                isConnecting = false,
                isStreaming = false,
                isScanning = false,
                deviceOptions = emptyList(),
                selectedDeviceMac = null,
                selectedDeviceName = null,
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
                sampleRateOptions = listOf(
                    8.0,
                    16.0,
                    32.0,
                    64.0,
                    128.0,
                    256.0
                ),
                timestamp = null,
                accelX = null,
                accelY = null,
                accelZ = null,
                gsrData = null,
                gsrConductance = null,
                history = emptyList(),
                errorMessage = null
            )
    }
}

data class ShimmerDeviceOption(
    val mac: String,
    val name: String?,
    val rssi: Int?
)

data class SampleEntry(
    val timestampMillis: Long,
    val conductanceMicrosiemens: Double?,
    val resistanceKOhm: Double?
)

private const val ATTR_LAST_SAMPLE_TIMESTAMP =
    "shimmer.last_sample_timestamp_ms"
private const val ATTR_LAST_CONDUCTANCE =
    "shimmer.last_conductance_microsiemens"
private const val ATTR_LAST_RESISTANCE =
    "shimmer.last_resistance_ohms"

private const val LIVE_HISTORY_WINDOW_MS =
    30_000L
