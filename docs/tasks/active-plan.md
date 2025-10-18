# Active Task Plan: Documentation Consolidation

**Created**: 2025-10-18  
**Target completion**: 2025-10-18  
**Status**: 🟢 Completed

## Objective

Prune the sprawling documentation set and replace it with a compact collection of living guides that
cover platform
essentials, day-to-day workflows, and validation drills without losing critical knowledge.

## Acceptance Criteria

1. Core documentation reduced to a curated set (`system-overview.md`, `requirements.md`,
   `development.md`, `testing.md`,
   `index.md`, `agents.md`, and `tasks/`).
2. Key hardware, automation, and manual-test guidance folded into the surviving documents.
3. `docs/index.md` updated to reflect the streamlined layout and point at supporting assets (
   automation harness, thesis
   material, active plan).
4. Redundant directories (analysis, project diaries, deep-dive guides, visualisations, etc.) removed
   from the repository.
5. `docs/tasks/active-plan.md` rewritten to reflect the documentation work and remain the
   authoritative planning artefact.

## Task Breakdown

- [x] Audit the `docs/` tree, label redundant folders, and decide which knowledge must be preserved.
- [x] Update `system-overview.md`, `development.md`, and `testing.md` with merged content (hardware
  status, automation
  harness usage, condensed manual drills).
- [x] Refresh `requirements.md` references and rebuild `docs/index.md` as the navigation hub.
- [x] Delete legacy documentation folders/files (`analysis/`, `architecture/`, `automation/`,
  `project/`, historical
  guides, conversion notes, etc.) to complete the prune.
- [x] Capture this consolidation in `docs/tasks/active-plan.md` and outline follow-up housekeeping.

## Residual Risks & Follow-Up Ideas

| Item                       | Notes                                                                                                                             | Owner      |
|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------|------------|
| Missing niche instructions | Some deep-dive reports now live only in Git history; restore on demand if research work resumes.                                  | Team       |
| Automation deep dive       | Advanced auto-continue docs were trimmed; recreate a focused guide if the harness evolves beyond the summary in `development.md`. | Automation |
| Visual assets              | Diagrams were removed alongside `visualizations/`; consider scripting exports from design tools if diagrams are still required.   | Design     |

## Next Actions

1. Socialise the new structure with maintainers so personal notes move to private archives instead
   of `docs/`.
2. Reinstate a compact automation reference if new workflows surface.
3. Schedule a quarterly doc review to keep the curated set current.
