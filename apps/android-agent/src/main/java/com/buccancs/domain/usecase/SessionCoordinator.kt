package com.buccancs.domain.usecase

import com.buccancs.application.recording.RecordingService
import com.buccancs.domain.time.TimeSyncService
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.session.SessionCoordinator
import com.buccancs.domain.session.SessionState
import com.buccancs.util.nowInstant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class SessionCoordinatorImpl @Inject constructor(
    private val recordingService: RecordingService,
    private val timeSyncService: TimeSyncService,
    private val sensorRepository: SensorRepository
) : SessionCoordinator {

    private val _sessionState =
        MutableStateFlow(
            SessionState(
                currentSessionId = generateSessionId(),
                isBusy = false,
                lastError = null
            )
        )
    override val sessionState: StateFlow<SessionState> =
        _sessionState.asStateFlow()

    override suspend fun startSession(
        sessionId: String,
        requestedStart: Instant?
    ): Result<Unit> {
        if (_sessionState.value.isBusy) {
            return Result.failure(
                IllegalStateException(
                    "Session coordinator is busy"
                )
            )
        }

        _sessionState.value =
            _sessionState.value.copy(
                isBusy = true,
                lastError = null
            )

        return try {
            val effectiveSessionId =
                sessionId.ifBlank { generateSessionId() }
            recordingService.startOrResume(
                effectiveSessionId,
                requestedStart
            )

            _sessionState.value =
                _sessionState.value.copy(
                    currentSessionId = effectiveSessionId,
                    isBusy = false,
                    lastError = null
                )
            Result.success(
                Unit
            )
        } catch (t: Throwable) {
            val errorMessage =
                t.message
                    ?: "Failed to start recording"
            _sessionState.value =
                _sessionState.value.copy(
                    isBusy = false,
                    lastError = errorMessage
                )
            Result.failure(
                t
            )
        }
    }

    override suspend fun stopSession(): Result<Unit> {
        if (_sessionState.value.isBusy) {
            return Result.failure(
                IllegalStateException(
                    "Session coordinator is busy"
                )
            )
        }

        _sessionState.value =
            _sessionState.value.copy(
                isBusy = true,
                lastError = null
            )

        return try {
            recordingService.stop()
            _sessionState.value =
                _sessionState.value.copy(
                    isBusy = false,
                    lastError = null
                )
            Result.success(
                Unit
            )
        } catch (t: Throwable) {
            val errorMessage =
                t.message
                    ?: "Failed to stop recording"
            _sessionState.value =
                _sessionState.value.copy(
                    isBusy = false,
                    lastError = errorMessage
                )
            Result.failure(
                t
            )
        }
    }

    override fun generateSessionId(): String {
        val now =
            nowInstant()
        return "session-${now.epochSeconds}"
    }

    fun updateSessionId(
        sessionId: String
    ) {
        _sessionState.value =
            _sessionState.value.copy(
                currentSessionId = sessionId
            )
    }

    fun clearError() {
        _sessionState.value =
            _sessionState.value.copy(
                lastError = null
            )
    }
}
