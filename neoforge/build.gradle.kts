plugins {
    id("idea")
    id("net.neoforged.moddev").version("1.0.+")
    id("java-library")
}

val MINECRAFT_VERSION: String by rootProject.extra
val NEOFORGE_VERSION: String by rootProject.extra

val SODIUM_VERSION: String by rootProject.extra
val YACL_VERSION: String by rootProject.extra

val MOD_VERSION: String by rootProject.extra

base {
    archivesName = "imagicthud-neoforge"
}

neoForge {
    // Specify the version of NeoForge to use.
    version = NEOFORGE_VERSION

    runs {
        create("client") {
            client()
        }
    }

    mods {
        create("imagicthud") {
            sourceSet(sourceSets.main.get())
            sourceSet(project.project(":common").sourceSets.main.get())
        }
    }
}


tasks.named("compileTestJava").configure {
    enabled = false
}

dependencies {
    //modImplementation("net.neoforged:neoforge:${NEOFORGE_VERSION}")

    compileOnly(project.project(":common").sourceSets.main.get().output)
}

java.toolchain.languageVersion = JavaLanguageVersion.of(21)
