package com.buccancs.di

import com.buccancs.data.bookmarks.DefaultBookmarkRepository
import com.buccancs.data.calibration.DefaultCalibrationRepository
import com.buccancs.data.events.DefaultDeviceEventRepository
import com.buccancs.data.orchestration.DataStoreOrchestratorConfigRepository
import com.buccancs.data.sensor.DefaultSensorRepository
import com.buccancs.data.sensor.config.DefaultSensorHardwareConfigRepository
import com.buccancs.data.sensor.shimmer.DataStoreShimmerSettingsRepository
import com.buccancs.data.sensor.topdon.DataStoreTopdonSettingsRepository
import com.buccancs.data.sensor.topdon.DefaultTopdonDeviceRepository
import com.buccancs.data.sensor.topdon.gallery.DefaultTopdonGalleryRepository
import com.buccancs.data.transfer.DefaultSessionTransferRepository
import com.buccancs.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSensorRepository(impl: DefaultSensorRepository): SensorRepository

    @Binds
    @Singleton
    abstract fun bindSessionTransferRepository(
        impl: DefaultSessionTransferRepository
    ): SessionTransferRepository

    @Binds
    @Singleton
    abstract fun bindCalibrationRepository(
        impl: DefaultCalibrationRepository
    ): CalibrationRepository

    @Binds
    @Singleton
    abstract fun bindOrchestratorConfigRepository(
        impl: DataStoreOrchestratorConfigRepository
    ): OrchestratorConfigRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        impl: DefaultBookmarkRepository
    ): BookmarkRepository

    @Binds
    @Singleton
    abstract fun bindDeviceEventRepository(
        impl: DefaultDeviceEventRepository
    ): DeviceEventRepository

    @Binds
    @Singleton
    abstract fun bindSensorHardwareConfigRepository(
        impl: DefaultSensorHardwareConfigRepository
    ): SensorHardwareConfigRepository

    @Binds
    @Singleton
    abstract fun bindShimmerSettingsRepository(
        impl: DataStoreShimmerSettingsRepository
    ): ShimmerSettingsRepository

    @Binds
    @Singleton
    abstract fun bindTopdonSettingsRepository(
        impl: DataStoreTopdonSettingsRepository
    ): TopdonSettingsRepository

    @Binds
    @Singleton
    internal abstract fun bindTopdonDeviceRepository(
        impl: DefaultTopdonDeviceRepository
    ): TopdonDeviceRepository

    @Binds
    @Singleton
    internal abstract fun bindTopdonGalleryRepository(
        impl: DefaultTopdonGalleryRepository
    ): TopdonGalleryRepository
}
