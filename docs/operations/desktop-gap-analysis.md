# Desktop Orchestrator Gap Analysis

Updated: 2025-10-23

This note captures the outstanding work on the PC (Compose Desktop) side of the Buccancs platform. Use it to align engineering, design, and validation efforts as we close the multi-modal capture experience.

## 1. Critical Gaps

| Area | Current Status | Impact | Required Actions |
|------|----------------|--------|------------------|
| Unified capture surface | Only thermal preview shipped; RGB controls and Shimmer waveform absent. | Operators cannot drive all modalities from desktop; manual workflows persist. | Complete unified UX spike, finalise layouts, and implement combined session controls per `docs/planning/active-plan.md`. |
| Session ViewModel contract | Legacy per-modality view models; no shared orchestration state. | Risk of inconsistent sensor states and brittle start/stop flows. | Define shared session model, error surfaces, and recording intents; back with integration tests. |
| Shimmer telemetry on desktop | gRPC plumbing exists but waveform visualisation and diagnostics are missing. | Operators lack real-time feedback on wearable health. | Integrate Shimmer stream client, render live waveform, and surface battery/link quality indicators. |
| Metadata synchronisation | Thermal pipeline emits manifests; cross-modality metadata remains fragmented. | Downstream analysis cannot rely on consistent timestamps and identifiers. | Extend recording pipeline to persist unified manifests with device IDs, frame/sample cadence, and storage artefacts. |
| Automated validation | Desktop test suites disabled by default; mDNS tests flaky. | Regression risk increases as UI and services evolve. | Restore test flag, refactor for API drifts, stabilise mDNS cases, and gate releases on passing runs. |
| CI coverage | CI blocked on Android SDK licence acceptance; desktop build lacks dedicated job. | Changes rely on ad-hoc validation. | Reintroduce CI stage focused on desktop build/test with documented licence bootstrap steps. |

## 2. High-Value Enhancements (Good to Have)

| Theme | Description | Notes |
|-------|-------------|-------|
| Telemetry overlays | Add sparklines, threshold animations, and export hooks referenced in `docs/operations/telemetry.md`. | Improves operator insight during long sessions; requires lightweight history buffers. |
| Comparative multi-agent views | Enable side-by-side telemetry panels when multiple agents join a session. | Builds on existing EncoderPanel patterns; ensure performance baselines on low-end hosts. |
| Alerting and drills | Surface scripted validation helpers inside the desktop app (e.g., trigger cadence checks, upload recovery). | Bridges manual drills from `docs/operations/testing.md`; consider toggles behind developer mode. |
| Packaging and distribution | Provide signed installers + auto-update hooks (Windows/macOS/Linux). | Reduces friction for lab deployments; coordinate with infra on signing pipeline. |
| Operator onboarding | Embed contextual help, tooltips, and quick-start overlays for new operators. | Reuse copy from `docs/operations/telemetry.md` and hardware guides. |
| Headless orchestration tooling | Expand `HeadlessServer` usage notes with CLI flags for scripted sessions. | Supports automation labs and integration tests without GUI. |

## 3. Dependencies and Risks

- **Hardware availability** – Physical Topdon and Shimmer devices remain essential for validating unified capture; plan lab time accordingly.
- **Performance budget** – Combined previews (thermal + RGB) may tax GPUs on lower-spec PCs; profile before locking layouts.
- **Protocol stability** – Recording metadata work must align with Android agent updates to avoid mismatched schemas.
- **Design alignment** – Unified capture UX should be reviewed with design stakeholders before implementation to avoid rework.

## 4. Recommended Next Steps

1. Schedule a desktop-focused design/engineering sync to lock the unified capture scope and assign owners.
2. Branch a desktop test-recovery effort to re-enable the disabled suites and document the required fixes.
3. Prototype the Shimmer waveform panel using simulated data to de-risk rendering and performance.
4. Update `docs/planning/active-plan.md` as milestones complete, linking validation artefacts (logs, manifests, screenshots).

Keep this document current as work lands; annotate closed items with dates and links to supporting evidence (PRs, validation reports).
