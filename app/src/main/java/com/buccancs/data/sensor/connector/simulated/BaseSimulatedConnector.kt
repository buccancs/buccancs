package com.buccancs.data.sensor.connector.simulated

import com.buccancs.util.nowInstant
import com.buccancs.data.sensor.connector.SensorConnector
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceCommandResult
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.math.max
import kotlin.random.Random

internal abstract class BaseSimulatedConnector(
    protected val scope: CoroutineScope,
    protected val artifactFactory: SimulatedArtifactFactory,
    initialDevice: SensorDevice
) : SensorConnector {
    final override val deviceId: DeviceId = initialDevice.id
    protected val deviceState = MutableStateFlow(initialDevice)
    protected val statusState = MutableStateFlow(emptyList<SensorStreamStatus>())
    private var streamJob: Job? = null
    private var simulationEnabled = false
    private var recordingStartedAt: Instant? = null
    protected var lastRecordingDurationMs: Long = 0
    protected var lastSessionAnchor: RecordingSessionAnchor? = null
    protected val isSimulationMode: Boolean
        get() = simulationEnabled
    override val device: StateFlow<SensorDevice> = deviceState.asStateFlow()
    override val streamStatuses: StateFlow<List<SensorStreamStatus>> = statusState.asStateFlow()
    override suspend fun refreshInventory() {
        if (simulationEnabled) {
            ensureConnectedForSimulation()
        }
    }

    override suspend fun applySimulation(enabled: Boolean) {
        simulationEnabled = enabled
        if (enabled) {
            ensureConnectedForSimulation()
        } else {
            disconnect()
        }
    }

    override suspend fun connect(): DeviceCommandResult {
        if (!simulationEnabled) {
            return DeviceCommandResult.Rejected("Simulation disabled and no hardware handler implemented yet.")
        }
        ensureConnectedForSimulation()
        return DeviceCommandResult.Accepted
    }

    override suspend fun disconnect(): DeviceCommandResult {
        stopStreaming()
        deviceState.update { device ->
            device.copy(
                connectionStatus = ConnectionStatus.Disconnected,
                isSimulated = simulationEnabled && device.isSimulated
            )
        }
        statusState.value = emptyList()
        clearRecordingMetadata()
        return DeviceCommandResult.Accepted
    }

    override suspend fun startStreaming(anchor: RecordingSessionAnchor): DeviceCommandResult {
        if (deviceState.value.connectionStatus !is ConnectionStatus.Connected) {
            return DeviceCommandResult.Rejected("Device not connected.")
        }
        streamJob?.cancel()
        recordingStartedAt = nowInstant()
        lastSessionAnchor = anchor
        lastRecordingDurationMs = 0
        streamJob = scope.launch {
            var frameCounter = 0L
            while (isActive) {
                statusState.value = sampleStatuses(
                    timestamp = nowInstant(),
                    frameCounter = frameCounter,
                    anchor = anchor
                )
                frameCounter++
                delay(streamIntervalMs())
            }
        }
        return DeviceCommandResult.Accepted
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        streamJob?.cancel()
        streamJob = null
        recordingStartedAt?.let { start ->
        val currentInstant = nowInstant()
        val duration = (currentInstant.toEpochMilliseconds() - start.toEpochMilliseconds()).coerceAtLeast(0)
            lastRecordingDurationMs = duration
        }
        recordingStartedAt = null
        statusState.value = emptyList()
        return DeviceCommandResult.Accepted
    }

    override suspend fun collectArtifacts(sessionId: String): List<SessionArtifact> {
        if (!isSimulationMode) {
            return emptyList()
        }
        val streams = deviceState.value.capabilities.filter { it != SensorStreamType.PREVIEW }
        if (streams.isEmpty()) {
            clearRecordingMetadata()
            return emptyList()
        }
        val durationMs = recordingDurationMs()
        val artifacts = streams.mapNotNull { stream ->
            when (stream) {
                SensorStreamType.GSR ->
                    artifactFactory.createArtifact(
                        sessionId = sessionId,
                        deviceId = deviceId,
                        streamType = stream,
                        extension = "csv",
                        mimeType = "text/csv"
                    ) {
                        generateGsrCsv(durationMs)
                    }

                SensorStreamType.RGB_VIDEO ->
                    artifactFactory.createRandomArtifact(
                        sessionId = sessionId,
                        deviceId = deviceId,
                        streamType = stream,
                        extension = "mp4",
                        mimeType = "video/mp4",
                        sizeBytes = estimateBinarySize(durationMs, bytesPerSecond = 750_000, minimumBytes = 512_000)
                    )

                SensorStreamType.THERMAL_VIDEO ->
                    artifactFactory.createRandomArtifact(
                        sessionId = sessionId,
                        deviceId = deviceId,
                        streamType = stream,
                        extension = "raw",
                        mimeType = "application/octet-stream",
                        sizeBytes = estimateBinarySize(durationMs, bytesPerSecond = 320_000, minimumBytes = 196_608)
                    )

                SensorStreamType.AUDIO ->
                    artifactFactory.createRandomArtifact(
                        sessionId = sessionId,
                        deviceId = deviceId,
                        streamType = stream,
                        extension = "wav",
                        mimeType = "audio/wav",
                        sizeBytes = estimateBinarySize(durationMs, bytesPerSecond = 176_000, minimumBytes = 131_072)
                    )

                SensorStreamType.PREVIEW -> null
            }
        }
        clearRecordingMetadata()
        return artifacts
    }

    protected fun ensureConnectedForSimulation() {
        val connected = deviceState.value.connectionStatus is ConnectionStatus.Connected
        if (connected) return
        val now = nowInstant()
        deviceState.update { device ->
            device.copy(
                connectionStatus = ConnectionStatus.Connected(
                    since = now,
                    batteryPercent = simulatedBatteryPercent(device),
                    rssiDbm = simulatedRssi(device)
                ),
                isSimulated = true
            )
        }
    }

    protected abstract fun streamIntervalMs(): Long
    protected abstract fun simulatedBatteryPercent(device: SensorDevice): Int?
    protected abstract fun simulatedRssi(device: SensorDevice): Int?
    protected abstract fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus>

    protected fun simulatedBufferedSeconds(
        streamType: SensorStreamType,
        baseVideo: Double,
        baseSample: Double,
        randomizer: () -> Double
    ): Double {
        val base = when (streamType) {
            SensorStreamType.GSR,
            SensorStreamType.AUDIO -> baseSample

            else -> baseVideo
        }
        return (base + randomizer()).coerceAtLeast(0.0)
    }

    protected fun recordingDurationMs(minimumMs: Long = 1_000L): Long =
        lastRecordingDurationMs.coerceAtLeast(minimumMs)

    protected fun currentSessionAnchor(): RecordingSessionAnchor? = lastSessionAnchor
    protected fun clearRecordingMetadata() {
        lastRecordingDurationMs = 0
        lastSessionAnchor = null
    }

    private fun estimateBinarySize(durationMs: Long, bytesPerSecond: Int, minimumBytes: Int): Int {
        val seconds = durationMs.coerceAtLeast(1_000L) / 1_000.0
        val estimated = (seconds * bytesPerSecond).toInt()
        return max(minimumBytes, estimated)
    }

    private fun generateGsrCsv(durationMs: Long): ByteArray {
        val sampleRate = 128
        val duration = durationMs.coerceAtLeast(1_000L)
        val sampleCount = max(sampleRate, ((duration * sampleRate) / 1_000L).toInt())
        val startTimestamp = (currentSessionAnchor()?.referenceTimestamp ?: nowInstant()).toEpochMilliseconds()
        val random = Random(deviceId.value.hashCode())
        val builder = StringBuilder()
        builder.append("timestamp_ms,gsr_uS\n")
        repeat(sampleCount) { index ->
            val timestamp = startTimestamp + (index * 1_000L) / sampleRate
            val value = 5.0 + random.nextDouble(-0.3, 0.3)
            builder.append(timestamp)
            builder.append(',')
            builder.append(String.format(Locale.US, "%.4f", value))
            builder.append('\n')
        }
        return builder.toString().toByteArray(StandardCharsets.US_ASCII)
    }
}
