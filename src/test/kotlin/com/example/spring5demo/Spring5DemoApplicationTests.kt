package com.example.spring5demo

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
@ContextConfiguration(initializers = [BeansConfiguration::class])
class Spring5DemoApplicationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `index page returns html`() {
        val responseBody = webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .returnResult()
                .responseBody

        assertThat(responseBody).contains("Welcome to Spring 5")
    }

    @Test
    fun `api page returns a valid json response`() {
        webTestClient.get().uri("/api/people/1")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name", equalTo("Alexey"))
    }
}
