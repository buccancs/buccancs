package com.buccancs.data.transfer

import android.content.Context
import android.util.Log
import com.buccancs.data.network.NetworkStateProvider
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadRecoveryRecord
import com.buccancs.domain.model.UploadState
import com.buccancs.domain.model.UploadStatus
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.util.nowInstant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Instant
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max

@Singleton
class DefaultSessionTransferRepository @Inject constructor(
    @param:ApplicationScope private val scope: CoroutineScope,
    private val client: DataTransferClient,
    @param:ApplicationContext private val context: Context,
    private val uploadRecoveryLogger: UploadRecoveryLogger,
    private val networkStateProvider: NetworkStateProvider,
    private val backlogTelemetryLogger: BacklogTelemetryLogger
) : SessionTransferRepository {
    private val uploadsState = MutableStateFlow<Map<String, UploadStatus>>(emptyMap())
    private val uploadsSnapshot = MutableStateFlow<List<UploadStatus>>(emptyList())
    private val mutex = Mutex()
    private val queue = Channel<UploadCommand>(Channel.UNLIMITED)
    private val recoveryState = MutableStateFlow<List<UploadRecoveryRecord>>(emptyList())
    private val recoveryMutex = Mutex()
    private val backlogState = MutableStateFlow(
        UploadBacklogState(
            level = UploadBacklogLevel.NORMAL,
            queuedCount = 0,
            queuedBytes = 0,
            message = null
        )
    )
    private val pendingQueue = mutableListOf<PendingArtifact>()
    private var lastBacklogLevel = UploadBacklogLevel.NORMAL

    override val uploads: StateFlow<List<UploadStatus>> = uploadsSnapshot.asStateFlow()
    override val recovery: StateFlow<List<UploadRecoveryRecord>> = recoveryState.asStateFlow()
    override val backlog: StateFlow<UploadBacklogState> = backlogState.asStateFlow()

    init {
        scope.launch {
            for (command in queue) {
                process(command)
            }
        }
    }

    override suspend fun enqueue(sessionId: String, artifacts: List<SessionArtifact>) {
        if (artifacts.isEmpty()) {
            return
        }
        val allowed = mutableListOf<PendingArtifact>()
        val dropped = mutableListOf<DroppedArtifact>()

        mutex.withLock {
            var pendingBytes = pendingBytesLocked()
            var pendingCount = pendingCountLocked()
            val next = uploadsState.value.toMutableMap()

            artifacts.forEach { artifact ->
                val shouldDrop = UploadBacklogCalculator.shouldDrop(pendingBytes, pendingCount, artifact.sizeBytes)
                if (shouldDrop) {
                    val status = UploadStatus(
                        sessionId = sessionId,
                        deviceId = artifact.deviceId,
                        streamType = artifact.streamType,
                        fileName = artifactName(artifact),
                        bytesTotal = artifact.sizeBytes,
                        bytesTransferred = 0,
                        attempt = 0,
                        state = UploadState.FAILED,
                        errorMessage = OVERFLOW_MESSAGE
                    )
                    next[keyFor(sessionId, artifact)] = status
                    dropped += DroppedArtifact(sessionId, artifact, status)
                } else {
                    pendingBytes += max(artifact.sizeBytes, 0L)
                    pendingCount += 1
                    val status = UploadStatus(
                        sessionId = sessionId,
                        deviceId = artifact.deviceId,
                        streamType = artifact.streamType,
                        fileName = artifactName(artifact),
                        bytesTotal = artifact.sizeBytes,
                        bytesTransferred = 0,
                        attempt = 0,
                        state = UploadState.QUEUED,
                        errorMessage = null
                    )
                    next[keyFor(sessionId, artifact)] = status
                    val pending = PendingArtifact(
                        sessionId = sessionId,
                        artifact = artifact,
                        queuedAt = nowInstant()
                    )
                    pendingQueue.add(pending)
                    allowed += pending
                }
            }

            publish(next)
        }

        dropped.forEach { droppedArtifact ->
            droppedArtifact.artifact.file?.delete()
            recordOverflow(droppedArtifact.status)
            Log.w(
                TAG,
                "Dropped artifact due to backlog overflow: ${droppedArtifact.artifact.file?.name ?: droppedArtifact.artifact.uri}"
            )
        }

        allowed.forEach { pending ->
            queue.send(UploadCommand(pending.sessionId, pending.artifact))
        }

        if (allowed.isNotEmpty()) {
            WorkPolicy.enqueueUpload(context)
        }
    }

    private suspend fun process(command: UploadCommand) {
        var attempt = 1
        while (true) {
            updateStatus(
                command = command,
                attempt = attempt,
                state = UploadState.IN_PROGRESS,
                bytesTransferred = 0,
                error = null
            )
            val result = client.upload(command.sessionId, command.artifact) { transferred ->
                updateProgress(command, attempt, transferred)
            }
            if (result.success) {
                updateStatus(
                    command = command,
                    attempt = attempt,
                    state = UploadState.COMPLETED,
                    bytesTransferred = command.artifact.sizeBytes,
                    error = null
                )
                command.artifact.file?.delete()
                break
            } else {
                updateStatus(
                    command = command,
                    attempt = attempt,
                    state = UploadState.FAILED,
                    bytesTransferred = command.artifact.sizeBytes,
                    error = result.errorMessage
                )
                if (attempt >= MAX_ATTEMPTS) {
                    break
                }
                attempt++
                delay(backoffFor(attempt))
            }
        }
    }

    private suspend fun updateProgress(command: UploadCommand, attempt: Int, transferred: Long) {
        mutex.withLock {
            val key = keyFor(command.sessionId, command.artifact)
            val current = uploadsState.value[key] ?: return@withLock
            val updated = current.copy(
                bytesTransferred = transferred.coerceAtMost(current.bytesTotal),
                attempt = attempt,
                state = UploadState.IN_PROGRESS,
                errorMessage = null
            )
            val next = uploadsState.value.toMutableMap()
            next[key] = updated
            publish(next)
        }
    }

    private suspend fun updateStatus(
        command: UploadCommand,
        attempt: Int,
        state: UploadState,
        bytesTransferred: Long,
        error: String?
    ) {
        var updatedStatus: UploadStatus? = null
        mutex.withLock {
            val key = keyFor(command.sessionId, command.artifact)
            val baseline = uploadsState.value[key]
            val total = baseline?.bytesTotal ?: command.artifact.sizeBytes
            val updated = UploadStatus(
                sessionId = command.sessionId,
                deviceId = command.artifact.deviceId,
                streamType = command.artifact.streamType,
                fileName = artifactName(command.artifact),
                bytesTotal = total,
                bytesTransferred = bytesTransferred.coerceAtMost(total),
                attempt = attempt,
                state = state,
                errorMessage = error
            )
            val next = uploadsState.value.toMutableMap()
            next[key] = updated
            publish(next)
            updatedStatus = updated
            if (state == UploadState.COMPLETED || state == UploadState.FAILED) {
                val index = pendingQueue.indexOfFirst { pending ->
                    pending.sessionId == command.sessionId &&
                            pending.artifact.deviceId == command.artifact.deviceId &&
                            pending.artifact.streamType == command.artifact.streamType
                }
                if (index >= 0) {
                    pendingQueue.removeAt(index)
                }
            }
        }
        if (updatedStatus != null) {
            when {
                state == UploadState.FAILED || state == UploadState.COMPLETED -> recordRecovery(updatedStatus!!)
                state == UploadState.IN_PROGRESS && attempt > 1 -> recordRecovery(updatedStatus!!)
            }
        }
    }

    private fun publish(next: MutableMap<String, UploadStatus>) {
        uploadsState.value = next.toMap()
        uploadsSnapshot.value = next.values
            .sortedWith(
                compareBy<UploadStatus>(
                    {
                        when (it.state) {
                            UploadState.IN_PROGRESS -> 0
                            UploadState.FAILED -> 1
                            UploadState.QUEUED -> 2
                            UploadState.COMPLETED -> 3
                        }
                    },
                    { it.sessionId },
                    { it.deviceId.value },
                    { it.streamType.name }
                )
            )
        val snapshot = UploadBacklogCalculator.snapshot(next.values)
        backlogState.value = snapshot
        if (snapshot.level != lastBacklogLevel) {
            scope.launch {
                backlogTelemetryLogger.append(snapshot)
            }
            lastBacklogLevel = snapshot.level
        }
    }

    private fun keyFor(sessionId: String, artifact: SessionArtifact): String =
        listOf(sessionId, artifact.deviceId.value, artifact.streamType.name).joinToString("|")

    private fun artifactName(artifact: SessionArtifact): String =
        artifact.file?.name
            ?: artifact.uri.lastPathSegment
            ?: "${artifact.deviceId.value}-${artifact.streamType.name.lowercase(Locale.US)}"

    private fun backoffFor(attempt: Int): Long = 1_000L * attempt.coerceAtMost(5)

    private suspend fun recordRecovery(status: UploadStatus) {
        val snapshot = networkStateProvider.snapshot()
        val record = UploadRecoveryRecord(
            sessionId = status.sessionId,
            deviceId = status.deviceId,
            streamType = status.streamType,
            attempt = status.attempt,
            state = status.state,
            timestamp = now(),
            bytesTransferred = status.bytesTransferred,
            bytesTotal = status.bytesTotal,
            network = snapshot,
            errorMessage = status.errorMessage
        )
        recoveryMutex.withLock {
            val next = (recoveryState.value + record)
                .takeLast(MAX_RECOVERY_EVENTS)
            recoveryState.value = next
        }
        uploadRecoveryLogger.append(record)
    }

    private fun now(): Instant = nowInstant()

    private fun pendingBytesLocked(): Long =
        uploadsState.value.values
            .filter(UploadBacklogCalculator::isActive)
            .sumOf { (it.bytesTotal - it.bytesTransferred).coerceAtLeast(0) }

    private fun pendingCountLocked(): Int =
        uploadsState.value.values.count(UploadBacklogCalculator::isActive)

    private suspend fun recordOverflow(status: UploadStatus) {
        recordRecovery(status)
    }

    private data class UploadCommand(
        val sessionId: String,
        val artifact: SessionArtifact
    )

    private data class PendingArtifact(
        val sessionId: String,
        val artifact: SessionArtifact,
        val queuedAt: Instant
    )

    private data class DroppedArtifact(
        val sessionId: String,
        val artifact: SessionArtifact,
        val status: UploadStatus
    )

    private companion object {
        private const val TAG = "SessionTransferRepository"
        private const val MAX_RECOVERY_EVENTS = 128
        private const val MAX_ATTEMPTS = 3
        private const val OVERFLOW_MESSAGE = UploadBacklogCalculator.OVERFLOW_MESSAGE
    }
}
