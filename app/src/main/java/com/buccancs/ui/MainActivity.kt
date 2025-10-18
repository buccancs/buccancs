package com.buccancs.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.WindowCompat
import android.util.Log
import com.buccancs.ui.base.DeviceHostActivity
import com.buccancs.ui.base.DeviceHostActivity.PermissionEducation
import com.buccancs.ui.navigation.AppNavHost
import com.buccancs.ui.theme.BuccancsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    DeviceHostActivity() {

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

        val composeView =
            ComposeView(
                this
            ).apply {
                setViewCompositionStrategy(
                    ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                        this@MainActivity
                    )
                )
                setTestTagsAsResourceIdCompat(
                    true
                )
            }
        composeView.setContent {
            BuccancsTheme {
                AppNavHost(usbAttachmentEvents = usbAttachmentEvents)
            }
        }
        setContentView(
            composeView
        )
    }

    override val permissionEducation: List<PermissionEducation> =
        listOf(
            PermissionEducation(
                Manifest.permission.BLUETOOTH_SCAN,
                heading = "Bluetooth Scanning",
                detail = "Required to discover nearby biometric sensors and thermal cameras."
            ),
            PermissionEducation(
                Manifest.permission.BLUETOOTH_CONNECT,
                heading = "Bluetooth Connection",
                detail = "Needed to pair and maintain live telemetry with supported wearables."
            ),
            PermissionEducation(
                Manifest.permission.ACCESS_FINE_LOCATION,
                heading = "Location Access",
                detail = "Android links Bluetooth discovery to location in order to protect privacy."
            ),
            PermissionEducation(
                Manifest.permission.CAMERA,
                heading = "Camera Access",
                detail = "Enables RGB previews and thermal capture alignment."
            ),
            PermissionEducation(
                Manifest.permission.RECORD_AUDIO,
                heading = "Microphone Access",
                detail = "Audio samples are captured alongside thermal data when available."
            )
        ) + if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                PermissionEducation(
                    Manifest.permission.POST_NOTIFICATIONS,
                    heading = "Notifications",
                    detail = "Allows the agent to surface device status and alerts even when minimised."
                )
            )
        } else {
            emptyList()
        }

    override fun isRecognisedDevice(device: android.hardware.usb.UsbDevice): Boolean =
        when (device.vendorId) {
            11261, // TopDon
            1003,  // Generic thermal cameras
            3034,  // FLIR
            1240 -> true // Seek Thermal
            else -> false
        }
}

private fun ComposeView.setTestTagsAsResourceIdCompat(
    enable: Boolean
) {
    runCatching {
        val method =
            ComposeView::class.java.getMethod(
                "setTestTagsAsResourceId",
                java.lang.Boolean.TYPE
            )
        method.invoke(
            this,
            enable
        )
    }.onSuccess {
        Log.d(
            TAG,
            "setTestTagsAsResourceIdCompat invoked (enable=$enable)"
        )
    }
        .onFailure { throwable ->
            Log.w(
                TAG,
                "Failed to set test tags as resource ids",
                throwable
            )
        }
}

private const val TAG =
    "MainActivity"
