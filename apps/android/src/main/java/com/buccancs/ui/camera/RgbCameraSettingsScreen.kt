package com.buccancs.ui.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.components.SectionHeader
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RgbCameraSettingsRoute(
    deviceId: DeviceId,
    onNavigateUp: () -> Unit,
    viewModel: RgbCameraSettingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(deviceId) {
        viewModel.setActiveDevice(deviceId)
    }

    RgbCameraSettingsScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onFieldChanged = viewModel::updateField,
        onRawEnabledChanged = viewModel::toggleRawEnabled,
        onAwbChanged = viewModel::selectAwb,
        onReset = viewModel::resetSettings,
        onApply = viewModel::applySettings
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RgbCameraSettingsScreen(
    state: RgbCameraSettingsUiState,
    onNavigateUp: () -> Unit,
    onFieldChanged: (com.buccancs.domain.usecase.RgbCameraField, String) -> Unit,
    onRawEnabledChanged: (Boolean) -> Unit,
    onAwbChanged: (String) -> Unit,
    onReset: () -> Unit,
    onApply: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("RGB Camera Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
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
                SectionHeader(
                    title = "Video Settings",
                    icon = Icons.Default.Settings
                )
            }
            item {
                VideoSettingsCard(
                    state = state,
                    onFieldChanged = onFieldChanged
                )
            }

            if (state.supportsRaw) {
                item {
                    SectionHeader(
                        title = "RAW Capture",
                        icon = Icons.Default.Camera
                    )
                }
                item {
                    RawCaptureCard(
                        state = state,
                        onRawEnabledChanged = onRawEnabledChanged,
                        onFieldChanged = onFieldChanged
                    )
                }
            }

            item {
                SectionHeader(
                    title = "Camera Controls",
                    icon = Icons.Default.Settings
                )
            }
            item {
                CameraControlsCard(
                    state = state,
                    onFieldChanged = onFieldChanged,
                    onAwbChanged = onAwbChanged
                )
            }

            item {
                ActionButtonsCard(
                    dirty = state.dirty,
                    applying = state.applying,
                    onReset = onReset,
                    onApply = onApply
                )
            }
        }
    }
}

@Composable
private fun VideoSettingsCard(
    state: RgbCameraSettingsUiState,
    onFieldChanged: (com.buccancs.domain.usecase.RgbCameraField, String) -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.SmallMedium
    ) {
        Text(
            text = "Video Recording",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        OutlinedTextField(
            value = state.videoFps,
            onValueChange = {
                onFieldChanged(
                    com.buccancs.domain.usecase.RgbCameraField.VIDEO_FPS,
                    it
                )
            },
            label = { Text("Frame Rate (fps)") },
            supportingText = { Text("Recommended: 30 or 60 fps") },
            isError = state.errors.containsKey(com.buccancs.domain.usecase.RgbCameraField.VIDEO_FPS),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        state.errors[com.buccancs.domain.usecase.RgbCameraField.VIDEO_FPS]?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = state.videoBitRate,
            onValueChange = {
                onFieldChanged(
                    com.buccancs.domain.usecase.RgbCameraField.VIDEO_BIT_RATE,
                    it
                )
            },
            label = { Text("Bitrate (bps)") },
            supportingText = { Text("Higher bitrate = better quality, larger files") },
            isError = state.errors.containsKey(com.buccancs.domain.usecase.RgbCameraField.VIDEO_BIT_RATE),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        state.errors[com.buccancs.domain.usecase.RgbCameraField.VIDEO_BIT_RATE]?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun RawCaptureCard(
    state: RgbCameraSettingsUiState,
    onRawEnabledChanged: (Boolean) -> Unit,
    onFieldChanged: (com.buccancs.domain.usecase.RgbCameraField, String) -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.SmallMedium
    ) {
        Text(
            text = "RAW Image Capture",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.small
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Spacing.SmallMedium,
                    vertical = Spacing.Small
                )
            ) {
                Text(
                    text = "RAW DNG Format",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Captures uncompressed DNG images at regular intervals during recording. DNG files preserve full sensor data for advanced post-processing.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.SmallMedium, vertical = Spacing.Small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Enable RAW capture",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = if (state.rawEnabled) "DNG files will be saved" else "Only video will be recorded",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = state.rawEnabled,
                    onCheckedChange = onRawEnabledChanged
                )
            }
        }

        if (state.rawEnabled) {
            OutlinedTextField(
                value = state.rawIntervalMs,
                onValueChange = {
                    onFieldChanged(
                        com.buccancs.domain.usecase.RgbCameraField.RAW_INTERVAL_MS,
                        it
                    )
                },
                label = { Text("Capture interval (ms)") },
                supportingText = { Text("Time between RAW captures") },
                isError = state.errors.containsKey(com.buccancs.domain.usecase.RgbCameraField.RAW_INTERVAL_MS),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            state.errors[com.buccancs.domain.usecase.RgbCameraField.RAW_INTERVAL_MS]?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CameraControlsCard(
    state: RgbCameraSettingsUiState,
    onFieldChanged: (com.buccancs.domain.usecase.RgbCameraField, String) -> Unit,
    onAwbChanged: (String) -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.SmallMedium
    ) {
        Text(
            text = "Camera Parameters",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        OutlinedTextField(
            value = state.exposureNs,
            onValueChange = {
                onFieldChanged(
                    com.buccancs.domain.usecase.RgbCameraField.EXPOSURE_NS,
                    it
                )
            },
            label = { Text("Exposure time (ns)") },
            supportingText = { Text("Optional: Manual exposure control") },
            isError = state.errors.containsKey(com.buccancs.domain.usecase.RgbCameraField.EXPOSURE_NS),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = state.iso,
            onValueChange = { onFieldChanged(com.buccancs.domain.usecase.RgbCameraField.ISO, it) },
            label = { Text("ISO sensitivity") },
            supportingText = { Text("Optional: Manual ISO value") },
            isError = state.errors.containsKey(com.buccancs.domain.usecase.RgbCameraField.ISO),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = state.focusMeters,
            onValueChange = {
                onFieldChanged(
                    com.buccancs.domain.usecase.RgbCameraField.FOCUS_METERS,
                    it
                )
            },
            label = { Text("Focus distance (m)") },
            supportingText = { Text("Optional: Manual focus distance") },
            isError = state.errors.containsKey(com.buccancs.domain.usecase.RgbCameraField.FOCUS_METERS),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        var awbExpanded by remember { mutableStateOf(false) }
        val awbOptions =
            listOf("auto", "incandescent", "fluorescent", "daylight", "cloudy", "shade")

        ExposedDropdownMenuBox(
            expanded = awbExpanded,
            onExpandedChange = { awbExpanded = !awbExpanded }
        ) {
            OutlinedTextField(
                value = state.awbMode,
                onValueChange = {},
                readOnly = true,
                label = { Text("White balance") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = awbExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                expanded = awbExpanded,
                onDismissRequest = { awbExpanded = false }
            ) {
                awbOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onAwbChanged(option)
                            awbExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionButtonsCard(
    dirty: Boolean,
    applying: Boolean,
    onReset: () -> Unit,
    onApply: () -> Unit
) {
    SectionCard(
        modifier = Modifier.fillMaxWidth(),
        spacing = Spacing.Small
    ) {
        if (dirty) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "You have unsaved changes",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(
                        horizontal = Spacing.SmallMedium,
                        vertical = Spacing.Small
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            FilledTonalButton(
                onClick = onReset,
                enabled = dirty && !applying,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
                Text("Reset")
            }
            FilledTonalButton(
                onClick = onApply,
                enabled = dirty && !applying,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.IconSizeSmall)
                )
                Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
                Text("Apply")
            }
        }
    }
}
