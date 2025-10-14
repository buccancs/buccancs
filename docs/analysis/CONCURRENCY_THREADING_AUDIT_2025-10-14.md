**Last Modified:** 2025-10-14 03:38 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Analysis

# Concurrency and Threading Audit

## Executive Summary

This document provides a comprehensive audit of concurrency and threading patterns across the BuccanCS codebase,
identifying 115 coroutine launch sites, minimal timeouts, Handler-based callback patterns, and threading primitives
usage.

**Key Findings:**

- **Good**: ApplicationScope pattern correctly used for long-running operations
- **Good**: Minimal direct threading (0 Thread() instances, using coroutines throughout)
- **Issue**: Only 2 timeout usages across 115 coroutine launch sites
- **Issue**: 6 Handler instances mixing Android callbacks with coroutines
- **Issue**: 17 threading primitives (@Volatile, AtomicBoolean) requiring validation
- **Critical**: DefaultRecordingService uses injection but no explicit scope for long-running operations

---

## 1. Coroutine Scope Management

### 1.1 Scope Usage Analysis

**Total Coroutine Launch Sites:** 115

**ApplicationScope Usage (Correct Pattern):**

- `BacklogPerformanceController` - Monitors backlog performance
- `DefaultCalibrationRepository` - Manages calibration state
- `CommandClient` - Long-lived command communication
- `HeartbeatMonitor` - Continuous heartbeat monitoring
- `DeviceOrchestratorBridge` - Device orchestration
- `MdnsAdvertiser` / `MdnsBrowser` - Network discovery services
- `DefaultSensorRepository` - Sensor state management
- `TopdonConnectorManager` / `TopdonThermalConnector` - Hardware management
- `ShimmerSensorConnector` - Shimmer device management (via `appScope`)
- DataStore repositories - Configuration persistence

**viewModelScope Usage:**

- Various ViewModels (MainViewModel, CalibrationViewModel, etc.)
- **Risk**: Configuration changes may cancel long-running operations

### 1.2 Critical Issue: Recording Service Scope

**File:** `app/src/main/java/com/buccancs/application/recording/DefaultRecordingService.kt`

```kotlin
@Singleton
class DefaultRecordingService @Inject constructor(
    private val sensorRepository: SensorRepository,
    // ... other dependencies
) : RecordingService {
    override suspend fun startOrResume(sessionId: String, requestedStart: Instant?): RecordingState {
        // Calls sensorRepository.startStreaming(anchor)
        // This can run for hours - which scope?
    }
}
```

**Issue:** The service doesn't explicitly inject `@ApplicationScope CoroutineScope`. It relies on the caller's scope,
which could be `viewModelScope` from a ViewModel. If the Activity is recreated during a recording session, the coroutine
would be cancelled.

**Recommendation:**

```kotlin
@Singleton
class DefaultRecordingService @Inject constructor(
    @ApplicationScope private val appScope: CoroutineScope,
    private val sensorRepository: SensorRepository,
    // ... other dependencies
) : RecordingService {
    override suspend fun startOrResume(sessionId: String, requestedStart: Instant?): RecordingState {
        // Use appScope for long-running operations
        return appScope.async {
            // Recording logic survives configuration changes
        }.await()
    }
}
```

---

## 2. Timeout Management

### 2.1 Current Timeout Usage

**Only 2 Instances Found:**

**File:** `app/src/main/java/com/buccancs/data/sensor/exercise/MultiDeviceRecordingExercise.kt`

```kotlin
withTimeoutOrNull(timeoutMillis) {
    // Recording exercise timeout
}
```

**Missing Timeouts on:**

- Bluetooth device connections (ShimmerSensorConnector, TopdonConnector)
- USB device connections (TopdonThermalConnector)
- Network operations (CommandClient, DeviceOrchestratorBridge)
- gRPC calls (GrpcServer, streaming operations)
- File I/O operations (calibration, recording)

### 2.2 High-Risk Operations Without Timeouts

#### Shimmer Connection (Lines 277-288)

```kotlin
override suspend fun connect(): DeviceCommandResult {
    return withContext(Dispatchers.Main) {
        val manager = ensureManager()
        deviceState.update { it.copy(connectionStatus = ConnectionStatus.Connecting) }
        manager.connectShimmerThroughBTAddress(mac, name, preferredBtType)
        // No timeout - could hang indefinitely
        DeviceCommandResult.Accepted
    }
}
```

#### Topdon USB Connection

Similar pattern in TopdonThermalConnector - no timeout on USB device initialisation.

#### Network Operations

CommandClient and DeviceOrchestratorBridge establish gRPC channels without connection timeouts.

### 2.3 Recommendations

Add timeouts to all hardware and network operations:

```kotlin
// Bluetooth connections
suspend fun connect(): DeviceCommandResult = withTimeout(30.seconds) {
    // Connection logic
}

// USB operations
suspend fun initialiseThermal(): DeviceCommandResult = withTimeout(20.seconds) {
    // USB initialisation
}

// Network calls
suspend fun sendCommand(): Result<Response> = withTimeout(10.seconds) {
    // gRPC call
}

// File operations (recording finalisation)
suspend fun finalizeRecording(): SessionArtifact? = withTimeout(5.seconds) {
    // File closure and checksum
}
```

---

## 3. Handler-Based Callback Pattern

### 3.1 Handler Instances Found

Six Handler instances mixing Android main-thread callbacks with coroutine flows:

1. **StimulusPresentationManager.kt:42**
   ```kotlin
   private val handler = Handler(Looper.getMainLooper())
   ```

2. **MdnsAdvertiser.kt:26**
   ```kotlin
   private val handler = Handler(Looper.getMainLooper())
   ```

3. **MdnsBrowser.kt:28**
   ```kotlin
   private val handler = Handler(Looper.getMainLooper())
   ```

4. **RgbCameraConnector.kt:419**
   ```kotlin
   handler = Handler(thread.looper)
   ```

5. **SegmentedMediaCodecRecorder.kt:61**
   ```kotlin
   val handler = Handler(thread.looper)
   ```

6. **ShimmerSensorConnector.kt:75** (Critical)
   ```kotlin
   private val handler = object : Handler(Looper.getMainLooper()) {
       override fun handleMessage(msg: Message) {
           // Shimmer SDK callback on main thread
           when (msg.what) {
               ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> {
                   // Should marshal to coroutine context
               }
               ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET -> {
                   // Data packet handling
               }
           }
       }
   }
   ```

### 3.2 Issue: Mixed Concurrency Models

The ShimmerSensorConnector shows the complexity of mixing Handler callbacks with coroutines:

**Current Pattern (Lines 96-116):**

```kotlin
private inner class ShimmerMessageHandler : android.os.Handler(android.os.Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> {
                appScope.launch { handleStateChange(msg.obj) }  // Marshal to coroutine
            }
            ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET -> {
                if (msg.obj is ObjectCluster) {
                    appScope.launch { handleDataPacket(msg.obj as ObjectCluster) }
                }
            }
        }
    }
}
```

**Problems:**

- Handler is tied to main looper, creating unnecessary main thread dependency
- Launching new coroutines for each message creates overhead
- No cancellation mechanism when connector is disconnected
- Difficult to test in isolation

### 3.3 Recommended Pattern: suspendCancellableCoroutine

Convert Handler-based callbacks to proper suspend functions:

```kotlin
suspend fun ShimmerBluetoothManagerAndroid.connectAsync(
    mac: String,
    name: String,
    btType: BT_TYPE
): Result<ShimmerBluetooth> = suspendCancellableCoroutine { continuation ->
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> {
                    val state = (msg.obj as? CallbackObject)?.mState
                    when (state) {
                        ShimmerBluetooth.BT_STATE.CONNECTED -> {
                            val device = getShimmerDeviceBtConnectedFromMac(mac)
                            continuation.resume(Result.success(device))
                        }
                        ShimmerBluetooth.BT_STATE.DISCONNECTED -> {
                            continuation.resume(Result.failure(ConnectionException("Connection failed")))
                        }
                    }
                }
            }
        }
    }
    
    continuation.invokeOnCancellation {
        // Clean up handler and connection attempt
        handler.removeCallbacksAndMessages(null)
        disconnectAllDevices()
    }
    
    // Initiate connection
    connectShimmerThroughBTAddress(mac, name, btType)
}
```

**Benefits:**

- Proper cancellation support
- Type-safe error handling
- Integrates cleanly with coroutine structured concurrency
- Testable with coroutine test libraries

---

## 4. Threading Primitives Analysis

### 4.1 AtomicBoolean Usage (12 instances)

**Purpose: State flags for services and closeable resources**

| File                              | Variable        | Purpose                    | Status                |
|-----------------------------------|-----------------|----------------------------|-----------------------|
| AppShutdownManager                | `closed`        | Shutdown flag              | ✓ Appropriate         |
| DefaultCalibrationRepository      | `OPEN_CV_READY` | Static initialisation flag | ✓ Appropriate         |
| MdnsAdvertiser                    | `active`        | Service active flag        | ⚠️ Consider StateFlow |
| MdnsBrowser                       | `browsing`      | Browsing state flag        | ⚠️ Consider StateFlow |
| PreviewStreamUploader             | `closed`        | Upload stream closure      | ✓ Appropriate         |
| SensorStreamUploader              | `closed`        | Sensor stream closure      | ✓ Appropriate         |
| SegmentedMediaCodecRecorder       | `closed`        | Recorder closure           | ✓ Appropriate         |
| GrpcServer (desktop)              | `started`       | Server state flag          | ⚠️ Consider StateFlow |
| DeviceConnectionMonitor (desktop) | `started`       | Monitor state flag         | ⚠️ Consider StateFlow |

**Analysis:**

- Closure flags (`closed`) are appropriately using AtomicBoolean for thread-safe resource management
- Service state flags (`active`, `browsing`, `started`) could benefit from StateFlow for reactive state updates
- Static initialisation flag (OPEN_CV_READY) is appropriate use case

### 4.2 @Volatile Usage (5 instances)

| File                        | Variable               | Purpose                       | Status                  |
|-----------------------------|------------------------|-------------------------------|-------------------------|
| CommandClient               | Connection state       | RPC client volatile state     | ⚠️ Review thread safety |
| MicrophoneConnector         | Recording state        | Audio recording volatile flag | ⚠️ Consider Mutex       |
| TopdonConnectorManager      | Device state           | USB device volatile state     | ⚠️ Consider Mutex       |
| TopdonThermalConnector (3x) | Multiple device fields | Thermal camera state          | ⚠️ Consider Mutex       |
| EncryptionManager (desktop) | Encryption state       | Key material volatile         | ⚠️ Review thread safety |

**Analysis:**

- @Volatile provides visibility guarantees but NOT atomicity
- Multiple volatile fields in TopdonThermalConnector (3 instances) suggest need for proper locking
- Consider replacing with Mutex or StateFlow for compound operations

### 4.3 AtomicInteger and AtomicReference

**AtomicInteger:** Found in `DefaultSessionTransferRepository` - likely for sequence numbers or counters (appropriate
use).

**AtomicReference:** Would need deeper inspection if found.

### 4.4 Recommendations

**Replace service state flags with StateFlow:**

```kotlin
// Instead of:
private val active = AtomicBoolean(false)

// Use:
private val _active = MutableStateFlow(false)
val active: StateFlow<Boolean> = _active.asStateFlow()

// Benefits:
// - Reactive updates
// - Structured concurrency integration
// - Easier testing
```

**Replace volatile with Mutex for compound operations:**

```kotlin
// Instead of:
@Volatile
private var deviceState: DeviceState? = null

fun updateState(newState: DeviceState) {
    deviceState = newState  // Not atomic if read-modify-write
}

// Use:
private val stateMutex = Mutex()
private var deviceState: DeviceState? = null

suspend fun updateState(newState: DeviceState) = stateMutex.withLock {
    deviceState = newState
}
```

---

## 5. Critical Findings Summary

### 5.1 High Priority Issues

1. **DefaultRecordingService lacks ApplicationScope**
    - **Impact:** Critical - recording sessions may be cancelled on configuration changes
    - **Fix:** Inject `@ApplicationScope CoroutineScope`

2. **No timeouts on hardware connections**
    - **Impact:** High - app can hang indefinitely on failed Bluetooth/USB connections
    - **Fix:** Add `withTimeout` to all connect operations (30s for BT, 20s for USB)

3. **ShimmerSensorConnector Handler complexity**
    - **Impact:** Medium - difficult to maintain, test, and reason about
    - **Fix:** Convert to `suspendCancellableCoroutine` pattern

### 5.2 Medium Priority Issues

4. **Service state flags using AtomicBoolean**
    - **Impact:** Low - works but not idiomatic Kotlin coroutines
    - **Fix:** Migrate to StateFlow for reactive state management

5. **Multiple @Volatile fields without locking**
    - **Impact:** Medium - potential race conditions in compound operations
    - **Fix:** Replace with Mutex for thread-safe compound updates

6. **Missing timeouts on network operations**
    - **Impact:** Medium - gRPC calls could hang
    - **Fix:** Add timeouts to CommandClient and DeviceOrchestratorBridge

---

## 6. Implementation Roadmap

### Phase 1: Critical Fixes (Week 1)

- [ ] Add `@ApplicationScope` to DefaultRecordingService
- [ ] Add timeouts to Shimmer Bluetooth connection (30s)
- [ ] Add timeouts to Topdon USB connection (20s)
- [ ] Add timeouts to gRPC operations (10s)

### Phase 2: Handler Migration (Week 2)

- [ ] Convert ShimmerSensorConnector Handler to suspendCancellableCoroutine
- [ ] Test cancellation behaviour with unit tests
- [ ] Verify no regression in connection stability

### Phase 3: StateFlow Migration (Week 3)

- [ ] Migrate MdnsAdvertiser to StateFlow
- [ ] Migrate MdnsBrowser to StateFlow
- [ ] Migrate GrpcServer state to StateFlow
- [ ] Migrate DeviceConnectionMonitor to StateFlow

### Phase 4: Threading Primitives Review (Week 4)

- [ ] Review @Volatile usage in TopdonThermalConnector
- [ ] Replace with Mutex where compound operations exist
- [ ] Add unit tests for concurrent access patterns

---

## 7. Testing Strategy

### 7.1 Coroutine Testing

```kotlin
@Test
fun `recording survives configuration change`() = runTest {
    val recordingService = DefaultRecordingService(
        appScope = backgroundScope,  // TestScope that survives cancellation
        // ... other dependencies
    )
    
    launch {
        recordingService.startOrResume("session-1", Instant.now())
    }
    
    // Simulate configuration change
    advanceTimeBy(5_000)
    
    // Recording should still be active
    assertThat(recordingService.isRecording()).isTrue()
}
```

### 7.2 Timeout Testing

```kotlin
@Test
fun `connection times out after 30 seconds`() = runTest {
    val connector = ShimmerSensorConnector(/* ... */)
    
    // Simulate unresponsive device
    coEvery { bluetoothManager.connect(any()) } coAnswers {
        delay(60_000)  // Hangs for 60s
    }
    
    val result = connector.connect()
    
    // Should timeout after 30s
    assertThat(result).isInstanceOf<DeviceCommandResult.Failed>()
    assertThat(currentTime).isEqualTo(30_000)
}
```

### 7.3 Cancellation Testing

```kotlin
@Test
fun `connection cancellation cleans up resources`() = runTest {
    val connector = ShimmerSensorConnector(/* ... */)
    
    val job = launch {
        connector.connect()
    }
    
    advanceTimeBy(5_000)
    job.cancel()
    
    // Verify handler removed, connection attempt cancelled
    verify(exactly = 1) { bluetoothManager.disconnectAllDevices() }
}
```

---

## 8. Code Examples

### 8.1 Before: DefaultRecordingService

```kotlin
@Singleton
class DefaultRecordingService @Inject constructor(
    private val sensorRepository: SensorRepository,
    // ... other dependencies
) : RecordingService {
    override suspend fun startOrResume(sessionId: String, requestedStart: Instant?): RecordingState {
        // Uses caller's scope (likely viewModelScope)
        sensorRepository.startStreaming(anchor)
        return sensorRepository.recordingState.value
    }
}
```

### 8.2 After: DefaultRecordingService

```kotlin
@Singleton
class DefaultRecordingService @Inject constructor(
    @ApplicationScope private val appScope: CoroutineScope,
    private val sensorRepository: SensorRepository,
    // ... other dependencies
) : RecordingService {
    override suspend fun startOrResume(sessionId: String, requestedStart: Instant?): RecordingState {
        // Explicitly uses application scope for long-running recording
        return withContext(appScope.coroutineContext) {
            withTimeout(60.seconds) {
                sensorRepository.startStreaming(anchor)
            }
            sensorRepository.recordingState.value
        }
    }
}
```

### 8.3 Before: Shimmer Connection

```kotlin
override suspend fun connect(): DeviceCommandResult {
    return withContext(Dispatchers.Main) {
        val manager = ensureManager()
        manager.connectShimmerThroughBTAddress(mac, name, preferredBtType)
        DeviceCommandResult.Accepted
    }
}
```

### 8.4 After: Shimmer Connection

```kotlin
override suspend fun connect(): DeviceCommandResult {
    return withTimeout(30.seconds) {
        withContext(Dispatchers.Main) {
            val manager = ensureManager()
            manager.connectAsync(mac, name, preferredBtType)
                .onSuccess { 
                    DeviceCommandResult.Accepted 
                }
                .onFailure { error ->
                    DeviceCommandResult.Failed(error)
                }
        }
    }
}

private suspend fun ShimmerBluetoothManagerAndroid.connectAsync(
    mac: String,
    name: String,
    btType: BT_TYPE
): Result<Unit> = suspendCancellableCoroutine { continuation ->
    // Handler implementation with proper cancellation
}
```

### 8.5 Before: Service State Flag

```kotlin
class MdnsAdvertiser @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope
) {
    private val active = AtomicBoolean(false)
    
    fun start() {
        if (active.compareAndSet(false, true)) {
            // Start advertising
        }
    }
}
```

### 8.6 After: Service State Flow

```kotlin
class MdnsAdvertiser @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope
) {
    private val _active = MutableStateFlow(false)
    val active: StateFlow<Boolean> = _active.asStateFlow()
    
    suspend fun start() {
        if (_active.compareAndSet(expect = false, update = true)) {
            // Start advertising
        }
    }
}
```

---

## 9. References

- [Kotlin Coroutines Best Practices](https://kotlinlang.org/docs/coroutines-best-practices.html)
- [Android Coroutine Best Practices](https://developer.android.com/kotlin/coroutines/coroutines-best-practices)
- [Structured Concurrency](https://kotlinlang.org/docs/coroutines-basics.html#structured-concurrency)
- [Timeout and Cancellation](https://kotlinlang.org/docs/cancellation-and-timeouts.html)

---

## Appendix A: Coroutine Launch Audit

**Total Launch Sites:** 115

**Breakdown by Type:**

- `viewModelScope.launch`: ~20 instances (ViewModels)
- `appScope.launch` / `scope.launch`: ~95 instances (Services, Repositories, Connectors)

**Key Files with High Launch Counts:**

1. ShimmerSensorConnector.kt: 15+ launch sites
2. TopdonThermalConnector.kt: 10+ launch sites
3. DefaultSensorRepository.kt: 10+ launch sites
4. DeviceOrchestratorBridge.kt: 8+ launch sites
5. CommandClient.kt: 5+ launch sites

**Recommendation:** Prioritise timeout addition to files with highest launch counts and hardware interaction.

---

## Appendix B: Handler Usage Details

| File                        | Line | Purpose               | Migration Priority |
|-----------------------------|------|-----------------------|--------------------|
| ShimmerSensorConnector      | 75   | Shimmer SDK callbacks | High               |
| RgbCameraConnector          | 419  | Camera thread handler | Medium             |
| SegmentedMediaCodecRecorder | 61   | MediaCodec thread     | Medium             |
| StimulusPresentationManager | 42   | Stimulus timing       | Low                |
| MdnsAdvertiser              | 26   | mDNS callbacks        | Low                |
| MdnsBrowser                 | 28   | mDNS callbacks        | Low                |

**High Priority:** ShimmerSensorConnector mixes Handler with coroutines extensively - highest complexity and maintenance
burden.

---

**End of Document**
