package com.topdon.lms.sdk.weiget

import android.content.Context
import android.widget.Toast

/**
 * Stub implementation of LMS SDK TToast
 */
object TToast {
    fun showShort(
        context: Context?,
        message: String?
    ) {
        context?.let {
            Toast.makeText(
                it,
                message
                    ?: "",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    fun showLong(
        context: Context?,
        message: String?
    ) {
        context?.let {
            Toast.makeText(
                it,
                message
                    ?: "",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    fun shortToast(
        context: Context?,
        messageResId: Int
    ) {
        context?.let {
            Toast.makeText(
                it,
                it.getString(
                    messageResId
                ),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    fun longToast(
        context: Context?,
        messageResId: Int
    ) {
        context?.let {
            Toast.makeText(
                it,
                it.getString(
                    messageResId
                ),
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
}
