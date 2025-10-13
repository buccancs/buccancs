# Multi-Device Stress Procedure

This playbook exercises NFR1 by coordinating simultaneous recordings across multiple Android agents while harvesting
performance telemetry.

## Prerequisites

- Desktop orchestrator running and reachable from the host executing the script.
- Latest Android build deployed to each target device (physical or emulator).
- `grpcurl`, `adb`, and `python3` installed on the host.
- Ensure agents are paired with the orchestrator and visible in the dashboard before starting.

## Script Overview

`tools/perf/multi_device_stress.sh` drives the following flow:

1. Issues `StartSession` over gRPC for a uniquely named stress session.
2. Sleeps for the configured duration while agents record in parallel.
3. Sends `StopSession` and finalises the run.
4. Pulls `performance_metrics.jsonl` from each device and feeds it into `tools/perf/analyze_metrics.py` for
   CPU/memory/storage summaries, writing per-session `performance_summary.json` snapshots alongside raw data.

Artifacts are stored under `artifacts/multi-device-stress/<timestamp>/`.

## Usage

```bash
./tools/perf/multi_device_stress.sh \
  --host 192.168.1.10 \
  --port 50051 \
  --session burn_in_october \
  --duration 600 \
  --runs 2 \
  --device emulator-5554 \
  --device 0123456789ABCDEF
```

Options:

- `--host/--port` – orchestrator address (defaults to `localhost:50051`).
- `--session` – base session ID used for each run (run index appended automatically).
- `--duration` – recording duration for each run in seconds.
- `--runs` – number of consecutive start/stop cycles.
- `--device` – ADB serial for each agent (omit to auto-discover attached devices).
- `--grpcurl-flag` – pass-through flags for TLS or auth, e.g., `--grpcurl-flag -import-path=protocol/src/main/proto`.

## Verifications

- After each run, inspect the generated `*_summary.txt` and `performance_summary.json` for CPU > 85%, memory growth, or
  rapidly decreasing free storage.
- Review `performance_metrics.jsonl` for spikes that align with UI updates or encoder activity.
- Cross-reference the Live Session backlog card to confirm uploads are keeping pace; critical backlog states will
  appear in the UI and be recorded in `backlog_telemetry.jsonl` if WorkManager cannot drain during the soak.

## Follow-Up

- Attach summaries to `dev-diary.txt` with run timestamps and notable observations.
- If critical backlog warnings occurred, revisit retention settings or network provisioning before the next test window.
- Track historic summaries in `docs/perf/` for longitudinal analysis.
