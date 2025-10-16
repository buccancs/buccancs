package com.buccancs.data.sensor.connector.topdon

import android.content.Context
import android.os.SystemClock
import androidx.test.core.app.ApplicationProvider
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.sensor.topdon.InMemoryTopdonSettingsRepository
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.hardware.topdon.GainMode
import com.buccancs.hardware.topdon.Palette
import com.buccancs.hardware.topdon.TopdonHardwareSettings
import com.buccancs.hardware.topdon.TopdonNotice
import com.buccancs.hardware.topdon.TopdonPreviewFrame
import com.buccancs.hardware.topdon.TopdonStatus
import com.buccancs.hardware.topdon.TopdonStreamEvent
import com.buccancs.hardware.topdon.TopdonTemperatureMetrics
import com.buccancs.hardware.topdon.TopdonThermalClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

@RunWith(RobolectricTestRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class TopdonThermalConnectorTest {

    private lateinit var context: Context
    private lateinit var storage: RecordingStorage
    private lateinit var artifactFactory: SimulatedArtifactFactory

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        storage = RecordingStorage(context)
        val artifactsRoot = File(context.cacheDir, "topdon-tests").apply { mkdirs() }
        artifactFactory = SimulatedArtifactFactory.fromRoot(artifactsRoot)
    }

    @Test
    fun `stream event produces thermal artifact`() = runTest {
        val hardware = FakeTopdonThermalClient()
        val connector = createConnector(this, hardware)

        assertEquals(DeviceCommandResult.Accepted, connector.connect())
        advanceUntilIdle()

        val anchor = RecordingSessionAnchor(
            sessionId = "session-${System.currentTimeMillis()}",
            anchorEpochMs = System.currentTimeMillis(),
            anchorElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
        )

        assertEquals(DeviceCommandResult.Accepted, connector.startStreaming(anchor))
        advanceUntilIdle()

        val payload = ByteArray(FRAME_SIZE) { (it % 255).toByte() }
        hardware.emitStreamFrame(payload, endOfStream = false)
        advanceUntilIdle()

        hardware.emitStreamFrame(ByteArray(0), endOfStream = true)
        advanceUntilIdle()

        assertEquals(DeviceCommandResult.Accepted, connector.stopStreaming())
        advanceUntilIdle()

        val artifacts = connector.collectArtifacts(anchor.sessionId)
        assertEquals(1, artifacts.size)
        val artifact = artifacts.first()
        assertTrue(artifact.sizeBytes > 0)
        assertEquals("1", artifact.metadata["frameCount"])
        assertTrue(connector.streamStatuses.value.isNotEmpty())
    }

    private fun createConnector(
        scope: TestScope,
        hardwareClient: TopdonThermalClient
    ): TopdonThermalConnector {
        val device = SensorDevice(
            id = DeviceId("topdon-test"),
            displayName = "Topdon Test",
            type = SensorDeviceType.TOPDON_TC001,
            capabilities = setOf(SensorStreamType.THERMAL_VIDEO, SensorStreamType.PREVIEW),
            connectionStatus = ConnectionStatus.Disconnected,
            isSimulated = false,
            attributes = emptyMap()
        )
        return TopdonThermalConnector(
            appScope = scope.backgroundScope,
            hardwareClient = hardwareClient,
            recordingStorage = storage,
            thermalNormalizer = ThermalNormalizer(),
            artifactFactory = artifactFactory,
            settingsRepository = InMemoryTopdonSettingsRepository(),
            initialDevice = device
        )
    }

    private class FakeTopdonThermalClient : TopdonThermalClient {
        private val statusState = MutableStateFlow<TopdonStatus>(TopdonStatus.Idle)
        private val previewFlow = MutableSharedFlow<TopdonPreviewFrame>(extraBufferCapacity = 8)
        private val streamFlow = MutableSharedFlow<TopdonStreamEvent>(extraBufferCapacity = 16)
        private val noticeFlow = MutableSharedFlow<TopdonNotice>(extraBufferCapacity = 4)

        private var connected = false
        private var streaming = false
        private var emittedFrames = 0L

        override val status: StateFlow<TopdonStatus> = statusState.asStateFlow()
        override val previewFrames = previewFlow.asSharedFlow()
        override val streamEvents = streamFlow.asSharedFlow()
        override val notices = noticeFlow.asSharedFlow()

        override suspend fun refreshInventory() {
            statusState.value = TopdonStatus.Attached(
                vendorId = TOPDON_VENDOR_ID,
                productId = TOPDON_PRODUCT_ID,
                serialNumber = "TEST123",
                hasPermission = true
            )
        }

        override suspend fun connect() {
            connected = true
            statusState.value = TopdonStatus.Connected(
                vendorId = TOPDON_VENDOR_ID,
                productId = TOPDON_PRODUCT_ID,
                serialNumber = "TEST123",
                sinceEpochMs = System.currentTimeMillis()
            )
        }

        override suspend fun disconnect() {
            connected = false
            streaming = false
            statusState.value = TopdonStatus.Idle
        }

        override suspend fun applySettings(settings: TopdonHardwareSettings) = Unit

        override suspend fun startPreview(): Result<Unit> = Result.success(Unit)

        override suspend fun stopPreview(): Result<Unit> = Result.success(Unit)

        override suspend fun startStreaming(request: com.buccancs.hardware.topdon.TopdonStreamRequest) {
            if (!connected) throw IllegalStateException("Not connected")
            streaming = true
            emittedFrames = 0
            statusState.value = TopdonStatus.Streaming(
                vendorId = TOPDON_VENDOR_ID,
                productId = TOPDON_PRODUCT_ID,
                serialNumber = "TEST123",
                sinceEpochMs = System.currentTimeMillis()
            )
        }

        override suspend fun stopStreaming() {
            streaming = false
            statusState.value = TopdonStatus.Connected(
                vendorId = TOPDON_VENDOR_ID,
                productId = TOPDON_PRODUCT_ID,
                serialNumber = "TEST123",
                sinceEpochMs = System.currentTimeMillis()
            )
            streamEvents.emit(
                TopdonStreamEvent(
                    timestampEpochMs = System.currentTimeMillis(),
                    frameCount = 0,
                    bytesCaptured = 0,
                    metrics = TopdonTemperatureMetrics.Unknown,
                    payload = null,
                    endOfStream = true
                )
            )
        }

        suspend fun emitStreamFrame(payload: ByteArray, endOfStream: Boolean) {
            if (!streaming && !endOfStream) {
                throw IllegalStateException("Streaming not started")
            }
            if (!endOfStream && payload.isNotEmpty()) {
                emittedFrames += 1
            }
            streamEvents.emit(
                TopdonStreamEvent(
                    timestampEpochMs = System.currentTimeMillis(),
                    frameCount = emittedFrames,
                    bytesCaptured = payload.size.toLong(),
                    metrics = TopdonTemperatureMetrics(
                        minCelsius = 18.0,
                        maxCelsius = 32.0,
                        avgCelsius = 25.0
                    ),
                    payload = if (payload.isEmpty()) null else payload,
                    endOfStream = endOfStream
                )
            )
        }
    }

    companion object {
        private const val TOPDON_VENDOR_ID = 0x3426
        private const val TOPDON_PRODUCT_ID = 0x0001
        private const val FRAME_WIDTH = 256
        private const val FRAME_HEIGHT = 192
        private const val FRAME_SIZE = FRAME_WIDTH * FRAME_HEIGHT * 2
    }
}
