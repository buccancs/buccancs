package com.buccancs.data.sensor.connector.simulated

import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import kotlinx.coroutines.CoroutineScope
import kotlinx.datetime.Instant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
internal class SimulatedMicrophoneConnector @Inject constructor(
    @ApplicationScope scope: CoroutineScope,
    artifactFactory: SimulatedArtifactFactory
) : BaseSimulatedConnector(
    scope = scope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = com.buccancs.domain.model.DeviceId("phone-mic"),
        displayName = "Phone Microphone",
        type = SensorDeviceType.AUDIO_MICROPHONE,
        capabilities = setOf(SensorStreamType.AUDIO),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = emptyMap()
    )
) {

    constructor(scope: CoroutineScope) : this(scope, SimulatedTestSupport.artifactFactory())

    override fun streamIntervalMs(): Long = 220L

    override fun simulatedBatteryPercent(device: SensorDevice): Int? = null

    override fun simulatedRssi(device: SensorDevice): Int? = null

    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: com.buccancs.domain.model.RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val random = Random(deviceId.value.hashCode() * 19 + frameCounter.toInt())
        val buffer = simulatedBufferedSeconds(
            streamType = SensorStreamType.AUDIO,
            baseVideo = 0.0,
            baseSample = 0.6,
            randomizer = { random.nextDouble(0.0, 0.12) }
        )
        val status = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.AUDIO,
            sampleRateHz = 44_100.0,
            frameRateFps = null,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = buffer,
            isStreaming = true,
            isSimulated = true
        )
        return listOf(status)
    }
}
