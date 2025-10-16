package com.buccancs.di

import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.recording.RecordingService
import com.buccancs.application.time.TimeSyncService
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
class ServiceModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var recordingService: RecordingService

    @Inject
    lateinit var deviceCommandService: DeviceCommandService

    @Inject
    lateinit var timeSyncService: TimeSyncService

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `all services are injectable`() {
        assertNotNull(recordingService)
        assertNotNull(deviceCommandService)
        assertNotNull(timeSyncService)
    }
}
