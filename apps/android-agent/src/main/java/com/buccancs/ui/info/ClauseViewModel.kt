package com.buccancs.ui.info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ClauseUiState(
    val appName: String = "TC001",
    val version: String = "1.0.0",
    val yearRange: String = "2023-2025",
    val showDisagreeDialog: Boolean = false
)

@HiltViewModel
class ClauseViewModel @Inject constructor() :
    ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ClauseUiState()
        )
    val uiState: StateFlow<ClauseUiState> =
        _uiState.asStateFlow()

    fun acceptClause() {
    }

    fun showDisagreeDialog() {
        _uiState.value =
            _uiState.value.copy(
                showDisagreeDialog = true
            )
    }

    fun dismissDisagreeDialog() {
        _uiState.value =
            _uiState.value.copy(
                showDisagreeDialog = false
            )
    }
}
