package com.buccancs.ui.components.topdon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.buccancs.ui.theme.CornerRadius
import com.buccancs.ui.theme.Spacing
import com.buccancs.ui.theme.topdon.TopdonColors
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Topdon-styled filled text field
 */
@Composable
fun TopdonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE
) {
    Column(
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            label = label?.let {
                {
                    Text(
                        it
                    )
                }
            },
            placeholder = placeholder?.let {
                {
                    Text(
                        it
                    )
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            shape = RoundedCornerShape(
                CornerRadius.Small
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = TopdonColors.TextTertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                errorIndicatorColor = TopdonColors.SelectRed,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorLabelColor = TopdonColors.SelectRed,
                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = TopdonColors.SelectRed
            )
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = TopdonColors.SelectRed,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    start = Spacing.Large,
                    top = Spacing.ExtraSmall
                )
            )
        }
    }
}

/**
 * Topdon-styled outlined text field
 */
@Composable
fun TopdonOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            label = label?.let {
                {
                    Text(
                        it
                    )
                }
            },
            placeholder = placeholder?.let {
                {
                    Text(
                        it
                    )
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            shape = RoundedCornerShape(
                CornerRadius.Small
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = TopdonColors.TextTertiary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                errorBorderColor = TopdonColors.SelectRed,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorLabelColor = TopdonColors.SelectRed,
                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = TopdonColors.SelectRed
            )
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = TopdonColors.SelectRed,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    start = Spacing.Large,
                    top = Spacing.ExtraSmall
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF16131E
)
@Composable
private fun TopdonTextFieldPreview() {
    TopdonTheme {
        var text by remember {
            mutableStateOf(
                ""
            )
        }
        Column(
            modifier = Modifier.padding(
                Spacing.Large
            )
        ) {
            TopdonTextField(
                value = text,
                onValueChange = {
                    text =
                        it
                },
                label = "Device Name",
                placeholder = "Enter device name"
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF16131E
)
@Composable
private fun TopdonOutlinedTextFieldPreview() {
    TopdonTheme {
        var text by remember {
            mutableStateOf(
                ""
            )
        }
        Column(
            modifier = Modifier.padding(
                Spacing.Large
            )
        ) {
            TopdonOutlinedTextField(
                value = text,
                onValueChange = {
                    text =
                        it
                },
                label = "Temperature",
                placeholder = "25.0",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF16131E
)
@Composable
private fun TopdonTextFieldErrorPreview() {
    TopdonTheme {
        Column(
            modifier = Modifier.padding(
                Spacing.Large
            )
        ) {
            TopdonOutlinedTextField(
                value = "Invalid",
                onValueChange = {},
                label = "Emissivity",
                isError = true,
                errorMessage = "Value must be between 0.1 and 1.0"
            )
        }
    }
}
