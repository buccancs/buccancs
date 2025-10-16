#!/bin/bash
# Simple continuation script for GitHub Copilot CLI

PROJECT_ROOT="/mnt/c/dev/buccancs"
LOG_DIR="$PROJECT_ROOT/logs"
SESSION_FILE="$LOG_DIR/last_session.txt"

mkdir -p "$LOG_DIR"

# Default prompt
PROMPT="continue with the next steps. also fix any remaining issues or errors. re-check your work and make sure to use best kotlin jetpack compose material 3 hilt clean architecture best practices. implement any missing tests, remove outdated comments, lines or files, and update documentation"

# Allow custom prompt as argument
if [ -n "$1" ]; then
    PROMPT="$1"
fi

# Save session info
echo "$(date +%s)" > "$SESSION_FILE"

# Launch copilot
cd "$PROJECT_ROOT"
gh copilot suggest -t shell "$PROMPT"
