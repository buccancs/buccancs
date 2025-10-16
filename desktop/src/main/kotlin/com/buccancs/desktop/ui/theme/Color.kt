package com.buccancs.desktop.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Material Design 3 Color tokens
 * Defined separately for better maintainability and theme switching
 */

// Light theme color palette
object LightColorTokens {
    val Primary = Color(0xFF6750A4)
    val OnPrimary = Color(0xFFFFFFFF)
    val PrimaryContainer = Color(0xFFEADDFF)
    val OnPrimaryContainer = Color(0xFF21005D)

    val Secondary = Color(0xFF625B71)
    val OnSecondary = Color(0xFFFFFFFF)
    val SecondaryContainer = Color(0xFFE8DEF8)
    val OnSecondaryContainer = Color(0xFF1D192B)

    val Tertiary = Color(0xFF7D5260)
    val OnTertiary = Color(0xFFFFFFFF)
    val TertiaryContainer = Color(0xFFFFD8E4)
    val OnTertiaryContainer = Color(0xFF31111D)

    val Error = Color(0xFFB3261E)
    val OnError = Color(0xFFFFFFFF)
    val ErrorContainer = Color(0xFFF9DEDC)
    val OnErrorContainer = Color(0xFF410E0B)

    val Background = Color(0xFFFFFBFE)
    val OnBackground = Color(0xFF1C1B1F)

    val Surface = Color(0xFFFFFBFE)
    val OnSurface = Color(0xFF1C1B1F)
    val SurfaceVariant = Color(0xFFE7E0EC)
    val OnSurfaceVariant = Color(0xFF49454F)

    val Outline = Color(0xFF79747E)
    val OutlineVariant = Color(0xFFCAC4D0)

    val Scrim = Color(0xFF000000)

    val InverseSurface = Color(0xFF313033)
    val InverseOnSurface = Color(0xFFF4EFF4)
    val InversePrimary = Color(0xFFD0BCFF)
}

// Dark theme color palette
object DarkColorTokens {
    val Primary = Color(0xFFD0BCFF)
    val OnPrimary = Color(0xFF381E72)
    val PrimaryContainer = Color(0xFF4F378B)
    val OnPrimaryContainer = Color(0xFFEADDFF)

    val Secondary = Color(0xFFCCC2DC)
    val OnSecondary = Color(0xFF332D41)
    val SecondaryContainer = Color(0xFF4A4458)
    val OnSecondaryContainer = Color(0xFFE8DEF8)

    val Tertiary = Color(0xFFEFB8C8)
    val OnTertiary = Color(0xFF492532)
    val TertiaryContainer = Color(0xFF633B48)
    val OnTertiaryContainer = Color(0xFFFFD8E4)

    val Error = Color(0xFFF2B8B5)
    val OnError = Color(0xFF601410)
    val ErrorContainer = Color(0xFF8C1D18)
    val OnErrorContainer = Color(0xFFF9DEDC)

    val Background = Color(0xFF1C1B1F)
    val OnBackground = Color(0xFFE6E1E5)

    val Surface = Color(0xFF1C1B1F)
    val OnSurface = Color(0xFFE6E1E5)
    val SurfaceVariant = Color(0xFF49454F)
    val OnSurfaceVariant = Color(0xFFCAC4D0)

    val Outline = Color(0xFF938F99)
    val OutlineVariant = Color(0xFF49454F)

    val Scrim = Color(0xFF000000)

    val InverseSurface = Color(0xFFE6E1E5)
    val InverseOnSurface = Color(0xFF313033)
    val InversePrimary = Color(0xFF6750A4)
}

// Semantic colors for specific use cases
object SemanticColors {
    val Success = Color(0xFF2E7D32)
    val OnSuccess = Color(0xFFFFFFFF)
    val SuccessContainer = Color(0xFFC8E6C9)
    val OnSuccessContainer = Color(0xFF1B5E20)

    val Warning = Color(0xFFED6C02)
    val OnWarning = Color(0xFFFFFFFF)
    val WarningContainer = Color(0xFFFFE0B2)
    val OnWarningContainer = Color(0xFFE65100)

    val Info = Color(0xFF0288D1)
    val OnInfo = Color(0xFFFFFFFF)
    val InfoContainer = Color(0xFFB3E5FC)
    val OnInfoContainer = Color(0xFF01579B)
}
