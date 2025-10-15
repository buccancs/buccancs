package com.topdon.module.thermal.ir.report.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.Utils
import com.google.gson.Gson
import com.topdon.lib.core.common.SharedManager
import com.topdon.lib.core.ktbase.BaseViewModel
import com.topdon.lib.core.tools.TimeTool
import com.topdon.libcom.R
import com.topdon.module.thermal.ir.report.bean.ReportData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CountDownLatch

class PdfViewModel : BaseViewModel() {
    val listData = MutableLiveData<ReportData?>()

    fun getReportData(isTC007: Boolean, page: Int) {
        if (!NetworkUtil.isConnected(Utils.getApp())) {
            listData.postValue(null)
            return
        }
        viewModelScope.launch {
            val data = getReportDataRepository(isTC007, page)
            listData.postValue(data)
        }
    }

    private suspend fun getReportDataRepository(isTC007: Boolean, page: Int): ReportData? {
        var result: ReportData? = null
        val downLatch = CountDownLatch(1)
        override fun onResponse(p0: String?) {
            result = Gson().fromJson(p0, ReportData::class.java)
            downLatch.countDown()
        }

        override fun onFail(p0: Exception?) {
            result = ReportData()
            result?.msg = p0?.message
            result?.code = -1
            downLatch.countDown()
            TLog.e("bcf", "获取报告列表失败：" + p0?.message)
        }

        override fun onFail(failMsg: String?, errorCode: String) {
            super.onFail(failMsg, errorCode)
            try {
                StringUtils.getResString(
                    LMS.mContext,
                    if (TextUtils.isEmpty(errorCode)) -500 else errorCode.toInt()
                ).let {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    })
    withContext(Dispatchers.IO)
    {
        downLatch.await()
    }
    return result
}
}