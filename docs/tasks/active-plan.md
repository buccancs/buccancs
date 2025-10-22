# Active Task Plan: Multi-Modal Capture Interface

- Created: 2025-10-20
- Target completion: 2025-11-01
- Status: 🟡 In progress

## Objective

Design and ship a unified capture experience that controls thermal (Topdon),
RGB, and Shimmer sensors from a single screen. Build on the completed Topdon
Phase 1 & 2 work and align with the `Topdon Integration Summary`.

## Acceptance Criteria

1. Shared recording surface that can connect, preview, and start/stop capture
   for IR + RGB + Shimmer simultaneously.
2. Palette, emissivity, and gain settings surfaced within the unified view,
   reusing the hardware APIs delivered in Phase 2.
3. RGB preview upgraded with focus, zoom, exposure controls comparable to the
   thermal UI.
4. Shimmer panel exposes live waveform and connection diagnostics.
5. Recording pipeline writes synchronized metadata for all modalities (frame
   timestamps, sensor identifiers) ready for downstream analysis.
6. Regression tests or scripted drills documented so future changes defend the
   multi-modal contract.

## Task Breakdown

- [ ] UX design spike: refine the unified capture mock based on the design
  notes below and produce final layouts with navigation + empty states.
- [ ] ViewModel architecture: define shared session state, recording control
  actions, and error surfaces.
- [ ] RGB enhancements: extend camera connector with manual exposure/zoom plus
  preview overlays.
- [ ] Shimmer telemetry: add waveform rendering and richer status indicators in
  `ShimmerConnectorManager`.
- [ ] Capture pipeline: persist synchronized metadata and verify storage format
  across warm/cold starts.
- [ ] Verification suite: document manual QA flow and add automated smoke tests
  where feasible (e.g., simulated connectors).

## Dependencies & Risks

| Item           | Notes                                                                                                                  | Owner        |
|----------------|------------------------------------------------------------------------------------------------------------------------|--------------|
| IRCMD coverage | Ensure new commands (auto shutter, calibration) remain stable; reuse helpers outlined in `Topdon Integration Summary`. | Thermal team |
| Performance    | Combined previews may strain low-end tablets; budget render and encoding costs early.                                  | Mobile       |
| Storage layout | Multi-modal recordings will grow quickly; confirm retention policy with research team.                                 | Product      |

## Reference Material

- `docs/tasks/topdon-integration-summary.md` – hardware capability baseline.
- `docs/ircamera-architecture-analysis.md` – patterns to borrow from the
  upstream Topdon app.
- `docs/telemetry-ui-improvements.md` – prior Compose patterns for monitoring
  dashboards.

## Design Notes (carry-over from proposal)

- Single screen tabs or segmented control to switch between combined preview,
  settings, and recording history.
- Thermal preview retains palette/emissivity quick toggles; RGB preview adds
  zoom/focus sliders; Shimmer panel shows live waveform and connection badges.
- Unified "Record All" control with per-sensor status indicators and capture
  timers.
- Contextual tooltips to explain advanced thermal settings (gain, emissivity).

## Next Checkpoint

Schedule design + engineering review on 2025-10-24 to lock the unified UX and
divide implementation tasks across teams.

2. Reinstate a compact automation reference if new workflows surface.

3. Schedule a quarterly doc review to keep the curated set current.
