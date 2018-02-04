package com.example.spring5demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class Spring5DemoApplication {

    @Bean
    fun routeTable(peopleRepository: PeopleRepository) = router {
        GET("/") { ServerResponse.ok().render("index") }

        path("/api").nest {
            GET("/people/{id}") { req ->
                val id = req.pathVariable("id").toInt()
                val person = peopleRepository.getPerson(id)
                ServerResponse.ok().body(fromObject(person))
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Spring5DemoApplication>(*args)
}