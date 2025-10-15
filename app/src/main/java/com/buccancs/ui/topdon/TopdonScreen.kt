package com.buccancs.ui.topdon

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
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
import com.buccancs.ui.components.topdon.TopdonButton
import com.buccancs.ui.components.topdon.TopdonOutlinedButton
import com.buccancs.ui.components.topdon.TopdonTextButton
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import kotlin.time.Instant
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopdonRoute(
    deviceId: DeviceId,
    onNavigateUp: () -> Unit,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopdonScreen(
    state: TopdonUiState,
    onNavigateUp: () -> Unit,
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
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(toolbarState)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TopdonStatusCard(state, onRefresh, onConnect, onDisconnect, onClearError)
            }
            item {
                TopdonPreviewCard(state, onStartPreview, onStopPreview, onTogglePreview)
            }
            item {
                TopdonSettingsCard(state, onSetAutoConnect, onSelectPalette, onSelectSuperSampling, onUpdatePreviewFps)
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
    onClearError: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (state.isConnected) Icons.Default.Check else Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if (state.isConnected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = state.connectionStatusLabel,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (state.isConnected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                if (state.scanning) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
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
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
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
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = error,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.weight(1f)
                        )
                        FilledTonalIconButton(
                            onClick = onClearError,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Dismiss error",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TopdonButton(
                    onClick = onConnect,
                    enabled = !state.isConnected,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
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
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Disconnect")
                }
                IconButton(
                    onClick = onRefresh,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
            }
        }
    }
}

@Composable
private fun TopdonPreviewCard(
    state: TopdonUiState,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit,
    onTogglePreview: () -> Unit
) {
    val previewFrame = state.previewFrame
    val imageBitmap = remember(previewFrame?.payload) {
        previewFrame?.payload?.let { bytes ->
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)?.asImageBitmap()
        }
    }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
                            modifier = Modifier.size(18.dp)
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
                        .height(240.dp)
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
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = if (state.previewActive) Icons.Default.PlayArrow else Icons.Default.Stop,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
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
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TopdonButton(
                    onClick = onStartPreview,
                    enabled = state.isConnected && !state.previewActive,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
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
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Stop")
                }
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
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                        .padding(12.dp),
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

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
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
