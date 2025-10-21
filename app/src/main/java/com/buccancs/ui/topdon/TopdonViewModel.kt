package com.buccancs.ui.topdon

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.TopdonDeviceState
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.model.TopdonDynamicRange
import com.buccancs.domain.repository.TopdonDeviceRepository
import com.buccancs.domain.repository.TopdonSettingsRepository
import com.buccancs.ui.components.topdon.MeasurementMode
import com.buccancs.ui.topdon.thermal.ThermalMeasurementTarget
import com.buccancs.ui.topdon.thermal.ThermalMeasurementTarget.Area
import com.buccancs.ui.topdon.thermal.ThermalMeasurementTarget.Line
import com.buccancs.ui.topdon.thermal.ThermalMeasurementTarget.Spot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Instant

@HiltViewModel
class TopdonViewModel @Inject constructor(
    private val deviceRepository: TopdonDeviceRepository,
    private val settingsRepository: TopdonSettingsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val streamState =
        deviceRepository.deviceState
    private val previewFrames =
        deviceRepository.previewFrame
    private val settingsFlow =
        settingsRepository.settings
    private val measurementModeState =
        MutableStateFlow(MeasurementMode.SPOT)
    private val measurementTargetState =
        MutableStateFlow<ThermalMeasurementTarget>(
            Spot(DEFAULT_SPOT_X, DEFAULT_SPOT_Y)
        )

    val uiState: StateFlow<TopdonUiState> =
        combine(
            streamState,
            previewFrames,
            settingsFlow,
            measurementModeState,
            measurementTargetState
        ) { deviceState, previewFrame, settings, measurementMode, measurementTarget ->
            TopdonUiState(
                deviceLabel = deviceState.device?.displayName
                    ?: "Topdon TC001",
                connectionStatusLabel = formatConnection(
                    deviceState.device?.connectionStatus
                ),
                isConnected = deviceState.device?.connectionStatus is ConnectionStatus.Connected,
                previewActive = deviceState.previewActive,
                previewFrame = previewFrame,
                lastPreviewTimestamp = deviceState.lastPreviewTimestamp,
                scanning = deviceState.scanning,
                errorMessage = deviceState.lastError,
                settings = settings,
                lastCalibrationTimestamp = deviceState.lastCalibrationTimestamp,
                streamStatuses = deviceState.statuses.map { it.toUiModel() },
                paletteOptions = TopdonPalette.values()
                    .toList(),
                superSamplingOptions = TopdonSuperSamplingFactor.values()
                    .toList(),
                measurementMode = measurementMode,
                measurementTarget = measurementTarget,
                isRecording = deviceState.statuses.any { status ->
                    status.streamType == SensorStreamType.THERMAL_VIDEO && status.isStreaming
                },
                previewStateCode = determinePreviewState(
                    deviceState,
                    previewFrame
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                5_000
            ),
            initialValue = TopdonUiState.initial()
        )

    init {
        val deviceIdArg =
            savedStateHandle.get<String>(
                DEVICE_ID_KEY
            )
                ?.takeIf { it.isNotBlank() }
        if (deviceIdArg != null) {
            viewModelScope.launch {
                deviceRepository.setActiveDevice(
                    DeviceId(
                        deviceIdArg
                    )
                )
            }
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
        val active =
            uiState.value.previewActive
        viewModelScope.launch {
            if (active) {
                deviceRepository.stopPreview()
            } else {
                deviceRepository.startPreview()
            }
        }
    }

    fun setAutoConnect(
        enabled: Boolean
    ) {
        viewModelScope.launch {
            settingsRepository.setAutoConnect(
                enabled
            )
        }
    }

    fun selectPalette(
        palette: TopdonPalette
    ) {
        viewModelScope.launch {
            settingsRepository.setPalette(
                palette
            )
        }
    }

    fun selectSuperSampling(
        factor: TopdonSuperSamplingFactor
    ) {
        viewModelScope.launch {
            settingsRepository.setSuperSampling(
                factor
            )
        }
    }

    fun updatePreviewFps(
        limit: Int
    ) {
        viewModelScope.launch {
            settingsRepository.setPreviewFpsLimit(
                limit
            )
        }
    }

    fun setMeasurementMode(
        mode: MeasurementMode
    ) {
        measurementModeState.value =
            mode
        measurementTargetState.value =
            defaultTargetForMode(
                mode
            )
    }

    fun updateMeasurementTarget(
        target: ThermalMeasurementTarget
    ) {
        measurementTargetState.value =
            target.clampToFrame(
                FRAME_WIDTH,
                FRAME_HEIGHT
            )
    }

    fun setAutoShutter(
        enabled: Boolean
    ) {
        viewModelScope.launch {
            settingsRepository.setAutoShutter(
                enabled
            )
        }
    }

    fun setDynamicRange(
        range: TopdonDynamicRange
    ) {
        viewModelScope.launch {
            settingsRepository.setDynamicRange(
                range
            )
        }
    }

    fun triggerManualCalibration() {
        viewModelScope.launch {
            deviceRepository.triggerManualCalibration()
        }
    }

    fun clearError() {
        viewModelScope.launch { deviceRepository.clearError() }
    }

    fun setActiveDevice(
        deviceId: DeviceId
    ) {
        viewModelScope.launch {
            deviceRepository.setActiveDevice(
                deviceId
            )
        }
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

    private fun determinePreviewState(
        deviceState: TopdonDeviceState,
        previewFrame: TopdonPreviewFrame?
    ): ThermalUiStateCode =
        when {
            deviceState.lastError != null ->
                ThermalUiStateCode.PREVIEW_ERROR

            deviceState.scanning ||
                    deviceState.device?.connectionStatus is ConnectionStatus.Connecting ->
                ThermalUiStateCode.DEVICE_CONNECTING

            deviceState.device?.connectionStatus is ConnectionStatus.Connected &&
                    !deviceState.previewActive ->
                ThermalUiStateCode.DEVICE_READY

            deviceState.previewActive && previewFrame != null ->
                ThermalUiStateCode.PREVIEW_STREAMING

            deviceState.previewActive ->
                ThermalUiStateCode.PREVIEW_BUFFERING

            else -> ThermalUiStateCode.DEVICE_DISCONNECTED
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
                frameRateFps?.let {
                    append(
                        String.format(
                            Locale.US,
                            "%.1f fps",
                            it
                        )
                    )
                }
                if (sampleRateHz != null) {
                    if (isNotEmpty()) append(
                        " | "
                    )
                    append(
                        String.format(
                            Locale.US,
                            "%.1f Hz",
                            sampleRateHz
                        )
                    )
                }
            }
        )

    private fun formatConnection(
        status: ConnectionStatus?
    ): String =
        when (status) {
            is ConnectionStatus.Connected -> "Connected since ${status.since}"
            ConnectionStatus.Connecting -> "Connecting"
            ConnectionStatus.Disconnected, null -> "Disconnected"
        }

    companion object {
        const val DEVICE_ID_KEY =
            "deviceId"
        private const val FRAME_WIDTH = 256
        private const val FRAME_HEIGHT = 192
        private const val DEFAULT_SPOT_X = FRAME_WIDTH / 2
        private const val DEFAULT_SPOT_Y = FRAME_HEIGHT / 2
        private const val DEFAULT_AREA_HALF = 32
    }

    private fun defaultTargetForMode(
        mode: MeasurementMode
    ): ThermalMeasurementTarget =
        when (mode) {
            MeasurementMode.SPOT ->
                Spot(
                    DEFAULT_SPOT_X,
                    DEFAULT_SPOT_Y
                )

            MeasurementMode.AREA ->
                Area(
                    startX = (DEFAULT_SPOT_X - DEFAULT_AREA_HALF).coerceAtLeast(0),
                    startY = (DEFAULT_SPOT_Y - DEFAULT_AREA_HALF).coerceAtLeast(0),
                    endX = (DEFAULT_SPOT_X + DEFAULT_AREA_HALF).coerceAtMost(FRAME_WIDTH - 1),
                    endY = (DEFAULT_SPOT_Y + DEFAULT_AREA_HALF).coerceAtMost(FRAME_HEIGHT - 1)
                )

            MeasurementMode.LINE ->
                Line(
                    startX = 0,
                    startY = DEFAULT_SPOT_Y,
                    endX = FRAME_WIDTH - 1,
                    endY = DEFAULT_SPOT_Y
                )

            MeasurementMode.MAX_MIN ->
                Spot(
                    DEFAULT_SPOT_X,
                    DEFAULT_SPOT_Y
                )
        }
}

data class TopdonUiState(
    val deviceLabel: String,
    val connectionStatusLabel: String,
    val isConnected: Boolean,
    val previewActive: Boolean,
    val previewStateCode: ThermalUiStateCode,
    val previewFrame: TopdonPreviewFrame?,
    val lastPreviewTimestamp: Instant?,
    val scanning: Boolean,
    val errorMessage: String?,
    val settings: TopdonSettings,
    val lastCalibrationTimestamp: Instant?,
    val streamStatuses: List<TopdonStreamStatusUi>,
    val paletteOptions: List<TopdonPalette>,
    val superSamplingOptions: List<TopdonSuperSamplingFactor>,
    val measurementMode: MeasurementMode,
    val measurementTarget: ThermalMeasurementTarget,
    val isRecording: Boolean = false
) {
    val previewStateSummary: String
        get() = "#${previewStateCode.code} ${previewStateCode.label}"

    val previewIsStreaming: Boolean
        get() =
            previewStateCode == ThermalUiStateCode.PREVIEW_STREAMING ||
                    previewStateCode == ThermalUiStateCode.PREVIEW_RECORDING

    val autoConnectEnabled: Boolean
        get() = settings.autoConnectOnAttach

    companion object {
        fun initial(): TopdonUiState =
            TopdonUiState(
                deviceLabel = "Topdon TC001",
                connectionStatusLabel = "Disconnected",
                isConnected = false,
                previewActive = false,
                previewStateCode = ThermalUiStateCode.DEVICE_DISCONNECTED,
                previewFrame = null,
                lastPreviewTimestamp = null,
                scanning = false,
                errorMessage = null,
                settings = TopdonSettings(),
                lastCalibrationTimestamp = null,
                streamStatuses = emptyList(),
                paletteOptions = TopdonPalette.values()
                    .toList(),
                superSamplingOptions = TopdonSuperSamplingFactor.values()
                    .toList(),
                measurementMode = MeasurementMode.SPOT,
                measurementTarget = Spot(128, 96)
            )
    }
}

data class TopdonStreamStatusUi(
    val label: String,
    val detail: String
)
