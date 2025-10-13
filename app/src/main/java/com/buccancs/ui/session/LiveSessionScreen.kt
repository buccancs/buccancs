package com.buccancs.ui.session

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.UploadStatus
import com.buccancs.ui.debug.ClockPanel
import com.buccancs.ui.debug.EncoderPanel
import java.util.Locale

@Composable
fun LiveSessionRoute(
    onNavigateUp: () -> Unit,
    viewModel: LiveSessionViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LiveSessionScreen(
        state = state,
        onNavigateUp = onNavigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveSessionScreen(
    state: LiveSessionUiState,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Live Session") },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                RecordingCard(state)
            }
            item {
                ClockPanel(
                    status = state.syncStatus,
                    history = state.syncHistory,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                EncoderPanel(
                    streams = state.streams,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                DeviceList(devices = state.devices)
            }
            if (state.uploads.isNotEmpty()) {
                item {
                    UploadList(uploads = state.uploads)
                }
            }
            if (state.events.isNotEmpty()) {
                item {
                    EventList(events = state.events)
                }
            }
            item {
                StorageCard(state = state)
            }
        }
    }
}

@Composable
private fun RecordingCard(state: LiveSessionUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Recording", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Lifecycle: ${state.recording.lifecycle}")
            Text(text = "Simulation: ${state.simulationEnabled}")
            val anchor = state.recording.anchor
            if (anchor != null) {
                Text(text = "Session ID: ${anchor.sessionId}")
                Text(text = "Reference: ${anchor.referenceTimestamp}")
                Text(text = "Clock offset: ${anchor.sharedClockOffsetMillis} ms")
            } else {
                Text(text = "Session idle")
            }
            Text(text = "Updated: ${state.recording.updatedAt}")
        }
    }
}

@Composable
private fun DeviceList(devices: List<SensorDevice>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Devices", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            if (devices.isEmpty()) {
                Text(text = "No devices registered.")
            } else {
                devices.forEach { device ->
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Text(text = "${device.displayName} (${device.type})", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Status: ${device.connectionStatus}")
                        Text(text = "Capabilities: ${device.capabilities.joinToString { it.name }}")
                        if (device.attributes.isNotEmpty()) {
                            Text(
                                text = "Attributes: ${
                                    device.attributes.entries.joinToString { "${it.key}=${it.value}" }
                                }",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
private fun UploadList(uploads: List<UploadStatus>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Uploads", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            uploads.forEach { upload ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(
                        text = "${upload.deviceId.value} — ${upload.streamType.name}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    val progress = if (upload.bytesTotal > 0) {
                        val pct = (upload.bytesTransferred.toDouble() / upload.bytesTotal.toDouble()) * 100.0
                        String.format(Locale.US, "%.1f%%", pct)
                    } else {
                        "n/a"
                    }
                    Text(text = "State: ${upload.state} · Progress: $progress")
                    Text(text = "Attempt: ${upload.attempt}")
                    upload.errorMessage?.let {
                        Text(text = "Error: $it", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}

@Composable
private fun EventList(events: List<DeviceEvent>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Recent Events", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            events.forEach { event ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = "${event.type} — ${event.label}")
                    Text(text = "Scheduled: ${event.scheduledAt}")
                    Text(text = "Received: ${event.receivedAt}")
                }
            }
        }
    }
}

@Composable
private fun StorageCard(state: LiveSessionUiState) {
    val storage = state.storage
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Storage", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Total: ${formatBytes(storage.totalBytes)}")
            Text(text = "Available: ${formatBytes(storage.availableBytes)}")
            Text(text = "Used: ${formatBytes(storage.usedBytes)}")
            Text(text = "Status: ${storage.status.name}")
            if (storage.sessions.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Sessions:", style = MaterialTheme.typography.labelLarge)
                storage.sessions.take(6).forEach { usage ->
                    Text(
                        text = "${usage.sessionId} — ${formatBytes(usage.bytes)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

private fun formatBytes(bytes: Long): String {
    if (bytes <= 0) return "0 B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (Math.log10(bytes.toDouble()) / Math.log10(1024.0)).toInt()
    val value = bytes / Math.pow(1024.0, digitGroups.toDouble())
    return String.format(Locale.US, "%.1f %s", value, units[digitGroups])
}

