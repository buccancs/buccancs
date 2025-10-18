package com.buccancs.data.sensor.connector.simulated

import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.data.sensor.SensorStreamClient
import com.buccancs.data.sensor.SensorStreamEmitter
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.sin
import kotlin.random.Random

@Singleton
internal class SimulatedShimmerConnector @Inject constructor(
    @ApplicationScope scope: CoroutineScope,
    artifactFactory: SimulatedArtifactFactory,
    private val streamClient: SensorStreamClient
) : BaseSimulatedConnector(
    scope = scope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = DeviceId(
            "shimmer-001"
        ),
        displayName = "Shimmer3 GSR",
        type = SensorDeviceType.SHIMMER_GSR,
        capabilities = setOf(
            SensorStreamType.GSR
        ),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = mapOf(
            "mac" to "00:00:00:00:00:01"
        )
    )
) {
    private var emitter: SensorStreamEmitter? =
        null
    private var emissionJob: Job? =
        null

    constructor(
        scope: CoroutineScope
    ) : this(
        scope = scope,
        artifactFactory = SimulatedTestSupport.artifactFactory(),
        streamClient = NoOpSensorStreamClient()
    )

    override fun streamIntervalMs(): Long =
        250L

    override fun simulatedBatteryPercent(
        device: SensorDevice
    ): Int? {
        val baseline =
            90 - (device.id.value.hashCode().absoluteValue % 12)
        return baseline.coerceIn(
            40,
            98
        )
    }

    override fun simulatedRssi(
        device: SensorDevice
    ): Int? =
        -48

    override fun sampleStatuses(
        timestamp: kotlin.time.Instant,
        frameCounter: Long,
        anchor: RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val random =
            Random(
                deviceId.value.hashCode() + frameCounter.toInt()
            )
        val sampleRateHz =
            128.0
        val buffer =
            simulatedBufferedSeconds(
                streamType = SensorStreamType.GSR,
                baseVideo = 0.0,
                baseSample = 0.6,
                randomizer = {
                    random.nextDouble(
                        0.0,
                        0.15
                    )
                }
            )
        return listOf(
            SensorStreamStatus(
                deviceId = deviceId,
                streamType = SensorStreamType.GSR,
                sampleRateHz = sampleRateHz,
                frameRateFps = null,
                lastSampleTimestamp = timestamp,
                bufferedDurationSeconds = buffer,
                isStreaming = true,
                isSimulated = true
            )
        )
    }

    override suspend fun startStreaming(
        anchor: RecordingSessionAnchor
    ): DeviceCommandResult {
        val result =
            super.startStreaming(
                anchor
            )
        if (result is DeviceCommandResult.Accepted) {
            val emitter =
                streamClient.openStream(
                    sessionId = anchor.sessionId,
                    deviceId = deviceId,
                    streamId = STREAM_ID,
                    sampleRateHz = SAMPLE_RATE_HZ
                )
            this.emitter =
                emitter
            emissionJob?.cancel()
            emissionJob =
                scope.launch(
                    Dispatchers.Default
                ) {
                    var sampleIndex =
                        0L
                    val startTimestamp =
                        nowInstant()
                    while (isActive) {
                        val elapsedMs =
                            sampleIndex * SAMPLE_PERIOD_MS
                        val timestampMs =
                            startTimestamp.toEpochMilliseconds() + elapsedMs
                        val conductance =
                            generateConductance(
                                sampleIndex
                            )
                        val resistance =
                            if (conductance > 0.0) 1_000_000.0 / conductance else 0.0
                        emitter.emit(
                            timestampEpochMs = timestampMs,
                            values = mapOf(
                                "conductance_microsiemens" to conductance,
                                "resistance_ohms" to resistance
                            )
                        )
                        sampleIndex++
                        delay(
                            SAMPLE_PERIOD_MS
                        )
                    }
                }
        }
        return result
    }

    override suspend fun stopStreaming(): DeviceCommandResult {
        emissionJob?.cancel()
        emissionJob =
            null
        runCatching { emitter?.close() }
        emitter =
            null
        return super.stopStreaming()
    }

    private fun generateConductance(
        index: Long
    ): Double {
        val base =
            5.0 + sin(
                index * 2.0 * PI / 256.0
            ) * 0.2
        val noise =
            Random(
                index.toInt()
            ).nextDouble(
                -0.05,
                0.05
            )
        return (base + noise).coerceAtLeast(
            0.1
        )
    }

    private class NoOpSensorStreamClient :
        SensorStreamClient {
        override suspend fun openStream(
            sessionId: String,
            deviceId: DeviceId,
            streamId: String,
            sampleRateHz: Double
        ): SensorStreamEmitter =
            object :
                SensorStreamEmitter {
                override suspend fun emit(
                    timestampEpochMs: Long,
                    values: Map<String, Double>
                ) =
                    Unit

                override suspend fun close() =
                    Unit
            }
    }

    private companion object {
        private const val STREAM_ID =
            "gsr"
        private const val SAMPLE_RATE_HZ =
            128.0
        private const val SAMPLE_PERIOD_MS =
            8L
    }
}
