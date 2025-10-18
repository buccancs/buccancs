package com.buccancs.ui.topdon

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buccancs.domain.model.DeviceId
import com.buccancs.ui.components.topdon.TopdonAppBarIconButton
import com.buccancs.ui.components.topdon.TopdonBackButton
import com.buccancs.ui.components.topdon.TopdonButton
import com.buccancs.ui.components.topdon.TopdonCenterAlignedTopAppBar
import com.buccancs.ui.components.topdon.TopdonOutlinedButton
import com.buccancs.ui.topdon.gallery.TopdonGalleryPane
import com.buccancs.ui.topdon.thermal.ThermalPreviewPane
import com.buccancs.ui.theme.LayoutPadding
import com.buccancs.ui.theme.Spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopdonRoute(
    deviceId: DeviceId,
    onNavigateUp: () -> Unit,
    onNavigateToThermalFullScreen: () -> Unit,
    onNavigateToGuide: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onOpenGalleryDetail: (String) -> Unit,
    viewModel: TopdonViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(deviceId) {
        viewModel.setActiveDevice(deviceId)
        viewModel.refresh()
    }

    val tabs = remember { TopdonFeatureTab.entries.toList() }
    val pagerState =
        rememberPagerState(
            initialPage = TopdonFeatureTab.Thermal.ordinal,
            pageCount = { tabs.size }
        )
    val coroutineScope = rememberCoroutineScope()
    val currentPage by remember {
        derivedStateOf { pagerState.currentPage }
    }
    val currentTab = tabs[currentPage]

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopdonHubTopBar(
                currentTab = currentTab,
                onNavigateUp = onNavigateUp,
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToGuide = onNavigateToGuide,
                onOpenThermalFullScreen = onNavigateToThermalFullScreen
            )
        },
        bottomBar = {
            TopdonHubBottomBar(
                tabs = tabs,
                currentPage = currentPage,
                onTabSelected = { index ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            key = { tabs[it].ordinal }
        ) { page ->
            when (tabs[page]) {
                TopdonFeatureTab.Monitor -> {
                    TopdonMonitorPage(
                        state = state,
                        modifier = Modifier.fillMaxSize(),
                        onNavigateToThermalPreview = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(TopdonFeatureTab.Thermal.ordinal)
                            }
                        },
                        onNavigateToGallery = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(TopdonFeatureTab.Gallery.ordinal)
                            }
                        },
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

                TopdonFeatureTab.Gallery -> {
                    TopdonGalleryPane(
                        modifier = Modifier.fillMaxSize(),
                        onNavigateToDetail = onOpenGalleryDetail
                    )
                }

                TopdonFeatureTab.Thermal -> {
                    ThermalPreviewPane(
                        state = state,
                        modifier = Modifier.fillMaxSize(),
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

                TopdonFeatureTab.Report -> {
                    TopdonReportPane(
                        modifier = Modifier.fillMaxSize(),
                        onNavigateToGuide = onNavigateToGuide
                    )
                }

                TopdonFeatureTab.Mine -> {
                    TopdonMinePane(
                        modifier = Modifier.fillMaxSize(),
                        onNavigateToSettings = onNavigateToSettings
                    )
                }
            }
        }
    }
}

private enum class TopdonFeatureTab(
    val title: String,
    val icon: ImageVector
) {
    Monitor("Monitor", Icons.Default.Speed),
    Gallery("Gallery", Icons.Default.Image),
    Thermal("Thermal", Icons.Default.Thermostat),
    Report("Report", Icons.Default.Article),
    Mine("Mine", Icons.Default.Person)
}

@Composable
private fun TopdonHubTopBar(
    currentTab: TopdonFeatureTab,
    onNavigateUp: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToGuide: () -> Unit,
    onOpenThermalFullScreen: () -> Unit
) {
    TopdonCenterAlignedTopAppBar(
        title = currentTab.title,
        navigationIcon = {
            TopdonBackButton(
                onClick = onNavigateUp
            )
        },
        actions = {
            when (currentTab) {
                TopdonFeatureTab.Monitor -> {
                    TopdonAppBarIconButton(
                        icon = Icons.Default.Settings,
                        contentDescription = "Device settings",
                        onClick = onNavigateToSettings
                    )
                }

                TopdonFeatureTab.Thermal -> {
                    TopdonAppBarIconButton(
                        icon = Icons.Default.OpenInFull,
                        contentDescription = "Open full-screen preview",
                        onClick = onOpenThermalFullScreen
                    )
                }

                TopdonFeatureTab.Report -> {
                    IconButton(
                        onClick = onNavigateToGuide
                    ) {
                        Icon(
                            imageVector = Icons.Default.Article,
                            contentDescription = "View guides"
                        )
                    }
                }

                else -> Unit
            }
        }
    )
}

@Composable
private fun TopdonHubBottomBar(
    tabs: List<TopdonFeatureTab>,
    currentPage: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = currentPage == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title
                    )
                },
                label = {
                    Text(tab.title)
                }
            )
        }
    }
}

@Composable
private fun TopdonReportPane(
    modifier: Modifier = Modifier,
    onNavigateToGuide: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = LayoutPadding.Screen,
                vertical = Spacing.Large
            ),
        verticalArrangement = Arrangement.spacedBy(
            Spacing.Medium
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Generate annotated thermal reports after signing in.",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Reports consolidate measurement snapshots, operator notes, and export-ready artefacts. Connect your orchestrator account to begin.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        TopdonButton(
            onClick = onNavigateToGuide
        ) {
            Text("Review connection guide")
        }
    }
}

@Composable
private fun TopdonMinePane(
    modifier: Modifier = Modifier,
    onNavigateToSettings: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = LayoutPadding.Screen,
                vertical = Spacing.Large
            ),
        verticalArrangement = Arrangement.spacedBy(
            Spacing.Medium
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Personalise your capture workflow.",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Manage auto-connect preferences, recording defaults, and notification rules from the device settings surface.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        TopdonOutlinedButton(
            onClick = onNavigateToSettings
        ) {
            Text("Open device settings")
        }
    }
}
