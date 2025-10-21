package com.buccancs.ui

import android.Manifest
import android.content.Intent
import android.hardware.usb.UsbConstants
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.WindowCompat
import com.buccancs.hardware.shimmer.ShimmerAutoLaunchPreferences
import com.buccancs.ui.base.DeviceHostActivity
import com.buccancs.ui.base.ShimmerAutoLaunchDecision
import com.buccancs.ui.base.ShimmerAutoLaunchEvent
import com.buccancs.ui.base.ShimmerAutoLaunchMode
import com.buccancs.ui.navigation.AppNavHost
import com.buccancs.ui.theme.BuccancsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@AndroidEntryPoint
class MainActivity :
    DeviceHostActivity() {

    override val autoRequestUsbPermission: Boolean =
        true

    private val shimmerAutoLaunchEvents =
        MutableSharedFlow<ShimmerAutoLaunchEvent>(extraBufferCapacity = 1)

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
                AppNavHost(
                    usbAttachmentEvents = usbAttachmentEvents,
                    shimmerAutoLaunchEvents = shimmerAutoLaunchEvents.asSharedFlow(),
                    onShimmerAutoLaunchDecision = ::handleShimmerAutoLaunchDecision
                )
            }
        }
        setContentView(
            composeView
        )

        handleShimmerIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleShimmerIntent(intent)
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
        device.vendorId in SUPPORTED_USB_VENDOR_IDS ||
            device.deviceClass == UsbConstants.USB_CLASS_VIDEO ||
            (device.deviceClass == UsbConstants.USB_CLASS_MISC &&
                device.deviceSubclass == USB_SUBCLASS_UVC_COLLECTION)

    companion object {
        private val SUPPORTED_USB_VENDOR_IDS =
            setOf(
                11261, // Legacy Topdon enumeration
                1003,  // Generic thermal cameras (Atmel)
                3034,  // Realtek bridge used by Topdon TC001
                1240,  // Seek Thermal
                13350, // Topdon vendor ID (0x3426)
                13428  // Infisense alternate enumeration
            )
        private const val USB_SUBCLASS_UVC_COLLECTION =
            2

        const val ACTION_HANDLE_SHIMMER_AUTO_LAUNCH =
            "com.buccancs.intent.SHIMMER_AUTO_LAUNCH"
        const val EXTRA_SHIMMER_DEVICE_ADDRESS =
            "extra_shimmer_device_address"
        const val EXTRA_SHIMMER_DEVICE_NAME =
            "extra_shimmer_device_name"
        const val EXTRA_SHIMMER_AUTO_LAUNCH_MODE =
            "extra_shimmer_auto_launch_mode"
    }

    private fun handleShimmerIntent(intent: Intent?) {
        if (intent?.action != ACTION_HANDLE_SHIMMER_AUTO_LAUNCH) {
            return
        }

        val address =
            intent.getStringExtra(EXTRA_SHIMMER_DEVICE_ADDRESS) ?: return
        val name =
            intent.getStringExtra(EXTRA_SHIMMER_DEVICE_NAME)
        val mode =
            intent.getStringExtra(EXTRA_SHIMMER_AUTO_LAUNCH_MODE)
                ?.let {
                    runCatching(ShimmerAutoLaunchMode::valueOf).getOrNull()
                } ?: ShimmerAutoLaunchMode.ASK

        shimmerAutoLaunchEvents.tryEmit(
            ShimmerAutoLaunchEvent(
                deviceAddress = address,
                deviceName = name,
                mode = mode
            )
        )

        intent.action =
            Intent.ACTION_MAIN
        intent.removeExtra(EXTRA_SHIMMER_DEVICE_ADDRESS)
        intent.removeExtra(EXTRA_SHIMMER_DEVICE_NAME)
        intent.removeExtra(EXTRA_SHIMMER_AUTO_LAUNCH_MODE)
    }

    private fun handleShimmerAutoLaunchDecision(
        event: ShimmerAutoLaunchEvent,
        decision: ShimmerAutoLaunchDecision
    ) {
        val mode =
            when (decision) {
                ShimmerAutoLaunchDecision.ALWAYS_ALLOW -> ShimmerAutoLaunchPreferences.Mode.ALWAYS
                ShimmerAutoLaunchDecision.ALLOW_ONCE -> ShimmerAutoLaunchPreferences.Mode.ASK
                ShimmerAutoLaunchDecision.DENY -> ShimmerAutoLaunchPreferences.Mode.NEVER
            }

        ShimmerAutoLaunchPreferences.setMode(
            this,
            event.deviceAddress,
            mode
        )
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
