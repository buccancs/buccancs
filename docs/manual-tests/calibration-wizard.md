# Calibration Wizard Operator Walkthrough

This sequence validates FR8 improvements by guiding an operator through the new wizard, confirming prompts, and
capturing final metrics.

## Environment

- Android agent build with `CalibrationWizardStep` UI.
- Dual-camera rig attached (Topdon thermal + RGB).
- Checkerboard pattern with configuration matching defaults (7×9, 24 mm squares).

## Steps

1. **Configure Pattern**
    1. Open Dashboard → *Stereo Calibration Wizard* panel.
    2. Verify `Configure Pattern` is highlighted.
    3. Adjust rows/columns/square size if required; press **Apply**.
    4. Expect info toast “Pattern updated to …” and wizard remains on Configure.

2. **Start Session**
    1. Press **Start Session**; wizard advances to `Capture`, guidance updates to prompt captures.
    2. Confirm capture progress bar resets to 0%.

3. **Capture Pairs**
    1. Position checkerboard; tap **Capture Pair** repeatedly while varying pose and distance.
    2. Watch capture list fill; guidance text reflects remaining pairs.
    3. When captured count ≥ required pairs, progress hits 100% and guidance prompts to compute calibration.

4. **Compute Calibration**
    1. Tap **Compute**; wizard enters `Validate`. Button disables while processing.
    2. Expect info message “Computing calibration…” then completion message with RMS value.
    3. Review warning banner if max error > 1.0 px.

5. **Review Metrics**
    1. Wizard transitions to `Review`; confidence label (High/Moderate/Low) shows.
    2. Verify metrics persisted:
       ```bash
       adb shell run-as com.buccancs.agent \
         cat files/datastore/calibration_metrics.preferences_pb
       ```
       (Use `datastore` tool or copy for decoding.)
    3. `Last Calibration` section should reflect the new result timestamp.

6. **Load Cached Result (Optional)**
    1. Tap **Load Cached Result** after clearing session; wizard re-populates metrics without re-capturing.

## Acceptance Checklist

- [ ] Wizard steps change in order: Configure → Capture → Validate → Review.
- [ ] Guidance text matches step context.
- [ ] Progress bar reflects capture ratio.
- [ ] Confidence label aligns with RMS/max thresholds.
- [ ] `calibration_metrics.preferences_pb` updates with latest run.
- [ ] `upload_recovery.jsonl` remains untouched (control check—wizard should not trigger uploads).

Log findings (including RMS, confidence, anomalies) in `dev-diary.txt`.
