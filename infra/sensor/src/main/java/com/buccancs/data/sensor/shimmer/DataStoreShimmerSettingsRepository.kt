package com.buccancs.data.sensor.shimmer

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.ShimmerSettings
import com.buccancs.domain.repository.ShimmerSettingsRepository
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
class DataStoreShimmerSettingsRepository @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope scope: CoroutineScope
) : ShimmerSettingsRepository {
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

    private val macKey =
        stringPreferencesKey(
            "target_mac"
        )
    private val rangeKey =
        intPreferencesKey(
            "gsr_range"
        )
    private val sampleRateKey =
        doublePreferencesKey(
            "sample_rate_hz"
        )

    override val settings: StateFlow<ShimmerSettings> =
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
                ShimmerSettings()
            )

    override suspend fun setTargetMac(
        macAddress: String?
    ) {
        dataStore.edit { prefs ->
            if (macAddress.isNullOrBlank()) {
                prefs.remove(
                    macKey
                )
            } else {
                prefs[macKey] =
                    macAddress.uppercase()
            }
        }
    }

    override suspend fun setGsrRange(
        index: Int
    ) {
        val normalized =
            index.coerceIn(
                0,
                MAX_GSR_RANGE_INDEX
            )
        dataStore.edit { prefs ->
            prefs[rangeKey] =
                normalized
        }
    }

    override suspend fun setSampleRate(
        sampleRateHz: Double
    ) {
        val sanitized =
            sampleRateHz.takeIf { it.isFinite() && it > 0.0 }
                ?: ShimmerSettings.DEFAULT_SAMPLE_RATE_HZ
        dataStore.edit { prefs ->
            prefs[sampleRateKey] =
                sanitized
        }
    }

    private fun Preferences.toSettings(): ShimmerSettings =
        ShimmerSettings(
            targetMacAddress = this[macKey],
            gsrRangeIndex = this[rangeKey]
                ?: ShimmerSettings.DEFAULT_GSR_RANGE,
            sampleRateHz = this[sampleRateKey]
                ?: ShimmerSettings.DEFAULT_SAMPLE_RATE_HZ
        )

    private companion object {
        private const val STORE_NAME =
            "shimmer_settings"
        private const val MAX_GSR_RANGE_INDEX =
            4
    }
}
