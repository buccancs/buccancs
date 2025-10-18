package com.buccancs.domain.usecase

import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.SensorRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Abstract device connection, discovery, and inventory management.
 * Extracted from MainViewModel to centralise device lifecycle operations.
 */
interface DeviceManagementUseCase {
    val devices: StateFlow<List<SensorDevice>>
    val simulationEnabled: StateFlow<Boolean>
    suspend fun connectDevice(
        deviceId: DeviceId
    ): Result<Unit>

    suspend fun disconnectDevice(
        deviceId: DeviceId
    ): Result<Unit>

    suspend fun refreshInventory(): Result<Unit>
    suspend fun toggleSimulation(): Result<Unit>
}

@Singleton
class DeviceManagementUseCaseImpl @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val hardwareConfigRepository: SensorHardwareConfigRepository
) : DeviceManagementUseCase {

    override val devices: StateFlow<List<SensorDevice>> =
        sensorRepository.devices

    override val simulationEnabled: StateFlow<Boolean> =
        sensorRepository.simulationEnabled

    override suspend fun connectDevice(
        deviceId: DeviceId
    ): Result<Unit> {
        return try {
            sensorRepository.connect(
                deviceId
            )
            Result.success(
                Unit
            )
        } catch (t: Throwable) {
            Result.failure(
                t
            )
        }
    }

    override suspend fun disconnectDevice(
        deviceId: DeviceId
    ): Result<Unit> {
        return try {
            sensorRepository.disconnect(
                deviceId
            )
            Result.success(
                Unit
            )
        } catch (t: Throwable) {
            Result.failure(
                t
            )
        }
    }

    override suspend fun refreshInventory(): Result<Unit> {
        return try {
            sensorRepository.refreshInventory()
            Result.success(
                Unit
            )
        } catch (t: Throwable) {
            Result.failure(
                t
            )
        }
    }

    override suspend fun toggleSimulation(): Result<Unit> {
        return try {
            val next =
                !sensorRepository.simulationEnabled.value
            sensorRepository.setSimulationEnabled(
                next
            )
            Result.success(
                Unit
            )
        } catch (t: Throwable) {
            Result.failure(
                t
            )
        }
    }
}
