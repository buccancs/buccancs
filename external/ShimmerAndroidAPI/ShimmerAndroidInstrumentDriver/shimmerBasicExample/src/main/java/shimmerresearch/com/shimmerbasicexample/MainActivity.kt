package shimmerresearch.com.shimmerbasicexample

import android.Manifest
import android.app.Activity
import android.content.Intent
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
import androidx.core.content.ContextCompat
import com.shimmerresearch.android.Shimmer
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.FormatCluster
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.exceptions.ShimmerException

class MainActivity :
    ComponentActivity() {

    companion object {
        private const val LOG_TAG =
            "ShimmerBasicExample"
    }

    private var shimmer: Shimmer? =
        null

    private val connectionState =
        mutableStateOf(
            "Disconnected"
        )
    private val crcEnabled =
        mutableStateOf(
            true
        )
    private val selectedCrcOption =
        mutableIntStateOf(
            0
        )
    private val packetReceptionRate =
        mutableStateOf(
            "N/A"
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

                            val prrFormats =
                                objectCluster.getCollectionOfFormatClusters(
                                    Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL
                                )
                            val prrCluster =
                                ObjectCluster.returnFormatCluster(
                                    prrFormats,
                                    "CAL"
                                ) as? FormatCluster
                            prrCluster?.let {
                                val prr =
                                    it.mData
                                Log.i(
                                    LOG_TAG,
                                    "Packet Reception Rate: $prr"
                                )
                                packetReceptionRate.value =
                                    String.format(
                                        "%.2f%%",
                                        prr
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

                        when (state) {
                            ShimmerBluetooth.BT_STATE.CONNECTED -> {
                                connectionState.value =
                                    "Connected"
                                shimmer?.let { shim ->
                                    if (shim.firmwareVersionCode >= 8) {
                                        when (shim.currentBtCommsCrcMode) {
                                            ShimmerBluetooth.CRC_MODE.OFF -> selectedCrcOption.intValue =
                                                0

                                            ShimmerBluetooth.CRC_MODE.ONE_BYTE_CRC -> selectedCrcOption.intValue =
                                                1

                                            ShimmerBluetooth.CRC_MODE.TWO_BYTE_CRC -> selectedCrcOption.intValue =
                                                2

                                            else -> {}
                                        }
                                        crcEnabled.value =
                                            true
                                    }
                                }
                            }

                            ShimmerBluetooth.BT_STATE.CONNECTING -> {
                                connectionState.value =
                                    "Connecting"
                            }

                            ShimmerBluetooth.BT_STATE.STREAMING -> {
                                connectionState.value =
                                    "Streaming"
                                crcEnabled.value =
                                    false
                            }

                            ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING -> {
                                connectionState.value =
                                    "Streaming & Logging"
                            }

                            ShimmerBluetooth.BT_STATE.SDLOGGING -> {
                                connectionState.value =
                                    "SD Logging"
                            }

                            ShimmerBluetooth.BT_STATE.DISCONNECTED -> {
                                connectionState.value =
                                    "Disconnected"
                                crcEnabled.value =
                                    false
                                selectedCrcOption.intValue =
                                    0
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

        var permissionGranted =
            true
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

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionGranted =
                    false
                break
            }
        }

        if (!permissionGranted) {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                110
            )
        }

        shimmer =
            Shimmer(
                mHandler,
                this
            )

        setContent {
            MaterialTheme {
                ShimmerBasicScreen()
            }
        }
    }

    @OptIn(
        ExperimentalMaterial3Api::class
    )
    @Composable
    fun ShimmerBasicScreen() {
        val state by connectionState
        val crcOptionsEnabled by crcEnabled
        val selectedOption by selectedCrcOption
        val prr by packetReceptionRate

        var expanded by remember {
            mutableStateOf(
                false
            )
        }
        val crcOptions =
            listOf(
                "Disable CRC",
                "Enable 1 byte CRC",
                "Enable 2 byte CRC"
            )

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
                    text = "Shimmer Basic Example",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )

                Text(
                    text = "Status: $state",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )

                Text(
                    text = "Packet Reception Rate: $prr",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        bottom = 16.dp
                    )
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(
                            16.dp
                        )
                    ) {
                        Text(
                            text = "CRC Mode Configuration",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(
                                bottom = 8.dp
                            )
                        )

                        ExposedDropdownMenuBox(
                            expanded = expanded && crcOptionsEnabled,
                            onExpandedChange = {
                                expanded =
                                    !expanded && crcOptionsEnabled
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = crcOptions[selectedOption],
                                onValueChange = {},
                                readOnly = true,
                                enabled = crcOptionsEnabled,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded && crcOptionsEnabled
                                    )
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                            )

                            ExposedDropdownMenu(
                                expanded = expanded && crcOptionsEnabled,
                                onDismissRequest = {
                                    expanded =
                                        false
                                }
                            ) {
                                crcOptions.forEachIndexed { index, option ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                option
                                            )
                                        },
                                        onClick = {
                                            onCrcOptionSelected(
                                                index
                                            )
                                            selectedCrcOption.intValue =
                                                index
                                            expanded =
                                                false
                                        }
                                    )
                                }
                            }
                        }

                        if (!crcOptionsEnabled) {
                            Text(
                                text = "CRC mode can only be changed when connected and not streaming",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(
                                    top = 8.dp
                                )
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(
                        8.dp
                    )
                )

                Text(
                    text = "Please refer to the Android Logcat for signal data",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                )

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
                    onClick = { startStreaming() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        ),
                    enabled = state == "Connected"
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
                        ),
                    enabled = state == "Streaming"
                ) {
                    Text(
                        "Stop Streaming"
                    )
                }

                Button(
                    onClick = { disconnectDevice() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        ),
                    enabled = state != "Disconnected"
                ) {
                    Text(
                        "Disconnect"
                    )
                }
            }
        }
    }

    private fun onCrcOptionSelected(
        position: Int
    ) {
        shimmer?.let {
            when (position) {
                0 -> it.disableBtCommsCrc()
                1 -> it.enableBtCommsOneByteCrc()
                2 -> it.enableBtCommsTwoByteCrc()
            }
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
        shimmer?.disconnect()
    }

    private fun startStreaming() {
        try {
            shimmer?.startStreaming()
        } catch (e: ShimmerException) {
            e.printStackTrace()
        }
    }

    private fun stopStreaming() {
        shimmer?.stopStreaming()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                val macAdd =
                    data?.getStringExtra(
                        ShimmerBluetoothDialog.EXTRA_DEVICE_ADDRESS
                    )
                shimmer =
                    Shimmer(
                        mHandler,
                        this
                    )
                macAdd?.let {
                    shimmer?.connect(
                        it,
                        "default"
                    )
                }
            }
        }
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )
    }
}
