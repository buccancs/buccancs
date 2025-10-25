# Shimmer Integration Gap Analysis

This note captures the delta between the updated Buccancs Shimmer path and the
behaviour of the upstream ShimmerCapturePure app. The focus is on partial,
missing, or fragile features so follow-on work can drive the integration to
parity.

---

## Snapshot of the current state

- `DefaultShimmerHardwareClient` now binds to the vendor `ShimmerService`,
  forwards connect/stream requests, and publishes state plus samples through the
  shared Kotlin flows (`hardware/shimmer-android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:25`).
- Compose surfaces the device selector and connection status but still lacks
  streaming controls, telemetry previews, or operator tooling
  (`apps/android-agent/src/main/java/com/buccancs/ui/shimmer/ShimmerScreen.kt:40`).
- The legacy ShimmerCapturePure app continues to provide the reference workflow
  with plotting, SD logging, firmware settings, and diagnostics
  (`external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:203`).

---

## Hardware abstraction gaps

- `applySettings` is still a no-op, so GSR range and sample-rate changes never
  reach the hardware (`hardware/shimmer-android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:284`).
- Streaming state currently reports a fixed sample rate and does not reflect the
  actual cadence negotiated with the device (`hardware/shimmer-android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:348`).
- Error handling is coarse: failures emit notices, but there is no automatic
  retry or multi-device coordination when several sensors are connected.

---

## Service lifecycle & permissions

- The agent eagerly binds `ShimmerService`, but it does so before runtime
  Bluetooth/location permissions are confirmed. If permissions are denied, the
  service still starts and only logs warnings.
- Unlike the upstream workflow that rebinds after permission acceptance, the
  current implementation does not automatically retry once permissions are
  granted (`external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:212`).

---

## Device discovery & selection

- The selector now shows bonded/connected devices from the hardware client, yet
  discovery still relies on bonded records—BLE scan results are not persisted or
  annotated with RSSI, firmware version, or battery metrics
  (`hardware/shimmer-android/src/main/java/com/buccancs/hardware/shimmer/DefaultShimmerHardwareClient.kt:184`).
- Connected sensors are not visually distinguished from available candidates, so
  operators cannot immediately identify the streaming device in the list.

---

## Streaming & telemetry

- Real samples flow from `ShimmerService` into `ShimmerSensorConnector`, but
  `ShimmerScreenViewModel` never collects them, leaving `ShimmerDataCard`
  empty (`apps/android-agent/src/main/java/com/buccancs/ui/components/shimmer/ShimmerDataCard.kt:9`).
- Streaming controls are still informational: there are no `start/stop`
  affordances, bandwidth indicators, or health summaries in the UI
  (`apps/android-agent/src/main/java/com/buccancs/ui/components/shimmer/ShimmerStreamingCard.kt:11`).
- Unlike ShimmerCapturePure, the agent does not expose plotting, SD logging, or
  diagnostics based on the live stream (`infra/sensor/src/main/java/com/buccancs/data/sensor/connector/shimmer/ShimmerSensorConnector.kt:361`).

---

## Configuration & persistence

- Device configuration updates live only in the settings repository; the
  hardware client ignores them and the device remains on its previous preset
  (`infra/sensor/src/main/java/com/buccancs/data/sensor/connector/shimmer/ShimmerConnectorManager.kt:266`).
- Logging, SD-card control, firmware presets, and Verisense-specific flows from
  the reference app are still absent from the agent UI
  (`external/ShimmerCapturePure/sources/com/shimmerresearch/shimmercapture/MainActivity.java:247`).

---

## Auto-launch & operator tooling

- `ShimmerAutoLaunchReceiver` still only raises the main activity; it does not
  preselect or auto-connect the just-detected MAC, so operators must reselect
  the sensor manually (`apps/android-agent/src/main/java/com/buccancs/hardware/shimmer/ShimmerAutoLaunchReceiver.kt:12`).
- There is no equivalent to the reference app’s data-sync view, plotting tabs,
  logging toggles, or firmware update prompts, leaving operators without
  diagnostics or export tooling.

---

## Recommended next steps

1. Consume the hardware client’s sample flow in the UI and surface live telemetry
   (conductance/resistance/acceleration) so operators can verify the stream.
2. Wire configuration updates (GSR range, sample rate, auto-stream) through the
   vendor SDK and surface failures with actionable notices.
3. Improve discovery by persisting BLE scan results with RSSI/firmware status
   and visually distinguishing connected vs. candidate devices.
4. Add streaming controls, plotting, logging, and data-sync affordances similar
   to ShimmerCapturePure to support field operations.
5. Harden the service lifecycle—retry binding after permissions are granted,
   detect missing vendor jars early, and show clear remediation guidance.
