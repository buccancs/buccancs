package com.buccancs.ui.session

import androidx.annotation.ColorInt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.application.stimulus.StimulusCue
import com.buccancs.application.stimulus.StimulusState
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.PerformanceThrottleLevel
import com.buccancs.domain.model.RecordingBookmark
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadRecoveryRecord
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
        onNavigateUp = onNavigateUp,
        onAddBookmark = { viewModel.recordBookmark() },
        onTriggerStimulus = { viewModel.previewStimulus() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveSessionScreen(
    state: LiveSessionUiState,
    onNavigateUp: () -> Unit,
    onAddBookmark: () -> Unit,
    onTriggerStimulus: () -> Unit
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
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("live-list")
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    RecordingCard(state, onAddBookmark)
                }
                item {
                    StimulusPanel(state.stimulus, onTriggerStimulus)
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
                if (state.backlog.level != UploadBacklogLevel.NORMAL || state.backlog.queuedCount > 0) {
                    item {
                        BacklogCard(state.backlog)
                    }
                }
                if (state.recoveries.isNotEmpty()) {
                    item {
                        RecoveryList(records = state.recoveries)
                    }
                }
                if (state.events.isNotEmpty()) {
                    item {
                        EventList(events = state.events)
                    }
                }
                if (state.bookmarks.isNotEmpty()) {
                    item {
                        BookmarkList(bookmarks = state.bookmarks)
                    }
                }
                item {
                    StorageCard(state = state)
                }
            }
            StimulusOverlay(state.stimulus.activeCue)
        }
    }
}

@Composable
private fun RecordingCard(
    state: LiveSessionUiState, onAddBookmark: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Recording", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Lifecycle: ${state.recording.lifecycle}")
            Text(text = "Simulation: ${state.simulationEnabled}")
            val throttleColor = if (state.throttleLevel == PerformanceThrottleLevel.CONSERVE) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
            Text(text = "Throttle: ${state.throttleLevel}", color = throttleColor)
            val anchor = state.recording.anchor
            if (anchor != null) {
                Text(text = "Session ID: ${anchor.sessionId}")
                Text(text = "Reference: ${anchor.referenceTimestamp}")
                Text(text = "Clock offset: ${anchor.sharedClockOffsetMillis} ms")
            } else {
                Text(text = "Session idle")
            }
            Text(text = "Updated: ${state.recording.updatedAt}")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onAddBookmark) {
                Text(text = "Add Bookmark")
            }
        }
    }
}

@Composable
private fun StimulusPanel(state: StimulusState, onTriggerStimulus: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Stimulus", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (state.hasExternalDisplay) "External display ready" else "External display not detected",
                style = MaterialTheme.typography.bodyMedium
            )
            state.activeCue?.let { cue ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Active: ${cue.label}", style = MaterialTheme.typography.bodySmall)
            } ?: state.lastCue?.let { cue ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Last: ${cue.label}", style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onTriggerStimulus) {
                Text(text = "Preview Stimulus")
            }
        }
    }
}

@Composable
private fun DeviceList(devices: List<SensorDevice>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("live-devices"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Devices", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            if (devices.isEmpty()) {
                Text(
                    text = "No devices registered.",
                    modifier = Modifier.testTag("live-no-devices")
                )
            } else {
                devices.forEach { device ->
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Text(
                            text = "${device.displayName} (${device.type})",
                            style = MaterialTheme.typography.bodyMedium
                        )
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
        modifier = Modifier
            .fillMaxWidth()
            .testTag("live-uploads"),
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
private fun BacklogCard(state: UploadBacklogState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("live-backlog"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Upload Backlog", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Level: ${state.level}",
                style = MaterialTheme.typography.bodyMedium,
                color = when (state.level) {
                    UploadBacklogLevel.NORMAL -> MaterialTheme.colorScheme.onSurfaceVariant
                    UploadBacklogLevel.WARNING -> MaterialTheme.colorScheme.tertiary
                    UploadBacklogLevel.CRITICAL -> MaterialTheme.colorScheme.error
                }
            )
            Text(
                text = "Pending: ${state.queuedCount} artifacts · ${formatBytes(state.queuedBytes)} remaining",
                style = MaterialTheme.typography.bodySmall
            )
            state.message?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (state.level == UploadBacklogLevel.CRITICAL) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.tertiary
                    }
                )
            }
            if (state.level == UploadBacklogLevel.CRITICAL) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Newest artifacts are dropped until backlog clears. Review network/retention configuration.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
            if (state.perSessionQueued.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Session Breakdown", style = MaterialTheme.typography.labelLarge)
                state.perSessionQueued
                    .toList()
                    .sortedByDescending { it.second }
                    .take(6)
                    .forEach { (sessionId, count) ->
                        val bytes = state.perSessionBytes[sessionId] ?: 0L
                        Text(
                            text = "$sessionId — $count items (${formatBytes(bytes)})",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
            }
        }
    }
}

@Composable
private fun RecoveryList(records: List<UploadRecoveryRecord>) {
    if (records.isEmpty()) return
    val recent = records.sortedByDescending { it.timestamp }.take(12)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Upload Recovery", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            recent.forEach { record ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(
                        text = "${record.deviceId.value} — ${record.streamType.name} · Attempt ${record.attempt} · ${record.state}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    val networkLabel = buildString {
                        append(record.network.transport)
                        append(if (record.network.connected) " connected" else " offline")
                        if (record.network.metered) {
                            append(" · metered")
                        }
                    }
                    val networkColor = if (record.network.connected) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                    Text(
                        text = "Timestamp: ${record.timestamp}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Network: $networkLabel",
                        style = MaterialTheme.typography.bodySmall,
                        color = networkColor
                    )
                    Text(
                        text = "Bytes: ${formatBytes(record.bytesTransferred)} / ${formatBytes(record.bytesTotal)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    record.errorMessage?.let { message ->
                        Text(
                            text = "Error: $message",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
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
private fun BookmarkList(bookmarks: List<RecordingBookmark>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Bookmarks", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            bookmarks.forEach { bookmark ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = bookmark.label.ifBlank { "Bookmark" })
                    Text(text = "Timestamp: ${bookmark.timestamp}")
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

@Composable
private fun StimulusOverlay(cue: StimulusCue?) {
    if (cue == null) {
        return
    }
    val background = remember(cue.color) { colorIntToComposeColor(cue.color) }
    val textColor = remember(cue.color) { pickReadableTextColor(cue.color) }
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background.copy(alpha = 0.85f))
    ) {
        Text(
            text = cue.label,
            color = textColor,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.Center)
                .padding(24.dp)
        )
    }
}

private fun colorIntToComposeColor(@ColorInt color: Int): Color =
    Color(
        red = android.graphics.Color.red(color) / 255f,
        green = android.graphics.Color.green(color) / 255f,
        blue = android.graphics.Color.blue(color) / 255f,
        alpha = android.graphics.Color.alpha(color) / 255f
    )

private fun pickReadableTextColor(@ColorInt background: Int): Color {
    val darkness =
        1 - (0.299 * android.graphics.Color.red(background) +
                0.587 * android.graphics.Color.green(background) +
                0.114 * android.graphics.Color.blue(background)) / 255
    return if (darkness >= 0.5) {
        Color.White
    } else {
        Color.Black
    }
}

private fun formatBytes(bytes: Long): String {
    if (bytes <= 0) return "0 B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (Math.log10(bytes.toDouble()) / Math.log10(1024.0)).toInt()
    val value = bytes / Math.pow(1024.0, digitGroups.toDouble())
    return String.format(Locale.US, "%.1f %s", value, units[digitGroups])
}










