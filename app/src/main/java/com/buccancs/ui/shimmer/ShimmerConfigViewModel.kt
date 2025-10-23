package com.buccancs.ui.shimmer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.usecase.HardwareConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.buccancs.core.result.exceptionOrNull

/**
 * ViewModel responsible for Shimmer device configuration.
 * Extracted from MainViewModel to focus on Shimmer-specific concerns.
 */
@HiltViewModel
class ShimmerConfigViewModel @Inject constructor(
    private val hardwareConfiguration: HardwareConfigurationUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ShimmerConfigUiState.initial()
        )
    val uiState: StateFlow<ShimmerConfigUiState> =
        _uiState.asStateFlow()

    fun selectShimmerDevice(
        id: DeviceId,
        mac: String?
    ) {
        viewModelScope.launch {
            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )
            val result =
                hardwareConfiguration.configureShimmerMacAddress(
                    id,
                    mac
                )
            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message
                )
        }
    }

    fun updateShimmerRange(
        id: DeviceId,
        rangeIndex: Int
    ) {
        viewModelScope.launch {
            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )
            val result =
                hardwareConfiguration.configureShimmerGsrRange(
                    id,
                    rangeIndex
                )
            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message
                )
        }
    }

    fun updateShimmerSampleRate(
        id: DeviceId,
        sampleRate: Double
    ) {
        viewModelScope.launch {
            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )
            val result =
                hardwareConfiguration.configureShimmerSampleRate(
                    id,
                    sampleRate
                )
            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message
                )
        }
    }

    fun clearError() {
        _uiState.value =
            _uiState.value.copy(
                errorMessage = null
            )
    }
}

data class ShimmerConfigUiState(
    val isLoading: Boolean,
    val errorMessage: String?
) {
    companion object {
        fun initial(): ShimmerConfigUiState =
            ShimmerConfigUiState(
                isLoading = false,
                errorMessage = null
            )
    }
}
