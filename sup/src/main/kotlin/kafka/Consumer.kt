package io.junnyland.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration.ofMillis
import java.util.Properties

class Consumer(
	private val topic: String,
	private val bootstrap: String = "localhost:9092",
) {
	private val consumer: KafkaConsumer<String, String> = configure()

	fun configure(): KafkaConsumer<String, String> = Properties().also {
		it[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrap
		it[ConsumerConfig.GROUP_ID_CONFIG] = "TEST_GROUP"
		it[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
		it[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
	}.let {
		KafkaConsumer(it)
	}

	fun receive() {
		consumer.subscribe(listOf(topic))
		consumer.poll(ofMillis(1000)).forEach { record ->
			println("Received: ${record.value()}")
		}
		consumer.close()
	}
}

fun main() {
	Consumer(
		"test",
	).receive(
	)
}