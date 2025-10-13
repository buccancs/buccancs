@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
    }
}

dependencies {
    implementation(project(":protocol"))
    implementation(compose.desktop.currentOs)
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.swing)
    implementation(libs.coroutines.jdk8)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.grpc.netty.shaded)

    implementation(libs.tink)
    implementation(libs.bouncycastle.bcprov)

    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)

    testImplementation(kotlin("test-junit5"))
    testImplementation(libs.junit.jupiter)
}

compose.desktop {
    application {
        mainClass = "com.buccancs.desktop.MainKt"
    }
}

tasks.test {
    enabled = false
}
