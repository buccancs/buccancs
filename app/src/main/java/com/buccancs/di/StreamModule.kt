package com.buccancs.di
import com.buccancs.data.sensor.SensorStreamClient
import com.buccancs.data.sensor.SensorStreamUploader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class StreamModule {
    @Binds
    @Singleton
    abstract fun bindSensorStreamClient(impl: SensorStreamUploader): SensorStreamClient
}
