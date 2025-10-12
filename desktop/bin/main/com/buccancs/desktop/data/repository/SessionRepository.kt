package com.buccancs.desktop.data.repository

import com.buccancs.desktop.data.encryption.EncryptionManager
import com.buccancs.desktop.data.retention.DataRetentionManager
import com.buccancs.desktop.data.session.EventLog
import com.buccancs.desktop.data.session.MetadataMetrics
import com.buccancs.desktop.data.session.RecordedFile
import com.buccancs.desktop.data.session.RetentionSnapshot
import com.buccancs.desktop.data.session.SessionMetadata
import com.buccancs.desktop.data.session.SessionStatusDto
import com.buccancs.desktop.domain.model.FileTransferProgress
import com.buccancs.desktop.domain.model.FileTransferState
import com.buccancs.desktop.domain.model.Session
import com.buccancs.desktop.domain.model.SessionMetrics
import com.buccancs.desktop.domain.model.SessionStatus
import com.buccancs.desktop.domain.model.StoredSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.security.MessageDigest
import java.time.Instant
import java.util.UUID
import java.util.Comparator
import kotlin.io.path.deleteIfExists
import kotlin.io.path.exists
import kotlin.streams.toList

class SessionRepository(
    private val baseDir: Path,
    private val encryptionManager: EncryptionManager,
    private val retentionManager: DataRetentionManager,
    private val json: Json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
) {
    private val logger = LoggerFactory.getLogger(SessionRepository::class.java)
    private val mutex = Mutex()
    private val metadataFileName = "metadata.enc"
    private val metadataPlainFileName = "metadata.json"
    private val _activeSession = MutableStateFlow<Session?>(null)
    val activeSession: StateFlow<Session?> = _activeSession.asStateFlow()
    private val storedSessions = MutableStateFlow<List<StoredSession>>(emptyList())
    private val eventTimeline = MutableStateFlow<List<EventLog>>(emptyList())
    private val transferStates = MutableStateFlow<Map<String, FileTransferProgress>>(emptyMap())
    private val transferSnapshots = MutableStateFlow<List<FileTransferProgress>>(emptyList())

    init {
        Files.createDirectories(baseDir)
        storedSessions.value = rebuildStoredSessionsUnlocked()
    }

    fun storedSessions(): StateFlow<List<StoredSession>> = storedSessions.asStateFlow()

    fun activeEvents(): StateFlow<List<EventLog>> = eventTimeline.asStateFlow()

    fun transferUpdates(): StateFlow<List<FileTransferProgress>> = transferSnapshots.asStateFlow()

    fun sessionDirectory(sessionId: String): Path = baseDir.resolve(sessionId)

    fun isSessionActive(sessionId: String): Boolean =
        _activeSession.value?.let { it.id == sessionId && it.status == SessionStatus.ACTIVE } == true

    suspend fun startSession(
        sessionId: String = UUID.randomUUID().toString(),
        operatorId: String? = null,
        subjectIds: List<String> = emptyList()
    ): Session =
        mutex.withLock {
            if (_activeSession.value?.status == SessionStatus.ACTIVE) {
                throw IllegalStateException("Session ${_activeSession.value?.id} already active")
            }
            storedSessions.value.firstOrNull { it.status == SessionStatus.ACTIVE }?.let { existing ->
                throw IllegalStateException("Session ${existing.id} already active")
            }
            clearTransfersForSession(sessionId)
            val sessionDir = baseDir.resolve(sessionId)
            val metadataPath = sessionDir.resolve(metadataPlainFileName)
            val encryptedMetadataPath = sessionDir.resolve(metadataFileName)
            if (Files.exists(metadataPath) || Files.exists(encryptedMetadataPath)) {
                throw IllegalStateException("Session $sessionId already exists on disk")
            }
            Files.createDirectories(sessionDir.resolve("devices"))
            Files.createDirectories(sessionDir.resolve("events"))

            val now = Instant.now()
            val metadata = SessionMetadata(
                sessionId = sessionId,
                createdAtEpochMs = now.toEpochMilli(),
                status = SessionStatusDto.ACTIVE,
                operatorId = operatorId,
                subjectIds = subjectIds,
                startedAtEpochMs = now.toEpochMilli()
            )
            persistMetadata(sessionDir, metadata)

            val session = Session(
                id = sessionId,
                createdAt = now,
                startedAt = now,
                directory = sessionDir,
                status = SessionStatus.ACTIVE,
                metrics = SessionMetrics(),
                subjectIds = subjectIds,
                totalDurationMs = null
            )
            _activeSession.value = session
            eventTimeline.value = emptyList()
            storedSessions.value = rebuildStoredSessionsUnlocked()
            logger.info("Started session {}", sessionId)
            session
        }

    suspend fun stopSession(): Session? = mutex.withLock {
        val current = _activeSession.value ?: return null
        val metadata = readMetadata(current.directory)
        val now = Instant.now().toEpochMilli()
        val duration = metadata.startedAtEpochMs?.let { start ->
            (now - start).takeIf { it >= 0 }
        }
        val updated = metadata.copy(
            status = SessionStatusDto.COMPLETED,
            stoppedAtEpochMs = now,
            totalDurationMs = duration,
            retention = latestRetentionSnapshot(current.id)
        )
        persistMetadata(current.directory, updated)
        val session = current.copy(status = SessionStatus.COMPLETED, totalDurationMs = duration)
        _activeSession.value = null
        eventTimeline.value = updated.events
        storedSessions.value = rebuildStoredSessionsUnlocked()
        logger.info("Stopped session {}", current.id)
        session
    }

    suspend fun eraseSession(sessionId: String) = mutex.withLock {
        if (_activeSession.value?.id == sessionId) {
            throw IllegalStateException("Cannot erase active session $sessionId")
        }
        val directory = baseDir.resolve(sessionId)
        if (!Files.exists(directory)) {
            return@withLock
        }
        Files.walk(directory).use { stream ->
            stream.sorted(Comparator.reverseOrder()).forEach { path ->
                path.deleteIfExists()
            }
        }
        retentionManager.resetSession(sessionId)
        clearTransfersForSession(sessionId)
        storedSessions.value = rebuildStoredSessionsUnlocked()
        if (_activeSession.value == null) {
            eventTimeline.value = emptyList()
        }
        logger.info("Erased session {}", sessionId)
    }

    suspend fun registerEvent(
        eventId: String,
        label: String,
        timestampMs: Long,
        deviceIds: List<String>
    ) = mutex.withLock {
        val current = _activeSession.value ?: return@withLock
        val metadata = readMetadata(current.directory)
        val updated = metadata.copy(
            events = metadata.events + EventLog(
                eventId = eventId,
                label = label,
                timestampEpochMs = timestampMs,
                deviceIds = deviceIds
            ),
            retention = latestRetentionSnapshot(current.id)
        )
        persistMetadata(current.directory, updated)
        eventTimeline.value = updated.events
        storedSessions.value = rebuildStoredSessionsUnlocked()
        logger.info("Registered event {} for session {}", eventId, current.id)
    }

    suspend fun markTransferStarted(
        sessionId: String,
        deviceId: String,
        fileName: String,
        streamType: String?,
        expectedBytes: Long,
        attempt: Int
    ) = mutex.withLock {
        val key = transferKey(sessionId, deviceId, fileName, streamType)
        val progress = FileTransferProgress(
            sessionId = sessionId,
            deviceId = deviceId,
            fileName = fileName,
            streamType = streamType,
            expectedBytes = expectedBytes,
            receivedBytes = 0,
            attempt = attempt,
            state = FileTransferState.IN_PROGRESS,
            lastError = null,
            completedAt = null
        )
        val next = transferStates.value.toMutableMap()
        next[key] = progress
        publishTransfers(next)
    }

    suspend fun markTransferProgress(
        sessionId: String,
        deviceId: String,
        fileName: String,
        streamType: String?,
        receivedBytes: Long
    ) = mutex.withLock {
        val key = transferKey(sessionId, deviceId, fileName, streamType)
        val existing = transferStates.value[key] ?: return@withLock
        val updated = existing.copy(
            receivedBytes = receivedBytes,
            state = FileTransferState.IN_PROGRESS,
            lastError = null
        )
        val next = transferStates.value.toMutableMap()
        next[key] = updated
        publishTransfers(next)
    }

    suspend fun markTransferFailed(
        sessionId: String,
        deviceId: String,
        fileName: String,
        streamType: String?,
        attempt: Int,
        receivedBytes: Long,
        error: String
    ) = mutex.withLock {
        val key = transferKey(sessionId, deviceId, fileName, streamType)
        val existing = transferStates.value[key]
        val expected = existing?.expectedBytes ?: receivedBytes
        val updated = FileTransferProgress(
            sessionId = sessionId,
            deviceId = deviceId,
            fileName = fileName,
            streamType = streamType,
            expectedBytes = expected,
            receivedBytes = receivedBytes,
            attempt = attempt,
            state = FileTransferState.FAILED,
            lastError = error,
            completedAt = Instant.now()
        )
        val next = transferStates.value.toMutableMap()
        next[key] = updated
        publishTransfers(next)
    }

    suspend fun markTransferCompleted(
        sessionId: String,
        deviceId: String,
        fileName: String,
        streamType: String?,
        expectedBytes: Long,
        receivedBytes: Long
    ) = mutex.withLock {
        val key = transferKey(sessionId, deviceId, fileName, streamType)
        val existing = transferStates.value[key]
        val updated = FileTransferProgress(
            sessionId = sessionId,
            deviceId = deviceId,
            fileName = fileName,
            streamType = streamType,
            expectedBytes = if (expectedBytes > 0) expectedBytes else existing?.expectedBytes ?: receivedBytes,
            receivedBytes = receivedBytes,
            attempt = (existing?.attempt ?: 0).coerceAtLeast(1),
            state = FileTransferState.COMPLETED,
            lastError = null,
            completedAt = Instant.now()
        )
        val next = transferStates.value.toMutableMap()
        next[key] = updated
        publishTransfers(next)
    }

    suspend fun attachFile(
        sessionId: String,
        deviceId: String,
        fileName: String,
        content: ByteArray,
        mimeType: String,
        checksum: ByteArray,
        streamType: String?
    ) = mutex.withLock {
        val sessionDir = baseDir.resolve(sessionId)
        if (!Files.exists(sessionDir)) {
            throw IllegalArgumentException("Session $sessionId not found")
        }
        val deviceDir = sessionDir.resolve("devices").resolve(deviceId)
        Files.createDirectories(deviceDir)
        val targetFile = deviceDir.resolve(fileName)
        val encrypted = encryptionManager.encrypt(content, sessionId.toByteArray(StandardCharsets.UTF_8))
        Files.write(
            targetFile,
            encrypted,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
        )
        val digest = if (checksum.isEmpty()) {
            MessageDigest.getInstance("SHA-256").digest(content)
        } else {
            checksum
        }
        val metadata = readMetadata(sessionDir)
        val recordedFile = RecordedFile(
            deviceId = deviceId,
            path = sessionDir.relativize(targetFile).toString(),
            bytes = content.size.toLong(),
            checksumSha256 = digest.joinToString("") { "%02x".format(it) },
            mimeType = mimeType,
            createdEpochMs = Instant.now().toEpochMilli(),
            streamType = streamType
        )
        retentionManager.registerWrite(sessionId, deviceId, content.size.toLong())
        val updated = metadata.copy(
            files = metadata.files + recordedFile,
            retention = latestRetentionSnapshot(sessionId)
        )
        persistMetadata(sessionDir, updated)
        storedSessions.value = rebuildStoredSessionsUnlocked()
    }

    suspend fun updateStreamingFile(
        sessionId: String,
        deviceId: String,
        relativePath: String,
        mimeType: String,
        streamType: String?,
        bytesTotal: Long,
        checksum: ByteArray? = null
    ) = mutex.withLock {
        require(bytesTotal >= 0) { "bytesTotal must be non-negative" }
        val sessionDir = baseDir.resolve(sessionId)
        if (!Files.exists(sessionDir)) {
            throw IllegalArgumentException("Session $sessionId not found")
        }
        val metadata = readMetadata(sessionDir)
        val existing = metadata.files.firstOrNull { it.path == relativePath }
        val createdAt = existing?.createdEpochMs ?: Instant.now().toEpochMilli()
        val previousBytes = existing?.bytes ?: 0L
        val delta = (bytesTotal - previousBytes).coerceAtLeast(0L)
        val checksumHex = checksum?.toHexString() ?: existing?.checksumSha256.orEmpty()
        val recordedFile = RecordedFile(
            deviceId = deviceId,
            path = relativePath,
            bytes = bytesTotal,
            checksumSha256 = checksumHex,
            mimeType = mimeType,
            createdEpochMs = createdAt,
            streamType = streamType
        )
        val files = if (existing == null) {
            metadata.files + recordedFile
        } else {
            metadata.files.map { if (it.path == relativePath) recordedFile else it }
        }
        if (delta > 0) {
            retentionManager.registerWrite(sessionId, deviceId, delta)
        }
        val updated = metadata.copy(
            files = files,
            retention = latestRetentionSnapshot(sessionId)
        )
        persistMetadata(sessionDir, updated)
        storedSessions.value = rebuildStoredSessionsUnlocked()
    }

    suspend fun registerMetrics(transform: (MetadataMetrics) -> MetadataMetrics) = mutex.withLock {
        val current = _activeSession.value ?: return@withLock
        val metadata = readMetadata(current.directory)
        val transformed = transform(metadata.metrics)
        val updated = metadata.copy(
            metrics = transformed,
            retention = latestRetentionSnapshot(current.id)
        )
        persistMetadata(current.directory, updated)
        _activeSession.value = current.copy(metrics = transformed.toDomain(current.metrics))
        storedSessions.value = rebuildStoredSessionsUnlocked()
    }

    suspend fun listSessionIds(): List<String> = mutex.withLock {
        if (!Files.exists(baseDir)) {
            return@withLock emptyList()
        }
        Files.list(baseDir).use { stream ->
            stream.filter { Files.isDirectory(it) }
                .map { it.fileName.toString() }
                .toList()
        }
    }

    suspend fun metadataFor(sessionId: String): SessionMetadata? = mutex.withLock {
        val directory = baseDir.resolve(sessionId)
        val metadataPath = directory.resolve(metadataFileName)
        if (!Files.exists(metadataPath)) {
            return@withLock null
        }
        readMetadata(directory)
    }

    private fun latestRetentionSnapshot(sessionId: String): RetentionSnapshot {
        val snapshot = retentionManager.state().value
        return RetentionSnapshot(
            perDeviceBytes = snapshot.perSessionDeviceBytes[sessionId] ?: emptyMap(),
            perSessionBytes = snapshot.perSessionBytes[sessionId] ?: 0,
            globalBytes = snapshot.totalBytes
        )
    }

    private fun clearTransfersForSession(sessionId: String) {
        val current = transferStates.value
        if (current.isEmpty()) {
            return
        }
        val filtered = current.filterKeys { !it.startsWith("$sessionId|") }
        if (filtered.size != current.size) {
            publishTransfers(filtered.toMutableMap())
        }
    }

    private fun transferKey(
        sessionId: String,
        deviceId: String,
        fileName: String,
        streamType: String?
    ): String = listOf(sessionId, deviceId, fileName, streamType.orEmpty()).joinToString("|")

    private fun publishTransfers(next: MutableMap<String, FileTransferProgress>) {
        val immutable = next.toMap()
        transferStates.value = immutable
        transferSnapshots.value = immutable.values.toList()
    }

    private fun persistMetadata(sessionDir: Path, metadata: SessionMetadata) {
        val jsonBytes = json.encodeToString(metadata).toByteArray(StandardCharsets.UTF_8)
        val encrypted = encryptionManager.encrypt(jsonBytes, metadata.sessionId.toByteArray(StandardCharsets.UTF_8))
        Files.write(
            sessionDir.resolve(metadataFileName),
            encrypted,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
        )
        Files.write(
            sessionDir.resolve(metadataPlainFileName),
            jsonBytes,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
        )
    }

    private fun readMetadata(sessionDir: Path): SessionMetadata {
        val sessionId = sessionDir.fileName.toString()
        val encryptedPath = sessionDir.resolve(metadataFileName)
        if (Files.exists(encryptedPath)) {
            val encrypted = Files.readAllBytes(encryptedPath)
            val jsonBytes = encryptionManager.decrypt(encrypted, sessionId.toByteArray(StandardCharsets.UTF_8))
            ensurePlainMetadata(sessionDir, jsonBytes)
            return json.decodeFromString(SessionMetadata.serializer(), String(jsonBytes, StandardCharsets.UTF_8))
        }
        val plainPath = sessionDir.resolve(metadataPlainFileName)
        if (!Files.exists(plainPath)) {
            throw IllegalStateException("Missing metadata for session $sessionId")
        }
        val jsonBytes = Files.readAllBytes(plainPath)
        val metadata = json.decodeFromString(SessionMetadata.serializer(), String(jsonBytes, StandardCharsets.UTF_8))
        // Backfill encrypted copy if legacy data exists without encryption.
        Files.write(
            encryptedPath,
            encryptionManager.encrypt(jsonBytes, sessionId.toByteArray(StandardCharsets.UTF_8)),
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
        )
        return metadata
    }

    private fun ensurePlainMetadata(sessionDir: Path, jsonBytes: ByteArray) {
        val plainPath = sessionDir.resolve(metadataPlainFileName)
        if (Files.exists(plainPath)) {
            return
        }
        Files.write(
            plainPath,
            jsonBytes,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
        )
    }

    private fun rebuildStoredSessionsUnlocked(): List<StoredSession> {
        if (!baseDir.exists()) {
            return emptyList()
        }
        return Files.list(baseDir).use { stream ->
            stream
                .filter { Files.isDirectory(it) }
                .map { dir ->
                    runCatching {
                        val metadata = readMetadata(dir)
                        StoredSession(
                            id = metadata.sessionId,
                            status = metadata.status.toDomainStatus(),
                            createdAt = Instant.ofEpochMilli(metadata.createdAtEpochMs),
                            startedAt = metadata.startedAtEpochMs?.let { Instant.ofEpochMilli(it) },
                            stoppedAt = metadata.stoppedAtEpochMs?.let { Instant.ofEpochMilli(it) },
                            totalBytes = metadata.files.sumOf { it.bytes },
                            durationMs = metadata.totalDurationMs,
                            subjects = metadata.subjectIds,
                            eventCount = metadata.events.size,
                            deviceCount = metadata.files.map { it.deviceId }.toSet().size
                        )
                    }.getOrElse { ex ->
                        logger.error("Unable to load metadata for {}", dir, ex)
                        null
                    }
                }
                .filter { it != null }
                .map { it!! }
                .toList()
                .sortedByDescending { it.createdAt }
        }
    }

    private fun MetadataMetrics.toDomain(previous: SessionMetrics): SessionMetrics =
        SessionMetrics(
            gsrSamples = gsrSamples,
            videoFrames = videoFrames,
            thermalFrames = thermalFrames,
            audioSamples = audioSamples,
            deviceCount = previous.deviceCount,
            activeRecordingCount = previous.activeRecordingCount,
            updatedAt = Instant.now()
        )

    private fun SessionStatusDto.toDomainStatus(): SessionStatus = when (this) {
        SessionStatusDto.IDLE -> SessionStatus.IDLE
        SessionStatusDto.ACTIVE -> SessionStatus.ACTIVE
        SessionStatusDto.STOPPING -> SessionStatus.STOPPING
        SessionStatusDto.COMPLETED -> SessionStatus.COMPLETED
    }

    private fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }
}
