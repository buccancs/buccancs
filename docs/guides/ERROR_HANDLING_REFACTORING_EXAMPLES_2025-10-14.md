**Last Modified:** 2025-10-14 03:58 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Guide

# Error Handling Refactoring Examples

## Overview

This document provides actual refactoring examples from the BuccanCS codebase, showing how to migrate from exception-based error handling to the Result pattern.

## Example 1: ShimmerSensorConnector.connect()

### Current Implementation (Lines 263-295)

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

### Problems
1. Silent failure with generic catch
2. Lost error context (can't distinguish permission vs I/O errors)
3. No structured error recovery
4. Logging only, no propagation to UI layer with details

### Refactored with Result Pattern

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

### Benefits
- Type-safe error categories (can distinguish Bluetooth, Permission, NotFound)
- Explicit error recovery per category
- Better logging with structured error information
- Testable without throwing exceptions
- Clear separation of concerns

## Example 2: SegmentedMediaCodecRecorder Error Handling

### Current Implementation (Lines 149-157, 164-166)

```kotlin
try {
    if (info.size > 0) {
        ensureSegmentReady(info)
        buffer.position(info.offset)
        buffer.limit(info.offset + info.size)
        currentMuxer?.writeSampleData(currentTrackIndex, buffer, info)
        // ... stats collection
    }
} catch (error: Throwable) {
    handleError(error)
}

override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
    handleError(e)
}
```

### Refactored with Result Pattern

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
        is Error.Codec -> {
            // Codec-specific recovery
            notifyCodecFailure(error)
        }
        is Error.Storage -> {
            // Storage-specific recovery
            notifyStorageFailure(error)
        }
        else -> {
            // Generic error handling
            notifyGenericFailure(error)
        }
    }
}
```

### Benefits
- Clear error categorization (Codec vs Storage vs others)
- Structured error recovery per category
- Better testability of error scenarios
- Cleaner separation of error detection and handling

## Example 3: RecordingStorage Operations

### Current Pattern (Inferred)

```kotlin
fun sessionDirectory(sessionId: String): File {
    val sessionRoot = File(root, sessionId)
    if (!sessionRoot.exists()) {
        sessionRoot.mkdirs()
    }
    return sessionRoot
}
```

### Problems
- Silent failure if mkdirs() fails
- No error propagation
- Caller assumes success

### Refactored with Result Pattern

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

// Usage
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

### Benefits
- Explicit validation
- Clear error propagation
- Chainable operations
- Testable without filesystem

## Example 4: Integration Testing

### Before (Exception-based)

```kotlin
@Test
fun `connect should succeed for valid device`() = runTest {
    // Hard to test exception scenarios
    try {
        connector.connect()
        // Success case
    } catch (ex: Exception) {
        fail("Should not throw")
    }
}
```

### After (Result-based)

```kotlin
@Test
fun `connect should succeed for valid device`() = runTest {
    val result = connector.connectHardware()
    
    assertTrue(result.isSuccess())
    val connection = result.getOrNull()
    assertNotNull(connection)
}

@Test
fun `connect should fail with Permission error when Bluetooth permission denied`() = runTest {
    // Mock to throw SecurityException
    whenever(bluetoothAdapter.isEnabled).thenThrow(SecurityException("Permission denied"))
    
    val result = connector.connectHardware()
    
    assertTrue(result.isFailure())
    val error = result.errorOrNull()
    assertTrue(error is Error.Permission)
    assertEquals("BLUETOOTH_CONNECT", (error as Error.Permission).permission)
}

@Test
fun `connect should fail with NotFound when no devices available`() = runTest {
    // Mock empty device list
    whenever(discoveredCandidates).thenReturn(emptyList())
    
    val result = connector.connectHardware()
    
    assertTrue(result.isFailure())
    val error = result.errorOrNull()
    assertTrue(error is Error.NotFound)
    assertContains(error!!.message, "No Shimmer device available")
}

@Test
fun `connect should retry on timeout`() = runTest {
    var attempts = 0
    val connector = object : ShimmerSensorConnector(...) {
        override suspend fun connectHardware(): Result<Unit> {
            attempts++
            return if (attempts < 3) {
                Result.Failure(Error.Timeout("Connection timed out", 30000))
            } else {
                Result.Success(Unit)
            }
        }
    }
    
    val result = connector.connectHardware()
        .recoverWith { error ->
            if (error is Error.Timeout && attempts < 3) {
                connector.connectHardware()
            } else {
                Result.Failure(error)
            }
        }
    
    assertTrue(result.isSuccess())
    assertEquals(3, attempts)
}
```

## Example 5: ViewModel Integration

### Before

```kotlin
viewModelScope.launch {
    try {
        sensorConnector.connect()
        _uiState.update { it.copy(connected = true) }
    } catch (ex: Exception) {
        _uiState.update { it.copy(error = ex.message) }
    }
}
```

### After

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

## Migration Checklist

For each method to refactor:

- [ ] Identify current error handling pattern
- [ ] Determine return type (Result<T> or keep existing + internal Result)
- [ ] Choose appropriate Error categories
- [ ] Extract operations into helper functions
- [ ] Use domain-specific helper (bluetoothOperation, storageOperation, codecOperation)
- [ ] Add recovery logic where appropriate
- [ ] Update call sites
- [ ] Add/update tests for success and failure paths
- [ ] Update documentation

## Helper Functions Summary

### BluetoothResultHelpers
- `BluetoothAdapter?.checkAvailable()` - Validates adapter
- `bluetoothOperation()` - Wraps BT operations with error handling

### StorageResultHelpers
- `storageOperation()` - Wraps file I/O
- `File.ensureDirectory()` - Creates and validates directory
- `File.ensureReadable()` - Validates readable file
- `File.safeDelete()` - Safe deletion

### CodecResultHelpers
- `codecOperation()` - Wraps codec operations
- `MediaCodec.safeRelease()` - Safe codec cleanup

## Next Steps

1. Complete migration of ShimmerSensorConnector.connect()
2. Refactor SegmentedMediaCodecRecorder error handling
3. Update RecordingStorage to return Result types
4. Add comprehensive tests
5. Document patterns in architecture guide

## References

- [ERROR_HANDLING_STRATEGY_2025-10-14.md](../architecture/ERROR_HANDLING_STRATEGY_2025-10-14.md)
- [ERROR_HANDLING_MIGRATION_GUIDE_2025-10-14.md](./ERROR_HANDLING_MIGRATION_GUIDE_2025-10-14.md)
- [Result.kt](../../app/src/main/java/com/buccancs/core/result/Result.kt)
