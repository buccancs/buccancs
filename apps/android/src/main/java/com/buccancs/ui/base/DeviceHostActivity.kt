package com.buccancs.ui.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.buccancs.hardware.DeviceScannerService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Base activity that encapsulates shared USB discovery, permission handling, and device scanning
 * concerns so feature activities can focus on UI.
 */
abstract class DeviceHostActivity : ComponentActivity() {

    @Inject
    lateinit var deviceScanner: DeviceScannerService

    private val _usbAttachmentEvents =
        MutableSharedFlow<UsbDeviceAttachmentEvent>(extraBufferCapacity = 1)
    val usbAttachmentEvents: SharedFlow<UsbDeviceAttachmentEvent> =
        _usbAttachmentEvents.asSharedFlow()

    private val pendingUsbPermissionRequests =
        mutableSetOf<String>()

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            onPermissionsResult(permissions)
        }

    private var educationPresented =
        false

    /**
     * Whether the host should immediately request permissions for recognised USB devices.
     */
    protected open val autoRequestUsbPermission: Boolean =
        false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestRuntimePermissions()
        handleUsbIntent(intent)
        deviceScanner.startBackgroundScan(intervalMs = backgroundScanIntervalMs)

        if (autoRequestUsbPermission) {
            startAutoUsbPermissionWatcher()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleUsbIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        deviceScanner.stopBackgroundScan()
    }

    /**
     * Customise the set of runtime permissions the host needs.
     */
    protected open val runtimePermissions: List<String> =
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ) + if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            emptyList()
        } + listOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        ) + if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            emptyList()
        }

    /**
     * Provide educational copy explaining why each permission is required.
     */
    protected open val permissionEducation: List<PermissionEducation> =
        emptyList()

    /**
     * Duration in milliseconds between background scans for attached devices.
     */
    protected open val backgroundScanIntervalMs: Long =
        30_000L

    /**
     * Hook invoked after new permissions have been handled.
     */
    protected open fun onPermissionsResult(permissions: Map<String, Boolean>) {
        // Subclasses can override to respond to granular permission changes.
    }

    /**
     * Determines whether the provided USB device should emit an attachment event.
     */
    protected open fun isRecognisedDevice(device: UsbDevice): Boolean =
        true

    /**
     * Builds the title for the permission education dialog.
     */
    protected open fun permissionEducationTitle(): String =
        "Permissions Required"

    /**
     * Builds the primary action label for the permission education dialog.
     */
    protected open fun permissionEducationConfirmLabel(): String =
        "Continue"

    /**
     * Builds the cancel action label for the permission education dialog.
     */
    protected open fun permissionEducationDismissLabel(): String =
        "Cancel"

    /**
     * Called when an eligible USB device is attached.
     */
    protected open fun onDeviceAttached(device: UsbDevice) {
        if (autoRequestUsbPermission) {
            pendingUsbPermissionRequests.add(device.permissionKey())
            deviceScanner.requestUsbPermission(device)
        }
        lifecycleScope.launch {
            _usbAttachmentEvents.emit(
                UsbDeviceAttachmentEvent(
                    device = device,
                    autoRequestedPermission = autoRequestUsbPermission
                )
            )
        }
    }

    private fun requestRuntimePermissions() {
        val pendingPermissions =
            runtimePermissions.distinct().filter { permission ->
                ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            }

        if (pendingPermissions.isEmpty()) {
            return
        }

        val educationCopy =
            permissionEducation.filter { pendingPermissions.contains(it.permission) }

        if (!educationPresented && educationCopy.isNotEmpty()) {
            educationPresented =
                true
            presentEducationAndRequest(
                educationCopy,
                pendingPermissions
            )
        } else {
            permissionLauncher.launch(
                pendingPermissions.toTypedArray()
            )
        }
    }

    private fun presentEducationAndRequest(
        educationCopy: List<PermissionEducation>,
        pendingPermissions: List<String>
    ) {
        val message =
            educationCopy.joinToString(separator = "\n\n") { item ->
                "${item.heading}: ${item.detail}"
            }

        MaterialAlertDialogBuilder(this)
            .setTitle(permissionEducationTitle())
            .setMessage(message)
            .setPositiveButton(permissionEducationConfirmLabel()) { _, _ ->
                permissionLauncher.launch(
                    pendingPermissions.toTypedArray()
                )
            }
            .setNegativeButton(permissionEducationDismissLabel(), null)
            .show()
    }

    private fun handleUsbIntent(intent: Intent?) {
        if (intent?.action != UsbManager.ACTION_USB_DEVICE_ATTACHED) {
            return
        }

        val usbDevice: UsbDevice? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(
                    UsbManager.EXTRA_DEVICE,
                    UsbDevice::class.java
                )
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
            }

        usbDevice?.let { device ->
            if (isRecognisedDevice(device)) {
                onDeviceAttached(device)
            }
        }
    }

    private fun startAutoUsbPermissionWatcher() {
        lifecycleScope.launch {
            deviceScanner.scanState.collectLatest { state ->
                val recognisedWithoutPermission =
                    state.usbDevices.filter { scanned ->
                        isRecognisedDevice(scanned.device) && !scanned.hasPermission
                    }

                val activeKeys =
                    recognisedWithoutPermission.map { scanned ->
                        scanned.device.permissionKey()
                    }.toSet()

                recognisedWithoutPermission.forEach { scanned ->
                    val key =
                        scanned.device.permissionKey()
                    if (pendingUsbPermissionRequests.add(key)) {
                        deviceScanner.requestUsbPermission(scanned.device)
                    }
                }

                pendingUsbPermissionRequests.retainAll(activeKeys)
            }
        }
    }

    private fun UsbDevice.permissionKey(): String =
        buildString {
            append(vendorId)
            append(':')
            append(productId)
            append(':')
            append(deviceName ?: "unknown")
        }

    data class PermissionEducation(
        val permission: String,
        val heading: String,
        val detail: String
    )
}
