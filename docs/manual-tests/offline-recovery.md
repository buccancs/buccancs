# Offline Upload Recovery Drill

This checklist exercises FR7 by simulating a mid-upload network loss, observing retry behaviour, and confirming artifact
reconciliation.

## Prerequisites

- Android agent build with the latest `UploadRecoveryLogger` changes.
- Recorded session with pending artifacts (stop an earlier session without connectivity to leave files on device).
- USB debugging enabled; host machine with `adb` installed.
- Optional: `adb logcat` capture to archive WorkManager traces.

## Steps

1. **Prime WorkManager** *(run manually or via `tools/tests/offline_recovery.sh <adb-serial>`)*
    1. Ensure device is online (`adb shell svc wifi enable`, `svc data enable` as needed).
    2. Trigger an upload job:
       ```bash
       adb shell cmd jobscheduler run -f com.buccancs.agent 0
       ```
    3. Confirm at least one artifact begins uploading (Live Session → Uploads card).

2. **Drop Connectivity Mid-Transfer**
    1. While upload is `IN_PROGRESS`, disconnect all transports:
       ```bash
       adb shell svc wifi disable
       adb shell svc data disable
       ```
    2. Observe the Live Session `Upload Recovery` card: a new entry should appear with `state=FAILED`,
       `network=... offline`.
    3. Tail recovery log:
       ```bash
       adb shell run-as com.buccancs.agent \
         tail -n 5 files/recordings/<session-id>/upload_recovery.jsonl
       ```
       Expect a FAILED record with the same timestamp as the UI entry.

3. **Restore Connectivity & Verify Retry**
    1. Re-enable network:
       ```bash
       adb shell svc wifi enable
       adb shell svc data enable
       ```
    2. Observe WorkManager eventually moves the upload back to `IN_PROGRESS` then `COMPLETED`.
    3. Confirm recovery log contains a matching COMPLETED record (same session/device/stream, higher `attempt`).
    4. If you used the helper script, inspect the generated `offline_recovery_<serial>.log` for WorkManager traces.

4. **Artifact Reconciliation**
    1. Check session manifest (`files/recordings/<session-id>/manifest.json`) to verify artifact remains listed.
    2. Ensure artifact file is deleted from device storage (upload success path) or present for future retries (failure
       path).
    3. Review `files/recordings/<session-id>/backlog_telemetry.jsonl` for warning/critical transitions recorded during
       the drill.

## Notes

- If WorkManager does not retry automatically, manually re-run the job (#1.2) after restoring connectivity.
- Capture `adb logcat -s UploadWorker WorkManager` during the drill for evidence.
- Record outcomes (timestamps, attempt counts, observations) in `dev-diary.txt`.
