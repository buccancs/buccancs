package com.buccancs.application.recording

import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.recording.manifest.ManifestWriter
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.util.nowInstant
import kotlinx.datetime.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRecordingService @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val timeSyncService: TimeSyncService,
    private val transferRepository: SessionTransferRepository,
    private val deviceEventRepository: DeviceEventRepository,
    private val manifestWriter: ManifestWriter
) : RecordingService {
    override suspend fun startOrResume(sessionId: String, requestedStart: Instant?): RecordingState {
        val syncStatus = timeSyncService.forceSync()
        val anchor = RecordingSessionAnchor(
            sessionId = sessionId,
            referenceTimestamp = requestedStart ?: nowInstant(),
            sharedClockOffsetMillis = syncStatus.offsetMillis
        )
        manifestWriter.beginSession(
            anchor = anchor,
            devices = sensorRepository.devices.value,
            simulation = sensorRepository.simulationEnabled.value
        )
        sensorRepository.startStreaming(anchor)
        return sensorRepository.recordingState.value
    }

    override suspend fun stop(): RecordingState = stopWithSummary().state

    override suspend fun stopWithSummary(): RecordingStopSummary {
        val anchor = sensorRepository.stopStreaming()
        val artifacts = if (anchor != null) {
            runCatching { sensorRepository.collectSessionArtifacts(anchor.sessionId) }
                .getOrElse { emptyList() }
        } else {
            emptyList()
        }
        if (anchor != null && artifacts.isNotEmpty()) {
            manifestWriter.appendArtifacts(anchor.sessionId, artifacts)
            transferRepository.enqueue(anchor.sessionId, artifacts)
        }
        if (anchor != null) {
            manifestWriter.finaliseSession(
                sessionId = anchor.sessionId,
                endedAt = nowInstant(),
                events = deviceEventRepository.events.value,
                artifacts = artifacts
            )
        }
        val state = sensorRepository.recordingState.value
        return RecordingStopSummary(
            state = state,
            anchor = anchor,
            artifacts = artifacts
        )
    }
}
