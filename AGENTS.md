# Repository Guidelines

**Last Modified:** 2025-10-13 20:05 UTC  
**Modified By:** GitHub Copilot CLI

## File Naming and Tracking Standards

### Documentation Files
- **All analysis, report, and documentation MD files MUST include the date in the filename** using format `FILENAME_YYYY-MM-DD.md`
  - Example: `TECHNICAL_DEBT_ANALYSIS_2025-10-13.md`, `CODE_REVIEW_2025-10-15.md`
- **Exception:** Core project files like `README.md`, `BACKLOG.md`, `PROJECT_TODO.md` do not require dates in filename

### File Header Requirements
All generated or modified documentation files MUST include a header block at the top:

```markdown
# Document Title

**Created/Last Modified:** YYYY-MM-DD HH:MM UTC  
**Modified By:** [Agent Name] (e.g., GitHub Copilot CLI, Gemini, Claude)  
**Document Type:** [Analysis/Report/Guide/Documentation]
```

Example:
```markdown
# Technical Debt Analysis

**Last Modified:** 2025-10-13 20:03 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Analysis Report
```

### Tracking Outdated Information
- When referencing dated documents, always check if newer versions exist
- When updating existing dated documents, create a new file with current date rather than modifying old ones
- Keep previous versions for historical tracking unless explicitly instructed to delete
- Add a note in README.md or relevant index pointing to the latest version

## File Permissions and Capabilities

### Markdown Files
- **Agents ARE ALLOWED** to read, modify, and create Markdown (`.md`) files
- **Documentation Generation** is actively encouraged for:
  - Technical analysis reports (following `FILENAME_YYYY-MM-DD.md` naming)
  - Architecture documentation
  - Code quality assessments
  - Implementation guides
  - API documentation

### Source Code
- Full access to read, modify, and create Kotlin, Java, and configuration files
- Follow coding standards and architectural patterns defined above

### LaTeX Files
- Agents CAN read and reference LaTeX files
- Should not modify LaTeX files without explicit instruction
- May extract information for documentation purposes

### Generated Documentation
- Create analysis documents as needed when:
  - Analyzing code quality or technical debt
  - Documenting architecture decisions
  - Creating implementation guides
  - Generating API documentation
- Always follow file naming and header standards

## Project Structure and Module Organization

- `app` hosts the Android agent built with Jetpack Compose, Hilt, and repository-backed data sources for Topdon,
  Shimmer, and media capture.
- `desktop` contains the Compose Desktop orchestrator, gRPC control server, and shared view model logic that coordinates
  sessions.
- `protocol` stores protobuf schemas and generated Kotlin stubs consumed by both front ends.
- `sdk/libs` and `external/` provide vendor SDK ground truth; consult these when aligning integrations.
- Operational notes live in `READ ME.md`, `BACKLOG.md`, and `dev-diary.md`; update them whenever workflows or
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
- If manual verification is required, document the scenario in `dev-diary.md` and link any captured logs or manifests.
- When tests return, prefer naming patterns like `FeatureScenarioTest` and co-locate them under `src/test` or
  `src/androidTest` mirroring the production package tree.

## Commit and Pull Request Guidelines

- Favor short, imperative commit subjects similar to `Delete SessionClock.kt`; avoid placeholder messages such as `asd`
  seen in legacy history.
- Each change should mention impacted modules and note doc updates in the body when relevant.
- Pull requests must describe the user-facing impact, list manual verification steps, and reference backlog items from
  `BACKLOG.md` or issues when available.
- Include screenshots or logs for UI or orchestration changes and call out any dependencies on vendor SDK updates.
