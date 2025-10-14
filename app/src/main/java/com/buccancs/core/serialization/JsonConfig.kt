package com.buccancs.core.serialization

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Qualifier
import javax.inject.Singleton

object JsonConfig {
    val standard: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = false
    }

    val pretty: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = true
    }

    val strict: Json = Json {
        ignoreUnknownKeys = false
        isLenient = false
        encodeDefaults = true
        prettyPrint = false
    }
}

@Module
@InstallIn(SingletonComponent::class)
object JsonModule {
    @Provides
    @Singleton
    @StandardJson
    fun provideStandardJson(): Json = JsonConfig.standard

    @Provides
    @Singleton
    @PrettyJson
    fun providePrettyJson(): Json = JsonConfig.pretty

    @Provides
    @Singleton
    @StrictJson
    fun provideStrictJson(): Json = JsonConfig.strict
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StandardJson

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrettyJson

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StrictJson
