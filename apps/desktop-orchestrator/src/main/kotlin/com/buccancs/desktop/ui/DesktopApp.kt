package com.buccancs.desktop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.buccancs.desktop.domain.model.SessionStatus
import com.buccancs.desktop.ui.components.BuccancsCard
import com.buccancs.desktop.ui.components.BuccancsOutlinedCard
import com.buccancs.desktop.ui.components.ConnectedBadge
import com.buccancs.desktop.ui.components.DisconnectedBadge
import com.buccancs.desktop.ui.components.IdleBadge
import com.buccancs.desktop.ui.components.PrimaryButton
import com.buccancs.desktop.ui.components.RecordingBadge
import com.buccancs.desktop.ui.components.SecondaryButton
import com.buccancs.desktop.domain.model.PreviewModality
import com.buccancs.desktop.ui.state.CaptureStreamState
import com.buccancs.desktop.ui.state.ControlPanelState
import com.buccancs.desktop.ui.state.DeviceListItem
import com.buccancs.desktop.ui.state.EventTimelineItem
import com.buccancs.desktop.ui.state.PreviewStreamState
import com.buccancs.desktop.ui.state.RetentionState
import com.buccancs.desktop.ui.state.SessionArchiveItem
import com.buccancs.desktop.ui.state.SessionSummary
import com.buccancs.desktop.ui.state.ShimmerCaptureState
import com.buccancs.desktop.ui.state.TransferStatusItem
import com.buccancs.desktop.ui.state.UnifiedCaptureState
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing
import com.buccancs.desktop.ui.util.decodePreviewImage
import com.buccancs.desktop.viewmodel.AppViewModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

@Composable
fun DesktopApp(
    viewModel: AppViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val formatter =
        rememberFormatter()
    val sessionTitle =
        state.session?.let { "Session ${it.id} (${it.status})" }
            ?: "No active session"
    val sessionActive =
        state.session?.status == SessionStatus.ACTIVE.name

    BuccancsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        Spacing.Medium
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                AppHeader(
                    sessionTitle
                )

                UnifiedCapturePanel(
                    capture = state.capture,
                    sessionActive = sessionActive,
                    formatter = formatter,
                    onStartSession = viewModel::startSession,
                    onStopSession = viewModel::stopSession
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

                state.session?.let {
                    SessionSummaryCard(
                        summary = it,
                        metricsUpdatedAt = it.metrics.updatedAt?.let(
                            formatter::format
                        ),
                        canErase = it.status == SessionStatus.COMPLETED.name,
                        formatter = formatter,
                        onEraseSession = {
                            viewModel.eraseSession(
                                it.id
                            )
                        }
                    )
                }

                DeviceSection(
                    devices = state.devices,
                    formatter = formatter
                )
                RetentionSection(
                    retention = state.retention
                )
                TransferSection(
                    transfers = state.transfers
                )
                EventTimelineSection(
                    events = state.events,
                    formatter = formatter
                )
                PreviewSection(
                    previews = state.previews,
                    formatter = formatter
                )
                ArchiveSection(
                    archives = state.historicalSessions,
                    formatter = formatter
                )
                AlertsSection(
                    alerts = state.alerts
                )
            }
        }
    }
}

@Composable
private fun AppHeader(
    sessionTitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            Spacing.ExtraSmall
        )
    ) {
        Text(
            text = "Buccancs Orchestrator",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = sessionTitle,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun UnifiedCapturePanel(
    capture: UnifiedCaptureState,
    sessionActive: Boolean,
    formatter: DateTimeFormatter,
    onStartSession: () -> Unit,
    onStopSession: () -> Unit
) {
    val subtitle =
        capture.lastUpdated?.let {
            "Last update ${formatter.format(it)}"
        }
            ?: "Awaiting streams"
    BuccancsCard(
        title = "Unified Capture",
        subtitle = subtitle,
        actions = {
            SecondaryButton(
                text = "Stop All",
                onClick = onStopSession,
                enabled = capture.recordingActive
            )
            PrimaryButton(
                text = if (sessionActive) "Recording…" else "Start All",
                onClick = onStartSession,
                enabled = !sessionActive
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Medium
            )
        ) {
            CaptureStreamGroup(
                title = "Thermal IR",
                emptyMessage = "No thermal streams detected",
                streams = capture.thermalStreams,
                formatter = formatter
            )
            CaptureStreamGroup(
                title = "RGB Video",
                emptyMessage = "No RGB streams detected",
                streams = capture.rgbStreams,
                formatter = formatter
            )
            ShimmerSummary(
                shimmer = capture.shimmer,
                formatter = formatter
            )
        }
    }
}

@Composable
private fun CaptureStreamGroup(
    title: String,
    emptyMessage: String,
    streams: List<CaptureStreamState>,
    formatter: DateTimeFormatter
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            Spacing.Small
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (streams.isEmpty()) {
            Text(
                text = emptyMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            streams.forEach { stream ->
                CaptureStreamCard(
                    stream = stream,
                    formatter = formatter
                )
            }
        }
    }
}

@Composable
private fun CaptureStreamCard(
    stream: CaptureStreamState,
    formatter: DateTimeFormatter
) {
    val modeLabel =
        when (stream.modality) {
            PreviewModality.RGB -> "RGB"
            PreviewModality.THERMAL -> "Thermal"
        }
    val subtitle =
        buildString {
            append(
                "${stream.resolution} • $modeLabel"
            )
            stream.fps?.let {
                append(
                    " • ${"%.1f".format(it)} fps"
                )
            }
        }
    BuccancsOutlinedCard(
        title = "${stream.deviceId} – ${stream.cameraId}",
        subtitle = subtitle
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                Spacing.Medium
            )
        ) {
            Text(
                text = "Latency ${"%.0f".format(stream.latencyMs)} ms",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Recording: ${if (stream.recording) "Yes" else "No"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = "Updated ${formatter.format(stream.receivedAt)}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ShimmerSummary(
    shimmer: ShimmerCaptureState,
    formatter: DateTimeFormatter
) {
    BuccancsOutlinedCard(
        title = "Shimmer GSR",
        subtitle = when {
            shimmer.streaming -> "Streaming"
            shimmer.connected -> "Connected"
            else -> "Offline"
        }
    ) {
        Text(
            text = "Sample rate: ${
                shimmer.sampleRateHz?.let { "${"%.1f".format(it)} Hz" } ?: "–"
            }",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Drops: ${shimmer.sampleDrops}  •  Max gap: ${shimmer.maxGapMs} ms",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Range: ${
                shimmer.minValue?.let { "%.2f".format(it) } ?: "–"
            } – ${
                shimmer.maxValue?.let { "%.2f".format(it) } ?: "–"
            }",
            style = MaterialTheme.typography.bodySmall
        )
        shimmer.updatedAt?.let {
            Text(
                text = "Updated ${formatter.format(it)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
internal fun ControlPanel(
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
    val offsetsSummary =
        remember(
            control.clockOffsetsPreview
        ) {
            control.clockOffsetsPreview.entries.joinToString { (deviceId, offset) ->
                "$deviceId=${
                    "%.2f".format(
                        offset
                    )
                }"
            }
        }
    val offsetsLabel =
        if (offsetsSummary.isBlank()) {
            "Clock offsets: n/a"
        } else {
            "Clock offsets: $offsetsSummary"
        }
    Column(
        verticalArrangement = Arrangement.spacedBy(
            Spacing.Medium
        )
    ) {
        BuccancsCard(
            title = "Session Control"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                OutlinedTextField(
                    value = control.operatorId,
                    onValueChange = onOperatorChange,
                    label = {
                        Text(
                            "Operator ID"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.subjectIds,
                    onValueChange = onSubjectChange,
                    label = {
                        Text(
                            "Subject IDs (comma separated)"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                PrimaryButton(
                    text = "Start Session",
                    onClick = onStartSession,
                    enabled = !sessionActive
                )
                SecondaryButton(
                    text = "Stop Session",
                    onClick = onStopSession,
                    enabled = sessionActive
                )
            }
        }
        BuccancsCard(
            title = "Synchronisation Signal",
            subtitle = offsetsLabel,
            actions = {
                PrimaryButton(
                    text = "Send Sync",
                    onClick = onSendSync,
                    enabled = sessionActive
                )
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                OutlinedTextField(
                    value = control.syncSignalType,
                    onValueChange = onSyncTypeChange,
                    label = {
                        Text(
                            "Signal Type"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.syncDelayMs,
                    onValueChange = onSyncDelayChange,
                    label = {
                        Text(
                            "Delay (ms)"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.syncTargets,
                    onValueChange = onSyncTargetsChange,
                    label = {
                        Text(
                            "Target device IDs"
                        )
                    },
                    modifier = Modifier.weight(
                        1.5f
                    ),
                    singleLine = true
                )
            }
        }
        BuccancsCard(
            title = "Event Markers",
            actions = {
                PrimaryButton(
                    text = "Add Event",
                    onClick = onAddEvent,
                    enabled = sessionActive
                )
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                OutlinedTextField(
                    value = control.eventMarkerId,
                    onValueChange = onEventIdChange,
                    label = {
                        Text(
                            "Event ID"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.eventDescription,
                    onValueChange = onEventDescriptionChange,
                    label = {
                        Text(
                            "Description"
                        )
                    },
                    modifier = Modifier.weight(
                        1.5f
                    )
                )
                OutlinedTextField(
                    value = control.eventTargets,
                    onValueChange = onEventTargetsChange,
                    label = {
                        Text(
                            "Targets"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
            }
        }
        BuccancsCard(
            title = "Stimulus Controls",
            actions = {
                PrimaryButton(
                    text = "Trigger",
                    onClick = onTriggerStimulus,
                    enabled = sessionActive
                )
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                OutlinedTextField(
                    value = control.stimulusId,
                    onValueChange = onStimulusIdChange,
                    label = {
                        Text(
                            "Stimulus ID"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.stimulusAction,
                    onValueChange = onStimulusActionChange,
                    label = {
                        Text(
                            "Action"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
                OutlinedTextField(
                    value = control.stimulusTargets,
                    onValueChange = onStimulusTargetsChange,
                    label = {
                        Text(
                            "Targets"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
            }
        }
        BuccancsCard(
            title = "Data Hygiene"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                OutlinedTextField(
                    value = control.subjectEraseId,
                    onValueChange = onSubjectEraseChange,
                    label = {
                        Text(
                            "Subject ID"
                        )
                    },
                    modifier = Modifier.weight(
                        1f
                    ),
                    singleLine = true
                )
                SecondaryButton(
                    text = "Erase Subject",
                    onClick = onSubjectErase
                )
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
    val subtitle =
        "Status: ${summary.status}"
    BuccancsCard(
        title = "Session ${summary.id}",
        subtitle = subtitle
    ) {
        Text(
            "Created: ${
                formatter.format(
                    summary.createdAt
                )
            }"
        )
        summary.startedAt?.let {
            Text(
                "Started: ${
                    formatter.format(
                        it
                    )
                }"
            )
        }
        Text(
            "Elapsed: ${
                durationToReadable(
                    summary.elapsedMillis
                )
            }"
        )
        summary.durationMs?.let {
            Text(
                "Duration: ${
                    durationToReadable(
                        it
                    )
                }"
            )
        }
        Text(
            "Transferred: ${
                bytesToReadable(
                    summary.totalBytes
                )
            }"
        )
        if (summary.subjectIds.isNotEmpty()) {
            Text(
                "Subjects: ${summary.subjectIds.joinToString()}"
            )
        }
        HorizontalDivider()
        Text(
            "Live Metrics",
            style = MaterialTheme.typography.titleSmall
        )
        val metrics =
            summary.metrics
        if (
            listOf(
                metrics.gsrSamples,
                metrics.gsrSampleDrops,
                metrics.gsrOutOfRangeSamples,
                metrics.videoFrames,
                metrics.thermalFrames,
                metrics.audioSamples,
                metrics.videoFrameDrops,
                metrics.thermalFrameDrops
            ).any { it > 0 }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                MetricItem(
                    "GSR Samples",
                    formatCount(
                        metrics.gsrSamples
                    ),
                    Modifier.weight(
                        1f
                    )
                )
                MetricItem(
                    "Video Frames",
                    formatCount(
                        metrics.videoFrames
                    ),
                    Modifier.weight(
                        1f
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                MetricItem(
                    "Thermal Frames",
                    formatCount(
                        metrics.thermalFrames
                    ),
                    Modifier.weight(
                        1f
                    )
                )
                MetricItem(
                    "Audio Samples",
                    formatCount(
                        metrics.audioSamples
                    ),
                    Modifier.weight(
                        1f
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                MetricItem(
                    "GSR Drops",
                    formatCount(
                        metrics.gsrSampleDrops
                    ),
                    Modifier.weight(
                        1f
                    )
                )
                MetricItem(
                    "GSR Out of Range",
                    formatCount(
                        metrics.gsrOutOfRangeSamples
                    ),
                    Modifier.weight(
                        1f
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                MetricItem(
                    "GSR Avg Hz",
                    formatHz(
                        metrics.gsrAverageHz
                    ),
                    Modifier.weight(
                        1f
                    )
                )
                MetricItem(
                    "GSR Max Gap (ms)",
                    formatCount(
                        metrics.gsrMaxGapMs
                    ),
                    Modifier.weight(
                        1f
                    )
                )
            }
            if (metrics.gsrMinValue != null || metrics.gsrMaxValue != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        Spacing.Medium
                    )
                ) {
                    MetricItem(
                        "GSR Min",
                        metrics.gsrMinValue?.let {
                            formatDouble(
                                it
                            )
                        }
                            ?: "—",
                        Modifier.weight(
                            1f
                        )
                    )
                    MetricItem(
                        "GSR Max",
                        metrics.gsrMaxValue?.let {
                            formatDouble(
                                it
                            )
                        }
                            ?: "—",
                        Modifier.weight(
                            1f
                        )
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                MetricItem(
                    "Video Drops",
                    formatCount(
                        metrics.videoFrameDrops
                    ),
                    Modifier.weight(
                        1f
                    )
                )
                MetricItem(
                    "Thermal Drops",
                    formatCount(
                        metrics.thermalFrameDrops
                    ),
                    Modifier.weight(
                        1f
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                MetricItem(
                    "Video Avg FPS",
                    formatFps(
                        metrics.videoAverageFps
                    ),
                    Modifier.weight(
                        1f
                    )
                )
                MetricItem(
                    "Thermal Avg FPS",
                    formatFps(
                        metrics.thermalAverageFps
                    ),
                    Modifier.weight(
                        1f
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                MetricItem(
                    "Video Max Gap (ms)",
                    formatCount(
                        metrics.videoMaxGapMs
                    ),
                    Modifier.weight(
                        1f
                    )
                )
                MetricItem(
                    "Thermal Max Gap (ms)",
                    formatCount(
                        metrics.thermalMaxGapMs
                    ),
                    Modifier.weight(
                        1f
                    )
                )
            }
        } else {
            Text(
                "No samples received yet",
                style = MaterialTheme.typography.bodySmall
            )
        }
        metricsUpdatedAt?.let {
            Text(
                "Metrics updated: $it",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            Button(
                onClick = onEraseSession,
                enabled = canErase
            ) {
                Text(
                    "Erase Session"
                )
            }
            if (!canErase) {
                Text(
                    "Stop session before erasing.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


@Composable
private fun MetricItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            Spacing.ExtraSmall
        )
    ) {
        Text(
            label,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun DeviceSection(
    devices: List<DeviceListItem>,
    formatter: DateTimeFormatter
) {
    val summary =
        if (devices.isEmpty()) {
            "No devices registered"
        } else {
            val connected =
                devices.count { it.connected }
            "Connected: $connected / ${devices.size}"
        }
    BuccancsCard(
        title = "Devices",
        subtitle = summary
    ) {
        if (devices.isEmpty()) {
            Text(
                text = "No devices connected",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .heightIn(
                        max = 400.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.Small
                )
            ) {
                items(
                    devices
                ) { device ->
                    DeviceCard(
                        device = device,
                        formatter = formatter
                    )
                }
            }
        }
    }
}

@Composable
private fun DeviceCard(
    device: DeviceListItem,
    formatter: DateTimeFormatter
) {
    val colors =
        when {
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

    BuccancsOutlinedCard(
        title = "${device.id} - ${device.model}",
        subtitle = device.platform
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            if (device.connected) {
                ConnectedBadge()
            } else {
                DisconnectedBadge()
            }
            if (device.recording) {
                RecordingBadge()
            } else {
                IdleBadge()
            }
        }
        device.sessionId?.let {
            Text(
                "Session: $it",
                style = MaterialTheme.typography.bodySmall
            )
        }
        device.batteryPercent?.let {
            Text(
                "Battery: ${
                    "%.1f".format(
                        it
                    )
                }%",
                style = MaterialTheme.typography.bodySmall
            )
        }
        device.previewLatencyMs?.let {
            Text(
                "Preview latency: ${
                    "%.1f".format(
                        it
                    )
                } ms",
                style = MaterialTheme.typography.bodySmall
            )
        }
        device.clockOffsetMs?.let {
            Text(
                "Clock offset: ${
                    "%.2f".format(
                        it
                    )
                } ms",
                style = MaterialTheme.typography.bodySmall
            )
        }
        device.lastHeartbeat?.let {
            Text(
                "Heartbeat: ${
                    formatter.format(
                        it
                    )
                }",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
internal fun RetentionSection(
    retention: RetentionState
) {
    val subtitle =
        "Total stored: ${
            bytesToReadable(
                retention.totalBytes
            )
        }"
    BuccancsCard(
        title = "Retention",
        subtitle = subtitle
    ) {
        if (retention.perSessionBytes.isEmpty() && retention.perDeviceBytes.isEmpty()) {
            Text(
                "No retention data available"
            )
        } else {
            if (retention.perSessionBytes.isNotEmpty()) {
                Text(
                    "Per session:",
                    style = MaterialTheme.typography.titleSmall
                )
                retention.perSessionBytes.forEach { (sessionId, bytes) ->
                    Text(
                        "$sessionId -> ${
                            bytesToReadable(
                                bytes
                            )
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            if (retention.perDeviceBytes.isNotEmpty()) {
                HorizontalDivider()
                Text(
                    "Per device:",
                    style = MaterialTheme.typography.titleSmall
                )
                retention.perDeviceBytes.forEach { (deviceId, bytes) ->
                    Text(
                        "$deviceId -> ${
                            bytesToReadable(
                                bytes
                            )
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            if (retention.perSessionDeviceBytes.isNotEmpty()) {
                HorizontalDivider()
                Text(
                    "Per session/device:",
                    style = MaterialTheme.typography.titleSmall
                )
                retention.perSessionDeviceBytes.forEach { (sessionId, devices) ->
                    Text(
                        "Session $sessionId",
                        style = MaterialTheme.typography.bodySmall
                    )
                    devices.forEach { (deviceId, bytes) ->
                        Text(
                            "    $deviceId -> ${
                                bytesToReadable(
                                    bytes
                                )
                            }",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
        if (retention.breaches.isNotEmpty()) {
            HorizontalDivider()
            Text(
                text = "Quota alerts",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.error
            )
            retention.breaches.forEach {
                Text(
                    it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}


@Composable
internal fun TransferSection(
    transfers: List<TransferStatusItem>
) {
    BuccancsCard(
        title = "Data Transfers"
    ) {
        if (transfers.isEmpty()) {
            Text(
                "No transfers recorded"
            )
        } else {
            transfers.forEach { transfer ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        Spacing.ExtraSmall
                    )
                ) {
                    Text(
                        "Session ${transfer.sessionId} - Device ${transfer.deviceId}"
                    )
                    Text(
                        "File: ${transfer.fileName} (${transfer.streamType ?: "unknown"})"
                    )
                    val progress =
                        "${
                            bytesToReadable(
                                transfer.bytesTransferred
                            )
                        } / ${
                            bytesToReadable(
                                transfer.bytesTotal
                            )
                        }"
                    Text(
                        "State: ${transfer.state} (attempt ${transfer.attempt}) - $progress"
                    )
                    transfer.errorMessage?.takeIf { it.isNotBlank() }
                        ?.let { error ->
                            Text(
                                "Error: $error",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                }
                HorizontalDivider()
            }
        }
    }
}


@Composable
private fun EventTimelineSection(
    events: List<EventTimelineItem>,
    formatter: DateTimeFormatter
) {
    BuccancsCard(
        title = "Event Timeline"
    ) {
        if (events.isEmpty()) {
            Text(
                "No events logged"
            )
        } else {
            events.forEach { event ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        Spacing.ExtraSmall
                    )
                ) {
                    Text(
                        "Event ${event.eventId} - ${event.label}"
                    )
                    Text(
                        "Time: ${
                            formatter.format(
                                event.timestamp
                            )
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                    if (event.deviceIds.isNotEmpty()) {
                        Text(
                            "Targets: ${event.deviceIds.joinToString()}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                HorizontalDivider()
            }
        }
    }
}


@Composable
private fun PreviewSection(
    previews: List<PreviewStreamState>,
    formatter: DateTimeFormatter
) {
    BuccancsCard(
        title = "Live Preview"
    ) {
        if (previews.isEmpty()) {
            Text(
                "No preview frames received yet"
            )
        } else {
            previews.forEach { preview ->
                val bitmap =
                    remember(
                        preview.payload
                    ) {
                        decodePreviewImage(
                            preview.payload
                        )
                    }
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        Spacing.ExtraSmall
                    )
                ) {
                    Text(
                        "${preview.deviceId} - ${preview.cameraId} (${preview.modality.name.lowercase(Locale.ROOT).replaceFirstChar { it.titlecase(Locale.getDefault()) }}) ${preview.width}x${preview.height} • ${preview.mimeType}"
                    )
                    Text(
                        text = "Latency ${
                            "%.1f".format(
                                preview.latencyMs
                            )
                        } ms - Received ${
                            formatter.format(
                                preview.receivedAt
                            )
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                    bitmap?.let { image ->
                        Image(
                            bitmap = image,
                            contentDescription = "Preview ${preview.deviceId}-${preview.cameraId}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(
                                    160.dp
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                        ?: Text(
                            "Preview unavailable (unsupported format)",
                            style = MaterialTheme.typography.bodySmall
                        )
                }
                HorizontalDivider()
            }
        }
    }
}


@Composable
private fun ArchiveSection(
    archives: List<SessionArchiveItem>,
    formatter: DateTimeFormatter
) {
    if (archives.isEmpty()) return
    BuccancsCard(
        title = "Archived Sessions",
        subtitle = "Count: ${archives.size}"
    ) {
        archives.forEach { archive ->
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.ExtraSmall
                )
            ) {
                Text(
                    "Session ${archive.id} - ${archive.status}"
                )
                Text(
                    "Created: ${
                        formatter.format(
                            archive.createdAt
                        )
                    }",
                    style = MaterialTheme.typography.bodySmall
                )
                archive.startedAt?.let {
                    Text(
                        "Started: ${
                            formatter.format(
                                it
                            )
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                archive.stoppedAt?.let {
                    Text(
                        "Stopped: ${
                            formatter.format(
                                it
                            )
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                archive.durationMs?.let {
                    Text(
                        "Duration: ${
                            durationToReadable(
                                it
                            )
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    "Total bytes: ${
                        bytesToReadable(
                            archive.totalBytes
                        )
                    }",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    "Subjects: ${archive.subjects.joinToString()}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    "Events: ${archive.eventCount} - Devices: ${archive.deviceCount}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            HorizontalDivider()
        }
    }
}


@Composable
private fun AlertsSection(
    alerts: List<String>
) {
    if (alerts.isEmpty()) return
    BuccancsCard(
        title = "Alerts",
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        alerts.forEach { alert ->
            Text(
                alert
            )
        }
    }
}


@Composable
private fun rememberFormatter(): DateTimeFormatter =
    remember {
        DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss"
        )
            .withZone(
                ZoneId.systemDefault()
            )
    }

private fun bytesToReadable(
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
    val exponent =
        (ln(bytes.toDouble()) / ln(
            1024.0
        )).toInt()
            .coerceAtMost(
                units.lastIndex
            )
    val value =
        bytes / 1024.0.pow(
            exponent.toDouble()
        )
    return String.format(
        Locale.US,
        "%.2f %s",
        value,
        units[exponent]
    )
}

private fun durationToReadable(
    durationMs: Long
): String {
    if (durationMs <= 0) return "00:00:00.000"
    val hours =
        durationMs / 3_600_000
    val minutes =
        (durationMs % 3_600_000) / 60_000
    val seconds =
        (durationMs % 60_000) / 1_000
    val millis =
        durationMs % 1_000
    return String.format(
        Locale.US,
        "%02d:%02d:%02d.%03d",
        hours,
        minutes,
        seconds,
        millis
    )
}

private fun formatCount(
    value: Long
): String =
    String.format(
        Locale.UK,
        "%,d",
        value
    )

private fun formatFps(
    value: Double
): String =
    if (!value.isFinite()) "0.0" else String.format(
        Locale.UK,
        "%.1f",
        value
    )

private fun formatHz(
    value: Double
): String =
    if (!value.isFinite()) "0.0" else String.format(
        Locale.UK,
        "%.1f",
        value
    )

private fun formatDouble(
    value: Double
): String =
    if (!value.isFinite()) "—" else String.format(
        Locale.UK,
        "%.3f",
        value
    )
