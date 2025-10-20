package com.buccancs.hardware

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
import android.util.Log
import com.buccancs.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Represents a scanned device (Bluetooth or USB)
 */
sealed class ScannedDevice {
    data class Bluetooth(
        val device: BluetoothDevice,
        val name: String?,
        val address: String,
        val bondState: Int,
        val rssi: Int? = null
    ) : ScannedDevice()

    data class Usb(
        val device: UsbDevice,
        val name: String,
        val deviceName: String,
        val vendorId: Int,
        val productId: Int,
        val hasPermission: Boolean
    ) : ScannedDevice()
}

/**
 * Scanning state
 */
data class ScanState(
    val isScanning: Boolean = false,
    val bluetoothDevices: List<ScannedDevice.Bluetooth> = emptyList(),
    val usbDevices: List<ScannedDevice.Usb> = emptyList(),
    val error: String? = null
)

/**
 * Service for scanning Bluetooth and USB devices
 */
@Singleton
class DeviceScannerService @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val scope: CoroutineScope,
    private val bluetoothService: BluetoothService,
    private val usbService: UsbService,
    private val usbManager: UsbManager
) {
    private val tag = "DeviceScanner"

    private val _scanState = MutableStateFlow(ScanState())
    val scanState: StateFlow<ScanState> = _scanState.asStateFlow()

    private val _deviceEvents = MutableSharedFlow<DeviceEvent>(extraBufferCapacity = 16)
    val deviceEvents: SharedFlow<DeviceEvent> = _deviceEvents.asSharedFlow()

    private var bluetoothReceiver: BroadcastReceiver? = null
    private var usbReceiver: BroadcastReceiver? = null
    private var backgroundScanJob: Job? = null
    private var isReceiverRegistered = false

    sealed class DeviceEvent {
        data class BluetoothDeviceFound(val device: ScannedDevice.Bluetooth) : DeviceEvent()
        data class UsbDeviceAttached(val device: ScannedDevice.Usb) : DeviceEvent()
        data class UsbDeviceDetached(val deviceName: String) : DeviceEvent()
        data class PermissionGranted(val device: ScannedDevice.Usb) : DeviceEvent()
        data class PermissionDenied(val device: ScannedDevice.Usb) : DeviceEvent()
    }

    init {
        // Register USB receiver for device attach/detach events
        registerUsbReceiver()
        // Initial scan
        scanUsbDevices()
    }

    /**
     * Start manual scan for Bluetooth and USB devices
     */
    @SuppressLint("MissingPermission")
    fun startManualScan() {
        scope.launch {
            try {
                _scanState.value = _scanState.value.copy(isScanning = true, error = null)

                // Scan Bluetooth
                if (bluetoothService.isAvailable() && bluetoothService.isEnabled()) {
                    registerBluetoothReceiver()

                    // Get bonded devices first
                    val bondedDevices = bluetoothService.getBondedDevices().map { device ->
                        ScannedDevice.Bluetooth(
                            device = device,
                            name = device.name,
                            address = device.address,
                            bondState = device.bondState
                        )
                    }

                    _scanState.value = _scanState.value.copy(bluetoothDevices = bondedDevices)

                    // Start discovery
                    bluetoothService.startDiscovery()

                    // Auto-stop after 12 seconds
                    delay(12000)
                    stopBluetoothScan()
                }

                // Scan USB
                scanUsbDevices()

            } catch (e: Exception) {
                Log.e(tag, "Error during manual scan", e)
                _scanState.value = _scanState.value.copy(
                    isScanning = false,
                    error = "Scan failed: ${e.message}"
                )
            }
        }
    }

    /**
     * Stop Bluetooth scanning
     */
    private fun stopBluetoothScan() {
        try {
            unregisterBluetoothReceiver()
            _scanState.value = _scanState.value.copy(isScanning = false)
        } catch (e: Exception) {
            Log.e(tag, "Error stopping Bluetooth scan", e)
        }
    }

    /**
     * Scan USB devices
     */
    private fun scanUsbDevices() {
        try {
            val deviceList = usbService.getDeviceList()
            val usbDevices = deviceList.values.map { device ->
                ScannedDevice.Usb(
                    device = device,
                    name = device.productName ?: "Unknown USB Device",
                    deviceName = device.deviceName,
                    vendorId = device.vendorId,
                    productId = device.productId,
                    hasPermission = usbService.hasPermission(device)
                )
            }

            _scanState.value = _scanState.value.copy(usbDevices = usbDevices)

            Log.d(tag, "Found ${usbDevices.size} USB devices")
        } catch (e: Exception) {
            Log.e(tag, "Error scanning USB devices", e)
        }
    }

    /**
     * Request permission for USB device
     */
    fun requestUsbPermission(device: UsbDevice) {
        try {
            if (!usbService.hasPermission(device)) {
                val permissionIntent = android.app.PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(ACTION_USB_PERMISSION),
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        android.app.PendingIntent.FLAG_MUTABLE
                    } else {
                        0
                    }
                )
                usbManager.requestPermission(device, permissionIntent)
            }
        } catch (e: Exception) {
            Log.e(tag, "Error requesting USB permission", e)
        }
    }

    /**
     * Start background periodic scanning
     */
    fun startBackgroundScan(intervalMs: Long = 30000) {
        stopBackgroundScan()

        backgroundScanJob = scope.launch {
            while (isActive) {
                try {
                    // Only scan USB in background (Bluetooth scanning is battery intensive)
                    scanUsbDevices()
                    delay(intervalMs)
                } catch (e: Exception) {
                    Log.e(tag, "Error in background scan", e)
                    delay(intervalMs)
                }
            }
        }

        Log.d(tag, "Background scanning started with interval ${intervalMs}ms")
    }

    /**
     * Stop background scanning
     */
    fun stopBackgroundScan() {
        backgroundScanJob?.cancel()
        backgroundScanJob = null
        Log.d(tag, "Background scanning stopped")
    }

    /**
     * Register Bluetooth receiver for device discovery
     */
    @SuppressLint("MissingPermission")
    private fun registerBluetoothReceiver() {
        if (bluetoothReceiver != null) return

        bluetoothReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        val device: BluetoothDevice? =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                intent.getParcelableExtra(
                                    BluetoothDevice.EXTRA_DEVICE,
                                    BluetoothDevice::class.java
                                )
                            } else {
                                @Suppress("DEPRECATION")
                                intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                            }

                        val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE)
                            .toInt()

                        device?.let { btDevice ->
                            val scannedDevice = ScannedDevice.Bluetooth(
                                device = btDevice,
                                name = btDevice.name,
                                address = btDevice.address,
                                bondState = btDevice.bondState,
                                rssi = rssi
                            )

                            // Update state with new device
                            val currentDevices = _scanState.value.bluetoothDevices.toMutableList()
                            val existingIndex =
                                currentDevices.indexOfFirst { it.address == btDevice.address }
                            if (existingIndex >= 0) {
                                currentDevices[existingIndex] = scannedDevice
                            } else {
                                currentDevices.add(scannedDevice)
                            }

                            _scanState.value =
                                _scanState.value.copy(bluetoothDevices = currentDevices)

                            scope.launch {
                                _deviceEvents.emit(DeviceEvent.BluetoothDeviceFound(scannedDevice))
                            }
                        }
                    }

                    BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                        stopBluetoothScan()
                    }
                }
            }
        }

        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(bluetoothReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(bluetoothReceiver, filter)
        }
    }

    /**
     * Unregister Bluetooth receiver
     */
    private fun unregisterBluetoothReceiver() {
        bluetoothReceiver?.let {
            try {
                context.unregisterReceiver(it)
            } catch (e: Exception) {
                Log.e(tag, "Error unregistering Bluetooth receiver", e)
            }
            bluetoothReceiver = null
        }
    }

    /**
     * Register USB receiver for attach/detach events
     */
    private fun registerUsbReceiver() {
        if (isReceiverRegistered) return

        usbReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    UsbManager.ACTION_USB_DEVICE_ATTACHED -> {
                        val device: UsbDevice? =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                intent.getParcelableExtra(
                                    UsbManager.EXTRA_DEVICE,
                                    UsbDevice::class.java
                                )
                            } else {
                                @Suppress("DEPRECATION")
                                intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                            }

                        device?.let { usbDevice ->
                            scanUsbDevices()

                            val scannedDevice = ScannedDevice.Usb(
                                device = usbDevice,
                                name = usbDevice.productName ?: "Unknown USB Device",
                                deviceName = usbDevice.deviceName,
                                vendorId = usbDevice.vendorId,
                                productId = usbDevice.productId,
                                hasPermission = usbService.hasPermission(usbDevice)
                            )

                            scope.launch {
                                _deviceEvents.emit(DeviceEvent.UsbDeviceAttached(scannedDevice))
                            }
                        }
                    }

                    UsbManager.ACTION_USB_DEVICE_DETACHED -> {
                        val device: UsbDevice? =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                intent.getParcelableExtra(
                                    UsbManager.EXTRA_DEVICE,
                                    UsbDevice::class.java
                                )
                            } else {
                                @Suppress("DEPRECATION")
                                intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                            }

                        device?.let { usbDevice ->
                            scanUsbDevices()

                            scope.launch {
                                _deviceEvents.emit(DeviceEvent.UsbDeviceDetached(usbDevice.deviceName))
                            }
                        }
                    }

                    ACTION_USB_PERMISSION -> {
                        val device: UsbDevice? =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                intent.getParcelableExtra(
                                    UsbManager.EXTRA_DEVICE,
                                    UsbDevice::class.java
                                )
                            } else {
                                @Suppress("DEPRECATION")
                                intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                            }

                        val granted =
                            intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)

                        device?.let { usbDevice ->
                            val scannedDevice = ScannedDevice.Usb(
                                device = usbDevice,
                                name = usbDevice.productName ?: "Unknown USB Device",
                                deviceName = usbDevice.deviceName,
                                vendorId = usbDevice.vendorId,
                                productId = usbDevice.productId,
                                hasPermission = granted
                            )

                            scanUsbDevices()

                            scope.launch {
                                if (granted) {
                                    _deviceEvents.emit(DeviceEvent.PermissionGranted(scannedDevice))
                                } else {
                                    _deviceEvents.emit(DeviceEvent.PermissionDenied(scannedDevice))
                                }
                            }
                        }
                    }
                }
            }
        }

        val filter = IntentFilter().apply {
            addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
            addAction(ACTION_USB_PERMISSION)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(usbReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(usbReceiver, filter)
        }

        isReceiverRegistered = true
    }

    /**
     * Cleanup
     */
    fun cleanup() {
        stopBackgroundScan()
        unregisterBluetoothReceiver()

        usbReceiver?.let {
            try {
                context.unregisterReceiver(it)
            } catch (e: Exception) {
                Log.e(tag, "Error unregistering USB receiver", e)
            }
            usbReceiver = null
        }
        isReceiverRegistered = false
    }

    companion object {
        private const val ACTION_USB_PERMISSION = "com.buccancs.USB_PERMISSION"
    }
}
