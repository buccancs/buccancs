# Desktop App - Android Virtual Device Test Run #2

**Test Date:** 2025-10-17 03:14 UTC  
**Emulator:** Pixel_9a (emulator-5554)  
**Desktop OS:** macOS

## Test Summary

✅ **Connection Status:** SUCCESSFUL  
✅ **Test Duration:** 1+ minutes of stable connection  
✅ **Reproducible:** Successfully connected on second run

## Test Execution Steps

### 1. Environment Cleanup
```bash
# Killed all previous emulator and adb processes
pkill -9 -f emulator
adb kill-server
```

### 2. Emulator Startup
```bash
~/Library/Android/sdk/emulator/emulator -avd Pixel_9a -no-snapshot-load
adb wait-for-device
# Wait for boot_completed property
```
**Result:** ✅ Emulator booted in ~15 seconds

### 3. Android App Launch
```bash
adb shell am start -W -n com.buccancs/.ui.MainActivity
adb shell input tap 650 1550  # Grant Bluetooth permission
```
**Result:** ✅ App started, permission granted

### 4. Desktop App Launch
```bash
./gradlew :desktop:run
```
**Result:** ✅ Desktop launched, gRPC server started

## Connection Timeline

| Time | Event |
|------|-------|
| T+2s | Desktop: mDNS service discovery started |
| T+2s | Desktop: gRPC server started on port 50051 |
| T+27s | Desktop: Command stream subscription from android-a09705a85f79386e |
| T+31s | Desktop: Device registered (protocol v1.0) |
| T+31s | Desktop: Device connected (no active session) |
| T+32s | Desktop: Time sync initiated |

## Connection Details

### Device Registration
- **Device ID:** android-a09705a85f79386e
- **Protocol Version:** v1.0
- **Connection Type:** gRPC over mDNS discovery
- **Session Status:** No active session (idle connection)

### Time Synchronization Metrics

| Timestamp | Drift (ms/s) | Offset (ms) | RTT (ms) | Quality |
|-----------|-------------|-------------|----------|---------|
| T+32s | -0.189 | 0.3 | 4.0 | Excellent |
| T+37s | 0.063 | 0.7 | 4.0 | Excellent |
| T+43s | 0.251 | 2.0 | 6.0 | Excellent |
| T+48s | 0.806 | 6.7 | 16.0 | Good |
| T+54s | -0.429 | 4.3 | 15.0 | Good |
| T+59s | 0.489 | 7.0 | 15.0 | Good |
| T+65s | 0.366 | 9.0 | 17.7 | Good |
| T+71s | -0.286 | 7.3 | 32.7 | Acceptable |

**Average RTT:** ~13ms  
**Clock Drift Range:** -0.429 to 0.806 ms/s  
**Status:** Stable synchronization with acceptable variance

## Key Logs

```
[DefaultDispatcher-worker-3] INFO MdnsServiceBrowser - Starting mDNS service discovery
[DefaultDispatcher-worker-3] INFO MdnsServiceBrowser - mDNS browser started successfully
[AWT-EventQueue-0] INFO GrpcServer - gRPC server started on port 50051
[DefaultDispatcher-worker-9] INFO CommandServiceImpl - Command stream subscription from android-a09705a85f79386e
[DefaultDispatcher-worker-1] INFO DeviceRepository - Registered device android-a09705a85f79386e
[DefaultDispatcher-worker-1] INFO OrchestrationServiceImpl - Device android-a09705a85f79386e registered with protocol version v1.0
[DefaultDispatcher-worker-9] INFO DeviceConnectionMonitor - Device android-a09705a85f79386e connected (no active session)
```

## Observations

1. **Discovery Time:** Device discovery took ~25 seconds from desktop app start
2. **Connection Stability:** Connection remained stable throughout the test period
3. **Time Sync:** Time synchronisation working correctly with minimal drift
4. **Network Latency:** RTT increased slightly over time (4ms → 32ms) but remained acceptable
5. **No Errors:** No connection errors or disconnections observed

## Files Generated

- `test_run2_connected.png` - Screenshot of connected emulator

## Conclusion

Second test run confirms the desktop orchestrator reliably connects to Android virtual devices. The connection establishment is reproducible, stable, and maintains low latency communication. Time synchronisation adapts to varying network conditions whilst maintaining acceptable clock drift levels.

**Status:** System ready for production testing with real hardware sensors.
