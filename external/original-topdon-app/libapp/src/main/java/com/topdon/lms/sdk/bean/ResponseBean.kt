package com.topdon.lms.sdk.bean

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Stub implementation of LMS SDK ResponseBean
 */
data class ResponseBean<T>(
    var code: Int? = null,
    var msg: String? = null,
    var data: T? = null,
    var success: Boolean = false
) {
    companion object {
        fun convertCommonBean(json: String?, defaultValue: CommonBean<*>?): CommonBean<*> {
            return try {
                if (json.isNullOrEmpty()) {
                    defaultValue ?: CommonBean<String>()
                } else {
                    val typeOfT = object : TypeToken<CommonBean<String>>() {}.type
                    Gson().fromJson<CommonBean<String>>(json, typeOfT) ?: defaultValue
                    ?: CommonBean<String>()
                }
            } catch (e: Exception) {
                defaultValue ?: CommonBean<String>()
            }
        }
    }
}
