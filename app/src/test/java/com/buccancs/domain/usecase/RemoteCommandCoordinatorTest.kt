package com.buccancs.domain.usecase

import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.control.commands.StartRecordingCommandPayload
import com.buccancs.control.commands.StopRecordingCommandPayload
import com.buccancs.control.commands.SyncSignalCommandPayload
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.util.nowInstant
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RemoteCommandCoordinatorTest {

    private lateinit var commandService: DeviceCommandService
    private lateinit var sessionCoordinator: SessionCoordinatorImpl
    private lateinit var timeSyncService: TimeSyncService
    private lateinit var coordinator: RemoteCommandCoordinatorImpl

    @Before
    fun setup() {
        commandService = mockk(relaxed = true)
        sessionCoordinator = mockk(relaxed = true)
        timeSyncService = mockk(relaxed = true)
        
        every { commandService.lastCommand } returns MutableStateFlow<DeviceCommandPayload?>(null)
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
        every { sessionCoordinator.sessionState } returns MutableStateFlow(
            SessionState(
                currentSessionId = "test-session",
                isBusy = false,
                lastError = null
            )
        )

        coordinator = RemoteCommandCoordinatorImpl(commandService, sessionCoordinator, timeSyncService)
    }

    @Test
    fun `handleCommand with StartRecording succeeds`() = runTest {
        // Given
        val command = StartRecordingCommandPayload(
            commandId = "cmd-1",
            sessionId = "session-1",
            anchorEpochMs = 0,
            executeEpochMs = System.currentTimeMillis()
        )
        coEvery { sessionCoordinator.startSession(any(), any()) } returns Result.success(Unit)
        coEvery { commandService.acknowledge(any(), any(), any()) } returns Unit

        // When
        val result = coordinator.handleCommand(command)

        // Then
        assertTrue(result.isSuccess)
        val handlingResult = result.getOrNull()!!
        assertTrue(handlingResult.success)
        
        coVerify { sessionCoordinator.startSession("session-1", null) }
        coVerify { commandService.acknowledge("cmd-1", true, null) }
    }

    @Test
    fun `handleCommand with StopRecording succeeds`() = runTest {
        // Given
        val command = StopRecordingCommandPayload(
            commandId = "cmd-2",
            sessionId = "session-1",
            executeEpochMs = System.currentTimeMillis()
        )
        coEvery { sessionCoordinator.stopSession() } returns Result.success(Unit)
        coEvery { commandService.acknowledge(any(), any(), any()) } returns Unit

        // When
        val result = coordinator.handleCommand(command)

        // Then
        assertTrue(result.isSuccess)
        val handlingResult = result.getOrNull()!!
        assertTrue(handlingResult.success)
        
        coVerify { sessionCoordinator.stopSession() }
        coVerify { commandService.acknowledge("cmd-2", true, null) }
    }

    @Test
    fun `handleCommand rejects when coordinator is busy`() = runTest {
        // Given
        every { sessionCoordinator.sessionState } returns MutableStateFlow(
            SessionState(
                currentSessionId = "test-session",
                isBusy = true, // Busy
                lastError = null
            )
        )
        val command = StartRecordingCommandPayload(
            commandId = "cmd-3",
            sessionId = "session-1",
            anchorEpochMs = 0,
            executeEpochMs = System.currentTimeMillis()
        )
        coEvery { commandService.acknowledge(any(), any(), any()) } returns Unit

        // When
        val result = coordinator.handleCommand(command)

        // Then
        assertTrue(result.isSuccess)
        val handlingResult = result.getOrNull()!!
        assertFalse(handlingResult.success)
        assertEquals("Device busy", handlingResult.message)
        
        coVerify { commandService.acknowledge("cmd-3", false, "Device busy") }
        coVerify(exactly = 0) { sessionCoordinator.startSession(any(), any()) }
    }

    @Test
    fun `handleCommand handles session start failure`() = runTest {
        // Given
        val command = StartRecordingCommandPayload(
            commandId = "cmd-4",
            sessionId = "session-1",
            anchorEpochMs = 0,
            executeEpochMs = System.currentTimeMillis()
        )
        val errorMessage = "Failed to start"
        coEvery { sessionCoordinator.startSession(any(), any()) } returns Result.failure(RuntimeException(errorMessage))
        coEvery { commandService.acknowledge(any(), any(), any()) } returns Unit

        // When
        val result = coordinator.handleCommand(command)

        // Then
        assertTrue(result.isSuccess)
        val handlingResult = result.getOrNull()!!
        assertFalse(handlingResult.success)
        assertEquals(errorMessage, handlingResult.message)
        
        coVerify { commandService.acknowledge("cmd-4", false, errorMessage) }
    }

    @Test
    fun `handleCommand with anchor timestamp passes it correctly`() = runTest {
        // Given
        val anchorEpochMs = 1000000L
        val command = StartRecordingCommandPayload(
            commandId = "cmd-5",
            sessionId = "session-1",
            anchorEpochMs = anchorEpochMs,
            executeEpochMs = System.currentTimeMillis()
        )
        coEvery { sessionCoordinator.startSession(any(), any()) } returns Result.success(Unit)
        coEvery { commandService.acknowledge(any(), any(), any()) } returns Unit

        // When
        val result = coordinator.handleCommand(command)

        // Then
        assertTrue(result.isSuccess)
        coVerify { 
            sessionCoordinator.startSession(
                "session-1", 
                match { it != null && it.toEpochMilliseconds() == anchorEpochMs }
            ) 
        }
    }

    @Test
    fun `handleCommand with unsupported command type returns success without action`() = runTest {
        // Given
        val command = SyncSignalCommandPayload(
            commandId = "cmd-6",
            signalType = "VISUAL",
            executeEpochMs = System.currentTimeMillis()
        )

        // When
        val result = coordinator.handleCommand(command)

        // Then
        assertTrue(result.isSuccess)
        val handlingResult = result.getOrNull()!!
        assertTrue(handlingResult.success)
        
        // Should not interact with session coordinator
        coVerify(exactly = 0) { sessionCoordinator.startSession(any(), any()) }
        coVerify(exactly = 0) { sessionCoordinator.stopSession() }
    }

    @Test
    fun `handleCommand updates session ID before starting`() = runTest {
        // Given
        val newSessionId = "new-session-id"
        val command = StartRecordingCommandPayload(
            commandId = "cmd-7",
            sessionId = newSessionId,
            anchorEpochMs = 0,
            executeEpochMs = System.currentTimeMillis()
        )
        coEvery { sessionCoordinator.startSession(any(), any()) } returns Result.success(Unit)
        coEvery { sessionCoordinator.updateSessionId(any()) } returns Unit
        coEvery { commandService.acknowledge(any(), any(), any()) } returns Unit

        // When
        val result = coordinator.handleCommand(command)

        // Then
        assertTrue(result.isSuccess)
        coVerify { sessionCoordinator.updateSessionId(newSessionId) }
        coVerify { sessionCoordinator.startSession(newSessionId, null) }
    }

    @Test
    fun `lastCommand flow exposes command service state`() = runTest {
        // Given
        val testCommand = mockk<DeviceCommandPayload>()
        every { commandService.lastCommand } returns MutableStateFlow(testCommand)

        // When
        val exposed = coordinator.lastCommand

        // Then
        assertEquals(commandService.lastCommand, exposed)
    }
}
