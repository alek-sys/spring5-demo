package com.example.spring5demo

import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class Spring5DemoApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `index page returns html`() {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Welcome to Spring 5")))
    }

    @Test
    fun `api page returns a valid json response`() {
        mockMvc.perform(get("/api/people/1/"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", equalTo("Alexey")))
    }
}
