**Last Modified:** 2025-10-14 10:42 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Analysis

# Compose Migration Viability Analysis - Original TOPDON App

## Executive Summary

Converting the original-topdon-app to Jetpack Compose is **technically viable but high-risk** due to extensive custom
rendering and complex UI requirements. Migration effort is substantial (6-12 months) with significant technical
challenges in critical rendering paths.

## Codebase Metrics

### Scale

- **Total source files**: 835 (Java/Kotlin)
- **Layout files**: 267 XML layouts
- **Activities/Fragments**: 122 screen components
- **RecyclerView Adapters**: 30 list implementations
- **Custom Views**: 18+ custom view implementations
- **Resource files**: 64 drawable/XML resources

### Architecture Pattern

- ViewPager2 with Fragment navigation
- Traditional XML layouts with findViewById/synthetic imports
- No ViewBinding or DataBinding usage detected
- EventBus for inter-component communication
- Mix of Java and Kotlin code

## Critical Technical Challenges

### 1. Custom OpenGL Rendering (HIGH RISK)

**Component**: MyGLSurfaceView

```java
public class MyGLSurfaceView extends GLSurfaceView
```

**Challenge**: GLSurfaceView requires direct hardware access and frame-by-frame rendering control. Compose does not
support GLSurfaceView natively.

**Solutions**:

- Use AndroidView wrapper to embed GLSurfaceView in Compose
- Performance overhead from interop layer
- Loss of Compose animation/transition benefits
- Requires careful lifecycle management

**Risk Level**: HIGH - Core thermal camera functionality

### 2. Thermal Image Processing (HIGH RISK)

**Component**: HikSurfaceView (256x192 thermal imaging)

```kotlin
class HikSurfaceView : SurfaceView {
    private val irImageHelp = IRImageHelp()
    private var bitmap: Bitmap = Bitmap.createBitmap(192, 256, Bitmap.Config.ARGB_8888)
    private val sourceArgbArray = ByteArray(256 * 192 * 4)
}
```

**Challenge**: Real-time thermal image rendering with:

- Native bitmap manipulation
- Pseudocolour mapping (9+ colour schemes)
- Temperature overlay rendering
- Image rotation and zoom (2x amplification)
- Alarm region highlighting

**Solutions**:

- AndroidView wrapper for SurfaceView
- Custom Canvas-based Compose drawing (performance concern)
- Hybrid approach with interop overhead

**Risk Level**: HIGH - Critical path for thermal camera display

### 3. Heavy Canvas Rendering

**Component**: libir module

- 135+ instances of Canvas/onDraw usage
- Custom temperature visualisation overlays
- Real-time drawing on thermal frames

**Challenge**: Compose Canvas API is fundamentally different from View Canvas. Requires complete reimplementation of
drawing logic.

**Risk Level**: MEDIUM-HIGH - Core functionality with significant rework

### 4. MPAndroidChart Library Integration

**Components**:

- IRLogMPChartActivity
- IRMonitorChartActivity
- ChartLogView, ChartTrendView, ChartMonitorView

**Challenge**: MPAndroidChart is View-based library not designed for Compose.

**Solutions**:

- AndroidView wrapper (loses Compose benefits)
- Migrate to Vico charts library (significant rework)
- Custom Compose chart implementation (high effort)

**Risk Level**: MEDIUM - Non-trivial but has migration path

### 5. Third-Party UI Libraries

**Dependencies**:

- XPopup (custom popups)
- SmartRefreshLayout (pull-to-refresh)
- UCrop (image cropping)
- Immersionbar (status bar control)
- WheelView (picker wheels)

**Challenge**: Most libraries are View-based without Compose equivalents.

**Solutions**:

- AndroidView wrappers (loses benefits)
- Find/build Compose alternatives (high effort)

**Risk Level**: MEDIUM - Affects UX consistency

### 6. Complex Custom Views

**Examples**:

- RangeSeekBar (custom slider control)
- DefRangeSeekBar
- PowerConsumptionRankingsBatteryView
- ProgressBarView
- MyEasySwipeMenuLayout
- ZoomableDraggableView (thermal image manipulation)

**Challenge**: Each requires full reimplementation in Compose paradigm.

**Risk Level**: MEDIUM - Time-consuming but manageable

## Migration Complexity by Module

### App Module (Main)

- **Complexity**: MEDIUM
- **Screens**: MainActivity with bottom navigation, 3 main fragments
- **Effort**: 2-3 weeks
- **Issues**: ViewPager2 to HorizontalPager migration, synthetic imports

### Thermal-IR Module

- **Complexity**: VERY HIGH
- **Screens**: 10+ thermal camera activities
- **Effort**: 8-12 weeks
- **Issues**: HikSurfaceView, GLSurfaceView, custom rendering, real-time processing

### LibIR Module

- **Complexity**: VERY HIGH
- **Components**: 62 files with extensive Canvas rendering
- **Effort**: 6-8 weeks
- **Issues**: Complete Canvas reimplementation, temperature overlays

### LibMenu Module

- **Complexity**: MEDIUM
- **Components**: 23 files
- **Effort**: 2-3 weeks
- **Issues**: Custom menu system with drawables

### User Module

- **Complexity**: LOW-MEDIUM
- **Screens**: Profile, settings, preferences
- **Effort**: 1-2 weeks
- **Issues**: Standard forms and lists

### Gallery Module

- **Complexity**: MEDIUM
- **Components**: Image/video gallery with editing
- **Effort**: 3-4 weeks
- **Issues**: Image manipulation, video playback

## Technical Risk Matrix

| Component        | Risk     | Reason                      | Mitigation             |
|------------------|----------|-----------------------------|------------------------|
| GLSurfaceView    | CRITICAL | No native Compose support   | AndroidView wrapper    |
| HikSurfaceView   | CRITICAL | Real-time thermal rendering | AndroidView wrapper    |
| Canvas rendering | HIGH     | 135+ instances to rewrite   | Phased migration       |
| MPAndroidChart   | MEDIUM   | Third-party dependency      | Wrapper or alternative |
| Custom Views     | MEDIUM   | 18+ components              | Incremental rewrite    |
| Navigation       | LOW      | Standard patterns           | Compose Navigation     |
| Lists            | LOW      | RecyclerView to LazyColumn  | Direct mapping         |

## Migration Strategy Options

### Option 1: Full Migration (NOT RECOMMENDED)

**Approach**: Convert entire codebase to Compose

**Pros**:

- Clean architecture
- Full Compose benefits
- No interop overhead

**Cons**:

- 6-12 months effort
- High business risk
- Complex critical path changes
- Testing burden enormous

**Verdict**: Too risky for thermal camera application

### Option 2: Hybrid Approach (RECOMMENDED)

**Approach**: Compose for new UI, keep critical rendering in Views

**Phase 1** (2-3 months):

- Convert navigation screens (MainActivity, settings, profile)
- Migrate simple lists and forms
- Keep thermal rendering in AndroidView wrappers

**Phase 2** (3-4 months):

- Convert gallery UI while keeping image processing
- Migrate chart screens with wrapper approach
- Convert custom controls selectively

**Phase 3** (Future):

- Evaluate custom Canvas reimplementation
- Consider native Compose rendering if performance acceptable

**Pros**:

- Gradual risk mitigation
- Business continuity maintained
- Critical paths untouched initially
- Allows learning and adjustment

**Cons**:

- Mixed codebase complexity
- AndroidView wrapper overhead
- Longer total timeline
- Potential inconsistencies

**Verdict**: Pragmatic approach balancing risk and modernisation

### Option 3: Selective Migration (CONSERVATIVE)

**Approach**: Only migrate non-critical screens

**Scope**:

- Settings, profile, about screens
- List-based screens (device list, version history)
- Simple forms and dialogs

**Keep in Views**:

- All thermal camera screens
- Image/video gallery
- Chart displays
- Custom measurement tools

**Pros**:

- Minimal risk
- Quick wins
- Proven technology for critical features
- Allows Compose evaluation

**Cons**:

- Limited benefits
- Codebase remains mixed long-term
- May not justify migration effort

**Verdict**: Safe but limited value proposition

## Performance Considerations

### AndroidView Wrapper Overhead

Using AndroidView to embed Views in Compose adds:

- Layout measurement overhead
- Composition/recomposition latency
- Memory overhead from dual tree structures
- Potential frame drops in animations

**Impact on thermal camera**: Acceptable for 30fps thermal display but requires testing.

### Canvas Rendering Performance

Compose Canvas is declarative vs imperative View Canvas:

- Recomposition overhead on every frame
- Less direct control over rendering
- Different optimisation patterns required

**Impact**: Likely performance degradation for real-time thermal overlay rendering.

## Dependency Migration

| Current Library    | Compose Alternative        | Migration Effort |
|--------------------|----------------------------|------------------|
| ViewPager2         | HorizontalPager            | LOW              |
| RecyclerView       | LazyColumn/Row             | LOW              |
| SmartRefreshLayout | pullrefresh modifier       | LOW              |
| XPopup             | Compose Dialog/BottomSheet | MEDIUM           |
| MPAndroidChart     | Vico / Custom              | HIGH             |
| UCrop              | AndroidView wrapper        | LOW (wrapper)    |
| GLSurfaceView      | AndroidView wrapper        | MEDIUM           |
| Custom SurfaceView | AndroidView wrapper        | MEDIUM           |

## Testing Implications

### Regression Testing

- All thermal camera functions require hardware testing
- Temperature accuracy validation critical
- Image processing algorithm verification
- BLE connectivity testing with TC001 device

### Effort Estimate

- Unit test migration: 4-6 weeks
- Integration test updates: 6-8 weeks
- Manual QA cycles: 3-4 iterations
- Hardware validation: 2-3 weeks per phase

## Recommendation

**Verdict**: PROCEED WITH CAUTION using **Hybrid Approach (Option 2)**

### Implementation Plan

**Phase 1: Foundation (Months 1-3)**

1. Set up Compose infrastructure
2. Migrate MainActivity navigation to Compose
3. Convert settings and profile screens
4. Implement Compose theme system
5. Create AndroidView wrappers for thermal displays

**Phase 2: Non-Critical Features (Months 4-6)**

1. Convert device list and connection screens
2. Migrate gallery list UI (keep processing in Views)
3. Implement Compose dialogs and popups
4. Convert chart screens with wrappers

**Phase 3: Optimisation (Months 7-9)**

1. Performance testing and tuning
2. Evaluate selective custom Canvas rewrites
3. Refine AndroidView wrapper implementations
4. Address identified issues

**Phase 4: Advanced Features (Future)**

1. Consider native Compose thermal rendering if viable
2. Custom chart implementations
3. Advanced animation and transitions

### Success Criteria

- No performance degradation in thermal camera display (30fps maintained)
- Temperature accuracy unchanged
- BLE connectivity stability maintained
- User experience consistency preserved
- Build time not significantly increased

### Risk Mitigation

1. **Feature flags**: Deploy behind flags for gradual rollout
2. **A/B testing**: Compare old vs new implementations
3. **Hardware testing**: Extensive validation with TC001 device
4. **Rollback plan**: Maintain View-based code paths
5. **Performance monitoring**: Thermal camera frame rates and latency

## Conclusion

Migration to Compose is viable but requires careful planning and phased execution. The critical thermal camera rendering
components should remain in Views wrapped by AndroidView until proven Compose implementations match performance and
accuracy requirements. This approach balances modernisation benefits with business risk management.

Total estimated effort for meaningful migration: **6-9 months** with dedicated team.

Priority should be given to screens that benefit most from Compose (navigation, lists, forms) while preserving proven
thermal imaging technology in existing implementation.
