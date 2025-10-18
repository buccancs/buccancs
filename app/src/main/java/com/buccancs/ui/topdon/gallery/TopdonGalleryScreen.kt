package com.buccancs.ui.topdon.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
<<<<<<< Updated upstream
=======
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.Spacing
>>>>>>> Stashed changes
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.ThermalMediaItem
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopdonGalleryRoute(
    onNavigateUp: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: TopdonGalleryViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    TopdonGalleryScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onNavigateToDetail = onNavigateToDetail,
        onToggleSelection = viewModel::toggleSelection,
        onEnterSelectionMode = viewModel::enterSelectionMode,
        onExitSelectionMode = viewModel::exitSelectionMode,
        onDeleteSelected = viewModel::deleteSelected,
        onShareSelected = viewModel::shareSelected
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopdonGalleryScreen(
    state: GalleryUiState,
    onNavigateUp: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    onToggleSelection: (String) -> Unit,
    onEnterSelectionMode: (String) -> Unit,
    onExitSelectionMode: () -> Unit,
    onDeleteSelected: () -> Unit,
    onShareSelected: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (state.selectionMode) {
                            "${state.selectedItems.size} selected"
                        } else {
                            "Gallery"
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (state.selectionMode) {
                            onExitSelectionMode()
                        } else {
                            onNavigateUp()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    if (state.selectionMode) {
                        IconButton(onClick = onShareSelected) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share"
                            )
                        }
                        IconButton(onClick = onDeleteSelected) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                        }
                    } else {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        GalleryContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            items = state.items,
            selectionMode = state.selectionMode,
            selectedItems = state.selectedItems,
            isLoading = state.isLoading,
            onItemClick = { id ->
                if (state.selectionMode) {
                    onToggleSelection(id)
                } else {
                    onNavigateToDetail(id)
                }
            },
            onItemLongClick = { id ->
                if (!state.selectionMode) {
                    onEnterSelectionMode(id)
                }
            }
        )
    }
}

@Composable
private fun GalleryContent(
    modifier: Modifier = Modifier,
    items: List<ThermalMediaItem>,
    selectionMode: Boolean,
    selectedItems: Set<String>,
    isLoading: Boolean,
    onItemClick: (String) -> Unit,
    onItemLongClick: (String) -> Unit
) {
    when {
        isLoading && items.isEmpty() -> LoadingGalleryState(modifier)
        items.isEmpty() -> EmptyGalleryState(modifier)
        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = modifier,
                contentPadding = PaddingValues(Spacing.ExtraSmall),
                horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
                verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
            ) {
                items(items, key = { it.id }) { item ->
                    GalleryItemTile(
                        item = item,
                        isSelected = selectedItems.contains(item.id),
                        selectionMode = selectionMode,
                        onClick = { onItemClick(item.id) },
                        onLongClick = { onItemLongClick(item.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun GalleryItemTile(
    item: ThermalMediaItem,
    isSelected: Boolean,
    selectionMode: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.Size48),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(Spacing.ExtraSmall)
            ) {
                Text(
                    text = item.timestamp.format(DateTimeFormatter.ofPattern("HH:mm")),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
                when (item) {
                    is ThermalMediaItem.Image -> {
                        Text(
                            text = "${item.image.maxTemperature.toInt()}°C",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }

                    is ThermalMediaItem.Video -> {
                        Text(
                            text = "${item.video.duration / 1000}s",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }
                }
            }

            if (selectionMode) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(Spacing.ExtraSmall)
                        .size(Dimensions.IconSizeDefault)
                        .background(
                            color = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.White.copy(alpha = 0.7f)
                            },
                            shape = MaterialTheme.shapes.small
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Selected",
                            tint = Color.White,
                            modifier = Modifier.size(Dimensions.IconSizeSmall)
                        )
                    }
                }
            }

            if (item is ThermalMediaItem.Video) {
                Text(
                    text = "VIDEO",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = Spacing.ExtraSmall, vertical = Spacing.ExtraSmall / 2)
                )
            }
        }
    }
}

@Composable
fun EmptyGalleryState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Image,
            contentDescription = null,
            modifier = Modifier.size(Dimensions.Size80),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        )
        Spacer(modifier = Modifier.height(Spacing.Medium))
        Text(
            text = "No images yet",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "Capture thermal images to see them here",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun LoadingGalleryState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
