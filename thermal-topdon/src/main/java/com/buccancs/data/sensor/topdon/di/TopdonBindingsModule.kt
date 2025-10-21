package com.buccancs.data.sensor.topdon.di

import com.buccancs.data.sensor.topdon.DataStoreTopdonSettingsRepository
import com.buccancs.data.sensor.topdon.DefaultTopdonDeviceRepository
import com.buccancs.data.sensor.topdon.gallery.DefaultTopdonGalleryRepository
import com.buccancs.hardware.topdon.DefaultTopdonThermalClient
import com.buccancs.hardware.topdon.TopdonThermalClient
import com.buccancs.domain.repository.TopdonDeviceRepository
import com.buccancs.domain.repository.TopdonGalleryRepository
import com.buccancs.domain.repository.TopdonSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TopdonRepositoryBindings {
    @Binds
    @Singleton
    abstract fun bindTopdonSettingsRepository(
        impl: DataStoreTopdonSettingsRepository
    ): TopdonSettingsRepository

    @Binds
    @Singleton
    abstract fun bindTopdonDeviceRepository(
        impl: DefaultTopdonDeviceRepository
    ): TopdonDeviceRepository

    @Binds
    @Singleton
    abstract fun bindTopdonGalleryRepository(
        impl: DefaultTopdonGalleryRepository
    ): TopdonGalleryRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TopdonHardwareBindings {
    @Binds
    @Singleton
    abstract fun bindTopdonThermalClient(
        impl: DefaultTopdonThermalClient
    ): TopdonThermalClient
}
