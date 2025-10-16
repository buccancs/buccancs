package com.buccancs.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.data.recording.manifest.ArtifactEntry
import com.buccancs.data.recording.manifest.BookmarkEntry
import com.buccancs.data.recording.manifest.DeviceManifest
import com.buccancs.data.recording.manifest.EventEntry
import com.buccancs.ui.components.AnimatedTonalButton
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing
import java.util.*

@Composable
fun SessionDetailRoute(
    onNavigateUp: () -> Unit,
    viewModel: SessionDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SessionDetailScreen(
        state = state,
        onRefresh = viewModel::refresh,
        onNavigateUp = onNavigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDetailScreen(
    state: SessionDetailUiState,
    onRefresh: () -> Unit,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Session ${state.sessionId}") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        when {
            state.isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Loading session...")
                }
            }

            state.errorMessage != null -> {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Unable to load session",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = state.errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        FilledTonalButton(onClick = onRefresh) {
                            Text(text = "Retry")
                        }
                    }
                }
            }

            state.manifest == null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Manifest unavailable.")
                }
            }

            else -> {
                val manifest = state.manifest
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("session-detail-list")
                        .padding(padding)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        SummaryCard(state = state)
                    }
                    item {
                        DeviceList(devices = manifest.devices)
                    }
                    if (manifest.artifacts.isNotEmpty()) {
                        item {
                            ArtifactList(artifacts = manifest.artifacts)
                        }
                    }
                    if (manifest.events.isNotEmpty()) {
                        item {
                            EventList(events = manifest.events)
                        }
                    }
                    if (manifest.bookmarks.isNotEmpty()) {
                        item {
                            BookmarkList(bookmarks = manifest.bookmarks)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SummaryCard(
    state: SessionDetailUiState,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    )
) {
    val manifest = state.manifest ?: return
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("session-summary")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Summary",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    InfoRow("Started", manifest.startedAt)
                    manifest.endedAt?.let { InfoRow("Ended", it) }
                    manifest.durationMillis?.let { duration ->
                        InfoRow("Duration", formatDuration(duration))
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatChip(
                    label = "Artifacts",
                    value = manifest.artifacts.size.toString(),
                    modifier = Modifier.weight(1f)
                )
                StatChip(
                    label = "Size",
                    value = formatBytes(state.totalBytes),
                    modifier = Modifier.weight(1f)
                )
            }

            if (manifest.simulation) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "Simulation Mode",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun StatChip(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
private fun DeviceList(devices: List<DeviceManifest>) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("session-devices")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Devices",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                devices.forEach { device ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = device.deviceId,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(text = "Name: ${device.displayName}")
                            Text(text = "Type: ${device.type}")
                            if (device.simulated) {
                                Text(
                                    text = "Simulated: ${device.simulated}",
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            if (device.capabilities.isNotEmpty()) {
                                Text(text = "Capabilities: ${device.capabilities.joinToString()}")
                            }
                            if (device.attributes.isNotEmpty()) {
                                Text(
                                    text = "Attributes: ${
                                        device.attributes.entries.joinToString { "${it.key}=${it.value}" }
                                    }",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ArtifactList(artifacts: List<ArtifactEntry>) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("session-artifacts")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Artifacts",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                artifacts.forEach { artifact ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "${artifact.streamType} - ${artifact.fileName()}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Device: ${artifact.deviceId}",
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Size: ${formatBytes(artifact.sizeBytes)}",
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Captured at: ${artifact.capturedEpochMs}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Mime: ${artifact.mimeType}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EventList(events: List<EventEntry>) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("session-events")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Events",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                events.forEach { event ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "${event.type} - ${event.eventId}",
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            event.label?.let {
                                Text(
                                    text = "Label: $it",
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                            event.scheduledEpochMs?.let {
                                Text(
                                    text = "Scheduled: $it",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                            event.receivedEpochMs?.let {
                                Text(
                                    text = "Received: $it",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BookmarkList(bookmarks: List<BookmarkEntry>) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("session-bookmarks")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Bookmarks",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                bookmarks.forEach { bookmark ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = bookmark.label.ifBlank { "Bookmark" },
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                            Text(
                                text = "Timestamp: ${bookmark.timestampEpochMs}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun ArtifactEntry.fileName(): String =
    relativePath?.substringAfterLast('/') ?: contentUri?.substringAfterLast('/') ?: streamType.lowercase()

private fun formatBytes(bytes: Long): String {
    if (bytes <= 0) return "0 B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (Math.log10(bytes.toDouble()) / Math.log10(1024.0)).toInt()
    val value = bytes / Math.pow(1024.0, digitGroups.toDouble())
    return String.format(Locale.US, "%.1f %s", value, units[digitGroups])
}

private fun formatDuration(millis: Long): String {
    val seconds = millis / 1000
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remaining = seconds % 60
    return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, remaining)
}
