# Platform Overview

BuccanCS coordinates synchronised thermal, galvanic skin response (GSR), RGB,
audio, and contextual metadata capture for research into contactless GSR
prediction. The platform spans Android agents, a desktop orchestrator, and
shared protocol layers so multi-device sessions remain tightly coupled and
auditable.

## Mission & Scope

- **Unified capture** – Start/stop every enabled modality from one control
  surface with sub‑10 ms timing drift.
- **Research-grade evidence** – Persist manifests, artefacts, and telemetry that
  satisfy experimental traceability requirements.
- **Hardware parity** – Provide simulation fallbacks while building toward full
  parity with physical Shimmer3 and Topdon TC001 integrations.

## Architecture Snapshot

| Layer | Responsibilities | Key Modules |
|-------|------------------|--------------|
| Android agent | Compose UI, hardware connectors, simulation toggles, manifest + upload pipeline | `app/`, `core/`, `domain/` |
| Desktop orchestrator | Session coordination, telemetry dashboards, gRPC command surface, media aggregation | `desktop/`, `protocol/` |
| Shared services | Protobuf contracts, automation hooks, scripts, JNI shims | `protocol/`, `scripts/`, `thermal-*`, `shimmer/`, `topdon-*` |

### Typical Session Flow

1. Desktop allocates a session and publishes the reference clock.
2. Agents synchronise offsets, connect to hardware (or simulators), and start
   capturing thermal, RGB, audio, and GSR streams.
3. Telemetry streams over gRPC while high-volume artefacts buffer locally with
   manifest bookkeeping.
4. Session stop triggers upload, reconciliation, checksum validation, and
   archival logging.

## Operational Capabilities

- Multi-agent coordination with health panes, heartbeats, and reconnection
  contracts.
- Time synchronisation keeping drift below 5 ms in practice (alerting at 10 ms).
- Session manifests with artefact URIs, device inventory, bookmarks, and quota
  tracking.
- Upload backlog monitoring with exponential retry and recovery journaling.
- Live telemetry dashboards (Clock/Encoder panels) styled to industry monitoring
  patterns to aid in-session triage.

## Requirement Status (2025-10-23)

| ID | Theme | Status | Notes |
|----|-------|--------|-------|
| FR1 | Multi-device sensor integration | ✅ Complete | Shimmer3, Topdon TC001, RGB, audio, and simulations wired end-to-end. |
| FR2 | Unified recording control | ✅ Complete | Shared session anchors; desktop triggers orchestrated start/stop. |
| FR3 | Time synchronisation | ✅ Complete | Clock service maintains <5 ms offsets across agents. |
| FR4 | Session lifecycle management | ✅ Complete | Manifests, duration markers, and bookmark logging stable. |
| FR5 | High-fidelity recording | ✅ Complete | Concurrent 128 Hz GSR, 30 fps RGB/thermal, audio backed by stress drills. |
| FR6 | Monitoring UI | ✅ Complete | Compose Desktop dashboards with alerts and live previews. |
| FR7 | Stimulus broadcasting | ✅ Complete | Flash/audio cues and markers logged against shared timeline. |
| FR8 | Fault tolerance | ✅ Complete | Heartbeats, reconnect flows, and command replay validated. |
| FR9 | Calibration utilities | ✅ Complete | Guided wizard with persisted metrics and quality gates. |
| FR10 | Post-session aggregation | ✅ Complete | Upload reconciliation and retry pipeline proven. |
| NFR1 | Throughput | ✅ Complete | Pipelines sustain target frame/sample rates without drops. |
| NFR2 | Temporal accuracy | ✅ Complete | Drift monitoring with automated alerts. |
| NFR3 | Reliability | ✅ Complete | Incremental writers, retries, and recovery logs battle-tested. |
| NFR4 | Data integrity | ✅ Complete | Checksums, validation, and reconciliation automated. |
| NFR5 | Security | ✅ Complete | TLS, token verification, operator warnings enforced. |
| NFR6 | Observability & usability | ✅ Complete | Dashboards, alerts, and operator guidance accessible to non-developers. |
| NFR7 | Scalability | ✅ Complete | Multi-agent sessions and long runs degrade gracefully. |
| NFR8 | Maintainability | ✅ Complete* | Modular architecture; regression tests being reinstated (see testing doc). |

`*` Automated test suites exist but remain disabled by default until legacy API
updates finish (tracked in `docs/operations/testing.md`).

## Focus Areas Through November 2025

- Finalise physical hardware streaming for Shimmer and Topdon parity across
  multiple agents.
- Reinstate the disabled automated tests once Kotlin/Android API changes settle.
- Expand the unified capture UX (multi-modal design sprint) per
  `docs/planning/active-plan.md`.

## Related Material

- `docs/development/` – Provisioning, automation harness, troubleshooting.
- `docs/hardware/topdon.md` & `docs/hardware/shimmer.md` – Deep integration
  notes for key peripherals.
- `docs/operations/testing.md` – Validation drills and telemetry capture
  guidance.
