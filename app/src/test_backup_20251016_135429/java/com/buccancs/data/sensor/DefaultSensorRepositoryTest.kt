package com.buccancs.data.sensor

import com.buccancs.data.sensor.connector.SensorConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedMicrophoneConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedRgbCameraConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedShimmerConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedThermalConnector
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.util.nowInstant
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DefaultSensorRepositoryTest {
    @Test
    fun simulationToggleConnectsDevices() = runTest {
        val repository = createRepository(this)
        repository.setSimulationEnabled(true)
        advanceUntilIdle()
        val devices = repository.devices.value
        assertTrue(devices.isNotEmpty())
        assertTrue(devices.all { it.connectionStatus is ConnectionStatus.Connected })
        assertTrue(repository.simulationEnabled.value)
    }

    @Test
    fun startStreamingPopulatesStreamStatuses() = runTest {
        val repository = createRepository(this)
        repository.setSimulationEnabled(true)
        advanceUntilIdle()
        val anchor = RecordingSessionAnchor(
            sessionId = "test-session",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )
        repository.startStreaming(anchor)
        advanceTimeBy(400L)
        val statuses = repository.streamStatuses.value
        assertTrue(statuses.isNotEmpty())
        val gsrStream = statuses.firstOrNull { it.streamType == SensorStreamType.GSR }
        assertTrue(gsrStream != null)
        val state = repository.recordingState.value
        assertEquals(RecordingLifecycleState.Recording, state.lifecycle)
        repository.stopStreaming()
    }

    @Test
    fun disablingSimulationDisconnectsDevices() = runTest {
        val repository = createRepository(this)
        repository.setSimulationEnabled(true)
        advanceUntilIdle()
        val anchor = RecordingSessionAnchor(
            sessionId = "test-session",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )
        repository.startStreaming(anchor)
        advanceTimeBy(400L)
        repository.setSimulationEnabled(false)
        advanceUntilIdle()
        val devices = repository.devices.value
        assertTrue(devices.all { it.connectionStatus is ConnectionStatus.Disconnected })
        assertTrue(repository.streamStatuses.value.isEmpty())
    }

    private fun createRepository(scope: TestScope): DefaultSensorRepository {
        val connectors: List<SensorConnector> = listOf(
            SimulatedShimmerConnector(scope),
            SimulatedRgbCameraConnector(scope),
            SimulatedThermalConnector(scope),
            SimulatedMicrophoneConnector(scope)
        )
        return DefaultSensorRepository(scope, connectors)
    }
}
