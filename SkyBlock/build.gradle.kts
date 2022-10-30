import java.util.Properties

plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven { url = uri("https://repo.purpurmc.org/snapshots") } //퍼퍼 버킷
    maven { url = uri("https://jitpack.io") }

    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
    maven { url = uri("https://repo.dmulloy2.net/repository/public/") }


}

dependencies {

    val version = Properties().apply { load(rootProject.file("env.properties").reader()) }.getProperty("coreversion")
        ?: throw NullPointerException("jarDirectory not settled inside .gradle/gradle.properties")


    compileOnly("com.github.SkyExcel-Team", "SkyExcelCore", "1.1.31")




    compileOnly("org.purpurmc.purpur:purpur-api:1.19.2-R0.1-SNAPSHOT") //퍼퍼 버킷
    compileOnly("com.arcaniax:HeadDatabase-API:1.3.1") // HEAD DATABASE API

    compileOnly(fileTree("api"))
    compileOnly("me.clip:placeholderapi:2.11.2")
    compileOnly("com.comphenix.protocol", "ProtocolLib", "4.8.0")

}

tasks.withType<Jar> {

    val dest = Properties().apply { load(rootProject.file("env.properties").reader()) }.getProperty("jarDirectory")
        ?: throw NullPointerException("jarDirectory not settled inside .gradle/gradle.properties")

    destinationDirectory.set(file(dest))
}

