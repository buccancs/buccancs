package com.buccancs.domain.usecase

import com.buccancs.application.recording.RecordingService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.util.nowInstant
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Instant

class SessionCoordinatorTest {

    private lateinit var recordingService: RecordingService
    private lateinit var timeSyncService: TimeSyncService
    private lateinit var sensorRepository: SensorRepository
    private lateinit var coordinator: SessionCoordinatorImpl

    @Before
    fun setup() {
        recordingService = mockk(relaxed = true)
        timeSyncService = mockk(relaxed = true)
        sensorRepository = mockk(relaxed = true)

        every { timeSyncService.status } returns MutableStateFlow(
            TimeSyncStatus(
                offsetMillis = 0,
                roundTripMillis = 100,
                lastSync = nowInstant(),
                driftEstimateMillisPerMinute = 0.0,
                filteredRoundTripMillis = 100.0,
                quality = TimeSyncQuality.GOOD,
                sampleCount = 10,
                regressionSlopeMillisPerMinute = 0.0
            )
        )

        coordinator = SessionCoordinatorImpl(recordingService, timeSyncService, sensorRepository)
    }

    @Test
    fun `startSession with valid sessionId succeeds`() = runTest {
        // Given
        val sessionId = "test-session-123"
        coEvery { recordingService.startOrResume(any(), any()) } returns Unit

        // When
        val result = coordinator.startSession(sessionId, requestedStart = null)

        // Then
        assertTrue(result.isSuccess)
        coVerify { recordingService.startOrResume(sessionId, null) }

        val state = coordinator.sessionState.first()
        assertEquals(sessionId, state.currentSessionId)
        assertFalse(state.isBusy)
        assertEquals(null, state.lastError)
    }

    @Test
    fun `startSession with blank sessionId generates new one`() = runTest {
        // Given
        coEvery { recordingService.startOrResume(any(), any()) } returns Unit

        // When
        val result = coordinator.startSession("", requestedStart = null)

        // Then
        assertTrue(result.isSuccess)
        coVerify { recordingService.startOrResume(match { it.startsWith("session-") }, null) }

        val state = coordinator.sessionState.first()
        assertTrue(state.currentSessionId.startsWith("session-"))
    }

    @Test
    fun `startSession with requestedStart passes timestamp`() = runTest {
        // Given
        val sessionId = "test-session"
        val requestedStart = Instant.fromEpochMilliseconds(1000000)
        coEvery { recordingService.startOrResume(any(), any()) } returns Unit

        // When
        val result = coordinator.startSession(sessionId, requestedStart)

        // Then
        assertTrue(result.isSuccess)
        coVerify { recordingService.startOrResume(sessionId, requestedStart) }
    }

    @Test
    fun `startSession handles failure and updates error state`() = runTest {
        // Given
        val sessionId = "test-session"
        val errorMessage = "Recording service failed"
        coEvery { recordingService.startOrResume(any(), any()) } throws RuntimeException(errorMessage)

        // When
        val result = coordinator.startSession(sessionId, requestedStart = null)

        // Then
        assertTrue(result.isFailure)

        val state = coordinator.sessionState.first()
        assertFalse(state.isBusy)
        assertEquals(errorMessage, state.lastError)
    }

    @Test
    fun `startSession prevents concurrent operations`() = runTest {
        // Given
        val sessionId = "test-session"
        coEvery { recordingService.startOrResume(any(), any()) } coAnswers {
            kotlinx.coroutines.delay(100)
            Unit
        }

        // When
        val firstCall = kotlinx.coroutines.async { coordinator.startSession(sessionId, null) }
        kotlinx.coroutines.delay(10) // Let first call start
        val secondCall = coordinator.startSession(sessionId, null)

        // Then
        assertTrue(secondCall.isFailure)
        assertEquals("Session coordinator is busy", secondCall.exceptionOrNull()?.message)

        val firstResult = firstCall.await()
        assertTrue(firstResult.isSuccess)
    }

    @Test
    fun `stopSession succeeds`() = runTest {
        // Given
        coEvery { recordingService.stop() } returns Unit

        // When
        val result = coordinator.stopSession()

        // Then
        assertTrue(result.isSuccess)
        coVerify { recordingService.stop() }

        val state = coordinator.sessionState.first()
        assertFalse(state.isBusy)
        assertEquals(null, state.lastError)
    }

    @Test
    fun `stopSession handles failure`() = runTest {
        // Given
        val errorMessage = "Failed to stop"
        coEvery { recordingService.stop() } throws RuntimeException(errorMessage)

        // When
        val result = coordinator.stopSession()

        // Then
        assertTrue(result.isFailure)

        val state = coordinator.sessionState.first()
        assertFalse(state.isBusy)
        assertEquals(errorMessage, state.lastError)
    }

    @Test
    fun `generateSessionId creates valid format`() {
        // When
        val sessionId = coordinator.generateSessionId()

        // Then
        assertTrue(sessionId.startsWith("session-"))
        assertTrue(sessionId.length > 8) // "session-" + timestamp
    }

    @Test
    fun `updateSessionId updates state`() = runTest {
        // Given
        val newSessionId = "custom-session-id"

        // When
        coordinator.updateSessionId(newSessionId)

        // Then
        val state = coordinator.sessionState.first()
        assertEquals(newSessionId, state.currentSessionId)
    }

    @Test
    fun `clearError removes error from state`() = runTest {
        // Given - trigger an error
        coEvery { recordingService.startOrResume(any(), any()) } throws RuntimeException("Test error")
        coordinator.startSession("test", null)

        // Verify error exists
        var state = coordinator.sessionState.first()
        assertNotNull(state.lastError)

        // When
        coordinator.clearError()

        // Then
        state = coordinator.sessionState.first()
        assertEquals(null, state.lastError)
    }
}
