package git.io.kakfa.global

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.GenericMessageListenerContainer
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.util.regex.Pattern


@EnableKafka
@Configuration
class KafkaConfiguration {

    @Bean
    fun kafkaTemplate():KafkaTemplate<String, Any> = KafkaTemplate(kafkaProperties())
//    @Bean
//    fun routingKafkaTemplate() = RoutingKafkaTemplate(pattern() to kafkaProperties())
    @Bean
    fun replyingKafkaTemplate(): ReplyingKafkaTemplate<String, Any, String> = ReplyingKafkaTemplate(kafkaProperties(),kafkaMessageListenerContainer()
        )

    fun pattern(): Pattern = Pattern.compile("test")

    fun  kafkaMessageListenerContainer(): KafkaMessageListenerContainer<String, String> = KafkaMessageListenerContainer(kafkaConsumerFactory(),
            ContainerProperties("test",)
        )

    @Bean
    fun kafkaProperties(): DefaultKafkaProducerFactory<String, Any> = DefaultKafkaProducerFactory<String,Any>(mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
        ProducerConfig.ACKS_CONFIG to "all",
        ConsumerConfig.GROUP_ID_CONFIG to "kafka-group"
        ))

    @Bean
    fun kafkaConsumerFactory(): ConsumerFactory<String, String> = DefaultKafkaConsumerFactory(
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            ConsumerConfig.GROUP_ID_CONFIG to "kafka-group"
        )
    )
}