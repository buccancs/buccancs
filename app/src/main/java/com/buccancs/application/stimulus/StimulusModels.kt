package com.buccancs.application.stimulus

import androidx.annotation.ColorInt
import com.buccancs.control.commands.StimulusCommandPayload

data class StimulusCue(
    val id: String,
    val action: String,
    val label: String,
    @ColorInt val color: Int,
    val durationMillis: Long,
    val audio: StimulusAudio,
    val metadata: Map<String, String>
) {
    companion object {
        fun fromPayload(payload: StimulusCommandPayload): StimulusCue {
            val metadata = payload.metadata
            val color = metadata["color"]?.let(::parseColor) ?: DEFAULT_COLOR
            val duration = metadata["durationMs"]?.toLongOrNull()?.coerceIn(100L, 30_000L)
                ?: DEFAULT_DURATION_MS
            val audio = metadata["audio"]?.let { StimulusAudio.fromKey(it) } ?: StimulusAudio.BEEP
            val label = metadata["label"].orEmpty()
                .ifBlank { payload.action.ifBlank { payload.stimulusId } }
            return StimulusCue(
                id = payload.stimulusId,
                action = payload.action,
                label = label,
                color = color,
                durationMillis = duration,
                audio = audio,
                metadata = metadata
            )
        }

        fun preview(): StimulusCue = StimulusCue(
            id = "preview",
            action = "Preview",
            label = "Preview",
            color = DEFAULT_COLOR,
            durationMillis = DEFAULT_DURATION_MS,
            audio = StimulusAudio.BEEP,
            metadata = emptyMap()
        )

        private fun parseColor(raw: String): Int = runCatching {
            android.graphics.Color.parseColor(raw)
        }.getOrElse { DEFAULT_COLOR }

        private const val DEFAULT_DURATION_MS = 1_000L
        private const val DEFAULT_COLOR = android.graphics.Color.WHITE
    }
}

enum class StimulusAudio {
    NONE,
    BEEP;

    companion object {
        fun fromKey(key: String): StimulusAudio = when (key.lowercase()) {
            "none" -> NONE
            else -> BEEP
        }
    }
}

data class StimulusState(
    val hasExternalDisplay: Boolean = false,
    val activeCue: StimulusCue? = null,
    val activeCueEndsAtEpochMs: Long? = null,
    val lastCue: StimulusCue? = null
)
