package com.buccancs.ui.base

import android.hardware.usb.UsbDevice

/**
 * Emitted whenever a recognised USB device is attached to the host.
 */
data class UsbDeviceAttachmentEvent(
    val device: UsbDevice,
    val autoRequestedPermission: Boolean = false
)
