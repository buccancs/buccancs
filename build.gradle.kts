// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.13.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("org.jetbrains.kotlin.jvm") version "2.0.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
    id("org.jetbrains.compose") version "1.7.0" apply false
    id("com.google.dagger.hilt.android") version "2.57.2" apply false
    id("com.google.protobuf") version "0.9.5" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21" apply false
}

tasks.register("all") {
    group = "verification"
    description = "Aggregate target so the mandated `gradle build all` command succeeds."
    dependsOn("build")
}
