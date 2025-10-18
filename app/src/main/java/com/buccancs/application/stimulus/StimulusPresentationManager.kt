package com.buccancs.application.stimulus

import android.app.Presentation
import android.content.Context
import android.hardware.display.DisplayManager
import android.hardware.display.DisplayManager.DisplayListener
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper
import android.view.Display
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.buccancs.control.commands.StimulusCommandPayload
import com.buccancs.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StimulusPresentationManager @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val scope: CoroutineScope
) {
    private val displayManager =
        context.getSystemService(
            DisplayManager::class.java
        )
    private val toneGenerator =
        ToneGenerator(
            AudioManager.STREAM_MUSIC,
            80
        )
    private val handler =
        Handler(
            Looper.getMainLooper()
        )
    private val _state =
        MutableStateFlow(
            StimulusState()
        )
    val state: StateFlow<StimulusState> =
        _state.asStateFlow()

    private var presentation: CuePresentation? =
        null
    private var presentationJob: Job? =
        null

    private val displayListener =
        object :
            DisplayListener {
            override fun onDisplayAdded(
                displayId: Int
            ) {
                updateDisplayAvailability()
            }

            override fun onDisplayRemoved(
                displayId: Int
            ) {
                if (presentation?.display?.displayId == displayId) {
                    dismissPresentation()
                }
                updateDisplayAvailability()
            }

            override fun onDisplayChanged(
                displayId: Int
            ) {
                updateDisplayAvailability()
            }
        }

    init {
        updateDisplayAvailability()
        displayManager.registerDisplayListener(
            displayListener,
            handler
        )
    }

    /**
     * Cleanup resources to prevent leaks.
     * Should be called when the manager is no longer needed.
     */
    fun shutdown() {
        scope.launch {
            presentationJob?.cancel()
            withContext(
                Dispatchers.Main
            ) {
                dismissPresentation()
            }
        }
        toneGenerator.release()
        displayManager.unregisterDisplayListener(
            displayListener
        )
        handler.removeCallbacksAndMessages(
            null
        )
    }

    fun present(
        payload: StimulusCommandPayload
    ) {
        present(
            StimulusCue.fromPayload(
                payload
            )
        )
    }

    private fun present(
        cue: StimulusCue
    ) {
        presentationJob?.cancel()
        presentationJob =
            scope.launch {
                val endsAt =
                    System.currentTimeMillis() + cue.durationMillis
                _state.update {
                    it.copy(
                        activeCue = cue,
                        activeCueEndsAtEpochMs = endsAt
                    )
                }
                withContext(
                    Dispatchers.Main
                ) {
                    showPresentation(
                        cue
                    )
                    playAudio(
                        cue
                    )
                }
                delay(
                    cue.durationMillis
                )
                withContext(
                    Dispatchers.Main
                ) {
                    dismissPresentation()
                }
                _state.update {
                    it.copy(
                        activeCue = null,
                        activeCueEndsAtEpochMs = null,
                        lastCue = cue
                    )
                }
            }
    }

    fun triggerPreview() {
        present(
            StimulusCue.preview()
        )
    }

    fun presentSyncCue(
        signalType: String
    ) {
        present(
            createSyncCue(
                signalType
            )
        )
    }

    private fun showPresentation(
        cue: StimulusCue
    ) {
        val display =
            choosePresentationDisplay()
        if (display != null) {
            val presentation =
                CuePresentation(
                    context,
                    display,
                    cue
                )
            presentation.show()
            this.presentation =
                presentation
        }
    }

    private fun dismissPresentation() {
        presentation?.dismiss()
        presentation =
            null
    }

    private fun playAudio(
        cue: StimulusCue
    ) {
        when (cue.audio) {
            StimulusAudio.NONE -> Unit
            StimulusAudio.BEEP -> toneGenerator.startTone(
                ToneGenerator.TONE_PROP_BEEP,
                cue.durationMillis.toInt()
            )
        }
    }

    private fun choosePresentationDisplay(): Display? {
        val presentationDisplays =
            displayManager.getDisplays(
                DisplayManager.DISPLAY_CATEGORY_PRESENTATION
            )
        if (presentationDisplays.isNotEmpty()) {
            return presentationDisplays.first()
        }
        // fallback to any non-default external display
        return displayManager.displays.firstOrNull { it.displayId != Display.DEFAULT_DISPLAY }
    }

    private fun updateDisplayAvailability() {
        val hasExternal =
            displayManager.getDisplays(
                DisplayManager.DISPLAY_CATEGORY_PRESENTATION
            )
                .isNotEmpty() ||
                    displayManager.displays.any { it.displayId != Display.DEFAULT_DISPLAY }
        _state.update {
            it.copy(
                hasExternalDisplay = hasExternal
            )
        }
    }

    companion object {
        internal fun createSyncCue(
            signalType: String,
            nowMs: Long = System.currentTimeMillis()
        ): StimulusCue {
            val safeSignal =
                signalType.ifBlank { "flash" }
            return StimulusCue.preview()
                .copy(
                    id = "sync-$safeSignal-$nowMs",
                    action = "SyncSignal",
                    label = safeSignal.uppercase(
                        Locale.UK
                    ),
                    metadata = mapOf(
                        "signalType" to safeSignal
                    )
                )
        }
    }

    private class CuePresentation(
        outerContext: Context,
        display: Display,
        private val cue: StimulusCue
    ) : Presentation(
        outerContext,
        display
    ) {
        override fun onCreate(
            savedInstanceState: android.os.Bundle?
        ) {
            super.onCreate(
                savedInstanceState
            )
            val frame =
                FrameLayout(
                    context
                ).apply {
                    setBackgroundColor(
                        cue.color
                    )
                }
            val textView =
                TextView(
                    context
                ).apply {
                    text =
                        cue.label
                    setTextColor(
                        pickTextColor(
                            cue.color
                        )
                    )
                    textSize =
                        48f
                    gravity =
                        Gravity.CENTER
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                            .apply {
                                gravity =
                                    Gravity.CENTER
                            }
                }
            frame.addView(
                textView
            )
            setContentView(
                frame
            )
        }

        @ColorInt
        private fun pickTextColor(
            @ColorInt background: Int
        ): Int {
            val darkness =
                1 - (0.299 * android.graphics.Color.red(
                    background
                ) + 0.587 * android.graphics.Color.green(
                    background
                ) + 0.114 * android.graphics.Color.blue(
                    background
                )) / 255
            return if (darkness >= 0.5) android.graphics.Color.WHITE else android.graphics.Color.BLACK
        }
    }
}

