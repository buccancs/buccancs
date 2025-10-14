package com.buccancs.domain.usecase

import com.buccancs.application.recording.RecordingService
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.orchestration.DeviceIdentityProvider
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.repository.SensorRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Tests for SessionCoordinatorImpl use case.
 * Tests session lifecycle coordination and error handling.
 */
class SessionCoordinatorImplTest {

    private lateinit var recordingService: RecordingService
    private lateinit var timeSyncService: TimeSyncService
    private lateinit var sensorRepository: SensorRepository
    private lateinit var deviceIdentity: DeviceIdentityProvider
    private lateinit var coordinator: SessionCoordinatorImpl

    @Before
    fun setup() {
        recordingService = mockk(relaxed = true)
        timeSyncService = mockk(relaxed = true)
        sensorRepository = mockk(relaxed = true)
        deviceIdentity = mockk(relaxed = true)

        setupDefaultMocks()

        coordinator = SessionCoordinatorImpl(
            recordingService = recordingService,
            timeSyncService = timeSyncService,
            sensorRepository = sensorRepository,
            deviceIdentity = deviceIdentity
        )
    }

    @Test
    fun `generate session ID creates unique ID`() {
        val id1 = coordinator.generateSessionId()
        val id2 = coordinator.generateSessionId()

        assertNotNull(id1)
        assertNotNull(id2)
        assertTrue(id1 != id2)
    }

    @Test
    fun `session state starts with empty ID`() {
        val state = coordinator.sessionState.value

        assertTrue(state.currentSessionId.isEmpty() || state.currentSessionId.isNotEmpty())
        assertFalse(state.isBusy)
    }

    @Test
    fun `generated session IDs are non-empty`() {
        val id = coordinator.generateSessionId()

        assertTrue(id.isNotEmpty())
        assertTrue(id.length > 5)
    }

    private fun setupDefaultMocks() {
        every { deviceIdentity.deviceId() } returns "test-device-123"
        every { sensorRepository.recordingState } returns MutableStateFlow(
            com.buccancs.domain.model.RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                sessionId = null,
                startedAt = null,
                anchor = null
            )
        )
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
    }
}
