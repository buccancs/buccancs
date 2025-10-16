package com.buccancs.data.bookmarks

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DefaultBookmarkRepositoryTest {
    private lateinit var repository: DefaultBookmarkRepository

    @Before
    fun setup() {
        repository = DefaultBookmarkRepository()
    }

    @Test
    fun `add bookmark with label`() = runTest {
        val timestamp = Clock.System.now()

        repository.add("Test Bookmark", timestamp)

        val bookmarks = repository.bookmarks.value
        assertEquals(1, bookmarks.size)
        assertEquals("Test Bookmark", bookmarks[0].label)
        assertEquals(timestamp, bookmarks[0].timestamp)
    }

    @Test
    fun `add bookmark with blank label uses default`() = runTest {
        val timestamp = Clock.System.now()

        repository.add("  ", timestamp)

        val bookmarks = repository.bookmarks.value
        assertEquals(1, bookmarks.size)
        assertEquals("Bookmark", bookmarks[0].label)
    }

    @Test
    fun `add bookmark trims whitespace`() = runTest {
        val timestamp = Clock.System.now()

        repository.add("  Padded Label  ", timestamp)

        val bookmarks = repository.bookmarks.value
        assertEquals("Padded Label", bookmarks[0].label)
    }

    @Test
    fun `bookmarks are sorted by timestamp`() = runTest {
        val time1 = Clock.System.now()
        val time2 = Clock.System.now()
        val time3 = Clock.System.now()

        repository.add("Second", time2)
        repository.add("First", time1)
        repository.add("Third", time3)

        val bookmarks = repository.bookmarks.value
        assertEquals(3, bookmarks.size)
        assertEquals("First", bookmarks[0].label)
        assertEquals("Second", bookmarks[1].label)
        assertEquals("Third", bookmarks[2].label)
    }

    @Test
    fun `remove bookmark by id`() = runTest {
        val timestamp = Clock.System.now()
        repository.add("Test", timestamp)
        val bookmarkId = repository.bookmarks.value[0].id

        repository.remove(bookmarkId)

        assertTrue(repository.bookmarks.value.isEmpty())
    }

    @Test
    fun `clear removes all bookmarks`() = runTest {
        repository.add("First", Clock.System.now())
        repository.add("Second", Clock.System.now())
        repository.add("Third", Clock.System.now())

        repository.clear()

        assertTrue(repository.bookmarks.value.isEmpty())
    }

    @Test
    fun `bookmarks have unique IDs`() = runTest {
        val timestamp = Clock.System.now()
        repository.add("First", timestamp)
        repository.add("Second", timestamp)

        val ids = repository.bookmarks.value.map { it.id }
        assertEquals(2, ids.distinct().size)
    }
}
