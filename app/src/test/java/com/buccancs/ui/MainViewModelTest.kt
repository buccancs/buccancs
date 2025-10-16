package com.buccancs.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.sensor.exercise.MultiDeviceRecordingExercise
import com.buccancs.domain.model.*
import com.buccancs.domain.repository.*
import com.buccancs.domain.usecase.*
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

class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private lateinit var sensorRepository: SensorRepository
    private lateinit var timeSyncService: TimeSyncService
    private lateinit var orchestratorConfigRepository: OrchestratorConfigRepository
    private lateinit var commandService: DeviceCommandService
    private lateinit var eventRepository: DeviceEventRepository
    private lateinit var shimmerSettingsRepository: ShimmerSettingsRepository
    private lateinit var hardwareConfigRepository: SensorHardwareConfigRepository
    private lateinit var topdonDeviceRepository: TopdonDeviceRepository
    private lateinit var exercise: MultiDeviceRecordingExercise
    private lateinit var sessionCoordinator: SessionCoordinator
    private lateinit var deviceManagement: DeviceManagementUseCase
    private lateinit var hardwareConfiguration: HardwareConfigurationUseCase
    private lateinit var remoteCommandCoordinator: RemoteCommandCoordinator
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        // Create mocks
        sensorRepository = mockk(relaxed = true)
        timeSyncService = mockk(relaxed = true)
        orchestratorConfigRepository = mockk(relaxed = true)
        commandService = mockk(relaxed = true)
        eventRepository = mockk(relaxed = true)
        shimmerSettingsRepository = mockk(relaxed = true)
        hardwareConfigRepository = mockk(relaxed = true)
        topdonDeviceRepository = mockk(relaxed = true)
        exercise = mockk(relaxed = true)
        sessionCoordinator = mockk(relaxed = true)
        deviceManagement = mockk(relaxed = true)
        hardwareConfiguration = mockk(relaxed = true)
        remoteCommandCoordinator = mockk(relaxed = true)

        setupDefaultMocks()

        viewModel = MainViewModel(
            sensorRepository = sensorRepository,
            timeSyncService = timeSyncService,
            orchestratorConfigRepository = orchestratorConfigRepository,
            commandService = commandService,
            deviceEventRepository = eventRepository,
            shimmerSettingsRepository = shimmerSettingsRepository,
            hardwareConfigRepository = hardwareConfigRepository,
            topdonDeviceRepository = topdonDeviceRepository,
            exercise = exercise,
            sessionCoordinator = sessionCoordinator,
            deviceManagement = deviceManagement,
            hardwareConfiguration = hardwareConfiguration,
            remoteCommandCoordinator = remoteCommandCoordinator
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `viewModel initializes successfully`() = runTest {
        assertNotNull(viewModel)
    }

    @Test
    fun `sensor repository is accessible`() = runTest {
        assertNotNull(sensorRepository)
    }

    @Test
    fun `time sync service is accessible`() = runTest {
        assertNotNull(timeSyncService)
    }

    @Test
    fun `session coordinator is accessible`() = runTest {
        assertNotNull(sessionCoordinator)
    }

    private fun setupDefaultMocks() {
        every { sensorRepository.devices } returns MutableStateFlow(emptyList())
        every { sensorRepository.streamStatuses } returns MutableStateFlow(emptyList())
        every { sensorRepository.recordingState } returns MutableStateFlow(
            RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                startedAt = null,
                anchor = null
            )
        )
        every { sensorRepository.simulationEnabled } returns MutableStateFlow(false)

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

        every { orchestratorConfigRepository.config } returns MutableStateFlow(
            OrchestratorConfig(
                host = "localhost",
                port = 50051,
                useTls = false
            )
        )

        every { commandService.commands } returns MutableStateFlow(null)
        every { commandService.lastCommand } returns MutableStateFlow(null)
        every { eventRepository.events } returns MutableStateFlow(emptyList())

        every { sessionCoordinator.sessionState } returns MutableStateFlow(
            SessionState(
                currentSessionId = "test-session",
                isBusy = false,
                lastError = null
            )
        )
        every { sessionCoordinator.generateSessionId() } returns "test-session-${System.currentTimeMillis()}"
    }
}
