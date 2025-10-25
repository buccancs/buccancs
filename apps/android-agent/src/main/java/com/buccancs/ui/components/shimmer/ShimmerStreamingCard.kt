package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.Spacing

@Composable
fun ShimmerStreamingCard(
    isStreaming: Boolean,
    isConnected: Boolean,
    modifier: Modifier = Modifier
) {
    SectionCard(
        modifier = modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Streaming Control",
            style = MaterialTheme.typography.titleMedium
        )

        val message =
            when {
                !isConnected -> "Connect the device to prepare for streaming."
                isStreaming -> "Streaming is active as part of the current recording session."
                else -> "Start a recording session to begin streaming automatically."
            }
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ShimmerStreamingCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(
                Spacing.Large
            ),
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Large
            )
        ) {
            ShimmerStreamingCard(
                isStreaming = false,
                isConnected = false
            )

            ShimmerStreamingCard(
                isStreaming = false,
                isConnected = true
            )

            ShimmerStreamingCard(
                isStreaming = true,
                isConnected = true
            )
        }
    }
}
