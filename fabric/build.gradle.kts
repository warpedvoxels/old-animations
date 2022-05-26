@Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")
plugins {
    alias(mod.plugins.fabric.loom)
    alias(mod.plugins.quilt.mappings.on.loom)
    alias(mod.plugins.loom.quiltflower)
}

repositories {
    maven(url = "https://maven.quiltmc.org/repository/release") {
        name = "Quilt"
    }
    maven(url = "https://maven.fabricmc.net/") {
        name = "Fabric"
    }
    mavenCentral()
}

dependencies {
    minecraft(mod.minecraft)
    mappings(loom.layered {
        addLayer(quiltMappings.mappings(mod.quilt.mappings.get().toString() + ":v2"))
    })
    modImplementation(mod.fabric.loader)
    modImplementation(mod.fabric.api)
}

tasks {
    processResources {
        inputs.property("versions", project.version)
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    // If you plan to use a different file for the license, don't forget to change the file name here!
    jar {
        from("LICENSE.txt") {
            rename { "${it}_old-animations" }
        }
    }
}

java {
    // This project uses Java 18.
    sourceCompatibility = JavaVersion.VERSION_18

    // Minecraft 1.18 (1.18-pre2) upwards uses Java 17.
    targetCompatibility = JavaVersion.VERSION_17

    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()
}
