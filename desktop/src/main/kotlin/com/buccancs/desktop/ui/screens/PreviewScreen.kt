package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Live preview screen showing real-time feeds from all cameras
 */
@Composable
fun PreviewScreen() {
    var gridLayout by remember {
        mutableStateOf(
            GridLayout.Grid2x2
        )
    }
    var showThermal by remember {
        mutableStateOf(
            true
        )
    }
    var showRgb by remember {
        mutableStateOf(
            true
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top bar with controls
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        Spacing.Medium
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Live Preview",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        Spacing.Small
                    )
                ) {
                    FilterChip(
                        selected = showThermal,
                        onClick = {
                            showThermal =
                                !showThermal
                        },
                        label = {
                            Text(
                                "Thermal"
                            )
                        },
                        leadingIcon = if (showThermal) {
                            {
                                Icon(
                                    Icons.Default.Check,
                                    "Selected",
                                    modifier = Modifier.size(
                                        18.dp
                                    )
                                )
                            }
                        } else null
                    )

                    FilterChip(
                        selected = showRgb,
                        onClick = {
                            showRgb =
                                !showRgb
                        },
                        label = {
                            Text(
                                "RGB"
                            )
                        },
                        leadingIcon = if (showRgb) {
                            {
                                Icon(
                                    Icons.Default.Check,
                                    "Selected",
                                    modifier = Modifier.size(
                                        18.dp
                                    )
                                )
                            }
                        } else null
                    )

                    IconButton(
                        onClick = { /* Fullscreen */ }) {
                        Icon(
                            Icons.Default.Fullscreen,
                            "Fullscreen"
                        )
                    }

                    IconButton(
                        onClick = {
                            gridLayout =
                                when (gridLayout) {
                                    GridLayout.Single -> GridLayout.Grid2x2
                                    GridLayout.Grid2x2 -> GridLayout.Grid3x3
                                    GridLayout.Grid3x3 -> GridLayout.Single
                                }
                        }) {
                        Icon(
                            Icons.Default.GridView,
                            "Layout"
                        )
                    }
                }
            }
        }

        // Preview grid
        val columns =
            when (gridLayout) {
                GridLayout.Single -> 1
                GridLayout.Grid2x2 -> 2
                GridLayout.Grid3x3 -> 3
            }

        LazyVerticalGrid(
            columns = GridCells.Fixed(
                columns
            ),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                Spacing.Medium
            ),
            horizontalArrangement = Arrangement.spacedBy(
                Spacing.Medium
            ),
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Medium
            )
        ) {
            if (showRgb) {
                items(
                    2
                ) { index ->
                    PreviewCard(
                        title = "Device-00${index + 1} RGB",
                        resolution = "1920x1080",
                        fps = "30",
                        latency = "45",
                        type = PreviewType.RGB
                    )
                }
            }

            if (showThermal) {
                items(
                    2
                ) { index ->
                    PreviewCard(
                        title = "Device-00${index + 1} Thermal",
                        resolution = "256x192",
                        fps = "25",
                        latency = "52",
                        type = PreviewType.Thermal
                    )
                }
            }
        }
    }
}

@Composable
private fun PreviewCard(
    title: String,
    resolution: String,
    fps: String,
    latency: String,
    type: PreviewType
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(
                16f / 9f
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Placeholder for actual video feed
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (type == PreviewType.Thermal)
                            Color(
                                0xFF1A1A2E
                            )
                        else
                            Color(
                                0xFF0F1419
                            )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        if (type == PreviewType.Thermal) Icons.Default.Thermostat else Icons.Default.Videocam,
                        contentDescription = null,
                        modifier = Modifier.size(
                            48.dp
                        ),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(
                        Modifier.height(
                            Spacing.Small
                        )
                    )
                    Text(
                        "Preview stream",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Overlay info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        Spacing.Small
                    )
            ) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.surface.copy(
                        alpha = 0.9f
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(
                            Spacing.Small
                        )
                    ) {
                        Text(
                            title,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Spacing.Small
                            )
                        ) {
                            Text(
                                "$resolution @ ${fps}fps",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                "•",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                "${latency}ms",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            // Recording indicator
            Surface(
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(
                        Spacing.Small
                    ),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.errorContainer
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = Spacing.Small,
                        vertical = 4.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(
                        4.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(
                                8.dp
                            )
                            .background(
                                MaterialTheme.colorScheme.error,
                                MaterialTheme.shapes.extraSmall
                            )
                    )
                    Text(
                        "REC",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
    }
}

private enum class GridLayout {
    Single, Grid2x2, Grid3x3
}

private enum class PreviewType {
    RGB, Thermal
}


@Preview
@Composable
private fun PreviewScreenPreview() {
    BuccancsTheme {
        Surface {
            PreviewScreen()
        }
    }
}
