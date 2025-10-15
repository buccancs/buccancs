package com.buccancs.ui.calibration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun CalibrationPanel(
    state: CalibrationUiState,
    actions: CalibrationActions
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
	            .fillMaxWidth()
	            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Stereo Calibration Wizard",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            WizardStepIndicator(current = state.wizardStep)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = state.guidanceMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (state.actionHints.isNotEmpty()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    state.actionHints.forEach { hint ->
                        Text(
                            text = "• $hint",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            if (state.wizardStep == CalibrationWizardStep.Capture || state.wizardStep == CalibrationWizardStep.Validate) {
                LinearProgressIndicator(
                    progress = { state.captureProgress },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = "Captured pairs: ${state.capturedCount} / ${state.requiredPairs}",
                style = MaterialTheme.typography.bodyMedium
            )
            state.confidenceLabel?.let { confidence ->
                Text(
                    text = confidence,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                if (state.isLowConfidence) {
                    Text(
                        text = "Confidence is low. Capture additional pairs with better coverage or clear blurry frames.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            state.latestMetrics?.let { metrics ->
                Text(
                    text = "Last metrics @ ${metrics.generatedAt} — RMS ${"%.3f".format(metrics.meanReprojectionError)} px · max ${
                        "%.3f".format(
                            metrics.maxReprojectionError
                        )
                    } px",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            HorizontalDivider()
            when (state.wizardStep) {
                CalibrationWizardStep.Configure -> {
                    PatternSettingsSection(state, actions)
                    Spacer(modifier = Modifier.height(4.dp))
                    StepControls(
                        primaryLabel = "Start Session",
                        primaryEnabled = !state.active && !state.isProcessing,
                        onPrimary = actions.onStartSession,
                        secondaryLabel = "Apply Settings",
                        onSecondary = actions.onApplySettings,
                        secondaryEnabled = !state.isProcessing
                    )
                }

                CalibrationWizardStep.Capture -> {
                    PatternSettingsSection(state, actions, compact = true)
                    Spacer(modifier = Modifier.height(8.dp))
                    CaptureControls(state, actions)
                }

                CalibrationWizardStep.Validate -> {
                    PatternSettingsSection(state, actions, compact = true)
                    Spacer(modifier = Modifier.height(8.dp))
                    CaptureControls(state, actions)
                    Spacer(modifier = Modifier.height(8.dp))
                    StepControls(
                        primaryLabel = if (state.isProcessing) "Computing..." else "Compute Calibration",
                        primaryEnabled = state.capturedCount >= state.requiredPairs && !state.isProcessing,
                        onPrimary = actions.onComputeCalibration,
                        secondaryLabel = "Refresh Metrics",
                        secondaryEnabled = !state.isProcessing,
                        onSecondary = actions.onLoadCachedResult
                    )
                }

                CalibrationWizardStep.Review -> {
                    PatternSettingsSection(state, actions, compact = true)
                    Spacer(modifier = Modifier.height(8.dp))
                    StepControls(
                        primaryLabel = "Re-run Capture",
                        primaryEnabled = !state.isProcessing,
                        onPrimary = actions.onStartSession,
                        secondaryLabel = "Load Cached Result",
                        secondaryEnabled = !state.isProcessing,
                        onSecondary = actions.onLoadCachedResult
                    )
                }
            }
            if (state.infoMessage != null) {
                Text(
                    text = state.infoMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
            if (state.captures.isNotEmpty()) {
                CapturedList(state, actions)
            }
            state.lastResult?.let { result ->
                HorizontalDivider()
                Text(
                    text = "Last Calibration (${result.generatedAt})",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Pairs used: ${result.usedPairs} · RMS ${"%.4f".format(result.meanReprojectionError)} px",
                    style = MaterialTheme.typography.bodySmall
                )
                if (result.perViewErrors.isNotEmpty()) {
                    val maxError = result.perViewErrors.maxOrNull() ?: 0.0
                    val median = result.perViewErrors.sorted()[result.perViewErrors.size / 2]
                    Text(
                        text = "Per-view error median ${"%.4f".format(median)} px · max ${"%.4f".format(maxError)} px",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                val translationNorm = sqrt(result.extrinsic.translation.sumOf { it * it })
                Text(
                    text = "Translation norm: ${"%.3f".format(translationNorm)} m",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun PatternSettingsSection(
    state: CalibrationUiState,
    actions: CalibrationActions,
    compact: Boolean = false
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Pattern Settings",
            style = MaterialTheme.typography.titleSmall
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = state.patternRowsInput,
                onValueChange = actions.onRowsChanged,
                label = { Text("Rows") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                enabled = !state.isProcessing && !compact
            )
            OutlinedTextField(
                value = state.patternColsInput,
                onValueChange = actions.onColsChanged,
                label = { Text("Columns") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                enabled = !state.isProcessing && !compact
            )
            OutlinedTextField(
                value = state.squareSizeMmInput,
                onValueChange = actions.onSquareSizeChanged,
                label = { Text("Square (mm)") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                enabled = !state.isProcessing && !compact
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = state.requiredPairsInput,
                onValueChange = actions.onRequiredPairsChanged,
                label = { Text("Required pairs") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                enabled = !state.isProcessing && !compact
            )
            if (!compact) {
                Button(
                    onClick = actions.onApplySettings,
                    enabled = !state.isProcessing
                ) {
                    Text("Apply")
                }
            }
        }
    }
}

@Composable
private fun CaptureControls(state: CalibrationUiState, actions: CalibrationActions) {
    Text(text = "Capture Guidance", style = MaterialTheme.typography.titleSmall)
    Spacer(modifier = Modifier.height(4.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(
            onClick = actions.onCapturePair,
            enabled = state.active && !state.isProcessing
        ) {
            Text("Capture Pair")
        }
        OutlinedButton(
            onClick = actions.onClearSession,
            enabled = !state.isProcessing
        ) {
            Text("Clear Captures")
        }
    }
}

@Composable
private fun StepControls(
    primaryLabel: String,
    primaryEnabled: Boolean,
    onPrimary: () -> Unit,
    secondaryLabel: String,
    secondaryEnabled: Boolean,
    onSecondary: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(
            onClick = onPrimary,
            enabled = primaryEnabled
        ) {
            Text(primaryLabel)
        }
        OutlinedButton(
            onClick = onSecondary,
            enabled = secondaryEnabled
        ) {
            Text(secondaryLabel)
        }
    }
}

@Composable
private fun CapturedList(state: CalibrationUiState, actions: CalibrationActions) {
    HorizontalDivider()
    Text(
        text = "Captured Frames",
        style = MaterialTheme.typography.titleSmall
    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(state.captures, key = { it.id }) { capture ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${capture.id} — ${capture.capturedAt}",
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(
                    onClick = { actions.onRemoveCapture(capture.id) },
                    enabled = !state.isProcessing
                ) {
                    Text("Remove")
                }
            }
        }
    }
}

@Composable
private fun WizardStepIndicator(current: CalibrationWizardStep) {
    val steps = CalibrationWizardStep.values()
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        steps.forEachIndexed { index, step ->
            val selected = step == current
            Surface(
                shape = MaterialTheme.shapes.small,
                color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                tonalElevation = if (selected) 4.dp else 0.dp
            ) {
                Text(
                    text = "${index + 1}. ${step.title}",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

data class CalibrationActions(
    val onRowsChanged: (String) -> Unit,
    val onColsChanged: (String) -> Unit,
    val onSquareSizeChanged: (String) -> Unit,
    val onRequiredPairsChanged: (String) -> Unit,
    val onApplySettings: () -> Unit,
    val onStartSession: () -> Unit,
    val onCapturePair: () -> Unit,
    val onComputeCalibration: () -> Unit,
    val onLoadCachedResult: () -> Unit,
    val onClearSession: () -> Unit,
    val onRemoveCapture: (String) -> Unit
)
