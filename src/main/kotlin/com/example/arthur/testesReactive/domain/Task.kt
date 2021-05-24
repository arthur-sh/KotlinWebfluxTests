package com.example.arthur.testesReactive.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Task (
    @Id
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    var done: Boolean = false
)