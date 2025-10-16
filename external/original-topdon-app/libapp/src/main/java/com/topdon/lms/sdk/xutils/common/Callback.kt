package com.topdon.lms.sdk.xutils.common

/**
 * Stub implementation of xutils3 Callback
 */
interface Callback {
    interface CommonCallback<T> {
        fun onSuccess(result: T?)
        fun onError(ex: Throwable?, isOnCallback: Boolean)
        fun onCancelled(cex: CancelledException?)
        fun onFinished()
    }
    
    class CancelledException(message: String?) : Exception(message)
}
