package com.buccancs.data.time

import android.util.Log
import com.buccancs.control.TimeSyncServiceGrpcKt
import com.buccancs.control.timeSyncPing
import com.buccancs.control.timeSyncReport
import com.buccancs.data.orchestration.DeviceIdentityProvider
import com.buccancs.data.orchestration.GrpcChannelFactory
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.buccancs.domain.repository.TimeSyncRepository
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
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs
import kotlin.math.roundToLong

@Singleton
class DefaultTimeSyncRepository @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val configRepository: OrchestratorConfigRepository,
    private val channelFactory: GrpcChannelFactory,
    private val identityProvider: DeviceIdentityProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TimeSyncRepository {
    private val _status = MutableStateFlow(initialStatus())
    override val status: StateFlow<TimeSyncStatus> = _status.asStateFlow()
    private var syncJob: Job? = null
    private var lastObservation: SyncObservation? = null
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
        val nowInstant = Clock.System.now()
        stub.report(
            timeSyncReport {
                this.deviceId = deviceId
                offsetMs = offsetAverage
                roundTripMs = roundTripAverage
                sampleEpochMs = nowInstant.toEpochMilliseconds()
            }
        )
        val driftEstimate = lastObservation?.let { previous ->
            val elapsedMs =
                (nowInstant.toEpochMilliseconds() - previous.timestamp.toEpochMilliseconds()).coerceAtLeast(1L)
            (offsetAverage - previous.offsetMs) / elapsedMs * 1000.0
        } ?: 0.0
        lastObservation = SyncObservation(
            timestamp = nowInstant,
            offsetMs = offsetAverage
        )
        _status.value = TimeSyncStatus(
            offsetMillis = offsetAverage.roundToLong(),
            roundTripMillis = roundTripAverage.roundToLong().coerceAtLeast(0L),
            lastSync = nowInstant,
            driftEstimateMillis = abs(driftEstimate)
        )
    }

    private fun handleFailure(error: Throwable) {
        Log.w(TAG, "Time sync failed: ${error.message}", error)
        val previous = _status.value
        _status.value = previous.copy(
            lastSync = Clock.System.now(),
            roundTripMillis = Long.MAX_VALUE
        )
    }

    private fun nowEpochMillis(): Long = System.currentTimeMillis()
    private fun initialStatus(): TimeSyncStatus = TimeSyncStatus(
        offsetMillis = 0,
        roundTripMillis = Long.MAX_VALUE,
        lastSync = Clock.System.now(),
        driftEstimateMillis = 0.0
    )

    private data class SyncObservation(
        val timestamp: Instant,
        val offsetMs: Double
    )

    private companion object {
        private const val TAG = "TimeSyncRepository"
        private const val SYNC_INTERVAL_MS = 5_000L
        private const val RETRY_INTERVAL_MS = 10_000L
        private const val SAMPLE_COUNT = 7
        private const val BEST_SAMPLE_COUNT = 3
        private const val SAMPLE_DELAY_MS = 40L
    }
}
