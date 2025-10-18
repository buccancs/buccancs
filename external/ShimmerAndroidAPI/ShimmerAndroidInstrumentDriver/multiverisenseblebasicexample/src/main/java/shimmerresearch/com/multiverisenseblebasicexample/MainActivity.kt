package shimmerresearch.com.multiverisenseblebasicexample

import android.Manifest
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.shimmerresearch.androidradiodriver.VerisenseBleAndroidRadioByteCommunication
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.exceptions.ShimmerException
import com.shimmerresearch.verisense.communication.VerisenseProtocolByteCommunication
import com.shimmerresearch.verisense.sensors.SensorLIS2DW12

data class DeviceState(
    val id: Int,
    val name: String,
    val macAddress: String,
    val connectionState: MutableState<String> = mutableStateOf(
        "Disconnected"
    ),
    val throughput: MutableState<String> = mutableStateOf(
        "0 kbps"
    ),
    val isSpeedTest: MutableState<Boolean> = mutableStateOf(
        false
    )
)

class MainActivity :
    ComponentActivity() {

    companion object {
        private const val LOG_TAG =
            "MultiVeriBLEExample"
    }

    private val devices =
        listOf(
            DeviceState(
                1,
                "Device 1",
                "DA:A6:19:F0:4A:D7"
            ),
            DeviceState(
                2,
                "Device 2",
                "C9:61:17:53:74:02"
            ),
            DeviceState(
                3,
                "Device 3",
                "F2:52:7C:20:D9:7E"
            )
        )

    private val radioMap =
        mutableMapOf<Int, VerisenseBleAndroidRadioByteCommunication>()
    private val protocolMap =
        mutableMapOf<Int, VerisenseProtocolByteCommunication>()
    private val deviceMap =
        mutableMapOf<Int, VerisenseDeviceAndroid>()

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

                            val accelXFormats =
                                objectCluster.getCollectionOfFormatClusters(
                                    SensorLIS2DW12.ObjectClusterSensorName.LIS2DW12_ACC_X
                                )
                            val accelXCluster =
                                ObjectCluster.returnFormatCluster(
                                    accelXFormats,
                                    "CAL"
                                ) as? FormatCluster
                            accelXCluster?.let {
                                Log.i(
                                    LOG_TAG,
                                    "Accel X: ${it.mData}"
                                )
                            }

                            val accelYFormats =
                                objectCluster.getCollectionOfFormatClusters(
                                    SensorLIS2DW12.ObjectClusterSensorName.LIS2DW12_ACC_Y
                                )
                            val accelYCluster =
                                ObjectCluster.returnFormatCluster(
                                    accelYFormats,
                                    "CAL"
                                ) as? FormatCluster
                            accelYCluster?.let {
                                Log.i(
                                    LOG_TAG,
                                    "Accel Y: ${it.mData}"
                                )
                            }

                            val accelZFormats =
                                objectCluster.getCollectionOfFormatClusters(
                                    SensorLIS2DW12.ObjectClusterSensorName.LIS2DW12_ACC_Z
                                )
                            val accelZCluster =
                                ObjectCluster.returnFormatCluster(
                                    accelZFormats,
                                    "CAL"
                                ) as? FormatCluster
                            accelZCluster?.let {
                                Log.i(
                                    LOG_TAG,
                                    "Accel Z: ${it.mData}"
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

                        devices.find { it.macAddress == macAddress }
                            ?.let { device ->
                                when (state) {
                                    ShimmerBluetooth.BT_STATE.CONNECTED -> device.connectionState.value =
                                        "Connected"

                                    ShimmerBluetooth.BT_STATE.CONNECTING -> device.connectionState.value =
                                        "Connecting"

                                    ShimmerBluetooth.BT_STATE.STREAMING -> device.connectionState.value =
                                        "Streaming"

                                    ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING -> device.connectionState.value =
                                        "Streaming & Logging"

                                    ShimmerBluetooth.BT_STATE.SDLOGGING -> device.connectionState.value =
                                        "SD Logging"

                                    ShimmerBluetooth.BT_STATE.DISCONNECTED -> {
                                        device.connectionState.value =
                                            "Disconnected"
                                        device.isSpeedTest.value =
                                            false
                                    }

                                    else -> {}
                                }
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

        val permissions =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                arrayOf(
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } else {
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
            }

        val permissionGranted =
            permissions.all {
                ContextCompat.checkSelfPermission(
                    this,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }

        if (!permissionGranted) {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                110
            )
        }

        BleManager.getInstance()
            .init(
                application
            )

        devices.forEach { device ->
            radioMap[device.id] =
                VerisenseBleAndroidRadioByteCommunication(
                    device.macAddress
                )
            protocolMap[device.id] =
                VerisenseProtocolByteCommunication(
                    radioMap[device.id]
                )
            deviceMap[device.id] =
                VerisenseDeviceAndroid(
                    mHandler
                )
        }

        setContent {
            MaterialTheme {
                MultiVerisenseScreen()
            }
        }
    }

    @Composable
    fun MultiVerisenseScreen() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        16.dp
                    )
            ) {
                Text(
                    text = "Multi Verisense BLE Example",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(
                        bottom = 16.dp
                    )
                )

                Text(
                    text = "Managing 3 Verisense Devices",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                        bottom = 16.dp
                    )
                )

                devices.forEach { device ->
                    DeviceCard(
                        device
                    )
                    Spacer(
                        modifier = Modifier.height(
                            16.dp
                        )
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(
                            16.dp
                        )
                    ) {
                        Text(
                            text = "Batch Operations",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(
                                bottom = 8.dp
                            )
                        )

                        Button(
                            onClick = { startAllSpeedTests() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Start All Speed Tests"
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(
                                8.dp
                            )
                        )

                        Button(
                            onClick = { stopAllSpeedTests() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Stop All Speed Tests"
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun DeviceCard(
        device: DeviceState
    ) {
        val state by device.connectionState
        val throughput by device.throughput

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(
                    16.dp
                )
            ) {
                Text(
                    text = device.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(
                        bottom = 4.dp
                    )
                )

                Text(
                    text = "MAC: ${device.macAddress}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )

                Text(
                    text = "Status: $state",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        bottom = 4.dp
                    )
                )

                Text(
                    text = "Throughput: $throughput",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )

                Divider(
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp
                    )
                ) {
                    Button(
                        onClick = {
                            connectDevice(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state == "Disconnected"
                    ) {
                        Text(
                            "Connect"
                        )
                    }

                    Button(
                        onClick = {
                            disconnectDevice(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state != "Disconnected"
                    ) {
                        Text(
                            "Disconnect"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(
                        8.dp
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp
                    )
                ) {
                    Button(
                        onClick = {
                            readOpConfig(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state == "Connected"
                    ) {
                        Text(
                            "Read Op"
                        )
                    }

                    Button(
                        onClick = {
                            readProdConfig(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state == "Connected"
                    ) {
                        Text(
                            "Read Prod"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(
                        8.dp
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp
                    )
                ) {
                    Button(
                        onClick = {
                            startStreaming(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state == "Connected"
                    ) {
                        Text(
                            "Start Stream"
                        )
                    }

                    Button(
                        onClick = {
                            stopStreaming(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state == "Streaming"
                    ) {
                        Text(
                            "Stop Stream"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(
                        8.dp
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp
                    )
                ) {
                    Button(
                        onClick = {
                            startSpeedTest(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state == "Connected"
                    ) {
                        Text(
                            "Start Speed"
                        )
                    }

                    Button(
                        onClick = {
                            stopSpeedTest(
                                device.id
                            )
                        },
                        modifier = Modifier.weight(
                            1f
                        ),
                        enabled = state == "Connected"
                    ) {
                        Text(
                            "Stop Speed"
                        )
                    }
                }
            }
        }
    }

    private fun connectDevice(
        deviceId: Int
    ) {
        Thread {
            deviceMap[deviceId]?.setProtocol(
                Configuration.COMMUNICATION_TYPE.BLUETOOTH,
                protocolMap[deviceId]
            )
            try {
                deviceMap[deviceId]?.connect()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun disconnectDevice(
        deviceId: Int
    ) {
        Thread {
            try {
                devices.find { it.id == deviceId }?.isSpeedTest?.value =
                    false
                deviceMap[deviceId]?.disconnect()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun readOpConfig(
        deviceId: Int
    ) {
        Thread {
            try {
                protocolMap[deviceId]?.readOperationalConfig()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun readProdConfig(
        deviceId: Int
    ) {
        Thread {
            try {
                protocolMap[deviceId]?.readProductionConfig()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun startStreaming(
        deviceId: Int
    ) {
        Thread {
            try {
                protocolMap[deviceId]?.startStreaming()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun stopStreaming(
        deviceId: Int
    ) {
        Thread {
            try {
                protocolMap[deviceId]?.stopStreaming()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun startSpeedTest(
        deviceId: Int
    ) {
        Thread {
            try {
                protocolMap[deviceId]?.startSpeedTest()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun stopSpeedTest(
        deviceId: Int
    ) {
        Thread {
            try {
                protocolMap[deviceId]?.stopSpeedTest()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun startAllSpeedTests() {
        Thread {
            try {
                protocolMap[1]?.startSpeedTest()
                protocolMap[2]?.startSpeedTest()
                protocolMap[3]?.startSpeedTest()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun stopAllSpeedTests() {
        Thread {
            try {
                protocolMap[1]?.stopSpeedTest()
                protocolMap[2]?.stopSpeedTest()
                protocolMap[3]?.stopSpeedTest()
            } catch (e: ShimmerException) {
                e.printStackTrace()
            }
        }.start()
    }
}
