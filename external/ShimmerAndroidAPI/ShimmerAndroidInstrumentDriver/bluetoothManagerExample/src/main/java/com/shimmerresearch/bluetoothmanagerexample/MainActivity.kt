package com.shimmerresearch.bluetoothmanagerexample

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.clj.fastble.BleManager
import com.shimmerresearch.android.Shimmer
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog
import com.shimmerresearch.android.guiUtilities.ShimmerDialogConfigurations
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.driver.ShimmerDevice
import com.shimmerresearch.exceptions.ShimmerException

class MainActivity :
    ComponentActivity() {

    companion object {
        const val LOG_TAG =
            "BluetoothManagerExample"
    }

    private val receiver =
        object :
            BroadcastReceiver() {
            override fun onReceive(
                context: Context,
                intent: Intent
            ) {
                val action =
                    intent.action
                if (BluetoothDevice.ACTION_FOUND == action) {
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(
                            BluetoothDevice.EXTRA_DEVICE
                        )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(
                                Manifest.permission.BLUETOOTH_CONNECT
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return
                        }
                    }
                    device?.let {
                        val deviceName =
                            it.name
                        val deviceHardwareAddress =
                            it.address
                    }
                }
            }
        }

    private var btManager: ShimmerBluetoothManagerAndroid? =
        null
    private var shimmerDevice: ShimmerDevice? =
        null
    private var shimmerBtAdd: String? =
        null
    private var preferredBtType: ShimmerBluetoothManagerAndroid.BT_TYPE? =
        null
    private val looper =
        Looper.myLooper()

    private val connectionState =
        mutableStateOf(
            "Disconnected"
        )
    private val statusMessage =
        mutableStateOf(
            "Please refer to the Android Logcat for signal data"
        )

    private val mHandler =
        object :
            Handler(
                Looper.getMainLooper()
            ) {
            override fun handleMessage(
                msg: Message
            ) {
                when (msg.what) {
                    ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET -> {
                        if (msg.obj is ObjectCluster) {
                            val objectCluster =
                                msg.obj as ObjectCluster

                            val allFormats =
                                objectCluster.getCollectionOfFormatClusters(
                                    Configuration.Shimmer3.ObjectClusterSensorName.TIMESTAMP
                                )
                            val timeStampCluster =
                                ObjectCluster.returnFormatCluster(
                                    allFormats,
                                    "CAL"
                                ) as? FormatCluster
                            val timeStampData =
                                timeStampCluster?.mData
                            Log.i(
                                LOG_TAG,
                                "Time Stamp: $timeStampData"
                            )

                            val accelFormats =
                                objectCluster.getCollectionOfFormatClusters(
                                    Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_X
                                )
                            val accelXCluster =
                                ObjectCluster.returnFormatCluster(
                                    accelFormats,
                                    "CAL"
                                ) as? FormatCluster
                            accelXCluster?.let {
                                val accelXData =
                                    it.mData
                                Log.i(
                                    LOG_TAG,
                                    "Accel LN X: $accelXData"
                                )
                            }
                        }
                    }

                    Shimmer.MESSAGE_TOAST -> {
                        Toast.makeText(
                            applicationContext,
                            msg.data.getString(
                                Shimmer.TOAST
                            ),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> {
                        var state: ShimmerBluetooth.BT_STATE? =
                            null
                        var macAddress =
                            ""

                        when (msg.obj) {
                            is ObjectCluster -> {
                                state =
                                    (msg.obj as ObjectCluster).mState
                                macAddress =
                                    (msg.obj as ObjectCluster).macAddress
                            }

                            is CallbackObject -> {
                                state =
                                    (msg.obj as CallbackObject).mState
                                macAddress =
                                    (msg.obj as CallbackObject).mBluetoothAddress
                            }
                        }

                        Log.d(
                            LOG_TAG,
                            "Shimmer state changed! Shimmer = $macAddress, new state = $state"
                        )

                        when (state) {
                            ShimmerBluetooth.BT_STATE.CONNECTED -> {
                                Log.i(
                                    LOG_TAG,
                                    "Shimmer [$macAddress] is now CONNECTED"
                                )
                                connectionState.value =
                                    "Connected"
                                shimmerBtAdd?.let {
                                    shimmerDevice =
                                        btManager?.getShimmerDeviceBtConnectedFromMac(
                                            it
                                        )
                                    if (shimmerDevice != null) {
                                        Log.i(
                                            LOG_TAG,
                                            "Got the ShimmerDevice!"
                                        )
                                    } else {
                                        Log.i(
                                            LOG_TAG,
                                            "ShimmerDevice returned is NULL!"
                                        )
                                    }
                                }
                            }

                            ShimmerBluetooth.BT_STATE.CONNECTING -> {
                                Log.i(
                                    LOG_TAG,
                                    "Shimmer [$macAddress] is CONNECTING"
                                )
                                connectionState.value =
                                    "Connecting"
                            }

                            ShimmerBluetooth.BT_STATE.STREAMING -> {
                                Log.i(
                                    LOG_TAG,
                                    "Shimmer [$macAddress] is now STREAMING"
                                )
                                connectionState.value =
                                    "Streaming"
                            }

                            ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING -> {
                                Log.i(
                                    LOG_TAG,
                                    "Shimmer [$macAddress] is now STREAMING AND LOGGING"
                                )
                                connectionState.value =
                                    "Streaming & Logging"
                            }

                            ShimmerBluetooth.BT_STATE.SDLOGGING -> {
                                Log.i(
                                    LOG_TAG,
                                    "Shimmer [$macAddress] is now SDLOGGING"
                                )
                                connectionState.value =
                                    "SD Logging"
                                if (shimmerDevice == null) {
                                    shimmerBtAdd?.let {
                                        shimmerDevice =
                                            btManager?.getShimmerDeviceBtConnectedFromMac(
                                                it
                                            )
                                        Log.i(
                                            LOG_TAG,
                                            "Got the ShimmerDevice!"
                                        )
                                    }
                                }
                            }

                            ShimmerBluetooth.BT_STATE.DISCONNECTED -> {
                                Log.i(
                                    LOG_TAG,
                                    "Shimmer [$macAddress] has been DISCONNECTED"
                                )
                                connectionState.value =
                                    "Disconnected"
                                shimmerDevice =
                                    null
                            }

                            else -> {}
                        }
                    }
                }
                super.handleMessage(
                    msg
                )
            }
        }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.BLUETOOTH_ADVERTISE,
                        Manifest.permission.BLUETOOTH_CONNECT,
                        Manifest.permission.BLUETOOTH_SCAN,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    0
                )
            } else {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    0
                )
            }
        }

        val filter =
            IntentFilter(
                BluetoothDevice.ACTION_FOUND
            )
        registerReceiver(
            receiver,
            filter
        )

        setContent {
            MaterialTheme {
                ShimmerManagerScreen()
            }
        }
    }

    @Composable
    fun ShimmerManagerScreen() {
        val state by connectionState
        val status by statusMessage

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Shimmer Bluetooth Manager",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )

                Text(
                    text = "Status: $state",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(
                        bottom = 16.dp
                    )
                )

                Button(
                    onClick = { openMenu() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Enable/Disable Sensors"
                    )
                }

                Button(
                    onClick = { openConfigMenu() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Configuration"
                    )
                }

                Spacer(
                    modifier = Modifier.height(
                        16.dp
                    )
                )

                Text(
                    text = status,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                )

                Button(
                    onClick = { startStreaming() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Start Streaming"
                    )
                }

                Button(
                    onClick = { stopStreaming() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Stop Streaming"
                    )
                }

                Button(
                    onClick = { startSDLogging() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Start SD Logging"
                    )
                }

                Button(
                    onClick = { stopSDLogging() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Stop SD Logging"
                    )
                }

                Button(
                    onClick = { connectDevice() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Connect"
                    )
                }

                Button(
                    onClick = { disconnectDevice() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        "Disconnect"
                    )
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
        val allPermissionsGranted =
            grantResults.all { it == PackageManager.PERMISSION_GRANTED }

        if (!allPermissionsGranted) {
            Toast.makeText(
                this,
                "Please allow all requested permissions",
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            BleManager.getInstance()
                .init(
                    application
                )
            try {
                btManager =
                    ShimmerBluetoothManagerAndroid(
                        this,
                        mHandler
                    )
            } catch (e: Exception) {
                Log.e(
                    LOG_TAG,
                    "Couldn't create ShimmerBluetoothManagerAndroid. Error: $e"
                )
                val enableBtIntent =
                    Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE
                    )
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                startActivityForResult(
                    enableBtIntent,
                    1
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(
            receiver
        )
    }

    override fun onStop() {
        shimmerDevice?.let {
            when {
                it.isSDLogging -> {
                    it.stopSDLogging()
                    Log.d(
                        LOG_TAG,
                        "Stopped Shimmer Logging"
                    )
                }

                it.isStreaming -> {
                    try {
                        it.stopStreaming()
                    } catch (e: ShimmerException) {
                        e.printStackTrace()
                    }
                    Log.d(
                        LOG_TAG,
                        "Stopped Shimmer Streaming"
                    )
                }

                else -> {
                    it.stopStreamingAndLogging()
                    Log.d(
                        LOG_TAG,
                        "Stopped Shimmer Streaming and Logging"
                    )
                }
            }
        }
        btManager?.disconnectAllDevices()
        Log.i(
            LOG_TAG,
            "Shimmer DISCONNECTED"
        )
        super.onStop()
    }

    private fun stopStreaming() {
        shimmerDevice?.let {
            try {
                it.stopStreaming()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }
    }

    private fun startStreaming() {
        shimmerDevice?.let {
            try {
                it.startStreaming()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }
    }

    private fun openConfigMenu() {
        shimmerDevice?.let { device ->
            if (!device.isStreaming && !device.isSDLogging) {
                ShimmerDialogConfigurations.buildShimmerConfigOptions(
                    device,
                    this,
                    btManager
                )
            } else {
                Log.e(
                    LOG_TAG,
                    "Cannot open menu! Shimmer device is STREAMING AND/OR LOGGING"
                )
                Toast.makeText(
                    this,
                    "Cannot open menu! Shimmer device is STREAMING AND/OR LOGGING",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
            ?: run {
                Log.e(
                    LOG_TAG,
                    "Cannot open menu! Shimmer device is not connected"
                )
                Toast.makeText(
                    this,
                    "Cannot open menu! Shimmer device is not connected",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

    private fun openMenu() {
        shimmerDevice?.let { device ->
            if (!device.isStreaming && !device.isSDLogging) {
                ShimmerDialogConfigurations.buildShimmerSensorEnableDetails(
                    device,
                    this,
                    btManager
                )
            } else {
                Log.e(
                    LOG_TAG,
                    "Cannot open menu! Shimmer device is STREAMING AND/OR LOGGING"
                )
                Toast.makeText(
                    this,
                    "Cannot open menu! Shimmer device is STREAMING AND/OR LOGGING",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
            ?: run {
                Log.e(
                    LOG_TAG,
                    "Cannot open menu! Shimmer device is not connected"
                )
                Toast.makeText(
                    this,
                    "Cannot open menu! Shimmer device is not connected",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

    private fun connectDevice() {
        val intent =
            Intent(
                applicationContext,
                ShimmerBluetoothDialog::class.java
            )
        startActivityForResult(
            intent,
            ShimmerBluetoothDialog.REQUEST_CONNECT_SHIMMER
        )
    }

    private fun disconnectDevice() {
        shimmerDevice?.let {
            try {
                (it as ShimmerBluetooth).disconnect()
            } catch (e: ShimmerException) {
                throw RuntimeException(
                    e
                )
            }
        }
    }

    private fun startSDLogging() {
        shimmerDevice?.let {
            (it as ShimmerBluetooth).writeConfigTime(
                System.currentTimeMillis()
            )
            it.startSDLogging()
        }
    }

    private fun stopSDLogging() {
        shimmerDevice?.stopSDLogging()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                if (btManager == null) {
                    try {
                        btManager =
                            ShimmerBluetoothManagerAndroid(
                                this,
                                mHandler
                            )
                    } catch (e: Exception) {
                        Log.e(
                            LOG_TAG,
                            "Couldn't create ShimmerBluetoothManagerAndroid. Error: $e"
                        )
                    }
                }
                btManager?.disconnectAllDevices()
                shimmerDevice =
                    null
                showBtTypeConnectionOption()
                val macAdd =
                    data?.getStringExtra(
                        ShimmerBluetoothDialog.EXTRA_DEVICE_ADDRESS
                    )
                val deviceName =
                    data?.getStringExtra(
                        ShimmerBluetoothDialog.EXTRA_DEVICE_NAME
                    )
                macAdd?.let {
                    btManager?.connectShimmerThroughBTAddress(
                        it,
                        deviceName,
                        preferredBtType
                    )
                    shimmerBtAdd =
                        it
                }
            }
        }
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )
    }

    private fun showBtTypeConnectionOption() {
        val alertDialog =
            AlertDialog.Builder(
                this
            )
                .create()
        alertDialog.setCancelable(
            false
        )
        alertDialog.setMessage(
            "Choose preferred Bluetooth type"
        )
        alertDialog.setButton(
            Dialog.BUTTON_POSITIVE,
            "BT CLASSIC"
        ) { dialog, _ ->
            preferredBtType =
                ShimmerBluetoothManagerAndroid.BT_TYPE.BT_CLASSIC
            looper?.quit()
        }
        alertDialog.setButton(
            Dialog.BUTTON_NEGATIVE,
            "BLE"
        ) { dialog, _ ->
            preferredBtType =
                ShimmerBluetoothManagerAndroid.BT_TYPE.BLE
            looper?.quit()
        }
        alertDialog.show()
        try {
            looper?.loop()
        } catch (e: RuntimeException) {
        }
    }
}
