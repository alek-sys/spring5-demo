package com.example.spring5demo

import org.springframework.stereotype.Component

@Component
class PeopleRepository {
    fun getPerson(id: Int): Person = when (id) {
        1    -> Person("Alexey")
        else -> Person("Someone else")
    }
}