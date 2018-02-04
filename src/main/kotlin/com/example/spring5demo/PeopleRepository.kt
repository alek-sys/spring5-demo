package com.example.spring5demo

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class PeopleRepository {
    fun getPerson(id: Int): Mono<Person> = when (id) {
        1    -> Mono.just(Person("Alexey"))
        else -> Mono.just(Person("Someone else"))
    }
}