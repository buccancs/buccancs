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
import androidx.compose.ui.unit.dp
import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncStatus
import kotlinx.datetime.toEpochMilliseconds
import java.util.Locale

@Composable
fun ClockPanel(
    status: TimeSyncStatus,
    history: List<TimeSyncObservation>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Time Synchronisation",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Offset: ${status.offsetMillis} ms")
            Text(text = "Round-trip: ${status.roundTripMillis} ms (filtered ${formatDouble(status.filteredRoundTripMillis)} ms)")
            Text(text = "Quality: ${status.quality.name.lowercase().replaceFirstChar { it.uppercase() }}")
            Text(text = "Drift estimate: ${formatDouble(status.driftEstimateMillisPerMinute)} ms/min")
            Text(text = "Regression slope: ${formatDouble(status.regressionSlopeMillisPerMinute)} ms/min")
            Text(text = "Samples collected: ${status.sampleCount}")
            if (history.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Recent Offsets", style = MaterialTheme.typography.labelLarge)
                history.takeLast(MAX_HISTORY_LINES).reversed().forEach { observation ->
                    val line = String.format(
                        Locale.US,
                        "%tT — %.2f ms (RTT %.2f)",
                        observation.timestamp.toEpochMilliseconds(),
                        observation.offsetMillis,
                        observation.roundTripMillis
                    )
                    Text(text = line, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

private fun formatDouble(value: Double): String =
    if (value.isFinite()) String.format(Locale.US, "%.2f", value) else "n/a"

private const val MAX_HISTORY_LINES = 6
