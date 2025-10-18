package com.topdon.tc001.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.io.File

data class GalleryImage(
    val id: String,
    val file: File,
    val thumbnailPath: String,
    val timestamp: Long
)

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun GalleryScreen(
    images: List<GalleryImage>,
    isLoading: Boolean,
    onImageClick: (GalleryImage) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Gallery"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    paddingValues
                )
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                images.isEmpty() -> {
                    EmptyGalleryContent(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                else -> {
                    ImageGrid(
                        images = images,
                        onImageClick = onImageClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ImageGrid(
    images: List<GalleryImage>,
    onImageClick: (GalleryImage) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(
            3
        ),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            8.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            8.dp
        )
    ) {
        items(
            images,
            key = { it.id }) { image ->
            ImageThumbnail(
                image = image,
                onClick = {
                    onImageClick(
                        image
                    )
                }
            )
        }
    }
}

@Composable
private fun ImageThumbnail(
    image: GalleryImage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(
                1f
            )
            .clickable(
                onClick = onClick
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        AsyncImage(
            model = image.file,
            contentDescription = "Thermal image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            error = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }
}

@Composable
private fun EmptyGalleryContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            32.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        )
    ) {
        Icon(
            imageVector = Icons.Default.Image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(
                0.3f
            )
                .aspectRatio(
                    1f
                ),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = "No images yet",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Thermal images will appear here",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
