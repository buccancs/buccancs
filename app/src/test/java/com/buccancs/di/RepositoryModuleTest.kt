package com.buccancs.di

import com.buccancs.domain.repository.BookmarkRepository
import com.buccancs.domain.repository.CalibrationRepository
import com.buccancs.domain.repository.DeviceEventRepository
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.SessionTransferRepository
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.buccancs.domain.repository.TopdonDeviceRepository
import com.buccancs.domain.repository.TopdonSettingsRepository
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
class RepositoryModuleTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var sensorRepository: SensorRepository

    @Inject
    lateinit var calibrationRepository: CalibrationRepository

    @Inject
    lateinit var bookmarkRepository: BookmarkRepository

    @Inject
    lateinit var deviceEventRepository: DeviceEventRepository

    @Inject
    lateinit var sensorHardwareConfigRepository: SensorHardwareConfigRepository

    @Inject
    lateinit var sessionTransferRepository: SessionTransferRepository

    @Inject
    lateinit var shimmerSettingsRepository: ShimmerSettingsRepository

    @Inject
    lateinit var topdonSettingsRepository: TopdonSettingsRepository

    @Inject
    lateinit var topdonDeviceRepository: TopdonDeviceRepository

    @Inject
    lateinit var orchestratorConfigRepository: OrchestratorConfigRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `all repositories are injectable`() {
        assertNotNull(sensorRepository)
        assertNotNull(calibrationRepository)
        assertNotNull(bookmarkRepository)
        assertNotNull(deviceEventRepository)
        assertNotNull(sensorHardwareConfigRepository)
        assertNotNull(sessionTransferRepository)
        assertNotNull(shimmerSettingsRepository)
        assertNotNull(topdonSettingsRepository)
        assertNotNull(topdonDeviceRepository)
        assertNotNull(orchestratorConfigRepository)
    }
}
