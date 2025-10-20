# BuccanCS Integration Action Plan: Fixing Preview, Sensors & Architecture

## Current Issues Analysis

### 1. Preview Not Working

**Problem**: The thermal preview UI exists but doesn't show actual frames from
the hardware.

**Root Causes**:

- `DefaultTopdonThermalClient` exists but incomplete implementation
- Missing native SDK integration bridge
- No actual frame callback wiring to UI
- Preview flow not connected to real hardware events

### 2. Sensor Handling Issues

**Problem**: Hardware abstraction is incomplete and untested.

**Root Causes**:

- `topdon.aar` exists but not fully integrated
- USB device detection/permission flow incomplete
- Missing UVC camera lifecycle management
- No temperature data extraction pipeline

### 3. Architecture Conflicts

**Problem**: Mix of clean architecture with incomplete hardware layer.

**Root Causes**:

- Domain layer defines abstractions but data layer doesn't fulfill them
- Simulation fallback works but hardware path broken
- UI state management assumes capabilities not yet implemented
- Missing intermediate data transformation layers

## IRCamera Elements We Can Adopt

### CRITICAL ADOPTI ONS (Must Have)

#### 1. USB Device Management Pattern

**From**:
`external/IRCamera/libir/src/main/java/com/infisense/usbir/camera/IRUVCTC.java`

**What to adopt**:

```kotlin
// Their working USBMonitor pattern
private val mUSBMonitor = USBMonitor(context, object : USBMonitor.OnDeviceConnectListener {
    override fun onAttach(device: UsbDevice) {
        mUSBMonitor.requestPermission(device)
    }

    override fun onConnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock, createNew: Boolean) {
        // Open UVC camera
        uvcCamera.open(ctrlBlock)
    }

    override fun onDisconnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock) {
        uvcCamera.close()
    }
})
```

**Apply to**: `DefaultTopdonThermalClient.kt` lines 132-150

**Action**:

- Extract their complete USB lifecycle handling
- Implement proper permission flow
- Add device attachment state tracking
- Wire up UVC camera open/close properly

#### 2. Frame Callback Pipeline

**From**: `IRUVCTC.java` IFrameCallback implementation

**What to adopt**:

```kotlin
// Their frame processing pattern
private val iFrameCallback = object : IFrameCallback {
    override fun onFrame(
        temperature: ByteArray?,
        image: ByteArray?,
        cameraSize: CameraSize?,
        isTempReplacedWithTNR: Boolean
    ) {
        // Process 256x192 thermal data
        // Extract temperature metrics
        // Apply palette rendering
        // Emit to UI layer
    }
}
```

**Apply to**: New file
`thermal-topdon/src/main/java/com/buccancs/hardware/topdon/FrameProcessor.kt`

**Action**:

- Create dedicated frame processor class
- Handle 256x192 resolution correctly (current code may assume different size)
- Extract min/max/avg temperature from raw data
- Convert to `TopdonPreviewFrame` domain model

#### 3. UVC Camera Builder Pattern

**From**: IRCamera's UVC initialization

**What to adopt**:

```kotlin
val uvcCamera = ConcreateUVCBuilder()
    .setContext(context)
    .setUVCType(UVCType.USB_TYPE_IR)
    .setResultListener(callback)
    .create()

uvcCamera.registerDataFormat(CommonParams.DataFlowMode.USB_STREAM_DIRECT)
uvcCamera.startPreview()
```

**Apply to**: `DefaultTopdonThermalClient.kt` - complete the stubbed methods

**Action**:

- Implement proper UVC camera creation in `connect()`
- Set correct data flow mode (USB_STREAM_DIRECT for preview)
- Register frame callbacks before starting preview
- Handle preview lifecycle correctly

#### 4. IRCMD Configuration System

**From**: Their hardware command layer

**What to adopt**:

```kotlin
ircmd = ConcreteIRCMDBuilder()
    .setContext(context)
    .setUsbDevice(device)
    .setIRCMDType(IRCMDType.TC001)
    .create()

// Apply settings
ircmd?.setPalette(paletteIndex, callback)
ircmd?.setEmissivity(value, callback)
ircmd?.setShutterMode(auto, callback)
```

**Apply to**: New file
`thermal-topdon/src/main/java/com/buccancs/hardware/topdon/CommandLayer.kt`

**Action**:

- Create hardware command facade
- Implement settings application (palette, emissivity, gain)
- Handle async command callbacks
- Map domain `TopdonHardwareSettings` to vendor commands

### HIGH PRIORITY ADOPTIONS (Should Have)

#### 5. Temperature Data Extraction

**From**: Their temperature processing logic

**What to adopt**:

- 256 × 192 × 2 byte array parsing
- Temperature value normalization (raw → Celsius)
- Min/max/avg calculation algorithms
- Spot/area/line measurement modes

**Apply to**: `thermal-topdon/capture/TemperatureExtractor.kt` (already exists,
needs completion)

**Action**:

- Study their raw-to-celsius conversion
- Implement correct byte ordering (little-endian vs big-endian)
- Add proper range handling (-40°C to 550°C typical for TC001)
- Validate against known reference temperatures

#### 6. Palette Management

**From**: `component:pseudo` module

**What to adopt**:

- Iron bow, Rainbow, Arctic, Gray color maps
- Palette index to color LUT (lookup table)
- Auto-scaling vs fixed range rendering
- Custom palette support

**Apply to**: New module `common-ui` or within `thermal-topdon`

**Action**:

- Extract their ColorRGB palette definitions
- Create Jetpack Compose rendering layer
- Support real-time palette switching
- Add palette preview chips in settings

#### 7. Device Detection & Filtering

**From**: `app/src/main/res/xml/ir_device_filter.xml`

**What to adopt**:

```xml
<!-- Topdon TC001 primary devices -->
<usb-device product-id="0x5840" vendor-id="0x0BDA" />
<usb-device product-id="0x5830" vendor-id="0x0BDA" />

<!-- UVC class -->
<usb-device class="14" />
<usb-device class="239" subclass="2" />
```

**Apply to**: `app/src/main/AndroidManifest.xml`

**Action**:

- Add USB device intent filter to manifest
- Implement auto-launch on device attachment
- Handle multi-device scenarios (TC001, TC001 Plus, TC001 Lite)
- Add device type detection logic

#### 8. Auto-Gain Switching

**From**: Their LibIRProcess integration

**What to adopt**:

- High gain vs low gain mode detection
- Auto-switching based on temperature range
- Gain transition smoothing
- Manual gain override support

**Apply to**: `TopdonHardwareSettings` and command layer

**Action**:

- Add gain mode to settings repository
- Implement auto-gain algorithm
- Provide UI toggle in settings
- Handle gain switch artifacts in frame processing

### MEDIUM PRIORITY ADOPTIONS (Nice to Have)

#### 9. Super-Resolution (AI Upscaling)

**From**: `ai-upscale-release.aar`

**What to adopt**:

- Neural network upscaling from 256x192 to higher resolution
- Super-sampling factor configuration (1x, 2x, 4x)
- GPU-accelerated processing

**Apply to**: Optional enhancement in preview pipeline

**Action**:

- Evaluate if needed for research use case
- Consider computational cost vs benefit
- May be better to keep raw 256x192 for data integrity

#### 10. Advanced Measurement Tools

**From**: Their custom view components

**What to adopt**:

- Spot temperature measurement with crosshair
- Area (rectangle/circle) temperature analysis
- Line profile temperature plot
- Isotherm overlay (highlight temp ranges)

**Apply to**: `app/src/main/java/com/buccancs/ui/components/topdon/`

**Action**:

- Implement Compose Canvas-based measurement overlays
- Add touch gesture handling for measurement placement
- Display measurement results in overlay cards
- Export measurement data to session metadata

#### 11. Gallery & Playback

**From**: `component:thermal-ir` gallery system

**What to adopt**:

- Thermal image format (storing raw + rendered)
- Gallery organization by session/date
- Thermal image viewer with palette adjustment
- Video playback with temperature timeline

**Apply to**: Enhanced `TopdonGalleryRepository`

**Action**:

- Define thermal image file format (raw + metadata JSON)
- Implement gallery browsing UI
- Add thermal video player component
- Support exporting to standard formats (TIFF, MP4)

### LOW PRIORITY / AVOID

#### 12. Social Sharing Features

**From**: Their upload/share modules

**What NOT to adopt**:

- Social media integration
- Cloud sync to Topdon servers
- User account management
- Promotional features

**Reason**: BuccanCS is research-focused, not consumer app

#### 13. AR/VR Preview Modes

**From**: Their advanced preview features

**What NOT to adopt**:

- Augmented reality thermal overlay
- 3D thermal reconstruction
- VR headset support

**Reason**: Out of scope for physiological data collection

#### 14. Report Generation

**From**: `component:house` building analysis

**What NOT to adopt**:

- PDF report templates
- Building thermal analysis presets
- Electrical inspection workflows

**Reason**: Domain-specific to building inspection, not applicable

## Concrete Implementation Steps

### Phase 1: Fix Preview (Week 1)

**Goal**: Get live thermal preview working with actual hardware

**Tasks**:

1. ✅ Extract USB device filter XML and add to AndroidManifest
2. ✅ Complete `DefaultTopdonThermalClient` USB monitor lifecycle
3. ✅ Implement UVC camera creation and opening
4. ✅ Wire frame callback to preview flow
5. ✅ Create frame processor for 256x192 data
6. ✅ Test with physical TC001 device
7. ✅ Validate temperature ranges make sense

**Acceptance Criteria**:

- App detects TC001 when plugged in
- Permission dialog appears and grants access
- Preview shows live thermal image
- Frame rate ~15-30 FPS
- Temperature metrics display in UI

### Phase 2: Fix Sensor Settings (Week 2)

**Goal**: Enable palette and emissivity configuration

**Tasks**:

1. ✅ Implement IRCMD command layer wrapper
2. ✅ Wire palette selection to hardware commands
3. ✅ Implement emissivity adjustment
4. ✅ Add gain mode control
5. ✅ Test settings persistence and application
6. ✅ Validate calibration accuracy

**Acceptance Criteria**:

- Palette changes update preview in real-time
- Emissivity adjustment affects temperature readings
- Settings persist across app restarts
- Gain mode switching works smoothly

### Phase 3: Architecture Cleanup (Week 3)

**Goal**: Resolve conflicts and complete domain layer

**Tasks**:

1. ✅ Review and refactor repository interfaces
2. ✅ Ensure clean separation: hardware ↔ data ↔ domain ↔ UI
3. ✅ Complete temperature extraction pipeline
4. ✅ Add comprehensive error handling
5. ✅ Document architecture decisions
6. ✅ Write integration tests

**Acceptance Criteria**:

- All domain interfaces have working implementations
- Simulation mode and hardware mode both work
- Error states handled gracefully
- Code follows clean architecture principles

### Phase 4: Advanced Features (Week 4)

**Goal**: Add measurement tools and recording

**Tasks**:

1. ✅ Implement spot temperature measurement
2. ✅ Add area measurement overlays
3. ✅ Complete thermal video recording
4. ✅ Implement frame capture to gallery
5. ✅ Add measurement export to metadata
6. ✅ Test multi-session recording

**Acceptance Criteria**:

- User can place measurement spots/areas
- Recordings include all metadata
- Gallery displays captured images/videos
- Export includes temperature data

## File-Level Changes Needed

### Files to Modify

#### 1. `thermal-topdon/src/main/java/com/buccancs/hardware/topdon/DefaultTopdonThermalClient.kt`

**Current State**: Stub implementation with incomplete lifecycle

**Changes Needed**:

```kotlin
// Line 132-150: Complete USB monitor initialization
private fun initializeUsbMonitor() {
    usbMonitor = USBMonitor(usbContext, deviceListener)
    usbMonitor?.register()
    monitorRegistered.set(true)
}

// Add complete device listener implementation
private val deviceListener = object : USBMonitor.OnDeviceConnectListener {
    override fun onAttach(device: UsbDevice) {
        Log.d(logTag, "USB device attached: ${device.productName}")
        statusState.value = TopdonStatus.Attached(
            vendorId = device.vendorId,
            productId = device.productId,
            serialNumber = device.serialNumber,
            hasPermission = usbManager.hasPermission(device)
        )
        if (!usbManager.hasPermission(device)) {
            usbMonitor?.requestPermission(device)
        }
    }

    override fun onConnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock, createNew: Boolean) {
        connectedDevice = device
        connectedSinceEpochMs = System.currentTimeMillis()
        openUvcCamera(ctrlBlock)
    }

    override fun onDisconnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock) {
        closeUvcCamera()
        connectedDevice = null
    }

    override fun onDettach(device: UsbDevice) {
        statusState.value = TopdonStatus.Idle
    }

    override fun onCancel(device: UsbDevice) {
        statusState.value = TopdonStatus.Error(
            "USB permission denied",
            recoverable = true
        )
    }
}

// Add UVC camera opening logic
private fun openUvcCamera(ctrlBlock: USBMonitor.UsbControlBlock) {
    try {
        uvcCamera = ConcreateUVCBuilder()
            .setContext(usbContext)
            .setUVCType(UVCType.USB_TYPE_IR)
            .setResultListener(object : OnCreateResultCallback {
                override fun onCreateResult(success: Boolean) {
                    if (success) {
                        initializeIrcmd()
                        statusState.value = TopdonStatus.Connected(
                            vendorId = connectedDevice!!.vendorId,
                            productId = connectedDevice!!.productId,
                            serialNumber = connectedDevice?.serialNumber,
                            sinceEpochMs = connectedSinceEpochMs
                        )
                    } else {
                        statusState.value = TopdonStatus.Error(
                            "Failed to open UVC camera",
                            recoverable = true
                        )
                    }
                }
            })
            .create()

        uvcCamera?.open(ctrlBlock)
        uvcCamera?.registerDataFormat(CommonParams.DataFlowMode.USB_STREAM_DIRECT)

    } catch (e: Exception) {
        Log.e(logTag, "Error opening UVC camera", e)
        statusState.value = TopdonStatus.Error(e.message ?: "Unknown error", true)
    }
}
```

**Priority**: CRITICAL

#### 2. `thermal-topdon/src/main/java/com/buccancs/hardware/topdon/FrameProcessor.kt` (NEW)

**Current State**: Doesn't exist

**What to Create**:

```kotlin
package com.buccancs.hardware.topdon

import com.buccancs.core.result.Result
import com.buccancs.core.result.resultOf
import com.energy.iruvc.utils.IFrameCallback
import com.energy.iruvc.uvc.CameraSize
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * Processes raw thermal frames from Topdon SDK into domain models.
 * Handles temperature extraction, palette application, and metrics calculation.
 */
class TopdonFrameProcessor @Inject constructor(
    private val temperatureExtractor: TemperatureExtractor,
    private val paletteRenderer: PaletteRenderer
) : IFrameCallback {

    private val frameChannel = Channel<TopdonPreviewFrame>(Channel.BUFFERED)
    val frames: Flow<TopdonPreviewFrame> = frameChannel.receiveAsFlow()

    private var currentPalette: Palette = Palette.Ironbow
    private var superSamplingFactor: Int = 1

    override fun onFrame(
        temperature: ByteArray?,
        image: ByteArray?,
        cameraSize: CameraSize?,
        isTempReplacedWithTNR: Boolean
    ) {
        if (temperature == null || cameraSize == null) return

        // Expected: 256x192 resolution, 2 bytes per pixel
        val expectedSize = 256 * 192 * 2
        if (temperature.size != expectedSize) {
            Log.w("FrameProcessor", "Unexpected temperature data size: ${temperature.size}")
            return
        }

        // Extract temperature metrics
        val metrics = temperatureExtractor.extract(
            rawData = temperature,
            width = 256,
            height = 192
        )

        // Render with current palette
        val renderedPayload = paletteRenderer.render(
            temperature = temperature,
            palette = currentPalette,
            metrics = metrics,
            superSamplingFactor = superSamplingFactor
        )

        val frame = TopdonPreviewFrame(
            timestampEpochMs = System.currentTimeMillis(),
            width = 256 * superSamplingFactor,
            height = 192 * superSamplingFactor,
            payload = renderedPayload,
            metrics = metrics,
            palette = currentPalette,
            superSamplingFactor = superSamplingFactor,
            mimeType = "image/rgba"
        )

        frameChannel.trySend(frame)
    }

    fun updatePalette(palette: Palette) {
        currentPalette = palette
    }

    fun updateSuperSampling(factor: Int) {
        superSamplingFactor = factor
    }
}
```

**Priority**: CRITICAL

#### 3. `thermal-topdon/src/main/java/com/buccancs/hardware/topdon/CommandLayer.kt` (NEW)

**Current State**: Doesn't exist

**What to Create**:

```kotlin
package com.buccancs.hardware.topdon

import android.hardware.usb.UsbDevice
import android.util.Log
import com.energy.iruvc.ircmd.ConcreteIRCMDBuilder
import com.energy.iruvc.ircmd.IRCMD
import com.energy.iruvc.ircmd.IRCMDType
import com.energy.iruvc.ircmd.OnResultListener
import com.energy.iruvc.ircmd.ResultCode
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

/**
 * Hardware command layer for Topdon thermal camera.
 * Wraps IRCMD vendor SDK with coroutine-based API.
 */
class TopdonCommandLayer @Inject constructor() {

    private var ircmd: IRCMD? = null

    fun initialize(device: UsbDevice, context: android.content.Context) {
        ircmd = ConcreteIRCMDBuilder()
            .setContext(context)
            .setUsbDevice(device)
            .setIRCMDType(IRCMDType.TC001)
            .create()
    }

    suspend fun setPalette(palette: Palette): Result<Unit> =
        suspendCancellableCoroutine { continuation ->
            val paletteIndex = when (palette) {
                Palette.Ironbow -> 0
                Palette.Gray -> 1
                Palette.Rainbow -> 2
                Palette.Arctic -> 3
                Palette.Custom -> 4
            }

            ircmd?.setPalette(paletteIndex, object : OnResultListener {
                override fun onResult(code: ResultCode) {
                    if (code == ResultCode.SUCCESS) {
                        continuation.resume(Result.Success(Unit))
                    } else {
                        continuation.resume(Result.Failure(Exception("Set palette failed: $code")))
                    }
                }
            })
        }

    suspend fun setEmissivity(value: Double): Result<Unit> =
        suspendCancellableCoroutine { continuation ->
            // Emissivity range: 0.01 to 1.0
            val clampedValue = value.coerceIn(0.01, 1.0)

            ircmd?.setEmissivity(clampedValue.toFloat(), object : OnResultListener {
                override fun onResult(code: ResultCode) {
                    if (code == ResultCode.SUCCESS) {
                        continuation.resume(Result.Success(Unit))
                    } else {
                        continuation.resume(Result.Failure(Exception("Set emissivity failed: $code")))
                    }
                }
            })
        }

    suspend fun setGainMode(mode: GainMode): Result<Unit> =
        suspendCancellableCoroutine { continuation ->
            val autoGain = mode == GainMode.Auto

            ircmd?.setShutterMode(autoGain, object : OnResultListener {
                override fun onResult(code: ResultCode) {
                    if (code == ResultCode.SUCCESS) {
                        continuation.resume(Result.Success(Unit))
                    } else {
                        continuation.resume(Result.Failure(Exception("Set gain mode failed: $code")))
                    }
                }
            })
        }

    fun shutdown() {
        ircmd?.close()
        ircmd = null
    }
}
```

**Priority**: CRITICAL

#### 4. `thermal-topdon/src/main/java/com/buccancs/hardware/topdon/PaletteRenderer.kt` (NEW)

**Current State**: Doesn't exist

**What to Create**:

```kotlin
package com.buccancs.hardware.topdon

import android.graphics.Color
import javax.inject.Inject

/**
 * Renders temperature data to RGB image using color palettes.
 * Based on IRCamera pseudo-color mapping.
 */
class PaletteRenderer @Inject constructor() {

    // Color lookup tables for each palette (256 colors)
    private val ironbowLut = generateIronbowPalette()
    private val grayLut = generateGrayPalette()
    private val rainbowLut = generateRainbowPalette()
    private val arcticLut = generateArcticPalette()

    fun render(
        temperature: ByteArray,
        palette: Palette,
        metrics: TopdonTemperatureMetrics,
        superSamplingFactor: Int = 1
    ): ByteArray {
        val lut = when (palette) {
            Palette.Ironbow -> ironbowLut
            Palette.Gray -> grayLut
            Palette.Rainbow -> rainbowLut
            Palette.Arctic -> arcticLut
            Palette.Custom -> ironbowLut // fallback
        }

        val width = 256
        val height = 192
        val outputWidth = width * superSamplingFactor
        val outputHeight = height * superSamplingFactor

        // RGBA output: 4 bytes per pixel
        val output = ByteArray(outputWidth * outputHeight * 4)

        // Normalize temperature values to 0-255 range for LUT indexing
        val tempMin = metrics.minCelsius
        val tempMax = metrics.maxCelsius
        val tempRange = tempMax - tempMin

        for (y in 0 until height) {
            for (x in 0 until width) {
                val srcIdx = (y * width + x) * 2

                // Convert 2-byte temperature value (little-endian)
                val tempRaw = ((temperature[srcIdx + 1].toInt() and 0xFF) shl 8) or
                             (temperature[srcIdx].toInt() and 0xFF)

                // Convert raw to celsius (formula from Topdon SDK docs)
                val tempCelsius = (tempRaw.toDouble() / 10.0) - 273.15

                // Normalize to 0-255 range
                val normalizedValue = if (tempRange > 0) {
                    ((tempCelsius - tempMin) / tempRange * 255.0).toInt().coerceIn(0, 255)
                } else {
                    128
                }

                // Get RGB from palette
                val rgb = lut[normalizedValue]

                // Apply super-sampling (simple nearest-neighbor)
                for (sy in 0 until superSamplingFactor) {
                    for (sx in 0 until superSamplingFactor) {
                        val destX = x * superSamplingFactor + sx
                        val destY = y * superSamplingFactor + sy
                        val destIdx = (destY * outputWidth + destX) * 4

                        output[destIdx] = Color.red(rgb).toByte()
                        output[destIdx + 1] = Color.green(rgb).toByte()
                        output[destIdx + 2] = Color.blue(rgb).toByte()
                        output[destIdx + 3] = 255.toByte() // Alpha
                    }
                }
            }
        }

        return output
    }

    private fun generateIronbowPalette(): IntArray {
        // Generate ironbow color ramp (black -> red -> yellow -> white)
        return IntArray(256) { i ->
            when {
                i < 85 -> Color.rgb(i * 3, 0, 0)
                i < 170 -> Color.rgb(255, (i - 85) * 3, 0)
                else -> Color.rgb(255, 255, (i - 170) * 3)
            }
        }
    }

    private fun generateGrayPalette(): IntArray {
        return IntArray(256) { i -> Color.rgb(i, i, i) }
    }

    private fun generateRainbowPalette(): IntArray {
        // HSV rainbow mapping
        return IntArray(256) { i ->
            Color.HSVToColor(floatArrayOf(i / 256f * 360f, 1f, 1f))
        }
    }

    private fun generateArcticPalette(): IntArray {
        // Blue to white gradient
        return IntArray(256) { i ->
            Color.rgb(i, i, 255)
        }
    }
}
```

**Priority**: HIGH

#### 5. `app/src/main/AndroidManifest.xml`

**Current State**: Missing USB intent filters

**Add**:

```xml
<activity
    android:name=".MainActivity"
    android:exported="true"
    ...>

    <!-- Add USB device attachment intent filter -->
    <intent-filter>
        <action android:name="android.hardware.usb.action.USB_DEVICE_ATTACHED" />
    </intent-filter>

    <meta-data
        android:name="android.hardware.usb.action.USB_DEVICE_ATTACHED"
        android:resource="@xml/usb_device_filter" />
</activity>
```

Create `app/src/main/res/xml/usb_device_filter.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Topdon TC001 primary devices -->
    <usb-device product-id="0x5840" vendor-id="0x0BDA" />
    <usb-device product-id="0x5830" vendor-id="0x0BDA" />

    <!-- Generic UVC video class -->
    <usb-device class="14" />
    <usb-device class="239" subclass="2" />
</resources>
```

**Priority**: CRITICAL

#### 6. `app/src/main/java/com/buccancs/ui/topdon/thermal/ThermalFrameDisplay.kt` (NEW)

**Current State**: Referenced in ThermalPreviewScreen but doesn't exist

**What to Create**:

```kotlin
package com.buccancs.ui.topdon.thermal

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.buccancs.hardware.topdon.TopdonPreviewFrame
import java.nio.ByteBuffer

@Composable
fun ThermalFrameDisplay(
    frame: TopdonPreviewFrame,
    modifier: Modifier = Modifier
) {
    val bitmap = remember(frame) {
        createBitmapFromFrame(frame)
    }

    Box(modifier = modifier) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Thermal preview",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

private fun createBitmapFromFrame(frame: TopdonPreviewFrame): Bitmap? {
    return try {
        // Assuming payload is RGBA format from PaletteRenderer
        val bitmap = Bitmap.createBitmap(
            frame.width,
            frame.height,
            Bitmap.Config.ARGB_8888
        )
        bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(frame.payload))
        bitmap
    } catch (e: Exception) {
        null
    }
}
```

**Priority**: CRITICAL

## Testing Strategy

### Unit Tests

1. `TemperatureExtractorTest` - Validate raw data parsing
2. `PaletteRendererTest` - Verify color mapping correctness
3. `FrameProcessorTest` - Test frame pipeline with mocked SDK

### Integration Tests

1. USB device detection with emulated device
2. Preview flow with simulated frames
3. Settings application end-to-end
4. Recording session with validation

### Manual Testing Checklist

- [ ] TC001 detected when plugged in
- [ ] Permission dialog appears
- [ ] Preview starts and shows image
- [ ] Temperature values are reasonable
- [ ] Palette switching updates preview
- [ ] Emissivity changes affect readings
- [ ] Spot measurement works
- [ ] Photo capture saves correctly
- [ ] Video recording includes all data
- [ ] Disconnect handled gracefully

## Success Metrics

### Preview Working

- Frame rate ≥ 15 FPS
- Latency < 200ms
- Temperature accuracy ± 2°C
- Zero crashes during 10-minute session

### Sensor Settings

- Palette change applies < 100ms
- Emissivity range 0.01-1.0 supported
- Settings persist across restarts
- All device variants detected

### Architecture Clean

- Zero cyclic dependencies
- All interfaces implemented
- Simulation and hardware modes work
- Code coverage > 70%

## Timeline

| Phase     | Duration    | Deliverable             |
| --------- | ----------- | ----------------------- |
| Phase 1   | 5 days      | Live preview working    |
| Phase 2   | 3 days      | Settings functional     |
| Phase 3   | 3 days      | Architecture refactored |
| Phase 4   | 4 days      | Advanced features       |
| **Total** | **15 days** | **Production ready**    |

## Risk Mitigation

### Risk 1: SDK Integration Issues

**Mitigation**: Keep simulation fallback working, gradual migration

### Risk 2: Performance Problems

**Mitigation**: Profile early, optimize rendering pipeline, consider native code

### Risk 3: Device Compatibility

**Mitigation**: Test with multiple TC001 variants, handle errors gracefully

### Risk 4: Architecture Conflicts

**Mitigation**: Refactor incrementally, maintain domain boundaries,
comprehensive tests

## Conclusion

The IRCamera codebase provides proven patterns for Topdon hardware integration.
By systematically adopting their USB management, frame processing, and command
layer approaches while maintaining BuccanCS's clean architecture, we can resolve
the preview and sensor issues within 2-3 weeks.

The key is to extract their working hardware layer patterns without importing
their consumer-focused feature set. Focus on the CRITICAL and HIGH priority
items first to unblock preview and basic functionality, then enhance with
measurement tools and advanced features.
