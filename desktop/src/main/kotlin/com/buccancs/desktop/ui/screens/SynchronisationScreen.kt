package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.ui.components.BuccancsCard
import com.buccancs.desktop.ui.components.ScreenHeader
import com.buccancs.desktop.ui.components.StatusCard
import com.buccancs.desktop.ui.components.StatusType
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Synchronisation status screen showing time sync accuracy across devices
 */
@Composable
fun SynchronisationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.Large),
        verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
    ) {
        ScreenHeader(
            title = "Synchronisation",
            subtitle = "Time synchronisation status and clock offsets across all devices"
        )

        // Overall Sync Status
        StatusCard(
            title = "Synchronisation Health",
            status = StatusType.Success
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                SyncMetric("Max Offset", "4.2 ms", "< 10 ms target")
                SyncMetric("Avg Offset", "2.1 ms", "Excellent")
                SyncMetric("Last Sync", "3s ago", "Active")
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            // Individual Device Offsets
            item {
                BuccancsCard(
                    title = "Device Clock Offsets",
                    subtitle = "Milliseconds relative to orchestrator reference time"
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                        DeviceOffsetRow("Device-001", 2.3f, Color(0xFF4CAF50))
                        HorizontalDivider()
                        DeviceOffsetRow("Device-002", -4.1f, Color(0xFFFFC107))
                        HorizontalDivider()
                        DeviceOffsetRow("Device-003", 1.8f, Color(0xFF4CAF50))
                    }
                }
            }

            // Sync History
            item {
                BuccancsCard(
                    title = "Synchronisation Events",
                    subtitle = "Recent time sync operations"
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                        listOf(
                            SyncEvent("3s ago", "All devices", "Success", "Drift: 2.1 ms"),
                            SyncEvent("33s ago", "All devices", "Success", "Drift: 2.3 ms"),
                            SyncEvent("1m 3s ago", "All devices", "Success", "Drift: 1.9 ms"),
                            SyncEvent("1m 33s ago", "Device-002", "Retry", "Timeout, retried"),
                            SyncEvent("2m 3s ago", "All devices", "Success", "Drift: 2.4 ms")
                        ).forEach { event ->
                            SyncEventRow(event)
                        }
                    }
                }
            }

            // Sync Configuration
            item {
                BuccancsCard(
                    title = "Sync Configuration",
                    subtitle = "Time synchronisation parameters"
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                        ConfigRow("Sync Interval", "30 seconds")
                        ConfigRow("Sync Protocol", "NTP-style with round-trip")
                        ConfigRow("Max Acceptable Drift", "10 ms")
                        ConfigRow("Sample Count", "5 measurements per sync")
                        ConfigRow("Timeout", "2 seconds")
                    }
                }
            }
        }
    }
}

@Composable
private fun SyncMetric(label: String, value: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(
            value,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DeviceOffsetRow(deviceId: String, offsetMs: Float, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(deviceId, style = MaterialTheme.typography.titleSmall)
            Text(
                "${if (offsetMs >= 0) "+" else ""}${"%.2f".format(offsetMs)} ms",
                style = MaterialTheme.typography.bodyMedium,
                color = color
            )
        }

        // Visual offset indicator
        Canvas(
            modifier = Modifier
                .width(200.dp)
                .height(40.dp)
        ) {
            val centerY = size.height / 2
            val maxOffset = 10f
            val normalizedOffset = (offsetMs / maxOffset).coerceIn(-1f, 1f)
            val offsetX = size.width / 2 + (normalizedOffset * size.width / 2 * 0.8f)

            // Center line
            drawLine(
                color = Color.Gray,
                start = Offset(size.width / 2, 0f),
                end = Offset(size.width / 2, size.height),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
            )

            // Offset marker
            drawCircle(
                color = color,
                radius = 8f,
                center = Offset(offsetX, centerY)
            )
        }
    }
}

@Composable
private fun SyncEventRow(event: SyncEvent) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(event.time, style = MaterialTheme.typography.bodySmall)
            Text(
                event.target,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                event.status,
                style = MaterialTheme.typography.labelSmall,
                color = if (event.status == "Success")
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
            Text(
                event.details,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ConfigRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

private data class SyncEvent(
    val time: String,
    val target: String,
    val status: String,
    val details: String
)


@Preview
@Composable
private fun SynchronisationScreenPreview() {
    BuccancsTheme {
        Surface {
            SynchronisationScreen()
        }
    }
}
