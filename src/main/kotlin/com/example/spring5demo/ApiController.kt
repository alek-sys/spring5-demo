package com.example.spring5demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class ApiController(private val peopleRepository: PeopleRepository) {

    @GetMapping("/people/{id}")
    fun getPerson(@PathVariable id: Int): Mono<Person> = peopleRepository.getPerson(id)
}