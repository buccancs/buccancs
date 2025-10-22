package com.buccancs.hardware.shimmer

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.buccancs.ui.MainActivity
import com.buccancs.ui.base.ShimmerAutoLaunchMode

/**
 * Listens for Shimmer Bluetooth connections so the app can be surfaced (or auto-launched) without
 * requiring the user to manually open the device list first.
 */
class ShimmerAutoLaunchReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        if (intent.action != BluetoothDevice.ACTION_ACL_CONNECTED) {
            return
        }

        if (
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val device =
            intent.getBluetoothDevice() ?: return

        val address =
            runCatching {
                device.address
            }.getOrNull() ?: return

        val mode =
            ShimmerAutoLaunchPreferences.getMode(
                context,
                address
            )

        if (mode == ShimmerAutoLaunchPreferences.Mode.NEVER) {
            return
        }

        if (mode == ShimmerAutoLaunchPreferences.Mode.ASK && !device.isLikelyShimmer()) {
            // Don't interrupt the user for unrelated headsets or peripherals.
            return
        }

        val name =
            runCatching {
                device.name
            }.getOrNull()

        val launchIntent =
            Intent(
                context,
                MainActivity::class.java
            ).apply {
                action =
                    MainActivity.ACTION_HANDLE_SHIMMER_AUTO_LAUNCH
                putExtra(
                    MainActivity.EXTRA_SHIMMER_DEVICE_ADDRESS,
                    address
                )
                if (!name.isNullOrBlank()) {
                    putExtra(
                        MainActivity.EXTRA_SHIMMER_DEVICE_NAME,
                        name
                    )
                }
                putExtra(
                    MainActivity.EXTRA_SHIMMER_AUTO_LAUNCH_MODE,
                    mode.toLaunchMode().name
                )
                addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_SINGLE_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                )
            }

        ContextCompat.startActivity(
            context,
            launchIntent,
            null
        )
    }

    private fun Intent.getBluetoothDevice(): BluetoothDevice? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getParcelableExtra(
                BluetoothDevice.EXTRA_DEVICE,
                BluetoothDevice::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
        }

    private fun BluetoothDevice.isLikelyShimmer(): Boolean =
        runCatching {
            name
        }.getOrNull()
            ?.contains(
                "shimmer",
                ignoreCase = true
            ) == true

    private fun ShimmerAutoLaunchPreferences.Mode.toLaunchMode(): ShimmerAutoLaunchMode =
        when (this) {
            ShimmerAutoLaunchPreferences.Mode.ASK -> ShimmerAutoLaunchMode.ASK
            ShimmerAutoLaunchPreferences.Mode.ALWAYS -> ShimmerAutoLaunchMode.ALWAYS
            ShimmerAutoLaunchPreferences.Mode.NEVER -> ShimmerAutoLaunchMode.ASK
        }
}
