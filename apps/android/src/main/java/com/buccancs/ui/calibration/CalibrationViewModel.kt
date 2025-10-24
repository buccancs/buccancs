@file:OptIn(
    ExperimentalTime::class
)

package com.buccancs.ui.calibration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccancs.domain.model.CalibrationDefaults
import com.buccancs.domain.model.CalibrationMetrics
import com.buccancs.domain.model.CalibrationPatternConfig
import com.buccancs.domain.model.CalibrationResult
import com.buccancs.domain.model.CalibrationSessionState
import com.buccancs.domain.repository.CalibrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@HiltViewModel
class CalibrationViewModel @Inject constructor(
    private val calibrationRepository: CalibrationRepository
) : ViewModel() {
    private val patternRowsInput =
        MutableStateFlow(
            CalibrationDefaults.Pattern.rows.toString()
        )
    private val patternColsInput =
        MutableStateFlow(
            CalibrationDefaults.Pattern.cols.toString()
        )
    private val squareSizeMmInput =
        MutableStateFlow(
            (CalibrationDefaults.Pattern.squareSizeMeters * 1_000.0).format(
                2
            )
        )
    private val requiredPairsInput =
        MutableStateFlow(
            CalibrationDefaults.RequiredPairs.toString()
        )
    private val busy =
        MutableStateFlow(
            false
        )
    private val inputState =
        combine(
            patternRowsInput,
            patternColsInput,
            squareSizeMmInput,
            requiredPairsInput,
            busy
        ) { rowsText, colsText, sizeText, requiredText, isBusy ->
            CalibrationInputs(
                rowsText = rowsText,
                colsText = colsText,
                sizeText = sizeText,
                requiredText = requiredText,
                isBusy = isBusy
            )
        }

    init {
        viewModelScope.launch {
            calibrationRepository.loadLatestResult()
        }
    }

    val uiState: StateFlow<CalibrationUiState> =
        combine(
            calibrationRepository.sessionState,
            calibrationRepository.metrics,
            inputState
        ) { session, metrics, inputs ->
            val step =
                determineStep(
                    session
                )
            val captureProgress =
                if (session.requiredPairs > 0) {
                    (session.captures.size.toFloat() / session.requiredPairs.toFloat()).coerceIn(
                        0f,
                        1f
                    )
                } else {
                    0f
                }
            val guidance =
                guidanceFor(
                    step,
                    session
                )
            val confidence =
                metrics?.let(
                    ::confidenceLabel
                )
            val lowConfidence =
                metrics?.let(
                    ::isLowConfidence
                )
                    ?: false
            val hints =
                hintsFor(
                    step,
                    session,
                    metrics
                )
            CalibrationUiState(
                patternRowsInput = inputs.rowsText,
                patternColsInput = inputs.colsText,
                squareSizeMmInput = inputs.sizeText,
                requiredPairsInput = inputs.requiredText,
                active = session.active,
                isProcessing = session.isProcessing || inputs.isBusy,
                wizardStep = step,
                captureProgress = captureProgress,
                guidanceMessage = guidance,
                confidenceLabel = confidence,
                isLowConfidence = lowConfidence,
                actionHints = hints,
                captures = session.captures.map {
                    CalibrationCaptureItem(
                        it.id,
                        it.rgb.capturedAt.toString()
                    )
                },
                capturedCount = session.captures.size,
                requiredPairs = session.requiredPairs,
                infoMessage = session.infoMessage,
                errorMessage = session.errorMessage,
                lastResult = session.lastResult,
                latestMetrics = metrics
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                5_000
            ),
            initialValue = CalibrationUiState.initial()
        )

    fun updatePatternRows(
        value: String
    ) {
        patternRowsInput.value =
            value.filterDigits()
    }

    fun updatePatternCols(
        value: String
    ) {
        patternColsInput.value =
            value.filterDigits()
    }

    fun updateSquareSizeMm(
        value: String
    ) {
        squareSizeMmInput.value =
            value.filterDecimal()
    }

    fun updateRequiredPairs(
        value: String
    ) {
        requiredPairsInput.value =
            value.filterDigits()
    }

    fun applyPatternSettings() {
        val rows =
            patternRowsInput.value.toIntOrNull()
                ?.coerceAtLeast(
                    2
                )
                ?: return
        val cols =
            patternColsInput.value.toIntOrNull()
                ?.coerceAtLeast(
                    2
                )
                ?: return
        val squareSizeMeters =
            squareSizeMmInput.value.toDoubleOrNull()
                ?.let { it / 1_000.0 }
                ?.takeIf { it > 0.0 }
                ?: return
        val requiredPairs =
            requiredPairsInput.value.toIntOrNull()
                ?.coerceAtLeast(
                    3
                )
                ?: return
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

    fun removeCapture(
        id: String
    ) {
        launchGuarded {
            calibrationRepository.removeCapture(
                id
            )
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

    private fun determineStep(
        session: CalibrationSessionState
    ): CalibrationWizardStep {
        val hasResult =
            session.lastResult != null
        return when {
            hasResult -> CalibrationWizardStep.Review
            session.isProcessing -> CalibrationWizardStep.Validate
            session.active && session.captures.size >= session.requiredPairs -> CalibrationWizardStep.Validate
            session.active -> CalibrationWizardStep.Capture
            session.captures.isNotEmpty() && session.captures.size >= session.requiredPairs -> CalibrationWizardStep.Validate
            session.captures.isNotEmpty() -> CalibrationWizardStep.Capture
            else -> CalibrationWizardStep.Configure
        }
    }

    private fun guidanceFor(
        step: CalibrationWizardStep,
        session: CalibrationSessionState
    ): String =
        when (step) {
            CalibrationWizardStep.Configure -> "Confirm checkerboard pattern settings, then start the calibration session when cameras are ready."
            CalibrationWizardStep.Capture -> {
                val remaining =
                    (session.requiredPairs - session.captures.size).coerceAtLeast(
                        0
                    )
                if (remaining == 0) {
                    "Required capture count met. Run Compute Calibration to continue."
                } else {
                    "Capture $remaining additional pair(s) with varied poses to reach ${session.requiredPairs} total."
                }
            }

            CalibrationWizardStep.Validate -> if (session.isProcessing) {
                "Computing calibration... keep the rig steady until metrics are produced."
            } else {
                "Run Compute Calibration to generate metrics and confirm stereo alignment."
            }

            CalibrationWizardStep.Review -> "Review the latest metrics and rerun capture if confidence is low or alignment drifts."
        }

    private fun confidenceLabel(
        metrics: CalibrationMetrics
    ): String {
        val mean =
            metrics.meanReprojectionError
        val max =
            metrics.maxReprojectionError
        val level =
            when {
                mean <= 0.4 && max <= 1.0 -> "High"
                mean <= 0.8 && max <= 1.6 -> "Moderate"
                else -> "Low"
            }
        return "$level confidence — RMS ${
            mean.format(
                3
            )
        } px · max ${
            max.format(
                3
            )
        } px"
    }

    private fun isLowConfidence(
        metrics: CalibrationMetrics
    ): Boolean =
        metrics.meanReprojectionError > 0.8 || metrics.maxReprojectionError > 1.6

    private fun hintsFor(
        step: CalibrationWizardStep,
        session: CalibrationSessionState,
        metrics: CalibrationMetrics?
    ): List<String> =
        when (step) {
            CalibrationWizardStep.Configure -> listOf(
                "Confirm the physical checkerboard matches the configured dimensions.",
                "Remove glare and ensure both cameras see the entire board before starting."
            )

            CalibrationWizardStep.Capture -> listOf(
                "Capture frames that cover all corners and tilt the board for perspective diversity.",
                "Hold the rig steady for one second before tapping Capture to avoid motion blur."
            )

            CalibrationWizardStep.Validate -> listOf(
                "Keep the checkerboard visible while processing.",
                "If validation fails, remove any blurry captures before recomputing."
            )

            CalibrationWizardStep.Review -> {
                if (metrics != null && isLowConfidence(
                        metrics
                    )
                ) {
                    listOf(
                        "Confidence is low—recapture the pattern with better lighting and perspective coverage.",
                        "Consider increasing required pairs and removing frames with partial board visibility."
                    )
                } else {
                    listOf(
                        "Archive the metrics snapshot for audit and proceed to live capture.",
                        "Re-run calibration if hardware placement changes or confidence drops in future sessions."
                    )
                }
            }
        }

    private fun launchGuarded(
        block: suspend () -> Unit
    ) {
        if (busy.compareAndSet(
                expect = false,
                update = true
            )
        ) {
            viewModelScope.launch {
                try {
                    block()
                } finally {
                    busy.value =
                        false
                }
            }
        }
    }

    private fun MutableStateFlow<Boolean>.compareAndSet(
        expect: Boolean,
        update: Boolean
    ): Boolean {
        return if (value == expect) {
            value =
                update
            true
        } else {
            false
        }
    }

    private data class CalibrationInputs(
        val rowsText: String,
        val colsText: String,
        val sizeText: String,
        val requiredText: String,
        val isBusy: Boolean
    )

    private fun String.filterDigits(): String =
        filter { it.isDigit() }.take(
            2
        )

    private fun String.filterDecimal(): String {
        val filtered =
            filter { it.isDigit() || it == '.' }
        val dotIndex =
            filtered.indexOf(
                '.'
            )
        return if (dotIndex >= 0) {
            filtered.substring(
                0,
                dotIndex + 1
            ) + filtered.substring(
                dotIndex + 1
            )
                .replace(
                    ".",
                    ""
                )
        } else {
            filtered
        }
    }

    private fun Double.format(
        decimals: Int
    ): String =
        "%.${decimals}f".format(
            this
        )
}

data class CalibrationUiState(
    val patternRowsInput: String,
    val patternColsInput: String,
    val squareSizeMmInput: String,
    val requiredPairsInput: String,
    val active: Boolean,
    val isProcessing: Boolean,
    val wizardStep: CalibrationWizardStep,
    val captureProgress: Float,
    val guidanceMessage: String,
    val confidenceLabel: String?,
    val isLowConfidence: Boolean,
    val actionHints: List<String>,
    val captures: List<CalibrationCaptureItem>,
    val capturedCount: Int,
    val requiredPairs: Int,
    val infoMessage: String?,
    val errorMessage: String?,
    val lastResult: CalibrationResult?,
    val latestMetrics: CalibrationMetrics?
) {
    companion object {
        fun initial(): CalibrationUiState =
            CalibrationUiState(
                patternRowsInput = CalibrationDefaults.Pattern.rows.toString(),
                patternColsInput = CalibrationDefaults.Pattern.cols.toString(),
                squareSizeMmInput = (CalibrationDefaults.Pattern.squareSizeMeters * 1_000.0).toString(),
                requiredPairsInput = CalibrationDefaults.RequiredPairs.toString(),
                active = false,
                isProcessing = false,
                wizardStep = CalibrationWizardStep.Configure,
                captureProgress = 0f,
                guidanceMessage = CalibrationWizardStep.Configure.defaultGuidance,
                confidenceLabel = null,
                isLowConfidence = false,
                actionHints = emptyList(),
                captures = emptyList(),
                capturedCount = 0,
                requiredPairs = CalibrationDefaults.RequiredPairs,
                infoMessage = null,
                errorMessage = null,
                lastResult = null,
                latestMetrics = null
            )
    }
}

data class CalibrationCaptureItem(
    val id: String,
    val capturedAt: String
)

enum class CalibrationWizardStep(
    val title: String,
    val defaultGuidance: String
) {
    Configure(
        "Configure Pattern",
        "Confirm checkerboard dimensions and required pair count before starting."
    ),
    Capture(
        "Capture Pairs",
        "Capture calibration frames with varied angles and distances."
    ),
    Validate(
        "Compute Calibration",
        "Process the capture set to generate calibration metrics."
    ),
    Review(
        "Review Metrics",
        "Assess reprojection errors and decide whether to rerun capture."
    )
}
