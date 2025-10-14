# UI Automation Phase 2 Kick-off

**Created/Last Modified:** 2025-10-14 10:59 UTC  
**Modified By:** Codex (GPT-5)  
**Document Type:** Project Management

## Objectives
- Select tooling that can exercise Tier 0/Tier 1 flows with minimal flakiness on Android and desktop.
- Define repeatable environment seeding so UI automation runs deterministically in CI and on developer workstations.
- Establish documentation conventions to keep scenarios, fixtures, and troubleshooting notes synchronised.

## Framework Selection
### Android Agent
- **Primary option:** Jetpack Compose UI Tests with `createAndroidComposeRule` and Navigation testing APIs.
    - Integrates cleanly with existing Compose architecture; enables direct state injection via Hilt test modules.
    - Supports semantics-based selectors, reducing brittleness compared with view IDs.
    - Works on emulators and Firebase Test Lab (if re-enabled later).
- **Supplementary tooling:** Fake orchestrator layer built with Kotlin coroutines test scope to simulate session lifecycle events.
- **Open tasks:** Create Hilt test component for repositories, expose fake `MainViewModel` dependencies (retention, session service).

### Desktop Orchestrator
- **Preferred approach:** JetBrains Compose UI Test (`org.jetbrains.compose.ui.test.junit4.createComposeRule`) running on JVM.
    - Provides semantics tree access comparable to Android Compose tests.
    - Supports headless execution when `-Djava.awt.headless=true` is set, suitable for CI containers.
- **Alternative fallback:** Robot pattern with Skiko screenshot diffing if semantics prove insufficient.
- **Open tasks:** Factor `DesktopApp` into test-friendly entry points, supply fake `AppViewModel` with controllable flows, ensure tests can run without native device SDKs.

## Environment Seeding Strategy
- **Data Fakes:** Provide deterministic session/device payloads by stubbing repositories (e.g., `SessionLibraryViewModel`, `AppViewModel`) with in-memory flows seeded from JSON fixtures under `tools/testdata/ui/`.
- **Orchestrator Simulation:** Introduce lightweight fake gRPC server harness or dispatcher that can emit time-sync, backlog, and device events on demand for both platforms.
- **Device Inventory:** Supply static `device-inventory.json` fixture ensuring Topdon/ Shimmer entries resolve without hardware.
- **Persistence:** Route DataStore/SharedPreferences calls to in-memory versions during tests to avoid filesystem variability.
- **Build Configuration:** Create dedicated Gradle tasks (`uiAndroidDebug`, `uiDesktop`) that package dependencies, set system properties, and disable long-running background jobs.

## Documentation Conventions
- Maintain scenario playbooks in `docs/guides/ui-automation/` with dated files mirroring test classes.
- Record fixture contracts (input JSON shape, expected state) beside the assets in `tools/testdata/ui/README.md`.
- Log flakes and tooling issues in `docs/project/dev-diary.md` with resolution links for future triage.
- Update `docs/analysis/UI_AUTOMATION_PHASE1_2025-10-14.md` whenever Tier assignments shift; append addendum rather than editing history.

## Immediate Actions
- Draft proof-of-concept tests: one Android smoke covering Dashboard → Settings path, one desktop smoke verifying Control Panel button enablement.
- Prepare Gradle module wiring (enable test variants, add Compose test dependencies for desktop).
- Author automation conventions guide skeleton in `docs/guides/` once proof-of-concept succeeds.

## Risks and Mitigations
- **Flaky hardware dependencies:** Mitigate by isolating vendor SDKs behind fake repositories and constraining tests to simulated data.
- **CI resource limits:** Evaluate headless emulator footprint; consider snapshot-based boot or running critical smokes nightly until infrastructure scales.
- **Tooling maturity:** Compose Desktop testing APIs evolving; keep fallback Robot harness plan and track upstream issues.
