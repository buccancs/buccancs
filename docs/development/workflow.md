# Development Workflow

Follow this guide for day-to-day engineering tasks. Environment provisioning and
toolchain bootstrap steps are centralised in `docs/development/environment.md`.

> Read `AGENTS.md` before automating edits. That checklist remains the
> contributor source of truth; this guide adds colour where extra context helps.

## Prerequisites

- Java: JDK 21 (set `JAVA_HOME` accordingly; verify with `java -version`).

- Android SDK: Platform 36 with build tools 36.1. Install via Android Studio or
  the `sdkmanager`. Accept licences on every host to unblock Gradle sync.

- Gradle Wrapper: Use the repository-provided wrapper (`gradlew`/ `gradlew.bat`)
  rather than a global install.

- Android Studio/IntelliJ IDEA: Recommended for editing and Compose previews.

- Platform Tools: `adb`, `grpcurl`, and `python3` for device management and
  automation scripts.

- Optional: WSL for scripting; run Gradle builds natively on Windows to avoid
  WSL filesystem issues.

## Repository Setup

1. Clone the repository and install the configured large-file storage tooling if
   you intend to update SDK artefacts.

2. Prime git submodules so the external projects (for example, `external/IRCamera`)
   are present locally:

   ```powershell
   git submodule update --init --recursive
   ```

3. Provision toolchains as described in `docs/development/environment.md`.
   Windows users should run `scripts\bootstrap-toolchains.ps1`; Linux/WSL users
   can run `./scripts/bootstrap-toolchains.sh` or map existing installs via
   symlinks. The Gradle init script keeps `local.properties` in sync
   automatically.

4. Open the project in Android Studio or IntelliJ; allow the IDE to import
   Gradle settings.

5. For desktop development disable “Build on Save” to keep Gradle invocations
   explicit.

   > Windows contributors: run `git config core.longpaths true` once in this
   > repository (or globally) to avoid path-length failures when dealing with
   > external dependencies.

## Common Build Commands

```powershell
# build everything
gradlew.bat build

# clean before a fresh attempt
gradlew.bat clean build

# assemble Android debug APK
gradlew.bat :apps:android-agent:assembleDebug

# package Android instrumentation tests
gradlew.bat :apps:android-agent:compileDebugAndroidTestKotlin

# run desktop orchestrator locally
gradlew.bat :apps:desktop-orchestrator:run

# lint / formatting checks
gradlew.bat :apps:android-agent:ktlintCheck
```

On macOS/Linux use `./gradlew` with the same tasks. If Gradle fails to locate
the Android SDK, ensure `toolchains/android-sdk` exists or override the paths
via `gradle/os-paths.properties` / environment variables.

## Running the Platform

- Android agent: Deploy the debug APK to a device/emulator, grant camera,
  microphone, and storage permissions, and connect required peripherals (Topdon
  TC001 via USB-C, Shimmer3 via Bluetooth). Simulation toggles are available in
  the app settings when hardware is absent.

- Desktop orchestrator: Execute `gradlew.bat :apps:desktop-orchestrator:run`. The app discovers
  agents via mDNS, displays status panels, and exposes session controls. Use
  matching protobuf versions to avoid gRPC negotiation issues.

- Sensor tooling: Utilities under `shimmer/` cover vendor workflows; tutorials
  for the removed `tools/` helpers can be recovered from the repository history
  if you need the original automation scripts.

## Troubleshooting

- WSL Gradle failures: Run builds from Windows PowerShell or Command Prompt. WSL
  access to the Windows-hosted Android SDK is unreliable and can corrupt build
  caches.

- Android SDK licences: If builds fail with licence prompts, run
  `"%ANDROID_SDK_ROOT%/cmdline-tools/latest/bin/sdkmanager.bat" --licenses`.

- Topdon driver: Ensure the Topdon TC001 helper driver is installed so Windows
  exposes the camera to the Android device; without it the agent falls back to
  simulation.

- Shimmer pairing: Remove stale Bluetooth pairings before testing; the connector
  enforces explicit state machines to guard against invalid transitions.

- Session storage: Sessions are written under `DesktopApp/sessions/<session-id>`
  (configurable). Monitor disk space during long captures.

## Automation Harness

Use the automation harness when you need Copilot, Codex, or Gemini CLI sessions
to maintain momentum whilst you are away from the keyboard.

- PowerShell loop: `automation/auto_continue.ps1` executes the configured CLI
  command, replays the default planning prompt, consumes queued prompt files
  (oldest first), and writes transcripts to `automation/logs/`.

- WSL/Linux loop: `automation/auto_continue.sh` mirrors the PowerShell behaviour
  with Bash-friendly switches so both shells can share queues and pause files.

- Queue & pause controls: Drop UTF-8 text files into `automation/queue/` to
  override the default prompt sequence. Create `automation/pause.txt` to halt
  before the next prompt; delete it to resume.

- Command templates: Update the harness arguments if you need to reuse an
  existing session (for example, sending prompts into tmux). Always include the
  repository planning reminder so `docs/planning/active-plan.md` stays
  authoritative.

- First-run checklist: Run `automation/configure_copilot_trust.ps1` once so
  Copilot trusts `C:\`, and keep CLI switches such as `--allow-all-paths`/
  `--allow-all-tools` in the template for unattended turns.

## Writing & Style Expectations

- Write documentation, comments, and commit messages in British English
  (`synchronise`, `optimise`, `colour`, `behaviour`).

- Keep American spellings only where APIs demand it (for example,
  `setBackgroundColor`, JSON keys, protobuf fields).

- Prefer the project date formats (`YYYY-MM-DD`) and maintain concise, factual
  prose.

- Configure spell-checkers (for example, cSpell) for `en-GB` to avoid accidental
  regressions.
