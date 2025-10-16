package com.buccancs.desktop.integration

import com.buccancs.control.*
import com.buccancs.desktop.data.aggregation.SessionAggregationService
import com.buccancs.desktop.data.encryption.EncryptionKeyProvider
import com.buccancs.desktop.data.encryption.EncryptionManager
import com.buccancs.desktop.data.grpc.GrpcServer
import com.buccancs.desktop.data.monitor.DeviceConnectionMonitor
import com.buccancs.desktop.data.recording.SensorRecordingManager
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.data.retention.DataRetentionManager
import com.buccancs.desktop.domain.model.SessionStatus
import com.buccancs.desktop.domain.policy.RetentionPolicy
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.deleteRecursively
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalPathApi::class, ExperimentalCoroutinesApi::class)
class EndToEndIntegrationTest {
    private lateinit var tempDir: Path
    private lateinit var grpcServer: GrpcServer
    private lateinit var sessionRepository: SessionRepository
    private lateinit var deviceRepository: DeviceRepository
    private lateinit var commandRepository: CommandRepository
    private lateinit var previewRepository: PreviewRepository
    private lateinit var sensorRecordingManager: SensorRecordingManager
    private lateinit var encryptionManager: EncryptionManager
    private lateinit var retentionManager: DataRetentionManager
    private lateinit var aggregationService: SessionAggregationService
    private lateinit var monitor: DeviceConnectionMonitor
    private lateinit var monitorScope: TestScope

    private val serverPort = 50051
    private val testDeviceId = "test-device-001"
    private val testSessionId = "test-session-001"

    @Before
    fun setup() {
        tempDir = Files.createTempDirectory("buccancs-integration-test")
        val keyProvider = EncryptionKeyProvider(tempDir.resolve("encryption.key"))
        encryptionManager = EncryptionManager(keyProvider)
        retentionManager = DataRetentionManager(
            RetentionPolicy(
                perSessionCapBytes = 10L * 1024 * 1024 * 1024,
                perDeviceCapBytes = 5L * 1024 * 1024 * 1024,
                globalCapBytes = 50L * 1024 * 1024 * 1024
            )
        )
        sessionRepository = SessionRepository(tempDir, encryptionManager, retentionManager)
        deviceRepository = DeviceRepository()
        commandRepository = CommandRepository()
        previewRepository = PreviewRepository()
        sensorRecordingManager = SensorRecordingManager(sessionRepository)
        aggregationService = SessionAggregationService(sessionRepository)
        monitorScope = TestScope(UnconfinedTestDispatcher())
        monitor = DeviceConnectionMonitor(deviceRepository, sessionRepository, monitorScope)
        monitor.start()

        grpcServer = GrpcServer(
            port = serverPort,
            sessionRepository = sessionRepository,
            deviceRepository = deviceRepository,
            previewRepository = previewRepository,
            sensorRecordingManager = sensorRecordingManager,
            commandRepository = commandRepository,
            aggregationService = aggregationService
        )
        grpcServer.start()
    }

    @After
    fun teardown() {
        grpcServer.stop()
        if (::monitor.isInitialized) {
            monitor.stop()
        }
        if (::monitorScope.isInitialized) {
            monitorScope.cancel()
        }
        if (::tempDir.isInitialized && Files.exists(tempDir)) {
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testDeviceRegistration() = runTest(timeout = 10.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        val client = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)

        val registration = deviceRegistration {
            deviceId = testDeviceId
            model = "Test Device"
            platform = "Android"
            softwareVersion = "1.0.0"
            capabilities.addAll(listOf("camera", "sensors"))
        }

        val ack = client.registerDevice(registration)
        assertTrue(ack.accepted)

        withTimeout(3000) {
            delay(500)
            val devices = deviceRepository.observe().first()
            val registered = devices.find { it.id == testDeviceId }
            assertNotNull("Device should be registered", registered)
            assertEquals("Test Device", registered?.model)
        }

        channel.shutdown()
    }

    @Test
    fun testSessionLifecycle() = runTest(timeout = 15.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        val client = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)

        val registration = deviceRegistration {
            deviceId = testDeviceId
            model = "Test Device"
            platform = "Android"
            softwareVersion = "1.0.0"
        }
        client.registerDevice(registration)
        delay(500)

        val startRequest = startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
            operatorId = "operator-001"
            subjectIds.add("subject-001")
        }

        val startAck = client.startSession(startRequest)
        assertTrue("Session start should be accepted", startAck.accepted)

        delay(500)

        val activeSession = sessionRepository.activeSession.first()
        assertNotNull("Active session should exist", activeSession)
        assertEquals(testSessionId, activeSession?.id)
        assertEquals(SessionStatus.ACTIVE, activeSession?.status)

        val stopRequest = stopSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        }
        val stopAck = client.stopSession(stopRequest)
        assertTrue("Session stop should be accepted", stopAck.accepted)

        delay(500)

        val stoppedSession = sessionRepository.activeSession.first()
        assertNull("Session should be stopped", stoppedSession)

        channel.shutdown()
    }

    @Test
    fun testFileUploadFlow() = runTest(timeout = 20.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val transferClient = DataTransferServiceGrpcKt.DataTransferServiceCoroutineStub(channel)

        sessionClient.registerDevice(deviceRegistration {
            deviceId = testDeviceId
            model = "Test Device"
            platform = "Android"
        })
        delay(500)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val testContent = "Test file content for upload".toByteArray()
        val fileChecksum = java.security.MessageDigest.getInstance("SHA-256")
            .digest(testContent)

        val requests = kotlinx.coroutines.flow.flow {
            emit(dataTransferRequest {
                session = sessionIdentifier { id = testSessionId }
                deviceId = testDeviceId
                fileName = "test-data.txt"
                sizeBytes = testContent.size.toLong()
                sha256 = com.google.protobuf.ByteString.copyFrom(fileChecksum)
                mimeType = "text/plain"
                streamType = "sensor"
                chunk = com.google.protobuf.ByteString.copyFrom(testContent)
                endOfStream = true
            })
        }

        val responses = mutableListOf<DataTransferStatus>()
        transferClient.upload(requests).collect { status ->
            responses.add(status)
        }

        assertEquals(1, responses.size)
        assertTrue("Upload should succeed", responses[0].success)
        assertEquals("test-data.txt", responses[0].fileName)

        delay(500)

        val sessionDir = tempDir.resolve(testSessionId)
        val deviceDir = sessionDir.resolve("devices").resolve(testDeviceId)
        val uploadedFile = deviceDir.resolve("test-data.txt")
        assertTrue("Uploaded file should exist", Files.exists(uploadedFile))

        channel.shutdown()
    }

    @Test
    fun testSensorStreamIngestion() = runTest(timeout = 20.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val sensorClient = SensorStreamServiceGrpcKt.SensorStreamServiceCoroutineStub(channel)

        sessionClient.registerDevice(deviceRegistration {
            deviceId = testDeviceId
            model = "Test Device"
            platform = "Android"
        })
        delay(500)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val sampleCount = 100
        val sampleSeries = (0 until sampleCount).map { i ->
            sensorSample {
                timestampEpochMs = System.currentTimeMillis() + i * 10
                values.add(sensorSampleValue {
                    key = "gsr"
                    value = 0.5 + i * 0.01
                })
            }
        }

        val batches = kotlinx.coroutines.flow.flow {
            emit(sensorSampleBatch {
                session = sessionIdentifier { id = testSessionId }
                deviceId = testDeviceId
                streamId = "gsr"
                sampleRateHz = 100.0
                samples += sampleSeries
                endOfStream = true
            })
        }

        val ack = sensorClient.stream(batches)
        assertTrue("Stream should succeed", ack.success)
        assertEquals(sampleCount.toLong(), ack.totalSamples)

        delay(1000)

        val sessionDir = tempDir.resolve(testSessionId)
        val sensorFile = sessionDir.resolve("sensors")
            .resolve(testDeviceId)
            .resolve("gsr.csv")
        assertTrue("Sensor file should exist", Files.exists(sensorFile))

        val lines = Files.readAllLines(sensorFile)
        assertTrue("Should have header + data", lines.size > sampleCount)

        channel.shutdown()
    }

    @Test
    fun testTimeSyncRoundTrip() = runTest(timeout = 10.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        val client = TimeSyncServiceGrpcKt.TimeSyncServiceCoroutineStub(channel)

        val clientSendTime = System.currentTimeMillis()

        val ping = timeSyncPing {
            clientSendEpochMs = clientSendTime
            deviceId = testDeviceId
        }

        val pong = client.ping(ping)
        val clientReceiveTime = System.currentTimeMillis()

        assertTrue("Server receive time should be set", pong.serverReceiveEpochMs > 0)
        assertTrue("Server send time should be set", pong.serverSendEpochMs > 0)
        assertTrue(
            "Server times should be ordered",
            pong.serverSendEpochMs >= pong.serverReceiveEpochMs
        )

        val roundTripMs = (clientReceiveTime - clientSendTime).toDouble()
        assertTrue("Round trip should be reasonable", roundTripMs < 1000.0)

        val offsetMs = ((pong.serverReceiveEpochMs + pong.serverSendEpochMs) / 2.0) -
                ((clientSendTime + clientReceiveTime) / 2.0)

        assertTrue(
            "Clock offset should be small on localhost",
            kotlin.math.abs(offsetMs) < 100.0
        )

        val report = timeSyncReport {
            deviceId = testDeviceId
            this.offsetMs = offsetMs
            this.roundTripMs = roundTripMs
            sampleEpochMs = clientReceiveTime
        }

        val reportAck = client.report(report)
        assertTrue("Report should be accepted", reportAck.accepted)

        channel.shutdown()
    }

    @Test
    fun testCommandBroadcast() = runTest(timeout = 15.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val commandClient = CommandServiceGrpcKt.CommandServiceCoroutineStub(channel)

        sessionClient.registerDevice(deviceRegistration {
            deviceId = testDeviceId
            model = "Test Device"
            platform = "Android"
        })
        delay(500)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val commandFlow = commandClient.subscribeCommands(commandSubscribeRequest {
            deviceId = testDeviceId
            sessionId = testSessionId
            includeBroadcast = true
        })

        delay(1000)

        sessionClient.sendSyncSignal(syncSignalRequest {
            session = sessionIdentifier { id = testSessionId }
            signalType = "flash"
            targets = deviceTarget { broadcast = true }
        })

        withTimeout(5000) {
            var receivedCommand = false
            commandFlow.collect { envelope ->
                if (envelope.commandJson.contains("flash")) {
                    receivedCommand = true
                    return@collect
                }
            }
            assertTrue("Should receive sync command", receivedCommand)
        }

        channel.shutdown()
    }

    @Test
    fun testPreviewStreamFlow() = runTest(timeout = 15.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val previewClient = PreviewServiceGrpcKt.PreviewServiceCoroutineStub(channel)

        sessionClient.registerDevice(deviceRegistration {
            deviceId = testDeviceId
            model = "Test Device"
            platform = "Android"
        })
        delay(500)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val frameCount = 10
        val frames = kotlinx.coroutines.flow.flow {
            repeat(frameCount) { i ->
                emit(previewFrame {
                    deviceId = testDeviceId
                    cameraId = "camera-0"
                    frameTimestampEpochMs = System.currentTimeMillis()
                    encodedFrame = com.google.protobuf.ByteString.copyFrom(
                        ByteArray(100) { (i + it).toByte() }
                    )
                    mimeType = "image/jpeg"
                    width = 640
                    height = 480
                })
                delay(100)
            }
        }

        val ack = previewClient.streamPreview(frames)
        assertTrue("Preview stream should be received", ack.received)

        delay(500)

        val preview = previewRepository.observe().first()
            .values
            .find { it.deviceId == testDeviceId }
        assertNotNull("Preview should be stored", preview)
        assertEquals("camera-0", preview?.cameraId)

        channel.shutdown()
    }

    @Test
    fun testMultiDeviceScenario() = runTest(timeout = 30.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)

        val device1 = "device-001"
        val device2 = "device-002"
        val device3 = "device-003"

        sessionClient.registerDevice(deviceRegistration {
            deviceId = device1
            model = "Device 1"
            platform = "Android"
        })

        sessionClient.registerDevice(deviceRegistration {
            deviceId = device2
            model = "Device 2"
            platform = "Android"
        })

        sessionClient.registerDevice(deviceRegistration {
            deviceId = device3
            model = "Device 3"
            platform = "Android"
        })

        delay(1000)

        val devices = deviceRepository.observe().first()
        assertEquals("Should have 3 devices", 3, devices.size)
        assertTrue(devices.any { it.id == device1 })
        assertTrue(devices.any { it.id == device2 })
        assertTrue(devices.any { it.id == device3 })

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val markerAck = sessionClient.sendEventMarker(eventMarkerRequest {
            session = sessionIdentifier { id = testSessionId }
            markerId = "marker-001"
            description = "Test event"
            targets = deviceTarget { broadcast = true }
        })
        assertTrue(markerAck.accepted)

        delay(500)

        sessionClient.stopSession(stopSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })

        channel.shutdown()
    }
}
