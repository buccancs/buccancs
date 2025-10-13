package com.buccancs

import android.app.Application
import com.buccancs.data.orchestration.DeviceOrchestratorBridge
import com.buccancs.data.storage.SpaceMonitor
import com.buccancs.data.transfer.WorkPolicy
import com.clj.fastble.BleManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BuccancsApplication : Application() {
    @Inject
    lateinit var orchestratorBridge: DeviceOrchestratorBridge
    @Inject
    lateinit var spaceMonitor: SpaceMonitor

    override fun onCreate() {
        super.onCreate()
        BleManager.getInstance().init(this)
        BleManager.getInstance().enableLog(false)
        spaceMonitor.start()
        WorkPolicy.scheduleRetention(this)
    }
}
