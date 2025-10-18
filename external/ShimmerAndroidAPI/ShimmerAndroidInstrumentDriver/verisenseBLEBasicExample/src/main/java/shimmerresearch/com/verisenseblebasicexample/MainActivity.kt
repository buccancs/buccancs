package shimmerresearch.com.verisenseblebasicexample

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
import com.shimmerresearch.android.VerisenseDeviceAndroid
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog
import com.shimmerresearch.androidradiodriver.VerisenseBleAndroidRadioByteCommunication
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.exceptions.ShimmerException
import com.shimmerresearch.verisense.communication.VerisenseProtocolByteCommunication
import com.shimmerresearch.verisense.sensors.SensorLIS2DW12

class MainActivity : ComponentActivity() {

    companion object {
        private const val LOG_TAG = "VeriBLEBasicExample"
    }

    private val macAddress = "C0:04:19:85:9A:D5"
    private var radio1: VerisenseBleAndroidRadioByteCommunication? = null
    private var protocol1: VerisenseProtocolByteCommunication? = null
    private var device1: VerisenseDeviceAndroid? = null

    private val connectionState = mutableStateOf("Disconnected")
    private val accelData = mutableStateOf("X: N/A\nY: N/A\nZ: N/A")

    private val mHandler = object : Handler(Looper.getMainLooper()) {
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
                            SensorLIS2DW12.ObjectClusterSensorName.LIS2DW12_ACC_X
                        )
                        val accelXCluster = ObjectCluster.returnFormatCluster(
                            accelXFormats,
                            "CAL"
                        ) as? FormatCluster
                        val accelXData = accelXCluster?.mData
                        if (accelXCluster != null) {
                            Log.i(LOG_TAG, "Accel X: $accelXData")
                        }

                        val accelYFormats = objectCluster.getCollectionOfFormatClusters(
                            SensorLIS2DW12.ObjectClusterSensorName.LIS2DW12_ACC_Y
                        )
                        val accelYCluster = ObjectCluster.returnFormatCluster(
                            accelYFormats,
                            "CAL"
                        ) as? FormatCluster
                        val accelYData = accelYCluster?.mData
                        if (accelYCluster != null) {
                            Log.i(LOG_TAG, "Accel Y: $accelYData")
                        }

                        val accelZFormats = objectCluster.getCollectionOfFormatClusters(
                            SensorLIS2DW12.ObjectClusterSensorName.LIS2DW12_ACC_Z
                        )
                        val accelZCluster = ObjectCluster.returnFormatCluster(
                            accelZFormats,
                            "CAL"
                        ) as? FormatCluster
                        val accelZData = accelZCluster?.mData
                        if (accelZCluster != null) {
                            Log.i(LOG_TAG, "Accel Z: $accelZData")
                        }

                        accelData.value =
                            "X: ${accelXData ?: "N/A"}\nY: ${accelYData ?: "N/A"}\nZ: ${accelZData ?: "N/A"}"
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

        val permissions = arrayOf(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val permissionGranted = permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }

        if (!permissionGranted) {
            ActivityCompat.requestPermissions(this, permissions, 110)
        }

        setContent {
            MaterialTheme {
                VerisenseBLEScreen()
            }
        }
    }

    @Composable
    fun VerisenseBLEScreen() {
        val state by connectionState
        val data by accelData

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
                    text = "Verisense BLE Basic Example",
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
                            text = "Accelerometer Data (LIS2DW12)",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = data,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { connectDevice() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Connect")
                }

                Button(
                    onClick = { readOpConfig() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state == "Connected"
                ) {
                    Text("Read Operational Config")
                }

                Button(
                    onClick = { readProdConfig() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state == "Connected"
                ) {
                    Text("Read Production Config")
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
                    onClick = { startSpeedTest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state == "Connected" || state == "Streaming"
                ) {
                    Text("Start Speed Test")
                }

                Button(
                    onClick = { stopSpeedTest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    enabled = state == "Connected" || state == "Streaming"
                ) {
                    Text("Stop Speed Test")
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
        startActivityForResult(pairedDevicesIntent, ShimmerBluetoothDialog.REQUEST_CONNECT_SHIMMER)
    }

    private fun disconnectDevice() {
        Thread {
            try {
                device1?.disconnect()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun readOpConfig() {
        Thread {
            try {
                protocol1?.readOperationalConfig()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun readProdConfig() {
        Thread {
            try {
                protocol1?.readProductionConfig()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun startStreaming() {
        Thread {
            try {
                protocol1?.startStreaming()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun stopStreaming() {
        Thread {
            try {
                protocol1?.stopStreaming()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun startSpeedTest() {
        Thread {
            try {
                protocol1?.startSpeedTest()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun stopSpeedTest() {
        Thread {
            try {
                protocol1?.stopSpeedTest()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ShimmerBluetoothDialog.REQUEST_CONNECT_SHIMMER) {
            if (resultCode == Activity.RESULT_OK) {
                BleManager.getInstance().init(application)
                device1 = VerisenseDeviceAndroid(mHandler)
                val macAdd = data?.getStringExtra(ShimmerBluetoothDialog.EXTRA_DEVICE_ADDRESS)
                macAdd?.let {
                    radio1 = VerisenseBleAndroidRadioByteCommunication(it)
                    protocol1 = VerisenseProtocolByteCommunication(radio1)

                    Thread {
                        device1?.setProtocol(Configuration.COMMUNICATION_TYPE.BLUETOOTH, protocol1)
                        try {
                            device1?.connect()
                        } catch (e: ShimmerException) {
                            e.printStackTrace()
                        }
                    }.start()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
