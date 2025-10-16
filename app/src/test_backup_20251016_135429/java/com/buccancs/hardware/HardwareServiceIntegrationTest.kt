package com.buccancs.hardware

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Integration test demonstrating how to use mocked hardware services.
 * This test uses TestHardwareModule which provides mocked implementations.
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class HardwareServiceIntegrationTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var bluetoothService: BluetoothService

    @Inject
    lateinit var cameraService: CameraService

    @Inject
    lateinit var usbService: UsbService

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `bluetooth service is mocked and configurable`() {
        // Verify we got a mock
        assertNotNull(bluetoothService)

        // Configure mock behavior for specific test
        every { bluetoothService.isEnabled() } returns true
        every { bluetoothService.isAvailable() } returns true

        // Test
        assertTrue(bluetoothService.isEnabled())
        assertTrue(bluetoothService.isAvailable())

        // Verify interaction
        verify { bluetoothService.isEnabled() }
    }

    @Test
    fun `camera service is mocked and returns test data`() {
        // Configure mock to return test camera IDs
        every { cameraService.getCameraIdList() } returns listOf("0", "1")

        // Test
        val cameras = cameraService.getCameraIdList()
        assertEquals(2, cameras.size)
        assertEquals("0", cameras[0])
        assertEquals("1", cameras[1])
    }

    @Test
    fun `can simulate bluetooth unavailable scenario`() {
        // Configure mock for error scenario
        every { bluetoothService.isAvailable() } returns false
        every { bluetoothService.isEnabled() } returns false

        // Test error handling
        assertFalse(bluetoothService.isAvailable())
        assertFalse(bluetoothService.isEnabled())
    }

    @Test
    fun `can simulate camera error scenarios`() {
        // Configure mock to throw exception
        every { cameraService.getCameraIdList() } throws IllegalStateException("Camera unavailable")

        // Test error handling
        try {
            cameraService.getCameraIdList()
            throw AssertionError("Expected exception")
        } catch (e: IllegalStateException) {
            assertEquals("Camera unavailable", e.message)
        }
    }

    @Test
    fun `usb service returns empty device list by default`() {
        // Relaxed mock returns empty map by default
        val devices = usbService.getDeviceList()
        assertNotNull(devices)
    }
}
