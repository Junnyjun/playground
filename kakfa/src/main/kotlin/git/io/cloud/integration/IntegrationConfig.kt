package git.io.cloud.integration

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.expression.common.LiteralExpression
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.messaging.MessageHandler


@Configuration
class IntegrationConfig {
    // Input Channel for receiving messages
    @Bean
    @ServiceActivator(inputChannel = "toKafka")
    @Throws(Exception::class)
    fun handler(): MessageHandler? {
        val handler =
            KafkaProducerMessageHandler(kafkaTemplate())
        handler.setTopicExpression(LiteralExpression("someTopic"))
        handler.setMessageKeyExpression(LiteralExpression("someKey"))
        return handler
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            ConsumerConfig.GROUP_ID_CONFIG to "kafka-group",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "true",
            ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG to "1000"
        ))
    }
}