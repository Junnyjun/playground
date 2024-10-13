package git.io.kakfa.global

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.TopicPartition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.CommonErrorHandler
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.scheduling.concurrent.CustomizableThreadFactory
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@EnableKafka
@Configuration
class KafkaConsumerConfiguration {

    @Bean
    fun kafkaConsumerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> =
        ConcurrentKafkaListenerContainerFactory<String, Any>()
            .also {
                it.setConcurrency(10)
                it.consumerFactory = consumerFactory()
                it.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
                it.containerProperties.listenerTaskExecutor = executor()
                it.setCommonErrorHandler(SeekToCurrentErrorHandlerCustom())
            }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> = DefaultKafkaConsumerFactory(consumerConfig())

    @Bean
    fun executor(): ThreadPoolTaskExecutor = ThreadPoolTaskExecutor()
        .also {
            it.corePoolSize = 10
            it.maxPoolSize = 200
            it.queueCapacity = 250
            it.setThreadFactory(CustomizableThreadFactory("kafka-thread")) // 이름 prefix
        }

    class SeekToCurrentErrorHandlerCustom() : CommonErrorHandler {
        override fun onPartitionsAssigned(
            consumer: Consumer<*, *>,
            partitions: MutableCollection<TopicPartition>,
            publishPause: Runnable
        ) {
            super.onPartitionsAssigned(consumer, partitions, publishPause)
        }
    }

    @Bean
    fun consumerConfig() = mapOf(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
        ConsumerConfig.GROUP_ID_CONFIG to "kafka-group",
        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
        ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "true",
        ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG to "1000"
    )
}
