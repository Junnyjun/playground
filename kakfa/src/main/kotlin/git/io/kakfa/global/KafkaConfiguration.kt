package git.io.kakfa.global

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer


@EnableKafka
@Configuration
class KafkaConfiguration {

    @Bean
    fun kafkaTemplate():KafkaTemplate<String, Any> = KafkaTemplate(kafkaProperties())

    @Bean
    fun kafkaProperties() = DefaultKafkaProducerFactory<String,Any>(mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
        ProducerConfig.ACKS_CONFIG to "all"
        ))

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> = DefaultKafkaConsumerFactory(
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            ConsumerConfig.GROUP_ID_CONFIG to "kafka-group"
        )
    )
}