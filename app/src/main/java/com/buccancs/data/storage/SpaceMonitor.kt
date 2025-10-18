package com.buccancs.data.storage

import android.os.StatFs
import com.buccancs.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max

@Singleton
class SpaceMonitor @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val storage: RecordingStorage
) {
    private val mutex =
        Mutex()
    private val _state =
        MutableStateFlow(
            SpaceState.EMPTY
        )
    val state: StateFlow<SpaceState> =
        _state.asStateFlow()
    private var job: Job? =
        null

    fun start(
        refreshIntervalMs: Long = DEFAULT_REFRESH_INTERVAL_MS
    ) {
        if (job != null) return
        job =
            scope.launch(
                Dispatchers.IO
            ) {
                while (isActive) {
                    refresh()
                    delay(
                        refreshIntervalMs
                    )
                }
            }
    }

    suspend fun refresh() {
        val sessions =
            mutableListOf<SessionUsage>()
        val (totalBytes, availableBytes) = computeFsStats()
        val usedBytes =
            max(
                0L,
                totalBytes - availableBytes
            )
        withContext(
            Dispatchers.IO
        ) {
            storage.sessionIds()
                .forEach { sessionId ->
                    val directory =
                        storage.sessionDirectoryOrNull(
                            sessionId
                        )
                            ?: return@forEach
                    val size =
                        storage.directorySize(
                            directory
                        )
                    sessions += SessionUsage(
                        sessionId = sessionId,
                        bytes = size,
                        lastModifiedEpochMs = directory.lastModified()
                    )
                }
        }
        val ordered =
            sessions.sortedBy { it.lastModifiedEpochMs }
        val status =
            determineStatus(
                totalBytes,
                availableBytes
            )
        val newState =
            SpaceState(
                totalBytes = totalBytes,
                usedBytes = usedBytes,
                availableBytes = availableBytes,
                status = status,
                sessions = ordered
            )
        mutex.withLock {
            _state.value =
                newState
        }
    }

    private fun computeFsStats(): Pair<Long, Long> {
        val root =
            storage.recordingsRoot()
        val stat =
            StatFs(
                root.absolutePath
            )
        val blockSize =
            stat.blockSizeLong
        val total =
            stat.blockCountLong * blockSize
        val available =
            stat.availableBlocksLong * blockSize
        return total to available
    }

    private fun determineStatus(
        totalBytes: Long,
        availableBytes: Long
    ): SpaceStatus {
        if (totalBytes <= 0L) return SpaceStatus.OK
        val ratio =
            availableBytes.toDouble() / totalBytes.toDouble()
        return when {
            availableBytes <= CRITICAL_MIN_BYTES || ratio <= CRITICAL_RATIO -> SpaceStatus.CRITICAL
            availableBytes <= WARNING_MIN_BYTES || ratio <= WARNING_RATIO -> SpaceStatus.WARNING
            else -> SpaceStatus.OK
        }
    }

    companion object {
        private const val DEFAULT_REFRESH_INTERVAL_MS =
            5 * 60 * 1000L
        private const val WARNING_RATIO =
            0.12
        private const val CRITICAL_RATIO =
            0.05
        private const val WARNING_MIN_BYTES =
            3L * 1024 * 1024 * 1024 // 3 GB
        private const val CRITICAL_MIN_BYTES =
            1L * 1024 * 1024 * 1024 // 1 GB
    }
}

data class SpaceState(
    val totalBytes: Long,
    val usedBytes: Long,
    val availableBytes: Long,
    val status: SpaceStatus,
    val sessions: List<SessionUsage>
) {
    companion object {
        val EMPTY =
            SpaceState(
                totalBytes = 0,
                usedBytes = 0,
                availableBytes = 0,
                status = SpaceStatus.OK,
                sessions = emptyList()
            )
    }
}

data class SessionUsage(
    val sessionId: String,
    val bytes: Long,
    val lastModifiedEpochMs: Long
)

enum class SpaceStatus {
    OK,
    WARNING,
    CRITICAL
}
