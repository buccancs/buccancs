package com.buccancs.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.application.stimulus.StimulusPresentationManager
import com.buccancs.application.stimulus.StimulusState
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.storage.SpaceMonitor
import com.buccancs.data.storage.SpaceState
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.PerformanceThrottleLevel
import com.buccancs.domain.model.RecordingBookmark
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadRecoveryRecord
import com.buccancs.domain.model.UploadStatus
import com.buccancs.domain.repository.BookmarkRepository
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.util.nowInstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Instant
import javax.inject.Inject

@HiltViewModel
class LiveSessionViewModel @Inject constructor(
    sensorRepository: SensorRepository,
    timeSyncService: TimeSyncService,
    transferRepository: SessionTransferRepository,
    deviceEventRepository: DeviceEventRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val stimulusPresentationManager: StimulusPresentationManager,
    spaceMonitor: SpaceMonitor
) : ViewModel() {
    private val simulation = sensorRepository.simulationEnabled
    private val streamAndDevices = combine(
        sensorRepository.streamStatuses,
        sensorRepository.devices
    ) { streams, devices -> streams to devices }

    private val syncState = combine(
        timeSyncService.status,
        timeSyncService.history
    ) { status, history -> status to history }

    private val uploadState = combine(
        transferRepository.uploads,
        transferRepository.recovery,
        transferRepository.backlog,
        deviceEventRepository.events
    ) { uploads, recovery, backlog, events ->
        UploadSnapshot(uploads, recovery, backlog, events)
    }

    private val spaceState = combine(
        spaceMonitor.state,
        simulation
    ) { space, simulationEnabled -> space to simulationEnabled }

    private val baseSnapshot = combine(
        sensorRepository.recordingState,
        streamAndDevices,
        syncState,
        uploadState,
        spaceState,
        sensorRepository.throttleLevel
    ) { values ->
        val recording = values[0] as RecordingState

        @Suppress("UNCHECKED_CAST")
        val streamDevice = values[1] as Pair<List<SensorStreamStatus>, List<SensorDevice>>

        @Suppress("UNCHECKED_CAST")
        val syncPair = values[2] as Pair<TimeSyncStatus, List<TimeSyncObservation>>
        val uploadSnapshot = values[3] as UploadSnapshot

        @Suppress("UNCHECKED_CAST")
        val spacePair = values[4] as Pair<SpaceState, Boolean>
        val throttleLevel = values[5] as PerformanceThrottleLevel

        val (streams, devices) = streamDevice
        val (sync, history) = syncPair
        val uploads = uploadSnapshot.uploads
        val recovery = uploadSnapshot.recovery
        val backlog = uploadSnapshot.backlog
        val events = uploadSnapshot.events
        val (space, simulationEnabled) = spacePair
        SessionSnapshot(
            recording = recording,
            streams = streams,
            devices = devices,
            syncStatus = sync,
            syncHistory = history,
            uploads = uploads,
            recoveries = recovery,
            backlog = backlog,
            events = events,
            storage = space,
            simulationEnabled = simulationEnabled,
            throttleLevel = throttleLevel
        )
    }

    private val combinedState = combine(
        baseSnapshot,
        stimulusPresentationManager.state,
        bookmarkRepository.bookmarks
    ) { snapshot, stimulus, bookmarks ->
        snapshot.toUiState(
            stimulus = stimulus,
            bookmarks = bookmarks.takeLast(MAX_BOOKMARKS)
        )
    }

    fun recordBookmark(label: String = "Bookmark") {
        val sanitized = label.trim().ifBlank { "Bookmark" }
        viewModelScope.launch {
            bookmarkRepository.add(sanitized, nowInstant())
        }
    }

    fun previewStimulus() {
        viewModelScope.launch {
            stimulusPresentationManager.triggerPreview()
        }
    }

    val uiState: StateFlow<LiveSessionUiState> = combinedState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LiveSessionUiState.initial()
    )
}

data class LiveSessionUiState(
    val recording: RecordingState,
    val streams: List<SensorStreamStatus>,
    val devices: List<SensorDevice>,
    val syncStatus: TimeSyncStatus,
    val syncHistory: List<TimeSyncObservation>,
    val uploads: List<UploadStatus>,
    val recoveries: List<UploadRecoveryRecord>,
    val backlog: UploadBacklogState,
    val events: List<DeviceEvent>,
    val bookmarks: List<RecordingBookmark>,
    val storage: SpaceState,
    val simulationEnabled: Boolean,
    val stimulus: StimulusState,
    val throttleLevel: PerformanceThrottleLevel
) {
    companion object {
        fun initial(): LiveSessionUiState = LiveSessionUiState(
            recording = RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                anchor = null,
                updatedAt = Instant.DISTANT_PAST
            ),
            streams = emptyList(),
            devices = emptyList(),
            syncStatus = TimeSyncStatus(
                offsetMillis = 0,
                roundTripMillis = Long.MAX_VALUE,
                lastSync = Instant.DISTANT_PAST,
                driftEstimateMillisPerMinute = 0.0,
                filteredRoundTripMillis = Double.POSITIVE_INFINITY,
                quality = TimeSyncQuality.UNKNOWN,
                sampleCount = 0,
                regressionSlopeMillisPerMinute = 0.0
            ),
            syncHistory = emptyList(),
            uploads = emptyList(),
            recoveries = emptyList(),
            backlog = UploadBacklogState(
                level = UploadBacklogLevel.NORMAL,
                queuedCount = 0,
                queuedBytes = 0,
                message = null
            ),
            events = emptyList(),
            bookmarks = emptyList(),
            storage = SpaceState.EMPTY,
            simulationEnabled = false,
            stimulus = StimulusState(),
            throttleLevel = PerformanceThrottleLevel.NORMAL
        )
    }
}

private const val MAX_EVENTS = 24
private const val MAX_BOOKMARKS = 64

private data class SessionSnapshot(
    val recording: RecordingState,
    val streams: List<SensorStreamStatus>,
    val devices: List<SensorDevice>,
    val syncStatus: TimeSyncStatus,
    val syncHistory: List<TimeSyncObservation>,
    val uploads: List<UploadStatus>,
    val recoveries: List<UploadRecoveryRecord>,
    val backlog: UploadBacklogState,
    val events: List<DeviceEvent>,
    val storage: SpaceState,
    val simulationEnabled: Boolean,
    val throttleLevel: PerformanceThrottleLevel
)

private fun SessionSnapshot.toUiState(
    stimulus: StimulusState,
    bookmarks: List<RecordingBookmark>
): LiveSessionUiState =
    LiveSessionUiState(
        recording = recording,
        streams = streams,
        devices = devices,
        syncStatus = syncStatus,
        syncHistory = syncHistory,
        uploads = uploads,
        recoveries = recoveries,
        backlog = backlog,
        events = events.takeLast(MAX_EVENTS),
        bookmarks = bookmarks,
        storage = storage,
        simulationEnabled = simulationEnabled,
        stimulus = stimulus,
        throttleLevel = throttleLevel
    )

private data class UploadSnapshot(
    val uploads: List<UploadStatus>,
    val recovery: List<UploadRecoveryRecord>,
    val backlog: UploadBacklogState,
    val events: List<DeviceEvent>
)
