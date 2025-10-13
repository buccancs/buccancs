package com.buccancs.data.sensor

import android.os.SystemClock
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.util.toEpochMillis

internal data class SessionClock(
    val sessionId: String,
    val anchorEpochMs: Long,
    val clockOffsetMs: Long,
    val deviceEpochMs: Long,
    val deviceMonotonicNanos: Long,
    val startDeviceEpochMs: Long? = null,
    val startMonotonicNanos: Long? = null
) {
    fun alignDeviceEpoch(deviceEpochMs: Long): Long =
        anchorEpochMs + clockOffsetMs + (deviceEpochMs - this.deviceEpochMs)

    fun alignMonotonic(monotonicNanos: Long): Long =
        anchorEpochMs + clockOffsetMs + ((monotonicNanos - deviceMonotonicNanos) / 1_000_000L)

    fun markRecordingStart(deviceEpochMs: Long, monotonicNanos: Long): SessionClock =
        copy(startDeviceEpochMs = deviceEpochMs, startMonotonicNanos = monotonicNanos)

    fun startAlignedEpochMillis(): Long? =
        startDeviceEpochMs?.let { alignDeviceEpoch(it) }

    fun durationSinceStartMs(currentMonotonicNanos: Long): Long? {
        val start = startMonotonicNanos ?: return null
        val delta = currentMonotonicNanos - start
        return if (delta <= 0L) 0L else delta / 1_000_000L
    }

    fun hasRecordingStarted(): Boolean = startDeviceEpochMs != null

    companion object {
        fun fromAnchor(anchor: RecordingSessionAnchor): SessionClock =
            SessionClock(
                sessionId = anchor.sessionId,
                anchorEpochMs = anchor.referenceTimestamp.toEpochMillis(),
                clockOffsetMs = anchor.sharedClockOffsetMillis,
                deviceEpochMs = System.currentTimeMillis(),
                deviceMonotonicNanos = SystemClock.elapsedRealtimeNanos()
            )
    }
}
