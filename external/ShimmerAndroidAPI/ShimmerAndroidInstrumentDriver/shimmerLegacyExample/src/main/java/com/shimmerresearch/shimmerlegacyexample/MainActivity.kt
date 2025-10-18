package com.shimmerresearch.shimmerlegacyexample

import android.app.Activity
import android.content.Intent
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
import com.shimmerresearch.android.Shimmer
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.exceptions.ShimmerException

class MainActivity : ComponentActivity() {

    companion object {
        const val LOG_TAG = "ShimmerLegacyExample"
    }

    private var btManager: ShimmerBluetoothManagerAndroid? = null
    private var shimmerBtAdd = ""
    private var mFirstTimeConnection = true

    private val connectionState = mutableStateOf("Disconnected")
    private val deviceAddress = mutableStateOf("Not connected")

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> {
                    var state: ShimmerBluetooth.BT_STATE? = null
                    var macAddress = ""
                    var shimmerName = ""

                    when (msg.obj) {
                        is ObjectCluster -> {
                            state = (msg.obj as ObjectCluster).mState
                            macAddress = (msg.obj as ObjectCluster).macAddress
                            shimmerName = (msg.obj as ObjectCluster).shimmerName
                        }

                        is CallbackObject -> {
                            state = (msg.obj as CallbackObject).mState
                            macAddress = (msg.obj as CallbackObject).mBluetoothAddress
                            shimmerName = (msg.obj as ObjectCluster).shimmerName
                        }
                    }

                    when (state) {
                        ShimmerBluetooth.BT_STATE.CONNECTING -> {
                            Log.i(LOG_TAG, "Connecting to device: $macAddress")
                            connectionState.value = "Connecting"
                            deviceAddress.value = macAddress
                        }

                        ShimmerBluetooth.BT_STATE.CONNECTED -> {
                            Log.i(LOG_TAG, "Device connected: $macAddress")
                            connectionState.value = "Connected"
                            deviceAddress.value = macAddress
                            if (mFirstTimeConnection) {
                                val shimmer =
                                    btManager?.getShimmerDeviceBtConnectedFromMac(macAddress) as? Shimmer
                                (shimmer as? ShimmerBluetooth)?.writeEnabledSensors(ShimmerBluetooth.SENSOR_ACCEL)
                            }
                            mFirstTimeConnection = false
                        }

                        ShimmerBluetooth.BT_STATE.STREAMING -> {
                            Log.i(LOG_TAG, "Device: $macAddress now streaming")
                            connectionState.value = "Streaming"
                        }

                        ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING -> {
                            connectionState.value = "Streaming & Logging"
                        }

                        ShimmerBluetooth.BT_STATE.DISCONNECTED -> {
                            Log.i(LOG_TAG, "Device disconnected: $macAddress")
                            connectionState.value = "Disconnected"
                            deviceAddress.value = "Not connected"
                        }

                        else -> {}
                    }
                }

                ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET -> {
                    if (msg.obj is ObjectCluster) {
                        val objectCluster = msg.obj as ObjectCluster

                        val allFormats = objectCluster.getCollectionOfFormatClusters(
                            Configuration.Shimmer2.ObjectClusterSensorName.TIMESTAMP
                        )
                        val timeStampCluster =
                            ObjectCluster.returnFormatCluster(allFormats, "CAL") as? FormatCluster
                        val timeStampData = timeStampCluster?.mData
                        Log.i(LOG_TAG, "Time Stamp: $timeStampData")

                        val accelFormats = objectCluster.getCollectionOfFormatClusters(
                            Configuration.Shimmer2.ObjectClusterSensorName.ACCEL_X
                        )
                        val accelXCluster =
                            ObjectCluster.returnFormatCluster(accelFormats, "CAL") as? FormatCluster
                        accelXCluster?.let {
                            val accelXData = it.mData
                            Log.i(LOG_TAG, "Accel LN X: $accelXData")
                        }
                    }
                }

                Shimmer.MESSAGE_TOAST -> {
                    Toast.makeText(
                        applicationContext,
                        msg.data.getString(Shimmer.TOAST),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                ShimmerBluetooth.MSG_IDENTIFIER_NOTIFICATION_MESSAGE -> {
                    if (msg.obj is CallbackObject) {
                        val ind = (msg.obj as CallbackObject).mIndicator
                        if (ind == ShimmerBluetooth.NOTIFICATION_SHIMMER_FULLY_INITIALIZED) {
                            Log.i(LOG_TAG, "Shimmer fully initialised")
                        }
                    }
                }
            }
            super.handleMessage(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            btManager = ShimmerBluetoothManagerAndroid(this, mHandler)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            MaterialTheme {
                ShimmerLegacyScreen()
            }
        }
    }

    @Composable
    fun ShimmerLegacyScreen() {
        val state by connectionState
        val address by deviceAddress

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Shimmer Legacy Example",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Shimmer2 / Shimmer2r Support",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Device Status",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Status: $state",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Address: $address",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                Text(
                    text = "Please refer to Android Logcat for sensor data",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { selectDevice() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Select Device")
                }

                Button(
                    onClick = { startStreaming() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state == "Connected"
                ) {
                    Text("Start Streaming")
                }

                Button(
                    onClick = { stopStreaming() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state == "Streaming"
                ) {
                    Text("Stop Streaming")
                }

                Button(
                    onClick = { disconnectDevice() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state != "Disconnected"
                ) {
                    Text("Disconnect")
                }

                Text(
                    text = "Note: Accelerometer sensor is automatically enabled on first connection",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    private fun selectDevice() {
        val intent = Intent(applicationContext, ShimmerBluetoothDialog::class.java)
        startActivityForResult(intent, ShimmerBluetoothDialog.REQUEST_CONNECT_SHIMMER)
    }

    private fun startStreaming() {
        try {
            btManager?.startStreaming(shimmerBtAdd)
        } catch (e: ShimmerException) {
            e.printStackTrace()
        }
    }

    private fun stopStreaming() {
        try {
            btManager?.stopStreaming(shimmerBtAdd)
        } catch (e: ShimmerException) {
            e.printStackTrace()
        }
    }

    private fun disconnectDevice() {
        btManager?.disconnectAllDevices()
        mFirstTimeConnection = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                btManager?.disconnectAllDevices()

                val macAdd = data?.getStringExtra(ShimmerBluetoothDialog.EXTRA_DEVICE_ADDRESS)
                macAdd?.let {
                    btManager?.connectShimmerThroughBTAddress(it)
                    shimmerBtAdd = it
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        btManager?.disconnectAllDevices()
        super.onDestroy()
    }
}
