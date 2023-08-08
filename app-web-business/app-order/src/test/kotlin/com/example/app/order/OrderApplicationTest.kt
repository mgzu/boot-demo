package com.example.app.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

/**
 * @author MaGuangZu
 * @since 2022-10-25
 */
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApplicationTest {
	@Autowired
	lateinit var mvc: MockMvc

	@Test
	fun exampleTest() {
		mvc
			.get("/").andExpectAll {
				status { isOk() }
			}
	}

	@Test
	fun orderTest() {
		mvc
			.get("/order").andExpectAll {
				status { isOk() }
			}
	}

	@Test
	fun `test actuator health`() {
		val result = mvc
			.get("/actuator/health").andExpectAll {
				status { isOk() }
			}.andReturn()

		assertThat(result.response.contentAsString).isEqualTo("{\"status\":\"UP\"}")
	}

}
