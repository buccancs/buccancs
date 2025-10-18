package com.topdon.module.thermal.ir.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
    darkColorScheme(
        primary = Color(
            0xFFFF6B35
        ),
        onPrimary = Color(
            0xFF000000
        ),
        primaryContainer = Color(
            0xFFCC5529
        ),
        onPrimaryContainer = Color(
            0xFFFFFFFF
        ),
        secondary = Color(
            0xFF4ECDC4
        ),
        onSecondary = Color(
            0xFF000000
        ),
        secondaryContainer = Color(
            0xFF3EA49C
        ),
        onSecondaryContainer = Color(
            0xFFFFFFFF
        ),
        background = Color(
            0xFF1a1a2e
        ),
        onBackground = Color(
            0xFFE0E0E0
        ),
        surface = Color(
            0xFF16213e
        ),
        onSurface = Color(
            0xFFE0E0E0
        ),
        surfaceVariant = Color(
            0xFF0f1419
        ),
        onSurfaceVariant = Color(
            0xFFB0B0B0
        ),
        error = Color(
            0xFFCF6679
        ),
        onError = Color(
            0xFF000000
        )
    )

@Composable
fun ThermalIRTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
