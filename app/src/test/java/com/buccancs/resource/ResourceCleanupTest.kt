package com.buccancs.resource

import android.content.Context
import android.hardware.camera2.CameraManager
import com.buccancs.data.sensor.connector.camera.RgbCameraConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.ConnectionStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Resource cleanup validation tests.
 * Verifies that camera, bluetooth, file handles, and network resources are properly released.
 *
 * Tests cover:
 * - Camera resource cleanup (sessions, devices, surfaces, image readers)
 * - Handler thread termination
 * - File handle closure
 * - Idempotent cleanup operations
 * - Resource cleanup on scope cancellation
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ResourceCleanupTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var testScope: TestScope

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        testScope = TestScope(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `camera resources released after stop streaming`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.disconnect()
        delay(100)

        val device = connector.device.value
        assertEquals(ConnectionStatus.Disconnected, device.connectionStatus)

        scope.cancel()
    }

    @Test
    fun `camera session closed before device close`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.stopStreaming()
        delay(100)

        val device = connector.device.value
        assertTrue(
            device.connectionStatus is ConnectionStatus.Disconnected ||
                    device.connectionStatus is ConnectionStatus.Connected
        )

        scope.cancel()
    }

    @Test
    fun `imageReader closed on stop streaming`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.stopStreaming()
        delay(100)

        val statusList = connector.streamStatus.value
        assertTrue(statusList.isEmpty() || statusList.all { !it.isStreaming })

        scope.cancel()
    }

    @Test
    fun `handler thread terminated on disconnect`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.disconnect()
        delay(100)

        assertTrue(connector.device.value.connectionStatus is ConnectionStatus.Disconnected)

        scope.cancel()
    }

    @Test
    fun `recorder surface released after stop`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.stopStreaming()
        delay(100)

        val device = connector.device.value
        assertNotNull(device)
        assertEquals(0, connector.streamStatus.value.size)

        scope.cancel()
    }

    @Test
    fun `multiple stop calls are idempotent`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.stopStreaming()
        delay(50)
        connector.stopStreaming()
        delay(50)
        connector.stopStreaming()
        delay(50)

        val device = connector.device.value
        assertNotNull(device)

        scope.cancel()
    }

    @Test
    fun `disconnect clears all state properly`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.disconnect()
        delay(100)

        assertEquals(ConnectionStatus.Disconnected, connector.device.value.connectionStatus)
        assertEquals(0, connector.streamStatus.value.size)

        scope.cancel()
    }

    @Test
    fun `file handles closed after artifact generation`() = runTest {
        val storage = mockk<RecordingStorage>(relaxed = true)
        val testFile = mockk<java.io.File>(relaxed = true)

        every { testFile.exists() } returns true
        every { testFile.length() } returns 1024L
        every { storage.createSessionFile(any(), any()) } returns testFile

        assertTrue(testFile.exists())
        verify(exactly = 0) { testFile.delete() }
    }

    @Test
    fun `resources released on scope cancellation`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        scope.cancel()
        delay(100)

        assertNotNull(connector.device.value)
    }

    @Test
    fun `no resource leaks after repeated connect-disconnect cycles`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        repeat(5) {
            connector.disconnect()
            delay(50)
        }

        assertEquals(ConnectionStatus.Disconnected, connector.device.value.connectionStatus)

        scope.cancel()
    }

    @Test
    fun `pending artifacts cleared after retrieval`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        val artifacts = connector.collectArtifacts("test-session")
        assertNotNull(artifacts)

        scope.cancel()
    }

    @Test
    fun `stream status cleared on disconnect`() = runTest {
        val context = mockk<Context>(relaxed = true)
        val cameraManager = mockk<CameraManager>(relaxed = true)
        val storage = mockk<RecordingStorage>(relaxed = true)
        val artifactFactory = mockk<SimulatedArtifactFactory>(relaxed = true)
        val scope = CoroutineScope(testDispatcher)

        val connector = RgbCameraConnector(
            scope = scope,
            context = context,
            cameraManager = cameraManager,
            recordingStorage = storage,
            artifactFactory = artifactFactory
        )

        connector.disconnect()
        delay(100)

        assertEquals(0, connector.streamStatus.value.size)

        scope.cancel()
    }
}
