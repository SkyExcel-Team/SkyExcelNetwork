import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.util.Properties

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.skyexcel.server"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }

    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
    maven { url = uri("https://repo.dmulloy2.net/repository/public/") }
    maven { url = uri("https://eldonexus.de/repository/maven-releases/") }
}

dependencies {
    val version = Properties().apply { load(rootProject.file("env.properties").reader()) }.getProperty("coreversion")
        ?: throw NullPointerException("jarDirectory not settled inside .gradle/gradle.properties")

    /* 코어 API */
    compileOnly("com.github.SkyExcel-Team", "SkyExcelCore", version)

    /* 버킷 API */
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")

    /* 마크 API */
    compileOnly(fileTree("api"))
    compileOnly("com.arcaniax:HeadDatabase-API:1.3.1")
    compileOnly("me.clip:placeholderapi:2.11.2")
    compileOnly("com.github.yannicklamprecht:worldborderapi:1.180.0")
    compileOnly("com.comphenix.protocol", "ProtocolLib", "4.8.0")

    /* 기타 API */
    compileOnly("org.jetbrains:annotations:23.0.0")
}

tasks.withType<Jar> {
    val dest = Properties().apply { load(rootProject.file("env.properties").reader()) }.getProperty("jarDirectory")
        ?: throw NullPointerException("jarDirectory not settled inside .gradle/gradle.properties")

    destinationDirectory.set(file(dest))
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "net.skyexcel.server.SkyExcelNetworkMain"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
