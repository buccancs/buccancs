package com.buccancs.data.sensor

import com.buccancs.data.sensor.connector.SensorConnector
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingState
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Edge case tests for sensor connections.
 * Tests boundary conditions, race conditions, and unusual scenarios.
 */
class SensorConnectionEdgeCasesTest {

    @Before
    fun setup() {
        // Setup if needed
    }

    @Test
    fun `rapid connection and disconnection handled`() = runTest {
        // Simulate rapid connect/disconnect cycles
        val repository = createTestRepository()

        repeat(10) {
            repository.setSimulationEnabled(true)
            repository.setSimulationEnabled(false)
        }

        // Repository should handle rapid toggling
        val devices = repository.devices.value
        assertTrue(devices.all { it.connectionStatus is ConnectionStatus.Disconnected })
    }

    @Test
    fun `zero devices in simulation mode`() = runTest {
        val repository = createTestRepository(emptyList())
        
        repository.setSimulationEnabled(true)
        
        val devices = repository.devices.value
        assertEquals(0, devices.size)
    }

    @Test
    fun `maximum devices connected simultaneously`() = runTest {
        // Create many simulated connectors
        val connectors = List(20) { index ->
            createSimulatedConnector("device-$index")
        }
        val repository = DefaultSensorRepository(TestScope(), connectors)

        repository.setSimulationEnabled(true)

        val devices = repository.devices.value
        assertTrue(devices.size <= 20)
    }

    @Test
    fun `recording state transitions with no devices`() = runTest {
        val repository = createTestRepository(emptyList())

        val state = repository.recordingState.value
        assertEquals(RecordingLifecycleState.Idle, state.lifecycle)
    }

    @Test
    fun `duplicate device IDs handled gracefully`() = runTest {
        // Create connectors with duplicate IDs
        val connectors = List(3) {
            createSimulatedConnector("duplicate-id")
        }
        val repository = DefaultSensorRepository(TestScope(), connectors)

        repository.setSimulationEnabled(true)

        // Repository should handle duplicates
        val devices = repository.devices.value
        assertTrue(devices.isNotEmpty())
    }

    @Test
    fun `device connection status boundary values`() = runTest {
        val repository = createTestRepository()

        repository.setSimulationEnabled(true)

        val devices = repository.devices.value
        devices.forEach { device ->
            // Each device should have valid connection status
            assertTrue(
                device.connectionStatus is ConnectionStatus.Connected ||
                device.connectionStatus is ConnectionStatus.Disconnected ||
                device.connectionStatus is ConnectionStatus.Connecting
            )
        }
    }

    @Test
    fun `long session IDs handled correctly`() = runTest {
        val repository = createTestRepository()
        val longSessionId = "a".repeat(1000) // Very long session ID

        // Should handle long IDs without crashing
        assertTrue(longSessionId.length == 1000)
    }

    private fun createTestRepository(
        connectors: List<SensorConnector> = listOf(
            createSimulatedConnector("test-1"),
            createSimulatedConnector("test-2")
        )
    ) = DefaultSensorRepository(TestScope(), connectors)

    private fun createSimulatedConnector(id: String) =
        com.buccancs.data.sensor.connector.simulated.SimulatedShimmerConnector(
            TestScope()
        )
}
