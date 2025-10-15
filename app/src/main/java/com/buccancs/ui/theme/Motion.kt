package com.buccancs.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.ui.unit.IntOffset

/**
 * Material Design 3 Motion System
 * Following MD3 easing curves and duration patterns
 * 
 * Motion should:
 * - Explain state changes
 * - Show hierarchy
 * - Be subtle and purposeful
 * - Use appropriate easing
 */

/**
 * MD3 Easing Curves
 * https://m3.material.io/styles/motion/easing-and-duration/tokens-specs
 */
object MotionEasing {
    /**
     * Emphasized easing - For attention-getting, expressive transitions
     * Used for: Large elements, important state changes
     */
    val Emphasized: Easing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    
    /**
     * Emphasized decelerate - Elements entering the screen
     * Used for: Incoming elements, expanding
     */
    val EmphasizedDecelerate: Easing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f)
    
    /**
     * Emphasized accelerate - Elements leaving the screen
     * Used for: Outgoing elements, collapsing
     */
    val EmphasizedAccelerate: Easing = CubicBezierEasing(0.3f, 0.0f, 0.8f, 0.15f)
    
    /**
     * Standard easing - Default for most transitions
     * Used for: Standard animations, subtle changes
     */
    val Standard: Easing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    
    /**
     * Standard decelerate - Elements entering
     * Used for: Fade in, scale up
     */
    val StandardDecelerate: Easing = CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f)
    
    /**
     * Standard accelerate - Elements leaving
     * Used for: Fade out, scale down
     */
    val StandardAccelerate: Easing = CubicBezierEasing(0.3f, 0.0f, 1.0f, 1.0f)
    
    /**
     * Legacy easing - For backward compatibility
     * Used for: Subtle, less pronounced animations
     */
    val Legacy: Easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
    
    /**
     * Legacy decelerate
     */
    val LegacyDecelerate: Easing = CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f)
    
    /**
     * Legacy accelerate
     */
    val LegacyAccelerate: Easing = CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f)
}

/**
 * MD3 Duration Tokens
 * https://m3.material.io/styles/motion/easing-and-duration/tokens-specs
 */
object MotionDuration {
    // Short durations - Small elements, simple state changes
    const val Short1 = 50      // Minimal, instant feel
    const val Short2 = 100     // Checkboxes, switches
    const val Short3 = 150     // Radio buttons, selection
    const val Short4 = 200     // Chips, small cards
    
    // Medium durations - Standard transitions
    const val Medium1 = 250    // Standard fade, small movements
    const val Medium2 = 300    // FAB transitions, button press
    const val Medium3 = 350    // Card expansion, list items
    const val Medium4 = 400    // Bottom sheets, dialogs
    
    // Long durations - Large elements, complex transitions
    const val Long1 = 450      // Large cards, sheets
    const val Long2 = 500      // Screen transitions
    const val Long3 = 550      // Complex multi-element
    const val Long4 = 600      // Full-screen transitions
    
    // Extra long - Very large or multiple elements
    const val ExtraLong1 = 700
    const val ExtraLong2 = 800
    const val ExtraLong3 = 900
    const val ExtraLong4 = 1000
}

/**
 * Pre-configured animation specs for common use cases
 */
object MotionTokens {
    /**
     * Fade animations
     */
    fun <T> fadeIn(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Short4,
        easing = MotionEasing.StandardDecelerate
    )
    
    fun <T> fadeOut(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Short3,
        easing = MotionEasing.StandardAccelerate
    )
    
    /**
     * Small elements (chips, checkboxes, small cards)
     */
    fun <T> smallElementEnter(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Short4,
        easing = MotionEasing.StandardDecelerate
    )
    
    fun <T> smallElementExit(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Short3,
        easing = MotionEasing.StandardAccelerate
    )
    
    /**
     * Medium elements (cards, list items)
     */
    fun <T> mediumElementEnter(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Medium3,
        easing = MotionEasing.EmphasizedDecelerate
    )
    
    fun <T> mediumElementExit(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Medium2,
        easing = MotionEasing.EmphasizedAccelerate
    )
    
    /**
     * Large elements (bottom sheets, dialogs, screens)
     */
    fun <T> largeElementEnter(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Medium4,
        easing = MotionEasing.EmphasizedDecelerate
    )
    
    fun <T> largeElementExit(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Medium3,
        easing = MotionEasing.EmphasizedAccelerate
    )
    
    /**
     * Container transforms (expand/collapse)
     */
    fun <T> containerExpand(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Medium4,
        easing = MotionEasing.EmphasizedDecelerate
    )
    
    fun <T> containerCollapse(): AnimationSpec<T> = tween(
        durationMillis = MotionDuration.Medium3,
        easing = MotionEasing.EmphasizedAccelerate
    )
    
    /**
     * Spring animations for natural motion
     */
    fun <T> springBounce(): AnimationSpec<T> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMediumLow
    )
    
    fun <T> springSoft(): AnimationSpec<T> = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
}

/**
 * Common transition patterns
 */
object MotionTransitions {
    /**
     * Fade transition - Simple appear/disappear
     */
    fun fadeEnter(): EnterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = MotionDuration.Short4,
            easing = MotionEasing.StandardDecelerate
        )
    )
    
    fun fadeExit(): ExitTransition = fadeOut(
        animationSpec = tween(
            durationMillis = MotionDuration.Short3,
            easing = MotionEasing.StandardAccelerate
        )
    )
    
    /**
     * Slide horizontal - For navigation
     */
    fun slideInFromEnd(): EnterTransition = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(
            durationMillis = MotionDuration.Medium4,
            easing = MotionEasing.EmphasizedDecelerate
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = MotionDuration.Medium2,
            easing = MotionEasing.StandardDecelerate
        )
    )
    
    fun slideOutToStart(): ExitTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(
            durationMillis = MotionDuration.Medium3,
            easing = MotionEasing.EmphasizedAccelerate
        )
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = MotionDuration.Medium2,
            easing = MotionEasing.StandardAccelerate
        )
    )
    
    /**
     * Slide vertical - For bottom sheets, expanding content
     */
    fun slideInFromBottom(): EnterTransition = slideInVertically(
        initialOffsetY = { it },
        animationSpec = tween(
            durationMillis = MotionDuration.Medium4,
            easing = MotionEasing.EmphasizedDecelerate
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = MotionDuration.Medium2,
            easing = MotionEasing.StandardDecelerate
        )
    )
    
    fun slideOutToBottom(): ExitTransition = slideOutVertically(
        targetOffsetY = { it },
        animationSpec = tween(
            durationMillis = MotionDuration.Medium3,
            easing = MotionEasing.EmphasizedAccelerate
        )
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = MotionDuration.Medium2,
            easing = MotionEasing.StandardAccelerate
        )
    )
    
    /**
     * Expand/collapse - For collapsible sections
     */
    fun expandVertically(): EnterTransition = expandVertically(
        animationSpec = tween(
            durationMillis = MotionDuration.Medium3,
            easing = MotionEasing.EmphasizedDecelerate
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = MotionDuration.Short4,
            easing = MotionEasing.StandardDecelerate
        )
    )
    
    fun shrinkVertically(): ExitTransition = shrinkVertically(
        animationSpec = tween(
            durationMillis = MotionDuration.Medium2,
            easing = MotionEasing.EmphasizedAccelerate
        )
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = MotionDuration.Short3,
            easing = MotionEasing.StandardAccelerate
        )
    )
}

/**
 * Helper functions for common use cases
 */

/**
 * Get duration for element based on size
 */
fun getDurationForSize(small: Boolean = false, large: Boolean = false): Int = when {
    small -> MotionDuration.Short4
    large -> MotionDuration.Medium4
    else -> MotionDuration.Medium2
}

/**
 * Get easing for direction
 */
fun getEasingForDirection(entering: Boolean = true, emphasized: Boolean = false): Easing = when {
    emphasized && entering -> MotionEasing.EmphasizedDecelerate
    emphasized && !entering -> MotionEasing.EmphasizedAccelerate
    entering -> MotionEasing.StandardDecelerate
    else -> MotionEasing.StandardAccelerate
}
