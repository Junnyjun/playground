package git.io.cloud.stream

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
class SampleBinder {

    @Bean
    fun bindToUpperCase():Function<String,String>
}