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
    fun jdbcClient(datasource:DataSource):JdbcClient = JdbcClient.create(datasource)


}