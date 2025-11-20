package io.junnyland

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SupApplication

fun main(args: Array<String>) : CommandLineRunner {
	runApplication<SupApplication>(*args)
}