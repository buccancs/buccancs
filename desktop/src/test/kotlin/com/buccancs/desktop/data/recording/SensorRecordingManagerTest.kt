package com.buccancs.desktop.data.recording

import com.buccancs.control.sensorSample
import com.buccancs.control.sensorSampleBatch
import com.buccancs.control.sensorSampleValue
import com.buccancs.control.sessionIdentifier
import com.buccancs.desktop.data.encryption.EncryptionKeyProvider
import com.buccancs.desktop.data.encryption.EncryptionManager
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.data.retention.DataRetentionManager
import com.buccancs.desktop.domain.policy.RetentionPolicy
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SensorRecordingManagerTest {
    @TempDir
    lateinit var tempDir: Path
    private lateinit var sessionRepository: SessionRepository
    private lateinit var recordingManager: SensorRecordingManager

    @BeforeTest
    fun setUp() {
        val sessionsRoot = tempDir.resolve("sessions")
        val keyProvider = EncryptionKeyProvider(tempDir.resolve("keys.json"))
        val encryptionManager = EncryptionManager(keyProvider)
        val retentionPolicy = RetentionPolicy(
            perSessionCapBytes = 10 * 1024 * 1024,
            perDeviceCapBytes = 10 * 1024 * 1024,
            globalCapBytes = 50 * 1024 * 1024
        )
        val retentionManager = DataRetentionManager(retentionPolicy)
        sessionRepository = SessionRepository(
            baseDir = sessionsRoot,
            encryptionManager = encryptionManager,
            retentionManager = retentionManager
        )
        recordingManager = SensorRecordingManager(sessionRepository)
    }

    @AfterTest
    fun tearDown() {
        tempDir.toFile().deleteRecursively()
    }

    @Test
    fun `append writes csv and updates metadata`() = runBlocking {
        val sessionId = "session-test"
        val deviceId = "device-001"
        val streamId = "gsr"
        sessionRepository.startSession(sessionId = sessionId)
        val batch = sensorSampleBatch {
            session = sessionIdentifier { id = sessionId }
            this.deviceId = deviceId
            this.streamId = streamId
            sampleRateHz = 128.0
            samples += sensorSample {
                timestampEpochMs = 1_000L
                values += sensorSampleValue {
                    key = "conductance_microsiemens"
                    value = 4.5
                }
                values += sensorSampleValue {
                    key = "resistance_ohms"
                    value = 222_222.0
                }
            }
            samples += sensorSample {
                timestampEpochMs = 1_008L
                values += sensorSampleValue {
                    key = "conductance_microsiemens"
                    value = 4.6
                }
                values += sensorSampleValue {
                    key = "resistance_ohms"
                    value = 217_391.0
                }
            }
            endOfStream = false
        }
        val written = recordingManager.append(batch)
        assertEquals(2L, written)
        val metadataDuring = sessionRepository.metadataFor(sessionId)
        assertNotNull(metadataDuring)
        assertEquals(2, metadataDuring.metrics.gsrSamples.toInt())
        recordingManager.finalizeStream(sessionId, deviceId, streamId)
        val metadata = sessionRepository.metadataFor(sessionId)
        assertNotNull(metadata)
        val recordedFile = metadata.files.firstOrNull { it.path.contains("sensors/$deviceId/$streamId.csv") }
        assertNotNull(recordedFile)
        assertTrue(recordedFile.bytes > 0)
        assertTrue(recordedFile.checksumSha256.isNotBlank())
        val sessionDir = sessionRepository.sessionDirectory(sessionId)
        val csv = sessionDir.resolve("sensors").resolve(deviceId).resolve("$streamId.csv")
        assertTrue(Files.exists(csv), "Expected CSV file at $csv")
        val content = Files.readAllLines(csv)
        assertTrue(content.first().startsWith("# stream_id"))
        assertEquals(4, content.size)
    }
}
