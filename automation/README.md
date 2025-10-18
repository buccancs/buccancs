# Automation Scripts

Collection of automated test scripts for the Buccancs desktop-Android orchestration system.

## Available Scripts

### 1. Desktop-Android Connection Test
**File:** `test_desktop_android_connection.sh`  
**Purpose:** Automated connection test with permission granting  
**Runtime:** ~48 seconds  
**Features:**
- Automatically grants all 14 runtime permissions via ADB
- Starts emulator and desktop app
- Verifies connection establishment
- Generates screenshots and logs

**Usage:**
```bash
./automation/test_desktop_android_connection.sh
```

**Outputs:**
- `test_android_screen.png` - Screenshot after app launch
- `test_android_connected.png` - Screenshot after connection
- `desktop_test_output.log` - Desktop logs

---

### 2. Headless Command Infrastructure Test
**File:** `test_command_infrastructure.sh`  
**Purpose:** Headless verification of command infrastructure  
**Runtime:** ~45 seconds  
**Features:**
- Runs emulator in headless mode (`-no-window -no-audio`)
- Runs desktop orchestrator headless (`runHeadlessServer`)
- Verifies command stream infrastructure
- Suitable for CI/CD

**Usage:**
```bash
./automation/test_command_infrastructure.sh
```

**Outputs:**
- `logs/headless_command_test/desktop.log`
- `logs/headless_command_test/android_commands.log`
- `logs/headless_command_test/emulator.log`

---

### 3. Headless Commands Test (Extended)
**File:** `test_headless_commands.sh`  
**Purpose:** Extended headless testing template  
**Status:** Template for future enhancement  
**Features:**
- Full headless environment setup
- Command simulation placeholders
- Comprehensive logging

**Usage:**
```bash
chmod +x automation/test_headless_commands.sh
./automation/test_headless_commands.sh
```

---

### 4. gRPC Command Sender (Python)
**File:** `send_grpc_command.py`  
**Purpose:** Python client for sending commands  
**Status:** Conceptual (requires proto compilation)  
**Features:**
- Command payload generation
- Session and device ID support
- START/STOP command types

**Usage:**
```bash
python3 automation/send_grpc_command.py <session_id> <device_id> [START|STOP]
```

---

## Test Comparison

| Script | Mode | UI Required | Duration | CI/CD Ready | Permissions |
|--------|------|-------------|----------|-------------|-------------|
| test_desktop_android_connection.sh | Normal | No (automated) | 48s | ✅ Yes | ✅ Auto-granted |
| test_command_infrastructure.sh | Headless | No | 45s | ✅ Yes | ✅ Auto-granted |
| test_headless_commands.sh | Headless | No | Variable | ⚠️ Template | ✅ Auto-granted |

## Prerequisites

### Required Software
- macOS with Android SDK
- Android Virtual Device named `Pixel_9a`
- Gradle (via gradlew)
- ADB tools

### Environment Setup
```bash
# Check Android SDK
ls ~/Library/Android/sdk/emulator/emulator
ls ~/Library/Android/sdk/platform-tools/adb

# Check AVD
~/Library/Android/sdk/emulator/emulator -list-avds

# Verify Gradle
./gradlew --version
```

## Common Permissions Granted

All scripts automatically grant these runtime permissions:
- `BLUETOOTH_SCAN` - BLE scanning
- `BLUETOOTH_CONNECT` - Bluetooth connections
- `BLUETOOTH_ADVERTISE` - Bluetooth advertising
- `ACCESS_FINE_LOCATION` - Precise location (for BLE)
- `ACCESS_COARSE_LOCATION` - Approximate location
- `ACCESS_BACKGROUND_LOCATION` - Background location
- `CAMERA` - Camera access
- `RECORD_AUDIO` - Microphone access
- `READ_MEDIA_IMAGES` - Read images
- `READ_MEDIA_VIDEO` - Read videos
- `READ_EXTERNAL_STORAGE` - Read storage (API ≤32)
- `WRITE_EXTERNAL_STORAGE` - Write storage (API ≤32)
- `POST_NOTIFICATIONS` - Notifications
- `READ_PHONE_STATE` - Phone state

## Troubleshooting

### Emulator Won't Start
```bash
# Check if emulator is already running
ps aux | grep emulator

# Kill existing instances
pkill -9 -f emulator

# Check AVD configuration
~/Library/Android/sdk/emulator/emulator -avd Pixel_9a -verbose
```

### Permission Grant Failures
```bash
# Manually check which permissions failed
adb shell dumpsys package com.buccancs | grep "granted=false"

# Grant manually
adb shell pm grant com.buccancs android.permission.CAMERA
```

### Connection Timeout
```bash
# Check desktop logs
cat desktop_test_output.log | grep ERROR

# Check Android logs
adb logcat | grep -i buccancs

# Verify network
adb shell netstat -an | grep 50051
```

### Port Already in Use
```bash
# Find process using port 50051
lsof -ti:50051

# Kill the process
lsof -ti:50051 | xargs kill -9
```

## Log Locations

### Automated Test Logs
- Desktop: `desktop_test_output.log` (root directory)
- Android: Via `adb logcat`
- Screenshots: `test_*.png` (root directory)

### Headless Test Logs
- Desktop: `logs/headless_command_test/desktop.log`
- Android: `logs/headless_command_test/android_commands.log`
- Emulator: `logs/headless_command_test/emulator.log`

## CI/CD Integration

### GitHub Actions Example
```yaml
name: Desktop-Android Integration Test

on: [push, pull_request]

jobs:
  test:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v3
      
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          
      - name: Run Headless Test
        run: |
          ./automation/test_command_infrastructure.sh
        timeout-minutes: 5
        
      - name: Upload Logs
        if: always()
        uses: actions/upload-artifact@v3
        with:
          name: test-logs
          path: logs/
```

## Development Workflow

### 1. Quick Connection Test
```bash
# After code changes, verify connection still works
./automation/test_desktop_android_connection.sh
```

### 2. Headless Regression Test
```bash
# Run full headless test before committing
./automation/test_command_infrastructure.sh
```

### 3. Manual Verification
```bash
# If automated tests pass but issues persist
./gradlew :desktop:run  # Launch with UI
# Then interact manually
```

## Documentation

- `README_DESKTOP_ANDROID_TEST.md` - Detailed connection test documentation
- `../AUTOMATED_TEST_SUMMARY.md` - First automated test results
- `../HEADLESS_COMMAND_TEST_SUMMARY.md` - Headless test results

## Contributing

When adding new automation scripts:
1. Make scripts executable: `chmod +x script.sh`
2. Add error handling and cleanup traps
3. Use colored output for readability
4. Generate logs in `logs/` directory
5. Document in this README
6. Create summary document for test results

## Version History

| Date | Version | Changes |
|------|---------|---------|
| 2025-10-17 | 1.0 | Initial automation scripts |
| 2025-10-17 | 1.1 | Added headless testing support |
