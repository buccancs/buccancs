package kotlinx.datetime

fun Instant.toEpochMilliseconds(): Long =
    epochSeconds * 1_000 + nanosecondsOfSecond / 1_000_000

