package com.buccancs.ui.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.usecase.RgbCameraField
import com.buccancs.domain.usecase.RgbCameraStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.buccancs.core.result.exceptionOrNull

@HiltViewModel
class RgbCameraSettingsViewModel @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val cameraStateManager: RgbCameraStateManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(RgbCameraSettingsUiState())
    val uiState: StateFlow<RgbCameraSettingsUiState> = _uiState.asStateFlow()

    private var activeDeviceId: DeviceId? = null

    fun setActiveDevice(deviceId: DeviceId) {
        activeDeviceId = deviceId
        viewModelScope.launch {
            sensorRepository.devices.collect { devices ->
                val device = devices.firstOrNull { it.id == deviceId }
                device?.let {
                    val cameraState = cameraStateManager.ensureState(it)
                    updateFromCameraState(cameraState)
                }
            }
        }
        viewModelScope.launch {
            cameraStateManager.cameraInputs.collect { inputs ->
                inputs[deviceId]?.let { cameraState ->
                    updateFromCameraState(cameraState)
                }
            }
        }
    }

    fun updateField(field: RgbCameraField, value: String) {
        val id = activeDeviceId ?: return
        cameraStateManager.updateField(id, field, value)
    }

    fun toggleRawEnabled(enabled: Boolean) {
        val id = activeDeviceId ?: return
        cameraStateManager.toggleRawEnabled(id, enabled)
    }

    fun selectAwb(awbValue: String) {
        val id = activeDeviceId ?: return
        cameraStateManager.selectAwb(id, awbValue)
    }

    fun resetSettings() {
        val id = activeDeviceId ?: return
        cameraStateManager.resetSettings(id)
    }

    fun applySettings() {
        val id = activeDeviceId ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(applying = true) }
            val result = cameraStateManager.applySettings(id)
            _uiState.update {
                it.copy(
                    applying = false,
                    applyError = result.exceptionOrNull()?.message
                )
            }
        }
    }

    private fun updateFromCameraState(state: com.buccancs.domain.usecase.RgbCameraInputState) {
        _uiState.update {
            it.copy(
                supportsRaw = state.supportsRaw,
                videoFps = state.inputs.videoFps,
                videoBitRate = state.inputs.videoBitRate,
                rawEnabled = state.inputs.rawEnabled,
                rawIntervalMs = state.inputs.rawIntervalMs,
                exposureNs = state.inputs.exposureNs,
                iso = state.inputs.iso,
                focusMeters = state.inputs.focusMeters,
                awbMode = state.inputs.awbMode,
                dirty = state.dirty,
                errors = state.errors
            )
        }
    }
}

data class RgbCameraSettingsUiState(
    val supportsRaw: Boolean = false,
    val videoFps: String = "30",
    val videoBitRate: String = "8000000",
    val rawEnabled: Boolean = false,
    val rawIntervalMs: String = "1000",
    val exposureNs: String = "",
    val iso: String = "",
    val focusMeters: String = "",
    val awbMode: String = "auto",
    val dirty: Boolean = false,
    val applying: Boolean = false,
    val applyError: String? = null,
    val errors: Map<RgbCameraField, String> = emptyMap()
)
