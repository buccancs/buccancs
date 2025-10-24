package com.buccancs.ui.components.topdon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.buccancs.ui.theme.CornerRadius
import com.buccancs.ui.theme.Dimensions
import com.buccancs.ui.theme.Spacing
import com.buccancs.ui.theme.topdon.TopdonColors
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Topdon-styled filled button with theme accent colour
 * Based on original Topdon UI design patterns
 */
@Composable
fun TopdonButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(
            Dimensions.TouchTargetMinimum
        ),
        enabled = enabled,
        shape = RoundedCornerShape(
            CornerRadius.ExtraLarge
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.outlineVariant,
            disabledContentColor = TopdonColors.TextTertiary
        ),
        contentPadding = PaddingValues(
            horizontal = Spacing.Large,
            vertical = Spacing.SmallMedium
        ),
        content = content
    )
}

/**
 * Topdon-styled outlined button
 */
@Composable
fun TopdonOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(
            Dimensions.TouchTargetMinimum
        ),
        enabled = enabled,
        shape = RoundedCornerShape(
            CornerRadius.ExtraLarge
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = TopdonColors.TextTertiary
        ),
        border = ButtonDefaults.outlinedButtonBorder(
            enabled = enabled
        )
            .copy(
                brush = androidx.compose.ui.graphics.SolidColor(
                    if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                )
            ),
        contentPadding = PaddingValues(
            horizontal = Spacing.Large,
            vertical = Spacing.SmallMedium
        ),
        content = content
    )
}

/**
 * Topdon-styled text button
 */
@Composable
fun TopdonTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = TopdonColors.TextTertiary
        ),
        content = content
    )
}

/**
 * Topdon-styled icon button
 */
@Composable
fun TopdonIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = MaterialTheme.colorScheme.onSurface
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = tint,
            disabledContentColor = TopdonColors.TextTertiary
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF16131E
)
@Composable
private fun TopdonButtonPreview() {
    TopdonTheme {
        TopdonButton(
            onClick = {}) {
            Text(
                "Connect Device"
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF16131E
)
@Composable
private fun TopdonOutlinedButtonPreview() {
    TopdonTheme {
        TopdonOutlinedButton(
            onClick = {}) {
            Text(
                "Disconnect"
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF16131E
)
@Composable
private fun TopdonTextButtonPreview() {
    TopdonTheme {
        TopdonTextButton(
            onClick = {}) {
            Text(
                "Refresh"
            )
        }
    }
}
