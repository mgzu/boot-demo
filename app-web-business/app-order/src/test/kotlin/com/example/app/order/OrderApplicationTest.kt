package com.example.app.order

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * @author MaGuangZu
 * @since 2022-10-25
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApplicationTest {
    @Test
    fun exampleTest(@Autowired webClient: WebTestClient) {
        webClient
            .get().uri("/")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun orderTest(@Autowired webClient: WebTestClient) {
        webClient
            .get().uri("/order")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `test actuator`(@Autowired webClient: WebTestClient) {
        webClient
            .get().uri("/actuator/health")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `test actuator health`(@Autowired webClient: WebTestClient) {
        webClient
            .get().uri("/actuator/health")
            .exchange()
            .expectStatus().isOk
            .expectBody().json("{\"status\":\"UP\"}")
    }
}
