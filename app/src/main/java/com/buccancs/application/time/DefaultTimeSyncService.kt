package com.buccancs.application.time

import android.util.Log
import com.buccancs.control.TimeSyncServiceGrpcKt
import com.buccancs.control.timeSyncPing
import com.buccancs.control.timeSyncReport
import com.buccancs.data.orchestration.DeviceIdentityProvider
import com.buccancs.data.orchestration.GrpcChannelFactory
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.buccancs.util.nowInstant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Instant

@Singleton
class DefaultTimeSyncService @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val configRepository: OrchestratorConfigRepository,
    private val channelFactory: GrpcChannelFactory,
    private val identityProvider: DeviceIdentityProvider
) : TimeSyncService {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _status = MutableStateFlow(initialStatus())
    override val status: StateFlow<TimeSyncStatus> = _status.asStateFlow()
    private val _history = MutableStateFlow<List<TimeSyncObservation>>(emptyList())
    override val history: StateFlow<List<TimeSyncObservation>> = _history.asStateFlow()
    private var syncJob: Job? = null
    private val historyBuffer = ArrayDeque<TimeSyncObservation>(MAX_HISTORY)

    override suspend fun start() {
        if (syncJob != null) return
        syncJob = scope.launch(dispatcher) {
            while (isActive) {
                val config = configRepository.config.first()
                val success = runCatching { performSync(config) }
                    .onFailure { handleFailure(it) }
                    .isSuccess
                val waitMs = if (success) SYNC_INTERVAL_MS else RETRY_INTERVAL_MS
                delay(waitMs)
            }
        }
    }

    override suspend fun forceSync(): TimeSyncStatus = withContext(dispatcher) {
        val config = configRepository.config.first()
        runCatching { performSync(config) }
            .onFailure { ex ->
                handleFailure(ex)
                throw ex
            }
        status.value
    }

    private suspend fun performSync(config: OrchestratorConfig) {
        val channel = channelFactory.channel(config)
        val stub = TimeSyncServiceGrpcKt.TimeSyncServiceCoroutineStub(channel)
        val deviceId = identityProvider.deviceId()
        val samples = mutableListOf<SyncSample>()
        repeat(SAMPLE_COUNT) { index ->
            val sendEpochMs = nowEpochMillis()
            val pong = stub.ping(
                timeSyncPing {
                    clientSendEpochMs = sendEpochMs
                    this.deviceId = deviceId
                }
            )
            val receiveEpochMs = nowEpochMillis()
            samples += computeSyncSample(
                sendEpochMs = sendEpochMs,
                receiveEpochMs = receiveEpochMs,
                serverReceiveEpochMs = pong.serverReceiveEpochMs,
                serverSendEpochMs = pong.serverSendEpochMs
            )
            if (index < SAMPLE_COUNT - 1) {
                delay(SAMPLE_DELAY_MS)
            }
        }
        if (samples.isEmpty()) {
            throw IllegalStateException("Time sync produced no samples")
        }
        val ranked = samples.sortedBy { it.roundTripMs }
        val best = ranked.take(BEST_SAMPLE_COUNT.coerceAtMost(ranked.size))
        val offsetAverage = best.map { it.offsetMs }.average()
        val roundTripAverage = best.map { it.roundTripMs }.average()
        val filteredRoundTrip = ranked.medianBy { it.roundTripMs }
        val currentInstant = nowInstant()
        stub.report(
            timeSyncReport {
                this.deviceId = deviceId
                offsetMs = offsetAverage
                roundTripMs = roundTripAverage
                sampleEpochMs = currentInstant.toEpochMilliseconds()
            }
        )
        val observation = TimeSyncObservation(
            timestamp = currentInstant,
            offsetMillis = offsetAverage,
            roundTripMillis = roundTripAverage
        )
        appendHistory(observation)
        val slopeMillisPerMinute = computeSlopePerMinute(historyBuffer)
        val driftEstimate = computeDriftEstimate(historyBuffer, offsetAverage, currentInstant)
        val quality = qualityFor(offsetAverage, roundTripAverage, best.size, filteredRoundTrip)
        _status.value = TimeSyncStatus(
            offsetMillis = offsetAverage.roundToLong(),
            roundTripMillis = roundTripAverage.roundToLong().coerceAtLeast(0L),
            lastSync = currentInstant,
            driftEstimateMillisPerMinute = driftEstimate,
            filteredRoundTripMillis = filteredRoundTrip,
            quality = quality,
            sampleCount = best.size,
            regressionSlopeMillisPerMinute = slopeMillisPerMinute
        )
    }

    private fun handleFailure(error: Throwable) {
        Log.w(TAG, "Time sync failed: ${error.message}", error)
        val previous = _status.value
        _status.value = previous.copy(
            lastSync = nowInstant(),
            roundTripMillis = Long.MAX_VALUE,
            filteredRoundTripMillis = Double.POSITIVE_INFINITY,
            quality = TimeSyncQuality.UNKNOWN,
            regressionSlopeMillisPerMinute = 0.0
        )
    }

    private fun appendHistory(observation: TimeSyncObservation) {
        historyBuffer += observation
        while (historyBuffer.size > MAX_HISTORY) {
            historyBuffer.removeFirst()
        }
        _history.value = historyBuffer.toList()
    }

    private fun computeDriftEstimate(
        history: Collection<TimeSyncObservation>,
        currentOffsetMs: Double,
        currentInstant: Instant
    ): Double {
        val previous = history.lastOrNull { it.timestamp < currentInstant } ?: return 0.0
        val elapsedMs =
            (currentInstant.toEpochMilliseconds() - previous.timestamp.toEpochMilliseconds()).coerceAtLeast(1L)
        val delta = currentOffsetMs - previous.offsetMillis
        return delta / elapsedMs * 60_000.0
    }

    private fun computeSlopePerMinute(history: Collection<TimeSyncObservation>): Double {
        if (history.size < 2) return 0.0
        val times = history.map { it.timestamp.toEpochMilliseconds().toDouble() }
        val offsets = history.map { it.offsetMillis }
        val meanTime = times.average()
        val meanOffset = offsets.average()
        var numerator = 0.0
        var denominator = 0.0
        for (index in history.indices) {
            val dt = times[index] - meanTime
            val doffset = offsets[index] - meanOffset
            numerator += dt * doffset
            denominator += dt * dt
        }
        if (denominator == 0.0) return 0.0
        val slopePerMillisecond = numerator / denominator
        return slopePerMillisecond * 60_000.0
    }

    private fun qualityFor(
        offsetMs: Double,
        roundTripMs: Double,
        sampleCount: Int,
        filteredRoundTripMs: Double
    ): TimeSyncQuality {
        if (sampleCount == 0) return TimeSyncQuality.UNKNOWN
        val absOffset = abs(offsetMs)
        val effectiveRtt = minOf(roundTripMs, filteredRoundTripMs)
        return when {
            effectiveRtt <= 12.0 && absOffset <= 2.0 -> TimeSyncQuality.GOOD
            effectiveRtt <= 25.0 && absOffset <= 5.0 -> TimeSyncQuality.FAIR
            else -> TimeSyncQuality.POOR
        }
    }

    private fun List<SyncSample>.medianBy(selector: (SyncSample) -> Double): Double {
        if (isEmpty()) return Double.POSITIVE_INFINITY
        val sorted = map(selector).sorted()
        val mid = sorted.size / 2
        return if (sorted.size % 2 == 0) {
            (sorted[mid - 1] + sorted[mid]) / 2.0
        } else {
            sorted[mid]
        }
    }

    private fun computeSyncSample(
        sendEpochMs: Long,
        receiveEpochMs: Long,
        serverReceiveEpochMs: Long,
        serverSendEpochMs: Long
    ): SyncSample {
        val roundTrip = (receiveEpochMs - sendEpochMs).coerceAtLeast(0L).toDouble()
        val serverDelta = serverSendEpochMs - serverReceiveEpochMs
        val clientDelta = receiveEpochMs - sendEpochMs
        val offset = ((serverReceiveEpochMs - sendEpochMs) + (serverSendEpochMs - receiveEpochMs)) / 2.0
        val adjustedOffset = offset - (serverDelta - clientDelta) / 2.0
        return SyncSample(
            offsetMs = adjustedOffset,
            roundTripMs = roundTrip
        )
    }

    private fun nowEpochMillis(): Long = System.currentTimeMillis()

    private fun initialStatus(): TimeSyncStatus = TimeSyncStatus(
        offsetMillis = 0,
        roundTripMillis = Long.MAX_VALUE,
        lastSync = nowInstant(),
        driftEstimateMillisPerMinute = 0.0,
        filteredRoundTripMillis = Double.POSITIVE_INFINITY,
        quality = TimeSyncQuality.UNKNOWN,
        sampleCount = 0,
        regressionSlopeMillisPerMinute = 0.0
    )

    private data class SyncSample(
        val offsetMs: Double,
        val roundTripMs: Double
    )

    private companion object {
        private const val TAG = "TimeSyncService"
        private const val SYNC_INTERVAL_MS = 5_000L
        private const val RETRY_INTERVAL_MS = 10_000L
        private const val SAMPLE_COUNT = 7
        private const val BEST_SAMPLE_COUNT = 3
        private const val SAMPLE_DELAY_MS = 40L
        private const val MAX_HISTORY = 64
    }
}
