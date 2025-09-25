pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.5.2"
        id("org.jetbrains.kotlin.android") version "2.0.10"
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.10"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Proyecto1"
include(":app")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
