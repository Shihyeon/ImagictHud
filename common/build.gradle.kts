plugins {
    id("java")
    id("idea")
    id("fabric-loom") version ("1.8.+")
}

val MINECRAFT_VERSION: String by rootProject.extra
val PARCHMENT_VERSION: String? by rootProject.extra
val FABRIC_LOADER_VERSION: String by rootProject.extra
val FABRIC_API_VERSION: String by rootProject.extra

val SODIUM_VERSION: String by rootProject.extra
val YACL_VERSION: String by rootProject.extra

repositories {
    maven("https://maven.parchmentmc.org/")
    maven {
        name = "Xander Maven"
        url = uri("https://maven.isxander.dev/releases")
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }
}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = MINECRAFT_VERSION)
    mappings(loom.layered() {
        officialMojangMappings()
        if (PARCHMENT_VERSION != null) {
            parchment("org.parchmentmc.data:parchment-${MINECRAFT_VERSION}:${PARCHMENT_VERSION}@zip")
        }
    })
    compileOnly("io.github.llamalad7:mixinextras-common:0.3.5")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.3.5")
    compileOnly("net.fabricmc:sponge-mixin:0.13.2+mixin.0.8.5")

    fun addDependentFabricModule(name: String) {
        val module = fabricApi.module(name, FABRIC_API_VERSION)
        modCompileOnly(module)
    }

    addDependentFabricModule("fabric-api-base")
    addDependentFabricModule("fabric-key-binding-api-v1")
    addDependentFabricModule("fabric-lifecycle-events-v1")
    addDependentFabricModule("fabric-resource-loader-v0")
    addDependentFabricModule("fabric-screen-api-v1")

    modCompileOnly("maven.modrinth:sodium:${SODIUM_VERSION}-fabric")
    modCompileOnly("dev.isxander:yet-another-config-lib:${YACL_VERSION}-fabric")
}

loom {
    mixin {
        defaultRefmapName = "imagicthud.refmap.json"
    }
    accessWidenerPath = file("src/main/resources/imagicthud.accesswidener")

    mods {
        val main by creating { // to match the default mod generated for Forge
            sourceSet("main")
        }
    }
}

tasks {
    jar {
        from(rootDir.resolve("LICENSE"))
    }
}

// This trick hides common tasks in the IDEA list.
tasks.configureEach {
    group = null
}