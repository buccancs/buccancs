package com.buccancs.data.sensor.topdon

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
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
        return TopdonSettings(
            autoConnectOnAttach = autoConnect,
            palette = palette,
            superSampling = superSampling,
            previewFpsLimit = fpsLimit.coerceIn(
                MIN_FPS_LIMIT,
                MAX_FPS_LIMIT
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
