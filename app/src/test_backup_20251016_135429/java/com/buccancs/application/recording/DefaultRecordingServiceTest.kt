package com.buccancs.application.recording

import com.buccancs.application.performance.PerformanceMetricsAnalyzer
import com.buccancs.application.performance.PerformanceMetricsRecorder
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.recording.manifest.ManifestWriter
import com.buccancs.domain.model.*
import com.buccancs.domain.repository.*
import com.buccancs.util.nowInstant
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DefaultRecordingServiceTest {
    private lateinit var appScope: TestScope
    private lateinit var sensorRepository: SensorRepository
    private lateinit var timeSyncService: TimeSyncService
    private lateinit var transferRepository: SessionTransferRepository
    private lateinit var deviceEventRepository: DeviceEventRepository
    private lateinit var bookmarkRepository: BookmarkRepository
    private lateinit var manifestWriter: ManifestWriter
    private lateinit var performanceMetricsRecorder: PerformanceMetricsRecorder
    private lateinit var performanceMetricsAnalyzer: PerformanceMetricsAnalyzer
    private lateinit var service: DefaultRecordingService

    @Before
    fun setup() {
        appScope = TestScope()
        sensorRepository = mockk(relaxed = true)
        timeSyncService = mockk(relaxed = true)
        transferRepository = mockk(relaxed = true)
        deviceEventRepository = mockk(relaxed = true)
        bookmarkRepository = mockk(relaxed = true)
        manifestWriter = mockk(relaxed = true)
        performanceMetricsRecorder = mockk(relaxed = true)
        performanceMetricsAnalyzer = mockk(relaxed = true)

        // Setup mocks
        every { sensorRepository.recordingState } returns MutableStateFlow(
            RecordingState(
                lifecycle = RecordingLifecycleState.Idle,
                anchor = null,
                updatedAt = nowInstant()
            )
        )
        every { sensorRepository.devices } returns MutableStateFlow(emptyList())
        every { sensorRepository.simulationEnabled } returns MutableStateFlow(false)
        
        every { timeSyncService.status } returns MutableStateFlow(
            TimeSyncStatus(
                offsetMillis = 0,
                roundTripMillis = 10,
                lastSync = nowInstant(),
                driftEstimateMillisPerMinute = 0.0,
                filteredRoundTripMillis = 10.0,
                quality = TimeSyncQuality.GOOD,
                sampleCount = 5,
                regressionSlopeMillisPerMinute = 0.0
            )
        )
        
        coEvery { timeSyncService.forceSync() } returns TimeSyncStatus(
            offsetMillis = 0,
            roundTripMillis = 10,
            lastSync = nowInstant(),
            driftEstimateMillisPerMinute = 0.0,
            filteredRoundTripMillis = 10.0,
            quality = TimeSyncQuality.GOOD,
            sampleCount = 5,
            regressionSlopeMillisPerMinute = 0.0
        )

        service = DefaultRecordingService(
            appScope = appScope,
            sensorRepository = sensorRepository,
            timeSyncService = timeSyncService,
            transferRepository = transferRepository,
            deviceEventRepository = deviceEventRepository,
            bookmarkRepository = bookmarkRepository,
            manifestWriter = manifestWriter,
            performanceMetricsRecorder = performanceMetricsRecorder,
            performanceMetricsAnalyzer = performanceMetricsAnalyzer
        )
    }

    @Test
    fun `starting recording initializes session`() = runTest {
        coEvery { sensorRepository.startStreaming(any()) } returns Unit
        coEvery { manifestWriter.beginSession(any(), any(), any()) } returns Unit
        coEvery { bookmarkRepository.clear() } returns Unit
        coEvery { performanceMetricsRecorder.start(any()) } returns Unit

        val state = service.startOrResume("test-session")

        coVerify { sensorRepository.startStreaming(any()) }
        coVerify { manifestWriter.beginSession(any(), any(), any()) }
        coVerify { performanceMetricsRecorder.start("test-session") }
    }

    @Test
    fun `stopping recording finalizes session`() = runTest {
        val anchor = RecordingSessionAnchor(
            sessionId = "test-session",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )
        
        coEvery { sensorRepository.stopStreaming() } returns anchor
        coEvery { sensorRepository.collectSessionArtifacts(any()) } returns emptyList()
        coEvery { performanceMetricsRecorder.stop() } returns Unit
        coEvery { performanceMetricsAnalyzer.summarise(any()) } returns mockk()
        coEvery { manifestWriter.finaliseSession(any(), any(), any(), any()) } returns Unit

        service.stop()

        coVerify { sensorRepository.stopStreaming() }
        coVerify { performanceMetricsRecorder.stop() }
    }

    @Test
    fun `recording state is accessible from sensor repository`() = runTest {
        val state = sensorRepository.recordingState.value

        assertEquals(RecordingLifecycleState.Idle, state.lifecycle)
    }
}
