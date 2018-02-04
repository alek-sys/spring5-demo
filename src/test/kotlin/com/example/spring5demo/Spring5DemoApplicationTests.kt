package com.example.spring5demo

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureWebTestClient
@ContextConfiguration(initializers = [BeansConfiguration::class])
class Spring5DemoApplicationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    @DisplayName("😱")
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
