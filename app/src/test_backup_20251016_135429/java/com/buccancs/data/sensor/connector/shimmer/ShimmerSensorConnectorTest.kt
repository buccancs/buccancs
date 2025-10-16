package com.buccancs.data.sensor.connector.shimmer

import android.content.Context
import android.os.SystemClock
import androidx.test.core.app.ApplicationProvider
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.data.sensor.SensorStreamClient
import com.buccancs.data.sensor.SensorStreamEmitter
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.sensor.shimmer.InMemoryShimmerSettingsRepository
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.hardware.shimmer.ShimmerHardwareClient
import com.buccancs.hardware.shimmer.ShimmerHardwareDevice
import com.buccancs.hardware.shimmer.ShimmerHardwareSettings
import com.buccancs.hardware.shimmer.ShimmerNotice
import com.buccancs.hardware.shimmer.ShimmerSample
import com.buccancs.hardware.shimmer.ShimmerStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File
import kotlin.time.Duration

@RunWith(RobolectricTestRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class ShimmerSensorConnectorTest {

    private lateinit var context: Context
    private lateinit var storage: RecordingStorage
    private lateinit var artifactFactory: SimulatedArtifactFactory

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        storage = RecordingStorage(context)
        val artifactsRoot = File(context.cacheDir, "shimmer-tests").apply { mkdirs() }
        artifactFactory = SimulatedArtifactFactory.fromRoot(artifactsRoot)
    }

    @Test
    fun `hardware sample produces artifact and updates statuses`() = runTest {
        val hardware = FakeShimmerHardwareClient()
        val streamClient = RecordingStreamRecorder()
        val connector = createConnector(this, hardware, streamClient)

        hardware.emitDevices(
            listOf(
                ShimmerHardwareDevice(
                    macAddress = TEST_MAC,
                    name = "Shimmer Test",
                    rssi = -55,
                    firmwareVersion = "1.0.0",
                    hardwareVersion = 3,
                    bonded = true
                )
            )
        )

        connector.refreshInventory()
        advanceUntilIdle()

        val connectResult = connector.connect()
        assertEquals(DeviceCommandResult.Accepted, connectResult)
        advanceUntilIdle()

        val anchor = RecordingSessionAnchor(
            sessionId = "session-${System.currentTimeMillis()}",
            anchorEpochMs = System.currentTimeMillis(),
            anchorElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
        )

        val startResult = connector.startStreaming(anchor)
        assertEquals(DeviceCommandResult.Accepted, startResult)

        hardware.emitSample(
            ShimmerSample(
                timestampEpochMs = System.currentTimeMillis(),
                conductanceSiemens = 4.25,
                resistanceOhms = 2200.0
            )
        )
        advanceUntilIdle()

        connector.stopStreaming()
        advanceUntilIdle()

        val artifacts = connector.collectArtifacts(anchor.sessionId)
        assertEquals(1, artifacts.size)
        assertTrue(artifacts.first().sizeBytes > 0)
        assertTrue(streamClient.emissions.isNotEmpty())
        assertTrue(connector.streamStatuses.value.isNotEmpty())
    }

    private fun createConnector(
        scope: TestScope,
        hardwareClient: ShimmerHardwareClient,
        streamClient: SensorStreamClient
    ): ShimmerSensorConnector {
        val device = SensorDevice(
            id = DeviceId("shimmer-test"),
            displayName = "Shimmer Test",
            type = SensorDeviceType.SHIMMER_GSR,
            capabilities = setOf(SensorStreamType.GSR),
            connectionStatus = ConnectionStatus.Disconnected,
            isSimulated = false,
            attributes = emptyMap()
        )
        return ShimmerSensorConnector(
            appScope = scope.backgroundScope,
            hardwareClient = hardwareClient,
            artifactFactory = artifactFactory,
            streamClient = streamClient,
            recordingStorage = storage,
            shimmerSettingsRepository = InMemoryShimmerSettingsRepository(),
            initialDevice = device
        )
    }

    private class FakeShimmerHardwareClient : ShimmerHardwareClient {
        private val devicesState = MutableStateFlow<List<ShimmerHardwareDevice>>(emptyList())
        private val statusState = MutableStateFlow<ShimmerStatus>(ShimmerStatus.Idle)
        private val samplesFlow = MutableSharedFlow<ShimmerSample>(extraBufferCapacity = 16)
        private val noticesFlow = MutableSharedFlow<ShimmerNotice>(extraBufferCapacity = 4)
        private var currentMac: String? = null

        override val devices: StateFlow<List<ShimmerHardwareDevice>> = devicesState.asStateFlow()
        override val status: StateFlow<ShimmerStatus> = statusState.asStateFlow()
        override val samples = samplesFlow.asSharedFlow()
        override val notices = noticesFlow.asSharedFlow()

        suspend fun emitDevices(devices: List<ShimmerHardwareDevice>) {
            devicesState.emit(devices)
        }

        suspend fun emitSample(sample: ShimmerSample) {
            samplesFlow.emit(sample)
        }

        override suspend fun refreshBondedDevices() = Unit

        override suspend fun scan(duration: Duration) = Unit

        override suspend fun connect(macAddress: String) {
            currentMac = macAddress
            statusState.value = ShimmerStatus.Connecting(macAddress)
            statusState.value = ShimmerStatus.Connected(
                macAddress = macAddress,
                sinceEpochMs = System.currentTimeMillis(),
                firmwareVersion = "1.0.0",
                hardwareVersion = 3
            )
        }

        override suspend fun disconnect() {
            statusState.value = ShimmerStatus.Idle
        }

        override suspend fun applySettings(settings: ShimmerHardwareSettings) = Unit

        override suspend fun startStreaming() {
            val mac = currentMac ?: TEST_MAC
            statusState.value = ShimmerStatus.Streaming(
                macAddress = mac,
                sinceEpochMs = System.currentTimeMillis(),
                samplesPerSecond = 128.0
            )
        }

        override suspend fun stopStreaming() {
            val mac = currentMac ?: TEST_MAC
            statusState.value = ShimmerStatus.Connected(
                macAddress = mac,
                sinceEpochMs = System.currentTimeMillis(),
                firmwareVersion = "1.0.0",
                hardwareVersion = 3
            )
        }
    }

    private class RecordingStreamRecorder : SensorStreamClient {
        val emissions = mutableListOf<Pair<Long, Map<String, Double>>>()

        override suspend fun openStream(
            sessionId: String,
            deviceId: DeviceId,
            streamId: String,
            sampleRateHz: Double
        ): SensorStreamEmitter = object : SensorStreamEmitter {
            override suspend fun emit(timestampEpochMs: Long, values: Map<String, Double>) {
                emissions += timestampEpochMs to values
            }

            override suspend fun close() = Unit
        }
    }

    private companion object {
        private const val TEST_MAC = "AA:BB:CC:DD:EE:FF"
    }
}
