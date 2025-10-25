package com.buccancs.data.sensor.connector.camera

import android.media.MediaCodec
import com.buccancs.domain.model.EncoderStats
import kotlin.math.max

internal class EncoderStatsCollector(
    private val segmentIndex: Int
) {
    private var startedAtEpochMs: Long =
        0
    private var endedAtEpochMs: Long =
        0
    private var firstPresentationUs: Long =
        -1
    private var lastPresentationUs: Long =
        -1
    private var frameCount: Long =
        0
    private var keyFrameCount: Long =
        0
    private var droppedFrameCount: Long =
        0
    private var bytesWritten: Long =
        0
    private var maxLatencyNs: Long =
        0
    private var totalLatencyNs: Long =
        0
    private var latencySamples: Long =
        0

    fun markStart(
        epochMs: Long
    ) {
        startedAtEpochMs =
            epochMs
        endedAtEpochMs =
            epochMs
    }

    fun recordBuffer(
        info: MediaCodec.BufferInfo,
        writtenBytes: Int,
        latencyNs: Long
    ) {
        if (firstPresentationUs < 0) {
            firstPresentationUs =
                info.presentationTimeUs
        }
        lastPresentationUs =
            info.presentationTimeUs
        frameCount += 1
        if (info.flags and MediaCodec.BUFFER_FLAG_KEY_FRAME != 0) {
            keyFrameCount += 1
        }
        bytesWritten += writtenBytes.coerceAtLeast(
            0
        )
        if (latencyNs > 0) {
            totalLatencyNs += latencyNs
            latencySamples += 1
            maxLatencyNs =
                max(
                    maxLatencyNs,
                    latencyNs
                )
        }
    }

    fun markDropped(
        frames: Long
    ) {
        if (frames > 0) {
            droppedFrameCount += frames
        }
    }

    fun markEnd(
        epochMs: Long
    ) {
        endedAtEpochMs =
            max(
                epochMs,
                startedAtEpochMs
            )
    }

    fun buildStats(): EncoderStats {
        val timelineDurationMs =
            if (lastPresentationUs >= firstPresentationUs && firstPresentationUs >= 0) {
                (lastPresentationUs - firstPresentationUs) / 1_000
            } else {
                0L
            }
        val wallClockDurationMs =
            (endedAtEpochMs - startedAtEpochMs).coerceAtLeast(
                0
            )
        val durationMillis =
            max(
                timelineDurationMs,
                wallClockDurationMs
            )
        val averageBitrateBps =
            if (durationMillis > 0) {
                (bytesWritten * 8_000L) / durationMillis
            } else {
                0L
            }
        val maxLatencyMillis =
            maxLatencyNs / 1_000_000.0
        val averageLatencyMillis =
            if (latencySamples > 0) {
                (totalLatencyNs / latencySamples.toDouble()) / 1_000_000.0
            } else {
                0.0
            }
        val endedEpoch =
            max(
                endedAtEpochMs,
                startedAtEpochMs + durationMillis
            )
        return EncoderStats(
            segmentIndex = segmentIndex,
            startedAtEpochMs = startedAtEpochMs,
            endedAtEpochMs = endedEpoch,
            durationMillis = durationMillis,
            frameCount = frameCount,
            keyFrameCount = keyFrameCount,
            droppedFrameCount = droppedFrameCount,
            bytesWritten = bytesWritten,
            averageBitrateBps = averageBitrateBps,
            maxEncodeLatencyMillis = maxLatencyMillis,
            averageEncodeLatencyMillis = averageLatencyMillis
        )
    }
}
