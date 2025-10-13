package com.buccancs.ui.calibration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
                text = "Camera Calibration",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
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
                    enabled = !state.isProcessing
                )
                OutlinedTextField(
                    value = state.patternColsInput,
                    onValueChange = actions.onColsChanged,
                    label = { Text("Columns") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    enabled = !state.isProcessing
                )
                OutlinedTextField(
                    value = state.squareSizeMmInput,
                    onValueChange = actions.onSquareSizeChanged,
                    label = { Text("Square (mm)") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    enabled = !state.isProcessing
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = state.requiredPairsInput,
                    onValueChange = actions.onRequiredPairsChanged,
                    label = { Text("Required pairs") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    enabled = !state.isProcessing
                )
                Button(
                    onClick = actions.onApplySettings,
                    enabled = !state.isProcessing
                ) {
                    Text("Apply Settings")
                }
            }
            Divider()
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = actions.onStartSession,
                    enabled = !state.active && !state.isProcessing
                ) {
                    Text("Start Session")
                }
                Button(
                    onClick = actions.onCapturePair,
                    enabled = state.active && !state.isProcessing
                ) {
                    Text("Capture Pair")
                }
                Button(
                    onClick = actions.onComputeCalibration,
                    enabled = state.capturedCount >= state.requiredPairs && !state.isProcessing
                ) {
                    Text("Compute")
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = actions.onLoadCachedResult,
                    enabled = !state.isProcessing
                ) {
                    Text("Load Cached Result")
                }
                OutlinedButton(
                    onClick = actions.onClearSession,
                    enabled = !state.isProcessing
                ) {
                    Text("Clear Session")
                }
            }
            Text(
                text = "Captured pairs: ${state.capturedCount} / ${state.requiredPairs}",
                style = MaterialTheme.typography.bodyMedium
            )
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
                Divider()
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
                                text = "${capture.id} - ${capture.capturedAt}",
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
            state.lastResult?.let { result ->
                Divider()
                Text(
                    text = "Last Calibration (${result.generatedAt})",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Pairs used: ${result.usedPairs} - RMS: ${"%.4f".format(result.meanReprojectionError)}",
                    style = MaterialTheme.typography.bodySmall
                )
                if (result.perViewErrors.isNotEmpty()) {
                    val maxError = result.perViewErrors.maxOrNull() ?: 0.0
                    val median = result.perViewErrors.sorted()[result.perViewErrors.size / 2]
                    Text(
                        text = "Per-view error median ${"%.4f".format(median)}, max ${"%.4f".format(maxError)}",
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
