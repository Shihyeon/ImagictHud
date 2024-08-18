pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            name = "NeoForge"
            url = uri("https://maven.neoforged.net/releases/")
        }

        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "ImagictHud"

include(
    "common",
    "fabric",
    "neoforge"
)