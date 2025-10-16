package com.buccancs.ui.topdon

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.repository.TopdonDeviceRepository
import com.buccancs.domain.repository.TopdonSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Instant

@HiltViewModel
class TopdonViewModel @Inject constructor(
    private val deviceRepository: TopdonDeviceRepository,
    private val settingsRepository: TopdonSettingsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val streamState = deviceRepository.deviceState
    private val previewFrames = deviceRepository.previewFrame
    private val settingsFlow = settingsRepository.settings

    val uiState: StateFlow<TopdonUiState> = combine(
        streamState,
        previewFrames,
        settingsFlow
    ) { deviceState, previewFrame, settings ->
        TopdonUiState(
            deviceLabel = deviceState.device?.displayName ?: "Topdon TC001",
            connectionStatusLabel = formatConnection(deviceState.device?.connectionStatus),
            isConnected = deviceState.device?.connectionStatus is ConnectionStatus.Connected,
            previewActive = deviceState.previewActive,
            previewFrame = previewFrame,
            lastPreviewTimestamp = deviceState.lastPreviewTimestamp,
            scanning = deviceState.scanning,
            errorMessage = deviceState.lastError,
            settings = settings,
            streamStatuses = deviceState.statuses.map { it.toUiModel() },
            paletteOptions = TopdonPalette.values().toList(),
            superSamplingOptions = TopdonSuperSamplingFactor.values().toList()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TopdonUiState.initial()
    )

    init {
        val deviceIdArg = savedStateHandle.get<String>(DEVICE_ID_KEY)?.takeIf { it.isNotBlank() }
        if (deviceIdArg != null) {
            viewModelScope.launch { deviceRepository.setActiveDevice(DeviceId(deviceIdArg)) }
        }
    }

    fun refresh() {
        viewModelScope.launch { deviceRepository.refresh() }
    }

    fun connect() {
        viewModelScope.launch { deviceRepository.connect() }
    }

    fun disconnect() {
        viewModelScope.launch { deviceRepository.disconnect() }
    }

    fun startPreview() {
        viewModelScope.launch { deviceRepository.startPreview() }
    }

    fun stopPreview() {
        viewModelScope.launch { deviceRepository.stopPreview() }
    }

    fun togglePreview() {
        val active = uiState.value.previewActive
        viewModelScope.launch {
            if (active) {
                deviceRepository.stopPreview()
            } else {
                deviceRepository.startPreview()
            }
        }
    }

    fun setAutoConnect(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setAutoConnect(enabled)
        }
    }

    fun selectPalette(palette: TopdonPalette) {
        viewModelScope.launch {
            settingsRepository.setPalette(palette)
        }
    }

    fun selectSuperSampling(factor: TopdonSuperSamplingFactor) {
        viewModelScope.launch {
            settingsRepository.setSuperSampling(factor)
        }
    }

    fun updatePreviewFps(limit: Int) {
        viewModelScope.launch {
            settingsRepository.setPreviewFpsLimit(limit)
        }
    }

    fun clearError() {
        viewModelScope.launch { deviceRepository.clearError() }
    }

    fun setActiveDevice(deviceId: DeviceId) {
        viewModelScope.launch { deviceRepository.setActiveDevice(deviceId) }
    }

    fun capturePhoto() {
        viewModelScope.launch { deviceRepository.capturePhoto() }
    }

    fun startRecording() {
        viewModelScope.launch { deviceRepository.startRecording() }
    }

    fun stopRecording() {
        viewModelScope.launch { deviceRepository.stopRecording() }
    }

    private fun SensorStreamStatus.toUiModel(): TopdonStreamStatusUi =
        TopdonStreamStatusUi(
            label = when (streamType) {
                SensorStreamType.THERMAL_VIDEO -> "Thermal"
                SensorStreamType.PREVIEW -> "Preview"
                SensorStreamType.RGB_VIDEO -> "RGB"
                SensorStreamType.RAW_DNG -> "RAW"
                SensorStreamType.GSR -> "GSR"
                SensorStreamType.AUDIO -> "Audio"
            },
            detail = buildString {
                frameRateFps?.let { append(String.format("%.1f fps", it)) }
                if (sampleRateHz != null) {
                    if (isNotEmpty()) append(" | ")
                    append(String.format("%.1f Hz", sampleRateHz))
                }
            }
        )

    private fun formatConnection(status: ConnectionStatus?): String = when (status) {
        is ConnectionStatus.Connected -> "Connected since ${status.since}"
        ConnectionStatus.Connecting -> "Connecting"
        ConnectionStatus.Disconnected, null -> "Disconnected"
    }

    companion object {
        const val DEVICE_ID_KEY = "deviceId"
    }
}

data class TopdonUiState(
    val deviceLabel: String,
    val connectionStatusLabel: String,
    val isConnected: Boolean,
    val previewActive: Boolean,
    val previewFrame: TopdonPreviewFrame?,
    val lastPreviewTimestamp: Instant?,
    val scanning: Boolean,
    val errorMessage: String?,
    val settings: TopdonSettings,
    val streamStatuses: List<TopdonStreamStatusUi>,
    val paletteOptions: List<TopdonPalette>,
    val superSamplingOptions: List<TopdonSuperSamplingFactor>,
    val isRecording: Boolean = false
) {
    val autoConnectEnabled: Boolean
        get() = settings.autoConnectOnAttach

    companion object {
        fun initial(): TopdonUiState = TopdonUiState(
            deviceLabel = "Topdon TC001",
            connectionStatusLabel = "Disconnected",
            isConnected = false,
            previewActive = false,
            previewFrame = null,
            lastPreviewTimestamp = null,
            scanning = false,
            errorMessage = null,
            settings = TopdonSettings(),
            streamStatuses = emptyList(),
            paletteOptions = TopdonPalette.values().toList(),
            superSamplingOptions = TopdonSuperSamplingFactor.values().toList()
        )
    }
}

data class TopdonStreamStatusUi(
    val label: String,
    val detail: String
)
