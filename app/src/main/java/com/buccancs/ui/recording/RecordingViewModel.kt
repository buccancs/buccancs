@file:OptIn(ExperimentalTime::class)

package com.buccancs.ui.recording

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.data.sensor.exercise.DeviceExerciseResult
import com.buccancs.data.sensor.exercise.MultiDeviceRecordingExercise
import com.buccancs.data.sensor.exercise.RecordingExerciseResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.usecase.SessionCoordinator
import com.buccancs.ui.sensorStreamLabel
import com.buccancs.util.nowInstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * ViewModel responsible for recording session lifecycle and exercise execution.
 * Extracted from MainViewModel to focus on recording-specific concerns.
 */
@HiltViewModel
class RecordingViewModel @Inject constructor(
    private val sessionCoordinator: SessionCoordinator,
    private val sensorRepository: SensorRepository,
    private val exercise: MultiDeviceRecordingExercise
) : ViewModel() {

    private val exerciseResult = MutableStateFlow<RecordingExerciseResult?>(null)
    private val exerciseBusy = MutableStateFlow(false)
    private val exerciseError = MutableStateFlow<String?>(null)

    private val exerciseUiState = exerciseResult
        .map { it?.toUiModel() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val uiState: StateFlow<RecordingUiState> = combine(
        sessionCoordinator.sessionState,
        sensorRepository.recordingState,
        exerciseUiState,
        exerciseBusy,
        exerciseError
    ) { sessionState, recordingState, exerciseUi, exBusy, exError ->
        RecordingUiState(
            sessionId = sessionState.currentSessionId,
            lifecycle = recordingState.lifecycle,
            anchorReference = recordingState.anchor?.referenceTimestamp,
            sharedClockOffsetMillis = recordingState.anchor?.sharedClockOffsetMillis,
            isBusy = sessionState.isBusy || exBusy,
            errorMessage = sessionState.lastError ?: exError,
            exercise = exerciseUi
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecordingUiState.initial()
    )

    fun onSessionIdChanged(value: String) {
        (sessionCoordinator as? com.buccancs.domain.usecase.SessionCoordinatorImpl)
            ?.updateSessionId(value)
    }

    fun startRecording() {
        viewModelScope.launch {
            val id = uiState.value.sessionId.ifBlank {
                sessionCoordinator.generateSessionId()
            }
            sessionCoordinator.startSession(id, requestedStart = null)
        }
    }

    fun stopRecording() {
        viewModelScope.launch {
            sessionCoordinator.stopSession()
        }
    }

    fun runExercise() {
        if (exerciseBusy.value) return
        viewModelScope.launch {
            exerciseBusy.value = true
            try {
                val result = exercise.run()
                exerciseResult.value = result
                exerciseError.value = null
            } catch (t: Throwable) {
                if (t is CancellationException) throw t
                Log.e(TAG, "Exercise failed: ${t.message}", t)
                exerciseError.value = t.message ?: "Multi-device exercise failed."
            } finally {
                exerciseBusy.value = false
            }
        }
    }

    private companion object {
        private const val TAG = "RecordingViewModel"
    }
}

data class RecordingUiState(
    val sessionId: String,
    val lifecycle: RecordingLifecycleState,
    val anchorReference: Instant?,
    val sharedClockOffsetMillis: Long?,
    val isBusy: Boolean,
    val errorMessage: String?,
    val exercise: RecordingExerciseUi?
) {
    val isRecording: Boolean
        get() = lifecycle == RecordingLifecycleState.Recording

    companion object {
        fun initial(): RecordingUiState = RecordingUiState(
            sessionId = "session-${nowInstant().epochSeconds}",
            lifecycle = RecordingLifecycleState.Idle,
            anchorReference = null,
            sharedClockOffsetMillis = null,
            isBusy = false,
            errorMessage = null,
            exercise = null
        )
    }
}

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

private fun RecordingExerciseResult.toUiModel(): RecordingExerciseUi =
    RecordingExerciseUi(
        sessionId = sessionId,
        startedAt = startedAt,
        success = deviceResults.all { it.success },
        devices = deviceResults.map { it.toUiModel() }
    )

private fun DeviceExerciseResult.toUiModel(): DeviceExerciseUi {
    val streamLabels = streamsObserved.map { sensorStreamLabel(it) }.sorted()
    return DeviceExerciseUi(
        deviceId = deviceId,
        name = displayName,
        success = success,
        streams = streamLabels,
        startObservedAt = startObservedAt?.toString(),
        stopObservedAt = stopObservedAt?.toString(),
        artifactNames = artifacts.map { it.file?.name ?: it.uri.lastPathSegment ?: it.streamType.name }
    )
}
