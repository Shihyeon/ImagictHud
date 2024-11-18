plugins {
    id("fabric-loom") version("1.8.+") apply(false)
    id("java")
}

val JAVA_VERSION by extra { 21 }

val MINECRAFT_VERSION by extra { "1.21.2" }
val FABRIC_LOADER_VERSION by extra { "0.16.7" }
val FABRIC_API_VERSION by extra { "0.106.1+1.21.2" }
val NEOFORGE_VERSION by extra { "21.2.0-beta" }

// This value can be set to null to disable Parchment.
val PARCHMENT_VERSION by extra { null }

val SODIUM_VERSION by extra { "mc1.21.2-0.6.0-beta.3" }
val YACL_VERSION by extra { "3.6.1+1.21.2" }
val MODMENU_VERSION by extra { "12.0.0-beta.1" }

// https://semver.org/
val MAVEN_GROUP by extra { "kr.shihyeon" }
val ARCHIVE_NAME by extra { "imagicthud" }
val MOD_VERSION by extra { "1.6.0" }

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.jar {
    enabled = false
}

subprojects {
    apply(plugin = "maven-publish")

    java.toolchain.languageVersion = JavaLanguageVersion.of(JAVA_VERSION)

    tasks.processResources {
        val propertiesMap = mapOf(
            "version" to createVersionString(),
            "minecraft_version" to MINECRAFT_VERSION,
            "fabric_loader_version" to FABRIC_LOADER_VERSION,
            "neoforge_version" to NEOFORGE_VERSION
        )

        inputs.properties(propertiesMap)

        filesMatching(listOf("META-INF/neoforge.mods.toml", "fabric.mod.json")) {
            expand(propertiesMap)
        }
    }

    version = createVersionString()
    group = MAVEN_GROUP

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(JAVA_VERSION)
    }

    tasks.withType<GenerateModuleMetadata>().configureEach {
        enabled = false
    }
}

fun createVersionString(): String {
    val builder = StringBuilder()

    val isReleaseBuild = project.hasProperty("build.release")
    val buildId = System.getenv("GITHUB_RUN_NUMBER")

    if (isReleaseBuild) {
        builder.append(MOD_VERSION)
    } else {
        builder.append(MOD_VERSION.substringBefore('-'))
        builder.append("-snapshot")
    }

    builder.append("+mc").append(MINECRAFT_VERSION)

    if (!isReleaseBuild) {
        if (buildId != null) {
            builder.append("-build.${buildId}")
        } else {
            builder.append("-local")
        }
    }

    return builder.toString()
}

tasks.register("printProperties") {
    doLast {
        println("MINECRAFT_VERSION=${MINECRAFT_VERSION}")
        println("MOD_VERSION=${MOD_VERSION}")
    }
}
