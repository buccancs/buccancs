package com.buccancs.desktop.viewmodel

import com.buccancs.desktop.data.erasure.SubjectErasureManager
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.PreviewRepository.PreviewFrameData
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.data.retention.DataRetentionManager
import com.buccancs.desktop.data.retention.DataRetentionManager.QuotaSnapshot
import com.buccancs.desktop.data.session.EventLog
import com.buccancs.desktop.domain.model.DeviceConnectionEvent
import com.buccancs.desktop.domain.model.DeviceInfo
import com.buccancs.desktop.domain.model.FileTransferProgress
import com.buccancs.desktop.domain.model.FileTransferState
import com.buccancs.desktop.domain.model.Session
import com.buccancs.desktop.domain.model.SessionStatus
import com.buccancs.desktop.domain.model.StoredSession
import com.buccancs.desktop.ui.state.AppUiState
import com.buccancs.desktop.ui.state.ControlPanelState
import com.buccancs.desktop.ui.state.DeviceListItem
import com.buccancs.desktop.ui.state.EventTimelineItem
import com.buccancs.desktop.ui.state.PreviewStreamState
import com.buccancs.desktop.ui.state.RetentionState
import com.buccancs.desktop.ui.state.SessionArchiveItem
import com.buccancs.desktop.ui.state.SessionMetricsState
import com.buccancs.desktop.ui.state.SessionSummary
import com.buccancs.desktop.ui.state.TransferStatusItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class AppViewModel(
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val retentionManager: DataRetentionManager,
    private val previewRepository: PreviewRepository,
    private val commandRepository: CommandRepository,
    private val subjectErasureManager: SubjectErasureManager,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) {
    private val logger = LoggerFactory.getLogger(AppViewModel::class.java)
    private val alerts = MutableStateFlow<List<String>>(emptyList())
    private val controlState = MutableStateFlow(ControlPanelState())
    private val transferState = MutableStateFlow<List<FileTransferProgress>>(emptyList())
    private val transferCompletionNotified = mutableSetOf<String>()
    private val transferFailureNotified = mutableSetOf<String>()
    private val _uiState = MutableStateFlow(
        AppUiState(
            session = null,
            devices = emptyList(),
            retention = RetentionState(emptyMap(), emptyMap(), emptyMap(), 0, emptyList()),
            previews = emptyList(),
            transfers = emptyList(),
            alerts = emptyList(),
            control = ControlPanelState(),
            events = emptyList(),
            historicalSessions = emptyList()
        )
    )
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()
    private val clock = tickerFlow()
    private val timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(ZoneId.systemDefault())

    init {
        scope.launch {
            sessionRepository.transferUpdates().collect { progress ->
                handleTransferNotifications(progress)
                transferState.value = progress
            }
        }
        val baseSnapshotFlow = combine(
            sessionRepository.activeSession,
            deviceRepository.observe(),
            retentionManager.state(),
            previewRepository.observe(),
            sessionRepository.activeEvents()
        ) { session, devices, retention, previews, events ->
            BaseSnapshot(
                session = session,
                devices = devices,
                retention = retention,
                previews = previews,
                events = events
            )
        }

        val uiInputFlow = combine(
            baseSnapshotFlow,
            sessionRepository.storedSessions(),
            transferState,
            alerts,
            controlState
        ) { base, archives, transfers, alertList, control ->
            UiInputs(
                base = base,
                archives = archives,
                transfers = transfers,
                alerts = alertList,
                control = control
            )
        }
        scope.launch {
            combine(uiInputFlow, clock) { snapshot, now ->
                buildUiState(snapshot, now)
            }.collect { state ->
                _uiState.value = state
            }
        }
        scope.launch {
            deviceRepository.events().collect { event ->
                when (event) {
                    is DeviceConnectionEvent.Connected ->
                        appendAlert("Device ${event.deviceId} connected")

                    is DeviceConnectionEvent.Disconnected -> {
                        val reason = event.reason.name.lowercase(Locale.US).replace('_', '-')
                        appendAlert("Device ${event.deviceId} disconnected ($reason)")
                    }
                }
            }
        }
    }

    fun startSession() {
        scope.launch {
            try {
                val control = controlState.value
                val subjectIds = parseCsv(control.subjectIds)
                val session = sessionRepository.startSession(
                    operatorId = control.operatorId.takeIf { it.isNotBlank() },
                    subjectIds = subjectIds
                )
                deviceRepository.assignSession(session.id)
                val anchorEpochMs = session.startedAt?.toEpochMilli() ?: System.currentTimeMillis()
                commandRepository.enqueueStartRecording(
                    sessionId = session.id,
                    anchorEpochMs = anchorEpochMs,
                    scheduledEpochMs = null
                )
                appendAlert("Session started at ${timestampFormatter.format(Instant.now())}")
            } catch (ex: Exception) {
                logger.error("Unable to start session", ex)
                appendAlert("Unable to start session: ${ex.message}")
            }
        }
    }

    fun stopSession() {
        scope.launch {
            try {
                val activeSession = sessionRepository.activeSession.value
                val stopEpochMs = System.currentTimeMillis()
                sessionRepository.stopSession()
                deviceRepository.assignSession(null)
                if (activeSession != null) {
                    commandRepository.enqueueStopRecording(
                        sessionId = activeSession.id,
                        stopEpochMs = stopEpochMs
                    )
                }
                appendAlert("Session stopped at ${timestampFormatter.format(Instant.now())}")
            } catch (ex: Exception) {
                logger.error("Unable to stop session", ex)
                appendAlert("Unable to stop session: ${ex.message}")
            }
        }
    }

    fun eraseSession(sessionId: String) {
        scope.launch {
            try {
                subjectErasureManager.eraseSession(sessionId)
                appendAlert("Session $sessionId erased")
            } catch (ex: Exception) {
                logger.error("Unable to erase session {}", sessionId, ex)
                appendAlert("Unable to erase session $sessionId: ${ex.message}")
            }
        }
    }

    fun eraseSubject() {
        scope.launch {
            try {
                val subjectId = controlState.value.subjectEraseId.trim()
                if (subjectId.isEmpty()) {
                    appendAlert("Subject identifier is required for erasure")
                    return@launch
                }
                val result = subjectErasureManager.eraseSubject(subjectId)
                appendAlert("Erased ${result.erasedSessions.size} sessions for subject $subjectId")
                updateControl { it.copy(subjectEraseId = "") }
            } catch (ex: Exception) {
                logger.error("Unable to erase subject {}", controlState.value.subjectEraseId, ex)
                appendAlert("Unable to erase subject ${controlState.value.subjectEraseId}: ${ex.message}")
            }
        }
    }

    fun triggerSyncSignal() {
        scope.launch {
            val session = sessionRepository.activeSession.value
            if (session == null || session.status != SessionStatus.ACTIVE) {
                appendAlert("Cannot send sync signal without an active session")
                return@launch
            }
            val control = controlState.value
            val delayMs = control.syncDelayMs.trim().toLongOrNull()?.coerceAtLeast(0) ?: 0
            val executeAt = System.currentTimeMillis() + delayMs
            val signalType = control.syncSignalType.ifBlank { "flash" }
            val targetResolution = resolveTargetsForCommand(control.syncTargets)
            val markerId = "sync-$signalType-$executeAt"
            try {
                sessionRepository.registerEvent(
                    eventId = markerId,
                    label = "sync:$signalType",
                    timestampMs = executeAt,
                    deviceIds = targetResolution.eventDeviceIds
                )
                commandRepository.enqueueSyncSignal(
                    sessionId = session.id,
                    signalType = signalType,
                    executeEpochMs = executeAt,
                    targets = targetResolution.commandTargets,
                    initiator = control.operatorId.ifBlank { "desktop-ui" }
                )
                appendAlert("Sync $signalType queued (${delayMs} ms delay)")
            } catch (ex: Exception) {
                logger.error("Unable to queue sync signal", ex)
                appendAlert("Unable to queue sync signal: ${ex.message}")
            }
        }
    }

    fun addEventMarker() {
        scope.launch {
            val session = sessionRepository.activeSession.value
            if (session == null || session.status != SessionStatus.ACTIVE) {
                appendAlert("Cannot add event without an active session")
                return@launch
            }
            val control = controlState.value
            val timestamp = System.currentTimeMillis()
            val markerId = control.eventMarkerId.ifBlank { "event-$timestamp" }
            val label = control.eventDescription.ifBlank { "manual-marker" }
            val targetResolution = resolveTargetsForCommand(control.eventTargets)
            try {
                sessionRepository.registerEvent(
                    eventId = markerId,
                    label = label,
                    timestampMs = timestamp,
                    deviceIds = targetResolution.eventDeviceIds
                )
                commandRepository.enqueueEventMarker(
                    sessionId = session.id,
                    markerId = markerId,
                    description = label,
                    timestampEpochMs = timestamp,
                    targets = targetResolution.commandTargets
                )
                appendAlert("Event $markerId recorded")
                updateControl { it.copy(eventMarkerId = "", eventDescription = "") }
            } catch (ex: Exception) {
                logger.error("Unable to record event marker", ex)
                appendAlert("Unable to record event marker: ${ex.message}")
            }
        }
    }

    fun triggerStimulus() {
        scope.launch {
            val session = sessionRepository.activeSession.value
            if (session == null || session.status != SessionStatus.ACTIVE) {
                appendAlert("Cannot trigger stimulus without an active session")
                return@launch
            }
            val control = controlState.value
            val stimulusId = control.stimulusId.ifBlank { "stimulus-${System.currentTimeMillis()}" }
            val action = control.stimulusAction.ifBlank { "play" }
            val targetResolution = resolveTargetsForCommand(control.stimulusTargets)
            val timestamp = System.currentTimeMillis()
            runCatching {
                sessionRepository.registerEvent(
                    eventId = "stimulus-$stimulusId",
                    label = "stimulus:$action",
                    timestampMs = timestamp,
                    deviceIds = targetResolution.eventDeviceIds
                )
                commandRepository.enqueueStimulus(
                    sessionId = session.id,
                    stimulusId = stimulusId,
                    action = action,
                    executeEpochMs = timestamp,
                    metadata = emptyMap(),
                    targets = targetResolution.commandTargets
                )
            }.onSuccess {
                appendAlert("Stimulus $stimulusId triggered")
                updateControl { it.copy(stimulusId = "", stimulusAction = "") }
            }.onFailure { ex ->
                logger.error("Unable to trigger stimulus {}", stimulusId, ex)
                appendAlert("Unable to trigger stimulus: ${ex.message}")
            }
        }
    }

    fun updateOperatorId(value: String) = updateControl { it.copy(operatorId = value) }
    fun updateSubjectIds(value: String) = updateControl { it.copy(subjectIds = value) }
    fun updateSyncSignalType(value: String) = updateControl { it.copy(syncSignalType = value) }
    fun updateSyncDelayMs(value: String) = updateControl { it.copy(syncDelayMs = value) }
    fun updateSyncTargets(value: String) = updateControl { it.copy(syncTargets = value) }
    fun updateEventMarkerId(value: String) = updateControl { it.copy(eventMarkerId = value) }
    fun updateEventDescription(value: String) = updateControl { it.copy(eventDescription = value) }
    fun updateEventTargets(value: String) = updateControl { it.copy(eventTargets = value) }
    fun updateStimulusId(value: String) = updateControl { it.copy(stimulusId = value) }
    fun updateStimulusAction(value: String) = updateControl { it.copy(stimulusAction = value) }
    fun updateStimulusTargets(value: String) = updateControl { it.copy(stimulusTargets = value) }
    fun updateSubjectEraseId(value: String) = updateControl { it.copy(subjectEraseId = value) }
    fun clearAlert(message: String) {
        alerts.value = alerts.value.filterNot { it == message }
    }

    private fun handleTransferNotifications(progress: List<FileTransferProgress>) {
        val grouped = progress.groupBy { it.sessionId }
        grouped.forEach { (sessionId, items) ->
            if (items.any { it.state != FileTransferState.COMPLETED }) {
                transferCompletionNotified.remove(sessionId)
            }
            val allCompleted = items.isNotEmpty() && items.all { it.state == FileTransferState.COMPLETED }
            if (allCompleted && transferCompletionNotified.add(sessionId)) {
                appendAlert("All uploads received for session $sessionId")
            }
            items.filter { it.state == FileTransferState.FAILED }.forEach { item ->
                val key = "${item.sessionId}|${item.deviceId}|${item.fileName}|${item.attempt}"
                if (transferFailureNotified.add(key)) {
                    appendAlert("Upload failed for ${item.deviceId}/${item.fileName}: ${item.lastError ?: "unknown error"}")
                }
            }
        }
    }

    private fun appendAlert(message: String) {
        alerts.value = (alerts.value + message).takeLast(10)
    }

    private fun updateControl(transform: (ControlPanelState) -> ControlPanelState) {
        controlState.value = transform(controlState.value)
    }

    private fun resolveTargetsForCommand(raw: String): TargetResolution {
        val explicit = parseCsv(raw).toSet()
        val connected = deviceRepository.snapshot()
            .filter { it.connected }
            .map { it.id }
        val commandTargets = if (explicit.isEmpty()) emptySet<String>() else explicit
        val eventDeviceIds = if (commandTargets.isEmpty()) {
            connected
        } else {
            connected.filter { commandTargets.contains(it) }
        }
        return TargetResolution(commandTargets, eventDeviceIds)
    }

    private fun parseCsv(raw: String): List<String> =
        raw.split(",", ";", " ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()

    private data class BaseSnapshot(
        val session: Session?,
        val devices: List<DeviceInfo>,
        val retention: QuotaSnapshot,
        val previews: Map<PreviewRepository.PreviewKey, PreviewFrameData>,
        val events: List<EventLog>
    )

    private data class UiInputs(
        val base: BaseSnapshot,
        val archives: List<StoredSession>,
        val transfers: List<FileTransferProgress>,
        val alerts: List<String>,
        val control: ControlPanelState
    )

    private fun buildUiState(inputs: UiInputs, now: Instant): AppUiState {
        val base = inputs.base
        val clockOffsets = base.devices.mapNotNull { info ->
            val offset = info.clockOffsetMs ?: return@mapNotNull null
            info.id to offset
        }.toMap()

        val controlWithMetrics = if (clockOffsets == inputs.control.clockOffsetsPreview) {
            inputs.control
        } else {
            inputs.control.copy(clockOffsetsPreview = clockOffsets)
        }

        val offlineWarnings = base.devices
            .filter { !it.connected }
            .map { info -> "Device ${info.id} disconnected" }

        val sessionSummary = base.session?.let { session ->
            val startInstant = session.startedAt ?: session.createdAt
            val elapsedMillis = if (session.status == SessionStatus.COMPLETED && session.totalDurationMs != null) {
                session.totalDurationMs
            } else {
                Duration.between(startInstant, now).toMillis().coerceAtLeast(0)
            }
            val metricsState = SessionMetricsState(
                gsrSamples = session.metrics.gsrSamples,
                videoFrames = session.metrics.videoFrames,
                thermalFrames = session.metrics.thermalFrames,
                audioSamples = session.metrics.audioSamples,
                updatedAt = session.metrics.updatedAt
            )
            SessionSummary(
                id = session.id,
                status = session.status.name,
                startedAt = startInstant,
                createdAt = session.createdAt,
                totalBytes = base.retention.perSessionBytes[session.id] ?: 0,
                durationMs = session.totalDurationMs,
                subjectIds = session.subjectIds,
                elapsedMillis = elapsedMillis,
                metrics = metricsState
            )
        }

        val retentionState = RetentionState(
            perSessionBytes = base.retention.perSessionBytes,
            perDeviceBytes = base.retention.perDeviceBytes,
            perSessionDeviceBytes = base.retention.perSessionDeviceBytes,
            totalBytes = base.retention.totalBytes,
            breaches = base.retention.actions.map { formatRetentionAlert(it) }
        )

        val previewStates = base.previews.values
            .sortedByDescending { it.receivedAt }
            .map { frame ->
                PreviewStreamState(
                    deviceId = frame.deviceId,
                    cameraId = frame.cameraId,
                    mimeType = frame.mimeType,
                    width = frame.width,
                    height = frame.height,
                    latencyMs = frame.latencyMs,
                    receivedAt = frame.receivedAt,
                    payload = frame.payload
                )
            }

        val transferStates = inputs.transfers.map { transfer ->
            TransferStatusItem(
                sessionId = transfer.sessionId,
                deviceId = transfer.deviceId,
                fileName = transfer.fileName,
                streamType = transfer.streamType,
                state = transfer.state.name,
                attempt = transfer.attempt,
                bytesTransferred = transfer.receivedBytes,
                bytesTotal = transfer.expectedBytes,
                errorMessage = transfer.lastError
            )
        }

        val eventItems = base.events
            .sortedBy { it.timestampEpochMs }
            .takeLast(200)
            .map { event ->
                EventTimelineItem(
                    eventId = event.eventId,
                    label = event.label,
                    timestamp = Instant.ofEpochMilli(event.timestampEpochMs),
                    deviceIds = event.deviceIds
                )
            }

        val archiveItems = inputs.archives
            .sortedByDescending { it.createdAt }
            .map { archive ->
                SessionArchiveItem(
                    id = archive.id,
                    status = archive.status.name,
                    createdAt = archive.createdAt,
                    startedAt = archive.startedAt,
                    stoppedAt = archive.stoppedAt,
                    totalBytes = archive.totalBytes,
                    durationMs = archive.durationMs,
                    subjects = archive.subjects,
                    eventCount = archive.eventCount,
                    deviceCount = archive.deviceCount
                )
            }

        val alertMessages = (inputs.alerts + offlineWarnings + base.retention.actions.map { formatRetentionAlert(it) })
            .distinct()

        return AppUiState(
            session = sessionSummary,
            devices = base.devices
                .sortedWith(compareByDescending<DeviceInfo> { it.connected }.thenBy { it.id })
                .map { device ->
                DeviceListItem(
                    id = device.id,
                    model = device.model,
                    platform = device.platform,
                    connected = device.connected,
                    recording = device.recording,
                    batteryPercent = device.batteryPercent,
                    previewLatencyMs = device.previewLatencyMs,
                    clockOffsetMs = device.clockOffsetMs,
                    lastHeartbeat = device.lastHeartbeat,
                    sessionId = device.sessionId
                )
            },
            retention = retentionState,
            previews = previewStates,
            transfers = transferStates,
            alerts = alertMessages,
            control = controlWithMetrics,
            events = eventItems,
            historicalSessions = archiveItems
        )
    }

    private fun formatRetentionAlert(action: DataRetentionManager.QuotaAction): String = when (action) {
        is DataRetentionManager.QuotaAction.DeviceCapExceeded ->
            "Device ${action.deviceId} exceeded quota (${bytesToReadable(action.usageBytes)} > ${bytesToReadable(action.limitBytes)})"

        is DataRetentionManager.QuotaAction.GlobalCapExceeded ->
            "Global quota exceeded (${bytesToReadable(action.usageBytes)} > ${bytesToReadable(action.limitBytes)})"

        is DataRetentionManager.QuotaAction.SessionCapExceeded ->
            "Session ${action.sessionId} exceeded quota (${bytesToReadable(action.usageBytes)} > ${
                bytesToReadable(
                    action.limitBytes
                )
            })"
    }

    private fun tickerFlow(periodMillis: Long = 1_000L) = flow {
        while (true) {
            emit(Instant.now())
            delay(periodMillis)
        }
    }

    private fun bytesToReadable(bytes: Long): String {
        if (bytes <= 0) return "0 B"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val exp = (Math.log(bytes.toDouble()) / Math.log(1024.0)).toInt().coerceAtMost(units.lastIndex)
        val value = bytes / Math.pow(1024.0, exp.toDouble())
        return String.format("%.2f %s", value, units[exp])
    }

    private data class TargetResolution(
        val commandTargets: Set<String>,
        val eventDeviceIds: List<String>
    )
}
