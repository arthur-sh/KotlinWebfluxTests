package com.example.arthur.testesReactive.repository

import com.example.arthur.testesReactive.domain.Task
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface TaskRepository: ReactiveMongoRepository<Task, String> {
    fun findByName(name: String): Mono<Task>
    fun countByName(name: String): Mono<Long>
    fun deleteByName(name: String): Mono<Long>
}