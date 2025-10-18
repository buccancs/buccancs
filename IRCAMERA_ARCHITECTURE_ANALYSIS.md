# IRCamera Architecture Deep Dive

## Executive Summary

The IRCamera project is a sophisticated thermal imaging Android application with a **modular multi-flavor architecture** supporting multiple device types (TC001, TS004, TC007, HIK, Lite variants). It demonstrates enterprise-grade patterns for hardware integration, real-time data processing, and cross-module navigation.

---

## 1. Module Architecture

### 1.1 Module Dependency Tree

```
app (Application Module)
├── libapp (Core Library - Foundation)
│   ├── BaseActivity, BaseFragment, BaseViewModel
│   ├── BaseApplication (Singleton lifecycle manager)
│   ├── ARouter initialization
│   ├── EventBus global messaging
│   ├── SharedManager (SharedPreferences wrapper)
│   ├── WebSocketProxy (Device communication)
│   └── Repository layer (TS004, TC007, Gallery)
│
├── component/* (Feature Modules - Pluggable)
│   ├── thermal-ir (Primary thermal imaging)
│   ├── thermal-hik (HIK device variant)
│   ├── thermal-lite (Lite device variant)
│   ├── thermal04 (TS004 monocular)
│   ├── thermal07 (TC007 device)
│   ├── user (Settings & profile)
│   ├── house (Building inspection)
│   ├── pseudo (Custom pseudo-color)
│   ├── edit3d (3D editing)
│   └── transfer (Data transfer)
│
├── libir (Thermal Processing - Native)
│   ├── Native libraries (.so files)
│   ├── Image processing pipelines
│   └── Temperature correction algorithms
│
├── libcom (Common UI Components)
│   ├── Alarm helpers
│   ├── Color pickers
│   └── Shared dialogs
│
├── libui (UI Library)
│   ├── Custom views (SeekBar, Temperature overlays)
│   └── Dialog frameworks
│
├── libmenu (Menu System)
├── libhik (HIK-specific code)
├── BleModule (Bluetooth LE)
├── ai-upscale (Super-resolution)
└── RangeSeekBar (Custom component)
```

### 1.2 Modularization Benefits

**Achieved:**
- **Device-specific builds**: Different thermal modules compiled per variant
- **Feature isolation**: House inspection can be removed without breaking core
- **Parallel development**: Teams work on `thermal-ir` vs `thermal07` independently
- **Reduced build times**: Only changed modules recompile
- **A/B testing**: Enable/disable features via module inclusion

---

## 2. Navigation Architecture

### 2.1 ARouter Deep Linking System

**Why ARouter over Navigation Component:**
- Cross-module navigation without direct dependencies
- Runtime route discovery (no compile-time coupling)
- Deep link support for external intents
- Interceptors for permission/login gates

**Route Configuration** (`RouterConfig.kt`):
```kotlin
object RouterConfig {
    private const val GROUP_APP = "app"
    private const val GROUP_IR = "ir"
    private const val GROUP_USER = "user"
    
    const val MAIN = "/$GROUP_APP/main"
    const val IR_MAIN = "/$GROUP_IR/irMain"
    const val IR_THERMAL_07 = "/tc007/IRThermal07Activity"
    const val TS004_MORE = "/$GROUP_USER/ts004More"
}
```

**Usage Pattern:**
```kotlin
// Navigate to thermal imaging
ARouter.getInstance()
    .build(RouterConfig.IR_MAIN)
    .withBoolean(ExtraKeyConfig.IS_TC007, false)
    .navigation(this)

// Navigate with result callback
ARouter.getInstance()
    .build(RouterConfig.IR_GALLERY_HOME)
    .navigation(this, REQUEST_CODE)
```

**Route Declaration:**
```kotlin
@Route(path = RouterConfig.IR_MAIN)
class IRMainActivity : BaseActivity() {
    // Activity receives deep link automatically
}
```

### 2.2 Bottom Navigation Pattern

**MainActivity & IRMainActivity** use identical patterns:
- `ViewPager2` for smooth swipe transitions
- Fragment adapters with `BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT`
- State preservation via `offscreenPageLimit`
- Tab selection state synchronized with ViewPager callbacks

```kotlin
view_page.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        refreshTabSelect(position)
    }
})

private fun refreshTabSelect(index: Int) {
    // Update all tab indicators
    iv_icon_gallery.isSelected = (index == 0)
    tv_icon_gallery.isSelected = (index == 0)
    // ... for each tab
}
```

**Fragment State Management:**
```kotlin
private class ViewPagerAdapter(activity: FragmentActivity, isTC007: Boolean) 
    : FragmentStateAdapter(activity) {
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IRGalleryTabFragment().apply {
                arguments = Bundle().also {
                    it.putBoolean(ExtraKeyConfig.CAN_SWITCH_DIR, true)
                }
            }
            1 -> IRThermalFragment()
            // ...
        }
    }
}
```

---

## 3. Device Communication Architecture

### 3.1 WebSocket Proxy Pattern

**Single-Responsibility WebSocket Manager:**

```kotlin
class WebSocketProxy {
    companion object {
        private const val TS004_URL = "ws://192.168.40.1:888"
        private const val TC007_URL = "ws://192.168.40.1:63206/v1/thermal/temp/template/data"
    }
    
    private var currentSSID: String? = null
    private var mWsManager: WsManager? = null
    private var reconnectHandler = ReconnectHandler()
    
    fun startWebSocket(ssid: String, network: Network? = null) {
        // Auto-detect device type from SSID
        val url = if (ssid.startsWith(DeviceConfig.TS004_NAME_START)) 
            TS004_URL else TC007_URL
            
        mWsManager = WsManager.Builder()
            .client(getOKHttpClient())
            .wsUrl(url)
            .setWsStatusListener(webSocketListener)
            .build()
    }
}
```

**Key Features:**
1. **Auto-reconnection**: 3 attempts with 3-second intervals
2. **SSID-based routing**: Detects device type from WiFi name
3. **Network binding**: Forces traffic through specific network interface
4. **Heartbeat**: Prevents connection timeout with periodic pings
5. **Frame parsing**: Binary frames for TC007, JSON for TS004

**Reconnection Strategy:**
```kotlin
private class ReconnectHandler : Handler(Looper.getMainLooper()) {
    companion object {
        private const val MAX_RECONNECT_COUNT = 3
        private const val RECONNECT_MILLIS = 3000L
    }
    
    fun handleFail(currentSSID: String) {
        if (reconnectCount < MAX_RECONNECT_COUNT) {
            getInstance().stopWebSocket()
            postDelayed(RECONNECT_MILLIS) {
                getInstance().startWebSocket(currentSSID)
            }
        } else {
            // Give up and notify UI
            EventBus.getDefault().post(SocketStateEvent(false, isTS004))
        }
    }
}
```

### 3.2 Repository Pattern for HTTP/REST

**TS004Repository** demonstrates clean separation:

```kotlin
object TS004Repository {
    private fun getOKHttpClient(): OkHttpClient {
        val build = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(OKLogInterceptor(false))
        
        netWork?.socketFactory?.let {
            build.socketFactory(it) // Force network binding
        }
        return build.build()
    }
    
    private fun getTS004Service(): TS004Service = Retrofit.Builder()
        .baseUrl("http://192.168.40.1:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOKHttpClient())
        .build()
        .create(TS004Service::class.java)
}
```

**Batch Download Pattern:**
```kotlin
suspend fun downloadList(
    dataMap: Map<String, File>, 
    listener: ((path: String, isSuccess: Boolean) -> Unit)
): Int {
    return withContext(Dispatchers.IO) {
        var successCount = 0
        dataMap.forEach {
            val isSuccess = download(it.key, it.value)
            launch(Dispatchers.Main) {
                listener.invoke(it.key, isSuccess)
            }
            if (isSuccess) successCount++
        }
        return@withContext successCount
    }
}
```

---

## 4. State Management Architecture

### 4.1 Settings Persistence Layers

**SharedManager** (Always persisted):
```kotlin
object SharedManager {
    var hasTS004: Boolean
        get() = SPUtils.getInstance().getBoolean("hasConnectTS004", false)
        set(value) = SPUtils.getInstance().put("hasConnectTS004", value)
    
    var is04AutoSync: Boolean
        get() = SPUtils.getInstance().getBoolean("is04AutoSync", false)
        set(value) = SPUtils.getInstance().put("is04AutoSync", value)
}
```

**SaveSettingUtil** (Conditional - respects "Save Settings" toggle):
```kotlin
object SaveSettingUtil {
    fun saveIRConfig(config: CameraIRConfig) {
        if (SharedManager.isSaveSetting()) {
            val json = Gson().toJson(config)
            SPUtils.getInstance().put("ir_config", json)
        }
    }
}
```

**CameraLiveDateUtil** (Live session data):
```kotlin
// Singleton pattern for in-memory state
object CameraLiveDateUtil {
    private var cameraConfig = CameraIRConfig()
    
    val cameraIRConfigLiveDate = MutableLiveData<CameraIRConfig>()
    
    fun savePseudoColorMode(mode: Int) {
        cameraConfig.pseudoColorMode = mode
        cameraIRConfigLiveDate.postValue(cameraConfig)
        SaveSettingUtil.saveIfEnabled(cameraConfig)
    }
}
```

### 4.2 UI State Management

**Numeric State Codes Pattern:**
```kotlin
// In RecyclerView adapter or custom view
var cameraPreviewStats = 450  // 450=closed, 451=open
var cameraAlphaStats = 470    // 470=inactive, 471=active
var alarmStats = 500          // 500=off, 501=on

// Update UI based on state
when (cameraPreviewStats) {
    450 -> {
        // Show "Enable Camera Preview" button
    }
    451 -> {
        // Show camera preview overlay
    }
}
```

**Why this pattern:**
- Survives configuration changes (saved in `onSaveInstanceState`)
- Easy to serialize for debugging
- No complex enum serialization
- Numeric codes can be logged/tracked in analytics

---

## 5. BaseActivity Template Pattern

### 5.1 Core Responsibilities

```kotlin
abstract class BaseActivity : RxAppCompatActivity() {
    
    // 1. Lifecycle management
    protected abstract fun initContentView(): Int
    protected abstract fun initView()
    protected abstract fun initData()
    
    // 2. Device connection callbacks
    protected open fun connected() {}
    protected open fun disConnected() {}
    protected open fun onSocketConnected(isTS004: Boolean) {}
    protected open fun onSocketDisConnected(isTS004: Boolean) {}
    
    // 3. EventBus auto-registration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
    }
    
    // 4. Language/locale handling
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(
            AppLanguageUtils.attachBaseContext(
                newBase, 
                SharedManager.getLanguage(newBase!!)
            )
        )
    }
    
    // 5. Standardized loading dialogs
    private var loadingDialog: LoadingDialog? = null
    fun showLoadingDialog(@StringRes resId: Int = R.string.tip_loading) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.setTips(getString(resId))
        loadingDialog?.show()
    }
}
```

### 5.2 EventBus Integration

**Global Event Delivery:**
```kotlin
@Subscribe(threadMode = ThreadMode.MAIN)
fun getConnectState(event: DeviceConnectEvent) {
    if (event.isConnect) {
        connected()
    } else {
        disConnected()
    }
}

@Subscribe(threadMode = ThreadMode.MAIN)
fun onSocketConnectState(event: SocketStateEvent) {
    if (event.isConnect) {
        onSocketConnected(event.isTS004)
    } else {
        onSocketDisConnected(event.isTS004)
    }
}
```

**Event Posting from Anywhere:**
```kotlin
// From device layer
EventBus.getDefault().post(DeviceConnectEvent(isConnect = true))

// From WebSocket
EventBus.getDefault().post(SocketStateEvent(isConnect, isTS004))

// From user actions
EventBus.getDefault().post(PDFEvent())
```

---

## 6. Permission Management Architecture

### 6.1 XXPermissions Library Pattern

**Educational Permission Flow:**
```kotlin
private fun checkCameraPermission() {
    if (!XXPermissions.isGranted(this, Permission.CAMERA)) {
        if (BaseApplication.instance.isDomestic()) {
            // Chinese market: Explain before requesting
            TipDialog.Builder(this)
                .setMessage(getString(R.string.permission_request_camera_app, appName))
                .setPositiveListener(R.string.app_confirm) {
                    initCameraPermission()
                }
                .create().show()
        } else {
            // International: Request directly
            initCameraPermission()
        }
    }
}

private fun initCameraPermission() {
    XXPermissions.with(this)
        .permission(Permission.CAMERA)
        .request(object : OnPermissionCallback {
            override fun onGranted(permissions: List<String>, allGranted: Boolean) {
                if (allGranted) {
                    proceedWithFeature()
                }
            }
            
            override fun onDenied(permissions: List<String>, doNotAskAgain: Boolean) {
                if (doNotAskAgain) {
                    // Show "Open Settings" dialog
                    TipDialog.Builder(this@MainActivity)
                        .setMessage(R.string.app_camera_content)
                        .setPositiveListener(R.string.app_open) {
                            AppUtils.launchAppDetailsSettings()
                        }
                        .create().show()
                }
            }
        })
}
```

### 6.2 Dynamic Permission Lists

**SDK-Aware Permission Requests:**
```kotlin
private val permissionList by lazy {
    if (applicationInfo.targetSdkVersion >= 34) {
        listOf(
            Permission.READ_MEDIA_VIDEO,
            Permission.READ_MEDIA_IMAGES,
            Permission.WRITE_EXTERNAL_STORAGE
        )
    } else if (applicationInfo.targetSdkVersion >= 33) {
        listOf(
            Permission.READ_MEDIA_VIDEO,
            Permission.READ_MEDIA_IMAGES,
            Permission.WRITE_EXTERNAL_STORAGE
        )
    } else {
        listOf(
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE
        )
    }
}
```

---

## 7. Feature UI Patterns

### 7.1 PopupWindow for Contextual Settings

**Why PopupWindow over Dialog:**
- Lighter weight (no window dimming)
- Positioned relative to anchor view
- Immediate visual feedback (e.g., seekbar changes reflect in real-time)
- Doesn't interrupt flow like full dialog

**Implementation Pattern:**
```kotlin
private fun setParamLevelContrast() {
    if (thermal_recycler.contrastStats == 431) {
        popupWindow?.dismiss()
        return
    }
    
    thermal_recycler.contrastStats = 431
    popupWindow = PopupWindow(this)
    val contentView = LayoutInflater.from(this)
        .inflate(R.layout.layout_camera_seek_bar, null)
    
    popupWindow?.apply {
        this.contentView = contentView
        isFocusable = false
        animationStyle = R.style.SeekBarAnimation
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        setBackgroundDrawable(ColorDrawable(0))
    }
    
    // Setup SeekBar
    val seekBar = contentView.findViewById<CommSeekBar>(R.id.seek_bar)
    seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            val value = (seekBar!!.progress * 2.56f).toInt()
            ircmd?.setPropImageParams(
                PropImageParams.IMAGE_PROP_LEVEL_CONTRAST, 
                value.toString()
            )
        }
    })
    
    popupWindow?.setOnDismissListener {
        thermal_recycler.contrastStats = 430
    }
    
    // Position above thermal view
    popupWindow?.showAsDropDown(
        thermal_lay, 
        0, 
        getPopupWindowY(contentHeight), 
        Gravity.NO_GRAVITY
    )
}
```

### 7.2 ViewStub Lazy Loading

**Deferred Inflation Pattern:**
```kotlin
ViewStubUtils.showViewStub(view_stub_camera, true, callback = { view: View? ->
    view?.let {
        val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        
        val adapter = CameraItemAdapter(cameraItemBeanList)
        adapter.listener = { position, item ->
            handleCameraAction(item)
        }
        recyclerView.adapter = adapter
    }
})

// Hide when done
ViewStubUtils.showViewStub(view_stub_camera, false, null)
```

**Benefits:**
- Initial layout inflation faster
- Memory saved until feature used
- Progressive feature disclosure

### 7.3 Custom View Communication

**Temperature Overlay Pattern:**
```kotlin
class TemperatureView : View {
    var listener: TempListener? = null
    
    enum class RegionMode {
        REGION_MODE_POINT,
        REGION_MODE_LINE,
        REGION_MODE_RECTANGLE,
        REGION_MODE_CENTER,
        REGION_MODE_CLEAN
    }
    
    var temperatureRegionMode: RegionMode = REGION_MODE_CENTER
        set(value) {
            field = value
            clear()
            invalidate()
        }
    
    fun setTemperature(tempBytes: ByteArray) {
        // Parse temperature data
        val result = calculateMinMax(tempBytes)
        listener?.invoke(result.max, result.min, tempBytes)
    }
}

// Activity/Fragment usage
temperatureView.listener = TempListener { max, min, tempData ->
    runOnUiThread {
        tv_temp_content.text = "Max:${UnitTools.showC(max)}\nMin:${UnitTools.showC(min)}"
        AlarmHelp.getInstance().alarmData(max, min, temp_bg)
    }
}
```

---

## 8. Coroutine Architecture

### 8.1 Lifecycle-Aware Coroutines

**Best Practice Usage:**
```kotlin
lifecycleScope.launch {
    // Main thread by default
    showLoading()
    
    val result = withContext(Dispatchers.IO) {
        // Background work
        repository.downloadFile(url, file)
    }
    
    // Back to main thread automatically
    dismissLoading()
    handleResult(result)
}
```

**Structured Concurrency:**
```kotlin
lifecycleScope.launch {
    // Launch multiple parallel operations
    val deferredVersion = async(Dispatchers.IO) { 
        TS004Repository.getVersion() 
    }
    val deferredConfig = async(Dispatchers.IO) { 
        configRepository.readConfig() 
    }
    
    // Wait for both
    val version = deferredVersion.await()
    val config = deferredConfig.await()
    
    updateUI(version, config)
}
```

### 8.2 Countdown/Timer Pattern

**Reusable Countdown Extension:**
```kotlin
fun countDownCoroutines(
    total: Int,
    interval: Long,
    scope: CoroutineScope,
    onTick: (current: Int) -> Unit,
    onStart: (() -> Unit)? = null,
    onFinish: (() -> Unit)? = null
): Job {
    return scope.launch {
        onStart?.invoke()
        
        repeat(total) { current ->
            onTick(current + 1)
            delay(interval)
        }
        
        onFinish?.invoke()
    }
}

// Usage for auto-capture
val autoJob = countDownCoroutines(
    total = cameraSetting.number,
    interval = (cameraSetting.time * 1000).toLong(),
    scope = lifecycleScope,
    onTick = { camera() },
    onStart = { tv_type_ind.visibility = VISIBLE },
    onFinish = { tv_type_ind.visibility = GONE }
)
autoJob.start()
```

---

## 9. Build Flavor Architecture

### 9.1 Product Flavors

**Multi-Market Strategy:**
```gradle
flavorDimensions 'app'

productFlavors {
    dev {
        dimension 'app'
        buildConfigField "String", "SOFT_CODE", "\"TC001\""
        manifestPlaceholders = [app_name: "TopInfrared"]
    }
    
    prod {
        dimension 'app'
        buildConfigField "String", "SOFT_CODE", "\"TC001\""
        manifestPlaceholders = [app_name: "IRCamera"]
    }
    
    prodTopdon {
        dimension 'app'
        targetSdk 27  // Android 10 compatibility
        buildConfigField "String", "SOFT_CODE", "\"TC001_10\""
    }
    
    insideChina {
        dimension 'app'
        buildConfigField "int", "ENV_TYPE", "1"
        buildConfigField "String", "SOFT_CODE", "\"TC001_CN\""
        manifestPlaceholders = [app_name: "热视界"]
    }
}
```

**Runtime Flavor Detection:**
```kotlin
abstract class BaseApplication : Application() {
    abstract fun isDomestic(): Boolean
}

class App : BaseApplication() {
    override fun isDomestic(): Boolean {
        return BuildConfig.ENV_TYPE == 1
    }
}

// Usage throughout app
if (BaseApplication.instance.isDomestic()) {
    // Chinese market specific logic
    showEducationalPermissionDialog()
} else {
    // International market
    requestPermissionDirectly()
}
```

---

## 10. Key Architectural Patterns Summary

### 10.1 Design Patterns Used

| Pattern | Implementation | Benefit |
|---------|---------------|---------|
| **Singleton** | `WebSocketProxy`, `SharedManager`, repositories | Single source of truth |
| **Repository** | `TS004Repository`, `TC007Repository` | Data layer abstraction |
| **Observer** | EventBus, LiveData | Decoupled communication |
| **Template Method** | `BaseActivity`, `BaseFragment` | Consistent lifecycle |
| **Strategy** | Device-specific thermal modules | Pluggable algorithms |
| **Proxy** | `WebSocketProxy` | Connection abstraction |
| **Builder** | All dialog classes | Fluent API construction |
| **Factory** | Fragment/Adapter creation | Centralized instantiation |
| **State** | Numeric state codes | UI state management |
| **Facade** | `DeviceTools`, `AlarmHelp` | Simplified interfaces |

### 10.2 Architectural Principles

**SOLID Compliance:**
- ✅ **Single Responsibility**: Each module has clear domain (thermal processing, networking, UI)
- ✅ **Open/Closed**: New device types added without modifying existing code
- ✅ **Liskov Substitution**: All thermal activities extend `BaseIRActivity`
- ✅ **Interface Segregation**: Repositories use focused interfaces (`TS004Service`)
- ✅ **Dependency Inversion**: Activities depend on abstractions (`WebSocketProxy`, not OkHttp)

**Clean Architecture Layers:**
```
┌─────────────────────────────────────┐
│  Presentation (Activities/Fragments) │
├─────────────────────────────────────┤
│  ViewModel/LiveData                  │
├─────────────────────────────────────┤
│  Repository (Data abstraction)       │
├─────────────────────────────────────┤
│  Data Sources (WebSocket/HTTP/DB)   │
└─────────────────────────────────────┘
```

---

## 11. Recommendations for Adoption

### 11.1 Immediate Wins

1. **ARouter Integration**: Adopt for cross-module navigation without tight coupling
2. **BaseActivity Template**: Centralize device connection callbacks, permission flows
3. **WebSocket Proxy Pattern**: Single manager for all device communication
4. **SharedManager Pattern**: Type-safe SharedPreferences access
5. **EventBus for Decoupling**: Replace intent-based broadcasts with EventBus

### 11.2 Medium-Term Improvements

6. **ViewPager2 Navigation**: Migrate from manual fragment transactions
7. **PopupWindow Menus**: Replace full-screen dialogs with contextual popups
8. **Numeric State Codes**: Simplify UI state serialization
9. **Repository Pattern**: Wrap all network/DB access in repositories
10. **Lifecycle Coroutines**: Replace AsyncTask/Handlers with `lifecycleScope`

### 11.3 Long-Term Architecture

11. **Feature Modules**: Split monolithic app into dynamic feature modules
12. **Hilt/Dagger**: Replace manual singleton management
13. **Jetpack Compose**: Migrate complex custom views
14. **Kotlin Flow**: Replace LiveData for reactive streams
15. **MVI Architecture**: Unidirectional data flow for state management

---

## 12. Code Quality Observations

### 12.1 Strengths

✅ **Modular Architecture**: Clear separation between device types  
✅ **Defensive Programming**: Null checks, try-catch blocks  
✅ **Internationalization**: Full i18n support with dynamic locale switching  
✅ **Permission UX**: Educational dialogs before permission requests  
✅ **Reconnection Logic**: Robust WebSocket recovery  
✅ **State Preservation**: Survives configuration changes  
✅ **Native Optimization**: Heavy lifting in C++ (.so libraries)  

### 12.2 Technical Debt

⚠️ **Commented Code**: Large sections of IRThermalActivity commented out  
⚠️ **Global State**: Some mutable static fields in singletons  
⚠️ **Mixed Languages**: Java and Kotlin interop increases complexity  
⚠️ **Deep Nesting**: Some 4-5 level deep coroutine launches  
⚠️ **Magic Numbers**: Numeric state codes lack enum clarity  
⚠️ **Fragment Communication**: Some parent-cast patterns instead of ViewModel sharing  

---

## 13. Performance Patterns

### 13.1 Memory Management

**Bitmap Recycling:**
```kotlin
override fun onDestroy() {
    cameraViewBitmap?.recycle()
    seekBarBitmap?.recycle()
    super.onDestroy()
}
```

**Synchronized Bitmap Access:**
```kotlin
val syncimage = SynchronizedBitmap()

synchronized(syncimage.dataLock) {
    val bitmap = cameraView.bitmap
    // Process safely across threads
}
```

### 13.2 Native Integration

**JNI for Heavy Lifting:**
```kotlin
// Kotlin calls native code
LibIRProcess.getIRProcessVersion()
IRUtils.temperatureCorrection(...)

// Native libraries handle:
// - Image format conversion (Y16 to ARGB)
// - Temperature calculation
// - Pseudo-color mapping
// - Image filtering (DDE, contrast)
```

---

## Conclusion

The IRCamera architecture demonstrates **enterprise-grade Android development** with:
- Modular multi-device support
- Sophisticated state management
- Robust hardware communication
- International market readiness
- Performance-optimized rendering

Key takeaways for your project:
1. **Copy the BaseActivity pattern** for standardized lifecycle/permissions
2. **Use ARouter** for scalable navigation
3. **Adopt WebSocketProxy pattern** for device communication
4. **Implement numeric state codes** for configuration survival
5. **EventBus for global events** (connection state, settings changes)

This architecture has scaled to support 5+ device types, 2 market variants, and complex real-time thermal processing. The patterns are production-proven and worth adopting incrementally.
