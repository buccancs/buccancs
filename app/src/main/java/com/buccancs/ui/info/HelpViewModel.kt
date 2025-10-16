package com.buccancs.ui.info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HelpSection(
    val title: String,
    val content: String
)

data class HelpUiState(
    val helpSections: List<HelpSection> = emptyList()
)

@HiltViewModel
class HelpViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        HelpUiState(
            helpSections = listOf(
                HelpSection(
                    title = "Getting Started",
                    content = "Connect your thermal camera device via USB or Bluetooth. " +
                            "The app will automatically detect and connect to supported devices."
                ),
                HelpSection(
                    title = "Capturing Thermal Images",
                    content = "Navigate to the Devices tab, select your thermal camera, " +
                            "and tap the capture button to take thermal images. " +
                            "Images are automatically saved to your gallery."
                ),
                HelpSection(
                    title = "Viewing Temperature Data",
                    content = "Tap on any thermal image to view detailed temperature information. " +
                            "Use pinch gestures to zoom and pan around the image."
                ),
                HelpSection(
                    title = "Managing Sessions",
                    content = "Recording sessions can be started from the Live tab. " +
                            "All sessions are saved in the Sessions tab where you can review, " +
                            "export, or delete them."
                ),
                HelpSection(
                    title = "Device Settings",
                    content = "Configure your thermal camera settings including palette, " +
                            "sampling rate, and auto-connect options in the device detail screen."
                ),
                HelpSection(
                    title = "Troubleshooting",
                    content = "If your device is not detected, ensure USB debugging is enabled " +
                            "and proper permissions are granted. Try reconnecting the device " +
                            "or restarting the application."
                ),
                HelpSection(
                    title = "Support",
                    content = "For additional support, contact our team at support@example.com " +
                            "or visit our website for detailed documentation and FAQs."
                )
            )
        )
    )
    val uiState: StateFlow<HelpUiState> = _uiState.asStateFlow()
}
