package com.buccancs.domain.repository

import com.buccancs.domain.model.DeviceId
import com.buccancs.domain.model.TopdonDeviceState
import com.buccancs.domain.model.TopdonPreviewFrame
import kotlinx.coroutines.flow.StateFlow

interface TopdonDeviceRepository {
    val deviceState: StateFlow<TopdonDeviceState>
    val previewFrame: StateFlow<TopdonPreviewFrame?>
    val activeDeviceId: StateFlow<DeviceId>
    suspend fun setActiveDevice(deviceId: DeviceId)
    suspend fun refresh()
    suspend fun connect()
    suspend fun disconnect()
    suspend fun startPreview()
    suspend fun stopPreview()
    suspend fun clearError()
    suspend fun capturePhoto()
    suspend fun startRecording()
    suspend fun stopRecording()
}
