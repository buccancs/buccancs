package com.buccancs.data.recording.manifest

import android.util.Log
import com.buccancs.core.serialization.PrettyJson
import com.buccancs.core.storage.WriteResult
import com.buccancs.data.storage.AtomicFileWriter
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.RecordingBookmark
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class ManifestWriter @Inject constructor(
    private val storage: RecordingStorage,
    @PrettyJson private val json: Json
) {
    private val mutex = Mutex()
    private var activeManifest: SessionManifest? = null
    private var activeFile: File? = null

    companion object {
        private const val TAG = "ManifestWriter"
    }

    suspend fun beginSession(
        anchor: RecordingSessionAnchor,
        devices: List<SensorDevice>,
        simulation: Boolean
    ) {
        mutex.withLock {
            val manifestFile = storage.manifestFile(anchor.sessionId)
            val base = readManifest(manifestFile)
            val manifest = (base ?: SessionManifest(
                sessionId = anchor.sessionId,
                startedAt = anchor.referenceTimestamp.toString(),
                startedAtEpochMs = anchor.referenceTimestamp.toEpochMilliseconds(),
                simulation = simulation,
                orchestratorOffsetMillis = anchor.sharedClockOffsetMillis,
                devices = devices.map(::toDeviceManifest)
            )).copy(
                startedAt = anchor.referenceTimestamp.toString(),
                startedAtEpochMs = anchor.referenceTimestamp.toEpochMilliseconds(),
                orchestratorOffsetMillis = anchor.sharedClockOffsetMillis,
                simulation = simulation,
                devices = devices.map(::toDeviceManifest)
            )
            activeManifest = manifest
            activeFile = manifestFile
            writeManifest(manifestFile, manifest)
        }
    }

    suspend fun appendArtifacts(
        sessionId: String,
        artifacts: List<SessionArtifact>
    ) {
        if (artifacts.isEmpty()) return
        mutex.withLock {
            val current = ensureSession(sessionId) ?: return
            val updatedArtifacts = (current.artifacts + artifacts.map { toArtifactEntry(sessionId, it) })
                .distinctBy { entry -> entry.deviceId + "|" + entry.relativePath }
            val updated = current.copy(artifacts = updatedArtifacts)
            activeManifest = updated
            val file = activeFile ?: storage.manifestFile(sessionId)
            activeFile = file
            writeManifest(file, updated)
        }
    }

    suspend fun finaliseSession(
        sessionId: String,
        endedAt: Instant,
        events: List<DeviceEvent>,
        artifacts: List<SessionArtifact> = emptyList(),
        bookmarks: List<RecordingBookmark> = emptyList()
    ) {
        mutex.withLock {
            val current = ensureSession(sessionId) ?: return
            val artifactEntries = (current.artifacts + artifacts.map { toArtifactEntry(sessionId, it) })
                .distinctBy { entry -> entry.deviceId + "|" + entry.relativePath }
            val sessionStart = current.startedAtEpochMs
            val endEpochMs = endedAt.toEpochMilliseconds()
            val eventEntries = events
                .filter { event ->
                    val scheduled = event.scheduledAt.toEpochMilliseconds()
                    scheduled >= sessionStart && scheduled <= endEpochMs
                }
                .map(::toEventEntry)
            val bookmarkEntries = bookmarks.map(::toBookmarkEntry)
            val duration = (endEpochMs - sessionStart).takeIf { it >= 0 }
            val updated = current.copy(
                artifacts = artifactEntries,
                events = eventEntries,
                bookmarks = bookmarkEntries,
                endedAt = endedAt.toString(),
                endedAtEpochMs = endEpochMs,
                durationMillis = duration
            )
            activeManifest = null
            val file = activeFile ?: storage.manifestFile(sessionId)
            activeFile = null
            writeManifest(file, updated)
        }
    }

    private fun toDeviceManifest(device: SensorDevice): DeviceManifest =
        DeviceManifest(
            deviceId = device.id.value,
            displayName = device.displayName,
            type = device.type.name,
            capabilities = device.capabilities.map { it.name }.sorted(),
            attributes = device.attributes,
            simulated = device.isSimulated
        )

    private fun toArtifactEntry(sessionId: String, artifact: SessionArtifact): ArtifactEntry {
        return ArtifactEntry(
            deviceId = artifact.deviceId.value,
            streamType = artifact.streamType.name,
            relativePath = artifact.file?.let { storage.relativePath(sessionId, it) },
            contentUri = artifact.uri.toString(),
            mimeType = artifact.mimeType,
            sizeBytes = artifact.sizeBytes,
            checksumSha256 = artifact.checksumSha256.toHexString(),
            metadata = artifact.metadata,
            capturedEpochMs = artifact.file?.lastModified()?.takeIf { it > 0 } ?: System.currentTimeMillis()
        )
    }

    private fun toEventEntry(event: DeviceEvent): EventEntry =
        EventEntry(
            eventId = event.id,
            type = event.type.name,
            label = event.label.ifBlank { null },
            scheduledEpochMs = event.scheduledAt.toEpochMilliseconds(),
            receivedEpochMs = event.receivedAt.toEpochMilliseconds()
        )

    private fun toBookmarkEntry(bookmark: RecordingBookmark): BookmarkEntry =
        BookmarkEntry(
            bookmarkId = bookmark.id,
            label = bookmark.label,
            timestampEpochMs = bookmark.timestamp.toEpochMilliseconds()
        )

    private suspend fun writeManifest(target: File, manifest: SessionManifest) {
        withContext(Dispatchers.IO) {
            target.parentFile?.mkdirs()
            val payload = json.encodeToString(manifest)

            when (val result = AtomicFileWriter.writeAtomicSafe(target, payload, checkSpace = true)) {
                is WriteResult.Success -> {
                    Log.d(TAG, "Manifest written: ${manifest.sessionId}")
                }

                is WriteResult.Failure.InsufficientSpace -> {
                    Log.e(
                        TAG,
                        "Insufficient space to write manifest: need ${result.required} bytes, have ${result.available} bytes"
                    )
                    throw Exception("Insufficient storage space")
                }

                is WriteResult.Failure.WriteError -> {
                    Log.e(TAG, "Failed to write manifest: ${result.message}", result.cause)
                    throw Exception("Failed to write manifest: ${result.message}", result.cause)
                }
            }
        }
    }

    private fun readManifest(file: File): SessionManifest? {
        if (!file.exists() || !file.isFile) {
            return null
        }
        return runCatching {
            json.decodeFromString(SessionManifest.serializer(), file.readText(Charsets.UTF_8))
        }.getOrNull()
    }

    private fun ensureSession(sessionId: String): SessionManifest? {
        val current = activeManifest
        if (current != null && current.sessionId == sessionId) {
            return current
        }
        val file = storage.manifestFile(sessionId)
        val loaded = readManifest(file)
        if (loaded != null) {
            activeManifest = loaded
            activeFile = file
        }
        return loaded
    }

    private fun ByteArray.toHexString(): String =
        joinToString(separator = "") { byte -> "%02x".format(byte) }
}

private fun toBookmarkEntry(bookmark: RecordingBookmark): BookmarkEntry =
    BookmarkEntry(
        bookmarkId = bookmark.id,
        label = bookmark.label,
        timestampEpochMs = bookmark.timestamp.toEpochMilliseconds()
    )
