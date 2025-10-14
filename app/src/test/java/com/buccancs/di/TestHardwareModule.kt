package com.buccancs.di

import com.buccancs.hardware.BluetoothService
import com.buccancs.hardware.CameraService
import com.buccancs.hardware.UsbService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [HardwareModule::class]
)
object TestHardwareModule {
    @Provides
    @Singleton
    fun provideBluetoothService(): BluetoothService = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideCameraService(): CameraService = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideUsbService(): UsbService = mockk(relaxed = true)
}
