package com.buccancs.ui.topdon.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.ThermalImage
import com.buccancs.ui.theme.Spacing
import java.time.format.DateTimeFormatter

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun ImageDetailRoute(
    imageId: String,
    onNavigateUp: () -> Unit,
    viewModel: ImageDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ImageDetailScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onDelete = {
            viewModel.deleteImage(
                imageId
            )
        },
        onShare = {
            viewModel.shareImage(
                imageId
            )
        },
        onExport = {
            viewModel.exportImage(
                imageId
            )
        }
    )
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun ImageDetailScreen(
    state: ImageDetailUiState,
    onNavigateUp: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit,
    onExport: () -> Unit
) {
    var showMenu by remember {
        mutableStateOf(
            false
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    state.image?.let { image ->
                        Text(
                            text = image.timestamp.format(
                                DateTimeFormatter.ofPattern(
                                    "dd MMM yyyy, HH:mm"
                                )
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            showMenu =
                                true
                        }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options"
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = {
                            showMenu =
                                false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Edit"
                                )
                            },
                            onClick = {
                                showMenu =
                                    false
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = null
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Export"
                                )
                            },
                            onClick = {
                                showMenu =
                                    false
                                onExport()
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Share,
                                    contentDescription = null
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Delete"
                                )
                            },
                            onClick = {
                                showMenu =
                                    false
                                onDelete()
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        bottomBar = {
            state.image?.let { image ->
                BottomAppBar(
                    containerColor = Color.Black.copy(
                        alpha = 0.9f
                    )
                ) {
                    ImageMetadataBar(
                        image
                    )
                }
            }
        },
        containerColor = Color.Black,
        contentWindowInsets = WindowInsets(
            0,
            0,
            0,
            0
        )
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
        ) {
            state.image?.let { image ->
                ZoomableImageView(
                    image = image,
                    modifier = Modifier.fillMaxSize()
                )
            }

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun ZoomableImageView(
    image: ThermalImage,
    modifier: Modifier = Modifier
) {
    var scale by remember {
        mutableFloatStateOf(
            1f
        )
    }
    var offsetX by remember {
        mutableFloatStateOf(
            0f
        )
    }
    var offsetY by remember {
        mutableFloatStateOf(
            0f
        )
    }

    val state =
        rememberTransformableState { zoomChange, panChange, _ ->
            scale =
                (scale * zoomChange).coerceIn(
                    1f,
                    5f
                )

            val maxX =
                (image.width * scale - image.width) / 2
            val maxY =
                (image.height * scale - image.height) / 2

            offsetX =
                (offsetX + panChange.x).coerceIn(
                    -maxX,
                    maxX
                )
            offsetY =
                (offsetY + panChange.y).coerceIn(
                    -maxY,
                    maxY
                )
        }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Color.Black
            )
            .transformable(
                state = state
            )
            .pointerInput(
                Unit
            ) {
                detectTapGestures(
                    onDoubleTap = {
                        if (scale > 1f) {
                            scale =
                                1f
                            offsetX =
                                0f
                            offsetY =
                                0f
                        } else {
                            scale =
                                2f
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(
                    image.width.dp,
                    image.height.dp
                )
                .background(
                    MaterialTheme.colorScheme.surfaceVariant
                )
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ZoomIn,
                contentDescription = null,
                modifier = Modifier.size(
                    64.dp
                ),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                    alpha = 0.3f
                )
            )
        }

        if (scale > 1f) {
            ZoomIndicator(
                scale = scale,
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(
                        Spacing.Medium
                    )
            )
        }
    }
}

@Composable
private fun ZoomIndicator(
    scale: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Black.copy(
                    alpha = 0.7f
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(
                horizontal = Spacing.SmallMedium,
                vertical = Spacing.ExtraSmall
            )
    ) {
        Text(
            text = "${(scale * 100).toInt()}%",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}

@Composable
private fun ImageMetadataBar(
    image: ThermalImage
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Spacing.Medium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Temperature Range",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(
                    alpha = 0.7f
                )
            )
            Text(
                text = "${image.minTemperature.toInt()}°C - ${image.maxTemperature.toInt()}°C",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Average",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(
                    alpha = 0.7f
                )
            )
            Text(
                text = "${image.avgTemperature.toInt()}°C",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Resolution",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(
                    alpha = 0.7f
                )
            )
            Text(
                text = "${image.width}×${image.height}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}
