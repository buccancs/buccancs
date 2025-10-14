**Last Modified:** 2025-01-14 04:26 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Analysis

# External Dependency Analysis

## Overview

Analysis of external SDK integrations (Shimmer, Topdon, OpenCV) focusing on integration quality, issues, and
recommendations for improvement.

## 1. Shimmer SDK Integration

### Current Status

**Usage:** 83 import statements from Shimmer packages  
**Primary File:** `app/src/main/java/com/buccancs/data/sensor/connector/shimmer/ShimmerSensorConnector.kt` (770+ lines)

### Integration Quality ✅

**Strengths:**

- ✅ Proper abstraction via `SensorConnector` interface
- ✅ Simulation mode for testing without hardware
- ✅ Manager pattern for multi-device support
- ✅ Comprehensive error handling
- ✅ Resource cleanup implemented

### Issues Identified

#### 1.1 Handler-Based Callbacks (Old Android Pattern)

**Current Implementation:**

```kotlin
private val handler = object : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> handleStateChange(msg.obj)
            ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET -> if (msg.obj is ObjectCluster) {
                handleDataPacket(msg.obj as ObjectCluster)
            }
            Shimmer.MESSAGE_TOAST -> {
                val text = msg.data?.getString(Shimmer.TOAST)
                // Handle toast
            }
            else -> super.handleMessage(msg)
        }
    }
}
```

**Issue:**

- Old Android pattern using Messages instead of modern coroutines
- Handler must run on Main thread (requires withContext switches)
- Callback-based instead of suspending functions

**Impact:** Medium - Code works but not idiomatic Kotlin

#### 1.2 Synchronous Blocking in Async Context

**Examples:**

```kotlin
// Multiple withContext(Dispatchers.Main) calls for Shimmer SDK
return withContext(Dispatchers.Main) {
    try {
        val manager = ensureManager()
        // Shimmer SDK requires Main thread
        manager.connectShimmerThroughBTAddress(mac, name, preferredBtType)
        DeviceCommandResult.Accepted
    } catch (t: Throwable) {
        DeviceCommandResult.Failed(t)
    }
}
```

**Issue:**

- Shimmer SDK requires Main thread for operations
- Forces async code to switch to Main dispatcher
- Can block UI if Shimmer operations are slow

**Impact:** Medium - Can cause UI jank

#### 1.3 Complex State Management

**Current State Variables:**

```kotlin
private var bluetoothManager: ShimmerBluetoothManagerAndroid? = null
private var shimmerDevice: ShimmerDevice? = null
private var targetMac: String? = null
private var streamingAnchor: RecordingSessionAnchor? = null
private var samplesSeen = 0L
private var lastSampleTimestamp: Instant? = null
private var streamEmitter: SensorStreamEmitter? = null
private var localWriter: BufferedWriter? = null
private var localDigest: MessageDigest? = null
private var localDigestStream: DigestOutputStream? = null
private var localFile: File? = null
private var localSessionId: String? = null
private var completedArtifact: SessionArtifact? = null
private var completedArtifactSessionId: String? = null
```

**Issue:**

- 14+ mutable state variables
- No explicit state machine
- State transitions implicit in code
- Difficult to reason about valid state combinations

**Impact:** High - Increases bug risk, reduces maintainability

#### 1.4 Java Compatibility Constraints

**Requirement:** Java 11-13 (documented in build files)

**Issue:**

- Newer Java features not available
- SDK built for older Android APIs
- May require workarounds for modern Kotlin features

**Impact:** Low - Manageable constraint

### Recommendations

#### R1.1 Wrap Shimmer SDK in Suspending Functions

**Priority:** Medium  
**Effort:** 2-3 days

**Proposed Wrapper:**

```kotlin
/**
 * Suspending wrapper for Shimmer SDK operations.
 * Handles thread requirements and converts callbacks to suspend functions.
 */
class ShimmerSdkWrapper(
    private val context: Context
) {
    private val handler = Handler(Looper.getMainLooper())
    
    /**
     * Connect to Shimmer device with suspend API.
     */
    suspend fun connect(mac: String, name: String): Result<ShimmerDevice> = 
        suspendCancellableCoroutine { continuation ->
            withContext(Dispatchers.Main) {
                val manager = ShimmerBluetoothManagerAndroid(context, handler)
                // Set up callback that resumes continuation
                // Handle timeout, cancellation
            }
        }
    
    /**
     * Start streaming with suspend API.
     */
    suspend fun startStreaming(device: ShimmerDevice): Result<Unit> =
        suspendCancellableCoroutine { continuation ->
            withContext(Dispatchers.Main) {
                // Start streaming, set up callback
                // Resume continuation on success/failure
            }
        }
}
```

**Benefits:**

- Modern Kotlin coroutine API
- Proper cancellation support
- Clear error handling
- Testable with mockito-kotlin

#### R1.2 Create Explicit State Machine

**Priority:** High  
**Effort:** 3-4 days

**Proposed State Machine:**

```kotlin
sealed class ShimmerConnectionState {
    object Disconnected : ShimmerConnectionState()
    object Connecting : ShimmerConnectionState()
    data class Connected(val device: ShimmerDevice, val since: Instant) : ShimmerConnectionState()
    data class Streaming(val device: ShimmerDevice, val anchor: RecordingSessionAnchor) : ShimmerConnectionState()
    data class Error(val error: Throwable, val previousState: ShimmerConnectionState) : ShimmerConnectionState()
}

sealed class RecordingState {
    object Idle : RecordingState()
    data class Recording(val writer: ShimmerDataWriter, val startedAt: Instant) : RecordingState()
    data class Finalizing(val file: File) : RecordingState()
}

class ShimmerStateMachine {
    private val _connectionState = MutableStateFlow<ShimmerConnectionState>(ShimmerConnectionState.Disconnected)
    val connectionState: StateFlow<ShimmerConnectionState> = _connectionState.asStateFlow()
    
    private val _recordingState = MutableStateFlow<RecordingState>(RecordingState.Idle)
    val recordingState: StateFlow<RecordingState> = _recordingState.asStateFlow()
    
    suspend fun transition(event: ShimmerEvent): Result<Unit> {
        return when (event) {
            is ShimmerEvent.Connect -> handleConnect(event)
            is ShimmerEvent.Disconnect -> handleDisconnect()
            is ShimmerEvent.StartStreaming -> handleStartStreaming(event)
            is ShimmerEvent.StopStreaming -> handleStopStreaming()
            is ShimmerEvent.Error -> handleError(event)
        }
    }
    
    private suspend fun handleConnect(event: ShimmerEvent.Connect): Result<Unit> {
        return when (val current = _connectionState.value) {
            is ShimmerConnectionState.Disconnected -> {
                _connectionState.value = ShimmerConnectionState.Connecting
                // Attempt connection
                // Update to Connected or Error
                Result.success(Unit)
            }
            else -> Result.failure(IllegalStateException("Cannot connect from state: $current"))
        }
    }
}
```

**Benefits:**

- Clear state transitions
- Invalid state transitions prevented at compile time
- Easier testing
- Better error messages

#### R1.3 Add Circuit Breaker for Connection Failures

**Priority:** Medium  
**Effort:** 1 day

**Proposed Implementation:**

```kotlin
class ShimmerCircuitBreaker(
    private val failureThreshold: Int = 5,
    private val resetTimeoutMs: Long = 60_000L
) {
    private var failureCount = 0
    private var lastFailureTime: Long = 0
    private var state = State.CLOSED
    
    enum class State {
        CLOSED,     // Normal operation
        OPEN,       // Too many failures, reject requests
        HALF_OPEN   // Testing if service recovered
    }
    
    suspend fun <T> execute(operation: suspend () -> T): Result<T> {
        return when (state) {
            State.OPEN -> {
                if (System.currentTimeMillis() - lastFailureTime > resetTimeoutMs) {
                    state = State.HALF_OPEN
                    executeWithRecovery(operation)
                } else {
                    Result.failure(CircuitBreakerOpenException("Too many connection failures"))
                }
            }
            State.HALF_OPEN -> executeWithRecovery(operation)
            State.CLOSED -> executeNormal(operation)
        }
    }
    
    private suspend fun <T> executeNormal(operation: suspend () -> T): Result<T> {
        return try {
            val result = operation()
            reset()
            Result.success(result)
        } catch (e: Exception) {
            recordFailure()
            Result.failure(e)
        }
    }
    
    private fun recordFailure() {
        failureCount++
        lastFailureTime = System.currentTimeMillis()
        if (failureCount >= failureThreshold) {
            state = State.OPEN
            Log.w("ShimmerCircuitBreaker", "Circuit opened after $failureCount failures")
        }
    }
}
```

**Benefits:**

- Prevents repeated connection attempts to failing device
- Automatic recovery after timeout
- Reduces battery drain from failed connections

#### R1.4 Document SDK Limitations and Workarounds

**Priority:** High  
**Effort:** 1 day

**Required Documentation:**

- Shimmer SDK version and source
- Main thread requirement for all operations
- Known bugs and workarounds
- Bluetooth permission requirements
- Device pairing requirements
- GSR range calibration details
- Sample rate limitations

## 2. Topdon SDK Integration

### Current Status

**Primary File:** `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonThermalConnector.kt` (630 lines)  
**External Code:** `external/original-topdon-app/` (14,817 files!)

### Issues Identified

#### 2.1 External App Code Included

**Current Structure:**

```
external/original-topdon-app/
├── app/              (Full Android app)
├── BleModule/        (Bluetooth module)
├── commonlibrary/    (Utilities)
├── component/        (UI components)
├── libapp/          (App library)
├── libcom/          (Communication)
├── libhik/          (Hikvision?)
├── libir/           (IR camera)
├── libir-demo/      (Demo code)
├── libmatrix/       (Matrix operations)
├── libmenu/         (Menu system)
├── libui/           (UI library)
└── ...
```

**File Count:** 14,817 files including:

- 2,678 XML files
- 2,105 .flat files (Android resources)
- 1,761 JSON files
- 1,747 DEX files (compiled code)
- 1,005 .class files
- 824 Java files
- 379 Kotlin files

**Issue:**

- Entire example app included instead of minimal SDK
- Build artifacts committed to repository (.dex, .class, build/)
- Deprecated code and unused modules
- Increases repository size significantly

**Impact:** High - Repository bloat, maintenance burden

#### 2.2 Chinese Comments and Strings

**Examples Found:**

```java
// 中文注释 (Chinese comments throughout)
String errorMessage = "连接失败"; // Connection failed
Log.d(TAG, "设备已断开"); // Device disconnected
```

**Issue:**

- Makes code difficult to maintain for non-Chinese speakers
- Mixed language codebase
- Internationalization issues

**Impact:** Medium - Maintenance difficulty

#### 2.3 Complex USB Initialization Sequence

**Current Implementation:**

```kotlin
private val listener = object : USBMonitor.OnDeviceConnectListener {
    override fun onAttach(device: UsbDevice) {
        // Request permission
    }
    
    override fun onGranted(usbDevice: UsbDevice, granted: Boolean) {
        // Permission result
    }
    
    override fun onConnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock, createNew: Boolean) {
        if (!createNew) return
        usbControlBlock = ctrlBlock
        openCamera(ctrlBlock)
    }
    
    override fun onDisconnect(device: UsbDevice, ctrlBlock: USBMonitor.UsbControlBlock) {
        // Handle disconnect
    }
    
    override fun onDettach(device: UsbDevice) {
        // Handle detach (typo in SDK)
    }
    
    override fun onCancel(device: UsbDevice) {
        // Permission cancelled
    }
}
```

**Issue:**

- 6 callbacks for USB lifecycle
- Async initialization with no timeout
- No retry logic
- Typo in SDK ("onDettach" should be "onDetach")

**Impact:** Medium - Complex state management

#### 2.4 Thermal Frame Format Conversions

**Current Issue:**

- Thermal data in proprietary binary format
- No documentation of frame structure
- Complex normalization required
- Temperature extraction unclear

**Impact:** High - Critical for thermal imaging

### Recommendations

#### R2.1 Extract Minimal SDK Wrapper

**Priority:** High  
**Effort:** 3-5 days

**Proposed Structure:**

```
app/src/main/java/com/buccancs/data/sensor/connector/topdon/
├── TopdonThermalConnector.kt           (Existing)
├── sdk/
│   ├── TopdonSdkWrapper.kt            (Minimal wrapper)
│   ├── ThermalFrameDecoder.kt          (Frame parsing)
│   ├── UsbCameraManager.kt             (USB lifecycle)
│   └── TopdonExceptions.kt             (Error types)
└── ThermalNormalizer.kt                (Existing)
```

**TopdonSdkWrapper:**

```kotlin
/**
 * Minimal wrapper for Topdon TC001 thermal camera SDK.
 * Wraps only the essential USB UVC functionality.
 */
class TopdonSdkWrapper(
    private val context: Context
) {
    private val usbManager = context.getSystemService(UsbManager::class.java)
    private var monitor: USBMonitor? = null
    
    /**
     * Initialize USB monitoring with suspend API.
     */
    suspend fun initialize(): Result<Unit> = suspendCancellableCoroutine { continuation ->
        try {
            val monitor = USBMonitor(context, createListener(continuation))
            monitor.register()
            this.monitor = monitor
            
            continuation.invokeOnCancellation {
                monitor.unregister()
                monitor.destroy()
            }
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }
    
    /**
     * Connect to thermal camera with timeout.
     */
    suspend fun connect(timeoutMs: Long = 5000): Result<UVCCamera> = withTimeout(timeoutMs) {
        // Connection logic
    }
}
```

**Benefits:**

- Minimal code footprint
- Clean suspend API
- Proper timeout handling
- Testable without hardware

#### R2.2 Remove Unused Topdon Example Code

**Priority:** High  
**Effort:** 1 day

**Action Plan:**

1. Identify actively used SDK files
2. Extract minimal required libraries
3. Remove example app code
4. Remove build artifacts
5. Update documentation

**Keep:**

- `libir/` - Core IR camera library
- USB UVC components
- Frame decoding utilities

**Remove:**

- Full example app
- UI components
- Demo code
- Build artifacts (.dex, .class, build/)
- Unused modules (libhik, libmatrix, etc.)

**Expected Reduction:** ~14,500 files → ~50-100 files

#### R2.3 Document Thermal Frame Format

**Priority:** High  
**Effort:** 2-3 days

**Required Documentation:**

```markdown
# Topdon TC001 Thermal Frame Format

## Frame Structure
- Resolution: 256x192 pixels
- Format: 16-bit little-endian per pixel
- Frame size: 98,304 bytes (256 * 192 * 2)

## Temperature Conversion
Raw Value → Temperature (Celsius)
```kotlin
fun rawToTemperature(raw: Int): Float {
    // Documented conversion formula
    return (raw / 64.0f) - 273.15f
}
```

## Normalization

- Min/max tracking for display
- Colormap application
- Spatial filtering options

## Performance

- Frame rate: ~25 FPS
- Latency: <50ms typical
- USB bandwidth: ~5 MB/s

```

#### R2.4 Add Thermal Camera Simulator

**Priority:** Medium  
**Effort:** 2-3 days

**Proposed Implementation:**
```kotlin
class SimulatedThermalCamera : ThermalCamera {
    private val random = Random()
    private var baseTemperature = 25.0f
    
    override suspend fun captureFrame(): ThermalFrame {
        // Generate realistic thermal data
        val width = 256
        val height = 192
        val data = ShortArray(width * height)
        
        for (y in 0 until height) {
            for (x in 0 until width) {
                // Simulate temperature variation
                val temp = baseTemperature + random.nextGaussian().toFloat() * 2.0f
                data[y * width + x] = temperatureToRaw(temp)
            }
        }
        
        return ThermalFrame(data, width, height, System.currentTimeMillis())
    }
    
    // Simulate hot spot
    fun addHotSpot(x: Int, y: Int, temperature: Float, radius: Int) {
        // Add localized heat source
    }
}
```

**Benefits:**

- Testing without hardware
- Reproducible test scenarios
- UI development without camera
- Automated testing

## 3. OpenCV Integration

### Current Status

**Usage:** Camera calibration only  
**Primary File:** `app/src/main/java/com/buccancs/data/calibration/DefaultCalibrationRepository.kt`  
**Library Size:** 50+ MB

### Integration Quality

**Strengths:**

- ✅ Used for specific task (calibration)
- ✅ Proper Mat lifecycle management (release() called)
- ✅ Results cached and reused
- ✅ Encapsulated in repository pattern

### Issues Identified

#### 3.1 Large Library for Limited Functionality

**Usage Analysis:**

```kotlin
// Only using these OpenCV functions:
- OpenCVLoader.initDebug()
- Utils.bitmapToMat()
- Imgproc.cvtColor()
- Imgproc.findChessboardCorners()
- Calib3d.stereoCalibrate()
- Mat lifecycle (release())
```

**Issue:**

- 50+ MB library for ~6 functions
- Includes unused computer vision algorithms
- Native libraries for multiple architectures
- Increases APK size significantly

**Impact:** Medium - APK bloat

#### 3.2 Native Library Loading Complexity

**Current Implementation:**

```kotlin
private suspend fun ensureOpenCvLoaded() {
    if (!openCvLoaded) {
        openCvLoaded = withContext(Dispatchers.Default) {
            OpenCVLoader.initDebug()
        }
        if (!openCvLoaded) {
            throw IllegalStateException("Failed to initialize OpenCV")
        }
    }
}
```

**Issue:**

- Native library loading can fail
- No fallback if loading fails
- Architecture-specific binaries
- Potential crashes on unsupported devices

**Impact:** Medium - Calibration unavailable on failure

#### 3.3 Calibration Quality Thresholds Not Enforced

**Current Code:**

```kotlin
val warning = if (metricsSnapshot.maxReprojectionError > 1.0) {
    "High reprojection error detected..."
} else {
    null
}
```

**Issue:**

- Warning shown but calibration still accepted
- No hard threshold for rejection
- Users can proceed with bad calibration
- No guidance on what's acceptable

**Impact:** High - Bad calibration affects all measurements

### Recommendations

#### R3.1 Document OpenCV Initialization Requirements

**Priority:** High  
**Effort:** 1 day

**Required Documentation:**

```markdown
# OpenCV Integration

## Version
- OpenCV Android SDK: 4.x
- Native library size: ~50 MB
- Architectures: armeabi-v7a, arm64-v8a, x86, x86_64

## Initialization
```kotlin
// Initialize before use
if (!OpenCVLoader.initDebug()) {
    // Fallback or error
}
```

## Usage

- Stereo camera calibration
- Chessboard corner detection
- Reprojection error calculation

## Limitations

- Requires Android 5.0+
- Native libraries must match device architecture
- First initialization may take 1-2 seconds

## Alternatives Considered

- Custom calibration implementation (complex)
- BoofCV (alternative CV library)
- Delegating calibration to desktop app

```

#### R3.2 Add Automated Quality Checks

**Priority:** High  
**Effort:** 2-3 days

**Proposed Implementation:**
```kotlin
data class CalibrationQualityCheck(
    val passed: Boolean,
    val issues: List<String>,
    val warnings: List<String>,
    val metrics: CalibrationMetrics
)

fun validateCalibrationQuality(result: StereoCalibrationResult): CalibrationQualityCheck {
    val issues = mutableListOf<String>()
    val warnings = mutableListOf<String>()
    
    // Hard thresholds (reject calibration)
    if (result.meanReprojectionError > 2.0) {
        issues.add("Mean reprojection error too high: ${result.meanReprojectionError} > 2.0 px")
    }
    
    if (result.maxReprojectionError > 5.0) {
        issues.add("Max reprojection error too high: ${result.maxReprojectionError} > 5.0 px")
    }
    
    // Soft thresholds (warn user)
    if (result.meanReprojectionError > 1.0) {
        warnings.add("Mean reprojection error elevated: ${result.meanReprojectionError} px (target: < 1.0 px)")
    }
    
    // Check for insufficient captures
    if (result.imageCount < 10) {
        warnings.add("Low capture count: ${result.imageCount} (recommended: 10+)")
    }
    
    return CalibrationQualityCheck(
        passed = issues.isEmpty(),
        issues = issues,
        warnings = warnings,
        metrics = result.metrics
    )
}
```

**Benefits:**

- Prevents bad calibrations
- Clear quality feedback
- Guidance for users
- Consistent quality standards

#### R3.3 Consider Alternative Libraries

**Priority:** Low  
**Effort:** Research phase

**Alternatives:**

1. **BoofCV** - Pure Java CV library
    - Pros: No native dependencies, smaller size
    - Cons: Less mature, fewer features

2. **Custom Implementation** - Write calibration from scratch
    - Pros: Full control, minimal size
    - Cons: Complex math, testing burden

3. **Desktop Calibration** - Move to desktop app
    - Pros: No mobile overhead, more powerful
    - Cons: Extra step for users

**Recommendation:** Keep OpenCV for now, revisit if APK size becomes critical issue.

#### R3.4 Add Fallback for OpenCV Load Failure

**Priority:** Medium  
**Effort:** 1 day

**Proposed Implementation:**

```kotlin
private suspend fun ensureOpenCvLoaded(): Result<Unit> {
    if (openCvLoaded) {
        return Result.success(Unit)
    }
    
    return withContext(Dispatchers.Default) {
        try {
            val loaded = OpenCVLoader.initDebug()
            if (loaded) {
                openCvLoaded = true
                Result.success(Unit)
            } else {
                Result.failure(OpenCvLoadException("OpenCV initialization returned false"))
            }
        } catch (e: Exception) {
            Log.e("Calibration", "OpenCV load failed", e)
            Result.failure(OpenCvLoadException("OpenCV failed to load: ${e.message}", e))
        }
    }
}

// In UI layer
when (val result = calibrationRepository.startCalibration()) {
    is Result.Success -> { /* Proceed */ }
    is Result.Failure -> {
        when (val error = result.error) {
            is OpenCvLoadException -> {
                // Show helpful error
                showError("""
                    Camera calibration unavailable.
                    
                    OpenCV library failed to load. This may be due to:
                    - Unsupported device architecture
                    - Corrupted app installation
                    - Insufficient storage
                    
                    Try:
                    - Reinstalling the app
                    - Updating to latest version
                    - Using desktop calibration tool
                """.trimIndent())
            }
            else -> showError("Calibration error: ${error.message}")
        }
    }
}
```

## Summary of Recommendations

### High Priority (Do First)

| Issue                         | SDK     | Priority | Effort   | Impact |
|-------------------------------|---------|----------|----------|--------|
| Create explicit state machine | Shimmer | High     | 3-4 days | High   |
| Extract minimal SDK wrapper   | Topdon  | High     | 3-5 days | High   |
| Remove unused Topdon code     | Topdon  | High     | 1 day    | High   |
| Document thermal frame format | Topdon  | High     | 2-3 days | High   |
| Enforce calibration quality   | OpenCV  | High     | 2-3 days | High   |
| Document SDK requirements     | All     | High     | 1 day    | Medium |

### Medium Priority (Do Soon)

| Issue                        | SDK     | Priority | Effort   | Impact |
|------------------------------|---------|----------|----------|--------|
| Wrap in suspending functions | Shimmer | Medium   | 2-3 days | Medium |
| Circuit breaker pattern      | Shimmer | Medium   | 1 day    | Medium |
| Thermal camera simulator     | Topdon  | Medium   | 2-3 days | Medium |
| OpenCV fallback handling     | OpenCV  | Medium   | 1 day    | Medium |

### Low Priority (Future)

| Issue                  | SDK    | Priority | Effort   | Impact |
|------------------------|--------|----------|----------|--------|
| Alternative CV library | OpenCV | Low      | Research | Low    |

## Testing Recommendations

### Shimmer SDK Testing

1. Connection state machine tests
2. Circuit breaker behavior tests
3. Handler cleanup verification
4. Multi-device connection tests
5. Bluetooth permission scenarios

### Topdon SDK Testing

1. USB connection lifecycle tests
2. Frame parsing correctness tests
3. Thermal simulator accuracy tests
4. Permission handling tests
5. Reconnection scenarios

### OpenCV Testing

1. Calibration quality threshold tests
2. Load failure scenarios
3. Mat lifecycle leak detection
4. Different calibration patterns
5. Edge case handling (few captures, poor images)

## Migration Path

### Phase 1: Documentation (1 week)

- Document all SDK requirements
- Document thermal frame format
- Document calibration quality standards
- Add inline code documentation

### Phase 2: Shimmer Improvements (2-3 weeks)

- Implement state machine
- Add circuit breaker
- Wrap in suspending functions
- Comprehensive testing

### Phase 3: Topdon Cleanup (2 weeks)

- Extract minimal SDK
- Remove unused code
- Add thermal simulator
- Documentation

### Phase 4: OpenCV Improvements (1 week)

- Enforce quality thresholds
- Add fallback handling
- Improve error messages
- Testing

## Conclusion

All three SDK integrations are functional but have room for improvement. Priority should be:

1. **High Priority:** State management, code cleanup, quality enforcement
2. **Medium Priority:** Modern API wrappers, simulators, fallbacks
3. **Low Priority:** Alternative libraries (only if needed)

Current integrations work but can be more robust, maintainable, and testable with these improvements.
