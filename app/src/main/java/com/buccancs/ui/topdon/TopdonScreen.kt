package com.buccancs.ui.topdon

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
                    TextButton(onNavigateUp) { Text("Back") }
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
            Text(state.deviceLabel, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(state.connectionStatusLabel, style = MaterialTheme.typography.bodyMedium)
            if (state.scanning) {
                Text(
                    text = "Scanning USB...",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onConnect, enabled = !state.isConnected) { Text("Connect") }
                OutlinedButton(onClick = onDisconnect, enabled = state.isConnected) { Text("Disconnect") }
                TextButton(onClick = onRefresh) { Text("Refresh") }
            }
            state.errorMessage?.let { error ->
                AssistChip(
                    onClick = onClearError,
                    label = { Text(error) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                )
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
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Infrared Preview", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
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
                    Text(
                        text = if (state.previewActive) "Awaiting frame..." else "Preview idle",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            previewFrame?.let { frame ->
                Text(
                    text = "Resolution: ${frame.width} x ${frame.height} (x${frame.superSamplingFactor})",
                    style = MaterialTheme.typography.bodySmall
                )
                TopdonTimestamp(frame.timestamp)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onStartPreview, enabled = state.isConnected && !state.previewActive) { Text("Start Preview") }
                OutlinedButton(onStopPreview, enabled = state.previewActive) { Text("Stop Preview") }
                AssistChip(
                    onClick = onTogglePreview,
                    label = { Text(if (state.previewActive) "Streaming" else "Idle") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (state.previewActive) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                    )
                )
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
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Settings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Auto-connect on USB", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = if (state.autoConnectEnabled) "Device starts preview automatically" else "Manual start required",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(checked = state.autoConnectEnabled, onCheckedChange = onSetAutoConnect)
            }
            PaletteDropdown(
                label = "Palette",
                options = state.paletteOptions,
                selected = state.settings.palette,
                onSelect = onSelectPalette
            )
            SuperSamplingDropdown(
                label = "Super sampling",
                options = state.superSamplingOptions,
                selected = state.settings.superSampling,
                onSelect = onSelectSuperSampling
            )
            PreviewFpsSlider(current = state.settings.previewFpsLimit, onUpdate = onUpdatePreviewFps)
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
    label: String,
    options: List<TopdonPalette>,
    selected: TopdonPalette,
    onSelect: (TopdonPalette) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Text(text = label, style = MaterialTheme.typography.bodySmall)
    OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
        Text(selected.name.lowercase().replaceFirstChar { it.titlecase() })
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
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
    label: String,
    options: List<TopdonSuperSamplingFactor>,
    selected: TopdonSuperSamplingFactor,
    onSelect: (TopdonSuperSamplingFactor) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Text(text = label, style = MaterialTheme.typography.bodySmall)
    OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
        Text("x${selected.multiplier}")
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
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
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Preview frame rate limit", style = MaterialTheme.typography.bodyMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${sliderValue.roundToInt()} fps", style = MaterialTheme.typography.bodySmall)
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 2f..30f,
                steps = 28,
                onValueChangeFinished = {
                    onUpdate(sliderValue.roundToInt())
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
