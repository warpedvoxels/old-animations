rootProject.name = "old-animations"
include(":fabric")

pluginManagement {
    repositories {
        maven(url = "https://maven.quiltmc.org/repository/release") {
            name = "Quilt"
        }
        maven(url = "https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("mod") {
            from(files("${rootProject.projectDir}/mod.versions.toml"))
        }
    }
}