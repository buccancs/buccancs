package com.buccancs.domain
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.domain.repository.TimeSyncRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class RecordingCoordinator @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val timeSyncRepository: TimeSyncRepository,
    private val transferRepository: SessionTransferRepository
) {
    suspend fun startOrResume(sessionId: String, requestedStart: Instant? = null): RecordingState {
        val syncStatus = timeSyncRepository.forceSync()
        val anchor = RecordingSessionAnchor(
            sessionId = sessionId,
            referenceTimestamp = requestedStart ?: Clock.System.now(),
            sharedClockOffsetMillis = syncStatus.offsetMillis
        )
        sensorRepository.startStreaming(anchor)
        return sensorRepository.recordingState.value
    }
    suspend fun stop(): RecordingState {
        val anchor = sensorRepository.stopStreaming()
        if (anchor != null) {
            val artifacts = runCatching {
                sensorRepository.collectSessionArtifacts(anchor.sessionId)
            }.getOrElse { emptyList() }
            if (artifacts.isNotEmpty()) {
                transferRepository.enqueue(anchor.sessionId, artifacts)
            }
        }
        return sensorRepository.recordingState.value
    }
}
