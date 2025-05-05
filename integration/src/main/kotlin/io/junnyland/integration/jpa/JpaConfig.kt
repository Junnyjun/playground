package io.junnyland.integration.jpa

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.jpa.core.JpaExecutor
import org.springframework.integration.jpa.dsl.Jpa
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

	@Bean
	fun jpaExecutor(): JpaExecutor {
		val jpaExecutor = JpaExecutor()
		jpaExecutor.setEntityClass(Message::class.java)
		jpaExecutor.setJpaQuery("from Message where processed = false")
		jpaExecutor.setDeleteAfterPoll(false)
		jpaExecutor.setDeleteInBatch(false)
		return jpaExecutor
	}

	@Bean
	fun dbIntegrationFlow(): IntegrationFlow {
		return IntegrationFlow.from(
			Jpa.inboundAdapter(jpaExecutor())
				.entityManager("entityManagerFactory"),
			c -> c.poller(Pollers.fixedDelay(Duration.ofSeconds(10))
		.transactional(transactionManager))
		)
		.filter { payload -> payload != null && (payload as List<*>).isNotEmpty() }
			.handle { payload, _ ->
				val messages = payload as List<Message>
				println("처리할 메시지 수: ${messages.size}")

				// 메시지 ID 추출
				val messageIds = messages.mapNotNull { it.id }

				// 비즈니스 로직 처리
				messages.forEach { message ->
					println("메시지 처리: ${message.content}")
				}

				// 처리된 메시지를 업데이트
				if (messageIds.isNotEmpty()) {
					messageRepository.markAsProcessed(messageIds)
				}

				MessageBuilder.withPayload(messages).build()
			}
			.get()
	}

	// 샘플 데이터 초기화용 빈
	@Bean
	fun initData(messageRepository: MessageRepository): Boolean {
		// 애플리케이션 시작 시 샘플 데이터 생성
		val messages = listOf(
			Message(content = "테스트 메시지 1"),
			Message(content = "테스트 메시지 2"),
			Message(content = "테스트 메시지 3")
		)

		messageRepository.saveAll(messages)
		println("샘플 데이터 초기화 완료")
		return true
	}

}