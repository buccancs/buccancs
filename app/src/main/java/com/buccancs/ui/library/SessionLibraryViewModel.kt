package com.buccancs.ui.library

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
import java.io.File
import java.util.*
import javax.inject.Inject
import kotlin.time.Instant

@HiltViewModel
class SessionLibraryViewModel @Inject constructor(
    private val storage: RecordingStorage
) : ViewModel() {
    private val json =
        Json {
            ignoreUnknownKeys =
                true; prettyPrint =
            false
        }
    private val _state =
        MutableStateFlow(
            SessionLibraryUiState(
                isLoading = true,
                sessions = emptyList(),
                errorMessage = null
            )
        )
    val state: StateFlow<SessionLibraryUiState> =
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
                runCatching { loadSessions() }
            _state.value =
                result.fold(
                    onSuccess = { summaries ->
                        SessionLibraryUiState(
                            isLoading = false,
                            sessions = summaries,
                            errorMessage = null
                        )
                    },
                    onFailure = { error ->
                        SessionLibraryUiState(
                            isLoading = false,
                            sessions = emptyList(),
                            errorMessage = error.message
                                ?: "Unable to load sessions"
                        )
                    }
                )
        }
    }

    private suspend fun loadSessions(): List<SessionSummary> =
        withContext(
            Dispatchers.IO
        ) {
            val sessionIds =
                storage.sessionIds()
            val summaries =
                mutableListOf<SessionSummary>()
            sessionIds.forEach { sessionId ->
                val manifestFile =
                    storage.manifestFile(
                        sessionId
                    )
                val manifest =
                    parseManifest(
                        manifestFile
                    )
                        ?: return@forEach
                val directory =
                    storage.sessionDirectory(
                        sessionId
                    )
                val sizeBytes =
                    storage.directorySize(
                        directory
                    )
                summaries += SessionSummary(
                    sessionId = sessionId,
                    startedAt = Instant.fromEpochMilliseconds(
                        manifest.startedAtEpochMs
                    ),
                    endedAt = manifest.endedAtEpochMs?.let {
                        Instant.fromEpochMilliseconds(
                            it
                        )
                    },
                    durationMillis = manifest.durationMillis,
                    artifactCount = manifest.artifacts.size,
                    totalBytes = sizeBytes,
                    simulation = manifest.simulation
                )
            }
            summaries.sortedByDescending { it.startedAt }
        }

    private fun parseManifest(
        file: File
    ): SessionManifest? {
        if (!file.exists()) return null
        return runCatching {
            json.decodeFromString(
                SessionManifest.serializer(),
                file.readText()
            )
        }
            .getOrNull()
    }
}

data class SessionLibraryUiState(
    val isLoading: Boolean,
    val sessions: List<SessionSummary>,
    val errorMessage: String?
) {
    companion object {
        fun empty(): SessionLibraryUiState =
            SessionLibraryUiState(
                isLoading = false,
                sessions = emptyList(),
                errorMessage = null
            )
    }
}

data class SessionSummary(
    val sessionId: String,
    val startedAt: Instant,
    val endedAt: Instant?,
    val durationMillis: Long?,
    val artifactCount: Int,
    val totalBytes: Long,
    val simulation: Boolean
) {
    val durationFormatted: String
        get() = durationMillis?.let { millis ->
            val seconds =
                millis / 1000
            val hours =
                seconds / 3600
            val minutes =
                (seconds % 3600) / 60
            val remaining =
                seconds % 60
            String.format(
                Locale.US,
                "%02d:%02d:%02d",
                hours,
                minutes,
                remaining
            )
        }
            ?: "n/a"
}

