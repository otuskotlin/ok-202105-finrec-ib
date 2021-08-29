plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":be-common"))
    implementation(project(":finrec-transport-main-openapi"))

    testImplementation(kotlin("test"))
}