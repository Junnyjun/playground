package com.example.playground.http.jdbcClient

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.simple.JdbcClient
import javax.sql.DataSource

@Configuration
class DatasourceConfiguration {

    @Bean
    fun datasource(): DataSource = DataSourceBuilder.create()
        .type(HikariDataSource::class.java)
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:testdb")
        .username("sa")
        .password("")
        .build()

    @Bean
    fun jdbcClient():JdbcClient = JdbcClient.create(datasource())


}