package com.buccancs.domain.usecase

import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import com.buccancs.domain.repository.ShimmerSettingsRepository
import com.buccancs.domain.repository.TopdonDeviceRepository
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Device-specific configuration operations for Shimmer, Topdon, and RGB Camera.
 * Extracted from MainViewModel to isolate hardware configuration logic.
 */
interface HardwareConfigurationUseCase {
    // Shimmer operations
    suspend fun configureShimmerMacAddress(deviceId: DeviceId, macAddress: String?): Result<Unit>
    suspend fun configureShimmerGsrRange(deviceId: DeviceId, rangeIndex: Int): Result<Unit>
    suspend fun configureShimmerSampleRate(deviceId: DeviceId, sampleRateHz: Double): Result<Unit>

    // Topdon operations
    suspend fun setActiveTopdon(deviceId: DeviceId): Result<Unit>

    // RGB Camera operations
    suspend fun configureRgbCamera(deviceId: DeviceId, settings: Map<String, String>): Result<Unit>
}

@Singleton
class HardwareConfigurationUseCaseImpl @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val shimmerSettingsRepository: ShimmerSettingsRepository,
    private val hardwareConfigRepository: SensorHardwareConfigRepository,
    private val topdonDeviceRepository: TopdonDeviceRepository
) : HardwareConfigurationUseCase {

    override suspend fun configureShimmerMacAddress(deviceId: DeviceId, macAddress: String?): Result<Unit> {
        return try {
            val normalized = macAddress?.takeIf { it.isNotBlank() }?.uppercase(Locale.US)
            
            // Update shimmer settings repository
            shimmerSettingsRepository.setTargetMac(normalized)
            
            // Update hardware config repository
            hardwareConfigRepository.updateConfig { config ->
                val updated = config.shimmer.map { entry ->
                    if (entry.id == deviceId.value) {
                        entry.copy(macAddress = normalized)
                    } else {
                        entry
                    }
                }
                config.copy(shimmer = updated)
            }
            
            Result.success(Unit)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun configureShimmerGsrRange(deviceId: DeviceId, rangeIndex: Int): Result<Unit> {
        return try {
            val normalized = rangeIndex.coerceIn(0, 4) // 0-4 for Shimmer GSR ranges
            
            // Update shimmer settings repository
            shimmerSettingsRepository.setGsrRange(normalized)
            
            // Update hardware config repository
            hardwareConfigRepository.updateConfig { config ->
                val updated = config.shimmer.map { entry ->
                    if (entry.id == deviceId.value) {
                        entry.copy(gsrRangeIndex = normalized)
                    } else {
                        entry
                    }
                }
                config.copy(shimmer = updated)
            }
            
            Result.success(Unit)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun configureShimmerSampleRate(deviceId: DeviceId, sampleRateHz: Double): Result<Unit> {
        return try {
            val sanitized = if (sampleRateHz.isFinite() && sampleRateHz > 0.0) {
                sampleRateHz
            } else {
                128.0 // Default sample rate
            }
            
            // Update shimmer settings repository
            shimmerSettingsRepository.setSampleRate(sanitized)
            
            // Update hardware config repository
            hardwareConfigRepository.updateConfig { config ->
                val updated = config.shimmer.map { entry ->
                    if (entry.id == deviceId.value) {
                        entry.copy(sampleRateHz = sanitized)
                    } else {
                        entry
                    }
                }
                config.copy(shimmer = updated)
            }
            
            Result.success(Unit)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun setActiveTopdon(deviceId: DeviceId): Result<Unit> {
        return try {
            topdonDeviceRepository.setActiveDevice(deviceId)
            Result.success(Unit)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun configureRgbCamera(deviceId: DeviceId, settings: Map<String, String>): Result<Unit> {
        return try {
            sensorRepository.configure(deviceId, settings)
            Result.success(Unit)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}
