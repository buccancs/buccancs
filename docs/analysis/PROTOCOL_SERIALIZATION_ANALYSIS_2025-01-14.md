**Last Modified:** 2025-01-14 03:40 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Analysis

# Protocol Buffer and Serialisation Analysis

## Executive Summary

This document analyses the Protocol Buffer definitions, JSON serialisation patterns, and file I/O operations within the Buccancs application. Whilst the current implementation demonstrates good practices in several areas (modern kotlinx.serialisation, proper proto package organisation, automatic resource cleanup with `.use {}`), there are critical issues that could impact data integrity, interoperability, and maintainability.

**Overall Quality Assessment:**
- **Protocol Buffers:** GOOD (clear structure, proper organisation) with MODERATE concerns (no versioning, embedded JSON)
- **JSON Serialisation:** GOOD (modern kotlinx.serialisation) with MODERATE concerns (multiple configurations, no validation)
- **File I/O:** ADEQUATE with HIGH concerns (no disk space checks, missing atomicity guarantees)

**Priority Recommendations:**
1. **HIGH PRIORITY:** Add disk space validation before file writes (affects data integrity)
2. **HIGH PRIORITY:** Implement atomic writes for critical files (manifest corruption risk)
3. **MEDIUM PRIORITY:** Add protocol versioning (future compatibility)
4. **MEDIUM PRIORITY:** Replace embedded JSON in protobufs with typed messages (type safety)
5. **LOW PRIORITY:** Centralise Json configuration (consistency)

---

## 1. Protocol Buffer Definitions

### 1.1 Files Identified

**Application Proto Files:**
- `protocol/src/main/proto/orchestration.proto` - Session orchestration, device registration, time sync, data transfer
- `protocol/src/main/proto/sync/control.proto` - Local control service, command envelopes

**Quality Assessment:** GOOD
- Clear message structure with logical grouping
- Proper package organisation (`com.buccancs.control`, `com.buccancs.control.sync`)
- Java multi-file generation enabled
- Well-defined services (6 services in orchestration, 1 in control)

### 1.2 Critical Issues

#### Issue 1.1: No Protocol Versioning

**Location:** All proto files  
**Severity:** MEDIUM  
**Impact:** Future compatibility issues when protocol evolves

**Current State:**
```protobuf
// orchestration.proto
syntax = "proto3";
package com.buccancs.control;

message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  string device_id = 3;
  int64 issued_epoch_ms = 4;
  int64 execute_epoch_ms = 5;
  string command_json = 6;  // No version field
}
```

**Problem:** Without version fields, clients and servers cannot negotiate protocol capabilities or handle breaking changes gracefully.

**Recommended Solution:**

```protobuf
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  string device_id = 3;
  int64 issued_epoch_ms = 4;
  int64 execute_epoch_ms = 5;
  string command_json = 6;
  int32 protocol_version = 7;  // Add version tracking
}

message DeviceRegistration {
  string device_id = 1;
  string model = 2;
  string platform = 3;
  string software_version = 4;
  repeated string capabilities = 5;
  int32 protocol_version = 6;  // Add version tracking
}
```

**Implementation Steps:**
1. Add `protocol_version` field to key message types:
   - `CommandEnvelope` (both proto files)
   - `DeviceRegistration`
   - `ControlEvent`
2. Define version constant in Kotlin:
   ```kotlin
   object ProtocolVersion {
       const val CURRENT = 1
       const val MIN_SUPPORTED = 1
   }
   ```
3. Add version checking in service implementations
4. Document version compatibility matrix

**Effort:** 1 day

---

#### Issue 1.2: Embedded JSON in Protocol Buffers

**Location:** `sync/control.proto`, `orchestration.proto`  
**Severity:** MEDIUM  
**Impact:** Loss of type safety, increased message size, parsing overhead

**Current State:**
```protobuf
// sync/control.proto
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  string payload_json = 3;  // Type safety lost
  int64 execute_epoch_ms = 4;
  string token = 5;
}

message ControlEvent {
  string event_id = 1;
  string session_id = 2;
  string type = 3;
  string detail_json = 4;  // Type safety lost
  int64 timestamp_epoch_ms = 5;
}
```

**Problems:**
1. **No compile-time type checking** - errors only detected at runtime
2. **Increased message size** - JSON is verbose compared to protobuf binary
3. **Double serialisation overhead** - payload serialised to JSON, then protobuf wraps it
4. **No automatic validation** - malformed JSON can be transmitted

**Recommended Solution:**

```protobuf
// Define specific command types
message StartRecordingCommand {
  string session_id = 1;
  repeated string camera_ids = 2;
  bool enable_preview = 3;
}

message StopRecordingCommand {
  string session_id = 1;
  bool finalise = 2;
}

message SyncSignalCommand {
  string signal_type = 1;
  repeated string device_ids = 2;
  int64 scheduled_epoch_ms = 3;
}

// Use oneof for type-safe payloads
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  int64 execute_epoch_ms = 4;
  string token = 5;
  int32 protocol_version = 6;
  
  oneof payload {
    StartRecordingCommand start_recording = 10;
    StopRecordingCommand stop_recording = 11;
    SyncSignalCommand sync_signal = 12;
    // Add other command types as needed
  }
}

// Similarly for events
message RecordingStartedEvent {
  string camera_id = 1;
  int64 start_timestamp_epoch_ms = 2;
}

message RecordingStoppedEvent {
  string camera_id = 1;
  int64 stop_timestamp_epoch_ms = 2;
  uint64 file_size_bytes = 3;
}

message ControlEvent {
  string event_id = 1;
  string session_id = 2;
  int64 timestamp_epoch_ms = 5;
  
  oneof detail {
    RecordingStartedEvent recording_started = 10;
    RecordingStoppedEvent recording_stopped = 11;
    // Add other event types as needed
  }
}
```

**Benefits:**
1. **Type safety at compile time** - compiler catches errors early
2. **Smaller message size** - protobuf binary is more compact than JSON
3. **Automatic validation** - protobuf validates field types and presence
4. **Better IDE support** - autocomplete and navigation work properly

**Migration Strategy:**
1. Phase 1: Add new typed fields alongside existing JSON fields
2. Phase 2: Update clients to use typed fields whilst maintaining JSON for compatibility
3. Phase 3: Deprecate JSON fields after all clients migrate
4. Phase 4: Remove JSON fields in next major version

**Effort:** 2-3 days for definition + 1 week for gradual migration

---

#### Issue 1.3: Duplicate CommandEnvelope Definitions

**Location:** `orchestration.proto` line 156, `sync/control.proto` line 13  
**Severity:** LOW  
**Impact:** Confusion, potential divergence over time

**Current State:**
```protobuf
// orchestration.proto
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  string device_id = 3;
  int64 issued_epoch_ms = 4;
  int64 execute_epoch_ms = 5;
  string command_json = 6;
}

// sync/control.proto
message CommandEnvelope {
  string command_id = 1;
  string session_id = 2;
  string payload_json = 3;  // Different field name!
  int64 execute_epoch_ms = 4;
  string token = 5;  // Additional field!
}
```

**Problem:** Two different message types with the same name in different packages could cause confusion and maintenance issues.

**Recommended Solution:**
1. Rename to reflect purpose: `OrchestrationCommand` vs `LocalControlCommand`
2. Or consolidate into single definition in shared proto file
3. Import shared definitions where needed

**Effort:** 0.5 days

---

## 2. JSON Serialisation Patterns

### 2.1 Usage Analysis

**Libraries Used:**
- Primary: `kotlinx.serialization` (modern, performant)
- Legacy: `Gson` (only in external code)

**Quality Assessment:** GOOD - modern kotlinx.serialisation throughout application code

**Instances Found:** 92 serialisation/deserialisation operations

### 2.2 Critical Issues

#### Issue 2.1: Multiple Json Configuration Instances

**Location:** Throughout application  
**Severity:** MEDIUM  
**Impact:** Inconsistent behaviour, potential parsing errors

**Current State:**

Found 11 different Json instances with varying configurations:

```kotlin
// PerformanceMetricsAnalyzer.kt:18
private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

// PerformanceMetricsRecorder.kt:37
private val json = Json { encodeDefaults = true }

// CalibrationStorage.kt:24
private val json = Json {
    prettyPrint = true
    encodeDefaults = true
}

// ManifestWriter.kt:25
private val json = Json {
    prettyPrint = true
    encodeDefaults = true
}

// DefaultSensorHardwareConfigRepository.kt:32
private val json = Json { ignoreUnknownKeys = true }

// ShimmerSensorConnector.kt:75
private val json = Json { ignoreUnknownKeys = true }

// BacklogTelemetryLogger.kt:19
private val json = Json { encodeDefaults = true }

// UploadRecoveryLogger.kt:19
private val json = Json { encodeDefaults = true }

// UploadWorker.kt:25
private val json = Json { ignoreUnknownKeys = true }

// MainViewModel.kt:91
private val shimmerJson = Json { ignoreUnknownKeys = true }

// Plus default Json instances used without explicit configuration
```

**Problems:**
1. **Inconsistent parsing behaviour** - some ignore unknown keys, others don't
2. **Maintenance burden** - configuration changes must be applied everywhere
3. **Hard to debug** - behaviour varies by location
4. **No central documentation** - unclear which settings are standard

**Recommended Solution:**

Create centralised Json configuration module:

```kotlin
// app/src/main/java/com/buccancs/core/serialization/JsonConfig.kt
package com.buccancs.core.serialization

import kotlinx.serialization.json.Json

object JsonConfig {
    /**
     * Standard JSON configuration for application-wide use.
     * 
     * Configuration:
     * - ignoreUnknownKeys: Allows forward compatibility when adding new fields
     * - isLenient: Accepts non-standard JSON (quoted enums, etc.)
     * - encodeDefaults: Always encode default values (for completeness)
     * - prettyPrint: false (optimise for size in production)
     */
    val standard: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = false
    }
    
    /**
     * Pretty-printed JSON for human-readable files (manifests, configs).
     */
    val pretty: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = true
    }
    
    /**
     * Strict JSON configuration for external APIs where schema must match exactly.
     */
    val strict: Json = Json {
        ignoreUnknownKeys = false
        isLenient = false
        encodeDefaults = true
        prettyPrint = false
    }
}

// Provide via Hilt for dependency injection
@Module
@InstallIn(SingletonComponent::class)
object JsonModule {
    @Provides
    @Singleton
    @StandardJson
    fun provideStandardJson(): Json = JsonConfig.standard
    
    @Provides
    @Singleton
    @PrettyJson
    fun providePrettyJson(): Json = JsonConfig.pretty
}

// Qualifiers
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StandardJson

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrettyJson
```

**Usage:**

```kotlin
@Singleton
class ManifestWriter @Inject constructor(
    @PrettyJson private val json: Json,  // Inject pretty JSON
    private val storage: RecordingStorage
) {
    fun writeManifest(manifest: RecordingManifest, sessionId: String) {
        val target = storage.manifestFile(sessionId)
        target.writeText(json.encodeToString(manifest), UTF8)
    }
}

@Singleton
class DefaultSensorHardwareConfigRepository @Inject constructor(
    @StandardJson private val json: Json,  // Inject standard JSON
    @ApplicationContext private val context: Context
) {
    // Use injected json instance
}
```

**Implementation Steps:**
1. Create `JsonConfig` object and Hilt module
2. Update all classes to inject Json instance instead of creating locally
3. Remove all local `private val json = Json { }` declarations
4. Run tests to verify behaviour unchanged
5. Document configuration choices in code comments

**Effort:** 1 day for implementation + testing

---

#### Issue 2.2: No Schema Validation on Load

**Location:** All JSON deserialization points  
**Severity:** MEDIUM  
**Impact:** Runtime errors from malformed data, difficult debugging

**Current State:**

```kotlin
// DefaultSensorHardwareConfigRepository.kt
private fun loadFromFile(): SensorHardwareConfig? {
    return if (inventoryFile.exists()) {
        try {
            json.decodeFromString(inventoryFile.readText(StandardCharsets.UTF_8))
        } catch (e: Exception) {
            null  // Silent failure
        }
    } else null
}
```

**Problems:**
1. **No validation** - malformed JSON can be loaded
2. **Silent failures** - errors swallowed, hard to debug
3. **No version checking** - old formats cause crashes
4. **No integrity checks** - corrupted files undetected

**Recommended Solution:**

Add validation layer with proper error handling:

```kotlin
@Serializable
data class SensorHardwareConfig(
    val version: Int,
    val devices: List<HardwareDevice>,
    val lastModified: Long = System.currentTimeMillis()
) {
    init {
        require(version > 0) { "Invalid version: $version (must be positive)" }
        require(version <= CURRENT_VERSION) { 
            "Unsupported version: $version (current: $CURRENT_VERSION)" 
        }
        require(devices.isNotEmpty()) { "Device list cannot be empty" }
        devices.forEach { device ->
            require(device.id.isNotBlank()) { "Device ID cannot be blank" }
            require(device.type.isNotBlank()) { "Device type cannot be blank" }
        }
    }
    
    companion object {
        const val CURRENT_VERSION = 1
    }
}

// Sealed class for explicit error handling
sealed class LoadResult<out T> {
    data class Success<T>(val data: T) : LoadResult<T>()
    sealed class Failure : LoadResult<Nothing>() {
        data class FileNotFound(val path: String) : Failure()
        data class ParseError(val message: String, val cause: Throwable?) : Failure()
        data class ValidationError(val message: String) : Failure()
        data class VersionMismatch(val found: Int, val expected: Int) : Failure()
    }
}

private fun loadFromFile(): LoadResult<SensorHardwareConfig> {
    if (!inventoryFile.exists()) {
        return LoadResult.Failure.FileNotFound(inventoryFile.absolutePath)
    }
    
    return try {
        val content = inventoryFile.readText(StandardCharsets.UTF_8)
        val config = json.decodeFromString<SensorHardwareConfig>(content)
        
        // Additional validation beyond init block
        if (config.devices.distinctBy { it.id }.size != config.devices.size) {
            return LoadResult.Failure.ValidationError("Duplicate device IDs found")
        }
        
        LoadResult.Success(config)
    } catch (e: SerializationException) {
        LoadResult.Failure.ParseError("Failed to parse JSON", e)
    } catch (e: IllegalArgumentException) {
        LoadResult.Failure.ValidationError(e.message ?: "Validation failed")
    } catch (e: Exception) {
        LoadResult.Failure.ParseError("Unexpected error: ${e.message}", e)
    }
}

// Usage with proper error handling
fun loadConfig(): SensorHardwareConfig {
    return when (val result = loadFromFile()) {
        is LoadResult.Success -> result.data
        is LoadResult.Failure.FileNotFound -> {
            Timber.w("Config file not found, creating default")
            createDefaultConfig()
        }
        is LoadResult.Failure.ParseError -> {
            Timber.e(result.cause, "Parse error: ${result.message}")
            createDefaultConfig()
        }
        is LoadResult.Failure.ValidationError -> {
            Timber.e("Validation error: ${result.message}")
            createDefaultConfig()
        }
        is LoadResult.Failure.VersionMismatch -> {
            Timber.e("Version mismatch: found ${result.found}, expected ${result.expected}")
            migrateOrCreateDefault(result.found)
        }
    }
}
```

**Benefits:**
1. **Explicit error handling** - know exactly what went wrong
2. **Data integrity** - validation catches corruption early
3. **Better logging** - specific error messages for debugging
4. **Version migration path** - handle old formats gracefully

**Effort:** 2 days across all serialisation points

---

## 3. File I/O Patterns

### 3.1 Usage Analysis

**Total File Operations:** 97 instances

**Patterns Found:**
- ✅ 51 `.use {}` blocks (good - automatic cleanup)
- ✅ Proper `mkdirs()` calls (directory creation)
- ✅ Explicit UTF-8 charset in many places
- ⚠️ Direct `File().writeBytes/writeText()` calls
- ❌ No disk space checks
- ❌ No atomic write guarantees

**Quality Assessment:** ADEQUATE - proper resource management but missing critical safety checks

### 3.2 Critical Issues

#### Issue 3.1: No Disk Space Validation

**Location:** All file write operations  
**Severity:** HIGH  
**Impact:** Data loss, application crashes, corrupted recordings

**Current State:**

```kotlin
// Found in 8+ locations
fun writeRecording(data: ByteArray) {
    File(path).writeBytes(data)  // What if disk is full?
}

// ManifestWriter.kt:158
target.writeText(payload, UTF8)  // No space check

// CalibrationStorage.kt:55
output.writeText(json.encodeToString(result))  // No space check
```

**Problems:**
1. **Silent failures** - writes fail mid-operation if disk full
2. **Partial writes** - files left in corrupted state
3. **No user feedback** - users unaware of storage issues
4. **Cascading failures** - subsequent operations fail

**Real-World Scenario:**
```
1. User starts 30-minute recording session
2. Disk fills up after 15 minutes
3. Video file corrupted (incomplete write)
4. Manifest write fails (no space)
5. Session unrecoverable, data lost
6. No error shown to user
```

**Recommended Solution:**

Create storage validation utility:

```kotlin
// app/src/main/java/com/buccancs/core/storage/StorageValidator.kt
package com.buccancs.core.storage

import java.io.File
import java.io.IOException

sealed class StorageCheckResult {
    object Sufficient : StorageCheckResult()
    data class Insufficient(
        val required: Long,
        val available: Long,
        val location: String
    ) : StorageCheckResult()
    data class Error(val message: String, val cause: Throwable?) : StorageCheckResult()
}

object StorageValidator {
    private const val SAFETY_MULTIPLIER = 2.0  // Require 2x data size
    private const val MIN_FREE_BYTES = 100 * 1024 * 1024L  // Always keep 100MB free
    
    /**
     * Check if sufficient storage available for write operation.
     * 
     * @param location Target file or directory
     * @param requiredBytes Minimum bytes needed
     * @param safetyMultiplier Multiply required by this factor for safety (default 2.0)
     * @return Result indicating if space is sufficient
     */
    fun checkSpace(
        location: File,
        requiredBytes: Long,
        safetyMultiplier: Double = SAFETY_MULTIPLIER
    ): StorageCheckResult {
        return try {
            val dir = if (location.isDirectory) location else location.parentFile ?: location
            val available = dir.usableSpace
            val required = (requiredBytes * safetyMultiplier).toLong()
            val totalRequired = required + MIN_FREE_BYTES
            
            if (available >= totalRequired) {
                StorageCheckResult.Sufficient
            } else {
                StorageCheckResult.Insufficient(
                    required = totalRequired,
                    available = available,
                    location = dir.absolutePath
                )
            }
        } catch (e: SecurityException) {
            StorageCheckResult.Error("Permission denied", e)
        } catch (e: IOException) {
            StorageCheckResult.Error("I/O error checking space", e)
        }
    }
    
    /**
     * Format bytes for human-readable display.
     */
    fun formatBytes(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            bytes < 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024)} MB"
            else -> "${bytes / (1024 * 1024 * 1024)} GB"
        }
    }
}

// Extension function for easy use
fun File.hasSpaceFor(bytes: Long): StorageCheckResult {
    return StorageValidator.checkSpace(this, bytes)
}

// Result type for write operations
sealed class WriteResult<out T> {
    data class Success<T>(val value: T) : WriteResult<T>()
    sealed class Failure : WriteResult<Nothing>() {
        data class InsufficientSpace(val required: Long, val available: Long) : Failure()
        data class WriteError(val message: String, val cause: Throwable?) : Failure()
    }
}
```

**Updated Write Operations:**

```kotlin
// ManifestWriter.kt
fun writeManifest(manifest: RecordingManifest, sessionId: String): WriteResult<Unit> {
    val target = storage.manifestFile(sessionId)
    val payload = json.encodeToString(manifest)
    val payloadBytes = payload.toByteArray(UTF8)
    
    return when (val spaceCheck = target.hasSpaceFor(payloadBytes.size.toLong())) {
        is StorageCheckResult.Sufficient -> {
            try {
                target.writeText(payload, UTF8)
                WriteResult.Success(Unit)
            } catch (e: IOException) {
                WriteResult.Failure.WriteError("Failed to write manifest", e)
            }
        }
        is StorageCheckResult.Insufficient -> {
            Timber.e("Insufficient space: need ${StorageValidator.formatBytes(spaceCheck.required)}, " +
                    "have ${StorageValidator.formatBytes(spaceCheck.available)}")
            WriteResult.Failure.InsufficientSpace(spaceCheck.required, spaceCheck.available)
        }
        is StorageCheckResult.Error -> {
            WriteResult.Failure.WriteError(spaceCheck.message, spaceCheck.cause)
        }
    }
}

// Usage in ViewModel
viewModelScope.launch {
    when (val result = manifestWriter.writeManifest(manifest, sessionId)) {
        is WriteResult.Success -> {
            // Continue normally
        }
        is WriteResult.Failure.InsufficientSpace -> {
            _uiState.update { 
                it.copy(
                    error = "Insufficient storage: need ${formatBytes(result.required)}, " +
                           "have ${formatBytes(result.available)}"
                )
            }
        }
        is WriteResult.Failure.WriteError -> {
            _uiState.update { it.copy(error = "Failed to save: ${result.message}") }
        }
    }
}
```

**Implementation Priority:**
1. **Phase 1** (Critical): Manifest writes, calibration results
2. **Phase 2** (Important): Recording metadata, performance logs
3. **Phase 3** (Nice-to-have): Debug logs, temporary files

**Effort:** 2 days for utility + 1 day per phase

---

#### Issue 3.2: Non-Atomic Writes for Critical Files

**Location:** Manifest writes, config saves  
**Severity:** HIGH  
**Impact:** File corruption on crash/power loss

**Current State:**

```kotlin
// Direct write - if process crashes during write, file corrupted
target.writeText(payload, UTF8)
```

**Problem:** Power loss or crash during write leaves file in inconsistent state. No way to recover.

**Recommended Solution:**

Implement write-ahead logging pattern:

```kotlin
// app/src/main/java/com/buccancs/core/storage/AtomicFileWriter.kt
package com.buccancs.core.storage

import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8

object AtomicFileWriter {
    /**
     * Write file atomically using write-ahead pattern.
     * 
     * Process:
     * 1. Write to temporary file (.tmp)
     * 2. Sync to disk (fsync)
     * 3. Rename to target (atomic operation)
     * 4. If target exists, create backup first
     */
    fun writeTextAtomic(
        target: File,
        content: String,
        charset: Charset = UTF_8,
        createBackup: Boolean = true
    ): WriteResult<Unit> {
        val tempFile = File(target.parentFile, "${target.name}.tmp")
        val backupFile = File(target.parentFile, "${target.name}.bak")
        
        return try {
            // Ensure parent directory exists
            target.parentFile?.mkdirs()
            
            // Write to temporary file
            tempFile.writeText(content, charset)
            
            // Sync to disk (ensure data physically written)
            tempFile.outputStream().use { it.fd.sync() }
            
            // Create backup of existing file
            if (createBackup && target.exists()) {
                if (backupFile.exists()) backupFile.delete()
                target.renameTo(backupFile)
            }
            
            // Atomic rename (if crashes here, temp file remains, original intact)
            if (!tempFile.renameTo(target)) {
                throw IOException("Failed to rename temp file to target")
            }
            
            // Success - delete backup if desired
            if (!createBackup && backupFile.exists()) {
                backupFile.delete()
            }
            
            WriteResult.Success(Unit)
        } catch (e: IOException) {
            // Clean up temp file on failure
            tempFile.delete()
            WriteResult.Failure.WriteError("Atomic write failed: ${e.message}", e)
        }
    }
    
    /**
     * Recover from backup if main file corrupted.
     */
    fun recoverFromBackup(target: File): Boolean {
        val backupFile = File(target.parentFile, "${target.name}.bak")
        return if (backupFile.exists()) {
            backupFile.copyTo(target, overwrite = true)
            true
        } else {
            false
        }
    }
}

// Extension function
fun File.writeTextAtomic(
    content: String,
    charset: Charset = UTF_8,
    createBackup: Boolean = true
): WriteResult<Unit> {
    return AtomicFileWriter.writeTextAtomic(this, content, charset, createBackup)
}
```

**Usage:**

```kotlin
// ManifestWriter.kt
fun writeManifest(manifest: RecordingManifest, sessionId: String): WriteResult<Unit> {
    val target = storage.manifestFile(sessionId)
    val payload = json.encodeToString(manifest)
    
    // Check space first
    when (val spaceCheck = target.hasSpaceFor(payload.length.toLong())) {
        is StorageCheckResult.Insufficient -> {
            return WriteResult.Failure.InsufficientSpace(
                spaceCheck.required, 
                spaceCheck.available
            )
        }
        else -> { /* Continue */ }
    }
    
    // Atomic write with backup
    return target.writeTextAtomic(payload, createBackup = true)
}
```

**Benefits:**
1. **Crash safety** - never lose data to partial writes
2. **Power-loss resilience** - backup available if corruption detected
3. **Automatic recovery** - can restore from backup automatically
4. **Minimal performance impact** - fsync only on critical files

**Effort:** 1 day for implementation + 0.5 days for integration

---

#### Issue 3.3: Inconsistent Charset Usage

**Location:** Various file operations  
**Severity:** LOW  
**Impact:** Potential encoding issues on different platforms

**Current State:**

```kotlin
// Good - explicit UTF-8
OutputStreamWriter(fos, StandardCharsets.UTF_8)
target.writeText(content, StandardCharsets.UTF_8)

// Risky - platform default charset
target.writeText(content)  // Uses Charset.defaultCharset()
```

**Recommendation:** Always specify UTF-8 explicitly for all text file operations. Add lint rule to enforce.

**Effort:** 0.5 days + linting setup

---

## 4. Storage Architecture

### 4.1 Current Structure

```
/<sessionId>/
  ├── manifest.json
  ├── recording_<timestamp>_<stream>.mp4
  ├── recording_<timestamp>_<stream>.mp4
  ├── metrics/
  │   ├── performance_metrics.jsonl
  │   └── summary.json
  ├── sensors/
  │   ├── shimmer_<id>.jsonl
  │   └── shimmer_<id>_metadata.json
  └── calibration/
      └── result.json
```

**Quality Assessment:** ADEQUATE - logical organisation but missing safety features

### 4.2 Architectural Issues

#### Issue 4.1: No Backup Mechanism

**Severity:** HIGH  
**Impact:** Data loss if files corrupted or deleted

**Current State:** Single copy of critical data

**Recommendation:**
1. Implement backup on manifest write (covered in Issue 3.2)
2. Add periodic consistency checks
3. Consider cloud backup integration for completed sessions

**Effort:** Covered in Issue 3.2

---

#### Issue 4.2: No Consistency Verification

**Severity:** MEDIUM  
**Impact:** Corrupted sessions undetected

**Recommendation:**

Add consistency checker:

```kotlin
data class ConsistencyReport(
    val sessionId: String,
    val manifestPresent: Boolean,
    val manifestValid: Boolean,
    val recordingsPresent: Int,
    val recordingsExpected: Int,
    val recordingsMissing: List<String>,
    val recordingsCorrupted: List<String>,
    val checksumMatches: Boolean,
    val issues: List<String>
)

class SessionConsistencyChecker @Inject constructor(
    private val storage: RecordingStorage
) {
    fun checkSession(sessionId: String): ConsistencyReport {
        val issues = mutableListOf<String>()
        
        // Check manifest
        val manifestFile = storage.manifestFile(sessionId)
        val manifestPresent = manifestFile.exists()
        val manifestValid = if (manifestPresent) {
            try {
                storage.loadManifest(sessionId)
                true
            } catch (e: Exception) {
                issues.add("Manifest corrupted: ${e.message}")
                false
            }
        } else {
            issues.add("Manifest missing")
            false
        }
        
        // Check recordings
        val manifest = if (manifestValid) storage.loadManifest(sessionId) else null
        val expected = manifest?.streams?.size ?: 0
        val actual = storage.sessionDir(sessionId)
            .listFiles { f -> f.extension == "mp4" }?.size ?: 0
            
        if (actual < expected) {
            issues.add("Missing ${expected - actual} recording files")
        }
        
        // Check checksums if available
        val checksumMatches = manifest?.let { m ->
            m.streams.all { stream ->
                val file = storage.recordingFile(sessionId, stream.streamId)
                // Verify SHA-256 checksum if stored in manifest
                true  // Implement actual checksum verification
            }
        } ?: false
        
        return ConsistencyReport(
            sessionId = sessionId,
            manifestPresent = manifestPresent,
            manifestValid = manifestValid,
            recordingsPresent = actual,
            recordingsExpected = expected,
            recordingsMissing = emptyList(),  // Implement detailed checking
            recordingsCorrupted = emptyList(),
            checksumMatches = checksumMatches,
            issues = issues
        )
    }
}
```

**Effort:** 1-2 days

---

#### Issue 4.3: No Compression

**Severity:** LOW (likely intentional)  
**Current State:** Raw video files stored uncompressed

**Recommendation:** Verify this is intentional design decision. If not, consider:
- Video files already compressed (H.264/H.265), further compression minimal benefit
- Metadata/JSON files could benefit from compression
- Consider `.jsonl.gz` for large sensor data streams

**Effort:** 1 day if compression desired

---

#### Issue 4.4: Manual Cleanup Process

**Severity:** LOW  
**Current State:** Retention worker not fully implemented

**Recommendation:**

Complete retention policy implementation:

```kotlin
@HiltWorker
class StorageCleanupWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val storage: RecordingStorage,
    private val consistency: SessionConsistencyChecker
) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result {
        val retentionDays = 30  // Configurable
        val cutoffTime = System.currentTimeMillis() - (retentionDays * 24 * 60 * 60 * 1000L)
        
        // Find old sessions
        val oldSessions = storage.listSessions()
            .filter { it.createdTime < cutoffTime }
            .filter { it.uploaded }  // Only delete after upload
            
        // Verify consistency before deletion
        val toDelete = oldSessions.filter { session ->
            val report = consistency.checkSession(session.id)
            report.issues.isEmpty()  // Only delete if consistent
        }
        
        // Delete
        toDelete.forEach { session ->
            storage.deleteSession(session.id)
        }
        
        return Result.success()
    }
}
```

**Effort:** 1 day

---

## 5. Implementation Roadmap

### Phase 1: Critical Data Integrity (Week 1)

**Priority: HIGHEST**

1. **Disk Space Validation** (Issue 3.1)
   - Days 1-2: Implement `StorageValidator` utility
   - Day 3: Integrate with manifest/config writes
   - Day 4: Add UI error handling
   - Day 5: Testing

2. **Atomic Writes** (Issue 3.2)
   - Day 1: Implement `AtomicFileWriter`
   - Day 2: Integrate with critical file writes
   - Day 3: Testing and verification

### Phase 2: Protocol Improvements (Week 2)

**Priority: MEDIUM**

1. **Protocol Versioning** (Issue 1.1)
   - Days 1-2: Add version fields to proto files
   - Day 3: Implement version checking in services
   - Days 4-5: Testing and documentation

2. **Remove Embedded JSON** (Issue 1.2)
   - Days 1-2: Define typed message variants
   - Days 3-4: Gradual migration maintaining backwards compatibility
   - Day 5: Testing

### Phase 3: Serialisation Consistency (Week 3)

**Priority: MEDIUM**

1. **Centralise Json Config** (Issue 2.1)
   - Day 1: Create `JsonConfig` and Hilt module
   - Days 2-3: Migrate all usage points
   - Day 4: Testing
   - Day 5: Documentation

2. **Schema Validation** (Issue 2.2)
   - Days 1-2: Add validation to data classes
   - Days 3-4: Implement `LoadResult` pattern
   - Day 5: Testing

### Phase 4: Storage Robustness (Week 4)

**Priority: LOW**

1. **Consistency Checking** (Issue 4.2)
   - Days 1-2: Implement consistency checker
   - Day 3: Add periodic verification
   - Days 4-5: Testing

2. **Cleanup Automation** (Issue 4.4)
   - Days 1-2: Complete retention worker
   - Day 3: Configure scheduling
   - Days 4-5: Testing

---

## 6. Testing Strategy

### 6.1 Disk Space Testing

```kotlin
@Test
fun `writeManifest fails when insufficient disk space`() = runTest {
    // Mock File.usableSpace to return low value
    val result = manifestWriter.writeManifest(testManifest, "session123")
    
    assertThat(result).isInstanceOf<WriteResult.Failure.InsufficientSpace>()
}

@Test
fun `writeManifest succeeds when sufficient disk space`() = runTest {
    // Mock File.usableSpace to return high value
    val result = manifestWriter.writeManifest(testManifest, "session123")
    
    assertThat(result).isInstanceOf<WriteResult.Success>()
}
```

### 6.2 Atomic Write Testing

```kotlin
@Test
fun `atomic write recovers from crash during write`() = runTest {
    val target = tempDir.resolve("test.json")
    val content = "test content"
    
    // Simulate crash by injecting IOException
    // Verify temp file cleaned up
    // Verify original file intact
}

@Test
fun `atomic write preserves backup on failure`() = runTest {
    // Write initial file
    // Attempt write with simulated failure
    // Verify backup still exists
    // Verify can recover from backup
}
```

### 6.3 Protocol Version Testing

```kotlin
@Test
fun `rejects messages with unsupported protocol version`() {
    val command = CommandEnvelope.newBuilder()
        .setProtocolVersion(999)  // Future version
        .build()
        
    val result = commandHandler.handle(command)
    
    assertThat(result.accepted).isFalse()
    assertThat(result.reason).contains("Unsupported protocol version")
}
```

---

## 7. Monitoring and Alerts

### 7.1 Metrics to Track

```kotlin
// Add to performance metrics
data class StorageMetrics(
    val availableSpaceBytes: Long,
    val usedSpaceBytes: Long,
    val manifestWriteSuccessCount: Int,
    val manifestWriteFailureCount: Int,
    val diskSpaceWarningCount: Int,
    val atomicWriteRecoveryCount: Int,
    val consistencyCheckFailureCount: Int
)
```

### 7.2 Logging

```kotlin
// Add structured logging for storage operations
Timber.tag("Storage")
    .d("Manifest write: sessionId=%s, size=%d, duration=%dms",
        sessionId, payloadSize, duration)
        
Timber.tag("Storage").w("Low disk space: available=%s, required=%s",
    formatBytes(available), formatBytes(required))
    
Timber.tag("Storage").e(exception, "Atomic write recovery: file=%s", filename)
```

---

## 8. Documentation Requirements

### 8.1 Developer Documentation

1. **Storage Architecture Guide**
   - File structure and organisation
   - Write patterns (atomic, space checking)
   - Error handling patterns
   - Consistency checking

2. **Protocol Evolution Guide**
   - How to add new message types
   - Version compatibility matrix
   - Migration strategies

3. **JSON Serialisation Guide**
   - When to use each Json configuration
   - Validation requirements
   - Error handling patterns

### 8.2 API Documentation

Update all affected methods with:
- Return types (WriteResult, LoadResult)
- Possible failure modes
- Example usage
- Threading requirements

---

## 9. Risks and Mitigation

### 9.1 Migration Risks

| Risk | Impact | Likelihood | Mitigation |
|------|--------|------------|------------|
| Breaking existing sessions | HIGH | MEDIUM | Implement version migration, maintain backwards compatibility |
| Performance regression | MEDIUM | LOW | Benchmark before/after, atomic writes only for critical files |
| Storage overhead from backups | MEDIUM | HIGH | Implement cleanup policy, make backups optional per file type |
| Increased crash rate from strict validation | MEDIUM | MEDIUM | Gradual rollout with feature flags, extensive testing |

### 9.2 Rollback Plan

For each phase:
1. Implement behind feature flag
2. Deploy to small percentage of users
3. Monitor error rates and performance
4. Rollback if issues detected
5. Full deployment after stability confirmed

---

## 10. Success Criteria

### 10.1 Data Integrity

- ✅ Zero manifest corruption incidents
- ✅ Zero data loss due to disk space issues
- ✅ Successful recovery from power loss during write

### 10.2 Protocol Robustness

- ✅ Graceful handling of version mismatches
- ✅ Type-safe command handling (no runtime JSON errors)
- ✅ Clear error messages for protocol issues

### 10.3 Code Quality

- ✅ Single Json configuration pattern throughout app
- ✅ Consistent error handling (LoadResult, WriteResult)
- ✅ Comprehensive validation on all external data

---

## 11. References

### 11.1 Related Documents

- `ARCHITECTURE_SUMMARY.md` - Overall system architecture
- `ARCHITECTURAL_ISSUES.md` - Broader architectural concerns
- Protocol files: `protocol/src/main/proto/`

### 11.2 External Resources

- [Protocol Buffers Best Practices](https://protobuf.dev/programming-guides/api/)
- [Kotlinx.serialization Documentation](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/serialization-guide.md)
- [Android Storage Best Practices](https://developer.android.com/training/data-storage)

---

## Appendix A: File Locations Reference

### Protocol Buffers
- `protocol/src/main/proto/orchestration.proto` - Main orchestration protocol
- `protocol/src/main/proto/sync/control.proto` - Local control protocol

### JSON Serialisation (11 instances)
- `app/src/main/java/com/buccancs/application/performance/PerformanceMetricsAnalyzer.kt:18`
- `app/src/main/java/com/buccancs/application/performance/PerformanceMetricsRecorder.kt:37`
- `app/src/main/java/com/buccancs/data/calibration/CalibrationStorage.kt:24`
- `app/src/main/java/com/buccancs/data/recording/manifest/ManifestWriter.kt:25`
- `app/src/main/java/com/buccancs/data/sensor/config/DefaultSensorHardwareConfigRepository.kt:32`
- `app/src/main/java/com/buccancs/data/sensor/connector/shimmer/ShimmerSensorConnector.kt:75`
- `app/src/main/java/com/buccancs/data/transfer/BacklogTelemetryLogger.kt:19`
- `app/src/main/java/com/buccancs/data/transfer/UploadRecoveryLogger.kt:19`
- `app/src/main/java/com/buccancs/data/transfer/UploadWorker.kt:25`
- `app/src/main/java/com/buccancs/ui/MainViewModel.kt:91`

### Critical Write Operations
- `app/src/main/java/com/buccancs/data/recording/manifest/ManifestWriter.kt:158` - Manifest write
- `app/src/main/java/com/buccancs/data/calibration/CalibrationStorage.kt:55` - Calibration results
- `app/src/main/java/com/buccancs/data/sensor/config/DefaultSensorHardwareConfigRepository.kt:96` - Config save

---

**End of Document**
