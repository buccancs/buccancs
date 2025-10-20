# Testing and Validation

BuccanCS ships with automated test suites and targeted manual drills that ensure
multi-device recording remains stable. Use this guide to plan coverage, run
stress scenarios, and capture the right artefacts for debugging.

> Refer to `AGENTS.md` for the canonical testing expectations and contributor
> checklist. This > document expands on those > requirements with drill
> instructions and artefact references.

## Automated Tests

- Unit tests: `gradlew.bat:app:testDebugUnitTest` and
  `gradlew.bat:desktop:test`. These cover Result-pattern helpers, DI modules,
  repositories, and protocol utilities. The desktop suite exercises ~ 50 UI and
  data-layer tests; two mDNS integration cases remain timing-sensitive and may
  flare up on congested networks.

- Instrumentation tests: `gradlew.bat:app:connectedDebugAndroidTest` drives
  device-level validation for sensor connectors and UI state machines. Requires
  attached hardware or simulator with the relevant toggles.

- Static analysis: `gradlew.bat:app:ktlintCheck` and `./gradlew:app:lintDebug`
  enforce formatting and Android lint baselines.

- Continuous integration: Build pipelines are blocked on Android SDK Platform 36
  licence acceptance; run local smoke builds (`gradlew.bat build`) before
  opening pull requests.

## Manual Drills

### Hardware Validation Session

Use this run to exercise the full stack with physical hardware before studies or
demonstrations.

1. Prepare one Android handset (Android 12+), a paired Shimmer3 GSR unit, a
   connected Topdon TC001, and a desktop host on the same low-latency network.
   Confirm storage headroom on both endpoints.

2. Launch the desktop orchestrator, verify the Android agent appears in the
   device list, and confirm time synchronisation offsets remain below 5 ms
   (alerts trigger at 10 ms).

3. Start a short (5–10 minute) session with every modality enabled. Observe live
   previews and double-check that GSR sample counts and thermal frame cadence
   track expectations.

4. Stop the session and confirm uploads reconcile without retries. The resulting
   manifest should list GSR CSV, RGB MP4, thermal frame sequence, and audio
   artefacts alongside storage usage.

5. Record any anomalies (for example, Bluetooth reconnects, USB driver hiccups,
   upload retries) together with log paths in the sprint notes or defect
   tracker.

Capture offset statistics, session duration, device models, battery levels, and
free space in the validation log so the results can be reused in thesis
material.

### Calibration Wizard Walkthrough

Validates the stereo calibration wizard end-to-end:

1. Launch the wizard, adjust checkerboard parameters, and confirm UI toasts.

2. Capture diverse poses until progress reaches 100%.

3. Compute calibration and note RMS plus confidence labels.

4. Verify `files/datastore/calibration_metrics.preferences_pb` updates with the
   new metrics.

5. Optionally reload cached results to ensure persistence works without
   re-capturing.

### Multi-Device Stress Test

Restore the historical harness (`tools/perf/multi_device_stress.sh`) from the
repository history or recreate its flow manually:

1. Ensure each agent is visible in the desktop dashboard.

2. For automated runs, reintroduce the shell script and supply orchestrator
   host/port, session name, duration, run count, and each device serial. For
   manual runs, issue `StartSession`/ `StopSession` gRPC commands with `grpcurl`
   while a timer governs the capture window.

3. After each run, review
   `artifacts/multi-device-stress/<timestamp>/performance_summary.json` and
   compare CPU, memory, and storage trends (generate these summaries via the
   restored helper or your own Python notebook).

4. Inspect `performance_metrics.jsonl` for spikes aligned with UI updates or
   encoder activity.

5. Capture observations in your tracking issue or sprint notes.

### Offline Upload Recovery Drill

Simulates a mid-upload network loss:

1. Trigger an upload via
   `adb shell cmd jobscheduler run -f com.buccancs.agent 0` (or restore the
   historical helper `tools/tests/offline_recovery.sh` to automate the
   sequence).

2. Disable Wi-Fi and mobile data mid-transfer; confirm a FAILED record appears
   in `upload_recovery.jsonl` and the dashboard.

3. Re-enable connectivity and wait for WorkManager to mark the upload COMPLETED
   with an incremented attempt counter.

4. Ensure session manifests and artefact files reconcile correctly after
   recovery.

## Telemetry and Logs

- Performance metrics: Harvested per agent as NDJSON
  (`performance_metrics.jsonl`); analyse with a notebook or the historical
  helper script (`tools/perf/analyze_metrics.py`) restored from the repository
  history.

- Backlog telemetry: `backlog_telemetry.jsonl` records warning/critical
  transitions for upload queues.

- Frame/sensor cadence: Desktop orchestrator now emits `frame-drop:*` session
  events and operator alerts whenever preview cadence drops exceed the
  configured thresholds (RGB ≥10, thermal ≥5). Capture the orchestrator logs
  together with `session-manifest.json` so reviewers can audit the registered
  events.

- Cadence validation drill: With at least one thermal device streaming, let the
  session run for 2+ minutes at 30 fps, then deliberately stress the link (USB
  bandwidth drop or simulated CPU load). Pull
  `~/.buccancs/sessions/<id>/session-manifest.json` and attach the relevant
  frame-drop events plus the UI screenshot of the alert.

- Upload recovery: `upload_recovery.jsonl` documents state, network conditions,
  failed attempts, and retries.

- Session manifests: Under each session folder, summarising device
  participation, timestamps, and artefact URIs.

- Desktop logs: See the orchestrator console output for gRPC interactions and
  command traces.

## Troubleshooting Failures

- If time synchronisation drifts beyond 10 ms, verify the desktop time service
  is running and network latency is stable.

- Missing artefacts usually indicate storage exhaustion or WorkManager
  throttling; inspect backlog telemetry and device free space.

- Thermal preview hiccups often stem from Topdon driver issues—reinstall the
  driver or swap USB ports.

- For persistent upload failures, clear WorkManager jobs
  (`adb shell cmd jobscheduler cancel-all com.buccancs.agent`) and restart the
  agent before rerunning the drill.
