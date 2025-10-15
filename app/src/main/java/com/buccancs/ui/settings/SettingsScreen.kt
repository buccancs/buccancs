package com.buccancs.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.util.Locale

@Composable
fun SettingsRoute(
    onNavigateUp: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    SettingsScreen(
        state = state,
        onHostChanged = viewModel::onHostChanged,
        onPortChanged = viewModel::onPortChanged,
        onUseTlsChanged = viewModel::onUseTlsChanged,
        onRetentionMinFreeChanged = viewModel::onRetentionMinFreeChanged,
        onRetentionMaxSessionsChanged = viewModel::onRetentionMaxSessionsChanged,
        onRetentionMaxAgeDaysChanged = viewModel::onRetentionMaxAgeDaysChanged,
        onApplyOrchestrator = viewModel::applyOrchestrator,
        onApplyRetention = { viewModel.applyRetention(context) },
        onClearMessage = viewModel::clearMessage,
        onNavigateUp = onNavigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    state: SettingsUiState,
    onHostChanged: (String) -> Unit,
    onPortChanged: (String) -> Unit,
    onUseTlsChanged: (Boolean) -> Unit,
    onRetentionMinFreeChanged: (String) -> Unit,
    onRetentionMaxSessionsChanged: (String) -> Unit,
    onRetentionMaxAgeDaysChanged: (String) -> Unit,
    onApplyOrchestrator: () -> Unit,
    onApplyRetention: () -> Unit,
    onClearMessage: () -> Unit,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
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
        Column(
            modifier = Modifier
	            .fillMaxSize()
	            .padding(padding)
	            .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OrchestratorCard(
                state = state,
                onHostChanged = onHostChanged,
                onPortChanged = onPortChanged,
                onUseTlsChanged = onUseTlsChanged,
                onApply = onApplyOrchestrator
            )
            RetentionCard(
                state = state,
                onMinFreeChanged = onRetentionMinFreeChanged,
                onMaxSessionsChanged = onRetentionMaxSessionsChanged,
                onMaxAgeDaysChanged = onRetentionMaxAgeDaysChanged,
                onApply = onApplyRetention
            )
            StorageSummaryCard(state = state)
            state.message?.let {
                MessageCard(message = it, onDismiss = onClearMessage)
            }
        }
    }
}

@Composable
private fun OrchestratorCard(
    state: SettingsUiState,
    onHostChanged: (String) -> Unit,
    onPortChanged: (String) -> Unit,
    onUseTlsChanged: (Boolean) -> Unit,
    onApply: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = "Orchestrator", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = state.hostInput,
                onValueChange = onHostChanged,
                label = { Text(text = "Host") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.portInput,
                onValueChange = onPortChanged,
                label = { Text(text = "Port") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Switch(
                    checked = state.useTls,
                    onCheckedChange = onUseTlsChanged
                )
                Text(text = "Use TLS")
            }
            Button(
                onClick = onApply,
                enabled = !state.isApplying,
                modifier = Modifier.testTag("settings-apply-orchestrator")
            ) {
                Text(text = "Apply Orchestrator")
            }
        }
    }
}

@Composable
private fun RetentionCard(
    state: SettingsUiState,
    onMinFreeChanged: (String) -> Unit,
    onMaxSessionsChanged: (String) -> Unit,
    onMaxAgeDaysChanged: (String) -> Unit,
    onApply: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = "Retention Policy", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Defaults: min free ${formatGigabytes(state.retentionDefaults.minFreeBytes)}, " +
                        "max sessions ${state.retentionDefaults.maxSessions}, " +
                        "max age ${state.retentionDefaults.maxAgeDays} days",
                style = MaterialTheme.typography.bodySmall
            )
            OutlinedTextField(
                value = state.retentionMinFreeInput,
                onValueChange = onMinFreeChanged,
                label = { Text(text = "Minimum free space (GB)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.retentionMaxSessionsInput,
                onValueChange = onMaxSessionsChanged,
                label = { Text(text = "Maximum sessions") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.retentionMaxAgeDaysInput,
                onValueChange = onMaxAgeDaysChanged,
                label = { Text(text = "Maximum age (days)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onApply,
                enabled = !state.isApplying,
                modifier = Modifier.testTag("settings-apply-retention")
            ) {
                Text(text = "Apply Retention")
            }
        }
    }
}

@Composable
private fun StorageSummaryCard(state: SettingsUiState) {
    val storage = state.storageState
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Storage", style = MaterialTheme.typography.titleMedium)
            Text(text = "Total: ${formatBytes(storage.totalBytes)}")
            Text(text = "Available: ${formatBytes(storage.availableBytes)}")
            Text(text = "Used: ${formatBytes(storage.usedBytes)}")
            Text(text = "Status: ${storage.status.name}")
        }
    }
}

@Composable
private fun MessageCard(message: String, onDismiss: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = message, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = onDismiss) {
                Text(text = "Dismiss")
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

private fun formatGigabytes(bytes: Long): String {
    val gb = bytes.toDouble() / (1024.0 * 1024.0 * 1024.0)
    return String.format(Locale.US, "%.2f GB", gb)
}
