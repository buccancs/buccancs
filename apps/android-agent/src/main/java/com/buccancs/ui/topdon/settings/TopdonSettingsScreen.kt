package com.buccancs.ui.topdon.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Slider
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.TopdonDynamicRange
import com.buccancs.domain.model.TopdonGainMode
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.model.TopdonTemperatureUnit
import com.buccancs.ui.common.HorizontalDivider
import com.buccancs.ui.components.topdon.FpsLimitDialog
import com.buccancs.ui.components.topdon.PaletteSelectionDialog
import com.buccancs.ui.components.topdon.SuperSamplingDialog
import com.buccancs.ui.theme.Spacing
import com.buccancs.ui.topdon.TopdonViewModel
import java.util.Locale

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun TopdonSettingsRoute(
    onNavigateUp: () -> Unit,
    viewModel: TopdonViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    var showPaletteDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showSuperSamplingDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showFpsDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showEmissivityDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showDistanceDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showGainModeDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showDynamicRangeDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showTemperatureUnitDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showAmbientTemperatureDialog by remember {
        mutableStateOf(
            false
        )
    }
    var showAmbientHumidityDialog by remember {
        mutableStateOf(
            false
        )
    }

    TopdonSettingsScreen(
        onNavigateUp = onNavigateUp,
        palette = state.settings.palette,
        superSampling = state.settings.superSampling,
        autoConnect = state.settings.autoConnectOnAttach,
        previewFps = state.settings.previewFpsLimit,
        emissivity = state.settings.emissivity,
        distanceMeters = state.settings.distanceMeters,
        gainMode = state.settings.gainMode,
        dynamicRange = state.settings.dynamicRange,
        autoShutter = state.settings.autoShutterEnabled,
        temperatureUnit = state.settings.temperatureUnit,
        ambientTemperatureCelsius = state.settings.ambientTemperatureCelsius,
        ambientHumidityPercent = state.settings.ambientHumidityPercent,
        onPaletteClick = {
            showPaletteDialog =
                true
        },
        onSuperSamplingClick = {
            showSuperSamplingDialog =
                true
        },
        onFpsClick = {
            showFpsDialog =
                true
        },
        onAutoConnectChange = viewModel::setAutoConnect,
        onEmissivityClick = {
            showEmissivityDialog =
                true
        },
        onDistanceClick = {
            showDistanceDialog =
                true
        },
        onGainModeClick = {
            showGainModeDialog =
                true
        },
        onDynamicRangeClick = {
            showDynamicRangeDialog =
                true
        },
        onTemperatureUnitClick = {
            showTemperatureUnitDialog =
                true
        },
        onAmbientTemperatureClick = {
            showAmbientTemperatureDialog =
                true
        },
        onAmbientHumidityClick = {
            showAmbientHumidityDialog =
                true
        },
        onAutoShutterChange = viewModel::setAutoShutter
    )

    if (showPaletteDialog) {
        PaletteSelectionDialog(
            currentPalette = state.settings.palette,
            onDismiss = {
                showPaletteDialog =
                    false
            },
            onConfirm = viewModel::selectPalette
        )
    }

    if (showSuperSamplingDialog) {
        SuperSamplingDialog(
            currentFactor = state.settings.superSampling,
            onDismiss = {
                showSuperSamplingDialog =
                    false
            },
            onConfirm = viewModel::selectSuperSampling
        )
    }

    if (showFpsDialog) {
        FpsLimitDialog(
            currentFps = state.settings.previewFpsLimit,
            onDismiss = {
                showFpsDialog =
                    false
            },
            onConfirm = viewModel::updatePreviewFps
        )
    }

    if (showEmissivityDialog) {
        EmissivityDialog(
            currentValue = state.settings.emissivity,
            onDismiss = {
                showEmissivityDialog =
                    false
            },
            onConfirm = {
                viewModel.setEmissivity(it)
                showEmissivityDialog = false
            }
        )
    }

    if (showDistanceDialog) {
        DistanceDialog(
            currentValue = state.settings.distanceMeters,
            onDismiss = {
                showDistanceDialog =
                    false
            },
            onConfirm = {
                viewModel.setDistanceMeters(it)
                showDistanceDialog = false
            }
        )
    }

    if (showGainModeDialog) {
        GainModeDialog(
            current = state.settings.gainMode,
            options = state.gainModeOptions,
            onDismiss = {
                showGainModeDialog =
                    false
            },
            onSelect = {
                viewModel.setGainMode(it)
                showGainModeDialog = false
            }
        )
    }

    if (showDynamicRangeDialog) {
        DynamicRangeDialog(
            current = state.settings.dynamicRange,
            onDismiss = {
                showDynamicRangeDialog =
                    false
            },
            onSelect = {
                viewModel.setDynamicRange(it)
                showDynamicRangeDialog = false
            }
        )
    }

    if (showTemperatureUnitDialog) {
        TemperatureUnitDialog(
            current = state.settings.temperatureUnit,
            options = state.temperatureUnitOptions,
            onDismiss = {
                showTemperatureUnitDialog =
                    false
            },
            onSelect = {
                viewModel.setTemperatureUnit(it)
                showTemperatureUnitDialog = false
            }
        )
    }

    if (showAmbientTemperatureDialog) {
        AmbientTemperatureDialog(
            currentValue = state.settings.ambientTemperatureCelsius,
            useFahrenheit = state.settings.temperatureUnit == TopdonTemperatureUnit.FAHRENHEIT,
            onDismiss = {
                showAmbientTemperatureDialog =
                    false
            },
            onConfirm = {
                viewModel.setAmbientTemperatureCelsius(it)
                showAmbientTemperatureDialog = false
            }
        )
    }

    if (showAmbientHumidityDialog) {
        AmbientHumidityDialog(
            currentValue = state.settings.ambientHumidityPercent,
            onDismiss = {
                showAmbientHumidityDialog =
                    false
            },
            onConfirm = {
                viewModel.setAmbientHumidityPercent(it)
                showAmbientHumidityDialog = false
            }
        )
    }
}

@Composable
private fun EmissivityDialog(
    currentValue: Double,
    onDismiss: () -> Unit,
    onConfirm: (Double) -> Unit
) {
    var sliderValue by remember(currentValue) {
        mutableFloatStateOf(
            currentValue.toFloat()
                .coerceIn(0.1f, 1f)
        )
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Emissivity") },
        text = {
            Column {
                Text(
                    text = "Adjust emissivity (0.10 – 1.00)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Text(
                    text = String.format(
                        Locale.US,
                        "Current: %.2f",
                        sliderValue
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0.1f..1.0f,
                    steps = 90
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(sliderValue.toDouble())
                }
            ) {
                Text("Apply")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun DistanceDialog(
    currentValue: Double,
    onDismiss: () -> Unit,
    onConfirm: (Double) -> Unit
) {
    var sliderValue by remember(currentValue) {
        mutableFloatStateOf(
            currentValue.toFloat()
                .coerceIn(0.1f, 10f)
        )
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Distance Correction") },
        text = {
            Column {
                Text(
                    text = "Set object distance (metres)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Text(
                    text = String.format(
                        Locale.US,
                        "Current: %.2f m",
                        sliderValue
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0.1f..10f,
                    steps = 98
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(sliderValue.toDouble())
                }
            ) {
                Text("Apply")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun GainModeDialog(
    current: TopdonGainMode,
    options: List<TopdonGainMode>,
    onDismiss: () -> Unit,
    onSelect: (TopdonGainMode) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Gain Mode") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                options.forEach { mode ->
                    TextButton(
                        onClick = {
                            onSelect(mode)
                        }
                    ) {
                        Text(
                            text = mode.name.lowercase()
                                .replaceFirstChar { it.uppercase() },
                            color = if (mode == current) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
private fun DynamicRangeDialog(
    current: TopdonDynamicRange,
    onDismiss: () -> Unit,
    onSelect: (TopdonDynamicRange) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Dynamic Range") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                TopdonDynamicRange.values()
                    .forEach { range ->
                        TextButton(
                            onClick = {
                                onSelect(range)
                            }
                        ) {
                            Text(
                                text = when (range) {
                                    TopdonDynamicRange.STANDARD -> "Standard"
                                    TopdonDynamicRange.WIDE -> "Wide (HDR)"
                                },
                                color = if (range == current) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
private fun TemperatureUnitDialog(
    current: TopdonTemperatureUnit,
    options: List<TopdonTemperatureUnit>,
    onDismiss: () -> Unit,
    onSelect: (TopdonTemperatureUnit) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Temperature Unit") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.Small)
            ) {
                options.forEach { unit ->
                    TextButton(
                        onClick = {
                            onSelect(unit)
                        }
                    ) {
                        Text(
                            text = when (unit) {
                                TopdonTemperatureUnit.CELSIUS -> "Celsius (°C)"
                                TopdonTemperatureUnit.FAHRENHEIT -> "Fahrenheit (°F)"
                            },
                            color = if (unit == current) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
private fun AmbientTemperatureDialog(
    currentValue: Double,
    useFahrenheit: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Double) -> Unit
) {
    val displayValue =
        if (useFahrenheit) currentValue * 9.0 / 5.0 + 32.0 else currentValue
    var sliderValue by remember(displayValue) {
        mutableFloatStateOf(displayValue.toFloat())
    }
    val range =
        if (useFahrenheit) -40f..212f else -40f..100f
    val unitLabel = if (useFahrenheit) "°F" else "°C"
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ambient Temperature") },
        text = {
            Column {
                Text(
                    text = "Set ambient temperature",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Text(
                    text = String.format(
                        Locale.US,
                        "%.1f %s",
                        sliderValue,
                        unitLabel
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = range,
                    steps = 0
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val celsius =
                        if (useFahrenheit) {
                            (sliderValue - 32f) * 5f / 9f
                        } else {
                            sliderValue
                        }
                    onConfirm(celsius.toDouble())
                }
            ) {
                Text("Apply")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun AmbientHumidityDialog(
    currentValue: Double,
    onDismiss: () -> Unit,
    onConfirm: (Double) -> Unit
) {
    var sliderValue by remember(currentValue) {
        mutableFloatStateOf(currentValue.toFloat().coerceIn(0f, 100f))
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ambient Humidity") },
        text = {
            Column {
                Text(
                    text = "Set relative humidity",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Text(
                    text = String.format(
                        Locale.US,
                        "%.0f %%",
                        sliderValue
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(Spacing.Small))
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..100f,
                    steps = 0
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(sliderValue.toDouble())
                }
            ) {
                Text("Apply")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun TopdonSettingsScreen(
    onNavigateUp: () -> Unit,
    palette: TopdonPalette,
    superSampling: TopdonSuperSamplingFactor,
    autoConnect: Boolean,
    previewFps: Int,
    emissivity: Double,
    distanceMeters: Double,
    gainMode: TopdonGainMode,
    dynamicRange: TopdonDynamicRange,
    autoShutter: Boolean,
    temperatureUnit: TopdonTemperatureUnit,
    ambientTemperatureCelsius: Double,
    ambientHumidityPercent: Double,
    onPaletteClick: () -> Unit,
    onSuperSamplingClick: () -> Unit,
    onFpsClick: () -> Unit,
    onAutoConnectChange: (Boolean) -> Unit,
    onEmissivityClick: () -> Unit,
    onDistanceClick: () -> Unit,
    onGainModeClick: () -> Unit,
    onDynamicRangeClick: () -> Unit,
    onTemperatureUnitClick: () -> Unit,
    onAmbientTemperatureClick: () -> Unit,
    onAmbientHumidityClick: () -> Unit,
    onAutoShutterChange: (Boolean) -> Unit
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
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
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
            SettingsSection(
                title = "Display"
            ) {
                SettingsItem(
                    title = "Colour Palette",
                    subtitle = palette.name.lowercase()
                        .replaceFirstChar { it.uppercase() },
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

            SettingsSection(
                title = "Connection"
            ) {
                SettingsToggleItem(
                    title = "Auto-Connect",
                    subtitle = "Automatically connect when device is attached",
                    checked = autoConnect,
                    onCheckedChange = onAutoConnectChange
                )
            }

            SettingsSection(
                title = "Storage"
            ) {
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

            SettingsSection(
                title = "Temperature"
            ) {
                SettingsItem(
                    title = "Unit",
                    subtitle = when (temperatureUnit) {
                        TopdonTemperatureUnit.CELSIUS -> "Celsius (°C)"
                        TopdonTemperatureUnit.FAHRENHEIT -> "Fahrenheit (°F)"
                    },
                    onClick = onTemperatureUnitClick
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Emissivity",
                    subtitle = String.format(
                        Locale.US,
                        "%.2f",
                        emissivity
                    ),
                    onClick = onEmissivityClick
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Distance Correction",
                    subtitle = String.format(
                        Locale.US,
                        "%.2f m",
                        distanceMeters
                    ),
                    onClick = onDistanceClick
                )

                HorizontalDivider()

                val ambientDisplay =
                    if (temperatureUnit == TopdonTemperatureUnit.FAHRENHEIT) {
                        String.format(
                            Locale.US,
                            "%.1f °F",
                            ambientTemperatureCelsius * 9.0 / 5.0 + 32.0
                        )
                    } else {
                        String.format(
                            Locale.US,
                            "%.1f °C",
                            ambientTemperatureCelsius
                        )
                    }

                SettingsItem(
                    title = "Ambient Temperature",
                    subtitle = ambientDisplay,
                    onClick = onAmbientTemperatureClick
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Ambient Humidity",
                    subtitle = String.format(
                        Locale.US,
                        "%.0f %%",
                        ambientHumidityPercent
                    ),
                    onClick = onAmbientHumidityClick
                )

                HorizontalDivider()

                SettingsToggleItem(
                    title = "Auto Shutter",
                    subtitle = "Trigger shutter automatically to stabilise measurements",
                    checked = autoShutter,
                    onCheckedChange = onAutoShutterChange
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Gain Mode",
                    subtitle = gainMode.name.lowercase()
                        .replaceFirstChar { it.uppercase() },
                    onClick = onGainModeClick
                )

                HorizontalDivider()

                SettingsItem(
                    title = "Dynamic Range",
                    subtitle = when (dynamicRange) {
                        TopdonDynamicRange.STANDARD -> "Standard"
                        TopdonDynamicRange.WIDE -> "Wide (HDR)"
                    },
                    onClick = onDynamicRangeClick
                )
            }

            SettingsSection(
                title = "About"
            ) {
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
        verticalArrangement = Arrangement.spacedBy(
            0.dp
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
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
            .clickable(
                onClick = onClick
            )
            .padding(
                Spacing.Medium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(
                1f
            )
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
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
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
            .padding(
                Spacing.Medium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(
                1f
            )
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
