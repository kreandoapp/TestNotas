package com.example.testnotas

import java.util.UUID

data class Note(
    var description: String? = null,
    val id: UUID = UUID.randomUUID(),
    var title: String = "New Note"
)
