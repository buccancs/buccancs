package com.buccancs.data.sensor.connector.topdon.capture

import kotlin.math.max
import kotlin.math.min

/**
 * Extracts temperature data from Topdon TC001 thermal frames.
 * Handles both raw 16-bit payloads (preferred) and legacy YUV422 buffers.
 */
internal object TemperatureExtractor {

    data class TemperatureStats(
        val minTemp: Float,
        val maxTemp: Float,
        val avgTemp: Float
    )

    fun extractTemperatureStats(
        frameData: ByteArray,
        width: Int,
        height: Int
    ): TemperatureStats {
        if (frameData.isEmpty() || width <= 0 || height <= 0) {
            return TemperatureStats(0f, 0f, 0f)
        }
        val bytesPerPixel =
            resolveBytesPerPixel(frameData, width, height)
        val pixelCount = width * height
        var minValue =
            Double.POSITIVE_INFINITY
        var maxValue =
            Double.NEGATIVE_INFINITY
        var sumValue =
            0.0

        for (index in 0 until pixelCount) {
            val offset = index * bytesPerPixel
            if (offset + bytesPerPixel > frameData.size) break
            val temperature =
                readTemperature(frameData, offset, bytesPerPixel)
            minValue = min(minValue, temperature)
            maxValue = max(maxValue, temperature)
            sumValue += temperature
        }

        val avgValue =
            if (pixelCount > 0) sumValue / pixelCount else 0.0
        return TemperatureStats(
            minTemp = minValue.toFloat(),
            maxTemp = maxValue.toFloat(),
            avgTemp = avgValue.toFloat()
        )
    }

    fun extractTemperatureAt(
        frameData: ByteArray,
        width: Int,
        x: Int,
        y: Int
    ): Float {
        if (x !in 0 until width) return 0f
        val bytesPerPixel =
            resolveBytesPerPixel(frameData, width)
        val offset = (y * width + x) * bytesPerPixel
        if (offset < 0 || offset + bytesPerPixel > frameData.size) return 0f
        return readTemperature(frameData, offset, bytesPerPixel).toFloat()
    }

    fun extractAreaTemperature(
        frameData: ByteArray,
        width: Int,
        x: Int,
        y: Int,
        w: Int,
        h: Int
    ): TemperatureStats {
        val bytesPerPixel =
            resolveBytesPerPixel(frameData, width)
        var minValue =
            Double.POSITIVE_INFINITY
        var maxValue =
            Double.NEGATIVE_INFINITY
        var sumValue =
            0.0
        var count =
            0

        for (row in y until (y + h)) {
            for (col in x until (x + w)) {
                if (col < 0 || row < 0 || col >= width) continue
                val offset = (row * width + col) * bytesPerPixel
                if (offset + bytesPerPixel > frameData.size) continue
                val temperature =
                    readTemperature(frameData, offset, bytesPerPixel)
                minValue = min(minValue, temperature)
                maxValue = max(maxValue, temperature)
                sumValue += temperature
                count += 1
            }
        }

        if (count == 0) {
            return TemperatureStats(0f, 0f, 0f)
        }

        val avgValue = sumValue / count
        return TemperatureStats(
            minTemp = minValue.toFloat(),
            maxTemp = maxValue.toFloat(),
            avgTemp = avgValue.toFloat()
        )
    }

    fun extractMaxMinLocations(
        frameData: ByteArray,
        width: Int,
        height: Int
    ): MaxMinResult {
        val bytesPerPixel =
            resolveBytesPerPixel(frameData, width, height)
        var minTemp =
            Double.POSITIVE_INFINITY
        var maxTemp =
            Double.NEGATIVE_INFINITY
        var minIndex = 0
        var maxIndex = 0
        val pixelCount = width * height

        for (index in 0 until pixelCount) {
            val offset = index * bytesPerPixel
            if (offset + bytesPerPixel > frameData.size) break
            val temperature =
                readTemperature(frameData, offset, bytesPerPixel)
            if (temperature < minTemp) {
                minTemp = temperature
                minIndex = index
            }
            if (temperature > maxTemp) {
                maxTemp = temperature
                maxIndex = index
            }
        }

        val maxX = maxIndex % width
        val maxY = maxIndex / width
        val minX = minIndex % width
        val minY = minIndex / width
        return MaxMinResult(
            maxX = maxX,
            maxY = maxY,
            maxTemp = maxTemp.toFloat(),
            minX = minX,
            minY = minY,
            minTemp = minTemp.toFloat()
        )
    }

    private fun resolveBytesPerPixel(
        data: ByteArray,
        width: Int,
        height: Int = data.size
    ): Int {
        val expectedRaw = width * height * RAW_BYTES_PER_PIXEL
        return if (data.size >= expectedRaw) RAW_BYTES_PER_PIXEL else 1
    }

    private fun readTemperature(
        data: ByteArray,
        offset: Int,
        bytesPerPixel: Int
    ): Double {
        return if (bytesPerPixel == RAW_BYTES_PER_PIXEL) {
            val raw =
                (data[offset].toInt() and 0xFF) or ((data[offset + 1].toInt() and 0xFF) shl 8)
            rawToTemperature(raw)
        } else {
            val yValue = data[offset].toInt() and 0xFF
            yValueToTemperature(yValue)
        }
    }

    private fun rawToTemperature(
        raw: Int
    ): Double =
        raw / 100.0 - 273.15

    private fun yValueToTemperature(
        yValue: Int
    ): Double {
        val minTemp = -20.0
        val maxTemp = 550.0
        val range = maxTemp - minTemp
        return minTemp + (yValue / 255.0) * range
    }

    private const val RAW_BYTES_PER_PIXEL = 2
}

data class MaxMinResult(
    val maxX: Int,
    val maxY: Int,
    val maxTemp: Float,
    val minX: Int,
    val minY: Int,
    val minTemp: Float
)
