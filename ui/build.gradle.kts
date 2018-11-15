import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.3.10"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

plugins {
    java
}

group = "pl.info.qwerty"
version = "0.0.1-SNAPSHOT"

apply {
    plugin("kotlin")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8", kotlin_version))
    compile(kotlin("reflect", kotlin_version))
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.7")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.7")
    compile("org.controlsfx:controlsfx:9.0.0")
    compile("org.openjfx:javafx-controls:11:linux")
    compile("org.openjfx:javafx-fxml:11:linux")
    compile("org.openjfx:javafx-graphics:11:linux")
    compile("org.openjfx:javafx-base:11:linux")
    compile("com.squareup.retrofit2:retrofit:2.4.0")
    compile("com.squareup.retrofit2:converter-jackson:2.4.0")

    compile(project(":api"))

    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}