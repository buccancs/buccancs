package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.buccancs.desktop.ui.components.BuccancsCard
import com.buccancs.desktop.ui.components.PrimaryButton
import com.buccancs.desktop.ui.components.ScreenHeader
import com.buccancs.desktop.ui.components.SecondaryButton
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Calibration screen for managing sensor calibration profiles and stereo camera calibration
 */
@Composable
fun CalibrationScreen() {
    var selectedDevice by remember { mutableStateOf("Device-001") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.Large),
        verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
    ) {
        ScreenHeader(
            title = "Calibration",
            subtitle = "Sensor calibration profiles and stereo camera calibration"
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            // Device Selection
            item {
                BuccancsCard(title = "Device Selection") {
                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it }
                    ) {
                        OutlinedTextField(
                            value = selectedDevice,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Select Device") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(
                                    ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                                    enabled = true
                                ),
                            leadingIcon = { Icon(Icons.Default.PhoneAndroid, "Device") }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            listOf("Device-001", "Device-002", "Device-003").forEach { device ->
                                DropdownMenuItem(
                                    text = { Text(device) },
                                    onClick = {
                                        selectedDevice = device
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // Shimmer GSR Calibration
            item {
                BuccancsCard(
                    title = "Shimmer3 GSR+ Calibration",
                    subtitle = "Galvanic skin response sensor calibration"
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                        CalibrationMetric("Status", "Calibrated", true)
                        CalibrationMetric("Last Calibration", "2025-10-14 09:30", false)
                        CalibrationMetric("Range", "0-100 µS", false)
                        CalibrationMetric("Offset", "+2.3 µS", false)
                        CalibrationMetric("Quality Score", "95/100", true)

                        HorizontalDivider()

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
                        ) {
                            PrimaryButton(
                                text = "Start Calibration",
                                onClick = { /* Start */ },
                                modifier = Modifier.weight(1f)
                            )
                            SecondaryButton(
                                text = "Load Profile",
                                onClick = { /* Load */ },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Stereo Camera Calibration
            item {
                BuccancsCard(
                    title = "Stereo Camera Calibration",
                    subtitle = "Thermal + RGB camera stereo calibration"
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                        CalibrationMetric("Status", "Calibrated", true)
                        CalibrationMetric("Last Calibration", "2025-10-15 14:20", false)
                        CalibrationMetric("Intrinsics - RGB", "fx=1820, fy=1825", false)
                        CalibrationMetric("Intrinsics - Thermal", "fx=385, fy=388", false)
                        CalibrationMetric("Reprojection Error", "0.42 px", true)
                        CalibrationMetric("Baseline", "45.3 mm", false)

                        HorizontalDivider()

                        Text(
                            "Calibration Board Requirements:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            "• Checkerboard: 9x6 squares, 25mm square size\n" +
                                    "• Temperature contrast: ≥15°C\n" +
                                    "• Capture 20+ poses with various angles",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        HorizontalDivider()

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
                        ) {
                            PrimaryButton(
                                text = "Start Calibration",
                                onClick = { /* Start */ },
                                modifier = Modifier.weight(1f)
                            )
                            SecondaryButton(
                                text = "View Matrix",
                                onClick = { /* View */ },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Calibration History
            item {
                BuccancsCard(
                    title = "Calibration History",
                    subtitle = "Recent calibration sessions"
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                        listOf(
                            CalibrationRecord(
                                "2025-10-15 14:20",
                                "Stereo Camera",
                                "Success",
                                "0.42 px error"
                            ),
                            CalibrationRecord(
                                "2025-10-14 09:30",
                                "GSR Sensor",
                                "Success",
                                "95/100 quality"
                            ),
                            CalibrationRecord(
                                "2025-10-13 16:45",
                                "Stereo Camera",
                                "Failed",
                                "Insufficient poses"
                            ),
                            CalibrationRecord(
                                "2025-10-12 11:20",
                                "GSR Sensor",
                                "Success",
                                "92/100 quality"
                            )
                        ).forEach { record ->
                            CalibrationRecordRow(record)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CalibrationMetric(label: String, value: String, highlight: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = if (highlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CalibrationRecordRow(record: CalibrationRecord) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(record.timestamp, style = MaterialTheme.typography.bodySmall)
            Text(
                record.type,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                record.status,
                style = MaterialTheme.typography.labelSmall,
                color = if (record.status == "Success")
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
            Text(
                record.details,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private data class CalibrationRecord(
    val timestamp: String,
    val type: String,
    val status: String,
    val details: String
)


@Preview
@Composable
private fun CalibrationScreenPreview() {
    BuccancsTheme {
        Surface {
            CalibrationScreen()
        }
    }
}
