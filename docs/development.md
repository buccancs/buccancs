# Development Guide

Follow this guide to provision a workstation, build the modules, and exercise the platform during day-to-day
development.

> Canonical contributor guidance now lives in `AGENTS.md`. Use this document for extended setup notes and troubleshooting
> detail that complement the ground-truth checklist.

## Prerequisites

- **Java:** JDK 21 (set `JAVA_HOME` accordingly; verify with `java -version`).
- **Android SDK:** Platform 36 with build tools 36.1. Install via Android Studio or the `sdkmanager`. Accept licences on
  every host to unblock Gradle sync.
- **Gradle Wrapper:** Use the repository-provided wrapper (`gradlew`/`gradlew.bat`) rather than a global install.
- **Android Studio / IntelliJ IDEA:** Recommended for editing and Compose previews.
- **Platform Tools:** `adb`, `grpcurl`, and `python3` for device management and automation scripts.
- **Optional:** WSL for scripting; run Gradle builds natively on Windows to avoid WSL filesystem issues.

## Repository Setup

1. Clone the repository and install the configured large-file storage tooling if you intend to update SDK artefacts.
2. Copy or create `local.properties` with `sdk.dir` pointing at your Android SDK location (already tracked for the
   canonical Windows path).
3. Open the project in Android Studio or IntelliJ; allow the IDE to import Gradle settings.
4. For desktop development disable “Build on Save” to keep Gradle invocations explicit.

## Common Build Commands

```powershell
# build everything
gradlew.bat build

# clean before a fresh attempt
gradlew.bat clean build

# assemble Android debug APK
gradlew.bat :app:assembleDebug

# package Android instrumentation tests
gradlew.bat :app:compileDebugAndroidTestKotlin

# run desktop orchestrator locally
gradlew.bat :desktop:run

# lint / formatting checks
gradlew.bat :app:ktlintCheck
```

On macOS/Linux use `./gradlew` with the same tasks. If Gradle fails to locate the Android SDK, revisit `local.properties`
or the `ANDROID_HOME` environment variable.

## Running the Platform

- **Android agent:** Deploy the debug APK to a device/emulator, grant camera, microphone, and storage permissions, and
  connect required peripherals (Topdon TC001 via USB-C, Shimmer3 via Bluetooth). Simulation toggles are available in the
  app settings when hardware is absent.
- **Desktop orchestrator:** Execute `gradlew.bat :desktop:run`. The app discovers agents via mDNS, displays status
  panels, and exposes session controls. Use matching protobuf versions to avoid gRPC negotiation issues.
- **Sensor tooling:** Utilities under `shimmer/` cover vendor workflows; tutorials for the removed `tools/` helpers can be
  recovered from the repository history if you need the original automation scripts.

## Troubleshooting

- **WSL Gradle failures:** Run builds from Windows PowerShell or Command Prompt. WSL access to the Windows-hosted Android
  SDK is unreliable and can corrupt build caches.
- **Android SDK licences:** If builds fail with licence prompts, run
  `"%ANDROID_SDK_ROOT%/cmdline-tools/latest/bin/sdkmanager.bat" --licenses`.
- **Topdon driver:** Ensure the Topdon TC001 helper driver is installed so Windows exposes the camera to the Android
  device; without it the agent falls back to simulation.
- **Shimmer pairing:** Remove stale Bluetooth pairings before testing; the connector enforces explicit state machines to
  guard against invalid transitions.
- **Session storage:** Sessions are written under `DesktopApp/sessions/<session-id>` (configurable). Monitor disk space
  during long captures.

## Automation Aids

- `automation/auto_continue.ps1` – PowerShell harness that replays continuation prompts, consumes queued instructions,
  and logs agent output. See `docs/automation.md` for full setup.
- `automation/auto_continue.sh` – Bash/WSL variant for the same workflow; can share queue and pause files with the
  PowerShell harness when both point at the same Windows path.
- `automation/configure_copilot_trust.ps1` – One-off helper that writes `C:\` into Copilot CLI’s trusted folders list so
  the assistant can operate anywhere under the drive without repeated prompts.
- `automation/templates/` – Ready-made prompt snippets (best-practice continuation, Codex review hand-off) you can copy
  into the queue; see `docs/automation.md` for usage patterns.
- `automation/logs/` – Session transcripts with prompts, responses, session IDs, and token usage (when `--show-usage`
  is enabled).
- `docs/tasks/active-plan.md` – The working plan for the current task; ensure it exists and stays current so automated
  sessions always know the goal, acceptance criteria, risks, and parallelisable task lists.
- `docs/testing.md` remains the source of truth for manual and automated validation steps, including how to recreate the
  historical stress and recovery drills.
