pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.scijava.org/content/repositories/public")
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.scijava.org/content/repositories/public")
        maven("https://jitpack.io")
    }
}

rootProject.name = "buccancs"

val localModules = rootDir
    .listFiles { candidate ->
        candidate.isDirectory &&
            (candidate.resolve("build.gradle.kts").isFile || candidate.resolve("build.gradle").isFile)
    }
    ?.sortedBy { it.name }
    ?: emptyList()

localModules.forEach { moduleDir ->
    include(":${moduleDir.name}")
    project(":${moduleDir.name}").projectDir = moduleDir
}
