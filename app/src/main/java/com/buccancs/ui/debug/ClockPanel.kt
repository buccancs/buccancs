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
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.ui.theme.Spacing
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

@Composable
fun ClockPanel(
    status: TimeSyncStatus,
    history: List<TimeSyncObservation>,
    modifier: Modifier = Modifier
) {
    val qualityColor = when (status.quality) {
        TimeSyncQuality.GOOD -> MaterialTheme.colorScheme.primary
        TimeSyncQuality.FAIR -> MaterialTheme.colorScheme.tertiary
        TimeSyncQuality.POOR -> MaterialTheme.colorScheme.error
        TimeSyncQuality.UNKNOWN -> MaterialTheme.colorScheme.outline
    }

    val qualityIcon = when (status.quality) {
        TimeSyncQuality.GOOD -> Icons.Default.CheckCircle
        TimeSyncQuality.FAIR -> Icons.Default.Warning
        TimeSyncQuality.POOR -> Icons.Default.Error
        TimeSyncQuality.UNKNOWN -> Icons.Default.Help
    }

    Card(
        modifier = modifier
            .testTag("live-time-sync-card")
            .semantics(mergeDescendants = true) {
                val description = buildString {
                    append("Time synchronisation. ")
                    append("Offset ${status.offsetMillis} milliseconds. ")
                    append("Round trip ${status.roundTripMillis} milliseconds. ")
                    append(
                        "Quality ${
                            status.quality.name.lowercase().replaceFirstChar { it.uppercase() }
                        }. "
                    )
                    append("Drift estimate ${formatDouble(status.driftEstimateMillisPerMinute)} milliseconds per minute. ")
                    append("Regression slope ${formatDouble(status.regressionSlopeMillisPerMinute)} milliseconds per minute. ")
                    append("Samples collected ${status.sampleCount}.")
                }
                contentDescription = description
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium)
        ) {
            // Header with quality indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Time Synchronization",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.testTag("time-sync-title")
                )
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = qualityColor.copy(alpha = 0.15f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = qualityIcon,
                            contentDescription = null,
                            tint = qualityColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = status.quality.name.lowercase()
                                .replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.labelMedium,
                            color = qualityColor,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.testTag("time-sync-quality")
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.Medium))

            // Primary metrics grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                MetricCard(
                    label = "Clock Offset",
                    value = "${status.offsetMillis}",
                    unit = "ms",
                    modifier = Modifier
                        .weight(1f)
                        .testTag("time-sync-offset"),
                    highlight = abs(status.offsetMillis) > 10
                )
                MetricCard(
                    label = "Round Trip",
                    value = "${status.roundTripMillis}",
                    unit = "ms",
                    modifier = Modifier
                        .weight(1f)
                        .testTag("time-sync-round-trip"),
                    secondaryValue = "Filtered: ${formatDouble(status.filteredRoundTripMillis)} ms"
                )
            }

            Spacer(modifier = Modifier.height(Spacing.Small))

            // Secondary metrics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                MetricCard(
                    label = "Drift Rate",
                    value = formatDouble(status.driftEstimateMillisPerMinute),
                    unit = "ms/min",
                    modifier = Modifier
                        .weight(1f)
                        .testTag("time-sync-drift")
                )
                MetricCard(
                    label = "Samples",
                    value = "${status.sampleCount}",
                    unit = "",
                    modifier = Modifier
                        .weight(1f)
                        .testTag("time-sync-samples")
                )
            }

            // Advanced metric
            if (status.regressionSlopeMillisPerMinute.isFinite() && status.sampleCount > 3) {
                Spacer(modifier = Modifier.height(Spacing.Small))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(
                        modifier = Modifier.padding(Spacing.SmallMedium),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Regression Slope",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${formatDouble(status.regressionSlopeMillisPerMinute)} ms/min",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.testTag("time-sync-regression")
                        )
                    }
                }
            }

            // History section
            if (history.isNotEmpty()) {
                Spacer(modifier = Modifier.height(Spacing.Medium))
                Text(
                    text = "Recent Observations",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.testTag("time-sync-recent-header")
                )
                Spacer(modifier = Modifier.height(Spacing.Small))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Column(
                        modifier = Modifier.padding(Spacing.SmallMedium),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        history.takeLast(MAX_HISTORY_LINES).reversed()
                            .forEachIndexed { index, observation ->
                                HistoryRow(
                                    observation = observation,
                                    modifier = Modifier.testTag("time-sync-history-$index")
                                )
                            }
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricCard(
    label: String,
    value: String,
    unit: String,
    modifier: Modifier = Modifier,
    secondaryValue: String? = null,
    highlight: Boolean = false
) {
    Surface(
        modifier = modifier,
        color = if (highlight) {
            MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
        } else {
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
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
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = if (highlight) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.primary
                    }
                )
                if (unit.isNotEmpty()) {
                    Text(
                        text = unit,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
            if (secondaryValue != null) {
                Text(
                    text = secondaryValue,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun HistoryRow(
    observation: TimeSyncObservation,
    modifier: Modifier = Modifier
) {
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
    val timestamp = timeFormat.format(Date(observation.timestamp.toEpochMilliseconds()))

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = timestamp,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = String.format(Locale.US, "Δ %+.1f ms", observation.offsetMillis),
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = String.format(Locale.US, "RTT %.1f ms", observation.roundTripMillis),
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun formatDouble(value: Double): String =
    if (value.isFinite()) String.format(Locale.US, "%.2f", value) else "n/a"

private const val MAX_HISTORY_LINES = 6
