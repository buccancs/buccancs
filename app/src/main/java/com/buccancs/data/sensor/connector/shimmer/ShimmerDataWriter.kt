package com.buccancs.data.sensor.connector.shimmer

import android.net.Uri
import android.util.Log
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import java.security.DigestOutputStream
import java.security.MessageDigest
import java.util.Locale

/**
 * Handles file I/O operations for Shimmer GSR data recording.
 * Encapsulates all file writing, checksum computation, and resource management.
 */
internal class ShimmerDataWriter private constructor(
    private val sessionId: String,
    private val deviceId: DeviceId,
    private val file: File,
    private val writer: BufferedWriter,
    private val digest: MessageDigest,
    private val digestStream: DigestOutputStream
) {
    private val mutex = Mutex()
    private val logTag = "ShimmerDataWriter"

    /**
     * Write a GSR sample to the file.
     */
    suspend fun writeSample(
        timestampEpochMs: Long,
        conductance: Double?,
        resistance: Double?
    ) {
        val conductanceStr = conductance?.let { String.format(Locale.US, "%.6f", it) } ?: ""
        val resistanceStr = resistance?.let { String.format(Locale.US, "%.2f", it) } ?: ""
        val line = buildString {
            append(timestampEpochMs)
            append(',')
            append(conductanceStr)
            append(',')
            append(resistanceStr)
        }

        withContext(Dispatchers.IO) {
            mutex.withLock {
                runCatching {
                    writer.write(line)
                    writer.newLine()
                    writer.flush()
                }.onFailure { ex ->
                    Log.w(logTag, "Failed to write GSR sample: ${ex.message}")
                }
            }
        }
    }

    /**
     * Finalise the recording and return the artifact.
     * This method closes all resources and computes the final checksum.
     */
    suspend fun finalise(): SessionArtifact = withContext(Dispatchers.IO) {
        mutex.withLock {
            runCatching { writer.flush() }
            runCatching { writer.close() }
        }
        runCatching { digestStream.close() }

        val checksum = digest.digest()

        SessionArtifact(
            deviceId = deviceId,
            streamType = SensorStreamType.GSR,
            uri = Uri.fromFile(file),
            file = file,
            mimeType = "text/csv",
            sizeBytes = file.length(),
            checksumSha256 = checksum
        )
    }

    /**
     * Abort the recording and optionally delete the file.
     * This method closes all resources without computing the checksum.
     */
    suspend fun abort(deleteFile: Boolean = true) {
        withContext(Dispatchers.IO) {
            mutex.withLock {
                runCatching { writer.flush() }
                runCatching { writer.close() }
            }
            runCatching { digestStream.close() }

            if (deleteFile) {
                file.delete()
            }
        }
    }

    companion object {
        /**
         * Create a new data writer for a recording session.
         */
        suspend fun create(
            sessionId: String,
            deviceId: DeviceId,
            recordingStorage: RecordingStorage
        ): ShimmerDataWriter = withContext(Dispatchers.IO) {
            val file = recordingStorage.createArtifactFile(
                sessionId = sessionId,
                deviceId = deviceId.value,
                streamType = "gsr",
                timestampEpochMs = System.currentTimeMillis(),
                extension = "csv"
            )

            val digest = MessageDigest.getInstance("SHA-256")
            val stream = DigestOutputStream(FileOutputStream(file), digest)
            val writer = BufferedWriter(
                OutputStreamWriter(stream, StandardCharsets.UTF_8)
            )

            // Write CSV header
            writer.write("timestamp_ms,conductance_microsiemens,resistance_ohms")
            writer.newLine()
            writer.flush()

            ShimmerDataWriter(
                sessionId = sessionId,
                deviceId = deviceId,
                file = file,
                writer = writer,
                digest = digest,
                digestStream = stream
            )
        }
    }
}
