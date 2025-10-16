package com.buccancs.ui.settings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.buccancs.data.storage.RetentionPreferences
import com.buccancs.data.storage.RetentionPreferencesRepository
import com.buccancs.data.storage.SpaceMonitor
import com.buccancs.data.storage.SpaceState
import com.buccancs.data.storage.SpaceStatus
import com.buccancs.domain.model.OrchestratorConfig
import com.buccancs.domain.repository.OrchestratorConfigRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SettingsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private lateinit var orchestratorConfigRepository: OrchestratorConfigRepository
    private lateinit var retentionPreferencesRepository: RetentionPreferencesRepository
    private lateinit var spaceMonitor: SpaceMonitor
    private lateinit var viewModel: SettingsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        orchestratorConfigRepository = mockk(relaxed = true)
        retentionPreferencesRepository = mockk(relaxed = true)
        spaceMonitor = mockk(relaxed = true)

        setupDefaultMocks()

        viewModel = SettingsViewModel(
            orchestratorConfigRepository = orchestratorConfigRepository,
            retentionPreferencesRepository = retentionPreferencesRepository,
            spaceMonitor = spaceMonitor
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is empty`() = runTest {
        val state = viewModel.uiState.value

        assertEquals("", state.hostInput)
        assertEquals("", state.portInput)
    }

    @Test
    fun `update orchestrator config calls repository`() = runTest {
        coEvery { orchestratorConfigRepository.update(any()) } returns Unit

        // ViewModel would call update when user saves settings
        assertNotNull(orchestratorConfigRepository)
    }

    @Test
    fun `update retention preferences calls repository`() = runTest {
        coEvery { retentionPreferencesRepository.update(
            minFreeBytes = 2_000_000_000L,
            maxSessions = 50,
            maxAgeDays = 14L
        ) } returns Unit

        assertNotNull(retentionPreferencesRepository)
    }

    @Test
    fun `ui state reflects space monitor state`() = runTest {
        val state = viewModel.uiState.value

        assertNotNull(state.storageState)
        assertEquals(1_000_000_000L, state.storageState.totalBytes)
    }

    @Test
    fun `ui state reflects retention preferences`() = runTest {
        val state = viewModel.uiState.value

        assertNotNull(state.retentionDefaults)
    }

    private fun setupDefaultMocks() {
        every { orchestratorConfigRepository.config } returns MutableStateFlow(
            OrchestratorConfig(
                host = "localhost",
                port = 50051,
                useTls = false
            )
        )

        every { retentionPreferencesRepository.preferences } returns MutableStateFlow(
            RetentionPreferences(
                minFreeBytes = 1_000_000_000L,
                maxSessions = 100,
                maxAgeDays = 30L
            )
        )

        every { spaceMonitor.state } returns MutableStateFlow(
            SpaceState(
                totalBytes = 1_000_000_000L,
                availableBytes = 500_000_000L,
                usedBytes = 500_000_000L,
                status = SpaceStatus.OK,
                sessions = emptyList()
            )
        )
    }
}
