# Automated Desktop-Android Connection Test

## Overview

This automated test script verifies the connection between the desktop orchestrator app and an Android virtual device, automatically granting all required permissions via ADB before starting the app.

## Script Location

```bash
automation/test_desktop_android_connection.sh
```

## Prerequisites

- macOS with Android SDK installed
- Android Virtual Device (AVD) named `Pixel_9a` configured
- Gradle and project dependencies installed
- No other emulator instances running

## Permissions Granted

The script automatically grants the following runtime permissions:

### Bluetooth Permissions
- `BLUETOOTH_SCAN` - Scan for Bluetooth devices
- `BLUETOOTH_CONNECT` - Connect to paired Bluetooth devices
- `BLUETOOTH_ADVERTISE` - Advertise Bluetooth services

### Location Permissions
- `ACCESS_FINE_LOCATION` - Precise location (required for BLE)
- `ACCESS_COARSE_LOCATION` - Approximate location
- `ACCESS_BACKGROUND_LOCATION` - Location access in background

### Media Permissions
- `CAMERA` - Camera access for video capture
- `RECORD_AUDIO` - Audio recording
- `READ_MEDIA_IMAGES` - Read images from storage
- `READ_MEDIA_VIDEO` - Read videos from storage

### Storage Permissions
- `READ_EXTERNAL_STORAGE` - Read from external storage (API ≤32)
- `WRITE_EXTERNAL_STORAGE` - Write to external storage (API ≤32)

### System Permissions
- `POST_NOTIFICATIONS` - Display notifications
- `READ_PHONE_STATE` - Read phone state information

## Usage

### Basic Usage

```bash
cd /path/to/buccancs
./automation/test_desktop_android_connection.sh
```

### Expected Output

```
========================================
Desktop-Android Connection Test
========================================

[1/6] Cleaning up previous sessions...
✓ Cleanup complete

[2/6] Starting Android emulator...
✓ Emulator ready

[3/6] Granting all app permissions...
✓ Granted 14 permissions

[4/6] Starting Android app...
✓ Android app started

[5/6] Starting desktop app...
✓ Desktop app started successfully

[6/6] Monitoring connection...
✓✓✓ CONNECTION ESTABLISHED ✓✓✓

✓ Test completed successfully!
```

## Test Steps

### 1. Environment Cleanup
- Kills any running emulator processes
- Stops ADB server
- Ensures clean test environment

### 2. Emulator Startup
- Starts Android Virtual Device (Pixel_9a)
- Waits for device boot completion
- Verifies device is ready

### 3. Permission Granting
- Grants all 14 runtime permissions via ADB
- Verifies permissions were granted successfully
- Reports any permissions not applicable to current Android version

### 4. Android App Launch
- Starts the Buccancs app
- Takes initial screenshot (`test_android_screen.png`)
- Verifies app is running

### 5. Desktop App Launch
- Starts desktop orchestrator with Gradle
- Waits for gRPC server initialization
- Logs output to `desktop_test_output.log`

### 6. Connection Monitoring
- Monitors desktop logs for device registration
- Waits up to 60 seconds for connection
- Reports connection status and metrics

## Generated Files

After successful test:

| File | Description |
|------|-------------|
| `test_android_screen.png` | Screenshot after app launch |
| `test_android_connected.png` | Screenshot after connection established |
| `desktop_test_output.log` | Complete desktop app logs |

## Exit Codes

- `0` - Test passed, connection established successfully
- `1` - Test failed (cleanup issues, connection timeout, or app startup failure)

## Troubleshooting

### Emulator fails to start
```bash
# Check AVD exists
~/Library/Android/sdk/emulator/emulator -list-avds

# Create AVD if needed
# Use Android Studio AVD Manager
```

### Permission grant failures
Some permissions may not be applicable to the Android API level:
- This is normal and logged as warnings
- Core permissions (Bluetooth, Location) must succeed

### Connection timeout
If device doesn't register within 60 seconds:
1. Check `desktop_test_output.log` for errors
2. Verify emulator network connectivity
3. Ensure mDNS discovery is working

### Port already in use
```bash
# Find and kill process using port 50051
lsof -ti:50051 | xargs kill -9
```

## Script Configuration

Edit these variables in the script to customize:

```bash
AVD_NAME="Pixel_9a"              # Change AVD name
PACKAGE_NAME="com.buccancs"      # App package name
TIMEOUT=60                        # Connection timeout (seconds)
```

## Manual Permission Grant

If you need to grant permissions manually:

```bash
# Single permission
adb shell pm grant com.buccancs android.permission.BLUETOOTH_SCAN

# Check granted permissions
adb shell dumpsys package com.buccancs | grep "granted=true"

# Revoke permission
adb shell pm revoke com.buccancs android.permission.CAMERA
```

## Integration with CI/CD

The script can be used in automated testing pipelines:

```yaml
# Example GitHub Actions workflow
- name: Run Desktop-Android Connection Test
  run: |
    cd $GITHUB_WORKSPACE
    ./automation/test_desktop_android_connection.sh
  timeout-minutes: 5
```

## Performance Metrics

Typical execution times:
- Emulator boot: 10-20 seconds
- Permission grant: 2-3 seconds
- App startup: 3-5 seconds
- Desktop startup: 15-20 seconds
- Connection establishment: 2-5 seconds
- **Total: ~35-55 seconds**

## Related Documentation

- `desktop_android_test_results.md` - Manual test results (run 1)
- `desktop_android_test_run2.md` - Manual test results (run 2)
- `app/src/main/AndroidManifest.xml` - Complete permission declarations

## Version History

| Date | Version | Changes |
|------|---------|---------|
| 2025-10-17 | 1.0 | Initial automated test script with permission granting |
