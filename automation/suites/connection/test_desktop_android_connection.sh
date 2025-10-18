#!/usr/bin/env bash
#
# Backwards-compatible wrapper for the cross-platform desktop↔Android connection test.
# Maintains the original script name while delegating to the actively maintained implementation.

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
TARGET_SCRIPT="${SCRIPT_DIR}/../desktop/test_desktop_android_cross_platform.sh"

if [[ ! -x "${TARGET_SCRIPT}" ]]; then
    echo "error: ${TARGET_SCRIPT} is missing or not executable" >&2
    exit 1
fi

exec "${TARGET_SCRIPT}" "$@"
