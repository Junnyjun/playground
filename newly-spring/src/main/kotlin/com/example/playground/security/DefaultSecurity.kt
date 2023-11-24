package com.example.playground.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class DefaultSecurity {
    @Bean
    fun configure(httpSecurity: HttpSecurity): SecurityFilterChain = httpSecurity
            .csrf{ it.disable() }
            .cors{ it.disable() }
            .authorizeHttpRequests { it.anyRequest().permitAll() }
            .build()

}