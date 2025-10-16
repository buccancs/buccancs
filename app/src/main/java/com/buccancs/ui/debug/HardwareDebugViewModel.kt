package com.buccancs.ui.debug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.debug.BluetoothInfo
import com.buccancs.debug.HardwareDebugger
import com.buccancs.debug.SystemInfo
import com.buccancs.debug.UsbDeviceInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HardwareDebugViewModel @Inject constructor(
    private val hardwareDebugger: HardwareDebugger
) : ViewModel() {

    private val _uiState = MutableStateFlow(HardwareDebugUiState())
    val uiState: StateFlow<HardwareDebugUiState> = _uiState.asStateFlow()

    init {
        startDebugSession()
    }

    fun startDebugSession() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isScanning = true) }

            try {
                hardwareDebugger.startDebugSession()

                val systemInfo = hardwareDebugger.getSystemInfo()
                val usbDevices = hardwareDebugger.scanUsbDevices()
                val bluetoothInfo = hardwareDebugger.scanBluetoothDevices()
                val logPath = hardwareDebugger.retrieveDebugLogFile().absolutePath

                _uiState.update {
                    it.copy(
                        systemInfo = systemInfo,
                        usbDevices = usbDevices,
                        bluetoothInfo = bluetoothInfo,
                        logFilePath = logPath,
                        isScanning = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isScanning = false) }
            }
        }
    }

    fun saveDebugLog() {
        viewModelScope.launch(Dispatchers.IO) {
            hardwareDebugger.endDebugSession()
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveDebugLog()
    }
}

data class HardwareDebugUiState(
    val systemInfo: SystemInfo? = null,
    val usbDevices: List<UsbDeviceInfo> = emptyList(),
    val bluetoothInfo: BluetoothInfo? = null,
    val logFilePath: String? = null,
    val isScanning: Boolean = false
)
