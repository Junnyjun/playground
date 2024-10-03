package git.io.kakfa

import org.apache.kafka.clients.producer.*
import org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.util.*

private val log = LoggerFactory.getLogger("KafkaClientTest")

class KafkaClientTest {
    val topic = "test"

    @Test
    fun sendTest() {
        val producer = KafkaProducer<String, String>(ProducerConfiguration().config())
        val send = producer.send(ProducerRecord(topic, "value"),ProducerCallback())

        val get = send.get()


        producer.flush()
        producer.close()
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
        val bootstrapServers = "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002"
        val groupId = "test-group"

    }
}