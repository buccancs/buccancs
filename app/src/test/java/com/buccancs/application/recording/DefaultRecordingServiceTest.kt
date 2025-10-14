package com.buccancs.application.recording

import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.util.nowInstant
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class DefaultRecordingServiceTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var sensorRepository: SensorRepository
    private lateinit var service: DefaultRecordingService

    @Before
    fun setup() {
        hiltRule.inject()
        
        // Mock repository
        sensorRepository = mockk(relaxed = true)
        every { sensorRepository.recordingState } returns MutableStateFlow(
            com.buccancs.domain.model.RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                startedAt = null
            )
        )
        
        service = DefaultRecordingService(sensorRepository)
    }

    @Test
    fun `starting recording calls sensor repository`() = runTest {
        val anchor = RecordingSessionAnchor(
            sessionId = "test-session",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )

        coEvery { sensorRepository.startStreaming(any()) } returns Unit

        service.startRecording(anchor)

        coVerify { sensorRepository.startStreaming(anchor) }
    }

    @Test
    fun `stopping recording finalises session`() = runTest {
        val anchor = RecordingSessionAnchor(
            sessionId = "test-session",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )

        coEvery { sensorRepository.startStreaming(any()) } returns Unit
        coEvery { sensorRepository.stopStreaming() } returns Unit

        service.startRecording(anchor)
        service.stopRecording()

        coVerify { sensorRepository.stopStreaming() }
    }

    @Test
    fun `recording state reflects service state`() = runTest {
        val state = service.getRecordingState()
        
        assertEquals(RecordingLifecycleState.Idle, state.lifecycle)
    }
}
