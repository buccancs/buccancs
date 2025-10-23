package com.buccancs.di

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.hardware.camera2.CameraManager
import android.hardware.usb.UsbManager
import com.buccancs.hardware.AndroidBluetoothService
import com.buccancs.hardware.AndroidUsbService
import com.buccancs.hardware.BluetoothService
import com.buccancs.hardware.UsbService
import com.buccancs.hardware.shimmer.DefaultShimmerHardwareClient
import com.buccancs.hardware.shimmer.ShimmerHardwareClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
object HardwareModule {
    @Provides
    @Singleton
    fun provideBluetoothAdapter(): BluetoothAdapter? =
        BluetoothAdapter.getDefaultAdapter()

    @Provides
    @Singleton
    fun provideCameraManager(
        @ApplicationContext context: Context
    ): CameraManager =
        context.getSystemService(
            Context.CAMERA_SERVICE
        ) as CameraManager

    @Provides
    @Singleton
    fun provideUsbManager(
        @ApplicationContext context: Context
    ): UsbManager =
        context.getSystemService(
            Context.USB_SERVICE
        ) as UsbManager

    @Provides
    @Singleton
    fun provideShimmerHardwareClient(
        impl: DefaultShimmerHardwareClient,
    ): ShimmerHardwareClient =
        impl

    @Provides
    @Singleton
    fun provideBluetoothService(
        adapter: BluetoothAdapter?
    ): BluetoothService = AndroidBluetoothService(adapter)

    @Provides
    @Singleton
    fun provideUsbService(
        usbManager: UsbManager
    ): UsbService = AndroidUsbService(usbManager)
}
