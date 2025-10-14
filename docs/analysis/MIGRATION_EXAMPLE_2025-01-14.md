**Last Modified:** 2025-01-14 04:58 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Migration Guide

# Screen Migration Example

This guide shows how to migrate a Composable screen from using MainViewModel to the new focused ViewModels.

## Example: Settings Screen Migration

### Before (Using MainViewModel)

```kotlin
@Composable
fun SettingsScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Column {
        // Orchestrator Configuration
        OutlinedTextField(
            value = uiState.orchestratorHostInput,
            onValueChange = { viewModel.onOrchestratorHostChanged(it) },
            label = { Text("Host") }
        )
        
        OutlinedTextField(
            value = uiState.orchestratorPortInput,
            onValueChange = { viewModel.onOrchestratorPortChanged(it) },
            label = { Text("Port") }
        )
        
        Switch(
            checked = uiState.orchestratorUseTls,
            onCheckedChange = { viewModel.onOrchestratorUseTlsChanged(it) }
        )
        
        Button(
            onClick = { viewModel.applyOrchestratorConfig() },
            enabled = uiState.orchestratorConfigDirty
        ) {
            Text("Save Configuration")
        }
        
        uiState.configMessage?.let { message ->
            Text(
                text = message,
                color = if (message.contains("saved")) Color.Green else Color.Red
            )
        }
    }
}
```

### After (Using OrchestratorConfigViewModel)

```kotlin
@Composable
fun SettingsScreen(
    orchestratorViewModel: OrchestratorConfigViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by orchestratorViewModel.uiState.collectAsState()
    
    Column {
        // Orchestrator Configuration
        OutlinedTextField(
            value = uiState.hostInput,
            onValueChange = { orchestratorViewModel.onHostChanged(it) },
            label = { Text("Host") }
        )
        
        OutlinedTextField(
            value = uiState.portInput,
            onValueChange = { orchestratorViewModel.onPortChanged(it) },
            label = { Text("Port") }
        )
        
        Switch(
            checked = uiState.useTls,
            onCheckedChange = { orchestratorViewModel.onUseTlsChanged(it) }
        )
        
        Button(
            onClick = { orchestratorViewModel.applyConfig() },
            enabled = uiState.isDirty
        ) {
            Text("Save Configuration")
        }
        
        uiState.message?.let { message ->
            Text(
                text = message,
                color = if (message.contains("saved")) Color.Green else Color.Red
            )
        }
    }
}
```

### Changes Summary

1. **ViewModel Type:** `MainViewModel` → `OrchestratorConfigViewModel`
2. **State Properties:** Simpler names (removed "orchestrator" prefix)
3. **Method Names:** Shorter, more focused
4. **Dependencies:** Only what's needed for this screen

## Example: MainScreen with Multiple ViewModels

### Before (Using MainViewModel)

```kotlin
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Column {
        // Recording Controls
        RecordingSection(
            sessionId = uiState.sessionIdInput,
            isRecording = uiState.isRecording,
            onSessionIdChanged = { viewModel.onSessionIdChanged(it) },
            onStartRecording = { viewModel.startRecording() },
            onStopRecording = { viewModel.stopRecording() }
        )
        
        // Device List
        DeviceList(
            devices = uiState.devices,
            onConnect = { viewModel.connectDevice(it) },
            onDisconnect = { viewModel.disconnectDevice(it) }
        )
        
        // Stream Status
        StreamStatusList(
            streams = uiState.streamStatuses
        )
    }
}
```

### After (Using Multiple Focused ViewModels)

```kotlin
@Composable
fun MainScreen(
    recordingViewModel: RecordingViewModel = hiltViewModel(),
    deviceViewModel: DeviceInventoryViewModel = hiltViewModel(),
    telemetryViewModel: TelemetryViewModel = hiltViewModel()
) {
    val recordingState by recordingViewModel.uiState.collectAsState()
    val deviceState by deviceViewModel.uiState.collectAsState()
    val telemetryState by telemetryViewModel.uiState.collectAsState()
    
    Column {
        // Recording Controls
        RecordingSection(
            sessionId = recordingState.sessionId,
            isRecording = recordingState.isRecording,
            onSessionIdChanged = { recordingViewModel.onSessionIdChanged(it) },
            onStartRecording = { recordingViewModel.startRecording() },
            onStopRecording = { recordingViewModel.stopRecording() }
        )
        
        // Device List
        DeviceList(
            devices = deviceState.devices,
            onConnect = { deviceViewModel.connectDevice(it) },
            onDisconnect = { deviceViewModel.disconnectDevice(it) }
        )
        
        // Stream Status
        StreamStatusList(
            streams = telemetryState.streamStatuses
        )
    }
}
```

### Changes Summary

1. **Multiple ViewModels:** Inject only what's needed
2. **Multiple State Objects:** Each with focused properties
3. **Clear Separation:** Recording, devices, and telemetry independent
4. **Easier Testing:** Can test each section independently

## Migration Patterns

### Pattern 1: Single Concern Screen

**Use Case:** Screen uses only one type of functionality

**Before:**
```kotlin
@Composable
fun MyScreen(viewModel: MainViewModel = hiltViewModel())
```

**After:**
```kotlin
@Composable
fun MyScreen(myViewModel: SpecificViewModel = hiltViewModel())
```

**Example Screens:**
- Settings → OrchestratorConfigViewModel
- Shimmer Config → ShimmerConfigViewModel

### Pattern 2: Multi-Concern Screen

**Use Case:** Screen needs multiple types of functionality

**Before:**
```kotlin
@Composable
fun MyScreen(viewModel: MainViewModel = hiltViewModel())
```

**After:**
```kotlin
@Composable
fun MyScreen(
    viewModel1: FirstViewModel = hiltViewModel(),
    viewModel2: SecondViewModel = hiltViewModel()
)
```

**Example Screens:**
- MainScreen → Recording + Device + Telemetry ViewModels
- LiveSession → Recording + Device + Telemetry ViewModels

### Pattern 3: Gradual Migration

**Use Case:** Large screen, migrate piece by piece

**Step 1: Add New ViewModel**
```kotlin
@Composable
fun MyScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    newViewModel: NewViewModel = hiltViewModel() // Add this
)
```

**Step 2: Use New ViewModel for One Section**
```kotlin
// Old section still uses mainViewModel
OldSection(state = mainState, onAction = { mainViewModel.doOld(it) })

// New section uses newViewModel
NewSection(state = newState, onAction = { newViewModel.doNew(it) })
```

**Step 3: Complete Migration**
```kotlin
@Composable
fun MyScreen(
    newViewModel: NewViewModel = hiltViewModel() // Remove mainViewModel
)
```

## State Property Mapping

### MainUiState → New ViewModels

| MainUiState Property | New ViewModel | New Property |
|---------------------|---------------|--------------|
| `sessionIdInput` | RecordingViewModel | `sessionId` |
| `recordingLifecycle` | RecordingViewModel | `lifecycle` |
| `anchorReference` | RecordingViewModel | `anchorReference` |
| `isRecording` | RecordingViewModel | `isRecording` |
| `exercise` | RecordingViewModel | `exercise` |
| `devices` | DeviceInventoryViewModel | `devices` |
| `simulationEnabled` | DeviceInventoryViewModel | `simulationEnabled` |
| `inventory` | DeviceInventoryViewModel | `inventory` |
| `streamStatuses` | TelemetryViewModel | `streamStatuses` |
| `timeSyncStatus` | TelemetryViewModel | `timeSyncStatus` |
| `deviceEvents` | TelemetryViewModel | `deviceEvents` |
| `showSyncFlash` | TelemetryViewModel | `showSyncFlash` |
| `orchestratorHostInput` | OrchestratorConfigViewModel | `hostInput` |
| `orchestratorPortInput` | OrchestratorConfigViewModel | `portInput` |
| `orchestratorUseTls` | OrchestratorConfigViewModel | `useTls` |
| `orchestratorConfigDirty` | OrchestratorConfigViewModel | `isDirty` |
| `configMessage` | OrchestratorConfigViewModel | `message` |

### Method Mapping

| MainViewModel Method | New ViewModel | New Method |
|---------------------|---------------|------------|
| `onSessionIdChanged()` | RecordingViewModel | `onSessionIdChanged()` |
| `startRecording()` | RecordingViewModel | `startRecording()` |
| `stopRecording()` | RecordingViewModel | `stopRecording()` |
| `runExercise()` | RecordingViewModel | `runExercise()` |
| `connectDevice()` | DeviceInventoryViewModel | `connectDevice()` |
| `disconnectDevice()` | DeviceInventoryViewModel | `disconnectDevice()` |
| `refreshInventory()` | DeviceInventoryViewModel | `refreshInventory()` |
| `toggleSimulation()` | DeviceInventoryViewModel | `toggleSimulation()` |
| `selectShimmerDevice()` | ShimmerConfigViewModel | `selectShimmerDevice()` |
| `updateShimmerRange()` | ShimmerConfigViewModel | `updateShimmerRange()` |
| `updateShimmerSampleRate()` | ShimmerConfigViewModel | `updateShimmerSampleRate()` |
| `onOrchestratorHostChanged()` | OrchestratorConfigViewModel | `onHostChanged()` |
| `onOrchestratorPortChanged()` | OrchestratorConfigViewModel | `onPortChanged()` |
| `onOrchestratorUseTlsChanged()` | OrchestratorConfigViewModel | `onUseTlsChanged()` |
| `applyOrchestratorConfig()` | OrchestratorConfigViewModel | `applyConfig()` |

## Testing After Migration

### Unit Test Example

```kotlin
@Test
fun `settings screen displays host input`() = runTest {
    // Arrange
    val viewModel = OrchestratorConfigViewModel(mockRepository)
    
    // Act
    composeTestRule.setContent {
        SettingsScreen(orchestratorViewModel = viewModel)
    }
    
    // Assert
    composeTestRule
        .onNodeWithText("Host")
        .assertExists()
}
```

### Integration Test Example

```kotlin
@Test
fun `can save orchestrator configuration`() = runTest {
    // Arrange
    composeTestRule.setContent {
        SettingsScreen()
    }
    
    // Act
    composeTestRule.onNodeWithText("Host").performTextInput("localhost")
    composeTestRule.onNodeWithText("Port").performTextInput("8080")
    composeTestRule.onNodeWithText("Save Configuration").performClick()
    
    // Assert
    composeTestRule.onNodeWithText("Configuration saved").assertExists()
}
```

## Common Pitfalls

### 1. Forgetting to Import New ViewModel

**Error:**
```
Unresolved reference: RecordingViewModel
```

**Solution:**
```kotlin
import com.buccancs.ui.recording.RecordingViewModel
```

### 2. Using Wrong State Property Name

**Error:**
```
Unresolved reference: orchestratorHostInput
```

**Solution:**
```kotlin
// Before: uiState.orchestratorHostInput
// After: uiState.hostInput
```

### 3. Not Collecting State

**Error:**
```
UI not updating
```

**Solution:**
```kotlin
val uiState by viewModel.uiState.collectAsState()
```

### 4. Injecting Too Many ViewModels

**Warning:**
If you need >3 ViewModels, consider splitting the screen

**Solution:**
```kotlin
// Instead of:
fun BigScreen(vm1, vm2, vm3, vm4, vm5) // Too many!

// Do:
fun BigScreen() {
    TabRow {
        Tab { Section1(vm1) }
        Tab { Section2(vm2, vm3) }
    }
}
```

## Quick Reference

### Import Statements

```kotlin
// ViewModels
import com.buccancs.ui.recording.RecordingViewModel
import com.buccancs.ui.device.DeviceInventoryViewModel
import com.buccancs.ui.shimmer.ShimmerConfigViewModel
import com.buccancs.ui.orchestrator.OrchestratorConfigViewModel
import com.buccancs.ui.telemetry.TelemetryViewModel

// Hilt
import androidx.hilt.navigation.compose.hiltViewModel
```

### Typical Screen Structure

```kotlin
@Composable
fun MyScreen(
    viewModel1: ViewModel1 = hiltViewModel(),
    viewModel2: ViewModel2 = hiltViewModel()
) {
    val state1 by viewModel1.uiState.collectAsState()
    val state2 by viewModel2.uiState.collectAsState()
    
    Column {
        Section1(state1, viewModel1::action1)
        Section2(state2, viewModel2::action2)
    }
}
```

## Help and Support

### Questions?
- Check the full documentation in `docs/analysis/`
- Review test examples in `app/src/test/`
- Ask the team in #architecture channel

### Found a Bug?
- Create a ticket with "Migration:" prefix
- Include before/after code snippets
- Tag with "refactoring" label

### Need Help Migrating?
- Schedule a pairing session
- Review this guide first
- Bring specific questions
