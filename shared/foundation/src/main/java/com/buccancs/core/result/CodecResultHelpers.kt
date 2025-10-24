package com.buccancs.core.result

import android.media.MediaCodec
import java.io.IOException

/**
 * Helper functions for MediaCodec-specific Result operations.
 */

/**
 * Wraps MediaCodec operations with appropriate error handling.
 */
inline fun <T> codecOperation(
    crossinline operation: () -> T
): Result<T> =
    try {
        Result.Success(
            operation()
        )
    } catch (ex: MediaCodec.CodecException) {
        Result.Failure(
            Error.Codec(
                "Codec error: ${ex.diagnosticInfo}",
                ex
            )
        )
    } catch (ex: IllegalStateException) {
        Result.Failure(
            Error.Codec(
                "Invalid codec state: ${ex.message}",
                ex
            )
        )
    } catch (ex: IOException) {
        Result.Failure(
            Error.Codec(
                "Codec I/O error: ${ex.message}",
                ex
            )
        )
    } catch (ex: Exception) {
        Result.Failure(
            Error.Codec(
                "Unexpected codec error: ${ex.message}",
                ex
            )
        )
    }

/**
 * Safely releases a MediaCodec instance.
 */
fun MediaCodec.safeRelease(): Result<Unit> =
    try {
        stop()
        release()
        Result.Success(
            Unit
        )
    } catch (ex: IllegalStateException) {
        // Codec might already be stopped, just try to release
        try {
            release()
            Result.Success(
                Unit
            )
        } catch (releaseEx: Exception) {
            Result.Failure(
                Error.Codec(
                    "Failed to release codec: ${releaseEx.message}",
                    releaseEx
                )
            )
        }
    } catch (ex: Exception) {
        Result.Failure(
            Error.Codec(
                "Failed to stop/release codec: ${ex.message}",
                ex
            )
        )
    }
