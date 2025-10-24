package com.buccancs.di

import com.buccancs.application.control.DefaultDeviceCommandService
import com.buccancs.application.control.DeviceCommandService
import com.buccancs.application.recording.DefaultRecordingService
import com.buccancs.application.recording.RecordingService
import com.buccancs.application.time.DefaultTimeSyncService
import com.buccancs.application.time.TimeSyncService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
abstract class ServiceModule {
    @Binds
    @Singleton
    abstract fun bindRecordingService(
        impl: DefaultRecordingService
    ): RecordingService

    @Binds
    @Singleton
    abstract fun bindDeviceCommandService(
        impl: DefaultDeviceCommandService
    ): DeviceCommandService

    @Binds
    @Singleton
    abstract fun bindTimeSyncService(
        impl: DefaultTimeSyncService
    ): TimeSyncService
}

