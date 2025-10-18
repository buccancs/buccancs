package com.buccancs.hardware

import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

interface CameraService {
    fun getCameraIdList(): List<String>
    fun getCameraCharacteristics(
        cameraId: String
    ): CameraCharacteristics
}

class AndroidCameraService(
    private val manager: CameraManager
) : CameraService {
    override fun getCameraIdList(): List<String> =
        manager.cameraIdList.toList()

    override fun getCameraCharacteristics(
        cameraId: String
    ): CameraCharacteristics =
        manager.getCameraCharacteristics(
            cameraId
        )
}
