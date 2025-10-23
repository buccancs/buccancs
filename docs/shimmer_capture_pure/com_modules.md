# Core Module Map (`com/shimmerresearch`)

This note captures the major responsibilities and notable classes under `external/ShimmerCapturePure/sources/com/shimmerresearch`. Use it as a quick index when wiring parts of the SDK into Buccancs.

## `algorithms`
- Houses reusable DSP/feature extraction routines.
- `Filter.java` implements configurable FIR/IIR filters used by HR/GSR conversions.
- `orientation/OrientationModule*.java` provide 6/9 DOF attitude filters (gradient descent, quaternion tracking) for IMU fusion.
- `gyroOnTheFlyCal` and `verisense` subfolders contain device-specific calibration helpers.

## `android`
- Android-specific device wrappers.
- `Shimmer.java`, `Shimmer4Android.java` – manage lifecycle of Shimmer Classic / Shimmer4 devices (connection state, configuration, streaming callbacks).
- `VerisenseDeviceAndroid.java` bridges the generic `VerisenseDevice` to Android transports.
- Subpackages:
  - `guiUtilities` – fragments/dialogs for device lists, sensor selection, plotting, signal picker.
  - `manager` – `ShimmerBluetoothManagerAndroid.java` orchestrates scan/connect/stream, configuration pushes, logging.
  - `protocol` – Android implementation of the Verisense byte protocol (`VerisenseProtocolByteCommunicationAndroid.java`).
  - `shimmerService` – `ShimmerService.java` background service exposing device control, logging, plotting.

## `androidinstrumentdriver`
- Contains only `R.java`, the decompiled resource map; no logic.

## `androidradiodriver`
- Transport abstractions for Bluetooth Classic / BLE radios.
- `Shimmer3BLEAndroid.java`, `Shimmer3BleAndroidRadioByteCommunication.java`, `VerisenseBleAndroidRadioByteCommunication.java` encapsulate characteristic writes, notifications, and reconnection logic.
- `ShimmerRadioInitializerAndroid.java` handles socket creation, handshake, and version interrogation.

## `biophysicalprocessing`
- Feature-extraction algorithms:
  - `ECGtoHRAdaptive.java`, `PPGtoHRAlgorithm.java` convert raw waveforms into heart rate with adaptive filtering.
  - `GSRMetrics.java` computes conductance metrics from GSR signals.

## `bluetooth`
- Low-level Bluetooth state and command abstractions.
- `ShimmerBluetooth.java` is the core device controller: sends instructions, manages sensor masks, handles calibration, writes configuration bytes.
- `BtCommandDetails.java`, `BluetoothProgressReport*` provide logging/progress reporting per device or per command.
- `ShimmerDeviceCommsProtocolAdaptor.java` adapts high-level commands to radio protocol frames.

## `bluetoothmanager`
- Legacy GUI support for Bluetooth manager (thin wrappers around modern `android.manager` code).

## `comms`
- Shared communication infrastructure used by multiple transports.
- `SerialPortByteCommunication.java` / `wiredProtocol` implement serial communications (dock/USB).
- `radioProtocol` contains `LiteProtocol` (command framing), `CommsProtocolRadio.java` and `RadioListener.java` interfaces.
- `serialPortInterface` supplies HAL abstractions (reader/writer threads, error handling).

## `driver`
- Core data structures representing configuration and streamed data:
  - `Configuration.java` enumerates sensor IDs, channel names, units, compatibility tables.
  - `ObjectCluster.java` is the central container for time-stamped samples and derived channels.
  - `FormatCluster`, `SensorData`, `SensorDataArray` manage raw/calibrated values, units, timestamp metadata.
  - `MsgDock.java` and related classes support dock-specific communications.

## `driverUtilities`
- Support utilities:
  - `AssembleShimmerConfig.java` builds consistent configuration byte arrays from device clones.
  - `ChannelDetails.java`, `ConfigOptionDetails*.java` describe sensor channels and GUI configuration options.
  - `BluetoothDeviceDetails.java`, `ByteUtils.java`, `ConsoleDiverter.java` provide helper logic for device metadata and logging.

## `exception` / `exceptions`
- Custom exception hierarchy:
  - `DeviceNotPairedException.java` indicates pairing issues.
  - `ShimmerException.java`, `ConnectionExceptionListener.java` general error handling callbacks.

## `exgConfig`
- Extended ExG (biopotential) configuration helpers and enums.
- `ExGConfigOptionDetails.java` provides option metadata for ECG/EMG sensor settings and register maps.

## `grpc`
- Java gRPC control plane and BLE bridge:
  - `ShimmerServerGrpc.java`, `ShimmerGRPC.java` – generated code for the main Shimmer gRPC service.
  - `GrpcBLERadioByteCommunication.java`, `GrpcBLERadioByteTools.java` – connect to external BLE relays via gRPC.
  - `ShimmerClientGrpcStream.java` – client that forwards streamed `ObjectCluster` messages.

## `guiUtilities`
- Shared plotting infrastructure:
  - `AbstractPlotManager.java` defines series management, color assignment, and render hooks used by the Android plotting fragments.

## `managers`
- Legacy manager interface: `ShimmerBluetoothManager.java` (non-Android-specific), extended by the Android manager.

## `sensors`
- Object model for each physical or derived sensor.
- `AbstractSensor.java` base class; each `Sensor*.java` (e.g., `SensorGSR.java`, `SensorPPG.java`, `SensorBattVoltage.java`) defines channel mappings, configuration bytes, and calibration logic.
- `SensorECGToHRFw.java`, `ActionSetting.java` supply firmware-level control of sensor behaviours.

## `shimmer3`
- Device-specific utilities for classic Shimmer3.
- `communication/SpeedTestProtocol.java` handles throughput tests over radio.

## `shimmerConfig`
- Hard-coded factory configurations: `FixedShimmerConfigs.java` holds preset sensor masks / sample rates.

## `shimmercapture`
- Application layer:
  - `MainActivity.java` ties fragments, service binding, and UI events together.
  - `R.java` generated resource identifiers.

## `thirdpartyDevices`
- Integrations for non-Shimmer sensors.
- Example: `noninOnyxII` package wraps Nonin pulse oximeter data into Shimmer pipelines.

## `tools`
- Android-friendly tooling:
  - `FileUtils.java` (and synthetic companion) manage SAF/URI file operations.
  - `Logging.java` centralized logger with file output.
  - `PlotManagerAndroid.java` extends `AbstractPlotManager` with AndroidPlot-specific behaviour.

## `utilityfunctions`
- Math helpers: `ListStatistics.java`, `LombScarglePeriodogram.java` used by algorithms for spectral analysis and statistics.

## `verisense`
- Everything specific to the Verisense product line.
- `VerisenseDevice.java` extends `ShimmerDevice` with Verisense sensor set, configuration, and state machine.
- `communication/VerisenseProtocolByteCommunication.java` implements the Verisense binary protocol (packet framing, CRC, streaming logged data).
- `communication/payloads/*` define parsed payload structures (status, RWC schedule, config, event logs).
- `sensors/SensorGSRVerisense.java`, etc., adapt base sensor classes to Verisense hardware.
- `UtilVerisenseDriver.java`, `PendingEventSchedule.java` provide helper utilities and scheduling.

---

# Supporting Third-Party Folders

## Android plotting (`pl/flex_it/androidplot`)
- `MultitouchPlot.java` adds pinch/zoom and panning gestures to AndroidPlot.
- `XYSeriesShimmer.java` wraps streamed Shimmer data for plotting with automatic windowing and clear-on-limit behaviour.

## Large-array math (`pl/edu/icm/jlargearrays`)
- Off-heap array implementations (`DoubleLargeArray`, `FloatLargeArray`, etc.) and `LargeArrayArithmetics` for high-performance operations used by legacy DSP code.

## BLE helper (`it/gerdavax/easybluetooth`)
- Thin abstraction over classic Bluetooth socket APIs (pre-BLE support). Legacy path for older hardware; modern code primarily uses Android’s standard APIs.

## Okio / gRPC / Kotlin / JUnit / javax / Envoy / UDPA (`okio`, `io`, `kotlin`, `kotlinx`, `junit`, `javax`, `envoy`, `udpa`, `org`)
- Entire upstream libraries embedded in the APK:
  - Okio, Kotlin stdlib/coroutines, JUnit, javax annotations, Apache/BouncyCastle, gRPC stubs, Envoy & UDPA annotations.
- Treat them as vendored dependencies; Buccancs should pull official Maven artifacts to stay current.
