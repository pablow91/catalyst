import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    var kotlinVersion: String by extra
    var springBootVersion: String by extra
    var spockVersion: String by extra
    var jtidyVersion: String by extra
    var swaggerVersion: String by extra
    kotlinVersion = "1.2.41"
    springBootVersion = "2.0.2.RELEASE"
    spockVersion = "1.1-groovy-2.4"
    jtidyVersion = "r938"
    swaggerVersion = "2.7.0"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath(kotlin("allopen", kotlinVersion))
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

plugins {
    java
}

group = "pl.info.qwerty"
version = "0.0.1-SNAPSHOT"

apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("eclipse")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
}

val kotlinVersion: String by extra
val springBootVersion: String by extra
val spockVersion: String by extra
val jtidyVersion: String by extra
val swaggerVersion: String by extra


repositories {
    mavenCentral()
}

dependencies {
    // Web
    compile("org.springframework.boot:spring-boot-starter-web")

    //API Module
    compile(project(":api"))

    // Kotlin
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")

    //MongoDB
//    compile("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

    //Scheduling
    compile("org.springframework.boot:spring-boot-starter-quartz")

    //Tools
    compile("net.sf.jtidy:jtidy:$jtidyVersion")

    compile("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

    // Swagger
    compile("io.springfox:springfox-swagger2:$swaggerVersion")
    compile("io.springfox:springfox-swagger-ui:$swaggerVersion")

    // Test
    testCompile("org.springframework.boot:spring-boot-starter-test")
    testCompile("io.projectreactor:reactor-test")
    testCompile("org.spockframework:spock-core:$spockVersion")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}