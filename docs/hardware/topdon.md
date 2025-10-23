# Topdon TC001 Integration

This note consolidates everything we rely on from the upstream Topdon IRCamera
application, the current state of our integration, and the commands required to
keep the external project healthy.

## 1. Integration Status (2025-10-23)

| Phase | Outcome | Highlights |
|-------|---------|------------|
| Phase 1 – Live Preview | ✅ Complete | TC001 vendor/product IDs registered; `DefaultTopdonThermalClient` streams raw 16‑bit frames via `USB_STREAM_DIRECT`; preview renders at ~12–15 FPS with accurate temperatures. |
| Phase 2 – Hardware Controls | ✅ Complete | Palette, emissivity, distance, gain, and shutter hooks exposed in the client, persisted via `DataStoreTopdonSettingsRepository`, and surfaced in Compose UI. |
| Phase 3 – Candidates | 🔜 Planned | HDR calibration, hardware-triggered capture, synchronised RGB/IR recording pipeline, regression harness replay, palette diagnostics, refined UX tooling. |

Reconnect logic reapplies stored settings, and simulation pathways remain intact
for lab work without hardware.

## 2. Upstream Architecture Primer

Topdon’s official app (`external/IRCamera`) is a modular Android project built
with Gradle 7.1.3 / Kotlin 1.7.20 targeting SDK 34 (min 24). Key modules:

- **Core libraries** – `libir` (native/JNI thermal SDK wrapper), `libapp`,
  `libcom`, `libui`, `libmenu`, `libhik`.
- **Feature components** – `component:thermal-ir`, `thermal-lite`, `thermal07`,
  `thermal04`, `thermal-hik`, `pseudo`, `edit3d`, `house`, `transfer`, `user`.
- **Local repositories** – `LocalRepo:libac020`, `libcommon`, `libirutils`.
- **Third-party add-ons** – `RangeSeekBar`, `ai-upscale`.

USB handling centres on `IRUVCTC` with vendor/product IDs for Realtek (0x0BDA),
Infisense (0x3474), and generic UVC classes. Native payloads include
`libusbdualsdk`, `libopen3d`, `libopencv_java4`, and supporting OpenGL/SR image
components.

## 3. Gradle Integration Cheat Sheet

The repo tracks IRCamera as a submodule (`external/IRCamera`). Ensure it is
initialised:

```bash
git submodule update --init --recursive
```

Gradle exposes convenience tasks (Java 21 max):

```bash
# Build only IRCamera (debug)
./gradlew buildIRCamera

# Build all external projects (including IRCamera)
./gradlew externalBuild

# Full build (internal + external)
./gradlew build

# Inspect available external tasks
./gradlew tasks --group="external build"
```

Outputs land in
`external/IRCamera/app/build/outputs/apk/debug/app-debug.apk`.

If you need to override the Java runtime, set `externalJavaHome` in
`gradle.properties` or export `EXTERNAL_JAVA_HOME`. Windows contributors should
enable `git config core.longpaths true` before cloning submodules.

## 4. Bridging Into Buccancs

- Runtime client: `DefaultTopdonThermalClient` drives IRCMD calls (`setPalette`,
  `setEmissivity`, `setDistance`, `setGainMode`, `setShutterMode`) and maps
  device events into domain models.
- Settings persistence: `DataStoreTopdonSettingsRepository` stores palette,
  emissivity, gain mode, and distance so reconnects reapply state automatically.
- UI: `TopdonViewModel` orchestrates preview state and surfaces device controls;
  Compose screens expose quick toggles plus advanced sheets.
- Permissions: `DeviceScannerService` and `TopdonDevicePermissionDialog` manage
  USB access prompts and telemetry events.

## 5. Operational Gotchas

- **Drivers** – Install the TC001 USB helper driver on Windows hosts; without
  it the Android agent falls back to simulation.
- **Data flow mode** – We rely on `CommonParams.DataFlowMode.USB_STREAM_DIRECT`
  to bypass H.264 encoding; switching modes requires matching pipeline updates.
- **Performance** – Preview targets ~12–15 FPS; long sessions should monitor the
  buffer health indicators (`docs/operations/telemetry.md`) for degradation.
- **Testing** – Include thermal hardware in the manual validation drill
  (`docs/operations/testing.md`) whenever modifying the connector or UI.

## 6. Next Steps Tracking

Capture planned improvements under the hardware workstream in
`docs/planning/active-plan.md`. When a phase lands, update the status table
above and mention validation artefacts (logs, manifests, screenshots) in the
related change request.
