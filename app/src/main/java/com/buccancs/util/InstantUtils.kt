package com.buccancs.util

import kotlinx.datetime.Instant

fun nowInstant(): Instant = Instant.fromEpochMilliseconds(System.currentTimeMillis())
