# Test Automation Status

**Created/Last Modified:** 2025-10-14 22:05 UTC  
**Modified By:** Codex (GPT-5)  
**Document Type:** Project Management

## Current State (Tier 0 & Tier 1)
- **Android instrumentation:** `:app:assembleAndroidTest` passes. New Compose smokes cover dashboard navigation, Live Session diagnostics, Settings actions, Session Library list, and Session Detail screen using dedicated `testTag`s.
- **Desktop UI JVM tests:** `:desktop:uiDesktop -Ptests.enabled=true` passes with smokes verifying retention and transfer panels (populated and empty states).
- **Connected device run:** `connectedDebugAndroidTest` currently fails on the emulator because tagged nodes are absent in runtime UI:
  - Dashboard never renders `device-open-console-topdon-001` (device state not fed through `inventory` pipeline).
  - Session Detail test cannot locate “Session total json”.
  - Live Session test cannot display “Important marker” backlog entry.
  - Settings test cannot surface “Settings saved.” message.

## Immediate Follow-Ups
1. **Align test seeds with production semantics:**
   - Seed Topdon device via `inventory`/`inventory.devices` rather than `state.devices`.
   - Provide manifest/artifact entries that match UI strings (“Session total json”) and backlog events that produce “Important marker”.
   - Pre-populate `SettingsUiState.message` or trigger the save flow so the success chip appears.
2. **Verify scrolling behaviour:** ensure `LazyColumn` containers expose test tags and that tests `performScrollToNode` before assertions.
3. **Rerun** `./gradlew connectedDebugAndroidTest` until all Tier‑1 smokes pass on an emulator.

## Near-Term Expansion (Tier 2)
- **Android:** add smokes for calibration panel, Topdon preview state, and session exercise cards once deterministic fakes exist.
- **Desktop:** extend coverage to preview stream list, alert panel, and archive cards using fabricated payloads.

## Tracking & Reporting
- Maintain this document (latest version only) for ongoing testing status.
- Publish emulator run results in `docs/project/dev-diary.md` when connected tests succeed or fail with non-trivial causes.
- Reference this status note in future automation plans before expanding Tier 2 scope.
