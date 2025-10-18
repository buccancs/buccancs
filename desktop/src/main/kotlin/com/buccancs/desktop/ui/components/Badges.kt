package com.buccancs.desktop.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Status badge component for displaying device states, recording status, etc.
 * Follows Material 3 assistant chip pattern
 */
@Composable
fun StatusBadge(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = Spacing.Small,
                vertical = Spacing.ExtraSmall
            ),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

/**
 * Semantic status badges for common states
 */
@Composable
fun ConnectedBadge(
    modifier: Modifier = Modifier
) {
    StatusBadge(
        text = "Connected",
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
fun DisconnectedBadge(
    modifier: Modifier = Modifier
) {
    StatusBadge(
        text = "Disconnected",
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.errorContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer
    )
}

@Composable
fun RecordingBadge(
    modifier: Modifier = Modifier
) {
    StatusBadge(
        text = "Recording",
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
    )
}

@Composable
fun IdleBadge(
    modifier: Modifier = Modifier
) {
    StatusBadge(
        text = "Idle",
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
