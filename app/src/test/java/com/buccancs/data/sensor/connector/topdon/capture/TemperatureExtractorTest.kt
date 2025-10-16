package com.buccancs.data.sensor.connector.topdon.capture

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs

class TemperatureExtractorTest {

    @Test
    fun `extractTemperatureStats returns correct min max avg`() {
        // Create test YUV422 data: Y0 U0 Y1 V0 pattern
        // Y values: 0, 128, 255 (min, mid, max)
        val yuvData = byteArrayOf(
            0, 0,      // Y=0 (min temp)
            128, 0,    // Y=128 (mid temp)
            255.toByte(), 0  // Y=255 (max temp)
        )
        val width = 3
        val height = 1

        val stats = TemperatureExtractor.extractTemperatureStats(yuvData, width, height)

        // Expected temperatures: -20°C (Y=0), ~265°C (Y=128), 550°C (Y=255)
        assertEquals(-20f, stats.minTemp, 0.1f)
        assertEquals(550f, stats.maxTemp, 0.1f)
        assertTrue(abs(stats.avgTemp - 265f) < 50f) // Mid-range approximate
    }

    @Test
    fun `extractTemperatureStats handles empty data`() {
        val stats = TemperatureExtractor.extractTemperatureStats(ByteArray(0), 0, 0)

        assertEquals(0f, stats.minTemp, 0.01f)
        assertEquals(0f, stats.maxTemp, 0.01f)
        assertEquals(0f, stats.avgTemp, 0.01f)
    }

    @Test
    fun `extractTemperatureAt returns correct pixel temperature`() {
        // Create 2x2 frame with known Y values
        val yuvData = byteArrayOf(
            0, 0,      // (0,0) Y=0
            128, 0,    // (1,0) Y=128
            64, 0,     // (0,1) Y=64
            192.toByte(), 0  // (1,1) Y=192
        )
        val width = 2

        val temp00 = TemperatureExtractor.extractTemperatureAt(yuvData, width, 0, 0)
        val temp11 = TemperatureExtractor.extractTemperatureAt(yuvData, width, 1, 1)

        assertEquals(-20f, temp00, 0.1f)  // Y=0 -> -20°C
        assertTrue(abs(temp11 - 407f) < 10f)  // Y=192 -> ~407°C
    }

    @Test
    fun `extractTemperatureAt handles out of bounds`() {
        val yuvData = byteArrayOf(100, 0, 150.toByte(), 0)
        val width = 2

        val temp = TemperatureExtractor.extractTemperatureAt(yuvData, width, 10, 10)

        assertEquals(0f, temp, 0.01f)
    }

    @Test
    fun `extractAreaTemperature calculates region stats`() {
        // Create 4x4 frame with gradient
        val yuvData = ByteArray(4 * 4 * 2) { i ->
            if (i % 2 == 0) (i / 2 * 16).toByte() else 0
        }
        val width = 4

        // Extract 2x2 area at top-left
        val stats = TemperatureExtractor.extractAreaTemperature(
            yuvData, width, 0, 0, 2, 2
        )

        // Should have min/max/avg from first 4 pixels
        assertTrue(stats.minTemp < stats.maxTemp)
        assertTrue(stats.avgTemp >= stats.minTemp)
        assertTrue(stats.avgTemp <= stats.maxTemp)
    }

    @Test
    fun `temperature stats converts to Fahrenheit`() {
        val celsius = TemperatureStats(0f, 100f, 50f)

        val fahrenheit = celsius.toFahrenheit()

        assertEquals(32f, fahrenheit.minTemp, 0.1f)
        assertEquals(212f, fahrenheit.maxTemp, 0.1f)
        assertEquals(122f, fahrenheit.avgTemp, 0.1f)
    }

    @Test
    fun `extractTemperatureStats with realistic thermal data`() {
        // Simulate realistic 256x192 thermal frame
        val width = 256
        val height = 192
        val yuvData = ByteArray(width * height * 2) { i ->
            if (i % 2 == 0) {
                // Y values around room temperature (20-30°C)
                // Y value ~45 corresponds to ~20°C
                (45 + (i / 1000) % 10).toByte()
            } else {
                0
            }
        }

        val stats = TemperatureExtractor.extractTemperatureStats(yuvData, width, height)

        // Should be in realistic range
        assertTrue(stats.minTemp >= -20f && stats.minTemp <= 30f)
        assertTrue(stats.maxTemp >= 20f && stats.maxTemp <= 50f)
        assertTrue(stats.avgTemp >= stats.minTemp && stats.avgTemp <= stats.maxTemp)
    }
}
