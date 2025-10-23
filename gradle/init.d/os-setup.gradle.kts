import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties
import org.gradle.internal.os.OperatingSystem

/**
 * Dynamically sets SDK/JDK/Gradle locations based on host OS.
 *
 * Precedence for each entry:
 *   1. Environment variables (ANDROID_SDK_ROOT, JAVA_HOME, GRADLE_USER_HOME, etc.)
 *   2. Matching entry in gradle/os-paths.properties (ignored if missing)
 *
 * Properties supported in gradle/os-paths.properties:
 *   windows.android.sdk, windows.android.ndk, windows.java.home, windows.gradle.user.home
 *   linux.android.sdk,   linux.android.ndk,   linux.java.home,   linux.gradle.user.home
 *   mac.android.sdk,     mac.android.ndk,     mac.java.home,     mac.gradle.user.home
 *
 * This init script runs on every Gradle invocation so Windows, WSL, and macOS
 * builds share the same configuration automatically.
 */

val os = OperatingSystem.current()
val osKey = when {
    os.isWindows -> "windows"
    os.isLinux -> "linux"
    os.isMacOsX -> "mac"
    else -> null
}

if (osKey == null) {
    println("⚠️  Unsupported host OS '${System.getProperty(\"os.name\")}'. Skipping os-setup.gradle.kts.")
    return
}

fun env(name: String): String? = System.getenv(name)?.takeIf { it.isNotBlank() }

val config = Properties()
val configFile = File(rootDir, "gradle/os-paths.properties")
if (configFile.isFile) {
    FileInputStream(configFile).use(config::load)
}

val toolchainRoot = File(rootDir, "toolchains").apply { mkdirs() }
val osToolchainRoot = when (osKey) {
    "windows" -> File(toolchainRoot, "windows")
    "linux" -> File(toolchainRoot, "linux")
    "mac" -> File(toolchainRoot, "mac")
    else -> toolchainRoot
}.apply { mkdirs() }
val homeToolchainRoot = File(System.getProperty("user.home"), "toolchain")

fun dirHasContent(dir: File): Boolean =
    dir.exists() && dir.isDirectory && dir.list()?.isNotEmpty() == true

fun javaHomeLooksValid(dir: File): Boolean =
    dir.exists() && dir.isDirectory && (
        File(dir, "bin/java").exists() ||
        File(dir, "bin/java.exe").exists()
    )

fun defaultToolchainPath(
    child: String,
    ensureDir: Boolean = false,
    validator: (File) -> Boolean = File::exists
): String? {
    val candidates = listOf(
        File(osToolchainRoot, child),
        File(toolchainRoot, child),
        File(homeToolchainRoot, child)
    )

    candidates.forEach { dir ->
        if (validator(dir)) {
            if (ensureDir && !dir.exists()) {
                dir.mkdirs()
            }
            return dir.absolutePath
        }
    }

    if (ensureDir) {
        candidates.forEach { dir ->
            if (!dir.exists()) {
                dir.mkdirs()
            }
            if (validator(dir)) {
                return dir.absolutePath
            }
        }
    }

    return null
}

fun Properties.getOsSpecific(keySuffix: String): String? =
    getProperty("$osKey.$keySuffix")?.takeIf { it.isNotBlank() }

fun normalizePath(raw: String): String {
    val trimmed = raw.trim()
    if (trimmed.isEmpty()) return trimmed

    val windowsAbsolute = Regex("^[A-Za-z]:\\\\.*").matches(trimmed)
    val normalizedForward = trimmed.replace('\\', '/')
    val isUnixAbsolute = normalizedForward.startsWith("/") || normalizedForward.startsWith("~/")

    return when {
        os.isLinux && windowsAbsolute -> {
            val driveLetter = trimmed.substring(0, 1).lowercase()
            val tail = trimmed.substring(2).replace('\\', '/').trimStart('/')
            "/mnt/$driveLetter/$tail"
        }
        os.isWindows && windowsAbsolute -> trimmed.replace('/', '\\')
        os.isWindows -> File(rootDir, normalizedForward).absolutePath.replace('/', '\\')
        isUnixAbsolute -> normalizedForward
        else -> File(rootDir, normalizedForward).absolutePath.replace('\\', '/')
    }
}

fun resolvePath(vararg candidates: Pair<String, String?>): String? {
    candidates.forEach { (_, value) ->
        if (value != null && value.isNotBlank()) {
            return normalizePath(value)
        }
    }
    return null
}

val androidSdk = resolvePath(
    "env.ANDROID_SDK_ROOT" to env("ANDROID_SDK_ROOT"),
    "env.ANDROID_HOME" to env("ANDROID_HOME"),
    "config.android.sdk" to config.getOsSpecific("android.sdk"),
    "repo.toolchains.android.sdk" to defaultToolchainPath(
        child = "android-sdk",
        ensureDir = false,
        validator = ::dirHasContent
    )
)

val androidNdk = resolvePath(
    "env.ANDROID_NDK_ROOT" to env("ANDROID_NDK_ROOT"),
    "config.android.ndk" to config.getOsSpecific("android.ndk"),
    "repo.toolchains.android.ndk" to defaultToolchainPath(
        child = "android-ndk",
        ensureDir = false,
        validator = ::dirHasContent
    )
)

val javaHome = resolvePath(
    "env.JAVA_HOME" to env("JAVA_HOME"),
    "env.JDK_HOME" to env("JDK_HOME"),
    "config.java.home" to config.getOsSpecific("java.home"),
    "repo.toolchains.java.home" to defaultToolchainPath(
        child = "java",
        ensureDir = false,
        validator = ::javaHomeLooksValid
    )
)

val gradleUserHome = resolvePath(
    "env.GRADLE_USER_HOME" to env("GRADLE_USER_HOME"),
    "config.gradle.user.home" to config.getOsSpecific("gradle.user.home"),
    "repo.toolchains.gradle.user.home" to defaultToolchainPath(
        child = "gradle-user-home",
        ensureDir = true,
        validator = { true }
    )
)

if (javaHome != null) {
    gradle.startParameter.javaHome = File(javaHome)
    System.setProperty("org.gradle.java.home", javaHome)
}

if (gradleUserHome != null) {
    gradle.startParameter.gradleUserHomeDir = File(gradleUserHome)
    System.setProperty("GRADLE_USER_HOME", gradleUserHome)
}

fun updateLocalProperties(file: File, updates: Map<String, String?>) {
    if (updates.values.all { it.isNullOrBlank() }) {
        return
    }

    val props = Properties()
    if (file.isFile) {
        FileInputStream(file).use(props::load)
    }

    var dirty = false
    updates.forEach { (key, rawValue) ->
        val value = rawValue?.takeIf { it.isNotBlank() }
        val current = props.getProperty(key)?.trim()

        if (value == null && current != null) {
            props.remove(key)
            dirty = true
        } else if (value != null && value != current) {
            props.setProperty(key, value)
            dirty = true
        }
    }

    if (dirty || !file.exists()) {
        file.parentFile?.mkdirs()
        FileOutputStream(file).use {
            props.store(it, "Managed by gradle/init.d/os-setup.gradle.kts")
        }
    }
}

updateLocalProperties(
    File(rootDir, "local.properties"),
    mapOf(
        "sdk.dir" to androidSdk,
        "ndk.dir" to androidNdk
    )
)
