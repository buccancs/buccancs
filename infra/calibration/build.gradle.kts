import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.buccancs.data.calibration"
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
    implementation(project(":infra:storage"))

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.core.ktx)
    implementation(libs.opencv.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
