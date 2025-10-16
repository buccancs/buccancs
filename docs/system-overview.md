# System Overview

BuccanCS delivers synchronised, multi-modal physiological data capture for research into contactless GSR prediction. It
combines Android agents, a desktop orchestrator, and shared tooling to coordinate sensors, persist artefacts, and
recover from transient failures.

## Purpose

- Collect galvanic skin response (GSR), thermal infrared frames, RGB video, audio, and contextual metadata in lockstep.
- Provide laboratory-grade session management, manifesting, and telemetry so researchers can audit every capture.
- Serve as the engineering backbone for a thesis exploring contactless GSR estimation.

## Component Architecture

- **Android agent (`app/`)** – Jetpack Compose client using MVVM, Hilt, and structured concurrency to control Shimmer3
  GSR sensors, Topdon TC001 thermal cameras, the on-device RGB camera, and audio capture. Includes simulation toggles
  for bench testing.
- **Desktop orchestrator (`desktop/`)** – Kotlin/Java application that discovers agents over mDNS, issues gRPC
  commands, manages recording sessions, aggregates uploads, and exposes monitoring panels.
- **Shared protocol (`protocol/`)** – Protobuf definitions and generated artefacts that keep desktop and Android
  modules aligned.
- **Tooling (`shimmer/`, `sdk/`, `external/`, historical `tools/`)** – Integration harnesses, vendor artefacts, and
  automation scripts (stress, recovery, auto-continue). Restore the removed `tools/` helpers from history when needed.

## Data Flow

1. Orchestrator opens a recording session, provisioning a timestamped directory and manifest on desktop storage.
2. Android agents receive start commands, synchronise clocks against the desktop time service, and begin capturing the
   enabled modalities in parallel.
3. GSR samples stream over gRPC to the desktop in near real time while high-volume artefacts (RGB, thermal, audio) are
   buffered locally on the device.
4. When the session stops, agents finalise local manifests and queue uploads; WorkManager drains artefacts with retry
   and recovery logging.
5. Desktop ingestion reconciles artefacts against the session manifest, producing a coherent dataset per run.

## Functional Capabilities

- Multi-device coordination with single-button start/stop and per-agent health reporting.
- NTP-style time synchronisation with round-trip sampling that targets <10 ms offset between devices.
- Session manifests capturing start/end timestamps, device inventory, storage usage, and bookmark events.
- Parallel capture of high-rate GSR CSV, RGB MP4, thermal frame sequences, audio, and optional stimuli markers.
- Upload backlog monitoring, retry with exponential back-off, and structured recovery logs (`upload_recovery.jsonl`).
- Performance telemetry covering CPU, memory, thermal load, and storage headroom per agent.
- Automation support, including Copilot auto-continuation sessions and command-line stress scripts.

## Outstanding Work

- End-to-end validation with physical hardware across multiple Android agents and the desktop orchestrator.
- Completion of TOPDON feature parity for migrated Compose UI (palette presets, gallery review polish).
- Re-enabling and stabilising the suspended automated test suites once infrastructure blockers are resolved.
- Accepting Android SDK Platform 36 licences on build hosts to unblock CI verification.

## Related Research Assets

Academic write-ups live under `docs/latex/` and remain the definitive source for thesis chapters, literature surveys,
and requirement traceability.

For requirement-level detail and delivery status, see `docs/requirements.md`.
