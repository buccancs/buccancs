package com.buccancs.data.sensor.connector.shimmer

import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.sensor.shimmer.InMemoryShimmerSettingsRepository
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.connector.MultiDeviceConnector
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.ShimmerDeviceConfig
import com.buccancs.domain.model.ShimmerSettings
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.sensor.SensorStreamClient
import com.buccancs.hardware.shimmer.ShimmerHardwareClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShimmerConnectorManager @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val artifactFactory: SimulatedArtifactFactory,
    private val streamClient: SensorStreamClient,
    private val recordingStorage: RecordingStorage,
    private val configRepository: SensorHardwareConfigRepository,
    private val hardwareClient: ShimmerHardwareClient,
) : MultiDeviceConnector {
    private val connectorsMutex =
        Mutex()
    private val devicesMutex =
        Mutex()
    private val statusMutex =
        Mutex()
    private val connectors =
        mutableMapOf<DeviceId, ManagedConnector>()
    private val devicesState =
        MutableStateFlow<Map<DeviceId, SensorDevice>>(
            emptyMap()
        )
    private val statusesState =
        MutableStateFlow<Map<DeviceId, List<SensorStreamStatus>>>(
            emptyMap()
        )
    override val devices: StateFlow<Map<DeviceId, SensorDevice>> =
        devicesState.asStateFlow()
    override val streamStatuses: StateFlow<Map<DeviceId, List<SensorStreamStatus>>> =
        statusesState.asStateFlow()

    init {
        scope.launch(
            Dispatchers.Default
        ) {
            configRepository.config.collect { config ->
                rebuildConnectors(
                    config.shimmer
                )
            }
        }
    }

    override suspend fun refreshInventory() {
        val snapshot =
            connectorsSnapshot()
        snapshot.forEach { connector -> connector.refreshInventory() }
    }

    override suspend fun applySimulation(
        enabled: Boolean
    ) {
        val snapshot =
            connectorsSnapshot()
        snapshot.forEach { connector ->
            connector.applySimulation(
                enabled
            )
        }
    }

    override suspend fun connect(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val connector =
            connectorFor(
                deviceId
            )
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Shimmer device ${deviceId.value}"
                )
        return connector.connect()
    }

    override suspend fun disconnect(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val connector =
            connectorFor(
                deviceId
            )
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Shimmer device ${deviceId.value}"
                )
        return connector.disconnect()
    }

    override suspend fun configure(
        deviceId: DeviceId,
        options: Map<String, String>
    ): DeviceCommandResult {
        val connector =
            connectorFor(
                deviceId
            )
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Shimmer device ${deviceId.value}"
                )
        return connector.configure(
            options
        )
    }

    override suspend fun startStreaming(
        deviceId: DeviceId,
        anchor: RecordingSessionAnchor
    ): DeviceCommandResult {
        val connector =
            connectorFor(
                deviceId
            )
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Shimmer device ${deviceId.value}"
                )
        return connector.startStreaming(
            anchor
        )
    }

    override suspend fun stopStreaming(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val connector =
            connectorFor(
                deviceId
            )
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Shimmer device ${deviceId.value}"
                )
        return connector.stopStreaming()
    }

    override suspend fun collectArtifacts(
        deviceId: DeviceId,
        sessionId: String
    ): List<SessionArtifact> {
        val connector =
            connectorFor(
                deviceId
            )
                ?: return emptyList()
        return connector.collectArtifacts(
            sessionId
        )
    }

    private suspend fun rebuildConnectors(
        configs: List<ShimmerDeviceConfig>
    ) {
        val desired =
            configs.associateBy {
                DeviceId(
                    it.id.trim()
                )
            }
        val toRemove =
            connectorsMutex.withLock {
                val existing =
                    connectors.keys.toSet()
                existing - desired.keys
            }
        toRemove.forEach {
            removeConnector(
                it
            )
        }
        desired.forEach { (deviceId, config) ->
            connectorsMutex.withLock {
                val existing =
                    connectors[deviceId]
                if (existing == null) {
                    val managed =
                        createConnector(
                            deviceId,
                            config
                        )
                    connectors[deviceId] =
                        managed
                } else {
                    applyConfig(
                        existing,
                        config
                    )
                }
            }
        }
    }

    private suspend fun removeConnector(
        deviceId: DeviceId
    ) {
        val removed =
            connectorsMutex.withLock {
                connectors.remove(
                    deviceId
                )
            }
                ?: return
        removed.deviceJob.cancel()
        removed.statusJob.cancel()
        removed.configJob.cancel()
        withContext(
            Dispatchers.IO
        ) {
            runCatching { removed.connector.stopStreaming() }
            runCatching { removed.connector.disconnect() }
        }
        devicesMutex.withLock {
            val next =
                devicesState.value.toMutableMap()
            next.remove(
                deviceId
            )
            devicesState.value =
                next.toMap()
        }
        statusMutex.withLock {
            val next =
                statusesState.value.toMutableMap()
            next.remove(
                deviceId
            )
            statusesState.value =
                next.toMap()
        }
    }

    private suspend fun createConnector(
        deviceId: DeviceId,
        config: ShimmerDeviceConfig
    ): ManagedConnector {
        val initialSettings =
            ShimmerSettings(
                targetMacAddress = config.macAddress?.uppercase(),
                gsrRangeIndex = config.gsrRangeIndex?.coerceIn(
                    0,
                    ShimmerSettings.DEFAULT_GSR_RANGE
                )
                    ?: ShimmerSettings.DEFAULT_GSR_RANGE,
                sampleRateHz = config.sampleRateHz?.takeIf { it.isFinite() && it > 0.0 }
                    ?: ShimmerSettings.DEFAULT_SAMPLE_RATE_HZ
            )
        val settingsRepository =
            InMemoryShimmerSettingsRepository(
                initialSettings
            )
        val device =
            SensorDevice(
                id = deviceId,
                displayName = config.displayName.ifBlank { "Shimmer ${deviceId.value}" },
                type = SensorDeviceType.SHIMMER_GSR,
                capabilities = setOf(
                    SensorStreamType.GSR
                ),
                connectionStatus = ConnectionStatus.Disconnected,
                isSimulated = false,
                attributes = buildAttributes(
                    config
                )
            )
        val connector =
            ShimmerSensorConnector(
                appScope = scope,
                hardwareClient = hardwareClient,
                artifactFactory = artifactFactory,
                streamClient = streamClient,
                recordingStorage = recordingStorage,
                shimmerSettingsRepository = settingsRepository,
                initialDevice = device
            )
        var managedRef: ManagedConnector? =
            null
        val deviceJob =
            scope.launch {
                connector.device.collect { snapshot ->
                    devicesMutex.withLock {
                        val next =
                            devicesState.value.toMutableMap()
                        next[deviceId] =
                            snapshot
                        devicesState.value =
                            next.toMap()
                    }
                    managedRef?.let { managed ->
                        val updated =
                            enrichShimmerConfig(
                                managed.config,
                                snapshot
                            )
                        if (updated != managed.config) {
                            managed.config =
                                updated
                            configRepository.upsertShimmerDevice(
                                updated
                            )
                        }
                    }
                }
            }
        val statusJob =
            scope.launch {
                connector.streamStatuses.collect { statuses ->
                    statusMutex.withLock {
                        val next =
                            statusesState.value.toMutableMap()
                        next[deviceId] =
                            statuses
                        statusesState.value =
                            next.toMap()
                    }
                }
            }
        val normalizedConfig =
            config.normalizeDefaults(
                initialSettings
            )
        var currentConfig =
            normalizedConfig
        val configJob =
            scope.launch(
                start = CoroutineStart.LAZY
            ) {
                settingsRepository.settings.collect { settings ->
                    val updated =
                        currentConfig.copy(
                            macAddress = settings.targetMacAddress
                                ?: currentConfig.macAddress,
                            gsrRangeIndex = settings.gsrRangeIndex,
                            sampleRateHz = settings.sampleRateHz
                        )
                    if (updated != currentConfig) {
                        currentConfig =
                            updated
                        managedRef?.config =
                            updated
                        configRepository.upsertShimmerDevice(
                            updated
                        )
                    }
                }
            }
        devicesMutex.withLock {
            val next =
                devicesState.value.toMutableMap()
            next[deviceId] =
                connector.device.value
            devicesState.value =
                next.toMap()
        }
        statusMutex.withLock {
            val next =
                statusesState.value.toMutableMap()
            next[deviceId] =
                connector.streamStatuses.value
            statusesState.value =
                next.toMap()
        }
        val managed =
            ManagedConnector(
                connector = connector,
                settings = settingsRepository,
                deviceJob = deviceJob,
                statusJob = statusJob,
                configJob = configJob,
                config = normalizedConfig
            )
        managedRef =
            managed
        configRepository.upsertShimmerDevice(
            normalizedConfig
        )
        configJob.start()
        return managed
    }

    private suspend fun applyConfig(
        existing: ManagedConnector,
        config: ShimmerDeviceConfig
    ) {
        config.macAddress?.let {
            existing.settings.setTargetMac(
                it
            )
        }
        config.gsrRangeIndex?.let {
            existing.settings.setGsrRange(
                it
            )
        }
        config.sampleRateHz?.let {
            existing.settings.setSampleRate(
                it
            )
        }
        val updated =
            existing.config.copy(
                displayName = config.displayName.ifBlank { existing.config.displayName },
                macAddress = config.macAddress
                    ?: existing.config.macAddress,
                gsrRangeIndex = config.gsrRangeIndex
                    ?: existing.config.gsrRangeIndex,
                sampleRateHz = config.sampleRateHz
                    ?: existing.config.sampleRateHz
            )
        if (updated != existing.config) {
            existing.config =
                updated
            configRepository.upsertShimmerDevice(
                updated
            )
        }
    }

    private suspend fun connectorsSnapshot(): List<ShimmerSensorConnector> =
        connectorsMutex.withLock { connectors.values.map { it.connector } }

    private suspend fun connectorFor(
        deviceId: DeviceId
    ): ShimmerSensorConnector? =
        connectorsMutex.withLock { connectors[deviceId]?.connector }

    private fun buildAttributes(
        config: ShimmerDeviceConfig
    ): Map<String, String> {
        val attributes =
            mutableMapOf<String, String>()
        val mac =
            config.macAddress?.trim()
        if (!mac.isNullOrEmpty()) {
            attributes["config.macAddress"] =
                mac.uppercase()
        }
        config.sampleRateHz?.takeIf { it > 0.0 }
            ?.let {
                attributes["config.sampleRateHz"] =
                    "%.2f".format(
                        it
                    )
            }
        config.gsrRangeIndex?.let {
            attributes["config.gsrRangeIndex"] =
                it.toString()
        }
        return attributes
    }

    private data class ManagedConnector(
        val connector: ShimmerSensorConnector,
        val settings: InMemoryShimmerSettingsRepository,
        val deviceJob: Job,
        val statusJob: Job,
        val configJob: Job,
        var config: ShimmerDeviceConfig
    )

    private fun ShimmerDeviceConfig.normalizeDefaults(
        initial: ShimmerSettings
    ): ShimmerDeviceConfig =
        copy(
            macAddress = macAddress
                ?: initial.targetMacAddress,
            gsrRangeIndex = gsrRangeIndex
                ?: initial.gsrRangeIndex,
            sampleRateHz = sampleRateHz
                ?: initial.sampleRateHz
        )

    private fun enrichShimmerConfig(
        current: ShimmerDeviceConfig,
        device: SensorDevice
    ): ShimmerDeviceConfig {
        val selectedMac =
            device.attributes[ATTR_SELECTED_MAC]?.takeIf { it.isNotBlank() }
        val name =
            device.displayName.takeIf { it.isNotBlank() }
        return current.copy(
            displayName = name
                ?: current.displayName,
            macAddress = selectedMac
                ?: current.macAddress
        )
    }

    private companion object {
        private const val ATTR_SELECTED_MAC =
            "shimmer.selected"
    }
}
