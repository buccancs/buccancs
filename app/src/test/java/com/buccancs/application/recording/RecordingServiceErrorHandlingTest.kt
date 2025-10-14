package com.buccancs.application.recording

import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.util.nowInstant
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class RecordingServiceErrorHandlingTest {
    private lateinit var sensorRepository: SensorRepository
    private lateinit var service: DefaultRecordingService

    @Before
    fun setup() {
        sensorRepository = mockk(relaxed = true)
        service = DefaultRecordingService(sensorRepository)
    }

    @Test
    fun `start recording handles repository failure gracefully`() = runTest {
        val anchor = RecordingSessionAnchor(
            sessionId = "test-session",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )
        coEvery { sensorRepository.startStreaming(any()) } throws IllegalStateException("Repository error")

        assertFailsWith<IllegalStateException> {
            service.startRecording(anchor)
        }
    }

    @Test
    fun `stop recording handles repository failure gracefully`() = runTest {
        coEvery { sensorRepository.stopStreaming() } throws IllegalStateException("Repository error")

        assertFailsWith<IllegalStateException> {
            service.stopRecording()
        }
    }

    @Test
    fun `start recording with null session id still calls repository`() = runTest {
        val anchor = RecordingSessionAnchor(
            sessionId = "",
            referenceTimestamp = nowInstant(),
            sharedClockOffsetMillis = 0
        )
        coEvery { sensorRepository.startStreaming(any()) } returns Unit

        service.startRecording(anchor)

        coVerify { sensorRepository.startStreaming(anchor) }
    }
}
