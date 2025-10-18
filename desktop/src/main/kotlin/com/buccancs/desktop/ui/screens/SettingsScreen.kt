package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.buccancs.desktop.ui.components.BuccancsCard
import com.buccancs.desktop.ui.components.PrimaryButton
import com.buccancs.desktop.ui.components.ScreenHeader
import com.buccancs.desktop.ui.components.SecondaryButton
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * Settings screen for configuring the desktop orchestrator
 */
@Composable
fun SettingsScreen() {
    var serverPort by remember { mutableStateOf("50051") }
    var dataPath by remember { mutableStateOf("~/.buccancs/sessions") }
    var darkTheme by remember { mutableStateOf(true) }
    var autoDiscovery by remember { mutableStateOf(true) }
    var logLevel by remember { mutableStateOf("INFO") }
    var retentionDays by remember { mutableStateOf("30") }
    var maxStorageGB by remember { mutableStateOf("50") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.Large),
        verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
    ) {
        ScreenHeader(
            title = "Settings",
            subtitle = "Configure server, storage, appearance and logging"
        )

        // Server Configuration
        BuccancsCard(
            title = "Server Configuration",
            subtitle = "gRPC server and network settings"
        ) {
            OutlinedTextField(
                value = serverPort,
                onValueChange = { serverPort = it },
                label = { Text("gRPC Port") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.NetworkCheck, "Port") },
                singleLine = true
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Auto-discovery (mDNS)", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "Automatically discover Android agents",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = autoDiscovery,
                    onCheckedChange = { autoDiscovery = it }
                )
            }
        }

        // Storage Configuration
        BuccancsCard(
            title = "Storage Configuration",
            subtitle = "Data paths and retention policies"
        ) {
            OutlinedTextField(
                value = dataPath,
                onValueChange = { dataPath = it },
                label = { Text("Data Directory") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Folder, "Path") },
                trailingIcon = {
                    IconButton(onClick = { /* Browse */ }) {
                        Icon(Icons.Default.FolderOpen, "Browse")
                    }
                },
                singleLine = true
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Medium)
            ) {
                OutlinedTextField(
                    value = retentionDays,
                    onValueChange = { retentionDays = it },
                    label = { Text("Retention (days)") },
                    modifier = Modifier.weight(1f),
                    leadingIcon = { Icon(Icons.Default.CalendarToday, "Days") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = maxStorageGB,
                    onValueChange = { maxStorageGB = it },
                    label = { Text("Max Storage (GB)") },
                    modifier = Modifier.weight(1f),
                    leadingIcon = { Icon(Icons.Default.Storage, "Storage") },
                    singleLine = true
                )
            }
        }

        // Appearance
        BuccancsCard(
            title = "Appearance",
            subtitle = "Theme and display preferences"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Dark Theme", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "Use dark colour scheme",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = darkTheme,
                    onCheckedChange = { darkTheme = it }
                )
            }
        }

        // Logging
        BuccancsCard(
            title = "Logging",
            subtitle = "Debug and diagnostic settings"
        ) {
            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = logLevel,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Log Level") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(
                            ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                            enabled = true
                        ),
                    leadingIcon = { Icon(Icons.Default.BugReport, "Log") }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("TRACE", "DEBUG", "INFO", "WARN", "ERROR").forEach { level ->
                        DropdownMenuItem(
                            text = { Text(level) },
                            onClick = {
                                logLevel = level
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        // Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            PrimaryButton(
                text = "Save Changes",
                onClick = { /* Save */ },
                modifier = Modifier.weight(1f)
            )

            SecondaryButton(
                text = "Reset to Defaults",
                onClick = { /* Reset */ },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    BuccancsTheme {
        Surface {
            SettingsScreen()
        }
    }
}
