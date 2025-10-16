package com.topdon.tc001

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.topdon.lib.core.config.ExtraKeyConfig
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.tools.DeviceTools
import com.topdon.tc001.ui.theme.TopdonTheme

class DeviceTypeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TopdonTheme {
                DeviceTypeScreen(
                    onNavigateUp = { finish() },
                    onDeviceSelected = { deviceType ->
                        when (deviceType) {
                            IRDeviceType.TS004 -> {
                                ARouter.getInstance()
                                    .build(RouterConfig.IR_DEVICE_ADD)
                                    .withBoolean("isTS004", true)
                                    .navigation(this)
                            }

                            IRDeviceType.TC007 -> {
                                ARouter.getInstance()
                                    .build(RouterConfig.IR_DEVICE_ADD)
                                    .withBoolean("isTS004", false)
                                    .navigation(this)
                            }

                            else -> {
                                ARouter.getInstance()
                                    .build(RouterConfig.IR_MAIN)
                                    .withBoolean(ExtraKeyConfig.IS_TC007, false)
                                    .navigation(this)
                                if (DeviceTools.isConnect()) {
                                    finish()
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    enum class IRDeviceType(val deviceName: String, val isLine: Boolean) {
        TC001("TC001", true),
        TC001_PLUS("TC001 Plus", true),
        TC002C_DUO("TC002C Duo", true),
        TC007("TC007", false),
        TS001("TS001", true),
        TS004("TS004", false)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeviceTypeScreen(
    onNavigateUp: () -> Unit,
    onDeviceSelected: (DeviceTypeActivity.IRDeviceType) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Device Type") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
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
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DeviceSection(
                title = "USB Connection",
                devices = listOf(
                    DeviceTypeActivity.IRDeviceType.TS001 to R.drawable.ic_device_type_ts001,
                    DeviceTypeActivity.IRDeviceType.TC001 to R.drawable.ic_device_type_tc001
                ),
                secondRow = listOf(
                    DeviceTypeActivity.IRDeviceType.TC001_PLUS to R.drawable.ic_device_type_tc001_plus,
                    DeviceTypeActivity.IRDeviceType.TC002C_DUO to R.drawable.ic_device_type_tc001_plus
                ),
                onDeviceSelected = onDeviceSelected
            )

            DeviceSection(
                title = "WiFi Connection",
                devices = listOf(
                    DeviceTypeActivity.IRDeviceType.TS004 to R.drawable.ic_device_type_ts004
                ),
                secondRow = emptyList(),
                onDeviceSelected = onDeviceSelected
            )
        }
    }
}

@Composable
private fun DeviceSection(
    title: String,
    devices: List<Pair<DeviceTypeActivity.IRDeviceType, Int>>,
    secondRow: List<Pair<DeviceTypeActivity.IRDeviceType, Int>>,
    onDeviceSelected: (DeviceTypeActivity.IRDeviceType) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            devices.forEach { (deviceType, iconRes) ->
                DeviceCard(
                    deviceType = deviceType,
                    iconRes = iconRes,
                    onSelected = { onDeviceSelected(deviceType) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (secondRow.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                secondRow.forEach { (deviceType, iconRes) ->
                    DeviceCard(
                        deviceType = deviceType,
                        iconRes = iconRes,
                        onSelected = { onDeviceSelected(deviceType) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun DeviceCard(
    deviceType: DeviceTypeActivity.IRDeviceType,
    iconRes: Int,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable(onClick = onSelected),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = deviceType.deviceName,
                modifier = Modifier.size(80.dp)
            )

            Text(
                text = deviceType.deviceName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}