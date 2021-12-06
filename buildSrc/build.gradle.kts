repositories {
    jcenter()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.avast.gradle:gradle-docker-compose-plugin:0.14.3")
}