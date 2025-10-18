# Repository Guidelines

## Scope & Ownership
BuccanCS captures multimodal physiological data through an Android agent, a desktop orchestrator, shared protocol layers, and automation tooling. This document replaces overlapping instructions elsewhere—treat it as the single source of truth and keep it current when workflows change.

## Workspace & Tooling Setup
- Install JDK 21 and ensure `JAVA_HOME` points to it. Verify with `java -version`.
- Install Android SDK Platform 36 and build tools 36.1; accept licences on every workstation. Record the SDK path in `local.properties` (`sdk.dir=...`) and never commit machine-specific secrets.
- Use the repository Gradle wrapper (`gradlew.bat` on Windows, `./gradlew` on macOS/Linux). Avoid running Gradle out of WSL against Windows file paths—invoke builds from native PowerShell or Command Prompt.
- Optional but recommended: Android Studio or IntelliJ IDEA for Compose previews, plus `adb`, `grpcurl`, and `python3` for sensor debugging and automation scripts.

## Project Structure
- `app/` – Jetpack Compose Android agent with manifests, assets, and tests (`src/test`, `src/androidTest`).
- `desktop/` – Desktop orchestrator UI and gRPC services.
- `protocol/`, `sdk/`, `external/` – Shared protobufs, native shims, and vendor SDK glue.
- `automation/` – Auto-continue harnesses, queues, logs, and prompt templates.
- `docs/` – Canonical reference material; maintain `docs/tasks/active-plan.md` so automation prompts can enforce planning.
- `shimmer/` – Vendor utilities and hardware helpers.
Keep assets beside their module owners and route large binaries through the approved large-file storage service.

## Build, Run, and Diagnostics
Common Gradle tasks:
- `gradlew.bat build` – full multi-module build and default checks.
- `gradlew.bat :app:assembleDebug` – Android debug APK.
- `gradlew.bat :desktop:run` – launch the orchestrator.
- `gradlew.bat :app:ktlintCheck` / `:app:lintDebug` – formatting and Android lint.
- `gradlew.bat :app:testDebugUnitTest`, `:desktop:test` – JVM unit suites.
- `gradlew.bat :app:connectedDebugAndroidTest` – instrumentation (requires hardware or simulation toggles).
Troubleshooting reminders: rerun `gradlew.bat clean build` after cache issues, accept SDK licences via `sdkmanager --licenses`, and keep Windows USB drivers current for the Topdon camera.

## Coding Standards
- Architecture: Jetpack Compose UI, MVVM view models, Hilt DI, and explicit Result-based error handling.
- Concurrency: Use structured coroutines; avoid unmanaged threads.
- State machines: Model hardware connectors and orchestration flows explicitly to guard transitional states.
- Formatting: Four-space indents, `camelCase` members, `PascalCase` types under `com.buccancs.*`. Maintain ASCII unless interacting with third-party assets that require otherwise.
- Language: Use British English in code comments, documentation, and commits. Keep comments concise and purposeful.

## Testing & Validation
- Run unit tests for impacted modules and rerun disabled suites when re-enabling infrastructure (`docs/testing.md` tracks status).
- Expectation for feature work: pass instrumentation tests or explain constraints when hardware is absent.
- Static analysis (`ktlintCheck`, `lintDebug`) must pass before submission.
- Manual drills: choose at least one relevant scenario (Calibration Wizard, Multi-Device Stress Test, Offline Upload Recovery). Follow the steps in `docs/testing.md`, capture artefacts (`performance_metrics.jsonl`, logs, session manifests), and link them in issues/PRs.

## Automation & Continuations
- PowerShell harness: `pwsh automation/auto_continue.ps1 ...`; WSL variant: `./automation/auto_continue.sh ...`.
- Ensure `automation/configure_copilot_trust.ps1` has been run so `C:\` is trusted.
- Default prompt pulls goals from `docs/tasks/active-plan.md`; keep that plan accurate (objective, acceptance criteria, risks, parallel work streams).
- Queue overrides live in `automation/queue/` as UTF-8 `.txt` files processed oldest-first. Create `automation/pause.txt` to halt automation.
- Logs write to `automation/logs/`; review transcripts for approvals, stop patterns (`[WAITING]`, `[NEEDS_APPROVAL]`), and token usage. Do not store sensitive credentials or personal data in prompt queues or logs.

## Documentation & Pull Requests
- Change summaries use imperative, sentence-case language (e.g. `Add build reports`). Group related changes; avoid catch-all submissions.
- Every PR includes: executed commands (or rationale for skips), behavioural change notes, linked issues, and updated documentation (`readme.md`, `docs/requirements.md`, module guides) when user-facing behaviour shifts.
- Before requesting review, walk through the checklist from `docs/contributing.md` (build status, tests, docs, follow-ups). Summarise manual validation and attach supporting artefacts or logs.
- Keep this file and `readme.md` aligned; when instructions evolve, update them together.
## Configuration, Data & Security
- Never commit API keys, credentials, or captured participant data. Scrub `DesktopApp/sessions/<session-id>` exports before sharing externally.
- Track long-lived hardware setup notes, time-sync metrics, and validation results in `docs/system-overview.md`, `docs/testing.md`, or the active task plan as appropriate.
- Use the large-file storage service for heavy artefacts (videos, thermal frames, sensor dumps). Confirm pointers before sharing artefacts.
- Monitor disk space for session captures and clean up stale logs in `automation/logs/` and `logs/` directories.

## Assistant Integrations
- Codex, Copilot, and Gemini sessions must read this file on every run; automation templates and repository instruction files enforce this requirement.
- The Copilot-specific instructions live in `COPILOT_INSTRUCTIONS.md` and reference this guide directly.
- Gemini configuration in `.gemini/config.yaml` allows Markdown so AGENTS.md is always visible during inline reviews.
- When introducing new automation or assistant tooling, ensure it sources its operating rules from `AGENTS.md` before executing code or documentation changes.
