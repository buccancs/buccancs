package com.buccancs.data.transfer

import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadStatus
import java.util.Locale
import kotlin.math.log10
import kotlin.math.max
import kotlin.math.pow

internal object UploadBacklogCalculator {
    const val WARNING_BYTES: Long = 2L * 1024 * 1024 * 1024 // 2 GiB
    const val CRITICAL_BYTES: Long = 4L * 1024 * 1024 * 1024 // 4 GiB
    const val WARNING_COUNT: Int = 48
    const val CRITICAL_COUNT: Int = 96
    const val OVERFLOW_MESSAGE: String = "Dropped due to upload backlog overflow"

    fun shouldDrop(pendingBytes: Long, pendingCount: Int, artifactSize: Long): Boolean {
        val predictedBytes = pendingBytes + max(artifactSize, 0L)
        val predictedCount = pendingCount + 1
        return levelFor(predictedBytes, predictedCount) == UploadBacklogLevel.CRITICAL
    }

    fun snapshot(statuses: Collection<UploadStatus>): UploadBacklogState {
        val active = statuses.filter(::isActive)
        val queuedBytes = active.sumOf { (it.bytesTotal - it.bytesTransferred).coerceAtLeast(0) }
        val queuedCount = active.size
        val perSessionQueued = active.groupBy { it.sessionId }.mapValues { it.value.size }
        val perSessionBytes = active.groupBy { it.sessionId }
            .mapValues { entry ->
                entry.value.sumOf {
                    (it.bytesTotal - it.bytesTransferred).coerceAtLeast(
                        0
                    )
                }
            }
        val level = levelFor(queuedBytes, queuedCount)
        val message = when (level) {
            UploadBacklogLevel.NORMAL -> null
            UploadBacklogLevel.WARNING ->
                "Upload backlog warning: $queuedCount items pending (${formatBytes(queuedBytes)})."

            UploadBacklogLevel.CRITICAL ->
                "Upload backlog critical: throttling queue at $queuedCount items (${
                    formatBytes(
                        queuedBytes
                    )
                })."
        }
        return UploadBacklogState(
            level = level,
            queuedCount = queuedCount,
            queuedBytes = queuedBytes,
            message = message,
            perSessionQueued = perSessionQueued,
            perSessionBytes = perSessionBytes
        )
    }

    fun isActive(status: UploadStatus): Boolean {
        if (status.state.name == "COMPLETED") return false
        if (status.state.name == "FAILED" && status.errorMessage == OVERFLOW_MESSAGE) {
            return false
        }
        return true
    }

    private fun levelFor(bytes: Long, count: Int): UploadBacklogLevel = when {
        bytes >= CRITICAL_BYTES || count >= CRITICAL_COUNT -> UploadBacklogLevel.CRITICAL
        bytes >= WARNING_BYTES || count >= WARNING_COUNT -> UploadBacklogLevel.WARNING
        else -> UploadBacklogLevel.NORMAL
    }

    private fun formatBytes(bytes: Long): String {
        if (bytes <= 0) return "0 B"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (log10(bytes.toDouble()) / log10(1024.0)).toInt()
        val value = bytes / 1024.0.pow(digitGroups.toDouble())
        return String.format(Locale.US, "%.1f %s", value, units[digitGroups])
    }
}
