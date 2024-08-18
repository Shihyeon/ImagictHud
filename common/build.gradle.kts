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

repositories {
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
    minecraft("com.mojang:minecraft:${MINECRAFT_VERSION}")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:${FABRIC_LOADER_VERSION}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${FABRIC_API_VERSION}")

    modCompileOnly("maven.modrinth:sodium:${SODIUM_VERSION}")
    //modCompileOnly("dev.isxander:yet-another-config-lib:${YACL_VERSION}-fabric")
    //modCompileOnly("com.terraformersmc:modmenu:${MODMENU_VERSION}")
}

sourceSets {
    val main = getByName("main")

    main.apply {
        java {}
    }
}

//loom {
//    accessWidenerPath = file("src/main/resources/imagicthud.accesswidener")
//}

tasks {
    processResources {
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
        from(rootDir.resolve("LICENSE"))
    }
}

tasks.configureEach {
    group = null
}
