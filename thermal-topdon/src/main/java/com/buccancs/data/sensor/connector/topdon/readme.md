# Topdon TC001 Thermal Camera Integration

## Overview

Complete
integration
of
Topdon
TC001
thermal
camera
with
preview
streaming,
photo
capture,
and
video
recording
capabilities.
Uses
UVC
protocol
over
USB-C
for
thermal
frame
acquisition.

## Components

### TopdonThermalConnector.kt

Main
connector
handling
USB
communication
and
thermal
frame
processing.

*

*Features:
**

-

USB
UVC
camera
connection
via
USBMonitor

-

Real-time
thermal
frame
capture
at
25
FPS

-

Preview
frame
emission
with
24
FPS
throttling

-

Photo
capture
and
video
recording

-

Simulated
mode
for
testing
without
hardware

*

*Key
Methods:
**

-

`startPreview()` -
Start
preview
frame
streaming

-

`stopPreview()` -
Stop
preview
streaming

-

`emitPreviewFrame()` -
Convert
thermal
data
to
TopdonPreviewFrame

-

`createSimulatedPreviewFrame()` -
Generate
test
frames

*

*State
Flows:
**

-

`previewFrameFlow` -
Emits
TopdonPreviewFrame
with
thermal
data

-

`previewRunningFlow` -
Emits
preview
running
state

### TopdonConnectorManager.kt

Multi-device
manager
coordinating
multiple
Topdon
cameras.

*

*Features:
**

-

Device
management
and
lifecycle

-

Preview
frame
routing
by
device
ID

-

Photo
capture
with
MediaStore
integration

-

Video
recording
with
session
management

-

Settings
persistence
per
device

*

*Key
Methods:
**

-

`previewFrame(deviceId)` -
Get
preview
flow
for
device

-

`previewRunning(deviceId)` -
Check
if
preview
active

-

`startPreview(deviceId)` -
Start
device
preview

-

`stopPreview(deviceId)` -
Stop
device
preview

-

`capturePhoto(deviceId)` -
Save
current
frame
to
gallery

-

`startRecording(deviceId)` -
Begin
thermal
video
recording

-

`stopRecording(deviceId)` -
Finalize
thermal
video

### ThermalNormalizer.kt

Temperature
data
processing
and
visualization.

*

*Features:
**

-

Raw
thermal
data
to
celsius
conversion

-

Temperature
normalization (
0-255
range)

-

Ironbow
palette
colorization

-

Bitmap
creation
for
UI
rendering

-

Average
temperature
calculation

*

*Key
Methods:
**

-

`normalize(rawFrame)` -
Extract
temperature
data

-

`createBitmapFromFrame(frame)` -
Convert
frame
to
bitmap

-

`createBitmap(metrics, width, height)` -
Render
with
palette

-

`applyIronbowPalette(normalized)` -
Color
mapping

### ThermalPreviewScreen.kt

Compose
UI
for
thermal
display
and
interaction.

*

*Features:
**

-

Full-screen
thermal
preview

-

Real-time
frame
rendering

-

Temperature
range
overlay

-

Control
panel
with
buttons

-

Settings
overlay

-

Connection
state
handling

*

*Composables:
**

-

`ThermalPreviewRoute()` -
Navigation
entry
point

-

`ThermalPreviewScreen()` -
Main
screen
layout

-

`ThermalFrameDisplay()` -
Frame
rendering

-

`ThermalPreviewArea()` -
Preview
container

-

`ThermalControlPanel()` -
Action
buttons

## Data Flow

### Preview Streaming

```
USB Camera (UVCCamera)
  ↓ IFrameCallback (thermal bytes)
  ↓ Check throttle (42ms interval)
  ↓ emitPreviewFrame()
  ↓ TopdonPreviewFrame created
  ↓ _previewFrameFlow.value = frame
  ↓ TopdonConnectorManager.previewFrame()
  ↓ DefaultTopdonDeviceRepository.previewFrame
  ↓ TopdonViewModel.uiState.previewFrame
  ↓ ThermalPreviewScreen
  ↓ createThermalBitmap()
  ↓ applyThermalPalette()
  ↓ Image(bitmap) renders
```

### Photo Capture

```
User taps capture button
  ↓ TopdonViewModel.capturePhoto()
  ↓ Repository.capturePhoto()
  ↓ Manager.capturePhoto(deviceId)
  ↓ Get current preview frame
  ↓ ThermalNormalizer.createBitmapFromFrame()
  ↓ MediaStore.insert()
  ↓ Bitmap.compress(JPEG, 95%)
  ↓ Save to Pictures/BuccanCS/Thermal
  ↓ DeviceCommandResult.Accepted
```

### Video Recording

```
User taps record
  ↓ TopdonViewModel.startRecording()
  ↓ Manager.startRecording(deviceId)
  ↓ Create RecordingSessionAnchor
  ↓ Connector.startStreaming(anchor)
  ↓ Frame callback writes to file
  ↓ User taps stop
  ↓ TopdonViewModel.stopRecording()
  ↓ Connector.stopStreaming()
  ↓ Finalize file, write metadata
  ↓ Add to session artifacts
```

## Configuration

### Thermal Specifications

-

Resolution:
256x192
pixels

-

Frame
Rate:
25
FPS (
camera),
24
FPS (
preview
throttle)

-

Data
Format:
16-bit
little-endian
per
pixel

-

Temperature
Range:
-20°C
to
400°C

-

Temperature
Formula:
celsius = (
raw /
100.0) -
273.15

### Performance

-

Frame
Processing: ~
5ms
per
frame

-

Preview
Throttle:
42ms (
24
FPS)

-

Bitmap
Creation: ~
10ms
per
frame

-

Memory
per
Frame: ~
200KB (
ARGB_8888)

### File Formats

-

Photo:
JPEG (
95%
quality)
in
MediaStore

-

Video:
Raw
thermal
data (
.raw) +
metadata (
.json)

-

Manifest:
Session
metadata
with
checksums

## Usage

### Start Preview

```kotlin
viewModel.startPreview()
// Preview frames emitted to uiState.previewFrame
```

### Capture Photo

```kotlin
viewModel.capturePhoto()
// Photo saved to gallery: Pictures/BuccanCS/Thermal
```

### Record Video

```kotlin
viewModel.startRecording()
// ... recording ...
viewModel.stopRecording()
// Video saved to session directory
```

### Access Preview Frame

```kotlin
val state by viewModel.uiState.collectAsStateWithLifecycle()
state.previewFrame?.let { frame ->
    // Access frame.payload (raw thermal data)
    // Access frame.minTemp, frame.maxTemp, frame.avgTemp
    // Render with ThermalFrameDisplay
}
```

## Testing

### Unit Tests

-

`TopdonThermalConnectorPreviewTest.kt` -
Preview
functionality

-

`ThermalNormalizerTest.kt` -
Temperature
processing
and
rendering

### Test Coverage

-

Frame
throttling
logic

-

Temperature
conversion
accuracy

-

Bitmap
creation
validation

-

Payload
size
verification

-

Metrics
calculation

### Manual Testing

1.

Enable
thermal
simulator:
`viewModel.applySimulation(true)`

2.

Start
preview
and
verify
simulated
gradient

3.

Capture
photo
and
check
gallery

4.

Start/stop
recording
and
verify
file
created

### Hardware Testing

1.

Connect
Topdon
TC001
via
USB-C

2.

Grant
USB
permissions

3.

Start
preview
and
verify
thermal
display

4.

Measure
known
temperature
sources

5.

Validate
against
contact
thermometer

## Troubleshooting

### No Preview Frames

-

Check
USB
connection
and
permissions

-

Verify
camera
detected:
`usbManager.deviceList`

-

Check
preview
running:
`state.previewActive`

-

Enable
logs:
`Log.d("TopdonConnector", ...)`

### Photo Capture Fails

-

Verify
WRITE_EXTERNAL_STORAGE
permission

-

Check
MediaStore
API
compatibility (
Android
10+)

-

Ensure
preview
frame
available

### Performance Issues

-

Reduce
preview
FPS
throttle (
increase
previewThrottleMs)

-

Check
device
CPU
usage

-

Monitor
memory
with
Android
Profiler

-

Consider
hardware
acceleration

### Temperature Inaccurate

-

Verify
raw
data
format (
16-bit
LE)

-

Check
temperature
formula: (
raw /
100.0) -
273.15

-

Validate
against
known
temperature
source

-

Check
emissivity
settings

## Future Enhancements

### Phase 2 (8 hours)

-

Implement
grayscale
and
rainbow
palettes

-

Apply
super-sampling (
2x,
4x)

-

Real-time
settings
updates

-

FPS
adjustment
feedback

### Phase 3 (12 hours)

-

Spot
temperature
measurement

-

Area/line
measurement
tools

-

Temperature
tracking
overlay

-

Touch
interaction
handling

### Phase 4 (20 hours)

-

OpenGL
rendering
for
60
FPS

-

GPU
palette
shaders

-

Hardware
acceleration

-

Smooth
zoom/pan
animations

## References

-

Topdon
SDK:
`sdk/libs/topdon.aar`

-

External
App:
`external/original-topdon-app/`

-

Platform
overview (
hardware
summary):
`docs/system-overview.md`

-

Validation
drills
and
troubleshooting:
`docs/testing.md`
