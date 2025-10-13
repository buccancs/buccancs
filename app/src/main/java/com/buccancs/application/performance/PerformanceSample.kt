package com.buccancs.application.performance

import kotlinx.serialization.Serializable

@Serializable
data class PerformanceSample(
    val sessionId: String,
    val timestampEpochMs: Long,
    val cpuPercent: Double,
    val processCpuMillis: Long,
    val memoryPssMb: Double,
    val javaHeapMb: Double,
    val availableStorageMb: Double,
    val batteryTempC: Double?
)
