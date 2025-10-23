# Telemetry UX Handbook

The Live Session telemetry panes (ClockPanel and EncoderPanel) now follow a
consistent pattern language borrowed from Grafana, Datadog, New Relic, and
Material Design 3. This guide captures the rules so future changes stay aligned.

## 1. Panels at a Glance

| Panel | Purpose | Key Components |
|-------|---------|----------------|
| ClockPanel | Visualises time synchronisation health between desktop and agents. | Quality badge, primary metric cards (offset, round-trip), secondary metrics, history table. |
| EncoderPanel | Tracks stream health per modality (thermal, RGB, audio, GSR). | Active-stream summary, per-stream status cards, metric chips, buffer health bar, timestamp indicators. |

Both panels surface simulation states, highlight stale data, and keep layout
responsive for tablet and desktop viewports.

## 2. Status Semantics

| State | Colour Token | Icon | Description |
|-------|--------------|------|-------------|
| GOOD | `primary` | `CheckCircle` | Within target thresholds; no operator action. |
| FAIR | `tertiary` | `Warning` | Degraded but tolerable; monitor closely. |
| POOR | `error` | `Error` | Immediate attention required. |
| UNKNOWN | `outline` | `Help` | Insufficient data; often at start-up. |

Apply the same semantic mapping across badges, progress bars, and text accents.

## 3. Metric Presentation Rules

- **Metric cards** – Label (`labelSmall`), value (`headlineSmall`, monospace),
  unit (`bodySmall`). Reserve cards for primary metrics only.
- **Progress indicators** – Buffer health uses a horizontal progress bar with
  thresholds: ≥70 % primary, 30–69 % tertiary, <30 % error.
- **Timestamps** – Show absolute (`HH:mm:ss.SSS`) and relative (“2 s ago”)
  values. Switch text colour to `error` when age ≥5 s.
- **History table** – Align columns with monospace fonts, include offset sign,
  and keep the most recent ~6 samples.
- **Metric chips** – Compact containers for secondary values (rate, buffer
  duration). Structure: label (small), divider, value (bold, monospace).

## 4. Progressive Disclosure

1. Always render primary metrics and badges.
2. Render secondary metrics only when data exists (e.g., sample count > 0).
3. Gate advanced metrics (regression slope) behind clear predicates so empty
   states never flash on screen.
4. Provide simulation badges whenever connectors run in synthetic mode.

## 5. Implementation Notes

- Compose components live in:
  - `ClockPanel.kt` → `MetricCard`, `HistoryRow`.
  - `EncoderPanel.kt` → `StreamCard`, `StreamMetricChip`.
- Data contracts remained unchanged; the remodel focused on presentation.
- Accessibility:
  - All icons include `contentDescription`.
  - Text + icon redundancy ensures colour-blind readability.
  - Test tags (`testTag`) preserved for UI automation.
- Performance:
  - No new animations; recomposition scope limited to individual cards.
  - Formatting uses `Locale.US` for consistent decimal output.

## 6. Future Enhancements

1. Sparkline overlays for offset and buffer trends.
2. Threshold alerts with gentle animations when metrics flip state.
3. Comparative views for multi-agent sessions.
4. Export hooks for telemetry snapshots (CSV/JSON) to support post-session
   analysis.

When expanding telemetry, reuse these patterns or extend them deliberately. Any
departure should include rationale and updated guidance in this document.
