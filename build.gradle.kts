object Constants {
    // https://fabricmc.net/develop/
    const val MINECRAFT_VERSION: String = "1.21"
    const val YARN_MAPPINGS: String = "1.21+build.9"
    const val FABRIC_LOADER_VERSION: String = "0.15.11"
    const val FABRIC_API_VERSION: String = "0.100.7+1.21"

    const val SODIUM_VERSION: String = "mc1.21-0.5.11"
    const val YACL_VERSION: String = "3.5.0+1.21-fabric"
    const val MODMENU_VERSION: String = "11.0.1"

    // https://semver.org/
    const val MOD_VERSION: String = "1.5.0"
    const val MOD_TYPE: String = "release" // release/beta/alpha
}

plugins {
    id("fabric-loom") version "1.7.+"
    id("maven-publish")
    //id("com.modrinth.minotaur") version "2.+"
}

base {
    archivesName = "imagicthud"

    group = "kr.shihyeon"
    version = createVersionString()
}

loom {
    accessWidenerPath = file("${rootProject.projectDir}/src/main/resources/imagicthud.accesswidener")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
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
    //mappings(loom.officialMojangMappings())
    mappings("net.fabricmc:yarn:${Constants.YARN_MAPPINGS}:v2")
    modImplementation("net.fabricmc:fabric-loader:${Constants.FABRIC_LOADER_VERSION}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${Constants.FABRIC_API_VERSION}")

    modImplementation("maven.modrinth:sodium:${Constants.SODIUM_VERSION}")
    modImplementation("dev.isxander:yet-another-config-lib:${Constants.YACL_VERSION}")
    modImplementation("com.terraformersmc:modmenu:${Constants.MODMENU_VERSION}")
}

tasks {
    processResources {
        val propertiesMap = mapOf(
            "version" to project.version,
            "minecraft_version" to Constants.MINECRAFT_VERSION,
            "loader_version" to Constants.FABRIC_LOADER_VERSION,
            "fabric" to Constants.FABRIC_API_VERSION
        )

        inputs.properties(propertiesMap)

        filesMatching("fabric.mod.json") {
            expand(propertiesMap)
        }
    }

    jar {
        from("${rootProject.projectDir}/LICENSE")
    }
}

// ensure that the encoding is set to UTF-8, no matter what the system default is
// this fixes some edge cases with special characters not displaying correctly
// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release = 21
    //options.compilerArgs.add("-Xlint:deprecation")
}

//modrinth {
//    token.set(System.getenv("MODRINTH_TOKEN"))
//    changelog.set(file("changelog.txt").readText())
//    projectId.set("uWeqs5CX")
//    versionNumber.set("mc" + Constants.MINECRAFT_VERSION + "-" + Constants.MOD_VERSION)
//    versionName.set("Imagict Hud " + Constants.MOD_VERSION)
//    versionType.set(Constants.MOD_TYPE)
//    uploadFile.set(tasks.remapJar)
//    gameVersions.addAll(arrayListOf(Constants.MINECRAFT_VERSION))
//    loaders.add("fabric")
//    dependencies {
//        required.project("P7dR8mSH") // Fabric API
//        required.project("1eAoo2KR") // YetAnotherConfigLib
//        optional.project("mOgUt4GM") // Mod Menu
//    }
//}

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
