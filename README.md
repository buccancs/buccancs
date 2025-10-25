# BuccanCS Platform

BuccanCS orchestrates synchronised, multi-modal capture across Android agents,
a desktop control surface, and shared protocol layers. It pairs thermal imaging
(Topdon TC001), Shimmer3 galvanic skin response sensors, RGB video, audio, and
operator context to produce audit-ready research sessions.

## Quick Start

```bash
# clone and enter the repo
git clone <repo-url> && cd buccancs

# provision developer toolchains (Android SDK, JDK, Gradle caches)
./scripts/bootstrap-toolchains.sh         # Linux / WSL
pwsh -File scripts/bootstrap-toolchains.ps1  # Windows PowerShell

# assemble the Android agent and desktop orchestrator
./gradlew :apps:android-agent:assembleDebug :apps:desktop-orchestrator:run
```

Key prerequisites (full detail in `docs/development/environment.md`):

- Temurin JDK 21
- Android SDK Platform 36 + Build Tools 36.1
- Gradle wrapper bundled with the repo
- Topdon TC001 drivers (Windows) when testing physical hardware

## Repository Layout

- `apps/android-agent/` – Jetpack Compose Android agent (UI, orchestrator client, DI).
- `apps/desktop-orchestrator/` – Compose Desktop orchestrator and telemetry console.
- `shared/foundation`, `shared/domain`, `shared/data`, `shared/protocol` – Platform-neutral
  utilities, business logic, and protobuf/gRPC contracts.
- `infra/` – Android infrastructure modules (`calibration`, `events`, `orchestration`,
  `sensor`, `storage`, `transfer`) housing repositories, connectors, and background workers.
- `hardware/shimmer-android/`, `hardware/topdon-android/`, `hardware/topdon-runtime/`,
  `hardware/topdon-sdk/` – Device-specific connectors and vendor bridge layers.
- `simulations/thermal/` – Simulation pipelines used in lieu of physical
  sensors.
- `legacy/external/` – Vendor SDK drops and archival samples (read-only).
- `scripts/`, `toolchains/` – Bootstrap and local toolchain helpers
  (git-ignored payloads).

## Documentation Map

The curated documentation set lives under `docs/`:

- `docs/README.md` – Top-level index for all guides.
- `docs/overview.md` – Architecture snapshot, mission, and requirement status.
- `docs/development/` – Environment provisioning and day-to-day workflows.
- `docs/hardware/` – Topdon TC001 and Shimmer integration notes.
- `docs/operations/` – Testing, telemetry UX, and hardware-facing drills.
- `docs/planning/` – Active plan tracker and archived programme notes.

Refer to `AGENTS.md` before automating edits or dispatching unattended CLI
sessions; it remains the ground-truth contributor playbook.

## Contributing

1. Read `AGENTS.md` to align with the workspace guardrails and automation rules.
2. Update `docs/planning/active-plan.md` when picking up or closing work items.
3. Run the relevant Gradle test targets (see `docs/operations/testing.md`)
   before opening a pull request.
4. Capture validation evidence (logs, manifests, dumps) alongside code changes,
   especially when touching hardware integrations.

## Support

Raise issues in the project tracker with:

- OS and device information (Android build, desktop OS, hardware variants).
- Exact Gradle task or workflow used.
- Reproduction steps and observed vs expected behaviour.
- Linked session manifests/logs where applicable.

Security-sensitive reports should be directed to the maintainers privately.
