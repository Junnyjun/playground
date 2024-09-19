package git.io.kakfa.global

import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration
import org.springframework.kafka.config.KafkaStreamsConfiguration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

@EnableKafka
@Configuration
class KafkaConfiguration {

    @Bean
    fun kafkaTemplate():KafkaTemplate<String, Any> = KafkaTemplate(kafkaProperties())



    @Bean
    fun kafkaProperties() = DefaultKafkaProducerFactory<String,Any>(mapOf(
            "bootstrap.servers" to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
            "listener" to "PLAINTEXT",
            "key.serializer" to "org.apache.kafka.common.serialization.StringSerializer",
            "value.serializer" to "org.apache.kafka.common.serialization.JsonSerializer",
            "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
            "value.deserializer" to "org.apache.kafka.common.serialization.JsonSerializer"
        ))


}