# thermal-ir Module - Compose Migration Guide

## Status: Ready for Phase 2

**Build Configuration:** ✅ Updated  
**Dependencies:** ✅ Added  
**Theme:** ✅ Created  
**Activities Converted:** 0/34 (0%)

## Module Overview

The thermal-ir module is the core thermal imaging component with 34 activities handling:

- Real-time thermal camera processing
- OpenGL rendering
- Temperature measurement and analysis
- Image/video capture and playback
- Monitoring and charting
- Configuration and calibration

## Build Configuration

### Updated Files

- ✅ `build.gradle` - Compose plugin and dependencies added
- ✅ Theme created at `ui/theme/Theme.kt`
- ✅ Directory structure created

### Compose Dependencies Added

```gradle
// Compose BOM
implementation platform('androidx.compose:compose-bom:2024.09.03')

// Material 3
implementation 'androidx.compose.material3:material3'

// Compose Core
implementation 'androidx.compose.ui:ui'
implementation 'androidx.compose.foundation:foundation'

// Integration
implementation 'androidx.activity:activity-compose:1.9.2'
implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6'

// Coil for images
implementation 'io.coil-kt:coil-compose:2.7.0'
```

## Migration Strategy

### Hybrid Approach Required

Due to complexity of thermal processing, use **Compose Shell + AndroidView Core** pattern:

```kotlin
@Route(path = RouterConfig.IR_THERMAL)
class IRThermalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        setContent {
            ThermalIRTheme {
                ThermalCameraScreen(
                    onNavigateUp = { finish() }
                )
            }
        }
    }
}

@Composable
private fun ThermalCameraScreen(onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thermal Camera") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        // Preserve existing thermal processing
        AndroidView(
            factory = { context ->
                LayoutInflater.from(context)
                    .inflate(R.layout.activity_ir_thermal, null)
            },
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}
```

## Activities by Priority

### Tier 1: UI Chrome Conversion (Week 1)

Convert navigation bars and toolbars to Compose, preserve thermal views:

1. **IRMainActivity** (ViewPager with tabs)
    - Convert bottom navigation to Compose
    - Preserve fragment ViewPager in AndroidView
    - Maintain tab selection logic

2. **IRThermalActivity** (Main camera interface)
    - Convert top app bar to Compose
    - Convert bottom controls to Compose
    - Preserve thermal camera view

3. **IRGalleryHomeActivity** (Gallery home) - ✅ Started
    - Use fragment in AndroidView
    - Add Compose app bar

4. **IRMonitorActivity** (Monitoring interface)
    - Convert navigation to Compose
    - Preserve monitoring views

5. **IRConfigActivity** (Configuration screen)
    - Convert app bar to Compose
    - Keep RecyclerView in AndroidView initially

### Tier 2: Simple Screens (Week 2)

Full Compose conversion for settings/configuration:

6. **IRCameraSettingActivity** - Camera settings list
7. **IREmissivityActivity** - Emissivity configuration
8. **ManualStep1Activity** - Manual calibration step 1
9. **ManualStep2Activity** - Manual calibration step 2

### Tier 3: Gallery & Media (Week 3)

10. **IRGalleryDetail01Activity** - Gallery detail view
11. **IRGalleryDetail04Activity** - Alternative detail view
12. **ImagePickIRActivity** - Image picker
13. **ImagePickIRPlushActivity** - Enhanced picker
14. **IRVideoGSYActivity** - Video player

### Tier 4: Monitoring & Charts (Week 4)

15. **MonitoryHomeActivity** - Monitor home
16. **IRMonitorChartActivity** - Chart display
17. **IRLogMPChartActivity** - Log charts

### Tier 5: Advanced Features (Future)

18-34. Remaining activities (calibration, correction, algorithms, etc.)

## Code Patterns

### Pattern 1: ViewPager Activity

```kotlin
@Composable
fun MainScreen(
    pages: List<Fragment>,
    initialPage: Int = 0
) {
    var currentPage by remember { mutableStateOf(initialPage) }
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                pages.forEachIndexed { index, page ->
                    NavigationBarItem(
                        selected = currentPage == index,
                        onClick = { currentPage = index },
                        icon = { Icon(...) },
                        label = { Text(...) }
                    )
                }
            }
        }
    ) { padding ->
        AndroidView(
            factory = { context ->
                // ViewPager2 with fragments
            }
        )
    }
}
```

### Pattern 2: Settings List

```kotlin
@Composable
fun SettingsScreen(
    settings: List<Setting>,
    onSettingChange: (Setting) -> Unit
) {
    LazyColumn {
        items(settings) { setting ->
            SettingItem(
                setting = setting,
                onChanged = { onSettingChange(setting) }
            )
        }
    }
}
```

### Pattern 3: Camera View

```kotlin
@Composable
fun CameraScreen() {
    Scaffold(
        topBar = { CameraTopBar() },
        bottomBar = { CameraControls() }
    ) { padding ->
        AndroidView(
            factory = { context ->
                ThermalCameraView(context).apply {
                    // Initialize thermal camera
                }
            },
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}
```

## Theme Usage

```kotlin
import com.topdon.module.thermal.ir.ui.theme.ThermalIRTheme

@Composable
fun MyScreen() {
    ThermalIRTheme {
        Scaffold { padding ->
            // Your content
        }
    }
}
```

## Testing Checklist

For each converted activity:

- [ ] Builds successfully
- [ ] Navigation works correctly
- [ ] All buttons/controls functional
- [ ] State preserved on configuration change
- [ ] Thermal processing unaffected
- [ ] No performance regression
- [ ] Visual design matches Material 3
- [ ] Accessibility labels present

## Critical Warnings

⚠️ **DO NOT** rewrite thermal processing logic  
⚠️ **DO NOT** modify OpenGL rendering code  
⚠️ **DO NOT** change temperature calculation algorithms  
⚠️ **DO** preserve existing Views for complex thermal UI  
⚠️ **DO** test thoroughly on actual devices (TC001, TS004, TC007)

## Performance Considerations

1. **AndroidView overhead:** Minimal for static views
2. **Recomposition:** Keep state outside AndroidView
3. **Memory:** Monitor thermal view lifecycle
4. **Camera preview:** No impact if wrapped properly

## Next Steps

1. Start with IRMainActivity (highest priority, most visible)
2. Test thoroughly on TC001 device
3. Measure performance benchmarks
4. Continue with IRThermalActivity
5. Document lessons learned

## Resources

- **Theme:** `ui/theme/Theme.kt`
- **Example:** See app module activities for patterns
- **Documentation:** `/docs/project/TOPDON_PHASE2_STRATEGY_2025-10-16_0045.md`

---

**Status:** Ready to Start  
**First Target:** IRMainActivity  
**Estimated Phase 2:** 2-3 weeks for Tier 1-2
