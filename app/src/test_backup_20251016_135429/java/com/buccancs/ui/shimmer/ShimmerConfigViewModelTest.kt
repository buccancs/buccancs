package com.buccancs.ui.shimmer

import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.usecase.HardwareConfigurationUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class ShimmerConfigViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var hardwareConfiguration: HardwareConfigurationUseCase
    private lateinit var viewModel: ShimmerConfigViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        hardwareConfiguration = mockk(relaxed = true)
        viewModel = ShimmerConfigViewModel(hardwareConfiguration)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState initial values are correct`() = runTest {
        // When
        val state = viewModel.uiState.first()

        // Then
        assertFalse(state.isLoading)
        assertNull(state.errorMessage)
    }

    @Test
    fun `selectShimmerDevice sets loading state`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-1")
        coEvery { hardwareConfiguration.configureShimmerMacAddress(any(), any()) } coAnswers {
            kotlinx.coroutines.delay(100)
            Result.success(Unit)
        }

        // When
        viewModel.selectShimmerDevice(deviceId, "00:11:22:33:44:55")
        advanceUntilIdle()

        // Then
        coVerify { hardwareConfiguration.configureShimmerMacAddress(deviceId, "00:11:22:33:44:55") }

        val state = viewModel.uiState.first()
        assertFalse(state.isLoading) // Should be false after completion
    }

    @Test
    fun `selectShimmerDevice handles failure`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-1")
        val errorMessage = "Configuration failed"
        coEvery { hardwareConfiguration.configureShimmerMacAddress(any(), any()) } returns
                Result.failure(RuntimeException(errorMessage))

        // When
        viewModel.selectShimmerDevice(deviceId, "00:11:22:33:44:55")
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.first()
        assertFalse(state.isLoading)
        assertEquals(errorMessage, state.errorMessage)
    }

    @Test
    fun `updateShimmerRange calls use case`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-1")
        val rangeIndex = 2
        coEvery { hardwareConfiguration.configureShimmerGsrRange(deviceId, rangeIndex) } returns
                Result.success(Unit)

        // When
        viewModel.updateShimmerRange(deviceId, rangeIndex)
        advanceUntilIdle()

        // Then
        coVerify { hardwareConfiguration.configureShimmerGsrRange(deviceId, rangeIndex) }
    }

    @Test
    fun `updateShimmerSampleRate calls use case`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-1")
        val sampleRate = 64.0
        coEvery { hardwareConfiguration.configureShimmerSampleRate(deviceId, sampleRate) } returns
                Result.success(Unit)

        // When
        viewModel.updateShimmerSampleRate(deviceId, sampleRate)
        advanceUntilIdle()

        // Then
        coVerify { hardwareConfiguration.configureShimmerSampleRate(deviceId, sampleRate) }
    }

    @Test
    fun `clearError removes error from state`() = runTest {
        // Given - trigger an error
        val deviceId = DeviceId("shimmer-1")
        coEvery { hardwareConfiguration.configureShimmerMacAddress(any(), any()) } returns
                Result.failure(RuntimeException("Test error"))
        viewModel.selectShimmerDevice(deviceId, "00:11:22:33:44:55")
        advanceUntilIdle()

        var state = viewModel.uiState.first()
        assertEquals("Test error", state.errorMessage)

        // When
        viewModel.clearError()

        // Then
        state = viewModel.uiState.first()
        assertNull(state.errorMessage)
    }
}
