package com.buccancs.hardware

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice

interface BluetoothService {
    fun isEnabled(): Boolean
    fun getBondedDevices(): Set<BluetoothDevice>
    fun startDiscovery(): Boolean
    fun isAvailable(): Boolean
}

class AndroidBluetoothService(private val adapter: BluetoothAdapter?) : BluetoothService {
    override fun isEnabled(): Boolean = adapter?.isEnabled == true

    override fun getBondedDevices(): Set<BluetoothDevice> =
        adapter?.bondedDevices ?: emptySet()

    override fun startDiscovery(): Boolean = adapter?.startDiscovery() == true

    override fun isAvailable(): Boolean = adapter != null
}
