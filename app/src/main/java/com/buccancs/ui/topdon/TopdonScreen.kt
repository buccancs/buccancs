package com.buccancs.ui.topdon

import android.graphics.BitmapFactory
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.components.topdon.TopdonButton
import com.buccancs.ui.components.topdon.TopdonOutlinedButton
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing
import kotlin.math.roundToInt
import kotlin.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopdonRoute(
    deviceId: DeviceId,
    onNavigateUp: () -> Unit,
    onNavigateToThermalPreview: () -> Unit = {},
    onNavigateToGallery: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToGuide: () -> Unit = {},
    viewModel: TopdonViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(deviceId) {
        viewModel.setActiveDevice(deviceId)
        viewModel.refresh()
    }
    TopdonScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onNavigateToThermalPreview = onNavigateToThermalPreview,
        onNavigateToGallery = onNavigateToGallery,
        onNavigateToSettings = onNavigateToSettings,
        onNavigateToGuide = onNavigateToGuide,
        onRefresh = viewModel::refresh,
        onConnect = viewModel::connect,
        onDisconnect = viewModel::disconnect,
        onStartPreview = viewModel::startPreview,
        onStopPreview = viewModel::stopPreview,
        onTogglePreview = viewModel::togglePreview,
        onSetAutoConnect = viewModel::setAutoConnect,
        onSelectPalette = viewModel::selectPalette,
        onSelectSuperSampling = viewModel::selectSuperSampling,
        onUpdatePreviewFps = viewModel::updatePreviewFps,
        onClearError = viewModel::clearError
    )
}

@VisibleForTesting
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopdonScreen(
    state: TopdonUiState,
    onNavigateUp: () -> Unit,
    onNavigateToThermalPreview: () -> Unit,
    onNavigateToGallery: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToGuide: () -> Unit,
    onRefresh: () -> Unit,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit,
    onTogglePreview: () -> Unit,
    onSetAutoConnect: (Boolean) -> Unit,
    onSelectPalette: (TopdonPalette) -> Unit,
    onSelectSuperSampling: (TopdonSuperSamplingFactor) -> Unit,
    onUpdatePreviewFps: (Int) -> Unit,
    onClearError: () -> Unit
) {
    val toolbarState = rememberTopAppBarState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Topdon TC001",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(toolbarState)
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(LayoutPadding.Screen),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            item {
                TopdonStatusCard(
                    state,
                    onRefresh,
                    onConnect,
                    onDisconnect,
                    onClearError,
                    onNavigateToGuide
                )
            }
            item {
                TopdonPreviewCard(
                    state,
                    onStartPreview,
                    onStopPreview,
                    onTogglePreview,
                    onNavigateToThermalPreview
                )
            }
            item {
                TopdonQuickActionsCard(onNavigateToGallery, onNavigateToSettings)
            }
            item {
                TopdonSettingsCard(
                    state,
                    onSetAutoConnect,
                    onSelectPalette,
                    onSelectSuperSampling,
                    onUpdatePreviewFps
                )
            }
        }
    }
}

@Composable
private fun TopdonStatusCard(
    state: TopdonUiState,
    onRefresh: () -> Unit,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onClearError: () -> Unit,
    onNavigateToGuide: () -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.SmallMedium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = state.deviceLabel,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)
                ) {
                    Icon(
                        imageVector = if (state.isConnected) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(Dimensions.IconSizeSmall),
                        tint = if (state.isConnected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                    Text(
                        text = state.connectionStatusLabel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (state.isConnected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
            if (state.scanning) {
                CircularProgressIndicator(modifier = Modifier.size(Dimensions.IconSizeDefault))
            }
        }

        if (state.scanning) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Scanning USB...",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        horizontal = Spacing.SmallMedium,
                        vertical = Spacing.Small
                    )
                )
            }
        }

        state.errorMessage?.let { error ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.errorContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.SmallMedium),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.size(Dimensions.IconSizeDefault)
                    )
                    Text(
                        text = error,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.weight(1f)
                    )
                    FilledTonalIconButton(
                        onClick = onClearError,
                        modifier = Modifier.size(Dimensions.TouchTargetMinimum)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss error",
                            modifier = Modifier.size(Dimensions.IconSizeSmall)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            TopdonButton(
                onClick = onConnect,
                enabled = !state.isConnected,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
                Text("Connect")
            }
            TopdonOutlinedButton(
                onClick = onDisconnect,
                enabled = state.isConnected,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
                Text("Disconnect")
            }
            IconButton(
                onClick = onRefresh,
                modifier = Modifier.size(Dimensions.TouchTargetMinimum)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh"
                )
            }
        }

        if (!state.isConnected) {
            TopdonOutlinedButton(
                onClick = onNavigateToGuide,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.SmallMedium))
                Text("Connection Guide")
            }
        }
    }
}

@Composable
private fun TopdonPreviewCard(
    state: TopdonUiState,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit,
    onTogglePreview: () -> Unit,
    onNavigateToThermalPreview: () -> Unit
) {
    val previewFrame = state.previewFrame
    val imageBitmap = remember(previewFrame?.payload) {
        previewFrame?.payload?.let { bytes ->
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)?.asImageBitmap()
        }
    }
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
                text = "Thermal Preview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            AssistChip(
                onClick = onTogglePreview,
                label = { Text(if (state.previewActive) "Streaming" else "Idle") },
                leadingIcon = {
                    Icon(
                        imageVector = if (state.previewActive) Icons.Default.PlayArrow else Icons.Default.Stop,
                        contentDescription = null,
                        modifier = Modifier.size(Dimensions.IconSizeSmall)
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (state.previewActive) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                    labelColor = if (state.previewActive) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

        OutlinedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.Size160)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                if (imageBitmap != null) {
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = "Thermal preview",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Spacing.Small)
                    ) {
                        Icon(
                            imageVector = if (state.previewActive) Icons.Default.PlayArrow else Icons.Default.Stop,
                            contentDescription = null,
                            modifier = Modifier.size(Dimensions.IconSizeLarge),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = if (state.previewActive) "Awaiting frame..." else "Preview idle",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        previewFrame?.let { frame ->
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
                Text(
                    text = "Resolution: ${frame.width} x ${frame.height} (x${frame.superSamplingFactor})",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                TopdonTimestamp(frame.timestamp)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            TopdonButton(
                onClick = onStartPreview,
                enabled = state.isConnected && !state.previewActive,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
                Text("Start")
            }
            TopdonOutlinedButton(
                onClick = onStopPreview,
                enabled = state.previewActive,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Stop,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
                Text("Stop")
            }

            FilledTonalIconButton(
                onClick = onNavigateToThermalPreview,
                enabled = state.isConnected,
                modifier = Modifier.size(Dimensions.TouchTargetMinimum)
            ) {
                Icon(
                    imageVector = Icons.Default.OpenInFull,
                    contentDescription = "Full screen preview"
                )
            }
        }
    }
}

@Composable
private fun TopdonQuickActionsCard(
    onNavigateToGallery: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            TopdonOutlinedButton(
                onClick = onNavigateToGallery,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.SmallMedium))
                Text("Gallery")
            }

            TopdonOutlinedButton(
                onClick = onNavigateToSettings,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.SmallMedium))
                Text("Settings")
            }
        }
    }
}

@Composable
private fun TopdonSettingsCard(
    state: TopdonUiState,
    onSetAutoConnect: (Boolean) -> Unit,
    onSelectPalette: (TopdonPalette) -> Unit,
    onSelectSuperSampling: (TopdonSuperSamplingFactor) -> Unit,
    onUpdatePreviewFps: (Int) -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

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
                        vertical = Spacing.Small
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Auto-connect on USB",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = if (state.autoConnectEnabled) "Starts preview automatically" else "Manual start required",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = state.autoConnectEnabled,
                    onCheckedChange = onSetAutoConnect
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
            Text(
                text = "Colour Palette",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            PaletteDropdown(
                options = state.paletteOptions,
                selected = state.settings.palette,
                onSelect = onSelectPalette
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
            Text(
                text = "Super Sampling",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            SuperSamplingDropdown(
                options = state.superSamplingOptions,
                selected = state.settings.superSampling,
                onSelect = onSelectSuperSampling
            )
        }

        PreviewFpsSlider(
            current = state.settings.previewFpsLimit,
            onUpdate = onUpdatePreviewFps
        )
    }
}

@Composable
private fun TopdonTimestamp(timestamp: Instant) {
    Text(
        text = "Last frame: $timestamp",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun PaletteDropdown(
    options: List<TopdonPalette>,
    selected: TopdonPalette,
    onSelect: (TopdonPalette) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    TopdonButton(
        onClick = { expanded = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(selected.name.lowercase().replaceFirstChar { it.titlecase() })
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = { Text(option.name.lowercase().replaceFirstChar { it.titlecase() }) },
                onClick = {
                    expanded = false
                    onSelect(option)
                }
            )
        }
    }
}

@Composable
private fun SuperSamplingDropdown(
    options: List<TopdonSuperSamplingFactor>,
    selected: TopdonSuperSamplingFactor,
    onSelect: (TopdonSuperSamplingFactor) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    TopdonButton(
        onClick = { expanded = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("x${selected.multiplier}")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = { Text("x${option.multiplier}") },
                onClick = {
                    expanded = false
                    onSelect(option)
                }
            )
        }
    }
}

@Composable
private fun PreviewFpsSlider(
    current: Int,
    onUpdate: (Int) -> Unit
) {
    var sliderValue by remember { mutableFloatStateOf(current.toFloat()) }
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Preview Frame Rate",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "${sliderValue.roundToInt()} fps",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        horizontal = Spacing.SmallMedium,
                        vertical = Spacing.ExtraSmall
                    )
                )
            }
        }
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 2f..30f,
            steps = 28,
            onValueChangeFinished = {
                onUpdate(sliderValue.roundToInt())
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
