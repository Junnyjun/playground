plugins {
	id("org.springframework.boot") version "3.2.4" // Spring Boot 관리용
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "2.2.20"
	kotlin("plugin.spring") version "2.2.20"
}

group = "io.junnyland"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web") // REST 엔드포인트 테스트용
	implementation("org.springframework.kafka:spring-kafka") // Spring Kafka (버전은 BOM으로 관리)
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // JSON 직렬화/역직렬화
	implementation(kotlin("reflect"))

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test") // Embedded Kafka 테스트 지원
	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}