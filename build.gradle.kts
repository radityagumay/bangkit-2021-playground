import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.0"
    application
}

group = "com.raditya"
version = "1.0-SNAPSHOT"

repositories {
    maven { setUrl("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
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