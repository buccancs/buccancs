package com.buccancs.ui.topdon.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.ThermalMediaItem
import com.buccancs.domain.repository.TopdonGalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GalleryUiState(
    val items: List<ThermalMediaItem> = emptyList(),
    val selectedItems: Set<String> = emptySet(),
    val selectionMode: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class TopdonGalleryViewModel @Inject constructor(
    private val galleryRepository: TopdonGalleryRepository
) : ViewModel() {

    private val selectedItems = MutableStateFlow<Set<String>>(emptySet())
    private val selectionMode = MutableStateFlow(false)
    private val isLoading = MutableStateFlow(false)
    private val error = MutableStateFlow<String?>(null)

    val uiState: StateFlow<GalleryUiState> = combine(
        galleryRepository.getAllMedia(),
        selectedItems,
        selectionMode,
        isLoading,
        error
    ) { items, selected, mode, loading, err ->
        GalleryUiState(
            items = items,
            selectedItems = selected,
            selectionMode = mode,
            isLoading = loading,
            error = err
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = GalleryUiState(isLoading = true)
    )

    fun toggleSelection(id: String) {
        val current = selectedItems.value
        selectedItems.value = if (current.contains(id)) {
            current - id
        } else {
            current + id
        }
    }

    fun enterSelectionMode(initialId: String? = null) {
        selectionMode.value = true
        initialId?.let { toggleSelection(it) }
    }

    fun exitSelectionMode() {
        selectionMode.value = false
        selectedItems.value = emptySet()
    }

    fun deleteSelected() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val ids = selectedItems.value.toList()
                val result = galleryRepository.deleteMultiple(ids)
                result.onFailure { e ->
                    error.value = "Failed to delete: ${e.message}"
                }
                exitSelectionMode()
            } catch (e: Exception) {
                error.value = "Failed to delete: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun shareSelected() {
        viewModelScope.launch {
            try {
                val ids = selectedItems.value.toList()
                val result = galleryRepository.shareImages(ids)
                result.onFailure { e ->
                    error.value = "Failed to share: ${e.message}"
                }
                exitSelectionMode()
            } catch (e: Exception) {
                error.value = "Failed to share: ${e.message}"
            }
        }
    }

    fun clearError() {
        error.value = null
    }
}
