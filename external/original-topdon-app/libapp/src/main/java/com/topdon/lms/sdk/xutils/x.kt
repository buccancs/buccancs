package com.topdon.lms.sdk.xutils

import com.topdon.lms.sdk.xutils.common.Callback
import com.topdon.lms.sdk.xutils.http.RequestParams

/**
 * Stub implementation of xutils3 main class
 */
object x {
    fun http(): HttpManager = HttpManager

    object HttpManager {
        fun <T> get(params: RequestParams, callback: Callback.CommonCallback<T>?) {
            // Stub: no-op HTTP call
            callback?.onError(Exception("Stub: xutils3 not implemented"), false)
        }

        fun <T> post(params: RequestParams, callback: Callback.CommonCallback<T>?) {
            // Stub: no-op HTTP call
            callback?.onError(Exception("Stub: xutils3 not implemented"), false)
        }

        fun <T> put(params: RequestParams, callback: Callback.CommonCallback<T>?) {
            // Stub: no-op HTTP call
            callback?.onError(Exception("Stub: xutils3 not implemented"), false)
        }

        fun <T> delete(params: RequestParams, callback: Callback.CommonCallback<T>?) {
            // Stub: no-op HTTP call
            callback?.onError(Exception("Stub: xutils3 not implemented"), false)
        }
    }
}
