package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.ui.components.BuccancsCard
import com.buccancs.desktop.ui.components.BuccancsOutlinedCard
import com.buccancs.desktop.ui.components.PrimaryButton
import com.buccancs.desktop.ui.components.SecondaryButton
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * File explorer for browsing session data and artefacts
 */
@Composable
fun FileExplorerScreen() {
    var currentPath by remember { mutableStateOf("~/.buccancs/sessions") }
    var selectedFile by remember { mutableStateOf<FileItem?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        // File browser
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(Spacing.Large),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            Text(
                "File Explorer",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )

            // Breadcrumb navigation
            BuccancsCard(title = "Location") {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Folder, "Location", Modifier.size(20.dp))
                    Text(
                        currentPath,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // File list
            BuccancsCard(
                title = "Files",
                subtitle = "Session data and recordings"
            ) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 600.dp),
                    verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
                ) {
                    items(getSampleFiles()) { file ->
                        FileListItem(
                            file = file,
                            selected = selectedFile == file,
                            onClick = { selectedFile = file }
                        )
                    }
                }
            }

            // Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                SecondaryButton(
                    text = "Open Location",
                    onClick = { /* Open */ }
                )
                SecondaryButton(
                    text = "Refresh",
                    onClick = { /* Refresh */ }
                )
            }
        }

        // File details sidebar
        Surface(
            modifier = Modifier
                .width(350.dp)
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 1.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Spacing.Large),
                verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
            ) {
                if (selectedFile != null) {
                    Text(
                        "File Details",
                        style = MaterialTheme.typography.titleLarge
                    )

                    BuccancsOutlinedCard(title = selectedFile!!.name) {
                        Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                            DetailRow("Type", selectedFile!!.type)
                            DetailRow("Size", selectedFile!!.size)
                            DetailRow("Modified", selectedFile!!.modified)

                            if (selectedFile!!.type == "Video") {
                                HorizontalDivider()
                                DetailRow("Resolution", "1920x1080")
                                DetailRow("Duration", "3:05")
                                DetailRow("Codec", "H.264")
                                DetailRow("Frame Rate", "30 fps")
                            }

                            if (selectedFile!!.type == "CSV") {
                                HorizontalDivider()
                                DetailRow("Rows", "128,547")
                                DetailRow("Sample Rate", "128 Hz")
                                DetailRow("Channels", "GSR, PPG")
                            }
                        }
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                        PrimaryButton(
                            text = "Open File",
                            onClick = { /* Open */ },
                            modifier = Modifier.fillMaxWidth()
                        )

                        SecondaryButton(
                            text = "Export",
                            onClick = { /* Export */ },
                            modifier = Modifier.fillMaxWidth()
                        )

                        SecondaryButton(
                            text = "Show in Folder",
                            onClick = { /* Show */ },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    SecondaryButton(
                        text = "Delete",
                        onClick = { /* Delete */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
                        ) {
                            Icon(
                                Icons.Default.InsertDriveFile,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                "Select a file",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FileListItem(
    file: FileItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        color = if (selected)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.Small),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                when (file.type) {
                    "Folder" -> Icons.Default.Folder
                    "Video" -> Icons.Default.VideoFile
                    "CSV" -> Icons.Default.TableChart
                    "JSON" -> Icons.Default.DataObject
                    else -> Icons.Default.InsertDriveFile
                },
                contentDescription = file.type,
                modifier = Modifier.size(24.dp),
                tint = if (selected)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    file.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (selected)
                        MaterialTheme.colorScheme.onPrimaryContainer
                    else
                        MaterialTheme.colorScheme.onSurface
                )
                Text(
                    "${file.type} • ${file.size}",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selected)
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun getSampleFiles(): List<FileItem> {
    return listOf(
        FileItem("session-2025-10-16-001", "Folder", "—", "16 Oct 10:30"),
        FileItem("rgb_device001.mp4", "Video", "458 MB", "16 Oct 10:35"),
        FileItem("thermal_device001.mp4", "Video", "234 MB", "16 Oct 10:35"),
        FileItem("gsr_device001.csv", "CSV", "12 MB", "16 Oct 10:35"),
        FileItem("session_manifest.json", "JSON", "4 KB", "16 Oct 10:35"),
        FileItem("session-2025-10-15-003", "Folder", "—", "15 Oct 16:20"),
        FileItem("rgb_device002.mp4", "Video", "512 MB", "15 Oct 16:25"),
        FileItem("thermal_device002.mp4", "Video", "256 MB", "15 Oct 16:25"),
        FileItem("gsr_device002.csv", "CSV", "15 MB", "15 Oct 16:25"),
        FileItem("bookmarks.json", "JSON", "2 KB", "15 Oct 16:25")
    )
}

private data class FileItem(
    val name: String,
    val type: String,
    val size: String,
    val modified: String
)


@Preview
@Composable
private fun FileExplorerScreenPreview() {
    BuccancsTheme {
        Surface {
            FileExplorerScreen()
        }
    }
}
