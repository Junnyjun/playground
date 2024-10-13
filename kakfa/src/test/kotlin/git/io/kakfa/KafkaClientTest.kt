package git.io.kakfa

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.*
import org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.common.message.ConsumerProtocolAssignment.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.time.Duration
import java.time.temporal.TemporalUnit
import java.util.*
import java.util.concurrent.atomic.AtomicLong

private val log = LoggerFactory.getLogger("KafkaClientTest")

class KafkaClientTest {
    val topic = "test"

    @Test
    fun sendTest() {
        val producer = KafkaProducer<String, String>(ProducerConfiguration().config())
        producer.initTransactions()

        val send = producer.send(ProducerRecord(topic, "value"),ProducerCallback())

        val get = send.get()


        producer.flush()

        producer.close()
    }

    @Test
    fun receiveTest() {
        val consumer = KafkaConsumer<String, String>(ClientConfiguration().config())
        consumer.subscribe(listOf(topic),CustomConsumerReBalanceListener(consumer))

        while (true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            records.forEach {
                log.info("Received message: ${it}")
                consumer.commitSync()
            }
        }
    }


    class ProducerConfiguration {
        fun config() = Properties().apply {
            put(BOOTSTRAP_SERVERS_CONFIG, "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002")
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        }
    }
    class ProducerCallback : Callback{
        override fun onCompletion(p0: RecordMetadata?, p1: Exception?) {
            p1
                ?.let { log.error("Error => ${p1.message}") }
                ?: log.info("Success => ${p0.toString()}")
        }
    }

    class ClientConfiguration {
        fun config() = Properties().apply {
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002")
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
            put(ConsumerConfig.GROUP_ID_CONFIG, "test-group")
            put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
        }
    }

    open class CustomConsumerReBalanceListener(
        private val consumer: KafkaConsumer<String, String>
    ): ConsumerRebalanceListener {
        override fun onPartitionsRevoked(p0: MutableCollection<org.apache.kafka.common.TopicPartition>?) {
            log.info("Partitions revoked => ${p0.toString()}")
        }

        override fun onPartitionsAssigned(p0: MutableCollection<org.apache.kafka.common.TopicPartition>?) {
            log.info("Partitions assigned => ${p0.toString()}")
            consumer.commitSync()
        }

        override fun onPartitionsLost(partitions: MutableCollection<org.apache.kafka.common.TopicPartition>?) {
            super.onPartitionsLost(partitions)
        }
    }
}