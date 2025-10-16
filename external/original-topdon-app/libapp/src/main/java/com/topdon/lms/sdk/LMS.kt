package com.topdon.lms.sdk

import android.content.Context
import com.topdon.lms.sdk.network.IResponseCallback
import com.topdon.lms.sdk.bean.CommonBean
import com.topdon.lms.sdk.bean.UserInfo

/**
 * Stub implementation of LMS SDK
 * Original LMS SDK is proprietary and not available
 */
class LMS private constructor() {
    
    var token: String? = "stub_token"  // Default stub token
    val isLogin: Boolean
        get() = token != null
    
    companion object {
        @Volatile
        private var instance: LMS? = null
        
        var mContext: Context? = null
        
        const val SUCCESS = 0
        
        fun getInstance(): LMS {
            return instance ?: synchronized(this) {
                instance ?: LMS().also { instance = it }
            }
        }
    }
    
    fun checkAppUpdate(callback: (ResponseData) -> Unit) {
        // Stub: no update available
        callback(ResponseData(code = 2000, data = "{}"))
    }
    
    fun getStatement(type: String, callback: IResponseCallback) {
        // Stub: return empty statement
        callback.onResponse("{}")
    }
    
    fun syncUserInfo(callback: ((CommonBean<UserInfo>) -> Unit)? = null) {
        // Stub: return empty user info
        callback?.invoke(CommonBean(code = "0", msg = "Stub", data = UserInfo()))
    }
    
    fun getUserInfo(callback: ((CommonBean<String>) -> Unit)?) {
        // Stub: return empty user info as JSON string with all required fields
        val jsonData = """{"topdonId":"","userName":"","email":"","url":"","pwd":"","remark":"","createTime":0,"updateTime":0,"profilePicture":"","lastVisitTime":"","phone":"","avatar":""}"""
        callback?.invoke(CommonBean(code = "0", msg = "Stub", data = jsonData))
    }
    
    fun getUserInfo(): UserInfo? {
        // Stub: no user info
        return null
    }
    
    fun bindDevice(sn: String, randomNum: String, callback: ((Int) -> Unit)? = null): Int {
        // Stub: return success code
        callback?.invoke(SUCCESS)
        return SUCCESS
    }
    
    fun bindDevice(sn: String, randomNum: String, param3: String, param4: String, callback: ((ResponseData) -> Unit)?) {
        // Stub: return success response
        callback?.invoke(ResponseData(code = SUCCESS, data = "{}"))
    }
    
    data class ResponseData(
        val code: Int,
        val data: String
    )
}
