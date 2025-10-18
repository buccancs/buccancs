package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.Spacing
import java.util.Locale

@Composable
fun ShimmerDataCard(
    timestamp: Double?,
    accelX: Double?,
    accelY: Double?,
    accelZ: Double?,
    gsrData: Double?,
    modifier: Modifier = Modifier
) {
    SectionCard(
        modifier = modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Live Data",
            style = MaterialTheme.typography.titleMedium
        )

        if (timestamp != null) {
            DataRow(
                label = "Timestamp",
                value = String.format(
                    Locale.US,
                    "%.2f ms",
                    timestamp
                )
            )
        }

        if (accelX != null || accelY != null || accelZ != null) {
            Text(
                text = "Accelerometer (m/s^2)",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (accelX != null) {
                DataRow(
                    label = "X-Axis",
                    value = String.format(
                        Locale.US,
                        "%.3f",
                        accelX
                    )
                )
            }
            if (accelY != null) {
                DataRow(
                    label = "Y-Axis",
                    value = String.format(
                        Locale.US,
                        "%.3f",
                        accelY
                    )
                )
            }
            if (accelZ != null) {
                DataRow(
                    label = "Z-Axis",
                    value = String.format(
                        Locale.US,
                        "%.3f",
                        accelZ
                    )
                )
            }
        }

        if (gsrData != null) {
            Text(
                text = "Galvanic Skin Response",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            DataRow(
                label = "GSR",
                value = String.format(
                    Locale.US,
                    "%.2f kOhm",
                    gsrData
                )
            )
        }

        if (timestamp == null && accelX == null && accelY == null && accelZ == null && gsrData == null) {
            Text(
                text = "No data available. Start streaming to see live data.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DataRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ShimmerDataCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            ShimmerDataCard(
                timestamp = null,
                accelX = null,
                accelY = null,
                accelZ = null,
                gsrData = null
            )

            ShimmerDataCard(
                timestamp = 12345.67,
                accelX = 0.123,
                accelY = -0.456,
                accelZ = 9.789,
                gsrData = 125.45
            )
        }
    }
}
