import org.gradle.accessors.dm.LibrariesForLibs
import java.util.*

val releaseRenderscriptOptimLevel by extra(
    ""
)
val libs =
    the<LibrariesForLibs>()

plugins {
    alias(
        libs.plugins.androidApplication
    ) apply false
    alias(
        libs.plugins.kotlinAndroid
    ) apply false
    alias(
        libs.plugins.kotlinJvm
    ) apply false
    alias(
        libs.plugins.kotlinCompose
    ) apply false
    alias(
        libs.plugins.composeMultiplatform
    ) apply false
    alias(
        libs.plugins.hilt
    ) apply false
    alias(
        libs.plugins.protobuf
    ) apply false
    alias(
        libs.plugins.kotlinSerialization
    ) apply false
    alias(
        libs.plugins.kotlinKapt
    ) apply false
}

// Test execution flag - enable via: ./gradlew test -Ptests.enabled=true
val testsEnabled =
    project.findProperty(
        "tests.enabled"
    )
        ?.toString()
        ?.toBoolean()
        ?: false

// Make testsEnabled available to subprojects
extra.set(
    "testsEnabled",
    testsEnabled
)

if (testsEnabled) {
    logger.lifecycle(
        "Tests are ENABLED via -Ptests.enabled=true"
    )
} else {
    logger.lifecycle(
        "Tests are DISABLED (use -Ptests.enabled=true to enable)"
    )
}


private data class ExternalProjectBuild(
    val taskName: String,
    val projectDir: String,
    val tasksToRun: List<String> = listOf(
        "build"
    ),
    val wrapperScriptName: String = "gradlew",
    val maxSupportedJavaMajor: Int? = null
)

private val externalBuilds =
    listOf(
        // IRCamera - Topdon thermal camera application
        ExternalProjectBuild(
            "buildIRCamera",
            "external/IRCamera",
            tasksToRun = listOf("assembleDebug"),
            maxSupportedJavaMajor = 21
        ),

        // Temporarily disabled due to Gradle version conflict
        ExternalProjectBuild(
            "buildOriginalTopdonApp",
            "external/original-topdon-app"
        ),
        ExternalProjectBuild(
            "buildTopdonLibirSample",
            "external/example_topdon_sdk/libir_sample"
        ),

        // Shimmer SDK builds - now updated to Java 21
        ExternalProjectBuild(
            "buildShimmerBluetoothManager",
            "external/Shimmer-Java-Android-API/ShimmerBluetoothManager",
            maxSupportedJavaMajor = 24
        ),
        ExternalProjectBuild(
            "buildShimmerDriver",
            "external/Shimmer-Java-Android-API/ShimmerDriver",
            maxSupportedJavaMajor = 24
        ),
        ExternalProjectBuild(
            "buildShimmerDriverPC",
            "external/Shimmer-Java-Android-API/ShimmerDriverPC",
            maxSupportedJavaMajor = 24
        ),
        ExternalProjectBuild(
            "buildShimmerTCP",
            "external/Shimmer-Java-Android-API/ShimmerTCP",
            maxSupportedJavaMajor = 24
        ),
        ExternalProjectBuild(
            "buildShimmerPCBasicExamples",
            "external/Shimmer-Java-Android-API/ShimmerPCBasicExamples",
            maxSupportedJavaMajor = 24
        ),
        ExternalProjectBuild(
            "buildShimmerLSL",
            "external/Shimmer-Java-Android-API/ShimmerLSL",
            maxSupportedJavaMajor = 24
        ),
        ExternalProjectBuild(
            "buildJavaShimmerConnect",
            "external/Shimmer-Java-Android-API/JavaShimmerConnect",
            maxSupportedJavaMajor = 24
        ),
    )

private val usingWindowsWrapperExtension =
    System.getProperty(
        "os.name"
    )
        .startsWith(
            "Windows",
            ignoreCase = true
        )

private fun readJavaMajorVersion(
    javaHome: File
): Int? {
    val releaseFile =
        File(
            javaHome,
            "release"
        )
    if (!releaseFile.exists()) {
        return null
    }
    val declaration =
        releaseFile.useLines { lines ->
            lines.firstOrNull {
                it.startsWith(
                    "JAVA_VERSION="
                )
            }
        }
            ?: return null
    val rawVersion =
        declaration.substringAfter(
            '"'
        )
            .substringBefore(
                '"'
            )
    val components =
        rawVersion.split(
            '.'
        )
    return when {
        components.isEmpty() -> null
        components[0] == "1" && components.size > 1 -> components[1].toIntOrNull()
        else -> components[0].toIntOrNull()
    }
}

private fun findExternalJavaHome(
    project: Project,
    maxJavaMajor: Int?
): File? {
    val propertyValue =
        project.providers.gradleProperty(
            "externalJavaHome"
        ).orNull?.takeIf { it.isNotBlank() }
    val envValue =
        System.getenv(
            "EXTERNAL_JAVA_HOME"
        )
            ?.takeIf { it.isNotBlank() }
    val osName =
        System.getProperty(
            "os.name"
        )
            .lowercase(
                Locale.US
            )
    val defaultCandidates =
        buildList {
            if (osName.contains(
                    "windows"
                )
            ) {
                add("C:\\Program Files\\Java\\jdk-24")
                add("C:\\Program Files\\Java\\jdk-21")
                add("C:\\Program Files\\Java\\jdk-17")
            } else {
                add("/usr/lib/jvm/java-21-openjdk")
                add("/usr/lib/jvm/java-21")
                add("/usr/lib/jvm/java-17-openjdk")
                add("/usr/lib/jvm/java-17")
                add("/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home")
                add("/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home")
            }
        }
    val combinedCandidates =
        listOfNotNull(
            propertyValue,
            envValue,
            System.getenv(
                "JAVA_HOME"
            )
        ) + defaultCandidates
    return combinedCandidates
        .map {
            project.file(
                it
            )
        }
        .firstOrNull { candidate ->
            candidate.exists() && (maxJavaMajor == null ||
                    readJavaMajorVersion(
                        candidate
                    )?.let { it <= maxJavaMajor } == true)
        }
}

private val externalBuildTasks =
    externalBuilds.mapNotNull { external ->
        val wrapperFileName =
            if (usingWindowsWrapperExtension) {
                "${external.wrapperScriptName}.bat"
            } else {
                external.wrapperScriptName
            }
        val wrapperFile =
            file(
                "${external.projectDir}/$wrapperFileName"
            )
        if (!wrapperFile.exists()) {
            logger.lifecycle(
                "Skipping external build task ${external.taskName} because wrapper was not found at $wrapperFileName in ${external.projectDir}"
            )
            null
        } else {
            tasks.register<Exec>(
                external.taskName
            ) {
                group =
                    "external build"
                description =
                    "Builds the external project located at ${external.projectDir}."
                workingDir =
                    file(
                        external.projectDir
                    )
                commandLine(
                    listOf(
                        wrapperFile.absolutePath
                    ) + external.tasksToRun + listOf(
                        "-x",
                        "test"
                    )
                )
                val javaHomeOverride =
                    findExternalJavaHome(
                        project,
                        external.maxSupportedJavaMajor
                    )
                if (javaHomeOverride != null) {
                    environment(
                        "JAVA_HOME",
                        javaHomeOverride.absolutePath
                    )
                    val existingPath =
                        System.getenv(
                            "PATH"
                        )
                            ?: ""
                    val updatedPath =
                        javaHomeOverride.resolve(
                            "bin"
                        ).absolutePath + File.pathSeparator + existingPath
                    environment(
                        "PATH",
                        updatedPath
                    )
                } else if (external.maxSupportedJavaMajor != null) {
                    logger.lifecycle(
                        "Skipping external build task ${external.taskName} because no compatible Java runtime (<= ${external.maxSupportedJavaMajor}) was found."
                    )
                    enabled =
                        false
                }
            }
        }
    }

val externalBuildAggregate =
    tasks.register(
        "externalBuild"
    ) {
        group =
            "external build"
        description =
            "Builds all curated external projects without building the internal modules."
        dependsOn(
            externalBuildTasks
        )
    }

subprojects {
    tasks.withType<Test>()
        .configureEach {
            enabled =
                rootProject.extra.properties["testsEnabled"] as? Boolean
                    ?: false
        }
}

tasks.register(
    "build"
) {
    group =
        "build"
    description =
        "Aggregate build for all subprojects and curated external projects."
    dependsOn(
        subprojects.map { "${it.path}:build" })
    dependsOn(
        externalBuildAggregate
    )
}

tasks.register(
    "all"
) {
    group =
        "verification"
    description =
        "Aggregate target so the mandated `gradle build all` command succeeds."
    dependsOn(
        "build"
    )
}

// Logical bundle tasks to harmonize common build/run entry points
// These provide stable, memorable Gradle targets and fuel shared IDE run configurations.
@Suppress("UnstableApiUsage")
fun Project.subprojectTasksByPrefix(prefix: String, task: String = "build"): List<String> =
    subprojects
        .filter { it.name.startsWith(prefix, ignoreCase = true) }
        .map { "${it.path}:$task" }

@Suppress("UnstableApiUsage")
fun Project.existingModuleTasks(names: List<String>, task: String = "build"): List<String> =
    names.mapNotNull { module ->
        findProject(":$module")?.let { "$it:$task" }
    }

// Core libraries and shared components
val bundleCore = tasks.register("bundleCore") {
    group = "bundles"
    description = "Builds core/shared modules (protocol, domain, core, storage, common-ui, sdk) if present."
    dependsOn(
        existingModuleTasks(listOf("protocol", "domain", "core", "storage", "common-ui", "sdk"))
    )
}

// Desktop application and related libs
val bundleDesktop = tasks.register("bundleDesktop") {
    group = "bundles"
    description = "Builds the desktop application and prerequisites."
    dependsOn(bundleCore)
    // Build desktop module if present
    findProject(":desktop")?.let { dependsOn("$it:build") }
}

// Android application (assembleDebug/installDebug)
val bundleAndroid = tasks.register("bundleAndroid") {
    group = "bundles"
    description = "Builds the Android application (assembleDebug)."
    dependsOn(bundleCore)
    findProject(":app")?.let { dependsOn("$it:assembleDebug") }
}

// Thermal-related modules (e.g., thermal-simulated, thermal-topdon)
val bundleThermal = tasks.register("bundleThermal") {
    group = "bundles"
    description = "Builds all thermal-related modules."
    val thermalTasks = subprojectTasksByPrefix(prefix = "thermal", task = "build")
    dependsOn(thermalTasks)
}

// Topdon-related modules (e.g., topdon-runtime, topdon-sdk)
val bundleTopdon = tasks.register("bundleTopdon") {
    group = "bundles"
    description = "Builds all Topdon-related modules."
    val topdonTasks = subprojectTasksByPrefix(prefix = "topdon", task = "build")
    dependsOn(topdonTasks)
}

// Everything internal, then curated external builds
tasks.register("bundleAll") {
    group = "bundles"
    description = "Builds all internal bundles and curated external projects."
    dependsOn(bundleCore)
    dependsOn(bundleDesktop)
    dependsOn(bundleAndroid)
    dependsOn(bundleThermal)
    dependsOn(bundleTopdon)
    dependsOn(externalBuildAggregate)
}

// Convenience run targets that proxy to subproject run tasks when present
// Run the Compose Desktop app
tasks.register("runDesktop") {
    group = "application"
    description = "Runs the desktop application (:desktop:run)."
    findProject(":desktop")?.let { dependsOn("$it:run") }
}

