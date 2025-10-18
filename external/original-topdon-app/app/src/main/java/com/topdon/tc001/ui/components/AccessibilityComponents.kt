package com.topdon.tc001.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/**
 * Ensures minimum 48dp touch target for accessibility
 */
fun Modifier.accessibleTouchTarget() =
    this.minimumInteractiveComponentSize()

/**
 * Minimum size for accessibility compliance
 */
val MinimumTouchTargetSize =
    48.dp

/**
 * Clickable with proper accessibility and ripple effect
 */
@Composable
fun Modifier.accessibleClickable(
    label: String? = null,
    role: Role? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier {
    val interactionSource =
        remember { MutableInteractionSource() }
    val ripple =
        rememberRipple()

    return this
        .semantics(
            mergeDescendants = true
        ) {
            label?.let {
                contentDescription =
                    it
            }
            role?.let {
                this.role =
                    it
            }
        }
        .clickable(
            interactionSource = interactionSource,
            indication = ripple,
            enabled = enabled,
            role = role,
            onClick = onClick
        )
}

/**
 * Helper for image content descriptions
 */
fun imageContentDescription(
    description: String,
    isDecorative: Boolean = false
): String? {
    return if (isDecorative) null else description
}
