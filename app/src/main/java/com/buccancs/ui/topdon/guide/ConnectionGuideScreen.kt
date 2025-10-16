package com.buccancs.ui.topdon.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionGuideRoute(
    onNavigateUp: () -> Unit,
    onConnectDevice: () -> Unit
) {
    ConnectionGuideScreen(
        onNavigateUp = onNavigateUp,
        onConnectDevice = onConnectDevice
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConnectionGuideScreen(
    onNavigateUp: () -> Unit,
    onConnectDevice: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Connection Guide",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
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
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "How to Connect Your Device",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Follow these steps to connect your thermal camera",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            ConnectionStep(
                number = 1,
                title = "Connect USB Cable",
                description = "Plug the USB cable into your thermal camera device"
            )

            Spacer(modifier = Modifier.height(24.dp))

            ConnectionStep(
                number = 2,
                title = "Connect to Phone",
                description = "Connect the other end of the USB cable to your phone using the USB-C adapter"
            )

            Spacer(modifier = Modifier.height(24.dp))

            ConnectionStep(
                number = 3,
                title = "Grant Permissions",
                description = "When prompted, allow USB access to the thermal camera"
            )

            Spacer(modifier = Modifier.height(48.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Usb,
                    contentDescription = "USB Connection",
                    modifier = Modifier.padding(48.dp),
                    tint = Color.White.copy(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onConnectDevice,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Connect Device",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
private fun ConnectionStep(
    number: Int,
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Step $number",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.8f),
            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight.times(1.2f)
        )
    }
}
