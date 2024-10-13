package git.io.kakfa.global

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.*
import org.springframework.kafka.retrytopic.RetryTopicConfiguration
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder
import org.springframework.kafka.support.serializer.JsonDeserializer


@EnableKafka
@Configuration
class KafkaProducerConfiguration {

    @Bean
    fun kafkaTemplate():KafkaTemplate<String, Any> = KafkaTemplate(kafkaProperties())
        .also { it.setObservationEnabled(true)
            it.transactionIdPrefix = "kafka-transaction"

        }
//    @Bean
//    fun routingKafkaTemplate() = RoutingKafkaTemplate(pattern() to kafkaProperties())

    @Bean
    fun kafkaProperties(): DefaultKafkaProducerFactory<String, Any> = DefaultKafkaProducerFactory<String,Any>(mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
        ProducerConfig.ACKS_CONFIG to "all",
        ConsumerConfig.GROUP_ID_CONFIG to "kafka-group"
        ))

    @Bean
    fun producerFactory(): RetryTopicConfiguration = RetryTopicConfigurationBuilder.newInstance().exponentialBackoff(1000,2.0,500).maxAttempts(3)
        .retryOn(Exception::class.java)
        .create(kafkaTemplate())
}