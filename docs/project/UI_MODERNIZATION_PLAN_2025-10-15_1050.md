**Last Modified:** 2025-10-15 10:50 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Implementation Plan

# UI Modernization Plan

## Overview
Complete redesign of both Android and Desktop UIs following Material 3 guidelines and modern Compose best practices.

## Current Issues
1. Inconsistent spacing and padding
2. Poor information architecture
3. Navigation not following Material 3 patterns
4. Desktop UI outdated
5. Missing proper state management patterns
6. Inconsistent component hierarchy

## Material 3 Android Implementation

### Navigation Structure
Replace current navigation with Material 3 Navigation components:

**Primary Navigation (Bottom Nav Bar):**
- Live Session (primary action)
- Sessions Library
- Devices (new - device management)
- Settings

**Current MainScreen becomes Device Management screen**

### Screen Reorganization

#### 1. Live Session Screen (Primary)
- **Purpose:** Active recording and monitoring
- **Content:**
  - Session info card (collapsed/expandable)
  - Recording controls (prominent FAB)
  - Device stream status grid
  - Real-time sync status
  - Event log (bottom sheet)
- **Navigation:** Root destination

#### 2. Devices Screen (New)
- **Purpose:** Device configuration and connection
- **Content:**
  - Connected devices list
  - Available devices (scan results)
  - Shimmer configuration
  - TOPDON configuration
  - Calibration wizard (moved here)
- **Navigation:** Tabs for different device types

#### 3. Sessions Library Screen
- **Purpose:** Browse recorded sessions
- **Keep existing:** List and detail views
- **Improvements:**
  - Add search/filter
  - Better card layout
  - Thumbnail previews

#### 4. Settings Screen
- **Purpose:** App configuration
- **Content:**
  - Orchestrator connection
  - Simulation mode
  - App preferences
  - About

### Component Architecture

**Material 3 Components to use:**
- NavigationBar (bottom nav)
- TopAppBar (with scroll behavior)
- Card (elevated, filled, outlined variants)
- FloatingActionButton (for primary actions)
- ModalBottomSheet (for device selection, event logs)
- Tabs (for device types)
- SegmentedButton (for view modes)

### Design Tokens
```kotlin
Spacing:
  - Extra Small: 4.dp
  - Small: 8.dp  
  - Medium: 16.dp (primary)
  - Large: 24.dp
  - Extra Large: 32.dp

Corner Radius:
  - Small: 8.dp
  - Medium: 12.dp
  - Large: 16.dp
  - Extra Large: 28.dp
```

## Desktop UI Implementation

### Window Structure
```
+------------------------------------------+
| Menu Bar (File, Edit, View, Help)       |
+------------------------------------------+
| Tool Bar (Quick actions)                 |
+------------------------------------------+
| +--------+  +--------------------------+ |
| | Nav    |  | Main Content Area        | |
| | Rail   |  |                          | |
| |        |  |                          | |
| | Home   |  |                          | |
| | Device |  |                          | |
| | Sessn  |  |                          | |
| | Logs   |  |                          | |
| | Setg   |  |                          | |
| +--------+  +--------------------------+ |
+------------------------------------------+
| Status Bar (connection, sync status)     |
+------------------------------------------+
```

### Desktop Screens

#### 1. Dashboard (Home)
- System status overview
- Active sessions
- Connected devices summary
- Recent activity

#### 2. Device Management
- Device registry
- Connection management
- Configuration panels
- Live preview (thermal/RGB)

#### 3. Session Management
- Active sessions list
- Session controls
- Data transfer status
- Storage management

#### 4. Logs & Monitoring
- Real-time logs
- Performance metrics
- Error tracking

### Desktop Design System
- Use Compose Desktop Material 3
- Responsive layout for different window sizes
- Proper keyboard navigation
- Context menus

## Implementation Order

### Phase 1: Android Foundation (Priority 1)
1. Create new navigation structure
2. Implement bottom navigation
3. Create base screen templates
4. Update theme system

### Phase 2: Android Screens (Priority 1)
1. Refactor Live Session screen
2. Create new Devices screen (move device mgmt)
3. Update Sessions Library
4. Update Settings screen

### Phase 3: Desktop Foundation (Priority 2)
1. Create navigation rail
2. Implement menu bar
3. Create base layouts
4. Update desktop theme

### Phase 4: Desktop Screens (Priority 2)
1. Dashboard screen
2. Device management
3. Session management
4. Logs & monitoring

### Phase 5: Polish (Priority 3)
1. Animations and transitions
2. Loading states
3. Error states
4. Empty states

## File Changes Required

### New Files (Android)
- `ui/navigation/BottomNavigationBar.kt`
- `ui/devices/DevicesScreen.kt`
- `ui/devices/DevicesViewModel.kt`
- `ui/components/DeviceCard.kt`
- `ui/components/SessionCard.kt`
- `ui/components/StatusChip.kt`

### Modified Files (Android)
- `ui/MainActivity.kt` - Add navigation
- `ui/navigation/AppNavHost.kt` - Update routes
- `ui/session/LiveSessionScreen.kt` - Redesign
- `ui/library/SessionListScreen.kt` - Improve
- `ui/settings/SettingsScreen.kt` - Reorganize
- `ui/theme/AppTheme.kt` - Material 3 tokens

### New Files (Desktop)
- `desktop/ui/navigation/NavigationRail.kt`
- `desktop/ui/dashboard/DashboardScreen.kt`
- `desktop/ui/devices/DeviceManagementScreen.kt`
- `desktop/ui/sessions/SessionManagementScreen.kt`
- `desktop/ui/logs/LogsScreen.kt`
- `desktop/ui/components/` (various)

### Modified Files (Desktop)
- `desktop/ui/DesktopApp.kt` - Complete redesign
- `desktop/viewmodel/AppViewModel.kt` - Update state
- `desktop/ui/state/AppUiState.kt` - New structure

## Backend Connectivity Verification

### Verify ViewModels properly inject:
- SensorRepository
- RecordingService
- OrchestratorClient
- DeviceManagement UseCase
- Session UseCase

### Verify State Flows:
- Device connection state
- Recording state
- Stream status
- Sync status
- Upload status

### Verify Actions:
- Start/Stop recording
- Connect/Disconnect devices
- Scan for devices
- Configure devices
- Upload sessions

## Testing Strategy
1. Test navigation flows
2. Test state management
3. Test backend integration
4. Test error handling
5. Manual UI testing on device

## Time Estimate
- Phase 1: 2-3 hours
- Phase 2: 4-5 hours
- Phase 3: 2-3 hours
- Phase 4: 3-4 hours
- Phase 5: 2-3 hours
**Total: 13-18 hours of work**

## Notes
- This is a significant refactor
- Maintain backward compatibility where possible
- Keep existing ViewModels initially, refactor later if needed
- Test incrementally after each phase
- Document breaking changes
