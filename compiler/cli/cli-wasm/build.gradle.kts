plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":compiler:util"))
    api(project(":compiler:cli-common"))
    api(project(":compiler:cli"))
    api(project(":compiler:frontend"))
    api(project(":compiler:backend-common"))
    api(project(":compiler:fir:fir-serialization"))
    api(project(":compiler:ir.backend.common"))
    api(project(":compiler:ir.serialization.js"))
    api(project(":compiler:ir.tree"))
    api(project(":compiler:backend.wasm"))
    api(project(":wasm:wasm.frontend"))
    api(project(":wasm:wasm.config"))

    compileOnly(intellijCore())
}

sourceSets {
    "main" { projectDefault() }
}
