package com.topdon.lms.sdk.utils

import android.content.Context
import java.util.Locale

/**
 * Stub implementation of LMS SDK LanguageUtil
 */
object LanguageUtil {
    fun getLanguageId(
        context: Context?
    ): Int {
        // Stub: return English language ID
        val locale =
            context?.resources?.configuration?.locales?.get(
                0
            )
                ?: Locale.getDefault()
        return when (locale.language) {
            "zh" -> 1  // Chinese
            "es" -> 2  // Spanish
            "fr" -> 3  // French
            "de" -> 4  // German
            "ja" -> 5  // Japanese
            "ko" -> 6  // Korean
            else -> 0  // English (default)
        }
    }

    fun getLanguageCode(
        context: Context?
    ): String {
        val locale =
            context?.resources?.configuration?.locales?.get(
                0
            )
                ?: Locale.getDefault()
        return locale.language
    }
}
