# Shimmer & Topdon Integration Roadmap

## Goals
- Decouple our production code from the vendor sample repositories while relying on the shipped `jar`/`aar` artifacts only.
- Wrap vendor SDK behaviour (device discovery, connection lifecycle, streaming, settings, calibration) in Buccancs-owned adapter layers with deterministic contracts.
- Preserve scientific accuracy and hardware capabilities (firmware metadata, EXG presets, thermal calibration) while improving reliability and observability.
- Establish documentation and acceptance checks that future work can reference during iterative delivery.

## Current Findings
- **Shimmer**  
  - Vendor manager maintains paired-device caches, BLE scans, firmware/hardware metadata, retry logic, and EXG configuration helpers (`external/ShimmerAndroidAPI/.../ShimmerBluetoothManagerAndroid.java`).  
  - Our `ShimmerSensorConnector` mirrors basic handler flow but lacks SDK-backed retry, firmware insight, multi-device cache seeding, and the richer configuration API (`app/src/main/java/com/buccancs/data/sensor/connector/shimmer/ShimmerSensorConnector.kt`).  
  - `ShimmerConnectorManager` creates connectors from configuration only, so newly paired devices are not auto-added.
- **Topdon**  
  - Sample projects centralize `USBMonitor`, instantiate `IRCMD`, leverage `LibIRProcess` for temperature/gain control, and restart previews on state changes (`external/example_topdon_sdk/libir_sample/.../IRUVC.java`, `external/original-topdon-app/component/thermal-ir/...`).  
  - Our `TopdonThermalConnector` initializes `USBMonitor` per connector, never creates `IRCMD`, falls back to linear temperature conversion, and omits calibration/shutter/palette commands (`app/src/main/java/com/buccancs/data/sensor/connector/topdon/TopdonThermalConnector.kt`).  
  - Settings repositories collect user intent but do not currently translate those preferences into SDK command calls.

## Planned Steps
1. **Artifact Baseline**
   - Inventory public classes/methods needed from `sdk/libs/*.jar` and `sdk/libs/topdon.aar`.  
   - Confirm signature parity between sample usage and the distributed binaries; document any reflection requirements.
2. **Adapter Interface Design**
   - Define `ShimmerClient` and `TopdonThermalClient` interfaces (inventory, connect/disconnect, streaming, settings, telemetry).  
   - Specify domain events/DTOs to replace direct SDK objects.
3. **Adapter Implementation**
   - Implement adapters using only bundled artifacts, reproducing required behaviour (device cache, handler dispatch, firmware metadata, IRCMD command flow, temperature normalization).  
   - Introduce shared USB monitor service and retry/backoff logic mirroring vendor resilience.
4. **Integration & Refactoring**
   - Wire adapters into `ShimmerConnectorManager` and `TopdonConnectorManager`, removing direct references to sample source.  
   - Translate stored settings into adapter calls (EXG presets, palettes, emissivity, gain).  
   - Add structured logging/metrics for connection, streaming, and command failures.
5. **Validation**
   - Add automated tests around adapter contracts (handler event simulation, USB monitor lifecycle, IRCMD command invocations).  
   - Run end-to-end hardware smoke tests to verify streaming, calibration changes, and artifact generation without sample code dependencies.  
   - Deprecate and eventually remove `external/…` sample directories once parity is confirmed.

## Acceptance Criteria
- All production modules reference only Buccancs adapter interfaces; no Kotlin/Java code imports classes from the sample repositories.
- Shimmer discovery uses bonded + BLE scan data, maintains firmware/hardware metadata, applies EXG/sample-rate settings, and retries connections via the adapter.
- Topdon integration manages a single USB monitor service, initializes `IRCMD`, honors palette/calibration/shutter commands, and derives temperatures through SDK calibration routines.
- Domain repositories and UI receive the same or richer device/stream status data compared to the current implementation (including firmware, gain, temperature ranges).
- Automated tests cover handler/state transitions and USB permission flows; manual test checklist validated on real hardware.
- Documented teardown plan for removing `external/example_topdon_sdk` and `external/original-topdon-app` after verification.
