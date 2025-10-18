package com.buccancs.data.sensor.connector.topdon.capture

import kotlin.math.max
import kotlin.math.min

/**
 * Extracts temperature data from Topdon TC001 thermal frames
 * YUV422 format where Y channel contains temperature information
 */
internal object TemperatureExtractor {

    /**
     * Extract temperature statistics from YUV422 thermal frame
     *
     * @param yuvData Raw YUV422 frame data
     * @param width Frame width in pixels
     * @param height Frame height in pixels
     * @return Temperature statistics (min, max, avg)
     */
    fun extractTemperatureStats(
        yuvData: ByteArray,
        width: Int,
        height: Int
    ): TemperatureStats {
        if (yuvData.isEmpty()) {
            return TemperatureStats(
                0f,
                0f,
                0f
            )
        }

        val pixelCount =
            width * height
        var minValue =
            255
        var maxValue =
            0
        var sum =
            0L

        // YUV422: Y0 U0 Y1 V0 pattern (2 bytes per pixel for Y, U and V shared)
        // Extract Y values which contain temperature information
        for (i in 0 until min(
            pixelCount * 2,
            yuvData.size
        ) step 2) {
            val yValue =
                yuvData[i].toInt() and 0xFF
            minValue =
                min(
                    minValue,
                    yValue
                )
            maxValue =
                max(
                    maxValue,
                    yValue
                )
            sum += yValue
        }

        val avgValue =
            if (pixelCount > 0) sum.toFloat() / pixelCount else 0f

        // Convert raw Y values to approximate temperature (Celsius)
        // TC001 typical range: -20°C to 550°C
        // Y value 0-255 maps to temperature range
        val minTemp =
            yValueToTemperature(
                minValue
            )
        val maxTemp =
            yValueToTemperature(
                maxValue
            )
        val avgTemp =
            yValueToTemperature(
                avgValue.toInt()
            )

        return TemperatureStats(
            minTemp,
            maxTemp,
            avgTemp
        )
    }

    /**
     * Extract temperature at specific pixel coordinate
     *
     * @param yuvData Raw YUV422 frame data
     * @param width Frame width in pixels
     * @param x Pixel X coordinate
     * @param y Pixel Y coordinate
     * @return Temperature at pixel in Celsius
     */
    fun extractTemperatureAt(
        yuvData: ByteArray,
        width: Int,
        x: Int,
        y: Int
    ): Float {
        val offset =
            (y * width + x) * 2 // YUV422 has 2 bytes per pixel
        if (offset < 0 || offset >= yuvData.size) {
            return 0f
        }

        val yValue =
            yuvData[offset].toInt() and 0xFF
        return yValueToTemperature(
            yValue
        )
    }

    /**
     * Extract temperature data for rectangular area
     *
     * @param yuvData Raw YUV422 frame data
     * @param width Frame width in pixels
     * @param x Rectangle X coordinate
     * @param y Rectangle Y coordinate
     * @param w Rectangle width
     * @param h Rectangle height
     * @return Temperature statistics for area
     */
    fun extractAreaTemperature(
        yuvData: ByteArray,
        width: Int,
        x: Int,
        y: Int,
        w: Int,
        h: Int
    ): TemperatureStats {
        var minValue =
            255
        var maxValue =
            0
        var sum =
            0L
        var count =
            0

        for (row in y until (y + h)) {
            for (col in x until (x + w)) {
                val offset =
                    (row * width + col) * 2
                if (offset >= 0 && offset < yuvData.size) {
                    val yValue =
                        yuvData[offset].toInt() and 0xFF
                    minValue =
                        min(
                            minValue,
                            yValue
                        )
                    maxValue =
                        max(
                            maxValue,
                            yValue
                        )
                    sum += yValue
                    count++
                }
            }
        }

        val avgValue =
            if (count > 0) sum.toFloat() / count else 0f

        return TemperatureStats(
            minTemp = yValueToTemperature(
                minValue
            ),
            maxTemp = yValueToTemperature(
                maxValue
            ),
            avgTemp = yValueToTemperature(
                avgValue.toInt()
            )
        )
    }

    /**
     * Convert Y channel value to temperature
     * TC001 specifications: -20°C to 550°C range
     * Linear mapping from 0-255 to temperature range
     */
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

    /**
     * Convert temperature to Y channel value
     */
    private fun temperatureToYValue(
        tempCelsius: Float
    ): Int {
        val minTemp =
            -20f
        val maxTemp =
            550f
        val range =
            maxTemp - minTemp
        val normalized =
            (tempCelsius - minTemp) / range
        return (normalized * 255f).toInt()
            .coerceIn(
                0,
                255
            )
    }
}

/**
 * Temperature statistics for thermal frame or region
 */
data class TemperatureStats(
    val minTemp: Float,
    val maxTemp: Float,
    val avgTemp: Float
) {
    /**
     * Convert to Fahrenheit
     */
    fun toFahrenheit(): TemperatureStats {
        return TemperatureStats(
            minTemp = celsiusToFahrenheit(
                minTemp
            ),
            maxTemp = celsiusToFahrenheit(
                maxTemp
            ),
            avgTemp = celsiusToFahrenheit(
                avgTemp
            )
        )
    }

    private fun celsiusToFahrenheit(
        celsius: Float
    ): Float {
        return (celsius * 9f / 5f) + 32f
    }
}
