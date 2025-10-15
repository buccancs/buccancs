package com.buccancs.data.sensor.connector.simulated

import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import kotlinx.coroutines.CoroutineScope
import kotlin.time.Instant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
internal class SimulatedRgbCameraConnector @Inject constructor(
    @ApplicationScope scope: CoroutineScope,
    artifactFactory: SimulatedArtifactFactory
) : BaseSimulatedConnector(
    scope = scope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = com.buccancs.domain.model.DeviceId("phone-rgb"),
        displayName = "Phone RGB Camera",
        type = SensorDeviceType.ANDROID_RGB_CAMERA,
        capabilities = setOf(
            SensorStreamType.RGB_VIDEO,
            SensorStreamType.RAW_DNG,
            SensorStreamType.PREVIEW
        ),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = mapOf("cameraId" to "0")
    )
) {
    constructor(scope: CoroutineScope) : this(scope, SimulatedTestSupport.artifactFactory())

    override fun streamIntervalMs(): Long = 160L
    override fun simulatedBatteryPercent(device: SensorDevice): Int? = null
    override fun simulatedRssi(device: SensorDevice): Int? = null
    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: com.buccancs.domain.model.RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val random = Random(deviceId.value.hashCode() + frameCounter.toInt())
        val rgbStream = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.RGB_VIDEO,
            sampleRateHz = null,
            frameRateFps = 30.0,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = simulatedBufferedSeconds(
                streamType = SensorStreamType.RGB_VIDEO,
                baseVideo = 0.5,
                baseSample = 0.0,
                randomizer = { random.nextDouble(0.0, 0.2) }
            ),
            isStreaming = true,
            isSimulated = true
        )
        val rawStream = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.RAW_DNG,
            sampleRateHz = null,
            frameRateFps = 1.0,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = simulatedBufferedSeconds(
                streamType = SensorStreamType.RAW_DNG,
                baseVideo = 0.2,
                baseSample = 0.0,
                randomizer = { random.nextDouble(0.0, 0.05) }
            ),
            isStreaming = true,
            isSimulated = true
        )
        val previewStream = SensorStreamStatus(
            deviceId = deviceId,
            streamType = SensorStreamType.PREVIEW,
            sampleRateHz = null,
            frameRateFps = 15.0,
            lastSampleTimestamp = timestamp,
            bufferedDurationSeconds = simulatedBufferedSeconds(
                streamType = SensorStreamType.PREVIEW,
                baseVideo = 0.4,
                baseSample = 0.0,
                randomizer = { random.nextDouble(0.0, 0.1) }
            ),
            isStreaming = true,
            isSimulated = true
        )
        return listOf(rgbStream, rawStream, previewStream)
    }
}
