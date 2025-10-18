package com.buccancs.desktop.data.monitor

import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.domain.model.DeviceConnectionEvent
import com.buccancs.desktop.domain.model.SessionStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.Instant
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

class DeviceConnectionMonitor(
    private val deviceRepository: DeviceRepository,
    private val sessionRepository: SessionRepository,
    private val commandRepository: CommandRepository,
    private val scope: CoroutineScope,
    private val heartbeatTimeoutMs: Long = DEFAULT_HEARTBEAT_TIMEOUT_MS,
    private val pollIntervalMs: Long = DEFAULT_POLL_INTERVAL_MS,
    private val timeProvider: () -> Instant = { Instant.now() }
) {
    private val logger = LoggerFactory.getLogger(DeviceConnectionMonitor::class.java)
    private val started = AtomicBoolean(false)
    private var monitorJob: Job? = null
    private var eventJob: Job? = null
    private val pendingReplay = ConcurrentHashMap<String, String>()
    fun start() {
        if (!started.compareAndSet(false, true)) {
            return
        }
        monitorJob = scope.launch { monitorLoop() }
        eventJob = scope.launch { collectEvents() }
    }

    fun stop() {
        monitorJob?.cancel()
        eventJob?.cancel()
        monitorJob = null
        eventJob = null
        pendingReplay.clear()
        started.set(false)
    }

    private suspend fun monitorLoop() {
        while (currentCoroutineContext().isActive) {
            val now = timeProvider()
            deviceRepository.snapshot().forEach { info ->
                if (!info.connected) {
                    return@forEach
                }
                val last = info.lastHeartbeat ?: return@forEach
                val elapsedMs = Duration.between(last, now).toMillis()
                if (elapsedMs > heartbeatTimeoutMs) {
                    logger.warn(
                        "Marking device {} offline after {} ms without heartbeat (limit {} ms)",
                        info.id,
                        elapsedMs,
                        heartbeatTimeoutMs
                    )
                    deviceRepository.markOffline(
                        id = info.id,
                        reason = DeviceConnectionEvent.DisconnectReason.HEARTBEAT_TIMEOUT
                    )
                }
            }
            delay(pollIntervalMs)
        }
    }

    private suspend fun collectEvents() {
        deviceRepository.events().collect { event ->
            when (event) {
                is DeviceConnectionEvent.Connected -> handleConnected(event)
                is DeviceConnectionEvent.Disconnected -> handleDisconnected(event)
            }
        }
    }

    private suspend fun handleConnected(event: DeviceConnectionEvent.Connected) {
        val session = sessionRepository.activeSession.value
        val timestamp = event.timestamp.toEpochMilli()
        if (session != null) {
            val eventId = "device-connected-${event.deviceId}-$timestamp"
            runCatching {
                sessionRepository.registerEvent(
                    eventId = eventId,
                    label = "device-connected:${event.deviceId}",
                    timestampMs = timestamp,
                    deviceIds = listOf(event.deviceId)
                )
            }.onFailure { ex ->
                logger.debug(
                    "Unable to register connection event for {}: {}",
                    event.deviceId,
                    ex.message
                )
            }
        }
        if (session != null) {
            logger.info(
                "Device {} reconnected to session {}",
                event.deviceId,
                session.id
            )
            if (session.status == SessionStatus.ACTIVE) {
                val pendingSessionId = pendingReplay.remove(event.deviceId)
                if (pendingSessionId == session.id) {
                    runCatching {
                        commandRepository.replayRecordingState(session.id, event.deviceId)
                        val replayTimestamp = timeProvider().toEpochMilli()
                        sessionRepository.registerEvent(
                            eventId = "device-replay-${event.deviceId}-$replayTimestamp",
                            label = "device-replay:${event.deviceId}",
                            timestampMs = replayTimestamp,
                            deviceIds = listOf(event.deviceId)
                        )
                    }.onFailure { ex ->
                        logger.warn("Unable to replay recording state for {}", event.deviceId, ex)
                    }
                } else if (pendingSessionId != null && pendingSessionId != session.id) {
                    logger.debug(
                        "Skipping replay for {} because session changed from {} to {}",
                        event.deviceId,
                        pendingSessionId,
                        session.id
                    )
                }
            }
        } else {
            logger.info("Device {} connected (no active session)", event.deviceId)
        }
    }

    private suspend fun handleDisconnected(event: DeviceConnectionEvent.Disconnected) {
        val session = sessionRepository.activeSession.value
        val timestamp = event.timestamp.toEpochMilli()
        val reasonToken = event.reason.name.lowercase(Locale.US)
        if (session != null) {
            val eventId = "device-disconnected-${event.deviceId}-${reasonToken}-$timestamp"
            runCatching {
                sessionRepository.registerEvent(
                    eventId = eventId,
                    label = "device-disconnected:${reasonToken}:${event.deviceId}",
                    timestampMs = timestamp,
                    deviceIds = listOf(event.deviceId)
                )
            }.onFailure { ex ->
                logger.debug(
                    "Unable to register disconnect event for {}: {}",
                    event.deviceId,
                    ex.message
                )
            }
        }
        if (session != null) {
            logger.warn(
                "Device {} disconnected from session {} (reason: {})",
                event.deviceId,
                session.id,
                reasonToken
            )
            if (session.status == SessionStatus.ACTIVE) {
                pendingReplay[event.deviceId] = session.id
                logger.info(
                    "Device {} queued for command replay once connection resumes",
                    event.deviceId
                )
                val queuedTimestamp = timeProvider().toEpochMilli()
                runCatching {
                    sessionRepository.registerEvent(
                        eventId = "device-replay-queued-${event.deviceId}-$queuedTimestamp",
                        label = "device-replay-queued:${event.deviceId}",
                        timestampMs = queuedTimestamp,
                        deviceIds = listOf(event.deviceId)
                    )
                }.onFailure { ex ->
                    logger.debug(
                        "Unable to register replay queue event for {}: {}",
                        event.deviceId,
                        ex.message
                    )
                }
            }
        } else {
            logger.warn(
                "Device {} disconnected (no active session) (reason: {})",
                event.deviceId,
                reasonToken
            )
        }
    }

    companion object {
        private const val DEFAULT_HEARTBEAT_TIMEOUT_MS = 5_000L
        private const val DEFAULT_POLL_INTERVAL_MS = 1_000L
    }
}
