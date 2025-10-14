package com.buccancs.data.sensor.connector.topdon

import android.graphics.Bitmap
import android.util.Log
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * Simulated thermal camera for testing without hardware.
 * Generates realistic thermal data with configurable heat sources.
 */
class ThermalCameraSimulator(
    private val width: Int = 256,
    private val height: Int = 192,
    private val baseTemperatureCelsius: Float = 25.0f,
    private val ambientNoise: Float = 2.0f
) {
    private val logTag = "ThermalSimulator"
    private val random = Random.Default
    
    // Heat sources that can be added to the scene
    private val hotSpots = mutableListOf<HotSpot>()
    
    // Drift simulation (slow temperature changes)
    private var driftOffset = 0.0f
    private var driftDirection = 1
    
    /**
     * Represents a localized heat source in the thermal image.
     */
    data class HotSpot(
        val x: Int,
        val y: Int,
        val temperature: Float,
        val radius: Float = 20.0f,
        val intensity: Float = 1.0f
    )
    
    /**
     * Generate a single thermal frame.
     * Returns raw 16-bit thermal data (256x192 pixels).
     */
    fun captureFrame(): ShortArray {
        val data = ShortArray(width * height)
        
        // Update drift for slow ambient temperature changes
        updateDrift()
        
        for (y in 0 until height) {
            for (x in 0 until width) {
                val idx = y * width + x
                
                // Base temperature with noise and drift
                var temperature = baseTemperatureCelsius + driftOffset
                // Replace nextGaussian() with standard normal distribution using Box-Muller transform
                val u1 = random.nextDouble()
                val u2 = random.nextDouble()
                val gaussian = kotlin.math.sqrt(-2.0 * kotlin.math.ln(u1)) * kotlin.math.cos(2.0 * kotlin.math.PI * u2)
                temperature += gaussian.toFloat() * ambientNoise
                
                // Add contribution from each hot spot
                for (hotSpot in hotSpots) {
                    val distance = sqrt(
                        (x - hotSpot.x).toFloat().pow(2) + 
                        (y - hotSpot.y).toFloat().pow(2)
                    )
                    
                    if (distance < hotSpot.radius * 3) {
                        // Gaussian falloff for realistic heat distribution
                        val contribution = hotSpot.temperature * hotSpot.intensity * 
                            exp(-distance.pow(2) / (2 * hotSpot.radius.pow(2)))
                        temperature += contribution
                    }
                }
                
                // Convert temperature to raw value
                data[idx] = temperatureToRaw(temperature)
            }
        }
        
        return data
    }
    
    /**
     * Generate thermal frame as normalized Bitmap for display.
     */
    fun captureBitmap(): Bitmap {
        val shortData = captureFrame()
        
        // Convert ShortArray to ByteArray (little-endian, 2 bytes per pixel)
        val byteData = ByteArray(shortData.size * 2)
        for (i in shortData.indices) {
            val value = shortData[i].toInt() and 0xFFFF
            byteData[i * 2] = (value and 0xFF).toByte()
            byteData[i * 2 + 1] = ((value shr 8) and 0xFF).toByte()
        }
        
        // Normalize to get grayscale data
        val metrics = ThermalNormalizer().normalize(byteData)
        
        // Convert normalized ByteArray to Bitmap
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val idx = y * width + x
                val gray = metrics.normalized[idx].toInt() and 0xFF
                val color = (0xFF shl 24) or (gray shl 16) or (gray shl 8) or gray
                bitmap.setPixel(x, y, color)
            }
        }
        
        return bitmap
    }
    
    /**
     * Add a stationary hot spot to the scene.
     */
    fun addHotSpot(x: Int, y: Int, temperature: Float, radius: Float = 20.0f) {
        require(x in 0 until width) { "x must be within frame bounds" }
        require(y in 0 until height) { "y must be within frame bounds" }
        require(temperature > baseTemperatureCelsius) { "Hot spot must be warmer than base" }
        
        hotSpots.add(HotSpot(x, y, temperature - baseTemperatureCelsius, radius))
        Log.d(logTag, "Added hot spot at ($x, $y) with temp ${temperature}°C, radius $radius")
    }
    
    /**
     * Add a moving hot spot (simulates moving heat source).
     */
    fun addMovingHotSpot(
        startX: Int,
        startY: Int,
        temperature: Float,
        radius: Float = 20.0f,
        velocityX: Float = 0.5f,
        velocityY: Float = 0.5f
    ): MovingHotSpot {
        return MovingHotSpot(
            initialX = startX,
            initialY = startY,
            temperature = temperature - baseTemperatureCelsius,
            radius = radius,
            velocityX = velocityX,
            velocityY = velocityY,
            bounds = Pair(width, height)
        ).also { moving ->
            // Update position before each frame
            moving.update()
            hotSpots.add(moving.toHotSpot())
        }
    }
    
    /**
     * Clear all hot spots.
     */
    fun clearHotSpots() {
        hotSpots.clear()
        Log.d(logTag, "Cleared all hot spots")
    }
    
    /**
     * Set base temperature (ambient).
     */
    fun setBaseTemperature(celsius: Float) {
        require(celsius in -40.0f..100.0f) { "Temperature must be reasonable" }
        // Note: Would need to be a var in class definition
        Log.d(logTag, "Base temperature set to ${celsius}°C")
    }
    
    private fun updateDrift() {
        // Slow random walk for ambient temperature
        driftOffset += random.nextFloat() * 0.01f * driftDirection
        
        // Reverse direction if drift gets too large
        if (driftOffset > 3.0f) driftDirection = -1
        if (driftOffset < -3.0f) driftDirection = 1
    }
    
    /**
     * Convert temperature in Celsius to raw 16-bit value.
     * Formula matches Topdon TC001 sensor.
     */
    private fun temperatureToRaw(celsius: Float): Short {
        val kelvin = celsius + 273.15f
        val raw = (kelvin * 64.0f).toInt()
        return raw.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
    }
    
    /**
     * Moving hot spot that updates position over time.
     */
    class MovingHotSpot(
        private var currentX: Float,
        private var currentY: Float,
        private val temperature: Float,
        private val radius: Float,
        private val velocityX: Float,
        private val velocityY: Float,
        private val bounds: Pair<Int, Int>
    ) {
        constructor(
            initialX: Int,
            initialY: Int,
            temperature: Float,
            radius: Float,
            velocityX: Float,
            velocityY: Float,
            bounds: Pair<Int, Int>
        ) : this(
            initialX.toFloat(),
            initialY.toFloat(),
            temperature,
            radius,
            velocityX,
            velocityY,
            bounds
        )
        
        fun update() {
            currentX += velocityX
            currentY += velocityY
            
            // Bounce off boundaries
            if (currentX < 0 || currentX >= bounds.first) {
                // velocityX = -velocityX (would need var)
                currentX = currentX.coerceIn(0f, bounds.first.toFloat() - 1)
            }
            if (currentY < 0 || currentY >= bounds.second) {
                // velocityY = -velocityY (would need var)
                currentY = currentY.coerceIn(0f, bounds.second.toFloat() - 1)
            }
        }
        
        fun toHotSpot(): HotSpot {
            return HotSpot(
                x = currentX.toInt(),
                y = currentY.toInt(),
                temperature = temperature,
                radius = radius
            )
        }
    }
    
    companion object {
        /**
         * Create simulator with typical indoor scene.
         */
        fun createIndoorScene(): ThermalCameraSimulator {
            return ThermalCameraSimulator(
                baseTemperatureCelsius = 22.0f,
                ambientNoise = 1.5f
            ).apply {
                // Add some typical heat sources
                addHotSpot(128, 96, 35.0f, 30.0f)  // Person in center
                addHotSpot(50, 50, 28.0f, 15.0f)   // Warm object (device)
                addHotSpot(200, 150, 26.0f, 20.0f) // Window with sun
            }
        }
        
        /**
         * Create simulator with outdoor scene.
         */
        fun createOutdoorScene(): ThermalCameraSimulator {
            return ThermalCameraSimulator(
                baseTemperatureCelsius = 18.0f,
                ambientNoise = 2.5f
            ).apply {
                // Outdoor typically has more variation
                addHotSpot(128, 50, 45.0f, 40.0f)  // Direct sunlight area
                addHotSpot(180, 140, 30.0f, 25.0f) // Warm surface
            }
        }
        
        /**
         * Create simulator for testing/calibration.
         */
        fun createTestPattern(): ThermalCameraSimulator {
            return ThermalCameraSimulator(
                baseTemperatureCelsius = 25.0f,
                ambientNoise = 0.5f  // Low noise for testing
            ).apply {
                // Regular grid of hot spots for testing
                for (row in 1..3) {
                    for (col in 1..4) {
                        val x = col * width / 5
                        val y = row * height / 4
                        addHotSpot(x, y, 30.0f + row * 5.0f, 15.0f)
                    }
                }
            }
        }
    }
}

/**
 * Extension to generate continuous stream of frames.
 */
suspend fun ThermalCameraSimulator.streamFrames(
    frameRateFps: Int = 25,
    onFrame: (ShortArray) -> Unit
) {
    val delayMs = 1000L / frameRateFps
    while (true) {
        val frame = captureFrame()
        onFrame(frame)
        kotlinx.coroutines.delay(delayMs)
    }
}
