package com.buccancs.desktop.data.repository

import com.buccancs.desktop.data.encryption.EncryptionKeyProvider
import com.buccancs.desktop.data.encryption.EncryptionManager
import com.buccancs.desktop.data.retention.DataRetentionManager
import com.buccancs.desktop.data.session.SessionMetadata
import com.buccancs.desktop.data.session.SessionStatusDto
import com.buccancs.desktop.domain.policy.RetentionPolicy
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.io.TempDir
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SessionRepositoryTest {
    @TempDir
    lateinit var tempDir: Path
    private lateinit var sessionRepository: SessionRepository
    private lateinit var json: Json

    @BeforeTest
    fun setUp() {
        val sessionsRoot = tempDir.resolve("sessions")
        val keyProvider = EncryptionKeyProvider(tempDir.resolve("keys.json"))
        val encryptionManager = EncryptionManager(keyProvider)
        val retentionPolicy = RetentionPolicy(
            perSessionCapBytes = 10L * 1024L * 1024L,
            perDeviceCapBytes = 10L * 1024L * 1024L,
            globalCapBytes = 50L * 1024L * 1024L
        )
        val retentionManager = DataRetentionManager(retentionPolicy)
        sessionRepository = SessionRepository(
            baseDir = sessionsRoot,
            encryptionManager = encryptionManager,
            retentionManager = retentionManager
        )
        json = Json {
            ignoreUnknownKeys = true
        }
    }

    @AfterTest
    fun tearDown() {
        tempDir.toFile().deleteRecursively()
    }

    @Test
    fun `start session creates metadata artifacts`() = runBlocking {
        val session = sessionRepository.startSession(
            sessionId = "session-alpha",
            operatorId = "operator-1",
            subjectIds = listOf("subject-001")
        )
        val sessionDir = sessionRepository.sessionDirectory(session.id)
        val metadataFile = sessionDir.resolve("metadata.json")
        val encryptedMetadata = sessionDir.resolve("metadata.enc")
        assertTrue(Files.exists(sessionDir), "Expected session directory to be created")
        assertTrue(Files.exists(metadataFile), "Expected plaintext metadata.json to be created")
        assertTrue(Files.exists(encryptedMetadata), "Expected encrypted metadata.enc to be created")
        val payload = Files.readAllBytes(metadataFile)
        val metadata = json.decodeFromString(SessionMetadata.serializer(), String(payload, StandardCharsets.UTF_8))
        assertEquals(session.id, metadata.sessionId)
        assertEquals(SessionStatusDto.ACTIVE, metadata.status)
        assertEquals("operator-1", metadata.operatorId)
        assertEquals(listOf("subject-001"), metadata.subjectIds)
        assertNotNull(metadata.startedAtEpochMs, "Expected session start timestamp to be recorded")
        assertEquals(null, metadata.stoppedAtEpochMs, "Stop timestamp should not be set while active")
    }

    @Test
    fun `stop session finalises metadata`() = runBlocking {
        val sessionId = "session-bravo"
        sessionRepository.startSession(sessionId = sessionId)
        delay(5)
        val session = sessionRepository.stopSession()
        assertNotNull(session, "Expected session to be returned on stop")
        val metadata = sessionRepository.metadataFor(sessionId)
        assertNotNull(metadata, "Metadata should exist after stopping session")
        assertEquals(SessionStatusDto.COMPLETED, metadata.status)
        assertNotNull(metadata.startedAtEpochMs)
        val stoppedAt = metadata.stoppedAtEpochMs
        assertNotNull(stoppedAt, "Stopped timestamp should be recorded")
        val duration = metadata.totalDurationMs
        assertNotNull(duration, "Duration should be recorded once session stops")
        assertTrue(duration >= 0, "Duration must be non-negative")
        if (metadata.startedAtEpochMs != null) {
            val expected = stoppedAt - metadata.startedAtEpochMs!!
            assertTrue(
                kotlin.math.abs(expected - duration) <= 5,
                "Duration should match difference between start and stop timestamps"
            )
        }
    }

    @Test
    fun `cannot start multiple active sessions`() = runBlocking {
        sessionRepository.startSession(sessionId = "session-charlie")
        val exception = assertFailsWith<IllegalStateException> {
            sessionRepository.startSession(sessionId = "session-delta")
        }
        assertTrue(exception.message.orEmpty().contains("already active"))
    }

    @Test
    fun `cannot reuse existing session identifier`() = runBlocking {
        val sessionId = "session-echo"
        sessionRepository.startSession(sessionId = sessionId)
        sessionRepository.stopSession()
        val exception = assertFailsWith<IllegalStateException> {
            sessionRepository.startSession(sessionId = sessionId)
        }
        assertTrue(
            exception.message.orEmpty().contains("already exists on disk"),
            "Expected reuse attempts to be rejected"
        )
    }
}
