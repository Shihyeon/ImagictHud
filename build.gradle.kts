plugins {
    id("java")
    id("fabric-loom").version("1.7.+").apply(false)
}

val JAVA_VERSION by extra { 21 }

val MINECRAFT_VERSION by extra { "1.21" }
val NEOFORGE_VERSION by extra { "21.0.167" }
val FABRIC_LOADER_VERSION by extra { "0.15.11" }
val FABRIC_API_VERSION by extra { "0.100.8+1.21" }

val SODIUM_VERSION by extra { "mc1.21-0.5.11" }
val YACL_VERSION by extra { "3.5.0+1.21" }
val MODMENU_VERSION by extra { "11.0.1" }

// https://semver.org/
val MOD_VERSION by extra { "1.5.4" }
val MOD_TYPE by extra { "release" } // release/beta/alpha

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

subprojects {
    apply(plugin = "maven-publish")

    java.toolchain.languageVersion = JavaLanguageVersion.of(JAVA_VERSION)

    fun createVersionString(): String {
        val builder = StringBuilder()

        val isReleaseBuild = System.getProperty("build.release") != null
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

    tasks.processResources {
        filesMatching("META-INF/neoforge.mods.toml") {
            expand(mapOf("version" to createVersionString()))
        }
    }

    version = createVersionString()
    group = "kr.shihyeon"

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(JAVA_VERSION)
    }

    tasks.withType<GenerateModuleMetadata>().configureEach {
        enabled = false
    }
}
