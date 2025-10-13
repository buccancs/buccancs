package com.buccancs.data.sensor.connector.audio

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.SystemClock
import com.buccancs.data.sensor.MetadataWriters
import com.buccancs.data.sensor.SessionClock
import android.util.Log
import com.buccancs.data.sensor.connector.simulated.BaseSimulatedConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import java.io.File
import java.io.RandomAccessFile
import java.security.MessageDigest
import kotlin.text.Charsets
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.absoluteValue
import kotlin.io.DEFAULT_BUFFER_SIZE

@Singleton
internal class MicrophoneConnector @Inject constructor(
    @ApplicationScope private val appScope: CoroutineScope,
    private val recordingStorage: RecordingStorage,
    artifactFactory: SimulatedArtifactFactory
) : BaseSimulatedConnector(
    scope = appScope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = DeviceId("phone-mic"),
        displayName = "Phone Microphone",
        type = SensorDeviceType.AUDIO_MICROPHONE,
        capabilities = setOf(SensorStreamType.AUDIO),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = emptyMap()
    )
) {
    private val logTag = "MicrophoneConnector"
    private var audioRecord: AudioRecord? = null
    private var bufferSizeInFrames: Int = 0
    private var recordingJob: Job? = null
    private var currentSessionId: String? = null
    private var completedSessionId: String? = null
    private var audioFile: File? = null
    private var audioWriter: RandomAccessFile? = null
    private var audioDigest: MessageDigest? = null
    private var bytesWritten: Long = 0
    private val pendingArtifacts = mutableListOf<SessionArtifact>()
    private var pcmScratch = ByteArray(0)
    @Volatile
    private var sessionClock: SessionClock? = null
    override suspend fun refreshInventory() {
        if (isSimulationMode) {
            super.refreshInventory()
        }
    }

    override suspend fun applySimulation(enabled: Boolean) {
        if (enabled) {
            stopHardwareRecording()
            releaseAudioRecord()
            sessionClock = null
        }
        super.applySimulation(enabled)
    }

    override suspend fun connect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.connect()
        }
        return try {
            ensureAudioRecord()
            deviceState.update {
                it.copy(
                    connectionStatus = ConnectionStatus.Connected(
                        since = deviceNowInstant(),
                        batteryPercent = null,
                        rssiDbm = null
                    ),
                    isSimulated = false
                )
            }
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to initialise microphone", t)
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun disconnect(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.disconnect()
        }
        return try {
            stopHardwareRecording()
            finalizeRecording()
            releaseAudioRecord()
            statusState.value = emptyList()
            deviceState.update { it.copy(connectionStatus = ConnectionStatus.Disconnected, isSimulated = false) }
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to release microphone", t)
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult {
        if (isSimulationMode) {
            return super.startStreaming(anchor)
        }
        return try {
            sessionClock = SessionClock.fromAnchor(anchor)
            val record = ensureAudioRecord()
            prepareOutputFile(anchor.sessionId)
            record.startRecording()
            sessionClock = sessionClock?.markRecordingStart(System.currentTimeMillis(), SystemClock.elapsedRealtimeNanos())
            if (recordingJob?.isActive == true) {
                recordingJob?.cancel()
            }
            val shortBuffer = ShortArray(bufferSizeInFrames.coerceAtLeast(MIN_BUFFER_FRAMES))
            recordingJob = appScope.launch(Dispatchers.IO) {
                while (isActive) {
                    val read = try {
                        record.read(shortBuffer, 0, shortBuffer.size, AudioRecord.READ_BLOCKING)
                    } catch (t: Throwable) {
                        Log.e(logTag, "Audio read failed", t)
                        break
                    }
                    if (read > 0) {
                        writePcmSamples(shortBuffer, read)
                        val alignedNow = alignedNowInstant()
                        val bufferedSeconds = read.toDouble() / SAMPLE_RATE_HZ.toDouble()
                        val status = SensorStreamStatus(
                            deviceId = deviceId,
                            streamType = SensorStreamType.AUDIO,
                            sampleRateHz = SAMPLE_RATE_HZ.toDouble(),
                            frameRateFps = null,
                            lastSampleTimestamp = alignedNow,
                            bufferedDurationSeconds = bufferedSeconds,
                            isStreaming = true,
                            isSimulated = false
                        )
                        statusState.value = listOf(status)
                    }
                }
            }
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to start microphone streaming", t)
            sessionClock = null
            DeviceCommandResult.Failed(t)
        }
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        if (isSimulationMode) {
            return super.stopStreaming()
        }
        return try {
            stopHardwareRecording()
            finalizeRecording()
            statusState.value = emptyList()
            sessionClock = null
            DeviceCommandResult.Accepted
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to stop microphone streaming", t)
            sessionClock = null
            DeviceCommandResult.Failed(t)
        }
    }

    override fun streamIntervalMs(): Long = 220L
    override fun simulatedBatteryPercent(device: SensorDevice): Int? = null
    override fun simulatedRssi(device: SensorDevice): Int? = null
    override fun sampleStatuses(
        timestamp: kotlinx.datetime.Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val random = ((frameCounter + deviceId.value.hashCode()).absoluteValue % 1000) / 10_000.0
        val status = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.AUDIO,
            sampleRateHz = SAMPLE_RATE_HZ.toDouble(),
            frameRateFps = null,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = simulatedBufferedSeconds(
                streamType = SensorStreamType.AUDIO,
                baseVideo = 0.0,
                baseSample = 0.6,
                randomizer = { random }
            ),
            isStreaming = true,
            isSimulated = true
        )
        return listOf(status)
    }

    override suspend fun collectArtifacts(sessionId: String): List<SessionArtifact> {
        if (isSimulationMode) {
            return super.collectArtifacts(sessionId)
        }
        if (sessionId != completedSessionId) {
            return emptyList()
        }
        completedSessionId = null
        val artifacts = pendingArtifacts.toList()
        pendingArtifacts.clear()
        return artifacts
    }

    private fun ensureAudioRecord(): AudioRecord {
        val existing = audioRecord
        if (existing != null && existing.state == AudioRecord.STATE_INITIALIZED) {
            return existing
        }
        val minBufferSize = AudioRecord.getMinBufferSize(
            SAMPLE_RATE_HZ,
            CHANNEL_CONFIG,
            AUDIO_FORMAT
        )
        require(minBufferSize > 0) { "Unsupported audio configuration" }
        val record = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            SAMPLE_RATE_HZ,
            CHANNEL_CONFIG,
            AUDIO_FORMAT,
            minBufferSize * 2
        )
        require(record.state == AudioRecord.STATE_INITIALIZED) { "AudioRecord failed to initialize" }
        audioRecord = record
        bufferSizeInFrames = minBufferSize
        return record
    }

    private fun stopHardwareRecording() {
        recordingJob?.cancel()
        recordingJob = null
        audioRecord?.let { record ->
            try {
                if (record.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                    record.stop()
                }
            } catch (t: Throwable) {
                Log.w(logTag, "Failed to stop AudioRecord cleanly", t)
            }
        }
    }

    private fun releaseAudioRecord() {
        audioRecord?.release()
        audioRecord = null
        bufferSizeInFrames = 0
    }

    private fun prepareOutputFile(sessionId: String) {
        val directory = recordingStorage.deviceDirectory(sessionId, deviceId.value)
        val fileName = "audio-${System.currentTimeMillis()}.wav"
        val target = File(directory, fileName)
        val writer = RandomAccessFile(target, "rw")
        writer.setLength(0)
        writeWavHeader(writer, SAMPLE_RATE_HZ, CHANNEL_COUNT, BITS_PER_SAMPLE, 0)
        audioFile = target
        audioWriter = writer
        audioDigest = MessageDigest.getInstance("SHA-256")
        bytesWritten = 0
        currentSessionId = sessionId
    }

    private fun writePcmSamples(samples: ShortArray, length: Int) {
        val bytesNeeded = length * BYTES_PER_SAMPLE
        if (pcmScratch.size < bytesNeeded) {
            pcmScratch = ByteArray(bytesNeeded)
        }
        var index = 0
        for (i in 0 until length) {
            val value = samples[i].toInt()
            pcmScratch[index++] = (value and 0xFF).toByte()
            pcmScratch[index++] = ((value shr 8) and 0xFF).toByte()
        }
        try {
            audioWriter?.write(pcmScratch, 0, bytesNeeded)
            audioDigest?.update(pcmScratch, 0, bytesNeeded)
            bytesWritten += bytesNeeded
        } catch (t: Throwable) {
            Log.e(logTag, "Failed to persist audio samples", t)
        }
    }

    private fun finalizeRecording() {
        val writer = audioWriter ?: return
        val file = audioFile
        val clockSnapshot = sessionClock
        val sessionIdSnapshot = currentSessionId
        val bytesCaptured = bytesWritten
        try {
            writeWavHeader(writer, SAMPLE_RATE_HZ, CHANNEL_COUNT, BITS_PER_SAMPLE, bytesCaptured)
        } catch (t: Throwable) {
            Log.w(logTag, "Unable to finalise WAV header", t)
        } finally {
            try {
                writer.close()
            } catch (_: Throwable) {
            }
            audioWriter = null
        }
        if (file != null && file.exists()) {
            val checksum = audioDigest?.digest() ?: ByteArray(0)
            val artifact = SessionArtifact(
                deviceId = deviceId,
                streamType = SensorStreamType.AUDIO,
                file = file,
                mimeType = "audio/wav",
                sizeBytes = file.length(),
                checksumSha256 = checksum
            )
            pendingArtifacts += artifact
            val metadataSessionId = sessionIdSnapshot ?: clockSnapshot?.sessionId ?: deviceId.value
            val entries = mutableListOf<Pair<String, String>>().apply {
                add("sessionId" to MetadataWriters.stringValue(metadataSessionId))
                add("deviceId" to MetadataWriters.stringValue(deviceId.value))
                add("streamType" to MetadataWriters.stringValue("audio_wav"))
                add("artifactFile" to MetadataWriters.stringValue(file.name))
                val anchorEpoch = clockSnapshot?.let { it.anchorEpochMs + it.clockOffsetMs } ?: System.currentTimeMillis()
                add("anchorEpochMs" to anchorEpoch.toString())
                clockSnapshot?.let { add("clockOffsetMs" to it.clockOffsetMs.toString()) }
                clockSnapshot?.startDeviceEpochMs?.let { add("deviceStartEpochMs" to it.toString()) }
                clockSnapshot?.startAlignedEpochMillis()?.let { add("alignedStartEpochMs" to it.toString()) }
                clockSnapshot?.durationSinceStartMs(SystemClock.elapsedRealtimeNanos())?.let { duration ->
                    add("durationMs" to duration.toString())
                    clockSnapshot.startAlignedEpochMillis()?.let { start ->
                        add("alignedEndEpochMs" to (start + duration).toString())
                    }
                }
                add("sampleRateHz" to SAMPLE_RATE_HZ.toString())
                add("channelCount" to CHANNEL_COUNT.toString())
                add("bitsPerSample" to BITS_PER_SAMPLE.toString())
                add("bytesCaptured" to bytesCaptured.toString())
                add("checksumSha256" to MetadataWriters.stringValue(checksum.toHexString()))
            }
            val directory = file.parentFile ?: recordingStorage.deviceDirectory(metadataSessionId, deviceId.value)
            val metadataFile = File(directory, "${file.nameWithoutExtension}-timeline.json")
            MetadataWriters.writeMetadata(metadataFile, entries)
            val metadataChecksum = digestFile(metadataFile)
            pendingArtifacts += SessionArtifact(
                deviceId = deviceId,
                streamType = SensorStreamType.AUDIO,
                file = metadataFile,
                mimeType = "application/json",
                sizeBytes = metadataFile.length(),
                checksumSha256 = metadataChecksum
            )
            completedSessionId = metadataSessionId
        }
        audioDigest = null
        audioFile = null
        bytesWritten = 0
        currentSessionId = null
    }

    private fun writeWavHeader(
        file: RandomAccessFile,
        sampleRate: Int,
        channels: Int,
        bitsPerSample: Int,
        dataLength: Long
    ) {
        val byteRate = sampleRate * channels * bitsPerSample / 8
        val blockAlign = channels * bitsPerSample / 8
        val chunkSize = (dataLength + 36).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
        val header = ByteArray(WAV_HEADER_SIZE)
        header.writeAscii(0, "RIFF")
        header.writeIntLE(4, chunkSize)
        header.writeAscii(8, "WAVE")
        header.writeAscii(12, "fmt ")
        header.writeIntLE(16, 16)
        header.writeShortLE(20, 1)
        header.writeShortLE(22, channels)
        header.writeIntLE(24, sampleRate)
        header.writeIntLE(28, byteRate)
        header.writeShortLE(32, blockAlign)
        header.writeShortLE(34, bitsPerSample)
        header.writeAscii(36, "data")
        header.writeIntLE(40, dataLength.coerceAtMost(Int.MAX_VALUE.toLong()).toInt())
        file.seek(0)
        file.write(header, 0, WAV_HEADER_SIZE)
    }

    private fun ByteArray.writeAscii(offset: Int, value: String) {
        val bytes = value.toByteArray(Charsets.US_ASCII)
        bytes.copyInto(this, offset, 0, bytes.size.coerceAtMost(size - offset))
    }

    private fun ByteArray.writeIntLE(offset: Int, value: Int) {
        this[offset] = (value and 0xFF).toByte()
        this[offset + 1] = ((value shr 8) and 0xFF).toByte()
        this[offset + 2] = ((value shr 16) and 0xFF).toByte()
        this[offset + 3] = ((value shr 24) and 0xFF).toByte()
    }

    private fun ByteArray.writeShortLE(offset: Int, value: Int) {
        this[offset] = (value and 0xFF).toByte()
        this[offset + 1] = ((value shr 8) and 0xFF).toByte()
    }

    private fun deviceNowInstant(): Instant =
        Instant.fromEpochMilliseconds(System.currentTimeMillis())

    private fun alignedInstant(instant: Instant): Instant {
        val clock = sessionClock ?: return instant
        val alignedMs = clock.alignDeviceEpoch(instant.toEpochMilliseconds())
        return Instant.fromEpochMilliseconds(alignedMs)
    }

    private fun alignedNowInstant(): Instant = alignedInstant(deviceNowInstant())

    private fun digestFile(file: File): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        file.inputStream().use { stream ->
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            while (true) {
                val read = stream.read(buffer)
                if (read <= 0) break
                digest.update(buffer, 0, read)
            }
        }
        return digest.digest()
    }

    private fun ByteArray.toHexString(): String =
        joinToString(separator = "") { byte ->
            ((byte.toInt() and 0xFF) + 0x100).toString(16).substring(1)
        }

    companion object {
        private const val SAMPLE_RATE_HZ = 44_100
        private const val CHANNEL_COUNT = 1
        private const val BITS_PER_SAMPLE = 16
        private const val BYTES_PER_SAMPLE = BITS_PER_SAMPLE / 8
        private const val WAV_HEADER_SIZE = 44
        private const val MIN_BUFFER_FRAMES = 4_096
        private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
        private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
    }
}
