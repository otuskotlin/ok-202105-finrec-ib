plugins {
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
}

group = "nuwak.finrec"
version = "0.1.0"

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}
