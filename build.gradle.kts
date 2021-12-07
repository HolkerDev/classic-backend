import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    kotlin("plugin.jpa") version "1.6.0"
    id("com.bmuschko.docker-remote-api") version "6.7.0"
    id("docker-compose")
    id("com.google.cloud.tools.jib") version "3.0.0"
}

group = "eu.holker"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val kotlinVersion = "1.6.0"
val kluentVersion = "1.68"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val dependencyServices = setOf("db")

dockerCompose {
    projectName = "classic"
    stopContainers = false

    dependencyServices.forEach { dependency ->
        nested(dependency).apply {
            startedServices.addAll(listOf(dependency))
        }
    }
}

jib {
    to {
        image = "classic-music-backend"
    }
    from.image = "gcr.io/distroless/java-debian10:11"
}

tasks {
    named("composeUp") {
        dependsOn("build")
        dependsOn("jibDockerBuild")
    }
}
