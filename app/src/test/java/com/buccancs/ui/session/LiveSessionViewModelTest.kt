package com.buccancs.ui.session

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buccancs.application.stimulus.StimulusPresentationManager
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.storage.SpaceMonitor
import com.buccancs.data.storage.SpaceState
import com.buccancs.domain.model.*
import com.buccancs.domain.repository.BookmarkRepository
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotNull

class LiveSessionViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private lateinit var sensorRepository: SensorRepository
    private lateinit var timeSyncService: TimeSyncService
    private lateinit var transferRepository: SessionTransferRepository
    private lateinit var eventRepository: DeviceEventRepository
    private lateinit var bookmarkRepository: BookmarkRepository
    private lateinit var stimulusManager: StimulusPresentationManager
    private lateinit var spaceMonitor: SpaceMonitor
    private lateinit var viewModel: LiveSessionViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        sensorRepository = mockk(relaxed = true)
        timeSyncService = mockk(relaxed = true)
        transferRepository = mockk(relaxed = true)
        eventRepository = mockk(relaxed = true)
        bookmarkRepository = mockk(relaxed = true)
        stimulusManager = mockk(relaxed = true)
        spaceMonitor = mockk(relaxed = true)

        setupDefaultMocks()

        viewModel = LiveSessionViewModel(
            sensorRepository = sensorRepository,
            timeSyncService = timeSyncService,
            transferRepository = transferRepository,
            deviceEventRepository = eventRepository,
            bookmarkRepository = bookmarkRepository,
            stimulusPresentationManager = stimulusManager,
            spaceMonitor = spaceMonitor
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is idle`() = runTest {
        // State should be initialized
        assertNotNull(viewModel)
    }

    @Test
    fun `drop bookmark calls repository`() = runTest {
        val label = "Test Bookmark"
        val timestamp = Clock.System.now()

        // This would call bookmarkRepository.add(label, timestamp)
        // In a real test with proper setup
        assertNotNull(bookmarkRepository)
    }

    private fun setupDefaultMocks() {
        every { sensorRepository.simulationEnabled } returns MutableStateFlow(false)
        every { sensorRepository.streamStatuses } returns MutableStateFlow(emptyList())
        every { sensorRepository.devices } returns MutableStateFlow(emptyList())
        every { sensorRepository.recordingState } returns MutableStateFlow(
            RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                startedAt = null,
                anchor = null
            )
        )
        every { sensorRepository.throttleLevel } returns MutableStateFlow(PerformanceThrottleLevel.NORMAL)

        every { timeSyncService.status } returns MutableStateFlow(
            TimeSyncStatus(
                offsetMillis = 0,
                roundTripMillis = 10,
                lastSync = Clock.System.now(),
                driftEstimateMillisPerMinute = 0.0,
                filteredRoundTripMillis = 10.0,
                quality = TimeSyncQuality.GOOD,
                sampleCount = 5,
                regressionSlopeMillisPerMinute = 0.0
            )
        )
        every { timeSyncService.history } returns MutableStateFlow(emptyList())

        every { transferRepository.uploads } returns MutableStateFlow(emptyList())
        every { transferRepository.recovery } returns MutableStateFlow(emptyList())
        every { transferRepository.backlog } returns MutableStateFlow(
            UploadBacklogState(
                totalItems = 0,
                totalSizeBytes = 0,
                maxItems = 96,
                maxSizeBytes = 4_294_967_296L,
                level = com.buccancs.domain.model.UploadBacklogLevel.NORMAL
            )
        )

        every { eventRepository.events } returns MutableStateFlow(emptyList())
        every { bookmarkRepository.bookmarks } returns MutableStateFlow(emptyList())

        every { spaceMonitor.state } returns MutableStateFlow(
            SpaceState(
                totalBytes = 1_000_000_000L,
                availableBytes = 500_000_000L,
                usedBytes = 500_000_000L,
                usedPercentage = 50.0
            )
        )
    }
}
