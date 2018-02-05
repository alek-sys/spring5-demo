package com.example.spring5demo

import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.ipc.netty.http.server.HttpServer

fun main(args: Array<String>) {
    val peopleRepository = PeopleRepository()

    val routes = router {
        GET("/people/{id}") { req ->
            val person = peopleRepository.getPerson(req.pathVariable("id").toInt())
            ServerResponse.ok().body(person)
        }
    }

    val handler = RouterFunctions.toHttpHandler(routes)

    HttpServer.create().startAndAwait(ReactorHttpHandlerAdapter(handler))
}