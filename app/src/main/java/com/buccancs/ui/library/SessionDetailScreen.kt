package com.buccancs.ui.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.data.recording.manifest.ArtifactEntry
import com.buccancs.data.recording.manifest.DeviceManifest
import com.buccancs.data.recording.manifest.EventEntry
import java.util.Locale

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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Unable to load session",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(text = state.errorMessage, style = MaterialTheme.typography.bodyMedium)
                    androidx.compose.material3.Button(onClick = onRefresh) {
                        Text(text = "Retry")
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
                }
            }
        }
    }
}

@Composable
private fun SummaryCard(state: SessionDetailUiState) {
    val manifest = state.manifest ?: return
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Summary", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Started: ${manifest.startedAt}")
            manifest.endedAt?.let { Text(text = "Ended: $it") }
            manifest.durationMillis?.let { duration ->
                Text(text = "Duration: ${formatDuration(duration)}")
            }
            Text(text = "Artifacts: ${manifest.artifacts.size}")
            Text(text = "Total size: ${formatBytes(state.totalBytes)}")
            Text(text = "Simulation: ${manifest.simulation}")
        }
    }
}

@Composable
private fun DeviceList(devices: List<DeviceManifest>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Devices", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            devices.forEach { device ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = device.deviceId, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Name: ${device.displayName}")
                    Text(text = "Type: ${device.type}")
                    Text(text = "Simulated: ${device.simulated}")
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

@Composable
private fun ArtifactList(artifacts: List<ArtifactEntry>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Artifacts", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            artifacts.forEach { artifact ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = "${artifact.streamType} - ${artifact.fileName()}")
                    Text(text = "Device: ${artifact.deviceId}")
                    Text(text = "Size: ${formatBytes(artifact.sizeBytes)}")
                    Text(text = "Captured at: ${artifact.capturedEpochMs}")
                    Text(text = "Mime: ${artifact.mimeType}")
                }
            }
        }
    }
}

@Composable
private fun EventList(events: List<EventEntry>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Events", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            events.forEach { event ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = "${event.type} - ${event.eventId}")
                    event.label?.let { Text(text = "Label: $it") }
                    event.scheduledEpochMs?.let { Text(text = "Scheduled: $it") }
                    event.receivedEpochMs?.let { Text(text = "Received: $it") }
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
