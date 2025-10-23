package com.buccancs.data.bookmarks

import com.buccancs.domain.model.RecordingBookmark
import com.buccancs.domain.repository.BookmarkRepository
import com.buccancs.util.nowInstant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class DefaultBookmarkRepository @Inject constructor() :
    BookmarkRepository {
    private val _bookmarks =
        MutableStateFlow<List<RecordingBookmark>>(
            emptyList()
        )
    override val bookmarks: StateFlow<List<RecordingBookmark>> =
        _bookmarks.asStateFlow()

    override suspend fun add(
        label: String,
        timestamp: Instant
    ) {
        val trimmed =
            label.trim()
                .ifBlank { DEFAULT_LABEL }
        val bookmark =
            RecordingBookmark(
                id = UUID.randomUUID()
                    .toString(),
                label = trimmed,
                timestamp = timestamp,
                createdAt = nowInstant()
            )
        _bookmarks.update { current -> (current + bookmark).sortedBy { it.timestamp } }
    }

    override suspend fun remove(
        bookmarkId: String
    ) {
        _bookmarks.update { current -> current.filterNot { it.id == bookmarkId } }
    }

    override suspend fun clear() {
        _bookmarks.value =
            emptyList()
    }

    private companion object {
        private const val DEFAULT_LABEL =
            "Bookmark"
    }
}
