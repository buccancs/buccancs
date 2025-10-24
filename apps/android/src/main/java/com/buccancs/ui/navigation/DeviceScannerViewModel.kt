package com.buccancs.ui.navigation

import androidx.lifecycle.ViewModel
import com.buccancs.hardware.DeviceScannerService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel to provide DeviceScannerService to composables
 */
@HiltViewModel
class DeviceScannerViewModel @Inject constructor(
    val deviceScanner: DeviceScannerService
) : ViewModel()
