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
    private const val SAFETY_MULTIPLIER = 2.0
    private const val MIN_FREE_BYTES = 100 * 1024 * 1024L

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

    fun formatBytes(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            bytes < 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024)} MB"
            else -> "${bytes / (1024 * 1024 * 1024)} GB"
        }
    }
}

fun File.hasSpaceFor(bytes: Long): StorageCheckResult {
    return StorageValidator.checkSpace(this, bytes)
}
