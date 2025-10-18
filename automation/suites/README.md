# Test Suites

Reusable automation for end-to-end and headless scenarios lives here. Each
subdirectory owns its own documentation and keeps generated artefacts local to
`automation/suites/logs/`.

- `connection/` – Backwards-compatible entry point for the classic desktop ↔
  Android connection drill. Delegates to the desktop suite.
- `desktop/` – Cross-platform scripts (Bash, PowerShell, and Batch) that boot
  an emulator, grant permissions, launch the orchestrator, and confirm a live
  session.
- `headless/` – CI-friendly harnesses that run the emulator and desktop server
  without a UI, including the infrastructure smoke test used by automation.
- `lib/` – Shared shell helpers (SDK detection, OS checks) consumed by the Bash
  suites.
- `logs/` – Scratch space for run artefacts. Everything here is ignored by git.

Add new suites by creating a sibling directory with its own README and routing
script. Prefer wrappers similar to `connection/test_desktop_android_connection.sh`
when you need to preserve older entry points.
