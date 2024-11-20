plugins {
    id("multiloader-base")
    id("java-library")

    id("fabric-loom") version ("1.8.+")
}

base {
    archivesName = "imagicthud-common"
}

sourceSets {
    val main = getByName("main")
    val api = create("api")

    api.apply {
        java {
            compileClasspath += main.compileClasspath
        }
    }

    main.apply {
        java {
            compileClasspath += api.output
        }
    }
}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = BuildConfig.MINECRAFT_VERSION)
    mappings(loom.layered {
        officialMojangMappings()

        if (BuildConfig.PARCHMENT_VERSION != null) {
            parchment("org.parchmentmc.data:parchment-${BuildConfig.MINECRAFT_VERSION}:${BuildConfig.PARCHMENT_VERSION}@zip")
        }
    })

    compileOnly("io.github.llamalad7:mixinextras-common:0.3.5")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.3.5")

    compileOnly("net.fabricmc:sponge-mixin:0.13.2+mixin.0.8.5")
    compileOnly("net.fabricmc:fabric-loader:${BuildConfig.FABRIC_LOADER_VERSION}")

    fun addDependentFabricModule(name: String) {
        val module = fabricApi.module(name, BuildConfig.FABRIC_API_VERSION)
        modCompileOnly(module)
    }

    addDependentFabricModule("fabric-api-base")
    addDependentFabricModule("fabric-key-binding-api-v1")
    addDependentFabricModule("fabric-lifecycle-events-v1")
    addDependentFabricModule("fabric-resource-loader-v0")
    addDependentFabricModule("fabric-screen-api-v1")

    modCompileOnly("maven.modrinth:sodium:${BuildConfig.SODIUM_VERSION}-fabric")
    modCompileOnly("dev.isxander:yet-another-config-lib:${BuildConfig.YACL_VERSION}-fabric")
}

loom {
    accessWidenerPath = file("src/main/resources/imagicthud.common.accesswidener")

    mixin {
        useLegacyMixinAp = false
    }
}

fun exportSourceSetJava(name: String, sourceSet: SourceSet) {
    val configuration = configurations.create("${name}Java") {
        isCanBeResolved = true
        isCanBeConsumed = true
    }

    val compileTask = tasks.getByName<JavaCompile>(sourceSet.compileJavaTaskName)
    artifacts.add(configuration.name, compileTask.destinationDirectory) {
        builtBy(compileTask)
    }
}

fun exportSourceSetResources(name: String, sourceSet: SourceSet) {
    val configuration = configurations.create("${name}Resources") {
        isCanBeResolved = true
        isCanBeConsumed = true
    }

    val compileTask = tasks.getByName<ProcessResources>(sourceSet.processResourcesTaskName)
    compileTask.apply {
        //exclude("**/README.txt")
        exclude("/*.accesswidener")
    }

    artifacts.add(configuration.name, compileTask.destinationDir) {
        builtBy(compileTask)
    }
}

// Exports the compiled output of the source set to the named configuration.
fun exportSourceSet(name: String, sourceSet: SourceSet) {
    exportSourceSetJava(name, sourceSet)
    exportSourceSetResources(name, sourceSet)
}

exportSourceSet("commonMain", sourceSets["main"])
exportSourceSet("commonApi", sourceSets["api"])

tasks.jar { enabled = false }
tasks.remapJar { enabled = false }