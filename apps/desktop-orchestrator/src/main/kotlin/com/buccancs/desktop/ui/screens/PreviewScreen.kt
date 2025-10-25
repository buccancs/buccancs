package com.buccancs.desktop.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.domain.model.PreviewModality
import com.buccancs.desktop.ui.state.PreviewStreamState
import com.buccancs.desktop.ui.theme.Spacing
import com.buccancs.desktop.ui.util.decodePreviewImage
import com.buccancs.desktop.viewmodel.AppViewModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Dedicated live preview workspace that surfaces real camera frames with basic controls.
 */
@Composable
fun PreviewScreen(
    viewModel: AppViewModel
) {
    val state by viewModel.uiState.collectAsState()
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
    val formatter =
        remember {
            DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss"
            )
                .withZone(
                    ZoneId.systemDefault()
                )
        }

    val filteredPreviews =
        state.previews.filter { preview ->
            when (preview.modality) {
                PreviewModality.THERMAL -> showThermal
                PreviewModality.RGB -> showRgb
            }
        }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PreviewToolbar(
            gridLayout = gridLayout,
            onToggleLayout = { layout ->
                gridLayout =
                    layout
            },
            showThermal = showThermal,
            showRgb = showRgb,
            onToggleThermal = {
                showThermal =
                    !showThermal
            },
            onToggleRgb = {
                showRgb =
                    !showRgb
            }
        )

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
            if (filteredPreviews.isEmpty()) {
                item {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(
                                16f / 9f
                            ),
                        tonalElevation = 2.dp
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No preview frames received",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(
                    items = filteredPreviews,
                    key = { preview ->
                        "${preview.deviceId}-${preview.cameraId}"
                    }
                ) { preview ->
                    PreviewCard(
                        preview = preview,
                        formatter = formatter
                    )
                }
            }
        }
    }
}

@Composable
private fun PreviewToolbar(
    gridLayout: GridLayout,
    onToggleLayout: (GridLayout) -> Unit,
    showThermal: Boolean,
    showRgb: Boolean,
    onToggleThermal: () -> Unit,
    onToggleRgb: () -> Unit
) {
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
                    onClick = onToggleThermal,
                    label = {
                        Text(
                            "Thermal"
                        )
                    },
                    leadingIcon = if (showThermal) {
                        {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(
                                    18.dp
                                )
                            )
                        }
                    } else null
                )

                FilterChip(
                    selected = showRgb,
                    onClick = onToggleRgb,
                    label = {
                        Text(
                            "RGB"
                        )
                    },
                    leadingIcon = if (showRgb) {
                        {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(
                                    18.dp
                                )
                            )
                        }
                    } else null
                )

                IconButton(
                    onClick = {
                        val next =
                            when (gridLayout) {
                                GridLayout.Single -> GridLayout.Grid2x2
                                GridLayout.Grid2x2 -> GridLayout.Grid3x3
                                GridLayout.Grid3x3 -> GridLayout.Single
                            }
                        onToggleLayout(
                            next
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.GridView,
                        contentDescription = "Change layout"
                    )
                }

                IconButton(
                    onClick = { /* Fullscreen TBD */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Fullscreen,
                        contentDescription = "Fullscreen"
                    )
                }
            }
        }
    }
}

@Composable
private fun PreviewCard(
    preview: PreviewStreamState,
    formatter: DateTimeFormatter
) {
    val image =
        remember(
            preview.payload
        ) {
            decodePreviewImage(
                preview.payload
            )
        }
    val modeColour =
        when (preview.modality) {
            PreviewModality.THERMAL -> Color(
                0xFF1A1A2E
            )

            PreviewModality.RGB -> Color(
                0xFF0F1419
            )
        }
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
            if (image != null) {
                Image(
                    bitmap = image,
                    contentDescription = "${preview.deviceId}-${preview.cameraId}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            modeColour
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    val icon =
                        when (preview.modality) {
                            PreviewModality.THERMAL -> Icons.Default.Thermostat
                            PreviewModality.RGB -> Icons.Default.Videocam
                        }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            icon,
                            contentDescription = null,
                            modifier = Modifier.size(
                                48.dp
                            ),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(
                            modifier = Modifier.height(
                                Spacing.Small
                            )
                        )
                        Text(
                            "Preview unavailable",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            PreviewOverlay(
                preview = preview,
                formatter = formatter
            )
        }
    }
}

@Composable
private fun PreviewOverlay(
    preview: PreviewStreamState,
    formatter: DateTimeFormatter
) {
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
                ),
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
                Text(
                    "${preview.deviceId} – ${preview.cameraId}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    "${preview.width}x${preview.height} • ${
                        preview.modality.name.lowercase(Locale.ROOT)
                            .replaceFirstChar { it.titlecase(Locale.getDefault()) }
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Latency ${
                        String.format(
                            Locale.US,
                            "%.1f ms",
                            preview.latencyMs
                        )
                    } • Received ${
                        formatter.format(
                            preview.receivedAt
                        )
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "FPS ${
                        String.format(
                            Locale.US,
                            "%.1f",
                            preview.averageFps
                        )
                    } • Drops ${preview.dropCount} • Max gap ${preview.maxGapMs} ms",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private enum class GridLayout {
    Single,
    Grid2x2,
    Grid3x3
}
