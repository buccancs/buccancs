import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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
                // Ensure both Java and Kotlin sources exist while avoiding duplicate registration
                runCatching { getByName("java") }.getOrElse { id("java") }
                runCatching { getByName("kotlin") }.getOrElse { id("kotlin") }
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<GenerateProtoTask>().configureEach {
    val protoBaseDirProvider =
        layout.buildDirectory.dir("generated/sources/proto/${sourceSet.name}")
    val kotlinOutputDirProvider = protoBaseDirProvider.map { it.dir("kotlin") }
    val projectDir = project.projectDir

    doFirst {
        val protoBaseDir = protoBaseDirProvider.get().asFile
        listOf("java", "kotlin", "grpc", "grpckotlin").forEach { subDir ->
            protoBaseDir.resolve(subDir).mkdirs()
        }
    }
    doLast {
        val kotlinOutputDir = kotlinOutputDirProvider.get().asFile
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
                    logger.info("Sanitized protobuf Kotlin DSL in ${file.relativeTo(projectDir)}")
                }
            }
    }
}

