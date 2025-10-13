# Repository Guidelines

## Project Structure and Module Organization

- `app` hosts the Android agent built with Jetpack Compose, Hilt, and repository-backed data sources for Topdon,
  Shimmer, and media capture.
- `desktop` contains the Compose Desktop orchestrator, gRPC control server, and shared view model logic that coordinates
  sessions.
- `protocol` stores protobuf schemas and generated Kotlin stubs consumed by both front ends.
- `sdk/libs` and `external/` provide vendor SDK ground truth; consult these when aligning integrations.
- Operational notes live in `READ ME.md`, `BACKLOG.txt`, and `dev-diary.txt`; update them whenever workflows or
  priorities change.

## Build, Test, and Development Commands

- `.\gradlew assemble` (Windows) or `./gradlew assemble` (Unix) produces binaries for all modules without running tests.
- `.\gradlew :desktop:run` launches the orchestrator for local verification.
- `.\gradlew :app:installDebug` deploys the Android agent to a connected device.
- Tests remain disabled per workflow guidance; do not re-enable suites without explicit direction.

## Coding Style and Naming Conventions

- Follow official Kotlin and Android style guides: 4-space indentation, PascalCase for types, camelCase for members, and
  SCREAMING_SNAKE_CASE for constants.
- Compose UI files should keep one screen per file and expose a `@Composable` entry point named `<ScreenName>Screen`.
- ViewModels live in `.../presentation/...` packages and must surface state via immutable flows; repositories reside
  under `.../data/...`.
- Keep ASCII-only identifiers and comments to simplify cross-platform tooling.

## Testing Guidelines

- Automated tests are presently disabled; leave `test` tasks untouched in Gradle scripts.
- If manual verification is required, document the scenario in `dev-diary.txt` and link any captured logs or manifests.
- When tests return, prefer naming patterns like `FeatureScenarioTest` and co-locate them under `src/test` or
  `src/androidTest` mirroring the production package tree.

## Commit and Pull Request Guidelines

- Favor short, imperative commit subjects similar to `Delete SessionClock.kt`; avoid placeholder messages such as `asd`
  seen in legacy history.
- Each change should mention impacted modules and note doc updates in the body when relevant.
- Pull requests must describe the user-facing impact, list manual verification steps, and reference backlog items from
  `BACKLOG.txt` or issues when available.
- Include screenshots or logs for UI or orchestration changes and call out any dependencies on vendor SDK updates.
