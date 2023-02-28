import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.ZoneId

plugins {
    java
    id("org.springframework.boot") version "2.7.2"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Spring boot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("io.netty:netty-all")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.httpcomponents:httpclient")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
    
    // Database
    runtimeOnly("com.mysql:mysql-connector-j:8.0.31")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.0.6")
    runtimeOnly("org.xerial:sqlite-jdbc:3.39.2.0")
    implementation("com.github.gwenn:sqlite-dialect:0.1.2")
    
    // Lombok
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    
    // Others
    implementation("commons-fileupload:commons-fileupload:1.4")
}

group = "icu.samnya"
version = "0.0.43-RELEASE"
description = "Aqua Server"
java.sourceCompatibility = JavaVersion.VERSION_17

val buildTime: String by extra(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.of("UTC")).format(Instant.now()))

tasks.processResources {
    filesMatching("**/application.properties") {
        expand(project.properties)
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
