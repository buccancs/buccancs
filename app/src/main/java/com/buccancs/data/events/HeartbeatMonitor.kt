package com.buccancs.data.events

import com.buccancs.di.ApplicationScope
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class HeartbeatMonitor @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope
) {
    private val lastBeatEpochMs = AtomicLong(0L)
    private val _status = MutableStateFlow(
        HeartbeatStatus(
            state = HeartbeatState.WARN,
            lastBeat = null,
            ageMillis = Long.MAX_VALUE
        )
    )
    val status: StateFlow<HeartbeatStatus> = _status.asStateFlow()

    init {
        scope.launch {
            while (true) {
                updateStatus()
                delay(CHECK_INTERVAL_MS)
            }
        }
    }

    fun record(timestamp: Instant = nowInstant()) {
        lastBeatEpochMs.set(timestamp.toEpochMilliseconds())
    }

    private fun updateStatus() {
        val nowMs = nowInstant().toEpochMilliseconds()
        val beatMs = lastBeatEpochMs.get()
        val age = if (beatMs == 0L) Long.MAX_VALUE else nowMs - beatMs
        val state = if (age <= WARN_THRESHOLD_MS) {
            HeartbeatState.SAFE
        } else {
            HeartbeatState.WARN
        }
        _status.value = HeartbeatStatus(
            state = state,
            lastBeat = if (beatMs == 0L) null else Instant.fromEpochMilliseconds(beatMs),
            ageMillis = age
        )
        if (age >= AUTO_STOP_THRESHOLD_MS) {
            onAutoStop()
        }
    }

    protected fun onAutoStop() {

    }

    data class HeartbeatStatus(
        val state: HeartbeatState,
        val lastBeat: Instant?,
        val ageMillis: Long
    )

    enum class HeartbeatState {
        SAFE,
        WARN
    }

    companion object {
        private const val CHECK_INTERVAL_MS = 1_000L
        private const val WARN_THRESHOLD_MS = 5_000L
        private const val AUTO_STOP_THRESHOLD_MS = 12_000L
    }
}

