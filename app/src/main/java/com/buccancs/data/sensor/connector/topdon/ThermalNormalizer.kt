package com.buccancs.data.sensor.connector.topdon

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.min

@Singleton
class ThermalNormalizer @Inject constructor() {
    data class Metrics(
        val minCelsius: Double,
        val maxCelsius: Double,
        val normalized: ByteArray
    )

    fun normalize(rawFrame: ByteArray): Metrics {
        if (rawFrame.isEmpty()) {
            return Metrics(
                minCelsius = 0.0,
                maxCelsius = 0.0,
                normalized = ByteArray(0)
            )
        }
        val pixelCount = rawFrame.size / BYTES_PER_PIXEL
        var minValue = Double.POSITIVE_INFINITY
        var maxValue = Double.NEGATIVE_INFINITY
        val celsius = DoubleArray(pixelCount)
        var index = 0
        while (index < rawFrame.size) {
            val raw = (rawFrame[index].toInt() and 0xFF) or ((rawFrame[index + 1].toInt() and 0xFF) shl 8)
            val temperature = convertRawToCelsius(raw)
            val pixelIndex = index / BYTES_PER_PIXEL
            celsius[pixelIndex] = temperature
            minValue = min(minValue, temperature)
            maxValue = max(maxValue, temperature)
            index += BYTES_PER_PIXEL
        }
        val range = (maxValue - minValue).takeIf { it > 0.0001 } ?: 1.0
        val normalized = ByteArray(pixelCount)
        for (i in 0 until pixelCount) {
            val scaled = ((celsius[i] - minValue) / range * 255.0).coerceIn(0.0, 255.0)
            normalized[i] = scaled.toInt().toByte()
        }
        return Metrics(
            minCelsius = minValue,
            maxCelsius = maxValue,
            normalized = normalized
        )
    }

    private fun convertRawToCelsius(raw: Int): Double {
        return raw / 100.0 - 273.15
    }

    private companion object {
        private const val BYTES_PER_PIXEL = 2
    }
}
