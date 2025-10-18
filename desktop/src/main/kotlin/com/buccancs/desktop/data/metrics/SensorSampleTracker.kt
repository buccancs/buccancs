package com.buccancs.desktop.data.metrics

import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

/**
 * Tracks cadence for sensor streams so we can spot rate drops and large gaps.
 */
class SampleRateTracker(
    private val targetHz: Double,
    private val dropToleranceMultiplier: Double = DEFAULT_DROP_TOLERANCE_MULTIPLIER
) {
    init {
        require(
            targetHz > 0
        ) { "targetHz must be positive" }
        require(
            dropToleranceMultiplier >= 1.0
        ) { "dropToleranceMultiplier must be at least 1.0" }
    }

    private var firstTimestampMs: Long? =
        null
    private var lastTimestampMs: Long? =
        null
    private var sampleCount: Long =
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
        sampleCount =
            0
        dropCount =
            0
        maxGapMs =
            0
    }

    fun record(
        timestampMs: Long
    ): SampleRateSnapshot {
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
        sampleCount += 1
        val referenceStart =
            firstTimestampMs
                ?: timestampMs
        val elapsedMs =
            (lastTimestampMs!! - referenceStart).coerceAtLeast(
                1
            )
        val averageHz =
            if (sampleCount <= 1) {
                targetHz
            } else {
                ((sampleCount - 1) * MS_PER_SECOND) / elapsedMs.toDouble()
            }
        return SampleRateSnapshot(
            totalSamples = sampleCount,
            averageHz = averageHz,
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
        MS_PER_SECOND / targetHz

    data class SampleRateSnapshot(
        val totalSamples: Long,
        val averageHz: Double,
        val dropCount: Long,
        val maxGapMs: Long
    )

    companion object {
        private const val MS_PER_SECOND =
            1000.0
        private const val DEFAULT_DROP_TOLERANCE_MULTIPLIER =
            1.2
    }
}

data class ValueRange(
    val min: Double? = null,
    val max: Double? = null
)

/**
 * Validates sensor values against declared ranges while keeping aggregate statistics.
 */
class SensorValueValidator(
    private val ranges: Map<String, ValueRange>
) {
    private var outOfRangeCount: Long =
        0
    private var minObserved: Double? =
        null
    private var maxObserved: Double? =
        null

    fun record(
        values: Map<String, Double?>
    ): ValidationSnapshot {
        values.forEach { (key, value) ->
            val actual =
                value
                    ?: return@forEach
            minObserved =
                minObserved?.let {
                    min(
                        it,
                        actual
                    )
                }
                    ?: actual
            maxObserved =
                maxObserved?.let {
                    max(
                        it,
                        actual
                    )
                }
                    ?: actual
            val range =
                ranges[key]
                    ?: return@forEach
            if ((range.min != null && actual < range.min) || (range.max != null && actual > range.max)) {
                outOfRangeCount += 1
            }
        }
        return ValidationSnapshot(
            outOfRangeCount = outOfRangeCount,
            minObserved = minObserved,
            maxObserved = maxObserved
        )
    }

    data class ValidationSnapshot(
        val outOfRangeCount: Long,
        val minObserved: Double?,
        val maxObserved: Double?
    )
}
