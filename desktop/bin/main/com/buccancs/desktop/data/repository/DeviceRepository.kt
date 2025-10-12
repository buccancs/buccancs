package com.buccancs.desktop.data.repository
import com.buccancs.desktop.domain.model.DeviceConnectionEvent
import com.buccancs.desktop.domain.model.DeviceInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap
class DeviceRepository {
    private val logger = LoggerFactory.getLogger(DeviceRepository::class.java)
    private val devices = ConcurrentHashMap<String, DeviceInfo>()
    private val state = MutableStateFlow<List<DeviceInfo>>(emptyList())
    private val events = MutableSharedFlow<DeviceConnectionEvent>(replay = 0, extraBufferCapacity = 64)
    fun observe(): StateFlow<List<DeviceInfo>> = state.asStateFlow()
    fun events(): SharedFlow<DeviceConnectionEvent> = events.asSharedFlow()
    fun snapshot(): List<DeviceInfo> = devices.values.map { it }
    fun register(
        id: String,
        model: String,
        platform: String,
        capabilities: Set<String>,
        softwareVersion: String,
        sessionId: String?
    ) {
        val now = Instant.now()
        val info = DeviceInfo(
            id = id,
            model = "$model ($softwareVersion)",
            platform = platform,
            capabilities = capabilities,
            connected = true,
            recording = false,
            batteryPercent = null,
            previewLatencyMs = null,
            clockOffsetMs = null,
            lastHeartbeat = now,
            sessionId = sessionId?.takeIf { it.isNotBlank() }
        )
        devices[id] = info
        publish()
        emitConnected(info, now)
        logger.info("Registered device {}", id)
    }
    fun updateStatus(
        id: String,
        connected: Boolean,
        recording: Boolean,
        batteryPercent: Double?,
        previewLatencyMs: Double?,
        clockOffsetMs: Double?,
        heartbeat: Instant,
        sessionId: String?
    ) {
        val current = devices[id]
        val resolvedBattery = batteryPercent ?: current?.batteryPercent
        val resolvedPreviewLatency = previewLatencyMs ?: current?.previewLatencyMs
        val resolvedClockOffset = clockOffsetMs ?: current?.clockOffsetMs
        val resolvedSessionId = sessionId?.takeIf { it.isNotBlank() } ?: current?.sessionId
        val previousConnected = current?.connected ?: false
        val updated = (current ?: DeviceInfo(
            id = id,
            model = "Unknown",
            platform = "Unknown",
            capabilities = emptySet(),
            connected = connected,
            recording = recording,
            batteryPercent = resolvedBattery,
            previewLatencyMs = resolvedPreviewLatency,
            clockOffsetMs = resolvedClockOffset,
            lastHeartbeat = heartbeat,
            sessionId = resolvedSessionId
        )).copy(
            connected = connected,
            recording = recording,
            batteryPercent = resolvedBattery,
            previewLatencyMs = resolvedPreviewLatency,
            clockOffsetMs = resolvedClockOffset,
            lastHeartbeat = heartbeat,
            sessionId = resolvedSessionId
        )
        devices[id] = updated
        publish()
        if (previousConnected != updated.connected) {
            if (updated.connected) {
                emitConnected(updated, heartbeat)
            } else {
                emitDisconnected(updated, DeviceConnectionEvent.DisconnectReason.EXPLICIT_STATUS, heartbeat)
            }
        }
    }
    fun markOffline(
        id: String,
        reason: DeviceConnectionEvent.DisconnectReason = DeviceConnectionEvent.DisconnectReason.MANUAL
    ) {
        val current = devices[id] ?: return
        if (!current.connected) {
            return
        }
        val now = Instant.now()
        val updated = current.copy(
            connected = false,
            recording = false,
            lastHeartbeat = now
        )
        devices[id] = updated
        publish()
        emitDisconnected(updated, reason, now)
    }
    fun updatePreviewLatency(id: String, latencyMs: Double, heartbeat: Instant) {
        val current = devices[id] ?: return
        devices[id] = current.copy(previewLatencyMs = latencyMs, lastHeartbeat = heartbeat)
        publish()
    }
    fun updateClockOffset(id: String, offsetMs: Double?, heartbeat: Instant) {
        val current = devices[id] ?: return
        devices[id] = current.copy(clockOffsetMs = offsetMs, lastHeartbeat = heartbeat)
        publish()
    }
    fun assignSession(sessionId: String?) {
        val normalized = sessionId?.takeIf { it.isNotBlank() }
        devices.replaceAll { _, value ->
            value.copy(sessionId = normalized)
        }
        publish()
    }
    fun updateRecordingState(id: String, recording: Boolean) {
        val current = devices[id] ?: return
        if (current.recording == recording) {
            return
        }
        val updated = current.copy(
            recording = recording,
            lastHeartbeat = Instant.now()
        )
        devices[id] = updated
        publish()
    }
    fun connectedDeviceIds(): Set<String> =
        devices.values
            .filter { it.connected }
            .mapTo(mutableSetOf()) { it.id }
    private fun emitConnected(info: DeviceInfo, timestamp: Instant) {
        events.tryEmit(
            DeviceConnectionEvent.Connected(
                deviceId = info.id,
                timestamp = timestamp,
                sessionId = info.sessionId
            )
        )
    }
    private fun emitDisconnected(
        info: DeviceInfo,
        reason: DeviceConnectionEvent.DisconnectReason,
        timestamp: Instant
    ) {
        events.tryEmit(
            DeviceConnectionEvent.Disconnected(
                deviceId = info.id,
                timestamp = timestamp,
                sessionId = info.sessionId,
                reason = reason
            )
        )
    }
    private fun publish() {
        state.value = devices.values.sortedBy { it.id }
    }
}
