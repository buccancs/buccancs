**Last Modified:** 2025-10-16 02:30 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Hardware Debug Tools Summary

# Hardware Debug Tools Summary - 2025-10-16 02:30 UTC

## Overview

Created comprehensive hardware debugging toolkit for field testing TOPDON TC001 thermal camera and Shimmer3 GSR sensor. Three-file solution providing real-time diagnostics, logging, and metrics collection.

## Files Created

### 1. HardwareDebugger.kt (12.6KB)
**Location:** `app/src/main/java/com/buccancs/debug/HardwareDebugger.kt`  
**Type:** Singleton utility class  
**Dependencies:** Android USB/Bluetooth APIs, Hilt injection

**Features:**
- USB device enumeration with permission checks
- Bluetooth adapter and bonded device scanning
- Auto-detection of TOPDON (vendor 0x3426)
- Auto-detection of Shimmer (by device name)
- Connection event logging with timing
- Frame/packet callback logging (throttled every 100)
- Stream statistics logging
- Error logging with stack traces
- Temperature data logging (TOPDON)
- GSR data logging (Shimmer)
- Frame rate monitor with drop detection
- System information collection
- Session management with timestamps
- File-based persistent logging

**Public API:**
```kotlin
fun scanUsbDevices(): List<UsbDeviceInfo>
fun scanBluetoothDevices(): BluetoothInfo
fun logConnection(deviceId: DeviceId, deviceType: String, success: Boolean, durationMs: Long?)
fun logFrameCallback(deviceId: DeviceId, frameCount: Long, dataSize: Int, timestamp: Long)
fun logStreamStats(deviceId: DeviceId, status: SensorStreamStatus)
fun logError(deviceId: DeviceId?, operation: String, error: Throwable)
fun logTemperatureData(deviceId: DeviceId, minTemp: Float?, maxTemp: Float?, avgTemp: Float?)
fun logGsrData(deviceId: DeviceId, conductance: Double?, resistance: Double?)
fun getSystemInfo(): SystemInfo
fun startDebugSession()
fun endDebugSession()
fun getDebugLogFile(): File
```

**Data Classes:**
- UsbDeviceInfo - USB device details with permission status
- BluetoothInfo - Bluetooth adapter state and bonded devices
- BondedDeviceInfo - Individual Bluetooth device info
- SystemInfo - Android device hardware information
- FrameRateStats - Performance metrics (fps, jitter, drops)

**Frame Rate Monitor:**
```kotlin
class FrameRateMonitor(expectedFps: Double) {
    fun recordFrame(timestamp: Long): FrameRateStats?
}
```

Tracks:
- Actual FPS vs expected
- Jitter (max - min interval)
- Accuracy percentage
- Total frames captured
- Dropped frames count
- Drop rate percentage

**Log File Format:**
- Location: `/storage/emulated/0/Android/data/com.buccancs/files/debug/`
- Filename: `hardware_debug_<timestamp>.log`
- Format: `YYYY-MM-DD HH:mm:ss.SSS | <message>`
- Persists across app restarts
- Shareable via file manager

### 2. HardwareDebugScreen.kt (11.4KB)
**Location:** `app/src/main/java/com/buccancs/ui/debug/HardwareDebugScreen.kt`  
**Type:** Jetpack Compose screen  
**Dependencies:** Material 3, Hilt Navigation

**UI Components:**

TopBar:
- Title: "Hardware Debugger"

Control Section:
- Start Session button (Refresh icon)
- Save Log button (Save icon)

System Information Card:
- Device manufacturer and model
- Android version and SDK level
- Total memory
- CPU architecture

USB Devices Card:
- Device count indicator
- Loading spinner during scan
- Empty state message

USB Device Cards (one per device):
- Product name or device path
- Vendor ID (hex)
- Product ID (hex)
- Manufacturer name
- Serial number
- Permission status with icon
- TOPDON detection highlight

Bluetooth Info Card:
- Adapter availability
- Enabled state
- Adapter name
- Bonded device count

Bluetooth Device Cards (one per bonded device):
- Device name
- MAC address (monospace)
- Bond state
- Device type
- Shimmer detection highlight

Debug Log Card:
- Log file path (monospace)

**Visual Design:**
- Material 3 Cards throughout
- Icons for status (Check/Close for permissions)
- Colour coding (primary for success, error for failures)
- Monospace font for technical data
- Responsive LazyColumn layout
- Consistent 16dp padding
- 8dp spacing between elements

### 3. HardwareDebugViewModel.kt (2.4KB)
**Location:** `app/src/main/java/com/buccancs/ui/debug/HardwareDebugViewModel.kt`  
**Type:** Hilt ViewModel  
**Dependencies:** HardwareDebugger injection

**State Management:**
```kotlin
data class HardwareDebugUiState(
    val systemInfo: SystemInfo?,
    val usbDevices: List<UsbDeviceInfo>,
    val bluetoothInfo: BluetoothInfo?,
    val logFilePath: String?,
    val isScanning: Boolean
)
```

**Lifecycle:**
- `init {}` - Automatically starts debug session
- `onCleared()` - Saves debug log on ViewModel destruction

**Functions:**
- `startDebugSession()` - Scans hardware and updates UI state
- `saveDebugLog()` - Ends debug session and finalises log

**Coroutines:**
- Uses `viewModelScope` with `Dispatchers.IO`
- Non-blocking UI updates via StateFlow
- Error handling with try-catch

## Integration Guide

### Step 1: Add Navigation Route
**File:** `app/src/main/java/com/buccancs/ui/navigation/AppNavHost.kt`

```kotlin
composable("hardware_debug") {
    HardwareDebugScreen()
}
```

### Step 2: Add Menu Item
**Example in Settings Screen:**

```kotlin
Card(
    modifier = Modifier.fillMaxWidth(),
    onClick = { navController.navigate("hardware_debug") }
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Icon(Icons.Default.BugReport, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text("Hardware Debugger")
    }
}
```

### Step 3: Inject into Connectors
**Example in TopdonThermalConnector:**

```kotlin
@Singleton
class TopdonThermalConnector @Inject constructor(
    // existing dependencies...
    private val debugger: HardwareDebugger
) {
    private val frameCallback = IFrameCallback { data ->
        thermalFrameCount++
        debugger.logFrameCallback(deviceId, thermalFrameCount, data?.size ?: 0, System.currentTimeMillis())
        
        // existing frame processing...
        
        val metrics = thermalNormalizer.normalize(data)
        debugger.logTemperatureData(deviceId, metrics.minCelsius, metrics.maxCelsius, metrics.avgCelsius)
    }
    
    override suspend fun connect(): DeviceCommandResult {
        val startTime = System.currentTimeMillis()
        val result = performConnect()
        val duration = System.currentTimeMillis() - startTime
        
        debugger.logConnection(deviceId, "TOPDON", result.isSuccess, duration)
        return result
    }
}
```

**Example in ShimmerSensorConnector:**

```kotlin
class ShimmerSensorConnector(
    // existing dependencies...
    private val debugger: HardwareDebugger
) {
    private fun handleDataPacket(cluster: ObjectCluster) {
        samplesSeen++
        debugger.logFrameCallback(deviceId, samplesSeen, 0, System.currentTimeMillis())
        
        val conductance = extractConductance(cluster)
        val resistance = conductance?.let { if (it > 0) 1_000_000.0 / it else 0.0 }
        
        debugger.logGsrData(deviceId, conductance, resistance)
        
        // existing data processing...
    }
}
```

### Step 4: Monitor Frame Rates
**Example in connector:**

```kotlin
private val frameRateMonitor = HardwareDebugger.FrameRateMonitor(expectedFps = 25.0)

private val frameCallback = IFrameCallback { data ->
    val stats = frameRateMonitor.recordFrame(System.currentTimeMillis())
    stats?.let {
        Log.i(TAG, "FPS: ${String.format("%.2f", it.actualFps)}, " +
                   "Accuracy: ${String.format("%.1f", it.accuracyPercent)}%, " +
                   "Drops: ${it.droppedFrames}/${it.totalFrames} (${String.format("%.2f", it.dropRate)}%)")
    }
    // process frame...
}
```

## Usage Workflow

### Pre-Testing Setup
1. Build APK with debug tools
2. Install on test device
3. Grant all runtime permissions
4. Navigate to Hardware Debugger screen

### During Hardware Testing
1. Open Hardware Debugger
2. Tap "Start Session" to scan
3. Verify system information correct
4. Check USB devices list
5. Verify TOPDON detected (if connected)
6. Check Bluetooth bonded devices
7. Verify Shimmer detected (if paired)
8. Return to main app
9. Perform hardware testing operations
10. Return to Hardware Debugger
11. Tap "Save Log"

### Post-Testing Analysis
1. Navigate to log file location
2. Share via file manager
3. Open in text editor
4. Analyse timestamps, events, errors
5. Check frame rates and drop counts
6. Verify temperature/GSR data ranges
7. Review error logs with stack traces

## Example Log Output

```
2025-10-16 02:30:15.123 | ================================================================================
2025-10-16 02:30:15.124 | HARDWARE DEBUG SESSION STARTED
2025-10-16 02:30:15.125 | Timestamp: 2025-10-16 02:30:15.125
2025-10-16 02:30:15.126 | ================================================================================
2025-10-16 02:30:15.127 | 
2025-10-16 02:30:15.128 | === SYSTEM INFO ===
2025-10-16 02:30:15.129 | Device: Google Pixel 7
2025-10-16 02:30:15.130 | Android: 14 (SDK 34)
2025-10-16 02:30:15.131 | Build ID: UP1A.231105.003
2025-10-16 02:30:15.132 | CPU: arm64-v8a
2025-10-16 02:30:15.133 | Memory: 8192 MB
2025-10-16 02:30:15.134 | 
2025-10-16 02:30:15.135 | === USB DEVICE SCAN ===
2025-10-16 02:30:15.136 | USB Device: /dev/bus/usb/001/002
2025-10-16 02:30:15.137 |   Vendor: 0x3426 (TOPDON)
2025-10-16 02:30:15.138 |   Product: 0x0001 (TC001)
2025-10-16 02:30:15.139 |   Serial: TC001-12345678
2025-10-16 02:30:15.140 |   Class: 239, Subclass: 2
2025-10-16 02:30:15.141 |   Permission: true
2025-10-16 02:30:15.142 |   *** TOPDON TC001 DETECTED ***
2025-10-16 02:30:15.143 | 
2025-10-16 02:30:15.144 | === BLUETOOTH SCAN ===
2025-10-16 02:30:15.145 | Available: true
2025-10-16 02:30:15.146 | Enabled: true
2025-10-16 02:30:15.147 | Adapter: Pixel 7
2025-10-16 02:30:15.148 | Address: A1:B2:C3:D4:E5:F6
2025-10-16 02:30:15.149 | Bonded Devices: 2
2025-10-16 02:30:15.150 |   Shimmer3-A1B2 (00:11:22:33:44:55)
2025-10-16 02:30:15.151 |     Bond State: 12, Type: 1
2025-10-16 02:30:15.152 |     *** SHIMMER DEVICE DETECTED ***
2025-10-16 02:30:15.153 | 
2025-10-16 02:30:20.234 | CONNECTION SUCCESS: TOPDON topdon-tc001 (1250ms)
2025-10-16 02:30:21.456 | FRAME: topdon-tc001 #100 size=98304B timestamp=1729043421456
2025-10-16 02:30:22.567 | TEMPERATURE: topdon-tc001
2025-10-16 02:30:22.568 |   Min: 18.50C
2025-10-16 02:30:22.569 |   Max: 35.20C
2025-10-16 02:30:22.570 |   Avg: 25.80C
2025-10-16 02:30:22.571 |   Range: 16.70C
2025-10-16 02:30:23.678 | FRAME: topdon-tc001 #200 size=98304B timestamp=1729043423678
2025-10-16 02:30:30.123 | CONNECTION SUCCESS: SHIMMER shimmer3-a1b2 (2340ms)
2025-10-16 02:30:31.234 | FRAME: shimmer3-a1b2 #100 size=0B timestamp=1729043431234
2025-10-16 02:30:31.235 | GSR: shimmer3-a1b2
2025-10-16 02:30:31.236 |   Conductance: 2.345678 microsiemens
2025-10-16 02:30:31.237 |   Resistance: 426439.12 ohms
2025-10-16 02:30:45.678 | ERROR: topdon-tc001 - Frame callback
2025-10-16 02:30:45.679 |   Message: Buffer overflow
2025-10-16 02:30:45.680 |   Type: IOException
2025-10-16 02:30:45.681 |     at java.io.FileOutputStream.write(FileOutputStream.java:345)
2025-10-16 02:30:45.682 |     at com.buccancs.data.sensor.connector.topdon.TopdonThermalConnector$frameCallback$1.invoke(TopdonThermalConnector.kt:686)
```

## Benefits

1. **No ADB Required** - All diagnostics accessible in-app
2. **Real-time Monitoring** - See hardware status immediately
3. **Comprehensive Logging** - All events captured with context
4. **Performance Metrics** - Frame rates, drops, jitter tracked
5. **Error Tracking** - Stack traces with timestamps
6. **Field Testing Ready** - Export logs for remote analysis
7. **Developer Friendly** - Material 3 UI, easy navigation
8. **Auto-detection** - Automatically identifies TOPDON and Shimmer
9. **Persistent Logs** - Survives app restarts
10. **Professional Design** - Production-quality UI

## Testing Workflow Enhancement

Original 5-phase plan enhanced with debug tools:

**Phase 1: Environment Setup**
- Now includes Hardware Debug screen verification
- System info validation
- USB/Bluetooth capability confirmation

**Phase 2: TOPDON Testing**
- Real-time device detection
- Permission status visibility
- Frame callback monitoring
- Temperature data validation

**Phase 3: Shimmer Testing**
- Bonded device verification
- Connection status tracking
- GSR data validation
- Sample rate monitoring

**Phase 4: Multi-Device Testing**
- Both devices visible simultaneously
- No interference detection
- Frame rate comparison
- Coordinated logging

**Phase 5: Error Scenarios**
- Detailed error logs with context
- Stack traces for debugging
- Connection timing data
- Recovery attempt tracking

## Next Steps

1. Add navigation route to Hardware Debug screen
2. Add developer menu item for access
3. Build APK with debug tools
4. Test in emulator (limited USB/BLE)
5. Deploy to physical device
6. Execute Phase 1 with debug verification
7. Proceed with Phases 2-5 hardware validation
8. Collect and analyse debug logs
9. Document field results
10. Update troubleshooting guide

## Status

**Complete:** All debug tools implemented and ready for integration  
**Next:** Add navigation route and build APK  
**Ready:** Field testing with comprehensive diagnostics  
**Documentation:** Complete with examples and integration guide

## References

- hardware-debug-session-2025-10-16-0121.md - Full session log
- hardware-debug-summary-2025-10-16-0210.md - Executive summary
- build-status-2025-10-15-2300.md - Build blocker details
- backlog.md - Task tracking with debug tools entry
- dev-diary.md - Development log with tool creation details


