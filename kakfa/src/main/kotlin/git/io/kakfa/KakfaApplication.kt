package git.io.kakfa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class KakfaApplication

fun main(args: Array<String>) {
	runApplication<KakfaApplication>(*args)
}
