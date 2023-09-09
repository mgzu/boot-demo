package com.example.framework.web.it.error

import com.example.framework.testsupport.BaseCase
import com.example.framework.web.entity.Result
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

/**
 * @author MaGuangZu
 * @since 2023-09-08
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ErrorMappingControllerIT : BaseCase() {
	@Autowired
	lateinit var template: TestRestTemplate

	@CsvSource(
		value = [
			"/failed, 500",
			"/failed2, 500",
			"/favicon.ico, 404"
		]
	)
	@ParameterizedTest
	fun `test error mapping`(url: String, statusCode: Int) {
		val httpStatus = HttpStatus.valueOf(statusCode)
		val responseEntity = template.getForEntity(url, String::class.java)

		assertThat(responseEntity.statusCode.value()).isEqualTo(HttpStatus.OK.value())
		assertThat(responseEntity.body).isEqualTo(
			objectMapper.writeValueAsString(
				Result.builder<Any>()
					.success(false)
					.code(httpStatus.value())
					.message(httpStatus.reasonPhrase)
					.build()
			)
		)
	}

}
