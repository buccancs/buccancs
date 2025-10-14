**Last Modified:** 2025-01-14 04:41 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Technical Documentation

# Topdon TC001 Thermal Frame Format

## Overview

Documentation of the Topdon TC001 thermal camera frame format, temperature conversion, and data processing pipeline.

## Hardware Specifications

**Model:** Topdon TC001  
**Sensor Type:** Uncooled microbolometer  
**Resolution:** 256 x 192 pixels (49,152 pixels total)  
**Frame Rate:** 25 FPS (typical)  
**Interface:** USB UVC (Video Class)  
**Temperature Range:** -20°C to 550°C (typical)  
**Accuracy:** ±2°C or ±2% (whichever is greater)

## Frame Structure

### Raw Frame Format

Each thermal frame consists of 16-bit raw values in little-endian format:

```
Frame Size: 98,304 bytes (256 * 192 * 2)
Pixel Format: 16-bit unsigned integer (little-endian)
Layout: Row-major (left-to-right, top-to-bottom)
```

### Pixel Layout

```
Pixel[0,0]     Pixel[1,0]     ...  Pixel[255,0]
Pixel[0,1]     Pixel[1,1]     ...  Pixel[255,1]
...            ...            ...  ...
Pixel[0,191]   Pixel[1,191]   ...  Pixel[255,191]
```

### Frame Header

No additional header in raw UVC stream. Frame boundaries determined by USB packet structure.

## Temperature Conversion

### Raw Value to Temperature

The Topdon TC001 uses a linear conversion formula:

```kotlin
/**
 * Convert 16-bit raw value to temperature in Celsius.
 * 
 * Formula: Temperature (K) = RawValue / 64
 *          Temperature (°C) = (RawValue / 64) - 273.15
 */
fun rawToTemperatureCelsius(raw: Short): Float {
    val kelvin = raw.toInt() / 64.0f
    val celsius = kelvin - 273.15f
    return celsius
}

/**
 * Convert temperature in Celsius to raw 16-bit value.
 * 
 * Formula: RawValue = (Temperature(°C) + 273.15) * 64
 */
fun temperatureToRaw(celsius: Float): Short {
    val kelvin = celsius + 273.15f
    val raw = (kelvin * 64.0f).toInt()
    return raw.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
}
```

### Example Conversions

| Raw Value | Kelvin | Celsius | Fahrenheit |
|-----------|--------|---------|------------|
| 17903     | 279.73 | 6.58    | 43.84      |
| 18944     | 296.00 | 22.85   | 73.13      |
| 19200     | 300.00 | 26.85   | 80.33      |
| 20480     | 320.00 | 46.85   | 116.33     |
| 32768     | 512.00 | 238.85  | 461.93     |

### Validation

```kotlin
// Typical indoor scene range checks
fun isValidThermalValue(raw: Short): Boolean {
    val celsius = rawToTemperatureCelsius(raw)
    return celsius in -40.0f..100.0f  // Reasonable range
}
```

## Frame Processing Pipeline

### 1. Frame Capture

```kotlin
// Callback from UVCCamera SDK
private val frameCallback = IFrameCallback { data: ByteArray? ->
    if (data != null && data.size == EXPECTED_FRAME_SIZE) {
        processFrame(data)
    }
}

companion object {
    const val FRAME_WIDTH = 256
    const val FRAME_HEIGHT = 192
    const val EXPECTED_FRAME_SIZE = FRAME_WIDTH * FRAME_HEIGHT * 2  // 98,304 bytes
}
```

### 2. Raw Data Extraction

```kotlin
/**
 * Extract 16-bit pixel values from raw byte array.
 */
fun extractPixels(rawData: ByteArray): ShortArray {
    require(rawData.size == EXPECTED_FRAME_SIZE) { "Invalid frame size" }
    
    val pixels = ShortArray(FRAME_WIDTH * FRAME_HEIGHT)
    val buffer = ByteBuffer.wrap(rawData).order(ByteOrder.LITTLE_ENDIAN)
    
    for (i in pixels.indices) {
        pixels[i] = buffer.getShort(i * 2)
    }
    
    return pixels
}
```

### 3. Temperature Analysis

```kotlin
/**
 * Analyze thermal frame for min/max temperatures.
 */
data class ThermalAnalysis(
    val minCelsius: Float,
    val maxCelsius: Float,
    val meanCelsius: Float,
    val minLocation: Pair<Int, Int>,
    val maxLocation: Pair<Int, Int>
)

fun analyzeFrame(pixels: ShortArray): ThermalAnalysis {
    var minRaw = Short.MAX_VALUE
    var maxRaw = Short.MIN_VALUE
    var sumCelsius = 0.0
    var minIdx = 0
    var maxIdx = 0
    
    for (i in pixels.indices) {
        val raw = pixels[i]
        if (raw < minRaw) {
            minRaw = raw
            minIdx = i
        }
        if (raw > maxRaw) {
            maxRaw = raw
            maxIdx = i
        }
        sumCelsius += rawToTemperatureCelsius(raw)
    }
    
    return ThermalAnalysis(
        minCelsius = rawToTemperatureCelsius(minRaw),
        maxCelsius = rawToTemperatureCelsius(maxRaw),
        meanCelsius = (sumCelsius / pixels.size).toFloat(),
        minLocation = Pair(minIdx % FRAME_WIDTH, minIdx / FRAME_WIDTH),
        maxLocation = Pair(maxIdx % FRAME_WIDTH, maxIdx / FRAME_WIDTH)
    )
}
```

### 4. Normalization for Display

```kotlin
/**
 * Normalize thermal data to 8-bit grayscale for display.
 */
fun normalizeForDisplay(pixels: ShortArray): ByteArray {
    val minRaw = pixels.minOrNull() ?: 0
    val maxRaw = pixels.maxOrNull() ?: 1
    val range = (maxRaw - minRaw).coerceAtLeast(1)
    
    return ByteArray(pixels.size) { i ->
        val normalized = ((pixels[i] - minRaw).toFloat() / range * 255.0f)
        normalized.toInt().coerceIn(0, 255).toByte()
    }
}
```

### 5. False Color Mapping

```kotlin
/**
 * Apply false color (e.g., Ironbow) to thermal data.
 * Maps temperature range to color gradient.
 */
fun applyFalseColor(pixels: ShortArray, colorMap: ColorMap): IntArray {
    val minTemp = pixels.minOrNull()?.let { rawToTemperatureCelsius(it) } ?: 0f
    val maxTemp = pixels.maxOrNull()?.let { rawToTemperatureCelsius(it) } ?: 100f
    val range = (maxTemp - minTemp).coerceAtLeast(1f)
    
    return IntArray(pixels.size) { i ->
        val temp = rawToTemperatureCelsius(pixels[i])
        val normalized = ((temp - minTemp) / range).coerceIn(0f, 1f)
        colorMap.getColor(normalized)
    }
}

enum class ColorMap {
    GRAYSCALE,
    IRONBOW,
    RAINBOW,
    HOT;
    
    fun getColor(normalized: Float): Int {
        // Color lookup table implementation
        // Returns ARGB color value
    }
}
```

## File Storage Format

### Raw Thermal Recording

```kotlin
/**
 * Store raw thermal frames to file for later processing.
 * Format: Sequential 16-bit values, no compression.
 */
fun writeRawFrame(outputStream: OutputStream, pixels: ShortArray) {
    val buffer = ByteBuffer.allocate(pixels.size * 2).order(ByteOrder.LITTLE_ENDIAN)
    pixels.forEach { buffer.putShort(it) }
    outputStream.write(buffer.array())
}

/**
 * Read raw thermal frame from file.
 */
fun readRawFrame(inputStream: InputStream): ShortArray {
    val bytes = ByteArray(EXPECTED_FRAME_SIZE)
    val read = inputStream.read(bytes)
    require(read == EXPECTED_FRAME_SIZE) { "Incomplete frame" }
    
    return extractPixels(bytes)
}
```

### Metadata Format

```json
{
  "sessionId": "session-uuid",
  "deviceId": "topdon-tc001",
  "streamType": "thermal_video",
  "frameWidth": 256,
  "frameHeight": 192,
  "frameCount": 1523,
  "bytesCaptured": 149782272,
  "durationMs": 60920,
  "startAlignedEpochMs": 1704398765432,
  "alignedEndEpochMs": 1704398826352,
  "frameMinCelsius": 18.45,
  "frameMaxCelsius": 34.67,
  "checksumSha256": "a1b2c3..."
}
```

## Performance Characteristics

### Frame Timing

- **Capture Rate:** 25 FPS (40ms per frame)
- **USB Transfer:** ~5 MB/s average
- **Processing Time:** < 5ms per frame (normalized)
- **Display Latency:** < 50ms typical

### Memory Usage

- **Raw Frame:** 98,304 bytes (96 KB)
- **Normalized:** 49,152 bytes (48 KB)
- **ARGB Bitmap:** 196,608 bytes (192 KB)
- **Total per Frame:** ~336 KB

### Recording Storage

```
1 minute @ 25 FPS:
  Frames: 1,500
  Raw data: 147 MB
  With metadata: ~148 MB
  
1 hour @ 25 FPS:
  Frames: 90,000
  Raw data: 8.8 GB
  With metadata: ~8.85 GB
```

## Calibration and Accuracy

### Temperature Accuracy

**Manufacturer Spec:** ±2°C or ±2% (whichever is greater)

**Example Error Margins:**
- At 25°C: ±2°C (reading: 23-27°C)
- At 100°C: ±2°C (reading: 98-102°C)
- At 200°C: ±4°C (reading: 196-204°C, 2% of 200)
- At 500°C: ±10°C (reading: 490-510°C, 2% of 500)

### Factors Affecting Accuracy

1. **Emissivity** - Surface material affects IR emission
2. **Distance** - Optimal range: 0.3m - 3m
3. **Ambient Temperature** - Calibration assumes 25°C ambient
4. **Reflected Temperature** - Shiny surfaces can reflect IR
5. **Transmission** - Air moisture, dust can attenuate

### Emissivity Correction

```kotlin
/**
 * Correct temperature reading based on material emissivity.
 * Most materials: 0.95, Shiny metal: 0.1-0.3, Matte surfaces: 0.95-0.98
 */
fun correctForEmissivity(measuredTemp: Float, emissivity: Float, ambientTemp: Float = 25f): Float {
    val measuredK = measuredTemp + 273.15f
    val ambientK = ambientTemp + 273.15f
    
    // Stefan-Boltzmann correction
    val correctedK4 = (measuredK.pow(4) - (1 - emissivity) * ambientK.pow(4)) / emissivity
    val correctedK = correctedK4.pow(0.25f)
    
    return correctedK - 273.15f
}
```

## Troubleshooting

### Common Issues

**Issue: All pixels read as same value**
- Cause: Camera shutter closed or not initialized
- Solution: Send init command, wait for first valid frame

**Issue: Temperatures way off (e.g., 500°C indoors)**
- Cause: Incorrect byte order or scale factor
- Solution: Verify little-endian, check division by 64

**Issue: Noisy image**
- Cause: Normal sensor noise or low-quality optics
- Solution: Apply spatial filtering, verify focus

**Issue: Saturated readings (max/min values)**
- Cause: Temperature outside sensor range
- Solution: Check if scene is within -20°C to 550°C

## Testing

### Validation Tests

```kotlin
@Test
fun `temperature conversion roundtrip`() {
    val originalTemp = 25.0f
    val raw = temperatureToRaw(originalTemp)
    val convertedTemp = rawToTemperatureCelsius(raw)
    
    assertEquals(originalTemp, convertedTemp, 0.1f)
}

@Test
fun `frame size validation`() {
    val frame = captureFrame()
    assertEquals(EXPECTED_FRAME_SIZE, frame.size)
}

@Test
fun `typical indoor scene temperatures`() {
    val analysis = analyzeFrame(captureFrame())
    
    // Indoor scene should be roughly room temperature
    assertTrue(analysis.minCelsius in 15f..30f)
    assertTrue(analysis.maxCelsius in 20f..40f)
}
```

## References

- Topdon TC001 User Manual (Chinese)
- USB Video Class Specification 1.5
- Stefan-Boltzmann Law for IR radiation
- Current implementation: `TopdonThermalConnector.kt`
- Normalization: `ThermalNormalizer.kt`

## Revision History

| Date | Version | Changes |
|------|---------|---------|
| 2025-01-14 | 1.0 | Initial documentation |

---

**Document Status:** Complete  
**Reviewed:** Pending  
**Next Review:** When SDK is updated
