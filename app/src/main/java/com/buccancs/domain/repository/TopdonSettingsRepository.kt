package com.buccancs.domain.repository

import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import kotlinx.coroutines.flow.StateFlow

interface TopdonSettingsRepository {
    val settings: StateFlow<TopdonSettings>
    suspend fun setAutoConnect(
        enabled: Boolean
    )

    suspend fun setPalette(
        palette: TopdonPalette
    )

    suspend fun setSuperSampling(
        superSampling: TopdonSuperSamplingFactor
    )

    suspend fun setPreviewFpsLimit(
        limit: Int
    )
}
