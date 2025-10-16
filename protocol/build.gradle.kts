import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.api.tasks.testing.Test

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    api(libs.grpc.kotlin.stub)
    api(libs.grpc.protobuf)
    api(libs.grpc.stub)
    api(libs.protobuf.kotlin)
    api(libs.protobuf.java.util)
    api(libs.coroutines.core)
    api(libs.kotlinx.serialization.json)
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protobuf.get()}"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${libs.versions.grpc.get()}"
        }
        create("grpckotlin") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${libs.versions.grpcKotlin.get()}:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().configureEach {
            plugins {
                id("grpc")
                id("grpckotlin")
            }
            builtins {
                id("kotlin")
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<GenerateProtoTask>().configureEach {
    doLast {
        val kotlinOutputDir = project.layout.buildDirectory
            .dir("generated/sources/proto/${sourceSet.name}/kotlin")
            .get()
            .asFile
        if (!kotlinOutputDir.exists()) return@doLast
        kotlinOutputDir.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .forEach { file ->
                val original = file.readText()
                var changed = false
                val sanitizedLines = buildList {
                    original.split('\n').forEach { line ->
                        when {
                            line.startsWith("package ") && line.trimEnd().endsWith(";") -> {
                                add(line.trimEnd().removeSuffix(";"))
                                changed = true
                            }

                            line.trimStart().startsWith("@JvmName(") -> {
                                changed = true
                            }

                            else -> add(line)
                        }
                    }
                }
                val fixed = sanitizedLines.joinToString("\n")
                if (changed && fixed != original) {
                    file.writeText(fixed)
                    logger.info("Sanitized protobuf Kotlin DSL in ${file.relativeTo(project.projectDir)}")
                }
            }
    }
}

