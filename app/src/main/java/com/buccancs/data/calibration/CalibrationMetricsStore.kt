package com.buccancs.data.calibration

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.buccancs.domain.model.CalibrationMetrics
import com.buccancs.domain.model.CalibrationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class CalibrationMetricsStore @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile(STORE_NAME) }
    )

    val metrics: Flow<CalibrationMetrics?> = dataStore.data.map { preferences ->
        val generatedAt = preferences[KEY_GENERATED_AT] ?: return@map null
        val meanError = preferences[KEY_MEAN_ERROR] ?: return@map null
        val maxError = preferences[KEY_MAX_ERROR] ?: return@map null
        val usedPairs = preferences[KEY_USED_PAIRS] ?: return@map null
        val requiredPairs = preferences[KEY_REQUIRED_PAIRS] ?: return@map null
        CalibrationMetrics(
            generatedAt = Instant.fromEpochMilliseconds(generatedAt),
            meanReprojectionError = meanError,
            maxReprojectionError = maxError,
            usedPairs = usedPairs,
            requiredPairs = requiredPairs
        )
    }

    suspend fun update(result: CalibrationResult) {
        dataStore.edit { preferences ->
            preferences[KEY_GENERATED_AT] = result.generatedAt.toEpochMilliseconds()
            preferences[KEY_MEAN_ERROR] = result.meanReprojectionError
            preferences[KEY_MAX_ERROR] =
                result.perViewErrors.maxOrNull() ?: result.meanReprojectionError
            preferences[KEY_USED_PAIRS] = result.usedPairs
            preferences[KEY_REQUIRED_PAIRS] = result.requiredPairs
        }
    }

    companion object {
        private const val STORE_NAME = "calibration_metrics.preferences_pb"
        private val KEY_GENERATED_AT = longPreferencesKey("generated_at_epoch_ms")
        private val KEY_MEAN_ERROR = doublePreferencesKey("mean_reprojection_error")
        private val KEY_MAX_ERROR = doublePreferencesKey("max_reprojection_error")
        private val KEY_USED_PAIRS = intPreferencesKey("used_pairs")
        private val KEY_REQUIRED_PAIRS = intPreferencesKey("required_pairs")
    }
}
