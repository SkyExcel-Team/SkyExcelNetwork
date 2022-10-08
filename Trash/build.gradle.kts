import java.util.Properties

plugins {
    java
}

group =  "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.purpurmc.org/snapshots") } //퍼퍼 버킷
    maven { url = uri("https://jitpack.io") }
}

dependencies {

    val dest = Properties().apply { load(rootProject.file("env.properties").reader())}.getProperty("coreversion")
        ?: throw NullPointerException("jarDirectory not settled inside .gradle/gradle.properties")

    compileOnly("org.purpurmc.purpur:purpur-api:1.19.2-R0.1-SNAPSHOT") //퍼퍼 버킷

    implementation("com.github.SkyExcel-Team:SkyExcelCore:$dest")
}

tasks.withType<Jar> {
    val dest = Properties().apply { load(rootProject.file("env.properties").reader())}.getProperty("jarDirectory")
        ?: throw NullPointerException("jarDirectory not settled inside .gradle/gradle.properties")
    destinationDirectory.set(file(dest))
}
