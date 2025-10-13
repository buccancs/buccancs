package com.buccancs.data.storage

import android.content.Context
import android.os.StatFs
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlin.math.max
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetentionWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val storage = RecordingStorage(applicationContext)
        val minFreeBytes = inputData.getLong(KEY_MIN_FREE_BYTES, DEFAULT_MIN_FREE_BYTES)
        val maxSessions = inputData.getInt(KEY_MAX_SESSIONS, DEFAULT_MAX_SESSIONS)
        val maxAgeMs = inputData.getLong(KEY_MAX_SESSION_AGE_MS, DEFAULT_MAX_SESSION_AGE_MS)
        val now = System.currentTimeMillis()
        val root = storage.recordingsRoot()
        val initialStats = filesystemStats(root)
        var availableBytes = initialStats.availableBytes
        val sessions = storage.sessionIds().mapNotNull { sessionId ->
            val directory = storage.sessionDirectoryOrNull(sessionId) ?: return@mapNotNull null
            val size = storage.directorySize(directory)
            SessionUsage(
                sessionId = sessionId,
                bytes = size,
                lastModifiedEpochMs = directory.lastModified()
            ) to directory
        }.sortedBy { it.first.lastModifiedEpochMs }

        val mutableSessions = sessions.toMutableList()
        var deletedCount = 0
        var reclaimedBytes = 0L

        fun deleteSession(index: Int) {
            val (usage, directory) = mutableSessions.removeAt(index)
            val deleted = if (directory.exists()) directory.deleteRecursively() else true
            if (deleted) {
                deletedCount += 1
                reclaimedBytes += usage.bytes
                availableBytes += usage.bytes
            }
        }

        if (maxAgeMs > 0) {
            var idx = 0
            while (idx < mutableSessions.size) {
                val (usage, _) = mutableSessions[idx]
                if (now - usage.lastModifiedEpochMs > maxAgeMs) {
                    deleteSession(idx)
                } else {
                    idx += 1
                }
            }
        }

        while (mutableSessions.size > maxSessions && mutableSessions.isNotEmpty()) {
            deleteSession(0)
        }

        val updatedStats = filesystemStats(root)
        availableBytes = max(availableBytes, updatedStats.availableBytes)
        while (availableBytes < minFreeBytes && mutableSessions.isNotEmpty()) {
            deleteSession(0)
        }

        Result.success(
            Data.Builder()
                .putInt(OUTPUT_DELETED_SESSIONS, deletedCount)
                .putLong(OUTPUT_RECLAIMED_BYTES, reclaimedBytes)
                .build()
        )
    }

    private data class FsStats(
        val totalBytes: Long,
        val availableBytes: Long
    )

    private fun filesystemStats(root: java.io.File): FsStats {
        val stat = StatFs(root.absolutePath)
        val blockSize = stat.blockSizeLong
        val total = stat.blockCountLong * blockSize
        val available = stat.availableBlocksLong * blockSize
        return FsStats(totalBytes = total, availableBytes = available)
    }

    companion object {
        const val KEY_MIN_FREE_BYTES = "min_free_bytes"
        const val KEY_MAX_SESSIONS = "max_sessions"
        const val KEY_MAX_SESSION_AGE_MS = "max_session_age_ms"
        const val OUTPUT_DELETED_SESSIONS = "deleted_sessions"
        const val OUTPUT_RECLAIMED_BYTES = "reclaimed_bytes"

        const val DEFAULT_MAX_SESSIONS = 48
        const val DEFAULT_MIN_FREE_BYTES = 6L * 1024 * 1024 * 1024
        const val DEFAULT_MAX_SESSION_AGE_MS = 30L * 24L * 60L * 60L * 1000L
    }
}
