# Development Guide

Follow this guide to provision a workstation, build the modules, and exercise the platform during day-to-day
development.

## Prerequisites

- **Java:** JDK 21 (set `JAVA_HOME` accordingly; verify with `java -version`).
- **Android SDK:** Platform 36 with build tools 36.1. Install via Android Studio or the `sdkmanager`. Accept licences on
  every host to unblock Gradle sync.
- **Gradle Wrapper:** Use the repository-provided wrapper (`gradlew`/`gradlew.bat`) rather than a global install.
- **Android Studio / IntelliJ IDEA:** Recommended for editing and Compose previews.
- **Platform Tools:** `adb`, `grpcurl`, and `python3` for device management and automation scripts.
- **Optional:** WSL for Git and scripting; run Gradle builds natively on Windows to avoid WSL filesystem issues.

## Repository Setup

1. Clone the repository and install Git LFS if you intend to update SDK artefacts.
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
  recovered from Git history if you need the original automation scripts.

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

- Earlier revisions included helper scripts such as `tools/perf/multi_device_stress.sh` and
  `tools/tests/offline_recovery.sh`. Retrieve them from Git history or recreate equivalent runners using the flows
  described in `docs/testing.md`.
- Copilot auto-continuation tooling (formerly under `tools/automation/`) can be restored from history if you need to
  bootstrap scripted prompt loops.
- `docs/testing.md` remains the source of truth for manual and automated validation steps.
