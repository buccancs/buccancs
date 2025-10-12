import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinCompose)
}

android {
    namespace = "com.buccancs"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    val orchestratorHost = project.findProperty("orchestrator.host") as? String ?: "10.0.2.2"
    val orchestratorPort = (project.findProperty("orchestrator.port") as? String)?.toIntOrNull() ?: 50051

    defaultConfig {
        applicationId = "com.buccancs"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "ORCHESTRATOR_HOST", "\"$orchestratorHost\"")
        buildConfigField("int", "ORCHESTRATOR_PORT", orchestratorPort.toString())
        buildConfigField("boolean", "ORCHESTRATOR_USE_TLS", "false")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    testOptions {
        unitTests.all { it.enabled = false }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)

    implementation(project(":protocol"))
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.google.material)
    implementation(libs.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.documentfile)
    implementation(libs.opencv.android)
    implementation(libs.guava)
    implementation(libs.vecmath)
    implementation(libs.fastble)
    implementation(files("../sdk/libs/shimmerandroidinstrumentdriver-3.2.4_beta.aar"))
    implementation(files("../sdk/libs/shimmerbluetoothmanager-0.11.5_beta.jar"))
    implementation(files("../sdk/libs/shimmerdriver-0.11.5_beta.jar"))
    implementation(files("../sdk/libs/shimmerdriverpc-0.11.5_beta.jar"))
    implementation(files("../sdk/libs/topdon.aar"))
    implementation(libs.grpc.okhttp)
    implementation(libs.grpc.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}
