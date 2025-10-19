plugins {
	kotlin("jvm") version "2.2.20"
}

group = "io.junnyland"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))
	// kafka client
	implementation("org.apache.kafka:kafka-clients:3.5.2")
}

tasks.test {
	useJUnitPlatform()
}
kotlin {
	jvmToolchain(21)
}