# UI Automation Phase 1 Assessment

**Created/Last Modified:** 2025-10-14 10:55 UTC  
**Modified By:** Codex (GPT-5)  
**Document Type:** Analysis Report

## Objective and Scope
- Capture the current Android and desktop Compose surfaces to inform Phase 2 automation design.
- Highlight navigation paths, high-value controls, and existing pain points referenced in `docs/project/BACKLOG.md` and `docs/project/dev-diary.md`.
- Provide an initial prioritisation, success criteria, and KPIs so stakeholders can align on what “Phase 1 complete” means.

## Android Agent Flows
| Flow | Entry Point | Key Interactions | Business Impact | Regression Notes |
| --- | --- | --- | --- | --- |
| Dashboard overview (`MainRoute`) | App launch → `AppNavHost` startDestination `dashboard` | Session ID entry, simulation toggle, orchestrator host/port/TLS inputs, apply/clear config, device connect/disconnect buttons, calibration panel actions, navigation chips to Live Session, Library, Settings | **High** – gate for all operator tasks; misbehaviour blocks data capture and hardware orchestration | Frequent platform churn noted in `docs/project/dev-diary.md` (2025-10-12 entries) increases risk of slips; complex state (`MainViewModel`) flagged as refactor priority in `docs/project/BACKLOG.md` |
| Live session monitoring (`LiveSessionRoute`) | Dashboard → “Live Session” button | Bookmark button, stimulus trigger, diagnostic cards (clock, encoder, device events, uploads, backlog, recoveries, storage) | **High** – primary recording feedback loop; regressions can hide capture failures | Upload/backlog telemetry still evolving; need smoke tests to ensure cards render and actions fire without crashes |
| Session library (`SessionLibraryRoute`) | Dashboard → “Library” | Refresh, empty/error/loading branches, session row selection | **Medium** – operators audit recordings; regressions mostly degrade productivity | Recent gaps around manifest loading captured in dev diary; priorities lower than live capture but still require navigation smoke |
| Session detail (`SessionDetailRoute`) | Library → session card | Retry on error, manifest summary/device/artifact/event/bookmark sections | **Medium** – necessary for audit/export tasks, but less time-critical | Manifest parsing sensitive to data changes; ensure UI does not crash on missing sections |
| Settings (`SettingsRoute`) | Dashboard → “Settings” | Orchestrator host/port/TLS toggles, retention policy inputs, apply buttons, message chip | **High** – misconfiguration blocks orchestration; retention applies safety limits | Backlog emphasises retention automation; ensure fields persist and buttons unlock only when valid |
| Topdon device console (`TopdonRoute`) | Dashboard device card → `topdon/{deviceId}` | Connect/disconnect/refresh, preview start/stop/toggle, auto-connect switch, palette/supersampling menus, FPS slider, error chip | **High** for thermal capture field exercises | Recent additions (dev diary 2025-10-12) imply volatile code paths; automation should guard navigation, preview toggles, and error handling |

### Android Navigation Summary
- `dashboard` (start) ⇄ `liveSession`, `sessionLibrary`, `settings`, `topdon/{deviceId}`, with nested `sessionDetail/{sessionId}` from library.
- Back navigation relies on Compose `navigateUp()`; tests must verify stack correctness when chaining (Dashboard → Library → Detail → back).

## Desktop Orchestrator Flows
| Flow | Surface | Key Interactions | Business Impact | Regression Notes |
| --- | --- | --- | --- | --- |
| Control panel | `DesktopApp` > `ControlPanel` | Start/stop session, operator/subject IDs, sync signal and delay inputs, event marker fields, stimulus trigger, subject erasure | **Critical** – commands hardware orchestration for entire system | Backlog lists end-to-end soaks and sync accuracy as critical; automation should ensure primary buttons remain enabled/disabled correctly |
| Session summary | `SessionSummaryCard` | Metrics display, erase session | **High** – operators verify recording health and erase post-run data | Erase restricted to completed sessions; guard for regressions in enablement logic |
| Device roster | `DeviceSection` | Status badges, telemetry (battery, latency, offsets) | **High** – indicates fleet health; regressions can hide disconnections | Device list fed by network updates; UI smoke should ensure render without crashes when list empty, connected, or mixed |
| Retention dashboard | `RetentionSection` | Storage breakdown, quota alerts | **Medium** – compliance requirement but read-only | Ensure large datasets do not break layout; no active controls |
| Transfer monitor | `TransferSection` (progress + backlog) | Transfer rows with statuses, retry buttons if present | **High** – data pipeline visibility | Transfer reliability is active backlog item; verify UI surfaces states and buttons stay responsive |
| Event timeline | `EventTimelineSection` | Chronological list with metadata | **Medium** – aids audits; mostly read-only | Ensure long lists remain scrollable and format consistent |
| Preview console | `PreviewSection` | Image previews, frame timing badges | **High** – quick validation of device feeds | Needs guard rails for missing/invalid frames; ensure placeholder renders |
| Archive & alerts | `ArchiveSection`, `AlertsSection` | Historical sessions list, alert chips | **Medium** – reduces manual log checks | Validate empty/non-empty states so automation can catch regressions in data bindings |

### Desktop Navigation Summary
- Single-window experience driven by a unified `DesktopApp` composable. Actions mainly mutate view-model state instead of switching routes; automation should focus on panel visibility, button state transitions, and data refresh behaviours.

## Prioritisation Approach
- **Tier 0 (Critical smoke)**: Android Dashboard navigation, Start/Stop session loop (Android + Desktop), Orchestrator configuration (Settings + Control Panel), Topdon connect/preview happy path. These flows directly impact the ability to capture data and align with critical backlog items.
- **Tier 1 (Support coverage)**: Session Library/Detail navigation, Retention/Transfer panels, Live Session diagnostics (bookmarks, backlog cards), Desktop preview rendering. These protect operator feedback loops and post-run validation.
- **Tier 2 (Nice-to-have)**: Calibration controls, detailed manifest subsections, archive browsing, alert rendering. Automate once Tier 0/1 prove stable and telemetry identifies frequent use.

Prioritisation weights consider:
- Business impact (can operators still capture compliant data?).
- Recent churn (per `docs/project/dev-diary.md` 2025-10-12 to 2025-10-13 entries).
- Complexity and historical bugs (large `MainViewModel`, newly integrated Topdon flow, pending desktop transfer completion noted in `docs/project/BACKLOG.md`).

## Success Criteria (Phase 1)
- Stakeholders sign off on this catalogue and priority matrix, locking Tier 0 scope before Phase 2 framework work begins.
- Each Tier 0 flow has an agreed acceptance checklist describing start state, navigation path, primary assertions, and recovery expectations.
- Reference datasets or mock fixtures identified for deterministic automation (e.g., seeded session data, device stubs).
- Open risks and assumptions logged with owners (e.g., Desktop automation harness decision) ahead of Phase 2.

## Proposed KPIs
- **Coverage**: 100% of Tier 0 flows mapped with acceptance criteria; 80% of Tier 1 flows outlined with draft scenarios.
- **Traceability**: Every planned automated scenario references a backlog item or documented risk (links maintained in follow-up tracking sheet).
- **Readiness**: Blocking unknowns reduced to ≤2 items (e.g., tool selection, fixture availability) before Phase 2 kicks off.
- **Change Control**: Introduce a lightweight change log so future UI modifications update this catalogue within two working days, preventing drift.
- **Stakeholder Alignment**: Achieve explicit acknowledgement from Android, desktop, and QA leads recorded in `docs/project/dev-diary.md` once priorities are accepted.

## Next Steps
- Validate Tier 0/Tier 1 lists with product and engineering leads.
- Begin Phase 2 tasks: select automation frameworks, define environment seeding strategy, and document conventions in `docs/guides/`.
