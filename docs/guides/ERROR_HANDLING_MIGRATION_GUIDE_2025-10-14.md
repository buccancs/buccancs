**Last Modified:** 2025-10-14 03:38 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Guide

# Error Handling Migration Guide

## Overview

This guide provides practical examples for migrating from exception-based error handling to the Result pattern.

## Before and After Examples

### Example 1: Bluetooth Connection

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

### Example 2: Storage Operations

#### Before (Anti-Pattern)
```kotlin
fun saveData(sessionId: String, data: ByteArray): Uri? {
    try {
        val file = File(storageDir, "$sessionId.dat")
        file.writeBytes(data)
        return Uri.fromFile(file)
    } catch (ex: Exception) {
        Log.e(TAG, "Save failed", ex)
        return null
    }
}
```

#### After (Result Pattern)
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

// Usage with error recovery
saveData(sessionId, data)
    .onSuccess { uri -> Log.i(TAG, "Saved to $uri") }
    .onFailure { error -> 
        when (error) {
            is Error.Storage -> showStorageError(error)
            is Error.Validation -> showValidationError(error)
            else -> showGenericError(error)
        }
    }
```

### Example 3: Cancellation Handling

#### Before (Anti-Pattern)
```kotlin
suspend fun processData(data: List<Item>) {
    launch {
        try {
            data.forEach { item ->
                delay(100)  // Simulation
                processItem(item)
            }
        } catch (ex: Exception) {
            // BUG: Catches CancellationException!
            Log.e(TAG, "Processing failed", ex)
            notifyError(ex)
        }
    }
}
```

#### After (Correct Pattern)
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

### Example 4: Chaining Operations

#### Before (Anti-Pattern)
```kotlin
suspend fun initialiseSession(config: Config): Session? {
    val device = connectDevice(config.deviceId) ?: return null
    val calibration = loadCalibration(device) ?: return null
    val session = createSession(device, calibration) ?: return null
    return session
}
```

#### After (Result Pattern)
```kotlin
suspend fun initialiseSession(config: Config): Result<Session> =
    connectDevice(config.deviceId)
        .flatMap { device -> loadCalibration(device).map { device to it } }
        .flatMap { (device, calibration) -> createSession(device, calibration) }

// Usage with intermediate logging
initialiseSession(config)
    .onSuccess { session -> 
        Log.i(TAG, "Session initialised: ${session.id}")
        startRecording(session)
    }
    .onFailure { error ->
        Log.e(TAG, "Initialisation failed: ${error.message}", error.cause)
        showError(error)
    }
```

### Example 5: Multiple Result Combination

#### Before (Anti-Pattern)
```kotlin
suspend fun synchroniseDevices(ids: List<String>): List<Device> {
    val devices = mutableListOf<Device>()
    for (id in ids) {
        try {
            val device = connectDevice(id)
            devices.add(device)
        } catch (ex: Exception) {
            Log.e(TAG, "Failed to connect $id", ex)
            // Continue with partial results
        }
    }
    return devices
}
```

#### After (Result Pattern - Fail Fast)
```kotlin
suspend fun synchroniseDevices(ids: List<String>): Result<List<Device>> {
    val results = ids.map { id -> connectDevice(id) }
    return results.combine()  // Fails if any connection fails
}

// Or partial success pattern
suspend fun synchroniseDevicesPartial(ids: List<String>): Result<DeviceSyncResult> =
    resultOf {
        val results = ids.map { id -> id to connectDevice(id) }
        val successful = results.mapNotNull { (id, result) -> 
            result.getOrNull()?.let { id to it }
        }
        val failed = results.mapNotNull { (id, result) ->
            result.errorOrNull()?.let { id to it }
        }
        DeviceSyncResult(successful.toMap(), failed.toMap())
    }

data class DeviceSyncResult(
    val connected: Map<String, Device>,
    val failed: Map<String, Error>
)
```

### Example 6: Converting Nullable to Result

#### Before (Anti-Pattern)
```kotlin
fun findDevice(id: String): Device? {
    return deviceCache[id]
}

// Caller doesn't know why it's null
val device = findDevice(id)
if (device == null) {
    // Not found? Not initialised? Error?
    showError("Device not found")
}
```

#### After (Result Pattern)
```kotlin
fun findDevice(id: String): Result<Device> =
    deviceCache[id].toResult("Device $id not found in cache")

// Clear intent
when (val result = findDevice(id)) {
    is Result.Success -> useDevice(result.value)
    is Result.Failure -> {
        Log.w(TAG, "Device lookup failed: ${result.error.message}")
        showError(result.error)
    }
}
```

## Migration Checklist

### For Each Method

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

#### ❌ Don't catch CancellationException
```kotlin
try {
    suspendingOperation()
} catch (ex: Exception) {
    // BAD: Catches CancellationException
}
```

#### ✅ Use resultOf instead
```kotlin
resultOf {
    suspendingOperation()
}
// CancellationException automatically rethrown
```

#### ❌ Don't lose error context
```kotlin
Result.Failure(Error.Unknown("Failed"))  // Lost details
```

#### ✅ Preserve cause and context
```kotlin
Result.Failure(Error.Hardware(
    deviceId = device.id,
    message = "Connection timeout after 30s",
    cause = originalException
))
```

#### ❌ Don't ignore errors
```kotlin
operation().getOrNull()  // Silent failure
```

#### ✅ Handle explicitly
```kotlin
operation()
    .onSuccess { /* handle */ }
    .onFailure { /* handle */ }
```

## Testing with Result

### Success Case
```kotlin
@Test
fun `connect device returns success for valid device`() = runTest {
    val result = connector.connectDevice(validDeviceId)
    
    assertTrue(result.isSuccess())
    val connection = result.getOrNull()
    assertNotNull(connection)
    assertEquals(validDeviceId, connection.deviceId)
}
```

### Failure Case
```kotlin
@Test
fun `connect device returns bluetooth error for invalid device`() = runTest {
    val result = connector.connectDevice(invalidDeviceId)
    
    assertTrue(result.isFailure())
    val error = result.errorOrNull()
    assertTrue(error is Error.Bluetooth)
    assertEquals(invalidDeviceId, (error as Error.Bluetooth).deviceId)
}
```

### Error Recovery
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
    assertEquals(2, attempts)  // Failed once, succeeded on retry
}
```

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

## Additional Resources

- [ERROR_HANDLING_STRATEGY_2025-10-14.md](./ERROR_HANDLING_STRATEGY_2025-10-14.md) - Architecture overview
- [Result.kt](../../app/src/main/java/com/buccancs/core/result/Result.kt) - Implementation
- [ResultExtensions.kt](../../app/src/main/java/com/buccancs/core/result/ResultExtensions.kt) - Extension functions
