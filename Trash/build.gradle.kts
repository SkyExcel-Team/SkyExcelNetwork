plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.purpurmc.org/snapshots") } //퍼퍼 버킷
}

dependencies {
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.2-R0.1-SNAPSHOT") //퍼퍼 버킷
}

