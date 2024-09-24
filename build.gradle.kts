import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
}

group = "org.study"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")  // Kotest JUnit5 Runner
    testImplementation("io.kotest:kotest-assertions-core:5.7.2") // Kotest Core Assertions
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

    // mockk
    testImplementation("io.mockk:mockk:1.13.8")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}