package com.buccancs.data.transfer

import com.buccancs.core.serialization.StandardJson
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BacklogTelemetryLogger @Inject constructor(
    private val storage: RecordingStorage,
    @StandardJson private val json: Json
) {

    suspend fun append(
        state: UploadBacklogState
    ) {
        if (state.level == UploadBacklogLevel.NORMAL) return
        val entries =
            buildEntries(
                state
            )
        if (entries.isEmpty()) return
        withContext(
            Dispatchers.IO
        ) {
            entries.forEach { entry ->
                val file =
                    storage.backlogTelemetryFile(
                        entry.sessionId
                    )
                file.parentFile?.mkdirs()
                file.appendText(
                    json.encodeToString(
                        entry
                    ) + "\n"
                )
            }
        }
    }

    private fun buildEntries(
        state: UploadBacklogState
    ): List<Entry> {
        if (state.perSessionQueued.isEmpty()) {
            return emptyList()
        }
        return state.perSessionQueued.entries.map { (sessionId, count) ->
            Entry(
                sessionId = sessionId,
                level = state.level.name,
                queuedCount = count,
                queuedBytes = state.perSessionBytes[sessionId]
                    ?: 0L,
                message = state.message
            )
        }
    }

    @Serializable
    private data class Entry(
        val sessionId: String,
        val level: String,
        val queuedCount: Int,
        val queuedBytes: Long,
        val message: String?
    )
}
