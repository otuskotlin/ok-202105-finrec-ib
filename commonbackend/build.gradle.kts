plugins {
    kotlin("jvm")
}


val kotestVersion: String by project

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test-junit5"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
