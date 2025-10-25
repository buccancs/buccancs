package com.buccancs.hardware.shimmer

import android.bluetooth.BluetoothAdapter
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import com.buccancs.di.ApplicationScope
import com.shimmerresearch.android.shimmerService.ShimmerService
import com.shimmerresearch.bluetooth.ShimmerBluetooth
import com.shimmerresearch.driver.CallbackObject
import com.shimmerresearch.driver.Configuration
import com.shimmerresearch.driver.ObjectCluster
import com.shimmerresearch.driver.ShimmerDevice
import com.shimmerresearch.driverUtilities.ChannelDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Hardware client backed by the upstream Shimmer Android service.
 * Binds `ShimmerService`, proxies connection commands, and publishes device
 * inventory, status, notices, and raw samples to the rest of the agent.
 */
@Singleton
class DefaultShimmerHardwareClient @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val bluetoothAdapter: BluetoothAdapter?,
) : ShimmerHardwareClient {

    private val logTag = "ShimmerHardware"

    private val devicesState =
        MutableStateFlow<List<ShimmerHardwareDevice>>(emptyList())
    private val statusState =
        MutableStateFlow<ShimmerStatus>(ShimmerStatus.Idle)
    private val samplesSharedFlow =
        MutableSharedFlow<ShimmerSample>(extraBufferCapacity = 128)
    private val noticesSharedFlow =
        MutableSharedFlow<ShimmerNotice>(extraBufferCapacity = 32)

    override val devices: StateFlow<List<ShimmerHardwareDevice>> =
        devicesState.asStateFlow()
    override val status: StateFlow<ShimmerStatus> =
        statusState.asStateFlow()
    override val samples: Flow<ShimmerSample> =
        samplesSharedFlow.asSharedFlow()
    override val notices: Flow<ShimmerNotice> =
        noticesSharedFlow.asSharedFlow()

    private val deviceLock = Any()
    private val bondedDevices =
        linkedMapOf<String, ShimmerHardwareDevice>()
    private val connectedDevices =
        linkedMapOf<String, ShimmerHardwareDevice>()

    private val shimmerServiceBound =
        AtomicBoolean(false)
    private var shimmerService: ShimmerService? = null

    private val serviceHandler =
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    STATE_CHANGE_MESSAGE -> handleStateChange(msg)
                    DATA_PACKET_MESSAGE -> handleDataPacket(msg)
                    TOAST_MESSAGE -> handleToast(msg)
                }
            }
        }

    private val serviceConnection =
        object : ServiceConnection {
            override fun onServiceConnected(component: ComponentName?, binder: IBinder?) {
                val bound =
                    (binder as? ShimmerService.LocalBinder)?.service
                        ?: return
                shimmerService = bound
                bound.addHandlerToList(serviceHandler)
                shimmerServiceBound.set(true)
                applicationScope.launch {
                    noticesSharedFlow.emit(
                        ShimmerNotice(
                            "Shimmer service connected",
                            ShimmerNotice.Category.Info
                        )
                    )
                }
                syncConnectedDevices()
            }

            override fun onServiceDisconnected(component: ComponentName?) {
                shimmerService = null
                shimmerServiceBound.set(false)
                applicationScope.launch {
                    noticesSharedFlow.emit(
                        ShimmerNotice(
                            "Shimmer service disconnected",
                            ShimmerNotice.Category.Warning
                        )
                    )
                }
                synchronized(deviceLock) {
                    connectedDevices.clear()
                }
                mergeDevices()
                statusState.value = ShimmerStatus.Idle
                bindToService()
            }
        }

    init {
        bindToService()
    }

    private fun bindToService() {
        Handler(Looper.getMainLooper()).post {
            try {
                val intent =
                    Intent(context, ShimmerService::class.java)
                context.startService(intent)
                val bound =
                    context.bindService(
                        intent,
                        serviceConnection,
                        Context.BIND_AUTO_CREATE
                    )
                shimmerServiceBound.set(bound)
                if (!bound) {
                    applicationScope.launch {
                        noticesSharedFlow.emit(
                            ShimmerNotice(
                                "Unable to bind Shimmer service",
                                ShimmerNotice.Category.Error
                            )
                        )
                    }
                }
            } catch (t: Throwable) {
                Log.e(logTag, "Failed to bind ShimmerService", t)
                applicationScope.launch {
                    noticesSharedFlow.emit(
                        ShimmerNotice(
                            "Failed to bind Shimmer service: ${t.message}",
                            ShimmerNotice.Category.Error
                        )
                    )
                }
            }
        }
    }

    override suspend fun refreshBondedDevices() {
        val bonded =
            withContext(Dispatchers.IO) {
                runCatching {
                    bluetoothAdapter?.bondedDevices
                        ?.filter { device ->
                            val name = device.name?.lowercase()
                            name?.contains("shimmer") == true || name?.contains("gsr") == true
                        }
                        ?.map { device ->
                            ShimmerHardwareDevice(
                                macAddress = device.address,
                                name = device.name ?: "Shimmer",
                                rssi = null,
                                firmwareVersion = null,
                                hardwareVersion = null,
                                bonded = true
                            )
                        }
                        ?: emptyList()
                }.onFailure { error ->
                    Log.e(logTag, "Bluetooth permission missing during bonded scan", error)
                    applicationScope.launch {
                        noticesSharedFlow.emit(
                            ShimmerNotice(
                                "Bluetooth permission missing for Shimmer discovery",
                                ShimmerNotice.Category.Error
                            )
                        )
                    }
                }.getOrElse { emptyList() }
            }
        synchronized(deviceLock) {
            bondedDevices.clear()
            bonded.forEach { device ->
                bondedDevices[device.macAddress.uppercase()] = device
            }
        }
        mergeDevices()
    }

    override suspend fun scan(
        duration: Duration
    ) {
        val millis =
            duration.coerceAtLeast(MIN_SCAN_DURATION).inWholeMilliseconds
        try {
            if (bluetoothAdapter?.isDiscovering == true) {
                bluetoothAdapter.cancelDiscovery()
            }
            bluetoothAdapter?.startDiscovery()
            withContext(Dispatchers.IO) {
                kotlinx.coroutines.delay(millis)
            }
        } catch (se: SecurityException) {
            Log.e(logTag, "Bluetooth scan failed", se)
            applicationScope.launch {
                noticesSharedFlow.emit(
                    ShimmerNotice(
                        "Unable to perform Bluetooth scan: ${se.message}",
                        ShimmerNotice.Category.Error
                    )
                )
            }
        } finally {
            try {
                bluetoothAdapter?.cancelDiscovery()
            } catch (_: SecurityException) {
            }
        }
        refreshBondedDevices()
    }

    override suspend fun connect(
        macAddress: String
    ) {
        val service =
            shimmerService
        if (service == null) {
            statusState.value =
                ShimmerStatus.Error(
                    macAddress,
                    "Shimmer service unavailable",
                    recoverable = true
                )
            applicationScope.launch {
                noticesSharedFlow.emit(
                    ShimmerNotice(
                        "Cannot connect to $macAddress: service not bound",
                        ShimmerNotice.Category.Error
                    )
                )
            }
            bindToService()
            return
        }

        statusState.value =
            ShimmerStatus.Connecting(macAddress)
        runCatching {
            withContext(Dispatchers.Main) {
                service.connectShimmer(macAddress)
            }
        }.onFailure { error ->
            Log.e(logTag, "connectShimmer failed for $macAddress", error)
            statusState.value =
                ShimmerStatus.Error(
                    macAddress,
                    error.message ?: "Failed to connect",
                    recoverable = true
                )
            applicationScope.launch {
                noticesSharedFlow.emit(
                    ShimmerNotice(
                        "Failed to connect to $macAddress: ${error.message}",
                        ShimmerNotice.Category.Error
                    )
                )
            }
        }
    }

    override suspend fun disconnect() {
        val macAddress =
            (statusState.value as? ShimmerStatus.Streaming)?.macAddress
                ?: (statusState.value as? ShimmerStatus.Connected)?.macAddress
                ?: return
        val service =
            shimmerService ?: return
        runCatching {
            withContext(Dispatchers.Main) {
                service.disconnectShimmer(macAddress)
            }
        }.onFailure { error ->
            Log.w(logTag, "disconnectShimmer failed for $macAddress", error)
            applicationScope.launch {
                noticesSharedFlow.emit(
                    ShimmerNotice(
                        "Failed to disconnect $macAddress: ${error.message}",
                        ShimmerNotice.Category.Warning
                    )
                )
            }
        }
        synchronized(deviceLock) {
            connectedDevices.remove(macAddress.uppercase())
        }
        mergeDevices()
        statusState.value = ShimmerStatus.Idle
    }

    override suspend fun applySettings(
        settings: ShimmerHardwareSettings
    ) {
        applicationScope.launch {
            noticesSharedFlow.emit(
                ShimmerNotice(
                    "Hardware configuration updates are not yet supported via the vendor SDK.",
                    ShimmerNotice.Category.Warning
                )
            )
        }
    }

    override suspend fun startStreaming() {
        val macAddress =
            (statusState.value as? ShimmerStatus.Connected)?.macAddress
                ?: (statusState.value as? ShimmerStatus.Streaming)?.macAddress
                ?: return
        val service =
            shimmerService ?: return
        runCatching {
            withContext(Dispatchers.Main) {
                service.startStreaming(macAddress)
            }
        }.onFailure { error ->
            Log.e(logTag, "startStreaming failed for $macAddress", error)
            statusState.value =
                ShimmerStatus.Error(
                    macAddress,
                    error.message ?: "Failed to start streaming",
                    recoverable = true
                )
            applicationScope.launch {
                noticesSharedFlow.emit(
                    ShimmerNotice(
                        "Failed to start streaming: ${error.message}",
                        ShimmerNotice.Category.Error
                    )
                )
            }
        }
    }

    override suspend fun stopStreaming() {
        val macAddress =
            (statusState.value as? ShimmerStatus.Streaming)?.macAddress
                ?: return
        val service =
            shimmerService ?: return
        runCatching {
            withContext(Dispatchers.Main) {
                service.stopStreaming(macAddress)
            }
        }.onFailure { error ->
            Log.w(logTag, "stopStreaming failed for $macAddress", error)
            applicationScope.launch {
                noticesSharedFlow.emit(
                    ShimmerNotice(
                        "Failed to stop streaming: ${error.message}",
                        ShimmerNotice.Category.Warning
                    )
                )
            }
        }
    }

    private fun handleStateChange(message: Message) {
        val payload =
            message.obj
        val (state, mac, shimmerName) =
            when (payload) {
                is ObjectCluster -> Triple(payload.mState, payload.macAddress, payload.shimmerName)
                is CallbackObject -> Triple(payload.mState, payload.mBluetoothAddress, payload.mMyObject as? String)
                else -> Triple(null, null, null)
            }

        if (state == null || mac.isNullOrBlank()) {
            return
        }

        when (state) {
            ShimmerBluetooth.BT_STATE.CONNECTING -> {
                statusState.value =
                    ShimmerStatus.Connecting(mac)
            }

            ShimmerBluetooth.BT_STATE.CONNECTED -> {
                statusState.value =
                    ShimmerStatus.Connected(
                        macAddress = mac,
                        sinceEpochMs = System.currentTimeMillis(),
                        firmwareVersion = fetchFirmwareVersion(mac),
                        hardwareVersion = fetchHardwareVersion(mac)
                    )
                updateConnectedDevice(mac, shimmerName)
            }

            ShimmerBluetooth.BT_STATE.STREAMING,
            ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING,
            ShimmerBluetooth.BT_STATE.STREAMING_LOGGED_DATA -> {
                statusState.value =
                    ShimmerStatus.Streaming(
                        macAddress = mac,
                        sinceEpochMs = System.currentTimeMillis(),
                        samplesPerSecond = DEFAULT_SAMPLE_RATE_HZ
                    )
            }

            ShimmerBluetooth.BT_STATE.CONNECTION_LOST,
            ShimmerBluetooth.BT_STATE.CONNECTION_FAILED -> {
                statusState.value =
                    ShimmerStatus.Error(
                        macAddress = mac,
                        message = state.toString(),
                        recoverable = state == ShimmerBluetooth.BT_STATE.CONNECTION_LOST
                    )
                applicationScope.launch {
                    noticesSharedFlow.emit(
                        ShimmerNotice(
                            "$mac: ${state.toString()}",
                            ShimmerNotice.Category.Warning
                        )
                    )
                }
                removeConnectedDevice(mac)
            }

            ShimmerBluetooth.BT_STATE.DISCONNECTED -> {
                removeConnectedDevice(mac)
                statusState.value = ShimmerStatus.Idle
            }

            else -> Unit
        }
    }

    private fun handleDataPacket(message: Message) {
        val cluster =
            message.obj as? ObjectCluster ?: return
        val mac =
            cluster.macAddress ?: return
        val conductanceMicroSiemens =
            cluster.readCalibrated(
                Configuration.Shimmer3.ObjectClusterSensorName.GSR_CONDUCTANCE
            )
        val resistanceKOhm =
            cluster.readCalibrated(
                Configuration.Shimmer3.ObjectClusterSensorName.GSR_RESISTANCE
            )
        val sample =
            ShimmerSample(
                timestampEpochMs = System.currentTimeMillis(),
                conductanceSiemens = conductanceMicroSiemens?.div(1_000_000.0),
                resistanceOhms = resistanceKOhm?.times(1_000.0)
            )
        samplesSharedFlow.tryEmit(sample)
        if (statusState.value !is ShimmerStatus.Streaming) {
            statusState.value =
                ShimmerStatus.Streaming(
                    macAddress = mac,
                    sinceEpochMs = System.currentTimeMillis(),
                    samplesPerSecond = DEFAULT_SAMPLE_RATE_HZ
                )
        }
    }

    private fun handleToast(message: Message) {
        val toast =
            message.data?.getString("toast") ?: return
        applicationScope.launch {
            noticesSharedFlow.emit(
                ShimmerNotice(
                    toast,
                    ShimmerNotice.Category.Info
                )
            )
        }
    }

    private fun updateConnectedDevice(
        macAddress: String,
        shimmerName: String?
    ) {
        val device =
            runCatching { shimmerService?.getShimmer(macAddress) }
                .onFailure {
                    Log.w(logTag, "Unable to fetch device details for $macAddress", it)
                }
                .getOrNull()
        val hardware =
            toHardwareDevice(macAddress, shimmerName, device)
        synchronized(deviceLock) {
            connectedDevices[macAddress.uppercase()] = hardware
        }
        mergeDevices()
    }

    private fun removeConnectedDevice(macAddress: String) {
        synchronized(deviceLock) {
            connectedDevices.remove(macAddress.uppercase())
        }
        mergeDevices()
    }

    private fun mergeDevices() {
        val combined =
            linkedMapOf<String, ShimmerHardwareDevice>()
        synchronized(deviceLock) {
            bondedDevices.forEach { (mac, device) ->
                combined[mac] = device
            }
            connectedDevices.forEach { (mac, device) ->
                combined[mac] =
                    device.copy(
                        bonded = combined[mac]?.bonded == true || device.bonded
                    )
            }
        }
        devicesState.value = combined.values.toList()
    }

    private fun syncConnectedDevices() {
        val service =
            shimmerService ?: return
        applicationScope.launch(Dispatchers.IO) {
            val connected: List<ShimmerDevice> =
                runCatching { service.listOfConnectedDevices ?: emptyList() }
                    .onFailure {
                        Log.w(logTag, "Unable to read connected devices", it)
                    }
                    .getOrElse { emptyList() }
            synchronized(deviceLock) {
                connectedDevices.clear()
                connected.forEach { shimmerDevice ->
                    val hardware =
                        toHardwareDevice(
                            macAddress = shimmerDevice.getBluetoothAddress(),
                            shimmerName = shimmerDevice.shimmerUserAssignedName,
                            shimmerDevice = shimmerDevice
                        )
                    connectedDevices[hardware.macAddress.uppercase()] = hardware
                }
            }
            mergeDevices()
        }
    }

    private fun toHardwareDevice(
        macAddress: String,
        shimmerName: String?,
        shimmerDevice: ShimmerDevice?
    ): ShimmerHardwareDevice {
        val normalized =
            macAddress.uppercase()
        val nameCandidates =
            listOf(
                shimmerDevice?.shimmerUserAssignedName,
                shimmerDevice?.alternativeName,
                shimmerName,
                shimmerDevice?.getBluetoothAddress()
            )
        val displayName =
            nameCandidates.firstOrNull { value ->
                !value.isNullOrBlank() && !DEFAULT_NAME_SENTINELS.contains(value)
            } ?: "Shimmer"
        val firmware =
            fetchFirmwareVersion(macAddress)
        val hardwareVersion =
            fetchHardwareVersion(macAddress)
        val bonded =
            synchronized(deviceLock) {
                bondedDevices.containsKey(normalized)
            }
        return ShimmerHardwareDevice(
            macAddress = macAddress,
            name = displayName,
            rssi = null,
            firmwareVersion = firmware,
            hardwareVersion = hardwareVersion,
            bonded = bonded
        )
    }

    private fun ObjectCluster.readCalibrated(sensorName: String): Double? {
        val clusters =
            getCollectionOfFormatClusters(sensorName) ?: return null
        val calibrated =
            ObjectCluster.returnFormatCluster(
                clusters,
                ChannelDetails.CHANNEL_TYPE.CAL.toString()
            ) ?: return null
        return calibrated.mData
    }

    private fun fetchFirmwareVersion(macAddress: String): String? =
        runCatching { shimmerService?.getFWVersion(macAddress) }.getOrNull()

    private fun fetchHardwareVersion(macAddress: String): Int? =
        runCatching { shimmerService?.getShimmerVersion(macAddress) }.getOrNull()

    companion object {
        private val MIN_SCAN_DURATION: Duration = 3.seconds
        private const val DEFAULT_SAMPLE_RATE_HZ = 128.0
        private const val STATE_CHANGE_MESSAGE = 0
        private const val DATA_PACKET_MESSAGE = 2
        private const val TOAST_MESSAGE = 999
        private val DEFAULT_NAME_SENTINELS =
            setOf(
                "",
                "N/A",
                ShimmerDevice.DEFAULT_SHIMMER_NAME,
                ShimmerDevice.DEFAULT_SHIMMER_NAME_WITH_ERROR
            )
    }
}
