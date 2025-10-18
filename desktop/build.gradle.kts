@file:Suppress(
    "UnstableApiUsage"
)

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(
        libs.plugins.kotlinJvm
    )
    alias(
        libs.plugins.kotlinCompose
    )
    alias(
        libs.plugins.composeMultiplatform
    )
    alias(
        libs.plugins.kotlinSerialization
    )
}

kotlin {
    jvmToolchain(
        21
    )
    compilerOptions {
        jvmTarget.set(
            JvmTarget.JVM_21
        )
        freeCompilerArgs.add(
            "-opt-in=kotlin.time.ExperimentalTime"
        )
        freeCompilerArgs.add(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }
}

dependencies {
    implementation(
        project(
            ":protocol"
        )
    )
    implementation(
        compose.desktop.currentOs
    )
    implementation(
        compose.runtime
    )
    implementation(
        compose.foundation
    )
    implementation(
        compose.material3
    )
    implementation(
        compose.materialIconsExtended
    )
    implementation(
        libs.coroutines.core
    )
    implementation(
        libs.coroutines.swing
    )
    implementation(
        libs.coroutines.jdk8
    )
    implementation(
        libs.kotlinx.serialization.json
    )

    implementation(
        libs.grpc.netty.shaded
    )

    implementation(
        libs.tink
    )
    implementation(
        libs.bouncycastle.bcprov
    )

    implementation(
        "org.jmdns:jmdns:3.6.2"
    )

    implementation(
        libs.slf4j.api
    )
    runtimeOnly(
        libs.slf4j.simple
    )

    testImplementation(
        kotlin(
            "test-junit5"
        )
    )
    testImplementation(
        libs.junit.jupiter
    )
    testImplementation(
        libs.junit.junit
    )
    testImplementation(
        "org.junit.platform:junit-platform-suite:6.0.0"
    )
    testImplementation(
        libs.compose.ui.test.junit4
    )
    testImplementation(
        libs.coroutines.test
    )
    testImplementation(
        libs.mockk
    )
}

compose.desktop {
    application {
        mainClass =
            "com.buccancs.desktop.MainKt"
    }
}

tasks.register<JavaExec>(
    "runHeadlessServer"
) {
    group =
        "application"
    description =
        "Runs the orchestrator gRPC server without launching the desktop UI."
    classpath =
        sourceSets["main"].runtimeClasspath
    mainClass.set(
        "com.buccancs.desktop.HeadlessServerKt"
    )
    systemProperty(
        "java.awt.headless",
        "true"
    )
}

tasks.test {
    val testsEnabled =
        project.rootProject.extra.get(
            "testsEnabled"
        ) as Boolean
    enabled =
        testsEnabled
    useJUnitPlatform()
    testLogging {
        events(
            "passed",
            "skipped",
            "failed"
        )
        showStandardStreams =
            false
    }
}

val desktopTestSourceSet =
    sourceSets.named(
        "test"
    )

tasks.register<Test>(
    "uiDesktop"
) {
    description =
        "Runs desktop Compose UI smoke tests."
    group =
        "verification"
    testClassesDirs =
        desktopTestSourceSet.get().output.classesDirs
    classpath =
        desktopTestSourceSet.get().runtimeClasspath
    systemProperty(
        "java.awt.headless",
        "true"
    )
    useJUnitPlatform()
}
