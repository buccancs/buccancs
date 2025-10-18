# Bluetooth and USB Device Scanner - Feature Summary

## What Was Added

### 1. **Device Scanner Service**
A comprehensive service that handles:
- **Manual scanning** for Bluetooth and USB devices
- **Background periodic scanning** (30-second intervals by default)
- **Real-time device monitoring** via Kotlin Flows
- **USB permission management**

### 2. **Device Scanner Dialog**
A user-friendly dialog that shows:
- All connected USB devices with their details
- Discovered Bluetooth devices with signal strength
- Grant permission buttons for USB devices
- Tabbed interface to switch between USB and Bluetooth
- Real-time scanning status

### 3. **Topdon Device Auto-Detection**
When a Topdon thermal imaging device is connected:
- A permission dialog automatically appears
- Shows device details (name, vendor ID, product ID)
- User can allow or deny access
- If allowed, the app requests USB permission

### 4. **Background Scanning**
- Automatically scans for USB devices every 30 seconds
- Lightweight implementation to preserve battery
- Starts when the app launches
- Stops when the app is closed

## How to Use

### For Users

#### Manual Device Scan
1. Open the **Devices** screen
2. Tap the **search icon** (🔍) in the top-right corner
3. In the dialog, tap **"Start Scan"**
4. View discovered devices in the **USB Devices** or **Bluetooth Devices** tabs
5. For USB devices without permission, tap **"Grant"** to request access

#### Topdon Device Connection
1. **Plug in your Topdon thermal camera**
2. A permission dialog will appear automatically
3. Review the device information
4. Tap **"Allow"** to grant access
5. The device will be ready to use

### For Developers

```kotlin
// Inject the scanner service
@Inject lateinit var deviceScanner: DeviceScannerService

// Start a manual scan
deviceScanner.startManualScan()

// Observe scan results
lifecycleScope.launch {
    deviceScanner.scanState.collect { state ->
        println("Scanning: ${state.isScanning}")
        println("USB devices: ${state.usbDevices.size}")
        println("Bluetooth devices: ${state.bluetoothDevices.size}")
    }
}

// Listen for device events
lifecycleScope.launch {
    deviceScanner.deviceEvents.collect { event ->
        when (event) {
            is DeviceEvent.UsbDeviceAttached -> {
                println("USB device attached: ${event.device.name}")
            }
            is DeviceEvent.BluetoothDeviceFound -> {
                println("Bluetooth device found: ${event.device.name}")
            }
            // ... handle other events
        }
    }
}

// Request USB permission
deviceScanner.requestUsbPermission(usbDevice)

// Start background scanning
deviceScanner.startBackgroundScan(intervalMs = 30000)

// Stop background scanning
deviceScanner.stopBackgroundScan()
```

## Technical Details

### Files Modified
- `MainActivity.kt` - Added USB intent handling and background scanning
- `AppNavHost.kt` - Integrated permission dialog for Topdon devices
- `DevicesScreen.kt` - Added scan button and scanner dialog

### Files Created
- `DeviceScannerService.kt` - Core scanning service
- `DeviceScannerDialog.kt` - UI components for device scanning
- `DeviceScannerViewModel.kt` - ViewModel for Hilt injection

### Supported Devices
The app automatically detects these thermal camera brands:
- **Topdon** (Vendor ID: 11261)
- **Generic Thermal Cameras** (Vendor ID: 1003)
- **FLIR** (Vendor ID: 3034)
- **Seek Thermal** (Vendor ID: 1240)

### Permissions
All required permissions are already configured:
- Bluetooth scanning and connection
- Location (required for Bluetooth on older Android)
- USB access

## Key Features

✅ **Manual scanning** - User-initiated device discovery  
✅ **Background scanning** - Automatic USB device monitoring  
✅ **Auto-detection** - Topdon devices trigger permission dialog  
✅ **Real-time updates** - Live device list via Kotlin Flow  
✅ **USB permission handling** - One-tap permission requests  
✅ **Material 3 design** - Modern, clean UI  
✅ **Battery efficient** - Background scan is USB-only  

## Example Use Cases

1. **Finding a Shimmer device**: Open scanner dialog, switch to Bluetooth tab, start scan, select your device
2. **Connecting Topdon camera**: Just plug it in - permission dialog appears automatically
3. **Monitoring USB devices**: Background scan detects when devices are plugged in or removed
4. **Debugging connectivity**: Use the scanner dialog to check if devices are visible and have permissions

## Future Enhancements

Potential improvements for future versions:
- Device pairing automation
- Remember user preferences for specific devices
- Notification when target device is detected
- Device filtering by type
- Scan history
- Device nicknames

## Reference

For detailed implementation notes, see: `docs/device-scanner-implementation.md`
