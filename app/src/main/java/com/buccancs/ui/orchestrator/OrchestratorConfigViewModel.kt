package com.buccancs.ui.orchestrator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.repository.OrchestratorConfigRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for orchestrator connection configuration.
 * Extracted from MainViewModel to focus on orchestrator configuration concerns.
 */
@HiltViewModel
class OrchestratorConfigViewModel @Inject constructor(
    private val orchestratorConfigRepository: OrchestratorConfigRepository
) : ViewModel() {

    private val hostInput = MutableStateFlow("")
    private val portInput = MutableStateFlow("")
    private val useTlsInput = MutableStateFlow(false)
    private val configMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<OrchestratorConfigUiState> = combine(
        hostInput,
        portInput,
        useTlsInput,
        orchestratorConfigRepository.config,
        configMessage
    ) { host, portText, useTls, persistedConfig, message ->
        val parsedPort = portText.toIntOrNull()
        val configDirty = persistedConfig?.let {
            host != it.host ||
                    parsedPort != it.port ||
                    useTls != it.useTls
        } ?: (host.isNotBlank() || portText.isNotBlank())

        OrchestratorConfigUiState(
            hostInput = host,
            portInput = portText,
            useTls = useTls,
            currentConfig = persistedConfig,
            isDirty = configDirty,
            message = message
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = OrchestratorConfigUiState.initial()
    )

    init {
        // Sync inputs with persisted config
        viewModelScope.launch {
            orchestratorConfigRepository.config.collect { config ->
                if (!inputsDirty()) {
                    hostInput.value = config.host
                    portInput.value = config.port.toString()
                    useTlsInput.value = config.useTls
                }
            }
        }
    }

    fun onHostChanged(value: String) {
        hostInput.value = value.trim()
        configMessage.value = null
    }

    fun onPortChanged(value: String) {
        portInput.value = value.trim()
        configMessage.value = null
    }

    fun onUseTlsChanged(enabled: Boolean) {
        useTlsInput.value = enabled
        configMessage.value = null
    }

    fun applyConfig() {
        viewModelScope.launch {
            val host = hostInput.value.trim()
            val portValue = portInput.value.trim()
            val port = portValue.toIntOrNull()
            
            if (host.isEmpty()) {
                configMessage.value = "Host cannot be blank"
                return@launch
            }
            if (port == null || port !in 1..65535) {
                configMessage.value = "Port must be between 1 and 65535"
                return@launch
            }
            
            runCatching {
                orchestratorConfigRepository.update(
                    OrchestratorConfig(
                        host = host,
                        port = port,
                        useTls = useTlsInput.value
                    )
                )
            }.onSuccess {
                configMessage.value = "Configuration saved"
            }.onFailure { ex ->
                configMessage.value = ex.message ?: "Unable to save configuration"
            }
        }
    }

    fun clearMessage() {
        configMessage.value = null
    }

    private fun inputsDirty(): Boolean {
        val config = uiState.value.currentConfig ?: return false
        val port = portInput.value.toIntOrNull()
        return hostInput.value != config.host ||
                port == null ||
                port != config.port ||
                useTlsInput.value != config.useTls
    }
}

data class OrchestratorConfigUiState(
    val hostInput: String,
    val portInput: String,
    val useTls: Boolean,
    val currentConfig: OrchestratorConfig?,
    val isDirty: Boolean,
    val message: String?
) {
    companion object {
        fun initial(): OrchestratorConfigUiState = OrchestratorConfigUiState(
            hostInput = "",
            portInput = "",
            useTls = false,
            currentConfig = null,
            isDirty = false,
            message = null
        )
    }
}
