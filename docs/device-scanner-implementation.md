# Device Scanner Implementation Summary

## Overview Implemented Bluetooth and USB OTG device scanning functionality with background scanning, manual scan dialog, and Topdon device permission dialog.

## Files Created

### 1. DeviceScannerService.kt Location: `app/src/main/java/com/buccancs/hardware/DeviceScannerService.kt`

Features: - Manual scanning for Bluetooth and USB devices - Background periodic
scanning (configurable interval, default 30s) - Real-time device events
(attach/detach, permission granted/denied) - Automatic USB receiver registration
for device monitoring - State management with Kotlin Flow

Key Functions: - `startManualScan()` - Initiates manual Bluetooth and USB scan -
`startBackgroundScan(intervalMs)` - Starts periodic background scanning -
`stopBackgroundScan()` - Stops background scanning -
`requestUsbPermission(device)` - Requests USB permission for a device -
`scanUsbDevices()` - Scans connected USB devices - `scanState` - StateFlow of
current scan state (devices found, scanning status) - `deviceEvents` -
SharedFlow of device attachment/detachment events

### 2. DeviceScannerDialog.kt Location: `app/src/main/java/com/buccancs/ui/components/scanner/DeviceScannerDialog.kt`

Features: - Dialog UI for device scanning - Two tabs: USB Devices and Bluetooth
Devices - Device items show name, address/vendor ID, connection state - Grant
permission button for USB devices - Shows scanning progress and errors - Empty
state when no devices found

Components: - `DeviceScannerDialog` - Main dialog composable -
`TopdonDevicePermissionDialog` - Permission request dialog for Topdon devices -
`UsbDeviceItem` - USB device card with permission button -
`BluetoothDeviceItem` - Bluetooth device card with signal strength

### 3. MainActivity.kt (Updated) Location: `app/src/main/java/com/buccancs/ui/MainActivity.kt`

Changes: - Added USB device attachment intent handling - Detects Topdon devices
by vendor ID (11261, 1003, 3034, 1240) - Emits USB attachment events via
SharedFlow - Starts background device scanning on activity creation - Stops
background scanning on activity destroy - Handles `onNewIntent` for USB device
attachment

### 4. AppNavHost.kt (Updated) Location: `app/src/main/java/com/buccancs/ui/navigation/AppNavHost.kt`

Changes: - Added `usbAttachmentEvents` parameter to listen for USB attachments -
Shows `TopdonDevicePermissionDialog` when Topdon device is detected -
Automatically requests USB permission when user confirms - Integrates with
DeviceScannerService

### 5. DeviceScannerViewModel.kt Location: `app/src/main/java/com/buccancs/ui/navigation/DeviceScannerViewModel.kt`

Purpose: - Simple ViewModel to inject DeviceScannerService into composables -
Makes the service accessible via Hilt

### 6. DevicesScreen.kt (Updated) Location: `app/src/main/java/com/buccancs/ui/devices/DevicesScreen.kt`

Changes: - Added scan button (search icon) to top app bar - Shows
DeviceScannerDialog when scan button is clicked - Integrates with
DeviceScannerService - Handles device selection from scanner dialog

## Features Implemented

### 1. Manual Device Scanning - Users can tap the search icon in the Devices screen - Opens a dialog showing USB and Bluetooth devices - "Start Scan" button initiates Bluetooth discovery (12 second duration) - USB devices are scanned immediately - Shows device details: name, VID/PID for USB, address/signal for Bluetooth

### 2. Background Scanning - Automatically scans USB devices every 30 seconds - Runs in background without user interaction - Lightweight - only scans USB (Bluetooth scanning is battery intensive) - Starts when MainActivity is created - Stops when MainActivity is destroyed

### 3. USB Device Attachment Detection - Listens for `USB_DEVICE_ATTACHED` intent - Detects when USB device is plugged in - Identifies Topdon devices by vendor ID - Shows permission dialog automatically for Topdon devices

### 4. Topdon Device Permission Dialog - Appears when Topdon thermal camera is connected - Shows device name, vendor ID, and product ID - "Allow" button grants USB permission - "Deny" button dismisses dialog - Clean Material 3 design

### 5. USB Permission Management - Shows "Grant" button for USB devices without permission - Requests permission using PendingIntent - Updates UI when permission is granted/denied - Tracks permission state in real-time

### 6. Device Event Monitoring - Real-time events via Kotlin Flow - Events: Bluetooth device found, USB attached, USB detached, permission granted/denied - Can be observed by any part of the app

## Technical Details

### Permissions Already configured in AndroidManifest.xml: - `BLUETOOTH`, `BLUETOOTH_ADMIN` (API ≤ 30) - `BLUETOOTH_SCAN`, `BLUETOOTH_CONNECT`, `BLUETOOTH_ADVERTISE` (API 31+) - `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION` (for Bluetooth scanning) - `USB_PERMISSION`

### USB Device Filter Already configured in `app/src/main/res/xml/device_filter.xml`: - Topdon: vendor ID 11261 - Generic thermal: vendor ID 1003 - FLIR: vendor ID 3034 - Seek Thermal: vendor ID 1240

### Dependency Injection - `DeviceScannerService` is a `@Singleton` injected via Hilt - Requires `BluetoothService`, `UsbService`, `UsbManager` - Uses `@ApplicationScope` for coroutines

### Threading - All scanning operations run on coroutines - Bluetooth broadcast receiver callbacks handled safely - USB receiver callbacks handled safely - State updates via StateFlow/SharedFlow are thread-safe

## Usage

### For Users 1. **Manual Scan**: Tap search icon in Devices screen → tap "Start Scan" 2. **Background Scan**: Automatic, no user action needed 3. **USB Attachment**: Plug in Topdon device → permission dialog appears automatically

### For Developers

```kotlin
// Access the scanner service
@Inject lateinit var deviceScanner: DeviceScannerService

// Start manual scan
deviceScanner.startManualScan()

// Start background scan (30 second interval)
deviceScanner.startBackgroundScan(intervalMs = 30000)

// Stop background scan
deviceScanner.stopBackgroundScan()

// Observe scan state
deviceScanner.scanState.collect { state ->
    // state.isScanning
    // state.bluetoothDevices
    // state.usbDevices
    // state.error
}

// Observe device events
deviceScanner.deviceEvents.collect { event ->
    when (event) {
        is DeviceEvent.BluetoothDeviceFound -> { /* ... */ }
        is DeviceEvent.UsbDeviceAttached -> { /* ... */ }
        is DeviceEvent.UsbDeviceDetached -> { /* ... */ }
        is DeviceEvent.PermissionGranted -> { /* ... */ }
        is DeviceEvent.PermissionDenied -> { /* ... */ }
    }
}

// Request USB permission
deviceScanner.requestUsbPermission(usbDevice)
```

## Testing Checklist - [ ] Plug in Topdon device → permission dialog appears - [ ] Tap "Allow" → permission granted, dialog closes - [ ] Tap "Deny" → dialog closes without granting permission - [ ] Open Devices screen → tap search icon → dialog appears - [ ] In scanner dialog → tap "Start Scan" → devices appear - [ ] USB devices without permission show "Grant" button - [ ] Bluetooth devices show signal strength - [ ] Switch between USB and Bluetooth tabs - [ ] Background scan detects USB device plug/unplug - [ ] Scanner service stops when app is destroyed

## Future Enhancements 1. Save user preference for Topdon device auto-connect 2. Add device pairing for Bluetooth devices 3. Show notification when Topdon device is detected 4. Filter devices by type (thermal cameras only) 5. Add device nickname/labeling 6. Remember previously connected devices 7. Add scan history 8. Improve error handling and user feedback

## Notes - Background scanning is USB-only to preserve battery - Bluetooth scanning runs for 12 seconds when manual scan is initiated - Topdon vendor IDs: 11261, 1003, 3034, 1240 - Device scanner service is application-scoped singleton - All UI components use Material 3 design
