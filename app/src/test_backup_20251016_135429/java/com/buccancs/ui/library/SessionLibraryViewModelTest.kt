package com.buccancs.ui.library

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buccancs.data.recording.manifest.SessionManifest
import com.buccancs.data.storage.RecordingStorage
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Test for SessionLibraryViewModel.
 * Tests session manifest loading and display logic.
 */
class SessionLibraryViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private lateinit var storage: RecordingStorage
    private lateinit var viewModel: SessionLibraryViewModel
    private val json = Json { ignoreUnknownKeys = true }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        storage = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state shows loading`() = runTest {
        every { storage.sessionIds() } returns emptyList()
        
        viewModel = SessionLibraryViewModel(storage)
        
        val initialState = viewModel.state.value
        assertTrue(initialState.isLoading, "Should be loading initially")
    }

    @Test
    fun `loads empty session list successfully`() = runTest {
        every { storage.sessionIds() } returns emptyList()
        
        viewModel = SessionLibraryViewModel(storage)
        dispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.state.first()
        assertFalse(state.isLoading)
        assertEquals(0, state.sessions.size)
        assertEquals(null, state.errorMessage)
    }

    @Test
    fun `loads session summaries from storage`() = runTest {
        val sessionId = "test-session-123"
        val manifestFile = mockk<File>()
        val sessionDir = mockk<File>()
        
        val manifest = SessionManifest(
            sessionId = sessionId,
            startedAt = "2024-01-01T10:00:00Z",
            startedAtEpochMs = 1000000L,
            simulation = false,
            orchestratorOffsetMillis = 0,
            devices = emptyList(),
            endedAtEpochMs = 1005000L,
            durationMillis = 5000L,
            artifacts = emptyList()
        )
        
        every { storage.sessionIds() } returns listOf(sessionId)
        every { storage.manifestFile(sessionId) } returns manifestFile
        every { storage.sessionDirectory(sessionId) } returns sessionDir
        every { storage.directorySize(sessionDir) } returns 1024L
        every { manifestFile.exists() } returns true
        every { manifestFile.readText() } returns json.encodeToString(manifest)
        
        viewModel = SessionLibraryViewModel(storage)
        dispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.state.first()
        assertFalse(state.isLoading)
        assertEquals(1, state.sessions.size)
        
        val summary = state.sessions.first()
        assertEquals(sessionId, summary.sessionId)
        assertEquals(5000L, summary.durationMillis)
        assertEquals(1024L, summary.totalBytes)
        assertFalse(summary.simulation)
    }

    @Test
    fun `handles missing manifest file gracefully`() = runTest {
        val sessionId = "missing-manifest"
        val manifestFile = mockk<File>()
        
        every { storage.sessionIds() } returns listOf(sessionId)
        every { storage.manifestFile(sessionId) } returns manifestFile
        every { manifestFile.exists() } returns false
        
        viewModel = SessionLibraryViewModel(storage)
        dispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.state.first()
        assertFalse(state.isLoading)
        assertEquals(0, state.sessions.size, "Should skip sessions with missing manifests")
    }

    @Test
    fun `sorts sessions by start time descending`() = runTest {
        val session1Id = "session-1"
        val session2Id = "session-2"
        val manifest1File = mockk<File>()
        val manifest2File = mockk<File>()
        val dir1 = mockk<File>()
        val dir2 = mockk<File>()
        
        val manifest1 = SessionManifest(
            sessionId = session1Id,
            startedAt = "2024-01-01T10:00:00Z",
            startedAtEpochMs = 1000000L,
            simulation = false,
            orchestratorOffsetMillis = 0,
            devices = emptyList(),
            endedAtEpochMs = null,
            durationMillis = null,
            artifacts = emptyList()
        )
        
        val manifest2 = SessionManifest(
            sessionId = session2Id,
            startedAt = "2024-01-01T11:00:00Z",
            startedAtEpochMs = 2000000L, // Later than session1
            simulation = false,
            orchestratorOffsetMillis = 0,
            devices = emptyList(),
            endedAtEpochMs = null,
            durationMillis = null,
            artifacts = emptyList()
        )
        
        every { storage.sessionIds() } returns listOf(session1Id, session2Id)
        every { storage.manifestFile(session1Id) } returns manifest1File
        every { storage.manifestFile(session2Id) } returns manifest2File
        every { storage.sessionDirectory(session1Id) } returns dir1
        every { storage.sessionDirectory(session2Id) } returns dir2
        every { storage.directorySize(any()) } returns 0L
        every { manifest1File.exists() } returns true
        every { manifest2File.exists() } returns true
        every { manifest1File.readText() } returns json.encodeToString(manifest1)
        every { manifest2File.readText() } returns json.encodeToString(manifest2)
        
        viewModel = SessionLibraryViewModel(storage)
        dispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.state.first()
        assertEquals(2, state.sessions.size)
        assertEquals(session2Id, state.sessions[0].sessionId, "Newer session should be first")
        assertEquals(session1Id, state.sessions[1].sessionId, "Older session should be second")
    }

    @Test
    fun `refresh reloads session data`() = runTest {
        every { storage.sessionIds() } returns emptyList()
        
        viewModel = SessionLibraryViewModel(storage)
        dispatcher.scheduler.advanceUntilIdle()
        
        val sessionId = "new-session"
        val manifestFile = mockk<File>()
        val sessionDir = mockk<File>()
        val manifest = SessionManifest(
            sessionId = sessionId,
            startedAt = "2024-01-01T10:00:00Z",
            startedAtEpochMs = 1000000L,
            simulation = false,
            orchestratorOffsetMillis = 0,
            devices = emptyList(),
            endedAtEpochMs = null,
            durationMillis = null,
            artifacts = emptyList()
        )
        
        every { storage.sessionIds() } returns listOf(sessionId)
        every { storage.manifestFile(sessionId) } returns manifestFile
        every { storage.sessionDirectory(sessionId) } returns sessionDir
        every { storage.directorySize(sessionDir) } returns 512L
        every { manifestFile.exists() } returns true
        every { manifestFile.readText() } returns json.encodeToString(manifest)
        
        viewModel.refresh()
        dispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.state.first()
        assertEquals(1, state.sessions.size)
        assertEquals(sessionId, state.sessions.first().sessionId)
    }
}
