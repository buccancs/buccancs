package com.buccancs.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.BuildConfig
import com.buccancs.ui.components.InfoRow
import com.buccancs.ui.components.SectionHeader
import com.buccancs.ui.theme.Spacing
import java.util.*

@Composable
fun SettingsRoute(
    onNavigateUp: () -> Unit = {},
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
        onClearMessage = viewModel::clearMessage
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
    onClearMessage: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            item {
                SectionHeader(
                    title = "Connection",
                    icon = Icons.Default.Cloud
                )
            }
            item {
                OrchestratorCard(
                    state = state,
                    onHostChanged = onHostChanged,
                    onPortChanged = onPortChanged,
                    onUseTlsChanged = onUseTlsChanged,
                    onApply = onApplyOrchestrator
                )
            }

            item {
                SectionHeader(
                    title = "Data Management",
                    icon = Icons.Default.Storage
                )
            }
            item {
                RetentionCard(
                    state = state,
                    onMinFreeChanged = onRetentionMinFreeChanged,
                    onMaxSessionsChanged = onRetentionMaxSessionsChanged,
                    onMaxAgeDaysChanged = onRetentionMaxAgeDaysChanged,
                    onApply = onApplyRetention
                )
            }
            item {
                StorageSummaryCard(state = state)
            }

            item {
                SectionHeader(
                    title = "Simulation",
                    icon = Icons.Default.Science
                )
            }
            item {
                SimulationModeCard(state = state)
            }

            item {
                SectionHeader(
                    title = "About",
                    icon = Icons.Default.Info
                )
            }
            item {
                AppInfoCard()
            }

            if (state.message != null) {
                item {
                    MessageCard(message = state.message, onDismiss = onClearMessage)
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    SectionHeader(title = title, icon = icon)
}

@Composable
private fun OrchestratorCard(
    state: SettingsUiState,
    onHostChanged: (String) -> Unit,
    onPortChanged: (String) -> Unit,
    onUseTlsChanged: (Boolean) -> Unit,
    onApply: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            Text(
                text = "Orchestrator Connection",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
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
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Use TLS",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Switch(
                        checked = state.useTls,
                        onCheckedChange = onUseTlsChanged
                    )
                }
            }
            FilledTonalButton(
                onClick = onApply,
                enabled = !state.isApplying,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("settings-apply-orchestrator")
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Apply Connection")
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
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            Text(
                text = "Retention Policy",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Defaults: ${formatGigabytes(state.retentionDefaults.minFreeBytes)} free, " +
                            "${state.retentionDefaults.maxSessions} sessions max, " +
                            "${state.retentionDefaults.maxAgeDays} days",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
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
            FilledTonalButton(
                onClick = onApply,
                enabled = !state.isApplying,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("settings-apply-retention")
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Apply Policy")
            }
        }
    }
}

@Composable
private fun StorageSummaryCard(state: SettingsUiState) {
    val storage = state.storageState
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            Text(
                text = "Storage Status",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.Small))
            InfoRow("Total", formatBytes(storage.totalBytes))
            InfoRow("Available", formatBytes(storage.availableBytes))
            InfoRow("Used", formatBytes(storage.usedBytes))
            InfoRow("Status", storage.status.name)
        }
    }
}

@Composable
private fun StorageInfoRow(label: String, value: String) {
    InfoRow(label = label, value = value)
}

@Composable
private fun SimulationModeCard(state: SettingsUiState) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            Text(
                text = "Simulation Mode",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Simulation mode uses generated data instead of real sensors for testing purposes.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Enable Simulation",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Switch(
                        checked = false,
                        onCheckedChange = {},
                        enabled = false
                    )
                }
            }
        }
    }
}

@Composable
private fun AppInfoCard() {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            Text(
                text = "Application Info",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.Small))
            InfoRow("Version", BuildConfig.VERSION_NAME)
            InfoRow("Build", BuildConfig.VERSION_CODE.toString())
            InfoRow("Type", if (BuildConfig.DEBUG) "Debug" else "Release")
        }
    }
}

@Composable
private fun MessageCard(message: String, onDismiss: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.errorContainer
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.Medium),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.weight(1f)
                )
                FilledTonalIconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss",
                        modifier = Modifier.size(16.dp)
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

private fun formatGigabytes(bytes: Long): String {
    val gb = bytes.toDouble() / (1024.0 * 1024.0 * 1024.0)
    return String.format(Locale.US, "%.2f GB", gb)
}
