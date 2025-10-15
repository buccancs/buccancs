package com.buccancs.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Design tokens for dimensions following Material Design 3 guidelines
 * All touch targets should be >= 48dp
 * Layout aligned to 8dp grid, small elements to 4dp grid
 */
object Dimensions {
    // Icon sizes aligned to MD3 standards
    val IconSizeSmall = 16.dp      // For status indicators, chips
    val IconSizeDefault = 24.dp    // Standard icon size
    val IconSizeLarge = 32.dp      // Large icons, FAB
    
    // Touch targets (minimum 48dp per MD3)
    val TouchTargetMinimum = 48.dp
    
    // Common sizes aligned to 8dp grid
    val Size160 = 160.dp  // Preview height
    val Size120 = 120.dp
    val Size80 = 80.dp
    val Size48 = 48.dp    // Minimum touch target
    val Size40 = 40.dp
    val Size32 = 32.dp
    val Size24 = 24.dp
    val Size16 = 16.dp
    val Size8 = 8.dp
    val Size4 = 4.dp
}
