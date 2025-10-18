# Agent Handbook (Short Form)

**Last updated:** 2025-10-16 06:00 UTC  
**Maintainer:** Core maintainers

This file is the canonical rulebook for repository assistants. Keep it short, keep it factual, and update it whenever the real rules change—then trim elsewhere.

## 1. Core Behaviour
- Default tone: calm, concise, no hype or marketing flourishes.
- Obey the most recent maintainer/user request; otherwise fall back to this handbook.
- Use British English in prose, comments, commit messages; identifiers keep their original spelling.
- Output ASCII unless quoting upstream artefacts that demand otherwise.
- When uncertain, ask. Do not invent requirements or assume missing context.

## 2. Working With The Repository
- New first-party folders follow `lowercase-kebab`; see `docs/development/folder-naming-proposal.md` for detail.
- Third-party drops remain under `external/` using the wrapper strategy described in that proposal.
- Source lives in existing Gradle modules (`app`, `desktop`, `protocol`, `shimmer`). Record new module approvals in `docs/project/dev-diary.md` before implementation.
- Run the relevant Gradle task(s) before claiming success. If you skip checks, state why.

## 3. Documentation
- Keep documentation inside `docs/` using the established layout (analysis, architecture, guides, project, manual-tests, latex, development).
- Use short, descriptive lowercase filenames. Add a date suffix only when the content is truly point-in-time.
- Each new or edited knowledge doc carries a lightweight header:
  ```markdown
  **Last Modified:** YYYY-MM-DD HH:MM UTC
  **Modified By:** <name or agent>
  ```
- Write major decisions, risks, and follow-ups in `docs/project/dev-diary.md` (what changed, why it matters, what happens next).

## 4. Coding Standards
- Kotlin/Java follow official Android + Kotlin style; Compose screens expose `<Name>Screen` composables.
- Comment only when the intent is non-obvious and keep comments in sync with the code.
- Do not weaken error handling or delete tests without recording the justification.
- Prefer incremental, reviewable changes; delete dead code while you are in the area.

## 5. Communication & Output
- Summaries lead with outcomes, then give the essential context, then list next steps.
- Reference files as `path/to/file.ext:line` so links stay navigable.
- Do not paste large files—point to them.
- Mention the validation you ran (or why you skipped it) in every change summary.

## 6. Hand-off Checklist
- Record open threads or blockers in `docs/project/dev-diary.md`.
- List outstanding review items and required follow-up tests.
- Ensure documentation headers and links reflect the current state.

If new rules emerge, add them here and trim redundant guidance elsewhere. All other agent-facing documents should link back to this handbook instead of duplicating it.
