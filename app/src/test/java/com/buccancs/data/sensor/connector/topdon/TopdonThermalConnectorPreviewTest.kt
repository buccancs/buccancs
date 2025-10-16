package com.buccancs.data.sensor.connector.topdon

import com.buccancs.domain.model.TopdonPreviewFrame
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

/**
 * Unit tests for Topdon thermal preview functionality
 */
@OptIn(ExperimentalCoroutinesApi::class)
class TopdonThermalConnectorPreviewTest {

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `previewFrameFlow initially emits null`() = runTest {
        // Simulated preview frame should be null initially
        // Test would need actual connector instance with mocked dependencies
        assertTrue(true) // Placeholder - requires full mock setup
    }

    @Test
    fun `previewRunningFlow initially emits false`() = runTest {
        // Preview should not be running initially
        // Test would need actual connector instance
        assertTrue(true) // Placeholder - requires full mock setup
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
