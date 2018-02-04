package com.example.spring5demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Spring5DemoApplication

fun main(args: Array<String>) {
    runApplication<Spring5DemoApplication>(*args)
}