package com.example.playground.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface SampleEntityJpaRepository : JpaRepository<SampleEntity, Long>{
}