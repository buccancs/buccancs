package com.buccancs.domain.repository

import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadRecoveryRecord
import com.buccancs.domain.model.UploadStatus
import kotlinx.coroutines.flow.StateFlow

interface SessionTransferRepository {
    val uploads: StateFlow<List<UploadStatus>>
    val recovery: StateFlow<List<UploadRecoveryRecord>>
    val backlog: StateFlow<UploadBacklogState>
    suspend fun enqueue(
        sessionId: String,
        artifacts: List<SessionArtifact>
    )
}
