package com.buccancs.desktop.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Navigation rail for desktop navigation
 * Provides persistent side navigation for quick screen access
 */
@Composable
fun BuccancsNavigationRail(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        header = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Buccancs",
                modifier = Modifier.padding(Spacing.Medium),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        Spacer(Modifier.height(Spacing.Medium))

        NavigationItem(
            icon = Icons.Default.Dashboard,
            label = "Dashboard",
            selected = currentScreen == Screen.Dashboard,
            onClick = { onNavigate(Screen.Dashboard) }
        )

        NavigationItem(
            icon = Icons.Default.Sensors,
            label = "Sensors",
            selected = currentScreen == Screen.SensorStatus,
            onClick = { onNavigate(Screen.SensorStatus) }
        )

        NavigationItem(
            icon = Icons.Default.Sync,
            label = "Sync",
            selected = currentScreen == Screen.Synchronisation,
            onClick = { onNavigate(Screen.Synchronisation) }
        )

        NavigationItem(
            icon = Icons.Default.Tune,
            label = "Calibration",
            selected = currentScreen == Screen.Calibration,
            onClick = { onNavigate(Screen.Calibration) }
        )

        NavigationItem(
            icon = Icons.Default.Videocam,
            label = "Preview",
            selected = currentScreen == Screen.Preview,
            onClick = { onNavigate(Screen.Preview) }
        )

        NavigationItem(
            icon = Icons.Default.PlayArrow,
            label = "Playback",
            selected = currentScreen == Screen.VideoPlayback,
            onClick = { onNavigate(Screen.VideoPlayback) }
        )

        NavigationItem(
            icon = Icons.Default.Folder,
            label = "Files",
            selected = currentScreen == Screen.FileExplorer,
            onClick = { onNavigate(Screen.FileExplorer) }
        )

        NavigationItem(
            icon = Icons.Default.Person,
            label = "Users",
            selected = currentScreen == Screen.Users,
            onClick = { onNavigate(Screen.Users) }
        )

        Spacer(Modifier.weight(1f))

        NavigationItem(
            icon = Icons.Default.Settings,
            label = "Settings",
            selected = currentScreen == Screen.Settings,
            onClick = { onNavigate(Screen.Settings) }
        )
    }
}

@Composable
private fun NavigationItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationRailItem(
        icon = { Icon(icon, contentDescription = label) },
        label = { Text(label, style = MaterialTheme.typography.labelSmall) },
        selected = selected,
        onClick = onClick,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.onSurface,
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}
