package com.buccancs.domain.model

import java.io.File

data class SessionArtifact(
    val deviceId: DeviceId,
    val streamType: SensorStreamType,
    val file: File,
    val mimeType: String,
    val sizeBytes: Long,
    val checksumSha256: ByteArray
)

data class UploadStatus(
    val sessionId: String,
    val deviceId: DeviceId,
    val streamType: SensorStreamType,
    val fileName: String,
    val bytesTotal: Long,
    val bytesTransferred: Long,
    val attempt: Int,
    val state: UploadState,
    val errorMessage: String? = null
)

enum class UploadState {
    QUEUED,
    IN_PROGRESS,
    COMPLETED,
    FAILED
}
