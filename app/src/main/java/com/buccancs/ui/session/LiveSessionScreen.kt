package com.buccancs.ui.session

import androidx.annotation.ColorInt
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.application.stimulus.StimulusCue
import com.buccancs.application.stimulus.StimulusState
import com.buccancs.domain.model.*
import com.buccancs.ui.components.AnimatedTonalButton
import com.buccancs.ui.components.InfoRow
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.components.StatusIndicator
import com.buccancs.ui.debug.ClockPanel
import com.buccancs.ui.debug.EncoderPanel
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.MotionTokens
import com.buccancs.ui.theme.MotionTransitions
import com.buccancs.ui.theme.Spacing
import java.util.*

@Composable
fun LiveSessionRoute(
    viewModel: LiveSessionViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LiveSessionScreen(
        state = state,
        onAddBookmark = { viewModel.recordBookmark() },
        onTriggerStimulus = { viewModel.previewStimulus() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveSessionScreen(
    state: LiveSessionUiState,
    onAddBookmark: () -> Unit,
    onTriggerStimulus: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.userMessage) {
        state.userMessage?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Live Session") },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    StatusIndicator(
                        icon = when (state.syncStatus.quality.name.uppercase()) {
                            "SYNCED" -> Icons.Default.CheckCircle
                            "SYNCING" -> Icons.Default.Cloud
                            else -> Icons.Default.Warning
                        },
                        label = state.syncStatus.quality.name,
                        color = when (state.syncStatus.quality.name.uppercase()) {
                            "SYNCED" -> MaterialTheme.colorScheme.primary
                            "SYNCING" -> MaterialTheme.colorScheme.secondary
                            else -> MaterialTheme.colorScheme.error
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBookmark,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "Add Bookmark"
                )
            }
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
                    .padding(LayoutPadding.Screen),
                verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
            ) {
                item {
                    CollapsibleRecordingCard(state)
                }
                item {
                    DeviceStreamGrid(devices = state.devices)
                }
                if (state.uploads.isNotEmpty() || (state.backlog.level != UploadBacklogLevel.NORMAL || state.backlog.queuedCount > 0)) {
                    item {
                        UploadStatusCard(uploads = state.uploads, backlog = state.backlog)
                    }
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
                if (state.bookmarks.isNotEmpty()) {
                    item {
                        BookmarkList(bookmarks = state.bookmarks)
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
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Recording",
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Lifecycle:",
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = state.recording.lifecycle.toString(),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Simulation:",
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = if (state.simulationEnabled) "Enabled" else "Disabled",
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (state.throttleLevel == PerformanceThrottleLevel.CONSERVE) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Throttle: ${state.throttleLevel}",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            val anchor = state.recording.anchor
            if (anchor != null) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.small
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Session ID: ${anchor.sessionId}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Reference: ${anchor.referenceTimestamp}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Clock offset: ${anchor.sharedClockOffsetMillis} ms",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            } else {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "Session idle",
                        modifier = Modifier.padding(12.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = "Updated: ${state.recording.updatedAt}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
            FilledTonalButton(
                onClick = onAddBookmark,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Add Bookmark")
            }
        }
    }
}

@Composable
private fun StimulusPanel(state: StimulusState, onTriggerStimulus: () -> Unit) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Stimulus",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.Lightbulb,
                contentDescription = null,
                tint = if (state.activeCue != null) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier.size(Dimensions.IconSizeDefault)
            )
        }
        HorizontalDivider()

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = if (state.hasExternalDisplay) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = Spacing.SmallMedium,
                    vertical = Spacing.Small
                ),
                horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (state.hasExternalDisplay) {
                        Icons.Default.CheckCircle
                    } else {
                        Icons.Default.Warning
                    },
                    contentDescription = null,
                    tint = if (state.hasExternalDisplay) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    modifier = Modifier.size(Dimensions.IconSizeDefault)
                )
                Text(
                    text = if (state.hasExternalDisplay) {
                        "External display ready"
                    } else {
                        "External display not detected"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (state.hasExternalDisplay) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }

        state.activeCue?.let { cue ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Active: ${cue.label}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.padding(
                        horizontal = Spacing.SmallMedium,
                        vertical = Spacing.Small
                    )
                )
            }
        } ?: state.lastCue?.let { cue ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Last: ${cue.label}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(
                        horizontal = Spacing.SmallMedium,
                        vertical = Spacing.Small
                    )
                )
            }
        }

        AnimatedTonalButton(
            onClick = onTriggerStimulus,
            modifier = Modifier.fillMaxWidth(),
            text = "Preview Stimulus"
        )
    }
}

@Composable
private fun DeviceList(devices: List<SensorDevice>) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("live-devices")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Devices",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            if (devices.isEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "No devices registered.",
                        modifier = Modifier
                            .padding(12.dp)
                            .testTag("live-no-devices"),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    devices.forEach { device ->
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = when (device.connectionStatus) {
                                is ConnectionStatus.Connected -> MaterialTheme.colorScheme.primaryContainer
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            },
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${device.displayName} (${device.type})",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.SemiBold,
                                        color = when (device.connectionStatus) {
                                            is ConnectionStatus.Connected -> MaterialTheme.colorScheme.onPrimaryContainer
                                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                                        }
                                    )
                                    Icon(
                                        imageVector = when (device.connectionStatus) {
                                            is ConnectionStatus.Connected -> Icons.Default.CheckCircle
                                            else -> Icons.Default.Warning
                                        },
                                        contentDescription = null,
                                        tint = when (device.connectionStatus) {
                                            is ConnectionStatus.Connected -> MaterialTheme.colorScheme.onPrimaryContainer
                                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                                        },
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Text(
                                    text = "Status: ${device.connectionStatus}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = when (device.connectionStatus) {
                                        is ConnectionStatus.Connected -> MaterialTheme.colorScheme.onPrimaryContainer
                                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                )
                                Text(
                                    text = "Capabilities: ${device.capabilities.joinToString { it.name }}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = when (device.connectionStatus) {
                                        is ConnectionStatus.Connected -> MaterialTheme.colorScheme.onPrimaryContainer
                                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                )
                                if (device.attributes.isNotEmpty()) {
                                    Text(
                                        text = "Attributes: ${
                                            device.attributes.entries.joinToString { "${it.key}=${it.value}" }
                                        }",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = when (device.connectionStatus) {
                                            is ConnectionStatus.Connected -> MaterialTheme.colorScheme.onPrimaryContainer
                                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                                        }
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
private fun UploadList(uploads: List<UploadStatus>) {
    SectionCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("live-uploads"),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Uploads",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        uploads.forEach { upload ->
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
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
                Text(
                    text = "State: ${upload.state} · Progress: $progress",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Attempt: ${upload.attempt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                upload.errorMessage?.let {
                    Text(
                        text = "Error: $it",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun BacklogCard(state: UploadBacklogState) {
    SectionCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("live-backlog"),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Upload Backlog",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
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
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        state.message?.let {
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
            Text(
                text = "Newest artifacts are dropped until backlog clears. Review network/retention configuration.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
        if (state.perSessionQueued.isNotEmpty()) {
            Text(
                text = "Session Breakdown",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            state.perSessionQueued
                .toList()
                .sortedByDescending { it.second }
                .take(6)
                .forEach { (sessionId, count) ->
                    val bytes = state.perSessionBytes[sessionId] ?: 0L
                    Text(
                        text = "$sessionId — $count items (${formatBytes(bytes)})",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
        }
    }
}

@Composable
private fun RecoveryList(records: List<UploadRecoveryRecord>) {
    if (records.isEmpty()) return
    val recent = records.sortedByDescending { it.timestamp }.take(12)
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Upload Recovery",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        recent.forEach { record ->
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
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

@Composable
private fun EventList(events: List<DeviceEvent>) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Recent Events",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        events.forEach { event ->
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
                Text(
                    text = "${event.type} — ${event.label}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Scheduled: ${event.scheduledAt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Received: ${event.receivedAt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun BookmarkList(bookmarks: List<RecordingBookmark>) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Bookmarks",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        bookmarks.forEach { bookmark ->
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
                Text(
                    text = bookmark.label.ifBlank { "Bookmark" },
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Timestamp: ${bookmark.timestamp}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun StorageCard(state: LiveSessionUiState) {
    val storage = state.storage
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Storage",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.Small))

        val statusContainer = when (storage.status.name) {
            "LOW" -> MaterialTheme.colorScheme.errorContainer
            "WARNING" -> MaterialTheme.colorScheme.tertiaryContainer
            else -> MaterialTheme.colorScheme.primaryContainer
        }
        val statusContent = when (storage.status.name) {
            "LOW" -> MaterialTheme.colorScheme.onErrorContainer
            "WARNING" -> MaterialTheme.colorScheme.onTertiaryContainer
            else -> MaterialTheme.colorScheme.onPrimaryContainer
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = statusContainer,
            shape = MaterialTheme.shapes.small
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Spacing.SmallMedium,
                    vertical = Spacing.Small
                ),
                verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
            ) {
                StorageStatRow("Total", formatBytes(storage.totalBytes), statusContent)
                StorageStatRow("Available", formatBytes(storage.availableBytes), statusContent)
                StorageStatRow("Used", formatBytes(storage.usedBytes), statusContent)
                StorageStatRow("Status", storage.status.name, statusContent, emphasize = true)
            }
        }

        if (storage.sessions.isNotEmpty()) {
            Text(
                text = "Sessions",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
                storage.sessions.take(6).forEach { usage ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Spacing.SmallMedium,
                                    vertical = Spacing.ExtraSmall
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = usage.sessionId,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = formatBytes(usage.bytes),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
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

@Composable
private fun CollapsibleRecordingCard(state: LiveSessionUiState) {
    var expanded by remember { mutableStateOf(true) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "rotation",
        animationSpec = MotionTokens.mediumElementEnter()
    )

    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        tonal = true,
        spacing = Spacing.SmallMedium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Recording",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.recording.lifecycle.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand",
                modifier = Modifier.rotate(rotationAngle)
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = MotionTransitions.expandVertically(),
            exit = MotionTransitions.shrinkVertically()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                val anchor = state.recording.anchor
                if (anchor != null) {
                    RecordingInfoRow("Session ID", anchor.sessionId)
                    RecordingInfoRow("Reference", anchor.referenceTimestamp.toString())
                    RecordingInfoRow("Clock Offset", "${anchor.sharedClockOffsetMillis} ms")
                } else {
                    Text(
                        text = "Session idle",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
                ) {
                    AssistChip(
                        onClick = {},
                        label = { Text(if (state.simulationEnabled) "Simulation" else "Live") }
                    )
                    if (state.throttleLevel == PerformanceThrottleLevel.CONSERVE) {
                        AssistChip(
                            onClick = {},
                            label = { Text("Throttled") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StorageStatRow(
    label: String,
    value: String,
    color: Color,
    emphasize: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = if (emphasize) FontWeight.SemiBold else FontWeight.Medium,
            color = color
        )
        Text(
            text = value,
            fontWeight = if (emphasize) FontWeight.SemiBold else FontWeight.Normal,
            color = color
        )
    }
}

@Composable
private fun RecordingInfoRow(label: String, value: String) {
    InfoRow(
        label = label,
        value = value,
        valueColor = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
private fun DeviceStreamGrid(devices: List<SensorDevice>) {
    if (devices.isEmpty()) return

    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Device Status",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.Small),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            items(devices, key = { it.id.value }) { device ->
                DeviceStatusChip(device)
            }
        }
    }
}

@Composable
private fun DeviceStatusChip(device: SensorDevice) {
    val backgroundColor = when (device.connectionStatus) {
        is ConnectionStatus.Connected -> MaterialTheme.colorScheme.primaryContainer
        ConnectionStatus.Connecting -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val iconColor = when (device.connectionStatus) {
        is ConnectionStatus.Connected -> MaterialTheme.colorScheme.primary
        ConnectionStatus.Connecting -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = when (device.connectionStatus) {
                    is ConnectionStatus.Connected -> Icons.Default.CheckCircle
                    else -> Icons.Default.Warning
                },
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(Dimensions.IconSizeDefault)
            )
            Text(
                text = device.displayName,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = Spacing.ExtraSmall)
            )
            Text(
                text = when (device.connectionStatus) {
                    is ConnectionStatus.Connected -> "Connected"
                    ConnectionStatus.Connecting -> "Connecting"
                    ConnectionStatus.Disconnected -> "Disconnected"
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun UploadStatusCard(uploads: List<UploadStatus>, backlog: UploadBacklogState) {
    val containerColor = when (backlog.level) {
        UploadBacklogLevel.CRITICAL -> MaterialTheme.colorScheme.errorContainer
        UploadBacklogLevel.WARNING -> MaterialTheme.colorScheme.tertiaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = containerColor),
        spacing = Spacing.Small
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Upload Status",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            if (backlog.queuedCount > 0) {
                AssistChip(
                    onClick = {},
                    label = { Text("${backlog.queuedCount} queued") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = null
                        )
                    }
                )
            }
        }

        if (uploads.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                uploads.forEach { upload ->
                    UploadProgressItem(upload)
                }
            }
        } else {
            Text(
                text = "No active uploads",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (backlog.level != UploadBacklogLevel.NORMAL || backlog.message != null) {
            Text(
                text = backlog.message ?: "Upload backlog: ${backlog.level}",
                style = MaterialTheme.typography.bodySmall,
                color = when (backlog.level) {
                    UploadBacklogLevel.CRITICAL -> MaterialTheme.colorScheme.error
                    UploadBacklogLevel.WARNING -> MaterialTheme.colorScheme.tertiary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

@Composable
private fun UploadProgressItem(upload: UploadStatus) {
    val progressValue = if (upload.bytesTotal > 0) {
        (upload.bytesTransferred.toFloat() / upload.bytesTotal.toFloat())
    } else {
        0f
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${upload.deviceId.value.take(8)}... ${upload.streamType.name}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${(progressValue * 100).toInt()}%",
                style = MaterialTheme.typography.bodySmall
            )
        }
        LinearProgressIndicator(
            progress = { progressValue },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.ExtraSmall)
        )
    }
}
