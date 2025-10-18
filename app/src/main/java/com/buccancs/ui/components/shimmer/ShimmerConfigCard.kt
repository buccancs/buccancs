package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShimmerConfigCard(
    gsrRange: String,
    gsrRangeOptions: List<String>,
    sampleRate: Double,
    sampleRateOptions: List<Double>,
    onGsrRangeChange: (Int) -> Unit,
    onSampleRateChange: (Double) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    var gsrRangeExpanded by remember { mutableStateOf(false) }
    var sampleRateExpanded by remember { mutableStateOf(false) }

    SectionCard(
        modifier = modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spacing.ExtraSmall),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            Text(
                text = "Configuration",
                style = MaterialTheme.typography.titleMedium
            )

            ExposedDropdownMenuBox(
                expanded = gsrRangeExpanded,
                onExpandedChange = { gsrRangeExpanded = !gsrRangeExpanded && enabled }
            ) {
                OutlinedTextField(
                    value = gsrRange,
                    onValueChange = {},
                    readOnly = true,
                    enabled = enabled,
                    label = { Text("GSR Range") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = gsrRangeExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
                )

                ExposedDropdownMenu(
                    expanded = gsrRangeExpanded,
                    onDismissRequest = { gsrRangeExpanded = false }
                ) {
                    gsrRangeOptions.forEachIndexed { index, option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                onGsrRangeChange(index)
                                gsrRangeExpanded = false
                            }
                        )
                    }
                }
            }

            ExposedDropdownMenuBox(
                expanded = sampleRateExpanded,
                onExpandedChange = { sampleRateExpanded = !sampleRateExpanded && enabled }
            ) {
                OutlinedTextField(
                    value = "$sampleRate Hz",
                    onValueChange = {},
                    readOnly = true,
                    enabled = enabled,
                    label = { Text("Sample Rate") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sampleRateExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
                )

                ExposedDropdownMenu(
                    expanded = sampleRateExpanded,
                    onDismissRequest = { sampleRateExpanded = false }
                ) {
                    sampleRateOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text("$option Hz") },
                            onClick = {
                                onSampleRateChange(option)
                                sampleRateExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
