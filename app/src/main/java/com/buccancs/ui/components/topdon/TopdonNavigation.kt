package com.buccancs.ui.components.topdon

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buccancs.ui.theme.topdon.TopdonColors
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Topdon-styled bottom navigation bar
 * Matches original app's bottom tab navigation design
 */
@Composable
fun TopdonBottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 0.dp,
        content = content
    )
}

/**
 * Topdon-styled navigation bar item
 */
@Composable
fun RowScope.TopdonNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    badge: @Composable (() -> Unit)? = null
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label
            )
        },
        label = { Text(label) },
        modifier = modifier,
        enabled = enabled,
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

/**
 * Navigation bar item with badge support (for notifications)
 */
@Composable
fun RowScope.TopdonNavigationBarItemWithBadge(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    badgeCount: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            androidx.compose.material3.BadgedBox(
                badge = {
                    if (badgeCount > 0) {
                        androidx.compose.material3.Badge(
                            containerColor = TopdonColors.SelectRed,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ) {
                            Text(
                                text = if (badgeCount > 99) "99+" else badgeCount.toString(),
                                style = androidx.compose.material3.MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label
                )
            }
        },
        label = { Text(label) },
        modifier = modifier,
        enabled = enabled,
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

@Preview
@Composable
private fun TopdonBottomNavigationPreview() {
    TopdonTheme {
        TopdonBottomNavigation {
            TopdonNavigationBarItem(
                selected = false,
                onClick = {},
                icon = Icons.Default.Camera,
                label = "Monitor"
            )
            TopdonNavigationBarItem(
                selected = false,
                onClick = {},
                icon = Icons.Default.PhotoLibrary,
                label = "Gallery"
            )
            TopdonNavigationBarItem(
                selected = true,
                onClick = {},
                icon = Icons.Default.Thermostat,
                label = "Thermal"
            )
            TopdonNavigationBarItem(
                selected = false,
                onClick = {},
                icon = Icons.Default.Description,
                label = "Report"
            )
            TopdonNavigationBarItemWithBadge(
                selected = false,
                onClick = {},
                icon = Icons.Default.Person,
                label = "Profile",
                badgeCount = 3
            )
        }
    }
}
