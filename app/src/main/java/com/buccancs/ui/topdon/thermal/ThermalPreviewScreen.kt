package com.buccancs.ui.topdon.thermal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.ui.components.topdon.MeasurementMode
import com.buccancs.ui.components.topdon.TopdonAppBarIconButton
import com.buccancs.ui.components.topdon.TopdonBackButton
import com.buccancs.ui.components.topdon.TopdonButton
import com.buccancs.ui.components.topdon.TopdonCenterAlignedTopAppBar
import com.buccancs.ui.components.topdon.TopdonElevatedCard
import com.buccancs.ui.components.topdon.TopdonEmptyState
import com.buccancs.ui.components.topdon.TopdonIconButton
import com.buccancs.ui.components.topdon.TopdonLinearProgress
import com.buccancs.ui.components.topdon.TopdonLoadingOverlay
import com.buccancs.ui.components.topdon.TopdonMeasurementModeSelector
import com.buccancs.ui.components.topdon.TopdonOutlinedButton
import com.buccancs.ui.theme.topdon.TopdonColors
import com.buccancs.ui.theme.topdon.TopdonSpacing
import com.buccancs.ui.topdon.ThermalUiStateCode
import com.buccancs.ui.topdon.TopdonUiState
import com.buccancs.ui.topdon.TopdonViewModel
import kotlin.math.roundToInt

/**
 * Full-screen thermal preview with camera controls
 * Migrated from IRThermalNightActivity
 */
@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun ThermalPreviewRoute(
    deviceId: DeviceId,
    onNavigateUp: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: TopdonViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(
        deviceId
    ) {
        viewModel.setActiveDevice(
            deviceId
        )
        viewModel.refresh()
    }

    ThermalPreviewScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onNavigateToSettings = onNavigateToSettings,
        onRefresh = viewModel::refresh,
        onConnect = viewModel::connect,
        onStartPreview = viewModel::startPreview,
        onStopPreview = viewModel::stopPreview,
        onCapture = viewModel::capturePhoto,
        onStartRecording = viewModel::startRecording,
        onStopRecording = viewModel::stopRecording,
        onSelectPalette = viewModel::selectPalette,
        onSelectSuperSampling = viewModel::selectSuperSampling,
        onUpdatePreviewFps = viewModel::updatePreviewFps
    )
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun ThermalPreviewScreen(
    state: TopdonUiState,
    onNavigateUp: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onRefresh: () -> Unit,
    onConnect: () -> Unit,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit,
    onCapture: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onSelectPalette: (TopdonPalette) -> Unit,
    onSelectSuperSampling: (TopdonSuperSamplingFactor) -> Unit,
    onUpdatePreviewFps: (Int) -> Unit
) {
    val (previewIcon, chipContainer, chipLabelColour) =
        when (state.previewStateCode) {
            ThermalUiStateCode.PREVIEW_STREAMING,
            ThermalUiStateCode.PREVIEW_RECORDING ->
                Triple(
                    Icons.Default.PlayArrow,
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.onPrimaryContainer
                )

            ThermalUiStateCode.PREVIEW_BUFFERING ->
                Triple(
                    Icons.Default.Refresh,
                    MaterialTheme.colorScheme.secondaryContainer,
                    MaterialTheme.colorScheme.onSecondaryContainer
                )

            ThermalUiStateCode.PREVIEW_ERROR ->
                Triple(
                    Icons.Default.Error,
                    MaterialTheme.colorScheme.errorContainer,
                    MaterialTheme.colorScheme.onErrorContainer
                )

            ThermalUiStateCode.DEVICE_CONNECTING ->
                Triple(
                    Icons.Default.Refresh,
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.colorScheme.onSurfaceVariant
                )

            ThermalUiStateCode.DEVICE_READY ->
                Triple(
                    Icons.Default.PlayArrow,
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.colorScheme.onSurfaceVariant
                )

            ThermalUiStateCode.DEVICE_DISCONNECTED ->
                Triple(
                    Icons.Default.Close,
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.colorScheme.onSurfaceVariant
                )
        }
    var settingsExpanded by remember { mutableStateOf(false) }
    var measurementMode by remember {
        mutableStateOf(
            MeasurementMode.SPOT
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopdonCenterAlignedTopAppBar(
                title = "Thermal Preview",
                navigationIcon = {
                    TopdonBackButton(
                        onClick = onNavigateUp
                    )
                },
                actions = {
                    Box {
                        TopdonAppBarIconButton(
                            icon = Icons.Default.Settings,
                            onClick = {
                                settingsExpanded =
                                    !settingsExpanded
                            },
                            contentDescription = "Settings"
                        )
                        ThermalQuickSettingsMenu(
                            expanded = settingsExpanded,
                            state = state,
                            onDismiss = { settingsExpanded = false },
                            onSelectPalette = {
                                onSelectPalette(it)
                                settingsExpanded = false
                            },
                            onSelectSuperSampling = {
                                onSelectSuperSampling(it)
                                settingsExpanded = false
                            },
                            onUpdatePreviewFps = {
                                onUpdatePreviewFps(it)
                                settingsExpanded = false
                            }
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(
            0,
            0,
            0,
            0
        )
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        padding
                    )
            ) {
                ThermalPreviewArea(
                    state = state,
                    onConnect = onConnect,
                    onRefresh = onRefresh,
                    modifier = Modifier.weight(
                        1f
                    )
                )

                ThermalControlPanel(
                    state = state,
                    measurementMode = measurementMode,
                    onMeasurementModeSelected = {
                        measurementMode =
                            it
                    },
                    onStartPreview = onStartPreview,
                    onStopPreview = onStopPreview,
                    onCapture = onCapture,
                    onStartRecording = onStartRecording,
                    onStopRecording = onStopRecording
                )
            }

            AssistChip(
                onClick = {},
                enabled = false,
                label = {
                    Text(
                        state.previewStateSummary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = previewIcon,
                        contentDescription = null
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = chipContainer,
                    disabledContainerColor = chipContainer,
                    labelColor = chipLabelColour,
                    disabledLabelColor = chipLabelColour,
                    disabledLeadingIconColor = chipLabelColour
                ),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            )

            if (state.scanning) {
                TopdonLoadingOverlay(
                    message = state.previewStateSummary
                )
            }
        }
    }
}

@Composable
fun ThermalPreviewPane(
    state: TopdonUiState,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    onConnect: () -> Unit,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit,
    onCapture: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onSelectPalette: (TopdonPalette) -> Unit,
    onSelectSuperSampling: (TopdonSuperSamplingFactor) -> Unit,
    onUpdatePreviewFps: (Int) -> Unit
) {
    val (previewIcon, chipContainer, chipLabelColour) =
        when (state.previewStateCode) {
            ThermalUiStateCode.PREVIEW_STREAMING,
            ThermalUiStateCode.PREVIEW_RECORDING ->
                Triple(
                    Icons.Default.PlayArrow,
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.onPrimaryContainer
                )

            ThermalUiStateCode.PREVIEW_BUFFERING ->
                Triple(
                    Icons.Default.Refresh,
                    MaterialTheme.colorScheme.secondaryContainer,
                    MaterialTheme.colorScheme.onSecondaryContainer
                )

            ThermalUiStateCode.PREVIEW_ERROR ->
                Triple(
                    Icons.Default.Error,
                    MaterialTheme.colorScheme.errorContainer,
                    MaterialTheme.colorScheme.onErrorContainer
                )

            ThermalUiStateCode.DEVICE_CONNECTING ->
                Triple(
                    Icons.Default.Refresh,
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.colorScheme.onSurfaceVariant
                )

            ThermalUiStateCode.DEVICE_READY ->
                Triple(
                    Icons.Default.PlayArrow,
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.colorScheme.onSurfaceVariant
                )

            ThermalUiStateCode.DEVICE_DISCONNECTED ->
                Triple(
                    Icons.Default.Close,
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.colorScheme.onSurfaceVariant
                )
        }
    var settingsExpanded by remember { mutableStateOf(false) }
    var measurementMode by remember { mutableStateOf(MeasurementMode.SPOT) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ThermalPreviewArea(
                state = state,
                onConnect = onConnect,
                onRefresh = onRefresh,
                modifier = Modifier.weight(1f)
            )

            ThermalControlPanel(
                state = state,
                measurementMode = measurementMode,
                onMeasurementModeSelected = { measurementMode = it },
                onStartPreview = onStartPreview,
                onStopPreview = onStopPreview,
                onCapture = onCapture,
                onStartRecording = onStartRecording,
                onStopRecording = onStopRecording
            )
        }

        AssistChip(
            onClick = {},
            enabled = false,
            label = {
                Text(
                    state.previewStateSummary
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = previewIcon,
                    contentDescription = null
                )
            },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = chipContainer,
                disabledContainerColor = chipContainer,
                labelColor = chipLabelColour,
                disabledLabelColor = chipLabelColour,
                disabledLeadingIconColor = chipLabelColour
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            TopdonAppBarIconButton(
                icon = Icons.Default.Settings,
                onClick = { settingsExpanded = !settingsExpanded },
                contentDescription = "Thermal settings"
            )
            ThermalQuickSettingsMenu(
                expanded = settingsExpanded,
                state = state,
                onDismiss = { settingsExpanded = false },
                onSelectPalette = {
                    onSelectPalette(it)
                    settingsExpanded = false
                },
                onSelectSuperSampling = {
                    onSelectSuperSampling(it)
                    settingsExpanded = false
                },
                onUpdatePreviewFps = {
                    onUpdatePreviewFps(it)
                    settingsExpanded = false
                }
            )
        }

        if (state.scanning) {
            TopdonLoadingOverlay(
                message = state.previewStateSummary
            )
        }
    }
}

@Composable
private fun ThermalQuickSettingsMenu(
    expanded: Boolean,
    state: TopdonUiState,
    onDismiss: () -> Unit,
    onSelectPalette: (TopdonPalette) -> Unit,
    onSelectSuperSampling: (TopdonSuperSamplingFactor) -> Unit,
    onUpdatePreviewFps: (Int) -> Unit
) {
    var fpsSliderValue by remember(state.settings.previewFpsLimit) {
        mutableFloatStateOf(state.settings.previewFpsLimit.toFloat())
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier.width(280.dp)
    ) {
        Text(
            text = "Palette",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
        state.paletteOptions.forEach { palette ->
            DropdownMenuItem(
                text = { Text(palette.name.replace('_', ' ')) },
                trailingIcon = {
                    if (state.settings.palette == palette) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                },
                onClick = {
                    onSelectPalette(palette)
                }
            )
        }
        Divider()
        Text(
            text = "Super sampling",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
        state.superSamplingOptions.forEach { factor ->
            DropdownMenuItem(
                text = { Text("x${factor.multiplier}") },
                trailingIcon = {
                    if (state.settings.superSampling == factor) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                },
                onClick = {
                    onSelectSuperSampling(factor)
                }
            )
        }
        Divider()
        Text(
            text = "Preview FPS • ${fpsSliderValue.roundToInt()}",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
        Slider(
            value = fpsSliderValue,
            onValueChange = { fpsSliderValue = it },
            valueRange = 4f..30f,
            steps = 26,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        DropdownMenuItem(
            text = { Text("Apply FPS limit") },
            onClick = {
                onUpdatePreviewFps(fpsSliderValue.roundToInt().coerceIn(4, 30))
            }
        )
    }
}

@Composable
private fun ThermalPreviewArea(
    state: TopdonUiState,
    onConnect: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {
        when {
            !state.isConnected -> {
                TopdonEmptyState(
                    message = "Device not connected",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(
                                16.dp
                            )
                        )
                    }
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        TopdonSpacing.Medium
                    )
                ) {
                    TopdonButton(
                        onClick = onConnect
                    ) {
                        Text(
                            "Connect"
                        )
                    }
                    TopdonOutlinedButton(
                        onClick = onRefresh
                    ) {
                        Text(
                            "Refresh"
                        )
                    }
                }
            }

            !state.previewActive -> {
                TopdonEmptyState(
                    message = "Preview idle"
                )
            }

            else -> {
                val previewFrame =
                    state.previewFrame
                if (previewFrame != null) {
                    ThermalFrameDisplay(
                        frame = previewFrame,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    TopdonLinearProgress(
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Awaiting frame...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(
                            TopdonSpacing.Medium
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ThermalControlPanel(
    state: TopdonUiState,
    measurementMode: MeasurementMode,
    onMeasurementModeSelected: (MeasurementMode) -> Unit,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit,
    onCapture: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopdonElevatedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    TopdonSpacing.Large
                ),
            verticalArrangement = Arrangement.spacedBy(
                TopdonSpacing.Medium
            )
        ) {
            val canStartPreview =
                state.previewStateCode in setOf(
                    ThermalUiStateCode.DEVICE_READY,
                    ThermalUiStateCode.DEVICE_DISCONNECTED,
                    ThermalUiStateCode.PREVIEW_ERROR
                )
            val canStopPreview =
                state.previewStateCode in setOf(
                    ThermalUiStateCode.PREVIEW_STREAMING,
                    ThermalUiStateCode.PREVIEW_RECORDING,
                    ThermalUiStateCode.PREVIEW_BUFFERING
                )
            TopdonMeasurementModeSelector(
                selectedMode = measurementMode,
                onModeSelected = onMeasurementModeSelected
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    TopdonSpacing.Small
                )
            ) {
                TopdonButton(
                    onClick = if (state.previewActive) onStopPreview else onStartPreview,
                    modifier = Modifier.weight(
                        1f
                    ),
                    enabled = if (state.previewActive) canStopPreview else canStartPreview
                ) {
                    Text(
                        if (state.previewActive) "Stop" else "Start"
                    )
                }

                TopdonIconButton(
                    onClick = onCapture,
                    icon = Icons.Default.Camera,
                    contentDescription = "Capture photo",
                    enabled = state.previewIsStreaming
                )

                TopdonIconButton(
                    onClick = if (state.isRecording) onStopRecording else onStartRecording,
                    icon = Icons.Default.Videocam,
                    contentDescription = "Record video",
                    tint = if (state.isRecording) TopdonColors.SelectRed else MaterialTheme.colorScheme.onSurface,
                    enabled = state.previewIsStreaming
                )
            }
        }
    }
}
