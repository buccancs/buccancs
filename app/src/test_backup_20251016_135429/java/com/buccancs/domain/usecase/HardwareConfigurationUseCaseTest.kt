package com.buccancs.domain.usecase

import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.buccancs.domain.repository.TopdonDeviceRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class HardwareConfigurationUseCaseTest {

    private lateinit var sensorRepository: SensorRepository
    private lateinit var shimmerSettingsRepository: ShimmerSettingsRepository
    private lateinit var hardwareConfigRepository: SensorHardwareConfigRepository
    private lateinit var topdonDeviceRepository: TopdonDeviceRepository
    private lateinit var useCase: HardwareConfigurationUseCaseImpl

    @Before
    fun setup() {
        sensorRepository = mockk(relaxed = true)
        shimmerSettingsRepository = mockk(relaxed = true)
        hardwareConfigRepository = mockk(relaxed = true)
        topdonDeviceRepository = mockk(relaxed = true)

        useCase = HardwareConfigurationUseCaseImpl(
            sensorRepository,
            shimmerSettingsRepository,
            hardwareConfigRepository,
            topdonDeviceRepository
        )
    }

    @Test
    fun `configureShimmerMacAddress with valid mac succeeds`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        val macAddress = "00:11:22:33:44:55"
        coEvery { shimmerSettingsRepository.setTargetMac(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When
        val result = useCase.configureShimmerMacAddress(deviceId, macAddress)

        // Then
        assertTrue(result.isSuccess)
        coVerify { shimmerSettingsRepository.setTargetMac("00:11:22:33:44:55") }
    }

    @Test
    fun `configureShimmerMacAddress normalizes mac to uppercase`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        val macAddress = "aa:bb:cc:dd:ee:ff"
        coEvery { shimmerSettingsRepository.setTargetMac(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When
        val result = useCase.configureShimmerMacAddress(deviceId, macAddress)

        // Then
        assertTrue(result.isSuccess)
        coVerify { shimmerSettingsRepository.setTargetMac("AA:BB:CC:DD:EE:FF") }
    }

    @Test
    fun `configureShimmerMacAddress with blank mac sets null`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        coEvery { shimmerSettingsRepository.setTargetMac(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When
        val result = useCase.configureShimmerMacAddress(deviceId, "   ")

        // Then
        assertTrue(result.isSuccess)
        coVerify { shimmerSettingsRepository.setTargetMac(null) }
    }

    @Test
    fun `configureShimmerMacAddress handles failure`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        coEvery { shimmerSettingsRepository.setTargetMac(any()) } throws RuntimeException("Config failed")

        // When
        val result = useCase.configureShimmerMacAddress(deviceId, "00:11:22:33:44:55")

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `configureShimmerGsrRange clamps to valid range`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        coEvery { shimmerSettingsRepository.setGsrRange(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When
        val result = useCase.configureShimmerGsrRange(deviceId, 10) // Out of range

        // Then
        assertTrue(result.isSuccess)
        coVerify { shimmerSettingsRepository.setGsrRange(4) } // Clamped to max
    }

    @Test
    fun `configureShimmerGsrRange accepts valid range`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        coEvery { shimmerSettingsRepository.setGsrRange(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When
        val result = useCase.configureShimmerGsrRange(deviceId, 2)

        // Then
        assertTrue(result.isSuccess)
        coVerify { shimmerSettingsRepository.setGsrRange(2) }
    }

    @Test
    fun `configureShimmerGsrRange handles negative values`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        coEvery { shimmerSettingsRepository.setGsrRange(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When
        val result = useCase.configureShimmerGsrRange(deviceId, -5)

        // Then
        assertTrue(result.isSuccess)
        coVerify { shimmerSettingsRepository.setGsrRange(0) } // Clamped to min
    }

    @Test
    fun `configureShimmerSampleRate with valid rate succeeds`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        val sampleRate = 64.0
        coEvery { shimmerSettingsRepository.setSampleRate(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When
        val result = useCase.configureShimmerSampleRate(deviceId, sampleRate)

        // Then
        assertTrue(result.isSuccess)
        coVerify { shimmerSettingsRepository.setSampleRate(64.0) }
    }

    @Test
    fun `configureShimmerSampleRate sanitizes invalid values`() = runTest {
        // Given
        val deviceId = DeviceId("shimmer-primary")
        coEvery { shimmerSettingsRepository.setSampleRate(any()) } returns Unit
        coEvery { hardwareConfigRepository.updateConfig(any()) } returns Unit

        // When - Invalid values
        useCase.configureShimmerSampleRate(deviceId, -10.0)
        useCase.configureShimmerSampleRate(deviceId, Double.NaN)
        useCase.configureShimmerSampleRate(deviceId, Double.POSITIVE_INFINITY)

        // Then - All sanitized to default (128.0)
        coVerify(exactly = 3) { shimmerSettingsRepository.setSampleRate(128.0) }
    }

    @Test
    fun `setActiveTopdon succeeds`() = runTest {
        // Given
        val deviceId = DeviceId("topdon-1")
        coEvery { topdonDeviceRepository.setActiveDevice(deviceId) } returns Unit

        // When
        val result = useCase.setActiveTopdon(deviceId)

        // Then
        assertTrue(result.isSuccess)
        coVerify { topdonDeviceRepository.setActiveDevice(deviceId) }
    }

    @Test
    fun `setActiveTopdon handles failure`() = runTest {
        // Given
        val deviceId = DeviceId("topdon-1")
        coEvery { topdonDeviceRepository.setActiveDevice(deviceId) } throws RuntimeException("Selection failed")

        // When
        val result = useCase.setActiveTopdon(deviceId)

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `configureRgbCamera succeeds`() = runTest {
        // Given
        val deviceId = DeviceId("rgb-camera")
        val settings = mapOf(
            "video_fps" to "60",
            "video_bitrate" to "75000000"
        )
        coEvery { sensorRepository.configure(deviceId, settings) } returns Unit

        // When
        val result = useCase.configureRgbCamera(deviceId, settings)

        // Then
        assertTrue(result.isSuccess)
        coVerify { sensorRepository.configure(deviceId, settings) }
    }

    @Test
    fun `configureRgbCamera handles failure`() = runTest {
        // Given
        val deviceId = DeviceId("rgb-camera")
        val settings = mapOf("video_fps" to "60")
        coEvery { sensorRepository.configure(any(), any()) } throws RuntimeException("Config failed")

        // When
        val result = useCase.configureRgbCamera(deviceId, settings)

        // Then
        assertTrue(result.isFailure)
    }
}
