package com.buccancs.desktop.domain.model
import java.time.Instant
sealed class DeviceConnectionEvent {
    abstract val deviceId: String
    abstract val timestamp: Instant
    abstract val sessionId: String?
    data class Connected(
        override val deviceId: String,
        override val timestamp: Instant,
        override val sessionId: String?
    ) : DeviceConnectionEvent()
    data class Disconnected(
        override val deviceId: String,
        override val timestamp: Instant,
        override val sessionId: String?,
        val reason: DisconnectReason
    ) : DeviceConnectionEvent()
    enum class DisconnectReason {
        HEARTBEAT_TIMEOUT,
        EXPLICIT_STATUS,
        MANUAL
    }
}
