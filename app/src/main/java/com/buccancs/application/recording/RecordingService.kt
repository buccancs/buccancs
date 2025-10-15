package com.buccancs.application.recording

import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SessionArtifact
import kotlin.time.Instant

interface RecordingService {
    suspend fun startOrResume(sessionId: String, requestedStart: Instant? = null): RecordingState
    suspend fun stop(): RecordingState
    suspend fun stopWithSummary(): RecordingStopSummary
}

data class RecordingStopSummary(
    val state: RecordingState,
    val anchor: RecordingSessionAnchor?,
    val artifacts: List<SessionArtifact>
)

