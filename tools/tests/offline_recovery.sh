#!/usr/bin/env bash
set -euo pipefail

DEVICE="${1:-}"

if [[ -z "${DEVICE}" ]]; then
  echo "Usage: $0 <adb-serial>" >&2
  exit 1
fi

run_adb() {
  adb -s "${DEVICE}" "$@"
}

echo "Preparing upload worker on ${DEVICE}"
run_adb shell cmd jobscheduler run -f com.buccancs.agent 0 || true

echo "Waiting 5s for upload to start"
sleep 5

echo "Disabling Wi-Fi and mobile data"
run_adb shell svc wifi disable || true
run_adb shell svc data disable || true

echo "Sleeping 10s to capture failure state"
sleep 10

echo "Re-enabling Wi-Fi"
run_adb shell svc wifi enable || true

echo "Re-enabling mobile data"
run_adb shell svc data enable || true

echo "Triggering upload worker retry"
run_adb shell cmd jobscheduler run -f com.buccancs.agent 0 || true

echo "Collecting recent WorkManager logs"
run_adb logcat -d -t 200 "UploadWorker:D" "WorkManager:D" > "offline_recovery_${DEVICE}.log"

echo "Offline recovery drill completed. Check Upload Recovery UI and backlog telemetry files for status."
