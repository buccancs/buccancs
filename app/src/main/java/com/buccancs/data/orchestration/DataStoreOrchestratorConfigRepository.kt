package com.buccancs.data.orchestration
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
import com.buccancs.BuildConfig
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.repository.OrchestratorConfigRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class DataStoreOrchestratorConfigRepository @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope scope: CoroutineScope
) : OrchestratorConfigRepository {
    private val dataStore = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
        scope = scope + Dispatchers.IO,
        produceFile = { context.preferencesDataStoreFile(STORE_NAME) }
    )
    private val defaults = OrchestratorConfig(
        host = BuildConfig.ORCHESTRATOR_HOST,
        port = BuildConfig.ORCHESTRATOR_PORT,
        useTls = BuildConfig.ORCHESTRATOR_USE_TLS
    )
    private val hostKey = stringPreferencesKey("host")
    private val portKey = intPreferencesKey("port")
    private val tlsKey = booleanPreferencesKey("use_tls")
    override val config: StateFlow<OrchestratorConfig> = dataStore.data
        .catch { ex ->
            if (ex is java.io.IOException) {
                emit(emptyPreferences())
            } else {
                throw ex
            }
        }
        .map { prefs -> prefs.toConfig() }
        .stateIn(scope, kotlinx.coroutines.flow.SharingStarted.Eagerly, defaults)
    override suspend fun update(config: OrchestratorConfig) {
        dataStore.edit { prefs ->
            prefs[hostKey] = config.host
            prefs[portKey] = config.port
            prefs[tlsKey] = config.useTls
        }
    }
    private fun Preferences.toConfig(): OrchestratorConfig = OrchestratorConfig(
        host = this[hostKey] ?: defaults.host,
        port = this[portKey] ?: defaults.port,
        useTls = this[tlsKey] ?: defaults.useTls
    )
    private companion object {
        const val STORE_NAME = "orchestrator_config"
    }
}
