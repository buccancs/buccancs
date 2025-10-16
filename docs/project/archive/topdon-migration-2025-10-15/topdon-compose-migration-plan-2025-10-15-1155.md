**Last Modified:** 2025-10-15 11:55 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Migration Plan

# TOPDON TC001 App Compose Material UI Migration Plan

## Executive Summary

Migration plan to modernise the original-topdon-app from XML layouts with DataBinding to Jetpack Compose with Material 3
UI. The app contains 190 XML layouts across 11 modules with complex thermal camera integration.

## Quick Reference

- **Timeline:** 15 weeks (can be compressed to 8-10 weeks with 4-person team)
- **Modules:** 11 modules to migrate
- **Screens:** 12 activities, 190 XML layouts
- **Critical Path:** Thermal imaging core (Week 7-9)
- **Risk Level:** Medium-High (thermal camera integration)

## Current State Assessment

### Architecture Overview

- **Base:** Traditional Android View system with DataBinding
- **Navigation:** ViewPager2, ARouter for routing
- **UI Framework:** XML layouts, Kotlin synthetics (deprecated)
- **Modules:** 11 modules (app + 5 lib* + 5 component)
- **Device Integration:** TC001 thermal camera via USB and WebSocket

### Module Structure

```
app/                    - Main application, splash, device selection
libapp/                 - App-level shared components  
libcom/                 - Common utilities
libir/                  - Infrared/thermal core logic
libmenu/                - Menu system
libui/                  - UI components library

component/
  ├── pseudo/           - Pseudo colour processing
  ├── thermal-ir/       - IR thermal imaging (Night mode)
  ├── thermal-lite/     - Lite thermal mode
  ├── transfer/         - Data transfer
  └── user/             - User profile and settings
```

### Key Statistics

- **App Module:** 12 activities, 2 fragments, 3 custom views
- **XML Layouts:** 190 total (excluding build outputs)
- **ViewModels:** Present but limited usage
- **Current Dependencies:** ViewBinding disabled, DataBinding enabled

### Technical Debt

1. Kotlin synthetics usage (deprecated)
2. ARouter for navigation (can be replaced with Compose Navigation)
3. Mixed Java/Kotlin code
4. Fragment-based architecture in ViewPager2
5. No consistent MVVM implementation

## Migration Phases Overview

### Phase 1: Foundation (Week 1-2)

- Compose setup across all modules
- Design system and Material 3 theme
- Base components library
- Architecture patterns

### Phase 2: Simple Screens (Week 3-4)

- 7 information screens migration
- Dialogs and popups
- Navigation setup

### Phase 3: Main Navigation (Week 5-6)

- MainActivity refactor
- Bottom navigation
- Gallery, Home, Profile screens

### Phase 4: Device Management (Week 7-8)

- Device selection
- Connection handling
- Permission flows

### Phase 5: Thermal Core (Week 9-11) - CRITICAL

- Thermal camera preview
- Three thermal modes
- Image processing
- Capture controls

### Phase 6: Library Modules (Week 12-13)

- libui, libmenu completion
- Component modules
- Integration

### Phase 7: Polish (Week 14-15)

- Performance optimisation
- Accessibility
- Testing
- Documentation

## Detailed Migration Plan

See TOPDON_PARALLEL_MIGRATION_WORKSTREAMS document for parallelisation strategy.
See TOPDON_DETAILED_TASK_ASSIGNMENTS document for day-by-day tasks.

## Key Technical Decisions

### 1. Navigation: Compose Navigation vs ARouter

**Decision:** Migrate to Compose Navigation
**Rationale:** Native integration, type-safe, better lifecycle handling

### 2. State Management: MVVM with StateFlow

**Pattern:**

```kotlin
class ThermalViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ThermalUiState())
    val uiState: StateFlow<ThermalUiState> = _uiState.asStateFlow()
}
```

### 3. Custom Views: Wrap vs Rewrite

**OpenGL Views:** Wrap in AndroidView (preserve working code)
**Other Views:** Rewrite as pure Compose

### 4. Material Design 3

**Decision:** Full MD3 adoption
**Benefits:** Modern UI, dynamic theming, better accessibility

## Risk Mitigation

### High-Risk Areas

1. **Thermal Camera Preview:** Wrap existing OpenGL, extensive hardware testing
2. **Device Communication:** Keep SDK layer, monitor stability
3. **Performance:** Profile early, background processing for thermal
4. **UX Changes:** Maintain familiarity, beta testing, feedback

### Rollout Strategy

**Recommended:** Hybrid approach (Compose in Fragments) → Feature-by-feature migration

### Rollback Plan

- Feature flags per screen
- Keep old Activities for 2 releases
- Tag releases clearly
- Monitoring and crash alerts

## Testing Strategy

### Unit Tests

- ViewModel business logic
- State transformations
- Use cases

### Compose UI Tests

```kotlin
@Test
fun thermalScreen_connectDevice_showsPreview() {
    composeTestRule.setContent { ThermalNightScreen() }
    composeTestRule.onNodeWithTag("connect_button").performClick()
    composeTestRule.onNodeWithTag("thermal_preview").assertIsDisplayed()
}
```

### Hardware Testing

- All TC001 variants (Plus, Lite, Night)
- Multiple Android versions
- USB OTG scenarios
- WebSocket connections

## Success Criteria

### Functional

- All features working
- Thermal camera functional
- Device connection stable
- Settings preserved

### Non-Functional

- No performance regression
- 60fps UI rendering
- Memory within limits
- Battery comparable or better

### Code Quality

- No XML layouts
- Consistent patterns
- 80%+ test coverage
- Complete documentation

## Resource Requirements

### Team

- 2-3 Android developers (Compose experience)
- 1 QA engineer
- TC001 hardware access
- Design support

### Timeline

- **Sequential:** 15 weeks
- **Parallel (4 people):** 8-10 weeks

### Hardware

- TC001, TC001 Plus, TC001 Lite devices
- Various Android versions (API 21-34)
- USB OTG cables
- Network for WebSocket testing

## Module Priority Order

1. **app/** - Main application
2. **libui/** - UI components
3. **component/user/** - User profile
4. **component/thermal-ir/** - Thermal camera
5. **component/thermal-lite/** - Lite thermal
6. **component/transfer/** - Data transfer
7. **libmenu/** - Menu system
8. **libir/** - IR core
9. **libcom/** - Common utilities
10. **libapp/** - App utilities
11. **component/pseudo/** - Colour processing

## Performance Benchmarks

Establish baselines before migration:

```
Metric                        Target      Baseline    Post-Migration
─────────────────────────────────────────────────────────────────────
App Launch (cold)            < 2s         TBD         TBD
Thermal Preview FPS          ≥ 30fps      TBD         TBD
Camera Connection Time       < 3s         TBD         TBD
Gallery Load (100 images)    < 1s         TBD         TBD
Memory Usage (idle)          < 150MB      TBD         TBD
Memory Usage (thermal)       < 300MB      TBD         TBD
Frame Drop Rate              < 1%         TBD         TBD
```

## Next Steps

1. Review and approve this migration plan
2. Assign team members and roles
3. Set up development environment
4. Create feature branches
5. Begin Phase 1: Foundation setup

## Related Documents

- `TOPDON_PARALLEL_MIGRATION_WORKSTREAMS_2025-10-15_1202.md` - Parallelisation strategy
- `TOPDON_DETAILED_TASK_ASSIGNMENTS_2025-10-15_1253.md` - Day-by-day task breakdown
- `docs/architecture/COMPOSE_DESIGN_TOKENS.md` - To be created
- `docs/architecture/THERMAL_RENDERING.md` - To be created

## Conclusion

This migration from XML to Compose Material UI requires 15 weeks with careful planning. The phased approach mitigates
risk while delivering value incrementally. Critical success factors include maintaining thermal camera functionality,
preserving performance, and ensuring smooth device communication.

Key recommendations:

1. Use hybrid approach initially (Compose in Fragments)
2. Wrap complex OpenGL views rather than rewriting
3. Implement feature flags for gradual rollout
4. Establish performance baselines before starting
5. Invest in Compose training for team
6. Maintain focus on device integration stability

The migration enables future development with modern tooling, improved maintainability, and better alignment with
Android best practices.
