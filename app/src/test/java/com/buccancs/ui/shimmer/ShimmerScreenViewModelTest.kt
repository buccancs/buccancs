package com.buccancs.ui.shimmer

import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SHIMMER_PRIMARY_DEVICE_ID
import com.buccancs.domain.usecase.DeviceManagementUseCase
import com.buccancs.domain.usecase.HardwareConfigurationUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ShimmerScreenViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var hardwareConfiguration: HardwareConfigurationUseCase
    private lateinit var deviceManagement: DeviceManagementUseCase
    private lateinit var devicesFlow: MutableStateFlow<List<SensorDevice>>
    private lateinit var viewModel: ShimmerScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        hardwareConfiguration = mockk(relaxed = true)
        deviceManagement = mockk(relaxed = true)
        devicesFlow = MutableStateFlow(emptyList())

        every { deviceManagement.devices } returns devicesFlow

        viewModel = ShimmerScreenViewModel(hardwareConfiguration, deviceManagement)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is correct`() = runTest {
        val state = viewModel.uiState.first()

        assertFalse(state.isConnected)
        assertFalse(state.isConnecting)
        assertFalse(state.isStreaming)
        assertFalse(state.isScanning)
        assertNull(state.deviceName)
        assertNull(state.deviceAddress)
        assertEquals(0, state.gsrRangeIndex)
        assertEquals(64.0, state.sampleRate)
        assertNull(state.errorMessage)
    }

    @Test
    fun `observes device state changes`() = runTest {
        val shimmerDevice = SensorDevice(
            id = SHIMMER_PRIMARY_DEVICE_ID,
            type = SensorDeviceType.SHIMMER_GSR,
            displayName = "Shimmer3 GSR",
            connectionStatus = ConnectionStatus.Connected,
            isSimulated = false,
            streams = emptyList(),
            attributes = mapOf("shimmer.selected" to "E8:EB:1B:97:67:FC")
        )

        devicesFlow.value = listOf(shimmerDevice)
        advanceUntilIdle()

        val state = viewModel.uiState.first()
        assertTrue(state.isConnected)
        assertEquals("Shimmer3 GSR", state.deviceName)
        assertEquals("E8:EB:1B:97:67:FC", state.deviceAddress)
    }

    @Test
    fun `connectDevice calls device management`() = runTest {
        coEvery { deviceManagement.connectDevice(SHIMMER_PRIMARY_DEVICE_ID) } returns Result.success(Unit)

        viewModel.connectDevice()
        advanceUntilIdle()

        coVerify { deviceManagement.connectDevice(SHIMMER_PRIMARY_DEVICE_ID) }
    }

    @Test
    fun `connectDevice handles failure`() = runTest {
        val errorMessage = "Connection failed"
        coEvery { deviceManagement.connectDevice(SHIMMER_PRIMARY_DEVICE_ID) } returns
                Result.failure(RuntimeException(errorMessage))

        viewModel.connectDevice()
        advanceUntilIdle()

        val state = viewModel.uiState.first()
        assertEquals(errorMessage, state.errorMessage)
    }

    @Test
    fun `disconnectDevice calls device management`() = runTest {
        coEvery { deviceManagement.disconnectDevice(SHIMMER_PRIMARY_DEVICE_ID) } returns Result.success(Unit)

        viewModel.disconnectDevice()
        advanceUntilIdle()

        coVerify { deviceManagement.disconnectDevice(SHIMMER_PRIMARY_DEVICE_ID) }
    }

    @Test
    fun `scanForDevices calls refresh inventory`() = runTest {
        coEvery { deviceManagement.refreshInventory() } returns Result.success(Unit)

        viewModel.scanForDevices()
        advanceUntilIdle()

        coVerify { deviceManagement.refreshInventory() }
        val state = viewModel.uiState.first()
        assertFalse(state.isScanning)
    }

    @Test
    fun `updateGsrRange calls hardware configuration`() = runTest {
        val rangeIndex = 2
        coEvery {
            hardwareConfiguration.configureShimmerGsrRange(SHIMMER_PRIMARY_DEVICE_ID, rangeIndex)
        } returns Result.success(Unit)

        viewModel.updateGsrRange(rangeIndex)
        advanceUntilIdle()

        coVerify {
            hardwareConfiguration.configureShimmerGsrRange(SHIMMER_PRIMARY_DEVICE_ID, rangeIndex)
        }

        val state = viewModel.uiState.first()
        assertEquals(rangeIndex, state.gsrRangeIndex)
    }

    @Test
    fun `updateSampleRate calls hardware configuration`() = runTest {
        val sampleRate = 128.0
        coEvery {
            hardwareConfiguration.configureShimmerSampleRate(SHIMMER_PRIMARY_DEVICE_ID, sampleRate)
        } returns Result.success(Unit)

        viewModel.updateSampleRate(sampleRate)
        advanceUntilIdle()

        coVerify {
            hardwareConfiguration.configureShimmerSampleRate(SHIMMER_PRIMARY_DEVICE_ID, sampleRate)
        }

        val state = viewModel.uiState.first()
        assertEquals(sampleRate, state.sampleRate)
    }

    @Test
    fun `clearError removes error message`() = runTest {
        coEvery { deviceManagement.connectDevice(any()) } returns
                Result.failure(RuntimeException("Test error"))

        viewModel.connectDevice()
        advanceUntilIdle()

        var state = viewModel.uiState.first()
        assertEquals("Test error", state.errorMessage)

        viewModel.clearError()

        state = viewModel.uiState.first()
        assertNull(state.errorMessage)
    }
}
