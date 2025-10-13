# Buccancs Control System

## Overview

- Multi-module project that coordinates Android capture devices with a desktop orchestrator.
- Compose Desktop console drives session lifecycle, device telemetry, retention policy monitoring, transfer progress,
  and preview tiles via a shared view model.
- Command pipeline delivers start/stop, sync flashes, stimuli, and event markers with clock-aligned scheduling plus
  automatic replay for reconnecting devices.
- Android agents push RGB 4K/60 video, thermal frames, RAW stills, and Shimmer GSR samples; artifacts upload
  automatically when a session stops.
- Audio, RGB, and thermal recordings now emit timeline metadata aligned to the orchestrator clock for downstream
  correlation across platforms.
- Recording manifests capture session metadata, device inventory, artifacts, and event timelines. Artifacts are named
  consistently per stream and completed sessions enqueue uploads against the manifest for replay.
- Calibration utilities on Android capture paired RGB/thermal frames, run OpenCV stereo calibration, and cache results
  for reuse across sessions.
- Android now exposes dedicated Live Session, Session Library, and Settings screens so operators can monitor active
  telemetry, review manifests, and adjust orchestrator/retention policy without leaving the app.
- Dashboard now surfaces hardware inventory from the declarative device list, supports per-device navigation, and
  exposes a one-tap multi-device recording exercise for validation runs.
- Application service layer introduced for time sync, device commands, and recording coordination to keep ViewModels
  thin and make orchestration flows testable.
- Local control server, mDNS discovery, and security scaffolding are in place to unblock on-device command intake work.
- DeviceCommandService now merges remote orchestrator traffic with the on-device gRPC ControlServer and exposes signed
  access tokens via `issueLocalToken`.

## Modules

- `app` Android client (Jetpack Compose, Hilt, hardware connectors).
- `desktop` Compose Desktop orchestrator and gRPC server.
- `protocol` Shared protobuf and Kotlin stubs.

## Development Notes

- Android app auto-starts `DeviceOrchestratorBridge` during application start to keep the desktop in sync.
- Multi-device exercise card on the dashboard runs a synchronized start/stop across all active hardware and reports
  per-device timestamps and artifacts.
- When editing `app/src/main/assets/device-inventory.json`, remember the app copies the file into internal storage on
  first launch; subsequent updates flow through the Compose inventory card and the `SensorHardwareConfigRepository`.
- All Gradle test tasks are disabled per workflow guidance until instrumentation baselines are in place.
- Configure orchestrator target via `app` DataStore or Gradle `BuildConfig`.
- GSR artifacts are queued for upload after every recording stop (or unexpected disconnect) to keep the desktop dataset
  complete.
- Shimmer device cards now surface discovery, selection, and on-device configuration so settings stay in sync with the
  hardware.
- Hardware inventory is declaratively managed via `app/src/main/assets/device-inventory.json`; update ids, names, MAC
  addresses, or USB descriptors there to provision additional sensors.
- Thermal module navigation now exposes a dedicated Topdon console screen (scan, connect/disconnect, preview, settings).
- Live Session screen (hamburger navigation) summarises recording state, stream telemetry, recent events, uploads, and
  storage capacity using the new `SpaceMonitor` service.
- Session Library lists persisted manifests and opens per-session detail views backed by the manifest JSON.
- Settings screen wraps orchestrator target edits and retention scheduling; updates persist via
  `RetentionPreferencesRepository` and schedule WorkManager jobs with `WorkPolicy` helpers.

## Pending Focus

- Run a full multi-device soak (2x Shimmer, 2x Topdon) to validate sync accuracy, transfer retries, and retention
  alarms.
- Persist runtime inventory discoveries (Shimmer scans, Topdon attachments) back into `device-inventory.json`.
- Add preview throttling and colour controls ahead of field deployment.
- Wire ControlServer token issuance into the desktop controller/CLI and expose connection diagnostics in-app.
- HeartbeatMonitor should feed WARN/SAFE status into the recording UI and auto-stop logic.
- Expand retention scheduling UI with presets for long-form studies and integrate manifest export tooling.
