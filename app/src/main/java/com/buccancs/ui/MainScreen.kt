package com.buccancs.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.platform.testTag
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TimeSyncQuality
import com.buccancs.domain.model.TimeSyncStatus
import com.buccancs.ui.calibration.CalibrationActions
import com.buccancs.ui.calibration.CalibrationPanel
import com.buccancs.ui.calibration.CalibrationUiState
import com.buccancs.ui.calibration.CalibrationViewModel
import java.util.*

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel(),
    calibrationViewModel: CalibrationViewModel = hiltViewModel(),
    onOpenLiveSession: () -> Unit = {},
    onOpenLibrary: () -> Unit = {},
    onOpenSettings: () -> Unit = {},
    onOpenTopdon: (DeviceId) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val calibrationState by calibrationViewModel.uiState.collectAsStateWithLifecycle()
    val calibrationActions = CalibrationActions(
        onRowsChanged = calibrationViewModel::updatePatternRows,
        onColsChanged = calibrationViewModel::updatePatternCols,
        onSquareSizeChanged = calibrationViewModel::updateSquareSizeMm,
        onRequiredPairsChanged = calibrationViewModel::updateRequiredPairs,
        onApplySettings = calibrationViewModel::applyPatternSettings,
        onStartSession = calibrationViewModel::startSession,
        onCapturePair = calibrationViewModel::capture,
        onComputeCalibration = calibrationViewModel::computeCalibration,
        onLoadCachedResult = calibrationViewModel::loadCachedResult,
        onClearSession = calibrationViewModel::clearSession,
        onRemoveCapture = calibrationViewModel::removeCapture
    )
    MainScreen(
        state = state,
        onSessionIdChanged = viewModel::onSessionIdChanged,
        onToggleSimulation = viewModel::toggleSimulation,
        onConnectDevice = viewModel::connectDevice,
        onDisconnectDevice = viewModel::disconnectDevice,
        onStartRecording = viewModel::startRecording,
        onStopRecording = viewModel::stopRecording,
        onOrchestratorHostChanged = viewModel::onOrchestratorHostChanged,
        onOrchestratorPortChanged = viewModel::onOrchestratorPortChanged,
        onOrchestratorUseTlsChanged = viewModel::onOrchestratorUseTlsChanged,
        onApplyConfig = viewModel::applyOrchestratorConfig,
        onClearConfigMessage = viewModel::clearConfigMessage,
        onOpenLiveSession = onOpenLiveSession,
        onOpenLibrary = onOpenLibrary,
        onOpenSettings = onOpenSettings,
        onOpenTopdon = onOpenTopdon,
        calibrationState = calibrationState,
        calibrationActions = calibrationActions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainUiState,
    onSessionIdChanged: (String) -> Unit,
    onToggleSimulation: () -> Unit,
    onConnectDevice: (DeviceId) -> Unit,
    onDisconnectDevice: (DeviceId) -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onOrchestratorHostChanged: (String) -> Unit,
    onOrchestratorPortChanged: (String) -> Unit,
    onOrchestratorUseTlsChanged: (Boolean) -> Unit,
    onApplyConfig: () -> Unit,
    onClearConfigMessage: () -> Unit,
    onOpenLiveSession: () -> Unit,
    onOpenLibrary: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenTopdon: (DeviceId) -> Unit,
    calibrationState: CalibrationUiState,
    calibrationActions: CalibrationActions
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Multi-Modal Capture",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        TimeSyncStatusView(state.timeSyncStatus)
                    },
                    colors = TopAppBarDefaults.topAppBarColors()
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    NavigationLinks(
                        onOpenLiveSession = onOpenLiveSession,
                        onOpenLibrary = onOpenLibrary,
                        onOpenSettings = onOpenSettings
                    )
                }
                item {
                    SessionCard(
                        state = state,
                        onSessionIdChanged = onSessionIdChanged,
                        onToggleSimulation = onToggleSimulation,
                        onStartRecording = onStartRecording,
                        onStopRecording = onStopRecording,
                        onOrchestratorHostChanged = onOrchestratorHostChanged,
                        onOrchestratorPortChanged = onOrchestratorPortChanged,
                        onOrchestratorUseTlsChanged = onOrchestratorUseTlsChanged,
                        onApplyConfig = onApplyConfig,
                        onClearConfigMessage = onClearConfigMessage
                    )
                }
                item {
                    CalibrationPanel(
                        state = calibrationState,
                        actions = calibrationActions
                    )
                }
                if (state.errorMessage != null) {
                    item {
                        ErrorBanner(message = state.errorMessage)
                    }
                }
                if (state.deviceEvents.isNotEmpty()) {
                    item {
                        DeviceEventLogCard(state.deviceEvents)
                    }
                }
                items(state.devices, key = { it.id.value }) { device ->
                    DeviceCard(
                        device = device,
                        onConnect = { onConnectDevice(device.id) },
                        onDisconnect = { onDisconnectDevice(device.id) },
                        onOpenTopdon = if (device.supportsTopdon) {
                            { onOpenTopdon(device.id) }
                        } else {
                            null
                        }
                    )
                }
            }
        }
        if (state.showSyncFlash) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.6f))
            )
        }
    }
}

@Composable
private fun NavigationLinks(
    onOpenLiveSession: () -> Unit,
    onOpenLibrary: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onOpenLiveSession,
            modifier = Modifier
                .weight(1f)
                .testTag("nav-live-session")
        ) {
            Text(text = "Live Session")
        }
        Button(
            onClick = onOpenLibrary,
            modifier = Modifier
                .weight(1f)
                .testTag("nav-library")
        ) {
            Text(text = "Sessions")
        }
        OutlinedButton(
            onClick = onOpenSettings,
            modifier = Modifier
                .weight(1f)
                .testTag("nav-settings")
        ) {
            Text(text = "Settings")
        }
    }
}

@Composable
private fun SessionCard(
    state: MainUiState,
    onSessionIdChanged: (String) -> Unit,
    onToggleSimulation: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onOrchestratorHostChanged: (String) -> Unit,
    onOrchestratorPortChanged: (String) -> Unit,
    onOrchestratorUseTlsChanged: (Boolean) -> Unit,
    onApplyConfig: () -> Unit,
    onClearConfigMessage: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Session Control",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            OutlinedTextField(
                value = state.sessionIdInput,
                onValueChange = onSessionIdChanged,
                label = { Text(text = "Session ID") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onStartRecording,
                    enabled = !state.isRecording && !state.isBusy
                ) {
                    Text(text = "Start")
                }
                OutlinedButton(
                    onClick = onStopRecording,
                    enabled = state.isRecording && !state.isBusy
                ) {
                    Text(text = "Stop")
                }
                TextButton(
                    onClick = onToggleSimulation
                ) {
                    Text(
                        text = if (state.simulationEnabled) "Disable Simulation" else "Enable Simulation"
                    )
                }
            }
            val anchorText = state.anchorReference?.toString() ?: "n/a"
            Text(
                text = "Recording state: ${state.recordingLifecycle.name} (anchor: $anchorText)",
                style = MaterialTheme.typography.bodyMedium
            )
            val offsetText = state.sharedClockOffsetMillis?.let { "$it ms" } ?: "-"
            Text(
                text = "Shared clock offset: $offsetText",
                style = MaterialTheme.typography.bodySmall
            )
            state.lastCommandMessage?.let { commandMessage ->
                Text(
                    text = "Last command: $commandMessage",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = "Orchestrator status: ${state.orchestratorConnectionStatus}",
                style = MaterialTheme.typography.bodySmall
            )
            HorizontalDivider()
            Text(
                text = "Orchestrator Connection",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            OutlinedTextField(
                value = state.orchestratorHostInput,
                onValueChange = onOrchestratorHostChanged,
                label = { Text("Host") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = state.orchestratorPortInput,
                onValueChange = onOrchestratorPortChanged,
                label = { Text("Port") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Switch(
                    checked = state.orchestratorUseTls,
                    onCheckedChange = onOrchestratorUseTlsChanged
                )
                Text(text = if (state.orchestratorUseTls) "TLS enabled" else "TLS disabled")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        onClearConfigMessage()
                        onApplyConfig()
                    },
                    enabled = state.orchestratorConfigDirty
                ) {
                    Text(text = "Save connection")
                }
                if (state.configMessage != null) {
                    Text(
                        text = state.configMessage,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorBanner(message: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}

@Composable
private fun DeviceEventLogCard(events: List<DeviceEventUiModel>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Recent Commands", style = MaterialTheme.typography.titleMedium)
            if (events.isEmpty()) {
                Text(
                    text = "No recent events",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                events.forEach { event ->
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "${event.label} (${event.type})",
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
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun DeviceCard(
    device: DeviceUiModel,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onOpenTopdon: (() -> Unit)?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("device-card-${device.id.value}"),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = device.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = device.typeLabel,
                style = MaterialTheme.typography.bodyMedium
            )
            val capabilityLine = device.capabilityLabels.joinToString(", ").ifEmpty { "n/a" }
            Text(
                text = "Capabilities: $capabilityLine",
                style = MaterialTheme.typography.bodySmall
            )
            val statusLine = buildString {
                append("Status: ${device.connectionStatus}")
                if (device.isSimulated) {
                    append(" (simulated)")
                }
            }
            Text(text = statusLine, style = MaterialTheme.typography.bodyMedium)
            val batteryText = device.batteryPercent?.let { "$it%" } ?: "n/a"
            Text(text = "Battery: $batteryText", style = MaterialTheme.typography.bodySmall)
            if (device.streams.isNotEmpty()) {
                HorizontalDivider()
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    device.streams.forEach { stream ->
                        Text(
                            text = "${stream.typeLabel} | ${stream.detail}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Last sample: ${stream.lastSampleTimestamp}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onConnect,
                    enabled = !device.isConnected
                ) {
                    Text(text = "Connect")
                }
                OutlinedButton(
                    onClick = onDisconnect,
                    enabled = device.isConnected
                ) {
                    Text(text = "Disconnect")
                }
                if (onOpenTopdon != null) {
                    TextButton(
                        onClick = onOpenTopdon,
                        modifier = Modifier.testTag("device-open-console-${device.id.value}")
                    ) {
                        Text("Open Console")
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeSyncStatusView(status: TimeSyncStatus) {
    val offsetText = if (status.offsetMillis == Long.MAX_VALUE) {
        "n/a"
    } else {
        String.format(Locale.US, "%d ms", status.offsetMillis)
    }
    val rttText = if (status.roundTripMillis == Long.MAX_VALUE) {
        "n/a"
    } else {
        String.format(Locale.US, "%d ms", status.roundTripMillis)
    }
    val filteredRttText = if (status.filteredRoundTripMillis.isFinite()) {
        String.format(Locale.US, "%.1f ms", status.filteredRoundTripMillis)
    } else {
        "n/a"
    }
    val driftText = if (status.driftEstimateMillisPerMinute.isFinite()) {
        String.format(Locale.US, "%.2f ms/min", status.driftEstimateMillisPerMinute)
    } else {
        "n/a"
    }
    val qualityLabel = when (status.quality) {
        TimeSyncQuality.GOOD -> "Good"
        TimeSyncQuality.FAIR -> "Fair"
        TimeSyncQuality.POOR -> "Poor"
        TimeSyncQuality.UNKNOWN -> "Unknown"
    }
    val text = "Offset $offsetText | RTT $rttText (filtered $filteredRttText) | Drift $driftText | $qualityLabel"
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(end = 12.dp)
    )
}
