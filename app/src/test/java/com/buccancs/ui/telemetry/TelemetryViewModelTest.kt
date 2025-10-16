package com.buccancs.ui.telemetry

import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.control.commands.SyncSignalCommandPayload
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.ui.StreamUiMapper
import com.buccancs.util.nowInstant
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TelemetryViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var sensorRepository: SensorRepository
    private lateinit var timeSyncService: TimeSyncService
    private lateinit var deviceEventRepository: DeviceEventRepository
    private lateinit var commandService: DeviceCommandService
    private lateinit var streamUiMapper: StreamUiMapper
    private lateinit var syncSignals: MutableSharedFlow<Unit>
    private lateinit var viewModel: TelemetryViewModel

    private val testTimeSyncStatus = TimeSyncStatus(
        offsetMillis = 0,
        roundTripMillis = 100,
        lastSync = nowInstant(),
        driftEstimateMillisPerMinute = 0.0,
        filteredRoundTripMillis = 100.0,
        quality = TimeSyncQuality.GOOD,
        sampleCount = 10,
        regressionSlopeMillisPerMinute = 0.0
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        sensorRepository = mockk(relaxed = true)
        timeSyncService = mockk(relaxed = true)
        deviceEventRepository = mockk(relaxed = true)
        commandService = mockk(relaxed = true)
        streamUiMapper = mockk(relaxed = true)

        syncSignals = MutableSharedFlow()

        every { sensorRepository.streamStatuses } returns MutableStateFlow(emptyList())
        every { timeSyncService.status } returns MutableStateFlow(testTimeSyncStatus)
        every { deviceEventRepository.events } returns MutableStateFlow(emptyList())
        every { commandService.lastCommand } returns MutableStateFlow(null)
        every { commandService.syncSignals } returns syncSignals

        every { streamUiMapper.toUiModel(any()) } returns mockk(relaxed = true)

        viewModel = TelemetryViewModel(
            sensorRepository,
            timeSyncService,
            deviceEventRepository,
            commandService,
            streamUiMapper
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState initial values are correct`() = runTest {
        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then
        assertEquals(emptyList(), state.streamStatuses)
        assertEquals(testTimeSyncStatus, state.timeSyncStatus)
        assertEquals(emptyList(), state.deviceEvents)
        assertEquals(null, state.lastCommandMessage)
        assertFalse(state.showSyncFlash)
    }

    @Test
    fun `sync flash shows on sync signal`() = runTest {
        // Given
        advanceUntilIdle()

        // When
        syncSignals.emit(Unit)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertTrue(state.showSyncFlash)
    }

    @Test
    fun `sync flash hides after duration`() = runTest {
        // Given
        syncSignals.emit(Unit)
        advanceUntilIdle()

        var state = viewModel.uiState.first()
        assertTrue(state.showSyncFlash)

        // When - advance past flash duration (400ms)
        advanceTimeBy(500)

        // Then
        state = viewModel.uiState.first()
        assertFalse(state.showSyncFlash)
    }

    @Test
    fun `multiple sync signals cancel previous flash`() = runTest {
        // Given
        syncSignals.emit(Unit)
        advanceUntilIdle()
        assertTrue(viewModel.uiState.first().showSyncFlash)

        // When - emit another signal before first completes
        advanceTimeBy(200) // Half way through
        syncSignals.emit(Unit)
        advanceUntilIdle()

        // Then - still showing
        assertTrue(viewModel.uiState.first().showSyncFlash)

        // And after full duration from second signal
        advanceTimeBy(500)
        assertFalse(viewModel.uiState.first().showSyncFlash)
    }

    @Test
    fun `connection status derives from time sync`() = runTest {
        // Given - fresh sync (good quality)
        val freshSync = testTimeSyncStatus.copy(
            lastSync = nowInstant(),
            quality = TimeSyncQuality.GOOD
        )
        every { timeSyncService.status } returns MutableStateFlow(freshSync)

        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then
        assertTrue(state.orchestratorConnectionStatus.contains("good"))
        assertTrue(state.orchestratorConnectionStatus.contains("fresh"))
    }

    @Test
    fun `device events are limited to 10 most recent`() = runTest {
        // Given - 15 events
        val events = (1..15).map { i ->
            DeviceEvent(
                id = "event-$i",
                label = "Event $i",
                type = DeviceEventType.MARKER,
                scheduledAt = nowInstant(),
                receivedAt = nowInstant()
            )
        }
        every { deviceEventRepository.events } returns MutableStateFlow(events)

        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then - only 10 most recent (sorted by receivedAt desc)
        assertEquals(10, state.deviceEvents.size)
    }

    @Test
    fun `lastCommandMessage formats sync signal command`() = runTest {
        // Given
        val command = SyncSignalCommandPayload(
            commandId = "cmd-1",
            signalType = "VISUAL",
            executeEpochMs = System.currentTimeMillis()
        )
        every { commandService.lastCommand } returns MutableStateFlow(command)

        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then
        assertTrue(state.lastCommandMessage?.contains("Sync") == true)
        assertTrue(state.lastCommandMessage?.contains("VISUAL") == true)
    }

    @Test
    fun `stream statuses are mapped via mapper`() = runTest {
        // Given
        val streamStatus = mockk<com.buccancs.domain.model.SensorStreamStatus>(relaxed = true)
        every { sensorRepository.streamStatuses } returns MutableStateFlow(listOf(streamStatus))
        every { streamUiMapper.toUiModel(streamStatus) } returns mockk(relaxed = true)

        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then
        assertEquals(1, state.streamStatuses.size)
    }
}
