plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("maven-publish")
}

group = "me.binarywriter"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("com.squareup.okhttp3", "okhttp", "4.9.3")
    api("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.3.2")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.binarywriter"
            artifactId = "stalcraft-api-kt"
            version = "0.1"

            from(components["java"])
        }
    }
}