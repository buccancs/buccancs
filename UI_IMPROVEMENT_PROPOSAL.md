# UI Improvement Proposal: Unified Multi-Modal Recording Interface

**Date**: October 20, 2025  
**Status**: 📋 PROPOSAL

## Executive Summary

Propose a unified recording interface inspired by IRCamera that provides simultaneous control of thermal (IR), RGB camera, and GSR (Shimmer) sensors. This creates a cohesive research data collection experience matching the platform's mission of **synchronized multi-modal physiological data capture**.

---

## Current State Analysis

### What We Have

**1. Separate Screens per Modality**
```
/devices → Device list
  ├─ /topdon/thermal → Thermal preview (good UI, Phase 1+2 complete)
  ├─ /camera/rgb → RGB preview (basic UI, minimal controls)
  └─ /shimmer → Shimmer GSR (card-based, no preview)
```

**Strengths**:
- ✅ Clean separation of concerns
- ✅ Topdon thermal UI is polished (palette, settings, preview)
- ✅ Each modality can be developed independently

**Weaknesses**:
- ❌ User must navigate between 3 separate screens
- ❌ No unified view showing all sensors simultaneously
- ❌ Difficult to verify synchronization across modalities
- ❌ No single "Record All" button for research sessions
- ❌ RGB camera UI is basic (no zoom, focus, exposure controls)
- ❌ Shimmer UI has no real-time waveform preview

---

## IRCamera Insights

### What IRCamera Does Well

**1. Dual Camera View** (IR + RGB side-by-side)
- Split-screen showing thermal and visible light simultaneously
- Picture-in-picture mode for compact view
- Toggle between thermal-primary and RGB-primary layouts
- Synchronized capture (both images taken together)

**2. Rich Camera Controls**
- Zoom slider (1x-8x digital zoom)
- Focus modes (auto, manual, touch-to-focus)
- Exposure compensation slider
- White balance presets (auto, daylight, cloudy, etc.)
- Grid overlay toggle
- Flash control for RGB

**3. Settings Organization**
- Quick-access toolbar for common settings (palette, emissivity)
- Expandable settings panel without leaving preview
- Settings grouped logically (Image, Camera, Measurement)

**4. Measurement Tools**
- Spot temperature (tap anywhere on thermal image)
- Area measurement (rectangle/ellipse selection)
- Line profile (temperature along a line)
- Multiple measurements simultaneously
- Measurement results overlay on preview

**5. Gallery Integration**
- In-app gallery showing captured images/videos
- Filter by date, measurement type
- Quick preview without leaving app
- Share/export from gallery

### What We Should Adopt

**High Priority** (Match research needs):
1. ✅ **Unified recording view** - All sensors in one screen
2. ✅ **Side-by-side preview** - Thermal + RGB + GSR waveform
3. ✅ **Synchronized capture** - Single button records all modalities
4. ✅ **Real-time waveform** - GSR data visualization during preview
5. ✅ **Session management** - Named sessions with all artifacts

**Medium Priority** (Enhance usability):
6. **RGB camera controls** - Zoom, focus, exposure
7. **Grid overlays** - Alignment aid for thermal+RGB
8. **Quick settings panel** - Access common settings without navigation
9. **Measurement tools** - Spot/area temperature (already in plan)

**Low Priority** (Nice to have):
10. Social sharing features (not needed for research)
11. AR mode (out of scope)
12. PDF reports (handled by desktop app)

---

## Proposed UI Architecture

### Option A: Unified Recording Screen (Recommended)

**Single screen with three panes**:

```
┌─────────────────────────────────────────────────┐
│  🔴 Recording Session: "Subject_001_Trial_3"   │
│  [00:05:23] Session active                      │
└─────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────┐
│           ┌──────────┐ ┌──────────┐             │
│  Thermal  │          │ │          │  RGB        │
│  256x192  │  IR View │ │ RGB View │  1920x1080  │
│  32.5°C   │          │ │          │  f/1.8      │
│  avg      └──────────┘ └──────────┘             │
└─────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────┐
│  GSR Waveform (Shimmer3)                        │
│  ╱╲    ╱╲    ╱╲     Current: 2.45 µS           │
│ ╱  ╲  ╱  ╲  ╱  ╲    Range: 1.8-4.2 µS          │
│      ╲╱    ╲╱                                    │
└─────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────┐
│  [⏸ Pause]  [⏹ Stop]  [📸 Snapshot]  [⚙️]      │
└─────────────────────────────────────────────────┘
```

**Navigation**: 
- `/session/recording` - Main unified recording screen
- `/session/settings` - Quick settings overlay (palette, emissivity, exposure)
- `/session/gallery` - Review captured data

**Advantages**:
- Single button starts/stops all sensors
- Immediate feedback on synchronization
- Researcher sees all data streams at once
- Matches research workflow perfectly

---

### Option B: Tabbed Interface with Preview Bar

**Three tabs with always-visible preview bar**:

```
┌─────────────────────────────────────────────────┐
│  🔴 Active Session: "Baseline_Measurement"      │
└─────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────┐
│  [Thermal] [RGB] [GSR]                          │ ← Tabs
│  ▀▀▀▀▀▀▀▀                                       │
│  ┌──────────────────────────────────────────┐   │
│  │                                          │   │
│  │        Active Tab Content                │   │
│  │        (Full screen preview)             │   │
│  │                                          │   │
│  └──────────────────────────────────────────┘   │
└─────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────┐
│  Mini Previews:  [IR: 32°C]  [RGB: ✓]  [GSR: ~]│ ← Always visible
└─────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────┐
│  [🔴 Record All]  [⏹ Stop]  [⚙️ Settings]      │
└─────────────────────────────────────────────────┘
```

**Advantages**:
- Each modality gets full-screen when needed
- Quick navigation between modalities
- Still shows all sensor status
- Easier to implement (less layout complexity)

**Disadvantages**:
- Not truly "simultaneous" view
- Requires tab switching to see all data

---

### Option C: Picture-in-Picture with Focus Mode

**One primary view with others as PiP overlays**:

```
┌─────────────────────────────────────────────────┐
│  ┌────────────────────────────────────────┐     │
│  │                                        │  ┌─┐ │
│  │                                        │  │R│ │ RGB PiP
│  │         Thermal (Primary)              │  │G│ │
│  │                                        │  │B│ │
│  │         256x192 @ 12 FPS                │  └─┘ │
│  │                                        │     │
│  │  Min: 18.2°C  Max: 36.8°C  Avg: 32.5  │  ┌─┐ │
│  │                                        │  │G│ │ GSR PiP
│  └────────────────────────────────────────┘  │S│ │
│                                               │R│ │
│  [↔️ Switch Primary] [⚙️] [🔴 Record]        └─┘ │
└─────────────────────────────────────────────────┘
```

**Advantages**:
- Follows IRCamera's proven pattern
- One sensor gets full attention
- Others still visible for synchronization check
- Tap PiP to swap primary

**Disadvantages**:
- PiP views are small
- May be hard to see details in secondary views

---

## Recommended Approach

**Phased Implementation**:

### Phase A: Enhanced Individual Screens (1 week)
**Goal**: Bring RGB and Shimmer UI to Topdon's quality level

**RGB Camera Improvements**:
1. Add camera controls similar to Topdon
   - Zoom slider (1x-8x digital)
   - Focus mode toggle (auto/manual)
   - Exposure compensation slider (-2 to +2 EV)
   - Grid overlay toggle
2. Add flash control
3. Improve preview with aspect ratio handling
4. Add capture button with feedback

**Shimmer Improvements**:
1. Add real-time waveform chart (using Compose Canvas)
   - Scrolling graph showing last 30 seconds
   - Y-axis: µS (microsiemens)
   - X-axis: time
2. Add current value display (large text)
3. Add statistics (min/max/avg over window)
4. Color coding for signal quality

**Deliverables**:
- `/camera/rgb` - Enhanced RGB preview with controls
- `/shimmer` - Real-time waveform visualization
- All modalities at feature parity with thermal

---

### Phase B: Unified Recording Screen (1-2 weeks)
**Goal**: Create single-screen multi-modal recording interface

**Implementation**:
1. Create new `/session/recording` screen
2. Implement three-pane layout (Option A)
   - Top: Thermal + RGB side-by-side (16:9 each)
   - Middle: GSR waveform (scrolling chart)
   - Bottom: Control bar
3. Synchronized recording:
   - Single "Record All" button
   - Starts thermal capture, RGB video, GSR streaming
   - Stops all simultaneously
   - Creates unified session directory
4. Session management:
   - Named sessions (e.g., "Subject_001_Baseline")
   - Timer showing recording duration
   - Status indicators per modality

**Deliverables**:
- Unified recording screen
- Session management (start/stop/pause all)
- Synchronized artifact storage

---

### Phase C: Gallery & Review (1 week)
**Goal**: In-app review of captured sessions

**Implementation**:
1. Create `/session/gallery` screen
2. List sessions with thumbnails
3. Session detail view:
   - Thermal image gallery
   - RGB video playback
   - GSR waveform playback (synchronized timeline)
4. Export options:
   - Share session (ZIP all artifacts)
   - Delete session
   - Rename session

**Deliverables**:
- Gallery screen with session list
- Session detail with multi-modal playback
- Export/share functionality

---

### Phase D: Advanced Features (1-2 weeks)
**Goal**: Measurement tools and analysis

**Implementation**:
1. Spot temperature measurement (tap thermal image)
2. Area measurement (rectangle selection)
3. Temperature tracking (follow hotspot)
4. RGB alignment grid (overlay on both views)
5. Quick settings panel (slide-up overlay)

**Deliverables**:
- Measurement tools (spot/area/line)
- Alignment aids
- Quick access settings

---

## Technical Architecture

### New Components

**1. UnifiedRecordingScreen.kt**
```kotlin
@Composable
fun UnifiedRecordingScreen(
    thermalState: TopdonUiState,
    rgbState: RgbCameraUiState,
    shimmerState: ShimmerUiState,
    sessionState: RecordingSessionState,
    onStartSession: (String) -> Unit,
    onStopSession: () -> Unit,
    onPauseSession: () -> Unit,
    onSnapshot: () -> Unit
)
```

**2. RecordingSessionViewModel.kt**
```kotlin
class RecordingSessionViewModel @Inject constructor(
    private val topdonRepo: TopdonDeviceRepository,
    private val rgbRepo: RgbCameraRepository,
    private val shimmerRepo: ShimmerRepository,
    private val sessionManager: RecordingSessionManager
) : ViewModel() {
    
    val combinedState: StateFlow<UnifiedRecordingState>
    
    suspend fun startRecordingAll(sessionName: String)
    suspend fun stopRecordingAll()
    suspend fun captureSnapshot()  // All sensors at once
}
```

**3. RealTimeWaveformChart.kt** (for GSR)
```kotlin
@Composable
fun RealTimeWaveformChart(
    dataPoints: List<GsrDataPoint>,
    windowSize: Duration = 30.seconds,
    modifier: Modifier = Modifier
) {
    Canvas(modifier) {
        // Draw scrolling waveform using Compose Canvas
        // Y-axis: µS (0-10 range typical)
        // X-axis: time (30 second window)
        // Auto-scaling based on data range
    }
}
```

**4. MultiModalGalleryScreen.kt**
```kotlin
@Composable
fun MultiModalGalleryScreen(
    sessions: List<RecordingSession>,
    onSessionClick: (SessionId) -> Unit,
    onExportSession: (SessionId) -> Unit,
    onDeleteSession: (SessionId) -> Unit
)
```

---

### Data Model Changes

**RecordingSession.kt**:
```kotlin
data class RecordingSession(
    val id: SessionId,
    val name: String,
    val startTimestamp: Instant,
    val endTimestamp: Instant?,
    val duration: Duration,
    val thermalArtifacts: List<ThermalArtifact>,
    val rgbArtifacts: List<RgbVideoArtifact>,
    val gsrArtifacts: List<GsrStreamArtifact>,
    val status: SessionStatus  // Recording, Paused, Complete
)

enum class SessionStatus {
    Recording,
    Paused,
    Complete,
    Failed
}
```

**UnifiedRecordingState.kt**:
```kotlin
data class UnifiedRecordingState(
    val session: RecordingSession?,
    val thermalPreview: TopdonPreviewFrame?,
    val rgbPreview: RgbPreviewFrame?,
    val gsrCurrent: GsrDataPoint?,
    val gsrWindow: List<GsrDataPoint>,  // Last 30 seconds
    val allSensorsReady: Boolean,
    val recordingActive: Boolean
)
```

---

## UI/UX Design Principles

### From IRCamera
1. **Minimize navigation depth** - Everything accessible in 2 taps
2. **Visual hierarchy** - Most important data largest (preview)
3. **Status visibility** - Always show if sensors are working
4. **Feedback on actions** - Loading states, success/error animations
5. **Consistent iconography** - Record, pause, stop, settings

### BuccanCS Specific
1. **Research-first** - Optimize for data quality over aesthetics
2. **Synchronization visibility** - Show when sensors are in sync
3. **Session-centric** - All artifacts grouped by research session
4. **Minimal distraction** - Clean UI during recording
5. **Desktop integration** - Sessions upload to desktop for analysis

---

## Implementation Priority Matrix

| Feature | Research Value | User Impact | Complexity | Priority |
|---------|---------------|-------------|------------|----------|
| **Unified Recording Screen** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | Medium | **P0** |
| **Real-time GSR Waveform** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | Medium | **P0** |
| **RGB Camera Controls** | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | Low | **P1** |
| **Session Gallery** | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | Medium | **P1** |
| **Synchronized Snapshot** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | Low | **P1** |
| **Measurement Tools** | ⭐⭐⭐ | ⭐⭐⭐ | Medium | **P2** |
| **Alignment Grid** | ⭐⭐⭐ | ⭐⭐ | Low | **P2** |
| **Quick Settings Panel** | ⭐⭐ | ⭐⭐⭐ | Low | **P3** |
| **Picture-in-Picture** | ⭐⭐ | ⭐⭐ | Medium | **P3** |

---

## Effort Estimation

### Phase A: Enhanced Individual Screens
- RGB controls: 2 days
- Shimmer waveform: 3 days
- Polish & testing: 1 day
**Total: 1 week**

### Phase B: Unified Recording Screen
- Layout & navigation: 2 days
- Session management: 3 days
- Synchronized recording: 2 days
- Testing: 1 day
**Total: 8 days (~1.5 weeks)**

### Phase C: Gallery & Review
- Gallery list UI: 1 day
- Session detail view: 2 days
- Export/share: 1 day
- Testing: 1 day
**Total: 5 days (1 week)**

### Phase D: Advanced Features
- Measurement tools: 3 days
- Alignment aids: 1 day
- Quick settings: 2 days
- Polish: 1 day
**Total: 7 days (~1.5 weeks)**

**Grand Total: 5 weeks** for complete multi-modal UI overhaul

---

## Success Metrics

### User Experience
- ✅ Single tap to start recording all sensors
- ✅ All sensor previews visible simultaneously
- ✅ Recording session setup time < 30 seconds
- ✅ Zero navigation required during active recording

### Data Quality
- ✅ Frame-level synchronization between IR and RGB
- ✅ Sub-10ms timestamp alignment across modalities
- ✅ Zero dropped frames during synchronized recording
- ✅ All artifacts grouped in single session directory

### System Performance
- ✅ 60 FPS UI with all three previews active
- ✅ < 100ms latency for preview updates
- ✅ < 5% CPU overhead for waveform rendering
- ✅ Smooth scrolling in gallery with thumbnails

---

## Risks & Mitigation

### Risk 1: Performance with Three Simultaneous Previews
**Impact**: High CPU/GPU usage, dropped frames  
**Probability**: Medium  
**Mitigation**: 
- Throttle non-primary views to lower FPS (e.g., 5 FPS for thumbnails)
- Use hardware acceleration for video rendering
- Profile early and optimize hot paths

### Risk 2: Synchronization Complexity
**Impact**: Frames from different sensors misaligned  
**Probability**: Medium  
**Mitigation**:
- Use shared timestamp source (already have time sync)
- Buffer frames with timestamps, align in post-processing
- Add sync validation in UI (show if drift > 50ms)

### Risk 3: Layout Complexity on Different Screen Sizes
**Impact**: UI cramped on phones, too sparse on tablets  
**Probability**: Low  
**Mitigation**:
- Responsive layout with breakpoints
- Tablet: side-by-side, Phone: stacked vertically
- Test on multiple device sizes early

### Risk 4: User Confusion with New Navigation
**Impact**: Users can't find features, abandon new UI  
**Probability**: Low  
**Mitigation**:
- Keep old screens accessible via devices list
- Add first-run tutorial
- Gradual rollout (A/B test if possible)

---

## Recommendation

**Start with Phase A + Phase B (3 weeks)**:

1. **Week 1**: Enhance RGB and Shimmer UIs to match Topdon quality
2. **Weeks 2-3**: Build unified recording screen with session management

This delivers **immediate research value** (synchronized multi-modal recording) while building foundation for future enhancements.

**Defer Phases C & D** until after Phase A+B validation with actual research use. Gallery and measurement tools can be added once core recording workflow proves successful.

---

## Next Steps

1. **Review this proposal** - Discuss with team/stakeholders
2. **Validate with research workflow** - Does unified screen match actual needs?
3. **Create UI mockups** - Detailed screens for Phase A & B
4. **Prioritize Phase A tasks** - Start with RGB controls or Shimmer waveform?
5. **Begin implementation** - Create feature branch for UI improvements

---

## Appendix: IRCamera Feature Comparison

| Feature | IRCamera | BuccanCS Current | Proposed |
|---------|----------|------------------|----------|
| Dual camera view | ✅ IR+RGB | ❌ Separate screens | ✅ Unified |
| Real-time waveform | ❌ N/A | ❌ No GSR preview | ✅ GSR chart |
| Synchronized capture | ✅ IR+RGB together | ❌ Manual | ✅ All 3 sensors |
| Palette control | ✅ Quick access | ✅ Settings screen | ✅ Quick panel |
| Measurement tools | ✅ Spot/area/line | ❌ None | ✅ Phase D |
| Zoom control | ✅ 1x-8x slider | ❌ None | ✅ Phase A |
| Gallery | ✅ In-app | ❌ External | ✅ Phase C |
| Session management | ❌ Individual files | ✅ Desktop app | ✅ Enhanced |
| Settings persistence | ✅ Per-device | ✅ DataStore | ✅ Keep |
| Export options | ✅ Share/PDF | ✅ Upload to desktop | ✅ Keep + enhance |

**BuccanCS Advantages**:
- ✅ GSR integration (IRCamera has no biosensors)
- ✅ Research session management
- ✅ Desktop orchestration
- ✅ Multi-device coordination
- ✅ Time synchronization across devices

**Areas to Improve** (from IRCamera):
- RGB camera controls
- Unified multi-sensor view
- Real-time data visualization
- In-app gallery

---

**End of Proposal**
