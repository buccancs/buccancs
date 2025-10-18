package com.topdon.lms.sdk.network

/**
 * Stub implementation of LMS SDK callback interface
 */
interface IResponseCallback {
    fun onResponse(
        response: String?
    )

    fun onFail(
        exception: Exception?
    ) {
    }

    fun onFail(
        failMsg: String?,
        errorCode: String
    ) {
    }

    fun onError(
        exception: Exception?,
        isOnCallback: Boolean
    ) {
        onFail(
            exception
        )
    }
}
