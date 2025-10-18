package com.topdon.tc001.viewmodel

import androidx.lifecycle.viewModelScope
import com.topdon.lib.core.ktbase.BaseViewModel
import com.topdon.lib.core.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PolicyViewModel :
    BaseViewModel() {
    val htmlViewData =
        SingleLiveEvent<HtmlBean>()

    fun getUrl(
        type: Int
    ) {
        // Internet functionality removed - return empty
        htmlViewData.postValue(
            HtmlBean()
        )
    }

    data class HtmlBean(
        val body: String? = null,
        val action: Int = 0
    )
}