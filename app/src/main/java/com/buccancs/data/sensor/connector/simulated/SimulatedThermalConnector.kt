package com.buccancs.data.sensor.connector.simulated

import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random
import kotlin.time.Instant

@Singleton
internal class SimulatedThermalConnector @Inject constructor(
    @ApplicationScope scope: CoroutineScope,
    artifactFactory: SimulatedArtifactFactory
) : BaseSimulatedConnector(
    scope = scope,
    artifactFactory = artifactFactory,
    initialDevice = SensorDevice(
        id = com.buccancs.domain.model.DeviceId(
            "topdon-tc001"
        ),
        displayName = "Topdon TC001",
        type = SensorDeviceType.TOPDON_TC001,
        capabilities = setOf(
            SensorStreamType.THERMAL_VIDEO,
            SensorStreamType.PREVIEW
        ),
        connectionStatus = ConnectionStatus.Disconnected,
        isSimulated = false,
        attributes = mapOf(
            "usb.vendorId" to "0x3426",
            "usb.productId" to "0x0001"
        )
    )
) {
    constructor(
        scope: CoroutineScope
    ) : this(
        scope,
        SimulatedTestSupport.artifactFactory()
    )

    override fun streamIntervalMs(): Long =
        200L

    override fun simulatedBatteryPercent(
        device: SensorDevice
    ): Int? =
        null

    override fun simulatedRssi(
        device: SensorDevice
    ): Int? =
        null

    override fun sampleStatuses(
        timestamp: Instant,
        frameCounter: Long,
        anchor: com.buccancs.domain.model.RecordingSessionAnchor
    ): List<SensorStreamStatus> {
        val random =
            Random(
                deviceId.value.hashCode() * 17 + frameCounter.toInt()
            )
        val thermal =
            SensorStreamStatus(
                deviceId = deviceId,
                streamType = SensorStreamType.THERMAL_VIDEO,
                sampleRateHz = null,
                frameRateFps = 25.0,
                lastSampleTimestamp = timestamp,
                bufferedDurationSeconds = simulatedBufferedSeconds(
                    streamType = SensorStreamType.THERMAL_VIDEO,
                    baseVideo = 0.6,
                    baseSample = 0.0,
                    randomizer = {
                        random.nextDouble(
                            0.0,
                            0.15
                        )
                    }
                ),
                isStreaming = true,
                isSimulated = true
            )
        val preview =
            SensorStreamStatus(
                deviceId = deviceId,
                streamType = SensorStreamType.PREVIEW,
                sampleRateHz = null,
                frameRateFps = 12.0,
                lastSampleTimestamp = timestamp,
                bufferedDurationSeconds = simulatedBufferedSeconds(
                    streamType = SensorStreamType.PREVIEW,
                    baseVideo = 0.3,
                    baseSample = 0.0,
                    randomizer = {
                        random.nextDouble(
                            0.0,
                            0.1
                        )
                    }
                ),
                isStreaming = true,
                isSimulated = true
            )
        return listOf(
            thermal,
            preview
        )
    }
}
