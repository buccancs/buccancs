package com.topdon.lms.sdk.xutils.http

/**
 * Stub implementation of xutils3 RequestParams
 */
class RequestParams {
    var uri: String = ""
    var isAsJsonContent: Boolean = false
    private val bodyParams = mutableMapOf<String, Any?>()
    private val queryParams = mutableMapOf<String, Any?>()

    fun addBodyParameter(key: String, value: Any?) {
        bodyParams[key] = value
    }

    fun addQueryStringParameter(key: String, value: String?) {
        queryParams[key] = value
    }

    fun getBodyParams(): Map<String, Any?> = bodyParams
    fun getQueryParams(): Map<String, Any?> = queryParams
}
