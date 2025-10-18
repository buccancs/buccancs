package com.buccancs.data.sensor.connector.topdon

import android.content.Context
import android.util.Log
import com.buccancs.core.result.DeviceCommandResult
import com.buccancs.data.sensor.connector.MultiDeviceConnector
import com.buccancs.data.sensor.connector.simulated.SimulatedArtifactFactory
import com.buccancs.data.sensor.topdon.InMemoryTopdonSettingsRepository
import com.buccancs.data.storage.RecordingStorage
import com.buccancs.di.ApplicationScope
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
import kotlin.time.Instant.Companion.fromEpochMilliseconds

@Singleton
internal class TopdonConnectorManager @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    @ApplicationContext private val context: Context,
    private val recordingStorage: RecordingStorage,
    private val thermalNormalizer: ThermalNormalizer,
    private val artifactFactory: SimulatedArtifactFactory,
    private val configRepository: SensorHardwareConfigRepository,
    private val hardwareClient: TopdonThermalClient,
) : MultiDeviceConnector {
    private val connectorsMutex = Mutex()
    private val devicesMutex = Mutex()
    private val statusMutex = Mutex()
    private val connectors = mutableMapOf<DeviceId, ManagedConnector>()

    @Volatile
    private var connectorCache: Map<DeviceId, ManagedConnector> = emptyMap()
    private val deviceState = MutableStateFlow<Map<DeviceId, SensorDevice>>(emptyMap())
    private val statusState = MutableStateFlow<Map<DeviceId, List<SensorStreamStatus>>>(emptyMap())
    override val devices: StateFlow<Map<DeviceId, SensorDevice>> = deviceState.asStateFlow()
    override val streamStatuses: StateFlow<Map<DeviceId, List<SensorStreamStatus>>> =
        statusState.asStateFlow()

    init {
        scope.launch(Dispatchers.Default) {
            configRepository.config.collect { config ->
                rebuild(config)
            }
        }
    }

    override suspend fun refreshInventory() {
        val snapshot = connectorsSnapshot()
        snapshot.forEach { it.refreshInventory() }
    }

    override suspend fun applySimulation(enabled: Boolean) {
        val snapshot = connectorsSnapshot()
        snapshot.forEach { it.applySimulation(enabled) }
    }

    override suspend fun connect(deviceId: DeviceId): DeviceCommandResult {
        val connector =
            connectorFor(deviceId)
                ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
        return connector.connect()
    }

    override suspend fun disconnect(deviceId: DeviceId): DeviceCommandResult {
        val connector =
            connectorFor(deviceId)
                ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
        return connector.disconnect()
    }

    override suspend fun configure(
        deviceId: DeviceId,
        options: Map<String, String>
    ): DeviceCommandResult {
        val connector =
            connectorFor(deviceId)
                ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
        return connector.configure(options)
    }

    override suspend fun startStreaming(
        deviceId: DeviceId,
        anchor: RecordingSessionAnchor
    ): DeviceCommandResult {
        val connector =
            connectorFor(deviceId)
                ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
        return connector.startStreaming(anchor)
    }

    override suspend fun stopStreaming(deviceId: DeviceId): DeviceCommandResult {
        val connector =
            connectorFor(deviceId)
                ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
        return connector.stopStreaming()
    }

    override suspend fun collectArtifacts(
        deviceId: DeviceId,
        sessionId: String
    ): List<SessionArtifact> {
        val connector = connectorFor(deviceId) ?: return emptyList()
        return connector.collectArtifacts(sessionId)
    }

    fun previewFrame(deviceId: DeviceId): StateFlow<TopdonPreviewFrame?>? {
        return connectorCache[deviceId]?.previewFrameFlow
    }

    fun previewRunning(deviceId: DeviceId): StateFlow<Boolean>? {
        return connectorCache[deviceId]?.previewRunningFlow
    }

    suspend fun startPreview(deviceId: DeviceId): DeviceCommandResult {
        val managed = connectorCache[deviceId]
            ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
        return managed.connector.startPreview()
    }

    suspend fun stopPreview(deviceId: DeviceId): DeviceCommandResult {
        val managed = connectorCache[deviceId]
            ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")
        return managed.connector.stopPreview()
    }

    suspend fun capturePhoto(deviceId: DeviceId): DeviceCommandResult {
        val managed = connectorCache[deviceId]
            ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")

        val frame = managed.previewFrameFlow.value
            ?: return DeviceCommandResult.Rejected("No preview frame available")

        return withContext(Dispatchers.IO) {
            try {
                val contentResolver = context.contentResolver
                val contentValues = android.content.ContentValues().apply {
                    put(
                        android.provider.MediaStore.Images.Media.DISPLAY_NAME,
                        "thermal_${System.currentTimeMillis()}.jpg"
                    )
                    put(android.provider.MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(
                        android.provider.MediaStore.Images.Media.RELATIVE_PATH,
                        "Pictures/BuccanCS/Thermal"
                    )
                }

                val uri = contentResolver.insert(
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                ) ?: return@withContext DeviceCommandResult.Failed(
                    IllegalStateException("Failed to create media store entry")
                )

                contentResolver.openOutputStream(uri)?.use { outputStream ->
                    val bitmap = thermalNormalizer.createBitmapFromFrame(frame)
                    bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 95, outputStream)
                }

                Log.i("TopdonConnectorManager", "Photo saved to $uri")
                DeviceCommandResult.Accepted
            } catch (t: Throwable) {
                Log.e("TopdonConnectorManager", "Failed to capture photo", t)
                DeviceCommandResult.Failed(t)
            }
        }
    }

    suspend fun startRecording(deviceId: DeviceId): DeviceCommandResult {
        val managed = connectorCache[deviceId]
            ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")

        val sessionId = "standalone_${System.currentTimeMillis()}"
        val anchor = RecordingSessionAnchor(
            sessionId = sessionId,
            referenceTimestamp = fromEpochMilliseconds(System.currentTimeMillis()),
            sharedClockOffsetMillis = 0L
        )

        return managed.connector.startStreaming(anchor)
    }

    suspend fun stopRecording(deviceId: DeviceId): DeviceCommandResult {
        val managed = connectorCache[deviceId]
            ?: return DeviceCommandResult.Rejected("Unknown Topdon device ${deviceId.value}")

        return managed.connector.stopStreaming()
    }

    private suspend fun rebuild(config: SensorHardwareConfig) {
        val entries = config.topdon.associateBy { DeviceId(it.id.trim()) }
        val toRemove = connectorsMutex.withLock {
            val existing = connectors.keys.toSet()
            existing - entries.keys
        }
        toRemove.forEach { removeConnector(it) }
        entries.forEach { (deviceId, entry) ->
            connectorsMutex.withLock {
                val existing = connectors[deviceId]
                if (existing == null) {
                    val managed = createConnector(deviceId, entry)
                    connectors[deviceId] = managed
                } else {
                    applyConfig(existing, entry)
                }
                refreshConnectorCacheLocked()
            }
        }
    }

    private suspend fun removeConnector(deviceId: DeviceId) {
        val managed = connectorsMutex.withLock {
            val removed = connectors.remove(deviceId)
            refreshConnectorCacheLocked()
            removed
        } ?: return
        managed.deviceJob.cancel()
        managed.statusJob.cancel()
        managed.configJob.cancel()
        withContext(Dispatchers.IO) {
            runCatching { managed.connector.stopStreaming() }
            runCatching { managed.connector.disconnect() }
        }
        devicesMutex.withLock {
            val next = deviceState.value.toMutableMap()
            next.remove(deviceId)
            deviceState.value = next.toMap()
        }
        statusMutex.withLock {
            val next = statusState.value.toMutableMap()
            next.remove(deviceId)
            statusState.value = next.toMap()
        }
    }

    private suspend fun createConnector(
        deviceId: DeviceId,
        entry: TopdonDeviceConfig
    ): ManagedConnector {
        val defaultSettings = TopdonSettings()
        val settings = InMemoryTopdonSettingsRepository(
            TopdonSettings(
                autoConnectOnAttach = entry.autoConnectOnAttach
                    ?: defaultSettings.autoConnectOnAttach,
                palette = entry.palette ?: defaultSettings.palette,
                superSampling = entry.superSampling ?: defaultSettings.superSampling,
                previewFpsLimit = entry.previewFpsLimit ?: TopdonSettings.DEFAULT_PREVIEW_FPS_LIMIT
            )
        )
        val device = SensorDevice(
            id = deviceId,
            displayName = entry.displayName.ifBlank { "Topdon ${deviceId.value}" },
            type = SensorDeviceType.TOPDON_TC001,
            capabilities = setOf(SensorStreamType.THERMAL_VIDEO, SensorStreamType.PREVIEW),
            connectionStatus = ConnectionStatus.Disconnected,
            isSimulated = false,
            attributes = buildAttributes(entry)
        )
        val connector = TopdonThermalConnector(
            appScope = scope,
            hardwareClient = hardwareClient,
            recordingStorage = recordingStorage,
            artifactFactory = artifactFactory,
            settingsRepository = settings,
            initialDevice = device
        )
        val normalizedConfig = entry.normalizeDefaults(settings.settings.value)
        var currentConfig = normalizedConfig
        var managedRef: ManagedConnector? = null
        val deviceJob = scope.launch {
            connector.device.collect { snapshot ->
                devicesMutex.withLock {
                    val next = deviceState.value.toMutableMap()
                    next[deviceId] = snapshot
                    deviceState.value = next.toMap()
                }
                managedRef?.let { managed ->
                    val updated = enrichTopdonConfig(managed.config, snapshot)
                    if (updated != managed.config) {
                        managed.config = updated
                        currentConfig = updated
                        configRepository.upsertTopdonDevice(updated)
                    }
                }
            }
        }
        val statusJob = scope.launch {
            connector.streamStatuses.collect { statuses ->
                statusMutex.withLock {
                    val next = statusState.value.toMutableMap()
                    next[deviceId] = statuses
                    statusState.value = next.toMap()
                }
            }
        }
        val configJob = scope.launch(start = CoroutineStart.LAZY) {
            settings.settings.collect { userSettings ->
                val updated = currentConfig.copy(
                    autoConnectOnAttach = userSettings.autoConnectOnAttach,
                    palette = userSettings.palette,
                    superSampling = userSettings.superSampling,
                    previewFpsLimit = userSettings.previewFpsLimit
                )
                if (updated != currentConfig) {
                    currentConfig = updated
                    managedRef?.config = updated
                    configRepository.upsertTopdonDevice(updated)
                }
            }
        }
        devicesMutex.withLock {
            val next = deviceState.value.toMutableMap()
            next[deviceId] = connector.device.value
            deviceState.value = next.toMap()
        }
        statusMutex.withLock {
            val next = statusState.value.toMutableMap()
            next[deviceId] = connector.streamStatuses.value
            statusState.value = next.toMap()
        }
        val managed = ManagedConnector(
            connector = connector,
            settings = settings,
            deviceJob = deviceJob,
            statusJob = statusJob,
            configJob = configJob,
            config = normalizedConfig,
            previewFrameFlow = connector.previewFrameFlow,
            previewRunningFlow = connector.previewRunningFlow
        )
        managedRef = managed
        configRepository.upsertTopdonDevice(normalizedConfig)
        configJob.start()
        return managed
    }

    private suspend fun applyConfig(existing: ManagedConnector, entry: TopdonDeviceConfig) {
        entry.autoConnectOnAttach?.let { existing.settings.setAutoConnect(it) }
        entry.palette?.let { existing.settings.setPalette(it) }
        entry.superSampling?.let { existing.settings.setSuperSampling(it) }
        entry.previewFpsLimit?.let { existing.settings.setPreviewFpsLimit(it) }
        val updated = existing.config.copy(
            displayName = entry.displayName.ifBlank { existing.config.displayName },
            vendorId = entry.vendorId ?: existing.config.vendorId,
            productId = entry.productId ?: existing.config.productId,
            serialNumber = entry.serialNumber ?: existing.config.serialNumber,
            autoConnectOnAttach = entry.autoConnectOnAttach ?: existing.config.autoConnectOnAttach,
            palette = entry.palette ?: existing.config.palette,
            superSampling = entry.superSampling ?: existing.config.superSampling,
            previewFpsLimit = entry.previewFpsLimit ?: existing.config.previewFpsLimit
        )
        if (updated != existing.config) {
            existing.config = updated
            configRepository.upsertTopdonDevice(updated)
        }
    }

    private suspend fun connectorsSnapshot(): List<TopdonThermalConnector> =
        connectorsMutex.withLock { connectors.values.map { it.connector } }

    private suspend fun connectorFor(deviceId: DeviceId): TopdonThermalConnector? =
        connectorsMutex.withLock { connectors[deviceId]?.connector }

    private fun buildAttributes(entry: TopdonDeviceConfig): Map<String, String> {
        val attributes = mutableMapOf<String, String>()
        entry.vendorId?.let { attributes["usb.vendorId"] = "0x${it.toString(16)}" }
        entry.productId?.let { attributes["usb.productId"] = "0x${it.toString(16)}" }
        entry.serialNumber?.takeIf { it.isNotBlank() }?.let { attributes["usb.serialNumber"] = it }
        return attributes
    }

    private fun refreshConnectorCacheLocked() {
        connectorCache = connectors.toMap()
    }

    private fun TopdonDeviceConfig.normalizeDefaults(settings: TopdonSettings): TopdonDeviceConfig =
        copy(
            displayName = displayName.ifBlank { "Topdon ${id}" },
            autoConnectOnAttach = autoConnectOnAttach ?: settings.autoConnectOnAttach,
            palette = palette ?: settings.palette,
            superSampling = superSampling ?: settings.superSampling,
            previewFpsLimit = previewFpsLimit ?: settings.previewFpsLimit
        )

    private fun enrichTopdonConfig(
        current: TopdonDeviceConfig,
        device: SensorDevice
    ): TopdonDeviceConfig {
        val vendor = device.attributes["usb.vendorId"]?.decodeHexInt()
        val product = device.attributes["usb.productId"]?.decodeHexInt()
        val serial = device.attributes["usb.serialNumber"]?.takeIf { it.isNotBlank() }
        val name = device.displayName.takeIf { it.isNotBlank() }
        return current.copy(
            displayName = name ?: current.displayName,
            vendorId = vendor ?: current.vendorId,
            productId = product ?: current.productId,
            serialNumber = serial ?: current.serialNumber
        )
    }

    private fun String.decodeHexInt(): Int? {
        val normalized = removePrefix("0x").removePrefix("0X")
        return normalized.toIntOrNull(16)
    }

    private data class ManagedConnector(
        val connector: TopdonThermalConnector,
        val settings: InMemoryTopdonSettingsRepository,
        val deviceJob: Job,
        val statusJob: Job,
        val configJob: Job,
        var config: TopdonDeviceConfig,
        val previewFrameFlow: StateFlow<TopdonPreviewFrame?>,
        val previewRunningFlow: StateFlow<Boolean>
    )
}

