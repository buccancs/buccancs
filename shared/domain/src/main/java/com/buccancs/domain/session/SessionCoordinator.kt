package com.buccancs.domain.session

import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Instant

/**
 * Coordinates the recording session lifecycle across agents/devices.
 *
 * Implementations live in platform layers (Android, Desktop) and bridge into
 * infrastructure services such as recording pipelines or time sync clients.
 */
interface SessionCoordinator {
    val sessionState: StateFlow<SessionState>

    suspend fun startSession(
        sessionId: String,
        requestedStart: Instant?
    ): Result<Unit>

    suspend fun stopSession(): Result<Unit>

    fun generateSessionId(): String
}

data class SessionState(
    val currentSessionId: String,
    val isBusy: Boolean,
    val lastError: String?
)
