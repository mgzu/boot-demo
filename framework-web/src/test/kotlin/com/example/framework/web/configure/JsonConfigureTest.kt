package com.example.framework.web.configure

import com.example.framework.common.util.JsonUtil
import com.example.framework.web.entity.Product
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JsonConfigureTest {

	@Autowired
	lateinit var objectMapper: ObjectMapper

	@Test
	fun `test localDate and localDateTime serialization`() {
		val product = Product()
		product.localDateTime1 = LocalDateTime.now()
		product.localDate1 = LocalDate.now()
		assertDoesNotThrow {
			objectMapper.writeValueAsString(product)
		}
	}

	@Test
	fun `test localDate and localDateTime serialization2`() {
		val product = Product()
		product.localDateTime1 = LocalDateTime.now()
		product.localDate1 = LocalDate.now()
		assertDoesNotThrow {
			val toJson = JsonUtil.toJson(product)
			println(toJson)
		}
	}
}
