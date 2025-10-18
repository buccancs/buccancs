@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.buccancs.domain.model

import android.net.Uri
import java.io.File
import kotlin.time.Instant

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

data class UploadRecoveryRecord(
    val sessionId: String,
    val deviceId: DeviceId,
    val streamType: SensorStreamType,
    val attempt: Int,
    val state: UploadState,
    val timestamp: Instant,
    val bytesTransferred: Long,
    val bytesTotal: Long,
    val network: NetworkSnapshot,
    val errorMessage: String? = null
)

data class NetworkSnapshot(
    val connected: Boolean,
    val transport: String,
    val metered: Boolean
)

data class UploadBacklogState(
    val level: UploadBacklogLevel,
    val queuedCount: Int,
    val queuedBytes: Long,
    val message: String?,
    val perSessionQueued: Map<String, Int> = emptyMap(),
    val perSessionBytes: Map<String, Long> = emptyMap()
)

enum class UploadBacklogLevel {
    NORMAL,
    WARNING,
    CRITICAL
}

enum class UploadState {
    QUEUED,
    IN_PROGRESS,
    COMPLETED,
    FAILED
}
