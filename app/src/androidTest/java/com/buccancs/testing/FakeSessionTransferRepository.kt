package com.buccancs.testing

import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadRecoveryRecord
import com.buccancs.domain.model.UploadStatus
import com.buccancs.domain.repository.SessionTransferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeSessionTransferRepository @Inject constructor() : SessionTransferRepository {
    private val _uploads = MutableStateFlow<List<UploadStatus>>(emptyList())
    private val _recovery = MutableStateFlow<List<UploadRecoveryRecord>>(emptyList())
    private val _backlog = MutableStateFlow(
        UploadBacklogState(
            level = UploadBacklogLevel.NORMAL,
            queuedCount = 0,
            queuedBytes = 0,
            message = null
        )
    )

    val recordedEnqueues: MutableList<Pair<String, List<SessionArtifact>>> = mutableListOf()

    override val uploads: StateFlow<List<UploadStatus>> = _uploads
    override val recovery: StateFlow<List<UploadRecoveryRecord>> = _recovery
    override val backlog: StateFlow<UploadBacklogState> = _backlog

    override suspend fun enqueue(sessionId: String, artifacts: List<SessionArtifact>) {
        recordedEnqueues += sessionId to artifacts
    }
}
