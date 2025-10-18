package com.buccancs.desktop.data.grpc.services

import com.buccancs.control.CommandAck
import com.buccancs.control.DeviceRegistration
import com.buccancs.control.DeviceStatus
import com.buccancs.control.EventMarkerRequest
import com.buccancs.control.OrchestrationServiceGrpcKt
import com.buccancs.control.ProtocolVersion
import com.buccancs.control.RegistrationAck
import com.buccancs.control.StartSessionRequest
import com.buccancs.control.StatusSubscribeRequest
import com.buccancs.control.StopSessionRequest
import com.buccancs.control.SyncSignalRequest
import com.buccancs.control.commandAck
import com.buccancs.control.deviceStatus
import com.buccancs.control.registrationAck
import com.buccancs.desktop.data.recording.SensorRecordingManager
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.domain.model.DeviceInfo
import com.buccancs.desktop.domain.model.SessionStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.time.Instant

class OrchestrationServiceImpl(
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val commandRepository: CommandRepository,
    private val sensorRecordingManager: SensorRecordingManager
) : OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(OrchestrationServiceImpl::class.java)

    override suspend fun registerDevice(request: DeviceRegistration): RegistrationAck {
        val clientVersion = request.protocolVersion.takeIf { it > 0 } ?: ProtocolVersion.CURRENT
        if (!ProtocolVersion.isCompatible(clientVersion)) {
            logger.warn(
                "Device {} rejected: incompatible protocol version {} (server: {})",
                request.deviceId,
                ProtocolVersion.versionString(clientVersion),
                ProtocolVersion.versionString(ProtocolVersion.CURRENT)
            )
            return registrationAck {
                accepted = false
                reason =
                    "Incompatible protocol version: ${ProtocolVersion.versionString(clientVersion)} " +
                            "(server: ${ProtocolVersion.versionString(ProtocolVersion.CURRENT)})"
            }
        }
        val active = sessionRepository.activeSession.value
        deviceRepository.register(
            id = request.deviceId,
            model = request.model,
            platform = request.platform,
            capabilities = request.capabilitiesList.toSet(),
            softwareVersion = request.softwareVersion,
            sessionId = active?.id
        )
        logger.info(
            "Device {} registered with protocol version {}",
            request.deviceId,
            ProtocolVersion.versionString(clientVersion)
        )
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

    override suspend fun startSession(request: StartSessionRequest): CommandAck = try {
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

    override suspend fun stopSession(request: StopSessionRequest): CommandAck = try {
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

    override suspend fun sendSyncSignal(request: SyncSignalRequest): CommandAck = try {
        val sessionId = resolveSessionId(request.session.id)
        val executeAt = request.scheduledEpochMs.takeIf { it > 0 } ?: System.currentTimeMillis()
        val signalType = request.signalType.ifBlank { "flash" }
        val targets = resolveTargets(request.targets)
        val eventDevices = if (targets.isEmpty()) deviceRepository.connectedDeviceIds()
            .toList() else targets.toList()
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

    override suspend fun sendEventMarker(request: EventMarkerRequest): CommandAck = try {
        val sessionId = resolveSessionId(request.session.id)
        val timestamp = request.timestampEpochMs.takeIf { it > 0 } ?: System.currentTimeMillis()
        val markerId = request.markerId.ifBlank { "event-$timestamp" }
        val description = request.description.ifBlank { "event-marker" }
        val targets = resolveTargets(request.targets)
        val eventDevices = if (targets.isEmpty()) deviceRepository.connectedDeviceIds()
            .toList() else targets.toList()
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

    override fun subscribeStatus(request: StatusSubscribeRequest): Flow<DeviceStatus> =
        channelFlow {
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
