package com.buccancs.ui.info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class VersionUiState(
    val appName: String = "TC001",
    val version: String = "1.0.0",
    val buildNumber: String = "100",
    val releaseDate: String = "2025-10-15",
    val features: List<String> = listOf(
        "Thermal imaging capture",
        "Real-time temperature analysis",
        "Image gallery management",
        "Multiple device support",
        "Data export capabilities"
    )
)

@HiltViewModel
class VersionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(VersionUiState())
    val uiState: StateFlow<VersionUiState> = _uiState.asStateFlow()
}
