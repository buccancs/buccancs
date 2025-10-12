package com.buccancs.desktop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.domain.model.SessionStatus
import com.buccancs.desktop.ui.state.ControlPanelState
import com.buccancs.desktop.ui.state.DeviceListItem
import com.buccancs.desktop.ui.state.EventTimelineItem
import com.buccancs.desktop.ui.state.PreviewStreamState
import com.buccancs.desktop.ui.state.RetentionState
import com.buccancs.desktop.ui.state.SessionArchiveItem
import com.buccancs.desktop.ui.state.SessionSummary
import com.buccancs.desktop.ui.state.TransferStatusItem
import com.buccancs.desktop.viewmodel.AppViewModel
import org.jetbrains.skia.Image
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

@Composable
fun DesktopApp(viewModel: AppViewModel) {
    val state by viewModel.uiState.collectAsState()
    val formatter = rememberFormatter()
    val sessionTitle = state.session?.let { "Session ${it.id} (${it.status})" } ?: "No active session"
    val sessionActive = state.session?.status == SessionStatus.ACTIVE.name
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(sessionTitle, style = MaterialTheme.typography.headlineSmall)
                ControlPanel(
                    control = state.control,
                    sessionActive = sessionActive,
                    onStartSession = viewModel::startSession,
                    onStopSession = viewModel::stopSession,
                    onOperatorChange = viewModel::updateOperatorId,
                    onSubjectChange = viewModel::updateSubjectIds,
                    onSyncTypeChange = viewModel::updateSyncSignalType,
                    onSyncDelayChange = viewModel::updateSyncDelayMs,
                    onSyncTargetsChange = viewModel::updateSyncTargets,
                    onSendSync = viewModel::triggerSyncSignal,
                    onEventIdChange = viewModel::updateEventMarkerId,
                    onEventDescriptionChange = viewModel::updateEventDescription,
                    onEventTargetsChange = viewModel::updateEventTargets,
                    onAddEvent = viewModel::addEventMarker,
                    onStimulusIdChange = viewModel::updateStimulusId,
                    onStimulusActionChange = viewModel::updateStimulusAction,
                    onStimulusTargetsChange = viewModel::updateStimulusTargets,
                    onTriggerStimulus = viewModel::triggerStimulus,
                    onSubjectEraseChange = viewModel::updateSubjectEraseId,
                    onSubjectErase = viewModel::eraseSubject
                )
                state.session?.let {
                    SessionSummaryCard(
                        summary = it,
                        metricsUpdatedAt = it.metrics.updatedAt?.let(formatter::format),
                        canErase = it.status == SessionStatus.COMPLETED.name,
                        formatter = formatter,
                        onEraseSession = { viewModel.eraseSession(it.id) }
                    )
                }
                DeviceSection(devices = state.devices, formatter = formatter)
                RetentionSection(retention = state.retention)
                TransferSection(transfers = state.transfers)
                EventTimelineSection(events = state.events, formatter = formatter)
                PreviewSection(previews = state.previews, formatter = formatter)
                ArchiveSection(archives = state.historicalSessions, formatter = formatter)
                AlertsSection(alerts = state.alerts)
            }
        }
    }
}

@Composable
private fun ControlPanel(
    control: ControlPanelState,
    sessionActive: Boolean,
    onStartSession: () -> Unit,
    onStopSession: () -> Unit,
    onOperatorChange: (String) -> Unit,
    onSubjectChange: (String) -> Unit,
    onSyncTypeChange: (String) -> Unit,
    onSyncDelayChange: (String) -> Unit,
    onSyncTargetsChange: (String) -> Unit,
    onSendSync: () -> Unit,
    onEventIdChange: (String) -> Unit,
    onEventDescriptionChange: (String) -> Unit,
    onEventTargetsChange: (String) -> Unit,
    onAddEvent: () -> Unit,
    onStimulusIdChange: (String) -> Unit,
    onStimulusActionChange: (String) -> Unit,
    onStimulusTargetsChange: (String) -> Unit,
    onTriggerStimulus: () -> Unit,
    onSubjectEraseChange: (String) -> Unit,
    onSubjectErase: () -> Unit
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Control Panel", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = control.operatorId,
                    onValueChange = onOperatorChange,
                    label = { Text("Operator ID") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.subjectIds,
                    onValueChange = onSubjectChange,
                    label = { Text("Subject IDs (comma separated)") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onStartSession, enabled = !sessionActive) { Text("Start Session") }
                Button(onClick = onStopSession, enabled = sessionActive) { Text("Stop Session") }
            }
            Divider()
            Text("Synchronization", style = MaterialTheme.typography.titleSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = control.syncSignalType,
                    onValueChange = onSyncTypeChange,
                    label = { Text("Signal Type") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.syncDelayMs,
                    onValueChange = onSyncDelayChange,
                    label = { Text("Delay (ms)") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.syncTargets,
                    onValueChange = onSyncTargetsChange,
                    label = { Text("Target device IDs") },
                    modifier = Modifier.weight(1.5f),
                    singleLine = true
                )
                Button(onClick = onSendSync, enabled = sessionActive) { Text("Send Sync") }
            }
            Divider()
            Text("Time Sync Snapshot", style = MaterialTheme.typography.titleSmall)
            val offsetsSummary = remember(control.clockOffsetsPreview) {
                control.clockOffsetsPreview.entries.joinToString { (deviceId, offset) ->
                    "$deviceId=${"%.2f".format(offset)}"
                }
            }
            Text(
                text = if (offsetsSummary.isBlank()) "Clock offsets (ms): n/a" else "Clock offsets (ms): $offsetsSummary",
                style = MaterialTheme.typography.bodySmall
            )
            Divider()
            Text("Event Markers", style = MaterialTheme.typography.titleSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = control.eventMarkerId,
                    onValueChange = onEventIdChange,
                    label = { Text("Event ID") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.eventDescription,
                    onValueChange = onEventDescriptionChange,
                    label = { Text("Description") },
                    modifier = Modifier.weight(1.5f)
                )
                OutlinedTextField(
                    value = control.eventTargets,
                    onValueChange = onEventTargetsChange,
                    label = { Text("Targets") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(onClick = onAddEvent, enabled = sessionActive) { Text("Add Event") }
            }
            Divider()
            Text("Stimulus", style = MaterialTheme.typography.titleSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = control.stimulusId,
                    onValueChange = onStimulusIdChange,
                    label = { Text("Stimulus ID") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.stimulusAction,
                    onValueChange = onStimulusActionChange,
                    label = { Text("Action") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.stimulusTargets,
                    onValueChange = onStimulusTargetsChange,
                    label = { Text("Targets") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(onClick = onTriggerStimulus, enabled = sessionActive) { Text("Trigger") }
            }
            Divider()
            Text("Subject Erasure", style = MaterialTheme.typography.titleSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = control.subjectEraseId,
                    onValueChange = onSubjectEraseChange,
                    label = { Text("Subject ID") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(onClick = onSubjectErase) { Text("Erase Subject") }
            }
        }
    }
}

@Composable
private fun SessionSummaryCard(
    summary: SessionSummary,
    metricsUpdatedAt: String?,
    canErase: Boolean,
    formatter: DateTimeFormatter,
    onEraseSession: () -> Unit
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Session ${summary.id}", style = MaterialTheme.typography.titleMedium)
            Text("Status: ${summary.status}")
            Text("Created: ${formatter.format(summary.createdAt)}")
            summary.startedAt?.let { Text("Started: ${formatter.format(it)}") }
            Text("Elapsed: ${durationToReadable(summary.elapsedMillis)}")
            summary.durationMs?.let { Text("Duration: ${durationToReadable(it)}") }
            Text("Transferred: ${bytesToReadable(summary.totalBytes)}")
            if (summary.subjectIds.isNotEmpty()) {
                Text("Subjects: ${summary.subjectIds.joinToString()}")
            }
            Divider()
            Text("Live Metrics", style = MaterialTheme.typography.titleSmall)
            val metrics = summary.metrics
            if (listOf(
                    metrics.gsrSamples,
                    metrics.videoFrames,
                    metrics.thermalFrames,
                    metrics.audioSamples
                ).any { it > 0 }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MetricItem("GSR Samples", metrics.gsrSamples, Modifier.weight(1f))
                    MetricItem("Video Frames", metrics.videoFrames, Modifier.weight(1f))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MetricItem("Thermal Frames", metrics.thermalFrames, Modifier.weight(1f))
                    MetricItem("Audio Samples", metrics.audioSamples, Modifier.weight(1f))
                }
            } else {
                Text("No samples received yet", style = MaterialTheme.typography.bodySmall)
            }
            metricsUpdatedAt?.let { Text("Metrics updated: $it", style = MaterialTheme.typography.bodySmall) }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onEraseSession, enabled = canErase) { Text("Erase Session") }
                if (!canErase) {
                    Text("Stop session before erasing.", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
private fun MetricItem(label: String, value: Long, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall)
        Text(
            text = formatCount(value),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun DeviceSection(devices: List<DeviceListItem>, formatter: DateTimeFormatter) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Devices", style = MaterialTheme.typography.titleMedium)
            if (devices.isEmpty()) {
                Text("No devices connected")
                return@Column
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(devices) { device ->
                    val colors = when {
                        !device.connected -> CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )

                        device.recording -> CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        else -> CardDefaults.cardColors()
                    }
                    Card(colors = colors) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text("${device.id} • ${device.model} (${device.platform})")
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                StatusBadge(
                                    text = if (device.connected) "Connected" else "Disconnected",
                                    background = if (device.connected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.errorContainer,
                                    content = if (device.connected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onErrorContainer
                                )
                                StatusBadge(
                                    text = if (device.recording) "Recording" else "Idle",
                                    background = if (device.recording) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                    content = if (device.recording) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            device.sessionId?.let { Text("Session: $it") }
                            device.batteryPercent?.let { Text("Battery: ${"%.1f".format(it)}%") }
                            device.previewLatencyMs?.let { Text("Preview latency: ${"%.1f".format(it)} ms") }
                            device.clockOffsetMs?.let { Text("Clock offset: ${"%.2f".format(it)} ms") }
                            device.lastHeartbeat?.let { Text("Heartbeat: ${formatter.format(it)}") }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatusBadge(text: String, background: Color, content: Color, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = background,
        contentColor = content,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun RetentionSection(retention: RetentionState) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Retention", style = MaterialTheme.typography.titleMedium)
            Text("Total usage: ${bytesToReadable(retention.totalBytes)}")
            if (retention.perSessionBytes.isNotEmpty()) {
                Text("Per session:")
                retention.perSessionBytes.forEach { (sessionId, bytes) ->
                    Text("• $sessionId -> ${bytesToReadable(bytes)}")
                }
            }
            if (retention.perDeviceBytes.isNotEmpty()) {
                Text("Per device:")
                retention.perDeviceBytes.forEach { (deviceId, bytes) ->
                    Text("• $deviceId -> ${bytesToReadable(bytes)}")
                }
            }
            if (retention.perSessionDeviceBytes.isNotEmpty()) {
                Text("Per session/device:")
                retention.perSessionDeviceBytes.forEach { (sessionId, devices) ->
                    Text("• Session $sessionId")
                    devices.forEach { (deviceId, bytes) ->
                        Text("    $deviceId -> ${bytesToReadable(bytes)}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            if (retention.breaches.isNotEmpty()) {
                Divider()
                Text(
                    "Quota Alerts",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.error
                )
                retention.breaches.forEach { Text("• $it", color = MaterialTheme.colorScheme.error) }
            }
        }
    }
}

@Composable
private fun TransferSection(transfers: List<TransferStatusItem>) {
    if (transfers.isEmpty()) return
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Data Transfers", style = MaterialTheme.typography.titleMedium)
            transfers.forEach { transfer ->
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Session ${transfer.sessionId} • Device ${transfer.deviceId}")
                    Text("File: ${transfer.fileName} (${transfer.streamType ?: "unknown"})")
                    val progress =
                        "${bytesToReadable(transfer.bytesTransferred)} / ${bytesToReadable(transfer.bytesTotal)}"
                    Text("State: ${transfer.state} (attempt ${transfer.attempt}) • $progress")
                    transfer.errorMessage?.takeIf { it.isNotBlank() }?.let { error ->
                        Text(
                            "Error: $error",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Divider()
            }
        }
    }
}

@Composable
private fun EventTimelineSection(events: List<EventTimelineItem>, formatter: DateTimeFormatter) {
    if (events.isEmpty()) return
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Event Timeline", style = MaterialTheme.typography.titleMedium)
            events.forEach { event ->
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text("Event ${event.eventId} • ${event.label}")
                    Text("Time: ${formatter.format(event.timestamp)}", style = MaterialTheme.typography.bodySmall)
                    if (event.deviceIds.isNotEmpty()) {
                        Text("Targets: ${event.deviceIds.joinToString()}", style = MaterialTheme.typography.bodySmall)
                    }
                }
                Divider()
            }
        }
    }
}

@Composable
private fun PreviewSection(previews: List<PreviewStreamState>, formatter: DateTimeFormatter) {
    if (previews.isEmpty()) return
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Preview Streams", style = MaterialTheme.typography.titleMedium)
            previews.forEach { preview ->
                val bitmap = remember(preview.payload) { decodeImage(preview.payload) }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("${preview.deviceId} • ${preview.cameraId} (${preview.mimeType}) ${preview.width}x${preview.height}")
                    Text(
                        text = "Latency ${"%.1f".format(preview.latencyMs)} ms • Received ${formatter.format(preview.receivedAt)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    bitmap?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "Preview ${preview.deviceId}-${preview.cameraId}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp),
                            contentScale = ContentScale.Crop
                        )
                    } ?: Text("Preview unavailable (unsupported format)", style = MaterialTheme.typography.bodySmall)
                }
                Divider()
            }
        }
    }
}

@Composable
private fun ArchiveSection(archives: List<SessionArchiveItem>, formatter: DateTimeFormatter) {
    if (archives.isEmpty()) return
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Archived Sessions", style = MaterialTheme.typography.titleMedium)
            archives.forEach { archive ->
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text("Session ${archive.id} • ${archive.status}")
                    Text("Created: ${formatter.format(archive.createdAt)}", style = MaterialTheme.typography.bodySmall)
                    archive.startedAt?.let {
                        Text(
                            "Started: ${formatter.format(it)}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    archive.stoppedAt?.let {
                        Text(
                            "Stopped: ${formatter.format(it)}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    archive.durationMs?.let {
                        Text(
                            "Duration: ${durationToReadable(it)}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Text(
                        "Total bytes: ${bytesToReadable(archive.totalBytes)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text("Subjects: ${archive.subjects.joinToString()}", style = MaterialTheme.typography.bodySmall)
                    Text(
                        "Events: ${archive.eventCount} • Devices: ${archive.deviceCount}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Divider()
            }
        }
    }
}

@Composable
private fun AlertsSection(alerts: List<String>) {
    if (alerts.isEmpty()) return
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("Alerts", style = MaterialTheme.typography.titleMedium)
            alerts.forEach { alert -> Text(alert) }
        }
    }
}

@Composable
private fun rememberFormatter(): DateTimeFormatter =
    remember {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault())
    }

private fun bytesToReadable(bytes: Long): String {
    if (bytes <= 0) return "0 B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val exponent = (ln(bytes.toDouble()) / ln(1024.0)).toInt().coerceAtMost(units.lastIndex)
    val value = bytes / 1024.0.pow(exponent.toDouble())
    return String.format(Locale.US, "%.2f %s", value, units[exponent])
}

private fun durationToReadable(durationMs: Long): String {
    if (durationMs <= 0) return "00:00:00.000"
    val hours = durationMs / 3_600_000
    val minutes = (durationMs % 3_600_000) / 60_000
    val seconds = (durationMs % 60_000) / 1_000
    val millis = durationMs % 1_000
    return String.format(Locale.US, "%02d:%02d:%02d.%03d", hours, minutes, seconds, millis)
}

private fun formatCount(value: Long): String = String.format(Locale.US, "%,d", value)
private fun decodeImage(bytes: ByteArray): ImageBitmap? {
    if (bytes.isEmpty()) return null
    return runCatching { Image.makeFromEncoded(bytes).asImageBitmap() }.getOrNull()
}
