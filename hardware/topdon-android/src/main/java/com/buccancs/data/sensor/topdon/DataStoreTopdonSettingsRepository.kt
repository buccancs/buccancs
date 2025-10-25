package com.buccancs.data.sensor.topdon

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.TopdonDynamicRange
import com.buccancs.domain.model.TopdonGainMode
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.model.TopdonTemperatureUnit
import com.buccancs.domain.repository.TopdonSettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreTopdonSettingsRepository @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope scope: CoroutineScope
) : TopdonSettingsRepository {
    private val dataStore =
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            produceFile = {
                context.preferencesDataStoreFile(
                    STORE_NAME
                )
            },
            scope = scope + Dispatchers.IO
        )

    private val autoConnectKey =
        booleanPreferencesKey(
            "auto_connect"
        )
    private val paletteKey =
        stringPreferencesKey(
            "palette"
        )
    private val superSamplingKey =
        intPreferencesKey(
            "super_sampling"
        )
    private val previewFpsKey =
        intPreferencesKey(
            "preview_fps_limit"
        )
    private val emissivityKey =
        doublePreferencesKey(
            "emissivity"
        )
    private val gainModeKey =
        stringPreferencesKey(
            "gain_mode"
        )
    private val autoShutterKey =
        booleanPreferencesKey(
            "auto_shutter"
        )
    private val dynamicRangeKey =
        stringPreferencesKey(
            "dynamic_range"
        )
    private val distanceMetersKey =
        doublePreferencesKey(
            "distance_meters"
        )
    private val temperatureUnitKey =
        stringPreferencesKey(
            "temperature_unit"
        )
    private val ambientTemperatureKey =
        doublePreferencesKey(
            "ambient_temperature_c"
        )
    private val ambientHumidityKey =
        doublePreferencesKey(
            "ambient_humidity_percent"
        )

    override val settings: StateFlow<TopdonSettings> =
        dataStore.data
            .catch { throwable ->
                if (throwable is java.io.IOException) {
                    emit(
                        emptyPreferences()
                    )
                } else {
                    throw throwable
                }
            }
            .map { it.toSettings() }
            .stateIn(
                scope,
                SharingStarted.Eagerly,
                TopdonSettings()
            )

    override suspend fun setAutoConnect(
        enabled: Boolean
    ) {
        dataStore.edit { prefs ->
            prefs[autoConnectKey] =
                enabled
        }
    }

    override suspend fun setPalette(
        palette: TopdonPalette
    ) {
        dataStore.edit { prefs ->
            prefs[paletteKey] =
                palette.name
        }
    }

    override suspend fun setSuperSampling(
        superSampling: TopdonSuperSamplingFactor
    ) {
        dataStore.edit { prefs ->
            prefs[superSamplingKey] =
                superSampling.multiplier
        }
    }

    override suspend fun setPreviewFpsLimit(
        limit: Int
    ) {
        val sanitized =
            limit.coerceIn(
                MIN_FPS_LIMIT,
                MAX_FPS_LIMIT
            )
        dataStore.edit { prefs ->
            prefs[previewFpsKey] =
                sanitized
        }
    }

    override suspend fun setEmissivity(
        emissivity: Double
    ) {
        val clamped = emissivity.coerceIn(0.01, 1.0)
        dataStore.edit { prefs ->
            prefs[emissivityKey] =
                clamped
        }
    }

    override suspend fun setGainMode(
        mode: TopdonGainMode
    ) {
        dataStore.edit { prefs ->
            prefs[gainModeKey] =
                mode.name
        }
    }

    override suspend fun setAutoShutter(
        enabled: Boolean
    ) {
        dataStore.edit { prefs ->
            prefs[autoShutterKey] =
                enabled
        }
    }

    override suspend fun setDynamicRange(
        range: TopdonDynamicRange
    ) {
        dataStore.edit { prefs ->
            prefs[dynamicRangeKey] =
                range.name
        }
    }

    override suspend fun setDistanceMeters(
        distance: Double
    ) {
        val clamped =
            distance.coerceIn(
                0.1,
                20.0
            )
        dataStore.edit { prefs ->
            prefs[distanceMetersKey] =
                clamped
        }
    }

    override suspend fun setTemperatureUnit(
        unit: TopdonTemperatureUnit
    ) {
        dataStore.edit { prefs ->
            prefs[temperatureUnitKey] =
                unit.name
        }
    }

    override suspend fun setAmbientTemperatureCelsius(
        temperatureCelsius: Double
    ) {
        val clamped =
            temperatureCelsius.coerceIn(
                -40.0,
                100.0
            )
        dataStore.edit { prefs ->
            prefs[ambientTemperatureKey] =
                clamped
        }
    }

    override suspend fun setAmbientHumidityPercent(
        humidityPercent: Double
    ) {
        val clamped =
            humidityPercent.coerceIn(
                0.0,
                100.0
            )
        dataStore.edit { prefs ->
            prefs[ambientHumidityKey] =
                clamped
        }
    }

    private fun Preferences.toSettings(): TopdonSettings {
        val autoConnect =
            this[autoConnectKey]
                ?: TopdonSettings().autoConnectOnAttach
        val paletteName =
            this[paletteKey]
        val palette =
            paletteName?.let {
                runCatching {
                    TopdonPalette.valueOf(
                        it
                    )
                }.getOrNull()
            }
                ?: TopdonSettings().palette
        val superSamplingValue =
            this[superSamplingKey]
                ?: TopdonSettings().superSampling.multiplier
        val superSampling =
            TopdonSuperSamplingFactor.fromMultiplier(
                superSamplingValue
            )
        val fpsLimit =
            this[previewFpsKey]
                ?: TopdonSettings.DEFAULT_PREVIEW_FPS_LIMIT
        val emissivity =
            this[emissivityKey]
                ?: TopdonSettings.DEFAULT_EMISSIVITY
        val gainModeName =
            this[gainModeKey]
        val gainMode =
            gainModeName?.let {
                runCatching {
                    TopdonGainMode.valueOf(
                        it
                    )
                }.getOrNull()
            }
                ?: TopdonSettings().gainMode
        val autoShutter =
            this[autoShutterKey]
                ?: TopdonSettings().autoShutterEnabled
        val dynamicRangeName =
            this[dynamicRangeKey]
        val dynamicRange =
            dynamicRangeName?.let {
                runCatching {
                    TopdonDynamicRange.valueOf(it)
                }.getOrNull()
            } ?: TopdonSettings().dynamicRange
        val distanceMeters =
            this[distanceMetersKey]
                ?: TopdonSettings.DEFAULT_DISTANCE_METERS
        val temperatureUnitName =
            this[temperatureUnitKey]
        val temperatureUnit =
            temperatureUnitName?.let {
                runCatching {
                    TopdonTemperatureUnit.valueOf(it)
                }.getOrNull()
            }
                ?: TopdonTemperatureUnit.CELSIUS
        val ambientTempC =
            this[ambientTemperatureKey]
                ?: TopdonSettings.DEFAULT_AMBIENT_TEMPERATURE_C
        val ambientHumidity =
            this[ambientHumidityKey]
                ?: TopdonSettings.DEFAULT_AMBIENT_HUMIDITY_PERCENT
        return TopdonSettings(
            autoConnectOnAttach = autoConnect,
            palette = palette,
            superSampling = superSampling,
            previewFpsLimit = fpsLimit.coerceIn(
                MIN_FPS_LIMIT,
                MAX_FPS_LIMIT
            ),
            emissivity = emissivity.coerceIn(0.01, 1.0),
            gainMode = gainMode,
            autoShutterEnabled = autoShutter,
            dynamicRange = dynamicRange,
            distanceMeters = distanceMeters.coerceIn(
                0.1,
                20.0
            ),
            temperatureUnit = temperatureUnit,
            ambientTemperatureCelsius = ambientTempC.coerceIn(
                -40.0,
                100.0
            ),
            ambientHumidityPercent = ambientHumidity.coerceIn(
                0.0,
                100.0
            )
        )
    }

    private companion object {
        private const val STORE_NAME =
            "topdon_settings"
        private const val MIN_FPS_LIMIT =
            2
        private const val MAX_FPS_LIMIT =
            30
    }
}
