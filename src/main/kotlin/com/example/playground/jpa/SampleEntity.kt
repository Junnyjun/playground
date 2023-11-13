package com.example.playground.jpa

import jakarta.persistence.*

@Entity
@Table(name = "SAMPLE_ENTITY")
class SampleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String? = null
) {
    constructor() : this(null, ""  ) {

    }
}