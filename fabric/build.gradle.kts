plugins {
    id("java")
    id("idea")
    id("fabric-loom").version ("1.7.+")
}

val MINECRAFT_VERSION: String by rootProject.extra
val FABRIC_LOADER_VERSION: String by rootProject.extra
val FABRIC_API_VERSION: String by rootProject.extra

val SODIUM_VERSION: String by rootProject.extra
val YACL_VERSION: String by rootProject.extra
val MODMENU_VERSION: String by rootProject.extra

val MOD_VERSION: String by rootProject.extra

base {
    archivesName.set("imagicthud-fabric")
}

repositories {
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    minecraft("com.mojang:minecraft:${MINECRAFT_VERSION}")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:${FABRIC_LOADER_VERSION}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${FABRIC_API_VERSION}")

//    modCompileOnly("maven.modrinth:sodium:${SODIUM_VERSION}")
//    modCompileOnly("dev.isxander:yet-another-config-lib:${YACL_VERSION}-fabric")
    modCompileOnly("com.terraformersmc:modmenu:${MODMENU_VERSION}")

    implementation(project.project(":common").sourceSets.getByName("main").output)
}

tasks.named("compileTestJava").configure {
    enabled = false
}

tasks.named("test").configure {
    enabled = false
}

loom {
    if (project(":common").file("src/main/resources/imagicthud.accesswidener").exists())
        accessWidenerPath.set(project(":common").file("src/main/resources/imagicthud.accesswidener"))

    @Suppress("UnstableApiUsage")

    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("run")
        }
    }
}

tasks {
    processResources {
        from(project.project(":common").sourceSets.main.get().resources)

        val propertiesMap = mapOf(
            "version" to project.version,
            "minecraft_version" to MINECRAFT_VERSION,
            "loader_version" to FABRIC_LOADER_VERSION,
            "fabric" to FABRIC_API_VERSION
        )

        inputs.properties(propertiesMap)

        filesMatching("fabric.mod.json") {
            expand(propertiesMap)
        }
    }

    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(zipTree(project.project(":common").tasks.jar.get().archiveFile))
    }
}
