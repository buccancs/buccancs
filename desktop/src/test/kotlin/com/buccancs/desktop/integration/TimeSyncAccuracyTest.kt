package com.buccancs.desktop.integration

import com.buccancs.control.OrchestrationServiceGrpcKt
import com.buccancs.control.TimeSyncServiceGrpcKt
import com.buccancs.control.deviceRegistration
import com.buccancs.control.timeSyncPing
import com.buccancs.control.timeSyncReport
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.deleteRecursively
import kotlin.math.abs
import kotlin.time.Duration.Companion.seconds

class TimeSyncAccuracyTest {
    private lateinit var tempDir: Path
    private lateinit var grpcServer: GrpcServer
    private lateinit var sessionRepository: SessionRepository
    private lateinit var deviceRepository: DeviceRepository

    private val serverPort = 50052
    private val testDeviceId = "accuracy-test-device"

    @Before
    fun setup() {
        tempDir = Files.createTempDirectory("buccancs-timesync-test")
        val encryptionManager = EncryptionManager()
        val retentionManager = DataRetentionManager(
            RetentionPolicy(
                maxSessionCount = 100,
                maxSessionBytes = 1L * 1024 * 1024 * 1024,
                maxDeviceBytes = 500L * 1024 * 1024,
                maxGlobalBytes = 10L * 1024 * 1024 * 1024,
                deleteOldestSessionWhenFull = true
            )
        )
        sessionRepository = SessionRepository(tempDir, encryptionManager, retentionManager)
        deviceRepository = DeviceRepository()
        val commandRepository = CommandRepository()
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
    fun testTimeSyncAccuracyRequirement() = runTest(timeout = 30.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val orchestrationClient = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val timeSyncClient = TimeSyncServiceGrpcKt.TimeSyncServiceCoroutineStub(channel)

        orchestrationClient.registerDevice(deviceRegistration {
            deviceId = testDeviceId
            model = "Accuracy Test Device"
            platform = "Android"
        })
        delay(500)

        val samples = mutableListOf<TimeSyncMeasurement>()
        val sampleCount = 100

        repeat(sampleCount) { iteration ->
            val t1 = System.nanoTime()
            val clientSendMs = System.currentTimeMillis()

            val pong = timeSyncClient.ping(timeSyncPing {
                clientSendEpochMs = clientSendMs
                deviceId = testDeviceId
            })

            val t4 = System.nanoTime()
            val clientReceiveMs = System.currentTimeMillis()

            val roundTripMs = (t4 - t1) / 1_000_000.0

            val serverMidpointMs = (pong.serverReceiveEpochMs + pong.serverSendEpochMs) / 2.0
            val clientMidpointMs = (clientSendMs + clientReceiveMs) / 2.0
            val offsetMs = serverMidpointMs - clientMidpointMs

            samples.add(
                TimeSyncMeasurement(
                    iteration = iteration,
                    roundTripMs = roundTripMs,
                    offsetMs = offsetMs,
                    serverProcessingMs = (pong.serverSendEpochMs - pong.serverReceiveEpochMs).toDouble()
                )
            )

            timeSyncClient.report(timeSyncReport {
                deviceId = testDeviceId
                this.offsetMs = offsetMs
                this.roundTripMs = roundTripMs
                sampleEpochMs = clientReceiveMs
            })

            delay(50)
        }

        val validSamples = samples.filter { it.roundTripMs < 100.0 }
        assertTrue("Should have valid samples", validSamples.isNotEmpty())

        val avgRoundTrip = validSamples.map { it.roundTripMs }.average()
        val maxRoundTrip = validSamples.maxOf { it.roundTripMs }
        val minRoundTrip = validSamples.minOf { it.roundTripMs }

        val avgOffset = validSamples.map { abs(it.offsetMs) }.average()
        val maxOffset = validSamples.maxOf { abs(it.offsetMs) }

        val avgProcessing = validSamples.map { it.serverProcessingMs }.average()

        println("Time Sync Accuracy Results:")
        println("  Sample count: ${validSamples.size} / $sampleCount")
        println(
            "  Round trip: avg=${String.format("%.3f", avgRoundTrip)}ms, " +
                    "min=${String.format("%.3f", minRoundTrip)}ms, " +
                    "max=${String.format("%.3f", maxRoundTrip)}ms"
        )
        println(
            "  Clock offset: avg=${String.format("%.3f", avgOffset)}ms, " +
                    "max=${String.format("%.3f", maxOffset)}ms"
        )
        println("  Server processing: avg=${String.format("%.3f", avgProcessing)}ms")

        assertTrue("Average round trip should be under 50ms on localhost", avgRoundTrip < 50.0)
        assertTrue("Max round trip should be under 100ms", maxRoundTrip < 100.0)

        assertTrue("Average offset should be under 5ms (NFR2 target)", avgOffset < 5.0)
        assertTrue("Max offset should be under 10ms (NFR2 maximum)", maxOffset < 10.0)

        assertTrue("Server processing should be minimal", avgProcessing < 2.0)

        channel.shutdown()
    }

    @Test
    fun testTimeSyncStability() = runTest(timeout = 60.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val timeSyncClient = TimeSyncServiceGrpcKt.TimeSyncServiceCoroutineStub(channel)

        val measurements = mutableListOf<Double>()
        val duration = 30
        val interval = 1000L

        repeat(duration) {
            val clientSendMs = System.currentTimeMillis()

            val pong = timeSyncClient.ping(timeSyncPing {
                clientSendEpochMs = clientSendMs
                deviceId = testDeviceId
            })

            val clientReceiveMs = System.currentTimeMillis()

            val serverMidpointMs = (pong.serverReceiveEpochMs + pong.serverSendEpochMs) / 2.0
            val clientMidpointMs = (clientSendMs + clientReceiveMs) / 2.0
            val offsetMs = serverMidpointMs - clientMidpointMs

            measurements.add(offsetMs)

            delay(interval)
        }

        val avgOffset = measurements.map { abs(it) }.average()
        val stdDev = calculateStdDev(measurements)
        val drift = calculateDrift(measurements)

        println("Time Sync Stability Results ($duration seconds):")
        println("  Average offset: ${String.format("%.3f", avgOffset)}ms")
        println("  Std deviation: ${String.format("%.3f", stdDev)}ms")
        println("  Drift: ${String.format("%.6f", drift)}ms/s")

        assertTrue("Average offset should remain under 5ms", avgOffset < 5.0)
        assertTrue("Standard deviation should be low", stdDev < 2.0)
        assertTrue("Drift should be minimal", abs(drift) < 0.1)

        channel.shutdown()
    }

    @Test
    fun testTimeSyncUnderLoad() = runTest(timeout = 30.seconds) {
        val channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
            .usePlaintext()
            .build()

        val timeSyncClient = TimeSyncServiceGrpcKt.TimeSyncServiceCoroutineStub(channel)

        val concurrentRequests = 10
        val samples = mutableListOf<TimeSyncMeasurement>()

        repeat(concurrentRequests) { deviceIndex ->
            val deviceId = "load-test-device-$deviceIndex"

            repeat(10) { iteration ->
                val clientSendMs = System.currentTimeMillis()

                val pong = timeSyncClient.ping(timeSyncPing {
                    clientSendEpochMs = clientSendMs
                    this.deviceId = deviceId
                })

                val clientReceiveMs = System.currentTimeMillis()
                val roundTripMs = (clientReceiveMs - clientSendMs).toDouble()

                val serverMidpointMs = (pong.serverReceiveEpochMs + pong.serverSendEpochMs) / 2.0
                val clientMidpointMs = (clientSendMs + clientReceiveMs) / 2.0
                val offsetMs = serverMidpointMs - clientMidpointMs

                samples.add(
                    TimeSyncMeasurement(
                        iteration = iteration,
                        roundTripMs = roundTripMs,
                        offsetMs = offsetMs,
                        serverProcessingMs = (pong.serverSendEpochMs - pong.serverReceiveEpochMs).toDouble()
                    )
                )
            }
        }

        val avgRoundTrip = samples.map { it.roundTripMs }.average()
        val avgOffset = samples.map { abs(it.offsetMs) }.average()
        val maxRoundTrip = samples.maxOf { it.roundTripMs }
        val maxOffset = samples.maxOf { abs(it.offsetMs) }

        println("Time Sync Under Load Results:")
        println("  Total requests: ${samples.size}")
        println("  Avg round trip: ${String.format("%.3f", avgRoundTrip)}ms")
        println("  Avg offset: ${String.format("%.3f", avgOffset)}ms")
        println("  Max round trip: ${String.format("%.3f", maxRoundTrip)}ms")
        println("  Max offset: ${String.format("%.3f", maxOffset)}ms")

        assertTrue("Average round trip should remain reasonable under load", avgRoundTrip < 100.0)
        assertTrue("Max round trip should not spike excessively", maxRoundTrip < 200.0)
        assertTrue("Average offset should meet NFR2", avgOffset < 5.0)
        assertTrue("Max offset should not exceed NFR2 limit", maxOffset < 10.0)

        channel.shutdown()
    }

    private fun calculateStdDev(values: List<Double>): Double {
        val mean = values.average()
        val variance = values.map { (it - mean) * (it - mean) }.average()
        return kotlin.math.sqrt(variance)
    }

    private fun calculateDrift(values: List<Double>): Double {
        if (values.size < 2) return 0.0

        val n = values.size
        val x = (0 until n).map { it.toDouble() }
        val y = values

        val meanX = x.average()
        val meanY = y.average()

        val numerator = (0 until n).sumOf { i -> (x[i] - meanX) * (y[i] - meanY) }
        val denominator = (0 until n).sumOf { i -> (x[i] - meanX) * (x[i] - meanX) }

        return if (denominator != 0.0) numerator / denominator else 0.0
    }

    private data class TimeSyncMeasurement(
        val iteration: Int,
        val roundTripMs: Double,
        val offsetMs: Double,
        val serverProcessingMs: Double
    )
}
