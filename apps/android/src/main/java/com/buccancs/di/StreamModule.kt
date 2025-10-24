package com.buccancs.di

import com.buccancs.data.preview.PreviewStreamClient
import com.buccancs.data.preview.PreviewStreamUploader
import com.buccancs.data.sensor.SensorStreamUploader
import com.buccancs.domain.sensor.SensorStreamClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
abstract class StreamModule {
    @Binds
    @Singleton
    abstract fun bindSensorStreamClient(
        impl: SensorStreamUploader
    ): SensorStreamClient

    @Binds
    @Singleton
    abstract fun bindPreviewStreamClient(
        impl: PreviewStreamUploader
    ): PreviewStreamClient
}
