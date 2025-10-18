# Folder Naming Proposal

**Author:** Codex agent  
**Last updated:** 2025-10-16

## Objectives
- Make directory names predictable and easy to traverse.
- Prevent merge noise caused by accidental case changes (especially on case-insensitive file systems).
- Preserve compatibility with build tooling and upstream third-party drop-ins.

## Current Landscape
- **Clean:** First-party Android/desktop modules (`app`, `desktop`, `protocol`, `shimmer`) already follow lower-case conventions that align with Kotlin package paths.
- **Mixed:** `external/` contains vendor drops (Shimmer, Topdon) whose directory structures are mostly CamelCase/TitleCase; renaming them wholesale risks diverging from upstream releases and breaking Gradle imports.
- **Android generated paths:** `src/androidTest`, `values-zh-rCN`, etc., are dictated by the Android Gradle Plugin and should remain unchanged.
- **Third-party artefacts:** Some nested folders like `ExternalTools` or `AppDatabase` are produced by vendored tooling; touching them complicates updates.

## Proposed Convention

| Scope | Style | Notes |
| --- | --- | --- |
| First-party folders (root and within `app`, `desktop`, `protocol`, `docs`, `tools`, etc.) | `lowercase-kebab` | Already applied to `docs/`; extend to any newly created module directories. |
| Android Gradle standard folders (`src/main`, `src/androidTest`, `res/values-zh-rCN`, …) | Preserve AGP defaults | Required by build system; renaming is not supported. |
| Kotlin/Java package folders | Preserve lower-case dotted packages | Managed by the compiler; renaming would break package declarations. |
| Third-party vendor drops in `external/` | Introduce thin wrapper directories using `lowercase-kebab` while leaving upstream trees untouched | e.g. move `external/ShimmerAndroidAPI` to `external/shimmer-android-api/src/**` where `src/**` mirrors the upstream layout. |
| Archived artefacts | Store in `archive/` directories using `lowercase-kebab` names describing the snapshot (e.g. `archive/2025-10-topdon-migration`). |

### Wrapper pattern for third-party drops
Instead of renaming vendor folders directly:
1. Create a wrapper directory with a clean name (e.g. `external/shimmer-android-sdk`).
2. Place the upstream archive inside a `vendor/` subfolder unchanged (`external/shimmer-android-sdk/vendor/ShimmerAndroidAPI/...`).
3. Adjust Gradle/JNI references to point at the wrapper.  
This keeps our repo consistent while easing future upstream updates.

## Implementation Plan

### Phase 1 – New Contributions
- Enforce the convention in PR templates and contribution docs.
- Add a lint check (Gradle task or script) that fails when new first-party folders contain uppercase characters or spaces.

### Phase 2 – Existing First-Party Folders
- Audit non-vendor root folders (e.g. `tools/`) and rename them to the standard form.
- Update build scripts and imports accordingly; run instrumentation/unit tests to validate.

### Phase 3 – Third-Party Wrappers
- For each vendor tree in `external/`, create the wrapper structure described above.
- Update Gradle `settings.gradle.kts` and module `build.gradle.kts` files to use the new wrapper path.
- Document update instructions so future vendor drops are unpacked into `vendor/` rather than committed at root level.

### Phase 4 – Tooling & Automation
- Add a CI check that scans directory names using the existing file-audit script to prevent regressions.
- Provide a developer script (`tools/scripts/check-folder-names.ps1`) that contributors can run locally.

## Open Questions
- Should we relocate extremely large vendor trees to dedicated submodules to avoid churn? (Worth evaluating once wrappers are in place.)
- Do we want a repository-level attributes file to normalise case sensitivity for specific folders on macOS/Windows?

## Next Steps
1. Circulate this proposal among maintainers for agreement on the wrapper approach.
2. Once approved, schedule Phase 2+3 work, starting with low-risk modules (`tools/`, `external/example_topdon_sdk/`).
3. Update `docs/agents.md` (after consolidation) to include the agreed folder policy.
