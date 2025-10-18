package com.buccancs.ui.components.topdon

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Topdon-styled circular progress indicator
 */
@Composable
fun TopdonCircularProgress(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: androidx.compose.ui.unit.Dp = Dimensions.StrokeWidth
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth
    )
}

/**
 * Topdon-styled linear progress indicator
 */
@Composable
fun TopdonLinearProgress(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.outline
) {
    LinearProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor
    )
}

/**
 * Topdon-styled determinate linear progress
 */
@Composable
fun TopdonLinearProgressWithValue(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.outline
) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier,
        color = color,
        trackColor = trackColor
    )
}

/**
 * Full-screen loading overlay
 * Matches original app's loading states
 */
@Composable
fun TopdonLoadingOverlay(
    modifier: Modifier = Modifier,
    message: String? = null
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopdonCircularProgress(
                modifier = Modifier.size(48.dp)
            )
            if (message != null) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

/**
 * Shimmer effect for loading placeholders
 * Matches original app's skeleton loading states
 */
@Composable
fun TopdonShimmerEffect(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )

    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.surface
                    ),
                    start = Offset(translateAnim - 500f, translateAnim - 500f),
                    end = Offset(translateAnim, translateAnim)
                )
            )
    )
}

/**
 * Empty state component
 */
@Composable
fun TopdonEmptyState(
    message: String,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon?.invoke()
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = if (icon != null) 16.dp else 0.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF16131E)
@Composable
private fun TopdonCircularProgressPreview() {
    TopdonTheme {
        Box(modifier = Modifier.padding(24.dp)) {
            TopdonCircularProgress()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF16131E)
@Composable
private fun TopdonLinearProgressPreview() {
    TopdonTheme {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopdonLinearProgress(modifier = Modifier.fillMaxSize())
            TopdonLinearProgressWithValue(
                progress = 0.65f,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun TopdonLoadingOverlayPreview() {
    TopdonTheme {
        TopdonLoadingOverlay(message = "Connecting to device...")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF16131E)
@Composable
private fun TopdonEmptyStatePreview() {
    TopdonTheme {
        TopdonEmptyState(message = "No devices connected")
    }
}
