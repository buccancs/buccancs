package com.buccancs.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.storage.SpaceMonitor
import com.buccancs.data.storage.SpaceState
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.RecordingBookmark
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.UploadStatus
import com.buccancs.domain.repository.BookmarkRepository
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.ui.session.LiveSessionUiState.Companion.initial
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.util.nowInstant

@HiltViewModel
class LiveSessionViewModel @Inject constructor(
    sensorRepository: SensorRepository,
    timeSyncService: TimeSyncService,
    transferRepository: SessionTransferRepository,
    deviceEventRepository: DeviceEventRepository,
    spaceMonitor: SpaceMonitor
) : ViewModel() {
    private val simulation = sensorRepository.simulationEnabled
    private val combinedState = combine(
        sensorRepository.recordingState,
        sensorRepository.streamStatuses,
        sensorRepository.devices,
        timeSyncService.status,
        timeSyncService.history,
        transferRepository.uploads,
        deviceEventRepository.events,
        spaceMonitor.state,
        simulation
    ) { recording, streams, devices, sync, history, uploads, events, space, simulationEnabled ->
        LiveSessionUiState(
            recording = recording,
            streams = streams,
            devices = devices,
            syncStatus = sync,
            syncHistory = history,
            uploads = uploads,
            events = events.takeLast(MAX_EVENTS),
            storage = space,
            simulationEnabled = simulationEnabled
        )
    }

    val uiState: StateFlow<LiveSessionUiState> = combinedState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = initial()
    )
}

data class LiveSessionUiState(
    val recording: RecordingState,
    val streams: List<SensorStreamStatus>,
    val devices: List<SensorDevice>,
    val syncStatus: TimeSyncStatus,
    val syncHistory: List<TimeSyncObservation>,
    val uploads: List<UploadStatus>,
    val events: List<DeviceEvent>,
    val storage: SpaceState,
    val simulationEnabled: Boolean
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
            events = emptyList(),
            storage = SpaceState.EMPTY,
            simulationEnabled = false
        )
    }
}

private const val MAX_EVENTS = 24
