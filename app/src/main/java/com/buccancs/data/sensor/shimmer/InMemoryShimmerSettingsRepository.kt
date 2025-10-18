package com.buccancs.data.sensor.shimmer

import com.buccancs.domain.model.ShimmerSettings
import com.buccancs.domain.repository.ShimmerSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class InMemoryShimmerSettingsRepository(
    initial: ShimmerSettings = ShimmerSettings()
) : ShimmerSettingsRepository {
    private val mutex =
        Mutex()
    private val state =
        MutableStateFlow(
            initial
        )
    override val settings: StateFlow<ShimmerSettings> =
        state.asStateFlow()

    override suspend fun setTargetMac(
        macAddress: String?
    ) {
        mutex.withLock {
            val normalized =
                macAddress?.trim()
                    ?.takeIf { it.isNotEmpty() }
                    ?.uppercase()
            state.value =
                state.value.copy(
                    targetMacAddress = normalized
                )
        }
    }

    override suspend fun setGsrRange(
        index: Int
    ) {
        mutex.withLock {
            val normalized =
                index.coerceIn(
                    0,
                    ShimmerSettings.DEFAULT_GSR_RANGE
                )
            state.value =
                state.value.copy(
                    gsrRangeIndex = normalized
                )
        }
    }

    override suspend fun setSampleRate(
        sampleRateHz: Double
    ) {
        mutex.withLock {
            val sanitized =
                sampleRateHz.takeIf { it.isFinite() && it > 0.0 }
                    ?: ShimmerSettings.DEFAULT_SAMPLE_RATE_HZ
            state.value =
                state.value.copy(
                    sampleRateHz = sanitized
                )
        }
    }
}
