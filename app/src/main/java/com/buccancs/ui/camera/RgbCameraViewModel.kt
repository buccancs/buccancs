package com.buccancs.ui.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.usecase.RgbCameraStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RgbCameraViewModel @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val cameraStateManager: RgbCameraStateManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(RgbCameraUiState())
    val uiState: StateFlow<RgbCameraUiState> = _uiState.asStateFlow()

    private var activeDeviceId: DeviceId? = null

    fun setActiveDevice(deviceId: DeviceId) {
        activeDeviceId = deviceId
        viewModelScope.launch {
            sensorRepository.devices.collect { devices ->
                val device = devices.firstOrNull { it.id == deviceId }
                device?.let {
                    val statuses = sensorRepository.streamStatuses.value.filter { it.deviceId == deviceId }
                    updateFromDevice(it, statuses.firstOrNull { it.streamType == SensorStreamType.PREVIEW })
                }
            }
        }
    }

    fun refresh() {
        val id = activeDeviceId ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(scanning = true) }
            sensorRepository.refreshInventory()
            _uiState.update { it.copy(scanning = false) }
        }
    }

    fun connect() {
        val id = activeDeviceId ?: return
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(scanning = true, errorMessage = null) }
                sensorRepository.connect(id)
                _uiState.update { it.copy(scanning = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        scanning = false,
                        errorMessage = e.message ?: "Connection failed"
                    )
                }
            }
        }
    }

    fun disconnect() {
        val id = activeDeviceId ?: return
        viewModelScope.launch {
            sensorRepository.disconnect(id)
        }
    }

    fun startPreview() {
        val id = activeDeviceId ?: return
        _uiState.update { it.copy(previewActive = true) }
    }

    fun stopPreview() {
        _uiState.update { it.copy(previewActive = false) }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    private fun updateFromDevice(device: SensorDevice, previewStatus: com.buccancs.domain.model.SensorStreamStatus?) {
        val isConnected = device.connectionStatus is ConnectionStatus.Connected
        val previewActive = previewStatus?.isStreaming == true
        val frameRate = previewStatus?.frameRateFps?.toInt() ?: 30

        _uiState.update {
            it.copy(
                deviceLabel = device.displayName,
                connectionStatusLabel = when (device.connectionStatus) {
                    is ConnectionStatus.Connected -> "Connected"
                    is ConnectionStatus.Connecting -> "Connecting..."
                    is ConnectionStatus.Disconnected -> "Disconnected"
                },
                isConnected = isConnected,
                previewActive = previewActive,
                frameRate = frameRate
            )
        }
    }
}

data class RgbCameraUiState(
    val deviceLabel: String = "RGB Camera",
    val connectionStatusLabel: String = "Disconnected",
    val isConnected: Boolean = false,
    val previewActive: Boolean = false,
    val frameRate: Int = 30,
    val scanning: Boolean = false,
    val errorMessage: String? = null
)
