plugins {
    // Provisions the JDK 17 toolchain joml2-records-jdk17 compiles on.
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    // Uploads every project's publications to Maven Central in one deployment.
    id("com.gradleup.nmcp.settings") version "1.6.2"
}

rootProject.name = "JOML2"

dependencyResolutionManagement {
    repositories { mavenCentral() }
}

// One subproject per published artifact; the directory name is the artifactId.
file("modules").listFiles()!!.filter { it.isDirectory }.sorted().forEach {
    include(it.name)
    project(":${it.name}").projectDir = it
}

nmcpSettings {
    centralPortal {
        username = providers.gradleProperty("mavenCentralUsername")
        password = providers.gradleProperty("mavenCentralPassword")
    }
}
