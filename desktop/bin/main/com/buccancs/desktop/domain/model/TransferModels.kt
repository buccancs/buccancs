package com.buccancs.desktop.domain.model

import java.time.Instant

data class FileTransferProgress(
    val sessionId: String,
    val deviceId: String,
    val fileName: String,
    val streamType: String?,
    val expectedBytes: Long,
    val receivedBytes: Long,
    val attempt: Int,
    val state: FileTransferState,
    val lastError: String? = null,
    val completedAt: Instant? = null
)

enum class FileTransferState {
    IN_PROGRESS,
    COMPLETED,
    FAILED
}
