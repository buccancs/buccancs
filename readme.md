# BuccanCS: Multi-Modal Physiological Data Collection Platform

BuccanCS is a research-grade platform for synchronised acquisition of galvanic
skin response (GSR), thermal imaging, RGB video, and audio. It coordinates
Android agents and a desktop orchestrator to capture time-aligned datasets for
contactless GSR research.

The repository contains the Android application, desktop orchestration tools,
shared protocol definitions, and automation scripts that support laboratory and
field data collection. The software now delivers the complete Chapter 3
requirements set confirmed in
`docs/requirements_analysis/REQUIREMENTS_GAP_ANALYSIS.md`.

For contributor workflows, environment setup, testing expectations, and
automation usage, follow the consolidated guidance in `AGENTS.md`, which
supersedes older documents.

## Quick Start

3. Use the Gradle wrapper to build modules from Windows PowerShell or Command
   Prompt:

```powershell
gradlew.bat build
```

4. To build the Android agent only:

```powershell
gradlew.bat :app:assembleDebug
```

5. To launch the desktop orchestrator during development:

```powershell
gradlew.bat :desktop:run
```

Additional setup details, `local.properties` guidance, and troubleshooting notes
live in `docs/development.md`.

## Documentation

- `docs/readme.md` – Documentation index and navigation

- `docs/status/status-2025-10-17.md` – Latest consolidated
  build/testing/UI/manifest snapshot with archive pointers

- `docs/system-overview.md` – Architecture, data flow, and platform capabilities

- `docs/automation.md` – Auto-continue harness for unattended CLI sessions

- `docs/requirements.md` – Functional and non-functional targets with delivery
  status

- `docs/development.md` – Environment setup, build, and run workflows

- `docs/testing.md` – Automated and manual validation procedures

- `docs/contributing.md` – Coding standards, documentation conventions, and
  collaboration guidelines

- `docs/tasks/` – Active task plans and archived work breakdowns

- `docs/latex/` – Thesis chapters and academic material (unchanged)

## Core Capabilities

- Multi-device management with synchronised start/stop control for Android
  agents and connected sensors

- Sub-10 ms time synchronisation across devices using an NTP-style service with
  round-trip sampling

- Session management that writes manifest metadata, telemetry, and artefacts per
  recording

- Parallel capture and later reconciliation of GSR CSV streams, RGB video,
  thermal frames, and audio

- Background upload pipeline with retry and recovery logging for interrupted
  transfers

- Automation scripts for stress testing, backlog monitoring, and Copilot-driven
  continuations

## Hardware and Sensors

Shimmer3 GSR+ connector delivering 128 Hz galvanic skin response and analogue
channels with calibration checks

Topdon TC001 thermal camera integration with live preview, configurable
palettes, and raw frame capture

Android RGB camera and microphone support via CameraX/Camera2 with bookmarks and
session annotations

Simulation toggles for each sensor mode to exercise orchestration flows without
physical hardware

## Project Status

- Android agent UI and orchestration flows: complete and aligned with the
  published requirements

- Desktop services and previews: production ready with synchronised sync cues
  and health monitoring

- Automated tests: full JVM suite restored under `app/src/test`; instrumentation
  still requires a provisioned SDK/device

- Thermal, GSR, RGB, audio integration: feature-complete with simulation
  fallbacks and calibration tooling

- Builds: Gradle wrapper ready; ensure `local.properties` points at Android SDK
  Platform 36 before running CI locally

Please raise issues or craft pull requests against the simplified documentation
structure introduced in October

## Shared Run Configurations and Logical Bundles

This repository now includes shared JetBrains run configurations under the .run/ directory and new Gradle bundle tasks
to harmonize common build/run entry points.

Quick usage (Windows PowerShell or Command Prompt):

- Build everything: gradlew.bat build
- Build curated external projects only: gradlew.bat externalBuild
- Build all internal bundles and curated externals: gradlew.bat bundleAll
- Build Android app fast path: gradlew.bat :app:assembleDebug
- Run desktop app: gradlew.bat :desktop:run

Logical bundle tasks:

- bundleCore – Builds core/shared modules (protocol, domain, core, storage, common-ui, sdk if present)
- bundleDesktop – Builds the desktop application and its prerequisites
- bundleAndroid – Assembles the Android app (assembleDebug)
- bundleThermal – Builds all modules starting with thermal-
- bundleTopdon – Builds all modules starting with topdon-
- bundleAll – Runs all of the above plus curated external builds

Shared IDE run configurations (.run/):

- Build - All.run.xml – Gradle build
- External - Build Curated.run.xml – Gradle externalBuild
- Bundles - All.run.xml – Gradle bundleAll
- Bundle - Core.run.xml – Gradle bundleCore
- Bundle - Desktop.run.xml – Gradle bundleDesktop
- Bundle - Android.run.xml – Gradle bundleAndroid
- Bundle - Thermal.run.xml – Gradle bundleThermal
- Bundle - Topdon.run.xml – Gradle bundleTopdon
- Desktop Run.run.xml – Gradle :desktop:run
- Desktop Headless Server.run.xml – Gradle :desktop:runHeadlessServer (available when the task exists)

Notes:

- Tests are disabled by default; enable with -Ptests.enabled=true.
- The Android SDK is auto-detected; see local.properties and settings.gradle.kts for detection logic.
