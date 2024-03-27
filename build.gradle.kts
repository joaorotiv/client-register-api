import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
}

group = "com.client-api"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.3")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.projectlombok:lombok:1.18.30")
	implementation("javax.transaction:javax.transaction-api:1.2")
	implementation("io.github.microutils:kotlin-logging:4.0.0-beta-2")
	implementation("javax.inject:javax.inject:1")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	implementation("org.springframework.boot:spring-boot-test-autoconfigure:3.2.3")
	implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
	implementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.postgresql:postgresql:42.7.3")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly ("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-test-autoconfigure:3.2.3")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
	testImplementation("com.h2database:h2:2.2.220")
	testImplementation("io.rest-assured:kotlin-extensions:5.4.0")
	testImplementation("com.github.database-rider:rider-junit5:1.41.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

