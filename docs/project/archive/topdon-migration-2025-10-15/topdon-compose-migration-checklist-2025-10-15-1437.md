**Last Modified:** 2025-10-15 14:37 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Migration Checklist

# TOPDON Compose Migration - Complete File Checklist

## Migration Summary

- **Total Activities:** 63
- **Total Fragments:** 15
- **Total Custom Views:** 46
- **Total XML Layouts:** 190
- **Total Dialogs/Popups:** 20
- **Total Adapters:** ~20
- **Priority:** High to Low based on module importance

## Module-by-Module Breakdown

### 1. APP MODULE (Priority: HIGH - Week 2-4)

#### Activities (11)

- [ ] `SplashActivity.kt` → `SplashScreen.kt` [WEEK 2, DAY 6]
- [ ] `MainActivity.kt` → `MainScreen.kt` [WEEK 3, DAY 11]
- [ ] `ClauseActivity.kt` → `ClauseScreen.kt` [WEEK 2, DAY 7]
- [ ] `PolicyActivity.kt` → `PolicyScreen.kt` [WEEK 2, DAY 7]
- [ ] `VersionActivity.kt` → `VersionScreen.kt` [WEEK 2, DAY 8]
- [ ] `MoreHelpActivity.kt` → `HelpScreen.kt` [WEEK 2, DAY 8]
- [ ] `WebViewActivity.kt` → `WebViewScreen.kt` [WEEK 2, DAY 9]
- [ ] `PdfActivity.kt` → `PdfViewerScreen.kt` [WEEK 2, DAY 9]
- [ ] `DeviceTypeActivity.kt` → `DeviceSelectionScreen.kt` [WEEK 3, DAY 13]
- [ ] `IRGalleryEditActivity.kt` → `ThermalEditScreen.kt` [WEEK 8, DAY 36]
- [ ] `BlankDevActivity.kt` → Assess need (may be unused)

#### Fragments (1)

- [ ] `MainFragment.kt` → `HomeScreen.kt` [WEEK 3, DAY 12]

#### Custom Views (3)

- [ ] `ConnectionGuideView.kt` → `ConnectionGuideBottomSheet.kt` [WEEK 3, DAY 12]
- [ ] `MyGLSurfaceView.java` → Wrap in `AndroidView` [CRITICAL - DO NOT REWRITE]
- [ ] `MyImageSpan.java` → Assess need or create Compose alternative

#### Popups (1)

- [ ] `DelPopup.kt` → `DeleteConfirmationDialog.kt` [WEEK 3]

#### XML Layouts (15)

All layouts to be replaced by Compose equivalents

**Status:** 0/15 migrated

---

### 2. COMPONENT/THERMAL-IR (Priority: CRITICAL - Week 7-9)

#### Activities (41)

##### Core Thermal Activities (Week 7-9)

- [ ] `IRThermalNightActivity.kt` → `ThermalNightScreen.kt` [WEEK 7-8, DAY 31-35]
- [ ] `IRThermalPlusActivity.kt` → `ThermalPlusScreen.kt` [WEEK 8, DAY 36-40]
- [ ] `IRThermalActivity.kt` → `ThermalScreen.kt` [WEEK 8]
- [ ] `BaseIRActivity.kt` → Base Compose screen pattern
- [ ] `BaseIRPlushActivity.kt` → Base Compose screen pattern

##### Settings & Configuration (Week 8-9)

- [ ] `IRCameraSettingActivity.kt` → `ThermalCameraSettingsScreen.kt`
- [ ] `IRConfigActivity.kt` → `ThermalConfigScreen.kt`
- [ ] `IREmissivityActivity.kt` → `EmissivitySettingsScreen.kt`

##### Correction & Calibration (Week 9)

- [ ] `IRCorrectionActivity.kt` → `CorrectionScreen.kt`
- [ ] `IRCorrectionTwoActivity.kt` → `CorrectionStep2Screen.kt`
- [ ] `IRCorrectionThreeActivity.kt` → `CorrectionStep3Screen.kt`
- [ ] `IRCorrectionFourActivity.kt` → `CorrectionStep4Screen.kt`
- [ ] `ManualStep1Activity.kt` → `ManualCalibrationStep1Screen.kt`
- [ ] `ManualStep2Activity.kt` → `ManualCalibrationStep2Screen.kt`

##### Gallery & Image (Week 5-6)

- [ ] `IRGalleryHomeActivity.kt` → `GalleryHomeScreen.kt`
- [ ] `IRGalleryDetail01Activity.kt` → `GalleryDetailScreen.kt`
- [ ] `IRGalleryDetail04Activity.kt` → `GalleryDetail04Screen.kt`
- [ ] `ImagePickIRActivity.kt` → `ImagePickerScreen.kt`
- [ ] `ImagePickIRPlushActivity.kt` → `ImagePickerPlusScreen.kt`
- [ ] `ReportPickImgActivity.kt` → `ReportImagePickerScreen.kt`

##### Monitoring & Charts (Week 9)

- [ ] `IRMonitorActivity.kt` → `MonitorScreen.kt`
- [ ] `IRMonitorChartActivity.kt` → `MonitorChartScreen.kt`
- [ ] `MonitoryHomeActivity.kt` → `MonitoringHomeScreen.kt`
- [ ] `IRLogMPChartActivity.kt` → `LogChartScreen.kt`

##### Algorithm & Video (Week 9)

- [ ] `AlgorithmImageActivity.kt` → `AlgorithmImageScreen.kt`
- [ ] `IRVideoGSYActivity.kt` → `VideoPlayerScreen.kt`
- [ ] `TestRecordActivity.java` → `TestRecordScreen.kt`

##### Main Navigation (Week 7)

- [ ] `IRMainActivity.kt` → Integrate into main navigation

##### Report Generation (Week 10)

- [ ] `PDFListActivity.kt` → `PDFListScreen.kt`
- [ ] `ReportCreateFirstActivity.kt` → `ReportCreateStep1Screen.kt`
- [ ] `ReportCreateSecondActivity.kt` → `ReportCreateStep2Screen.kt`
- [ ] `ReportDetailActivity.kt` → `ReportDetailScreen.kt`
- [ ] `ReportPreviewFirstActivity.kt` → `ReportPreviewStep1Screen.kt`
- [ ] `ReportPreviewSecondActivity.kt` → `ReportPreviewStep2Screen.kt`

#### Fragments (11)

- [ ] `IRGalleryTabFragment.kt` → `GalleryScreen.kt` [WEEK 3, DAY 13]
- [ ] `IRGalleryFragment.kt` → Part of GalleryScreen
- [ ] `GalleryFragment.kt` → Part of GalleryScreen
- [ ] `IRThermalFragment.kt` → Part of ThermalNightScreen
- [ ] `IRPlushFragment.kt` → Part of ThermalPlusScreen
- [ ] `BaseIRPlushFragment.kt` → Base pattern
- [ ] `IRCorrectionFragment.kt` → `CorrectionScreenSection.kt`
- [ ] `IRMonitorCaptureFragment.kt` → `MonitorCaptureSection.kt`
- [ ] `IRMonitorHistoryFragment.kt` → `MonitorHistorySection.kt`
- [ ] `IRMonitorThermalFragment.kt` → `MonitorThermalSection.kt`
- [ ] `PDFListFragment.kt` → Part of PDFListScreen

#### Custom Views (23)

##### Chart Views (Week 9)

- [ ] `ChartLogView.kt` → Chart Composable with Canvas
- [ ] `ChartMonitorView.kt` → Chart Composable
- [ ] `ChartTrendView.kt` → Chart Composable
- [ ] `TrendView.kt` → Chart Composable
- [ ] `MyMarkerView.java` → Compose equivalent

##### Temperature Views (Week 8)

- [ ] `Temperature07View.kt` → `Temperature07.kt` Composable
- [ ] `TemperatureBaseView.kt` → Base Composable pattern
- [ ] `TemperatureEditView.kt` → `TemperatureEditor.kt` Composable
- [ ] `TargetBarPickView.kt` → `TargetBarPicker.kt` Composable

##### Measurement Views (Week 8)

- [ ] `DistanceMeasureView.kt` → `DistanceMeasure.kt` Composable
- [ ] `EmissivityView.kt` → `EmissivityControl.kt` Composable

##### Report Views (Week 10)

- [ ] `ReportIRInputView.kt` → `ReportIRInput.kt` Composable
- [ ] `ReportIRShowView.kt` → `ReportIRDisplay.kt` Composable
- [ ] `ReportInfoView.kt` → `ReportInfo.kt` Composable
- [ ] `WatermarkView.kt` → `WatermarkOverlay.kt` Composable

##### Utility Views (Week 9)

- [ ] `TimeDownView.kt` → `CountdownTimer.kt` Composable
- [ ] `MoveImageView.java` → Use Compose gesture modifiers
- [ ] `DetectHorizontalScrollView.java` → Use Compose scrolling
- [ ] `LinearCompassView.kt` → `CompassIndicator.kt` Composable

#### Dialogs & Popups (8)

- [ ] `ConfigGuideDialog.kt` → `ConfigGuideDialog.kt` (Compose)
- [ ] `HomeGuideDialog.kt` → `HomeGuideDialog.kt` (Compose)
- [ ] `IRConfigInputDialog.kt` → `ConfigInputDialog.kt` (Compose)
- [ ] `CameraItemPopup.kt` → `CameraItemBottomSheet.kt`
- [ ] `GalleryChangePopup.kt` → `GalleryChangeBottomSheet.kt`
- [ ] `OptionPickPopup.kt` → `OptionPickerBottomSheet.kt`
- [ ] `SeekBarPopup.kt` → `SeekBarDialog.kt` (Compose)

#### XML Layouts (75)

All layouts to be replaced by Compose equivalents

**Status:** 0/75 migrated

---

### 3. COMPONENT/THERMAL-LITE (Priority: HIGH - Week 8)

#### Activities (7)

- [ ] `IRThermalLiteActivity.kt` → `ThermalLiteScreen.kt` [WEEK 8, DAY 41-45]
- [ ] `IRMonitorLiteActivity.kt` → `MonitorLiteScreen.kt`
- [ ] `IRMonitorChartLiteActivity.kt` → `MonitorChartLiteScreen.kt`
- [ ] `IRCorrectionLiteFourActivity.kt` → `CorrectionLiteStep4Screen.kt`
- [ ] `IRCorrectionLiteThreeActivity.kt` → `CorrectionLiteStep3Screen.kt`
- [ ] `ImagePickIRLiteActivity.kt` → `ImagePickerLiteScreen.kt`
- [ ] `IrDisplayActivity.java` → `DisplayScreen.kt`

#### Fragments (1)

- [ ] `IRMonitorLiteFragment.kt` → Part of MonitorLiteScreen

#### XML Layouts (6)

All layouts to be replaced by Compose equivalents

**Status:** 0/6 migrated

---

### 4. COMPONENT/USER (Priority: MEDIUM - Week 6)

#### Activities (10)

- [ ] `LanguageActivity.kt` → `LanguageSelectionScreen.kt` [WEEK 6]
- [ ] `MoreActivity.kt` → `MoreSettingsScreen.kt`
- [ ] `AutoSaveActivity.kt` → `AutoSaveSettingsScreen.kt`
- [ ] `DeviceDetailsActivity.kt` → `DeviceDetailsScreen.kt`
- [ ] `ElectronicManualActivity.kt` → `ManualScreen.kt`
- [ ] `QuestionActivity.kt` → `FAQScreen.kt`
- [ ] `QuestionDetailsActivity.kt` → `FAQDetailScreen.kt`
- [ ] `StorageSpaceActivity.kt` → `StorageScreen.kt`
- [ ] `TISRActivity.kt` → `TISRScreen.kt`
- [ ] `UnitActivity.kt` → `UnitSettingsScreen.kt`

#### Fragments (2)

- [ ] `MineFragment.kt` → `ProfileScreen.kt` [WEEK 3, DAY 15]
- [ ] `MoreFragment.kt` → Part of MoreSettingsScreen

#### Custom Views (3)

- [ ] `ListItemView.kt` → Standard Compose ListItem
- [ ] `DragCustomerView.java` → Compose drag modifier
- [ ] `ProgressBarView.java` → Standard Compose progress

#### Dialogs (2)

- [ ] `DownloadProDialog.kt` → `DownloadProgressDialog.kt` (Compose)
- [ ] `FirmwareInstallDialog.kt` → `FirmwareInstallDialog.kt` (Compose)

#### XML Layouts (20)

All layouts to be replaced by Compose equivalents

**Status:** 0/20 migrated

---

### 5. COMPONENT/TRANSFER (Priority: MEDIUM - Week 10)

#### Activities (1)

- [ ] `TransferActivity.kt` → `TransferScreen.kt`

#### Dialogs (1)

- [ ] `TransferDialog.kt` → `TransferDialog.kt` (Compose)

#### XML Layouts (2)

All layouts to be replaced by Compose equivalents

**Status:** 0/2 migrated

---

### 6. COMPONENT/PSEUDO (Priority: MEDIUM - Week 10)

#### Activities (1)

- [ ] `PseudoSetActivity.kt` → `PseudoColourSettingsScreen.kt`

#### Custom Views (1)

- [ ] `PseudoPickView.kt` → `PseudoColourPicker.kt` Composable

#### XML Layouts (1)

All layout to be replaced by Compose equivalent

**Status:** 0/1 migrated

---

### 7. COMPONENT/EDIT3D (Priority: LOW - Week 10)

#### Activities (1)

- [ ] `Image3DActivity.kt` → `Image3DScreen.kt`

#### Custom Views (2)

- [ ] `Menu3DView.kt` → `Menu3D.kt` Composable
- [ ] `MarqueeTextView.kt` → Use Compose BasicMarquee

#### XML Layouts (0)

No XML layouts in source (may be in build)

**Status:** N/A

---

### 8. LIBUI (Priority: HIGH - Week 1-2, 10)

#### Custom Views (3 main + wheel/chart library views)

- [ ] `NumberWheelView.java` → Consider Compose picker library
- [ ] `WheelView.java` → Consider Compose picker library
- [ ] `MarkerView.java` (charting) → Compose chart marker

#### Dialogs (9 - Base dialog system)

- [ ] `BaseDialog.java` → Base Compose dialog pattern
- [ ] `BottomDialog.java` → ModalBottomSheet pattern
- [ ] `ModalDialog.java` → AlertDialog pattern
- [ ] `DialogConfig.java` → Compose dialog configuration
- [ ] `DialogColor.java` → Colour utilities
- [ ] `DialogLog.java` → Logging utilities
- [ ] `DialogStyle.java` → Theme configuration

#### XML Layouts (34)

Base UI component layouts - migrate to Compose component library

**Status:** 0/34 migrated

---

### 9. LIBMENU (Priority: MEDIUM - Week 10)

#### Custom Views (5)

- [ ] `MenuEditView.kt` → `MenuEditor.kt` Composable
- [ ] `MenuFirstTabView.kt` → `MenuFirstTab.kt` Composable
- [ ] `MenuSecondView.kt` → `MenuSecondTab.kt` Composable
- [ ] `CameraMenuView.kt` → `CameraMenu.kt` Composable
- [ ] `ColorView.kt` → `ColourPicker.kt` Composable

#### XML Layouts (5)

Menu system layouts

**Status:** 0/5 migrated

---

### 10. LIBIR (Priority: HIGH - Week 5-6)

#### Custom Views (9 - Core thermal/camera views)

- [ ] `BaseDualView.java` → **WRAP in AndroidView** (USB camera)
- [ ] `BaseParamDualView.java` → **WRAP in AndroidView** (USB camera)
- [ ] `CameraJpegView.java` → **WRAP in AndroidView** (USB camera)
- [ ] `CameraView.java` → **WRAP in AndroidView** (USB camera)
- [ ] `TemperatureView.java` → **WRAP in AndroidView** or rewrite
- [ ] `CaliperImageView.kt` → `CaliperImage.kt` Composable
- [ ] `ZoomCaliperView.kt` → `ZoomCaliper.kt` Composable
- [ ] `DragScaleView.java` → Use Compose gesture modifiers
- [ ] `ZoomableDraggableView.java` → Use Compose transformable

#### XML Layouts (1)

Camera view layout

**Status:** 0/1 migrated

---

### 11. LIBCOM (Priority: MEDIUM - Week 10)

#### Custom Views (1)

- [ ] `CommLoadMoreView.kt` → Use Compose LazyColumn with load more

#### Dialogs (3)

- [ ] `ColorDialog.kt` → `ColourPickerDialog.kt` (Compose)
- [ ] `ColorPickDialog.kt` → `ColourSelectionDialog.kt` (Compose)
- [ ] `TempAlarmSetDialog.kt` → `TemperatureAlarmDialog.kt` (Compose)

#### XML Layouts (6)

Common component layouts

**Status:** 0/6 migrated

---

### 12. LIBAPP (Priority: MEDIUM - Week 10)

#### XML Layouts (25)

App-level shared layouts

**Status:** 0/25 migrated

---

## Adapters (Estimated 20)

All RecyclerView adapters need to be replaced with Compose LazyColumn/LazyRow/LazyGrid:

- [ ] Gallery image adapters → LazyVerticalGrid
- [ ] Settings list adapters → LazyColumn
- [ ] Menu item adapters → LazyColumn
- [ ] Report list adapters → LazyColumn
- [ ] Device list adapters → LazyColumn
- [ ] Temperature log adapters → LazyColumn
- [ ] And ~14 more adapters throughout modules

---

## Critical Views - DO NOT REWRITE

These views contain complex OpenGL/USB camera logic. **WRAP in AndroidView instead:**

1. `MyGLSurfaceView.java` - OpenGL thermal rendering
2. `BaseDualView.java` - USB dual camera
3. `BaseParamDualView.java` - USB camera with parameters
4. `CameraJpegView.java` - JPEG camera stream
5. `CameraView.java` - Base camera view

**Rationale:** These views are working and contain critical hardware communication. Rewriting risks introducing bugs in
thermal imaging.

---

## Migration Progress Tracking

### By Phase

- **Phase 1 (Week 1-2):** Foundation - 0% complete
- **Phase 2 (Week 3-4):** Simple screens - 0/7 screens (0%)
- **Phase 3 (Week 5-6):** Navigation - 0/3 main screens (0%)
- **Phase 4 (Week 7-8):** Device management - 0% complete
- **Phase 5 (Week 7-9):** Thermal core - 0/3 thermal modes (0%)
- **Phase 6 (Week 10-11):** Libraries - 0/5 modules (0%)
- **Phase 7 (Week 12-13):** Polish - 0% complete

### By Module

- **app:** 0/15 layouts, 0/11 activities (0%)
- **thermal-ir:** 0/75 layouts, 0/41 activities (0%)
- **thermal-lite:** 0/6 layouts, 0/7 activities (0%)
- **user:** 0/20 layouts, 0/10 activities (0%)
- **transfer:** 0/2 layouts, 0/1 activity (0%)
- **pseudo:** 0/1 layout, 0/1 activity (0%)
- **edit3d:** 0/1 activity (0%)
- **libui:** 0/34 layouts (0%)
- **libmenu:** 0/5 layouts (0%)
- **libir:** 0/1 layout (0%)
- **libcom:** 0/6 layouts (0%)
- **libapp:** 0/25 layouts (0%)

### Overall Progress

**Total: 0/190 layouts, 0/63 activities, 0/15 fragments, 0/46 custom views**

---

## Testing Checklist

For each migrated component:

- [ ] Compose preview works
- [ ] UI matches original design
- [ ] All interactions functional
- [ ] State survives configuration changes
- [ ] Dark theme supported
- [ ] Accessibility labels
- [ ] Performance acceptable
- [ ] Memory leak checked
- [ ] Unit tests written
- [ ] UI tests written

---

## Notes

1. **XML Layouts:** Total of 190 layouts to be eliminated. Each screen migration removes multiple layouts.

2. **Adapters:** All RecyclerView.Adapter classes will be replaced with LazyColumn/LazyRow/LazyGrid with item
   composables.

3. **Custom Views:** 46 custom views - most can be rewritten as Composables, but 5 critical camera/OpenGL views should
   be wrapped in AndroidView.

4. **Dialogs:** 20 dialog/popup classes to migrate to Compose AlertDialog or ModalBottomSheet.

5. **Fragments:** 15 fragments will become Composable screens or sections within screens.

6. **Priority Order:**
    - Week 1-2: Foundation (libui base components)
    - Week 3-4: App module (simple screens)
    - Week 5-6: User module + Gallery
    - Week 7-9: Thermal modules (critical path)
    - Week 10-11: Remaining lib modules
    - Week 12-13: Polish

7. **Hardware Dependencies:** Thermal-ir and thermal-lite modules require TC001 hardware for testing during migration.

---

## Related Documents

- `topdon-compose-migration-plan-2025-10-15-1155.md` - Overall migration strategy
- `topdon-parallel-workstreams-2025-10-15-1202.md` - Parallel work breakdown
- `topdon-task-assignments-2025-10-15-1253.md` - Day-by-day tasks

## Conclusion

This checklist provides a complete inventory of all files requiring migration from XML/View system to Compose Material
UI. The migration involves 63 activities, 15 fragments, 46 custom views, 190 XML layouts, 20 dialogs, and ~20 adapters
across 11 modules.

Priority is given to establishing foundation, then simple screens, then navigation, and finally the critical thermal
imaging core that requires careful hardware testing.

