package com.buccancs.domain.usecase

import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
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
import kotlin.test.assertTrue

class DeviceManagementUseCaseTest {

    private lateinit var sensorRepository: SensorRepository
    private lateinit var hardwareConfigRepository: SensorHardwareConfigRepository
    private lateinit var useCase: DeviceManagementUseCaseImpl

    private val testDevices = listOf(
        SensorDevice(
            id = DeviceId("device-1"),
            type = SensorDeviceType.SHIMMER_GSR,
            displayName = "Test Device 1",
            capabilities = listOf(SensorStreamType.GSR),
            connectionStatus = ConnectionStatus.Disconnected,
            isSimulated = false,
            attributes = emptyMap()
        )
    )

    @Before
    fun setup() {
        sensorRepository = mockk(relaxed = true)
        hardwareConfigRepository = mockk(relaxed = true)
        
        every { sensorRepository.devices } returns MutableStateFlow(testDevices)
        every { sensorRepository.simulationEnabled } returns MutableStateFlow(false)

        useCase = DeviceManagementUseCaseImpl(sensorRepository, hardwareConfigRepository)
    }

    @Test
    fun `connectDevice succeeds`() = runTest {
        // Given
        val deviceId = DeviceId("device-1")
        coEvery { sensorRepository.connect(deviceId) } returns Unit

        // When
        val result = useCase.connectDevice(deviceId)

        // Then
        assertTrue(result.isSuccess)
        coVerify { sensorRepository.connect(deviceId) }
    }

    @Test
    fun `connectDevice handles failure`() = runTest {
        // Given
        val deviceId = DeviceId("device-1")
        val errorMessage = "Connection failed"
        coEvery { sensorRepository.connect(deviceId) } throws RuntimeException(errorMessage)

        // When
        val result = useCase.connectDevice(deviceId)

        // Then
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `disconnectDevice succeeds`() = runTest {
        // Given
        val deviceId = DeviceId("device-1")
        coEvery { sensorRepository.disconnect(deviceId) } returns Unit

        // When
        val result = useCase.disconnectDevice(deviceId)

        // Then
        assertTrue(result.isSuccess)
        coVerify { sensorRepository.disconnect(deviceId) }
    }

    @Test
    fun `disconnectDevice handles failure`() = runTest {
        // Given
        val deviceId = DeviceId("device-1")
        coEvery { sensorRepository.disconnect(deviceId) } throws RuntimeException("Disconnect failed")

        // When
        val result = useCase.disconnectDevice(deviceId)

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `refreshInventory succeeds`() = runTest {
        // Given
        coEvery { sensorRepository.refreshInventory() } returns Unit

        // When
        val result = useCase.refreshInventory()

        // Then
        assertTrue(result.isSuccess)
        coVerify { sensorRepository.refreshInventory() }
    }

    @Test
    fun `refreshInventory handles failure`() = runTest {
        // Given
        coEvery { sensorRepository.refreshInventory() } throws RuntimeException("Refresh failed")

        // When
        val result = useCase.refreshInventory()

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `toggleSimulation enables when disabled`() = runTest {
        // Given
        every { sensorRepository.simulationEnabled } returns MutableStateFlow(false)
        coEvery { sensorRepository.setSimulationEnabled(true) } returns Unit

        // When
        val result = useCase.toggleSimulation()

        // Then
        assertTrue(result.isSuccess)
        coVerify { sensorRepository.setSimulationEnabled(true) }
    }

    @Test
    fun `toggleSimulation disables when enabled`() = runTest {
        // Given
        every { sensorRepository.simulationEnabled } returns MutableStateFlow(true)
        coEvery { sensorRepository.setSimulationEnabled(false) } returns Unit

        // When
        val result = useCase.toggleSimulation()

        // Then
        assertTrue(result.isSuccess)
        coVerify { sensorRepository.setSimulationEnabled(false) }
    }

    @Test
    fun `toggleSimulation handles failure`() = runTest {
        // Given
        coEvery { sensorRepository.setSimulationEnabled(any()) } throws RuntimeException("Toggle failed")

        // When
        val result = useCase.toggleSimulation()

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `devices flow exposes repository devices`() = runTest {
        // When
        val devices = useCase.devices.first()

        // Then
        assertEquals(testDevices, devices)
    }

    @Test
    fun `simulationEnabled flow exposes repository state`() = runTest {
        // Given
        every { sensorRepository.simulationEnabled } returns MutableStateFlow(true)

        // When
        val enabled = useCase.simulationEnabled.first()

        // Then
        assertTrue(enabled)
    }
}
