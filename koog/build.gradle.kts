plugins {
	kotlin("jvm") version "2.1.20"
	kotlin("plugin.spring") version "2.1.20"  // 스프링 관련 플러그인 추가
	id("org.springframework.boot") version "3.2.3"  // 스프링 부트 버전
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.koog.agent"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation("ai.koog:koog-agents:LATEST_VERSION")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}
kotlin {
	jvmToolchain(21)
}