package io.junnyland.kafka

import com.sun.jdi.Bootstrap
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties

class Producer(
	private val topic: String,
	private val bootstrap: String = "localhost:9092"
){

	fun congifure(): KafkaProducer<String, String> = Properties().also {
			it[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrap
			it[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
			it[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
		}.let {
			KafkaProducer(it)
		}

	fun send(message: String) {

	}
}