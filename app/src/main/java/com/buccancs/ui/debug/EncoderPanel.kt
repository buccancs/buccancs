package com.buccancs.ui.debug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.ui.theme.Spacing
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EncoderPanel(
    streams: List<SensorStreamStatus>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Stream Telemetry",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = if (streams.any { it.isStreaming }) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (streams.any { it.isStreaming }) Icons.Default.PlayArrow else Icons.Default.Stop,
                            contentDescription = null,
                            tint = if (streams.any { it.isStreaming }) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${streams.count { it.isStreaming }}/${streams.size} Active",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.Medium))

            if (streams.isEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "No active streams",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(Spacing.Medium)
                    )
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Spacing.Small)
                ) {
                    streams.forEach { status ->
                        StreamCard(status = status)
                    }
                }
            }
        }
    }
}

@Composable
private fun StreamCard(
    status: SensorStreamStatus
) {
    val streamColor = if (status.isStreaming) {
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = streamColor,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            // Header with device and stream type
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = status.deviceId.value,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = status.streamType.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    imageVector = if (status.isStreaming) Icons.Default.CheckCircle else Icons.Default.Error,
                    contentDescription = if (status.isStreaming) "Streaming" else "Not streaming",
                    tint = if (status.isStreaming) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    },
                    modifier = Modifier.size(20.dp)
                )
            }

            // Metrics grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                StreamMetricChip(
                    label = "Rate",
                    value = when {
                        status.sampleRateHz != null -> formatDouble(status.sampleRateHz)
                        status.frameRateFps != null -> formatDouble(status.frameRateFps)
                        else -> "n/a"
                    },
                    unit = when {
                        status.sampleRateHz != null -> "Hz"
                        status.frameRateFps != null -> "fps"
                        else -> ""
                    },
                    modifier = Modifier.weight(1f)
                )
                StreamMetricChip(
                    label = "Buffered",
                    value = formatDouble(status.bufferedDurationSeconds ?: 0.0),
                    unit = "s",
                    modifier = Modifier.weight(1f)
                )
            }

            // Buffer indicator
            status.bufferedDurationSeconds?.let { buffered ->
                val targetBuffer = 2.0 // Target buffer duration in seconds
                val bufferProgress = (buffered / targetBuffer).coerceIn(0.0, 1.0).toFloat()

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Buffer Health",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${(bufferProgress * 100).toInt()}%",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Medium,
                            color = when {
                                bufferProgress >= 0.7f -> MaterialTheme.colorScheme.primary
                                bufferProgress >= 0.3f -> MaterialTheme.colorScheme.tertiary
                                else -> MaterialTheme.colorScheme.error
                            }
                        )
                    }
                    LinearProgressIndicator(
                        progress = { bufferProgress },
                        modifier = Modifier.fillMaxWidth(),
                        color = when {
                            bufferProgress >= 0.7f -> MaterialTheme.colorScheme.primary
                            bufferProgress >= 0.3f -> MaterialTheme.colorScheme.tertiary
                            else -> MaterialTheme.colorScheme.error
                        },
                    )
                }
            }

            // Last sample timestamp
            status.lastSampleTimestamp?.let { timestamp ->
                val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.US)
                val formattedTime = timeFormat.format(Date(timestamp.toEpochMilliseconds()))
                val now = System.currentTimeMillis()
                val ageMs = now - timestamp.toEpochMilliseconds()
                val ageText = when {
                    ageMs < 1000 -> "just now"
                    ageMs < 60000 -> "${ageMs / 1000}s ago"
                    else -> "${ageMs / 60000}m ago"
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Last sample: $formattedTime",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = ageText,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (ageMs < 5000) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                    )
                }
            }

            // Simulation indicator
            if (status.isSimulated) {
                Surface(
                    color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "⚠ Simulated Data",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun StreamMetricChip(
    label: String,
    value: String,
    unit: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.primary
                )
                if (unit.isNotEmpty()) {
                    Text(
                        text = unit,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }
    }
}

private fun formatDouble(value: Double?): String =
    value?.let {
        String.format(Locale.US, "%.2f", it)
    } ?: "n/a"
