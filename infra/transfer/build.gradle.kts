import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.buccancs.data.transfer"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }

    buildFeatures {
        buildConfig = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
    }
}

dependencies {
    implementation(project(":shared:foundation"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:data"))
    implementation(project(":shared:protocol"))
    implementation(project(":infra:orchestration"))
    implementation(project(":infra:storage"))

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.grpc.okhttp)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
