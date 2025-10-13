package com.buccancs

import android.app.Application
import com.buccancs.data.orchestration.DeviceOrchestratorBridge
import com.clj.fastble.BleManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BuccancsApplication : Application() {
    @Inject
    lateinit var orchestratorBridge: DeviceOrchestratorBridge

    override fun onCreate() {
        super.onCreate()
        BleManager.getInstance().init(this)
        BleManager.getInstance().enableLog(false)
    }
}
