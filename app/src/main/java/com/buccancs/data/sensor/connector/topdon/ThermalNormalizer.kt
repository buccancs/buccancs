package com.buccancs.data.sensor.connector.topdon

import android.graphics.Bitmap
import android.graphics.Color
import com.buccancs.domain.model.TopdonPreviewFrame
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.min

@Singleton
class ThermalNormalizer @Inject constructor() {
    data class Metrics(
        val minCelsius: Double,
        val maxCelsius: Double,
        val avgCelsius: Double,
        val normalized: ByteArray
    )

    fun normalize(rawFrame: ByteArray): Metrics {
        if (rawFrame.isEmpty()) {
            return Metrics(
                minCelsius = 0.0,
                maxCelsius = 0.0,
                avgCelsius = 0.0,
                normalized = ByteArray(0)
            )
        }
        val pixelCount = rawFrame.size / BYTES_PER_PIXEL
        var minValue = Double.POSITIVE_INFINITY
        var maxValue = Double.NEGATIVE_INFINITY
        var sumValue = 0.0
        val celsius = DoubleArray(pixelCount)
        var index = 0
        while (index < rawFrame.size) {
            val raw =
                (rawFrame[index].toInt() and 0xFF) or ((rawFrame[index + 1].toInt() and 0xFF) shl 8)
            val temperature = convertRawToCelsius(raw)
            val pixelIndex = index / BYTES_PER_PIXEL
            celsius[pixelIndex] = temperature
            minValue = min(minValue, temperature)
            maxValue = max(maxValue, temperature)
            sumValue += temperature
            index += BYTES_PER_PIXEL
        }
        val avgValue = if (pixelCount > 0) sumValue / pixelCount else 0.0
        val range = (maxValue - minValue).takeIf { it > 0.0001 } ?: 1.0
        val normalized = ByteArray(pixelCount)
        for (i in 0 until pixelCount) {
            val scaled = ((celsius[i] - minValue) / range * 255.0).coerceIn(0.0, 255.0)
            normalized[i] = scaled.toInt().toByte()
        }
        return Metrics(
            minCelsius = minValue,
            maxCelsius = maxValue,
            avgCelsius = avgValue,
            normalized = normalized
        )
    }

    fun createBitmapFromFrame(frame: TopdonPreviewFrame): Bitmap {
        val metrics = normalize(frame.payload)
        return createBitmap(metrics, frame.width, frame.height)
    }

    fun createBitmap(metrics: Metrics, width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val pixels = IntArray(width * height)

        for (i in metrics.normalized.indices) {
            val normalized = (metrics.normalized[i].toInt() and 0xFF) / 255.0
            pixels[i] = applyIronbowPalette(normalized)
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    private fun applyIronbowPalette(normalized: Double): Int {
        val r = (normalized * 255).toInt().coerceIn(0, 255)
        val g = ((normalized - 0.5) * 510).toInt().coerceIn(0, 255)
        val b = ((1.0 - normalized) * 255).toInt().coerceIn(0, 255)
        return Color.rgb(r, g, b)
    }

    private fun convertRawToCelsius(raw: Int): Double {
        return raw / 100.0 - 273.15
    }

    private companion object {
        private const val BYTES_PER_PIXEL = 2
    }
}
