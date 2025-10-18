package com.buccancs.application.recording

import com.buccancs.application.performance.PerformanceMetricsAnalyzer
import com.buccancs.application.performance.PerformanceMetricsRecorder
import com.buccancs.application.time.TimeSyncService
import com.buccancs.data.recording.manifest.ManifestWriter
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.repository.BookmarkRepository
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class DefaultRecordingService @Inject constructor(
    @ApplicationScope private val appScope: CoroutineScope,
    private val sensorRepository: SensorRepository,
    private val timeSyncService: TimeSyncService,
    private val transferRepository: SessionTransferRepository,
    private val deviceEventRepository: DeviceEventRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val manifestWriter: ManifestWriter,
    private val performanceMetricsRecorder: PerformanceMetricsRecorder,
    private val performanceMetricsAnalyzer: PerformanceMetricsAnalyzer
) : RecordingService {
    override suspend fun startOrResume(
        sessionId: String,
        requestedStart: Instant?
    ): RecordingState {
        return withContext(
            appScope.coroutineContext
        ) {
            val syncStatus =
                timeSyncService.forceSync()
            bookmarkRepository.clear()
            val anchor =
                RecordingSessionAnchor(
                    sessionId = sessionId,
                    referenceTimestamp = requestedStart
                        ?: nowInstant(),
                    sharedClockOffsetMillis = syncStatus.offsetMillis
                )
            manifestWriter.beginSession(
                anchor = anchor,
                devices = sensorRepository.devices.value,
                simulation = sensorRepository.simulationEnabled.value
            )
            try {
                performanceMetricsRecorder.start(
                    anchor.sessionId
                )
                sensorRepository.startStreaming(
                    anchor
                )
            } catch (error: Throwable) {
                performanceMetricsRecorder.stop()
                throw error
            }
            sensorRepository.recordingState.value
        }
    }

    override suspend fun stop(): RecordingState =
        stopWithSummary().state

    override suspend fun stopWithSummary(): RecordingStopSummary {
        val anchor =
            try {
                sensorRepository.stopStreaming()
            } finally {
                performanceMetricsRecorder.stop()
            }
        val artifacts =
            if (anchor != null) {
                runCatching {
                    sensorRepository.collectSessionArtifacts(
                        anchor.sessionId
                    )
                }
                    .getOrElse { emptyList() }
            } else {
                emptyList()
            }
        if (anchor != null && artifacts.isNotEmpty()) {
            manifestWriter.appendArtifacts(
                anchor.sessionId,
                artifacts
            )
            transferRepository.enqueue(
                anchor.sessionId,
                artifacts
            )
        }
        if (anchor != null) {
            performanceMetricsAnalyzer.summarise(
                anchor.sessionId
            )
            manifestWriter.finaliseSession(
                sessionId = anchor.sessionId,
                endedAt = nowInstant(),
                events = deviceEventRepository.events.value,
                artifacts = artifacts
            )
        }
        val state =
            sensorRepository.recordingState.value
        return RecordingStopSummary(
            state = state,
            anchor = anchor,
            artifacts = artifacts
        )
    }
}
