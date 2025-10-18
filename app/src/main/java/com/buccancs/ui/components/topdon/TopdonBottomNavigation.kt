package com.buccancs.ui.components.topdon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccancs.ui.theme.Dimensions

@Composable
fun TopdonBottomNavigation(
    modifier: Modifier = Modifier,
    selectedTab: TopdonTab,
    onTabSelected: (TopdonTab) -> Unit,
    showNotificationBadge: Boolean = false
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = Dimensions.ElevationLevel5
    ) {
        TopdonTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = selectedTab == tab,
                onClick = {
                    onTabSelected(
                        tab
                    )
                },
                icon = {
                    Box {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label
                        )

                        if (tab == TopdonTab.PROFILE && showNotificationBadge) {
                            Box(
                                modifier = Modifier
                                    .size(
                                        8.dp
                                    )
                                    .align(
                                        Alignment.TopEnd
                                    )
                                    .clip(
                                        CircleShape
                                    )
                                    .background(
                                        MaterialTheme.colorScheme.error
                                    )
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = tab.label,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp
                    )
                }
            )
        }
    }
}

@Composable
fun TopdonCustomBottomNavigation(
    modifier: Modifier = Modifier,
    selectedTab: TopdonTab,
    onTabSelected: (TopdonTab) -> Unit,
    showNotificationBadge: Boolean = false,
    visible: Boolean = true
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it }),
        exit = slideOutVertically(
            targetOffsetY = { it })
    ) {
        Surface(
            modifier = modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = Dimensions.ElevationLevel5,
            shadowElevation = Dimensions.ElevationLevel5
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        56.dp
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopdonTab.entries.forEach { tab ->
                    TopdonNavItem(
                        tab = tab,
                        selected = selectedTab == tab,
                        onClick = {
                            onTabSelected(
                                tab
                            )
                        },
                        showBadge = tab == TopdonTab.PROFILE && showNotificationBadge,
                        modifier = Modifier.weight(
                            1f
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun TopdonNavItem(
    tab: TopdonTab,
    selected: Boolean,
    onClick: () -> Unit,
    showBadge: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                Icon(
                    imageVector = tab.icon,
                    contentDescription = tab.label,
                    tint = if (selected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    modifier = Modifier.size(
                        24.dp
                    )
                )

                if (showBadge) {
                    Box(
                        modifier = Modifier
                            .size(
                                8.dp
                            )
                            .align(
                                Alignment.TopEnd
                            )
                            .clip(
                                CircleShape
                            )
                            .background(
                                MaterialTheme.colorScheme.error
                            )
                    )
                }
            }

            Text(
                text = tab.label,
                style = MaterialTheme.typography.labelSmall,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 11.sp
            )
        }
    }
}

enum class TopdonTab(
    val label: String,
    val icon: ImageVector
) {
    GALLERY(
        "Gallery",
        Icons.Default.Image
    ),
    CAMERA(
        "Camera",
        Icons.Default.Thermostat
    ),
    PROFILE(
        "Profile",
        Icons.Default.Person
    )
}
