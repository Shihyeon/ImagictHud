object Constants {
    const val JAVA_VERSION: Int = 17

    // https://fabricmc.net/develop/
    const val MINECRAFT_VERSION: String = "1.20.4"
    const val YARN_MAPPINGS: String = "1.20.4+build.1"
    const val FABRIC_LOADER_VERSION: String = "0.15.11"
    const val FABRIC_API_VERSION: String = "0.97.2+1.20.4"

    const val SODIUM_VERSION: String = "mc1.20.4-0.5.8"
    const val YACL_VERSION: String = "3.5.0+1.20.4"
    const val MODMENU_VERSION: String = "11.0.1"

    // https://semver.org/
    const val MOD_VERSION: String = "1.6.0-beta"
    const val MOD_TYPE: String = "beta" // release/beta/alpha
}

plugins {
    id("fabric-loom") version("1.7.+")
    id("java")
    id("maven-publish")
}

base {
    archivesName = "imagicthud"

    group = "kr.shihyeon"
    version = createVersionString()
}

loom {
    accessWidenerPath = file("src/main/resources/imagicthud.accesswidener")
}

java {
    sourceCompatibility = JavaVersion.toVersion(Constants.JAVA_VERSION)
    targetCompatibility = JavaVersion.toVersion(Constants.JAVA_VERSION)
}

repositories {
    maven("https://maven.terraformersmc.com/releases/")
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
    minecraft("com.mojang:minecraft:${Constants.MINECRAFT_VERSION}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${Constants.FABRIC_LOADER_VERSION}")

    fun addEmbeddedFabricModule(name: String) {
        val module = fabricApi.module(name, Constants.FABRIC_API_VERSION)
        modImplementation(module)
        include(module)
    }

    addEmbeddedFabricModule("fabric-api-base")
    addEmbeddedFabricModule("fabric-key-binding-api-v1")
    addEmbeddedFabricModule("fabric-lifecycle-events-v1")
    addEmbeddedFabricModule("fabric-resource-loader-v0")

    modCompileOnly("maven.modrinth:sodium:${Constants.SODIUM_VERSION}-fabric")
    modCompileOnly("dev.isxander:yet-another-config-lib:${Constants.YACL_VERSION}-fabric")
    modCompileOnly("com.terraformersmc:modmenu:${Constants.MODMENU_VERSION}")
}

tasks {
    processResources {
        val propertiesMap = mapOf(
            "version" to project.version,
            "minecraft_version" to Constants.MINECRAFT_VERSION,
            "loader_version" to Constants.FABRIC_LOADER_VERSION,
        )

        inputs.properties(propertiesMap)

        filesMatching("fabric.mod.json") {
            expand(propertiesMap)
        }
    }

    jar {
        from(projectDir.resolve("LICENSE"))
    }
}

// ensure that the encoding is set to UTF-8, no matter what the system default is
// this fixes some edge cases with special characters not displaying correctly
// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release = Constants.JAVA_VERSION
    //options.compilerArgs.add("-Xlint:deprecation")
}

fun createVersionString(): String {
    val builder = StringBuilder()

    val isReleaseBuild = System.getProperty("build.release") != null
    val buildId = System.getenv("GITHUB_RUN_NUMBER")

    if (isReleaseBuild) {
        builder.append(Constants.MOD_VERSION)
    } else {
        builder.append(Constants.MOD_VERSION.substringBefore('-'))
        builder.append("-snapshot")
    }

    builder.append("+mc").append(Constants.MINECRAFT_VERSION)

    if (!isReleaseBuild) {
        if (buildId != null) {
            builder.append("-build.${buildId}")
        } else {
            builder.append("-local")
        }
    }

    return builder.toString()
}
