package com.buccancs.hardware

import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager

interface UsbService {
    fun getDeviceList(): Map<String, UsbDevice>
    fun hasPermission(
        device: UsbDevice
    ): Boolean
}

class AndroidUsbService(
    private val manager: UsbManager
) : UsbService {
    override fun getDeviceList(): Map<String, UsbDevice> =
        manager.deviceList

    override fun hasPermission(
        device: UsbDevice
    ): Boolean =
        manager.hasPermission(
            device
        )
}
