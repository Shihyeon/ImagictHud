plugins {
    id("idea")
    id("net.neoforged.moddev") version "2.0.28-beta"
    id("java-library")
}

val MINECRAFT_VERSION: String by rootProject.extra
val PARCHMENT_VERSION: String? by rootProject.extra
val NEOFORGE_VERSION: String by rootProject.extra
val MOD_VERSION: String by rootProject.extra

val SODIUM_VERSION: String by rootProject.extra
val YACL_VERSION: String by rootProject.extra

base {
    archivesName = "imagicthud-neoforge"
}

sourceSets {
    main.get().apply {
        compileClasspath += project(":common").sourceSets.main.get().output
    }
}

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

val serviceJar: Jar by tasks.creating(Jar::class) {
    from(rootDir.resolve("LICENSE"))
}

configurations {
    create("serviceConfig") {
        isCanBeConsumed = true
        isCanBeResolved = true // false
        outgoing {
            artifact(serviceJar)
        }
    }
}

dependencies {
    compileOnly(project(":common"))
    jarJar(project(":neoforge", "serviceConfig"))

    compileOnly("maven.modrinth:sodium:${SODIUM_VERSION}-neoforge")
    compileOnly("dev.isxander:yet-another-config-lib:${YACL_VERSION}-neoforge")
}

tasks.jar {
    from(rootDir.resolve("LICENSE"))

    filesMatching("neoforge.mods.toml") {
        expand(mapOf("version" to MOD_VERSION))
    }
}

neoForge {
    // Specify the version of NeoForge to use.
    version = NEOFORGE_VERSION

    if (PARCHMENT_VERSION != null) {
        parchment {
            minecraftVersion = MINECRAFT_VERSION
            mappingsVersion = PARCHMENT_VERSION
        }
    }

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

fun includeDep(dependency: String, closure: Action<ExternalModuleDependency>) {
    dependencies.implementation(dependency, closure)
    dependencies.jarJar(dependency, closure)
}

fun includeDep(dependency: String) {
    dependencies.implementation(dependency)
    dependencies.jarJar(dependency)
}

// NeoGradle compiles the game, but we don't want to add our common code to the game's code
val notNeoTask: (Task) -> Boolean = { it: Task -> !it.name.startsWith("neo") && !it.name.startsWith("compileService") }

tasks.withType<JavaCompile>().matching(notNeoTask).configureEach {
    source(project(":common").sourceSets.main.get().allSource)
}

tasks.withType<ProcessResources>().matching(notNeoTask).configureEach {
    from(project(":common").sourceSets.main.get().resources)
}

tasks.named("compileTestJava").configure {
    enabled = false
}

java.toolchain.languageVersion = JavaLanguageVersion.of(21)