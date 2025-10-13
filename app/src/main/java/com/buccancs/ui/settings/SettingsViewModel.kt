package com.buccancs.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.data.storage.RetentionPreferences
import com.buccancs.data.storage.RetentionPreferencesRepository
import com.buccancs.data.storage.SpaceMonitor
import com.buccancs.data.storage.SpaceState
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.buccancs.data.transfer.WorkPolicy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val orchestratorConfigRepository: OrchestratorConfigRepository,
    private val retentionPreferencesRepository: RetentionPreferencesRepository,
    spaceMonitor: SpaceMonitor
) : ViewModel() {
    private val hostInput = MutableStateFlow("")
    private val portInput = MutableStateFlow("")
    private val useTlsInput = MutableStateFlow(false)
    private val retentionMinFreeInput = MutableStateFlow("")
    private val retentionMaxSessionsInput = MutableStateFlow("")
    private val retentionMaxAgeDaysInput = MutableStateFlow("")
    private val message = MutableStateFlow<String?>(null)
    private val applying = MutableStateFlow(false)

    val uiState: StateFlow<SettingsUiState> = combine(
        hostInput,
        portInput,
        useTlsInput,
        retentionMinFreeInput,
        retentionMaxSessionsInput,
        retentionMaxAgeDaysInput,
        retentionPreferencesRepository.preferences,
        spaceMonitor.state,
        message,
        applying
    ) { host, port, tls, minFree, maxSessions, maxAge, retentionPrefs, space, msg, busy ->
        SettingsUiState(
            hostInput = host,
            portInput = port,
            useTls = tls,
            retentionMinFreeInput = minFree,
            retentionMaxSessionsInput = maxSessions,
            retentionMaxAgeDaysInput = maxAge,
            retentionDefaults = retentionPrefs,
            storageState = space,
            message = msg,
            isApplying = busy
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SettingsUiState.empty()
    )

    init {
        viewModelScope.launch {
            orchestratorConfigRepository.config.collect { config ->
                hostInput.value = config.host
                portInput.value = config.port.toString()
                useTlsInput.value = config.useTls
            }
        }
        viewModelScope.launch {
            retentionPreferencesRepository.preferences.collect { prefs ->
                if (retentionMinFreeInput.value.isBlank()) {
                    retentionMinFreeInput.value = bytesToGigabytes(prefs.minFreeBytes)
                }
                if (retentionMaxSessionsInput.value.isBlank()) {
                    retentionMaxSessionsInput.value = prefs.maxSessions.toString()
                }
                if (retentionMaxAgeDaysInput.value.isBlank()) {
                    retentionMaxAgeDaysInput.value = prefs.maxAgeDays.toString()
                }
            }
        }
    }

    fun onHostChanged(value: String) {
        hostInput.value = value.trim()
    }

    fun onPortChanged(value: String) {
        portInput.value = value.filter { it.isDigit() }
    }

    fun onUseTlsChanged(value: Boolean) {
        useTlsInput.value = value
    }

    fun onRetentionMinFreeChanged(value: String) {
        retentionMinFreeInput.value = value.filter { it.isDigit() || it == '.' }
    }

    fun onRetentionMaxSessionsChanged(value: String) {
        retentionMaxSessionsInput.value = value.filter { it.isDigit() }
    }

    fun onRetentionMaxAgeDaysChanged(value: String) {
        retentionMaxAgeDaysInput.value = value.filter { it.isDigit() }
    }

    fun clearMessage() {
        message.value = null
    }

    fun applyOrchestrator() {
        viewModelScope.launch {
            applying.value = true
            message.value = null
            val host = hostInput.value.trim()
            val port = portInput.value.toIntOrNull()
            if (host.isBlank() || port == null || port !in 1..65535) {
                message.value = "Enter a valid host and port."
                applying.value = false
                return@launch
            }
            val config = OrchestratorConfig(
                host = host,
                port = port,
                useTls = useTlsInput.value
            )
            runCatching { orchestratorConfigRepository.update(config) }
                .onSuccess { message.value = "Orchestrator settings saved." }
                .onFailure { message.value = it.message ?: "Unable to update orchestrator settings." }
            applying.value = false
        }
    }

    fun applyRetention(context: Context) {
        viewModelScope.launch {
            applying.value = true
            message.value = null
            val minFreeGb = retentionMinFreeInput.value.toDoubleOrNull()
            val maxSessions = retentionMaxSessionsInput.value.toIntOrNull()
            val maxAgeDays = retentionMaxAgeDaysInput.value.toLongOrNull()
            if (minFreeGb == null || maxSessions == null || maxAgeDays == null) {
                message.value = "Enter numeric retention values."
                applying.value = false
                return@launch
            }
            val prefs = RetentionPreferences(
                minFreeBytes = gigabytesToBytes(minFreeGb),
                maxSessions = maxSessions.coerceAtLeast(1),
                maxAgeDays = maxAgeDays.coerceAtLeast(1)
            )
            runCatching {
                retentionPreferencesRepository.update(
                    minFreeBytes = prefs.minFreeBytes,
                    maxSessions = prefs.maxSessions,
                    maxAgeDays = prefs.maxAgeDays
                )
                WorkPolicy.scheduleRetention(
                    context = context,
                    minFreeBytes = prefs.minFreeBytes,
                    maxSessions = prefs.maxSessions,
                    maxAgeDays = prefs.maxAgeDays
                )
            }.onSuccess {
                message.value = "Retention policy scheduled."
            }.onFailure { error ->
                message.value = error.message ?: "Unable to schedule retention."
            }
            applying.value = false
        }
    }

    private fun bytesToGigabytes(bytes: Long): String {
        val gb = bytes.toDouble() / (1024.0 * 1024.0 * 1024.0)
        return String.format(Locale.US, "%.2f", gb)
    }

    private fun gigabytesToBytes(gb: Double): Long {
        return (gb * 1024.0 * 1024.0 * 1024.0).toLong()
    }
}

data class SettingsUiState(
    val hostInput: String,
    val portInput: String,
    val useTls: Boolean,
    val retentionMinFreeInput: String,
    val retentionMaxSessionsInput: String,
    val retentionMaxAgeDaysInput: String,
    val retentionDefaults: RetentionPreferences,
    val storageState: SpaceState,
    val message: String?,
    val isApplying: Boolean
) {
    companion object {
        fun empty(): SettingsUiState = SettingsUiState(
            hostInput = "",
            portInput = "",
            useTls = false,
            retentionMinFreeInput = "",
            retentionMaxSessionsInput = "",
            retentionMaxAgeDaysInput = "",
            retentionDefaults = RetentionPreferences(
                minFreeBytes = 6L * 1024 * 1024 * 1024,
                maxSessions = 48,
                maxAgeDays = 30
            ),
            storageState = SpaceState.EMPTY,
            message = null,
            isApplying = false
        )
    }
}
