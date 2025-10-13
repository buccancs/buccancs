package com.buccancs.data.transfer

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.buccancs.data.recording.manifest.SessionManifest
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import dagger.hilt.EntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class UploadWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val entryPoint = EntryPointAccessors.fromApplication(
            applicationContext,
            UploadWorkerEntryPoint::class.java
        )
        val client = entryPoint.dataTransferClient()
        val storage = entryPoint.recordingStorage()
        val sessionIds = storage.sessionIds()
        var failures = 0
        sessionIds.forEach { sessionId ->
            val manifest = loadManifest(storage.manifestFile(sessionId)) ?: return@forEach
            val sessionDir = storage.sessionDirectory(sessionId)
            manifest.artifacts.forEach { artifactEntry ->
                val file = File(sessionDir, artifactEntry.relativePath)
                if (!file.exists()) {
                    return@forEach
                }
                val artifact = toSessionArtifact(artifactEntry, file) ?: return@forEach
                val result = client.upload(sessionId, artifact) { }
                if (result.success) {
                    file.delete()
                } else {
                    failures += 1
                    Log.w(TAG, "Upload failure for ${artifact.file.name}: ${result.errorMessage}")
                }
            }
        }
        if (failures == 0) Result.success() else Result.retry()
    }

    private fun loadManifest(file: File): SessionManifest? {
        if (!file.exists()) {
            return null
        }
        return runCatching {
            json.decodeFromString(SessionManifest.serializer(), file.readText())
        }.getOrNull()
    }

    private fun toSessionArtifact(entry: com.buccancs.data.recording.manifest.ArtifactEntry, file: File): SessionArtifact? {
        val streamType = runCatching { SensorStreamType.valueOf(entry.streamType) }.getOrNull()
            ?: return null
        val checksum = entry.checksumSha256.decodeHex()
        return SessionArtifact(
            deviceId = DeviceId(entry.deviceId),
            streamType = streamType,
            file = file,
            mimeType = entry.mimeType,
            sizeBytes = file.length(),
            checksumSha256 = checksum
        )
    }

    private fun String.decodeHex(): ByteArray {
        val clean = trim()
        if (clean.isEmpty()) {
            return ByteArray(0)
        }
        val length = clean.length
        val result = ByteArray(length / 2)
        var index = 0
        while (index < length) {
            val first = Character.digit(clean[index], 16)
            val second = Character.digit(clean[index + 1], 16)
            if (first == -1 || second == -1) {
                return ByteArray(0)
            }
            result[index / 2] = ((first shl 4) or second).toByte()
            index += 2
        }
        return result
    }

    @EntryPoint
    @dagger.hilt.InstallIn(SingletonComponent::class)
    interface UploadWorkerEntryPoint {
        fun dataTransferClient(): DataTransferClient
        fun recordingStorage(): RecordingStorage
    }

    companion object {
        private const val TAG = "UploadWorker"
    }
}
