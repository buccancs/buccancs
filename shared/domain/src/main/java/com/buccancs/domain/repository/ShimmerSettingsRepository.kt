package com.buccancs.domain.repository

import com.buccancs.domain.model.ShimmerSettings
import kotlinx.coroutines.flow.StateFlow

interface ShimmerSettingsRepository {
    val settings: StateFlow<ShimmerSettings>
    suspend fun setTargetMac(
        macAddress: String?
    )

    suspend fun setGsrRange(
        index: Int
    )

    suspend fun setSampleRate(
        sampleRateHz: Double
    )
}
