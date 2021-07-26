rootProject.name = "finrec"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
    }
}

include("ok-m1l1")
include("ok-m1l3-oop")
include("ok-m1l4")
include("ok-m1l5")
include("ok-m1l6-flows-and-channels")
include("ok-m1l7-kmp")
include("ok-m2l2-testing")
include("ok-m2l4-practice")
include("common")
include("common-finrec-backend")
