package com.buccancs.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.EventMarkerCommandPayload
import com.buccancs.control.commands.StartRecordingCommandPayload
import com.buccancs.control.commands.StimulusCommandPayload
import com.buccancs.control.commands.StopRecordingCommandPayload
import com.buccancs.control.commands.SyncSignalCommandPayload
import com.buccancs.data.control.CommandCoordinator
import com.buccancs.domain.RecordingCoordinator
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.model.UploadStatus
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.TimeSyncRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val timeSyncRepository: TimeSyncRepository,
    private val orchestratorConfigRepository: OrchestratorConfigRepository,
    private val recordingCoordinator: RecordingCoordinator,
    private val commandCoordinator: CommandCoordinator,
    private val deviceEventRepository: DeviceEventRepository
) : ViewModel() {

    private val sessionId = MutableStateFlow(generateSessionId())
    private val busy = MutableStateFlow(false)
    private val lastError = MutableStateFlow<String?>(null)
    private val hostInput = MutableStateFlow("")
    private val portInput = MutableStateFlow("")
    private val useTlsInput = MutableStateFlow(false)
    private val currentConfig = MutableStateFlow<OrchestratorConfig?>(null)
    private val configMessage = MutableStateFlow<String?>(null)
    private val uploads = MutableStateFlow<List<UploadStatus>>(emptyList())
    private val showSyncFlash = MutableStateFlow(false)
    private var syncFlashJob: Job? = null

    val uiState: StateFlow<MainUiState> = combine(
        sensorRepository.devices,
        sensorRepository.streamStatuses,
        sensorRepository.recordingState,
        sensorRepository.simulationEnabled,
        timeSyncRepository.status,
        sessionId,
        busy,
        lastError,
        hostInput,
        portInput,
        useTlsInput,
        currentConfig,
        configMessage,
        commandCoordinator.lastCommand,
        deviceEventRepository.events,
        showSyncFlash
    ) { devices, streams, recording, simulation, sync, session, isBusy, error,
        host, portText, useTls, persistedConfig, message, lastCommand, events, flash ->
        val parsedPort = portText.toIntOrNull()
        val configDirty = persistedConfig?.let {
            host != it.host || parsedPort != it.port || useTls != it.useTls
        } ?: (host.isNotBlank() || portText.isNotBlank())
        val connectionLabel = deriveConnectionLabel(sync)
        val commandMessage = lastCommand?.let { describeCommand(it) }
        val eventUi = events
            .sortedByDescending { it.receivedAt }
            .take(EVENT_LOG_LIMIT)
            .map { it.toUiModel() }
        MainUiState(
            sessionIdInput = session,
            simulationEnabled = simulation,
            devices = devices.map { it.toUiModel(streams) },
            streamStatuses = streams.map { it.toUiModel() },
            uploadStatuses = uploads,
            recordingLifecycle = recording.lifecycle,
            anchorReference = recording.anchor?.referenceTimestamp,
            sharedClockOffsetMillis = recording.anchor?.sharedClockOffsetMillis,
            timeSyncStatus = sync,
            isBusy = isBusy,
            errorMessage = error,
            orchestratorHostInput = host,
            orchestratorPortInput = portText,
            orchestratorUseTls = useTls,
            orchestratorConfigDirty = configDirty,
            orchestratorConnectionStatus = connectionLabel,
            configMessage = message,
            lastCommandMessage = commandMessage,
            deviceEvents = eventUi,
            showSyncFlash = flash
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState.initial()
    )

    init {
        viewModelScope.launch {
            timeSyncRepository.start()
        }
        viewModelScope.launch {
            sensorRepository.refreshInventory()
        }
        viewModelScope.launch {
            orchestratorConfigRepository.config.collect { config ->
                val previous = currentConfig.value
                currentConfig.value = config
                if (previous == null || !inputsDirty(previous)) {
                    hostInput.value = config.host
                    portInput.value = config.port.toString()
                    useTlsInput.value = config.useTls
                }
            }
        }
        viewModelScope.launch {
            commandCoordinator.commands.collect { payload ->
                handleRemoteCommand(payload)
            }
        }
        viewModelScope.launch {
            commandCoordinator.syncSignals.collect {
                syncFlashJob?.cancel()
                showSyncFlash.value = true
                syncFlashJob = viewModelScope.launch {
                    delay(SYNC_FLASH_DURATION_MS)
                    showSyncFlash.value = false
                }
            }
        }
    }

    fun onSessionIdChanged(value: String) {
        sessionId.value = value
    }

    fun toggleSimulation() {
        viewModelScope.launch {
            val next = !sensorRepository.simulationEnabled.value
            sensorRepository.setSimulationEnabled(next)
        }
    }

    fun connectDevice(id: DeviceId) {
        viewModelScope.launch {
            sensorRepository.connect(id)
        }
    }

    fun disconnectDevice(id: DeviceId) {
        viewModelScope.launch {
            sensorRepository.disconnect(id)
        }
    }

    fun onOrchestratorHostChanged(value: String) {
        hostInput.value = value.trim()
        configMessage.value = null
    }

    fun onOrchestratorPortChanged(value: String) {
        portInput.value = value.trim()
        configMessage.value = null
    }

    fun onOrchestratorUseTlsChanged(enabled: Boolean) {
        useTlsInput.value = enabled
        configMessage.value = null
    }

    fun applyOrchestratorConfig() {
        viewModelScope.launch {
            val host = hostInput.value.trim()
            val portValue = portInput.value.trim()
            val port = portValue.toIntOrNull()
            if (host.isEmpty()) {
                configMessage.value = "Host cannot be blank"
                return@launch
            }
            if (port == null || port !in 1..65535) {
                configMessage.value = "Port must be between 1 and 65535"
                return@launch
            }
            runCatching {
                orchestratorConfigRepository.update(
                    OrchestratorConfig(
                        host = host,
                        port = port,
                        useTls = useTlsInput.value
                    )
                )
            }.onSuccess {
                configMessage.value = "Configuration saved"
            }.onFailure { ex ->
                configMessage.value = ex.message ?: "Unable to save configuration"
            }
        }
    }

    fun clearConfigMessage() {
        configMessage.value = null
    }

    fun startRecording() {
        viewModelScope.launch {
            if (busy.getAndSet(true)) return@launch
            lastError.value = null
            try {
                val id = sessionId.value.ifBlank { generateSessionId() }
                recordingCoordinator.startOrResume(id, requestedStart = null)
            } catch (t: Throwable) {
                lastError.value = t.message ?: "Failed to start recording"
            } finally {
                busy.value = false
            }
        }
    }

    fun stopRecording() {
        viewModelScope.launch {
            if (busy.getAndSet(true)) return@launch
            lastError.value = null
            try {
                recordingCoordinator.stop()
            } catch (t: Throwable) {
                lastError.value = t.message ?: "Failed to stop recording"
            } finally {
                busy.value = false
            }
        }
    }

    private fun handleRemoteCommand(payload: DeviceCommandPayload) {
        when (payload) {
            is StartRecordingCommandPayload -> handleStartRecordingCommand(payload)
            is StopRecordingCommandPayload -> handleStopRecordingCommand(payload)
            else -> Unit
        }
    }

    private fun handleStartRecordingCommand(payload: StartRecordingCommandPayload) {
        viewModelScope.launch {
            Log.i(TAG, "Start recording command ${payload.commandId} for session ${payload.sessionId}")
            if (busy.getAndSet(true)) {
                acknowledgeCommand(payload.commandId, success = false, message = "Device busy")
                return@launch
            }
            var message: String? = null
            val success = try {
                sessionId.value = payload.sessionId
                val anchorInstant = payload.anchorEpochMs.takeIf { it > 0 }?.let { Instant.fromEpochMilliseconds(it) }
                recordingCoordinator.startOrResume(
                    sessionId = payload.sessionId,
                    requestedStart = anchorInstant
                )
                lastError.value = null
                true
            } catch (t: Throwable) {
                message = t.message ?: "Failed to start recording"
                lastError.value = message
                false
            } finally {
                busy.value = false
            }
            acknowledgeCommand(payload.commandId, success, message)
        }
    }

    private fun handleStopRecordingCommand(payload: StopRecordingCommandPayload) {
        viewModelScope.launch {
            Log.i(TAG, "Stop recording command ${payload.commandId} for session ${payload.sessionId}")
            if (busy.getAndSet(true)) {
                acknowledgeCommand(payload.commandId, success = false, message = "Device busy")
                return@launch
            }
            var message: String? = null
            val success = try {
                sessionId.value = payload.sessionId
                recordingCoordinator.stop()
                lastError.value = null
                true
            } catch (t: Throwable) {
                message = t.message ?: "Failed to stop recording"
                lastError.value = message
                false
            } finally {
                busy.value = false
            }
            acknowledgeCommand(payload.commandId, success, message)
        }
    }

    private suspend fun acknowledgeCommand(commandId: String, success: Boolean, message: String?) {
        runCatching {
            commandCoordinator.acknowledge(commandId, success, message)
        }.onFailure { ex ->
            Log.w(TAG, "Unable to acknowledge command $commandId: ${ex.message}", ex)
        }
    }

    private fun SensorDevice.toUiModel(streams: List<SensorStreamStatus>): DeviceUiModel {
        val connected = connectionStatus is ConnectionStatus.Connected
        val connectionLabel = connectionStatus.label()
        val batteryPercent = (connectionStatus as? ConnectionStatus.Connected)?.batteryPercent
        val activeStreams = streams.filter { it.deviceId == id }.map { it.toUiModel() }
        val capabilityLabels = capabilities.map { it.label() }.sorted()
        return DeviceUiModel(
            id = id,
            title = displayName,
            typeLabel = type.label(),
            connectionStatus = connectionLabel,
            batteryPercent = batteryPercent,
            isConnected = connected,
            isSimulated = isSimulated,
            capabilityLabels = capabilityLabels,
            streams = activeStreams
        )
    }

    private fun SensorDeviceType.label(): String = when (this) {
        SensorDeviceType.SHIMMER_GSR -> "Shimmer3 GSR"
        SensorDeviceType.ANDROID_RGB_CAMERA -> "Android RGB Camera"
        SensorDeviceType.TOPDON_TC001 -> "Topdon TC001"
        SensorDeviceType.ANDROID_THERMAL_CAMERA -> "Android Thermal Camera"
        SensorDeviceType.AUDIO_MICROPHONE -> "Microphone"
        SensorDeviceType.UNKNOWN -> "Unknown Device"
    }

    private fun SensorStreamStatus.toUiModel(): StreamUiModel {
        val rate = when {
            sampleRateHz != null -> "${sampleRateHz.roundToInt()} Hz"
            frameRateFps != null -> "${frameRateFps.roundToInt()} FPS"
            else -> "n/a"
        }
        val bufferText = bufferedDurationSeconds?.let { String.format(Locale.US, "%.1f", it) } ?: "-"
        val detail = "Buffered $bufferText s @ $rate"
        val lastSample = lastSampleTimestamp?.toString() ?: "-"
        return StreamUiModel(
            deviceId = deviceId,
            typeLabel = streamType.label(),
            detail = detail,
            lastSampleTimestamp = lastSample,
            isSimulated = isSimulated
        )
    }

    private fun SensorStreamType.label(): String = when (this) {
        SensorStreamType.GSR -> "GSR"
        SensorStreamType.RGB_VIDEO -> "RGB Video"
        SensorStreamType.THERMAL_VIDEO -> "Thermal Video"
        SensorStreamType.AUDIO -> "Audio"
        SensorStreamType.PREVIEW -> "Preview"
    }

    private fun ConnectionStatus.label(): String = when (this) {
        ConnectionStatus.Disconnected -> "Disconnected"
        ConnectionStatus.Connecting -> "Connecting"
        is ConnectionStatus.Connected -> "Connected"
    }

    private fun DeviceEvent.toUiModel(): DeviceEventUiModel = DeviceEventUiModel(
        id = id,
        label = label,
        type = type.name,
        scheduledAt = scheduledAt.toString(),
        receivedAt = receivedAt.toString()
    )

    private fun describeCommand(payload: DeviceCommandPayload): String {
        val whenText = Instant.fromEpochMilliseconds(payload.executeEpochMs).toString()
        return when (payload) {
            is SyncSignalCommandPayload -> "Sync ${payload.signalType} @ $whenText"
            is EventMarkerCommandPayload -> "Event ${payload.markerId} @ $whenText"
            is StartRecordingCommandPayload -> "Start ${payload.sessionId} @ $whenText"
            is StopRecordingCommandPayload -> "Stop ${payload.sessionId} @ $whenText"
            is StimulusCommandPayload -> "Stimulus ${payload.stimulusId} (${payload.action}) @ $whenText"
            else -> "Command ${payload.commandId} @ $whenText"
        }
    }

    private fun deriveConnectionLabel(status: TimeSyncStatus): String {
        val now = Clock.System.now().toEpochMilliseconds()
        val ageMs = now - status.lastSync.toEpochMilliseconds()
        return when {
            ageMs <= CONNECTED_THRESHOLD_MS -> "Connected"
            ageMs <= STALE_THRESHOLD_MS -> "Stale (${ageMs / 1000}s ago)"
            else -> "Offline"
        }
    }

    private fun inputsDirty(reference: OrchestratorConfig): Boolean {
        val port = portInput.value.toIntOrNull()
        return hostInput.value != reference.host ||
                port == null ||
                port != reference.port ||
                useTlsInput.value != reference.useTls
    }

    private fun generateSessionId(): String {
        val now = Clock.System.now()
        return "session-${now.epochSeconds}"
    }

    private fun <T> MutableStateFlow<T>.getAndSet(value: T): T {
        val current = this.value
        this.value = value
        return current
    }

    private companion object {
        private const val TAG = "MainViewModel"
        private const val CONNECTED_THRESHOLD_MS = 5_000L
        private const val STALE_THRESHOLD_MS = 20_000L
        private const val SYNC_FLASH_DURATION_MS = 400L
        private const val EVENT_LOG_LIMIT = 10
    }
}

data class MainUiState(
    val sessionIdInput: String,
    val simulationEnabled: Boolean,
    val devices: List<DeviceUiModel>,
    val streamStatuses: List<StreamUiModel>,
    val uploadStatuses: List<UploadStatus>,
    val recordingLifecycle: RecordingLifecycleState,
    val anchorReference: Instant?,
    val sharedClockOffsetMillis: Long?,
    val timeSyncStatus: TimeSyncStatus,
    val isBusy: Boolean,
    val errorMessage: String?,
    val orchestratorHostInput: String,
    val orchestratorPortInput: String,
    val orchestratorUseTls: Boolean,
    val orchestratorConfigDirty: Boolean,
    val orchestratorConnectionStatus: String,
    val configMessage: String?,
    val lastCommandMessage: String?,
    val deviceEvents: List<DeviceEventUiModel>,
    val showSyncFlash: Boolean
) {
    val isRecording: Boolean
        get() = recordingLifecycle == RecordingLifecycleState.Recording

    companion object {
        fun initial(): MainUiState = MainUiState(
            sessionIdInput = "session-${Clock.System.now().epochSeconds}",
            simulationEnabled = false,
            devices = emptyList(),
            streamStatuses = emptyList(),
            uploadStatuses = emptyList(),
            recordingLifecycle = RecordingLifecycleState.Idle,
            anchorReference = null,
            sharedClockOffsetMillis = null,
            timeSyncStatus = TimeSyncStatus(
                offsetMillis = 0,
                roundTripMillis = 0,
                lastSync = Clock.System.now(),
                driftEstimateMillis = 0.0
            ),
            isBusy = false,
            errorMessage = null,
            orchestratorHostInput = "",
            orchestratorPortInput = "",
            orchestratorUseTls = false,
            orchestratorConfigDirty = false,
            orchestratorConnectionStatus = "Unknown",
            configMessage = null,
            lastCommandMessage = null,
            deviceEvents = emptyList(),
            showSyncFlash = false
        )
    }
}

data class DeviceUiModel(
    val id: DeviceId,
    val title: String,
    val typeLabel: String,
    val connectionStatus: String,
    val batteryPercent: Int?,
    val isConnected: Boolean,
    val isSimulated: Boolean,
    val capabilityLabels: List<String>,
    val streams: List<StreamUiModel>
)

data class StreamUiModel(
    val deviceId: DeviceId,
    val typeLabel: String,
    val detail: String,
    val lastSampleTimestamp: String,
    val isSimulated: Boolean
)

data class DeviceEventUiModel(
    val id: String,
    val label: String,
    val type: String,
    val scheduledAt: String,
    val receivedAt: String
)
