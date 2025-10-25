package com.buccancs.desktop.ui.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

/**
 * Attempts to decode a raw preview payload into an [ImageBitmap].
 * Returns `null` for unsupported or empty payloads so callers can fall back gracefully.
 */
fun decodePreviewImage(
    bytes: ByteArray
): ImageBitmap? {
    if (bytes.isEmpty()) {
        return null
    }
    return runCatching {
        Image.makeFromEncoded(
            bytes
        )
            .toComposeImageBitmap()
    }.getOrNull()
}
