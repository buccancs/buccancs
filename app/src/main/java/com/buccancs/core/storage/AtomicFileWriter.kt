package com.buccancs.core.storage

import android.util.Log
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8

sealed class WriteResult<out T> {
    data class Success<T>(val value: T) : WriteResult<T>()
    sealed class Failure : WriteResult<Nothing>() {
        data class InsufficientSpace(val required: Long, val available: Long) : Failure()
        data class WriteError(val message: String, val cause: Throwable?) : Failure()
    }
}

object AtomicFileWriter {
    private const val TAG = "AtomicFileWriter"

    fun writeTextAtomic(
        target: File,
        content: String,
        charset: Charset = UTF_8,
        createBackup: Boolean = true
    ): WriteResult<Unit> {
        val tempFile = File(target.parentFile, "${target.name}.tmp")
        val backupFile = File(target.parentFile, "${target.name}.bak")

        return try {
            target.parentFile?.mkdirs()

            tempFile.writeText(content, charset)

            tempFile.outputStream().use { stream ->
                stream.fd.sync()
            }

            if (createBackup && target.exists()) {
                if (backupFile.exists()) backupFile.delete()
                if (!target.renameTo(backupFile)) {
                    Log.w(TAG, "Failed to create backup for ${target.name}")
                }
            }

            if (!tempFile.renameTo(target)) {
                throw IOException("Failed to rename temp file to target")
            }

            if (!createBackup && backupFile.exists()) {
                backupFile.delete()
            }

            WriteResult.Success(Unit)
        } catch (e: IOException) {
            tempFile.delete()
            WriteResult.Failure.WriteError("Atomic write failed: ${e.message}", e)
        } catch (e: Exception) {
            tempFile.delete()
            WriteResult.Failure.WriteError("Unexpected error: ${e.message}", e)
        }
    }

    fun recoverFromBackup(target: File): Boolean {
        val backupFile = File(target.parentFile, "${target.name}.bak")
        return if (backupFile.exists()) {
            try {
                backupFile.copyTo(target, overwrite = true)
                Log.i(TAG, "Recovered ${target.name} from backup")
                true
            } catch (e: Exception) {
                Log.e(TAG, "Failed to recover from backup", e)
                false
            }
        } else {
            false
        }
    }
}

fun File.writeTextAtomic(
    content: String,
    charset: Charset = UTF_8,
    createBackup: Boolean = true
): WriteResult<Unit> {
    return AtomicFileWriter.writeTextAtomic(this, content, charset, createBackup)
}
