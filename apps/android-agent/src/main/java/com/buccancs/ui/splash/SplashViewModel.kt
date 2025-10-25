package com.buccancs.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class NavigationTarget {
    NONE,
    CLAUSE,
    MAIN
}

data class SplashUiState(
    val appName: String = "TC001",
    val navigationTarget: NavigationTarget = NavigationTarget.NONE
)

@HiltViewModel
class SplashViewModel @Inject constructor() :
    ViewModel() {

    private val _uiState =
        MutableStateFlow(
            SplashUiState()
        )
    val uiState: StateFlow<SplashUiState> =
        _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(
                1000
            )
            val hasAcceptedClause =
                checkClauseAcceptance()
            _uiState.value =
                _uiState.value.copy(
                    navigationTarget = if (hasAcceptedClause) {
                        NavigationTarget.MAIN
                    } else {
                        NavigationTarget.CLAUSE
                    }
                )
        }
    }

    private fun checkClauseAcceptance(): Boolean {
        return true
    }
}
