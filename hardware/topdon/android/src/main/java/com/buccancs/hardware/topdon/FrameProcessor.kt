package com.buccancs.hardware.topdon

import kotlin.math.max
import kotlin.math.min

internal class FrameProcessor(
    private val width: Int = FRAME_WIDTH,
    private val height: Int = FRAME_HEIGHT,
    private val bytesPerPixel: Int = BYTES_PER_PIXEL
) {

    data class FrameData(
        val payload: ByteArray,
        val metrics: TopdonTemperatureMetrics
    )

    fun process(rawFrame: ByteArray): FrameData? {
        if (rawFrame.isEmpty()) return null
        val payload =
            if (rawFrame.size == expectedSize()) rawFrame.copyOf() else {
                rawFrame.copyOf(min(rawFrame.size, expectedSize()))
            }
        val metrics =
            computeMetrics(payload)
        return FrameData(
            payload = payload,
            metrics = metrics
        )
    }

    fun expectedSize(): Int =
        width * height * bytesPerPixel

    private fun computeMetrics(
        payload: ByteArray
    ): TopdonTemperatureMetrics {
        var minValue =
            Double.POSITIVE_INFINITY
        var maxValue =
            Double.NEGATIVE_INFINITY
        var sum =
            0.0
        var count =
            0
        var index =
            0
        while (index + 1 < payload.size) {
            val raw =
                (payload[index].toInt() and 0xFF) or ((payload[index + 1].toInt() and 0xFF) shl 8)
            val celsius =
                rawToCelsius(
                    raw
                )
            minValue =
                min(
                    minValue,
                    celsius
                )
            maxValue =
                max(
                    maxValue,
                    celsius
                )
            sum += celsius
            count += 1
            index += bytesPerPixel
        }
        val avgValue =
            if (count > 0) sum / count else Double.NaN
        return TopdonTemperatureMetrics(
            minCelsius = minValue,
            maxCelsius = maxValue,
            avgCelsius = avgValue
        )
    }

    private fun rawToCelsius(
        raw: Int
    ): Double =
        raw / 100.0 - KELVIN_OFFSET

    companion object {
        const val FRAME_WIDTH =
            256
        const val FRAME_HEIGHT =
            192
        const val BYTES_PER_PIXEL =
            2
        private const val KELVIN_OFFSET =
            273.15
    }
}
