package com.buccancs.di

import com.buccancs.hardware.BluetoothService
import com.buccancs.hardware.CameraService
import com.buccancs.hardware.UsbService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertNotNull

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class HardwareModuleTest {
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
    fun `all hardware services are injectable`() {
        assertNotNull(bluetoothService)
        assertNotNull(cameraService)
        assertNotNull(usbService)
    }
}
