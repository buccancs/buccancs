package com.buccancs.desktop.data.metrics

import kotlin.math.floor
import kotlin.math.max

/**
 * Tracks per-stream cadence metrics so callers can detect sustained drops or gaps.
 */
class FrameRateTracker(
    private val targetFps: Double,
    private val dropToleranceMultiplier: Double = DEFAULT_DROP_TOLERANCE_MULTIPLIER
) {
    init {
        require(
            targetFps > 0
        ) { "targetFps must be positive" }
        require(
            dropToleranceMultiplier >= 1.0
        ) { "dropToleranceMultiplier must be at least 1.0" }
    }

    private var firstTimestampMs: Long? =
        null
    private var lastTimestampMs: Long? =
        null
    private var frameCount: Long =
        0
    private var dropCount: Long =
        0
    private var maxGapMs: Long =
        0

    fun reset() {
        firstTimestampMs =
            null
        lastTimestampMs =
            null
        frameCount =
            0
        dropCount =
            0
        maxGapMs =
            0
    }

    fun record(
        timestampMs: Long
    ): FrameRateSnapshot {
        require(
            timestampMs >= 0
        ) { "timestampMs must be non-negative" }
        if (firstTimestampMs == null) {
            firstTimestampMs =
                timestampMs
        }
        lastTimestampMs?.let { previous ->
            val gapMs =
                (timestampMs - previous).coerceAtLeast(
                    0
                )
            maxGapMs =
                max(
                    maxGapMs,
                    gapMs
                )
            updateDrops(
                gapMs
            )
        }
        lastTimestampMs =
            timestampMs
        frameCount += 1
        val referenceStart =
            firstTimestampMs
                ?: timestampMs
        val elapsedMs =
            (lastTimestampMs!! - referenceStart).coerceAtLeast(
                1
            )
        val averageFps =
            if (frameCount <= 1) {
                targetFps
            } else {
                ((frameCount - 1) * MS_PER_SECOND) / elapsedMs
            }
        return FrameRateSnapshot(
            totalFrames = frameCount,
            averageFps = averageFps,
            dropCount = dropCount,
            maxGapMs = maxGapMs
        )
    }

    private fun updateDrops(
        gapMs: Long
    ) {
        val expectedGapMs =
            expectedGapMs()
        if (gapMs <= expectedGapMs * dropToleranceMultiplier) {
            return
        }
        val ratio =
            gapMs.toDouble() / expectedGapMs
        val estimatedDrops =
            floor(
                ratio
            ).toLong() - 1
        dropCount += if (estimatedDrops >= 1) estimatedDrops else 1
    }

    private fun expectedGapMs(): Double =
        MS_PER_SECOND / targetFps

    data class FrameRateSnapshot(
        val totalFrames: Long,
        val averageFps: Double,
        val dropCount: Long,
        val maxGapMs: Long
    )

    companion object {
        private const val DEFAULT_DROP_TOLERANCE_MULTIPLIER =
            1.5
        private const val MS_PER_SECOND =
            1000.0
    }
}
