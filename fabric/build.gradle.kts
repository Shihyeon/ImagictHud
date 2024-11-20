plugins {
    id("multiloader-platform")

    id("fabric-loom") version ("1.8.+")
}

base {
    archivesName.set("imagicthud-fabric")
}

val configurationCommonModJava: Configuration = configurations.create("commonJava") {
    isCanBeResolved = true
}
val configurationCommonModResources: Configuration = configurations.create("commonResources") {
    isCanBeResolved = true
}

repositories {
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    configurationCommonModJava(project(path = ":common", configuration = "commonMainJava"))
    configurationCommonModJava(project(path = ":common", configuration = "commonApiJava"))

    configurationCommonModResources(project(path = ":common", configuration = "commonMainResources"))
    configurationCommonModResources(project(path = ":common", configuration = "commonApiResources"))
}

sourceSets.apply {
    main {
        compileClasspath += configurationCommonModJava
        runtimeClasspath += configurationCommonModJava
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${BuildConfig.MINECRAFT_VERSION}")
    mappings(loom.layered {
        officialMojangMappings()

        if (BuildConfig.PARCHMENT_VERSION != null) {
            parchment("org.parchmentmc.data:parchment-${BuildConfig.MINECRAFT_VERSION}:${BuildConfig.PARCHMENT_VERSION}@zip")
        }
    })
    modImplementation("net.fabricmc:fabric-loader:${BuildConfig.FABRIC_LOADER_VERSION}")

    fun addEmbeddedFabricModule(name: String) {
        val module = fabricApi.module(name, BuildConfig.FABRIC_API_VERSION)
        modImplementation(module)
        include(module)
    }

    // Fabric API modules
    addEmbeddedFabricModule("fabric-api-base")
    addEmbeddedFabricModule("fabric-key-binding-api-v1")
    addEmbeddedFabricModule("fabric-lifecycle-events-v1")
    addEmbeddedFabricModule("fabric-resource-loader-v0")
    addEmbeddedFabricModule("fabric-screen-api-v1")

    modCompileOnly("com.terraformersmc:modmenu:${BuildConfig.MODMENU_VERSION}")
    modCompileOnly("maven.modrinth:sodium:${BuildConfig.SODIUM_VERSION}-fabric")
    modCompileOnly("dev.isxander:yet-another-config-lib:${BuildConfig.YACL_VERSION}-fabric")
}

loom {
    accessWidenerPath.set(file("src/main/resources/imagicthud.fabric.accesswidener"))

    mixin {
        useLegacyMixinAp = false
    }

    runs {
        named("client") {
            client()
            configName = "Fabric/Client"
            appendProjectPathToConfigName = false
            ideConfigGenerated(true)
            runDir("run")
        }
    }
}

tasks {
    jar {
        from(configurationCommonModJava)
    }

    remapJar {
        destinationDirectory.set(file(rootProject.layout.buildDirectory).resolve("mods"))
    }

    processResources {
        from(configurationCommonModResources)
    }
}