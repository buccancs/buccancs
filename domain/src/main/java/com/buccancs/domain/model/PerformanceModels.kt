package com.buccancs.domain.model

import kotlinx.serialization.Serializable

enum class PerformanceThrottleLevel {
    NORMAL,
    CONSERVE
}

@Serializable
data class PerformanceSummary(
    val sessionId: String,
    val sampleCount: Int,
    val durationSeconds: Double,
    val cpuAveragePercent: Double,
    val cpuMaxPercent: Double,
    val memoryPssAverageMb: Double,
    val memoryPssMaxMb: Double,
    val heapAverageMb: Double,
    val heapMaxMb: Double,
    val minAvailableStorageMb: Double,
    val maxBatteryTempC: Double?
)
