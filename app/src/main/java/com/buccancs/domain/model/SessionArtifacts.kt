package com.buccancs.domain.model

import android.net.Uri
import java.io.File

data class SessionArtifact(
    val deviceId: DeviceId,
    val streamType: SensorStreamType,
    val uri: Uri,
    val file: File?,
    val mimeType: String,
    val sizeBytes: Long,
    val checksumSha256: ByteArray,
    val metadata: Map<String, String> = emptyMap()
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
