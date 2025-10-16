package com.buccancs.data.sensor.exercise

import com.buccancs.application.recording.RecordingService
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.util.nowInstant
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class MultiDeviceRecordingExercise @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val recordingService: RecordingService
) {
    suspend fun run(
        sessionPrefix: String = "exercise",
        warmupMillis: Long = DEFAULT_WARMUP_MILLIS,
        startTimeoutMillis: Long = DEFAULT_START_TIMEOUT_MILLIS,
        stopTimeoutMillis: Long = DEFAULT_STOP_TIMEOUT_MILLIS
    ): RecordingExerciseResult {
        val devicesSnapshot = sensorRepository.devices.value
        val targetDevices = devicesSnapshot.filter { device ->
            device.capabilities.any { it != SensorStreamType.PREVIEW }
        }
        val sessionId = buildSessionId(sessionPrefix)
        val startedAt = nowInstant()
        recordingService.startOrResume(sessionId, startedAt)
        val startObservations = awaitStreaming(
            targetDevices = targetDevices,
            expectedActive = true,
            timeoutMillis = startTimeoutMillis
        )
        delay(warmupMillis)
        val stopSummary = recordingService.stopWithSummary()
        val stopObservations = awaitStreaming(
            targetDevices = targetDevices,
            expectedActive = false,
            timeoutMillis = stopTimeoutMillis
        )
        val artifactGroups = stopSummary.artifacts.groupBy { it.deviceId }
        val deviceResults = targetDevices.map { device ->
            val start = startObservations[device.id]
            val stop = stopObservations[device.id]
            val artifacts = artifactGroups[device.id].orEmpty()
            DeviceExerciseResult(
                deviceId = device.id,
                displayName = device.displayName,
                streamsObserved = start?.streamTypes ?: emptySet(),
                startObservedAt = start?.timestamp,
                stopObservedAt = stop?.timestamp,
                artifacts = artifacts,
                success = start != null && stop != null
            )
        }
        return RecordingExerciseResult(
            sessionId = sessionId,
            startedAt = startedAt,
            recordingState = stopSummary.state,
            anchor = stopSummary.anchor,
            artifacts = stopSummary.artifacts,
            deviceResults = deviceResults
        )
    }

    private suspend fun awaitStreaming(
        targetDevices: List<SensorDevice>,
        expectedActive: Boolean,
        timeoutMillis: Long
    ): Map<DeviceId, StreamingObservation> {
        if (targetDevices.isEmpty()) {
            return emptyMap()
        }
        val observed = mutableMapOf<DeviceId, StreamingObservation>()
        withTimeoutOrNull(timeoutMillis) {
            sensorRepository.streamStatuses.collect { statuses ->
                val timestamp = nowInstant()
                targetDevices.forEach { device ->
                    if (observed.containsKey(device.id)) return@forEach
                    val deviceStatuses = statuses.filter { it.deviceId == device.id }
                    val nonPreviewActive =
                        deviceStatuses.any { it.streamType != SensorStreamType.PREVIEW && it.isStreaming }
                    val meetsExpectation = if (expectedActive) nonPreviewActive else !nonPreviewActive
                    if (meetsExpectation) {
                        val activeStreams = if (expectedActive) {
                            deviceStatuses.filter { it.isStreaming }.map { it.streamType }.toSet()
                        } else {
                            emptySet()
                        }
                        observed[device.id] = StreamingObservation(timestamp, activeStreams)
                    }
                }
                if (observed.size == targetDevices.size) {
                    cancel()
                }
            }
        }
        return observed.toMap()
    }

    private fun buildSessionId(prefix: String): String =
        "$prefix-${System.currentTimeMillis()}"

    companion object {
        private const val DEFAULT_WARMUP_MILLIS = 3_000L
        private const val DEFAULT_START_TIMEOUT_MILLIS = 10_000L
        private const val DEFAULT_STOP_TIMEOUT_MILLIS = 10_000L
    }
}

data class RecordingExerciseResult(
    val sessionId: String,
    val startedAt: Instant,
    val recordingState: RecordingState,
    val anchor: RecordingSessionAnchor?,
    val artifacts: List<SessionArtifact>,
    val deviceResults: List<DeviceExerciseResult>
) {
    val success: Boolean
        get() = deviceResults.all { it.success }
}

data class DeviceExerciseResult(
    val deviceId: DeviceId,
    val displayName: String,
    val streamsObserved: Set<SensorStreamType>,
    val startObservedAt: Instant?,
    val stopObservedAt: Instant?,
    val artifacts: List<SessionArtifact>,
    val success: Boolean
)

data class StreamingObservation(
    val timestamp: Instant,
    val streamTypes: Set<SensorStreamType>
)
