package com.buccancs.data.transfer

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.buccancs.data.storage.RetentionWorker
import java.util.concurrent.TimeUnit

object WorkPolicy {
    private const val UPLOAD_WORK_NAME =
        "session-artifact-upload"
    private const val RETENTION_WORK_NAME =
        "session-storage-retention"

    fun enqueueUpload(
        context: Context
    ) {
        val constraints =
            Constraints.Builder()
                .setRequiredNetworkType(
                    NetworkType.CONNECTED
                )
                .build()
        val request =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .setConstraints(
                    constraints
                )
                .build()
        WorkManager.getInstance(
            context
        )
            .enqueueUniqueWork(
                UPLOAD_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                request
            )
    }

    fun scheduleRetention(
        context: Context,
        minFreeBytes: Long = RetentionWorker.DEFAULT_MIN_FREE_BYTES,
        maxSessions: Int = RetentionWorker.DEFAULT_MAX_SESSIONS,
        maxAgeDays: Long = TimeUnit.MILLISECONDS.toDays(
            RetentionWorker.DEFAULT_MAX_SESSION_AGE_MS
        )
    ) {
        val data =
            workDataOf(
                RetentionWorker.KEY_MIN_FREE_BYTES to minFreeBytes,
                RetentionWorker.KEY_MAX_SESSIONS to maxSessions,
                RetentionWorker.KEY_MAX_SESSION_AGE_MS to TimeUnit.DAYS.toMillis(
                    maxAgeDays
                )
            )
        val request =
            PeriodicWorkRequestBuilder<RetentionWorker>(
                12,
                TimeUnit.HOURS
            )
                .setInputData(
                    data
                )
                .build()
        WorkManager.getInstance(
            context
        )
            .enqueueUniquePeriodicWork(
                RETENTION_WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
    }
}
