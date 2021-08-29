rootProject.name = "ib.finrec"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val openApiVersion: String by settings

        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("org.openapi.generator") version openApiVersion
    }
}

include("common")
include("m2l2-testing")
include("finrec-transport-main-openapi")
include("be-common")
include("finrec-be-transport-mapping-openapi")
