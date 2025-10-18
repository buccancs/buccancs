package com.topdon.module.user.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.topdon.lib.core.config.ExtraKeyConfig
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.ktbase.BaseActivity
import com.topdon.lib.core.repository.ProductBean
import com.topdon.lib.core.repository.TS004Repository
import com.topdon.module.user.R
// Stubbed out - kotlinx.android.synthetic is deprecated
// // Stubbed: import kotlinx.android.synthetic.main.activity_device_details.*
import kotlinx.coroutines.launch

@Route(
    path = RouterConfig.DEVICE_INFORMATION
)
class DeviceDetailsActivity :
    BaseActivity(),
    View.OnClickListener {
    private var isTC007 =
        false

    override fun initContentView() =
        R.layout.activity_device_details

    override fun initView() {
        isTC007 =
            intent.getBooleanExtra(
                ExtraKeyConfig.IS_TC007,
                false
            )
        // Stubbed: cl_layout_copy.setOnClickListener(this)
    }

    override fun initData() {
        getDeviceDetails()
    }

    private fun getDeviceDetails() {
        lifecycleScope.launch {
            // Stubbed out implementation - requires view binding migration
            /*
            if (isTC007) {
                if (productBean == null) {
                } else {
                    tv_sn_value.text = productBean.ProductSN
                    tv_device_model_value.text = productBean.ProductName
                }
            } else {
                val deviceDetailsBean = TS004Repository.getDeviceInfo()
                if (deviceDetailsBean?.isSuccess()!!) {
                    TLog.d("ts004-->response", "${deviceDetailsBean.data}")
                    tv_sn_value.text = deviceDetailsBean.data!!.sn
                    tv_device_model_value.text = deviceDetailsBean.data!!.model
                } else {
                }
            }
            */
        }
    }

    override fun onClick(
        v: View?
    ) {
        when (v) {
            cl_layout_copy -> {
                val text =
                    "${tv_sn.text}:${tv_sn_value.text}  ${tv_device_model.text}:${tv_device_model_value.text}"
                val cm =
                    getSystemService(
                        CLIPBOARD_SERVICE
                    ) as ClipboardManager?
                val mClipData =
                    ClipData.newPlainText(
                        "text",
                        text
                    )
                cm!!.setPrimaryClip(
                    mClipData
                )
            }
        }
    }
}