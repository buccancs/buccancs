package com.topdon.thermal07.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.ktbase.BaseActivity
import com.topdon.lib.core.repository.TC007Repository
import com.topdon.module.thermal.ir.R
import com.topdon.module.thermal.ir.repository.ConfigRepository
import com.topdon.tc004.activity.video.PlayFragment
import kotlinx.coroutines.launch
import org.easydarwin.video.Client

/**
 *
 * 锅盖矫正
 * @author: CaiSongL
 * @date: 2023/8/4 9:06
 */
@Route(path = RouterConfig.IR_CORRECTION_07)
class IR07CorrectionThreeActivity : BaseActivity() {

    companion object {
        private const val RTSP_URL = "rtsp://192.168.40.1/stream0"
    }

    override fun initContentView(): Int = R.layout.activity_ir_correction_three

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val playFragment = PlayFragment.newInstance(RTSP_URL, Client.TRANSTYPE_TCP, 1, null,true)
            supportFragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.fragment_container_view, playFragment).commit()
        }

        val tvCorrection: TextView = findViewById(R.id.tv_correction)
        tvCorrection.setOnClickListener {
            val intent = Intent(this, IR07CorrectionFourActivity::class.java)
            startActivity(intent)
            finish()
        }

        lifecycleScope.launch {
            // 读取配置设置 环境温度、测温距离、发射率
            val config = ConfigRepository.readConfig(true)
            TC007Repository.setIRConfig(config.environment, config.distance, config.radiation)
            //清除点、线、面
            TC007Repository.clearAllTemp()
            //全图也关掉
            TC007Repository.setTempFrame(false)
        }
    }

    override fun onSocketDisConnected(isTS004: Boolean) {
        if (!isTS004) {//TC007 的 Socket 断了
            finish()
        }
    }

    override fun initView() {
    }

    override fun initData() {}
}