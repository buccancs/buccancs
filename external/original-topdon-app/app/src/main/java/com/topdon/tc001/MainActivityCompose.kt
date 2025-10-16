package com.topdon.tc001

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.AppUtils
import com.elvishew.xlog.XLog
import com.example.suplib.wrapper.SupHelp
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.topdon.lib.core.BaseApplication
import com.topdon.lib.core.common.SharedManager
import com.topdon.lib.core.config.AppConfig
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.dialog.FirmwareUpDialog
import com.topdon.lib.core.ktbase.BaseActivity
import com.topdon.lib.core.socket.WebSocketProxy
import com.topdon.lib.core.tools.DeviceTools
import com.topdon.lib.core.viewmodel.VersionViewModel
import com.topdon.tc001.app.App
import com.topdon.tc001.ui.screens.DeviceInfo
import com.topdon.tc001.ui.screens.DeviceType
import com.topdon.tc001.ui.screens.GalleryImage
import com.topdon.tc001.ui.screens.MainContainerScreen
import com.topdon.tc001.ui.theme.TopdonTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Route(path = RouterConfig.MAIN)
class MainActivityCompose : ComponentActivity() {
    private val versionViewModel: VersionViewModel by viewModels()

    private var devices by mutableStateOf<List<DeviceInfo>>(emptyList())
    private var galleryImages by mutableStateOf<List<GalleryImage>>(emptyList())
    private var isGalleryLoading by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logInfo()
        initializeApp()
        checkPermissions()

        setContent {
            TopdonTheme {
                MainContainerScreen(
                    devices = devices,
                    galleryImages = galleryImages,
                    isGalleryLoading = isGalleryLoading,
                    userName = null,
                    userEmail = null,
                    isLoggedIn = false,
                    currentLanguage = SharedManager.getLanguage(this),
                    appVersion = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                    onConnectDeviceClick = { navigateToDeviceType() },
                    onAddDeviceClick = { navigateToDeviceType() },
                    onDeviceClick = { deviceType ->
                        when (deviceType) {
                            DeviceType.TC001_LINE -> {
                                ARouter.getInstance()
                                    .build(RouterConfig.IR_MAIN)
                                    .withBoolean("IS_TC007", false)
                                    .navigation(this)
                            }

                            DeviceType.TS004 -> {
                                if (WebSocketProxy.getInstance().isTS004Connect()) {
                                    ARouter.getInstance()
                                        .build(RouterConfig.IR_MONOCULAR)
                                        .navigation(this)
                                } else {
                                    ARouter.getInstance()
                                        .build(RouterConfig.IR_DEVICE_ADD)
                                        .withBoolean("isTS004", true)
                                        .navigation(this)
                                }
                            }

                            DeviceType.TC007 -> {
                                ARouter.getInstance()
                                    .build(RouterConfig.IR_MAIN)
                                    .withBoolean("IS_TC007", true)
                                    .navigation(this)
                            }
                        }
                    },
                    onDeviceLongClick = { deviceType ->
                        // Show delete confirmation
                    },
                    onImageClick = { image ->
                        // Navigate to image viewer
                    },
                    onProfileClick = {
                        // Navigate to profile
                    },
                    onLanguageClick = {
                        // Navigate to language selection
                    },
                    onMoreSettingsClick = {
                        ARouter.getInstance()
                            .build(RouterConfig.MORE)
                            .navigation(this)
                    },
                    onAboutClick = {
                        ARouter.getInstance()
                            .build(RouterConfig.VERSION)
                            .navigation(this)
                    },
                    onLogoutClick = {
                        // Handle logout
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshDevices()
    }

    private fun initializeApp() {
        lifecycleScope.launch(Dispatchers.IO) {
            SupHelp.getInstance().initAiUpScaler(applicationContext)
        }

        App.instance.initWebSocket()
        BaseApplication.instance.clearDb()

        if (BaseApplication.instance.isDomestic()) {
            // Check app version for domestic users
        } else {
            versionViewModel.checkVersion()
        }

        autoConnectDevices()
    }

    private fun autoConnectDevices() {
        if (!SharedManager.hasTcLine && !SharedManager.hasTS004 && !SharedManager.hasTC007) {
            if (DeviceTools.isConnect()) {
                if (!WebSocketProxy.getInstance().isConnected()) {
                    ARouter.getInstance()
                        .build(RouterConfig.IR_MAIN)
                        .withBoolean("IS_TC007", false)
                        .navigation(this)
                }
            } else {
                if (WebSocketProxy.getInstance().isTS004Connect()) {
                    ARouter.getInstance()
                        .build(RouterConfig.IR_MONOCULAR)
                        .navigation(this)
                } else if (WebSocketProxy.getInstance().isTC007Connect()) {
                    ARouter.getInstance()
                        .build(RouterConfig.IR_MAIN)
                        .withBoolean("IS_TC007", true)
                        .navigation(this)
                }
            }
        }

        if (DeviceTools.isConnect()) {
            SharedManager.hasTcLine = true
        }
        if (WebSocketProxy.getInstance().isTS004Connect()) {
            SharedManager.hasTS004 = true
        }
        if (WebSocketProxy.getInstance().isTC007Connect()) {
            SharedManager.hasTC007 = true
        }
    }

    private fun refreshDevices() {
        val deviceList = mutableListOf<DeviceInfo>()

        if (SharedManager.hasTcLine) {
            deviceList.add(
                DeviceInfo(
                    name = "TC001 (Line)",
                    type = DeviceType.TC001_LINE,
                    isConnected = DeviceTools.isConnect(isAutoRequest = false)
                )
            )
        }

        if (SharedManager.hasTS004) {
            deviceList.add(
                DeviceInfo(
                    name = "TS004",
                    type = DeviceType.TS004,
                    isConnected = WebSocketProxy.getInstance().isTS004Connect()
                )
            )
        }

        if (SharedManager.hasTC007) {
            deviceList.add(
                DeviceInfo(
                    name = "TC007",
                    type = DeviceType.TC007,
                    isConnected = WebSocketProxy.getInstance().isTC007Connect()
                )
            )
        }

        devices = deviceList
    }

    private fun navigateToDeviceType() {
        ARouter.getInstance()
            .build(RouterConfig.DEVICE_TYPE)
            .navigation(this)
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            XXPermissions.with(this)
                .permission(
                    Permission.CAMERA,
                    Permission.READ_MEDIA_IMAGES,
                    Permission.READ_MEDIA_VIDEO
                )
                .request { _, _ -> }
        } else {
            XXPermissions.with(this)
                .permission(
                    Permission.CAMERA,
                    Permission.READ_EXTERNAL_STORAGE,
                    Permission.WRITE_EXTERNAL_STORAGE
                )
                .request { _, _ -> }
        }
    }

    private fun logInfo() {
        try {
            val str = StringBuilder()
            str.append("Info").append("\n")
            str.append("FLAVOR: ${BuildConfig.FLAVOR}").append("\n")
            str.append("VERSION_CODE: ${BuildConfig.VERSION_CODE}").append("\n")
            str.append("VERSION_NAME: ${BuildConfig.VERSION_NAME}").append("\n")
            str.append("VERSION_DATE: ${BuildConfig.VERSION_DATE}").append("\n")
            str.append("BRAND: ${Build.BRAND}").append("\n")
            str.append("MODEL: ${Build.MODEL}").append("\n")
            str.append("PRODUCT: ${Build.PRODUCT}").append("\n")
            str.append("CPU_ABI: ${Build.CPU_ABI}").append("\n")
            str.append("SDK_INT: ${Build.VERSION.SDK_INT}").append("\n")
            str.append("RELEASE: ${Build.VERSION.RELEASE}").append("\n")
            if (SharedManager.getHasShowClause()) {
                XLog.i(str)
            }
        } catch (e: Exception) {
            if (SharedManager.getHasShowClause()) {
                XLog.e("log error: ${e.message}")
            }
        }
    }
}
