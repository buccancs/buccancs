package com.topdon.lms.sdk.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Stub implementation of LMS SDK DateUtils
 */
object DateUtils {
    fun formatDate(date: Date?, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        if (date == null) return ""
        return try {
            SimpleDateFormat(pattern, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            ""
        }
    }
    
    fun formatDate(timestamp: Long, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        return formatDate(Date(timestamp), pattern)
    }
    
    fun format(timestamp: Long, pattern: String, timeZone: TimeZone): String {
        return try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.timeZone = timeZone
            sdf.format(Date(timestamp))
        } catch (e: Exception) {
            ""
        }
    }
    
    fun parseDate(dateString: String?, pattern: String = "yyyy-MM-dd HH:mm:ss"): Date? {
        if (dateString.isNullOrEmpty()) return null
        return try {
            SimpleDateFormat(pattern, Locale.getDefault()).parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
}
