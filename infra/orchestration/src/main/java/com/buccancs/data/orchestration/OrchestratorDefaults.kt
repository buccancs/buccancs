package com.buccancs.data.orchestration

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOrchestratorHost

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOrchestratorPort

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOrchestratorUseTls
