@file:OptIn(ExperimentalTime::class)

package com.buccancs.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.recording.RecordingService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.EventMarkerCommandPayload
import com.buccancs.control.commands.StartRecordingCommandPayload
import com.buccancs.control.commands.StimulusCommandPayload
import com.buccancs.control.commands.StopRecordingCommandPayload
import com.buccancs.control.commands.SyncSignalCommandPayload
import com.buccancs.data.sensor.exercise.DeviceExerciseResult
import com.buccancs.data.sensor.exercise.MultiDeviceRecordingExercise
import com.buccancs.data.sensor.exercise.RecordingExerciseResult
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.ShimmerDeviceCandidate
import com.buccancs.domain.model.ShimmerDeviceConfig
import com.buccancs.domain.model.ShimmerSettings
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.model.TopdonDeviceConfig
import com.buccancs.domain.model.UploadStatus
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.buccancs.domain.repository.TopdonDeviceRepository
import com.buccancs.util.nowInstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.time.ExperimentalTime

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val timeSyncService: TimeSyncService,
    private val orchestratorConfigRepository: OrchestratorConfigRepository,
    private val recordingService: RecordingService,
    private val commandService: DeviceCommandService,
    private val deviceEventRepository: DeviceEventRepository,
    private val shimmerSettingsRepository: ShimmerSettingsRepository,
    private val hardwareConfigRepository: SensorHardwareConfigRepository,
    private val topdonDeviceRepository: TopdonDeviceRepository,
    private val exercise: MultiDeviceRecordingExercise
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
    private val exerciseResult = MutableStateFlow<RecordingExerciseResult?>(null)
    private var syncFlashJob: Job? = null
    private val rgbCameraInputs = MutableStateFlow<Map<DeviceId, RgbCameraInputState>>(emptyMap())
    private val shimmerSettingsFlow = shimmerSettingsRepository.settings
    private val shimmerJson = Json { ignoreUnknownKeys = true }
    private val baseInputs = combine(
        sensorRepository.devices,
        sensorRepository.streamStatuses,
        sensorRepository.recordingState,
        sensorRepository.simulationEnabled,
        timeSyncService.status
    ) { devices, streams, recording, simulation, sync ->
        BaseInputs(
            devices = devices,
            streams = streams,
            recording = recording,
            simulation = simulation,
            sync = sync
        )
    }
    private val baseState = baseInputs.combine(shimmerSettingsFlow) { inputs, shimmerSettings ->
        BaseState(
            devices = inputs.devices,
            streams = inputs.streams,
            recording = inputs.recording,
            simulation = inputs.simulation,
            sync = inputs.sync,
            shimmerSettings = shimmerSettings
        )
    }
    private val sessionState = combine(
        sessionId,
        busy,
        lastError
    ) { session, isBusy, error ->
        SessionState(
            sessionId = session,
            isBusy = isBusy,
            lastError = error
        )
    }
    private val orchestratorState = combine(
        hostInput,
        portInput,
        useTlsInput,
        currentConfig,
        configMessage
    ) { host, portText, useTls, persistedConfig, message ->
        OrchestratorState(
            host = host,
            portText = portText,
            useTls = useTls,
            currentConfig = persistedConfig,
            message = message
        )
    }
    private val commandState = combine(
        commandService.lastCommand,
        deviceEventRepository.events
    ) { lastCommand, events ->
        CommandState(
            lastCommand = lastCommand,
            events = events
        )
    }
    private val toggleState = combine(
        uploads,
        showSyncFlash
    ) { uploadStatuses, flash ->
        ToggleState(
            uploads = uploadStatuses,
            showSyncFlash = flash
        )
    }
    private val inventoryState = combine(
        hardwareConfigRepository.config,
        sensorRepository.devices,
        topdonDeviceRepository.activeDeviceId
    ) { config, devices, activeTopdon ->
        InventoryUiModel(
            shimmer = config.shimmer.map { it.toUiModel(devices) },
            topdon = config.topdon.map { it.toUiModel(devices, activeTopdon) },
            activeTopdonId = activeTopdon
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = InventoryUiModel.empty()
    )
    private val exerciseUiState = exerciseResult
        .map { it?.toUiModel() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
    private val aggregateState = combine(
        baseState,
        sessionState,
        orchestratorState,
        commandState,
        toggleState
    ) { base, session, orchestrator, command, toggle ->
        UiAggregate(
            base = base,
            session = session,
            orchestrator = orchestrator,
            command = command,
            toggle = toggle
        )
    }
    val uiState: StateFlow<MainUiState> = combine(
        aggregateState,
        inventoryState,
        exerciseUiState
    ) { aggregate, inventory, exerciseUi ->
        val base = aggregate.base
        val session = aggregate.session
        val orchestrator = aggregate.orchestrator
        val command = aggregate.command
        val toggle = aggregate.toggle
        val parsedPort = orchestrator.portText.toIntOrNull()
        val configDirty = orchestrator.currentConfig?.let {
            orchestrator.host != it.host ||
                    parsedPort != it.port ||
                    orchestrator.useTls != it.useTls
        } ?: (orchestrator.host.isNotBlank() || orchestrator.portText.isNotBlank())
        val connectionLabel = deriveConnectionLabel(base.sync)
        val commandMessage = command.lastCommand?.let { describeCommand(it) }
        val eventUi = command.events
            .sortedByDescending { it.receivedAt }
            .take(EVENT_LOG_LIMIT)
            .map { it.toUiModel() }
        MainUiState(
            sessionIdInput = session.sessionId,
            simulationEnabled = base.simulation,
            devices = base.devices
                .sortedWith(compareByDescending<SensorDevice> { it.connectionStatus is ConnectionStatus.Connected }
                    .thenBy { it.id.value })
                .map { it.toUiModel(base.streams, base.shimmerSettings) },
            streamStatuses = base.streams.map { it.toUiModel() },
            uploadStatuses = toggle.uploads,
            recordingLifecycle = base.recording.lifecycle,
            anchorReference = base.recording.anchor?.referenceTimestamp,
            sharedClockOffsetMillis = base.recording.anchor?.sharedClockOffsetMillis,
            timeSyncStatus = base.sync,
            isBusy = session.isBusy,
            errorMessage = session.lastError,
            orchestratorHostInput = orchestrator.host,
            orchestratorPortInput = orchestrator.portText,
            orchestratorUseTls = orchestrator.useTls,
            orchestratorConfigDirty = configDirty,
            orchestratorConnectionStatus = connectionLabel,
            configMessage = orchestrator.message,
            lastCommandMessage = commandMessage,
            deviceEvents = eventUi,
            showSyncFlash = toggle.showSyncFlash,
            inventory = inventory,
            exercise = exerciseUi
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState.initial()
    )

    init {
        viewModelScope.launch {
            timeSyncService.start()
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
            commandService.commands.collect { payload ->
                handleRemoteCommand(payload)
            }
        }
        viewModelScope.launch {
            commandService.syncSignals.collect {
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

    fun refreshInventory() {
        viewModelScope.launch {
            sensorRepository.refreshInventory()
        }
    }

    fun selectShimmerDevice(id: DeviceId, mac: String?) {
        if (!id.isShimmer()) return
        viewModelScope.launch {
            val normalized = mac?.takeIf { it.isNotBlank() }?.uppercase(Locale.US)
            shimmerSettingsRepository.setTargetMac(normalized)
            hardwareConfigRepository.updateConfig { config ->
                val updated = config.shimmer.map { entry ->
                    if (entry.id == id.value) {
                        entry.copy(macAddress = normalized)
                    } else {
                        entry
                    }
                }
                config.copy(shimmer = updated)
            }
        }
    }

    fun updateShimmerRange(id: DeviceId, rangeIndex: Int) {
        if (!id.isShimmer()) return
        viewModelScope.launch {
            val normalized = rangeIndex.coerceIn(0, ShimmerSettings.DEFAULT_GSR_RANGE)
            shimmerSettingsRepository.setGsrRange(normalized)
            hardwareConfigRepository.updateConfig { config ->
                val updated = config.shimmer.map { entry ->
                    if (entry.id == id.value) {
                        entry.copy(gsrRangeIndex = normalized)
                    } else {
                        entry
                    }
                }
                config.copy(shimmer = updated)
            }
        }
    }

    fun updateShimmerSampleRate(id: DeviceId, sampleRate: Double) {
        if (!id.isShimmer()) return
        viewModelScope.launch {
            val sanitized = sampleRate.takeIf { it.isFinite() && it > 0.0 } ?: ShimmerSettings.DEFAULT_SAMPLE_RATE_HZ
            shimmerSettingsRepository.setSampleRate(sanitized)
            hardwareConfigRepository.updateConfig { config ->
                val updated = config.shimmer.map { entry ->
                    if (entry.id == id.value) {
                        entry.copy(sampleRateHz = sanitized)
                    } else {
                        entry
                    }
                }
                config.copy(shimmer = updated)
            }
        }
    }

    fun setActiveTopdon(deviceId: DeviceId) {
        viewModelScope.launch {
            topdonDeviceRepository.setActiveDevice(deviceId)
        }
    }

    fun runExercise() {
        if (busy.value) return
        viewModelScope.launch {
            busy.value = true
            try {
                val result = exercise.run()
                exerciseResult.value = result
            } catch (t: Throwable) {
                if (t is CancellationException) throw t
                Log.e(TAG, "Exercise failed: ${t.message}", t)
                lastError.value = t.message ?: "Multi-device exercise failed."
            } finally {
                busy.value = false
            }
        }
    }

    fun updateRgbCameraField(id: DeviceId, field: RgbCameraField, value: String) {
        if (!id.isRgbCamera()) return
        updateRgbCameraState(id) { state -> state.updateField(field, value) }
    }

    fun toggleRgbCameraRaw(id: DeviceId, enabled: Boolean) {
        if (!id.isRgbCamera()) return
        updateRgbCameraState(id) { state -> state.updateRawEnabled(enabled) }
    }

    fun selectRgbCameraAwb(id: DeviceId, awbValue: String) {
        if (!id.isRgbCamera()) return
        updateRgbCameraState(id) { state -> state.updateAwb(awbValue) }
    }

    fun resetRgbCameraSettings(id: DeviceId) {
        if (!id.isRgbCamera()) return
        updateRgbCameraState(id) { state -> state.reset() }
    }

    fun applyRgbCameraSettings(id: DeviceId) {
        if (!id.isRgbCamera()) return
        val current = rgbCameraInputs.value[id] ?: return
        if (!current.dirty) return
        val validation = validateRgbCamera(current)
        if (validation.errors.isNotEmpty()) {
            updateRgbCameraState(id) { state -> state.withErrors(validation.errors) }
            return
        }
        val options = validation.options
        viewModelScope.launch {
            try {
                sensorRepository.configure(id, options)
                updateRgbCameraState(id) { state -> state.markApplied() }
            } catch (t: Throwable) {
                lastError.value = "Failed to configure camera: ${t.message ?: "unknown error"}"
            }
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
                recordingService.startOrResume(id, requestedStart = null)
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
                recordingService.stop()
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
                awaitExecution(payload.executeEpochMs)
                sessionId.value = payload.sessionId
                val anchorInstant = payload.anchorEpochMs.takeIf { it > 0 }?.let { Instant.fromEpochMilliseconds(it) }
                recordingService.startOrResume(
                    sessionId = payload.sessionId,
                    requestedStart = anchorInstant
                )
                lastError.value = null
                true
            } catch (cancellation: CancellationException) {
                throw cancellation
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
                awaitExecution(payload.executeEpochMs)
                sessionId.value = payload.sessionId
                recordingService.stop()
                lastError.value = null
                true
            } catch (cancellation: CancellationException) {
                throw cancellation
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
            commandService.acknowledge(commandId, success, message)
        }.onFailure { ex ->
            Log.w(TAG, "Unable to acknowledge command $commandId: ${ex.message}", ex)
        }
    }

    private fun SensorDevice.toUiModel(
        streams: List<SensorStreamStatus>,
        shimmerSettings: ShimmerSettings
    ): DeviceUiModel {
        val connected = connectionStatus is ConnectionStatus.Connected
        val connectionLabel = connectionStatus.uiLabel()
        val batteryPercent = (connectionStatus as? ConnectionStatus.Connected)?.batteryPercent
        val activeStreams = streams.filter { it.deviceId == id }.map { it.toUiModel() }
        val capabilityLabels = capabilities.map { stream -> sensorStreamLabel(stream) }.sorted()
        val shimmerUi = if (type == SensorDeviceType.SHIMMER_GSR) {
            buildShimmerUi(attributes, shimmerSettings)
        } else {
            null
        }
        val rgbCameraUi = if (type == SensorDeviceType.ANDROID_RGB_CAMERA) {
            ensureRgbCameraState(this).toUiModel()
        } else {
            null
        }
        return DeviceUiModel(
            id = id,
            title = displayName,
            typeLabel = type.uiLabel(),
            connectionStatus = connectionLabel,
            batteryPercent = batteryPercent,
            isConnected = connected,
            isSimulated = isSimulated,
            supportsTopdon = type == SensorDeviceType.TOPDON_TC001,
            capabilityLabels = capabilityLabels,
            streams = activeStreams,
            rgbCamera = rgbCameraUi,
            shimmer = shimmerUi
        )
    }

    private fun SensorDeviceType.uiLabel(): String = when (this) {
        SensorDeviceType.SHIMMER_GSR -> "Shimmer3 GSR"
        SensorDeviceType.ANDROID_RGB_CAMERA -> "Android RGB Camera"
        SensorDeviceType.TOPDON_TC001 -> "Topdon TC001"
        SensorDeviceType.ANDROID_THERMAL_CAMERA -> "Android Thermal Camera"
        SensorDeviceType.AUDIO_MICROPHONE -> "Microphone"
        SensorDeviceType.UNKNOWN -> "Unknown Device"
    }

    private fun buildShimmerUi(
        attributes: Map<String, String>,
        shimmerSettings: ShimmerSettings
    ): ShimmerDeviceUi {
        val candidatesJson = attributes[ATTR_AVAILABLE_DEVICES]
        val candidates = candidatesJson?.let {
            runCatching {
                shimmerJson.decodeFromString(
                    ListSerializer(ShimmerDeviceCandidate.serializer()),
                    it
                )
            }.getOrElse { emptyList() }
        } ?: emptyList()
        val selectedMac = (attributes[ATTR_SELECTED_MAC]?.takeIf { it.isNotBlank() }
            ?: shimmerSettings.targetMacAddress)?.uppercase(Locale.US)
        val rangeIndex = attributes[ATTR_GSR_RANGE]?.toIntOrNull() ?: shimmerSettings.gsrRangeIndex
        val sampleRate = attributes[ATTR_SAMPLE_RATE]?.toDoubleOrNull() ?: shimmerSettings.sampleRateHz
        val candidateUi = candidates.sortedWith(
            compareBy<ShimmerDeviceCandidate> { it.name?.lowercase(Locale.US) ?: it.mac.lowercase(Locale.US) }
                .thenBy { it.mac }
        ).map {
            ShimmerCandidateUi(
                mac = it.mac,
                name = it.name,
                rssi = it.rssi
            )
        }
        return ShimmerDeviceUi(
            candidates = candidateUi,
            selectedMac = selectedMac,
            gsrRangeIndex = rangeIndex.coerceIn(0, SHIMMER_GSR_RANGE_LABELS.lastIndex),
            sampleRateHz = sampleRate,
            gsrRangeLabels = SHIMMER_GSR_RANGE_LABELS,
            sampleRateOptions = SHIMMER_SAMPLE_RATE_OPTIONS
        )
    }

    private suspend fun awaitExecution(executeEpochMs: Long) {
        if (executeEpochMs <= 0L) return
        val offset = timeSyncService.status.value.offsetMillis
        val clampedOffset = offset.coerceIn(-MAX_OFFSET_ADJUSTMENT_MS, MAX_OFFSET_ADJUSTMENT_MS)
        val adjustedNow = System.currentTimeMillis() + clampedOffset
        val delayMs = executeEpochMs - adjustedNow
        if (delayMs > 0) {
            delay(delayMs)
        }
    }

    private fun ensureRgbCameraState(device: SensorDevice): RgbCameraInputState {
        val supportsRaw = parseBooleanValue(device.attributes[ATTR_RGB_RAW_SUPPORTED]) ?: false
        val baseline = buildRgbBaseline(device.attributes, supportsRaw)
        var resolved: RgbCameraInputState? = null
        rgbCameraInputs.update { current ->
            val existing = current[device.id]
            val next = when {
                existing == null -> RgbCameraInputState(
                    supportsRaw = supportsRaw,
                    baseline = baseline,
                    inputs = baseline,
                    dirty = false,
                    errors = emptyMap()
                )

                !existing.dirty -> existing.copy(
                    supportsRaw = supportsRaw,
                    baseline = baseline,
                    inputs = baseline,
                    errors = emptyMap()
                )

                else -> existing.copy(
                    supportsRaw = supportsRaw,
                    baseline = baseline
                )
            }
            resolved = next
            current + (device.id to next)
        }
        return resolved!!
    }

    private fun buildRgbBaseline(
        attributes: Map<String, String>,
        supportsRaw: Boolean
    ): RgbCameraValues {
        val videoFps = attributes[ATTR_RGB_VIDEO_FPS]?.takeIf { it.isNotBlank() } ?: DEFAULT_RGB_VIDEO_FPS
        val videoBitRate = attributes[ATTR_RGB_VIDEO_BIT_RATE]?.takeIf { it.isNotBlank() } ?: DEFAULT_RGB_VIDEO_BITRATE
        val rawInterval = attributes[ATTR_RGB_RAW_INTERVAL]?.takeIf { it.isNotBlank() } ?: DEFAULT_RGB_RAW_INTERVAL
        val exposure = attributes[ATTR_RGB_EXPOSURE]?.takeIf { it.isNotBlank() } ?: ""
        val iso = attributes[ATTR_RGB_ISO]?.takeIf { it.isNotBlank() } ?: ""
        val focusMeters = attributes[ATTR_RGB_FOCUS_METERS]?.takeIf { it.isNotBlank() } ?: ""
        val awb = attributes[ATTR_RGB_AWB]?.takeIf { it.isNotBlank() } ?: DEFAULT_RGB_AWB
        val rawEnabled = if (!supportsRaw) {
            false
        } else {
            parseBooleanValue(attributes[ATTR_RGB_RAW_ENABLED]) ?: true
        }
        return RgbCameraValues(
            videoFps = videoFps,
            videoBitRate = videoBitRate,
            rawIntervalMs = rawInterval,
            exposureNs = exposure,
            iso = iso,
            focusMeters = focusMeters,
            awbMode = awb,
            rawEnabled = rawEnabled
        )
    }

    private fun RgbCameraInputState.toUiModel(): RgbCameraUi {
        val normalizedAwb = inputs.awbMode.ifBlank { DEFAULT_RGB_AWB }
        val options = if (RGB_AWB_OPTIONS.any { it.value == normalizedAwb }) {
            RGB_AWB_OPTIONS
        } else {
            val label = normalizedAwb.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString()
            }
            listOf(RgbCameraAwbOption(normalizedAwb, label)) + RGB_AWB_OPTIONS
        }
        return RgbCameraUi(
            supportsRaw = supportsRaw,
            rawEnabled = supportsRaw && inputs.rawEnabled,
            videoFps = inputs.videoFps,
            videoBitRate = inputs.videoBitRate,
            rawIntervalMs = inputs.rawIntervalMs,
            exposureNs = inputs.exposureNs,
            iso = inputs.iso,
            focusDistanceMeters = inputs.focusMeters,
            awbMode = normalizedAwb,
            awbOptions = options,
            errors = errors,
            isDirty = dirty
        )
    }

    private fun updateRgbCameraState(
        id: DeviceId,
        transform: (RgbCameraInputState) -> RgbCameraInputState
    ) {
        rgbCameraInputs.update { current ->
            val state = current[id] ?: return@update current
            current + (id to transform(state))
        }
    }

    private fun RgbCameraInputState.updateField(
        field: RgbCameraField,
        value: String
    ): RgbCameraInputState {
        val trimmed = value.trim()
        val updatedInputs = when (field) {
            RgbCameraField.VIDEO_FPS -> inputs.copy(videoFps = trimmed)
            RgbCameraField.VIDEO_BIT_RATE -> inputs.copy(videoBitRate = trimmed)
            RgbCameraField.RAW_INTERVAL_MS -> inputs.copy(rawIntervalMs = trimmed)
            RgbCameraField.EXPOSURE_NS -> inputs.copy(exposureNs = trimmed)
            RgbCameraField.ISO -> inputs.copy(iso = trimmed)
            RgbCameraField.FOCUS_METERS -> inputs.copy(focusMeters = trimmed)
        }
        return copy(inputs = updatedInputs, dirty = true, errors = errors - field)
    }

    private fun RgbCameraInputState.updateRawEnabled(enabled: Boolean): RgbCameraInputState {
        val coerced = if (supportsRaw) enabled else false
        val clearedErrors = if (!coerced) errors - RgbCameraField.RAW_INTERVAL_MS else errors
        return copy(inputs = inputs.copy(rawEnabled = coerced), dirty = true, errors = clearedErrors)
    }

    private fun RgbCameraInputState.updateAwb(value: String): RgbCameraInputState =
        copy(inputs = inputs.copy(awbMode = value), dirty = true)

    private fun RgbCameraInputState.reset(): RgbCameraInputState =
        copy(inputs = baseline, dirty = false, errors = emptyMap())

    private fun RgbCameraInputState.markApplied(): RgbCameraInputState =
        copy(baseline = inputs, dirty = false, errors = emptyMap())

    private fun RgbCameraInputState.withErrors(messages: Map<RgbCameraField, String>): RgbCameraInputState =
        copy(errors = messages)

    private fun validateRgbCamera(state: RgbCameraInputState): RgbCameraValidationResult {
        val options = mutableMapOf<String, String>()
        val errors = mutableMapOf<RgbCameraField, String>()
        val fpsText = state.inputs.videoFps.trim()
        if (fpsText.isEmpty()) {
            errors[RgbCameraField.VIDEO_FPS] = "Required"
        } else {
            val fps = fpsText.toIntOrNull()
            if (fps == null || fps <= 0) {
                errors[RgbCameraField.VIDEO_FPS] = "Invalid FPS"
            } else {
                options["video_fps"] = fps.toString()
            }
        }
        val bitrateText = state.inputs.videoBitRate.trim()
        if (bitrateText.isEmpty()) {
            errors[RgbCameraField.VIDEO_BIT_RATE] = "Required"
        } else {
            val bitrate = bitrateText.toIntOrNull()
            if (bitrate == null || bitrate <= 0) {
                errors[RgbCameraField.VIDEO_BIT_RATE] = "Invalid bitrate"
            } else {
                options["video_bitrate"] = bitrate.toString()
            }
        }
        if (state.supportsRaw) {
            options["raw_enabled"] = state.inputs.rawEnabled.toString()
            if (state.inputs.rawEnabled) {
                val intervalText = state.inputs.rawIntervalMs.trim()
                if (intervalText.isEmpty()) {
                    errors[RgbCameraField.RAW_INTERVAL_MS] = "Required"
                } else {
                    val interval = intervalText.toLongOrNull()
                    if (interval == null || interval <= 0L) {
                        errors[RgbCameraField.RAW_INTERVAL_MS] = "Invalid interval"
                    } else {
                        options["raw_interval_ms"] = interval.toString()
                    }
                }
            }
        }
        val exposureText = state.inputs.exposureNs.trim()
        if (exposureText.isNotEmpty()) {
            val exposure = exposureText.toLongOrNull()
            if (exposure == null || exposure <= 0L) {
                errors[RgbCameraField.EXPOSURE_NS] = "Invalid exposure"
            } else {
                options["exposure_time_ns"] = exposure.toString()
            }
        }
        val isoText = state.inputs.iso.trim()
        if (isoText.isNotEmpty()) {
            val iso = isoText.toIntOrNull()
            if (iso == null || iso <= 0) {
                errors[RgbCameraField.ISO] = "Invalid ISO"
            } else {
                options["iso"] = iso.toString()
            }
        }
        val focusText = state.inputs.focusMeters.trim()
        if (focusText.isNotEmpty()) {
            val focusMeters = focusText.toDoubleOrNull()
            if (focusMeters == null || focusMeters <= 0.0) {
                errors[RgbCameraField.FOCUS_METERS] = "Invalid distance"
            } else {
                options["focus_distance_m"] = String.format(Locale.US, "%.4f", focusMeters)
            }
        }
        val awb = state.inputs.awbMode.ifBlank { DEFAULT_RGB_AWB }
        options["awb_mode"] = awb
        return RgbCameraValidationResult(options, errors)
    }

    private fun parseBooleanValue(value: String?): Boolean? = when (value?.lowercase(Locale.US)) {
        "true" -> true
        "false" -> false
        else -> null
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
            typeLabel = sensorStreamLabel(streamType),
            detail = detail,
            lastSampleTimestamp = lastSample,
            isSimulated = isSimulated
        )
    }

    private fun ConnectionStatus.uiLabel(): String = when (this) {
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

    private fun DeviceId.isShimmer(): Boolean = value == SHIMMER_DEVICE_ID.value

    private fun DeviceId.isRgbCamera(): Boolean = value == RGB_CAMERA_DEVICE_ID.value

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
        val ageSeconds =
            ((nowInstant().toEpochMilliseconds() - status.lastSync.toEpochMilliseconds()) / 1000).coerceAtLeast(0)
        val freshness = when {
            ageSeconds <= 5 -> "fresh"
            ageSeconds <= 20 -> "stale ${ageSeconds}s"
            else -> "offline ${ageSeconds}s"
        }
        val quality = when (status.quality) {
            TimeSyncQuality.GOOD -> "good"
            TimeSyncQuality.FAIR -> "fair"
            TimeSyncQuality.POOR -> "poor"
            TimeSyncQuality.UNKNOWN -> "unknown"
        }
        return "Sync $quality - $freshness"
    }

    private fun inputsDirty(reference: OrchestratorConfig): Boolean {
        val port = portInput.value.toIntOrNull()
        return hostInput.value != reference.host ||
                port == null ||
                port != reference.port ||
                useTlsInput.value != reference.useTls
    }

    private fun generateSessionId(): String {
        val now = nowInstant()
        return "session-${now.epochSeconds}"
    }

    private fun <T> MutableStateFlow<T>.getAndSet(value: T): T {
        val current = this.value
        this.value = value
        return current
    }

    private data class BaseInputs(
        val devices: List<SensorDevice>,
        val streams: List<SensorStreamStatus>,
        val recording: RecordingState,
        val simulation: Boolean,
        val sync: TimeSyncStatus
    )

    private data class BaseState(
        val devices: List<SensorDevice>,
        val streams: List<SensorStreamStatus>,
        val recording: RecordingState,
        val simulation: Boolean,
        val sync: TimeSyncStatus,
        val shimmerSettings: ShimmerSettings
    )

    private data class SessionState(
        val sessionId: String,
        val isBusy: Boolean,
        val lastError: String?
    )

    private data class OrchestratorState(
        val host: String,
        val portText: String,
        val useTls: Boolean,
        val currentConfig: OrchestratorConfig?,
        val message: String?
    )

    private data class CommandState(
        val lastCommand: DeviceCommandPayload?,
        val events: List<DeviceEvent>
    )

    private data class ToggleState(
        val uploads: List<UploadStatus>,
        val showSyncFlash: Boolean
    )

    private data class UiAggregate(
        val base: BaseState,
        val session: SessionState,
        val orchestrator: OrchestratorState,
        val command: CommandState,
        val toggle: ToggleState
    )

    private companion object {
        private const val TAG = "MainViewModel"
        private const val MAX_OFFSET_ADJUSTMENT_MS = 60_000L
        private const val SYNC_FLASH_DURATION_MS = 400L
        private const val EVENT_LOG_LIMIT = 10
        private const val ATTR_AVAILABLE_DEVICES = "shimmer.candidates"
        private const val ATTR_SELECTED_MAC = "shimmer.selected"
        private const val ATTR_GSR_RANGE = "shimmer.gsr_range"
        private const val ATTR_SAMPLE_RATE = "shimmer.sample_rate"
        private val SHIMMER_GSR_RANGE_LABELS = listOf(
            "10kOhm - 56kOhm",
            "56kOhm - 220kOhm",
            "220kOhm - 680kOhm",
            "680kOhm - 4.7MOhm",
            "Auto Range"
        )
        private val SHIMMER_SAMPLE_RATE_OPTIONS = listOf(8.0, 16.0, 32.0, 64.0, 128.0, 256.0)
        private val SHIMMER_DEVICE_ID = DeviceId("shimmer-primary")
        private const val ATTR_RGB_VIDEO_FPS = "rgb.video_fps"
        private const val ATTR_RGB_VIDEO_BIT_RATE = "rgb.video_bitrate"
        private const val ATTR_RGB_RAW_ENABLED = "rgb.raw_enabled"
        private const val ATTR_RGB_RAW_INTERVAL = "rgb.raw_interval_ms"
        private const val ATTR_RGB_EXPOSURE = "rgb.exposure_time_ns"
        private const val ATTR_RGB_ISO = "rgb.iso"
        private const val ATTR_RGB_AWB = "rgb.awb_mode"
        private const val ATTR_RGB_FOCUS_METERS = "rgb.focus_distance_m"
        private const val ATTR_RGB_RAW_SUPPORTED = "rgb.raw_supported"
        private const val DEFAULT_RGB_VIDEO_FPS = "60"
        private const val DEFAULT_RGB_VIDEO_BITRATE = "75000000"
        private const val DEFAULT_RGB_RAW_INTERVAL = "1000"
        private const val DEFAULT_RGB_AWB = "auto"
        private val RGB_AWB_OPTIONS = listOf(
            RgbCameraAwbOption("auto", "Auto"),
            RgbCameraAwbOption("daylight", "Daylight"),
            RgbCameraAwbOption("cloudy", "Cloudy"),
            RgbCameraAwbOption("shade", "Shade"),
            RgbCameraAwbOption("incandescent", "Incandescent"),
            RgbCameraAwbOption("fluorescent", "Fluorescent"),
            RgbCameraAwbOption("twilight", "Twilight"),
            RgbCameraAwbOption("warm", "Warm Fluorescent"),
            RgbCameraAwbOption("off", "Off")
        )
        private val RGB_CAMERA_DEVICE_ID = DeviceId("android-rgb")
    }
}

private fun ShimmerDeviceConfig.toUiModel(devices: List<SensorDevice>): InventoryDeviceUi {
    val deviceId = DeviceId(id)
    val device = devices.firstOrNull { it.id == deviceId }
    val label = displayName.ifBlank { device?.displayName ?: id }
    val macDetail = macAddress?.uppercase(Locale.US) ?: "MAC not set"
    val connected = device?.connectionStatus is ConnectionStatus.Connected
    return InventoryDeviceUi(
        id = deviceId,
        label = label,
        detail = macDetail,
        connected = connected,
        isActive = false
    )
}

private fun TopdonDeviceConfig.toUiModel(
    devices: List<SensorDevice>,
    activeTopdon: DeviceId
): InventoryDeviceUi {
    val deviceId = DeviceId(id)
    val device = devices.firstOrNull { it.id == deviceId }
    val label = displayName.ifBlank { device?.displayName ?: id }
    val descriptorParts = mutableListOf<String>()
    vendorId?.let { descriptorParts += "VID 0x${it.toString(16)}" }
    productId?.let { descriptorParts += "PID 0x${it.toString(16)}" }
    serialNumber?.takeIf { it.isNotBlank() }?.let { descriptorParts += "SN $it" }
    val details = if (descriptorParts.isEmpty()) {
        "USB descriptor pending"
    } else {
        descriptorParts.joinToString(" | ")
    }
    val connected = device?.connectionStatus is ConnectionStatus.Connected
    return InventoryDeviceUi(
        id = deviceId,
        label = label,
        detail = details,
        connected = connected,
        isActive = deviceId == activeTopdon
    )
}

private fun RecordingExerciseResult.toUiModel(): RecordingExerciseUi =
    RecordingExerciseUi(
        sessionId = sessionId,
        startedAt = startedAt,
        success = deviceResults.all { it.success },
        devices = deviceResults.map { it.toUiModel() }
    )

private fun DeviceExerciseResult.toUiModel(): DeviceExerciseUi {
    val streamLabels = mutableListOf<String>()
    for (stream in streamsObserved) {
        streamLabels += sensorStreamLabel(stream)
    }
    return DeviceExerciseUi(
        deviceId = deviceId,
        name = displayName,
        success = success,
        streams = streamLabels.sorted(),
        startObservedAt = startObservedAt?.toString(),
        stopObservedAt = stopObservedAt?.toString(),
        artifactNames = artifacts.map { it.file.name }
    )
}

data class InventoryUiModel(
    val shimmer: List<InventoryDeviceUi>,
    val topdon: List<InventoryDeviceUi>,
    val activeTopdonId: DeviceId?
) {
    companion object {
        fun empty(): InventoryUiModel = InventoryUiModel(emptyList(), emptyList(), null)
    }
}

data class InventoryDeviceUi(
    val id: DeviceId,
    val label: String,
    val detail: String,
    val connected: Boolean,
    val isActive: Boolean
)

data class RecordingExerciseUi(
    val sessionId: String,
    val startedAt: Instant,
    val success: Boolean,
    val devices: List<DeviceExerciseUi>
)

data class DeviceExerciseUi(
    val deviceId: DeviceId,
    val name: String,
    val success: Boolean,
    val streams: List<String>,
    val startObservedAt: String?,
    val stopObservedAt: String?,
    val artifactNames: List<String>
)

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
    val inventory: InventoryUiModel,
    val exercise: RecordingExerciseUi?,
    val showSyncFlash: Boolean
) {
    val isRecording: Boolean
        get() = recordingLifecycle == RecordingLifecycleState.Recording

    companion object {
        fun initial(): MainUiState = MainUiState(
            sessionIdInput = "session-${nowInstant().epochSeconds}",
            simulationEnabled = false,
            devices = emptyList(),
            streamStatuses = emptyList(),
            uploadStatuses = emptyList(),
            recordingLifecycle = RecordingLifecycleState.Idle,
            anchorReference = null,
            sharedClockOffsetMillis = null,
            timeSyncStatus = TimeSyncStatus(
                offsetMillis = 0,
                roundTripMillis = Long.MAX_VALUE,
                lastSync = nowInstant(),
                driftEstimateMillisPerMinute = 0.0,
                filteredRoundTripMillis = Double.POSITIVE_INFINITY,
                quality = TimeSyncQuality.UNKNOWN,
                sampleCount = 0,
                regressionSlopeMillisPerMinute = 0.0
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
            inventory = InventoryUiModel.empty(),
            exercise = null,
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
    val supportsTopdon: Boolean,
    val capabilityLabels: List<String>,
    val streams: List<StreamUiModel>,
    val rgbCamera: RgbCameraUi? = null,
    val shimmer: ShimmerDeviceUi? = null
)

data class ShimmerDeviceUi(
    val candidates: List<ShimmerCandidateUi>,
    val selectedMac: String?,
    val gsrRangeIndex: Int,
    val sampleRateHz: Double,
    val gsrRangeLabels: List<String>,
    val sampleRateOptions: List<Double>
)

data class ShimmerCandidateUi(
    val mac: String,
    val name: String?,
    val rssi: Int?
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

data class RgbCameraUi(
    val supportsRaw: Boolean,
    val rawEnabled: Boolean,
    val videoFps: String,
    val videoBitRate: String,
    val rawIntervalMs: String,
    val exposureNs: String,
    val iso: String,
    val focusDistanceMeters: String,
    val awbMode: String,
    val awbOptions: List<RgbCameraAwbOption>,
    val errors: Map<RgbCameraField, String>,
    val isDirty: Boolean
)

data class RgbCameraAwbOption(
    val value: String,
    val label: String
)

enum class RgbCameraField {
    VIDEO_FPS,
    VIDEO_BIT_RATE,
    RAW_INTERVAL_MS,
    EXPOSURE_NS,
    ISO,
    FOCUS_METERS
}

private data class RgbCameraValues(
    val videoFps: String,
    val videoBitRate: String,
    val rawIntervalMs: String,
    val exposureNs: String,
    val iso: String,
    val focusMeters: String,
    val awbMode: String,
    val rawEnabled: Boolean
)

private data class RgbCameraInputState(
    val supportsRaw: Boolean,
    val baseline: RgbCameraValues,
    val inputs: RgbCameraValues,
    val dirty: Boolean,
    val errors: Map<RgbCameraField, String>
)

private data class RgbCameraValidationResult(
    val options: Map<String, String>,
    val errors: Map<RgbCameraField, String>
)

private fun sensorStreamLabel(type: SensorStreamType): String = when (type) {
    SensorStreamType.GSR -> "GSR"
    SensorStreamType.RGB_VIDEO -> "RGB Video"
    SensorStreamType.RAW_DNG -> "RAW (DNG)"
    SensorStreamType.THERMAL_VIDEO -> "Thermal Video"
    SensorStreamType.AUDIO -> "Audio"
    SensorStreamType.PREVIEW -> "Preview"
}

