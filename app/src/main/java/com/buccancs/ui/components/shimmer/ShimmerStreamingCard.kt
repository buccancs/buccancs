package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buccancs.ui.theme.Spacing

@Composable
fun ShimmerStreamingCard(
    isStreaming: Boolean,
    isConnected: Boolean,
    onStartStreaming: () -> Unit,
    onStopStreaming: () -> Unit,
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
                text = "Streaming Control",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = if (isStreaming) "Device is streaming data" else "Device is idle",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                if (isStreaming) {
                    OutlinedButton(
                        onClick = onStopStreaming,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Stop,
                            contentDescription = null
                        )
                        Text("Stop Streaming", modifier = Modifier.padding(start = Spacing.Small))
                    }
                } else {
                    Button(
                        onClick = onStartStreaming,
                        enabled = isConnected,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null
                        )
                        Text("Start Streaming", modifier = Modifier.padding(start = Spacing.Small))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShimmerStreamingCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ShimmerStreamingCard(
                isStreaming = false,
                isConnected = false,
                onStartStreaming = {},
                onStopStreaming = {}
            )

            ShimmerStreamingCard(
                isStreaming = false,
                isConnected = true,
                onStartStreaming = {},
                onStopStreaming = {}
            )

            ShimmerStreamingCard(
                isStreaming = true,
                isConnected = true,
                onStartStreaming = {},
                onStopStreaming = {}
            )
        }
    }
}
