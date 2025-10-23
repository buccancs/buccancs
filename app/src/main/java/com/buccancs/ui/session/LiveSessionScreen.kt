package com.buccancs.ui.session

import androidx.annotation.ColorInt
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.application.stimulus.StimulusCue
import com.buccancs.application.stimulus.StimulusState
import com.buccancs.data.storage.SessionUsage
import com.buccancs.data.storage.SpaceState
import com.buccancs.data.storage.SpaceStatus
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceEvent
import com.buccancs.domain.model.DeviceEventType
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.NetworkSnapshot
import com.buccancs.domain.model.PerformanceThrottleLevel
import com.buccancs.domain.model.RecordingBookmark
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.RecordingState
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.TimeSyncObservation
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.domain.model.UploadBacklogLevel
import com.buccancs.domain.model.UploadBacklogState
import com.buccancs.domain.model.UploadRecoveryRecord
import com.buccancs.domain.model.UploadState
import com.buccancs.domain.model.UploadStatus
import com.buccancs.ui.common.HorizontalDivider
import com.buccancs.ui.components.AnimatedTonalButton
import com.buccancs.ui.components.HealthAlertsCard
import com.buccancs.ui.components.InfoRow
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.components.StatusIndicator
import com.buccancs.ui.debug.ClockPanel
import com.buccancs.ui.debug.EncoderPanel
import com.buccancs.ui.theme.BuccancsTheme
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.MotionTokens
import com.buccancs.ui.theme.MotionTransitions
import com.buccancs.ui.theme.Spacing
import java.util.*
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

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

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun LiveSessionScreen(
    state: LiveSessionUiState,
    onAddBookmark: () -> Unit,
    onTriggerStimulus: () -> Unit
) {
    val snackbarHostState =
        remember { SnackbarHostState() }

    LaunchedEffect(
        state.userMessage
    ) {
        state.userMessage?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Live Session"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    val (syncIcon, syncLabel, syncColor) = when (state.syncStatus.quality) {
                        TimeSyncQuality.GOOD -> Triple(
                            Icons.Default.CheckCircle,
                            "Synced",
                            MaterialTheme.colorScheme.primary
                        )

                        TimeSyncQuality.FAIR -> Triple(
                            Icons.Default.Cloud,
                            "Syncing",
                            MaterialTheme.colorScheme.tertiary
                        )

                        TimeSyncQuality.POOR -> Triple(
                            Icons.Default.Warning,
                            "Degraded",
                            MaterialTheme.colorScheme.error
                        )

                        TimeSyncQuality.UNKNOWN -> Triple(
                            Icons.Default.Help,
                            "Unknown",
                            MaterialTheme.colorScheme.outline
                        )
                    }

                    StatusIndicator(
                        icon = syncIcon,
                        label = syncLabel,
                        color = syncColor
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
        },
        contentWindowInsets = WindowInsets(
            0,
            0,
            0,
            0
        )
    ) { padding ->
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(
                        "live-list"
                    )
                    .padding(
                        LayoutPadding.Screen
                    ),
                contentPadding = PaddingValues(
                    bottom = Spacing.Hero
                ),
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                item {
                    CollapsibleRecordingCard(
                        state
                    )
                }
                if (state.healthAlerts.isNotEmpty()) {
                    item {
                        HealthAlertsCard(
                            state.healthAlerts
                        )
                    }
                }
                item {
                    DeviceStreamGrid(
                        devices = state.devices
                    )
                }
                if (state.uploads.isNotEmpty() || (state.backlog.level != UploadBacklogLevel.NORMAL || state.backlog.queuedCount > 0)) {
                    item {
                        UploadStatusCard(
                            uploads = state.uploads,
                            backlog = state.backlog
                        )
                    }
                }
                item {
                    StimulusPanel(
                        state.stimulus,
                        onTriggerStimulus
                    )
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
                        BookmarkList(
                            bookmarks = state.bookmarks
                        )
                    }
                }
                if (state.recoveries.isNotEmpty()) {
                    item {
                        RecoveryList(
                            records = state.recoveries
                        )
                    }
                }
                if (state.events.isNotEmpty()) {
                    item {
                        EventList(
                            events = state.events
                        )
                    }
                }
                item {
                    StorageCard(
                        state = state
                    )
                }
            }
            StimulusOverlay(
                state.stimulus.activeCue
            )
        }
    }
}

@Composable
private fun RecordingCard(
    state: LiveSessionUiState,
    onAddBookmark: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                Spacing.Medium
            )
        ) {
            Text(
                text = "Recording",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = Spacing.Small
                )
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Column(
                    modifier = Modifier.padding(
                        Spacing.SmallMedium
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        Spacing.ExtraSmall
                    )
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

            Spacer(
                modifier = Modifier.height(
                    Spacing.Small
                )
            )

            if (state.throttleLevel == PerformanceThrottleLevel.CONSERVE) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(
                        modifier = Modifier.padding(
                            Spacing.SmallMedium
                        ),
                        horizontalArrangement = Arrangement.spacedBy(
                            Spacing.Small
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.size(
                                Dimensions.IconSizeSmall
                            )
                        )
                        Text(
                            text = "Throttle: ${state.throttleLevel}",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.height(
                        Spacing.Small
                    )
                )
            }

            val anchor =
                state.recording.anchor
            if (anchor != null) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.small
                ) {
                    Column(
                        modifier = Modifier.padding(
                            Spacing.SmallMedium
                        ),
                        verticalArrangement = Arrangement.spacedBy(
                            Spacing.ExtraSmall
                        )
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
                        modifier = Modifier.padding(
                            Spacing.SmallMedium
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = "Updated: ${state.recording.updatedAt}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    top = Spacing.Small
                )
            )

            Spacer(
                modifier = Modifier.height(
                    Spacing.SmallMedium
                )
            )
            FilledTonalButton(
                onClick = onAddBookmark,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(
                        minHeight = Dimensions.TouchTargetMinimum
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    modifier = Modifier.size(
                        Dimensions.IconSizeSmall
                    )
                )
                Spacer(
                    modifier = Modifier.width(
                        Spacing.ExtraSmall
                    )
                )
                Text(
                    text = "Add Bookmark"
                )
            }
        }
    }
}

@Composable
private fun StimulusPanel(
    state: StimulusState,
    onTriggerStimulus: () -> Unit
) {
    val statusDescription =
        if (state.hasExternalDisplay) {
            "External display ready"
        } else {
            "External display not detected"
        }
    val activeCueDescription =
        state.activeCue?.let { "Active cue ${it.label}" }
    val lastCueDescription =
        state.lastCue?.let { "Last cue ${it.label}" }

    SectionCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(
                "live-stimulus-card"
            )
            .semantics(
                mergeDescendants = true
            ) {
                contentDescription =
                    buildString {
                        append(
                            "Stimulus card. "
                        )
                        append(
                            statusDescription
                        )
                        activeCueDescription?.let {
                            append(
                                ". "
                            )
                            append(
                                it
                            )
                        }
                        if (activeCueDescription == null) {
                            lastCueDescription?.let {
                                append(
                                    ". "
                                )
                                append(
                                    it
                                )
                            }
                        }
                    }
            },
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
                modifier = Modifier.size(
                    Dimensions.IconSizeDefault
                )
            )
        }
        HorizontalDivider()

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(
                    "stimulus-external-display"
                )
                .semantics(
                    mergeDescendants = true
                ) {
                    contentDescription =
                        statusDescription
                },
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
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                ),
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
                    modifier = Modifier.size(
                        Dimensions.IconSizeDefault
                    )
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
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(
                        "stimulus-active-cue"
                    )
                    .semantics(
                        mergeDescendants = true
                    ) {
                        contentDescription =
                            "Active cue ${cue.label}"
                    },
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
        }
            ?: state.lastCue?.let { cue ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(
                            "stimulus-last-cue"
                        )
                        .semantics(
                            mergeDescendants = true
                        ) {
                            contentDescription =
                                "Last cue ${cue.label}"
                        },
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
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(
                    minHeight = Dimensions.TouchTargetMinimum
                )
                .testTag(
                    "stimulus-preview-button"
                ),
            text = "Preview Stimulus"
        )
    }
}

@Composable
private fun DeviceList(
    devices: List<SensorDevice>
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(
                "live-devices"
            )
    ) {
        Column(
            modifier = Modifier.padding(
                Spacing.Medium
            )
        ) {
            Text(
                text = "Devices",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = Spacing.Small
                )
            )
            if (devices.isEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "No devices registered.",
                        modifier = Modifier
                            .padding(
                                Spacing.SmallMedium
                            )
                            .testTag(
                                "live-no-devices"
                            ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        Spacing.Small
                    )
                ) {
                    devices.forEach { device ->
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = when (device.connectionStatus) {
                                is ConnectionStatus.Connected -> MaterialTheme.colorScheme.primaryContainer
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            },
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    Spacing.SmallMedium
                                )
                            ) {
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
                                        modifier = Modifier.size(
                                            Dimensions.IconSizeSmall
                                        )
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
private fun UploadList(
    uploads: List<UploadStatus>
) {
    SectionCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(
                "live-uploads"
            ),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Uploads",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        uploads.forEach { upload ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
                Text(
                    text = "${upload.deviceId.value} — ${upload.streamType.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
                val progress =
                    if (upload.bytesTotal > 0) {
                        val pct =
                            (upload.bytesTransferred.toDouble() / upload.bytesTotal.toDouble()) * 100.0
                        String.format(
                            Locale.US,
                            "%.1f%%",
                            pct
                        )
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
private fun BacklogCard(
    state: UploadBacklogState
) {
    SectionCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(
                "live-backlog"
            ),
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
            text = "Pending: ${state.queuedCount} artifacts · ${
                formatBytes(
                    state.queuedBytes
                )
            } remaining",
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
                .take(
                    6
                )
                .forEach { (sessionId, count) ->
                    val bytes =
                        state.perSessionBytes[sessionId]
                            ?: 0L
                    Text(
                        text = "$sessionId — $count items (${
                            formatBytes(
                                bytes
                            )
                        })",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
        }
    }
}

@Composable
private fun RecoveryList(
    records: List<UploadRecoveryRecord>
) {
    if (records.isEmpty()) return
    val recent =
        records.sortedByDescending { it.timestamp }
            .take(
                12
            )
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
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
                Text(
                    text = "${record.deviceId.value} — ${record.streamType.name} · Attempt ${record.attempt} · ${record.state}",
                    style = MaterialTheme.typography.bodyMedium
                )
                val networkLabel =
                    buildString {
                        append(
                            record.network.transport
                        )
                        append(
                            if (record.network.connected) " connected" else " offline"
                        )
                        if (record.network.metered) {
                            append(
                                " · metered"
                            )
                        }
                    }
                val networkColor =
                    if (record.network.connected) {
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
                    text = "Bytes: ${
                        formatBytes(
                            record.bytesTransferred
                        )
                    } / ${
                        formatBytes(
                            record.bytesTotal
                        )
                    }",
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
private fun EventList(
    events: List<DeviceEvent>
) {
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
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
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
private fun BookmarkList(
    bookmarks: List<RecordingBookmark>
) {
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
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
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
private fun StorageCard(
    state: LiveSessionUiState
) {
    val storage =
        state.storage
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Storage",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                vertical = Spacing.Small
            )
        )

        val statusContainer =
            when (storage.status.name) {
                "LOW" -> MaterialTheme.colorScheme.errorContainer
                "WARNING" -> MaterialTheme.colorScheme.tertiaryContainer
                else -> MaterialTheme.colorScheme.primaryContainer
            }
        val statusContent =
            when (storage.status.name) {
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
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
                StorageStatRow(
                    "Total",
                    formatBytes(
                        storage.totalBytes
                    ),
                    statusContent
                )
                StorageStatRow(
                    "Available",
                    formatBytes(
                        storage.availableBytes
                    ),
                    statusContent
                )
                StorageStatRow(
                    "Used",
                    formatBytes(
                        storage.usedBytes
                    ),
                    statusContent
                )
                StorageStatRow(
                    "Status",
                    storage.status.name,
                    statusContent,
                    emphasize = true
                )
            }
        }

        if (storage.sessions.isNotEmpty()) {
            Text(
                text = "Sessions",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
                storage.sessions.take(
                    6
                )
                    .forEach { usage ->
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
                                    text = formatBytes(
                                        usage.bytes
                                    ),
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
private fun StimulusOverlay(
    cue: StimulusCue?
) {
    if (cue == null) {
        return
    }
    val background =
        remember(
            cue.color
        ) {
            colorIntToComposeColor(
                cue.color
            )
        }
    val textColor =
        remember(
            cue.color
        ) {
            pickReadableTextColor(
                cue.color
            )
        }
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                background.copy(
                    alpha = 0.85f
                )
            )
    ) {
        Text(
            text = cue.label,
            color = textColor,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(
                    Alignment.Center
                )
                .padding(
                    Spacing.Large
                )
        )
    }
}

private fun colorIntToComposeColor(
    @ColorInt color: Int
): Color =
    Color(
        red = android.graphics.Color.red(
            color
        ) / 255f,
        green = android.graphics.Color.green(
            color
        ) / 255f,
        blue = android.graphics.Color.blue(
            color
        ) / 255f,
        alpha = android.graphics.Color.alpha(
            color
        ) / 255f
    )

private fun pickReadableTextColor(
    @ColorInt background: Int
): Color {
    val darkness =
        1 - (0.299 * android.graphics.Color.red(
            background
        ) +
                0.587 * android.graphics.Color.green(
            background
        ) +
                0.114 * android.graphics.Color.blue(
            background
        )) / 255
    return if (darkness >= 0.5) {
        Color.White
    } else {
        Color.Black
    }
}

private fun formatBytes(
    bytes: Long
): String {
    if (bytes <= 0) return "0 B"
    val units =
        arrayOf(
            "B",
            "KB",
            "MB",
            "GB",
            "TB"
        )
    val digitGroups =
        (Math.log10(
            bytes.toDouble()
        ) / Math.log10(
            1024.0
        )).toInt()
    val value =
        bytes / Math.pow(
            1024.0,
            digitGroups.toDouble()
        )
    return String.format(
        Locale.US,
        "%.1f %s",
        value,
        units[digitGroups]
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF6F1FF
)
@Composable
private fun LiveSessionScreenPreview() {
    BuccancsTheme {
        LiveSessionScreen(
            state = previewLiveSessionState(),
            onAddBookmark = {},
            onTriggerStimulus = {}
        )
    }
}

private fun previewLiveSessionState(): LiveSessionUiState {
    val now =
        Instant.fromEpochMilliseconds(
            1_700_000_000_000
        )
    val rgbCamera =
        DeviceId(
            "rgb-camera"
        )
    val thermalCamera =
        DeviceId(
            "topdon-thermal"
        )

    return LiveSessionUiState.initial()
        .copy(
            recording = RecordingState(
                lifecycle = RecordingLifecycleState.Recording,
                anchor = RecordingSessionAnchor(
                    sessionId = "session-042",
                    referenceTimestamp = now - 4.minutes,
                    sharedClockOffsetMillis = -3
                ),
                updatedAt = now
            ),
            streams = listOf(
                SensorStreamStatus(
                    deviceId = rgbCamera,
                    streamType = SensorStreamType.RGB_VIDEO,
                    sampleRateHz = 30.0,
                    frameRateFps = 30.0,
                    lastSampleTimestamp = now,
                    bufferedDurationSeconds = 1.4,
                    isStreaming = true,
                    isSimulated = false
                ),
                SensorStreamStatus(
                    deviceId = thermalCamera,
                    streamType = SensorStreamType.THERMAL_VIDEO,
                    sampleRateHz = 30.0,
                    frameRateFps = 24.0,
                    lastSampleTimestamp = now - 1.seconds,
                    bufferedDurationSeconds = 0.8,
                    isStreaming = true,
                    isSimulated = false
                )
            ),
            devices = listOf(
                SensorDevice(
                    id = rgbCamera,
                    displayName = "Phone RGB Camera",
                    type = SensorDeviceType.ANDROID_RGB_CAMERA,
                    capabilities = setOf(
                        SensorStreamType.RGB_VIDEO,
                        SensorStreamType.PREVIEW
                    ),
                    connectionStatus = ConnectionStatus.Connected(
                        now - 6.minutes
                    ),
                    isSimulated = false,
                    attributes = mapOf(
                        "Battery" to "82%"
                    )
                ),
                SensorDevice(
                    id = thermalCamera,
                    displayName = "Topdon TC001",
                    type = SensorDeviceType.TOPDON_TC001,
                    capabilities = setOf(
                        SensorStreamType.THERMAL_VIDEO,
                        SensorStreamType.PREVIEW
                    ),
                    connectionStatus = ConnectionStatus.Connected(
                        now - 5.minutes
                    ),
                    isSimulated = false
                )
            ),
            syncStatus = TimeSyncStatus(
                offsetMillis = 3,
                roundTripMillis = 9,
                lastSync = now - 2.seconds,
                driftEstimateMillisPerMinute = 0.4,
                filteredRoundTripMillis = 12.0,
                quality = TimeSyncQuality.FAIR,
                sampleCount = 18,
                regressionSlopeMillisPerMinute = 0.1
            ),
            syncHistory = listOf(
                TimeSyncObservation(
                    now - 15.seconds,
                    3.2,
                    11.0
                ),
                TimeSyncObservation(
                    now - 5.seconds,
                    2.4,
                    9.5
                ),
                TimeSyncObservation(
                    now,
                    2.0,
                    8.7
                )
            ),
            uploads = listOf(
                UploadStatus(
                    sessionId = "session-042",
                    deviceId = rgbCamera,
                    streamType = SensorStreamType.RGB_VIDEO,
                    fileName = "rgb_segment_01.mp4",
                    bytesTotal = 512_000_000,
                    bytesTransferred = 260_000_000,
                    attempt = 1,
                    state = UploadState.IN_PROGRESS
                )
            ),
            recoveries = listOf(
                UploadRecoveryRecord(
                    sessionId = "session-041",
                    deviceId = thermalCamera,
                    streamType = SensorStreamType.THERMAL_VIDEO,
                    attempt = 2,
                    state = UploadState.FAILED,
                    timestamp = now - 45.seconds,
                    bytesTransferred = 120_000_000,
                    bytesTotal = 200_000_000,
                    network = NetworkSnapshot(
                        connected = true,
                        transport = "WIFI",
                        metered = false
                    ),
                    errorMessage = "Connection dropped"
                )
            ),
            backlog = UploadBacklogState(
                level = UploadBacklogLevel.WARNING,
                queuedCount = 3,
                queuedBytes = 1_500_000_000,
                message = "Uploads pending",
                perSessionQueued = mapOf(
                    "session-042" to 2
                ),
                perSessionBytes = mapOf(
                    "session-042" to 900_000_000L
                )
            ),
            events = listOf(
                DeviceEvent(
                    id = "event-1",
                    type = DeviceEventType.STIMULUS,
                    label = "Stimulus: Flash",
                    scheduledAt = now - 30.seconds,
                    receivedAt = now - 29.seconds
                ),
                DeviceEvent(
                    id = "event-2",
                    type = DeviceEventType.COMMAND,
                    label = "Start Recording",
                    scheduledAt = now - 4.minutes,
                    receivedAt = now - 4.minutes
                )
            ),
            bookmarks = listOf(
                RecordingBookmark(
                    id = "bookmark-1",
                    label = "Interesting event",
                    timestamp = now - 25.seconds,
                    createdAt = now - 25.seconds
                )
            ),
            storage = SpaceState(
                totalBytes = 128L * 1024 * 1024 * 1024,
                usedBytes = 72L * 1024 * 1024 * 1024,
                availableBytes = 56L * 1024 * 1024 * 1024,
                status = SpaceStatus.WARNING,
                sessions = listOf(
                    SessionUsage(
                        sessionId = "session-040",
                        bytes = 32L * 1024 * 1024 * 1024,
                        lastModifiedEpochMs = now.toEpochMilliseconds() - 86_400_000
                    )
                )
            ),
            simulationEnabled = false,
            stimulus = StimulusState(
                hasExternalDisplay = false,
                activeCue = StimulusCue.preview(),
                activeCueEndsAtEpochMs = now.toEpochMilliseconds() + 3_000,
                lastCue = StimulusCue.preview()
            ),
            throttleLevel = PerformanceThrottleLevel.NORMAL
        )
}

@Composable
private fun CollapsibleRecordingCard(
    state: LiveSessionUiState
) {
    var expanded by remember {
        mutableStateOf(
            true
        )
    }
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
                .clickable {
                    expanded =
                        !expanded
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(
                    1f
                )
            ) {
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
                modifier = Modifier.rotate(
                    rotationAngle
                )
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = MotionTransitions.expandVertically(),
            exit = MotionTransitions.shrinkVertically()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.Small
                )
            ) {
                val anchor =
                    state.recording.anchor
                if (anchor != null) {
                    RecordingInfoRow(
                        "Session ID",
                        anchor.sessionId
                    )
                    RecordingInfoRow(
                        "Reference",
                        anchor.referenceTimestamp.toString()
                    )
                    RecordingInfoRow(
                        "Clock Offset",
                        "${anchor.sharedClockOffsetMillis} ms"
                    )
                } else {
                    Text(
                        text = "Session idle",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        Spacing.Small
                    )
                ) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                if (state.simulationEnabled) "Simulation" else "Live"
                            )
                        }
                    )
                    if (state.throttleLevel == PerformanceThrottleLevel.CONSERVE) {
                        AssistChip(
                            onClick = {},
                            label = {
                                Text(
                                    "Throttled"
                                )
                            },
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
private fun RecordingInfoRow(
    label: String,
    value: String
) {
    InfoRow(
        label = label,
        value = value,
        valueColor = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
private fun DeviceStreamGrid(
    devices: List<SensorDevice>
) {
    if (devices.isEmpty()) return

    SectionCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(
                "live-device-status"
            ),
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
                .padding(
                    top = Spacing.Small
                ),
            horizontalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            items(
                devices,
                key = { it.id.value }) { device ->
                DeviceStatusChip(
                    device
                )
            }
        }
    }
}

@Composable
private fun DeviceStatusChip(
    device: SensorDevice
) {
    val backgroundColor =
        when (device.connectionStatus) {
            is ConnectionStatus.Connected -> MaterialTheme.colorScheme.primaryContainer
            ConnectionStatus.Connecting -> MaterialTheme.colorScheme.secondaryContainer
            else -> MaterialTheme.colorScheme.surfaceVariant
        }

    val iconColor =
        when (device.connectionStatus) {
            is ConnectionStatus.Connected -> MaterialTheme.colorScheme.primary
            ConnectionStatus.Connecting -> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.onSurfaceVariant
        }

    Card(
        modifier = Modifier
            .testTag(
                "device-${device.id.value}"
            )
            .semantics(
                mergeDescendants = true
            ) {
                contentDescription =
                    buildString {
                        append(
                            device.displayName
                        )
                        append(
                            ", status "
                        )
                        append(
                            when (device.connectionStatus) {
                                is ConnectionStatus.Connected -> "Connected"
                                ConnectionStatus.Connecting -> "Connecting"
                                ConnectionStatus.Disconnected -> "Disconnected"
                            }
                        )
                    }
            },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(
                Spacing.Medium
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = when (device.connectionStatus) {
                    is ConnectionStatus.Connected -> Icons.Default.CheckCircle
                    else -> Icons.Default.Warning
                },
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(
                    Dimensions.IconSizeDefault
                )
            )
            Text(
                text = device.displayName,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(
                        top = Spacing.ExtraSmall
                    )
                    .testTag(
                        "device-${device.id.value}-name"
                    )
            )
            Text(
                text = when (device.connectionStatus) {
                    is ConnectionStatus.Connected -> "Connected"
                    ConnectionStatus.Connecting -> "Connecting"
                    ConnectionStatus.Disconnected -> "Disconnected"
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.testTag(
                    "device-${device.id.value}-status"
                )
            )
        }
    }
}

@Composable
private fun UploadStatusCard(
    uploads: List<UploadStatus>,
    backlog: UploadBacklogState
) {
    val containerColor =
        when (backlog.level) {
            UploadBacklogLevel.CRITICAL -> MaterialTheme.colorScheme.errorContainer
            UploadBacklogLevel.WARNING -> MaterialTheme.colorScheme.tertiaryContainer
            else -> MaterialTheme.colorScheme.surfaceVariant
        }

    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor
        ),
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
                    label = {
                        Text(
                            "${backlog.queuedCount} queued"
                        )
                    },
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
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.Small
                )
            ) {
                uploads.forEach { upload ->
                    UploadProgressItem(
                        upload
                    )
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
                text = backlog.message
                    ?: "Upload backlog: ${backlog.level}",
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
private fun UploadProgressItem(
    upload: UploadStatus
) {
    val progressValue =
        if (upload.bytesTotal > 0) {
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
                text = "${
                    upload.deviceId.value.take(
                        8
                    )
                }... ${upload.streamType.name}",
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
                .padding(
                    top = Spacing.ExtraSmall
                )
        )
    }
}
