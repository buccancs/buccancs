package com.buccancs.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

/**
 * Global shape system that keeps cards, dialogs, and surfaces consistent.
 */
val BuccancsShapes = Shapes(
    extraSmall = RoundedCornerShape(CornerRadius.Small),
    small = RoundedCornerShape(CornerRadius.Small),
    medium = RoundedCornerShape(CornerRadius.Medium),
    large = RoundedCornerShape(CornerRadius.Large),
    extraLarge = RoundedCornerShape(CornerRadius.ExtraLarge)
)
