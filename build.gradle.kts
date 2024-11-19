import java.io.ByteArrayOutputStream

allprojects {
    ext.set("appName", gitVersionTag())
    ext.set("appCode", gitVersionCode())
}

plugins {
    alias(libs.plugins.gradle.versions) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    //    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    //    alias(libs.plugins.versions) apply false
    //    alias(libs.plugins.room) apply false
    alias(libs.plugins.compose.compiler) apply false
}

fun gitVersionCode(): Int {
    val out = ByteArrayOutputStream()
    project.exec {
        commandLine = "git rev-list HEAD --count".split(" ")
        standardOutput = out
    }
    return String(out.toByteArray()).trim().toInt()
}

fun gitVersionTag(): String {
    val out = ByteArrayOutputStream()
    project.exec {
        commandLine = "git describe --tags".split(" ")
        standardOutput = out
    }
    return String(out.toByteArray()).trim()
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
