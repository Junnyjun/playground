package com.koog.agent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KoogAgentApplication

fun main(args: Array<String>) {
	runApplication<KoogAgentApplication>(*args)
}