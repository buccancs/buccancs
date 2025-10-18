package shimmerresearch.com.shimmer3blebasicexample

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.core.content.ContextCompat
import com.clj.fastble.BleManager
import com.shimmerresearch.android.Shimmer
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog
import com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.BasicProcessWithCallBack
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.driver.ShimmerMsg
import com.shimmerresearch.driverUtilities.ChannelDetails
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails
import com.shimmerresearch.driverUtilities.ShimmerVerDetails
import com.shimmerresearch.exceptions.ShimmerException
import com.shimmerresearch.sensors.kionix.SensorKionixAccel

class MainActivity : ComponentActivity() {

    companion object {
        const val REQUEST_CONNECT_SHIMMER = 2
        private const val LOG_TAG = "Shimmer3BLEBasicExample"
    }

    private var mHandler: Handler? = null
    private var shimmer1: Shimmer3BLEAndroid? = null
    private val macAddress = "E8:EB:1B:97:67:FC"

    private val connectionState = mutableStateOf("Disconnected")
    private val dataDisplay = mutableStateOf("Waiting for data...")

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET -> {
                    if (msg.obj is ObjectCluster) {
                        val objectCluster = msg.obj as ObjectCluster

                        val allFormats = objectCluster.getCollectionOfFormatClusters(
                            Configuration.Shimmer3.ObjectClusterSensorName.TIMESTAMP
                        )
                        val timeStampCluster =
                            ObjectCluster.returnFormatCluster(allFormats, "CAL") as? FormatCluster
                        val timeStampData = timeStampCluster?.mData
                        Log.i(LOG_TAG, "Time Stamp: $timeStampData")

                        val accelXFormats = objectCluster.getCollectionOfFormatClusters(
                            Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_X
                        )
                        val accelXCluster = ObjectCluster.returnFormatCluster(
                            accelXFormats,
                            "CAL"
                        ) as? FormatCluster
                        accelXCluster?.let {
                            val accelXData = it.mData
                            Log.i(LOG_TAG, "Accel LN X: $accelXData")
                        }

                        val accelYFormats = objectCluster.getCollectionOfFormatClusters(
                            Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y
                        )
                        val accelYCluster = ObjectCluster.returnFormatCluster(
                            accelYFormats,
                            "CAL"
                        ) as? FormatCluster
                        accelYCluster?.let {
                            val accelYData = it.mData
                            Log.i(LOG_TAG, "Accel LN Y: $accelYData")
                        }

                        val accelZFormats = objectCluster.getCollectionOfFormatClusters(
                            Configuration.Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z
                        )
                        val accelZCluster = ObjectCluster.returnFormatCluster(
                            accelZFormats,
                            "CAL"
                        ) as? FormatCluster
                        accelZCluster?.let {
                            val accelZData = it.mData
                            Log.i(LOG_TAG, "Accel LN Z: $accelZData")
                        }

                        dataDisplay.value = "X: ${accelXCluster?.mData ?: "N/A"}\n" +
                                "Y: ${accelYCluster?.mData ?: "N/A"}\n" +
                                "Z: ${accelZCluster?.mData ?: "N/A"}"
                    }
                }

                Shimmer.MESSAGE_TOAST -> {
                    Toast.makeText(
                        applicationContext,
                        msg.data.getString(Shimmer.TOAST),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE -> {
                    var state: ShimmerBluetooth.BT_STATE? = null
                    var macAddress = ""

                    when (msg.obj) {
                        is ObjectCluster -> {
                            state = (msg.obj as ObjectCluster).mState
                            macAddress = (msg.obj as ObjectCluster).macAddress
                        }

                        is CallbackObject -> {
                            state = (msg.obj as CallbackObject).mState
                            macAddress = (msg.obj as CallbackObject).mBluetoothAddress
                        }
                    }

                    when (state) {
                        ShimmerBluetooth.BT_STATE.CONNECTED -> connectionState.value = "Connected"
                        ShimmerBluetooth.BT_STATE.CONNECTING -> connectionState.value = "Connecting"
                        ShimmerBluetooth.BT_STATE.STREAMING -> connectionState.value = "Streaming"
                        ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING -> connectionState.value =
                            "Streaming & Logging"

                        ShimmerBluetooth.BT_STATE.SDLOGGING -> connectionState.value = "SD Logging"
                        ShimmerBluetooth.BT_STATE.DISCONNECTED -> connectionState.value =
                            "Disconnected"

                        else -> {}
                    }
                }
            }
            super.handleMessage(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var permissionGranted = true
        val permissions = arrayOf(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionGranted = false
                break
            }
        }

        if (!permissionGranted) {
            ActivityCompat.requestPermissions(this, permissions, 110)
        }

        BleManager.getInstance().init(application)
        mHandler = handler

        setContent {
            MaterialTheme {
                Shimmer3BLEScreen()
            }
        }
    }

    @Composable
    fun Shimmer3BLEScreen() {
        val state by connectionState
        val data by dataDisplay

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
                    text = "Shimmer3 BLE Basic Example",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Status: $state",
                    style = MaterialTheme.typography.bodyLarge,
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
                            text = "Accelerometer Data",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = data,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { connectDevice() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Connect")
                }

                Button(
                    onClick = { startStreaming() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state == "Connected" || state == "Streaming"
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
            }
        }
    }

    private fun connectDevice() {
        val pairedDevicesIntent = Intent(applicationContext, ShimmerBluetoothDialog::class.java)
        startActivityForResult(pairedDevicesIntent, REQUEST_CONNECT_SHIMMER)
    }

    private fun disconnectDevice() {
        Thread {
            try {
                shimmer1?.disconnect()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun startStreaming() {
        Thread {
            try {
                shimmer1?.startStreaming()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun stopStreaming() {
        Thread {
            shimmer1?.stopStreaming()
        }.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CONNECT_SHIMMER) {
            if (resultCode == Activity.RESULT_OK) {
                val macAdd = data?.getStringExtra(ShimmerBluetoothDialog.EXTRA_DEVICE_ADDRESS)
                val name = data?.getStringExtra(ShimmerBluetoothDialog.EXTRA_DEVICE_NAME)

                shimmer1 =
                    if (name != null && name.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER3R.toString())) {
                        Shimmer3BLEAndroid(ShimmerVerDetails.HW_ID.SHIMMER_3R, macAdd, mHandler)
                    } else {
                        Shimmer3BLEAndroid(ShimmerVerDetails.HW_ID.SHIMMER_3, macAdd, mHandler)
                    }

                val sdr = SensorDataReceived()
                sdr.setWaitForData(shimmer1)

                Thread {
                    shimmer1?.connect(macAdd, "default")
                }.start()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    inner class SensorDataReceived : BasicProcessWithCallBack() {
        override fun processMsgFromCallback(shimmerMSG: ShimmerMsg) {
            println(shimmerMSG.mIdentifier)

            val ind = shimmerMSG.mIdentifier
            val obj = shimmerMSG.mB

            when (ind) {
                Shimmer.MSG_IDENTIFIER_STATE_CHANGE -> {
                    val callbackObject = obj as CallbackObject
                    when (callbackObject.mState) {
                        ShimmerBluetooth.BT_STATE.CONNECTING -> {}
                        ShimmerBluetooth.BT_STATE.CONNECTED -> {}
                        ShimmerBluetooth.BT_STATE.DISCONNECTED,
                        ShimmerBluetooth.BT_STATE.CONNECTION_LOST -> {
                        }

                        else -> {}
                    }
                }

                Shimmer.MSG_IDENTIFIER_NOTIFICATION_MESSAGE -> {
                    val callbackObject = obj as CallbackObject
                    val msg = callbackObject.mIndicator
                    when (msg) {
                        Shimmer.NOTIFICATION_SHIMMER_FULLY_INITIALIZED -> {}
                        Shimmer.NOTIFICATION_SHIMMER_STOP_STREAMING -> {}
                        Shimmer.NOTIFICATION_SHIMMER_START_STREAMING -> {}
                        else -> {}
                    }
                }

                Shimmer.MSG_IDENTIFIER_DATA_PACKET -> {
                    val objc = shimmerMSG.mB as ObjectCluster

                    val adcFormatsX =
                        objc.getCollectionOfFormatClusters(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X)
                    val formatx = ObjectCluster.returnFormatCluster(
                        adcFormatsX,
                        ChannelDetails.CHANNEL_TYPE.CAL.toString()
                    ) as? FormatCluster

                    val adcFormatsY =
                        objc.getCollectionOfFormatClusters(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y)
                    val formaty = ObjectCluster.returnFormatCluster(
                        adcFormatsY,
                        ChannelDetails.CHANNEL_TYPE.CAL.toString()
                    ) as? FormatCluster

                    val adcFormatsZ =
                        objc.getCollectionOfFormatClusters(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z)
                    val formatz = ObjectCluster.returnFormatCluster(
                        adcFormatsZ,
                        ChannelDetails.CHANNEL_TYPE.CAL.toString()
                    ) as? FormatCluster

                    if (formatx != null) {
                        println("X:${formatx.mData} Y:${formaty?.mData} Z:${formatz?.mData}")
                    } else {
                        println("ERROR! FormatCluster is Null!")
                    }
                }

                Shimmer.MSG_IDENTIFIER_PACKET_RECEPTION_RATE_OVERALL -> {}
            }
        }
    }
}
