# Topdon TC001 Capture Utilities

This
package
provides
hardware
integration
utilities
for
the
Topdon
TC001
thermal
camera.

## Components

### TopdonCaptureManager

Manages
photo
capture
and
video
recording
for
thermal
frames.

*

*Usage:
**

```kotlin
val captureManager = TopdonCaptureManager(context, deviceId)

// Capture photo
val result = captureManager.capturePhoto(thermalFrame)
if (result.isSuccess) {
    val capture = result.getOrNull()!!
    Log.i("Capture", "Photo saved: ${capture.uri}")
}

// Record video
captureManager.startRecording(sessionId)
// ... for each frame
captureManager.recordFrame(thermalFrame)
// ... when done
val recordingResult = captureManager.stopRecording()
```

### TemperatureExtractor

Extracts
temperature
data
from
YUV422
thermal
frames.

*

*Usage:
**

```kotlin
// Full frame statistics
val stats = TemperatureExtractor.extractTemperatureStats(
    yuvData = frame.payload,
    width = frame.width,
    height = frame.height
)
println("Min: ${stats.minTemp}°C, Max: ${stats.maxTemp}°C")

// Single pixel
val temp = TemperatureExtractor.extractTemperatureAt(
    yuvData = frame.payload,
    width = frame.width,
    x = 100,
    y = 50
)

// Rectangular area
val areaStats = TemperatureExtractor.extractAreaTemperature(
    yuvData = frame.payload,
    width = frame.width,
    x = 10, y = 10, w = 50, h = 50
)
```

### MeasurementProcessor

Implements
thermal
measurement
modes (
spot,
area,
line,
max/min).

*

*Usage:
**

```kotlin
// Spot measurement
val spot = MeasurementProcessor.measureSpot(
    yuvData, width, height, x, y
)
println("Temperature at (${spot.x}, ${spot.y}): ${spot.temperature}°C")

// Area measurement
val area = MeasurementProcessor.measureArea(
    yuvData, width, height, x1, y1, x2, y2
)
println("Area: ${area.area} pixels, Avg: ${area.avgTemp}°C")

// Line measurement
val line = MeasurementProcessor.measureLine(
    yuvData, width, height, x1, y1, x2, y2
)
println("Temperature profile: ${line.profile}")

// Max/Min detection
val maxMin = MeasurementProcessor.measureMaxMin(
    yuvData, width, height
)
println("Hottest: (${maxMin.maxX}, ${maxMin.maxY}) = ${maxMin.maxTemp}°C")
println("Coldest: (${maxMin.minX}, ${maxMin.minY}) = ${maxMin.minTemp}°C")
```

## YUV422 Format

The
TC001
camera
outputs
thermal
data
in
YUV422
format:

-

Pattern:
Y0
U0
Y1
V0 (
4
bytes
for
2
pixels)

-

Y
channel
contains
temperature
information (
2
bytes
per
pixel)

-

Temperature
range:
-20°C
to
550°C
mapped
to
Y
values
0-255

## Output Files

Photos:
`/sdcard/Android/data/com.buccancs/files/Topdon/photos/`

-

THERMAL_yyyyMMdd_HHmmss.jpg
-
THERMAL_yyyyMMdd_HHmmss.jpg.json (
metadata)

Videos:
`/sdcard/Android/data/com.buccancs/files/Topdon/videos/`

-

THERMAL_VIDEO_yyyyMMdd_HHmmss.raw
-
THERMAL_VIDEO_yyyyMMdd_HHmmss.raw.json (
metadata)

## Testing

Unit
tests:
`app/src/test/.../capture/`

-

TemperatureExtractorTest.kt -
8
tests

-

MeasurementProcessorTest.kt -
12
tests

Run
tests:
`./gradlew test`
