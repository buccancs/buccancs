package com.buccancs.desktop.data.recording

import com.buccancs.control.SensorSample
import com.buccancs.control.SensorSampleBatch
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.data.session.MetadataMetrics
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.LoggerFactory
import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.security.MessageDigest
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class SensorRecordingManager(
    private val sessionRepository: SessionRepository
) {
    private val logger = LoggerFactory.getLogger(SensorRecordingManager::class.java)
    private val writers = ConcurrentHashMap<StreamKey, StreamWriter>()
    private val writerMutex = Mutex()

    suspend fun append(batch: SensorSampleBatch): Long {
        if (batch.samplesCount == 0) {
            return 0
        }
        val sessionId = batch.session.id
        require(sessionId.isNotBlank()) { "Session identifier is required" }
        val deviceId = batch.deviceId
        require(deviceId.isNotBlank()) { "Device identifier is required" }
        val streamId = batch.streamId.ifBlank { DEFAULT_STREAM_ID }
        if (!sessionRepository.isSessionActive(sessionId)) {
            throw IllegalStateException("Session $sessionId is not active")
        }
        val key = StreamKey(sessionId, deviceId, streamId)
        val writer = getOrCreateWriter(key, batch)
        val result = writer.append(batch.samplesList)
        if (result.samples > 0) {
            sessionRepository.updateStreamingFile(
                sessionId = sessionId,
                deviceId = deviceId,
                relativePath = writer.relativePath,
                mimeType = MIME_TYPE_CSV,
                streamType = streamId,
                bytesTotal = writer.totalBytes,
                checksum = null
            )
            val metricsUpdate = when {
                streamId.equals(STREAM_GSR, ignoreCase = true) ->
                    { metrics: MetadataMetrics -> metrics.copy(gsrSamples = metrics.gsrSamples + result.samples) }

                streamId.equals(STREAM_AUDIO, ignoreCase = true) ||
                        streamId.contains("audio", ignoreCase = true) ->
                    { metrics: MetadataMetrics -> metrics.copy(audioSamples = metrics.audioSamples + result.samples) }

                else -> null
            }
            if (metricsUpdate != null) {
                sessionRepository.registerMetrics(metricsUpdate)
            }
        }
        return result.samples
    }

    suspend fun finalizeStream(sessionId: String, deviceId: String, streamId: String) {
        val key = StreamKey(sessionId, deviceId, streamId)
        val writer = removeWriter(key) ?: return
        finalizeWriter(key, writer, includeChecksum = true)
    }

    suspend fun abortStream(sessionId: String, deviceId: String, streamId: String) {
        val key = StreamKey(sessionId, deviceId, streamId)
        val writer = removeWriter(key) ?: return
        finalizeWriter(key, writer, includeChecksum = false)
    }

    suspend fun closeSession(sessionId: String) {
        val keys = writers.keys.filter { it.sessionId == sessionId }
        for (key in keys) {
            val writer = removeWriter(key) ?: continue
            finalizeWriter(key, writer, includeChecksum = true)
        }
    }

    private suspend fun finalizeWriter(
        key: StreamKey,
        writer: StreamWriter,
        includeChecksum: Boolean
    ) {
        runCatching {
            writer.close()
            val finalBytes = Files.size(writer.filePath)
            val checksum = if (includeChecksum) {
                computeChecksum(writer.filePath)
            } else {
                null
            }
            sessionRepository.updateStreamingFile(
                sessionId = key.sessionId,
                deviceId = key.deviceId,
                relativePath = writer.relativePath,
                mimeType = MIME_TYPE_CSV,
                streamType = key.streamId,
                bytesTotal = finalBytes,
                checksum = checksum
            )
        }.onFailure { ex ->
            logger.error(
                "Failed to finalize stream {} for session {} device {}",
                key.streamId,
                key.sessionId,
                key.deviceId,
                ex
            )
        }
    }

    private suspend fun getOrCreateWriter(
        key: StreamKey,
        batch: SensorSampleBatch
    ): StreamWriter {
        writers[key]?.let { return it }
        return writerMutex.withLock {
            writers[key]?.let { return it }
            val sessionDir = sessionRepository.sessionDirectory(key.sessionId)
            val streamDir = sessionDir.resolve("sensors").resolve(key.deviceId)
            Files.createDirectories(streamDir)
            val filePath = streamDir.resolve("${key.streamId}.csv")
            val channels = extractChannels(batch)
            val writer = StreamWriter(
                sessionId = key.sessionId,
                deviceId = key.deviceId,
                streamId = key.streamId,
                filePath = filePath,
                relativePath = sessionDir.relativize(filePath).toString(),
                sampleRateHz = if (batch.sampleRateHz > 0) batch.sampleRateHz else DEFAULT_SAMPLE_RATE,
                channels = channels
            )
            writer.writeHeader()
            sessionRepository.updateStreamingFile(
                sessionId = key.sessionId,
                deviceId = key.deviceId,
                relativePath = writer.relativePath,
                mimeType = MIME_TYPE_CSV,
                streamType = key.streamId,
                bytesTotal = writer.totalBytes,
                checksum = null
            )
            writers[key] = writer
            writer
        }
    }

    private suspend fun removeWriter(key: StreamKey): StreamWriter? =
        writerMutex.withLock { writers.remove(key) }

    private fun extractChannels(batch: SensorSampleBatch): List<String> {
        val observed = linkedSetOf<String>()
        batch.samplesList.forEach { sample ->
            sample.valuesList.forEach { value ->
                if (value.key.isNotBlank()) {
                    observed.add(value.key)
                }
            }
        }
        if (observed.isEmpty()) {
            observed.add(DEFAULT_CHANNEL)
        }
        return observed.toList()
    }

    private fun computeChecksum(path: Path): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        Files.newInputStream(path, StandardOpenOption.READ).use { input ->
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            while (true) {
                val read = input.read(buffer)
                if (read <= 0) break
                digest.update(buffer, 0, read)
            }
        }
        return digest.digest()
    }

    private data class StreamKey(
        val sessionId: String,
        val deviceId: String,
        val streamId: String
    )

    private class StreamWriter(
        val sessionId: String,
        val deviceId: String,
        val streamId: String,
        val filePath: Path,
        val relativePath: String,
        val sampleRateHz: Double,
        private val channels: List<String>
    ) {
        private val charset = StandardCharsets.UTF_8
        private val mutex = Mutex()
        private val writer: BufferedWriter = Files.newBufferedWriter(
            filePath,
            charset,
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING
        )
        var totalSamples: Long = 0
            private set
        var totalBytes: Long = 0
            private set

        suspend fun append(samples: List<SensorSample>): AppendResult = mutex.withLock {
            if (samples.isEmpty()) return AppendResult(0, 0)
            val builder = StringBuilder(samples.size * (channels.size + 1) * 12)
            samples.forEach { sample ->
                builder.append(sample.timestampEpochMs)
                val values = sample.valuesList.associate { it.key to it.value }
                for (channel in channels) {
                    builder.append(',')
                    val value = values[channel]
                    if (value != null) {
                        builder.append(formatValue(value))
                    }
                }
                builder.append('\n')
            }
            val chunk = builder.toString()
            writer.write(chunk)
            val bytes = chunk.toByteArray(charset).size
            totalSamples += samples.size
            totalBytes += bytes
            AppendResult(samples.size.toLong(), bytes.toLong())
        }

        suspend fun writeHeader() = mutex.withLock {
            val headerMeta = "# stream_id=$streamId sample_rate_hz=${formatValue(sampleRateHz)}"
            val headerColumns = buildString {
                append("timestamp_epoch_ms")
                channels.forEach { append(',').append(it) }
            }
            writeLine(headerMeta)
            writeLine(headerColumns)
        }

        private fun writeLine(line: String) {
            writer.write(line)
            writer.write('\n'.code)
            val bytes = line.toByteArray(charset).size + 1
            totalBytes += bytes
        }

        suspend fun close() = mutex.withLock {
            writer.flush()
            writer.close()
        }

        private fun formatValue(value: Double): String =
            if (value.isFinite()) {
                String.format(Locale.ROOT, "%.6f", value)
            } else {
                ""
            }
    }

    private data class AppendResult(
        val samples: Long,
        val bytes: Long
    )

    private companion object {
        private const val MIME_TYPE_CSV = "text/csv"
        private const val DEFAULT_STREAM_ID = "sensor"
        private const val DEFAULT_CHANNEL = "value"
        private const val STREAM_GSR = "gsr"
        private const val STREAM_AUDIO = "audio"
        private const val DEFAULT_SAMPLE_RATE = 128.0
    }
}
