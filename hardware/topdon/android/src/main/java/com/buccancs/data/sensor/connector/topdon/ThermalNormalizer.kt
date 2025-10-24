package com.buccancs.data.sensor.connector.topdon

import android.graphics.Bitmap
import android.graphics.Color
import com.buccancs.domain.model.TopdonDynamicRange
import com.buccancs.domain.model.TopdonPalette
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
        val normalized: ByteArray,
        val scaledMinCelsius: Double,
        val scaledMaxCelsius: Double
    )

    fun normalize(
        rawFrame: ByteArray,
        dynamicRange: TopdonDynamicRange = TopdonDynamicRange.STANDARD
    ): Metrics {
        if (rawFrame.isEmpty()) {
            return Metrics(
                minCelsius = 0.0,
                maxCelsius = 0.0,
                avgCelsius = 0.0,
                normalized = ByteArray(0),
                scaledMinCelsius = 0.0,
                scaledMaxCelsius = 0.0
            )
        }
        val pixelCount =
            rawFrame.size / BYTES_PER_PIXEL
        var minValue =
            Double.POSITIVE_INFINITY
        var maxValue =
            Double.NEGATIVE_INFINITY
        var sumValue =
            0.0
        val celsius =
            DoubleArray(pixelCount)
        var index =
            0
        while (index + 1 < rawFrame.size) {
            val raw =
                (rawFrame[index].toInt() and 0xFF) or ((rawFrame[index + 1].toInt() and 0xFF) shl 8)
            val temperature =
                convertRawToCelsius(raw)
            val pixelIndex =
                index / BYTES_PER_PIXEL
            celsius[pixelIndex] =
                temperature
            minValue =
                min(minValue, temperature)
            maxValue =
                max(maxValue, temperature)
            sumValue += temperature
            index += BYTES_PER_PIXEL
        }
        val avgValue =
            if (pixelCount > 0) sumValue / pixelCount else 0.0

        val (scaledMin, scaledMax) =
            clampForDynamicRange(
                minValue,
                maxValue,
                dynamicRange
            )

        val range =
            (scaledMax - scaledMin).takeIf { it > 0.0001 } ?: 1.0
        val normalized =
            ByteArray(pixelCount)
        for (i in 0 until pixelCount) {
            val scaled =
                ((celsius[i] - scaledMin) / range * 255.0).coerceIn(
                    0.0,
                    255.0
                )
            normalized[i] =
                scaled.toInt().toByte()
        }

        return Metrics(
            minCelsius = minValue,
            maxCelsius = maxValue,
            avgCelsius = avgValue,
            normalized = normalized,
            scaledMinCelsius = scaledMin,
            scaledMaxCelsius = scaledMax
        )
    }

    fun createBitmapFromFrame(
        frame: TopdonPreviewFrame,
        palette: TopdonPalette = TopdonPalette.IRONBOW,
        dynamicRange: TopdonDynamicRange = TopdonDynamicRange.STANDARD
    ): Bitmap {
        val metrics =
            normalize(
                frame.payload,
                dynamicRange
            )
        return createBitmap(
            metrics = metrics,
            width = frame.width,
            height = frame.height,
            palette = palette
        )
    }

    fun createBitmap(
        metrics: Metrics,
        width: Int,
        height: Int,
        palette: TopdonPalette
    ): Bitmap {
        val bitmap =
            Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888
            )
        val pixels =
            IntArray(width * height)
        for (i in metrics.normalized.indices) {
            val normalized =
                (metrics.normalized[i].toInt() and 0xFF) / 255.0
            pixels[i] =
                colorForNormalized(
                    normalized,
                    palette
                )
        }
        bitmap.setPixels(
            pixels,
            0,
            width,
            0,
            0,
            width,
            height
        )
        return bitmap
    }

    private fun colorForNormalized(
        normalized: Double,
        palette: TopdonPalette
    ): Int =
        when (palette) {
            TopdonPalette.GRAYSCALE -> {
                val value =
                    (normalized * 255).toInt().coerceIn(0, 255)
                Color.rgb(
                    value,
                    value,
                    value
                )
            }

            TopdonPalette.IRONBOW ->
                ironbowColor(normalized)

            TopdonPalette.RAINBOW ->
                rainbowColor(normalized)

            TopdonPalette.ARCTIC ->
                arcticColor(normalized)
        }

    private fun ironbowColor(
        normalized: Double
    ): Int {
        val r =
            (normalized * 255).toInt().coerceIn(0, 255)
        val g =
            ((normalized - 0.4) * 320).toInt().coerceIn(0, 255)
        val b =
            ((1.0 - normalized) * 255).toInt().coerceIn(0, 255)
        return Color.rgb(
            r,
            g,
            b
        )
    }

    private fun rainbowColor(
        normalized: Double
    ): Int {
        val hue =
            ((1.0 - normalized) * 240.0).toFloat()
        return Color.HSVToColor(
            floatArrayOf(
                hue,
                1f,
                1f.coerceIn(0f, 1f)
            )
        )
    }

    private fun arcticColor(
        normalized: Double
    ): Int {
        val hue =
            (200.0 + normalized * 40.0).toFloat()
        val saturation =
            (0.4 + normalized * 0.4).toFloat().coerceIn(0f, 1f)
        val value =
            (0.3 + normalized * 0.7).toFloat().coerceIn(0f, 1f)
        return Color.HSVToColor(
            floatArrayOf(
                hue,
                saturation,
                value
            )
        )
    }

    private fun clampForDynamicRange(
        minValue: Double,
        maxValue: Double,
        dynamicRange: TopdonDynamicRange
    ): Pair<Double, Double> {
        if (dynamicRange == TopdonDynamicRange.STANDARD) {
            return minValue to maxValue
        }
        val span =
            maxValue - minValue
        if (span <= 0.0001) {
            return minValue to maxValue
        }
        val padding =
            span * 0.05
        val adjustedMin =
            minValue + padding
        val adjustedMax =
            maxValue - padding
        return adjustedMin to adjustedMax.coerceAtLeast(adjustedMin + 0.001)
    }

    private fun convertRawToCelsius(
        raw: Int
    ): Double =
        raw / 100.0 - 273.15

    companion object {
        private const val BYTES_PER_PIXEL =
            2
    }
}
