package com.buccancs.desktop.integration

import com.buccancs.control.*
import com.buccancs.desktop.data.encryption.EncryptionManager
import com.buccancs.desktop.data.grpc.GrpcServer
import com.buccancs.desktop.data.monitor.DeviceConnectionMonitor
import com.buccancs.desktop.data.recording.SensorRecordingManager
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.data.retention.DataRetentionManager
import com.buccancs.desktop.domain.policy.RetentionPolicy
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.deleteRecursively
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class MultiDeviceStressTest {
    private lateinit var tempDir: Path
    private lateinit var grpcServer: GrpcServer
    private lateinit var sessionRepository: SessionRepository
    private lateinit var deviceRepository: DeviceRepository
    private lateinit var commandRepository: CommandRepository
    
    private val serverPort = 50053
    private val testSessionId = "stress-test-session"

    @Before
    fun setup() {
        tempDir = Files.createTempDirectory("buccancs-stress-test")
        val encryptionManager = EncryptionManager()
        val retentionManager = DataRetentionManager(
            RetentionPolicy(
                maxSessionCount = 100,
                maxSessionBytes = 50L * 1024 * 1024 * 1024,
                maxDeviceBytes = 10L * 1024 * 1024 * 1024,
                maxGlobalBytes = 100L * 1024 * 1024 * 1024,
                deleteOldestSessionWhenFull = true
            )
        )
        sessionRepository = SessionRepository(tempDir, encryptionManager, retentionManager)
        deviceRepository = DeviceRepository()
        commandRepository = CommandRepository()
        val previewRepository = PreviewRepository()
        val sensorRecordingManager = SensorRecordingManager(sessionRepository)
        
        val monitor = DeviceConnectionMonitor(deviceRepository)
        monitor.start()
        
        grpcServer = GrpcServer(
            port = serverPort,
            sessionRepository = sessionRepository,
            deviceRepository = deviceRepository,
            previewRepository = previewRepository,
            sensorRecordingManager = sensorRecordingManager,
            commandRepository = commandRepository
        )
        grpcServer.start()
    }

    @After
    fun teardown() {
        grpcServer.stop()
        if (::tempDir.isInitialized && Files.exists(tempDir)) {
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun testEightDeviceRegistration() = runTest(timeout = 30.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        val client = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)

        val deviceCount = 8
        val devices = (1..deviceCount).map { "device-${String.format("%03d", it)}" }
        
        val registrations = devices.map { deviceId ->
            async {
                client.registerDevice(deviceRegistration {
                    this.deviceId = deviceId
                    model = "Stress Test Device"
                    platform = "Android"
                    softwareVersion = "1.0.0"
                    capabilities.addAll(listOf("camera", "sensors", "thermal"))
                })
            }
        }

        val acks = registrations.awaitAll()
        assertEquals("All devices should register", deviceCount, acks.size)
        assertTrue("All registrations should succeed", acks.all { it.accepted })
        
        delay(1000)
        
        val registered = deviceRepository.observe().first()
        assertEquals("All devices should be in repository", deviceCount, registered.size)
        
        devices.forEach { deviceId ->
            assertTrue("Device $deviceId should be registered", 
                registered.any { it.id == deviceId })
        }

        channel.shutdown()
    }

    @Test
    fun testConcurrentSensorStreaming() = runTest(timeout = 2.minutes) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        
        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val sensorClient = SensorStreamServiceGrpcKt.SensorStreamServiceCoroutineStub(channel)

        val deviceCount = 6
        val devices = (1..deviceCount).map { "sensor-device-${String.format("%03d", it)}" }
        
        devices.forEach { deviceId ->
            sessionClient.registerDevice(deviceRegistration {
                this.deviceId = deviceId
                model = "Sensor Device"
                platform = "Android"
            })
        }
        delay(1000)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val samplesPerDevice = 1000
        val streamJobs = devices.map { deviceId ->
            async {
                val samples = (0 until samplesPerDevice).map { i ->
                    sensorSample {
                        timestampEpochMs = System.currentTimeMillis() + i * 10
                        values.add(sensorSampleValue {
                            key = "gsr"
                            value = 0.5 + i * 0.001
                        })
                        values.add(sensorSampleValue {
                            key = "ppg"
                            value = 100.0 + i * 0.05
                        })
                    }
                }

                val batches = kotlinx.coroutines.flow.flow {
                    samples.chunked(100).forEach { chunk ->
                        emit(sensorSampleBatch {
                            session = sessionIdentifier { id = testSessionId }
                            this.deviceId = deviceId
                            streamId = "gsr"
                            sampleRateHz = 100.0
                            this.samples.addAll(chunk)
                            endOfStream = (chunk == samples.chunked(100).last())
                        })
                        delay(10)
                    }
                }

                sensorClient.stream(batches)
            }
        }

        val acks = streamJobs.awaitAll()
        assertEquals("All streams should complete", deviceCount, acks.size)
        assertTrue("All streams should succeed", acks.all { it.success })
        
        val totalSamples = acks.sumOf { it.totalSamples }
        assertEquals("Total sample count should match", 
            (deviceCount * samplesPerDevice).toLong(), totalSamples)
        
        delay(1000)
        
        devices.forEach { deviceId ->
            val sensorFile = tempDir.resolve(testSessionId)
                .resolve("sensors")
                .resolve(deviceId)
                .resolve("gsr.csv")
            assertTrue("Sensor file for $deviceId should exist", Files.exists(sensorFile))
            
            val lines = Files.readAllLines(sensorFile)
            assertTrue("Should have data for $deviceId", lines.size > samplesPerDevice)
        }

        channel.shutdown()
    }

    @Test
    fun testConcurrentFileUploads() = runTest(timeout = 2.minutes) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        
        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val transferClient = DataTransferServiceGrpcKt.DataTransferServiceCoroutineStub(channel)

        val deviceCount = 8
        val devices = (1..deviceCount).map { "upload-device-${String.format("%03d", it)}" }
        
        devices.forEach { deviceId ->
            sessionClient.registerDevice(deviceRegistration {
                this.deviceId = deviceId
                model = "Upload Device"
                platform = "Android"
            })
        }
        delay(1000)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val filesPerDevice = 5
        val fileSizeBytes = 1024 * 100
        
        val uploadJobs = devices.flatMap { deviceId ->
            (1..filesPerDevice).map { fileIndex ->
                async {
                    val fileName = "data-$fileIndex.bin"
                    val content = ByteArray(fileSizeBytes) { it.toByte() }
                    val sha256 = java.security.MessageDigest.getInstance("SHA-256").digest(content)
                    
                    val requests = kotlinx.coroutines.flow.flow {
                        val chunkSize = 8192
                        var offset = 0
                        while (offset < content.size) {
                            val end = minOf(offset + chunkSize, content.size)
                            val chunk = content.sliceArray(offset until end)
                            
                            emit(dataTransferRequest {
                                session = sessionIdentifier { id = testSessionId }
                                this.deviceId = deviceId
                                this.fileName = fileName
                                sizeBytes = content.size.toLong()
                                sha256 = com.google.protobuf.ByteString.copyFrom(sha256)
                                mimeType = "application/octet-stream"
                                streamType = "sensor"
                                this.chunk = com.google.protobuf.ByteString.copyFrom(chunk)
                                endOfStream = (end >= content.size)
                            })
                            
                            offset = end
                            delay(5)
                        }
                    }

                    val responses = mutableListOf<DataTransferStatus>()
                    transferClient.upload(requests).collect { status ->
                        responses.add(status)
                    }
                    responses.first()
                }
            }
        }

        val statuses = uploadJobs.awaitAll()
        val totalUploads = deviceCount * filesPerDevice
        assertEquals("All uploads should complete", totalUploads, statuses.size)
        
        val successful = statuses.count { it.success }
        assertTrue("Most uploads should succeed", successful >= totalUploads * 0.95)
        
        if (successful < totalUploads) {
            val failures = statuses.filter { !it.success }
            println("Upload failures (${failures.size}):")
            failures.forEach { println("  ${it.deviceId}/${it.fileName}: ${it.errorMessage}") }
        }

        channel.shutdown()
    }

    @Test
    fun testCommandBroadcastScale() = runTest(timeout = 30.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        
        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val commandClient = CommandServiceGrpcKt.CommandServiceCoroutineStub(channel)

        val deviceCount = 8
        val devices = (1..deviceCount).map { "cmd-device-${String.format("%03d", it)}" }
        
        devices.forEach { deviceId ->
            sessionClient.registerDevice(deviceRegistration {
                this.deviceId = deviceId
                model = "Command Device"
                platform = "Android"
            })
        }
        delay(1000)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val commandFlows = devices.map { deviceId ->
            async {
                val commands = mutableListOf<CommandEnvelope>()
                val flow = commandClient.subscribeCommands(commandSubscribeRequest {
                    this.deviceId = deviceId
                    sessionId = testSessionId
                    includeBroadcast = true
                })
                
                val job = kotlinx.coroutines.launch {
                    flow.collect { envelope ->
                        commands.add(envelope)
                        if (commands.size >= 10) {
                            return@collect
                        }
                    }
                }
                
                delay(500)
                commands to job
            }
        }

        val subscribers = commandFlows.awaitAll()
        delay(500)

        repeat(10) { iteration ->
            sessionClient.sendSyncSignal(syncSignalRequest {
                session = sessionIdentifier { id = testSessionId }
                signalType = "sync-$iteration"
                targets = deviceTarget { broadcast = true }
            })
            delay(100)
        }

        delay(2000)
        
        subscribers.forEach { (_, job) -> job.cancel() }
        
        val commandCounts = subscribers.map { (commands, _) -> commands.size }
        val avgCommands = commandCounts.average()
        val minCommands = commandCounts.minOrNull() ?: 0
        
        println("Command broadcast results:")
        println("  Devices: $deviceCount")
        println("  Avg commands received: $avgCommands")
        println("  Min commands received: $minCommands")
        
        assertTrue("Most devices should receive commands", avgCommands >= 8.0)
        assertTrue("All devices should receive some commands", minCommands >= 5)

        channel.shutdown()
    }

    @Test
    fun testLongDurationSession() = runTest(timeout = 3.minutes) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()
        
        val sessionClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val sensorClient = SensorStreamServiceGrpcKt.SensorStreamServiceCoroutineStub(channel)

        val deviceCount = 4
        val devices = (1..deviceCount).map { "duration-device-${String.format("%03d", it)}" }
        
        devices.forEach { deviceId ->
            sessionClient.registerDevice(deviceRegistration {
                this.deviceId = deviceId
                model = "Duration Test Device"
                platform = "Android"
            })
        }
        delay(1000)

        sessionClient.startSession(startSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })
        delay(500)

        val durationSeconds = 120
        val sampleRateHz = 100.0
        val batchIntervalMs = 1000L
        val samplesPerBatch = (sampleRateHz * (batchIntervalMs / 1000.0)).toInt()
        
        val streamingJobs = devices.map { deviceId ->
            async {
                var totalSent = 0L
                val startTime = System.currentTimeMillis()
                
                while (System.currentTimeMillis() - startTime < durationSeconds * 1000) {
                    val samples = (0 until samplesPerBatch).map { i ->
                        sensorSample {
                            timestampEpochMs = System.currentTimeMillis() + i * 10
                            values.add(sensorSampleValue {
                                key = "gsr"
                                value = 0.5 + Math.random() * 0.1
                            })
                        }
                    }

                    val batch = kotlinx.coroutines.flow.flow {
                        emit(sensorSampleBatch {
                            session = sessionIdentifier { id = testSessionId }
                            this.deviceId = deviceId
                            streamId = "gsr"
                            sampleRateHz = sampleRateHz
                            this.samples.addAll(samples)
                            endOfStream = false
                        })
                    }

                    runCatching {
                        sensorClient.stream(batch)
                        totalSent += samples.size
                    }
                    
                    delay(batchIntervalMs)
                }
                
                val finalBatch = kotlinx.coroutines.flow.flow {
                    emit(sensorSampleBatch {
                        session = sessionIdentifier { id = testSessionId }
                        this.deviceId = deviceId
                        streamId = "gsr"
                        sampleRateHz = sampleRateHz
                        endOfStream = true
                    })
                }
                sensorClient.stream(finalBatch)
                
                totalSent
            }
        }

        val sampleCounts = streamingJobs.awaitAll()
        val totalSamples = sampleCounts.sum()
        val expectedSamples = deviceCount * durationSeconds * sampleRateHz.toLong()
        val achievedRate = totalSamples.toDouble() / (deviceCount * durationSeconds)
        
        println("Long duration session results:")
        println("  Duration: ${durationSeconds}s")
        println("  Devices: $deviceCount")
        println("  Total samples: $totalSamples")
        println("  Expected samples: ~$expectedSamples")
        println("  Achieved rate: ${String.format("%.1f", achievedRate)} Hz/device")
        
        assertTrue("Should collect substantial data", totalSamples > expectedSamples * 0.9)
        assertTrue("Sample rate should be maintained", achievedRate > sampleRateHz * 0.9)

        sessionClient.stopSession(stopSessionRequest {
            session = sessionIdentifier { id = testSessionId }
        })

        channel.shutdown()
    }
}
