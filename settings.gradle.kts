rootProject.name = "old-animations"
enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
    repositories {
        jcenter()
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        gradlePluginPortal()
    }
}
