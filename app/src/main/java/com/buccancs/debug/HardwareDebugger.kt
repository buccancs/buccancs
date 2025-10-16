package com.buccancs.debug

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
import android.util.Log
import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.SensorStreamStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Hardware debugging utility for field testing and diagnostics
 * Provides detailed logging and metrics collection for TOPDON and Shimmer devices
 */
@Singleton
class HardwareDebugger @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val logTag = "HardwareDebugger"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US)

    private val debugLogFile by lazy {
        val dir = File(context.getExternalFilesDir(null), "debug")
        dir.mkdirs()
        File(dir, "hardware_debug_${System.currentTimeMillis()}.log")
    }

    /**
     * Scan and log all USB devices
     */
    fun scanUsbDevices(): List<UsbDeviceInfo> {
        val usbManager = context.getSystemService(Context.USB_SERVICE) as? UsbManager
        val devices = usbManager?.deviceList?.values?.map { device ->
            UsbDeviceInfo(
                deviceName = device.deviceName,
                vendorId = device.vendorId,
                productId = device.productId,
                deviceClass = device.deviceClass,
                deviceSubclass = device.deviceSubclass,
                manufacturerName = device.manufacturerName,
                productName = device.productName,
                serialNumber = device.serialNumber,
                hasPermission = usbManager.hasPermission(device)
            )
        } ?: emptyList()

        logToFile("=== USB DEVICE SCAN ===")
        if (devices.isEmpty()) {
            logToFile("No USB devices detected")
        } else {
            devices.forEach { device ->
                logToFile("USB Device: ${device.deviceName}")
                logToFile("  Vendor: 0x${device.vendorId.toString(16)} (${device.manufacturerName ?: "Unknown"})")
                logToFile("  Product: 0x${device.productId.toString(16)} (${device.productName ?: "Unknown"})")
                logToFile("  Serial: ${device.serialNumber ?: "N/A"}")
                logToFile("  Class: ${device.deviceClass}, Subclass: ${device.deviceSubclass}")
                logToFile("  Permission: ${device.hasPermission}")

                // Check if TOPDON
                if (device.vendorId == 0x3426) {
                    logToFile("  *** TOPDON TC001 DETECTED ***")
                }
            }
        }
        logToFile("")

        return devices
    }

    /**
     * Scan and log Bluetooth devices
     */
    fun scanBluetoothDevices(): BluetoothInfo {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
        val adapter = bluetoothManager?.adapter

        val info = BluetoothInfo(
            isAvailable = adapter != null,
            isEnabled = adapter?.isEnabled ?: false,
            adapterName = adapter?.name,
            adapterAddress = adapter?.address,
            bondedDevices = adapter?.bondedDevices?.map { device ->
                BondedDeviceInfo(
                    name = device.name,
                    address = device.address,
                    bondState = device.bondState,
                    type = device.type
                )
            } ?: emptyList()
        )

        logToFile("=== BLUETOOTH SCAN ===")
        logToFile("Available: ${info.isAvailable}")
        logToFile("Enabled: ${info.isEnabled}")
        logToFile("Adapter: ${info.adapterName ?: "N/A"}")
        logToFile("Address: ${info.adapterAddress ?: "N/A"}")
        logToFile("Bonded Devices: ${info.bondedDevices.size}")
        info.bondedDevices.forEach { device ->
            logToFile("  ${device.name} (${device.address})")
            logToFile("    Bond State: ${device.bondState}, Type: ${device.type}")

            // Check if Shimmer
            if (device.name?.contains("Shimmer", ignoreCase = true) == true) {
                logToFile("    *** SHIMMER DEVICE DETECTED ***")
            }
        }
        logToFile("")

        return info
    }

    /**
     * Log device connection event
     */
    fun logConnection(deviceId: DeviceId, deviceType: String, success: Boolean, durationMs: Long? = null) {
        val status = if (success) "SUCCESS" else "FAILED"
        val duration = durationMs?.let { " (${it}ms)" } ?: ""
        logToFile("CONNECTION $status: $deviceType ${deviceId.value}$duration")
        Log.i(logTag, "Connection $status: $deviceType ${deviceId.value}$duration")
    }

    /**
     * Log frame/packet callback
     */
    fun logFrameCallback(deviceId: DeviceId, frameCount: Long, dataSize: Int, timestamp: Long) {
        if (frameCount % 100 == 0L) { // Log every 100th frame to avoid spam
            logToFile("FRAME: ${deviceId.value} #$frameCount size=${dataSize}B timestamp=$timestamp")
        }
    }

    /**
     * Log stream statistics
     */
    fun logStreamStats(deviceId: DeviceId, status: SensorStreamStatus) {
        logToFile("STREAM STATS: ${deviceId.value}")
        logToFile("  Type: ${status.streamType}")
        logToFile("  Sample Rate: ${status.sampleRateHz ?: "N/A"} Hz")
        logToFile("  Frame Rate: ${status.frameRateFps ?: "N/A"} fps")
        logToFile("  Buffered: ${String.format(Locale.US, "%.3f", status.bufferedDurationSeconds)}s")
        logToFile("  Streaming: ${status.isStreaming}")
        logToFile("  Simulated: ${status.isSimulated}")
        logToFile("")
    }

    /**
     * Log error with context
     */
    fun logError(deviceId: DeviceId?, operation: String, error: Throwable) {
        val device = deviceId?.value ?: "Unknown"
        logToFile("ERROR: $device - $operation")
        logToFile("  Message: ${error.message}")
        logToFile("  Type: ${error.javaClass.simpleName}")
        error.stackTrace.take(5).forEach {
            logToFile("    at ${it.className}.${it.methodName}(${it.fileName}:${it.lineNumber})")
        }
        logToFile("")
        Log.e(logTag, "Error: $device - $operation", error)
    }

    /**
     * Log temperature data (TOPDON)
     */
    fun logTemperatureData(deviceId: DeviceId, minTemp: Float?, maxTemp: Float?, avgTemp: Float?) {
        if (minTemp != null && maxTemp != null && avgTemp != null) {
            logToFile("TEMPERATURE: ${deviceId.value}")
            logToFile("  Min: ${String.format(Locale.US, "%.2f", minTemp)}C")
            logToFile("  Max: ${String.format(Locale.US, "%.2f", maxTemp)}C")
            logToFile("  Avg: ${String.format(Locale.US, "%.2f", avgTemp)}C")
            logToFile("  Range: ${String.format(Locale.US, "%.2f", maxTemp - minTemp)}C")
        }
    }

    /**
     * Log GSR data (Shimmer)
     */
    fun logGsrData(deviceId: DeviceId, conductance: Double?, resistance: Double?) {
        logToFile("GSR: ${deviceId.value}")
        conductance?.let { logToFile("  Conductance: ${String.format(Locale.US, "%.6f", it)} microsiemens") }
        resistance?.let { logToFile("  Resistance: ${String.format(Locale.US, "%.2f", it)} ohms") }
    }

    /**
     * Monitor frame rate and detect drops
     */
    class FrameRateMonitor(private val expectedFps: Double) {
        private val frameTimes = mutableListOf<Long>()
        private var lastLogTime = 0L
        private var frameCount = 0L
        private var dropCount = 0L

        fun recordFrame(timestamp: Long): FrameRateStats? {
            frameCount++
            frameTimes.add(timestamp)

            // Keep only last 100 frames
            if (frameTimes.size > 100) {
                frameTimes.removeAt(0)
            }

            val now = System.currentTimeMillis()
            if (now - lastLogTime >= 10_000) { // Log every 10 seconds
                lastLogTime = now
                return calculateStats()
            }
            return null
        }

        private fun calculateStats(): FrameRateStats {
            if (frameTimes.size < 2) {
                return FrameRateStats(0.0, 0.0, 0.0, frameCount, dropCount)
            }

            val intervals = frameTimes.zipWithNext { a, b -> b - a }
            val avgInterval = intervals.average()
            val actualFps = 1000.0 / avgInterval
            val minInterval = intervals.minOrNull() ?: 0L
            val maxInterval = intervals.maxOrNull() ?: 0L
            val jitter = maxInterval - minInterval

            // Detect drops (interval > 1.5x expected)
            val expectedInterval = 1000.0 / expectedFps
            val drops = intervals.count { it > expectedInterval * 1.5 }
            dropCount += drops

            return FrameRateStats(actualFps, jitter.toDouble(), actualFps / expectedFps * 100, frameCount, dropCount)
        }
    }

    /**
     * Get system hardware information
     */
    fun getSystemInfo(): SystemInfo {
        return SystemInfo(
            manufacturer = Build.MANUFACTURER,
            model = Build.MODEL,
            androidVersion = Build.VERSION.RELEASE,
            sdkInt = Build.VERSION.SDK_INT,
            buildId = Build.ID,
            cpuAbi = Build.SUPPORTED_ABIS.joinToString(),
            totalMemoryMb = Runtime.getRuntime().maxMemory() / (1024 * 1024)
        )
    }

    /**
     * Log system information
     */
    fun logSystemInfo() {
        val info = getSystemInfo()
        logToFile("=== SYSTEM INFO ===")
        logToFile("Device: ${info.manufacturer} ${info.model}")
        logToFile("Android: ${info.androidVersion} (SDK ${info.sdkInt})")
        logToFile("Build ID: ${info.buildId}")
        logToFile("CPU: ${info.cpuAbi}")
        logToFile("Memory: ${info.totalMemoryMb} MB")
        logToFile("")
    }

    /**
     * Start debug session
     */
    fun startDebugSession() {
        logToFile("================================================================================")
        logToFile("HARDWARE DEBUG SESSION STARTED")
        logToFile("Timestamp: ${dateFormat.format(Date())}")
        logToFile("================================================================================")
        logToFile("")
        logSystemInfo()
        scanUsbDevices()
        scanBluetoothDevices()
    }

    /**
     * End debug session
     */
    fun endDebugSession() {
        logToFile("================================================================================")
        logToFile("HARDWARE DEBUG SESSION ENDED")
        logToFile("Timestamp: ${dateFormat.format(Date())}")
        logToFile("Log file: ${debugLogFile.absolutePath}")
        logToFile("================================================================================")
        Log.i(logTag, "Debug log saved to: ${debugLogFile.absolutePath}")
    }

    /**
     * Get debug log file
     */
    fun getDebugLogFile(): File = debugLogFile

    private fun logToFile(message: String) {
        try {
            val timestamp = dateFormat.format(Date())
            debugLogFile.appendText("$timestamp | $message\n")
        } catch (e: Exception) {
            Log.e(logTag, "Failed to write to debug log", e)
        }
    }
}

data class UsbDeviceInfo(
    val deviceName: String,
    val vendorId: Int,
    val productId: Int,
    val deviceClass: Int,
    val deviceSubclass: Int,
    val manufacturerName: String?,
    val productName: String?,
    val serialNumber: String?,
    val hasPermission: Boolean
)

data class BluetoothInfo(
    val isAvailable: Boolean,
    val isEnabled: Boolean,
    val adapterName: String?,
    val adapterAddress: String?,
    val bondedDevices: List<BondedDeviceInfo>
)

data class BondedDeviceInfo(
    val name: String?,
    val address: String,
    val bondState: Int,
    val type: Int
)

data class SystemInfo(
    val manufacturer: String,
    val model: String,
    val androidVersion: String,
    val sdkInt: Int,
    val buildId: String,
    val cpuAbi: String,
    val totalMemoryMb: Long
)

data class FrameRateStats(
    val actualFps: Double,
    val jitterMs: Double,
    val accuracyPercent: Double,
    val totalFrames: Long,
    val droppedFrames: Long
) {
    val dropRate: Double
        get() = if (totalFrames > 0) droppedFrames.toDouble() / totalFrames * 100 else 0.0
}
