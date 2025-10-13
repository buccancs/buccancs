# Repository Guidelines

**Last Modified:** 2025-10-13 20:31 UTC  
**Modified By:** GitHub Copilot CLI

## Documentation Organization

### Directory Structure
All documentation files MUST be organized in the `docs/` directory:

- **`docs/analysis/`** - Technical debt analysis, code quality reports, gap analysis (dated files: `FILENAME_YYYY-MM-DD.md`)
- **`docs/project/`** - Project management files: BACKLOG.md, dev-diary.md, PROJECT_TODO.md, requirements
- **`docs/architecture/`** - Technical documentation, architecture diagrams, design decisions
- **`docs/guides/`** - Agent instructions, user guides, how-to documents
- **`docs/latex/`** - Thesis chapters and LaTeX source files
- **`docs/manual-tests/`** - Manual testing procedures and checklists

### File Placement Rules
- Analysis reports → `docs/analysis/`
- Project management → `docs/project/`
- Technical specs → `docs/architecture/`
- User guides → `docs/guides/`
- Thesis work → `docs/latex/`
- Core README.md stays in root for visibility

### Exception
- `README.md` remains in repository root for immediate visibility
- `.github/copilot-instructions.md` stays in .github/ directory

## Language and Spelling Standards

### British English Requirement
- **ALL documentation, comments, commit messages, and LaTeX files MUST use British English spelling and conventions**
- Common conversions:
  - American: -ize, -ization → British: -ise, -isation (e.g., "synchronize" → "synchronise", "optimization" → "optimisation")
  - American: color, behavior, center → British: colour, behaviour, centre
  - American: analyze, defense → British: analyse, defence
- Exception: Variable names, function names, and API identifiers should follow established conventions (e.g., Android API `setBackgroundColor` remains unchanged)
- Exception: Third-party library references and technical terms remain as specified

### Application
- Code comments: British English
- Documentation files (.md, .tex): British English
- Commit messages: British English
- User-facing strings: British English
- Technical documentation: British English
- Academic writing (thesis, reports): British English

## File Naming and Tracking Standards

### Documentation Files
- **All analysis, report, and documentation MD files MUST include the date in the filename** using format `FILENAME_YYYY-MM-DD.md`
  - Example: `TECHNICAL_DEBT_ANALYSIS_2025-10-13.md`, `CODE_REVIEW_2025-10-15.md`
- **Exception:** Core project files like `README.md`, `docs/project/BACKLOG.md`, `PROJECT_TODO.md` do not require dates in filename

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
- **Agents ARE ALLOWED** to read, modify, and create LaTeX (`.tex`, `.latex`) files
- **Thesis and Academic Writing:** Agents can assist with:
  - Writing and editing thesis chapters
  - Creating LaTeX documentation
  - Formatting academic papers
  - Generating bibliographies and references
  - Creating figures and tables
  - Converting content to LaTeX format
- **British English Required:** All LaTeX content must use British English spelling
- **Best Practices:**
  - Maintain consistent LaTeX formatting and style
  - Follow academic writing conventions
  - Use proper LaTeX commands and packages
  - Keep bibliography entries well-formatted
  - Test compilation after modifications

### Generated Documentation
- Create analysis documents as needed when:
  - Analyzing code quality or technical debt
  - Documenting architecture decisions
  - Creating implementation guides
  - Generating API documentation
- Always follow file naming and header standards

## File and Directory Exclusions

To maintain focus on relevant source code and documentation, agents MUST ignore the following build output, IDE configuration, and dependency-related directories:

- `build/`
- `app/build/`
- `desktop/build/`
- `protocol/build/`
- `artifacts/build/`
- `.gradle/`
- `.idea/`
- `desktop/bin/`
- `protocol/bin/`

## Project Structure and Module Organization

- `app` hosts the Android agent built with Jetpack Compose, Hilt, and repository-backed data sources for Topdon,
  Shimmer, and media capture.
- `desktop` contains the Compose Desktop orchestrator, gRPC control server, and shared view model logic that coordinates
  sessions.
- `protocol` stores protobuf schemas and generated Kotlin stubs consumed by both front ends.
- `sdk/libs` and `external/` provide vendor SDK ground truth; consult these when aligning integrations.
- Operational notes live in `READ ME.md`, `docs/project/BACKLOG.md`, and `dev-diary.md`; update them whenever workflows or
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
  `docs/project/BACKLOG.md` or issues when available.
- Include screenshots or logs for UI or orchestration changes and call out any dependencies on vendor SDK updates.
