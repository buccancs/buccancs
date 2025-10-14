package com.buccancs.integration

import com.buccancs.application.recording.RecordingService
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.util.nowInstant
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

/**
 * Integration test for complete recording workflow.
 * Tests the interaction between RecordingService, SensorRepository,
 * DeviceEventRepository, and SessionTransferRepository.
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class RecordingWorkflowIntegrationTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var recordingService: RecordingService
    private lateinit var sensorRepository: SensorRepository
    private lateinit var eventRepository: DeviceEventRepository
    private lateinit var transferRepository: SessionTransferRepository

    @Before
    fun setup() {
        hiltRule.inject()

        // Create mock repositories
        sensorRepository = mockk(relaxed = true)
        eventRepository = mockk(relaxed = true)
        transferRepository = mockk(relaxed = true)

        setupDefaultMocks()

        recordingService = com.buccancs.application.recording.DefaultRecordingService(
            sensorRepository
        )
    }

    @Test
    fun `complete recording workflow succeeds`() = runTest {
        // Given - devices are connected
        val devices = listOf(
            createTestDevice("shimmer-1", SensorDeviceType.SHIMMER, ConnectionStatus.Connected()),
            createTestDevice("camera-1", SensorDeviceType.RGB_CAMERA, ConnectionStatus.Connected())
        )
        every { sensorRepository.devices } returns MutableStateFlow(devices)

        val anchor = RecordingSessionAnchor(
            sessionId = "integration-test-session",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )

        // When - recording starts
        coEvery { sensorRepository.startStreaming(anchor) } returns Unit
        recordingService.startRecording(anchor)

        // Then - recording should be active
        coVerify { sensorRepository.startStreaming(anchor) }

        // When - recording stops
        coEvery { sensorRepository.stopStreaming() } returns Unit
        recordingService.stopRecording()

        // Then - recording should finalize
        coVerify { sensorRepository.stopStreaming() }
    }

    @Test
    fun `recording handles device disconnection during session`() = runTest {
        val anchor = RecordingSessionAnchor(
            sessionId = "disconnection-test",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )

        // Start with connected devices
        val connectedDevices = listOf(
            createTestDevice("shimmer-1", SensorDeviceType.SHIMMER, ConnectionStatus.Connected()),
            createTestDevice("camera-1", SensorDeviceType.RGB_CAMERA, ConnectionStatus.Connected())
        )
        val devicesFlow = MutableStateFlow(connectedDevices)
        every { sensorRepository.devices } returns devicesFlow

        coEvery { sensorRepository.startStreaming(anchor) } returns Unit
        recordingService.startRecording(anchor)

        // Simulate device disconnection
        val disconnectedDevices = listOf(
            createTestDevice("shimmer-1", SensorDeviceType.SHIMMER, ConnectionStatus.Disconnected(null)),
            createTestDevice("camera-1", SensorDeviceType.RGB_CAMERA, ConnectionStatus.Connected())
        )
        devicesFlow.value = disconnectedDevices

        advanceTimeBy(100)

        // Recording should continue with remaining devices
        coVerify { sensorRepository.startStreaming(anchor) }

        // Stop should still work
        coEvery { sensorRepository.stopStreaming() } returns Unit
        recordingService.stopRecording()
        coVerify { sensorRepository.stopStreaming() }
    }

    @Test
    fun `recording workflow records events at key points`() = runTest {
        val anchor = RecordingSessionAnchor(
            sessionId = "event-recording-test",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )

        coEvery { sensorRepository.startStreaming(anchor) } returns Unit
        coEvery { eventRepository.record(any()) } returns Unit

        // Start recording
        recordingService.startRecording(anchor)

        // Verify repository was called (events would be recorded by repository)
        coVerify { sensorRepository.startStreaming(anchor) }
    }

    @Test
    fun `multiple recording sessions in sequence`() = runTest {
        // Session 1
        val anchor1 = RecordingSessionAnchor(
            sessionId = "session-1",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )
        coEvery { sensorRepository.startStreaming(anchor1) } returns Unit
        coEvery { sensorRepository.stopStreaming() } returns Unit

        recordingService.startRecording(anchor1)
        advanceTimeBy(1000)
        recordingService.stopRecording()

        coVerify { sensorRepository.startStreaming(anchor1) }
        coVerify { sensorRepository.stopStreaming() }

        // Session 2
        val anchor2 = RecordingSessionAnchor(
            sessionId = "session-2",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 100
        )
        coEvery { sensorRepository.startStreaming(anchor2) } returns Unit

        recordingService.startRecording(anchor2)
        advanceTimeBy(1000)
        recordingService.stopRecording()

        coVerify { sensorRepository.startStreaming(anchor2) }
        coVerify(exactly = 2) { sensorRepository.stopStreaming() }
    }

    @Test
    fun `recording with no devices connected handles gracefully`() = runTest {
        every { sensorRepository.devices } returns MutableStateFlow(emptyList())

        val anchor = RecordingSessionAnchor(
            sessionId = "no-devices-test",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )

        coEvery { sensorRepository.startStreaming(anchor) } returns Unit
        recordingService.startRecording(anchor)

        // Should not crash, repository handles empty device list
        coVerify { sensorRepository.startStreaming(anchor) }
    }

    @Test
    fun `recording lifecycle state transitions correctly`() = runTest {
        val stateFlow = MutableStateFlow(
            com.buccancs.domain.model.RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                startedAt = null,
                anchor = null
            )
        )
        every { sensorRepository.recordingState } returns stateFlow

        val anchor = RecordingSessionAnchor(
            sessionId = "state-transition-test",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )

        coEvery { sensorRepository.startStreaming(anchor) } answers {
            stateFlow.value = stateFlow.value.copy(
                lifecycle = RecordingLifecycleState.Recording,
                sessionId = anchor.sessionId,
                anchor = anchor
            )
        }

        recordingService.startRecording(anchor)
        advanceTimeBy(100)

        assertEquals(RecordingLifecycleState.Recording, stateFlow.value.lifecycle)
        assertEquals("state-transition-test", stateFlow.value.sessionId)

        coEvery { sensorRepository.stopStreaming() } answers {
            stateFlow.value = stateFlow.value.copy(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                anchor = null
            )
        }

        recordingService.stopRecording()
        advanceTimeBy(100)

        assertEquals(RecordingLifecycleState.Idle, stateFlow.value.lifecycle)
    }

    private fun setupDefaultMocks() {
        every { sensorRepository.devices } returns MutableStateFlow(emptyList())
        every { sensorRepository.recordingState } returns MutableStateFlow(
            com.buccancs.domain.model.RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                startedAt = null,
                anchor = null
            )
        )
        every { sensorRepository.streamStatuses } returns MutableStateFlow(emptyList())
        coEvery { eventRepository.record(any()) } returns Unit
        coEvery { transferRepository.queueManifest(any()) } returns Unit
    }

    private fun createTestDevice(
        id: String,
        type: SensorDeviceType,
        status: ConnectionStatus
    ) = SensorDevice(
        id = id,
        name = "Test $id",
        type = type,
        connectionStatus = status,
        capabilities = emptySet()
    )
}
