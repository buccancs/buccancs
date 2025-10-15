package com.buccancs.ui.theme.topdon

import androidx.compose.ui.graphics.Color

/**
 * Topdon TC001 thermal camera application colour palette
 * Extracted from original-topdon-app XML resources
 * 
 * Primary background: #16131E (dark purple-black)
 * Theme accent: #2B79D8 (blue)
 * Temperature indicators: #F3812F (orange hot), #28C445 (green cool)
 */
object TopdonColors {
    val DarkBackground = Color(0xFF16131E)
    val DarkBackgroundVariant = Color(0xFF1C1B1F)
    val DarkSurface = Color(0xFF2D2A33)
    val DarkSurfaceVariant = Color(0xFF3A3740)
    
    val Primary = Color(0xFF2B79D8)
    val PrimaryVariant = Color(0xFF1B5FB8)
    val PrimaryContainer = Color(0xFF3B89E8)
    
    val Secondary = Color(0xFF55272F)
    val SecondaryContainer = Color(0xFF65374F)
    
    val TempHot = Color(0xFFF3812F)
    val TempCool = Color(0xFF28C445)
    val TempNeutral = Color(0xFFEEEEEE)
    
    val GreenPoint = Color(0xFF0CEB82)
    val LineSeparator = Color(0xFF5B5961)
    
    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0x96FFFFFF)
    val TextTertiary = Color(0x7DFFFFFF)
    val TextQuaternary = Color(0xB2FFFFFF)
    
    val SelectBlack = Color(0xFF000000)
    val SelectWhite = Color(0xFFFFFFFF)
    val SelectBlue = Color(0xFF2B79D8)
    val SelectRed = Color(0xFFFF0000)
    val SelectGreen = Color(0xFF0FA752)
    val SelectGrey = Color(0xFF808080)
    
    val CustomControl = Color(0xFF8A898E)
    val WheelSelectBg = Color(0x1A2B79D7)
    
    val Transparent = Color(0x00000000)
    val TransparentWhite = Color(0xCCFFFFFF)
}
