package com.buccancs.desktop.data.erasure

import com.buccancs.desktop.data.repository.SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class SubjectErasureManager(
    private val sessionRepository: SessionRepository
) {
    private val logger = LoggerFactory.getLogger(SubjectErasureManager::class.java)

    suspend fun eraseSession(sessionId: String): SubjectErasureResult = withContext(Dispatchers.IO) {
        sessionRepository.eraseSession(sessionId)
        logger.info("Session {} erased per request", sessionId)
        SubjectErasureResult(subjectId = null, erasedSessions = listOf(sessionId))
    }

    suspend fun eraseSubject(subjectId: String): SubjectErasureResult = withContext(Dispatchers.IO) {
        val sessionIds = sessionRepository.listSessionIds()
        val erased = mutableListOf<String>()
        sessionIds.forEach { id ->
            val metadata = sessionRepository.metadataFor(id) ?: return@forEach
            if (metadata.subjectIds.contains(subjectId)) {
                sessionRepository.eraseSession(id)
                erased += id
                logger.info("Erased session {} for subject {}", id, subjectId)
            }
        }
        SubjectErasureResult(subjectId = subjectId, erasedSessions = erased)
    }
}

data class SubjectErasureResult(
    val subjectId: String?,
    val erasedSessions: List<String>
)
