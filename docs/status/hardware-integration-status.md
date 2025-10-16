# Hardware Integration Status – 2025-10-16

## Completed
- Android agent now recognises Realtek-branded TC001 (vendor `0x0BDA`, product `0x5830`) alongside the original Topdon IDs.
- USB serial access guarded behind `UsbManager.hasPermission`, eliminating the pre-permission crash.
- Vendor SDK wrapped with an unexported broadcast receiver context so Android 13+ no longer blocks the registration.
- Preview initialisation now retries multiple dimension combinations and degrades to warnings instead of aborting.
- Device screen correctly reports `Connecting` once permission is granted (no longer fixed on `Disconnected`).
- Runtime build and deployment verified via `./gradlew.bat :app:assembleDebug` and `:app:installDebug`.

## Outstanding
- **Topdon preview handshake** – `UVCCamera.setUSBPreviewSize` still returns non-success codes (`-51`, `1`), leaving the UI at `Connecting`. Need to query supported formats via the SDK and adjust our selection logic.
- **Thermal device never transitions to `Connected`** – even after multiple permission prompts, the status card does not flip; verify the USB callback chain and ensure `handleDeviceConnected` is invoked on success.
- **Streaming/preview unverified** – no evidence of preview frames or thermal stream events reaching the UI; once the connection stabilises, exercise preview and recording paths.
- **Orchestrator bridge unavailable** – repeated gRPC connection failures (`ECONNREFUSED` to 10.0.2.2:50051) flood the logs; confirm orchestrator availability or disable the auto-connect bridge for manual hardware tests.
- **Shimmer integration remains stubbed** – logs show `ShimmerHardwareStub`; real BLE plumbing still required for production testing.
- **Permission UX** – the user must approve camera, microphone, and nearby devices each session; consider persisting rationale or guiding through the sequence.

## Next Steps
1. Inspect `UVCCamera` supported sizes on-device; adjust `configureCamera` to negotiate an accepted width/height pair and confirm preview frames arrive.
2. Once preview succeeds, verify the connector sets `TopdonStatus.Connected` and that the Devices screen reports `Connected` without manual intervention.
3. Run through `docs/manual-tests/hardware-validation-plan.md` with the updated build, capturing logs and artefacts for the thermal camera and Shimmer (when real hardware support is ready).
4. Resolve the orchestrator gRPC failures or temporarily disable auto-bridge logging to keep hardware validation noise-free.

