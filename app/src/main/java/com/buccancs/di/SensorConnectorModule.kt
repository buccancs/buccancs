package com.buccancs.di
import com.buccancs.data.sensor.connector.SensorConnector
import com.buccancs.data.sensor.connector.audio.MicrophoneConnector
import com.buccancs.data.sensor.connector.camera.RgbCameraConnector
import com.buccancs.data.sensor.connector.shimmer.ShimmerSensorConnector
import com.buccancs.data.sensor.connector.topdon.TopdonThermalConnector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object SensorConnectorModule {
    @Provides
    @Singleton
    fun provideSensorConnectors(
        shimmer: ShimmerSensorConnector,
        rgb: RgbCameraConnector,
        thermal: TopdonThermalConnector,
        microphone: MicrophoneConnector
    ): List<SensorConnector> = listOf(shimmer, rgb, thermal, microphone)
}
