package com.buccancs.data.transfer

import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.UploadState
import com.buccancs.domain.model.UploadStatus
import com.buccancs.domain.repository.SessionTransferRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSessionTransferRepository @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val client: DataTransferClient
) : SessionTransferRepository {

    private val uploadsState = MutableStateFlow<Map<String, UploadStatus>>(emptyMap())
    private val uploadsSnapshot = MutableStateFlow<List<UploadStatus>>(emptyList())
    private val mutex = Mutex()
    private val queue = Channel<UploadCommand>(Channel.UNLIMITED)

    override val uploads: StateFlow<List<UploadStatus>> = uploadsSnapshot.asStateFlow()

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
        artifacts.forEach { artifact ->
            val key = keyFor(sessionId, artifact)
            mutex.withLock {
                val initial = UploadStatus(
                    sessionId = sessionId,
                    deviceId = artifact.deviceId,
                    streamType = artifact.streamType,
                    fileName = artifact.file.name,
                    bytesTotal = artifact.sizeBytes,
                    bytesTransferred = 0,
                    attempt = 0,
                    state = UploadState.QUEUED,
                    errorMessage = null
                )
                val next = uploadsState.value.toMutableMap()
                next[key] = initial
                publish(next)
            }
            queue.send(UploadCommand(sessionId, artifact))
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
                command.artifact.file.delete()
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
        mutex.withLock {
            val key = keyFor(command.sessionId, command.artifact)
            val baseline = uploadsState.value[key]
            val total = baseline?.bytesTotal ?: command.artifact.sizeBytes
            val updated = UploadStatus(
                sessionId = command.sessionId,
                deviceId = command.artifact.deviceId,
                streamType = command.artifact.streamType,
                fileName = command.artifact.file.name,
                bytesTotal = total,
                bytesTransferred = bytesTransferred.coerceAtMost(total),
                attempt = attempt,
                state = state,
                errorMessage = error
            )
            val next = uploadsState.value.toMutableMap()
            next[key] = updated
            publish(next)
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
    }

    private fun keyFor(sessionId: String, artifact: SessionArtifact): String =
        listOf(sessionId, artifact.deviceId.value, artifact.streamType.name).joinToString("|")

    private fun backoffFor(attempt: Int): Long = 1_000L * attempt.coerceAtMost(5)

    private data class UploadCommand(
        val sessionId: String,
        val artifact: SessionArtifact
    )

    private companion object {
        private const val MAX_ATTEMPTS = 3
    }
}
