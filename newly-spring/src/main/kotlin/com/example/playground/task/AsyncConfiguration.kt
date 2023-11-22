package com.example.playground.task

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class AsyncConfiguration {
    @Bean
    fun asyncTaskExecutor()= ThreadPoolTaskExecutor().apply {
        corePoolSize = 2
        maxPoolSize = 2
        queueCapacity = 500
        setThreadNamePrefix("Async-")
        initialize()
    }
}