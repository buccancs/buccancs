package com.buccancs.core.timeout

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout

/**
 * Standard timeout values for hardware operations.
 * All values in milliseconds.
 */
object HardwareTimeouts {
    // Bluetooth operations
    const val BLUETOOTH_SCAN = 30_000L
    const val BLUETOOTH_CONNECT = 15_000L
    const val BLUETOOTH_DISCONNECT = 5_000L
    const val BLUETOOTH_READ = 3_000L
    const val BLUETOOTH_WRITE = 3_000L
    
    // USB/Camera operations
    const val CAMERA_OPEN = 10_000L
    const val CAMERA_CONFIGURE = 5_000L
    const val CAMERA_CAPTURE = 2_000L
    const val CAMERA_CLOSE = 3_000L
    const val USB_DETECT = 5_000L
    
    // Network operations
    const val NETWORK_CONNECT = 10_000L
    const val NETWORK_REQUEST = 30_000L
    const val FILE_UPLOAD = 300_000L  // 5 minutes for large files
    const val GRPC_CALL = 15_000L
    
    // File I/O operations
    const val FILE_READ = 10_000L
    const val FILE_WRITE = 30_000L
    const val MANIFEST_WRITE = 5_000L
    
    // Sensor operations
    const val SENSOR_START = 5_000L
    const val SENSOR_STOP = 3_000L
    const val SENSOR_CALIBRATION = 10_000L
}

/**
 * Extension function to add timeout with hardware-specific error message.
 */
suspend fun <T> withHardwareTimeout(
    timeoutMs: Long,
    operation: String,
    block: suspend () -> T
): T {
    return try {
        withTimeout(timeoutMs) {
            block()
        }
    } catch (e: TimeoutCancellationException) {
        throw HardwareTimeoutException("$operation timed out after ${timeoutMs}ms", e)
    }
}

class HardwareTimeoutException(message: String, cause: Throwable? = null) : Exception(message, cause)
