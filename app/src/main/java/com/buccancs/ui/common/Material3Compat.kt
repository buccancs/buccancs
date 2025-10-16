package com.buccancs.ui.common

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Backport wrapper for HorizontalDivider that is missing on older Compose Material3 versions.
 */
@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = Dp.Hairline,
    color: Color = MaterialTheme.colorScheme.outlineVariant
) {
    Divider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
