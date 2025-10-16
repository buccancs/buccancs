package com.buccancs.ui.topdon.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.ThermalImage
import com.buccancs.domain.repository.TopdonGalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ImageDetailUiState(
    val image: ThermalImage? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val galleryRepository: TopdonGalleryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val imageId: String = savedStateHandle.get<String>("imageId") ?: ""

    private val _uiState = MutableStateFlow(ImageDetailUiState(isLoading = true))
    val uiState: StateFlow<ImageDetailUiState> = _uiState.asStateFlow()

    init {
        loadImage()
    }

    private fun loadImage() {
        viewModelScope.launch {
            try {
                val image = galleryRepository.getImageById(imageId)
                _uiState.update {
                    it.copy(image = image, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    fun deleteImage(id: String) {
        viewModelScope.launch {
            try {
                galleryRepository.deleteImage(id)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Failed to delete: ${e.message}")
                }
            }
        }
    }

    fun shareImage(id: String) {
        viewModelScope.launch {
            try {
                galleryRepository.shareImages(listOf(id))
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Failed to share: ${e.message}")
                }
            }
        }
    }

    fun exportImage(id: String) {
        viewModelScope.launch {
            try {
                galleryRepository.exportImages(listOf(id), "/storage/emulated/0/Download")
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Failed to export: ${e.message}")
                }
            }
        }
    }
}
