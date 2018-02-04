package com.example.spring5demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router

class BeansConfiguration: BeanDefinitionDsl() {
    init {
        bean {
            val peopleRepository = ref<PeopleRepository>()
            router {
                GET("/") { ServerResponse.ok().render("index") }

                path("/api").nest {
                    GET("/people/{id}") { req ->
                        val id = req.pathVariable("id").toInt()
                        val person = peopleRepository.getPerson(id)
                        ServerResponse.ok().body(person)
                    }
                }
            }
        }
    }
}

@SpringBootApplication
class Spring5DemoApplication

fun main(args: Array<String>) {
    runApplication<Spring5DemoApplication>(*args) {
        addInitializers(BeansConfiguration())
    }
}