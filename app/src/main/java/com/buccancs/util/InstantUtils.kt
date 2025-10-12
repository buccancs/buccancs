package com.buccancs.util

import com.buccancs.util.nowInstant
import kotlinx.datetime.Instant
import kotlin.system.getTimeMillis

fun nowInstant(): Instant = Instant.fromEpochMilliseconds(getTimeMillis())
