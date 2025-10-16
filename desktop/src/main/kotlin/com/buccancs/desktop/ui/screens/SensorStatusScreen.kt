package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.ui.components.*
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Sensor status screen showing all connected sensors across devices
 */
@Composable
fun SensorStatusScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.Large),
        verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
    ) {
        ScreenHeader(
            title = "Sensor Status",
            subtitle = "Real-time monitoring of all sensor connections and data streams"
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            // GSR Sensors
            item {
                SensorCategoryCard(
                    title = "GSR (Shimmer3)",
                    icon = Icons.Default.MonitorHeart,
                    sensors = listOf(
                        SensorInfo("Device-001", "Shimmer3 GSR+", true, "128 Hz", "2,457,891 samples"),
                        SensorInfo("Device-002", "Shimmer3 GSR+", false, "—", "—")
                    )
                )
            }
            
            // Thermal Cameras
            item {
                SensorCategoryCard(
                    title = "Thermal Cameras (Topdon TC001)",
                    icon = Icons.Default.Thermostat,
                    sensors = listOf(
                        SensorInfo("Device-001", "TC001 256x192", true, "25 FPS", "45,678 frames"),
                        SensorInfo("Device-002", "TC001 256x192", true, "25 FPS", "45,650 frames")
                    )
                )
            }
            
            // RGB Cameras
            item {
                SensorCategoryCard(
                    title = "RGB Cameras",
                    icon = Icons.Default.Videocam,
                    sensors = listOf(
                        SensorInfo("Device-001", "1920x1080@30fps", true, "30 FPS", "54,321 frames"),
                        SensorInfo("Device-002", "1920x1080@30fps", true, "30 FPS", "54,298 frames")
                    )
                )
            }
            
            // Microphones
            item {
                SensorCategoryCard(
                    title = "Microphones",
                    icon = Icons.Default.Mic,
                    sensors = listOf(
                        SensorInfo("Device-001", "48 kHz Mono", true, "48 kHz", "8,654,321 samples"),
                        SensorInfo("Device-002", "48 kHz Mono", true, "48 kHz", "8,653,987 samples")
                    )
                )
            }
        }
    }
}

@Composable
private fun SensorCategoryCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    sensors: List<SensorInfo>
) {
    BuccancsCard(
        title = title,
        subtitle = "${sensors.count { it.active }} / ${sensors.size} active"
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
            sensors.forEach { sensor ->
                SensorRow(sensor)
                if (sensor != sensors.last()) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun SensorRow(sensor: SensorInfo) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(sensor.deviceId, style = MaterialTheme.typography.titleSmall)
            Text(
                sensor.model,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Column(horizontalAlignment = Alignment.End) {
            if (sensor.active) {
                ConnectedBadge()
                Text(
                    "${sensor.sampleRate} • ${sensor.samplesCollected}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                DisconnectedBadge()
            }
        }
    }
}

private data class SensorInfo(
    val deviceId: String,
    val model: String,
    val active: Boolean,
    val sampleRate: String,
    val samplesCollected: String
)


@Preview
@Composable
private fun SensorStatusScreenPreview() {
    BuccancsTheme {
        Surface {
            SensorStatusScreen()
        }
    }
}
