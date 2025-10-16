package com.buccancs.ui.orchestrator

import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.repository.OrchestratorConfigRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class OrchestratorConfigViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: OrchestratorConfigRepository
    private lateinit var viewModel: OrchestratorConfigViewModel

    private val testConfig = OrchestratorConfig(
        host = "localhost",
        port = 8080,
        useTls = false
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        repository = mockk(relaxed = true)
        every { repository.config } returns MutableStateFlow(testConfig)

        viewModel = OrchestratorConfigViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState syncs with persisted config`() = runTest {
        // When
        advanceUntilIdle()
        val state = viewModel.uiState.first()

        // Then
        assertEquals("localhost", state.hostInput)
        assertEquals("8080", state.portInput)
        assertFalse(state.useTls)
        assertEquals(testConfig, state.currentConfig)
    }

    @Test
    fun `onHostChanged updates host input`() = runTest {
        // When
        viewModel.onHostChanged("192.168.1.1")
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertEquals("192.168.1.1", state.hostInput)
        assertNull(state.message)
    }

    @Test
    fun `onPortChanged updates port input`() = runTest {
        // When
        viewModel.onPortChanged("9090")
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertEquals("9090", state.portInput)
        assertNull(state.message)
    }

    @Test
    fun `onUseTlsChanged updates TLS flag`() = runTest {
        // When
        viewModel.onUseTlsChanged(true)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertTrue(state.useTls)
    }

    @Test
    fun `isDirty true when host changes`() = runTest {
        // When
        viewModel.onHostChanged("different-host")
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertTrue(state.isDirty)
    }

    @Test
    fun `isDirty true when port changes`() = runTest {
        // When
        viewModel.onPortChanged("9999")
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertTrue(state.isDirty)
    }

    @Test
    fun `applyConfig validates blank host`() = runTest {
        // Given
        viewModel.onHostChanged("")

        // When
        viewModel.applyConfig()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertEquals("Host cannot be blank", state.message)
        coVerify(exactly = 0) { repository.update(any()) }
    }

    @Test
    fun `applyConfig validates invalid port`() = runTest {
        // Given
        viewModel.onHostChanged("localhost")
        viewModel.onPortChanged("invalid")

        // When
        viewModel.applyConfig()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertEquals("Port must be between 1 and 65535", state.message)
    }

    @Test
    fun `applyConfig validates port range`() = runTest {
        // Given
        viewModel.onHostChanged("localhost")
        viewModel.onPortChanged("99999") // Out of range

        // When
        viewModel.applyConfig()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertEquals("Port must be between 1 and 65535", state.message)
    }

    @Test
    fun `applyConfig succeeds with valid inputs`() = runTest {
        // Given
        viewModel.onHostChanged("192.168.1.1")
        viewModel.onPortChanged("9090")
        viewModel.onUseTlsChanged(true)
        coEvery { repository.update(any()) } returns Unit

        // When
        viewModel.applyConfig()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertEquals("Configuration saved", state.message)
        coVerify {
            repository.update(
                OrchestratorConfig(
                    host = "192.168.1.1",
                    port = 9090,
                    useTls = true
                )
            )
        }
    }

    @Test
    fun `applyConfig handles failure`() = runTest {
        // Given
        viewModel.onHostChanged("localhost")
        viewModel.onPortChanged("8080")
        coEvery { repository.update(any()) } throws RuntimeException("Database error")

        // When
        viewModel.applyConfig()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertEquals("Database error", state.message)
    }

    @Test
    fun `clearMessage removes message from state`() = runTest {
        // Given - trigger a message
        viewModel.onHostChanged("")
        viewModel.applyConfig()
        advanceUntilIdle()

        var state = viewModel.uiState.first()
        assertEquals("Host cannot be blank", state.message)

        // When
        viewModel.clearMessage()
        advanceUntilIdle()

        // Then
        state = viewModel.uiState.first()
        assertNull(state.message)
    }
}
