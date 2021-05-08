import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.0"
    application
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    maven { setUrl("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-js"))
    // include for server side
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")
    // include for client-side
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")

    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}