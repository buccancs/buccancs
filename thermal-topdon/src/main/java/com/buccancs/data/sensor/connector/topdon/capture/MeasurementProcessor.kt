package com.buccancs.data.sensor.connector.topdon.capture

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Processes thermal measurements for different modes
 * Implements spot, area, line, and max/min measurement calculations
 */
internal object MeasurementProcessor {

    /**
     * Spot measurement at single point
     */
    fun measureSpot(
        yuvData: ByteArray,
        width: Int,
        height: Int,
        x: Int,
        y: Int
    ): SpotMeasurement {
        val temp =
            TemperatureExtractor.extractTemperatureAt(
                yuvData,
                width,
                x,
                y
            )
        return SpotMeasurement(
            x,
            y,
            temp
        )
    }

    /**
     * Area measurement (rectangular region)
     */
    fun measureArea(
        yuvData: ByteArray,
        width: Int,
        height: Int,
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int
    ): AreaMeasurement {
        val left =
            min(
                x1,
                x2
            )
        val top =
            min(
                y1,
                y2
            )
        val right =
            max(
                x1,
                x2
            )
        val bottom =
            max(
                y1,
                y2
            )
        val w =
            right - left
        val h =
            bottom - top

        val stats =
            TemperatureExtractor.extractAreaTemperature(
                yuvData,
                width,
                left,
                top,
                w,
                h
            )

        return AreaMeasurement(
            x1 = left,
            y1 = top,
            x2 = right,
            y2 = bottom,
            minTemp = stats.minTemp,
            maxTemp = stats.maxTemp,
            avgTemp = stats.avgTemp,
            area = w * h
        )
    }

    /**
     * Line measurement between two points
     */
    fun measureLine(
        yuvData: ByteArray,
        width: Int,
        height: Int,
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int
    ): LineMeasurement {
        val points =
            bresenhamLine(
                x1,
                y1,
                x2,
                y2
            )
        val temperatures =
            points.mapNotNull { (x, y) ->
                if (x in 0 until width && y in 0 until height) {
                    TemperatureExtractor.extractTemperatureAt(
                        yuvData,
                        width,
                        x,
                        y
                    )
                } else {
                    null
                }
            }

        if (temperatures.isEmpty()) {
            return LineMeasurement(
                x1,
                y1,
                x2,
                y2,
                0f,
                0f,
                0f,
                emptyList()
            )
        }

        val minTemp =
            temperatures.minOrNull()
                ?: 0f
        val maxTemp =
            temperatures.maxOrNull()
                ?: 0f
        val avgTemp =
            temperatures.average()
                .toFloat()

        return LineMeasurement(
            x1 = x1,
            y1 = y1,
            x2 = x2,
            y2 = y2,
            minTemp = minTemp,
            maxTemp = maxTemp,
            avgTemp = avgTemp,
            profile = temperatures
        )
    }

    /**
     * Find max/min temperature locations in frame
     */
    fun measureMaxMin(
        yuvData: ByteArray,
        width: Int,
        height: Int
    ): MaxMinMeasurement {
        if (yuvData.isEmpty()) {
            return MaxMinMeasurement(
                maxX = 0,
                maxY = 0,
                maxTemp = 0f,
                minX = 0,
                minY = 0,
                minTemp = 0f
            )
        }

        var minValue =
            255
        var maxValue =
            0
        var minX =
            0
        var minY =
            0
        var maxX =
            0
        var maxY =
            0

        val pixelCount =
            width * height
        for (i in 0 until min(
            pixelCount * 2,
            yuvData.size
        ) step 2) {
            val yValue =
                yuvData[i].toInt() and 0xFF
            val pixelIndex =
                i / 2
            val x =
                pixelIndex % width
            val y =
                pixelIndex / width

            if (yValue < minValue) {
                minValue =
                    yValue
                minX =
                    x
                minY =
                    y
            }
            if (yValue > maxValue) {
                maxValue =
                    yValue
                maxX =
                    x
                maxY =
                    y
            }
        }

        return MaxMinMeasurement(
            maxX = maxX,
            maxY = maxY,
            maxTemp = yValueToTemperature(
                maxValue
            ),
            minX = minX,
            minY = minY,
            minTemp = yValueToTemperature(
                minValue
            )
        )
    }

    /**
     * Bresenham's line algorithm for pixel sampling
     */
    private fun bresenhamLine(
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int
    ): List<Pair<Int, Int>> {
        val points =
            mutableListOf<Pair<Int, Int>>()
        var x =
            x1
        var y =
            y1
        val dx =
            abs(x2 - x1)
        val dy =
            abs(y2 - y1)
        val sx =
            if (x1 < x2) 1 else -1
        val sy =
            if (y1 < y2) 1 else -1
        var err =
            dx - dy

        while (true) {
            points.add(
                Pair(
                    x,
                    y
                )
            )

            if (x == x2 && y == y2) break

            val e2 =
                2 * err
            if (e2 > -dy) {
                err -= dy
                x += sx
            }
            if (e2 < dx) {
                err += dx
                y += sy
            }
        }

        return points
    }

    private fun yValueToTemperature(
        yValue: Int
    ): Float {
        val minTemp =
            -20f
        val maxTemp =
            550f
        val range =
            maxTemp - minTemp
        return minTemp + (yValue / 255f) * range
    }
}

/**
 * Spot measurement result
 */
data class SpotMeasurement(
    val x: Int,
    val y: Int,
    val temperature: Float
)

/**
 * Area measurement result
 */
data class AreaMeasurement(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
    val minTemp: Float,
    val maxTemp: Float,
    val avgTemp: Float,
    val area: Int
)

/**
 * Line measurement result
 */
data class LineMeasurement(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
    val minTemp: Float,
    val maxTemp: Float,
    val avgTemp: Float,
    val profile: List<Float>
)

/**
 * Max/Min measurement result
 */
data class MaxMinMeasurement(
    val maxX: Int,
    val maxY: Int,
    val maxTemp: Float,
    val minX: Int,
    val minY: Int,
    val minTemp: Float
)
