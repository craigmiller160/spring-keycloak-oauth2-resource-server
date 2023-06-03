import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectGroup: String by project
val projectVersion: String by project

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("io.craigmiller160.gradle.defaults") version "1.1.0"
    id("com.diffplug.spotless") version "6.17.0"
    kotlin("jvm")
    kotlin("plugin.spring")
}

group = projectGroup
version = projectVersion
java.sourceCompatibility = JavaVersion.VERSION_19

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    compileOnly("org.springframework:spring-webflux") // TODO replace with reactor
//    compileOnly("org.springframework:spring-webmvc")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "19"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configure<SpotlessExtension> {
    kotlin {
        ktfmt()
    }
}
