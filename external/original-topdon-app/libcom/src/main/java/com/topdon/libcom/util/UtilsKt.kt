package com.topdon.libcom.util

import com.blankj.utilcode.util.SizeUtils

val Float.dp: Float
    get() = SizeUtils.dp2px(
        this
    )
        .toFloat()
val Int.dp: Int
    get() = SizeUtils.dp2px(
        this.toFloat()
    )
val Float.sp: Float
    get() = SizeUtils.sp2px(
        this
    )
        .toFloat()
val Int.sp: Float
    get() = SizeUtils.sp2px(
        this.toFloat()
    )
        .toFloat()
