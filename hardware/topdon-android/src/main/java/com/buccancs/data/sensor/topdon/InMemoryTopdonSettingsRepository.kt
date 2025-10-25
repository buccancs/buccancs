package com.buccancs.data.sensor.topdon

import com.buccancs.domain.model.TopdonDynamicRange
import com.buccancs.domain.model.TopdonGainMode
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.model.TopdonTemperatureUnit
import com.buccancs.domain.repository.TopdonSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.max

class InMemoryTopdonSettingsRepository(
    initial: TopdonSettings = TopdonSettings()
) : TopdonSettingsRepository {
    private val mutex =
        Mutex()
    private val state =
        MutableStateFlow(
            initial
        )
    override val settings: StateFlow<TopdonSettings> =
        state.asStateFlow()

    override suspend fun setAutoConnect(
        enabled: Boolean
    ) {
        mutex.withLock {
            state.value =
                state.value.copy(
                    autoConnectOnAttach = enabled
                )
        }
    }

    override suspend fun setPalette(
        palette: TopdonPalette
    ) {
        mutex.withLock {
            state.value =
                state.value.copy(
                    palette = palette
                )
        }
    }

    override suspend fun setSuperSampling(
        superSampling: TopdonSuperSamplingFactor
    ) {
        mutex.withLock {
            state.value =
                state.value.copy(
                    superSampling = superSampling
                )
        }
    }

    override suspend fun setPreviewFpsLimit(
        limit: Int
    ) {
        mutex.withLock {
            val sanitized =
                max(
                    1,
                    limit
                )
            state.value =
                state.value.copy(
                    previewFpsLimit = sanitized
                )
        }
    }

    override suspend fun setEmissivity(
        emissivity: Double
    ) {
        mutex.withLock {
            val clamped = emissivity.coerceIn(0.01, 1.0)
            state.value =
                state.value.copy(
                    emissivity = clamped
                )
        }
    }

    override suspend fun setGainMode(
        mode: TopdonGainMode
    ) {
        mutex.withLock {
            state.value =
                state.value.copy(
                    gainMode = mode
                )
        }
    }

    override suspend fun setAutoShutter(
        enabled: Boolean
    ) {
        mutex.withLock {
            state.value =
                state.value.copy(
                    autoShutterEnabled = enabled
                )
        }
    }

    override suspend fun setDynamicRange(
        range: TopdonDynamicRange
    ) {
        mutex.withLock {
            state.value =
                state.value.copy(
                    dynamicRange = range
                )
        }
    }

    override suspend fun setDistanceMeters(
        distance: Double
    ) {
        mutex.withLock {
            val clamped =
                distance.coerceIn(
                    0.1,
                    20.0
                )
            state.value =
                state.value.copy(
                    distanceMeters = clamped
                )
        }
    }

    override suspend fun setTemperatureUnit(
        unit: TopdonTemperatureUnit
    ) {
        mutex.withLock {
            state.value =
                state.value.copy(
                    temperatureUnit = unit
                )
        }
    }

    override suspend fun setAmbientTemperatureCelsius(
        temperatureCelsius: Double
    ) {
        mutex.withLock {
            val clamped =
                temperatureCelsius.coerceIn(
                    -40.0,
                    100.0
                )
            state.value =
                state.value.copy(
                    ambientTemperatureCelsius = clamped
                )
        }
    }

    override suspend fun setAmbientHumidityPercent(
        humidityPercent: Double
    ) {
        mutex.withLock {
            val clamped =
                humidityPercent.coerceIn(
                    0.0,
                    100.0
                )
            state.value =
                state.value.copy(
                    ambientHumidityPercent = clamped
                )
        }
    }
}
