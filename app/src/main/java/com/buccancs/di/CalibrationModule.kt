package com.buccancs.di

import com.buccancs.data.calibration.DualCameraController
import com.buccancs.data.calibration.SimulatedDualCameraController
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CalibrationModule {
    @Binds
    @Singleton
    abstract fun bindDualCameraController(
        impl: SimulatedDualCameraController
    ): DualCameraController
}
