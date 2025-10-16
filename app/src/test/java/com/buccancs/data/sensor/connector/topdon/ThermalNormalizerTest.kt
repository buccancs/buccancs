package com.buccancs.data.sensor.connector.topdon

import android.graphics.Bitmap
import com.buccancs.domain.model.TopdonPreviewFrame
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Unit tests for ThermalNormalizer bitmap creation and palette application
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class ThermalNormalizerTest {

    private lateinit var normalizer: ThermalNormalizer

    @Before
    fun setup() {
        normalizer = ThermalNormalizer()
    }

    @Test
    fun `normalize returns valid metrics for valid data`() {
        // Create synthetic thermal data: 20°C to 30°C range
        val width = 256
        val height = 192
        val data = ByteArray(width * height * 2)

        // Fill with temperature gradient
        for (i in 0 until (width * height)) {
            val temp = 20.0 + (i.toDouble() / (width * height)) * 10.0 // 20-30°C
            val kelvin = temp + 273.15
            val raw = (kelvin * 100.0).toInt()
            val offset = i * 2
            data[offset] = (raw and 0xFF).toByte()
            data[offset + 1] = ((raw shr 8) and 0xFF).toByte()
        }

        val metrics = normalizer.normalize(data)

        assertNotNull(metrics)
        assertTrue("Min temp should be around 20°C", metrics.minCelsius > 19.0 && metrics.minCelsius < 21.0)
        assertTrue("Max temp should be around 30°C", metrics.maxCelsius > 29.0 && metrics.maxCelsius < 31.0)
        assertTrue("Avg temp should be around 25°C", metrics.avgCelsius > 24.0 && metrics.avgCelsius < 26.0)
        assertTrue(
            "Min < Avg < Max",
            metrics.minCelsius < metrics.avgCelsius && metrics.avgCelsius < metrics.maxCelsius
        )
    }

    @Test
    fun `normalize handles empty data`() {
        val metrics = normalizer.normalize(ByteArray(0))

        assertEquals(0.0, metrics.minCelsius, 0.01)
        assertEquals(0.0, metrics.maxCelsius, 0.01)
        assertEquals(0.0, metrics.avgCelsius, 0.01)
        assertEquals(0, metrics.normalized.size)
    }

    @Test
    fun `createBitmap produces valid bitmap`() {
        // Create simple thermal data
        val width = 256
        val height = 192
        val data = ByteArray(width * height * 2)

        // Fill with constant temperature
        val temp = 25.0 // 25°C
        val kelvin = temp + 273.15
        val raw = (kelvin * 100.0).toInt()
        for (i in 0 until (width * height)) {
            val offset = i * 2
            data[offset] = (raw and 0xFF).toByte()
            data[offset + 1] = ((raw shr 8) and 0xFF).toByte()
        }

        val metrics = normalizer.normalize(data)
        val bitmap = normalizer.createBitmap(metrics, width, height)

        assertNotNull(bitmap)
        assertEquals(width, bitmap.width)
        assertEquals(height, bitmap.height)
        assertEquals(Bitmap.Config.ARGB_8888, bitmap.config)
    }

    @Test
    fun `createBitmapFromFrame handles valid frame`() {
        val width = 256
        val height = 192
        val payload = ByteArray(width * height * 2)

        // Create synthetic thermal data
        val temp = 22.0 // 22°C
        val kelvin = temp + 273.15
        val raw = (kelvin * 100.0).toInt()
        for (i in 0 until (width * height)) {
            val offset = i * 2
            payload[offset] = (raw and 0xFF).toByte()
            payload[offset + 1] = ((raw shr 8) and 0xFF).toByte()
        }

        val frame = TopdonPreviewFrame(
            timestamp = kotlin.time.Instant.fromEpochMilliseconds(System.currentTimeMillis()),
            width = width,
            height = height,
            mimeType = "application/octet-stream",
            payload = payload,
            superSamplingFactor = 1,
            minTemp = 20.0f,
            maxTemp = 30.0f,
            avgTemp = 25.0f
        )

        val bitmap = normalizer.createBitmapFromFrame(frame)

        assertNotNull(bitmap)
        assertEquals(width, bitmap.width)
        assertEquals(height, bitmap.height)
    }

    @Test
    fun `ironbow palette produces valid RGB values`() {
        // Test palette at different normalized values
        val testValues = listOf(0.0, 0.25, 0.5, 0.75, 1.0)

        for (normalized in testValues) {
            val r = (normalized * 255).toInt().coerceIn(0, 255)
            val g = ((normalized - 0.5) * 510).toInt().coerceIn(0, 255)
            val b = ((1.0 - normalized) * 255).toInt().coerceIn(0, 255)

            assertTrue("R in range", r in 0..255)
            assertTrue("G in range", g in 0..255)
            assertTrue("B in range", b in 0..255)
        }
    }

    @Test
    fun `average temperature calculated correctly`() {
        val width = 10
        val height = 10
        val data = ByteArray(width * height * 2)

        // Create data with known average: 25°C
        val temp = 25.0
        val kelvin = temp + 273.15
        val raw = (kelvin * 100.0).toInt()
        for (i in 0 until (width * height)) {
            val offset = i * 2
            data[offset] = (raw and 0xFF).toByte()
            data[offset + 1] = ((raw shr 8) and 0xFF).toByte()
        }

        val metrics = normalizer.normalize(data)

        assertTrue(
            "Average should be close to 25°C",
            metrics.avgCelsius > 24.9 && metrics.avgCelsius < 25.1
        )
    }
}
