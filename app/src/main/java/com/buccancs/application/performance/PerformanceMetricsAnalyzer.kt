package com.buccancs.application.performance

import android.util.Log
import com.buccancs.core.serialization.StandardJson
import com.buccancs.core.storage.WriteResult
import com.buccancs.data.storage.AtomicFileWriter
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.PerformanceSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max

@Singleton
class PerformanceMetricsAnalyzer @Inject constructor(
    private val storage: RecordingStorage,
    @StandardJson private val json: Json
) {
    companion object {
        private const val TAG =
            "PerformanceMetricsAnalyzer"
    }

    suspend fun summarise(
        sessionId: String
    ) {
        withContext(
            Dispatchers.IO
        ) {
            val metricsFile =
                storage.performanceMetricsFile(
                    sessionId
                )
            val summaryFile =
                storage.performanceSummaryFile(
                    sessionId
                )
            summarise(
                sessionId,
                metricsFile,
                summaryFile
            )
        }
    }

    suspend fun summarise(
        sessionId: String,
        metricsFile: File,
        summaryFile: File
    ) {
        if (!metricsFile.exists()) return
        val samples =
            metricsFile.readLines()
                .mapNotNull { line -> line.takeIf { it.isNotBlank() } }
                .mapNotNull { line ->
                    runCatching {
                        json.decodeFromString<PerformanceSample>(
                            line
                        )
                    }.getOrNull()
                }
        if (samples.isEmpty()) return
        val summary =
            buildSummary(
                sessionId,
                samples
            )
        summaryFile.parentFile?.mkdirs()

        when (val result =
            AtomicFileWriter.writeAtomicSafe(
                summaryFile,
                json.encodeToString(
                    summary
                ),
                checkSpace = true
            )) {
            is WriteResult.Success -> {
                Log.d(
                    TAG,
                    "Performance summary written for session $sessionId"
                )
            }

            is WriteResult.Failure.InsufficientSpace -> {
                Log.w(
                    TAG,
                    "Insufficient space to write performance summary"
                )
            }

            is WriteResult.Failure.WriteError -> {
                Log.e(
                    TAG,
                    "Failed to write performance summary",
                    result.cause
                )
            }
        }
    }

    private fun buildSummary(
        sessionId: String,
        samples: List<PerformanceSample>
    ): PerformanceSummary {
        val cpuValues =
            samples.map { it.cpuPercent }
        val pssValues =
            samples.map { it.memoryPssMb }
        val heapValues =
            samples.map { it.javaHeapMb }
        val storageValues =
            samples.map { it.availableStorageMb }
        val batteryValues =
            samples.mapNotNull { it.batteryTempC }
        val durationSeconds =
            duration(
                samples
            )
        return PerformanceSummary(
            sessionId = sessionId,
            sampleCount = samples.size,
            durationSeconds = durationSeconds,
            cpuAveragePercent = cpuValues.averageSafe(),
            cpuMaxPercent = cpuValues.maxOrZero(),
            memoryPssAverageMb = pssValues.averageSafe(),
            memoryPssMaxMb = pssValues.maxOrZero(),
            heapAverageMb = heapValues.averageSafe(),
            heapMaxMb = heapValues.maxOrZero(),
            minAvailableStorageMb = storageValues.minOrNull()
                ?: 0.0,
            maxBatteryTempC = batteryValues.maxOrNull()
        )
    }

    private fun duration(
        samples: List<PerformanceSample>
    ): Double {
        val minTs =
            samples.minOfOrNull { it.timestampEpochMs }
                ?: return 0.0
        val maxTs =
            samples.maxOfOrNull { it.timestampEpochMs }
                ?: return 0.0
        return max(
            0L,
            maxTs - minTs
        ) / 1000.0
    }

    private fun List<Double>.averageSafe(): Double =
        if (isEmpty()) 0.0 else sum() / size

    private fun List<Double>.maxOrZero(): Double =
        maxOrNull()
            ?: 0.0
}
