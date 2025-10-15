# UI Automation Review Request

**Created/Last Modified:** 2025-10-14 10:57 UTC  
**Modified By:** Codex (GPT-5)  
**Document Type:** Project Management

## Purpose

- Secure explicit confirmation from Android, desktop, and QA leads on the Tier 0 and Tier 1 UI automation scope defined
  in `docs/analysis/UI_AUTOMATION_PHASE1_2025-10-14.md`.
- Capture any scope adjustments, blockers, or dependencies before Phase 2 begins.

## Requested Actions

- **Android Lead:** Validate the Android flow list, note missing screens or state transitions, and flag required
  fixtures or test doubles.
- **Desktop Lead:** Confirm desktop panels and interactions, highlight telemetry or state prerequisites, and identify
  risky areas needing early coverage.
- **QA Lead:** Approve prioritisation tiers, ensure acceptance criteria meet verification needs, and raise gaps in
  reporting or tooling expectations.

## Tier Overview (For Confirmation)

- **Tier 0 (Critical smoke):**
    - Android Dashboard navigation and orchestration configuration (`MainRoute`, `SettingsRoute`).
    - Start/Stop session loop across Android and desktop (`LiveSessionRoute`, `DesktopApp` control panel).
    - Topdon connect/preview happy path.
- **Tier 1 (Support coverage):**
    - Session Library and Detail navigation.
    - Live Session diagnostics rendering (bookmarks, backlog, telemetry cards).
    - Desktop retention, transfer, preview, and alert panels.

## Discussion Prompts

- Are there upcoming feature flags or refactors that could shift Tier 0/Tier 1 boundaries?
- What data fixtures or simulators do we need available to exercise the target flows deterministically?
- Which recent regressions or incidents should shape the acceptance criteria for smoke coverage?
- What reporting cadence and ownership model does QA expect once automated suites run in CI?

## Next Steps

- Collect responses by 2025-10-17 (EOD UTC) so Phase 2 framework work can begin on schedule.
- Record acknowledgements and any scope changes in `docs/project/dev-diary.md`.
- Amend `docs/analysis/UI_AUTOMATION_PHASE1_2025-10-14.md` if scope adjustments are agreed.
