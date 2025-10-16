# Hardware Validation Test Plan

**Last Modified:** 2025-10-15 06:15 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Test Plan

## Overview

Comprehensive validation plan for testing BuccanCS system with physical hardware: Android phone connected via WiFi,
Shimmer GSR unit via Bluetooth, and Topdon TC001 thermal camera via USB.

## Prerequisites

### Hardware Setup

- **Android Phone**: Samsung S22 or equivalent (Android 12+, USB 3.0)
- **Shimmer3 GSR+ Unit**: Charged, Bluetooth enabled, in range
- **Topdon TC001**: USB-C cable connected to phone
- **Desktop PC**: Running Windows/Linux, same WiFi network as phone
- **WiFi Network**: Stable connection with low latency

### Software Setup

- Desktop orchestrator running on PC
- Latest Android APK installed on phone
- Phone and PC on same WiFi network
- ADB debugging enabled (optional, for logs)

### Pre-Test Checks

```bash
# Verify desktop orchestrator is running
curl http://localhost:8080/health  # If health endpoint exists

# Check phone is reachable
ping <phone-ip-address>

# Verify ADB connection (optional)
adb devices

# Check disk space
df -h  # PC
adb shell df  # Phone
```

## Test Sequence

### Phase 1: Basic Connectivity (15 minutes)

#### Test 1.1: Phone-Desktop Connection

**Objective:** Verify Android app can reach desktop orchestrator

**Steps:**

1. Start desktop orchestrator
2. Note desktop PC IP address and port (default: 50051)
3. Open Android app
4. Go to Settings → Connection
5. Enter orchestrator IP and port
6. Tap "Test Connection"

**Expected:**

- Connection success message
- Device appears in desktop dashboard
- Green status indicator

**Validation:**

```bash
# Check gRPC server logs on desktop
grep "Device.*registered" desktop.log

# Check device repository
# Desktop UI should show device in "Connected Devices" panel
```

#### Test 1.2: Time Sync Validation

**Objective:** Verify time synchronisation meets NFR2 (5ms avg, 10ms max)

**Steps:**

1. Ensure phone is connected to orchestrator
2. Observe time sync metrics in Android app (if visible)
3. Check desktop logs for sync reports

**Expected:**

- Round-trip time < 100ms
- Clock offset < 5ms average
- No drift warnings

**Validation:**

```bash
# Desktop logs should show sync reports
grep "Sync report from" desktop.log | tail -20

# Look for drift warnings
grep "drift" desktop.log
```

### Phase 2: Hardware Detection (20 minutes)

#### Test 2.1: Shimmer GSR Discovery

**Objective:** Verify Bluetooth LE connection to Shimmer3 GSR+

**Steps:**

1. Power on Shimmer unit (LED should blink)
2. In Android app, go to Hardware tab
3. Tap "Scan for Shimmer Devices"
4. Wait for device to appear in list
5. Tap device to connect
6. Wait for connection success

**Expected:**

- Shimmer appears in scan results within 10 seconds
- Connection establishes within 5 seconds
- Battery level displayed
- Sensor status shows "Ready"

**Validation:**

```bash
# Check Android logs via ADB
adb logcat | grep -i shimmer

# Look for connection success
adb logcat | grep "Shimmer.*connected"
```

**Troubleshooting:**

- If no devices found: Check Shimmer battery, ensure Bluetooth is enabled
- If connection fails: Try power cycling Shimmer unit
- If repeated failures: Check circuit breaker logs (should prevent battery drain)

#### Test 2.2: Topdon TC001 Detection

**Objective:** Verify USB connection to Topdon thermal camera

**Steps:**

1. Connect Topdon TC001 to phone via USB-C cable
2. Grant USB permission when prompted
3. In Android app, go to Hardware tab
4. Topdon should auto-detect and show status

**Expected:**

- USB permission dialog appears
- Camera initialises within 5 seconds
- Live thermal preview visible
- Frame rate displayed (should be ~30fps)

**Validation:**

```bash
# Check Android logs
adb logcat | grep -i topdon

# Look for USB attachment
adb logcat | grep "USB.*attached"
```

**Troubleshooting:**

- If not detected: Check USB cable, try different USB port
- If permission denied: Manually grant permission in Android settings
- If camera fails to initialise: Check Topdon SDK logs

#### Test 2.3: RGB Camera Verification

**Objective:** Verify built-in camera access

**Steps:**

1. In Android app, go to Hardware tab
2. Select "RGB Camera"
3. Preview should appear

**Expected:**

- Camera preview loads within 2 seconds
- Preview shows live video feed
- Resolution displayed (should be 1920x1080)

**Validation:**

```bash
# Check camera initialisation
adb logcat | grep "CameraX\|Camera2"
```

### Phase 3: Single Session Recording (30 minutes)

#### Test 3.1: Short Recording Test (2 minutes)

**Objective:** Verify basic recording functionality with all sensors

**Steps:**

1. Ensure all hardware is connected (Shimmer, Topdon, RGB camera)
2. In desktop orchestrator, create new session
3. Add phone as participant device
4. Click "Start Recording"
5. Wait 2 minutes
6. Click "Stop Recording"
7. Wait for upload to complete

**Expected:**

- Recording starts on phone within 1 second of command
- All sensors show "Recording" status
- Desktop shows live telemetry
- Recording stops cleanly
- Files upload to desktop

**Validation:**

```bash
# Check session directory on desktop
ls -lh ~/buccancs-sessions/<session-id>/devices/<device-id>/

# Expected files:
# - gsr.csv (Shimmer data)
# - thermal_video.mp4 (Topdon recording)
# - rgb_video.mp4 (RGB camera)
# - metadata.json
```

**Data Quality Checks:**

```bash
# Check GSR data
head -20 gsr.csv
# Should have timestamp, GSR values, possibly accelerometer

# Check sample count (128Hz * 120 seconds = 15,360 samples)
wc -l gsr.csv

# Verify thermal video
ffprobe thermal_video.mp4
# Should show ~30fps, 120 seconds duration

# Verify RGB video
ffprobe rgb_video.mp4
```

#### Test 3.2: Sync Signal Test

**Objective:** Verify event markers are recorded with timestamps

**Steps:**

1. Start recording as in Test 3.1
2. After 30 seconds, send sync signal from desktop (flash LED)
3. Note the time
4. Continue recording for 30 more seconds
5. Stop recording

**Expected:**

- Sync signal reaches phone immediately
- Event marker recorded in metadata
- Timestamp aligns with actual event time

**Validation:**

```bash
# Check metadata for events
jq '.events' metadata.json

# Verify event timestamp
# Should be within 10ms of actual time
```

### Phase 4: Time Sync Accuracy Measurement (20 minutes)

#### Test 4.1: WiFi Network Latency Test

**Objective:** Measure actual time sync accuracy over WiFi (NFR2 validation)

**Steps:**

1. Ensure phone connected to orchestrator
2. Let time sync run for 5 minutes
3. Collect sync reports from desktop logs
4. Calculate statistics

**Expected:**

- Average offset < 5ms
- Maximum offset < 10ms
- Round-trip time stable

**Validation:**

```bash
# Extract sync reports from logs
grep "Sync report from" desktop.log > sync_reports.txt

# Use Python/script to calculate statistics
python3 << 'EOF'
import re
import statistics

offsets = []
rtts = []

with open('sync_reports.txt', 'r') as f:
    for line in f:
        # Extract offset and RTT from log line
        # Format: "Sync report from DEVICE offset=X.Xms rtt=Y.Yms"
        offset_match = re.search(r'offset=([-\d.]+)ms', line)
        rtt_match = re.search(r'rtt=([\d.]+)ms', line)
        
        if offset_match and rtt_match:
            offsets.append(abs(float(offset_match.group(1))))
            rtts.append(float(rtt_match.group(1)))

if offsets:
    print(f"Time Sync Statistics (n={len(offsets)}):")
    print(f"  Avg offset: {statistics.mean(offsets):.3f} ms")
    print(f"  Max offset: {max(offsets):.3f} ms")
    print(f"  Std dev:    {statistics.stdev(offsets):.3f} ms")
    print(f"  Avg RTT:    {statistics.mean(rtts):.3f} ms")
    print(f"  Max RTT:    {max(rtts):.3f} ms")
    print()
    print(f"NFR2 Compliance:")
    print(f"  Avg < 5ms:  {'PASS' if statistics.mean(offsets) < 5.0 else 'FAIL'}")
    print(f"  Max < 10ms: {'PASS' if max(offsets) < 10.0 else 'FAIL'}")
EOF
```

### Phase 5: Long Duration Test (2+ hours)

#### Test 5.1: 120-Minute Session

**Objective:** Verify system stability and data quality over extended recording

**Steps:**

1. Ensure all hardware connected and charged
2. Start recording session
3. Let record for 120 minutes (2 hours)
4. Monitor periodically for issues
5. Stop recording
6. Verify complete upload

**Expected:**

- No crashes or disconnections
- Continuous data collection
- All files upload successfully
- Battery consumption reasonable

**Monitoring:**

```bash
# Monitor desktop orchestrator logs
tail -f desktop.log | grep -E "error|warning|drift"

# Monitor phone battery (if ADB connected)
watch -n 60 "adb shell dumpsys battery | grep level"

# Monitor storage usage
watch -n 60 "df -h ~/buccancs-sessions"
```

**Post-Test Validation:**

```bash
# Check total samples collected
# GSR at 128Hz * 7200 seconds = 921,600 samples
wc -l gsr.csv

# Check video duration
ffprobe thermal_video.mp4 | grep Duration
# Should be ~7200 seconds (120 minutes)

# Check for gaps in data
# Use analysis script to detect timestamp gaps
python3 tools/perf/analyze_metrics.py --check-gaps gsr.csv
```

### Phase 6: Multi-Device Scenario (if multiple phones available)

#### Test 6.1: Two-Device Synchronised Recording

**Objective:** Verify multi-device coordination and synchronisation

**Steps:**

1. Connect two phones to orchestrator
2. Each phone has Shimmer and/or camera
3. Start session from desktop
4. Both phones should start recording simultaneously
5. Send sync signal
6. Stop session
7. Verify both uploads complete

**Expected:**

- Both devices receive start command within 100ms
- Sync signal reaches both devices simultaneously
- Timestamps aligned within 10ms
- Both datasets complete

**Validation:**

```bash
# Check both device directories exist
ls ~/buccancs-sessions/<session-id>/devices/

# Compare event timestamps
jq '.events[0].timestampEpochMs' device-001/metadata.json
jq '.events[0].timestampEpochMs' device-002/metadata.json
# Should differ by < 10ms

# Check data alignment
# Compare first GSR timestamp from both devices
head -3 device-001/gsr.csv
head -3 device-002/gsr.csv
```

## Data Collection for Thesis

### Required Metrics

For thesis evaluation chapter, collect:

1. **Time Sync Accuracy:**
    - 100+ samples of offset and RTT
    - Mean, max, std dev
    - Drift over time

2. **Sample Rates:**
    - Actual GSR Hz achieved
    - Actual thermal fps achieved
    - Frame drop rate

3. **System Performance:**
    - CPU usage during recording
    - Memory consumption
    - Battery drain rate
    - Storage write rate

4. **Reliability:**
    - Session success rate
    - Upload completion rate
    - Reconnection success rate
    - Data loss incidents

### Data Export

```bash
# Export time sync data
grep "Sync report" desktop.log > thesis_data/timesync.txt

# Export session metadata
cp -r ~/buccancs-sessions/<session-id> thesis_data/

# Export performance metrics
adb pull /sdcard/Android/data/com.buccancs/files/performance_metrics.jsonl thesis_data/

# Create summary report
python3 tools/perf/analyze_metrics.py thesis_data/performance_metrics.jsonl > thesis_data/performance_summary.txt
```

## Troubleshooting Guide

### Phone Not Connecting to Orchestrator

**Symptoms:** Connection timeout, "Cannot reach orchestrator" error

**Checks:**

- Verify phone and PC on same WiFi network
- Ping PC from phone (if network tools available)
- Check firewall isn't blocking port 50051
- Verify correct IP address entered

**Solutions:**

```bash
# Check gRPC server is listening
netstat -an | grep 50051

# Temporarily disable firewall
sudo ufw allow 50051  # Linux
```

### Shimmer Not Connecting

**Symptoms:** "Connection failed" after scan finds device

**Checks:**

- Shimmer battery level
- Bluetooth enabled on phone
- No other app connected to Shimmer
- Circuit breaker not tripped

**Solutions:**

- Power cycle Shimmer (remove battery, wait 10 seconds, replace)
- Clear Bluetooth cache on phone
- Check app logs for circuit breaker status

### Topdon Not Initialising

**Symptoms:** No USB permission dialog, or camera stays in "Initialising" state

**Checks:**

- USB cable firmly connected
- USB debugging enabled on phone
- Topdon SDK library present in APK

**Solutions:**

- Try different USB cable
- Manually grant USB permission in Settings
- Check USB mode (should be MTP or PTP, not charging only)

### Upload Failing

**Symptoms:** Recording completes but files don't appear on desktop

**Checks:**

- Network connectivity
- Desktop session directory permissions
- Available disk space

**Solutions:**

```bash
# Check desktop logs for upload errors
grep "upload\|transfer" desktop.log | tail -50

# Check phone upload queue
adb shell ls /sdcard/Android/data/com.buccancs/files/pending_uploads/

# Manually retry upload
# (Use Android app "Retry Failed Uploads" button if exists)
```

## Test Results Template

```
# Hardware Validation Results

Date: YYYY-MM-DD
Tester: Name
Environment: WiFi network name, signal strength

## Hardware
- Phone: Model, Android version
- Shimmer: Unit ID, battery level
- Topdon: Connected via USB
- PC: OS, specs

## Test Results

### Connectivity
- Phone-Desktop: [ ] PASS [ ] FAIL - Notes:
- Shimmer BLE: [ ] PASS [ ] FAIL - Notes:
- Topdon USB: [ ] PASS [ ] FAIL - Notes:

### Time Sync (NFR2)
- Average offset: X.XX ms [ ] < 5ms
- Maximum offset: X.XX ms [ ] < 10ms
- Compliance: [ ] PASS [ ] FAIL

### Recording Quality
- GSR samples collected: XXXXXX
- Expected samples: XXXXXX
- Completeness: XX.X%
- Thermal video duration: XX:XX
- RGB video duration: XX:XX

### Long Duration (120 min)
- Session completed: [ ] YES [ ] NO
- Issues encountered: None / List issues
- Battery remaining: XX%
- Data uploaded: [ ] Complete [ ] Partial [ ] Failed

### Notes
(Any observations, issues, or anomalies)

## Thesis Data Collected
- Time sync measurements: YES/NO
- Performance metrics: YES/NO  
- Sample rate validation: YES/NO
- Multi-device sync: YES/NO

## Conclusion
Overall system validation: [ ] PASS [ ] PASS WITH ISSUES [ ] FAIL
Ready for thesis submission: [ ] YES [ ] NO - Reason:
```

## Post-Test Actions

1. **Archive test data:**
   ```bash
   mkdir -p thesis_data/validation_$(date +%Y%m%d)
   cp -r ~/buccancs-sessions/<session-id> thesis_data/validation_$(date +%Y%m%d)/
   cp desktop.log thesis_data/validation_$(date +%Y%m%d)/
   ```

2. **Update documentation:**
    - Record results in dev-diary.md
    - Update VALIDATION_CHECKLIST with completion status
    - Note any issues in BACKLOG.md

3. **Generate thesis figures:**
    - Time sync accuracy plot
    - Sample rate consistency graph
    - System resource usage charts
    - Multi-device timeline visualisation

4. **Prepare evaluation chapter data:**
    - Statistical analysis of measurements
    - Comparison with NFR requirements
    - Performance benchmarks
    - Reliability metrics
