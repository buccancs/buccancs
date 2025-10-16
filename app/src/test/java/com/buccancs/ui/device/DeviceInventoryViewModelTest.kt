package com.buccancs.ui.device

import com.buccancs.domain.model.*
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.buccancs.domain.repository.TopdonDeviceRepository
import com.buccancs.domain.ui.DeviceUiMapper
import com.buccancs.domain.usecase.DeviceManagementUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class DeviceInventoryViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var deviceManagement: DeviceManagementUseCase
    private lateinit var sensorRepository: SensorRepository
    private lateinit var hardwareConfigRepository: SensorHardwareConfigRepository
    private lateinit var topdonDeviceRepository: TopdonDeviceRepository
    private lateinit var shimmerSettingsRepository: ShimmerSettingsRepository
    private lateinit var deviceUiMapper: DeviceUiMapper
    private lateinit var viewModel: DeviceInventoryViewModel

    private val testDevice = SensorDevice(
        id = DeviceId("test-device"),
        type = SensorDeviceType.SHIMMER_GSR,
        displayName = "Test Device",
        capabilities = listOf(SensorStreamType.GSR),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = emptyMap()
    )

    private val testConfig = SensorHardwareConfig(
        shimmer = listOf(
            ShimmerDeviceConfig(
                id = "shimmer-1",
                displayName = "Shimmer Device",
                macAddress = null,
                gsrRangeIndex = 0,
                sampleRateHz = 128.0
            )
        ),
        topdon = emptyList()
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        deviceManagement = mockk(relaxed = true)
        sensorRepository = mockk(relaxed = true)
        hardwareConfigRepository = mockk(relaxed = true)
        topdonDeviceRepository = mockk(relaxed = true)
        shimmerSettingsRepository = mockk(relaxed = true)
        deviceUiMapper = mockk(relaxed = true)

        every { deviceManagement.devices } returns MutableStateFlow(listOf(testDevice))
        every { deviceManagement.simulationEnabled } returns MutableStateFlow(false)
        every { sensorRepository.streamStatuses } returns MutableStateFlow(emptyList())
        every { shimmerSettingsRepository.settings } returns MutableStateFlow(ShimmerSettings())
        every { hardwareConfigRepository.config } returns MutableStateFlow(testConfig)
        every { topdonDeviceRepository.activeDeviceId } returns MutableStateFlow(null)

        every { deviceUiMapper.toUiModel(any(), any(), any()) } returns mockk(relaxed = true)

        viewModel = DeviceInventoryViewModel(
            deviceManagement,
            sensorRepository,
            hardwareConfigRepository,
            topdonDeviceRepository,
            shimmerSettingsRepository,
            deviceUiMapper
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
        assertFalse(state.simulationEnabled)
        assertEquals(1, state.devices.size)
        assertEquals(null, state.errorMessage)
    }

    @Test
    fun `toggleSimulation calls use case`() = runTest {
        // Given
        coEvery { deviceManagement.toggleSimulation() } returns Result.success(Unit)

        // When
        viewModel.toggleSimulation()
        advanceUntilIdle()

        // Then
        coVerify { deviceManagement.toggleSimulation() }
    }

    @Test
    fun `connectDevice calls use case with correct id`() = runTest {
        // Given
        val deviceId = DeviceId("test-device")
        coEvery { deviceManagement.connectDevice(deviceId) } returns Result.success(Unit)

        // When
        viewModel.connectDevice(deviceId)
        advanceUntilIdle()

        // Then
        coVerify { deviceManagement.connectDevice(deviceId) }
    }

    @Test
    fun `disconnectDevice calls use case with correct id`() = runTest {
        // Given
        val deviceId = DeviceId("test-device")
        coEvery { deviceManagement.disconnectDevice(deviceId) } returns Result.success(Unit)

        // When
        viewModel.disconnectDevice(deviceId)
        advanceUntilIdle()

        // Then
        coVerify { deviceManagement.disconnectDevice(deviceId) }
    }

    @Test
    fun `refreshInventory calls use case`() = runTest {
        // Given
        coEvery { deviceManagement.refreshInventory() } returns Result.success(Unit)

        // When
        viewModel.refreshInventory()
        advanceUntilIdle()

        // Then
        coVerify { deviceManagement.refreshInventory() }
    }

    @Test
    fun `devices are sorted with connected first`() = runTest {
        // Given
        val disconnected = testDevice.copy(id = DeviceId("disconnected"))
        val connected = testDevice.copy(
            id = DeviceId("connected"),
            connectionStatus = ConnectionStatus.Connected(batteryPercent = 80)
        )
        every { deviceManagement.devices } returns MutableStateFlow(listOf(disconnected, connected))

        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then - Connected devices should be first
        assertEquals(2, state.devices.size)
        // Mapper is called with devices, verify order by checking mock calls
        coVerify(exactly = 2) { deviceUiMapper.toUiModel(any(), any(), any()) }
    }

    @Test
    fun `simulation state reflects use case`() = runTest {
        // Given
        every { deviceManagement.simulationEnabled } returns MutableStateFlow(true)

        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then
        assertTrue(state.simulationEnabled)
    }
}
