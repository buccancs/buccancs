package com.topdon.lms.sdk.utils

import android.content.Context

/**
 * Stub implementation of LMS SDK StringUtils
 */
object StringUtils {
    fun getResString(
        context: Context?,
        resId: Int
    ): String {
        return try {
            context?.getString(
                resId
            )
                ?: "Error: resId=$resId"
        } catch (e: Exception) {
            "Error: resId=$resId"
        }
    }
}
