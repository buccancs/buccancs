package com.buccancs.data.sensor.connector.camera

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.EncoderStats
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import java.security.MessageDigest

internal class MediaStoreSegmentWriter(
    private val context: Context,
    private val storage: RecordingStorage,
    private val sessionId: String,
    private val deviceId: DeviceId,
    private val streamType: SensorStreamType
) {
    private val resolver =
        context.contentResolver

    fun open(
        segmentIndex: Int,
        timestampEpochMs: Long
    ): SegmentHandle {
        val fileName =
            storage.artifactFileName(
                streamType = streamType.name.lowercase(),
                timestampEpochMs = timestampEpochMs,
                segmentIndex = segmentIndex,
                extension = "mp4"
            )
        val relativePath =
            storage.mediaStoreRelativePath(
                sessionId,
                deviceId.value
            )
        val values =
            ContentValues().apply {
                put(
                    MediaStore.Video.Media.DISPLAY_NAME,
                    fileName
                )
                put(
                    MediaStore.Video.Media.MIME_TYPE,
                    "video/mp4"
                )
                put(
                    MediaStore.Video.Media.DATE_TAKEN,
                    timestampEpochMs
                )
                put(
                    MediaStore.Video.Media.RELATIVE_PATH,
                    relativePath
                )
                put(
                    MediaStore.Video.Media.IS_PENDING,
                    1
                )
            }
        val collection =
            MediaStore.Video.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )
        val uri =
            resolver.insert(
                collection,
                values
            )
                ?: throw IllegalStateException(
                    "Unable to create media store entry for $fileName"
                )
        val descriptor =
            resolver.openFileDescriptor(
                uri,
                "w"
            )
                ?: throw IllegalStateException(
                    "Unable to open descriptor for $uri"
                )
        return SegmentHandle(
            uri = uri,
            descriptor = descriptor,
            fileName = fileName,
            relativePath = relativePath,
            segmentIndex = segmentIndex,
            openedAtEpochMs = timestampEpochMs
        )
    }

    fun finalize(
        handle: SegmentHandle,
        stats: EncoderStats
    ): SessionArtifact {
        runCatching { handle.descriptor.close() }
        val sizeBytes =
            resolver.openFileDescriptor(
                handle.uri,
                "r"
            )
                ?.use { it.statSize }
                ?: 0L
        val checksum =
            digest(
                handle.uri
            )
        val update =
            ContentValues().apply {
                put(
                    MediaStore.Video.Media.IS_PENDING,
                    0
                )
                put(
                    MediaStore.Video.Media.SIZE,
                    sizeBytes
                )
                put(
                    MediaStore.Video.Media.DATE_TAKEN,
                    stats.startedAtEpochMs
                )
            }
        resolver.update(
            handle.uri,
            update,
            null,
            null
        )
        return SessionArtifact(
            deviceId = deviceId,
            streamType = streamType,
            uri = handle.uri,
            file = null,
            mimeType = "video/mp4",
            sizeBytes = sizeBytes,
            checksumSha256 = checksum,
            metadata = stats.asMetadata()
        )
    }

    fun abort(
        handle: SegmentHandle
    ) {
        runCatching { handle.descriptor.close() }
        resolver.delete(
            handle.uri,
            null,
            null
        )
    }

    private fun digest(
        uri: Uri
    ): ByteArray {
        val digest =
            MessageDigest.getInstance(
                "SHA-256"
            )
        resolver.openInputStream(
            uri
        )
            ?.use { stream ->
                val buffer =
                    ByteArray(
                        DEFAULT_BUFFER_SIZE
                    )
                while (true) {
                    val read =
                        stream.read(
                            buffer
                        )
                    if (read == -1) break
                    if (read > 0) {
                        digest.update(
                            buffer,
                            0,
                            read
                        )
                    }
                }
            }
        return digest.digest()
    }

    data class SegmentHandle(
        val uri: Uri,
        val descriptor: ParcelFileDescriptor,
        val fileName: String,
        val relativePath: String,
        val segmentIndex: Int,
        val openedAtEpochMs: Long
    )

    private companion object {
        private const val DEFAULT_BUFFER_SIZE =
            256 * 1024
    }
}
