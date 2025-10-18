package com.topdon.lms.sdk.weiget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Stub implementation of LMS SDK LmsLoadView
 * Simple loading view placeholder
 */
class LmsLoadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {

    init {
        // Stub: minimal initialization
    }

    fun show() {
        visibility =
            VISIBLE
    }

    fun hide() {
        visibility =
            GONE
    }

    fun setLoadingText(
        text: String?
    ) {
        // Stub: no-op
    }
}
