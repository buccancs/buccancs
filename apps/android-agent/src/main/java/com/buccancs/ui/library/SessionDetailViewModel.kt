package com.buccancs.ui.library

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.data.recording.manifest.SessionManifest
import com.buccancs.data.storage.RecordingStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class SessionDetailViewModel @Inject constructor(
    private val storage: RecordingStorage,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val json =
        Json {
            ignoreUnknownKeys =
                true; prettyPrint =
            true
        }
    private val sessionId: String =
        savedStateHandle[SESSION_ID_KEY]
            ?: ""
    private val _state =
        MutableStateFlow(
            SessionDetailUiState(
                isLoading = true,
                sessionId = sessionId,
                manifest = null,
                totalBytes = 0,
                errorMessage = null
            )
        )
    val state: StateFlow<SessionDetailUiState> =
        _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value =
                _state.value.copy(
                    isLoading = true,
                    errorMessage = null
                )
            val result =
                runCatching { loadManifest() }
            _state.value =
                result.fold(
                    onSuccess = { manifest ->
                        val sessionDir =
                            storage.sessionDirectory(
                                sessionId
                            )
                        val bytes =
                            storage.directorySize(
                                sessionDir
                            )
                        SessionDetailUiState(
                            isLoading = false,
                            sessionId = sessionId,
                            manifest = manifest,
                            totalBytes = bytes,
                            errorMessage = null
                        )
                    },
                    onFailure = { error ->
                        SessionDetailUiState(
                            isLoading = false,
                            sessionId = sessionId,
                            manifest = null,
                            totalBytes = 0,
                            errorMessage = error.message
                                ?: "Unable to load manifest"
                        )
                    }
                )
        }
    }

    private suspend fun loadManifest(): SessionManifest =
        withContext(
            Dispatchers.IO
        ) {
            val manifestFile =
                storage.manifestFile(
                    sessionId
                )
            require(
                manifestFile.exists()
            ) { "Missing manifest for session $sessionId" }
            json.decodeFromString(
                SessionManifest.serializer(),
                manifestFile.readText()
            )
        }

    companion object {
        const val SESSION_ID_KEY =
            "sessionId"
    }
}

data class SessionDetailUiState(
    val isLoading: Boolean,
    val sessionId: String,
    val manifest: SessionManifest?,
    val totalBytes: Long,
    val errorMessage: String?
) {
    val artifactCount: Int
        get() = manifest?.artifacts?.size
            ?: 0
}

