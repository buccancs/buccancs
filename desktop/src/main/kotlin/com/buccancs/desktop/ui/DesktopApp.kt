package com.buccancs.desktop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.buccancs.desktop.ui.state.SessionMetricsState
import com.buccancs.desktop.ui.state.TransferStatusItem
import com.buccancs.desktop.viewmodel.AppViewModel
import org.jetbrains.skia.Image
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DesktopApp(viewModel: AppViewModel) {
    val state by viewModel.uiState.collectAsState()
    val formatter = rememberFormatter()
    val sessionText = state.session?.let { "Session ${it.id} (${it.status})" } ?: "No active session"
    val sessionActive = state.session?.status == SessionStatus.ACTIVE.name

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = sessionText,
                    style = MaterialTheme.typography.headlineSmall
                )
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
                state.session?.let { session ->
                    val canErase = session.status == SessionStatus.COMPLETED.name
                    SessionSummaryCard(
                        sessionId = session.id,
                        createdAt = formatter.format(session.createdAt),
                        startedAt = session.startedAt?.let { formatter.format(it) },
                        durationMs = session.durationMs,
                        elapsedMs = session.elapsedMillis,
                        totalBytes = session.totalBytes,
                        subjectIds = session.subjectIds,
                        metrics = session.metrics,
                        metricsUpdatedAt = session.metrics.updatedAt?.let { formatter.format(it) },
                        canErase = canErase,
                        onEraseSession = { viewModel.eraseSession(session.id) }
                    )
                }
                DeviceSection(state.devices, formatter)
                RetentionSection(state.retention)
                TransferSection(state.transfers)
                EventTimelineSection(state.events, formatter)
                PreviewSection(state.previews, formatter)
                ArchiveSection(
                    sessions = state.historicalSessions,
                    formatter = formatter,
                    onEraseSession = viewModel::eraseSession
                )
                AlertsSection(state.alerts)
                Spacer(modifier = Modifier.height(12.dp))
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
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
                Button(onClick = onStartSession, enabled = !sessionActive) {
                    Text("Start Session")
                }
                Button(onClick = onStopSession, enabled = sessionActive) {
                    Text("Stop Session")
                }
            }
            Divider()
            Text("Synchronization", style = MaterialTheme.typography.titleSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
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
                Button(onClick = onSendSync, enabled = sessionActive) {
                    Text("Send Sync")
                }
            }
            Divider()
            Text("Time Sync Snapshot", style = MaterialTheme.typography.titleSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    buildString {
                        append("Clock offsets (ms): ")
                        val offsets = control.clockOffsetsPreview
                        if (offsets.isEmpty()) {
                            append("n/a")
                        } else {
                            append(offsets.entries.joinToString { "${it.key}=${"%.2f".format(it.value)}" })
                        }
                    },
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Divider()
            Text("Event Marker", style = MaterialTheme.typography.titleSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = control.eventMarkerId,
                    onValueChange = onEventIdChange,
                    label = { Text("Marker ID") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.eventDescription,
                    onValueChange = onEventDescriptionChange,
                    label = { Text("Description") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = control.eventTargets,
                    onValueChange = onEventTargetsChange,
                    label = { Text("Target device IDs") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(onClick = onAddEvent, enabled = sessionActive) {
                    Text("Add Event")
                }
            }
            Divider()
            Text("Stimulus", style = MaterialTheme.typography.titleSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = control.stimulusTargets,
                    onValueChange = onStimulusTargetsChange,
                    label = { Text("Target device IDs") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(onClick = onTriggerStimulus, enabled = sessionActive) {
                    Text("Trigger Stimulus")
                }
            }
            Divider()
            Text("GDPR Tools", style = MaterialTheme.typography.titleSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = control.subjectEraseId,
                    onValueChange = onSubjectEraseChange,
                    label = { Text("Subject ID to erase") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(onClick = onSubjectErase) {
                    Text("Erase Subject Data")
                }
            }
        }
    }
}

@Composable
private fun SessionSummaryCard(
    sessionId: String,
    createdAt: String,
    startedAt: String?,
    durationMs: Long?,
    elapsedMs: Long,
    totalBytes: Long,
    subjectIds: List<String>,
    metrics: SessionMetricsState,
    metricsUpdatedAt: String?,
    canErase: Boolean,
    onEraseSession: () -> Unit
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val metricsAvailable = listOf(
                metrics.gsrSamples,
                metrics.videoFrames,
                metrics.thermalFrames,
                metrics.audioSamples
            ).any { it > 0 }
            Text("Session $sessionId", style = MaterialTheme.typography.titleMedium)
            Text("Created: $createdAt")
            startedAt?.let { Text("Started: $it") }
            Text("Elapsed: ${durationToReadable(elapsedMs)}")
            durationMs?.let { Text("Duration: ${durationToReadable(it)}") }
            Text("Total bytes: ${bytesToReadable(totalBytes)}")
            if (subjectIds.isNotEmpty()) {
                Text(
                    "Subjects: ${
                        subjectIds.joinToString(\", \")}")
                    }
                            Divider ()
                            Text ("Live Metrics", style = MaterialTheme.typography.titleSmall
                )
                if (metricsAvailable) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        MetricItem(label = "GSR Samples", value = metrics.gsrSamples, modifier = Modifier.weight(1f))
                        MetricItem(label = "Video Frames", value = metrics.videoFrames, modifier = Modifier.weight(1f))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        MetricItem(
                            label = "Thermal Frames",
                            value = metrics.thermalFrames,
                            modifier = Modifier.weight(1f)
                        )
                        MetricItem(
                            label = "Audio Samples",
                            value = metrics.audioSamples,
                            modifier = Modifier.weight(1f)
                        )
                    }
                } else {
                    Text(
                        text = "No samples received yet",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                metricsUpdatedAt?.let {
                    Text("Metrics updated: $it", style = MaterialTheme.typography.bodySmall)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = onEraseSession, enabled = canErase) {
                        Text("Erase Session")
                    }
                    if (!canErase) {
                        Text("Stop session before erasing.", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
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
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(devices) { device ->
                            val cardColors = when {
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
                            Card(colors = cardColors) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                "${device.id} - ${device.model}",
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                            Text(
                                                "Platform: ${device.platform}",
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                        device.batteryPercent?.let {
                                            Text(
                                                "Battery ${"%.0f".format(it)}%",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        StatusBadge(
                                            text = if (device.connected) "Online" else "Offline",
                                            background = if (device.connected) {
                                                MaterialTheme.colorScheme.secondary
                                            } else {
                                                MaterialTheme.colorScheme.error
                                            },
                                            content = if (device.connected) {
                                                MaterialTheme.colorScheme.onSecondary
                                            } else {
                                                MaterialTheme.colorScheme.onError
                                            }
                                        )
                                        StatusBadge(
                                            text = if (device.recording) "Recording" else "Idle",
                                            background = if (device.recording) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                MaterialTheme.colorScheme.surfaceVariant
                                            },
                                            content = if (device.recording) {
                                                MaterialTheme.colorScheme.onPrimary
                                            } else {
                                                MaterialTheme.colorScheme.onSurfaceVariant
                                            }
                                        )
                                        device.previewLatencyMs?.let {
                                            StatusBadge(
                                                text = "Preview ${"%.1f".format(it)} ms",
                                                background = MaterialTheme.colorScheme.tertiaryContainer,
                                                content = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    }
                                    Text("Session: ${device.sessionId ?: "-"}")
                                    device.clockOffsetMs?.let {
                                        Text("Clock offset: ${"%.2f".format(it)} ms")
                                    }
                                    device.lastHeartbeat?.let {
                                        Text("Heartbeat: ${formatter.format(it)}")
                                    }
                                    if (!device.connected) {
                                        Text(
                                            "Connection lost",
                                            color = MaterialTheme.colorScheme.error,
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
    private fun StatusBadge(
        text: String,
        background: Color,
        content: Color,
        modifier: Modifier = Modifier
    ) {
        Surface(
            modifier = modifier,
            color = background,
            contentColor = content,
            shape = MaterialTheme.shapes.small,
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
                        Text("  $sessionId -> ${bytesToReadable(bytes)}")
                    }
                }
                if (retention.perDeviceBytes.isNotEmpty()) {
                    Text("Per device:")
                    retention.perDeviceBytes.forEach { (deviceId, bytes) ->
                        Text("  $deviceId -> ${bytesToReadable(bytes)}")
                    }
                }
                if (retention.perSessionDeviceBytes.isNotEmpty()) {
                    Text("Per session/device:")
                    retention.perSessionDeviceBytes.forEach { (sessionId, perDevice) ->
                        Text("  Session $sessionId")
                        perDevice.forEach { (deviceId, bytes) ->
                            Text("    $deviceId -> ${bytesToReadable(bytes)}")
                        }
                    }
                }
                if (retention.breaches.isNotEmpty()) {
                    retention.breaches.forEach { alert ->
                        Text("Alert: $alert", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }

    @Composable
    private fun EventTimelineSection(events: List<EventTimelineItem>, formatter: DateTimeFormatter) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Event Timeline", style = MaterialTheme.typography.titleMedium)
                if (events.isEmpty()) {
                    Text("No events recorded")
                } else {
                    events.forEach { event ->
                        Card {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text("${event.eventId}: ${event.label}")
                                Text("Timestamp: ${formatter.format(event.timestamp)}")
                                if (event.deviceIds.isNotEmpty()) {
                                    Text(
                                        "Devices: ${
                                            event.deviceIds.joinToString(\", \")}")
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }

        @Composable
        private fun ArchiveSection(
            sessions: List<SessionArchiveItem>,
            formatter: DateTimeFormatter,
            onEraseSession: (String) -> Unit
        ) {
            Card {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Session Archive", style = MaterialTheme.typography.titleMedium)
                    if (sessions.isEmpty()) {
                        Text("No stored sessions")
                    } else {
                        sessions.forEach { session ->
                            val canErase = session.status == SessionStatus.COMPLETED.name
                            Card {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text("Session ${session.id} (${session.status})")
                                    Text("Created: ${formatter.format(session.createdAt)}")
                                    session.startedAt?.let { Text("Started: ${formatter.format(it)}") }
                                    session.stoppedAt?.let { Text("Stopped: ${formatter.format(it)}") }
                                    session.durationMs?.let { Text("Duration: ${durationToReadable(it)}") }
                                    Text("Size: ${bytesToReadable(session.totalBytes)}")
                                    if (session.subjects.isNotEmpty()) {
                                        Text(
                                            "Subjects: ${
                                                session.subjects.joinToString(\", \")}")
                                            }
                                                    Text ("Events: ${session.eventCount} | Devices: ${session.deviceCount}")
                                                    Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                                Button(onClick = { onEraseSession(session.id) }, enabled = canErase) {
                                                    Text("Erase")
                                                }
                                                if (!canErase) {
                                                    Text(
                                                        "Active session cannot be erased",
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
            }

            @Composable
            private fun PreviewSection(previews: List<PreviewStreamState>, formatter: DateTimeFormatter) {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Preview Streams", style = MaterialTheme.typography.titleMedium)
                        if (previews.isEmpty()) {
                            Text("No live previews")
                        } else {
                            previews.forEach { preview ->
                                PreviewCard(preview, formatter)
                            }
                        }
                    }
                }
            }

            @Composable
            private fun PreviewCard(preview: PreviewStreamState, formatter: DateTimeFormatter) {
                val bitmap = remember(preview.payload) { decodeImage(preview.payload) }
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Device ${preview.deviceId} | Camera ${preview.cameraId}")
                        Text(
                            "Latency: ${"%.1f".format(preview.latencyMs)} ms | Received ${formatter.format(preview.receivedAt)}"
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
                        } ?: Text("Preview unavailable (unsupported format)")
                    }
                }
            }

            @Composable
            private fun AlertsSection(alerts: List<String>) {
                if (alerts.isEmpty()) {
                    return
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("Alerts", style = MaterialTheme.typography.titleMedium)
                        alerts.forEach { alert ->
                            Text(alert)
                        }
                    }
                }
            }

            @Composable
            private fun rememberFormatter(): DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())

            private fun bytesToReadable(bytes: Long): String {
                if (bytes <= 0) return "0 B"
                val units = arrayOf("B", "KB", "MB", "GB", "TB")
                val exp = (Math.log(bytes.toDouble()) / Math.log(1024.0)).toInt().coerceAtMost(units.lastIndex)
                val value = bytes / Math.pow(1024.0, exp.toDouble())
                return String.format("%.2f %s", value, units[exp])
            }

            private fun durationToReadable(durationMs: Long): String {
                if (durationMs <= 0L) {
                    return "00:00:00.000"
                }
                val hours = durationMs / 3_600_000
                val minutes = (durationMs % 3_600_000) / 60_000
                val seconds = (durationMs % 60_000) / 1_000
                val millis = durationMs % 1_000
                return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis)
            }

            private fun formatCount(value: Long): String = String.format(Locale.US, "%,d", value)

            private fun decodeImage(bytes: ByteArray): ImageBitmap? {
                if (bytes.isEmpty()) return null
                return runCatching { Image.makeFromEncoded(bytes).asImageBitmap() }.getOrNull()
            }

            @Composable
            private fun TransferSection(transfers: List<TransferStatusItem>) {
                if (transfers.isEmpty()) {
                    return
                }
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Data Transfers", style = MaterialTheme.typography.titleMedium)
                        transfers.forEach { transfer ->
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text("Session ${'$'}{transfer.sessionId} - ${'$'}{transfer.deviceId}")
                                Text("File: ${'$'}{transfer.fileName} (${transfer.streamType ?: "unknown"})")
                                val progressText = bytesToReadable(transfer.bytesTransferred) +
                                        " / " + bytesToReadable(transfer.bytesTotal)
                                Text("State: ${'$'}{transfer.state} (attempt ${'$'}{transfer.attempt}) - ${'$'}progressText")
                                transfer.errorMessage?.takeIf { it.isNotBlank() }?.let { error ->
                                    Text(
                                        text = "Error: ${'$'}error",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
