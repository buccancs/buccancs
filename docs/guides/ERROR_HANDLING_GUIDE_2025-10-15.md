**Last Modified:** 2025-10-15 20:52 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Guide

# Error Handling Guide

Comprehensive guide to error handling in BuccanCS using the Result pattern.

## Contents

- [Overview](#overview)
- [Migration Guide](#migration-guide)
- [Refactoring Examples](#refactoring-examples)
- [Testing Guide](#testing-guide)
- [Quick Reference](#quick-reference)

## Overview

BuccanCS uses the Result pattern for type-safe error handling. This replaces exception-based error handling with
explicit Result types that force callers to handle errors.

### Benefits

- Type-safe error categories
- Explicit error handling
- Testable without throwing exceptions
- Preserves cancellation semantics
- Better error recovery patterns

### Architecture

See `docs/architecture/ERROR_HANDLING_STRATEGY_2025-10-14.md` for detailed architecture.

## Migration Guide

### Basic Pattern

#### Before (Anti-Pattern)

```kotlin
suspend fun connectDevice(deviceId: String): Connection? {
    return try {
        val device = bluetoothAdapter.getRemoteDevice(deviceId)
        val socket = device.createRfcommSocketToServiceRecord(uuid)
        socket.connect()
        Connection(socket)
    } catch (ex: IOException) {
        Log.e(TAG, "Connection failed", ex)
        null  // Silent failure
    } catch (ex: SecurityException) {
        Log.e(TAG, "Permission denied", ex)
        null  // Lost error context
    }
}

// Caller doesn't know why it failed
val connection = connectDevice(id)
if (connection != null) {
    // success
} else {
    // failure - but why?
}
```

#### After (Result Pattern)

```kotlin
suspend fun connectDevice(deviceId: String): Result<Connection> = resultOf {
    val device = bluetoothAdapter.getRemoteDevice(deviceId)
        ?: throw IllegalArgumentException("Invalid device ID")
    
    val socket = device.createRfcommSocketToServiceRecord(uuid)
    socket.connect()
    Connection(socket)
}.recoverWith { error ->
    when (error) {
        is Error.Network -> Result.Failure(
            Error.Bluetooth(deviceId, "Failed to connect: ${error.message}", error.cause)
        )
        is Error.Permission -> Result.Failure(
            Error.Permission("Bluetooth permission required", "BLUETOOTH_CONNECT", error.cause)
        )
        else -> Result.Failure(error)
    }
}

// Caller must handle all cases
when (val result = connectDevice(id)) {
    is Result.Success -> startStreaming(result.value)
    is Result.Failure -> when (result.error) {
        is Error.Bluetooth -> showBluetoothError(result.error)
        is Error.Permission -> requestPermission(result.error)
        else -> showGenericError(result.error)
    }
}
```

### Common Patterns

#### Storage Operations

```kotlin
fun saveData(sessionId: String, data: ByteArray): Result<Uri> = resultOf {
    require(sessionId.isNotBlank()) { "Session ID cannot be blank" }
    require(data.isNotEmpty()) { "Data cannot be empty" }
    
    val file = File(storageDir, "$sessionId.dat")
    file.parentFile?.mkdirs()
    file.writeBytes(data)
    Uri.fromFile(file)
}.recoverWith { error ->
    when (error) {
        is Error.Validation -> Result.Failure(error)
        else -> Result.Failure(Error.Storage("Failed to save data: ${error.message}", error.cause))
    }
}
```

#### Cancellation Handling

```kotlin
suspend fun processData(data: List<Item>): Result<Unit> = resultOf {
    data.forEach { item ->
        delay(100)  // CancellationException will be rethrown
        processItem(item)
    }
}

// Usage
launch {
    processData(items)
        .onSuccess { notifyComplete() }
        .onFailure { error -> notifyError(error) }
    // CancellationException propagates correctly
}
```

#### Chaining Operations

```kotlin
suspend fun initialiseSession(config: Config): Result<Session> =
    connectDevice(config.deviceId)
        .flatMap { device -> loadCalibration(device).map { device to it } }
        .flatMap { (device, calibration) -> createSession(device, calibration) }
```

#### Converting Nullable to Result

```kotlin
fun findDevice(id: String): Result<Device> =
    deviceCache[id].toResult("Device $id not found in cache")
```

### Migration Checklist

- [ ] Identify current error handling mechanism
- [ ] Determine appropriate Result return type
- [ ] Choose correct Error category
- [ ] Replace try-catch with resultOf
- [ ] Ensure CancellationException is preserved
- [ ] Add error recovery if needed
- [ ] Update call sites
- [ ] Update tests
- [ ] Document error conditions

### Common Pitfalls

#### Don't catch CancellationException

```kotlin
// BAD
try {
    suspendingOperation()
} catch (ex: Exception) {
    // Catches CancellationException
}

// GOOD
resultOf {
    suspendingOperation()
}
// CancellationException automatically rethrown
```

#### Don't lose error context

```kotlin
// BAD
Result.Failure(Error.Unknown("Failed"))

// GOOD
Result.Failure(Error.Hardware(
    deviceId = device.id,
    message = "Connection timeout after 30s",
    cause = originalException
))
```

#### Don't ignore errors

```kotlin
// BAD
operation().getOrNull()  // Silent failure

// GOOD
operation()
    .onSuccess { /* handle */ }
    .onFailure { /* handle */ }
```

## Refactoring Examples

Real examples from BuccanCS codebase.

### Example 1: ShimmerSensorConnector.connect()

#### Current Implementation

```kotlin
override suspend fun connect(): DeviceCommandResult {
    if (isSimulationMode) {
        return super.connect()
    }
    val adapter = bluetoothAdapter 
        ?: return DeviceCommandResult.Rejected("Bluetooth not available on this device.")
    if (!adapter.isEnabled) {
        return DeviceCommandResult.Rejected("Bluetooth is disabled.")
    }
    val desiredMac = currentSettings.targetMacAddress?.normalizeMac()
    val mac = desiredMac
        ?: discoveredCandidates.firstOrNull()?.mac
        ?: deviceState.value.attributes[ATTR_LAST_BONDED_MAC]?.normalizeMac()
        ?: bondedShimmerDevices(adapter).firstOrNull()?.address?.normalizeMac()
        ?: return DeviceCommandResult.Rejected("No Shimmer device available. Use Scan to discover devices.")
    shimmerSettingsRepository.setTargetMac(mac)
    targetMac = mac
    updateDeviceMetadata()
    val name = discoveredCandidates.firstOrNull { it.mac == mac }?.name
        ?: deviceState.value.attributes[ATTR_LAST_BONDED_NAME]
        ?: deviceState.value.displayName
    return withContext(Dispatchers.Main) {
        try {
            val manager = ensureManager()
            deviceState.update { it.copy(connectionStatus = ConnectionStatus.Connecting, isSimulated = false) }
            manager.connectShimmerThroughBTAddress(mac, name, preferredBtType)
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to initiate Shimmer connection", t)
            deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
            DeviceCommandResult.Failed(t)
        }
    }
}
```

#### Problems

1. Silent failure with generic catch
2. Lost error context (can't distinguish permission vs I/O errors)
3. No structured error recovery
4. Logging only, no propagation to UI layer with details

#### Refactored with Result Pattern

```kotlin
import com.buccancs.core.result.*

override suspend fun connect(): DeviceCommandResult {
    if (isSimulationMode) {
        return super.connect()
    }
    
    return connectHardware()
        .map { DeviceCommandResult.Accepted }
        .recoverWith { error ->
            when (error) {
                is Error.Bluetooth -> Result.Success(DeviceCommandResult.Rejected(error.message))
                is Error.Permission -> Result.Success(DeviceCommandResult.Rejected("Bluetooth permission required. Please grant BLUETOOTH_CONNECT permission."))
                is Error.NotFound -> Result.Success(DeviceCommandResult.Rejected(error.message))
                else -> Result.Success(DeviceCommandResult.Failed(error.toException()))
            }
        }
        .getOrThrow()
}

private suspend fun connectHardware(): Result<Unit> {
    // Step 1: Validate adapter
    val adapter = bluetoothAdapter.checkAvailable()
        .getOrElse { error -> return Result.Failure(error) }
    
    // Step 2: Find target device
    val mac = findTargetMac(adapter)
        .getOrElse { error -> return Result.Failure(error) }
    
    // Step 3: Update settings
    shimmerSettingsRepository.setTargetMac(mac)
    targetMac = mac
    updateDeviceMetadata()
    
    // Step 4: Initiate connection
    val name = discoveredCandidates.firstOrNull { it.mac == mac }?.name
        ?: deviceState.value.attributes[ATTR_LAST_BONDED_NAME]
        ?: deviceState.value.displayName
    
    return withContext(Dispatchers.Main) {
        bluetoothOperation(mac) {
            val manager = ensureManager()
            deviceState.update { 
                it.copy(connectionStatus = ConnectionStatus.Connecting, isSimulated = false) 
            }
            manager.connectShimmerThroughBTAddress(mac, name, preferredBtType)
        }.onFailure { error ->
            Log.e(logTag, "Bluetooth connection failed: ${error.message}", error.cause)
            deviceState.update { 
                it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) 
            }
        }
    }
}

private fun findTargetMac(adapter: BluetoothAdapter): Result<String> {
    val desiredMac = currentSettings.targetMacAddress?.normalizeMac()
    val mac = desiredMac
        ?: discoveredCandidates.firstOrNull()?.mac
        ?: deviceState.value.attributes[ATTR_LAST_BONDED_MAC]?.normalizeMac()
        ?: bondedShimmerDevices(adapter).firstOrNull()?.address?.normalizeMac()
    
    return mac?.toResult("No Shimmer device available. Use Scan to discover devices.")
        ?: Result.Failure(Error.NotFound("No Shimmer device available"))
}
```

#### Benefits

- Type-safe error categories
- Explicit error recovery per category
- Better logging with structured error information
- Testable without throwing exceptions
- Clear separation of concerns

### Example 2: SegmentedMediaCodecRecorder

#### Refactored Pattern

```kotlin
import com.buccancs.core.result.*

override fun onOutputBufferAvailable(codec: MediaCodec, index: Int, info: MediaCodec.BufferInfo) {
    val buffer = codec.getOutputBuffer(index)
    if (buffer == null) {
        codec.releaseOutputBuffer(index, false)
        return
    }
    
    val drainStartNs = System.nanoTime()
    val result = processBuffer(codec, index, buffer, info, drainStartNs)
    
    result.onFailure { error ->
        Log.e(logTag, "Buffer processing failed: ${error.message}", error.cause)
        handleError(error)
    }
    
    codec.releaseOutputBuffer(index, false)
    
    if (info.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) {
        finalizeAndComplete()
    }
}

private fun processBuffer(
    codec: MediaCodec,
    index: Int,
    buffer: ByteBuffer,
    info: MediaCodec.BufferInfo,
    drainStartNs: Long
): Result<Unit> = codecOperation {
    if (info.flags and MediaCodec.BUFFER_FLAG_CODEC_CONFIG != 0) {
        return@codecOperation
    }
    
    if (info.size <= 0) {
        return@codecOperation
    }
    
    ensureSegmentReady(info)
    buffer.position(info.offset)
    buffer.limit(info.offset + info.size)
    
    currentMuxer?.writeSampleData(currentTrackIndex, buffer, info)
        ?: throw IllegalStateException("Muxer not ready")
    
    val epochMs = anchorEpochMs + info.presentationTimeUs / 1_000
    val latencyNs = System.nanoTime() - drainStartNs
    
    currentCollector?.let { collector ->
        if (currentSegmentStartUs < 0) {
            currentSegmentStartUs = info.presentationTimeUs
            collector.markStart(epochMs)
        }
        collector.recordBuffer(info, info.size, latencyNs)
        latestEpochMs = epochMs
    }
    
    if (shouldRotate(info)) {
        rotateSegment(info)
    }
}

override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
    val error = Error.Codec("Codec error: ${e.diagnosticInfo}", e)
    Log.e(logTag, error.message, e)
    handleError(error)
}

private fun handleError(error: Error) {
    when (error) {
        is Error.Codec -> notifyCodecFailure(error)
        is Error.Storage -> notifyStorageFailure(error)
        else -> notifyGenericFailure(error)
    }
}
```

### Example 3: RecordingStorage Operations

```kotlin
import com.buccancs.core.result.*

fun sessionDirectory(sessionId: String): Result<File> {
    require(sessionId.isNotBlank()) { "Session ID cannot be blank" }
    
    return storageOperation {
        File(root, sessionId)
    }.flatMap { file ->
        file.ensureDirectory()
    }
}

fun deviceDirectory(sessionId: String, deviceId: String): Result<File> {
    require(sessionId.isNotBlank()) { "Session ID cannot be blank" }
    require(deviceId.isNotBlank()) { "Device ID cannot be blank" }
    
    return sessionDirectory(sessionId)
        .flatMap { sessionDir ->
            storageOperation {
                File(sessionDir, deviceId)
            }.flatMap { it.ensureDirectory() }
        }
}

fun createArtifactFile(
    sessionId: String,
    deviceId: String,
    streamType: String,
    timestampEpochMs: Long,
    segmentIndex: Int = 0,
    extension: String
): Result<File> {
    return deviceDirectory(sessionId, deviceId)
        .map { deviceDir ->
            val fileName = artifactFileName(
                streamType = streamType,
                timestampEpochMs = timestampEpochMs,
                segmentIndex = segmentIndex,
                extension = extension
            )
            File(deviceDir, fileName)
        }
}

fun saveArtifact(sessionId: String, deviceId: String, data: ByteArray): Result<File> {
    return createArtifactFile(sessionId, deviceId, "data", System.currentTimeMillis(), 0, "bin")
        .flatMap { file ->
            storageOperation {
                file.writeBytes(data)
                file
            }
        }
        .onSuccess { file ->
            Log.i(TAG, "Artifact saved: ${file.absolutePath}")
        }
        .onFailure { error ->
            Log.e(TAG, "Failed to save artifact: ${error.message}", error.cause)
        }
}
```

### Example 4: ViewModel Integration

```kotlin
viewModelScope.launch {
    sensorConnector.connectHardware()
        .onSuccess {
            _uiState.update { it.copy(connected = true, error = null) }
        }
        .onFailure { error ->
            _uiState.update { 
                it.copy(
                    connected = false,
                    error = when (error) {
                        is Error.Bluetooth -> "Bluetooth error: ${error.message}"
                        is Error.Permission -> "Permission required: ${error.permission}"
                        is Error.NotFound -> "No devices found"
                        is Error.Timeout -> "Connection timed out"
                        else -> "Connection failed: ${error.message}"
                    }
                )
            }
        }
}
```

## Testing Guide

### Testing Patterns

#### Success Path

```kotlin
@Test
fun `operation succeeds with valid input`() {
    val result = service.operation(validInput)
    
    assertTrue(result.isSuccess())
    assertEquals(expectedValue, result.getOrNull())
    assertNull(result.errorOrNull())
}
```

#### Failure Path

```kotlin
@Test
fun `operation fails with specific error type`() {
    val result = service.operation(invalidInput)
    
    assertTrue(result.isFailure())
    val error = result.errorOrNull()
    assertTrue(error is Error.Validation)
    assertEquals("fieldName", (error as Error.Validation).field)
    assertEquals("Expected message", error.message)
}
```

#### Error Categorisation

```kotlin
@Test
fun `bluetoothOperation converts IOException to Bluetooth error`() = runTest {
    val deviceId = "00:11:22:33:44:55"
    val result = bluetoothOperation(deviceId) {
        throw IOException("Connection failed")
    }
    
    assertTrue(result.isFailure())
    val error = result.errorOrNull()
    assertTrue(error is Error.Bluetooth)
    assertEquals(deviceId, (error as Error.Bluetooth).deviceId)
}
```

#### Cancellation Safety

```kotlin
@Test
fun `resultOf preserves CancellationException`() = runTest {
    assertFailsWith<CancellationException> {
        resultOf {
            throw CancellationException("Test cancellation")
        }
    }
}
```

#### Error Recovery

```kotlin
@Test
fun `connect device retries on timeout`() = runTest {
    var attempts = 0
    val result = connectDevice(deviceId)
        .recoverWith { error ->
            if (error is Error.Timeout && attempts++ < 3) {
                connectDevice(deviceId)
            } else {
                Result.Failure(error)
            }
        }
    
    assertTrue(result.isSuccess())
    assertEquals(2, attempts)
}
```

#### Multiple Scenarios

```kotlin
@Test
fun `connect handles various error states`() = runTest {
    // Bluetooth disabled
    every { bluetoothService.isEnabled() } returns false
    val result1 = connector.connectHardware()
    assertTrue(result1.errorOrNull() is Error.Bluetooth)
    
    // Permission denied
    every { bluetoothService.isEnabled() } throws SecurityException()
    val result2 = connector.connectHardware()
    assertTrue(result2.errorOrNull() is Error.Permission)
    
    // No devices
    every { bluetoothService.isEnabled() } returns true
    every { bluetoothService.getBondedDevices() } returns emptySet()
    val result3 = connector.connectHardware()
    assertTrue(result3.errorOrNull() is Error.NotFound)
}
```

### Test Files

Tests implemented in:

- `app/src/test/java/com/buccancs/core/result/ResultTest.kt`
- `app/src/test/java/com/buccancs/core/result/ResultExtensionsTest.kt`
- `app/src/test/java/com/buccancs/core/result/BluetoothResultHelpersTest.kt`

## Quick Reference

### Creating Results

```kotlin
Result.success(value)
Result.failure(error)
resultOf { /* operation */ }
nullableValue.toResult("Not found")
```

### Transforming Results

```kotlin
result.map { transform(it) }
result.flatMap { nextOperation(it) }
result.recover { defaultValue }
result.recoverWith { alternativeOperation() }
```

### Consuming Results

```kotlin
result.getOrNull()
result.getOrElse(default)
result.getOrThrow()
result.onSuccess { }
result.onFailure { }
```

### Combining Results

```kotlin
listOfResults.combine()
result1.zip(result2)
result1.zip(result2) { a, b -> a + b }
```

### Helper Functions

#### BluetoothResultHelpers

```kotlin
BluetoothAdapter?.checkAvailable() // Validates adapter
bluetoothOperation(deviceId) { } // Wraps BT operations
```

#### StorageResultHelpers

```kotlin
storageOperation { } // Wraps file I/O
File.ensureDirectory() // Creates and validates directory
File.ensureReadable() // Validates readable file
File.safeDelete() // Safe deletion
```

#### CodecResultHelpers

```kotlin
codecOperation { } // Wraps codec operations
MediaCodec.safeRelease() // Safe codec cleanup
```

### Error Categories

```kotlin
Error.Bluetooth(deviceId, message, cause)
Error.Permission(message, permission, cause)
Error.Storage(message, cause)
Error.Codec(message, cause)
Error.Network(message, cause)
Error.Timeout(message, timeoutMs, cause)
Error.NotFound(message, cause)
Error.Validation(message, field, cause)
Error.Hardware(deviceId, message, cause)
Error.Unknown(message, cause)
```

## Related Documentation

- **Architecture**: `docs/architecture/ERROR_HANDLING_STRATEGY_2025-10-14.md`
- **Implementation**: `app/src/main/java/com/buccancs/core/result/`
- **Tests**: `app/src/test/java/com/buccancs/core/result/`
- **Analysis**: `docs/analysis/ERROR_HANDLING_AUDIT_2025-10-14.md`
- **Project Log**: `docs/project/ERROR_HANDLING_IMPLEMENTATION_2025-10-14.md`
