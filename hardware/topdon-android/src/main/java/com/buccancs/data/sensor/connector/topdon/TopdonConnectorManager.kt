package com.buccancs.data.sensor.connector.topdon

import android.content.Context
import android.util.Log
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.sensor.connector.topdon.capture.TopdonCaptureManager
import com.buccancs.data.sensor.topdon.InMemoryTopdonSettingsRepository
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.connector.MultiDeviceConnector
import com.buccancs.domain.model.ConnectionStatus
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.RecordingSessionAnchor
import com.buccancs.domain.model.SensorDevice
import com.buccancs.domain.model.SensorDeviceType
import com.buccancs.domain.model.SensorHardwareConfig
import com.buccancs.domain.model.SensorStreamStatus
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.model.SessionArtifact
import com.buccancs.domain.model.TopdonDeviceConfig
import com.buccancs.domain.model.TopdonPreviewFrame
import com.buccancs.domain.model.TopdonSettings
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import com.buccancs.domain.repository.TopdonReportRepository
import com.buccancs.hardware.topdon.TopdonThermalClient
import dagger.hilt.android.qualifiers.ApplicationContext
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
import kotlin.time.Instant
import kotlin.time.Instant.Companion.fromEpochMilliseconds

@Singleton
class TopdonConnectorManager @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    @ApplicationContext private val context: Context,
    private val recordingStorage: RecordingArtifactStorage,
    private val thermalNormalizer: ThermalNormalizer,
    private val artifactFactory: SimulatedArtifactFactory,
    private val configRepository: SensorHardwareConfigRepository,
    private val hardwareClient: TopdonThermalClient,
    private val reportRepository: TopdonReportRepository,
) : MultiDeviceConnector {
    private val connectorsMutex =
        Mutex()
    private val devicesMutex =
        Mutex()
    private val statusMutex =
        Mutex()
    private val connectors =
        mutableMapOf<DeviceId, ManagedConnector>()

    @Volatile
    private var connectorCache: Map<DeviceId, ManagedConnector> =
        emptyMap()
    private val deviceState =
        MutableStateFlow<Map<DeviceId, SensorDevice>>(
            emptyMap()
        )
    private val statusState =
        MutableStateFlow<Map<DeviceId, List<SensorStreamStatus>>>(
            emptyMap()
        )
    override val devices: StateFlow<Map<DeviceId, SensorDevice>> =
        deviceState.asStateFlow()
    override val streamStatuses: StateFlow<Map<DeviceId, List<SensorStreamStatus>>> =
        statusState.asStateFlow()

    init {
        scope.launch(
            Dispatchers.Default
        ) {
            configRepository.config.collect { config ->
                rebuild(
                    config
                )
            }
        }
    }

    override suspend fun refreshInventory() {
        val snapshot =
            connectorsSnapshot()
        snapshot.forEach { it.refreshInventory() }
    }

    override suspend fun applySimulation(
        enabled: Boolean
    ) {
        val snapshot =
            connectorsSnapshot()
        snapshot.forEach {
            it.applySimulation(
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
                    "Unknown Topdon device ${deviceId.value}"
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
                    "Unknown Topdon device ${deviceId.value}"
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
                    "Unknown Topdon device ${deviceId.value}"
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
                    "Unknown Topdon device ${deviceId.value}"
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
                    "Unknown Topdon device ${deviceId.value}"
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

    fun previewFrame(
        deviceId: DeviceId
    ): StateFlow<TopdonPreviewFrame?>? {
        return connectorCache[deviceId]?.previewFrameFlow
    }

    fun previewRunning(
        deviceId: DeviceId
    ): StateFlow<Boolean>? {
        return connectorCache[deviceId]?.previewRunningFlow
    }

    fun lastCalibration(
        deviceId: DeviceId
    ): StateFlow<Instant?>? {
        return connectorCache[deviceId]?.calibrationFlow
    }

    suspend fun startPreview(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val managed =
            connectorCache[deviceId]
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Topdon device ${deviceId.value}"
                )
        return managed.connector.startPreview()
    }

    suspend fun stopPreview(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val managed =
            connectorCache[deviceId]
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Topdon device ${deviceId.value}"
                )
        return managed.connector.stopPreview()
    }

    suspend fun triggerManualCalibration(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val managed =
            connectorCache[deviceId]
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Topdon device ${deviceId.value}"
                )
        return managed.connector.triggerManualCalibration()
    }

    suspend fun capturePhoto(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val managed =
            connectorCache[deviceId]
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Topdon device ${deviceId.value}"
                )

        val frame =
            managed.previewFrameFlow.value
                ?: return DeviceCommandResult.Rejected(
                    "No preview frame available"
                )
        val activeSettings =
            managed.settings.settings.value

        return withContext(Dispatchers.IO) {
            managed.captureManager.capturePhoto(
                frame = frame,
                settings = activeSettings
            ).fold(
                onSuccess = { result ->
                    Log.i(
                        "TopdonConnectorManager",
                        "Photo saved to ${result.file.absolutePath}"
                    )
                    DeviceCommandResult.Accepted
                },
                onFailure = { error ->
                    Log.e(
                        "TopdonConnectorManager",
                        "Failed to capture photo",
                        error
                    )
                    DeviceCommandResult.Failed(error)
                }
            )
        }
    }

    suspend fun startRecording(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val managed =
            connectorCache[deviceId]
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Topdon device ${deviceId.value}"
                )

        val sessionId =
            "standalone_${System.currentTimeMillis()}"
        val anchor =
            RecordingSessionAnchor(
                sessionId = sessionId,
                referenceTimestamp = fromEpochMilliseconds(
                    System.currentTimeMillis()
                ),
                sharedClockOffsetMillis = 0L
            )

        return managed.connector.startStreaming(
            anchor
        )
    }

    suspend fun stopRecording(
        deviceId: DeviceId
    ): DeviceCommandResult {
        val managed =
            connectorCache[deviceId]
                ?: return DeviceCommandResult.Rejected(
                    "Unknown Topdon device ${deviceId.value}"
                )

        return managed.connector.stopStreaming()
    }

    private suspend fun rebuild(
        config: SensorHardwareConfig
    ) {
        val entries =
            config.topdon.associateBy {
                DeviceId(
                    it.id.trim()
                )
            }
        val toRemove =
            connectorsMutex.withLock {
                val existing =
                    connectors.keys.toSet()
                existing - entries.keys
            }
        toRemove.forEach {
            removeConnector(
                it
            )
        }
        entries.forEach { (deviceId, entry) ->
            connectorsMutex.withLock {
                val existing =
                    connectors[deviceId]
                if (existing == null) {
                    val managed =
                        createConnector(
                            deviceId,
                            entry
                        )
                    connectors[deviceId] =
                        managed
                } else {
                    applyConfig(
                        existing,
                        entry
                    )
                }
                refreshConnectorCacheLocked()
            }
        }
    }

    private suspend fun removeConnector(
        deviceId: DeviceId
    ) {
        val managed =
            connectorsMutex.withLock {
                val removed =
                    connectors.remove(
                        deviceId
                    )
                refreshConnectorCacheLocked()
                removed
            }
                ?: return
        managed.deviceJob.cancel()
        managed.statusJob.cancel()
        managed.configJob.cancel()
        withContext(
            Dispatchers.IO
        ) {
            runCatching { managed.connector.stopStreaming() }
            runCatching { managed.connector.disconnect() }
        }
        devicesMutex.withLock {
            val next =
                deviceState.value.toMutableMap()
            next.remove(
                deviceId
            )
            deviceState.value =
                next.toMap()
        }
        statusMutex.withLock {
            val next =
                statusState.value.toMutableMap()
            next.remove(
                deviceId
            )
            statusState.value =
                next.toMap()
        }
    }

    private suspend fun createConnector(
        deviceId: DeviceId,
        entry: TopdonDeviceConfig
    ): ManagedConnector {
        val defaultSettings =
            TopdonSettings()
        val settings =
            InMemoryTopdonSettingsRepository(
                TopdonSettings(
                    autoConnectOnAttach = entry.autoConnectOnAttach
                        ?: defaultSettings.autoConnectOnAttach,
                    palette = entry.palette
                        ?: defaultSettings.palette,
                    superSampling = entry.superSampling
                        ?: defaultSettings.superSampling,
                    previewFpsLimit = entry.previewFpsLimit
                        ?: TopdonSettings.DEFAULT_PREVIEW_FPS_LIMIT,
                    emissivity = entry.emissivity
                        ?: defaultSettings.emissivity,
                    gainMode = entry.gainMode
                        ?: defaultSettings.gainMode,
                    autoShutterEnabled = entry.autoShutterEnabled
                        ?: defaultSettings.autoShutterEnabled,
                    dynamicRange = entry.dynamicRange
                        ?: defaultSettings.dynamicRange,
                    distanceMeters = entry.distanceMeters
                        ?: defaultSettings.distanceMeters,
                    temperatureUnit = entry.temperatureUnit
                        ?: defaultSettings.temperatureUnit,
                    ambientTemperatureCelsius = entry.ambientTemperatureCelsius
                        ?: defaultSettings.ambientTemperatureCelsius,
                    ambientHumidityPercent = entry.ambientHumidityPercent
                        ?: defaultSettings.ambientHumidityPercent
                )
            )
        scope.launch {
            reportRepository.updateEnvironment(
                settings.settings.value.ambientTemperatureCelsius,
                settings.settings.value.ambientHumidityPercent
            )
        }
        val device =
            SensorDevice(
                id = deviceId,
                displayName = entry.displayName.ifBlank { "Topdon ${deviceId.value}" },
                type = SensorDeviceType.TOPDON_TC001,
                capabilities = setOf(
                    SensorStreamType.THERMAL_VIDEO,
                    SensorStreamType.PREVIEW
                ),
                connectionStatus = ConnectionStatus.Disconnected,
                isSimulated = false,
                attributes = buildAttributes(
                    entry
                )
            )
        val captureManager =
            TopdonCaptureManager(
                context = context,
                deviceId = deviceId,
                normalizer = thermalNormalizer,
                reportRepository = reportRepository
            )
        val connector =
            TopdonThermalConnector(
                appScope = scope,
                hardwareClient = hardwareClient,
                recordingStorage = recordingStorage,
                artifactFactory = artifactFactory,
                settingsRepository = settings,
                captureManager = captureManager,
                initialDevice = device
            )
        val normalizedConfig =
            entry.normalizeDefaults(
                settings.settings.value
            )
        var currentConfig =
            normalizedConfig
        var managedRef: ManagedConnector? =
            null
        val deviceJob =
            scope.launch {
                connector.device.collect { snapshot ->
                    devicesMutex.withLock {
                        val next =
                            deviceState.value.toMutableMap()
                        next[deviceId] =
                            snapshot
                        deviceState.value =
                            next.toMap()
                    }
                    managedRef?.let { managed ->
                        val updated =
                            enrichTopdonConfig(
                                managed.config,
                                snapshot
                            )
                        if (updated != managed.config) {
                            managed.config =
                                updated
                            currentConfig =
                                updated
                            configRepository.upsertTopdonDevice(
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
                            statusState.value.toMutableMap()
                        next[deviceId] =
                            statuses
                        statusState.value =
                            next.toMap()
                    }
                }
            }
        val configJob =
            scope.launch(
                start = CoroutineStart.LAZY
            ) {
                settings.settings.collect { userSettings ->
                    reportRepository.updateEnvironment(
                        userSettings.ambientTemperatureCelsius,
                        userSettings.ambientHumidityPercent
                    )
                    val updated =
                        currentConfig.copy(
                            autoConnectOnAttach = userSettings.autoConnectOnAttach,
                            palette = userSettings.palette,
                            superSampling = userSettings.superSampling,
                            previewFpsLimit = userSettings.previewFpsLimit,
                            emissivity = userSettings.emissivity,
                            gainMode = userSettings.gainMode,
                            autoShutterEnabled = userSettings.autoShutterEnabled,
                            dynamicRange = userSettings.dynamicRange,
                            distanceMeters = userSettings.distanceMeters,
                            temperatureUnit = userSettings.temperatureUnit,
                            ambientTemperatureCelsius = userSettings.ambientTemperatureCelsius,
                            ambientHumidityPercent = userSettings.ambientHumidityPercent
                        )
                    if (updated != currentConfig) {
                        currentConfig =
                            updated
                        managedRef?.config =
                            updated
                        configRepository.upsertTopdonDevice(
                            updated
                        )
                    }
                }
            }
        devicesMutex.withLock {
            val next =
                deviceState.value.toMutableMap()
            next[deviceId] =
                connector.device.value
            deviceState.value =
                next.toMap()
        }
        statusMutex.withLock {
            val next =
                statusState.value.toMutableMap()
            next[deviceId] =
                connector.streamStatuses.value
            statusState.value =
                next.toMap()
        }
        val managed =
            ManagedConnector(
                connector = connector,
                settings = settings,
                deviceJob = deviceJob,
                statusJob = statusJob,
                configJob = configJob,
                config = normalizedConfig,
                previewFrameFlow = connector.previewFrameFlow,
                previewRunningFlow = connector.previewRunningFlow,
                calibrationFlow = connector.lastCalibrationFlow,
                captureManager = captureManager
            )
        managedRef =
            managed
        configRepository.upsertTopdonDevice(
            normalizedConfig
        )
        configJob.start()
        return managed
    }

    private suspend fun applyConfig(
        existing: ManagedConnector,
        entry: TopdonDeviceConfig
    ) {
        entry.autoConnectOnAttach?.let {
            existing.settings.setAutoConnect(
                it
            )
        }
        entry.palette?.let {
            existing.settings.setPalette(
                it
            )
        }
        entry.superSampling?.let {
            existing.settings.setSuperSampling(
                it
            )
        }
        entry.previewFpsLimit?.let {
            existing.settings.setPreviewFpsLimit(
                it
            )
        }
        entry.emissivity?.let {
            existing.settings.setEmissivity(
                it
            )
        }
        entry.gainMode?.let {
            existing.settings.setGainMode(
                it
            )
        }
        entry.autoShutterEnabled?.let {
            existing.settings.setAutoShutter(
                it
            )
        }
        entry.dynamicRange?.let {
            existing.settings.setDynamicRange(
                it
            )
        }
        entry.distanceMeters?.let {
            existing.settings.setDistanceMeters(
                it
            )
        }
        entry.temperatureUnit?.let {
            existing.settings.setTemperatureUnit(
                it
            )
        }
        entry.ambientTemperatureCelsius?.let {
            existing.settings.setAmbientTemperatureCelsius(
                it
            )
        }
        entry.ambientHumidityPercent?.let {
            existing.settings.setAmbientHumidityPercent(
                it
            )
        }
        val updated =
            existing.config.copy(
                displayName = entry.displayName.ifBlank { existing.config.displayName },
                vendorId = entry.vendorId
                    ?: existing.config.vendorId,
                productId = entry.productId
                    ?: existing.config.productId,
                serialNumber = entry.serialNumber
                    ?: existing.config.serialNumber,
                autoConnectOnAttach = entry.autoConnectOnAttach
                    ?: existing.config.autoConnectOnAttach,
                palette = entry.palette
                    ?: existing.config.palette,
                superSampling = entry.superSampling
                    ?: existing.config.superSampling,
                previewFpsLimit = entry.previewFpsLimit
                    ?: existing.config.previewFpsLimit,
                emissivity = entry.emissivity
                    ?: existing.config.emissivity,
                gainMode = entry.gainMode
                    ?: existing.config.gainMode,
                autoShutterEnabled = entry.autoShutterEnabled
                    ?: existing.config.autoShutterEnabled,
                dynamicRange = entry.dynamicRange
                    ?: existing.config.dynamicRange,
                distanceMeters = entry.distanceMeters
                    ?: existing.config.distanceMeters,
                temperatureUnit = entry.temperatureUnit
                    ?: existing.config.temperatureUnit,
                ambientTemperatureCelsius = entry.ambientTemperatureCelsius
                    ?: existing.config.ambientTemperatureCelsius,
                ambientHumidityPercent = entry.ambientHumidityPercent
                    ?: existing.config.ambientHumidityPercent
            )
        if (updated != existing.config) {
            existing.config =
                updated
            configRepository.upsertTopdonDevice(
                updated
            )
        }
    }

    private suspend fun connectorsSnapshot(): List<TopdonThermalConnector> =
        connectorsMutex.withLock { connectors.values.map { it.connector } }

    private suspend fun connectorFor(
        deviceId: DeviceId
    ): TopdonThermalConnector? =
        connectorsMutex.withLock { connectors[deviceId]?.connector }

    private fun buildAttributes(
        entry: TopdonDeviceConfig
    ): Map<String, String> {
        val attributes =
            mutableMapOf<String, String>()
        entry.vendorId?.let {
            attributes["usb.vendorId"] =
                "0x${
                    it.toString(
                        16
                    )
                }"
        }
        entry.productId?.let {
            attributes["usb.productId"] =
                "0x${
                    it.toString(
                        16
                    )
                }"
        }
        entry.serialNumber?.takeIf { it.isNotBlank() }
            ?.let {
                attributes["usb.serialNumber"] =
                    it
            }
        return attributes
    }

    private fun refreshConnectorCacheLocked() {
        connectorCache =
            connectors.toMap()
    }

    private fun TopdonDeviceConfig.normalizeDefaults(
        settings: TopdonSettings
    ): TopdonDeviceConfig =
        copy(
            displayName = displayName.ifBlank { "Topdon ${id}" },
            autoConnectOnAttach = autoConnectOnAttach
                ?: settings.autoConnectOnAttach,
            palette = palette
                ?: settings.palette,
            superSampling = superSampling
                ?: settings.superSampling,
            previewFpsLimit = previewFpsLimit
                ?: settings.previewFpsLimit,
            emissivity = emissivity
                ?: settings.emissivity,
            gainMode = gainMode
                ?: settings.gainMode,
            autoShutterEnabled = autoShutterEnabled
                ?: settings.autoShutterEnabled,
            dynamicRange = dynamicRange
                ?: settings.dynamicRange,
            distanceMeters = distanceMeters
                ?: settings.distanceMeters,
            temperatureUnit = temperatureUnit
                ?: settings.temperatureUnit,
            ambientTemperatureCelsius = ambientTemperatureCelsius
                ?: settings.ambientTemperatureCelsius,
            ambientHumidityPercent = ambientHumidityPercent
                ?: settings.ambientHumidityPercent
        )

    private fun enrichTopdonConfig(
        current: TopdonDeviceConfig,
        device: SensorDevice
    ): TopdonDeviceConfig {
        val vendor =
            device.attributes["usb.vendorId"]?.decodeHexInt()
        val product =
            device.attributes["usb.productId"]?.decodeHexInt()
        val serial =
            device.attributes["usb.serialNumber"]?.takeIf { it.isNotBlank() }
        val name =
            device.displayName.takeIf { it.isNotBlank() }
        return current.copy(
            displayName = name
                ?: current.displayName,
            vendorId = vendor
                ?: current.vendorId,
            productId = product
                ?: current.productId,
            serialNumber = serial
                ?: current.serialNumber
        )
    }

    private fun String.decodeHexInt(): Int? {
        val normalized =
            removePrefix(
                "0x"
            ).removePrefix(
                "0X"
            )
        return normalized.toIntOrNull(
            16
        )
    }

    private data class ManagedConnector(
        val connector: TopdonThermalConnector,
        val settings: InMemoryTopdonSettingsRepository,
        val deviceJob: Job,
        val statusJob: Job,
        val configJob: Job,
        var config: TopdonDeviceConfig,
        val previewFrameFlow: StateFlow<TopdonPreviewFrame?>,
        val previewRunningFlow: StateFlow<Boolean>,
        val calibrationFlow: StateFlow<Instant?>,
        val captureManager: TopdonCaptureManager
    )
}
