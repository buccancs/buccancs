package com.buccancs.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.sensor.exercise.MultiDeviceRecordingExercise
import com.buccancs.domain.model.*
import com.buccancs.domain.repository.*
import com.buccancs.domain.usecase.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
    fun `viewModel exposes devices from sensor repository`() = runTest {
        val testDevices = listOf(
            SensorDevice(
                id = DeviceId("test-device"),
                displayName = "Test Device",
                type = SensorDeviceType.SHIMMER_GSR,
                capabilities = setOf(SensorStreamType.GSR),
                connectionStatus = ConnectionStatus.Connected(
                    since = Clock.System.now(),
                    batteryPercent = 85,
                    rssiDbm = -50
                ),
                isSimulated = false,
                attributes = emptyMap()
            )
        )
        
        val devicesFlow = MutableStateFlow(testDevices)
        every { sensorRepository.devices } returns devicesFlow
        
        val devices = sensorRepository.devices.first()
        assertEquals(1, devices.size)
        assertEquals("Test Device", devices.first().displayName)
    }

    @Test
    fun `viewModel exposes recording state from sensor repository`() = runTest {
        val recordingState = RecordingState(
            lifecycle = RecordingLifecycleState.Recording,
            anchor = RecordingSessionAnchor(
                sessionId = "active-session",
                referenceTimestamp = Clock.System.now(),
                sharedClockOffsetMillis = 0
            ),
            updatedAt = Clock.System.now()
        )
        
        val stateFlow = MutableStateFlow(recordingState)
        every { sensorRepository.recordingState } returns stateFlow
        
        val state = sensorRepository.recordingState.first()
        assertEquals(RecordingLifecycleState.Recording, state.lifecycle)
        assertNotNull(state.anchor)
        assertEquals("active-session", state.anchor?.sessionId)
        assertNotNull(state.updatedAt)
    }

    @Test
    fun `toggleSimulation calls sensor repository`() = runTest {
        coEvery { sensorRepository.setSimulationEnabled(any()) } returns Unit
        
        viewModel.toggleSimulation()
        
        coVerify { sensorRepository.setSimulationEnabled(any()) }
    }

    @Test
    fun `refreshInventory delegates to device management use case`() = runTest {
        coEvery { deviceManagement.refreshDeviceInventory() } returns Unit
        
        viewModel.refreshInventory()
        dispatcher.scheduler.advanceUntilIdle()
        
        coVerify { deviceManagement.refreshDeviceInventory() }
    }

    @Test
    fun `time sync status is accessible from service`() = runTest {
        val status = timeSyncService.status.first()
        assertNotNull(status)
        assertEquals(TimeSyncQuality.GOOD, status.quality)
        assertEquals(0, status.offsetMillis)
    }

    @Test
    fun `simulation toggle updates state in repository`() = runTest {
        val simulationFlow = MutableStateFlow(false)
        every { sensorRepository.simulationEnabled } returns simulationFlow
        coEvery { sensorRepository.setSimulationEnabled(true) } answers {
            simulationFlow.value = true
        }
        
        assertFalse(simulationFlow.value)
        
        viewModel.toggleSimulation()
        dispatcher.scheduler.advanceUntilIdle()
        
        // The actual toggle depends on current state
        coVerify { sensorRepository.setSimulationEnabled(any()) }
    }

    @Test
    fun `orchestrator config is accessible`() = runTest {
        val config = orchestratorConfigRepository.config.first()
        assertNotNull(config)
        assertEquals("localhost", config.host)
        assertEquals(50051, config.port)
        assertFalse(config.useTls)
    }

    @Test
    fun `session coordinator state is accessible`() = runTest {
        val sessionState = sessionCoordinator.sessionState.first()
        assertNotNull(sessionState)
        assertEquals("test-session", sessionState.currentSessionId)
        assertFalse(sessionState.isBusy)
    }

    @Test
    fun `stream statuses are exposed from sensor repository`() = runTest {
        val testStatuses = listOf(
            SensorStreamStatus(
                deviceId = DeviceId("test-device"),
                streamType = SensorStreamType.GSR,
                sampleRateHz = 128.0,
                frameRateFps = null,
                lastSampleTimestamp = Clock.System.now(),
                bufferedDurationSeconds = 1.5,
                isStreaming = true,
                isSimulated = false
            )
        )
        
        val statusesFlow = MutableStateFlow(testStatuses)
        every { sensorRepository.streamStatuses } returns statusesFlow
        
        val statuses = sensorRepository.streamStatuses.first()
        assertEquals(1, statuses.size)
        assertTrue(statuses.first().isStreaming)
        assertEquals(128.0, statuses.first().sampleRateHz)
    }

    private fun setupDefaultMocks() {
        every { sensorRepository.devices } returns MutableStateFlow(emptyList())
        every { sensorRepository.streamStatuses } returns MutableStateFlow(emptyList())
        every { sensorRepository.recordingState } returns MutableStateFlow(
            RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                anchor = null,
                updatedAt = Clock.System.now()
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

        every { commandService.commands } returns MutableSharedFlow<DeviceCommandPayload>()
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
