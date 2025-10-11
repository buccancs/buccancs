package com.guide.zm04c.matrix.utils

import java.util.*

object ByteUtils {

    /**
     * byte[] => int
     */
    fun byteToInt(bytes: ByteArray): Int {
        var count = 0
        var b = 0
        for (i in bytes.size - 1 downTo 0) {
            b = bytes[i].toInt().and(0xff)
            count += b shl (8 * (bytes.size - i - 1))
        }
        return count
    }

    /**
     * byte[] => string
     * [0x01, 0x02] => 01 02
     */
//    @ExperimentalUnsignedTypes // just to make it clear that the experimental unsigned types are used
    fun ByteArray.toHexString() = asUByteArray().joinToString(" ") {
        it.toString(16).padStart(2, '0').uppercase(Locale.getDefault())
    }

    /**
     * string => byte[]
     * 0102 => [0x01, 0x02]
     */
    @ExperimentalUnsignedTypes
    fun String.hexStringToByteArray() =
        ByteArray(this.length / 2) { this.substring(it * 2, it * 2 + 2).toInt(16).toByte() }

    /**
     * UUID => ff01
     */
    fun UUID.getTag() = toString().substring(4, 8)
}