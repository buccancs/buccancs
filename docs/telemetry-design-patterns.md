# Telemetry Display Design Patterns

## Overview This document outlines the specific UX/UI design patterns applied to the telemetry displays, with references to industry standards.

## 1. Metric Card Pattern

### Structure

```
┌─────────────────────────┐
│ Label (small, gray)     │
│ ┌─────────┐             │
│ │ Value   │ unit        │ ← Large bold number + small unit
│ └─────────┘             │
│ Secondary info (small)  │ ← Optional context
└─────────────────────────┘
```

### Implementation - **Background:** Tinted container (primary/error based on status) - **Typography: - Label:

`labelSmall`, onSurfaceVariant - Value: `headlineSmall`, Bold, Monospace, themed color - Unit:
`bodySmall`, onSurfaceVariant - **Spacing:** 12dp padding, 4dp gaps - **Shape:\*\* Medium rounded corners

### Industry Examples - **Grafana:** "Stat" panel - **Datadog:** Metrics widget - **AWS CloudWatch:** Number widget

## 2. Status Badge Pattern

### Structure

```
┌────────────────────┐
│ [Icon] Quality     │ ← Icon + text in colored badge
└────────────────────┘
```

### Color Mapping - **GOOD:** Primary (blue) + CheckCircle icon - **FAIR:** Tertiary (amber) + Warning icon - **POOR:

** Error (red) + Error icon - **UNKNOWN:** Outline (gray) + Help icon

### Implementation - **Container:** Small surface with 15% alpha tint - **Padding:** 12dp horizontal, 6dp vertical - *

*Typography:** `labelMedium`, SemiBold - **Icon Size:** 16dp

### Industry Standard - **Google Cloud:** Service health indicators - **Azure:** Resource status badges - **Kubernetes

Dashboard:** Pod status

## 3. Progress Bar Health Indicator

### Structure

```
Buffer Health              78%
━━━━━━━━━━━━━━━━░░░░        ← Filled progress bar
```

### Color Thresholds - **≥70%:** Primary (green) - Healthy - **30-69%:** Tertiary (amber) - Warning - **<30%:

** Error (red) - Critical

### Implementation

```kotlin
val bufferProgress = (buffered / target).coerceIn(0.0, 1.0)
LinearProgressIndicator(
    progress = { bufferProgress },
    color = when {
        bufferProgress >= 0.7f -> primary
        bufferProgress >= 0.3f -> tertiary
        else -> error
    }
)
```

### Industry Standard - **New Relic:** Transaction performance - **Datadog:** Host metrics - **Prometheus/Grafana:

** Gauge displays

## 4. History Table Pattern

### Structure

```
┌────────────────────────────────────────┐
│ Recent Observations                    │
├────────────────────────────────────────┤
│ 10:30:45    Δ +2.3 ms    RTT 8.7 ms   │
│ 10:30:40    Δ +2.0 ms    RTT 9.2 ms   │
│ 10:30:35    Δ +1.8 ms    RTT 8.9 ms   │
└────────────────────────────────────────┘
```

### Formatting Rules - **Timestamp:** HH:mm:ss (no date if recent) - **Offset:** Show sign (+/-), 1 decimal place - *

*RTT:** 1 decimal place - **Font:** Monospace for alignment - **Color:** Primary for offset, variant for RTT

### Implementation

```kotlin
Row(horizontalArrangement = SpaceBetween) {
    Text(timestamp, fontFamily = Monospace)
    Text("Δ +%.1f ms", color = primary)
    Text("RTT %.1f ms", color = onSurfaceVariant)
}
```

### Industry Standard - **Chrome DevTools:** Network timing - **Grafana:** Table panel - **Kibana:** Log viewer

## 5. Stream Status Card Pattern

### Structure

```
┌─────────────────────────────────┐
│ device-name            [✓]      │ ← Header
│ STREAM_TYPE                     │
├─────────────────────────────────┤
│ ┌──────────┐ ┌──────────┐      │
│ │ Rate     │ │ Buffered │      │ ← Metric chips
│ │ 30.0 Hz  │ │ 1.4 s    │      │
│ └──────────┘ └──────────┘      │
├─────────────────────────────────┤
│ Buffer Health         70%       │
│ ━━━━━━━━━━━━━━░░░░░            │ ← Progress
├─────────────────────────────────┤
│ Last: 10:30:45.123    2s ago   │ ← Timestamp
└─────────────────────────────────┘
```

### Color States - **Streaming:** Primary container background - **Idle:** Surface variant background - **Error:

** Error container background (if disconnected)

### Industry Standard - **Kubernetes Dashboard:** Pod cards - **Docker Desktop:** Container cards - **Jenkins:

** Build job cards

## 6. Relative Time Display Pattern

### Rules

```
Age         Display
─────────────────────
< 1s        "just now"
1-59s       "Xs ago"
1-59m       "Xm ago"
≥ 1h        "Xh ago"
```

### Color Coding - **< 5s:** Primary (data is fresh) - **≥ 5s:** Error (data is stale)

### Implementation

```kotlin
val ageMs = now - timestamp
val ageText = when {
    ageMs < 1000 -> "just now"
    ageMs < 60000 -> "${ageMs / 1000}s ago"
    else -> "${ageMs / 60000}m ago"
}
```

### Industry Standard - **GitHub:** Commit times - **Slack:** Message timestamps - **Twitter/X:** Tweet timestamps

## 7. Metric Chip Pattern

### Structure

```
┌──────────┐
│ Label    │ ← Small label
│ ──────── │
│ 30.0 Hz  │ ← Value + unit
└──────────┘
```

### Styling - **Background:** Surface with 60% alpha - **Shape:** Small rounded corners - **Padding:** 8dp - **Value:**

`titleMedium`, Bold, Monospace

### Use Cases - Compact metric display - Grid layouts - Quick-scan information

## 8. Conditional Display Pattern

### Rules 1. \*\*Show only when relevant:

```kotlin
if (status.regressionSlope.isFinite() && status.sampleCount > 3) {
    // Show advanced metric
}
```

2. **Progressive enhancement:** - Always show: Primary metrics - Show when
   available: Secondary metrics - Show when significant: Warnings/alerts

3. **Graceful degradation:**
   ```kotlin
   value?.let { formatDouble(it) } ?: "n/a"
   ```

### Industry Standard - **Grafana:** Panel visibility rules - **Datadog:** Conditional formatting - **New Relic:

** Alert thresholds

## 9. Typography Scale

### Hierarchy

```
Title:     titleMedium (16sp), SemiBold
Metric:    headlineSmall (24sp), Bold, Monospace
Label:     labelSmall (11sp), Regular
Body:      bodyMedium (14sp), Regular
Secondary: bodySmall (12sp), Regular
```

### Font Families - **Monospace:** For numeric data (alignment, precision) - **Default:** For labels, descriptions

### Industry Standard - **Material Design 3:** Type scale - **IBM Carbon:** Type tokens - **Atlassian Design:

** Typography

## 10. Color Semantics

### Status Colors

```kotlin
GOOD/SUCCESS    → primary (blue/green)
WARNING/FAIR    → tertiary (amber)
ERROR/POOR      → error (red)
NEUTRAL/UNKNOWN → outline (gray)
```

### Usage - **Background tints:** 15-40% alpha - **Text colors:** Full opacity - **Icons:** Match text color - *

*Borders:** Not used (prefer elevation)

### Industry Standard - **Material Design:** Color system - **Bootstrap:** Contextual colors - **Tailwind:

** Color palette

## 11. Spacing & Density

### Spacing Scale (4dp grid)

```
ExtraSmall:  4dp   - Internal chip spacing
Small:       8dp   - Between elements
SmallMedium: 12dp  - Card internal padding
Medium:      16dp  - Card padding, gaps
Large:       24dp  - Section spacing
```

### Density Levels - **High:** Mobile, limited space - **Medium:** Tablet, standard (current) - **Low:

** Desktop, ample space

### Industry Standard - **Material Design:** Spacing - **iOS HIG:

** Layout margins - \*\*Web Content Accessibility Guidelines (WCAG)

## 12. Accessibility Patterns

### Required Elements 1. \*\*Semantic descriptions:

```kotlin
.semantics { contentDescription = "..." }
```

2. **Icon + text redundancy:**

   ```kotlin
   Icon(...) + Text(...) // Never icon alone
   ```

3. **Color independence:** - Don't rely solely on color - Use icons, text,
   patterns

4. **Touch targets:** - Minimum 48dp for interactive elements

### Industry Standard - **WCAG 2.1 Level AA - **Material Accessibility - \*\*Section 508 Compliance

## Summary

These patterns create a professional, scannable, and accessible monitoring
interface that follows industry standards from:

- **Cloud Providers:** AWS, Google Cloud, Azure - **Monitoring Platforms:**
  Grafana, Datadog, New Relic, Prometheus - **DevOps Tools:** Kubernetes,
  Docker, Jenkins - **Design Systems:** Material Design 3, IBM Carbon, Atlassian

The result is an interface that feels familiar to users of modern monitoring
dashboards while maintaining the app's unique design language.
