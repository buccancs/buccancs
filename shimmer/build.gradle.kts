plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.shimmerresearch.androidinstrumentdriver"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
        multiDexEnabled = true

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    sourceSets {
        getByName("main") {
            // Include manifest
            manifest.srcFile("../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/src/main/AndroidManifest.xml")
            // Include ALL source from external ShimmerAndroidAPI (including UI components)
            java.srcDir("../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/src/main/java")

            // Include all resource directories (matching original Shimmer structure)
            res.srcDirs(
                "../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/src/main/res/layouts/fragments",
                "../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/src/main/res/layouts/general",
                "../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/src/main/res/layouts",
                "../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/src/main/res"
            )
            // Include JAR libs
            resources.srcDirs(
                "../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/libs"
            )
        }
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/io.netty.versions.properties",
                "META-INF/LICENSE.txt",
                "META-INF/INDEX.LIST",
                "META-INF/LICENSE",
                "META-INF/DEPENDENCIES"
            )
        }
    }

    lint {
        abortOnError = false
    }
}

dependencies {
    // Core dependencies from original Shimmer build - use API so they're exported
    api("com.google.guava:guava:33.3.1-android")
    api("java3d:vecmath:1.3.1")
    api("androidx.appcompat:appcompat:1.7.0")
    api("androidx.documentfile:documentfile:1.0.1")
    api("com.github.Jasonchenlijian:FastBle:2.4.0")

    // Apache Commons dependencies
    api("org.apache.commons:commons-lang3:3.17.0")
    api("commons-codec:commons-codec:1.17.1")

    // Bolts framework (Facebook's task library)
    api("com.parse.bolts:bolts-tasks:1.4.0")

    // Include the JAR libraries from the external repo - use API to export them
    api(files("../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/libs/ShimmerBiophysicalProcessingLibrary_Rev_0_11.jar"))
    api(files("../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/libs/AndroidBluetoothLibrary.jar"))
    api(files("../external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver/ShimmerAndroidInstrumentDriver/libs/androidplot-core-0.5.0-release.jar"))

    // Java API libraries from Shimmer-Java-Android-API
    api(files("../sdk/libs/shimmerbluetoothmanager-0.11.5_beta.jar"))
    api(files("../sdk/libs/shimmerdriver-0.11.5_beta.jar"))
    api(files("../sdk/libs/shimmerdriverpc-0.11.5_beta.jar"))
}
