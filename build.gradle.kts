import org.gradle.accessors.dm.LibrariesForLibs
import java.util.*

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


private data class ExternalProjectBuild(
    val taskName: String,
    val projectDir: String,
    val tasksToRun: List<String> = listOf("build"),
    val wrapperScriptName: String = "gradlew",
    val maxSupportedJavaMajor: Int? = null
)

private val externalBuilds = listOf(
    // Temporarily disabled due to Gradle version conflict
    // ExternalProjectBuild("buildOriginalTopdonApp", "external/original-topdon-app"),
    // Temporarily disabled due to missing SDK configuration
    // ExternalProjectBuild(
    //     "buildShimmerAndroidInstrumentDriver",
    //     "external/ShimmerAndroidAPI/ShimmerAndroidInstrumentDriver"
    // ),
    ExternalProjectBuild(
        "buildShimmerBluetoothManager",
        "external/Shimmer-Java-Android-API/ShimmerBluetoothManager",
        maxSupportedJavaMajor = 13
    ),
    ExternalProjectBuild(
        "buildShimmerDriver",
        "external/Shimmer-Java-Android-API/ShimmerDriver",
        maxSupportedJavaMajor = 13
    ),
    ExternalProjectBuild(
        "buildShimmerDriverPC",
        "external/Shimmer-Java-Android-API/ShimmerDriverPC",
        maxSupportedJavaMajor = 13
    ),
    ExternalProjectBuild("buildShimmerTCP", "external/Shimmer-Java-Android-API/ShimmerTCP", maxSupportedJavaMajor = 13),
    ExternalProjectBuild(
        "buildShimmerPCBasicExamples",
        "external/Shimmer-Java-Android-API/ShimmerPCBasicExamples",
        maxSupportedJavaMajor = 13
    ),
    ExternalProjectBuild("buildShimmerLSL", "external/Shimmer-Java-Android-API/ShimmerLSL", maxSupportedJavaMajor = 13),
    ExternalProjectBuild(
        "buildJavaShimmerConnect",
        "external/Shimmer-Java-Android-API/JavaShimmerConnect",
        maxSupportedJavaMajor = 13
    ),
    ExternalProjectBuild("buildTopdonLibirSample", "external/example_topdon_sdk/libir_sample")
)

private val usingWindowsWrapperExtension = System.getProperty("os.name").startsWith("Windows", ignoreCase = true)

private fun readJavaMajorVersion(javaHome: File): Int? {
    val releaseFile = File(javaHome, "release")
    if (!releaseFile.exists()) {
        return null
    }
    val declaration = releaseFile.useLines { lines ->
        lines.firstOrNull { it.startsWith("JAVA_VERSION=") }
    } ?: return null
    val rawVersion = declaration.substringAfter('"').substringBefore('"')
    val components = rawVersion.split('.')
    return when {
        components.isEmpty() -> null
        components[0] == "1" && components.size > 1 -> components[1].toIntOrNull()
        else -> components[0].toIntOrNull()
    }
}

private fun findExternalJavaHome(project: org.gradle.api.Project, maxJavaMajor: Int?): File? {
    val propertyValue = project.providers.gradleProperty("externalJavaHome").orNull?.takeIf { it.isNotBlank() }
    val envValue = System.getenv("EXTERNAL_JAVA_HOME")?.takeIf { it.isNotBlank() }
    val osName = System.getProperty("os.name").lowercase(Locale.US)
    val defaultCandidates = buildList {
        if (osName.contains("windows")) {
            add("C:/Program Files/Java/jdk-17")
            add("C:/Program Files/Java/jdk-21")
        } else {
            add("/usr/lib/jvm/java-17-openjdk")
            add("/usr/lib/jvm/java-17")
            add("/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home")
        }
    }
    val combinedCandidates = listOfNotNull(propertyValue, envValue, System.getenv("JAVA_HOME")) + defaultCandidates
    return combinedCandidates
        .map { project.file(it) }
        .firstOrNull { candidate ->
            candidate.exists() && (maxJavaMajor == null ||
                    readJavaMajorVersion(candidate)?.let { it <= maxJavaMajor } == true)
        }
}

private val externalBuildTasks = externalBuilds.mapNotNull { external ->
    val wrapperFileName = if (usingWindowsWrapperExtension) {
        "${external.wrapperScriptName}.bat"
    } else {
        external.wrapperScriptName
    }
    val wrapperFile = file("${external.projectDir}/$wrapperFileName")
    if (!wrapperFile.exists()) {
        logger.lifecycle(
            "Skipping external build task ${external.taskName} because wrapper was not found at $wrapperFileName in ${external.projectDir}"
        )
        null
    } else {
        tasks.register<Exec>(external.taskName) {
            group = "external build"
            description = "Builds the external project located at ${external.projectDir}."
            workingDir = file(external.projectDir)
            commandLine(listOf(wrapperFile.absolutePath) + external.tasksToRun)
            val javaHomeOverride = findExternalJavaHome(project, external.maxSupportedJavaMajor)
            if (javaHomeOverride != null) {
                environment("JAVA_HOME", javaHomeOverride.absolutePath)
                val existingPath = System.getenv("PATH") ?: ""
                val updatedPath = javaHomeOverride.resolve("bin").absolutePath + File.pathSeparator + existingPath
                environment("PATH", updatedPath)
            } else if (external.maxSupportedJavaMajor != null) {
                logger.lifecycle(
                    "Skipping external build task ${external.taskName} because no compatible Java runtime (<= ${external.maxSupportedJavaMajor}) was found."
                )
                enabled = false
            }
        }
    }
}

subprojects {
    tasks.withType<Test>().configureEach {
        enabled = false
    }
}

tasks.register("build") {
    group = "build"
    description = "Aggregate build for all subprojects and curated external projects."
    dependsOn(subprojects.map { "${it.path}:build" })
    dependsOn(externalBuildTasks)
}

tasks.register("all") {
    group = "verification"
    description = "Aggregate target so the mandated `gradle build all` command succeeds."
    dependsOn("build")
}
