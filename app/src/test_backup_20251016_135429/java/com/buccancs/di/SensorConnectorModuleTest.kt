package com.buccancs.di

import com.buccancs.data.sensor.connector.MultiDeviceConnector
import com.buccancs.data.sensor.connector.SensorConnector
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
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class SensorConnectorModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var sensorConnectors: Set<@JvmSuppressWildcards SensorConnector>

    @Inject
    lateinit var multiDeviceConnectors: Set<@JvmSuppressWildcards MultiDeviceConnector>

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `sensor connector set contains expected connectors`() {
        assertTrue(sensorConnectors.isNotEmpty(), "Should have at least one sensor connector")
        assertEquals(2, sensorConnectors.size, "Should have 2 sensor connectors (RGB, Microphone)")
    }

    @Test
    fun `multi-device connector set contains expected connectors`() {
        assertTrue(multiDeviceConnectors.isNotEmpty(), "Should have at least one multi-device connector")
        assertEquals(2, multiDeviceConnectors.size, "Should have 2 multi-device connectors (Shimmer, Topdon)")
    }
}
