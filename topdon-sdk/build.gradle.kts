plugins {
    id("java-library")
}

dependencies {
    api(files("libs/topdon-classes.jar"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
