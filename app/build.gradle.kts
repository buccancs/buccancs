plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.buccancs"
    compileSdk = 36

    val orchestratorHost = project.findProperty("orchestrator.host") as? String ?: "10.0.2.2"
    val orchestratorPort = (project.findProperty("orchestrator.port") as? String)?.toIntOrNull() ?: 50051

    defaultConfig {
        applicationId = "com.buccancs"
        minSdk = 35
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "17"
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

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.10.00")

    implementation(project(":protocol"))
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.9.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    implementation("androidx.datastore:datastore-preferences:1.1.7")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.documentfile:documentfile:1.0.1")
    implementation("org.opencv:opencv-android:4.9.0")
    implementation("com.google.guava:guava:20.0")
    implementation("java3d:vecmath:1.3.1")
    implementation("com.github.Jasonchenlijian:FastBle:2.4.0")
    implementation(files("../sdk/libs/shimmerandroidinstrumentdriver-3.2.4_beta.aar"))
    implementation(files("../sdk/libs/shimmerbluetoothmanager-0.11.5_beta.jar"))
    implementation(files("../sdk/libs/shimmerdriver-0.11.5_beta.jar"))
    implementation(files("../sdk/libs/shimmerdriverpc-0.11.5_beta.jar"))
    implementation(files("../sdk/libs/topdon.aar"))
    implementation("io.grpc:grpc-okhttp:1.64.0")
    implementation("io.grpc:grpc-android:1.64.0")

    implementation("com.google.dagger:hilt-android:2.57.2")
    kapt("com.google.dagger:hilt-android-compiler:2.57.2")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
    correctErrorTypes = true
}
