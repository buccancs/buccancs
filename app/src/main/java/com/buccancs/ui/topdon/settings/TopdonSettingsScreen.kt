package com.buccancs.ui.topdon.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.ui.components.topdon.FpsLimitDialog
import com.buccancs.ui.components.topdon.PaletteSelectionDialog
import com.buccancs.ui.components.topdon.SuperSamplingDialog
import com.buccancs.ui.common.HorizontalDivider
import com.buccancs.ui.topdon.TopdonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopdonSettingsRoute(
    onNavigateUp: () -> Unit,
    viewModel: TopdonViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    var showPaletteDialog by remember { mutableStateOf(false) }
    var showSuperSamplingDialog by remember { mutableStateOf(false) }
    var showFpsDialog by remember { mutableStateOf(false) }

    TopdonSettingsScreen(
        onNavigateUp = onNavigateUp,
        palette = state.settings.palette,
        superSampling = state.settings.superSampling,
        autoConnect = state.settings.autoConnectOnAttach,
        previewFps = state.settings.previewFpsLimit,
        onPaletteClick = { showPaletteDialog = true },
        onSuperSamplingClick = { showSuperSamplingDialog = true },
        onFpsClick = { showFpsDialog = true },
        onAutoConnectChange = viewModel::setAutoConnect
    )

    if (showPaletteDialog) {
        PaletteSelectionDialog(
            currentPalette = state.settings.palette,
            onDismiss = { showPaletteDialog = false },
            onConfirm = viewModel::selectPalette
        )
    }

    if (showSuperSamplingDialog) {
        SuperSamplingDialog(
            currentFactor = state.settings.superSampling,
            onDismiss = { showSuperSamplingDialog = false },
            onConfirm = viewModel::selectSuperSampling
        )
    }

    if (showFpsDialog) {
        FpsLimitDialog(
            currentFps = state.settings.previewFpsLimit,
            onDismiss = { showFpsDialog = false },
            onConfirm = viewModel::updatePreviewFps
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopdonSettingsScreen(
    onNavigateUp: () -> Unit,
    palette: TopdonPalette,
    superSampling: TopdonSuperSamplingFactor,
    autoConnect: Boolean,
    previewFps: Int,
    onPaletteClick: () -> Unit,
    onSuperSamplingClick: () -> Unit,
    onFpsClick: () -> Unit,
    onAutoConnectChange: (Boolean) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Camera Settings",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsSection(title = "Display") {
                SettingsItem(
                    title = "Colour Palette",
                    subtitle = palette.name.lowercase().replaceFirstChar { it.uppercase() },
                    onClick = onPaletteClick
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Super Sampling",
                    subtitle = when (superSampling) {
                        TopdonSuperSamplingFactor.X1 -> "1x (Default)"
                        TopdonSuperSamplingFactor.X2 -> "2x (Better Quality)"
                        TopdonSuperSamplingFactor.X4 -> "4x (Best Quality)"
                    },
                    onClick = onSuperSamplingClick
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Preview Frame Rate",
                    subtitle = "$previewFps FPS",
                    onClick = onFpsClick
                )
            }

            SettingsSection(title = "Connection") {
                SettingsToggleItem(
                    title = "Auto-Connect",
                    subtitle = "Automatically connect when device is attached",
                    checked = autoConnect,
                    onCheckedChange = onAutoConnectChange
                )
            }

            SettingsSection(title = "Storage") {
                SettingsItem(
                    title = "Save Location",
                    subtitle = "Pictures/BuccanCS/Thermal",
                    onClick = { }
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Image Format",
                    subtitle = "PNG (with thermal data)",
                    onClick = { }
                )
            }

            SettingsSection(title = "Temperature") {
                SettingsItem(
                    title = "Unit",
                    subtitle = "Celsius (°C)",
                    onClick = { }
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Emissivity",
                    subtitle = "0.95 (Default)",
                    onClick = { }
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Distance Correction",
                    subtitle = "1.0 metre",
                    onClick = { }
                )
            }

            SettingsSection(title = "About") {
                SettingsItem(
                    title = "Device Information",
                    subtitle = "View device details and firmware",
                    onClick = { }
                )

                HorizontalDivider()

                SettingsItem(
                    title = "App Version",
                    subtitle = "1.0.0",
                    onClick = { }
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            content()
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SettingsToggleItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}
