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

        // Stubbed out network call - requires NetworkUtil and other dependencies
        // Original implementation had malformed override methods
        result = ReportData()
        result?.msg = "Stubbed implementation"
        result?.code = 0
        downLatch.countDown()

        withContext(Dispatchers.IO) {
            downLatch.await()
        }
        return result
    }
}