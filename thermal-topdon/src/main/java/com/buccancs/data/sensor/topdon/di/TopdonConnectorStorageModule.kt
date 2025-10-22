package com.buccancs.data.sensor.topdon.di

import com.buccancs.data.sensor.connector.topdon.RecordingArtifactStorage
import com.buccancs.data.sensor.connector.topdon.StorageBackedRecordingArtifactStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TopdonConnectorStorageModule {
    @Binds
    @Singleton
    abstract fun bindRecordingArtifactStorage(
        impl: StorageBackedRecordingArtifactStorage
    ): RecordingArtifactStorage
}
