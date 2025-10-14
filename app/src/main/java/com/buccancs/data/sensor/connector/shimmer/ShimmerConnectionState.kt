package com.buccancs.data.sensor.connector.shimmer

import kotlin.time.Instant
import com.buccancs.domain.model.SessionArtifact
import com.shimmerresearch.driver.ShimmerDevice

/**
 * Sealed class hierarchy representing all possible Shimmer connection states.
 * Replaces mutable nullable variables with explicit state modelling.
 */
internal sealed class ShimmerConnectionState {
    /** Device is disconnected and idle */
    data object Disconnected : ShimmerConnectionState()

    /** Connection attempt in progress */
    data class Connecting(
        val targetMac: String,
        val targetName: String
    ) : ShimmerConnectionState()

    /** Successfully connected to device */
    data class Connected(
        val device: ShimmerDevice,
        val mac: String,
        val connectedAt: Instant
    ) : ShimmerConnectionState()

    /** Connected and actively streaming data */
    data class Streaming(
        val device: ShimmerDevice,
        val mac: String,
        val connectedAt: Instant,
        val sessionId: String,
        val startedAt: Instant,
        val samplesSeen: Long = 0L,
        val lastSampleTimestamp: Instant? = null
    ) : ShimmerConnectionState()

    /** Helper to get device if in a connected state */
    fun deviceOrNull(): ShimmerDevice? = when (this) {
        is Connected -> device
        is Streaming -> device
        else -> null
    }

    /** Helper to check if currently streaming */
    fun isStreaming(): Boolean = this is Streaming

    /** Helper to check if connected (including streaming) */
    fun isConnected(): Boolean = this is Connected || this is Streaming
}

/**
 * Sealed class hierarchy for recording state.
 * Separates recording state from connection state for clarity.
 */
internal sealed class RecordingState {
    /** No recording in progress */
    data object Idle : RecordingState()

    /** Recording in progress */
    data class Recording(
        val sessionId: String,
        val writer: ShimmerDataWriter
    ) : RecordingState()

    /** Recording completed, artifact ready for collection */
    data class Completed(
        val sessionId: String,
        val artifact: SessionArtifact
    ) : RecordingState()
}
