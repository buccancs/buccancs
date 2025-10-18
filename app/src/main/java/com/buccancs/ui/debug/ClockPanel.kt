package com.buccancs.ui.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncStatus
import java.util.Locale

@Composable
fun ClockPanel(
    status: TimeSyncStatus,
    history: List<TimeSyncObservation>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .testTag(
                "live-time-sync-card"
            )
            .semantics(
                mergeDescendants = true
            ) {
                val description =
                    buildString {
                        append(
                            "Time synchronisation. "
                        )
                        append(
                            "Offset ${status.offsetMillis} milliseconds. "
                        )
                        append(
                            "Round trip ${status.roundTripMillis} milliseconds. "
                        )
                        append(
                            "Quality ${
                                status.quality.name.lowercase()
                                    .replaceFirstChar { it.uppercase() }
                            }. "
                        )
                        append(
                            "Drift estimate ${
                                formatDouble(
                                    status.driftEstimateMillisPerMinute
                                )
                            } milliseconds per minute. "
                        )
                        append(
                            "Regression slope ${
                                formatDouble(
                                    status.regressionSlopeMillisPerMinute
                                )
                            } milliseconds per minute. "
                        )
                        append(
                            "Samples collected ${status.sampleCount}."
                        )
                    }
                contentDescription =
                    description
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(
                16.dp
            )
        ) {
            Text(
                text = "Time Synchronisation",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.testTag(
                    "time-sync-title"
                )
            )
            Spacer(
                modifier = Modifier.height(
                    8.dp
                )
            )
            Text(
                text = "Offset: ${status.offsetMillis} ms",
                modifier = Modifier.testTag(
                    "time-sync-offset"
                )
            )
            Text(
                text = "Round-trip: ${status.roundTripMillis} ms (filtered ${
                    formatDouble(
                        status.filteredRoundTripMillis
                    )
                } ms)",
                modifier = Modifier.testTag(
                    "time-sync-round-trip"
                )
            )
            Text(
                text = "Quality: ${
                    status.quality.name.lowercase()
                        .replaceFirstChar { it.uppercase() }
                }",
                modifier = Modifier.testTag(
                    "time-sync-quality"
                )
            )
            Text(
                text = "Drift estimate: ${
                    formatDouble(
                        status.driftEstimateMillisPerMinute
                    )
                } ms/min",
                modifier = Modifier.testTag(
                    "time-sync-drift"
                )
            )
            Text(
                text = "Regression slope: ${
                    formatDouble(
                        status.regressionSlopeMillisPerMinute
                    )
                } ms/min",
                modifier = Modifier.testTag(
                    "time-sync-regression"
                )
            )
            Text(
                text = "Samples collected: ${status.sampleCount}",
                modifier = Modifier.testTag(
                    "time-sync-samples"
                )
            )
            if (history.isNotEmpty()) {
                Spacer(
                    modifier = Modifier.height(
                        12.dp
                    )
                )
                Text(
                    text = "Recent Offsets",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.testTag(
                        "time-sync-recent-header"
                    )
                )
                history.takeLast(
                    MAX_HISTORY_LINES
                )
                    .reversed()
                    .forEachIndexed { index, observation ->
                        val line =
                            String.format(
                                Locale.US,
                                "%tT — %.2f ms (RTT %.2f)",
                                observation.timestamp.toEpochMilliseconds(),
                                observation.offsetMillis,
                                observation.roundTripMillis
                            )
                        Text(
                            text = line,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.testTag(
                                "time-sync-history-$index"
                            )
                        )
                    }
            }
        }
    }
}

private fun formatDouble(
    value: Double
): String =
    if (value.isFinite()) String.format(
        Locale.US,
        "%.2f",
        value
    ) else "n/a"

private const val MAX_HISTORY_LINES =
    6
