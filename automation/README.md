# Automation Hub

Automation tasks in this repository now live under a single, tidy hub. The
directory is intentionally segmented so each workflow has a clear home and
generated artefacts stay out of version control.

## Layout

- `harness/` – Auto-continue wrappers for Codex, Copilot, and Gemini. Includes
  the prompt templates, a ready-to-use queue, and log stubs described in
  `docs/automation.md`.
- `suites/` – Cross-platform test suites for the Android agent and desktop
  orchestrator, organised by scenario (connection, headless, desktop runners).
- `tools/` – Supporting utilities such as the gRPC command sender used to poke
  running orchestrator sessions.
- `ui/` – The UI analyser pipeline, sample reports, and quick-start guidance for
  turning Android UI dumps into machine-readable issue summaries.

## Quick Start

- Run the Codex/Copilot automation loop:
  ```bash
  chmod +x automation/harness/auto_continue.sh
  ./automation/harness/auto_continue.sh --workdir "$PWD"
  ```
  Use the PowerShell variant (`automation/harness/auto_continue.ps1`) on
  Windows terminals, and drop prompt overrides into `automation/harness/queue/`.

- Verify the connection workflow from a fresh checkout:
  ```bash
  ./automation/suites/connection/test_desktop_android_connection.sh
  ```
  All suite scripts emit their logs under `automation/suites/logs/` so the
  workspace root remains clean.

- Generate an accessibility report for a captured UI dump:
  ```bash
  python3 automation/ui/ui_analyzer.py window_dump.xml \
      --screenshot screen.png \
      --output automation/ui/samples/latest
  ```
  Open the resulting HTML file inside `automation/ui/samples/latest/` to review
  the detected issues, and hand the generated prompt to your preferred assistant
  for remediation advice.

## Adding More Automation

- Keep reusable helpers inside `automation/suites/lib/`, and prefer sourcing
  them rather than pasting duplicates into each script.
- Write suite-specific notes alongside the scripts that use them (see
  `automation/suites/connection/README.md` for an example).
- Direct any generated assets into a subdirectory of `automation/harness/logs/`,
  `automation/suites/logs/`, or a dedicated path under `automation/ui/` so the
  repository stays free from transient files.
