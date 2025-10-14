package com.buccancs.core.result

import java.io.File
import java.io.IOException

/**
 * Helper functions for storage-specific Result operations.
 */

/**
 * Wraps file I/O operations with appropriate error handling.
 */
inline fun <T> storageOperation(
    crossinline operation: () -> T
): Result<T> = try {
    Result.Success(operation())
} catch (ex: SecurityException) {
    Result.Failure(
        Error.Permission(
            "Storage permission required",
            "WRITE_EXTERNAL_STORAGE",
            ex
        )
    )
} catch (ex: IOException) {
    Result.Failure(
        Error.Storage(
            "Storage operation failed: ${ex.message}",
            ex
        )
    )
} catch (ex: IllegalArgumentException) {
    Result.Failure(
        Error.Validation(
            "path",
            "Invalid file path: ${ex.message}",
            ex
        )
    )
} catch (ex: Exception) {
    Result.Failure(
        Error.Storage(
            "Unexpected storage error: ${ex.message}",
            ex
        )
    )
}

/**
 * Ensures a directory exists and is writable.
 */
fun File.ensureDirectory(): Result<File> = when {
    !exists() && !mkdirs() -> Result.Failure(
        Error.Storage("Failed to create directory: $absolutePath")
    )
    !isDirectory -> Result.Failure(
        Error.Storage("Path exists but is not a directory: $absolutePath")
    )
    !canWrite() -> Result.Failure(
        Error.Permission("Directory is not writable: $absolutePath", "WRITE_EXTERNAL_STORAGE")
    )
    else -> Result.Success(this)
}

/**
 * Checks if a file exists and is readable.
 */
fun File.ensureReadable(): Result<File> = when {
    !exists() -> Result.Failure(
        Error.NotFound("File does not exist: $absolutePath")
    )
    !isFile -> Result.Failure(
        Error.Validation("path", "Path is not a file: $absolutePath")
    )
    !canRead() -> Result.Failure(
        Error.Permission("File is not readable: $absolutePath", "READ_EXTERNAL_STORAGE")
    )
    else -> Result.Success(this)
}

/**
 * Safely deletes a file or directory.
 */
fun File.safeDelete(): Result<Unit> = try {
    when {
        !exists() -> Result.Success(Unit)
        deleteRecursively() -> Result.Success(Unit)
        else -> Result.Failure(
            Error.Storage("Failed to delete: $absolutePath")
        )
    }
} catch (ex: Exception) {
    Result.Failure(
        Error.Storage("Error deleting file: ${ex.message}", ex)
    )
}
