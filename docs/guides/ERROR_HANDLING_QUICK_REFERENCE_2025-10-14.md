**Last Modified:** 2025-10-14 03:58 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Quick Reference

# Error Handling Quick Reference

## Import Statement

```kotlin
import com.buccancs.core.result.*
```

## Creating Results

```kotlin
// Success
Result.success(value)
Result.Success(value)

// Failure
Result.failure(error)
Result.Failure(Error.Network("Connection failed"))

// From operation
resultOf { riskyOperation() }

// From nullable
nullableValue.toResult("Not found")
```

## Error Categories

```kotlin
Error.Network(message, cause)
Error.Hardware(deviceId, message, cause)
Error.Bluetooth(deviceId, message, cause)
Error.Storage(message, cause)
Error.Validation(field, message, cause)
Error.Codec(message, cause)
Error.Configuration(message, cause)
Error.Timeout(message, timeoutMs, cause)
Error.NotFound(message, cause)
Error.Permission(message, permission, cause)
Error.Unknown(message, cause)
```

## Consuming Results

```kotlin
// Pattern matching
when (result) {
    is Result.Success -> useValue(result.value)
    is Result.Failure -> handleError(result.error)
}

// Safe extraction
result.getOrNull()           // Returns null on failure
result.getOrElse(default)    // Returns default on failure
result.getOrThrow()          // Throws on failure

// Callbacks
result
    .onSuccess { value -> }
    .onFailure { error -> }
```

## Transforming Results

```kotlin
// Map value
result.map { value -> transform(value) }

// FlatMap (chain operations)
result.flatMap { value -> nextOperation(value) }

// Recover from error
result.recover { error -> defaultValue }
result.recoverWith { error -> alternativeOperation() }
```

## Domain-Specific Helpers

### Bluetooth

```kotlin
// Validate adapter
bluetoothAdapter.checkAvailable()

// Wrap operation
bluetoothOperation(deviceId) {
    adapter.connect(device)
}
```

### Storage

```kotlin
// Wrap file I/O
storageOperation {
    file.writeBytes(data)
}

// Ensure directory
directory.ensureDirectory()

// Check readability
file.ensureReadable()

// Safe delete
file.safeDelete()
```

### Codec

```kotlin
// Wrap codec operation
codecOperation {
    codec.configure(format, null, null, flags)
}

// Safe release
codec.safeRelease()
```

## Common Patterns

### Simple Operation

```kotlin
suspend fun loadData(): Result<Data> = resultOf {
    database.query()
}
```

### Chained Operations

```kotlin
suspend fun processUser(id: String): Result<ProcessedUser> =
    findUser(id)
        .flatMap { user -> validateUser(user) }
        .flatMap { user -> enrichUser(user) }
        .map { user -> ProcessedUser(user) }
```

### Error Recovery

```kotlin
suspend fun connectWithRetry(id: String): Result<Connection> =
    connect(id)
        .recoverWith { error ->
            when (error) {
                is Error.Timeout -> connect(id)  // Retry
                else -> Result.Failure(error)    // Propagate
            }
        }
```

### Multiple Results

```kotlin
val results: List<Result<Item>> = items.map { process(it) }
val combined: Result<List<Item>> = results.combine()
```

### Conditional Logic

```kotlin
suspend fun smartConnect(): Result<Connection> {
    return checkPermissions()
        .flatMap { permissions ->
            if (permissions.isBluetoothEnabled) {
                connectBluetooth()
            } else {
                Result.Failure(Error.Permission("Bluetooth disabled"))
            }
        }
}
```

## Testing

```kotlin
@Test
fun `operation succeeds with valid input`() = runTest {
    val result = service.operation(validInput)
    
    assertTrue(result.isSuccess())
    assertEquals(expected, result.getOrNull())
}

@Test
fun `operation fails with invalid input`() = runTest {
    val result = service.operation(invalidInput)
    
    assertTrue(result.isFailure())
    val error = result.errorOrNull()
    assertTrue(error is Error.Validation)
    assertEquals("field", (error as Error.Validation).field)
}
```

## ViewModel Integration

```kotlin
viewModelScope.launch {
    repository.getData()
        .onSuccess { data ->
            _uiState.update { it.copy(data = data, loading = false) }
        }
        .onFailure { error ->
            _uiState.update { 
                it.copy(
                    loading = false,
                    error = when (error) {
                        is Error.Network -> "Network error"
                        is Error.Permission -> "Permission required"
                        else -> "Unknown error"
                    }
                )
            }
        }
}
```

## Anti-Patterns to Avoid

### ❌ Don't catch CancellationException

```kotlin
// BAD
try {
    suspendingOperation()
} catch (ex: Exception) {
    // Catches cancellation!
}

// GOOD
resultOf {
    suspendingOperation()
}
```

### ❌ Don't lose error context

```kotlin
// BAD
Result.Failure(Error.Unknown("Failed"))

// GOOD
Result.Failure(Error.Hardware(
    deviceId = device.id,
    message = "Connection failed: ${ex.message}",
    cause = ex
))
```

### ❌ Don't ignore errors

```kotlin
// BAD
operation().getOrNull()  // Silent failure

// GOOD
operation()
    .onSuccess { /* handle */ }
    .onFailure { /* handle */ }
```

## Migration Checklist

- [ ] Import com.buccancs.core.result.*
- [ ] Replace try-catch with resultOf
- [ ] Choose appropriate Error category
- [ ] Use domain-specific helper if applicable
- [ ] Add error recovery if needed
- [ ] Update call sites
- [ ] Add/update tests
- [ ] Document error conditions

## See Also

- [ERROR_HANDLING_STRATEGY_2025-10-14.md](../architecture/ERROR_HANDLING_STRATEGY_2025-10-14.md) - Architecture
- [ERROR_HANDLING_MIGRATION_GUIDE_2025-10-14.md](./ERROR_HANDLING_MIGRATION_GUIDE_2025-10-14.md) - Detailed guide
- [ERROR_HANDLING_REFACTORING_EXAMPLES_2025-10-14.md](./ERROR_HANDLING_REFACTORING_EXAMPLES_2025-10-14.md) - Real examples
