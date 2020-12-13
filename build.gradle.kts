import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.nekkan.oldanimations.CompilerArgs
import com.nekkan.oldanimations.DependencyList
import com.nekkan.oldanimations.FabricProperties
import com.nekkan.oldanimations.KotlinProperties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version com.nekkan.oldanimations.KotlinProperties.VERSION
    id("fabric-loom") version com.nekkan.oldanimations.FabricProperties.LOOM_VERSION
    id("com.github.johnrengelman.shadow") version com.nekkan.oldanimations.DependencyList.SHADOW
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
    maven { url = uri("http://maven.fabricmc.net/") }
}

dependencies {
    minecraft("com.mojang:minecraft:${FabricProperties.MINECRAFT_VERSION}")
    mappings("net.fabricmc:yarn:${FabricProperties.YARN_MAPPINGS_VERSION}:v2")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${FabricProperties.FABRIC_VERSION}")
    modImplementation("net.fabricmc:fabric-loader:${FabricProperties.FABRIC_LOADER_VERSION}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${KotlinProperties.FABRIC_KOTLIN_VERSION}")
    compileOnly(DependencyList.LOG4J_CORE)
    compileOnly(DependencyList.LOG4J_API)
}

tasks.withType<ShadowJar> {
    // Enable ShadowJAR minimization to reduce the .jar size.
    // Read more: https://imperceptiblethoughts.com/shadow/configuration/minimizing/
    minimize()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = CompilerArgs.toList()
        jvmTarget = "1.8"
    }
}

tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}
