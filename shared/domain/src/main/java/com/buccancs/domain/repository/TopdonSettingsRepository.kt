package com.buccancs.domain.repository

import com.buccancs.domain.model.TopdonDynamicRange
import com.buccancs.domain.model.TopdonGainMode
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.model.TopdonTemperatureUnit
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

    suspend fun setEmissivity(
        emissivity: Double
    )

    suspend fun setGainMode(
        mode: TopdonGainMode
    )

    suspend fun setAutoShutter(
        enabled: Boolean
    )

    suspend fun setDynamicRange(
        range: TopdonDynamicRange
    )

    suspend fun setDistanceMeters(
        distance: Double
    )

    suspend fun setTemperatureUnit(
        unit: TopdonTemperatureUnit
    )

    suspend fun setAmbientTemperatureCelsius(
        temperatureCelsius: Double
    )

    suspend fun setAmbientHumidityPercent(
        humidityPercent: Double
    )
}
