package com.buccancs.application.time

import com.buccancs.data.orchestration.DeviceIdentityProvider
import com.buccancs.data.orchestration.GrpcChannelFactory
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.repository.OrchestratorConfigRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DefaultTimeSyncServiceTest {
    private lateinit var scope: TestScope
    private lateinit var configRepository: OrchestratorConfigRepository
    private lateinit var channelFactory: GrpcChannelFactory
    private lateinit var identityProvider: DeviceIdentityProvider
    private lateinit var service: DefaultTimeSyncService

    @Before
    fun setup() {
        scope = TestScope()
        configRepository = mockk(relaxed = true)
        channelFactory = mockk(relaxed = true)
        identityProvider = mockk(relaxed = true)

        every { identityProvider.deviceId() } returns "test-device"
        every { configRepository.config } returns MutableStateFlow(
            OrchestratorConfig(
                host = "localhost",
                port = 50051,
                useTls = false
            )
        )

        service = DefaultTimeSyncService(
            scope = scope,
            configRepository = configRepository,
            channelFactory = channelFactory,
            identityProvider = identityProvider
        )
    }

    @Test
    fun `initial status is unknown quality`() = runTest {
        val status = service.status.value
        
        assertEquals(TimeSyncQuality.UNKNOWN, status.quality)
        assertEquals(0, status.sampleCount)
        assertEquals(Long.MAX_VALUE, status.roundTripMillis)
    }

    @Test
    fun `initial history is empty`() = runTest {
        val history = service.history.value
        
        assertEquals(0, history.size)
    }

    @Test
    fun `status flow is initialized`() = runTest {
        assertNotNull(service.status.value)
    }

    @Test
    fun `service exposes history flow`() = runTest {
        assertNotNull(service.history)
    }
}
