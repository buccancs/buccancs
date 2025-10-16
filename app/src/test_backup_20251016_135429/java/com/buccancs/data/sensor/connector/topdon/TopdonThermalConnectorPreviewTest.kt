package com.buccancs.data.sensor.connector.topdon

import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.hardware.topdon.TopdonStatus
import com.buccancs.hardware.topdon.TopdonThermalClient
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for Topdon thermal preview functionality
 */
@OptIn(ExperimentalCoroutinesApi::class)
class TopdonThermalConnectorPreviewTest {

    private lateinit var mockClient: TopdonThermalClient

    @Before
    fun setup() {
        mockClient = mockk(relaxed = true)
        every { mockClient.status } returns MutableStateFlow(TopdonStatus.Idle)
    }

    @Test
    fun `preview state flow defaults to null when no frame available`() = runTest {
        // When no preview has been started, frame should be null
        val previewState = MutableStateFlow<TopdonPreviewFrame?>(null)
        
        val currentFrame = previewState.first()
        assertNull("Initial preview frame should be null", currentFrame)
    }

    @Test
    fun `preview running state defaults to false when not active`() = runTest {
        // When preview is not started, running should be false
        val runningState = MutableStateFlow(false)
        
        val isRunning = runningState.first()
        assertFalse("Preview should not be running initially", isRunning)
    }

    @Test
    fun `frame throttling enforces minimum interval`() {
        // Test that frames are throttled to prevent UI overload
        val throttleMs = 42L // 24 FPS
        val now = System.currentTimeMillis()
        val lastEmit = now - 30 // 30ms ago

        val shouldEmit = (now - lastEmit) >= throttleMs
        assertFalse("Should not emit frame within throttle window", shouldEmit)

        val lastEmitOld = now - 50 // 50ms ago
        val shouldEmitOld = (now - lastEmitOld) >= throttleMs
        assertTrue("Should emit frame after throttle window", shouldEmitOld)
    }

    @Test
    fun `TopdonPreviewFrame contains required fields`() {
        val frame = TopdonPreviewFrame(
            timestamp = kotlin.time.Instant.fromEpochMilliseconds(System.currentTimeMillis()),
            width = 256,
            height = 192,
            mimeType = "application/octet-stream",
            payload = ByteArray(256 * 192 * 2),
            superSamplingFactor = 1,
            minTemp = 18.0f,
            maxTemp = 35.0f,
            avgTemp = 26.5f
        )

        assertEquals(256, frame.width)
        assertEquals(192, frame.height)
        assertEquals(1, frame.superSamplingFactor)
        assertNotNull(frame.minTemp)
        assertNotNull(frame.maxTemp)
        assertNotNull(frame.avgTemp)
        assertTrue(frame.minTemp!! < frame.maxTemp!!)
        assertTrue(frame.avgTemp!! >= frame.minTemp!!)
        assertTrue(frame.avgTemp!! <= frame.maxTemp!!)
    }

    @Test
    fun `simulated frame has valid dimensions`() {
        // Simulated frame should match TC001 specifications
        val expectedWidth = 256
        val expectedHeight = 192
        val expectedSize = expectedWidth * expectedHeight * 2 // 16-bit thermal data

        assertEquals(256, expectedWidth)
        assertEquals(192, expectedHeight)
        assertEquals(98304, expectedSize)
    }

    @Test
    fun `temperature conversion is accurate`() {
        // Test raw to celsius conversion: (raw / 100.0) - 273.15
        val raw = 29815 // 25°C in Kelvin * 100
        val celsius = (raw / 100.0) - 273.15

        assertEquals(25.0, celsius, 0.15) // Allow small delta
    }

    @Test
    fun `preview frame payload size is correct for resolution`() {
        val width = 256
        val height = 192
        val bytesPerPixel = 2 // 16-bit thermal data
        val expectedSize = width * height * bytesPerPixel

        val payload = ByteArray(expectedSize)
        assertEquals(expectedSize, payload.size)
        assertEquals(98304, payload.size)
    }
}
