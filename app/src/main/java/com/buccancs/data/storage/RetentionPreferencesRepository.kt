package com.buccancs.data.storage

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetentionPreferencesRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(
                    DATA_STORE_NAME
                )
            }
        )

    val preferences: Flow<RetentionPreferences> =
        dataStore.data.map { prefs ->
            RetentionPreferences(
                minFreeBytes = prefs[MIN_FREE_BYTES_KEY]
                    ?: DEFAULT_MIN_FREE_BYTES,
                maxSessions = prefs[MAX_SESSIONS_KEY]
                    ?: DEFAULT_MAX_SESSIONS,
                maxAgeDays = prefs[MAX_AGE_DAYS_KEY]
                    ?: DEFAULT_MAX_AGE_DAYS
            )
        }

    suspend fun update(
        minFreeBytes: Long,
        maxSessions: Int,
        maxAgeDays: Long
    ) {
        dataStore.edit { prefs ->
            prefs[MIN_FREE_BYTES_KEY] =
                minFreeBytes
            prefs[MAX_SESSIONS_KEY] =
                maxSessions
            prefs[MAX_AGE_DAYS_KEY] =
                maxAgeDays
        }
    }

    companion object {
        private const val DATA_STORE_NAME =
            "retention_prefs"
        private val MIN_FREE_BYTES_KEY =
            longPreferencesKey(
                "min_free_bytes"
            )
        private val MAX_SESSIONS_KEY =
            intPreferencesKey(
                "max_sessions"
            )
        private val MAX_AGE_DAYS_KEY =
            longPreferencesKey(
                "max_age_days"
            )
        private const val DEFAULT_MAX_SESSIONS =
            48
        private const val DEFAULT_MIN_FREE_BYTES =
            6L * 1024 * 1024 * 1024
        private const val DEFAULT_MAX_AGE_DAYS =
            30L
    }
}

data class RetentionPreferences(
    val minFreeBytes: Long,
    val maxSessions: Int,
    val maxAgeDays: Long
)
