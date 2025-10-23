# Shimmer Integration

ShimmerCapturePure is the canonical Android app used to control Shimmer3 and
Verisense wearables. Buccancs reuses its patterns to finalise Shimmer hardware
support while simulations keep the stack usable without devices.

## 1. Architecture Overview

```
Activity / Compose wrappers
        ↓ (binder)
ShimmerService (bound service, lifecycle owner)
        ↓
ShimmerBluetoothManagerAndroid (radio orchestration)
        ↓
Transport drivers (ShimmerBluetooth, Verisense byte comms, gRPC proxy)
```

- **MainActivity** binds to `ShimmerService`, coordinates the fragment pager,
  handles permission flows, and relays callbacks via a central `Handler`.
- **ShimmerService** owns the manager instance, broadcasts state transitions,
  processes packets (`handleMsgDataPacket`), and exposes high-level commands for
  connect / stream / log workflows.
- **ShimmerBluetoothManagerAndroid** extends the shared manager to support dual
  transports (classic + BLE), pairing prompts, configuration cloning, and GSR
  range helpers (`writeGSRRange`, `configureShimmer`).

## 2. Integration Hooks for Buccancs

- **Connection control** – Callers rely on the service binder to start/stop
  streaming, initiate log capture, and request device info. Buccancs should keep
  the same guard clauses (connected vs SD logging) when mirroring APIs.
- **Configuration pipeline** – UI fragments clone device profiles, mutate sensor
  selections, and submit them through `AssembleShimmerConfig`. The service
  applies the resulting bytes asynchronously, which is safe to reuse from our
  ViewModels.
- **Remote control** – gRPC wrappers (`ShimmerServerGrpc`, `ShimmerClientGrpcStream`)
  enable desktop-side control. Reuse these clients if we keep the Android agent
  lightweight and drive experimentation from the orchestrator.

## 3. Data & Logging

- All data packets pass through `handleMsgDataPacket`, which can convert GSR to
  micro-siemens, run HR algorithms, log raw frames, and feed plotting managers.
- Logging outputs CSV/DAT artefacts with paths surfaced to the UI; Buccancs can
  subscribe to the same broadcasts when synchronising manifests.
- Broadcast intents include Bluetooth state, MAC address, and friendly names so
  observers (including our agent) can remain decoupled from radio internals.

## 4. Settings & Commands

- **Sensor definitions** – `SensorGSR`, `SensorBattVoltage`, and peers expose
  configuration handles and allowed ranges. Query `ShimmerDevice.getConfigOptionsMap()`
  when presenting settings dynamically.
- **GSR range helpers** – `writeGSRRange` / `readGSRRange` operate on the radio,
  performing validation and state checks; reuse them rather than reimplementing
  byte protocols.
- **Verisense support** – `GrpcBLERadioByteCommunication` allows desktop-hosted
  radios; the Android app communicates over TCP but exposes the same API to the
  firmware layer.

## 5. Operational Guidance

- **Permission flow** – Remove stale Bluetooth pairings before validating; the
  manager enforces strict state machines and will fail fast on invalid states.
- **Streaming pipeline** – `SignalsToPlotFragment` and `PlotManagerAndroid`
  illustrate how to keep UI selections in sync; mirror the same contract when
  routing waveforms into Compose.
- **Recovery** – Logging and streaming commands guard against conflicting states
  (e.g., SD logging while streaming). Maintain these checks when wiring into
  Buccancs to avoid firmware faults.

## 6. Next Steps

Track any Shimmer hardware milestones in `docs/planning/active-plan.md`. When
new functionality lands (waveform visualisation, diagnostics, gRPC bridging),
document the behaviour here and capture validation artefacts alongside the code
change.
