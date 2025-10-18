#!/usr/bin/env bash
#
# Wrapper to keep the legacy script name while routing to the cross-platform headless test.

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
TARGET_SCRIPT="${SCRIPT_DIR}/test_headless_cross_platform.sh"

if [[ ! -x "${TARGET_SCRIPT}" ]]; then
    echo "error: ${TARGET_SCRIPT} is missing or not executable" >&2
    exit 1
fi

exec "${TARGET_SCRIPT}" "$@"
