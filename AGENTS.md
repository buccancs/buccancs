# Repository Guidelines

## Scope & Ownership

buccancs captures multimodal physiological data through an Android agent, a desktop orchestrator,
shared protocol layers, and automation tooling. This document replaces overlapping instructions
elsewhere—treat it as the single source of truth and keep it current when workflows change.

## Workspace & Tooling Setup

- Install JDK 21 and ensure `JAVA_HOME` points to it. Verify with `java -version`.
- Install Android SDK Platform 36 and build tools 36.1; accept licences on every workstation. Record
  the SDK path in `local.properties` (`sdk.dir=...`) and never commit machine-specific secrets.
- Use the repository Gradle wrapper (`gradlew.bat` on Windows, `./gradlew` on macOS/Linux). Avoid
  running Gradle out of WSL against Windows file paths—invoke builds from native PowerShell or
  Command Prompt.
- Optional but recommended: Android Studio or IntelliJ IDEA for Compose previews, plus `adb`,
  `grpcurl`, and `python3` for sensor debugging and automation scripts.

## Project Structure

- `app/` – Jetpack Compose Android agent with manifests, assets, and tests (`src/test`,
  `src/androidTest`).
- `desktop/` – Desktop orchestrator UI and gRPC services.
- `protocol/`, `sdk/`, `external/` – Shared protobufs, native shims, and vendor SDK glue.
- `automation/` – Auto-continue harnesses, queues, logs, and prompt templates.
- `docs/` – Canonical reference material; maintain `docs/tasks/active-plan.md` so automation prompts
  can enforce planning.
- `shimmer/` – Vendor utilities and hardware helpers.
- `external/` – Vendor example apps.
  Keep assets beside their module owners and route large binaries through the approved large-file
  storage service.
- ignore everything that is in .gitignore

## Build, Run, and Diagnostics

Common Gradle tasks:

- `gradlew.bat build` – full multi-module build and default checks.
- `gradlew.bat :app:assembleDebug` – Android debug APK.
- `gradlew.bat :desktop:run` – launch the orchestrator.
- `gradlew.bat :app:ktlintCheck` / `:app:lintDebug` – formatting and Android lint.
- `gradlew.bat :app:testDebugUnitTest`, `:desktop:test` – JVM unit suites.
- `gradlew.bat :app:connectedDebugAndroidTest` – instrumentation (requires hardware or simulation
  toggles).
  Troubleshooting reminders: rerun `gradlew.bat clean build` after cache issues, accept SDK licences
  via `sdkmanager --licenses`, and keep Windows USB drivers current for the Topdon camera.

## Coding Standards

- Architecture: Jetpack Compose UI, MVVM view models, Hilt DI, and explicit Result-based error
  handling.
- Concurrency: Use structured coroutines; avoid unmanaged threads.
- State machines: Model hardware connectors and orchestration flows explicitly to guard transitional
  states.
- Formatting: Four-space indents, `camelCase` members, `PascalCase` types under `com.buccancs.*`.
  Maintain ASCII unless interacting with third-party assets that require otherwise.
- Language: Use British English in code comments, documentation, and commits. Keep comments concise
  and purposeful.
- Logging: Use `Log.d()` for debugging, `Log.i()` for informational, `Log.w()` for warnings, and
  `Log.e()` for errors.
- Naming: Use `camelCase` for local variables, `PascalCase` for types, and `snake_case` for
  constants.
- Testing: Use `mockk` for mocking, `robolectric` for unit tests, and `androidx.test` for
  instrumentation tests.
- Lint: Use `ktlintCheck` and `lintDebug` to check formatting and lint.

## Assistant Integrations

- Codex, Copilot, and Gemini sessions must read this file on every run; automation templates and
  repository instruction files enforce this requirement.
- The Copilot-specific instructions live in `COPILOT_INSTRUCTIONS.md` and reference this guide
  directly.
- Gemini configuration in `.gemini/config.yaml` allows Markdown so AGENTS.md is always visible
  during inline reviews.
- When introducing new automation or assistant tooling, ensure it sources its operating rules from
  `AGENTS.md` before executing code or documentation changes.
