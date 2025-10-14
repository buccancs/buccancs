package com.buccancs.di

import com.buccancs.data.preview.PreviewStreamClient
import com.buccancs.data.sensor.SensorStreamClient
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
class StreamModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var sensorStreamClient: SensorStreamClient
    @Inject
    lateinit var previewStreamClient: PreviewStreamClient

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `all stream clients are injectable`() {
        assertNotNull(sensorStreamClient)
        assertNotNull(previewStreamClient)
    }
}
