# Automation Harness

This harness keeps Copilot CLI, Codex CLI, Gemini CLI, or any compatible agent moving while you are away from the keyboard. It provides two thin wrappers—one for native PowerShell on Windows, one for WSL/Linux shells—that invoke the agent, feed prompts, log responses, and stop when human input is required.

> Overview, safety requirements, and approval policies for automation usage are centralised in `AGENTS.md`. Use this
> document for operational detail and command references.

## Features

- Replays a default continuation prompt or consumes queued prompt files.
- Watches for pause files or stop patterns (e.g. `[WAITING]`) before handing control back to you.
- Records the full transcript for every run under `automation/logs/`, including prompts, responses, session identifiers, and token usage (when the CLI exposes it).
- Supports optional iteration limits, per-run delays, and custom command templates.

## Directory Layout

```
automation/
├── auto_continue.ps1           # PowerShell harness (Windows)
├── auto_continue.sh            # Bash harness (WSL/Linux)
├── configure_copilot_trust.ps1 # Adds C:\ to Copilot CLI trusted folders
├── templates/                  # Sample prompt snippets to copy into the queue
├── queue/                      # Drop .txt prompts to override the default sequence
├── logs/                       # Session transcripts (created on demand)
└── pause.txt                   # When present, automation halts before the next prompt
```

Ensure these paths remain under `automation/` so both harnesses can find them.

## Pre-Flight Setup

- **Copilot CLI trust zone:** Run `pwsh automation/configure_copilot_trust.ps1` once so `C:\` and its subdirectories are trusted.
- **Repository instructions:** Every agent session must read and follow `AGENTS.md`. The automation harness assumes those rules are in force for Codex, Copilot, and Gemini.
- **Default Copilot permissions:** Command templates include `--allow-all-paths` and `--allow-all-tools`, letting Copilot read/write the workspace and launch helper binaries.
- **Default prompt template:** The harness loads `automation/templates/default-prompt.txt`, which first enforces planning in `docs/tasks/active-plan.md` (objective, acceptance criteria, risks, parallelisable task lists) and then reminds the agent to respect Jetpack Compose + Material 3 and clean architecture conventions, fix build errors/warnings, and add/update tests.
- **Codex CLI full approval:** When you run Codex CLI, include its full-approval switch (for example, `codex run --approval-policy full …`) in the command template.

## PowerShell Harness (`auto_continue.ps1`)

```powershell
pwsh automation/auto_continue.ps1 `
  -AgentExecutable "copilot-cli" `
  -AgentArgumentsTemplate @(
      "chat",
      "--allow-all-paths",
      "--allow-all-tools",
      "--show-usage",
      "--prompt",
      "{Prompt}"
  ) `
  -WorkingDirectory (Get-Location)
```

- Logs are written to `automation/logs/session-<timestamp>.log`.
- Drop UTF-8 `.txt` files into `automation/queue/` to override the default prompt; files are consumed (and removed) in chronological order.
- Create `automation/pause.txt` to halt before the next turn; delete the file and restart the harness to continue.
- To reuse an existing Copilot session (inside PowerShell, tmux, etc.), change `-AgentExecutable` / `-AgentArgumentsTemplate` to send the prompt into that session (for example, using `tmux send-keys`).

## WSL / Linux Harness (`auto_continue.sh`)

```bash
chmod +x automation/auto_continue.sh
./automation/auto_continue.sh \
  --command-template 'copilot-cli chat --allow-all-paths --allow-all-tools --show-usage --prompt "%s"' \
  --default-prompt 'continue' \
  --workdir "$PWD"
```

- Mirrors the PowerShell behaviour—logs go to `automation/logs/`, queue files live in `automation/queue/`, `pause.txt` stops the loop.
- Point `--command-template` at `tmux send-keys`, `screen`, or another wrapper if you want to control an already running CLI instance.

## Prompt Queue Format

- Prompts are simple UTF-8 text files. The oldest file in the queue directory is executed next.
- When a queued prompt finishes, the file is deleted; if the queue is empty, the default planning prompt from `templates/default-prompt.txt` is used.
- Copy templates into the queue to reuse them, e.g.:
  - PowerShell: `Copy-Item automation/templates/default-prompt.txt automation/queue/001-default.txt`
  - Bash: `cp automation/templates/default-prompt.txt automation/queue/001-default.txt`
- Number filenames (`001`, `002`, …) if you need a specific execution order.

## Stop Conditions

Automation halts when:

- `automation/pause.txt` exists.
- The agent exits with a non-zero status.
- Output matches any stop pattern (defaults: `[WAITING]`, `[NEEDS_APPROVAL]`).
- A configured `MaxIterations` limit is reached.

The harness prints the path to the latest session log before shutting down so you can pick up the conversation manually.

## Tips

- Keep `docs/tasks/active-plan.md` current—the default prompt expects it to describe the active goal, acceptance criteria, risks, and task lists.
- Enqueue `automation/templates/codex-review.txt` to capture feedback, request a Codex review, and append the response to the session log.
- Review the logs in `automation/logs/` to see the entire conversation, token usage, and session IDs.
