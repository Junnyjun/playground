package io.junnyland.integration.database

import io.junnyland.integration.jpa.Message
import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.jpa.core.JpaExecutor
import org.springframework.integration.jpa.dsl.Jpa

@Configuration
@EnableIntegration
class DBintegration(
	private val entitiManagerFactory: EntityManagerFactory
) {
	@Bean
	fun dbIntegrationFlow(): IntegrationFlow {
		return IntegrationFlow.from(
			Jpa.inboundAdapter(entitiManagerFactory)
		) {
		}.get()
	}

	private fun processMessage(message: Any) {
		// 여기서 실제 메시지 처리 로직을 구현합니다.
		println("Processing message: $message")
	}

	@Bean
	fun jpaExecutor(entityManagerFactory: EntityManagerFactory): JpaExecutor {
		val jpaExecutor = JpaExecutor(entityManagerFactory)
		jpaExecutor.setEntityClass(Message::class.java)
		jpaExecutor.setJpaQuery("from Message where processed = false")
		jpaExecutor.setDeleteAfterPoll(false)
		return jpaExecutor
	}
}
