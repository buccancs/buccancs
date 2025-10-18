package com.buccancs.ui.components.topdon

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Topdon-styled top app bar with left-aligned title
 */
@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun TopdonTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

/**
 * Topdon-styled centre-aligned top app bar
 */
@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun TopdonCenterAlignedTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

/**
 * Standard back navigation icon button
 */
@Composable
fun TopdonBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = "Navigate back"
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * App bar icon button
 */
@Composable
fun TopdonAppBarIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun TopdonTopAppBarPreview() {
    TopdonTheme {
        TopdonTopAppBar(
            title = "Thermal Camera",
            navigationIcon = {
                TopdonBackButton(
                    onClick = {})
            },
            actions = {
                TopdonAppBarIconButton(
                    icon = Icons.Default.Settings,
                    onClick = {},
                    contentDescription = "Settings"
                )
                TopdonAppBarIconButton(
                    icon = Icons.Default.MoreVert,
                    onClick = {},
                    contentDescription = "More options"
                )
            }
        )
    }
}

@Preview
@Composable
private fun TopdonCenterAlignedTopAppBarPreview() {
    TopdonTheme {
        TopdonCenterAlignedTopAppBar(
            title = "TC001",
            navigationIcon = {
                TopdonBackButton(
                    onClick = {})
            },
            actions = {
                TopdonAppBarIconButton(
                    icon = Icons.Default.Refresh,
                    onClick = {},
                    contentDescription = "Refresh"
                )
            }
        )
    }
}
