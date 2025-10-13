#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
ANALYZER="${ROOT_DIR}/tools/perf/analyze_metrics.py"

if [[ ! -f "${ANALYZER}" ]]; then
  echo "Performance analyzer not found at ${ANALYZER}" >&2
  exit 1
fi

HOST="localhost"
PORT="50051"
SESSION_ID="stress-session"
DURATION=120
RUNS=1
DEVICES=()
GRPCURL_BIN="${GRPCURL_BIN:-grpcurl}"
EXTRA_GRPCURL_FLAGS=()

usage() {
  cat <<EOF
Usage: $0 [options]

Options:
  -H, --host <host>           Orchestrator host (default: ${HOST})
  -P, --port <port>           Orchestrator port (default: ${PORT})
  -s, --session <id>          Session identifier for the stress run (default: ${SESSION_ID})
  -d, --duration <seconds>    Recording duration per run (default: ${DURATION})
  -r, --runs <count>          Number of consecutive runs (default: ${RUNS})
  --device <adb-id>           Target Android device (repeat for multiple; default: all attached)
  --grpcurl-flag <flag>       Extra flag passed to grpcurl (repeat as needed)
  -h, --help                  Show this message.

Example:
  $0 --session lab_burn_in --duration 300 --runs 3 --device emulator-5554 --device 0123456789ABCDEF
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    -H|--host)
      HOST="$2"; shift 2;;
    -P|--port)
      PORT="$2"; shift 2;;
    -s|--session)
      SESSION_ID="$2"; shift 2;;
    -d|--duration)
      DURATION="$2"; shift 2;;
    -r|--runs)
      RUNS="$2"; shift 2;;
    --device)
      DEVICES+=("$2"); shift 2;;
    --grpcurl-flag)
      EXTRA_GRPCURL_FLAGS+=("$2"); shift 2;;
    -h|--help)
      usage; exit 0;;
    *)
      echo "Unknown option: $1" >&2
      usage
      exit 1;;
  esac
done

if [[ ${#DEVICES[@]} -eq 0 ]]; then
  mapfile -t DEVICES < <(adb devices | awk 'NR>1 && $2=="device" {print $1}')
fi

if [[ ${#DEVICES[@]} -lt 1 ]]; then
  echo "No target devices detected. Use --device to specify at least one ADB id." >&2
  exit 1
fi

echo "Using devices: ${DEVICES[*]}"
echo "Session prefix: ${SESSION_ID}"
echo "Runs: ${RUNS}, Duration: ${DURATION}s"

run_grpc() {
  local method="$1"
  local payload="$2"
  "${GRPCURL_BIN}" -plaintext \
    "${EXTRA_GRPCURL_FLAGS[@]}" \
    -d "${payload}" \
    "${HOST}:${PORT}" \
    "com.buccancs.control.OrchestrationService/${method}"
}

timestamp_ms() {
  python3 - <<'PY'
import time
print(int(time.time() * 1000))
PY
}

ARTIFACT_ROOT="${ROOT_DIR}/artifacts/multi-device-stress/$(date +"%Y%m%d_%H%M%S")"
mkdir -p "${ARTIFACT_ROOT}"

for run in $(seq 1 "${RUNS}"); do
  RUN_SESSION="${SESSION_ID}_run${run}"
  echo ""
  echo "==> Run ${run}/${RUNS} - session ${RUN_SESSION}"
  START_PAYLOAD=$(cat <<JSON
{
  "session": {"id": "${RUN_SESSION}"},
  "scheduledEpochMs": $(timestamp_ms()),
  "operatorId": "stress_harness"
}
JSON
)
  echo "Starting session via OrchestrationService/StartSession..."
  run_grpc "StartSession" "${START_PAYLOAD}"

  echo "Recording for ${DURATION}s..."
  sleep "${DURATION}"

  STOP_PAYLOAD=$(cat <<JSON
{
  "session": {"id": "${RUN_SESSION}"},
  "scheduledEpochMs": $(timestamp_ms()),
  "finalize": true
}
JSON
)
  echo "Stopping session..."
  run_grpc "StopSession" "${STOP_PAYLOAD}"

  SESSION_DIR="${ARTIFACT_ROOT}/${RUN_SESSION}"
  mkdir -p "${SESSION_DIR}"

  echo "Collecting performance metrics..."
  for device in "${DEVICES[@]}"; do
    DEST="${SESSION_DIR}/${device}_performance_metrics.jsonl"
    adb -s "${device}" shell run-as com.buccancs.agent \
      cat "files/recordings/${RUN_SESSION}/performance_metrics.jsonl" \
      > "${DEST}" || {
        echo "Warning: unable to fetch metrics for ${device}" >&2
        continue
      }
    if [[ -s "${DEST}" ]]; then
      python3 "${ANALYZER}" --input "${DEST}" --label "${device}" \
        > "${DEST%.jsonl}_summary.txt"
    fi
  done
  if adb -s "${DEVICES[0]}" shell run-as com.buccancs.agent test -f "files/recordings/${RUN_SESSION}/performance_summary.json" >/dev/null 2>&1; then
    adb -s "${DEVICES[0]}" shell run-as com.buccancs.agent \
      cat "files/recordings/${RUN_SESSION}/performance_summary.json" \
      > "${SESSION_DIR}/performance_summary.json"
  fi
done

echo ""
echo "Stress run complete. Raw metrics stored under ${ARTIFACT_ROOT}"
echo "Summaries generated with ${ANALYZER} for each device."
