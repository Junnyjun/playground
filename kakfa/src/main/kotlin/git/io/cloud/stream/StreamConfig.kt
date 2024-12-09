package git.io.cloud.stream

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class StreamConfig {
    @Bean
    fun nameSink(): Consumer<String> = Consumer { println(it) }
}