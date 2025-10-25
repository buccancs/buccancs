package com.buccancs.ui.components.shimmer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerAutoLaunchDialog(
    deviceName: String?,
    onAllow: (Boolean) -> Unit,
    onDeny: () -> Unit
) {
    var alwaysOpen by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDeny,
        title = {
            Text(
                text = "Open for ${deviceName ?: "Shimmer device"}?",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column {
                Text(
                    text = "Buccancs can open automatically whenever this Shimmer sensor connects.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = alwaysOpen,
                        onCheckedChange = { checked -> alwaysOpen = checked }
                    )
                    Text(
                        text = "Always open when detected",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onAllow(alwaysOpen) }
            ) {
                Text("Allow")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDeny
            ) {
                Text("Don't allow")
            }
        }
    )
}
