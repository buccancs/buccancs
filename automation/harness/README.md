# Auto-Continue Harness

Codex, Copilot, and Gemini automation lives here. See `docs/automation.md` for
full instructions; this directory simply packages the scripts and templates in
one tidy place.

- `auto_continue.sh`/`auto_continue.ps1` – Primary wrappers for Linux/WSL and
  Windows shells.

- `configure_copilot_trust.ps1` – Adds the repository to Copilot CLI's trusted
  paths list.

- `templates/` – Default prompts consumed by the harness.

- `queue/` – Drop custom prompt files here when you want to override the default
  planner.

- `logs/` – Scratch space for per-run transcripts; ignored by git.
