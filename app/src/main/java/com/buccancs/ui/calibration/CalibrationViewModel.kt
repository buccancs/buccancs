package com.buccancs.ui.calibration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.CalibrationDefaults
import com.buccancs.domain.model.CalibrationPatternConfig
import com.buccancs.domain.model.CalibrationResult
import com.buccancs.domain.repository.CalibrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalibrationViewModel @Inject constructor(
    private val calibrationRepository: CalibrationRepository
) : ViewModel() {

    private val patternRowsInput = MutableStateFlow(CalibrationDefaults.Pattern.rows.toString())
    private val patternColsInput = MutableStateFlow(CalibrationDefaults.Pattern.cols.toString())
    private val squareSizeMmInput = MutableStateFlow((CalibrationDefaults.Pattern.squareSizeMeters * 1_000.0).format(2))
    private val requiredPairsInput = MutableStateFlow(CalibrationDefaults.RequiredPairs.toString())
    private val busy = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            calibrationRepository.loadLatestResult()
        }
    }

    val uiState: StateFlow<CalibrationUiState> = combine(
        calibrationRepository.sessionState,
        patternRowsInput,
        patternColsInput,
        squareSizeMmInput,
        requiredPairsInput,
        busy
    ) { session, rowsText, colsText, sizeText, requiredText, isBusy ->
        CalibrationUiState(
            patternRowsInput = rowsText,
            patternColsInput = colsText,
            squareSizeMmInput = sizeText,
            requiredPairsInput = requiredText,
            active = session.active,
            isProcessing = session.isProcessing || isBusy,
            captures = session.captures.map { CalibrationCaptureItem(it.id, it.rgb.capturedAt.toString()) },
            capturedCount = session.captures.size,
            requiredPairs = session.requiredPairs,
            infoMessage = session.infoMessage,
            errorMessage = session.errorMessage,
            lastResult = session.lastResult
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CalibrationUiState.initial()
    )

    fun updatePatternRows(value: String) {
        patternRowsInput.value = value.filterDigits()
    }

    fun updatePatternCols(value: String) {
        patternColsInput.value = value.filterDigits()
    }

    fun updateSquareSizeMm(value: String) {
        squareSizeMmInput.value = value.filterDecimal()
    }

    fun updateRequiredPairs(value: String) {
        requiredPairsInput.value = value.filterDigits()
    }

    fun applyPatternSettings() {
        val rows = patternRowsInput.value.toIntOrNull()?.coerceAtLeast(2) ?: return
        val cols = patternColsInput.value.toIntOrNull()?.coerceAtLeast(2) ?: return
        val squareSizeMeters =
            squareSizeMmInput.value.toDoubleOrNull()?.let { it / 1_000.0 }?.takeIf { it > 0.0 } ?: return
        val requiredPairs = requiredPairsInput.value.toIntOrNull()?.coerceAtLeast(3) ?: return
        viewModelScope.launch {
            calibrationRepository.configure(
                pattern = CalibrationPatternConfig(
                    rows = rows,
                    cols = cols,
                    squareSizeMeters = squareSizeMeters
                ),
                requiredPairs = requiredPairs
            )
        }
    }

    fun startSession() {
        launchGuarded {
            calibrationRepository.beginSession()
        }
    }

    fun clearSession() {
        launchGuarded {
            calibrationRepository.clearSession()
        }
    }

    fun capture() {
        launchGuarded {
            calibrationRepository.capturePair()
        }
    }

    fun removeCapture(id: String) {
        launchGuarded {
            calibrationRepository.removeCapture(id)
        }
    }

    fun computeCalibration() {
        launchGuarded {
            calibrationRepository.computeAndPersist()
        }
    }

    fun loadCachedResult() {
        launchGuarded {
            calibrationRepository.loadLatestResult()
        }
    }

    private fun launchGuarded(block: suspend () -> Unit) {
        if (busy.compareAndSet(expect = false, update = true)) {
            viewModelScope.launch {
                try {
                    block()
                } finally {
                    busy.value = false
                }
            }
        }
    }

    private fun MutableStateFlow<Boolean>.compareAndSet(expect: Boolean, update: Boolean): Boolean {
        return if (value == expect) {
            value = update
            true
        } else {
            false
        }
    }

    private fun String.filterDigits(): String = filter { it.isDigit() }.take(2)

    private fun String.filterDecimal(): String {
        val filtered = filter { it.isDigit() || it == '.' }
        val dotIndex = filtered.indexOf('.')
        return if (dotIndex >= 0) {
            filtered.substring(0, dotIndex + 1) + filtered.substring(dotIndex + 1).replace(".", "")
        } else {
            filtered
        }
    }

    private fun Double.format(decimals: Int): String =
        "%.${decimals}f".format(this)
}

data class CalibrationUiState(
    val patternRowsInput: String,
    val patternColsInput: String,
    val squareSizeMmInput: String,
    val requiredPairsInput: String,
    val active: Boolean,
    val isProcessing: Boolean,
    val captures: List<CalibrationCaptureItem>,
    val capturedCount: Int,
    val requiredPairs: Int,
    val infoMessage: String?,
    val errorMessage: String?,
    val lastResult: CalibrationResult?
) {
    companion object {
        fun initial(): CalibrationUiState = CalibrationUiState(
            patternRowsInput = CalibrationDefaults.Pattern.rows.toString(),
            patternColsInput = CalibrationDefaults.Pattern.cols.toString(),
            squareSizeMmInput = (CalibrationDefaults.Pattern.squareSizeMeters * 1_000.0).toString(),
            requiredPairsInput = CalibrationDefaults.RequiredPairs.toString(),
            active = false,
            isProcessing = false,
            captures = emptyList(),
            capturedCount = 0,
            requiredPairs = CalibrationDefaults.RequiredPairs,
            infoMessage = null,
            errorMessage = null,
            lastResult = null
        )
    }
}

data class CalibrationCaptureItem(
    val id: String,
    val capturedAt: String
)
