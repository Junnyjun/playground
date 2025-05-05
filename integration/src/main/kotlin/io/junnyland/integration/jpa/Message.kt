package io.junnyland.integration.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "messages")
data class Message(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	val content: String,

	val processed: Boolean = false,

	@Column(name = "created_at")
	val createdAt: LocalDateTime = LocalDateTime.now()
)
