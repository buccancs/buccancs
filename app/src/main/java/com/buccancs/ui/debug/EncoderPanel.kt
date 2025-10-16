package com.buccancs.ui.debug

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buccancs.domain.model.SensorStreamStatus
import java.util.*

@Composable
fun EncoderPanel(
    streams: List<SensorStreamStatus>,
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
                text = "Stream Telemetry",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            streams.forEach { status ->
                StreamRow(status = status)
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (streams.isEmpty()) {
                Text(
                    text = "No active streams",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun StreamRow(status: SensorStreamStatus) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "${status.deviceId.value} — ${status.streamType.name}",
            style = MaterialTheme.typography.bodyMedium
        )
        val rate = when {
            status.sampleRateHz != null -> formatDouble(status.sampleRateHz)
            status.frameRateFps != null -> formatDouble(status.frameRateFps)
            else -> "n/a"
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Rate: $rate ${if (status.sampleRateHz != null) "Hz" else "fps"}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Streaming: ${status.isStreaming}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
        }
        Text(
            text = "Buffered: ${formatDouble(status.bufferedDurationSeconds ?: 0.0)} s · Simulated: ${status.isSimulated}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Last sample: ${status.lastSampleTimestamp?.toString() ?: "n/a"}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

private fun formatDouble(value: Double?): String =
    value?.let { String.format(Locale.US, "%.2f", it) } ?: "n/a"
