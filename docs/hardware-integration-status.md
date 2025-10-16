# Hardware Integration Status

**Last updated:** 2025-10-16  
**Status:** ⚠️ Partial - Detection working, streaming via stubs

## Current Situation

The BuccanCS app currently uses **stub/simulation implementations** for sensor hardware by default. This allows the application to compile and run without requiring physical sensors, which is useful for development and testing the orchestration logic.

### What Works Now

-  Bluetooth device discovery and pairing
- ✅ Detection of bonded Shimmer3 devices (shows real MAC addresses and names)
- ✅ USB device enumeration for Topdon thermal camera
- ✅ On-device camera and microphone access
- ✅ Simulated data streaming for testing workflows

### What Needs Work

- ❌ **Real Shimmer3 GSR data streaming** - Stub generates synthetic waveforms instead of reading from sensor
- ❌ **Topdon thermal live streaming** - Preview works but streaming integration incomplete  
- ⚠️ **Hardware lifecycle management** - Connection, configuration, and error recovery need vendor SDK integration

## Why Sensors Aren't Recognized for Streaming

The issue you're encountering is architectural, not a bug:

1. **DefaultShimmerHardwareClient** is a lightweight stub that *can* discover and list paired Shimmer devices via Android Bluetooth APIs
2. However, actual streaming requires integrating the vendor **ShimmerAndroidAPI** (located in `external/ShimmerAndroidAPI/`)
3. The stub was created to keep the rest of the pipeline compiling while the full SDK integration is completed
4. The `shimmer` module in the project wraps the external SDK but isn't fully wired to the app's data layer yet

## Immediate Workaround

### Option 1: Use Simulation Mode (Recommended for Testing)

The app has a **simulation toggle** in the UI:

1. Launch the BuccanCS app
2. On the main screen, tap "Enable Simulation"
3. This generates synthetic GSR data streams that exercise the full recording pipeline
4. Useful for testing desktop orchestration, session management, and data upload without hardware

### Option 2: Enable Real Hardware Detection (Partial)

The current implementation *does* detect real Bluetooth devices:

1. Pair your Shimmer3-678B via Android Settings → Bluetooth
2. In BuccanCS, tap "Scan for Devices" or "Refresh"
3. You'll see the real device listed with its MAC address
4. **Note:** Connecting will work but streaming will still use synthetic data until the SDK is integrated

## Completing Real Hardware Integration

To enable full hardware streaming, the following work is required:

### Phase 1: Shimmer SDK Integration (4-6 hours)

1. Create `RealShimmerHardwareClient` that wraps `com.shimmerresearch.android.Shimmer`  
2. Implement Shimmer SDK callbacks for connection state and data packets  
3. Map Shimmer's `ObjectCluster` data format to `ShimmerSample` model  
4. Handle firmware configuration, sensor enable flags, and calibration  
5. Wire the real client into `HardwareModule` with runtime feature detection

**Files to modify:**
- `app/src/main/java/com/buccancs/hardware/shimmer/RealShimmerHardwareClient.kt` (create)
- `app/src/main/java/com/buccancs/di/HardwareModule.kt` (update provider)
- `shimmer/build.gradle.kts` (already configured)

### Phase 2: Topdon Thermal Streaming (2-3 hours)

1. Complete `TopdonThermalConnector` streaming mode implementation
2. Wire thermal frame buffers from preview to recording pipeline
3. Add thermal calibration and palette application
4. Test with physical TC001 camera via USB

**Files to modify:**
- `app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonThermalConnector.kt`
- `app/src/main/java/com/buccancs/hardware/topdon/DefaultTopdonThermalClient.kt`

### Phase 3: Testing & Validation (4-6 hours)

1. Execute end-to-end recording session with all physical sensors
2. Validate data integrity (GSR sample rates, thermal frame counts, RGB/audio sync)
3. Test reconnection logic and error recovery
4. Measure time sync accuracy across devices

## Technical Details

### Shimmer SDK Location

```
external/
└── ShimmerAndroidAPI/
    └── ShimmerAndroidInstrumentDriver/
        └── ShimmerAndroidInstrumentDriver/
            └── src/main/java/
                └── com/shimmerresearch/android/Shimmer.java
```

The `shimmer` module is already configured to include these sources, but the production client needs to be written.

### Key SDK Classes

- `com.shimmerresearch.android.Shimmer` - Main device controller
- `com.shimmerresearch.driver.ObjectCluster` - Data packet container
- `com.shimmerresearch.driver.CallbackObject` - Event callbacks
- `com.shimmerresearch.driver.ShimmerDevice` - Hardware constants

### Data Mapping

```kotlin
// Shimmer SDK → BuccanCS model
val objectCluster: ObjectCluster = /* from SDK */
val gsrConductance = objectCluster.getFormatClusterValue(
    Shimmer.CHANNEL_TYPE.CAL, 
    "GSR Conductance"
)

val sample = ShimmerSample(
    timestampEpochMs = systemTime(),
    conductanceSiemens = gsrConductance,
    resistanceOhms = 1.0 / gsrConductance
)
```

## Related Documentation

- [Active Task Plan](../tasks/active-plan.md) - Hardware integration roadmap
- [System Overview](../system-overview.md) - Architecture and data flow
- [Requirements](../requirements.md) - FR1 hardware integration status

## Getting Help

If you need to complete the hardware integration:

1. Review the Shimmer Android API documentation in `external/ShimmerAndroidAPI/docs/`
2. Check existing stubs in `app/src/main/java/com/buccancs/hardware/shimmer/`
3. Reference the connector patterns in `app/src/main/java/com/buccancs/data/sensor/connector/`
4. See test fixtures in `app/src/test/` for mock data structures

The stub implementation in `DefaultShimmerHardwareClient` shows the expected interface; a real implementation needs to delegate to the vendor SDK instead of generating synthetic data.
