#!/usr/bin/env bash
set -euo pipefail

COMMAND_TEMPLATE='copilot-cli chat --allow-all-paths --allow-all-tools --prompt "%s"'
DEFAULT_PROMPT='continue'
WORKDIR="$(pwd)"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
LOG_DIR="$SCRIPT_DIR/logs"
QUEUE_DIR="$SCRIPT_DIR/queue"
PAUSE_FILE="$SCRIPT_DIR/pause.txt"
DELAY_SECONDS=2
MAX_ITERATIONS=0
STOP_PATTERNS=('[WAITING]' '[NEEDS_APPROVAL]')

TEMPLATE_PROMPT_PATH="$SCRIPT_DIR/templates/default-prompt.txt"
if [[ -f "$TEMPLATE_PROMPT_PATH" ]]; then
  DEFAULT_PROMPT="$(<"$TEMPLATE_PROMPT_PATH")"
  DEFAULT_PROMPT="${DEFAULT_PROMPT//$'\r'/}"
  DEFAULT_PROMPT="${DEFAULT_PROMPT%$'\n'}"
fi

usage() {
  cat <<'EOF'
auto_continue.sh - automate repeat invocations of an agent CLI.

Options:
  -c, --command-template  Command template with a single "%s" placeholder for the prompt.
                          Default: copilot-cli chat --prompt "%s"
  -d, --default-prompt    Prompt to send when the queue is empty. Default: continue
  -w, --workdir           Working directory for the agent command. Default: current directory
  -l, --log-dir           Directory for log files. Default: automation/logs (next to this script)
  -q, --queue-dir         Directory scanned for queued prompt files. Default: automation/queue
  -p, --pause-file        Path to a file that, when present, pauses execution. Default: automation/pause.txt
  -t, --delay             Delay in seconds between prompts. Default: 2
  -s, --stop-pattern      Regex that, when matched in output, stops automation. Repeat for multiple patterns.
  -m, --max-iterations    Maximum prompts to send before stopping. 0 means unlimited. Default: 0
  -h, --help              Show this help message and exit.
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    -c|--command-template)
      COMMAND_TEMPLATE="$2"
      shift 2
      ;;
    -d|--default-prompt)
      DEFAULT_PROMPT="$2"
      shift 2
      ;;
    -w|--workdir)
      WORKDIR="$2"
      shift 2
      ;;
    -l|--log-dir)
      LOG_DIR="$2"
      shift 2
      ;;
    -q|--queue-dir)
      QUEUE_DIR="$2"
      shift 2
      ;;
    -p|--pause-file)
      PAUSE_FILE="$2"
      shift 2
      ;;
    -t|--delay)
      DELAY_SECONDS="$2"
      shift 2
      ;;
    -s|--stop-pattern)
      STOP_PATTERNS+=("$2")
      shift 2
      ;;
    -m|--max-iterations)
      MAX_ITERATIONS="$2"
      shift 2
      ;;
    -h|--help)
      usage
      exit 0
      ;;
    *)
      echo "Unknown option: $1" >&2
      usage >&2
      exit 1
      ;;
  esac
done

mkdir -p "$LOG_DIR" "$QUEUE_DIR"
LOG_FILE="$LOG_DIR/session-$(date -u +%Y%m%d-%H%M%S).log"
ITERATION=0

log() {
  local message="$1"
  printf '%s :: %s\n' "$(date -u '+%Y-%m-%d %H:%M:%S')" "$message" | tee -a "$LOG_FILE" >&2
}

get_next_prompt() {
  local source='default'
  local prompt="$DEFAULT_PROMPT"

  if [[ -d "$QUEUE_DIR" ]]; then
    local next_file
    next_file=$(find "$QUEUE_DIR" -maxdepth 1 -type f -print | sort | head -n 1 || true)
    if [[ -n "${next_file:-}" ]]; then
      prompt="$(<"$next_file")"
      source="$(basename "$next_file")"
      rm -f -- "$next_file"
    fi
  fi

  printf '%s\0%s' "$prompt" "$source"
}

run_agent() {
  local prompt="$1"
  local formatted_cmd
  printf -v formatted_cmd "$COMMAND_TEMPLATE" "$prompt"

  set +e
  local output
  output=$(cd "$WORKDIR" && eval "$formatted_cmd" 2>&1)
  local exit_code=$?
  set -e

  printf '%s\n' "$output" >> "$LOG_FILE"
  printf '%s' "$output"
  return "$exit_code"
}

log "automation started"

while true; do
  if [[ -f "$PAUSE_FILE" ]]; then
    log "pause file detected; stopping"
    break
  fi

  if [[ "$MAX_ITERATIONS" -gt 0 && "$ITERATION" -ge "$MAX_ITERATIONS" ]]; then
    log "max iterations reached ($MAX_ITERATIONS); stopping"
    break
  fi

  IFS=$'\0' read -r prompt source_info < <(get_next_prompt)
  ((ITERATION++))
  log "iteration $ITERATION using $source_info"
  log "prompt => ${prompt//$'\n'/ }"

  output="$(run_agent "$prompt")"
  exit_code=$?

  if [[ "$exit_code" -ne 0 ]]; then
    log "agent exited with code $exit_code; stopping"
    break
  fi

  should_continue=true
  for pattern in "${STOP_PATTERNS[@]}"; do
    if [[ "$output" =~ $pattern ]]; then
      log "stop pattern detected ($pattern); stopping"
      should_continue=false
      break
    fi
  done

  if [[ "$should_continue" != true ]]; then
    break
  fi

  sleep "$DELAY_SECONDS"
done

log "automation finished (log: $LOG_FILE)"
