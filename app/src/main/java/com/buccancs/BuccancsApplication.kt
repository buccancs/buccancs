package com.buccancs

import android.app.Application
import com.clj.fastble.BleManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BuccancsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BleManager.getInstance().init(this)
        BleManager.getInstance().enableLog(false)
    }
}
