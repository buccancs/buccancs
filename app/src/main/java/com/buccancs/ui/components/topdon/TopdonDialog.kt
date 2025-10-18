package com.buccancs.ui.components.topdon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.buccancs.ui.theme.topdon.TopdonTheme

/**
 * Topdon-styled Material 3 alert dialog
 * Migrated from original TipDialog pattern
 */
@Composable
fun TopdonAlertDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "OK",
    dismissText: String? = null,
    onConfirm: () -> Unit = onDismiss,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TopdonTextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text(confirmText)
            }
        },
        dismissButton = dismissText?.let {
            {
                TopdonTextButton(onClick = onDismiss) {
                    Text(it)
                }
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        properties = properties
    )
}

/**
 * Topdon-styled custom dialog with arbitrary content
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopdonDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
        properties = properties
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            content()
        }
    }
}

/**
 * Connection tip dialog variant
 * Matches original app's device connection prompts
 */
@Composable
fun TopdonConnectionDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "Connect",
    dismissText: String = "Cancel"
) {
    TopdonAlertDialog(
        title = "Device Connection",
        message = message,
        confirmText = confirmText,
        dismissText = dismissText,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        modifier = modifier
    )
}

/**
 * Permission rationale dialog
 */
@Composable
fun TopdonPermissionDialog(
    permission: String,
    rationale: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopdonAlertDialog(
        title = "Permission Required",
        message = "This app requires $permission permission. $rationale",
        confirmText = "Grant",
        dismissText = "Deny",
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        modifier = modifier
    )
}

/**
 * Loading dialog with custom content
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopdonLoadingDialog(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(24.dp)
            ) {
                androidx.compose.material3.CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun TopdonAlertDialogPreview() {
    TopdonTheme {
        TopdonAlertDialog(
            title = "Device Not Found",
            message = "Please connect your TC001 device via USB and try again.",
            confirmText = "OK",
            onDismiss = {}
        )
    }
}

@Preview
@Composable
private fun TopdonConnectionDialogPreview() {
    TopdonTheme {
        TopdonConnectionDialog(
            message = "USB device detected. Connect to TC001?",
            onConfirm = {},
            onDismiss = {}
        )
    }
}

@Preview
@Composable
private fun TopdonLoadingDialogPreview() {
    TopdonTheme {
        TopdonLoadingDialog(
            message = "Connecting to device...",
            onDismiss = {}
        )
    }
}
