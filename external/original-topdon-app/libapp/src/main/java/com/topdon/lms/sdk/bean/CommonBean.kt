package com.topdon.lms.sdk.bean

/**
 * Stub implementation of LMS SDK CommonBean
 */
data class CommonBean<T>(
    var code: String? = null,
    var msg: String? = null,
    var data: T? = null
)

/**
 * User info data class
 */
data class UserInfo(
    var token: String? = null,
    var topdonId: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var nickname: String? = null,
    var userName: String? = null,
    var avatar: String? = null
)
