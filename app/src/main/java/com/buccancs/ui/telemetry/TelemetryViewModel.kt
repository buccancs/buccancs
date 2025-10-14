package com.buccancs.ui.telemetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.EventMarkerCommandPayload
import com.buccancs.control.commands.StartRecordingCommandPayload
import com.buccancs.control.commands.StimulusCommandPayload
import com.buccancs.control.commands.StopRecordingCommandPayload
import com.buccancs.control.commands.SyncSignalCommandPayload
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.ui.StreamUiMapper
import com.buccancs.ui.DeviceEventUiModel
import com.buccancs.ui.StreamUiModel
import com.buccancs.util.nowInstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Instant

/**
 * ViewModel responsible for telemetry display: stream status, time sync, events.
 * Extracted from MainViewModel to focus on monitoring and telemetry concerns.
 */
@HiltViewModel
class TelemetryViewModel @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val timeSyncService: TimeSyncService,
    private val deviceEventRepository: DeviceEventRepository,
    private val commandService: DeviceCommandService,
    private val streamUiMapper: StreamUiMapper
) : ViewModel() {

    private val showSyncFlash = MutableStateFlow(false)
    private var syncFlashJob: Job? = null

    val uiState: StateFlow<TelemetryUiState> = combine(
        sensorRepository.streamStatuses,
        timeSyncService.status,
        deviceEventRepository.events,
        commandService.lastCommand,
        showSyncFlash
    ) { streams, syncStatus, events, lastCommand, flash ->
        TelemetryUiState(
            streamStatuses = streams.map { streamUiMapper.toUiModel(it) },
            timeSyncStatus = syncStatus,
            orchestratorConnectionStatus = deriveConnectionLabel(syncStatus),
            deviceEvents = events
                .sortedByDescending { it.receivedAt }
                .take(EVENT_LOG_LIMIT)
                .map { it.toUiModel() },
            lastCommandMessage = lastCommand?.let { describeCommand(it) },
            showSyncFlash = flash
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TelemetryUiState.initial()
    )

    init {
        // Monitor sync signals for flash effect
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

    private fun deriveConnectionLabel(status: TimeSyncStatus): String {
        val ageSeconds =
            ((nowInstant().toEpochMilliseconds() - status.lastSync.toEpochMilliseconds()) / 1000)
                .coerceAtLeast(0)
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

    private fun DeviceEvent.toUiModel(): DeviceEventUiModel = DeviceEventUiModel(
        id = id,
        label = label,
        type = type.name,
        scheduledAt = scheduledAt.toString(),
        receivedAt = receivedAt.toString()
    )

    private companion object {
        private const val SYNC_FLASH_DURATION_MS = 400L
        private const val EVENT_LOG_LIMIT = 10
    }
}

data class TelemetryUiState(
    val streamStatuses: List<StreamUiModel>,
    val timeSyncStatus: TimeSyncStatus,
    val orchestratorConnectionStatus: String,
    val deviceEvents: List<DeviceEventUiModel>,
    val lastCommandMessage: String?,
    val showSyncFlash: Boolean
) {
    companion object {
        fun initial(): TelemetryUiState = TelemetryUiState(
            streamStatuses = emptyList(),
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
            orchestratorConnectionStatus = "Unknown",
            deviceEvents = emptyList(),
            lastCommandMessage = null,
            showSyncFlash = false
        )
    }
}

data class DeviceEventUiModel(
    val id: String,
    val label: String,
    val type: String,
    val scheduledAt: String,
    val receivedAt: String
)
