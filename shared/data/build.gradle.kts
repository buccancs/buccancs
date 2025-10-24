import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace =
        "com.buccancs.data.shared"
    compileSdk =
        libs.versions.androidCompileSdk.get()
            .toInt()

    defaultConfig {
        minSdk =
            libs.versions.androidMinSdk.get()
                .toInt()
    }

    buildFeatures {
        buildConfig =
            false
    }

    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_21
        targetCompatibility =
            JavaVersion.VERSION_21
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(
            JvmTarget.JVM_21
        )
        freeCompilerArgs.add(
            "-opt-in=kotlin.time.ExperimentalTime"
        )
        freeCompilerArgs.add(
            "-Xannotation-default-target=param-property"
        )
    }
}

dependencies {
    implementation(
        project(
            ":shared:domain"
        )
    )
    implementation(
        project(
            ":shared:foundation"
        )
    )
    implementation(
        project(
            ":shared:protocol"
        )
    )
    implementation(
        libs.coroutines.core
    )
    implementation(
        libs.kotlinx.datetime
    )
    implementation(
        libs.kotlinx.serialization.json
    )
    implementation(
        libs.javax.inject
    )
    implementation(
        libs.grpc.okhttp
    )
}
