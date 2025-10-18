package com.buccancs.di

import com.buccancs.data.sensor.connector.MultiDeviceConnector
import com.buccancs.data.sensor.connector.SensorConnector
import com.buccancs.data.sensor.connector.audio.MicrophoneConnector
import com.buccancs.data.sensor.connector.camera.RgbCameraConnector
import com.buccancs.data.sensor.connector.shimmer.ShimmerConnectorManager
import com.buccancs.data.sensor.connector.topdon.TopdonConnectorManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
internal object SensorConnectorModule {
    @Provides
    @IntoSet
    @Singleton
    fun provideRgbConnector(
        connector: RgbCameraConnector
    ): SensorConnector =
        connector

    @Provides
    @IntoSet
    @Singleton
    fun provideMicrophoneConnector(
        connector: MicrophoneConnector
    ): SensorConnector =
        connector

    @Provides
    @IntoSet
    @Singleton
    fun provideShimmerMultiConnector(
        manager: ShimmerConnectorManager
    ): MultiDeviceConnector =
        manager

    @Provides
    @IntoSet
    @Singleton
    fun provideTopdonMultiConnector(
        manager: TopdonConnectorManager
    ): MultiDeviceConnector =
        manager
}

