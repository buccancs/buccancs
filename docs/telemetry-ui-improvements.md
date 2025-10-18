# Telemetry UI Improvements

## Overview
Enhanced the time sync and stream telemetry panels in the Live Session screen to follow modern industrial UI/UX standards for monitoring dashboards.

## Changes Made

### 1. ClockPanel (Time Synchronization)
**Before:** Plain text dump of time sync metrics without visual hierarchy or formatting.

**After:** Modern card-based dashboard following industry standards from platforms like Grafana, Datadog, and New Relic.

#### Key Improvements:

**Visual Hierarchy**
- Quality badge with color-coded status indicator in the header
- Primary metrics (Offset, Round Trip) displayed as prominent metric cards
- Secondary metrics organized in a clean grid layout
- Advanced metrics (regression slope) conditionally shown in a subtle container

**Color Coding**
- GOOD: Primary color (blue) - System performing optimally
- FAIR: Tertiary color (amber) - Acceptable but degraded performance  
- POOR: Error color (red) - Immediate attention needed
- UNKNOWN: Outline color (gray) - Status unavailable

**Status Icons**
- CheckCircle for GOOD quality
- Warning for FAIR quality
- Error for POOR quality
- Help for UNKNOWN quality

**Metric Cards**
- Large, bold numbers with proper font (Monospace for precision)
- Clear labels and units
- Color highlighting for critical values (e.g., high clock offset)
- Secondary information displayed below primary values

**History Display**
- Formatted timestamps (HH:mm:ss) instead of ISO format
- Offset shown with sign indicator (+/- format) for clarity
- RTT displayed in a separate column for easy scanning
- Monospace font for numeric alignment
- Contained in a subtle background card for visual separation

### 2. EncoderPanel (Stream Telemetry)
**Before:** Plain text rows with boolean values and unformatted data.

**After:** Rich, informative cards with progress indicators and real-time status.

#### Key Improvements:

**Active Stream Summary**
- Header badge showing "X/Y Active" with play/stop icon
- Immediate visual feedback on streaming status

**Stream Status Cards**
- Individual cards per stream with color-coded backgrounds
- Connected streams: Primary container (blue tint)
- Inactive streams: Surface variant (neutral gray)
- Status icons (CheckCircle/Error) for quick identification

**Metric Display**
- Rate and Buffer metrics in compact chip-style containers
- Large, bold values with proper units (Hz, fps, seconds)
- Monospace font for numeric data

**Buffer Health Indicator**
- Visual progress bar showing buffer fill level
- Color-coded based on health:
  - ≥70%: Primary (healthy)
  - 30-70%: Tertiary (warning)
  - <30%: Error (critical)
- Percentage display for precise monitoring

**Timestamp Formatting**
- Millisecond precision (HH:mm:ss.SSS) for accurate debugging
- Relative age display ("just now", "5s ago", "2m ago")
- Color coding: Recent (<5s) in primary, stale in error color

**Simulation Indicator**
- Clear visual badge when data is simulated
- Warning icon for awareness

## Industrial UX/UI Standards Applied

### 1. **Metrics Dashboard Pattern**
Following standards from Grafana, Datadog, New Relic, and AWS CloudWatch:
- Large, scannable numbers for key metrics
- Color coding for status levels
- Visual hierarchy (primary → secondary → tertiary data)
- Monospace fonts for numeric precision

### 2. **Status Indicators**
- Icons + text for redundancy (accessibility)
- Semantic colors (error, warning, success)
- Consistent positioning (top-right badges)

### 3. **Progressive Disclosure**
- Most critical information prominently displayed
- Advanced metrics shown conditionally
- Details organized in logical groups

### 4. **Data Density Balance**
- Not too sparse (wasted space)
- Not too dense (overwhelming)
- White space used strategically for grouping

### 5. **Real-time Monitoring Best Practices**
- Progress indicators for ongoing processes (buffer health)
- Relative timestamps for recent events
- Visual status changes (color, icons) for quick scanning

### 6. **Material Design 3 Compliance**
- Proper elevation (cards with shadows)
- Appropriate shape tokens (rounded corners)
- Theme-aware colors (works in light/dark modes)
- Consistent spacing using design tokens

### 7. **Accessibility**
- Semantic descriptions for screen readers maintained
- Text + icon redundancy (not icon-only)
- Sufficient color contrast
- Test tags preserved for automated testing

## Performance Considerations
- No expensive animations added
- Minimal recomposition scope (individual metric cards)
- Conditional rendering (e.g., regression slope only when relevant)
- Efficient string formatting with Locale.US

## Backwards Compatibility
- All test tags preserved for UI testing
- Semantic descriptions maintained for accessibility
- Same data contracts (no changes to data models)
- ViewModels unchanged

## Visual Comparison

### Time Sync Panel
**Before:**
```
Time Synchronisation
Offset: 3 ms
Round-trip: 9 ms (filtered 12.00 ms)
Quality: Fair
Drift estimate: 0.40 ms/min
Regression slope: 0.10 ms/min
Samples collected: 18
```

**After:**
- Header with "Fair" quality badge (amber, with warning icon)
- 2×2 grid of metric cards with large numbers
- Colored progress/status indicators
- Formatted history table with aligned columns

### Stream Telemetry Panel
**Before:**
```
rgb-camera — RGB_VIDEO
Rate: 30.00 fps  Streaming: true
Buffered: 1.40 s · Simulated: false
Last sample: 2023-11-14T10:30:45.123Z
```

**After:**
- Card header: "Stream Telemetry" with "1/2 Active" badge
- Rich stream card with:
  - Device name + stream type
  - Status icon (green check)
  - Rate & Buffer metric chips
  - Buffer health progress bar (70% - green)
  - Formatted timestamp with relative age ("2s ago")

## References
- Material Design 3: https://m3.material.io/
- Grafana Design System: https://grafana.com/developers/
- Datadog Metrics: https://www.datadoghq.com/
- Google Cloud Monitoring: https://cloud.google.com/monitoring
- AWS CloudWatch: https://aws.amazon.com/cloudwatch/
