package com.buccancs.core.result

import android.bluetooth.BluetoothAdapter
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.io.IOException

class BluetoothResultHelpersTest {

    @Test
    fun `checkAvailable returns failure when adapter is null`() {
        val adapter: BluetoothAdapter? = null
        val result = adapter.checkAvailable()

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Bluetooth)
        assertNull((error as Error.Bluetooth).deviceId)
        assertTrue(error.message.contains("not available"))
    }

    @Test
    fun `checkAvailable returns failure when adapter is disabled`() {
        val adapter = mock(BluetoothAdapter::class.java)
        `when`(adapter.isEnabled).thenReturn(false)

        val result = adapter.checkAvailable()

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Bluetooth)
        assertTrue(error.message.contains("disabled"))
    }

    @Test
    fun `checkAvailable returns success when adapter is enabled`() {
        val adapter = mock(BluetoothAdapter::class.java)
        `when`(adapter.isEnabled).thenReturn(true)

        val result = adapter.checkAvailable()

        assertTrue(result.isSuccess())
        assertEquals(adapter, result.getOrNull())
    }

    @Test
    fun `bluetoothOperation wraps successful operation`() = runTest {
        val result = bluetoothOperation("00:11:22:33:44:55") {
            "success"
        }

        assertTrue(result.isSuccess())
        assertEquals("success", result.getOrNull())
    }

    @Test
    fun `bluetoothOperation converts SecurityException to Permission error`() = runTest {
        val result = bluetoothOperation("00:11:22:33:44:55") {
            throw SecurityException("Permission denied")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Permission)
        assertEquals("BLUETOOTH_CONNECT", (error as Error.Permission).permission)
    }

    @Test
    fun `bluetoothOperation converts IOException to Bluetooth error`() = runTest {
        val deviceId = "00:11:22:33:44:55"
        val result = bluetoothOperation(deviceId) {
            throw IOException("Connection failed")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Bluetooth)
        assertEquals(deviceId, (error as Error.Bluetooth).deviceId)
        assertTrue(error.message.contains("I/O operation failed"))
    }

    @Test
    fun `bluetoothOperation converts IllegalArgumentException to Validation error`() = runTest {
        val result = bluetoothOperation("invalid") {
            throw IllegalArgumentException("Invalid device ID")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Validation)
        assertEquals("deviceId", (error as Error.Validation).field)
    }

    @Test
    fun `bluetoothOperation converts generic exception to Bluetooth error`() = runTest {
        val deviceId = "00:11:22:33:44:55"
        val result = bluetoothOperation(deviceId) {
            throw RuntimeException("Unknown error")
        }

        assertTrue(result.isFailure())
        val error = result.errorOrNull()
        assertTrue(error is Error.Bluetooth)
        assertEquals(deviceId, (error as Error.Bluetooth).deviceId)
    }
}
