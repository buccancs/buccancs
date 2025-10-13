package com.buccancs.util

import kotlinx.datetime.Instant

fun nowInstant(): Instant = Instant.fromEpochMilliseconds(System.currentTimeMillis())

fun Instant.toEpochMillis(): Long =
    (epochSeconds * 1_000) + (nanosecondsOfSecond / 1_000_000)
