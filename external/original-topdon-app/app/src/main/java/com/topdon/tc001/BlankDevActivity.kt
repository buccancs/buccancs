package com.topdon.tc001

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.topdon.lib.core.common.SharedManager
import com.topdon.lib.core.config.RouterConfig
import com.topdon.module.thermal.ir.activity.IRMainActivity
import com.topdon.tc001.app.App

class BlankDevActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (SharedManager.getHasShowClause()) {
            if (!App.instance.activityNameList.contains(IRMainActivity::class.simpleName)) {
                ARouter.getInstance().build(RouterConfig.MAIN).navigation(this)
                if (!SharedManager.isConnectAutoOpen) {
                    ARouter.getInstance().build(RouterConfig.IR_MAIN).navigation(this)
                }
            }
            finish()
        } else {
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }
    }
}