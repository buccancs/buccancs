**Last Modified:** 2025-10-16 00:12 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Implementation Guide

# Topdon Thermal Preview Implementation Guide

## Overview

This guide provides step-by-step instructions for implementing the missing Topdon thermal camera preview, photo capture,
and video recording features. The UI is complete and production-ready, but the data layer connections need to be wired
up.

## Prerequisites

- Android SDK Platform 36 licence accepted (`sdkmanager --licenses`)
- Clean build verification (`./gradlew :app:assembleDebug`)
- Topdon TC001 hardware or thermal simulator enabled

## Current State

### What Works

- USB connectivity via USBMonitor/UVCCamera
- Frame callbacks from thermal camera
- Temperature data extraction
- Thermal normalisation and palette application
- File I/O and session management
- UI components and navigation

### What's Missing

- Preview frame StateFlow exposure
- Photo capture integration
- Video recording state tracking
- Settings application to preview

## Implementation Tasks

### Task 1: Add Preview State to ManagedConnector

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonConnectorManager.kt`

**Location:** Line 364 (ManagedConnector data class)

**Change:**

```kotlin
private data class ManagedConnector(
    val connector: TopdonThermalConnector,
    val settings: InMemoryTopdonSettingsRepository,
    val deviceJob: Job,
    val statusJob: Job,
    val configJob: Job,
    var config: TopdonDeviceConfig,
    // ADD THESE TWO FIELDS:
    val previewFrameFlow: StateFlow<TopdonPreviewFrame?>,
    val previewRunningFlow: StateFlow<Boolean>
)
```

### Task 2: Create Preview Flows in TopdonThermalConnector

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonThermalConnector.kt`

**Location:** After line 98 (after pendingArtifacts)

**Add:**

```kotlin
// Preview state management
private val _previewFrameFlow = MutableStateFlow<TopdonPreviewFrame?>(null)
val previewFrameFlow: StateFlow<TopdonPreviewFrame?> = _previewFrameFlow.asStateFlow()

private val _previewRunningFlow = MutableStateFlow(false)
val previewRunningFlow: StateFlow<Boolean> = _previewRunningFlow.asStateFlow()

private var lastPreviewEmitTimeMs: Long = 0
private val previewThrottleMs = 42L // ~24 FPS
```

### Task 3: Update Frame Callback to Emit Preview Frames

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonThermalConnector.kt`

**Location:** Line 584 (frameCallback implementation)

**Modify the frameCallback from:**

```kotlin
private val frameCallback = IFrameCallback { data ->
    if (data != null) {
        try {
            thermalStream?.write(data)
            thermalDigest?.update(data)
            thermalBytes += data.size
        } catch (t: Throwable) {
            Log.w(logTag, "Failed to persist thermal frame", t)
        }
        val metrics = runCatching { thermalNormalizer.normalize(data) }.getOrNull()
        if (metrics != null) {
            lastThermalMetrics = metrics
        }
    }
    // ... rest of callback
}
```

**To:**

```kotlin
private val frameCallback = IFrameCallback { data ->
    if (data != null) {
        try {
            thermalStream?.write(data)
            thermalDigest?.update(data)
            thermalBytes += data.size
        } catch (t: Throwable) {
            Log.w(logTag, "Failed to persist thermal frame", t)
        }
        val metrics = runCatching { thermalNormalizer.normalize(data) }.getOrNull()
        if (metrics != null) {
            lastThermalMetrics = metrics
        }
        
        // NEW: Emit preview frames with throttling
        val now = System.currentTimeMillis()
        if (now - lastPreviewEmitTimeMs >= previewThrottleMs) {
            lastPreviewEmitTimeMs = now
            emitPreviewFrame(data, metrics)
        }
    }
    // ... rest of callback
}

// NEW METHOD: Add after frameCallback
private fun emitPreviewFrame(thermalData: ByteArray, metrics: ThermalNormalizer.Metrics?) {
    try {
        // Convert thermal data to bitmap using normalizer
        val bitmap = thermalNormalizer.createPreviewBitmap(thermalData)
        
        val frame = TopdonPreviewFrame(
            timestamp = Instant.fromEpochMilliseconds(System.currentTimeMillis()),
            width = THERMAL_WIDTH,
            height = THERMAL_HEIGHT,
            bitmap = bitmap,
            minTempCelsius = metrics?.minCelsius,
            maxTempCelsius = metrics?.maxCelsius,
            avgTempCelsius = metrics?.avgCelsius
        )
        
        _previewFrameFlow.value = frame
    } catch (t: Throwable) {
        Log.w(logTag, "Failed to emit preview frame", t)
    }
}
```

### Task 4: Add Preview Control Methods to TopdonThermalConnector

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonThermalConnector.kt`

**Location:** After line 582 (after collectArtifacts method)

**Add:**

```kotlin
suspend fun startPreview(): DeviceCommandResult {
    if (isSimulationMode) {
        _previewRunningFlow.value = true
        appScope.launch {
            // Simulate preview frames
            while (_previewRunningFlow.value) {
                delay(42) // ~24 FPS
                val simulatedFrame = createSimulatedPreviewFrame()
                _previewFrameFlow.value = simulatedFrame
            }
        }
        return DeviceCommandResult.Accepted
    }
    
    val camera = uvcCamera ?: return DeviceCommandResult.Rejected("Camera not connected")
    
    return try {
        camera.setFrameCallback(frameCallback)
        camera.onStartPreview()
        _previewRunningFlow.value = true
        DeviceCommandResult.Accepted
    } catch (t: Throwable) {
        Log.e(logTag, "Failed to start preview", t)
        DeviceCommandResult.Failed(t)
    }
}

suspend fun stopPreview(): DeviceCommandResult {
    if (isSimulationMode) {
        _previewRunningFlow.value = false
        _previewFrameFlow.value = null
        return DeviceCommandResult.Accepted
    }
    
    val camera = uvcCamera ?: return DeviceCommandResult.Rejected("Camera not connected")
    
    return try {
        camera.setFrameCallback(null)
        camera.onStopPreview()
        _previewRunningFlow.value = false
        _previewFrameFlow.value = null
        DeviceCommandResult.Accepted
    } catch (t: Throwable) {
        Log.e(logTag, "Failed to stop preview", t)
        DeviceCommandResult.Failed(t)
    }
}

private fun createSimulatedPreviewFrame(): TopdonPreviewFrame {
    // Use existing thermal simulator
    val bitmap = android.graphics.Bitmap.createBitmap(
        THERMAL_WIDTH, 
        THERMAL_HEIGHT, 
        android.graphics.Bitmap.Config.ARGB_8888
    )
    // Fill with gradient or pattern
    val canvas = android.graphics.Canvas(bitmap)
    val paint = android.graphics.Paint().apply {
        color = android.graphics.Color.rgb(
            (Math.random() * 100 + 100).toInt(),
            50,
            50
        )
    }
    canvas.drawRect(0f, 0f, THERMAL_WIDTH.toFloat(), THERMAL_HEIGHT.toFloat(), paint)
    
    return TopdonPreviewFrame(
        timestamp = Instant.fromEpochMilliseconds(System.currentTimeMillis()),
        width = THERMAL_WIDTH,
        height = THERMAL_HEIGHT,
        bitmap = bitmap,
        minTempCelsius = 18.0,
        maxTempCelsius = 35.0,
        avgTempCelsius = 26.5
    )
}
```

### Task 5: Wire Preview Methods in TopdonConnectorManager

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonConnectorManager.kt`

**Location:** Lines 116-132 (replace stub implementations)

**Replace:**

```kotlin
fun previewFrame(deviceId: DeviceId): StateFlow<TopdonPreviewFrame?>? = null

fun previewRunning(deviceId: DeviceId): StateFlow<Boolean>? = null

suspend fun startPreview(deviceId: DeviceId): DeviceCommandResult =
    if (connectorCache.containsKey(deviceId)) {
        DeviceCommandResult.Rejected("Preview control not implemented")
    } else {
        DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    }

suspend fun stopPreview(deviceId: DeviceId): DeviceCommandResult =
    if (connectorCache.containsKey(deviceId)) {
        DeviceCommandResult.Rejected("Preview control not implemented")
    } else {
        DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    }
```

**With:**

```kotlin
fun previewFrame(deviceId: DeviceId): StateFlow<TopdonPreviewFrame?>? {
    return connectorCache[deviceId]?.previewFrameFlow
}

fun previewRunning(deviceId: DeviceId): StateFlow<Boolean>? {
    return connectorCache[deviceId]?.previewRunningFlow
}

suspend fun startPreview(deviceId: DeviceId): DeviceCommandResult {
    val managed = connectorCache[deviceId]
        ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    return managed.connector.startPreview()
}

suspend fun stopPreview(deviceId: DeviceId): DeviceCommandResult {
    val managed = connectorCache[deviceId]
        ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    return managed.connector.stopPreview()
}
```

### Task 6: Update createConnector to Initialize Preview Flows

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonConnectorManager.kt`

**Location:** Line 293 (ManagedConnector creation)

**Replace:**

```kotlin
val managed = ManagedConnector(
    connector = connector,
    settings = settings,
    deviceJob = deviceJob,
    statusJob = statusJob,
    configJob = configJob,
    config = normalizedConfig
)
```

**With:**

```kotlin
val managed = ManagedConnector(
    connector = connector,
    settings = settings,
    deviceJob = deviceJob,
    statusJob = statusJob,
    configJob = configJob,
    config = normalizedConfig,
    previewFrameFlow = connector.previewFrameFlow,
    previewRunningFlow = connector.previewRunningFlow
)
```

### Task 7: Add Photo Capture Implementation

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonConnectorManager.kt`

**Location:** Lines 134-139 (replace stub)

**Replace:**

```kotlin
suspend fun capturePhoto(deviceId: DeviceId): DeviceCommandResult =
    if (connectorCache.containsKey(deviceId)) {
        DeviceCommandResult.Rejected("Photo capture not implemented")
    } else {
        DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    }
```

**With:**

```kotlin
suspend fun capturePhoto(deviceId: DeviceId): DeviceCommandResult {
    val managed = connectorCache[deviceId]
        ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    
    // Use the current preview frame as photo
    val frame = managed.previewFrameFlow.value
        ?: return DeviceCommandResult.Rejected("No preview frame available")
    
    return withContext(Dispatchers.IO) {
        try {
            // Save to MediaStore gallery
            val contentResolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "thermal_${System.currentTimeMillis()}.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/BuccanCS/Thermal")
            }
            
            val uri = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            ) ?: return@withContext DeviceCommandResult.Failed(
                IllegalStateException("Failed to create media store entry")
            )
            
            contentResolver.openOutputStream(uri)?.use { outputStream ->
                frame.bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 95, outputStream)
            }
            
            Log.i("TopdonConnectorManager", "Photo saved to $uri")
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e("TopdonConnectorManager", "Failed to capture photo", t)
            DeviceCommandResult.Failed(t)
        }
    }
}
```

### Task 8: Add Video Recording State Tracking

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonConnectorManager.kt`

**Location:** After line 61 (after statusState)

**Add:**

```kotlin
private val recordingState = MutableStateFlow<Map<DeviceId, Boolean>>(emptyMap())
```

### Task 9: Implement Video Recording Methods

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonConnectorManager.kt`

**Location:** Lines 141-153 (replace stubs)

**Replace:**

```kotlin
suspend fun startRecording(deviceId: DeviceId): DeviceCommandResult =
    if (connectorCache.containsKey(deviceId)) {
        DeviceCommandResult.Rejected("Video recording not implemented")
    } else {
        DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    }

suspend fun stopRecording(deviceId: DeviceId): DeviceCommandResult =
    if (connectorCache.containsKey(deviceId)) {
        DeviceCommandResult.Rejected("Video recording not implemented")
    } else {
        DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    }
```

**With:**

```kotlin
suspend fun startRecording(deviceId: DeviceId): DeviceCommandResult {
    val managed = connectorCache[deviceId]
        ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    
    if (recordingState.value[deviceId] == true) {
        return DeviceCommandResult.Rejected("Already recording")
    }
    
    // Create temporary session for standalone recording
    val sessionId = "standalone_${System.currentTimeMillis()}"
    val anchor = RecordingSessionAnchor(
        sessionId = sessionId,
        anchorEpochMs = System.currentTimeMillis(),
        anchorElapsedRealtimeNanos = android.os.SystemClock.elapsedRealtimeNanos()
    )
    
    val result = managed.connector.startStreaming(anchor)
    if (result is DeviceCommandResult.Accepted) {
        recordingState.value = recordingState.value + (deviceId to true)
    }
    return result
}

suspend fun stopRecording(deviceId: DeviceId): DeviceCommandResult {
    val managed = connectorCache[deviceId]
        ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
    
    if (recordingState.value[deviceId] != true) {
        return DeviceCommandResult.Rejected("Not recording")
    }
    
    val result = managed.connector.stopStreaming()
    if (result is DeviceCommandResult.Accepted) {
        recordingState.value = recordingState.value - deviceId
    }
    return result
}

fun isRecording(deviceId: DeviceId): Boolean {
    return recordingState.value[deviceId] == true
}
```

### Task 10: Expose Recording State to Repository

**File:** `app/src/main/java/com/buccancs/data/sensor/topdon/DefaultTopdonDeviceRepository.kt`

**Location:** After line 64 (after previewFrame)

**Add:**

```kotlin
override val isRecording: StateFlow<Boolean> = activeDeviceId
    .flatMapLatest { id -> flowOf(connectorManager.isRecording(id)) }
    .stateIn(scope, SharingStarted.Eagerly, false)
```

**Also update interface:**

**File:** `app/src/main/java/com/buccancs/domain/repository/TopdonDeviceRepository.kt`

**Location:** After line 10

**Add:**

```kotlin
val isRecording: StateFlow<Boolean>
```

### Task 11: Update TopdonViewModel to Use Recording State

**File:** `app/src/main/java/com/buccancs/ui/topdon/TopdonViewModel.kt`

**Location:** Line 185 (TopdonUiState data class)

**Replace:**

```kotlin
data class TopdonUiState(
    // ... existing fields ...
    val isRecording: Boolean = false
)
```

**With:**

```kotlin
// Update the combine in init block to include isRecording flow
val uiState: StateFlow<TopdonUiState> = combine(
    streamState,
    previewFrames,
    settingsFlow,
    deviceRepository.isRecording // ADD THIS
) { deviceState, previewFrame, settings, isRecording ->
    TopdonUiState(
        // ... existing fields ...
        isRecording = isRecording
    )
}.stateIn(/* ... */)
```

### Task 12: Add ThermalNormalizer Preview Bitmap Method

**File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/ThermalNormalizer.kt`

**Location:** After existing normalize method

**Add:**

```kotlin
fun createPreviewBitmap(thermalData: ByteArray): android.graphics.Bitmap {
    val metrics = normalize(thermalData)
    val bitmap = android.graphics.Bitmap.createBitmap(
        256, 192, android.graphics.Bitmap.Config.ARGB_8888
    )
    
    // Apply palette colorization
    val pixels = IntArray(256 * 192)
    for (i in pixels.indices) {
        val temp = metrics.temperatures.getOrNull(i) ?: metrics.avgCelsius
        val normalized = ((temp - metrics.minCelsius) / (metrics.maxCelsius - metrics.minCelsius))
            .coerceIn(0.0, 1.0)
        pixels[i] = applyIronbowPalette(normalized)
    }
    
    bitmap.setPixels(pixels, 0, 256, 0, 0, 256, 192)
    return bitmap
}

private fun applyIronbowPalette(normalized: Double): Int {
    // Simple ironbow palette (blue -> red -> yellow)
    val r = (normalized * 255).toInt().coerceIn(0, 255)
    val g = ((normalized - 0.5) * 510).toInt().coerceIn(0, 255)
    val b = ((1.0 - normalized) * 255).toInt().coerceIn(0, 255)
    return android.graphics.Color.rgb(r, g, b)
}
```

## Testing Checklist

After implementing all tasks:

### Unit Tests

- [ ] Preview frame emission throttling
- [ ] Photo capture MediaStore integration
- [ ] Recording state management
- [ ] Settings application to preview

### Integration Tests

- [ ] Start preview shows thermal feed
- [ ] Stop preview clears display
- [ ] Photo capture saves to gallery
- [ ] Recording creates video file
- [ ] Settings changes apply immediately

### Manual Testing

- [ ] Connect Topdon TC001
- [ ] Navigate to thermal preview screen
- [ ] Verify live thermal feed displays
- [ ] Capture photo and verify in gallery
- [ ] Start recording and verify file created
- [ ] Change palette and verify preview updates
- [ ] Change FPS limit and verify frame rate
- [ ] Disconnect camera and verify error handling

## Build and Deployment

```bash
# Clean build
./gradlew clean

# Compile and check for errors
./gradlew :app:compileDebugKotlin

# Run tests (when enabled)
./gradlew :app:testDebugUnitTest -Ptests.enabled=true

# Build APK
./gradlew :app:assembleDebug

# Install on device
./gradlew :app:installDebug

# Launch app
adb shell am start -n com.buccancs/.MainActivity
```

## Troubleshooting

### Preview Not Showing

- Check USB permissions granted
- Verify camera connection status
- Enable thermal simulator if hardware unavailable
- Check logcat for frame callback errors

### Photo Capture Fails

- Verify WRITE_EXTERNAL_STORAGE permission
- Check MediaStore API compatibility (Android 10+)
- Verify preview frame is available

### Recording Not Starting

- Check storage space available
- Verify session management initialised
- Check for concurrent recording conflicts

### Performance Issues

- Adjust preview FPS throttle (line 101 in TopdonThermalConnector)
- Enable hardware acceleration
- Profile with Android Profiler
- Check memory usage during recording

## Documentation Updates Required

After implementation:

1. **dev-diary.md** - Log implementation session
2. **backlog.md** - Mark tasks as DONE
3. **readme.md** - Update feature list to reflect thermal preview working
4. **docs/guides/** - Create thermal camera user guide
5. **docs/architecture/** - Document thermal preview architecture

## Estimated Implementation Time

- Task 1-6 (Preview): 3 hours
- Task 7 (Photo): 1 hour
- Task 8-11 (Recording): 2 hours
- Task 12 (Bitmap): 1 hour
- Testing: 2 hours
- Documentation: 1 hour

**Total: 10 hours**

## Next Steps After Implementation

1. Physical hardware validation with TC001
2. Performance optimization (GPU rendering)
3. OpenGL thermal rendering (Phase 7-9 from backlog)
4. Advanced measurement modes (spot, area, line)
5. Temperature alarms and tracking
6. Multi-camera support

## References

- External Topdon app: `external/original-topdon-app/`
- Topdon SDK documentation: `sdk/libs/topdon.aar`
- Material 3 components: `app/src/main/java/com/buccancs/ui/components/topdon/`
- Thermal preview screen: `app/src/main/java/com/buccancs/ui/topdon/thermal/ThermalPreviewScreen.kt`


