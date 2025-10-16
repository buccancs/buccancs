package com.buccancs.data.sensor.connector.topdon.capture

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MeasurementProcessorTest {

    @Test
    fun `measureSpot returns correct temperature`() {
        val yuvData = createTestFrame(
            4, 4, listOf(
                100, 110, 120, 130,
                140, 150, 160, 170,
                180, 190, 200, 210,
                220, 230, 240, 250
            )
        )

        val spot = MeasurementProcessor.measureSpot(yuvData, 4, 4, 2, 2)

        assertEquals(2, spot.x)
        assertEquals(2, spot.y)
        assertTrue(spot.temperature > 0f)
    }

    @Test
    fun `measureArea calculates region statistics`() {
        val yuvData = createTestFrame(4, 4, List(16) { 128 })

        val area = MeasurementProcessor.measureArea(yuvData, 4, 4, 0, 0, 2, 2)

        assertEquals(0, area.x1)
        assertEquals(0, area.y1)
        assertEquals(2, area.x2)
        assertEquals(2, area.y2)
        assertEquals(4, area.area)
        assertTrue(area.minTemp <= area.avgTemp)
        assertTrue(area.avgTemp <= area.maxTemp)
    }

    @Test
    fun `measureArea handles inverted coordinates`() {
        val yuvData = createTestFrame(4, 4, List(16) { 100 })

        // Pass coordinates in wrong order
        val area = MeasurementProcessor.measureArea(yuvData, 4, 4, 3, 3, 1, 1)

        // Should normalize to correct order
        assertEquals(1, area.x1)
        assertEquals(1, area.y1)
        assertEquals(3, area.x2)
        assertEquals(3, area.y2)
    }

    @Test
    fun `measureLine calculates temperature profile`() {
        val yuvData = createTestFrame(
            5, 5, listOf(
                100, 110, 120, 130, 140,
                110, 120, 130, 140, 150,
                120, 130, 140, 150, 160,
                130, 140, 150, 160, 170,
                140, 150, 160, 170, 180
            )
        )

        val line = MeasurementProcessor.measureLine(yuvData, 5, 5, 0, 0, 4, 4)

        assertEquals(0, line.x1)
        assertEquals(0, line.y1)
        assertEquals(4, line.x2)
        assertEquals(4, line.y2)
        assertTrue(line.profile.isNotEmpty())
        assertTrue(line.minTemp <= line.maxTemp)
    }

    @Test
    fun `measureLine horizontal produces correct profile`() {
        val yuvData = createTestFrame(5, 1, listOf(100, 110, 120, 130, 140))

        val line = MeasurementProcessor.measureLine(yuvData, 5, 1, 0, 0, 4, 0)

        assertEquals(5, line.profile.size)
        assertTrue(line.profile[0] < line.profile[4]) // Temperature increases
    }

    @Test
    fun `measureLine vertical produces correct profile`() {
        val yuvData = createTestFrame(1, 5, listOf(100, 110, 120, 130, 140))

        val line = MeasurementProcessor.measureLine(yuvData, 1, 5, 0, 0, 0, 4)

        assertEquals(5, line.profile.size)
    }

    @Test
    fun `measureMaxMin finds hottest and coldest points`() {
        val yuvData = createTestFrame(
            4, 4, listOf(
                150, 160, 170, 180,
                140, 255, 190, 200,  // 255 at (1,1)
                130, 210, 220, 230,
                0, 240, 250, 240      // 0 at (0,3)
            )
        )

        val maxMin = MeasurementProcessor.measureMaxMin(yuvData, 4, 4)

        assertEquals(1, maxMin.maxX)
        assertEquals(1, maxMin.maxY)
        assertEquals(0, maxMin.minX)
        assertEquals(3, maxMin.minY)
        assertTrue(maxMin.maxTemp > maxMin.minTemp)
    }

    @Test
    fun `measureMaxMin handles empty data`() {
        val maxMin = MeasurementProcessor.measureMaxMin(ByteArray(0), 0, 0)

        assertEquals(0, maxMin.maxX)
        assertEquals(0, maxMin.maxY)
        assertEquals(0, maxMin.minX)
        assertEquals(0, maxMin.minY)
    }

    @Test
    fun `measureMaxMin with uniform temperature`() {
        val yuvData = createTestFrame(3, 3, List(9) { 128 })

        val maxMin = MeasurementProcessor.measureMaxMin(yuvData, 3, 3)

        // Min and max should be same
        assertEquals(maxMin.minTemp, maxMin.maxTemp, 0.01f)
    }

    @Test
    fun `measureArea with single pixel`() {
        val yuvData = createTestFrame(2, 2, listOf(100, 150, 200, 250))

        val area = MeasurementProcessor.measureArea(yuvData, 2, 2, 1, 1, 1, 1)

        assertEquals(0, area.area) // Single pixel has no area
        assertEquals(area.minTemp, area.maxTemp, 0.01f)
    }

    // Helper to create test YUV422 frame from Y values
    private fun createTestFrame(width: Int, height: Int, yValues: List<Int>): ByteArray {
        val data = ByteArray(width * height * 2)
        yValues.forEachIndexed { index, yValue ->
            data[index * 2] = yValue.toByte()
            data[index * 2 + 1] = 0 // U or V (not used in temperature)
        }
        return data
    }
}
