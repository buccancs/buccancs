package com.buccancs.ui.info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class WebViewUiState(
    val url: String = "",
    val title: String = "Web View",
    val isLoading: Boolean = false
)

@HiltViewModel
class WebViewViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(WebViewUiState())
    val uiState: StateFlow<WebViewUiState> = _uiState.asStateFlow()

    fun loadUrl(url: String) {
        _uiState.value = _uiState.value.copy(
            url = url,
            isLoading = true
        )
    }
}
