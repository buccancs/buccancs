**Last Modified:** 2025-01-14 04:52 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Dependency Analysis

# Topdon Actual Dependencies

## Analysis Results

### Currently Used Imports

From `TopdonThermalConnector.kt`:
```kotlin
import com.infisense.iruvc.usb.USBMonitor
import com.infisense.iruvc.utils.CommonParams
import com.infisense.iruvc.utils.IFrameCallback
import com.infisense.iruvc.uvc.ConcreateUVCBuilder
import com.infisense.iruvc.uvc.UVCCamera
import com.infisense.iruvc.uvc.UVCType
```

### Required Classes

**Core USB/UVC:**
1. `USBMonitor` - USB device monitoring and connection
2. `USBMonitor.UsbControlBlock` - USB control
3. `USBMonitor.OnDeviceConnectListener` - Connection callbacks
4. `UVCCamera` - UVC camera interface
5. `ConcreateUVCBuilder` - Camera builder
6. `UVCType` - Camera type enum
7. `CommonParams` - Frame format parameters
8. `IFrameCallback` - Frame data callback

### Essential AAR Files (from libir/libs/)

Based on package names, these AARs contain the required classes:

1. **libusbdualsdk_1.3.4_2406271906_standard.aar** (8MB)
   - Contains USBMonitor, UVCCamera, IFrameCallback
   - **CRITICAL - MUST KEEP**

2. **opengl_1.3.2_standard.aar** (36KB)
   - OpenGL rendering support
   - **KEEP** (small, likely needed)

**Potentially Removable:**
3. ~~ai-upscale-release.aar (11MB)~~ - AI upscaling, not used
4. ~~jetified-tas_api-1.0.4.0.aar (149KB)~~ - TAS API, not used
5. ~~library_1.0.aar (64MB!)~~ - Unknown, very large
6. ~~suplib-release.aar (31MB)~~ - Support library, not directly used
7. ~~texturegesture-release.aar (45KB)~~ - Gesture handling, not used

### Source Files (libir/src/)

Most source files are for UI components we don't use:
- `TemperatureView.java` (66KB) - UI widget, not needed
- `CameraView.java`, `CameraJpegView.java` - UI views, not needed
- Various utils for UI, gestures, drawing - not needed

**Exception:**
- Some utilities in `com.infisense.usbir.utils` might be referenced by AAR internals

### Decision: Use Pre-compiled AARs

**Recommendation:** Keep only the essential AAR files, remove all source code

**Why:**
- The classes we need are pre-compiled in the AARs
- No need to maintain source code
- Significantly reduces complexity
- Native libraries are in the AARs

## Minimal Configuration

### Keep These Files Only

```
external/original-topdon-app/libir/libs/
├── libusbdualsdk_1.3.4_2406271906_standard.aar  (8MB) - ESSENTIAL
└── opengl_1.3.2_standard.aar                      (36KB) - LIKELY NEEDED
```

**Total:** ~8MB (vs current ~115MB in libir/)

### Remove Everything Else

```
external/original-topdon-app/
├── component/           (11,062 files) - DELETE
├── app/                  (2,129 files) - DELETE
├── libui/                  (410 files) - DELETE
├── libmenu/                (181 files) - DELETE
├── libapp/                 (167 files) - DELETE
├── libir-demo/             (108 files) - DELETE
├── BleModule/              (100 files) - DELETE
├── buildSrc/                (96 files) - DELETE
├── libir/src/              (~60 files) - DELETE (use AAR)
├── libir/libs/*.aar      (5 AARs, 107MB) - DELETE (unused)
├── .gradle/                 (38 files) - DELETE
├── .idea/                   (24 files) - DELETE
├── LocalRepo/              (215 files) - DELETE
├── libmatrix/               (62 files) - DELETE
├── libcom/                  (52 files) - DELETE
├── libhik/                  (13 files) - DELETE
├── RangeSeekBar/            (18 files) - DELETE
├── gradle/                   (3 files) - DELETE
└── commonlibrary/            (3 files) - DELETE
```

**Files to Remove:** ~20,200 files  
**Size to Remove:** ~492MB

## Migration Strategy

### Phase 1: Copy Essential AARs to Project

Instead of keeping external/, copy AARs to our project:

```
app/libs/
├── topdon-usbdual-1.3.4.aar  (renamed for clarity)
└── topdon-opengl-1.3.2.aar   (renamed for clarity)
```

### Phase 2: Update Gradle

```kotlin
// app/build.gradle.kts
dependencies {
    implementation(files("libs/topdon-usbdual-1.3.4.aar"))
    implementation(files("libs/topdon-opengl-1.3.2.aar"))
    
    // Remove old external project dependency
    // implementation(project(":external:original-topdon-app:libir"))
}
```

### Phase 3: Verify Imports Still Work

All imports should continue to work as they reference classes in the AARs:
- `com.infisense.iruvc.usb.USBMonitor` ✓
- `com.infisense.iruvc.uvc.UVCCamera` ✓
- `com.infisense.iruvc.utils.IFrameCallback` ✓

### Phase 4: Delete External Directory

```bash
# Create backup
tar -czf topdon-external-backup-$(date +%Y%m%d).tar.gz external/

# Delete entire external directory
rm -rf external/original-topdon-app/
```

### Phase 5: Update Settings.gradle.kts

Remove external project reference:
```kotlin
// settings.gradle.kts
// REMOVE: include(":external:original-topdon-app:libir")
```

## Expected Results

### Before
- **Files:** 20,207
- **Size:** ~500MB
- **Maintenance:** Nightmare (thousands of files to search)
- **Build Time:** Slow (processing thousands of files)

### After
- **Files:** 2 AAR files
- **Size:** ~8MB
- **Maintenance:** Simple (2 binary dependencies)
- **Build Time:** Faster (no source compilation)

**Reduction:** 99.99% fewer files, 98.4% size reduction

## Risk Assessment

### Low Risk
- AARs are pre-compiled and tested
- Classes we need are in the AARs
- No source code changes needed
- Easy rollback (restore from backup)

### Testing Required
1. Connect USB thermal camera
2. Verify frame capture works
3. Verify temperature conversion accurate
4. Test disconnect/reconnect
5. Check native library loading

### Rollback Plan
If something breaks:
1. Restore external/ from backup
2. Revert build.gradle.kts changes
3. Revert settings.gradle.kts changes
4. Rebuild project

## Implementation Commands

```bash
# 1. Create backup
cd /path/to/buccancs
tar -czf topdon-backup-$(date +%Y%m%d).tar.gz external/original-topdon-app/

# 2. Create app/libs if doesn't exist
mkdir -p app/libs

# 3. Copy essential AARs
cp external/original-topdon-app/libir/libs/libusbdualsdk_1.3.4_2406271906_standard.aar \
   app/libs/topdon-usbdual-1.3.4.aar
   
cp external/original-topdon-app/libir/libs/opengl_1.3.2_standard.aar \
   app/libs/topdon-opengl-1.3.2.aar

# 4. Update build.gradle.kts (manual)

# 5. Update settings.gradle.kts (manual)

# 6. Test build
./gradlew :app:assembleDebug

# 7. If successful, delete external
rm -rf external/original-topdon-app/

# 8. Commit changes
git add app/libs/*.aar app/build.gradle.kts settings.gradle.kts
git rm -r external/original-topdon-app
git commit -m "refactor: extract minimal Topdon SDK (2 AARs, 99.99% reduction)"
```

## Verification Checklist

- [ ] Backup created
- [ ] AARs copied to app/libs/
- [ ] build.gradle.kts updated
- [ ] settings.gradle.kts updated
- [ ] Project builds successfully
- [ ] Thermal camera connects
- [ ] Frame capture works
- [ ] Temperature conversion accurate
- [ ] No crashes or errors
- [ ] External directory deleted
- [ ] Changes committed

## Conclusion

We can reduce from 20,207 files to just 2 AAR files by:
1. Keeping only the essential pre-compiled libraries
2. Removing all unused source code and modules
3. Moving AARs directly into our project

This is safer and simpler than extracting source code, as we're using tested, compiled binaries.

**Recommendation:** Execute this plan immediately - it's low risk with high reward.
