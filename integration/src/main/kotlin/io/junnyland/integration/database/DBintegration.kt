package io.junnyland.integration.database

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter
import javax.sql.DataSource
import org.springframework.integration.jdbc.dsl.Jdb
import org.springframework.integration.jpa.core.JpaExecutor

@Configuration
@EnableIntegration
class DBintegration(
	private val dataSource: DataSource
) {


	@Bean
	fun dbIntegrationFlow(): IntegrationFlow {
		return IntegrationFlow.from(
			JpaExecutor(dataSource,)
				"SELECT * FROM test_table")
		)

	}
}