@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.buccancs.domain.repository

import com.buccancs.domain.model.RecordingBookmark
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Instant

interface BookmarkRepository {
    val bookmarks: StateFlow<List<RecordingBookmark>>
    suspend fun add(
        label: String,
        timestamp: Instant
    )

    suspend fun remove(
        bookmarkId: String
    )

    suspend fun clear()
}
