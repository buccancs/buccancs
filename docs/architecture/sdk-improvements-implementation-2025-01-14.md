**Last Modified:** 2025-01-14 04:26 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Implementation Guide

# SDK Improvements - Implementation Guide

## Quick Start Priority List

### Must Do (Week 1)

1. ✅ Document SDK requirements and limitations
2. 🔧 Implement Shimmer state machine
3. 🔧 Enforce calibration quality thresholds
4. 📝 Document thermal frame format

### Should Do (Week 2-3)

5. 🔧 Add Shimmer circuit breaker
6. 🧹 Clean up Topdon external code
7. 🔧 Add thermal camera simulator

### Nice to Have (Week 4+)

8. 🔧 Wrap Shimmer in suspending functions
9. 🔧 Extract minimal Topdon SDK wrapper
10. 🔧 Add OpenCV fallback handling

## 1. Shimmer State Machine Implementation

### Problem

Current code has 14+ mutable state variables with implicit state transitions, making it difficult to reason about valid
states.

### Solution: Explicit State Machine

```kotlin
package com.buccancs.data.sensor.connector.shimmer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * Connection state for Shimmer device.
 */
sealed class ShimmerConnectionState {
    object Disconnected : ShimmerConnectionState()
    object Connecting : ShimmerConnectionState()
    data class Connected(
        val device: ShimmerDevice,
        val since: Instant,
        val mac: String
    ) : ShimmerConnectionState()
    data class Streaming(
        val device: ShimmerDevice,
        val anchor: RecordingSessionAnchor,
        val since: Instant
    ) : ShimmerConnectionState()
    data class Error(
        val error: Throwable,
        val previousState: ShimmerConnectionState,
        val at: Instant
    ) : ShimmerConnectionState()
    
    fun canConnect(): Boolean = this is Disconnected
    fun canDisconnect(): Boolean = this is Connected || this is Streaming
    fun canStartStreaming(): Boolean = this is Connected
    fun canStopStreaming(): Boolean = this is Streaming
}

/**
 * Recording state for Shimmer device.
 */
sealed class ShimmerRecordingState {
    object Idle : ShimmerRecordingState()
    data class Recording(
        val writer: ShimmerDataWriter,
        val startedAt: Instant,
        val sessionId: String
    ) : ShimmerRecordingState()
    data class Finalizing(
        val file: File,
        val startedAt: Instant
    ) : ShimmerRecordingState()
    
    fun deviceOrNull(): ShimmerDevice? = when (this) {
        is Recording -> writer.device
        else -> null
    }
}

/**
 * Events that trigger state transitions.
 */
sealed class ShimmerEvent {
    data class Connect(val mac: String, val name: String) : ShimmerEvent()
    object Disconnect : ShimmerEvent()
    data class StartStreaming(val anchor: RecordingSessionAnchor) : ShimmerEvent()
    object StopStreaming : ShimmerEvent()
    data class Error(val error: Throwable) : ShimmerEvent()
    data class SdkCallback(val type: CallbackType, val data: Any?) : ShimmerEvent()
    
    enum class CallbackType {
        STATE_CHANGE,
        DATA_PACKET,
        TOAST
    }
}

/**
 * State machine for Shimmer device lifecycle.
 */
class ShimmerStateMachine(
    private val deviceId: DeviceId
) {
    private val logTag = "ShimmerStateMachine-${deviceId.value}"
    
    private val _connectionState = MutableStateFlow<ShimmerConnectionState>(
        ShimmerConnectionState.Disconnected
    )
    val connectionState: StateFlow<ShimmerConnectionState> = _connectionState.asStateFlow()
    
    private val _recordingState = MutableStateFlow<ShimmerRecordingState>(
        ShimmerRecordingState.Idle
    )
    val recordingState: StateFlow<ShimmerRecordingState> = _recordingState.asStateFlow()
    
    /**
     * Process event and transition to new state.
     * Returns error if transition is invalid.
     */
    suspend fun processEvent(event: ShimmerEvent): Result<Unit> {
        return when (event) {
            is ShimmerEvent.Connect -> handleConnect(event)
            is ShimmerEvent.Disconnect -> handleDisconnect()
            is ShimmerEvent.StartStreaming -> handleStartStreaming(event)
            is ShimmerEvent.StopStreaming -> handleStopStreaming()
            is ShimmerEvent.Error -> handleError(event)
            is ShimmerEvent.SdkCallback -> handleSdkCallback(event)
        }
    }
    
    private suspend fun handleConnect(event: ShimmerEvent.Connect): Result<Unit> {
        return when (val current = _connectionState.value) {
            is ShimmerConnectionState.Disconnected -> {
                Log.d(logTag, "Transitioning: Disconnected -> Connecting")
                _connectionState.value = ShimmerConnectionState.Connecting
                Result.success(Unit)
            }
            else -> {
                val error = "Cannot connect from state: ${current::class.simpleName}"
                Log.w(logTag, error)
                Result.failure(IllegalStateException(error))
            }
        }
    }
    
    private suspend fun handleDisconnect(): Result<Unit> {
        return when (val current = _connectionState.value) {
            is ShimmerConnectionState.Connected,
            is ShimmerConnectionState.Streaming -> {
                Log.d(logTag, "Transitioning: ${current::class.simpleName} -> Disconnected")
                
                // Stop recording if active
                if (_recordingState.value is ShimmerRecordingState.Recording) {
                    stopRecording()
                }
                
                _connectionState.value = ShimmerConnectionState.Disconnected
                Result.success(Unit)
            }
            is ShimmerConnectionState.Connecting -> {
                // Allow disconnect during connection attempt
                _connectionState.value = ShimmerConnectionState.Disconnected
                Result.success(Unit)
            }
            is ShimmerConnectionState.Disconnected -> {
                // Already disconnected, no-op
                Result.success(Unit)
            }
            is ShimmerConnectionState.Error -> {
                // Can always disconnect from error state
                _connectionState.value = ShimmerConnectionState.Disconnected
                Result.success(Unit)
            }
        }
    }
    
    private suspend fun handleStartStreaming(event: ShimmerEvent.StartStreaming): Result<Unit> {
        return when (val current = _connectionState.value) {
            is ShimmerConnectionState.Connected -> {
                Log.d(logTag, "Transitioning: Connected -> Streaming")
                _connectionState.value = ShimmerConnectionState.Streaming(
                    device = current.device,
                    anchor = event.anchor,
                    since = Instant.fromEpochMilliseconds(System.currentTimeMillis())
                )
                
                // Start recording
                startRecording(event.anchor)
                
                Result.success(Unit)
            }
            else -> {
                val error = "Cannot start streaming from state: ${current::class.simpleName}"
                Log.w(logTag, error)
                Result.failure(IllegalStateException(error))
            }
        }
    }
    
    private suspend fun handleStopStreaming(): Result<Unit> {
        return when (val current = _connectionState.value) {
            is ShimmerConnectionState.Streaming -> {
                Log.d(logTag, "Transitioning: Streaming -> Connected")
                _connectionState.value = ShimmerConnectionState.Connected(
                    device = current.device,
                    since = current.since,
                    mac = "" // Retrieve from device
                )
                
                // Stop recording
                stopRecording()
                
                Result.success(Unit)
            }
            else -> {
                val error = "Cannot stop streaming from state: ${current::class.simpleName}"
                Log.w(logTag, error)
                Result.failure(IllegalStateException(error))
            }
        }
    }
    
    private suspend fun handleError(event: ShimmerEvent.Error): Result<Unit> {
        val current = _connectionState.value
        Log.e(logTag, "Error in state ${current::class.simpleName}: ${event.error.message}", event.error)
        
        _connectionState.value = ShimmerConnectionState.Error(
            error = event.error,
            previousState = current,
            at = Instant.fromEpochMilliseconds(System.currentTimeMillis())
        )
        
        // Clean up recording on error
        if (_recordingState.value is ShimmerRecordingState.Recording) {
            abortRecording()
        }
        
        return Result.success(Unit)
    }
    
    private suspend fun handleSdkCallback(event: ShimmerEvent.SdkCallback): Result<Unit> {
        // Handle SDK callbacks and update state accordingly
        return Result.success(Unit)
    }
    
    private suspend fun startRecording(anchor: RecordingSessionAnchor) {
        // Implementation
    }
    
    private suspend fun stopRecording() {
        when (val current = _recordingState.value) {
            is ShimmerRecordingState.Recording -> {
                _recordingState.value = ShimmerRecordingState.Finalizing(
                    file = current.writer.file,
                    startedAt = current.startedAt
                )
                // Finalize writer
                current.writer.finalize()
                _recordingState.value = ShimmerRecordingState.Idle
            }
            else -> {
                // No-op
            }
        }
    }
    
    private suspend fun abortRecording() {
        when (val current = _recordingState.value) {
            is ShimmerRecordingState.Recording -> {
                current.writer.abort(deleteFile = true)
                _recordingState.value = ShimmerRecordingState.Idle
            }
            else -> {
                // No-op
            }
        }
    }
}
```

### Usage in Connector

```kotlin
class ShimmerSensorConnector(...) {
    private val stateMachine = ShimmerStateMachine(deviceId)
    
    override suspend fun connect(): DeviceCommandResult {
        return when (val result = stateMachine.processEvent(ShimmerEvent.Connect(mac, name))) {
            is Result.Success -> {
                // Proceed with actual connection
                connectToHardware(mac, name)
            }
            is Result.Failure -> {
                DeviceCommandResult.Rejected(result.error.message ?: "Invalid state transition")
            }
        }
    }
}
```

### Benefits

- ✅ Clear state transitions
- ✅ Invalid transitions prevented
- ✅ Easier debugging (log all transitions)
- ✅ Testable without hardware
- ✅ Type-safe states

## 2. Calibration Quality Enforcement

### Problem

Calibrations with high reprojection errors are accepted, potentially causing bad measurements.

### Solution: Hard Quality Thresholds

```kotlin
package com.buccancs.data.calibration

/**
 * Quality check result for stereo calibration.
 */
data class CalibrationQualityCheck(
    val passed: Boolean,
    val issues: List<QualityIssue>,
    val warnings: List<String>,
    val metrics: CalibrationMetrics,
    val recommendation: String?
)

/**
 * Quality issue severity.
 */
data class QualityIssue(
    val severity: Severity,
    val message: String,
    val metric: String,
    val actualValue: Double,
    val threshold: Double
) {
    enum class Severity {
        CRITICAL,  // Reject calibration
        ERROR,     // Reject calibration
        WARNING    // Accept but warn
    }
}

/**
 * Quality thresholds for calibration.
 */
object CalibrationQualityThresholds {
    // Hard thresholds - calibration rejected if exceeded
    const val MAX_MEAN_REPROJECTION_ERROR = 2.0  // pixels
    const val MAX_MAX_REPROJECTION_ERROR = 5.0   // pixels
    const val MIN_IMAGE_COUNT = 5
    
    // Soft thresholds - warnings shown but calibration accepted
    const val TARGET_MEAN_REPROJECTION_ERROR = 1.0  // pixels
    const val TARGET_MAX_REPROJECTION_ERROR = 3.0   // pixels
    const val RECOMMENDED_IMAGE_COUNT = 10
}

/**
 * Validate calibration quality against thresholds.
 */
fun validateCalibrationQuality(
    result: StereoCalibrationResult,
    thresholds: CalibrationQualityThresholds = CalibrationQualityThresholds
): CalibrationQualityCheck {
    val issues = mutableListOf<QualityIssue>()
    val warnings = mutableListOf<String>()
    
    // Check mean reprojection error (CRITICAL)
    if (result.meanReprojectionError > thresholds.MAX_MEAN_REPROJECTION_ERROR) {
        issues.add(QualityIssue(
            severity = QualityIssue.Severity.CRITICAL,
            message = "Mean reprojection error exceeds maximum threshold",
            metric = "meanReprojectionError",
            actualValue = result.meanReprojectionError,
            threshold = thresholds.MAX_MEAN_REPROJECTION_ERROR
        ))
    } else if (result.meanReprojectionError > thresholds.TARGET_MEAN_REPROJECTION_ERROR) {
        warnings.add(
            "Mean reprojection error (%.3f px) exceeds target (%.1f px). " +
            "Consider recapturing with better lighting and focus."
                .format(result.meanReprojectionError, thresholds.TARGET_MEAN_REPROJECTION_ERROR)
        )
    }
    
    // Check max reprojection error (ERROR)
    if (result.maxReprojectionError > thresholds.MAX_MAX_REPROJECTION_ERROR) {
        issues.add(QualityIssue(
            severity = QualityIssue.Severity.ERROR,
            message = "Maximum reprojection error exceeds threshold",
            metric = "maxReprojectionError",
            actualValue = result.maxReprojectionError,
            threshold = thresholds.MAX_MAX_REPROJECTION_ERROR
        ))
    } else if (result.maxReprojectionError > thresholds.TARGET_MAX_REPROJECTION_ERROR) {
        warnings.add(
            "Maximum reprojection error (%.3f px) is elevated. " +
            "Some captures may have poor corner detection."
                .format(result.maxReprojectionError)
        )
    }
    
    // Check image count (ERROR)
    if (result.imageCount < thresholds.MIN_IMAGE_COUNT) {
        issues.add(QualityIssue(
            severity = QualityIssue.Severity.ERROR,
            message = "Insufficient calibration images",
            metric = "imageCount",
            actualValue = result.imageCount.toDouble(),
            threshold = thresholds.MIN_IMAGE_COUNT.toDouble()
        ))
    } else if (result.imageCount < thresholds.RECOMMENDED_IMAGE_COUNT) {
        warnings.add(
            "Image count (${ result.imageCount}) is below recommended (${thresholds.RECOMMENDED_IMAGE_COUNT}). " +
            "More images improve calibration accuracy."
        )
    }
    
    // Generate recommendation
    val recommendation = when {
        issues.isNotEmpty() -> generateRecommendation(issues, result)
        warnings.isNotEmpty() -> "Calibration acceptable but could be improved. See warnings."
        else -> "Excellent calibration quality!"
    }
    
    return CalibrationQualityCheck(
        passed = issues.none { it.severity == QualityIssue.Severity.CRITICAL || it.severity == QualityIssue.Severity.ERROR },
        issues = issues,
        warnings = warnings,
        metrics = result.metrics,
        recommendation = recommendation
    )
}

private fun generateRecommendation(
    issues: List<QualityIssue>,
    result: StereoCalibrationResult
): String {
    val recommendations = mutableListOf<String>()
    
    if (issues.any { it.metric == "meanReprojectionError" || it.metric == "maxReprojectionError" }) {
        recommendations.add("Improve corner detection:")
        recommendations.add("  - Ensure good lighting (bright, even)")
        recommendations.add("  - Keep chessboard in focus")
        recommendations.add("  - Avoid motion blur")
        recommendations.add("  - Ensure entire pattern is visible")
    }
    
    if (issues.any { it.metric == "imageCount" }) {
        recommendations.add("Capture more calibration images:")
        recommendations.add("  - Minimum: ${CalibrationQualityThresholds.MIN_IMAGE_COUNT}")
        recommendations.add("  - Recommended: ${CalibrationQualityThresholds.RECOMMENDED_IMAGE_COUNT}+")
        recommendations.add("  - Vary angles and distances")
    }
    
    return recommendations.joinToString("\n")
}
```

### Usage in UI

```kotlin
// In CalibrationViewModel or Repository
suspend fun computeCalibration(): Result<StereoCalibrationResult> {
    val result = performCalibration()
    
    // Validate quality
    val qualityCheck = validateCalibrationQuality(result)
    
    if (!qualityCheck.passed) {
        return Result.failure(
            CalibrationQualityException(
                message = "Calibration quality insufficient",
                issues = qualityCheck.issues,
                recommendation = qualityCheck.recommendation
            )
        )
    }
    
    // Log warnings even if passed
    qualityCheck.warnings.forEach { warning ->
        Log.w(TAG, "Calibration warning: $warning")
    }
    
    return Result.success(result)
}

// In UI
when (val result = viewModel.computeCalibration()) {
    is Result.Success -> {
        showSuccess("Calibration complete!")
        // Show warnings if any
    }
    is Result.Failure -> {
        when (val error = result.error) {
            is CalibrationQualityException -> {
                showQualityError(error.issues, error.recommendation)
            }
            else -> showError(error.message)
        }
    }
}
```

## 3. Circuit Breaker Pattern

### Implementation

```kotlin
package com.buccancs.data.sensor.connector.shimmer

import kotlinx.coroutines.delay
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * Circuit breaker for Shimmer connection failures.
 * Prevents repeated connection attempts to failing device.
 */
class ShimmerCircuitBreaker(
    private val failureThreshold: Int = 5,
    private val resetTimeoutMs: Long = 60_000L,
    private val halfOpenRetries: Int = 1
) {
    private val failureCount = AtomicInteger(0)
    private val lastFailureTime = AtomicLong(0)
    @Volatile
    private var state = State.CLOSED
    
    enum class State {
        CLOSED,     // Normal operation, requests pass through
        OPEN,       // Too many failures, reject all requests
        HALF_OPEN   // Testing if service recovered
    }
    
    /**
     * Execute operation with circuit breaker protection.
     */
    suspend fun <T> execute(
        operation: suspend () -> T
    ): Result<T> {
        return when (state) {
            State.OPEN -> handleOpenState(operation)
            State.HALF_OPEN -> handleHalfOpenState(operation)
            State.CLOSED -> handleClosedState(operation)
        }
    }
    
    private suspend fun <T> handleOpenState(
        operation: suspend () -> T
    ): Result<T> {
        val timeSinceFailure = System.currentTimeMillis() - lastFailureTime.get()
        
        return if (timeSinceFailure > resetTimeoutMs) {
            // Try transitioning to half-open
            state = State.HALF_OPEN
            Log.i(TAG, "Circuit breaker: OPEN -> HALF_OPEN (testing recovery)")
            handleHalfOpenState(operation)
        } else {
            // Still too soon, reject immediately
            val remainingMs = resetTimeoutMs - timeSinceFailure
            Result.failure(
                CircuitBreakerOpenException(
                    "Circuit breaker open due to ${failureCount.get()} failures. " +
                    "Retry in ${remainingMs / 1000}s"
                )
            )
        }
    }
    
    private suspend fun <T> handleHalfOpenState(
        operation: suspend () -> T
    ): Result<T> {
        return try {
            val result = operation()
            // Success! Close circuit
            reset()
            Log.i(TAG, "Circuit breaker: HALF_OPEN -> CLOSED (recovery successful)")
            Result.success(result)
        } catch (e: Exception) {
            // Still failing, reopen circuit
            recordFailure()
            Log.w(TAG, "Circuit breaker: HALF_OPEN -> OPEN (recovery failed)")
            Result.failure(e)
        }
    }
    
    private suspend fun <T> handleClosedState(
        operation: suspend () -> T
    ): Result<T> {
        return try {
            val result = operation()
            // Success, reset failure count
            if (failureCount.get() > 0) {
                failureCount.set(0)
            }
            Result.success(result)
        } catch (e: Exception) {
            recordFailure()
            Result.failure(e)
        }
    }
    
    private fun recordFailure() {
        val failures = failureCount.incrementAndGet()
        lastFailureTime.set(System.currentTimeMillis())
        
        if (failures >= failureThreshold && state != State.OPEN) {
            state = State.OPEN
            Log.w(TAG, "Circuit breaker: CLOSED -> OPEN after $failures failures")
        }
    }
    
    private fun reset() {
        failureCount.set(0)
        state = State.CLOSED
    }
    
    /**
     * Get current circuit breaker status.
     */
    fun getStatus(): Status {
        return Status(
            state = state,
            failureCount = failureCount.get(),
            lastFailureTime = lastFailureTime.get(),
            resetTimeoutMs = resetTimeoutMs
        )
    }
    
    data class Status(
        val state: State,
        val failureCount: Int,
        val lastFailureTime: Long,
        val resetTimeoutMs: Long
    ) {
        fun isOpen(): Boolean = state == State.OPEN
        fun canRetry(): Boolean = state != State.OPEN
        fun timeUntilRetry(): Long {
            if (state != State.OPEN) return 0
            val elapsed = System.currentTimeMillis() - lastFailureTime
            return (resetTimeoutMs - elapsed).coerceAtLeast(0)
        }
    }
    
    companion object {
        private const val TAG = "ShimmerCircuitBreaker"
    }
}

class CircuitBreakerOpenException(message: String) : Exception(message)
```

### Usage

```kotlin
class ShimmerSensorConnector(...) {
    private val circuitBreaker = ShimmerCircuitBreaker(
        failureThreshold = 5,
        resetTimeoutMs = 60_000L
    )
    
    override suspend fun connect(): DeviceCommandResult {
        return when (val result = circuitBreaker.execute {
            connectToHardware()
        }) {
            is Result.Success -> DeviceCommandResult.Accepted
            is Result.Failure -> when (val error = result.error) {
                is CircuitBreakerOpenException -> {
                    DeviceCommandResult.Rejected(error.message ?: "Too many connection failures")
                }
                else -> DeviceCommandResult.Failed(error)
            }
        }
    }
}
```

## Summary

These three implementations address the highest priority issues:

1. **State Machine** - Makes Shimmer connection state explicit and manageable
2. **Quality Enforcement** - Prevents bad calibrations from being used
3. **Circuit Breaker** - Prevents battery drain from repeated failed connections

Each can be implemented independently and tested without hardware using simulators.

**Next Steps:**

1. Implement state machine (highest impact)
2. Add quality enforcement (prevents bad data)
3. Add circuit breaker (improves user experience)
4. Document thermal frame format
5. Clean up Topdon external code
