import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.buccancs.data.orchestration"
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
    implementation(project(":shared:protocol"))
    implementation(project(":shared:data"))

    implementation(libs.coroutines.core)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.grpc.okhttp)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    implementation(libs.grpc.netty.shaded)
    implementation(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
    kapt(libs.hilt.compiler)
}
