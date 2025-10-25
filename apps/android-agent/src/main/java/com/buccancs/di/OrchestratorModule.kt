package com.buccancs.di

import com.buccancs.BuildConfig
import com.buccancs.data.orchestration.DefaultOrchestratorHost
import com.buccancs.data.orchestration.DefaultOrchestratorPort
import com.buccancs.data.orchestration.DefaultOrchestratorUseTls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrchestratorModule {

    @Provides
    @Singleton
    @DefaultOrchestratorHost
    fun provideDefaultOrchestratorHost(): String = BuildConfig.ORCHESTRATOR_HOST

    @Provides
    @Singleton
    @DefaultOrchestratorPort
    fun provideDefaultOrchestratorPort(): Int = BuildConfig.ORCHESTRATOR_PORT

    @Provides
    @Singleton
    @DefaultOrchestratorUseTls
    fun provideDefaultOrchestratorUseTls(): Boolean = BuildConfig.ORCHESTRATOR_USE_TLS
}
