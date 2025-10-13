package com.buccancs.application.performance

import android.content.Context
import android.os.Debug
import android.os.Process
import android.os.SystemClock
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max

@Singleton
class PerformanceMetricsRecorder @Inject constructor(
    @ApplicationContext private val context: Context,
    private val storage: RecordingStorage,
    @ApplicationScope private val scope: CoroutineScope
) {
    private val mutex = Mutex()
    private val fileMutex = Mutex()
    private val json = Json { encodeDefaults = true }
    private var job: Job? = null
    private var activeSessionId: String? = null

    suspend fun start(sessionId: String) {
        mutex.withLock {
            stopLocked()
            activeSessionId = sessionId
            job = scope.launch(Dispatchers.Default) {
                collectSamples(sessionId)
            }
        }
    }

    suspend fun stop() {
        mutex.withLock { stopLocked() }
    }

    private suspend fun stopLocked() {
        val current = job
        job = null
        activeSessionId = null
        current?.cancelAndJoin()
    }

    private suspend fun collectSamples(sessionId: String) {
        var lastCpuMillis = Process.getElapsedCpuTime()
        var lastWallMillis = SystemClock.elapsedRealtime()
        val cores = max(Runtime.getRuntime().availableProcessors(), 1)
        while (currentCoroutineContext().isActive) {
            delay(SAMPLE_INTERVAL_MS)
            val nowCpuMillis = Process.getElapsedCpuTime()
            val nowWallMillis = SystemClock.elapsedRealtime()
            val cpuDelta = nowCpuMillis - lastCpuMillis
            val wallDelta = nowWallMillis - lastWallMillis
            val cpuPercent = if (wallDelta > 0L) {
                ((cpuDelta.toDouble() / (wallDelta.toDouble() * cores)) * 100.0).coerceIn(0.0, 100.0)
            } else {
                0.0
            }
            lastCpuMillis = nowCpuMillis
            lastWallMillis = nowWallMillis

            val memoryInfo = Debug.MemoryInfo()
            Debug.getMemoryInfo(memoryInfo)
            val pssMb = memoryInfo.totalPss / 1024.0
            val javaHeapMb =
                (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024.0 * 1024.0)
            val tempC: Double? = null

            val sessionDir = storage.sessionDirectory(sessionId)
            val availableStorageMb = sessionDir.usableSpace / (1024.0 * 1024.0)

            val timestamp = Instant.fromEpochMilliseconds(System.currentTimeMillis())
            val payload = PerformanceSample(
                sessionId = sessionId,
                timestampEpochMs = timestamp.toEpochMilliseconds(),
                cpuPercent = cpuPercent,
                processCpuMillis = nowCpuMillis,
                memoryPssMb = pssMb,
                javaHeapMb = javaHeapMb,
                availableStorageMb = availableStorageMb,
                batteryTempC = tempC
            )
            writeSample(sessionId, payload)
        }
    }

    private suspend fun writeSample(sessionId: String, payload: SamplePayload) {
        withContext(Dispatchers.IO) {
            fileMutex.withLock {
                val file = storage.performanceMetricsFile(sessionId)
                file.parentFile?.mkdirs()
                file.appendText(json.encodeToString(payload) + "\n")
            }
        }
    }

    companion object {
        private const val SAMPLE_INTERVAL_MS = 2_000L
    }
}
