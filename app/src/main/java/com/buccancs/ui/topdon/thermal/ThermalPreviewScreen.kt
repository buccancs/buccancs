package com.buccancs.ui.topdon.thermal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.ui.components.topdon.MeasurementMode
import com.buccancs.ui.components.topdon.ThermalPalette
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
import com.buccancs.ui.components.topdon.TopdonPaletteSelector
import com.buccancs.ui.components.topdon.TopdonSlider
import com.buccancs.ui.components.topdon.TopdonTemperatureRange
import com.buccancs.ui.theme.topdon.TopdonColors
import com.buccancs.ui.theme.topdon.TopdonSpacing
import com.buccancs.ui.theme.topdon.TopdonTheme
import com.buccancs.ui.topdon.TopdonUiState
import com.buccancs.ui.topdon.TopdonViewModel

/**
 * Full-screen thermal preview with camera controls
 * Migrated from IRThermalNightActivity
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThermalPreviewRoute(
    deviceId: DeviceId,
    onNavigateUp: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: TopdonViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(deviceId) {
        viewModel.setActiveDevice(deviceId)
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

@OptIn(ExperimentalMaterial3Api::class)
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
    var showSettings by remember { mutableStateOf(false) }
    var measurementMode by remember { mutableStateOf(MeasurementMode.SPOT) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopdonCenterAlignedTopAppBar(
                title = "Thermal Preview",
                navigationIcon = { TopdonBackButton(onClick = onNavigateUp) },
                actions = {
                    TopdonAppBarIconButton(
                        icon = Icons.Default.Settings,
                        onClick = { showSettings = !showSettings },
                        contentDescription = "Settings"
                    )
                }
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
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

            if (showSettings) {
                ThermalSettingsPanel(
                    state = state,
                    onDismiss = { showSettings = false },
                    onSelectPalette = onSelectPalette,
                    onSelectSuperSampling = onSelectSuperSampling,
                    onUpdatePreviewFps = onUpdatePreviewFps
                )
            }

            if (state.scanning) {
                TopdonLoadingOverlay(message = "Connecting to device...")
            }
        }
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
            .background(MaterialTheme.colorScheme.background),
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
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(TopdonSpacing.Medium)
                ) {
                    TopdonButton(onClick = onConnect) {
                        Text("Connect")
                    }
                    TopdonOutlinedButton(onClick = onRefresh) {
                        Text("Refresh")
                    }
                }
            }

            !state.previewActive -> {
                TopdonEmptyState(message = "Preview idle")
            }

            else -> {
                val previewFrame = state.previewFrame
                if (previewFrame != null) {
                    ThermalFrameDisplay(
                        frame = previewFrame,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    TopdonLinearProgress(modifier = Modifier.fillMaxWidth())
                    Text(
                        text = "Awaiting frame...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(TopdonSpacing.Medium)
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
                .padding(TopdonSpacing.Large),
            verticalArrangement = Arrangement.spacedBy(TopdonSpacing.Medium)
        ) {
            TopdonMeasurementModeSelector(
                selectedMode = measurementMode,
                onModeSelected = onMeasurementModeSelected
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TopdonSpacing.Small)
            ) {
                TopdonButton(
                    onClick = if (state.previewActive) onStopPreview else onStartPreview,
                    modifier = Modifier.weight(1f),
                    enabled = state.isConnected
                ) {
                    Text(if (state.previewActive) "Stop" else "Start")
                }

                TopdonIconButton(
                    onClick = onCapture,
                    icon = Icons.Default.Camera,
                    contentDescription = "Capture photo",
                    enabled = state.previewActive
                )

                TopdonIconButton(
                    onClick = if (state.isRecording) onStopRecording else onStartRecording,
                    icon = Icons.Default.Videocam,
                    contentDescription = "Record video",
                    tint = if (state.isRecording) TopdonColors.SelectRed else MaterialTheme.colorScheme.onSurface,
                    enabled = state.previewActive
                )
            }
        }
    }
}

@Composable
private fun ThermalSettingsPanel(
    state: TopdonUiState,
    onDismiss: () -> Unit,
    onSelectPalette: (TopdonPalette) -> Unit,
    onSelectSuperSampling: (TopdonSuperSamplingFactor) -> Unit,
    onUpdatePreviewFps: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.95f))
            .padding(TopdonSpacing.Large)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(TopdonSpacing.Large)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Thermal Settings",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    TopdonIconButton(
                        onClick = onDismiss,
                        icon = Icons.Default.Close,
                        contentDescription = "Close settings"
                    )
                }
            }

            item {
                val thermalPalettes = remember {
                    listOf(
                        ThermalPalette(
                            "Iron",
                            listOf(
                                androidx.compose.ui.graphics.Color(0xFF000033),
                                androidx.compose.ui.graphics.Color(0xFFFF0000),
                                androidx.compose.ui.graphics.Color(0xFFFFFF00),
                                androidx.compose.ui.graphics.Color(0xFFFFFFFF)
                            )
                        ),
                        ThermalPalette(
                            "Rainbow",
                            listOf(
                                androidx.compose.ui.graphics.Color(0xFF0000FF),
                                androidx.compose.ui.graphics.Color(0xFF00FF00),
                                androidx.compose.ui.graphics.Color(0xFFFFFF00),
                                androidx.compose.ui.graphics.Color(0xFFFF0000)
                            )
                        ),
                        ThermalPalette(
                            "Gray",
                            listOf(
                                androidx.compose.ui.graphics.Color(0xFF000000),
                                androidx.compose.ui.graphics.Color(0xFF808080),
                                androidx.compose.ui.graphics.Color(0xFFFFFFFF)
                            )
                        )
                    )
                }

                val selectedThermalPalette = remember(state.settings.palette) {
                    thermalPalettes[0]
                }

                TopdonPaletteSelector(
                    palettes = thermalPalettes,
                    selectedPalette = selectedThermalPalette,
                    onPaletteSelected = { onSelectPalette(TopdonPalette.IRONBOW) }
                )
            }

            item {
                var fpsValue by remember { mutableFloatStateOf(state.settings.previewFpsLimit.toFloat()) }
                TopdonSlider(
                    value = fpsValue,
                    onValueChange = { fpsValue = it },
                    valueRange = 2f..30f,
                    label = "Frame Rate Limit",
                    steps = 28,
                    onValueChangeFinished = { onUpdatePreviewFps(fpsValue.toInt()) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ThermalPreviewScreenPreview() {
    TopdonTheme {
        ThermalPreviewScreen(
            state = TopdonUiState(
                deviceLabel = "TC001",
                connectionStatusLabel = "Connected",
                isConnected = true,
                previewActive = true,
                previewFrame = null,
                lastPreviewTimestamp = null,
                scanning = false,
                errorMessage = null,
                settings = TopdonSettings(),
                streamStatuses = emptyList(),
                paletteOptions = TopdonPalette.values().toList(),
                superSamplingOptions = TopdonSuperSamplingFactor.values().toList(),
                isRecording = false
            ),
            onNavigateUp = {},
            onNavigateToSettings = {},
            onRefresh = {},
            onConnect = {},
            onStartPreview = {},
            onStopPreview = {},
            onCapture = {},
            onStartRecording = {},
            onStopRecording = {},
            onSelectPalette = {},
            onSelectSuperSampling = {},
            onUpdatePreviewFps = {}
        )
    }
}

@Composable
private fun ThermalFrameDisplay(
    frame: TopdonPreviewFrame,
    modifier: Modifier = Modifier
) {
    val bitmap = remember(frame.timestamp) {
        createThermalBitmap(frame)
    }

    Column(modifier = modifier) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = "Thermal preview",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Fit
            )

            TopdonTemperatureRange(
                minTemp = frame.minTemp ?: 0f,
                maxTemp = frame.maxTemp ?: 0f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(TopdonSpacing.Medium)
            )
        } else {
            TopdonEmptyState(message = "Processing thermal data...")
        }
    }
}

private fun createThermalBitmap(frame: TopdonPreviewFrame): androidx.compose.ui.graphics.ImageBitmap? {
    return try {
        val width = frame.width
        val height = frame.height
        val data = frame.payload

        if (data.size < width * height * 2) {
            return null
        }

        val bitmap = android.graphics.Bitmap.createBitmap(
            width,
            height,
            android.graphics.Bitmap.Config.ARGB_8888
        )
        val pixels = IntArray(width * height)

        var minTemp = Double.POSITIVE_INFINITY
        var maxTemp = Double.NEGATIVE_INFINITY
        val temperatures = DoubleArray(width * height)

        for (i in 0 until (width * height)) {
            val offset = i * 2
            if (offset + 1 < data.size) {
                val raw =
                    (data[offset].toInt() and 0xFF) or ((data[offset + 1].toInt() and 0xFF) shl 8)
                val temp = raw / 100.0 - 273.15
                temperatures[i] = temp
                minTemp = kotlin.math.min(minTemp, temp)
                maxTemp = kotlin.math.max(maxTemp, temp)
            }
        }

        val range = (maxTemp - minTemp).takeIf { it > 0.0001 } ?: 1.0

        for (i in pixels.indices) {
            val normalized = ((temperatures[i] - minTemp) / range).coerceIn(0.0, 1.0)
            pixels[i] = applyThermalPalette(normalized)
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        bitmap.asImageBitmap()
    } catch (e: Exception) {
        null
    }
}

private fun applyThermalPalette(normalized: Double): Int {
    val r = (normalized * 255).toInt().coerceIn(0, 255)
    val g = ((normalized - 0.5) * 510).toInt().coerceIn(0, 255)
    val b = ((1.0 - normalized) * 255).toInt().coerceIn(0, 255)
    return android.graphics.Color.rgb(r, g, b)
}
