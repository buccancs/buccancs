package com.topdon.tc001.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val index: Int
)

@Composable
fun MainContainerScreen(
    devices: List<DeviceInfo>,
    galleryImages: List<GalleryImage>,
    isGalleryLoading: Boolean,
    userName: String?,
    userEmail: String?,
    isLoggedIn: Boolean,
    currentLanguage: String,
    appVersion: String,
    onConnectDeviceClick: () -> Unit,
    onAddDeviceClick: () -> Unit,
    onDeviceClick: (DeviceType) -> Unit,
    onDeviceLongClick: (DeviceType) -> Unit,
    onImageClick: (GalleryImage) -> Unit,
    onProfileClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onMoreSettingsClick: () -> Unit,
    onAboutClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember {
        mutableIntStateOf(
            1
        )
    }

    val navItems =
        listOf(
            BottomNavItem(
                "Gallery",
                Icons.Default.Image,
                0
            ),
            BottomNavItem(
                "Main",
                Icons.Default.Home,
                1
            ),
            BottomNavItem(
                "Mine",
                Icons.Default.Person,
                2
            )
        )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                navItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(
                                item.label
                            )
                        },
                        selected = selectedTab == item.index,
                        onClick = {
                            selectedTab =
                                item.index
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues
                )
        ) {
            when (selectedTab) {
                0 -> GalleryScreen(
                    images = galleryImages,
                    isLoading = isGalleryLoading,
                    onImageClick = onImageClick
                )

                1 -> MainScreen(
                    devices = devices,
                    onConnectDeviceClick = onConnectDeviceClick,
                    onAddDeviceClick = onAddDeviceClick,
                    onDeviceClick = onDeviceClick,
                    onDeviceLongClick = onDeviceLongClick
                )

                2 -> SettingsScreen(
                    userName = userName,
                    userEmail = userEmail,
                    isLoggedIn = isLoggedIn,
                    currentLanguage = currentLanguage,
                    appVersion = appVersion,
                    onProfileClick = onProfileClick,
                    onLanguageClick = onLanguageClick,
                    onMoreSettingsClick = onMoreSettingsClick,
                    onAboutClick = onAboutClick,
                    onLogoutClick = onLogoutClick
                )
            }
        }
    }
}
