package com.buccancs.core.result

import android.bluetooth.BluetoothAdapter
import java.io.IOException

/**
 * Helper functions for Bluetooth-specific Result operations.
 */

/**
 * Checks if Bluetooth adapter is available and enabled.
 */
fun BluetoothAdapter?.checkAvailable(): Result<BluetoothAdapter> =
    when {
        this == null -> Result.Failure(
            Error.Bluetooth(
                null,
                "Bluetooth not available on this device"
            )
        )

        !isEnabled -> Result.Failure(
            Error.Bluetooth(
                null,
                "Bluetooth is disabled"
            )
        )

        else -> Result.Success(
            this
        )
    }

/**
 * Wraps Bluetooth connection operations with appropriate error handling.
 */
suspend inline fun <T> bluetoothOperation(
    deviceId: String? = null,
    crossinline operation: suspend () -> T
): Result<T> =
    try {
        Result.Success(
            operation()
        )
    } catch (ex: kotlinx.coroutines.CancellationException) {
        throw ex
    } catch (ex: SecurityException) {
        Result.Failure(
            Error.Permission(
                "Bluetooth permission required",
                "BLUETOOTH_CONNECT",
                ex
            )
        )
    } catch (ex: IOException) {
        Result.Failure(
            Error.Bluetooth(
                deviceId,
                "Bluetooth I/O operation failed: ${ex.message}",
                ex
            )
        )
    } catch (ex: IllegalArgumentException) {
        Result.Failure(
            Error.Validation(
                "deviceId",
                "Invalid device identifier: ${ex.message}",
                ex
            )
        )
    } catch (ex: Exception) {
        Result.Failure(
            Error.Bluetooth(
                deviceId,
                "Bluetooth operation failed: ${ex.message}",
                ex
            )
        )
    }
