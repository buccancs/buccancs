import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinCompose) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.kotlinKapt) apply false
}

extra.apply {
    set("android.minSdk", libs.versions.androidMinSdk.get().toInt())
    set("android.targetSdk", libs.versions.androidTargetSdk.get().toInt())
    set("android.compileSdk", libs.versions.androidCompileSdk.get().toInt())
}

tasks.register("build") {
    group = "build"
    description = "Aggregate build for all subprojects."
    dependsOn(subprojects.map { "${it.path}:build" })
}

tasks.register("all") {
    group = "verification"
    description = "Aggregate target so the mandated `gradle build all` command succeeds."
    dependsOn("build")
}
