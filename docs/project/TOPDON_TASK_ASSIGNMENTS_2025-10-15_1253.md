**Last Modified:** 2025-10-15 12:53 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Detailed Task Assignments

# TOPDON Compose Migration - Day-by-Day Task Breakdown

## Team Structure

**Team Lead (TL):** Senior Android Developer - Architecture oversight  
**Developer A (Dev-A):** Senior - UI/Design System/Infrastructure  
**Developer B (Dev-B):** Mid-Senior - Screen Migration  
**Developer C (Dev-C):** Senior - Thermal/OpenGL/Performance  
**QA Engineer (QA):** Test automation and hardware testing

## Week 1: Foundation Setup (Days 1-5)

### Day 1 (Monday) - Project Kickoff

**TL:**
- Create feature branches
- Set up project board
- Define code review process
- Schedule daily standups
- Set up communication channels
- Document hardware schedule

**Dev-A:**
- Analyse existing colours and typography
- Create theme/Color.kt
- Create theme/Type.kt
- Document design decisions

**Dev-B:**
- Update build.gradle for all modules
- Add Compose dependencies
- Create ComposeDependencies.kt
- Test module builds

**Dev-C:**
- Study MyGLSurfaceView.java
- Study thermal activity implementations
- Document thermal rendering pipeline
- List dependencies

**QA:**
- Review current test coverage
- Add Compose test dependencies
- Plan test module structure
- Document test strategy
- Schedule hardware testing

### Day 2 (Tuesday) - Core Architecture

**TL:**
- Review design system
- Review build configuration
- Review thermal analysis
- Approve architecture decisions

**Dev-A:**
- Create Theme.kt with Material 3
- Define light/dark colour schemes
- Create Spacing.kt
- Create Shape.kt
- Add preview functions

**Dev-B:**
- Create BaseViewModel.kt
- Create UiState sealed class
- Create MainViewModel skeleton
- Create GalleryViewModel skeleton
- Create ProfileViewModel skeleton

**Dev-C:**
- Create ThermalPreviewState.kt
- Design ThermalPreview interface
- Create ThermalPreview.kt stub
- Document AndroidView strategy
- Plan lifecycle handling

**QA:**
- Add test dependencies
- Create ComposeTestRules.kt
- Create MockDeviceManager.kt
- Create test fixtures
- Set up screenshot testing

### Day 3 (Wednesday) - Base Components

**TL:**
- Review ViewModel architecture
- Review thermal wrapper design
- Resolve blockers
- Plan Week 2

**Dev-A:**
- Create TopdonButton.kt
- Create TopdonOutlinedButton.kt
- Create TopdonTextButton.kt
- Create TopdonIconButton.kt
- Add previews

**Dev-B:**
- Map ARouter routes
- Create NavigationRoutes.kt
- Create NavigationGraph.kt skeleton
- Document navigation patterns
- Plan deep linking

**Dev-C:**
- Create DeviceRepository.kt
- Create DeviceRepositoryImpl.kt
- Abstract USB communication
- Create DeviceConnectionState
- Add Hilt bindings

**QA:**
- Create MockDeviceRepository
- Create MockThermalCamera
- Create test thermal images
- Document test utilities

### Day 4 (Thursday) - Components & Repositories

**TL:**
- Review components
- Review navigation
- Review device repository
- Merge to main

**Dev-A:**
- Create TopdonTextField.kt
- Create TopdonOutlinedTextField.kt
- Add validation support
- Add error states
- Create search variant

**Dev-B:**
- Create GalleryRepository.kt
- Create SettingsRepository.kt
- Create UserRepository.kt
- Define interfaces
- Add Hilt module

**Dev-C:**
- Analyse WebSocketProxy
- Create WebSocketManager.kt
- Create StateFlow for connection
- Handle TS004/TC007 devices
- Create connection UI model

**QA:**
- Create sample Compose screen
- Write first UI test
- Validate framework
- Document patterns
- Set up CI pipeline

### Day 5 (Friday) - Week 1 Wrap-up

**TL:**
- Team retrospective
- Review deliverables
- Prepare Week 2 tasks
- Risk assessment

**Dev-A:**
- Create TopdonDialog.kt
- Create TopdonAlertDialog.kt
- Migrate TipDialog pattern
- Add dialog variants
- Add previews

**Dev-B:**
- Implement MainViewModel
- Add permission state
- Add connection state
- Add tab selection state
- Write unit tests

**Dev-C:**
- Create ThermalPreview with AndroidView
- Wrap MyGLSurfaceView
- Implement lifecycle handling
- Test rendering
- Document issues

**QA:**
- Set up TC001 testing
- Verify device communication
- Document test procedures
- Create checklist
- Plan Week 2 testing

## Week 2: Components & First Screens (Days 6-10)

### Day 6 (Monday) - First Screen Migration

**Dev-A:**
- Create CircularProgress component
- Create LinearProgress component
- Create loading overlay
- Create shimmer effect

**Dev-B:**
- Create SplashScreen.kt
- Create SplashViewModel.kt
- Migrate splash logic
- Add animation
- Update Activity to use Compose

**Dev-C:**
- Add Accompanist Permissions
- Create CameraPermissionHandler.kt
- Create StoragePermissionHandler.kt
- Create rationale dialogs

**QA:**
- Write UI test for SplashScreen
- Test navigation
- Test animation
- Manual device testing

### Day 7-10 - Continue Pattern

**Dev-A:** UI components (navigation bars, app bars, lists, cards)
**Dev-B:** Screen migrations (Clause, Policy, Version, Help, WebView, PDF)
**Dev-C:** Device integration (thermal frame callbacks, settings state)
**QA:** Testing all migrated components and screens

## Week 3-4: Navigation & Gallery (Days 11-20)

Focus on main navigation implementation, gallery screen, profile screen, and device selection.

## Week 5-6: Device Management (Days 21-30)

Settings screens, gallery selection mode, device connection manager, integration testing.

## Week 7-9: Thermal Core - CRITICAL PATH (Days 31-45)

**Dev-C leads thermal implementation:**
- Days 31-35: ThermalNightScreen core
- Days 36-40: ThermalPlusScreen
- Days 41-45: ThermalLiteScreen

**Dev-B supports:**
- Image editor implementation
- Thermal UI polish

**Dev-A supports:**
- Thermal UI components
- Settings controls

**QA:**
- Continuous hardware testing
- Performance monitoring
- Temperature validation

## Week 10-11: Library Modules (Days 46-55)

**Dev-A:** libui complete migration
**Dev-B:** component modules (transfer, user, libmenu)
**Dev-C:** technical components (pseudo, libir)
**QA:** module testing, integration

## Week 12-13: Polish & Release (Days 56-65)

**Dev-A:** Accessibility audit
**Dev-B:** Documentation
**Dev-C:** Performance optimisation
**QA:** Final testing, bug bash, release prep

## Daily Standup Template

Each member answers:
1. What did I complete yesterday?
2. What will I work on today?
3. Any blockers or dependencies?
4. Do I need hardware/resources?

## Code Review Checklist

- Follows Compose best practices
- No unnecessary recompositions
- Proper state management
- Tests included
- Preview functions included
- Documentation updated
- No performance regressions
- Accessibility considered
- Dark theme tested

## Hardware Schedule

**Dev-C:** Primary (thermal development)
**Dev-B:** 2 hours/day (screen testing)
**QA:** 3 hours/day (QA testing)
**Dev-A:** On-demand (component testing)

## Communication Protocols

**Blockers:** Post immediately in #compose-blockers with description, impact, solutions
**Decisions:** Document in #compose-migration with rationale and tag #decision
**Questions:** Technical in channel, architecture with TL

## Success Metrics (Tracked Weekly)

- Screens migrated (target vs actual)
- Components completed
- Test coverage (target: 80%)
- Performance benchmarks met
- No P0/P1 bugs
- Code review turnaround < 24 hours

## Conclusion

This breakdown provides daily assignments ensuring clear ownership, parallelism, coordination, flexibility, and accountability. The critical path is Week 7-9 with Dev-C leading thermal core while others support and work on parallel features.

For complete details of all 65 days, see the full document or contact the team lead.
