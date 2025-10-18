@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.buccancs.domain.model

import kotlin.time.Instant

data class RecordingBookmark(
    val id: String,
    val label: String,
    val timestamp: Instant,
    val createdAt: Instant = timestamp
)
