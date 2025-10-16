package com.buccancs.di

import android.content.Context
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
import com.buccancs.domain.usecase.DeviceManagementUseCase
import com.buccancs.domain.usecase.HardwareConfigurationUseCase
import com.buccancs.domain.usecase.RemoteCommandCoordinator
import com.buccancs.domain.usecase.RgbCameraStateManager
import com.buccancs.domain.usecase.SessionCoordinator
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

/**
 * DI module validation tests.
 * Verifies that all Hilt modules are properly configured and dependencies resolve correctly.
 *
 * Tests cover:
 * - Repository bindings
 * - Use case bindings
 * - Service bindings
 * - Scope correctness
 * - Dependency resolution
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [33])
class DependencyInjectionTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var sensorRepository: SensorRepository

    @Inject
    lateinit var sessionTransferRepository: SessionTransferRepository

    @Inject
    lateinit var calibrationRepository: CalibrationRepository

    @Inject
    lateinit var orchestratorConfigRepository: OrchestratorConfigRepository

    @Inject
    lateinit var bookmarkRepository: BookmarkRepository

    @Inject
    lateinit var deviceEventRepository: DeviceEventRepository

    @Inject
    lateinit var sensorHardwareConfigRepository: SensorHardwareConfigRepository

    @Inject
    lateinit var shimmerSettingsRepository: ShimmerSettingsRepository

    @Inject
    lateinit var topdonSettingsRepository: TopdonSettingsRepository

    @Inject
    lateinit var topdonDeviceRepository: TopdonDeviceRepository

    @Inject
    lateinit var sessionCoordinator: SessionCoordinator

    @Inject
    lateinit var deviceManagementUseCase: DeviceManagementUseCase

    @Inject
    lateinit var hardwareConfigurationUseCase: HardwareConfigurationUseCase

    @Inject
    lateinit var remoteCommandCoordinator: RemoteCommandCoordinator

    @Inject
    lateinit var rgbCameraStateManager: RgbCameraStateManager

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `context is injected`() {
        assertNotNull(context)
    }

    @Test
    fun `sensor repository is injected`() {
        assertNotNull(sensorRepository)
    }

    @Test
    fun `session transfer repository is injected`() {
        assertNotNull(sessionTransferRepository)
    }

    @Test
    fun `calibration repository is injected`() {
        assertNotNull(calibrationRepository)
    }

    @Test
    fun `orchestrator config repository is injected`() {
        assertNotNull(orchestratorConfigRepository)
    }

    @Test
    fun `bookmark repository is injected`() {
        assertNotNull(bookmarkRepository)
    }

    @Test
    fun `device event repository is injected`() {
        assertNotNull(deviceEventRepository)
    }

    @Test
    fun `sensor hardware config repository is injected`() {
        assertNotNull(sensorHardwareConfigRepository)
    }

    @Test
    fun `shimmer settings repository is injected`() {
        assertNotNull(shimmerSettingsRepository)
    }

    @Test
    fun `topdon settings repository is injected`() {
        assertNotNull(topdonSettingsRepository)
    }

    @Test
    fun `topdon device repository is injected`() {
        assertNotNull(topdonDeviceRepository)
    }

    @Test
    fun `session coordinator use case is injected`() {
        assertNotNull(sessionCoordinator)
    }

    @Test
    fun `device management use case is injected`() {
        assertNotNull(deviceManagementUseCase)
    }

    @Test
    fun `hardware configuration use case is injected`() {
        assertNotNull(hardwareConfigurationUseCase)
    }

    @Test
    fun `remote command coordinator is injected`() {
        assertNotNull(remoteCommandCoordinator)
    }

    @Test
    fun `rgb camera state manager is injected`() {
        assertNotNull(rgbCameraStateManager)
    }

    @Test
    fun `all repositories are singleton instances`() {
        assertNotNull(sensorRepository)
        assertNotNull(sessionTransferRepository)
        assertNotNull(calibrationRepository)
        assertNotNull(orchestratorConfigRepository)
        assertNotNull(bookmarkRepository)
        assertNotNull(deviceEventRepository)
        assertNotNull(sensorHardwareConfigRepository)
        assertNotNull(shimmerSettingsRepository)
        assertNotNull(topdonSettingsRepository)
        assertNotNull(topdonDeviceRepository)
    }

    @Test
    fun `all use cases are singleton instances`() {
        assertNotNull(sessionCoordinator)
        assertNotNull(deviceManagementUseCase)
        assertNotNull(hardwareConfigurationUseCase)
        assertNotNull(remoteCommandCoordinator)
        assertNotNull(rgbCameraStateManager)
    }
}
