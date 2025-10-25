# Shimmer Integration Gap Analysis

This note captures the delta between the current Buccancs Shimmer implementation
and the behaviour of the upstream ShimmerCapturePure app. It focuses on features
that are stubbed, incomplete, or absent so future work can drive the integration
to parity.

---

## Snapshot of the current state

- The `ShimmerHardwareClient` contract is implemented by a synthetic stub that
  never forwards calls to the vendor SDK; it only generates random GSR data for
  tests (`hardware/shimmer/android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:23`).
- Compose screens surface connection status and settings but have no affordances
  for device discovery, streaming control, or live telemetry
  (`apps/android/src/main/java/com/buccancs/ui/shimmer/ShimmerScreen.kt:49`).
- In contrast, ShimmerCapturePure boots a bound `ShimmerService`, wires menus
  to real API calls, and presents full device lists through fragments
  (`external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:203` and
  `external/ShimmerCapturePure/sources/com/shimmerresearch/android/guiUtilities/supportfragments/ConnectedShimmersListFragment.java:52`).

---

## Hardware abstraction gaps

- The stub client does not create or bind to the vendor `ShimmerService`; all
  lifecycle work happens inside a singleton coroutine scope, so background
  streaming, reconnect logic, and firmware guards are missing
  (`hardware/shimmer/android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:35`).
- `scan`, `connect`, and `startStreaming` succeed regardless of the firmware
  state because they short-circuit to synthetic responses, whereas the reference
  app routes those commands through the manager and enforces state machines
  (`hardware/shimmer/android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:134` vs.
  `external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:309`).
- Notices emitted by the stub only log informational messages and never expose
  errors or firmware warnings, so callers cannot surface actionable feedback
  (`hardware/shimmer/android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:116`).

---

## Service lifecycle & permissions

- Buccancs never starts the vendor `ShimmerService`; the app therefore misses
  the long-lived BLE manager, foreground notifications, and retry logic that the
  reference app relies on (`external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:237`).
- Permission prompts stop after Compose requests; there is no bridging step that
  calls `startServiceandBTManager()` once permissions are granted, so the radio
  stack is never initialised outside simulations
  (`external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:212`).

---

## Device discovery & selection

- The Shimmer screen keeps a `showDeviceSelector` state but never toggles it,
  and the dialog is populated with empty lists, so operators cannot pick the MAC
  they want to use (`apps/android/src/main/java/com/buccancs/ui/shimmer/ShimmerScreen.kt:49` and
  `apps/android/src/main/java/com/buccancs/ui/components/shimmer/ShimmerDeviceSelectorDialog.kt:29`).
- `scanForDevices()` simply flips a loading flag before delegating to
  `deviceManagement.refreshInventory()`, but the hardware scan still relies on
  the stub adapter and the UI never surfaces discovered entries
  (`apps/android/src/main/java/com/buccancs/ui/shimmer/ShimmerScreenViewModel.kt:141` and
  `hardware/shimmer/android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:134`).
- Inventory results from `ShimmerSensorConnector` are serialised into device
  attributes, yet no Compose surface renders that list or allows users to retarget
  the device, unlike the list fragment approach in ShimmerCapturePure
  (`apps/android/src/main/java/com/buccancs/ui/MainViewModel.kt:760` and
  `external/ShimmerCapturePure/sources/com/shimmerresearch/android/guiUtilities/supportfragments/ConnectedShimmersListFragment.java:52`).

---

## Streaming & telemetry

- `ShimmerScreenViewModel` observes connection state but never consumes the
  `samples` flow, so `ShimmerDataCard` stays empty even when the stub is
  emitting data (`apps/android/src/main/java/com/buccancs/ui/shimmer/ShimmerScreenViewModel.kt:43` and
  `apps/android/src/main/java/com/buccancs/ui/components/shimmer/ShimmerDataCard.kt:9`).
- Because no samples are marshalled into the UI, there is no live preview of GSR
  or accelerometer data, unlike the plotting fragments in ShimmerCapturePure.
- There is no UI affordance to invoke `startStreaming` / `stopStreaming`; the
  streaming card only shows guidance text
  (`apps/android/src/main/java/com/buccancs/ui/components/shimmer/ShimmerStreamingCard.kt:11`).
- The reference app routes every sample through plotting, data sync, and SD
  logging workflows; Buccancs emits CSV rows from the connector but cannot show
  live plots or sensor health (`apps/android/src/main/java/com/buccancs/data/sensor/connector/shimmer/ShimmerSensorConnector.kt:361`).

---

## Configuration & persistence

- GSR range and sample rate updates write to the settings repository, but the
  stub `applySettings` method only logs the request and never writes to a device
  (`hardware/shimmer/android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:246`).
- Each managed connector spins up an `InMemoryShimmerSettingsRepository` so
  configuration changes are lost after process death unless the global config
  is also updated (`apps/android/src/main/java/com/buccancs/data/sensor/connector/shimmer/ShimmerConnectorManager.kt:266`).
- Logging, SD-card controls, firmware presets, and Verisense-specific options
  are entirely absent from the Compose UI; the baseline app exposes them through
  option menus and dialogs (`external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:247`).

---

## Auto-launch & operator tooling

- The Bluetooth auto-launch receiver only raises the main activity; it does not
  preselect the device or perform a follow-up attach to the service, so the user
  still lands on a stubbed experience
  (`apps/android/src/main/java/com/buccancs/hardware/shimmer/ShimmerAutoLaunchReceiver.kt:12`).
- There is no equivalent to the reference app’s data-sync view, plotting tabs,
  logging toggles, or firmware update prompts, leaving operators without
  diagnostics or export tooling.

---

## Recommended next steps

1. Replace `DefaultShimmerHardwareClient` with a proxy that binds to
   `ShimmerService` and delegates to the existing Java APIs packaged in
   `hardware/shimmer/android` (`hardware/shimmer/android/build.gradle.kts:1`).
2. Surface `ShimmerHardwareClient.devices` in the UI (e.g., a bottom sheet or
   dedicated screen) and make the dialog actionable.
3. Collect the `samples` flow inside `ShimmerScreenViewModel`, exposing recent
  values and trend summaries so the data card reflects live telemetry.
4. Add explicit streaming controls and status indicators that mirror the
  reference app’s menu actions.
5. Port critical operator tools (data sync, SD logging, firmware updates) from
   ShimmerCapturePure so parity scenarios—field data collection, diagnostics,
   recovery—are supported end-to-end.

Maintaining this checklist alongside implementation work will ensure the Shimmer
integration moves from a simulation stub to a production-ready hardware path.
