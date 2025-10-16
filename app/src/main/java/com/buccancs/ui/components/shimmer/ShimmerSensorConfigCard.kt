package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.buccancs.ui.theme.Spacing

data class ShimmerSensor(
    val id: String,
    val name: String,
    val isEnabled: Boolean
)

@Composable
fun ShimmerSensorConfigCard(
    sensors: List<ShimmerSensor>,
    onSensorToggle: (String, Boolean) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            Text(
                text = "Enabled Sensors",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = Spacing.Small)
            )

            sensors.forEach { sensor ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = sensor.isEnabled,
                        onCheckedChange = { isChecked ->
                            if (enabled) {
                                onSensorToggle(sensor.id, isChecked)
                            }
                        },
                        enabled = enabled
                    )
                    Text(
                        text = sensor.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            if (sensors.isEmpty()) {
                Text(
                    text = "No sensors available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
