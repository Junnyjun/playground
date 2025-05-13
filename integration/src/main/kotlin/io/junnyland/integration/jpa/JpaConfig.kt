package io.junnyland.integration.jpa

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.integration.config.EnableIntegration
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableIntegration
class JpaConfig(
	private val dataSource: DataSource,
	private val messageRepository: MessageRepository,
	private val entityManagerFactory: EntityManagerFactory,
	private val transactionManager: PlatformTransactionManager

) {



}