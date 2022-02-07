// 마인크래프트 플러그인 Gradle
plugins {
    kotlin("jvm") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = properties["group"]!!
version = properties["version"]!!

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
}


dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.github.AtSignRANK:MAP:1.0.1")
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "16" // JDK 버전
    }

    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set("")

        doLast {
            copy {
                from(archiveFile)
                into(File(rootDir, ".server/plugins/"))
            }
        }
    }
}
