package com.buccancs.ui.recording

import com.buccancs.data.sensor.exercise.MultiDeviceRecordingExercise
import com.buccancs.data.sensor.exercise.RecordingExerciseResult
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.usecase.SessionCoordinator
import com.buccancs.domain.usecase.SessionState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RecordingViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var sessionCoordinator: SessionCoordinator
    private lateinit var sensorRepository: SensorRepository
    private lateinit var exercise: MultiDeviceRecordingExercise
    private lateinit var viewModel: RecordingViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        sessionCoordinator = mockk(relaxed = true)
        sensorRepository = mockk(relaxed = true)
        exercise = mockk(relaxed = true)

        every { sessionCoordinator.sessionState } returns MutableStateFlow(
            SessionState(
                currentSessionId = "test-session",
                isBusy = false,
                lastError = null
            )
        )
        every { sensorRepository.recordingState } returns MutableStateFlow(
            RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                startedAt = null,
                anchor = null
            )
        )

        viewModel = RecordingViewModel(sessionCoordinator, sensorRepository, exercise)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `startRecording calls coordinator`() = runTest {
        // Given
        coEvery { sessionCoordinator.startSession(any(), any()) } returns Result.success(Unit)

        // When
        viewModel.startRecording()
        advanceUntilIdle()

        // Then
        coVerify { sessionCoordinator.startSession("test-session", null) }
    }

    @Test
    fun `stopRecording calls coordinator`() = runTest {
        // Given
        coEvery { sessionCoordinator.stopSession() } returns Result.success(Unit)

        // When
        viewModel.stopRecording()
        advanceUntilIdle()

        // Then
        coVerify { sessionCoordinator.stopSession() }
    }

    @Test
    fun `runExercise executes exercise`() = runTest {
        // Given
        val result = mockk<RecordingExerciseResult>(relaxed = true)
        coEvery { exercise.run() } returns result

        // When
        viewModel.runExercise()
        advanceUntilIdle()

        // Then
        coVerify { exercise.run() }
    }
}
