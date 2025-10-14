package com.buccancs.data.transfer

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.buccancs.core.serialization.StandardJson
import com.buccancs.data.recording.manifest.SessionManifest
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import dagger.hilt.EntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File

class UploadWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val entryPoint = EntryPointAccessors.fromApplication(
            applicationContext,
            UploadWorkerEntryPoint::class.java
        )
        val client = entryPoint.dataTransferClient()
        val storage = entryPoint.recordingStorage()
        val json = entryPoint.json()
        val sessionIds = storage.sessionIds()
        var failures = 0
        sessionIds.forEach { sessionId ->
            val manifest = loadManifest(storage.manifestFile(sessionId), json) ?: return@forEach
            manifest.artifacts.forEach { artifactEntry ->
                val artifact = toSessionArtifact(sessionId, artifactEntry, storage) ?: return@forEach
                val result = client.upload(sessionId, artifact) { }
                if (result.success) {
                    artifact.file?.delete()
                } else {
                    failures += 1
                    val name = artifact.file?.name ?: artifact.uri.toString()
                    Log.w(TAG, "Upload failure for $name: ${result.errorMessage}")
                }
            }
        }
        if (failures == 0) Result.success() else Result.retry()
    }

    private fun loadManifest(file: java.io.File, json: Json): SessionManifest? {
        if (!file.exists()) {
            return null
        }
        return runCatching {
            json.decodeFromString(SessionManifest.serializer(), file.readText())
        }.getOrNull()
    }

    private fun toSessionArtifact(
        sessionId: String,
        entry: com.buccancs.data.recording.manifest.ArtifactEntry,
        storage: RecordingStorage
    ): SessionArtifact? {
        val streamType = runCatching { SensorStreamType.valueOf(entry.streamType) }.getOrNull()
            ?: return null
        val checksum = entry.checksumSha256.decodeHex()
        val file = entry.relativePath?.let {
            val directory = storage.sessionDirectory(sessionId)
            File(directory, it).takeIf(File::exists)
        }
        val uri = entry.contentUri?.let { Uri.parse(it) }
            ?: file?.let { Uri.fromFile(it) }
            ?: return null
        return SessionArtifact(
            deviceId = DeviceId(entry.deviceId),
            streamType = streamType,
            uri = uri,
            file = file,
            mimeType = entry.mimeType,
            sizeBytes = entry.sizeBytes.takeIf { it > 0 } ?: file?.length() ?: 0,
            checksumSha256 = checksum,
            metadata = entry.metadata
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
        @StandardJson
        fun json(): Json
    }

    companion object {
        private const val TAG = "UploadWorker"
    }
}
