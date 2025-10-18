package com.topdon.lms.sdk.network

import com.topdon.lms.sdk.xutils.http.RequestParams

/**
 * Stub implementation of LMS SDK HttpProxy
 */
class HttpProxy private constructor() {

    companion object {
        @Volatile
        private var instance: HttpProxy? =
            null

        val instant: HttpProxy
            get() = instance
                ?: synchronized(
                    this
                ) {
                    instance
                        ?: HttpProxy().also {
                            instance =
                                it
                        }
                }
    }

    fun post(
        url: String,
        needToken: Boolean,
        params: RequestParams,
        callback: IResponseCallback?
    ) {
        // Stub: no-op HTTP call
        callback?.onFail(
            Exception(
                "Stub: HttpProxy not implemented"
            )
        )
    }

    fun post(
        url: String,
        params: RequestParams,
        callback: IResponseCallback?
    ) {
        post(
            url,
            false,
            params,
            callback
        )
    }

    fun get(
        url: String,
        needToken: Boolean,
        params: RequestParams,
        callback: IResponseCallback?
    ) {
        // Stub: no-op HTTP call
        callback?.onFail(
            Exception(
                "Stub: HttpProxy not implemented"
            )
        )
    }

    fun get(
        url: String,
        params: RequestParams,
        callback: IResponseCallback?
    ) {
        get(
            url,
            false,
            params,
            callback
        )
    }

    fun put(
        url: String,
        needToken: Boolean,
        params: RequestParams,
        callback: IResponseCallback?
    ) {
        // Stub: no-op HTTP call
        callback?.onError(
            Exception(
                "Stub: HttpProxy not implemented"
            ),
            false
        )
    }

    fun delete(
        url: String,
        needToken: Boolean,
        params: RequestParams,
        callback: IResponseCallback?
    ) {
        // Stub: no-op HTTP call
        callback?.onError(
            Exception(
                "Stub: HttpProxy not implemented"
            ),
            false
        )
    }
}
