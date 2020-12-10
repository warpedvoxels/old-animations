import com.nekkan.oldanimations.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.language.jvm.tasks.ProcessResources

plugins {
    kotlin("jvm") version com.nekkan.oldanimations.KotlinProperties.VERSION
    id("fabric-loom") version com.nekkan.oldanimations.FabricProperties.LOOM_VERSION
    java
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
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
