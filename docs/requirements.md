# Platform Requirements

This summary captures the functional and non-functional requirements that guided the original BuccanCS implementation.
Each entry lists the intent, essential acceptance notes, and the present delivery state so the team can track what still
needs hardening before field studies.

## Functional Requirements

- **FR1 – Multi-device sensor integration**  
  The platform must manage Shimmer3 GSR sensors, the Topdon TC001 thermal camera, on-device RGB camera, microphone, and
  simulation fallbacks.  
  *Status:* Connectors and simulators are in place across Android; automatic hardware discovery from the desktop is still
  incomplete.

- **FR2 – Synchronised recording control**  
  One start/stop action triggers every enabled modality to record against a shared session clock.  
  *Status:* Shared time anchors and orchestration hooks exist; real-world multi-handset runs remain to be executed.

- **FR3 – Time synchronisation service**  
  Desktop provides the reference clock; Android devices maintain offsets within ≈5 ms.  
  *Status:* Android client computes offsets and applies them; the desktop service needs production validation.

- **FR4 – Session lifecycle management**  
  Sessions require unique manifests with start/end markers, duration, and bookmark metadata.  
  *Status:* Manifest writer and storage scaffolding are complete; concurrent desktop operators still need verification.

- **FR5 – High-fidelity data recording**  
  Simultaneous GSR (128 Hz), RGB video (1080p/30 FPS), thermal frames, and audio must be stored without loss.  
  *Status:* Connectors stream to disk with structured artefact metadata; long-duration, multi-camera stress tests are
  pending.

- **FR6 – Monitoring user interface**  
  Desktop UI must list devices, expose session controls, raise alerts, and render live RGB/thermal previews.  
  *Status:* Compose Desktop console surfaces devices and controls; preview transport remains stubbed for final wiring.

- **FR7 – Stimulus broadcasting and markers**  
  Desktop issues sync flashes, auditory cues, and stimulus markers that every agent logs against the shared timeline.  
  *Status:* Command pipeline records events; authoring tools and guaranteed simultaneous actuation are incomplete.

- **FR8 – Fault tolerance during sessions**  
  Individual device failures should not abort the session; reconnecting devices resynchronise and honour missed commands.  
  *Status:* Heartbeats, reconnect logic, and manifest logging exist; automated recovery scenarios still require testing.

- **FR9 – Calibration utilities**  
  Provide guided RGB/thermal calibration with quality thresholds and persisted results.  
  *Status:* Android wizard runs OpenCV calibration and stores metrics; automated quality gates remain manual.

- **FR10 – Post-session data aggregation**  
  Android uploads every artefact, desktop reconciles them against the manifest with retries for failures.  
  *Status:* Upload pipeline and telemetry are available; desktop ingestion checks and retry validation are outstanding.

## Non-Functional Requirements

- **NFR1 – Real-time throughput**  
  Must handle concurrent 128 Hz sensors and 30 FPS video plus previews without drops.  
  *Status:* Pipelines use buffered coroutines and WorkManager; hardware soak tests are yet to prove sustained load.

- **NFR2 – Temporal accuracy**  
  Maintain ≤10 ms drift (target 5 ms) across devices throughout a session.  
  *Status:* Offset tracking exists on Android; reference timing on desktop still needs measurement tooling.

- **NFR3 – Reliability**  
  Recording survives network interruptions; data already written stays valid.  
  *Status:* Incremental file writers and retries are present; broader fault-injection coverage is missing.

- **NFR4 – Data integrity**  
  Validate sensor ranges, checksum artefacts, and reconcile manifests automatically.  
  *Status:* Checksums and manifests exist; automated reconciliation and range validation are TODO.

- **NFR5 – Security**  
  Employ authenticated, encrypted channels and warn when protections lapse.  
  *Status:* TLS hooks and token checks are partially integrated; end-to-end hardening remains.

- **NFR6 – Observability & usability**  
  Deliver metrics dashboards, operational warnings, and operator guidance suitable for non-developers.  
  *Status:* Telemetry streams exist; polished dashboards and clearer operator cues are in progress.

- **NFR7 – Scalability**  
  Support multiple agents and long sessions with graceful degradation rather than failure.  
  *Status:* Artefact rolling and retention policies exist; multi-device soak runs are outstanding.

- **NFR8 – Maintainability**  
  Keep modules loosely coupled with configuration-driven behaviour and solid test coverage.  
  *Status:* Modular architecture with DI is established; automated tests and PC-side modularity need expansion.

## Using This Document

- Update the relevant requirement entry whenever behaviour, acceptance tests, or status changes.
- Link new validation evidence (logs, test artefacts) from `docs/testing.md` so future reviewers can trace compliance.
- If new requirements emerge, add them here and update `docs/readme.md` to reflect the expansion.


