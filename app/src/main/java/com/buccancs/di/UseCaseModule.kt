package com.buccancs.di

import com.buccancs.domain.usecase.DeviceManagementUseCase
import com.buccancs.domain.usecase.DeviceManagementUseCaseImpl
import com.buccancs.domain.usecase.HardwareConfigurationUseCase
import com.buccancs.domain.usecase.HardwareConfigurationUseCaseImpl
import com.buccancs.domain.usecase.RemoteCommandCoordinator
import com.buccancs.domain.usecase.RemoteCommandCoordinatorImpl
import com.buccancs.domain.usecase.SessionCoordinator
import com.buccancs.domain.usecase.SessionCoordinatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing use case implementations.
 * Use cases encapsulate business logic and reduce ViewModel complexity.
 */
@Module
@InstallIn(
    SingletonComponent::class
)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindSessionCoordinator(
        impl: SessionCoordinatorImpl
    ): SessionCoordinator

    @Binds
    @Singleton
    abstract fun bindDeviceManagementUseCase(
        impl: DeviceManagementUseCaseImpl
    ): DeviceManagementUseCase

    @Binds
    @Singleton
    abstract fun bindHardwareConfigurationUseCase(
        impl: HardwareConfigurationUseCaseImpl
    ): HardwareConfigurationUseCase

    @Binds
    @Singleton
    abstract fun bindRemoteCommandCoordinator(
        impl: RemoteCommandCoordinatorImpl
    ): RemoteCommandCoordinator
}
