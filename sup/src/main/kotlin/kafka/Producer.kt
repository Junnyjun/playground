package io.junnyland.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties

class Producer(
	private val topic: String,
	private val bootstrap: String = "localhost:9092",
){
	private val producer: KafkaProducer<String, String> = configure()

	fun configure(): KafkaProducer<String, String> = Properties().also {
			it[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrap
			it[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
			it[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
			it[ProducerConfig.ACKS_CONFIG] = "1"
			it[ProducerConfig.RETRIES_CONFIG] = "3"
			it[ProducerConfig.TRANSACTIONAL_ID_CONFIG] = "TRX_TEST"
		}.let {
			KafkaProducer(it)
		}

	fun send(message: String) {
		val record = ProducerRecord<String, String>(topic, message)
		producer.send(record)
		producer.flush()
		producer.close()
	}


}

fun main(){
	Producer(
		"test",
	).send("test")
}