package com.buccancs.ui.camera

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
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.ui.components.SectionCard
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RgbCameraRoute(
    deviceId: DeviceId,
    onNavigateUp: () -> Unit,
    onNavigateToSettings: () -> Unit = {},
    viewModel: RgbCameraViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(deviceId) {
        viewModel.setActiveDevice(deviceId)
        viewModel.refresh()
    }

    RgbCameraScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onNavigateToSettings = onNavigateToSettings,
        onRefresh = viewModel::refresh,
        onConnect = viewModel::connect,
        onDisconnect = viewModel::disconnect,
        onStartPreview = viewModel::startPreview,
        onStopPreview = viewModel::stopPreview,
        onClearError = viewModel::clearError
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RgbCameraScreen(
    state: RgbCameraUiState,
    onNavigateUp: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onRefresh: () -> Unit,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit,
    onClearError: () -> Unit
) {
    val toolbarState = rememberTopAppBarState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "RGB Camera",
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
                            contentDescription = "Camera settings"
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
                CameraStatusCard(
                    state = state,
                    onRefresh = onRefresh,
                    onConnect = onConnect,
                    onDisconnect = onDisconnect,
                    onClearError = onClearError
                )
            }
            item {
                CameraPreviewCard(
                    state = state,
                    onStartPreview = onStartPreview,
                    onStopPreview = onStopPreview
                )
            }
        }
    }
}

@Composable
private fun CameraStatusCard(
    state: RgbCameraUiState,
    onRefresh: () -> Unit,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onClearError: () -> Unit
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
            FilledTonalButton(
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
            FilledTonalButton(
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
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    }
}

@Composable
private fun CameraPreviewCard(
    state: RgbCameraUiState,
    onStartPreview: () -> Unit,
    onStopPreview: () -> Unit
) {
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
                text = "Camera Preview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            AssistChip(
                onClick = { },
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

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                if (state.previewActive && state.isConnected) {
                    // Actual camera preview
                    CameraPreviewView(
                        modifier = Modifier.fillMaxSize(),
                        onPreviewStarted = { /* Preview started */ },
                        onPreviewStopped = { /* Preview stopped */ },
                        onError = { error ->
                            // Handle error
                        }
                    )
                } else {
                    // Placeholder when not active
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Spacing.Small)
                    ) {
                        Icon(
                            imageVector = if (state.previewActive) Icons.Default.Camera else Icons.Default.Cameraswitch,
                            contentDescription = null,
                            modifier = Modifier.size(Dimensions.IconSizeLarge),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = when {
                                !state.isConnected -> "Camera not connected"
                                !state.previewActive -> "Preview idle"
                                else -> "Starting preview..."
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        if (state.previewActive) {
            Text(
                text = "Recording at ${state.frameRate} fps • Tap Stop to end preview",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            FilledTonalButton(
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
            FilledTonalButton(
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
        }
    }
}
