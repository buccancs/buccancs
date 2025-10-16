package com.buccancs.ui.topdon.gallery

import com.buccancs.domain.model.ThermalImage
import com.buccancs.domain.model.ThermalMediaItem
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.repository.TopdonGalleryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TopdonGalleryViewModelTest {

    private lateinit var viewModel: TopdonGalleryViewModel
    private lateinit var repository: TopdonGalleryRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is loading`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)

        viewModel = TopdonGalleryViewModel(repository)

        val state = viewModel.uiState.value
        assertTrue(state.isLoading)
        assertEquals(emptyList(), state.items)
    }

    @Test
    fun `loads items from repository`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(2, state.items.size)
    }

    @Test
    fun `toggle selection adds item to selection`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        viewModel.toggleSelection("image1")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.selectedItems.contains("image1"))
        assertEquals(1, state.selectedItems.size)
    }

    @Test
    fun `toggle selection removes item from selection`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        viewModel.toggleSelection("image1")
        advanceUntilIdle()
        viewModel.toggleSelection("image1")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.selectedItems.contains("image1"))
        assertEquals(0, state.selectedItems.size)
    }

    @Test
    fun `enter selection mode enables selection`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        viewModel.enterSelectionMode("image1")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.selectionMode)
        assertTrue(state.selectedItems.contains("image1"))
    }

    @Test
    fun `exit selection mode clears selection`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        viewModel.enterSelectionMode("image1")
        advanceUntilIdle()
        viewModel.exitSelectionMode()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.selectionMode)
        assertEquals(0, state.selectedItems.size)
    }

    @Test
    fun `delete selected calls repository`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)
        coEvery { repository.deleteMultiple(any()) } returns Result.success(Unit)

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        viewModel.enterSelectionMode("image1")
        advanceUntilIdle()
        viewModel.deleteSelected()
        advanceUntilIdle()

        coVerify { repository.deleteMultiple(listOf("image1")) }
        val state = viewModel.uiState.value
        assertFalse(state.selectionMode)
    }

    @Test
    fun `delete selected handles error`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)
        coEvery { repository.deleteMultiple(any()) } returns Result.failure(Exception("Delete failed"))

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        viewModel.enterSelectionMode("image1")
        advanceUntilIdle()
        viewModel.deleteSelected()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("Failed to delete: Delete failed", state.error)
    }

    @Test
    fun `share selected calls repository`() = runTest {
        val items = createSampleItems()
        coEvery { repository.getAllMedia() } returns flowOf(items)
        coEvery { repository.shareImages(any()) } returns Result.success(Unit)

        viewModel = TopdonGalleryViewModel(repository)
        advanceUntilIdle()

        viewModel.enterSelectionMode("image1")
        advanceUntilIdle()
        viewModel.shareSelected()
        advanceUntilIdle()

        coVerify { repository.shareImages(listOf("image1")) }
    }

    private fun createSampleItems(): List<ThermalMediaItem> {
        val image1 = ThermalImage(
            id = "image1",
            filePath = "/path/to/image1.png",
            timestamp = LocalDateTime.now(),
            width = 256,
            height = 192,
            palette = TopdonPalette.IRONBOW,
            superSampling = TopdonSuperSamplingFactor.X1,
            minTemperature = 20f,
            maxTemperature = 35f,
            avgTemperature = 27.5f
        )

        val image2 = ThermalImage(
            id = "image2",
            filePath = "/path/to/image2.png",
            timestamp = LocalDateTime.now().minusHours(1),
            width = 256,
            height = 192,
            palette = TopdonPalette.GRAYSCALE,
            superSampling = TopdonSuperSamplingFactor.X2,
            minTemperature = 18f,
            maxTemperature = 32f,
            avgTemperature = 25f
        )

        return listOf(
            ThermalMediaItem.Image(image1),
            ThermalMediaItem.Image(image2)
        )
    }
}
