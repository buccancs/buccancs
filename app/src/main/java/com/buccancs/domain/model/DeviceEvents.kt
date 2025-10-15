package com.buccancs.domain.model

import kotlin.time.Instant

enum class DeviceEventType {
    SYNC_SIGNAL,
    EVENT_MARKER,
    STIMULUS,
    COMMAND
}

data class DeviceEvent(
    val id: String,
    val type: DeviceEventType,
    val label: String,
    val scheduledAt: Instant,
    val receivedAt: Instant
)
