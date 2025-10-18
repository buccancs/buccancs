package com.buccancs.core.time

import android.os.SystemClock
import com.buccancs.domain.model.RecordingSessionAnchor

data class TimeModelAdapter(
    val sessionId: String,
    val anchorEpochMs: Long,
    val clockOffsetMs: Long,
    val deviceEpochMs: Long,
    val deviceMonotonicNanos: Long,
    val recordingStartEpochMs: Long? = null,
    val recordingStartMonotonicNanos: Long? = null
) {
    fun alignDeviceEpoch(
        deviceEpochMs: Long
    ): Long =
        anchorEpochMs + clockOffsetMs + (deviceEpochMs - this.deviceEpochMs)

    fun alignMonotonic(
        monotonicNanos: Long
    ): Long =
        anchorEpochMs + clockOffsetMs + ((monotonicNanos - deviceMonotonicNanos) / NANOS_PER_MILLISECOND)

    fun markRecordingStart(
        deviceEpochMs: Long,
        monotonicNanos: Long
    ): TimeModelAdapter =
        copy(
            recordingStartEpochMs = deviceEpochMs,
            recordingStartMonotonicNanos = monotonicNanos
        )

    fun startAlignedEpochMillis(): Long? =
        recordingStartEpochMs?.let {
            alignDeviceEpoch(
                it
            )
        }

    fun durationSinceStartMs(
        currentMonotonicNanos: Long
    ): Long? {
        val start =
            recordingStartMonotonicNanos
                ?: return null
        val delta =
            currentMonotonicNanos - start
        return if (delta <= 0L) 0L else delta / NANOS_PER_MILLISECOND
    }

    fun hasRecordingStarted(): Boolean =
        recordingStartEpochMs != null

    companion object {
        private const val NANOS_PER_MILLISECOND =
            1_000_000L

        fun fromAnchor(
            anchor: RecordingSessionAnchor
        ): TimeModelAdapter =
            TimeModelAdapter(
                sessionId = anchor.sessionId,
                anchorEpochMs = anchor.referenceTimestamp.toEpochMilliseconds(),
                clockOffsetMs = anchor.sharedClockOffsetMillis,
                deviceEpochMs = System.currentTimeMillis(),
                deviceMonotonicNanos = SystemClock.elapsedRealtimeNanos()
            )
    }
}
