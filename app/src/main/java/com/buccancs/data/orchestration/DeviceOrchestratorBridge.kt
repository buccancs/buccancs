package com.buccancs.data.orchestration

import android.content.Context
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import com.buccancs.application.time.TimeSyncService
import com.buccancs.control.OrchestrationServiceGrpcKt
import com.buccancs.control.ProtocolVersion
import com.buccancs.control.deviceRegistration
import com.buccancs.control.deviceStatus
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.RecordingLifecycleState
import com.buccancs.domain.model.SensorStreamType
import com.buccancs.domain.repository.OrchestratorConfigRepository
import com.buccancs.domain.repository.SensorRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceOrchestratorBridge @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val configRepository: OrchestratorConfigRepository,
    private val channelFactory: GrpcChannelFactory,
    private val identityProvider: DeviceIdentityProvider,
    private val sensorRepository: SensorRepository,
    private val timeSyncService: TimeSyncService,
    @ApplicationContext context: Context
) {
    private val tag = "DeviceBridge"
    private val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as? BatteryManager

    init {
        scope.launch {
            try {
                timeSyncService.start()
            } catch (t: Throwable) {
                Log.w(tag, "Unable to start time sync immediately: ${t.message}", t)
            }
            configRepository.config.collectLatest { config ->
                maintainConnection(config)
            }
        }
    }

    private suspend fun maintainConnection(config: com.buccancs.domain.model.OrchestratorConfig) {
        while (currentCoroutineContext().isActive) {
            try {
                ensureRegistrationAndHeartbeat(config)
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (t: Throwable) {
                Log.w(tag, "Orchestrator bridge failure: ${t.message}", t)
                delay(RETRY_DELAY_MS)
            }
        }
    }

    private suspend fun ensureRegistrationAndHeartbeat(
        config: com.buccancs.domain.model.OrchestratorConfig
    ) {
        try {
            sensorRepository.refreshInventory()
        } catch (t: Throwable) {
            Log.w(tag, "Inventory refresh failed: ${t.message}", t)
        }
        val deviceId = identityProvider.deviceId()
        val channel = channelFactory.channel(config)
        val stub = OrchestrationServiceGrpcKt.OrchestrationServiceCoroutineStub(channel)
        val registration = buildRegistration(deviceId)
        val ack = withTimeout(10_000) {
            stub.registerDevice(registration)
        }
        if (!ack.accepted) {
            val reason = ack.reason.ifBlank { "Registration rejected by orchestrator." }
            throw IllegalStateException(reason)
        }
        Log.i(tag, "Registered with orchestrator as $deviceId (session=${ack.sessionId})")
        val context = currentCoroutineContext()
        while (context.isActive) {
            val status = buildStatus(deviceId)
            withTimeout(10_000) {
                stub.reportStatus(status)
            }
            delay(STATUS_INTERVAL_MS)
        }
    }

    private fun buildRegistration(deviceId: String) = deviceRegistration {
        this.deviceId = deviceId
        model = resolveModel()
        platform = "Android ${Build.VERSION.RELEASE}"
        softwareVersion = resolveSoftwareVersion()
        capabilities += gatherCapabilities()
        protocolVersion = ProtocolVersion.CURRENT
    }

    private fun resolveModel(): String {
        val manufacturer = Build.MANUFACTURER?.trim().orEmpty()
        val model = Build.MODEL?.trim().orEmpty()
        if (manufacturer.isEmpty() && model.isEmpty()) {
            return "Android Device"
        }
        if (manufacturer.isEmpty()) {
            return model
        }
        if (model.isEmpty()) {
            return manufacturer
        }
        return "$manufacturer $model"
    }

    private fun resolveSoftwareVersion(): String {
        val release = Build.VERSION.RELEASE?.trim().orEmpty()
        val sdk = Build.VERSION.SDK_INT
        return if (release.isBlank()) {
            "SDK $sdk"
        } else {
            "Android $release (SDK $sdk)"
        }
    }

    private fun gatherCapabilities(): Set<String> {
        val snapshot = sensorRepository.devices.value
        if (snapshot.isEmpty()) {
            return emptySet()
        }
        val labels = mutableSetOf<String>()
        snapshot.forEach { device ->
            labels += "device:${device.type.name.lowercase(Locale.US)}"
            device.capabilities.forEach { stream ->
                labels += "stream:${stream.toLabel()}"
            }
        }
        return labels
    }

    private fun SensorStreamType.toLabel(): String = name.lowercase(Locale.US)

    private fun buildStatus(deviceId: String) = deviceStatus {
        this.deviceId = deviceId
        online = true
        val recordingState = sensorRepository.recordingState.value
        recording = recordingState.lifecycle == RecordingLifecycleState.Recording
        val anchor = recordingState.anchor
        if (anchor != null) {
            sessionId = anchor.sessionId
        }
        val battery = readBatteryPercent()
        if (battery != null) {
            batteryPercent = battery
        }
        val clockOffset = timeSyncService.status.value.offsetMillis.toDouble()
        if (clockOffset.isFinite()) {
            clockOffsetMs = clockOffset
        }
        lastHeartbeatEpochMs = System.currentTimeMillis()
    }

    private fun readBatteryPercent(): Double? {
        val manager = batteryManager ?: return null
        val value = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        if (value <= 0 || value > 100) {
            return null
        }
        return value.toDouble()
    }

    private companion object {
        private const val RETRY_DELAY_MS = 5_000L
        private const val STATUS_INTERVAL_MS = 2_000L
    }
}
