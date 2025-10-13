package com.buccancs.desktop.data.grpc

import com.buccancs.control.CommandAck
import com.buccancs.control.CommandEnvelope
import com.buccancs.control.CommandReceipt
import com.buccancs.control.CommandServiceGrpcKt
import com.buccancs.control.CommandSubscribeRequest
import com.buccancs.control.DataTransferRequest
import com.buccancs.control.DataTransferServiceGrpcKt
import com.buccancs.control.DataTransferStatus
import com.buccancs.control.DeviceRegistration
import com.buccancs.control.DeviceStatus
import com.buccancs.control.OrchestrationServiceGrpcKt
import com.buccancs.control.PreviewFrame
import com.buccancs.control.PreviewServiceGrpcKt
import com.buccancs.control.RegistrationAck
import com.buccancs.control.SensorSampleBatch
import com.buccancs.control.SensorStreamAck
import com.buccancs.control.SensorStreamServiceGrpcKt
import com.buccancs.control.StatusSubscribeRequest
import com.buccancs.control.TimeSyncPing
import com.buccancs.control.TimeSyncPong
import com.buccancs.control.TimeSyncReport
import com.buccancs.control.TimeSyncServiceGrpcKt
import com.buccancs.control.commandAck
import com.buccancs.control.commandEnvelope
import com.buccancs.control.commands.StartRecordingCommandPayload
import com.buccancs.control.commands.StopRecordingCommandPayload
import com.buccancs.control.deviceStatus
import com.buccancs.control.previewAck
import com.buccancs.control.registrationAck
import com.buccancs.control.sensorStreamAck
import com.buccancs.control.timeSyncPong
import com.buccancs.desktop.data.recording.SensorRecordingManager
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.domain.model.DeviceInfo
import com.buccancs.desktop.domain.model.SessionStatus
import io.grpc.Server
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.time.Instant
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.abs

class GrpcServer(
    private val port: Int,
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val previewRepository: PreviewRepository,
    private val sensorRecordingManager: SensorRecordingManager,
    private val commandRepository: CommandRepository
) {
    private val logger = LoggerFactory.getLogger(GrpcServer::class.java)
    private val started = AtomicBoolean(false)
    private val server: Server = NettyServerBuilder
        .forPort(port)
        .executor(Dispatchers.Default.asExecutor())
        .addService(
            OrchestrationServiceImpl(
                sessionRepository,
                deviceRepository,
                commandRepository,
                sensorRecordingManager
            )
        )
        .addService(CommandServiceImpl(commandRepository, deviceRepository))
        .addService(TimeSyncServiceImpl(deviceRepository))
        .addService(PreviewServiceImpl(sessionRepository, deviceRepository, previewRepository))
        .addService(SensorStreamServiceImpl(sessionRepository, sensorRecordingManager))
        .addService(DataTransferServiceImpl(sessionRepository))
        .build()

    fun start() {
        if (started.compareAndSet(false, true)) {
            server.start()
            logger.info("gRPC server started on port {}", port)
            Runtime.getRuntime().addShutdownHook(Thread {
                stop()
            })
        }
    }

    fun stop() {
        if (started.compareAndSet(true, false)) {
            server.shutdown()
            logger.info("gRPC server stopped")
        }
    }
}

private class CommandServiceImpl(
    private val commandRepository: CommandRepository,
    private val deviceRepository: DeviceRepository
) : CommandServiceGrpcKt.CommandServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(CommandServiceImpl::class.java)
    override fun subscribeCommands(request: CommandSubscribeRequest): Flow<CommandEnvelope> {
        logger.info(
            "Command stream subscription from {} (session={})",
            request.deviceId,
            request.sessionId.ifBlank { "none" }
        )
        return commandRepository
            .observe(request.deviceId, includeBroadcast = request.includeBroadcast)
            .map { dispatch ->
                val targetId = dispatch.targetDeviceId ?: request.deviceId
                commandEnvelope {
                    commandId = dispatch.commandId
                    sessionId = dispatch.sessionId
                    deviceId = targetId
                    issuedEpochMs = dispatch.issuedEpochMs
                    executeEpochMs = dispatch.executeEpochMs
                    commandJson = dispatch.commandJson
                }
            }
    }

    override suspend fun reportCommandReceipt(request: CommandReceipt): CommandAck {
        val payload = commandRepository.findStartStopCommand(request.commandId)
        if (payload != null && request.success) {
            when (payload) {
                is StartRecordingCommandPayload -> deviceRepository.updateRecordingState(
                    request.deviceId,
                    recording = true
                )

                is StopRecordingCommandPayload -> deviceRepository.updateRecordingState(
                    request.deviceId,
                    recording = false
                )

                else -> Unit
            }
        }
        val info = buildString {
            append("Command ")
            append(request.commandId)
            append(" receipt from ")
            append(request.deviceId.ifBlank { "unknown-device" })
            append(": ")
            append(if (request.success) "success" else "failure")
            if (request.message.isNotBlank()) {
                append(" (")
                append(request.message)
                append(')')
            }
        }
        logger.info(info)
        return commandAck {
            accepted = true
            this.info = info
        }
    }
}

private class OrchestrationServiceImpl(
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val commandRepository: CommandRepository,
    private val sensorRecordingManager: SensorRecordingManager
) : OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(OrchestrationServiceImpl::class.java)
    override suspend fun registerDevice(request: DeviceRegistration): RegistrationAck {
        val active = sessionRepository.activeSession.value
        deviceRepository.register(
            id = request.deviceId,
            model = request.model,
            platform = request.platform,
            capabilities = request.capabilitiesList.toSet(),
            softwareVersion = request.softwareVersion,
            sessionId = active?.id
        )
        logger.info("Device {} registered", request.deviceId)
        val ack = registrationAck {
            accepted = true
            sessionId = active?.id ?: ""
        }
        if (active != null && active.status == SessionStatus.ACTIVE) {
            runCatching {
                commandRepository.replayRecordingState(active.id, request.deviceId)
            }.onFailure { ex ->
                logger.warn("Unable to replay recording state to {}", request.deviceId, ex)
            }
        }
        return ack
    }

    override suspend fun startSession(request: com.buccancs.control.StartSessionRequest): CommandAck = try {
        val session = sessionRepository.startSession(
            sessionId = request.session.id,
            operatorId = request.operatorId.takeIf { it.isNotBlank() },
            subjectIds = request.subjectIdsList.filter { it.isNotBlank() }
        )
        deviceRepository.assignSession(session.id)
        val anchorEpochMs = session.startedAt?.toEpochMilli() ?: System.currentTimeMillis()
        val scheduledEpochMs = request.scheduledEpochMs.takeIf { it > 0 }
        commandRepository.enqueueStartRecording(
            sessionId = session.id,
            anchorEpochMs = anchorEpochMs,
            scheduledEpochMs = scheduledEpochMs
        )
        commandAck {
            accepted = true
            info = "Session ${session.id} started"
        }
    } catch (ex: Exception) {
        logger.error("Failed to start session {}", request.session.id, ex)
        commandAck {
            accepted = false
            info = ex.message.orEmpty()
        }
    }

    override suspend fun stopSession(request: com.buccancs.control.StopSessionRequest): CommandAck = try {
        val sessionId = resolveSessionId(request.session.id)
        sessionRepository.stopSession()
        sensorRecordingManager.closeSession(sessionId)
        deviceRepository.assignSession(null)
        val stopEpochMs = request.scheduledEpochMs.takeIf { it > 0 } ?: System.currentTimeMillis()
        commandRepository.enqueueStopRecording(
            sessionId = sessionId,
            stopEpochMs = stopEpochMs
        )
        commandAck {
            accepted = true
            info = "Session $sessionId stopped"
        }
    } catch (ex: Exception) {
        logger.error("Failed to stop session {}", request.session.id, ex)
        commandAck {
            accepted = false
            info = ex.message.orEmpty()
        }
    }

    override suspend fun sendSyncSignal(request: com.buccancs.control.SyncSignalRequest): CommandAck = try {
        val sessionId = resolveSessionId(request.session.id)
        val executeAt = request.scheduledEpochMs.takeIf { it > 0 } ?: System.currentTimeMillis()
        val signalType = request.signalType.ifBlank { "flash" }
        val targets = resolveTargets(request.targets)
        val eventDevices = if (targets.isEmpty()) deviceRepository.connectedDeviceIds().toList() else targets.toList()
        sessionRepository.registerEvent(
            eventId = "sync-$signalType-$executeAt",
            label = "sync:$signalType",
            timestampMs = executeAt,
            deviceIds = eventDevices
        )
        commandRepository.enqueueSyncSignal(
            sessionId = sessionId,
            signalType = signalType,
            executeEpochMs = executeAt,
            targets = targets,
            initiator = "orchestrator"
        )
        commandAck {
            accepted = true
            info = "Sync signal queued"
        }
    } catch (ex: Exception) {
        logger.error("Failed to register sync signal", ex)
        commandAck {
            accepted = false
            info = ex.message.orEmpty()
        }
    }

    override suspend fun sendEventMarker(request: com.buccancs.control.EventMarkerRequest): CommandAck = try {
        val sessionId = resolveSessionId(request.session.id)
        val timestamp = request.timestampEpochMs.takeIf { it > 0 } ?: System.currentTimeMillis()
        val markerId = request.markerId.ifBlank { "event-$timestamp" }
        val description = request.description.ifBlank { "event-marker" }
        val targets = resolveTargets(request.targets)
        val eventDevices = if (targets.isEmpty()) deviceRepository.connectedDeviceIds().toList() else targets.toList()
        sessionRepository.registerEvent(
            eventId = markerId,
            label = description,
            timestampMs = timestamp,
            deviceIds = eventDevices
        )
        commandRepository.enqueueEventMarker(
            sessionId = sessionId,
            markerId = markerId,
            description = description,
            timestampEpochMs = timestamp,
            targets = targets
        )
        commandAck {
            accepted = true
            info = "Event marker accepted"
        }
    } catch (ex: Exception) {
        logger.error("Failed to register event marker {}", request.markerId, ex)
        commandAck {
            accepted = false
            info = ex.message.orEmpty()
        }
    }

    override suspend fun reportStatus(request: DeviceStatus): CommandAck {
        val heartbeat = if (request.lastHeartbeatEpochMs > 0) {
            Instant.ofEpochMilli(request.lastHeartbeatEpochMs)
        } else {
            Instant.now()
        }
        val battery = request.batteryPercent.takeIf { it > 0.0 && it <= 100.0 }
        val previewLatency = request.previewLatencyMs.takeIf { it > 0.0 }
        val clockOffset = request.clockOffsetMs.takeIf { it.isFinite() }
        deviceRepository.updateStatus(
            id = request.deviceId,
            connected = request.online,
            recording = request.recording,
            batteryPercent = battery,
            previewLatencyMs = previewLatency,
            clockOffsetMs = clockOffset,
            heartbeat = heartbeat,
            sessionId = request.sessionId
        )
        return commandAck {
            accepted = true
            info = "Status accepted"
        }
    }

    override fun subscribeStatus(request: StatusSubscribeRequest): Flow<DeviceStatus> = channelFlow {
        val job = launch {
            deviceRepository.observe().collectLatest { devices ->
                devices.map(::toProto).forEach { trySend(it) }
            }
        }
        awaitClose { job.cancel() }
    }

    private fun toProto(info: DeviceInfo): DeviceStatus = deviceStatus {
        deviceId = info.id
        online = info.connected
        recording = info.recording
        if (info.batteryPercent != null) {
            batteryPercent = info.batteryPercent
        }
        if (info.previewLatencyMs != null) {
            previewLatencyMs = info.previewLatencyMs
        }
        if (info.clockOffsetMs != null) {
            clockOffsetMs = info.clockOffsetMs
        }
        if (!info.sessionId.isNullOrBlank()) {
            sessionId = info.sessionId
        }
        lastHeartbeatEpochMs = info.lastHeartbeat?.toEpochMilli() ?: 0L
    }

    private fun resolveTargets(target: com.buccancs.control.DeviceTarget): Set<String> {
        val explicit = target.deviceIdsList
            .mapNotNull { it.takeIf { id -> id.isNotBlank() } }
            .toSet()
        if (target.broadcast || explicit.isEmpty()) {
            val connected = deviceRepository.connectedDeviceIds()
            if (connected.isNotEmpty()) {
                return connected
            }
        }
        return explicit
    }

    private fun resolveSessionId(requestedId: String): String {
        if (requestedId.isNotBlank()) {
            return requestedId
        }
        return sessionRepository.activeSession.value?.id
            ?: throw IllegalStateException("No active session")
    }
}

private class TimeSyncServiceImpl(
    private val deviceRepository: DeviceRepository
) : TimeSyncServiceGrpcKt.TimeSyncServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(TimeSyncServiceImpl::class.java)
    private val history = ConcurrentHashMap<String, TimeSyncSample>()
    override suspend fun ping(request: TimeSyncPing): TimeSyncPong {
        val receiveTs = System.currentTimeMillis()
        val sendTs = System.currentTimeMillis()
        return timeSyncPong {
            serverReceiveEpochMs = receiveTs
            serverSendEpochMs = sendTs
        }
    }

    override suspend fun report(request: TimeSyncReport): CommandAck {
        val offset = request.offsetMs.takeIf { it.isFinite() }
        val heartbeat = if (request.sampleEpochMs > 0) {
            Instant.ofEpochMilli(request.sampleEpochMs)
        } else {
            Instant.now()
        }
        deviceRepository.updateClockOffset(request.deviceId, offset, heartbeat)
        if (offset != null) {
            val previous = history.put(
                request.deviceId,
                TimeSyncSample(
                    timestamp = heartbeat,
                    offsetMs = offset,
                    roundTripMs = request.roundTripMs
                )
            )
            if (previous != null) {
                val elapsedMs = (heartbeat.toEpochMilli() - previous.timestamp.toEpochMilli()).coerceAtLeast(1L)
                val driftPerSecond = ((offset - previous.offsetMs) / elapsedMs) * 1000.0
                if (abs(driftPerSecond) > DRIFT_ALERT_THRESHOLD_MS_PER_SEC) {
                    logger.warn(
                        "Device {} drift {} ms/s (offset={}ms rtt={}ms)",
                        request.deviceId,
                        String.format(Locale.US, "%.3f", driftPerSecond),
                        String.format(Locale.US, "%.1f", offset),
                        String.format(Locale.US, "%.1f", request.roundTripMs)
                    )
                }
            }
            if (request.roundTripMs > ROUND_TRIP_ALERT_THRESHOLD_MS) {
                logger.warn(
                    "Time sync round trip elevated for {}: {} ms",
                    request.deviceId,
                    String.format(Locale.US, "%.1f", request.roundTripMs)
                )
            }
        }
        logger.debug(
            "Sync report from {} offset={}ms rtt={}ms",
            request.deviceId,
            offset,
            request.roundTripMs
        )
        return commandAck {
            accepted = true
            info = "Sync report accepted"
        }
    }

    private data class TimeSyncSample(
        val timestamp: Instant,
        val offsetMs: Double,
        val roundTripMs: Double
    )

    private companion object {
        private const val DRIFT_ALERT_THRESHOLD_MS_PER_SEC = 0.05
        private const val ROUND_TRIP_ALERT_THRESHOLD_MS = 50.0
    }
}

private class PreviewServiceImpl(
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val previewRepository: PreviewRepository
) : PreviewServiceGrpcKt.PreviewServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(PreviewServiceImpl::class.java)
    override suspend fun streamPreview(requests: Flow<PreviewFrame>): com.buccancs.control.PreviewAck {
        requests.collect { frame ->
            val now = Instant.now().toEpochMilli()
            val latency = (now - frame.frameTimestampEpochMs).coerceAtLeast(0L).toDouble()
            previewRepository.update(
                deviceId = frame.deviceId,
                cameraId = frame.cameraId,
                frameTimestampEpochMs = frame.frameTimestampEpochMs,
                encodedFrame = frame.encodedFrame.toByteArray(),
                mimeType = frame.mimeType.ifBlank { "application/octet-stream" },
                width = frame.width.toInt(),
                height = frame.height.toInt(),
                latencyMs = latency
            )
            deviceRepository.updatePreviewLatency(frame.deviceId, latency, Instant.ofEpochMilli(now))
            if (frame.encodedFrame.size() > 0) {
                updateMetrics(frame)
            }
        }
        logger.info("Preview stream closed")
        return previewAck {
            received = true
            info = "Preview stream closed"
        }
    }

    private suspend fun updateMetrics(frame: PreviewFrame) {
        val kind = classifyFrame(frame)
        sessionRepository.registerMetrics { metrics ->
            when (kind) {
                FrameKind.RGB ->
                    metrics.copy(videoFrames = metrics.videoFrames + 1)

                FrameKind.THERMAL ->
                    metrics.copy(thermalFrames = metrics.thermalFrames + 1)
            }
        }
    }

    private fun classifyFrame(frame: PreviewFrame): FrameKind {
        val cameraId = frame.cameraId.lowercase(Locale.ROOT)
        val mime = frame.mimeType.lowercase(Locale.ROOT)
        return when {
            cameraId.contains("thermal") ||
                    cameraId.contains("ir") ||
                    mime.contains("thermal") ||
                    mime.contains("infrared") ->
                FrameKind.THERMAL

            mime.startsWith("image/") ||
                    mime.startsWith("video/") ||
                    cameraId.contains("rgb") ||
                    cameraId.contains("rear") ||
                    cameraId.contains("front") ->
                FrameKind.RGB

            else -> FrameKind.RGB
        }
    }

    private enum class FrameKind {
        RGB,
        THERMAL
    }
}

private class SensorStreamServiceImpl(
    private val sessionRepository: SessionRepository,
    private val recordingManager: SensorRecordingManager
) : SensorStreamServiceGrpcKt.SensorStreamServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(SensorStreamServiceImpl::class.java)
    override suspend fun stream(requests: Flow<SensorSampleBatch>): SensorStreamAck {
        var activeKey: StreamKey? = null
        var totalSamples = 0L
        return try {
            requests.collect { batch ->
                val sessionId = batch.session.id
                if (sessionId.isBlank()) {
                    throw Status.INVALID_ARGUMENT
                        .withDescription("Missing session identifier")
                        .asRuntimeException()
                }
                if (!sessionRepository.isSessionActive(sessionId)) {
                    throw Status.FAILED_PRECONDITION
                        .withDescription("Session $sessionId is not active")
                        .asRuntimeException()
                }
                val deviceId = batch.deviceId
                if (deviceId.isBlank()) {
                    throw Status.INVALID_ARGUMENT
                        .withDescription("Missing device identifier")
                        .asRuntimeException()
                }
                val streamId = batch.streamId.ifBlank { "sensor" }
                if (activeKey == null) {
                    logger.info(
                        "Sensor stream started (session={}, device={}, stream={})",
                        sessionId,
                        deviceId,
                        streamId
                    )
                }
                activeKey = StreamKey(sessionId, deviceId, streamId)
                val appended = recordingManager.append(batch)
                totalSamples += appended
                if (batch.endOfStream) {
                    recordingManager.finalizeStream(sessionId, deviceId, streamId)
                    logger.info(
                        "Sensor stream completed (session={}, device={}, stream={}, samples={})",
                        sessionId,
                        deviceId,
                        streamId,
                        totalSamples
                    )
                    activeKey = null
                }
            }
            activeKey?.let {
                recordingManager.finalizeStream(it.sessionId, it.deviceId, it.streamId)
                logger.info(
                    "Sensor stream completed without explicit end flag (session={}, device={}, stream={}, samples={})",
                    it.sessionId,
                    it.deviceId,
                    it.streamId,
                    totalSamples
                )
            }
            sensorStreamAck {
                success = true
                totalSamples = totalSamples.coerceAtLeast(0)
            }
        } catch (statusEx: StatusException) {
            activeKey?.let { recordingManager.abortStream(it.sessionId, it.deviceId, it.streamId) }
            throw statusEx
        } catch (throwable: Throwable) {
            activeKey?.let { recordingManager.abortStream(it.sessionId, it.deviceId, it.streamId) }
            logger.error("Sensor stream failed: ${throwable.message}", throwable)
            throw Status.INTERNAL
                .withDescription(throwable.message ?: "Sensor stream failure")
                .withCause(throwable)
                .asRuntimeException()
        }
    }

    private data class StreamKey(
        val sessionId: String,
        val deviceId: String,
        val streamId: String
    )
}

private class DataTransferServiceImpl(
    private val sessionRepository: SessionRepository
) : DataTransferServiceGrpcKt.DataTransferServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(DataTransferServiceImpl::class.java)
    private val attempts = ConcurrentHashMap<String, Int>()
    override fun upload(requests: Flow<DataTransferRequest>): Flow<DataTransferStatus> = channelFlow {
        val accumulators = mutableMapOf<String, TransferAccumulator>()
        val job = launch {
            requests.collect { request ->
                val streamType = request.streamType.takeIf { it.isNotBlank() }
                val key = transferKey(request.session.id, request.deviceId, request.fileName, streamType)
                val accumulator = accumulators.getOrPut(key) {
                    val attempt = attempts.merge(key, 1) { previous, increment -> previous + increment } ?: 1
                    try {
                        sessionRepository.markTransferStarted(
                            sessionId = request.session.id,
                            deviceId = request.deviceId,
                            fileName = request.fileName,
                            streamType = streamType,
                            expectedBytes = request.sizeBytes,
                            attempt = attempt
                        )
                    } catch (ex: Exception) {
                        logger.warn(
                            "Unable to note transfer start for {} from {}",
                            request.fileName,
                            request.deviceId,
                            ex
                        )
                    }
                    TransferAccumulator(
                        sessionId = request.session.id,
                        deviceId = request.deviceId,
                        fileName = request.fileName,
                        expectedBytes = request.sizeBytes,
                        checksum = request.sha256.toByteArray(),
                        mimeType = request.mimeType.ifBlank { "application/octet-stream" },
                        streamType = streamType,
                        attempt = attempt
                    )
                }
                if (!request.chunk.isEmpty()) {
                    val chunkBytes = request.chunk.toByteArray()
                    accumulator.stream.write(chunkBytes)
                    accumulator.bytes += chunkBytes.size
                    try {
                        sessionRepository.markTransferProgress(
                            sessionId = accumulator.sessionId,
                            deviceId = accumulator.deviceId,
                            fileName = accumulator.fileName,
                            streamType = accumulator.streamType,
                            receivedBytes = accumulator.bytes.toLong()
                        )
                    } catch (ex: Exception) {
                        logger.warn(
                            "Unable to update transfer progress for {} from {}",
                            accumulator.fileName,
                            accumulator.deviceId,
                            ex
                        )
                    }
                }
                if (request.endOfStream) {
                    val payload = accumulator.stream.toByteArray()
                    val actualBytes = accumulator.bytes.takeIf { it > 0 }?.toLong() ?: payload.size.toLong()
                    try {
                        sessionRepository.markTransferProgress(
                            sessionId = accumulator.sessionId,
                            deviceId = accumulator.deviceId,
                            fileName = accumulator.fileName,
                            streamType = accumulator.streamType,
                            receivedBytes = actualBytes
                        )
                    } catch (ex: Exception) {
                        logger.warn(
                            "Unable to mark final transfer size for {} from {}",
                            accumulator.fileName,
                            accumulator.deviceId,
                            ex
                        )
                    }
                    if (accumulator.expectedBytes > 0 && actualBytes != accumulator.expectedBytes) {
                        val message =
                            "Size mismatch: expected ${accumulator.expectedBytes} bytes, received $actualBytes bytes"
                        logger.warn(
                            "Discarding file {} from {} due to size mismatch",
                            accumulator.fileName,
                            accumulator.deviceId
                        )
                        accumulators.remove(key)
                        try {
                            sessionRepository.markTransferFailed(
                                sessionId = accumulator.sessionId,
                                deviceId = accumulator.deviceId,
                                fileName = accumulator.fileName,
                                streamType = accumulator.streamType,
                                attempt = accumulator.attempt,
                                receivedBytes = actualBytes,
                                error = message
                            )
                        } catch (ex: Exception) {
                            logger.warn(
                                "Unable to record failed transfer for {} from {}",
                                accumulator.fileName,
                                accumulator.deviceId,
                                ex
                            )
                        }
                        trySend(buildTransferStatus(accumulator, success = false, errorMessage = message))
                        return@collect
                    }
                    if (accumulator.checksum.isNotEmpty()) {
                        val calculated = MessageDigest.getInstance("SHA-256").digest(payload)
                        if (!calculated.contentEquals(accumulator.checksum)) {
                            val message = "Checksum mismatch"
                            logger.warn(
                                "Checksum mismatch for file {} from device {}",
                                accumulator.fileName,
                                accumulator.deviceId
                            )
                            accumulators.remove(key)
                            try {
                                sessionRepository.markTransferFailed(
                                    sessionId = accumulator.sessionId,
                                    deviceId = accumulator.deviceId,
                                    fileName = accumulator.fileName,
                                    streamType = accumulator.streamType,
                                    attempt = accumulator.attempt,
                                    receivedBytes = actualBytes,
                                    error = message
                                )
                            } catch (ex: Exception) {
                                logger.warn(
                                    "Unable to record checksum failure for {} from {}",
                                    accumulator.fileName,
                                    accumulator.deviceId,
                                    ex
                                )
                            }
                            trySend(buildTransferStatus(accumulator, success = false, errorMessage = message))
                            return@collect
                        }
                    }
                    try {
                        sessionRepository.attachFile(
                            sessionId = accumulator.sessionId,
                            deviceId = accumulator.deviceId,
                            fileName = accumulator.fileName,
                            content = payload,
                            mimeType = accumulator.mimeType,
                            checksum = accumulator.checksum,
                            streamType = accumulator.streamType
                        )
                        try {
                            sessionRepository.markTransferCompleted(
                                sessionId = accumulator.sessionId,
                                deviceId = accumulator.deviceId,
                                fileName = accumulator.fileName,
                                streamType = accumulator.streamType,
                                expectedBytes = accumulator.expectedBytes,
                                receivedBytes = actualBytes
                            )
                        } catch (ex: Exception) {
                            logger.warn(
                                "Unable to mark transfer completion for {} from {}",
                                accumulator.fileName,
                                accumulator.deviceId,
                                ex
                            )
                        }
                        attempts.remove(key)
                        accumulators.remove(key)
                        logger.info(
                            "Received file {} from device {} ({} bytes)",
                            accumulator.fileName,
                            accumulator.deviceId,
                            actualBytes
                        )
                        trySend(buildTransferStatus(accumulator, success = true))
                    } catch (ex: Exception) {
                        logger.error(
                            "Failed to persist file {} for session {}",
                            accumulator.fileName,
                            accumulator.sessionId,
                            ex
                        )
                        accumulators.remove(key)
                        try {
                            sessionRepository.markTransferFailed(
                                sessionId = accumulator.sessionId,
                                deviceId = accumulator.deviceId,
                                fileName = accumulator.fileName,
                                streamType = accumulator.streamType,
                                attempt = accumulator.attempt,
                                receivedBytes = actualBytes,
                                error = ex.message.orEmpty()
                            )
                        } catch (markEx: Exception) {
                            logger.warn(
                                "Unable to record transfer failure for {} from {}",
                                accumulator.fileName,
                                accumulator.deviceId,
                                markEx
                            )
                        }
                        trySend(
                            buildTransferStatus(
                                accumulator = accumulator,
                                success = false,
                                errorMessage = ex.message.orEmpty()
                            )
                        )
                    }
                }
            }
        }
        awaitClose {
            job.cancel()
            accumulators.clear()
        }
    }

    private fun transferKey(
        sessionId: String,
        deviceId: String,
        fileName: String,
        streamType: String?
    ): String = listOf(sessionId, deviceId, fileName, streamType.orEmpty()).joinToString("|")

    private data class TransferAccumulator(
        val sessionId: String,
        val deviceId: String,
        val fileName: String,
        val expectedBytes: Long,
        val checksum: ByteArray,
        val mimeType: String,
        val streamType: String?,
        val attempt: Int,
        val stream: ByteArrayOutputStream = ByteArrayOutputStream(),
        var bytes: Long = 0
    )

    private fun buildTransferStatus(
        accumulator: TransferAccumulator,
        success: Boolean,
        errorMessage: String? = null
    ): DataTransferStatus {
        val builder = DataTransferStatus.newBuilder()
            .setFileName(accumulator.fileName)
            .setDeviceId(accumulator.deviceId)
            .setSuccess(success)
        builder.streamType = accumulator.streamType ?: ""
        errorMessage?.let { builder.errorMessage = it }
        return builder.build()
    }
}
