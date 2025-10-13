package com.buccancs.domain.model

import kotlinx.datetime.Instant

data class RecordingBookmark(
    val id: String,
    val label: String,
    val timestamp: Instant,
    val createdAt: Instant = timestamp
)
