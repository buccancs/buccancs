package com.buccancs.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.material3.HorizontalDivider as Material3HorizontalDivider

/**
 * Wrapper for HorizontalDivider with custom default styling.
 */
@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = Dp.Hairline,
    color: Color = MaterialTheme.colorScheme.outlineVariant
) {
    Material3HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
