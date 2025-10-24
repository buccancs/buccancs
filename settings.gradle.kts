import java.io.File
import java.io.FileInputStream
import java.util.*

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex(
                    "com\\.android.*"
                )
                includeGroupByRegex(
                    "com\\.google.*"
                )
                includeGroupByRegex(
                    "androidx.*"
                )
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven(
            "https://maven.pkg.jetbrains.space/public/p/compose/dev"
        )
        maven(
            "https://maven.scijava.org/content/repositories/public"
        )
        maven(
            "https://jitpack.io"
        )
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(
        RepositoriesMode.FAIL_ON_PROJECT_REPOS
    )
    repositories {
        google()
        mavenCentral()
        maven(
            "https://maven.pkg.jetbrains.space/public/p/compose/dev"
        )
        maven(
            "https://maven.scijava.org/content/repositories/public"
        )
        maven(
            "https://jitpack.io"
        )
    }
}

rootProject.name =
    "buccancs"

// Continue executing remaining tasks even after failures to build other modules.
gradle.startParameter.setContinueOnFailure(true)

fun isWindowsPath(
    path: String
): Boolean =
    Regex(
        "^[A-Za-z]:\\\\.*"
    ).matches(
        path
    )

fun isWsl(): Boolean =
    System.getenv(
        "WSL_DISTRO_NAME"
    ) != null ||
            System.getenv(
                "WSL_INTEROP"
            ) != null ||
            System.getenv(
                "WSLENV"
            ) != null ||
            System.getenv(
                "WSL_DISTRO"
            ) != null ||
            (System.getProperty(
                "os.name"
            )
                ?.lowercase()
                ?.contains(
                    "linux"
                ) == true &&
                    System.getenv(
                        "RUNNING_IN_WSL"
                    ) != null) ||
            runCatching {
                val procVersion =
                    java.io.File(
                        "/proc/version"
                    )
                procVersion.exists() && procVersion.readText()
                    .contains(
                        "Microsoft",
                        ignoreCase = true
                    )
            }.getOrDefault(
                false
            )

fun isWindowsHost(): Boolean =
    System.getProperty(
        "os.name"
    )
        ?.lowercase()
        ?.contains(
            "windows"
        ) == true && !isWsl()

fun windowsPathToWsl(
    path: String
): String {
    val driveLetter =
        path.substring(
            0,
            1
        )
            .lowercase()
    val remainder =
        path.substring(
            2
        )
            .replace(
                "\\",
                "/"
            )
    return "/mnt/$driveLetter/$remainder"
}

fun wslPathToWindows(
    path: String
): String {
    val matcher =
        Regex(
            "^/mnt/([a-z])/",
            RegexOption.IGNORE_CASE
        ).find(
            path
        )
            ?: return path
    val driveLetter =
        matcher.groupValues[1].uppercase()
    val remainder =
        path.substring(
            matcher.range.last + 1
        )
            .replace(
                "/",
                "\\"
            )
    return "$driveLetter:\\$remainder"
}

fun normalisePath(
    rawPath: String
): String {
    val unescaped =
        rawPath.replace(
            "\\\\",
            "\\"
        )
    return when {
        isWsl() && isWindowsPath(
            unescaped
        ) -> windowsPathToWsl(
            unescaped
        )

        isWindowsHost() && unescaped.startsWith(
            "/mnt/",
            ignoreCase = true
        ) -> wslPathToWindows(
            unescaped
        )

        else -> unescaped
    }
}

fun existingPath(
    vararg candidates: String?
): String? =
    candidates
        .asSequence()
        .mapNotNull {
            it?.trim()
                ?.takeIf(
                    String::isNotEmpty
                )
        }
        .map {
            normalisePath(
                it
            )
        }
        .map {
            file(
                it
            )
        }
        .firstOrNull(
            File::exists
        )
        ?.absolutePath

val localPropertiesFile =
    file(
        "local.properties"
    )

data class LocalSdkPaths(
    val primary: String?,
    val windows: String?
)

val localSdkPaths: LocalSdkPaths =
    if (localPropertiesFile.exists()) {
        val props =
            Properties()
        FileInputStream(
            localPropertiesFile
        ).use {
            props.load(
                it
            )
        }
        LocalSdkPaths(
            primary = props.getProperty(
                "sdk.dir"
            ),
            windows = props.getProperty(
                "sdk.dir.windows"
            )
        )
    } else LocalSdkPaths(
        null,
        null
    )

val windowsLocalAppData =
    System.getenv(
        "LOCALAPPDATA"
    )
        ?.let { "$it\\Android\\Sdk" }
val windowsUserProfile =
    System.getenv(
        "USERPROFILE"
    )
        ?.let { "$it\\AppData\\Local\\Android\\Sdk" }

val gradlePropSdkDir =
    providers.gradleProperty(
        "android.sdkDirectory"
    ).orNull
val envSdkDir =
    System.getenv(
        "ANDROID_SDK_ROOT"
    )
        ?: System.getenv(
            "ANDROID_HOME"
        )

val detectedSdkDir =
    existingPath(
        System.getProperty(
            "sdk.dir"
        ),
        envSdkDir,
        gradlePropSdkDir,
        localSdkPaths.primary,
        windowsLocalAppData,
        windowsUserProfile,
        localSdkPaths.windows
    )

if (detectedSdkDir != null) {
    System.setProperty(
        "sdk.dir",
        detectedSdkDir
    )
    System.setProperty(
        "android.home",
        detectedSdkDir
    )
    System.setProperty(
        "ANDROID_HOME",
        detectedSdkDir
    )
    System.setProperty(
        "ANDROID_SDK_ROOT",
        detectedSdkDir
    )
}

val registeredModules =
    mutableSetOf<String>()

fun File.isGradleModuleDir(): Boolean =
    isDirectory &&
            (resolve("build.gradle.kts").isFile ||
                    resolve("build.gradle").isFile)

fun registerModule(moduleDir: File) {
    val relativePath =
        moduleDir.relativeTo(
            rootDir
        )
            .invariantSeparatorsPath
    val gradlePath =
        ":${relativePath.replace('/', ':')}"
    if (registeredModules.add(gradlePath)) {
        include(
            gradlePath
        )
        project(
            gradlePath
        ).projectDir =
            moduleDir
    }
}

val nestedModuleRoots =
    listOf(
        "apps",
        "shared",
        "infra",
        "hardware",
        "simulations",
        "integrations",
        "legacy"
    )
        .map {
            rootDir.resolve(
                it
            )
        }
        .filter(File::exists)

val skipDirectories =
    setOf(
        "build",
        "out",
        "tmp",
        "bin",
        ".git",
        ".gradle",
        ".idea",
        "generated",
        "captures"
    )

nestedModuleRoots.forEach { root ->
    root.walkTopDown()
        .onEnter { dir ->
            dir.name !in skipDirectories
        }
        .filter(File::isGradleModuleDir)
        .forEach(::registerModule)
}

rootDir
    .listFiles { candidate ->
        candidate.isGradleModuleDir()
    }
    ?.sortedBy { it.name }
    ?.forEach(::registerModule)
