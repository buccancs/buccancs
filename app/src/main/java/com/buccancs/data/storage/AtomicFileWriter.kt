package com.buccancs.data.storage

import android.util.Log
import com.buccancs.core.storage.StorageCheckResult
import com.buccancs.core.storage.StorageValidator
import com.buccancs.core.storage.WriteResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * Utility for atomic file writes with backup/recovery support.
 * Prevents data corruption during crashes by using temp files and atomic rename operations.
 */
object AtomicFileWriter {
    private const val TAG = "AtomicFileWriter"

    /**
     * Atomically write content to a file using temp file and atomic rename with storage validation.
     *
     * @param targetFile The final destination file
     * @param content The content to write
     * @param charset Character encoding (defaults to UTF-8)
     * @param checkSpace Whether to validate available disk space before writing
     * @return WriteResult indicating success or failure with details
     */
    suspend fun writeAtomicSafe(
        targetFile: File,
        content: String,
        charset: Charset = StandardCharsets.UTF_8,
        checkSpace: Boolean = true
    ): WriteResult<Unit> = withContext(Dispatchers.IO) {
        val contentBytes = content.toByteArray(charset)

        if (checkSpace) {
            when (val spaceCheck = StorageValidator.checkSpace(targetFile, contentBytes.size.toLong())) {
                is StorageCheckResult.Insufficient -> {
                    Log.e(
                        TAG, "Insufficient space: need ${StorageValidator.formatBytes(spaceCheck.required)}, " +
                                "have ${StorageValidator.formatBytes(spaceCheck.available)}"
                    )
                    return@withContext WriteResult.Failure.InsufficientSpace(
                        spaceCheck.required,
                        spaceCheck.available
                    )
                }

                is StorageCheckResult.Error -> {
                    Log.e(TAG, "Error checking space: ${spaceCheck.message}", spaceCheck.cause)
                    return@withContext WriteResult.Failure.WriteError(spaceCheck.message, spaceCheck.cause)
                }

                StorageCheckResult.Sufficient -> {
                    // Continue
                }
            }
        }

        val tempFile = File(targetFile.parentFile, "${targetFile.name}.tmp")
        val backupFile = File(targetFile.parentFile, "${targetFile.name}.bak")

        try {
            targetFile.parentFile?.mkdirs()

            tempFile.writeText(content, charset)

            tempFile.outputStream().use { stream ->
                stream.fd.sync()
            }

            if (targetFile.exists()) {
                if (backupFile.exists()) backupFile.delete()
                if (!targetFile.renameTo(backupFile)) {
                    targetFile.copyTo(backupFile, overwrite = true)
                }
            }

            if (!tempFile.renameTo(targetFile)) {
                tempFile.copyTo(targetFile, overwrite = true)
                tempFile.delete()
            }

            WriteResult.Success(Unit)

        } catch (e: Exception) {
            Log.e(TAG, "Failed to write file atomically: ${targetFile.name}", e)

            if (backupFile.exists() && !targetFile.exists()) {
                try {
                    backupFile.copyTo(targetFile, overwrite = true)
                    Log.i(TAG, "Restored ${targetFile.name} from backup")
                } catch (restoreError: Exception) {
                    Log.e(TAG, "Failed to restore from backup", restoreError)
                }
            }

            tempFile.delete()
            WriteResult.Failure.WriteError("Atomic write failed: ${e.message}", e)
        }
    }

    /**
     * Atomically write content to a file using temp file and atomic rename.
     *
     * @param targetFile The final destination file
     * @param content The content to write
     * @throws Exception if write fails
     */
    suspend fun writeAtomic(targetFile: File, content: String) {
        when (val result = writeAtomicSafe(targetFile, content, checkSpace = false)) {
            is WriteResult.Success -> return
            is WriteResult.Failure.WriteError -> throw Exception(result.message, result.cause)
            is WriteResult.Failure.InsufficientSpace ->
                throw Exception("Insufficient space: need ${result.required}, have ${result.available}")
        }
    }

    /**
     * Recover file from backup if primary is corrupted or missing.
     *
     * @param targetFile The file to recover
     * @return true if recovered successfully, false if no backup available
     */
    suspend fun recoverFromBackup(targetFile: File): Boolean {
        return withContext(Dispatchers.IO) {
            val backupFile = File(targetFile.parentFile, "${targetFile.name}.bak")

            if (backupFile.exists()) {
                try {
                    backupFile.copyTo(targetFile, overwrite = true)
                    Log.i(TAG, "Recovered ${targetFile.name} from backup")
                    true
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to recover from backup: ${targetFile.name}", e)
                    false
                }
            } else {
                Log.w(TAG, "No backup available for ${targetFile.name}")
                false
            }
        }
    }

    /**
     * Check if a backup exists for a file.
     */
    fun hasBackup(targetFile: File): Boolean {
        val backupFile = File(targetFile.parentFile, "${targetFile.name}.bak")
        return backupFile.exists()
    }

    /**
     * Clean up temp and backup files.
     */
    suspend fun cleanupTemporaryFiles(targetFile: File) {
        withContext(Dispatchers.IO) {
            val tempFile = File(targetFile.parentFile, "${targetFile.name}.tmp")
            val backupFile = File(targetFile.parentFile, "${targetFile.name}.bak")

            tempFile.delete()
            backupFile.delete()
        }
    }
}
