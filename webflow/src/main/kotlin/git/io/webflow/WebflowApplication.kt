package git.io.webflow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebflowApplication

fun main(args: Array<String>) {
	runApplication<WebflowApplication>(*args)
}
