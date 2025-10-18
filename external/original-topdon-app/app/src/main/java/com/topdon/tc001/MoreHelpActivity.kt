package com.topdon.tc001

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.utils.Constants
import com.topdon.tc001.ui.theme.TopdonTheme

@Route(
    path = RouterConfig.IR_MORE_HELP
)
class MoreHelpActivity :
    ComponentActivity() {
    private lateinit var wifiManager: WifiManager

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        wifiManager =
            getSystemService(
                Context.WIFI_SERVICE
            ) as WifiManager
        val connectionType =
            intent.getIntExtra(
                Constants.SETTING_CONNECTION_TYPE,
                0
            )
        val isConnectionGuide =
            connectionType == Constants.SETTING_CONNECTION

        setContent {
            TopdonTheme {
                MoreHelpScreen(
                    isConnectionGuide = isConnectionGuide,
                    onNavigateUp = { finish() },
                    onOpenWifiSettings = { openWifiSettings() }
                )
            }
        }
    }

    private fun openWifiSettings() {
        if (!wifiManager.isWifiEnabled) {
            showWifiDialog()
        } else {
            launchWifiSettings()
        }
    }

    private fun showWifiDialog() {
        androidx.appcompat.app.AlertDialog.Builder(
            this
        )
            .setTitle(
                getString(
                    R.string.app_tip
                )
            )
            .setMessage(
                R.string.ts004_wlan_tips
            )
            .setPositiveButton(
                R.string.app_open
            ) { _, _ ->
                launchWifiSettings()
            }
            .setNegativeButton(
                R.string.app_cancel,
                null
            )
            .setCancelable(
                true
            )
            .show()
    }

    private fun launchWifiSettings() {
        if (Build.VERSION.SDK_INT < 29) {
            wifiManager.isWifiEnabled =
                true
        } else {
            var wifiIntent =
                Intent(
                    Settings.Panel.ACTION_WIFI
                )
            if (wifiIntent.resolveActivity(
                    packageManager
                ) == null
            ) {
                wifiIntent =
                    Intent(
                        Settings.ACTION_WIFI_SETTINGS
                    )
            }
            if (wifiIntent.resolveActivity(
                    packageManager
                ) != null
            ) {
                startActivity(
                    wifiIntent
                )
            }
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun MoreHelpScreen(
    isConnectionGuide: Boolean,
    onNavigateUp: () -> Unit,
    onOpenWifiSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isConnectionGuide) "Connection Guide" else "Help"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    padding
                )
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                16.dp
            )
        ) {
            if (isConnectionGuide) {
                ConnectionGuideContent()
            } else {
                DisconnectionHelpContent(
                    onOpenWifiSettings
                )
            }
        }
    }
}

@Composable
private fun ConnectionGuideContent() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                12.dp
            )
        ) {
            Text(
                text = "Device Connection Guide",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            HelpItem(
                number = "1",
                title = "Power On Device",
                description = "Turn on your thermal camera device"
            )

            HelpItem(
                number = "2",
                title = "Enable USB Connection",
                description = "Connect device via USB cable to your phone"
            )

            HelpItem(
                number = "3",
                title = "Grant Permissions",
                description = "Allow USB device access when prompted"
            )

            HelpItem(
                number = "4",
                title = "Start Using",
                description = "Device will automatically connect and you can start using thermal imaging features"
            )
        }
    }
}

@Composable
private fun DisconnectionHelpContent(
    onOpenWifiSettings: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp
                )
            ) {
                Text(
                    text = "Device Disconnected",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
                Text(
                    text = "Your device has been disconnected. Please follow these troubleshooting steps:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    12.dp
                )
            ) {
                HelpItem(
                    number = "1",
                    title = "Check USB Connection",
                    description = "Ensure USB cable is properly connected"
                )

                HelpItem(
                    number = "2",
                    title = "Restart Device",
                    description = "Power off and on your thermal camera"
                )

                HelpItem(
                    number = "3",
                    title = "Check WiFi Settings",
                    description = "For WiFi connected devices, verify network connection"
                )

                Button(
                    onClick = onOpenWifiSettings,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Open WiFi Settings"
                    )
                }
            }
        }
    }
}

@Composable
private fun HelpItem(
    number: String,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            12.dp
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(
                32.dp
            )
        ) {
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(
                    text = number,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Column(
            modifier = Modifier.weight(
                1f
            )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}