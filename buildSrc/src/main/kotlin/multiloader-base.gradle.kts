plugins {
    id("java-library")
    id("idea")
}

group = "kr.shihyeon"
version = BuildConfig.createVersionString(project)

java.toolchain.languageVersion = JavaLanguageVersion.of(BuildConfig.JAVA_VERSION)

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(BuildConfig.JAVA_VERSION)
}

tasks.withType<GenerateModuleMetadata>().configureEach {
    enabled = false
}

repositories {
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

    exclusiveContent {
        forRepository {
            maven {
                name = "Parchment"
                url = uri("https://maven.parchmentmc.org")
            }
        }
        filter {
            includeGroup("org.parchmentmc.data")
        }
    }

    maven {
        name = "Xander Maven"
        url = uri("https://maven.isxander.dev/releases")
    }
}