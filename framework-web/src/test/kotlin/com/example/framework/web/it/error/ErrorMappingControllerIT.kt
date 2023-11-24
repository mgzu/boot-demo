package com.example.framework.web.it.error

import com.example.framework.testsupport.BaseCase
import com.example.framework.web.entity.Result
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

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
		]
	)
	@ParameterizedTest
	fun `test service exception mapping`(url: String, statusCode: Int) {
		val httpStatus = HttpStatus.valueOf(statusCode)
		val responseEntity = template.getForEntity(url, String::class.java)

		assertThat(responseEntity.statusCode.value()).isEqualTo(HttpStatus.OK.value())
		assertThat(responseEntity.body).isEqualTo(
			objectMapper.writeValueAsString(
				Result.builder<Any>()
					.code(httpStatus.value())
					.message("failed")
					.build()
			)
		)
	}

	@CsvSource(
		value = [
			"/failed3, 405",
			"/favicon.ico, 404",
		]
	)
	@ParameterizedTest
	fun `test error mapping for json`(url: String, statusCode: Int) {
		val httpStatus = HttpStatus.valueOf(statusCode)
		val responseEntity = template.getForEntity(url, String::class.java)

		assertThat(responseEntity.statusCode.value()).isEqualTo(HttpStatus.OK.value())
		assertThat(responseEntity.body).isEqualTo(
			objectMapper.writeValueAsString(
				Result.builder<Any>()
					.code(httpStatus.value())
					.message(httpStatus.reasonPhrase)
					.build()
			)
		)
	}

	@CsvSource(
		value = [
			"/favicon.ico, 404",
		]
	)
	@ParameterizedTest
	fun `test error mapping for other resource`(url: String, statusCode: Int) {
		val headers: MultiValueMap<String, String> = LinkedMultiValueMap()
		headers.add(HttpHeaders.ACCEPT, "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
		val responseEntity = template.exchange(url, HttpMethod.GET, HttpEntity<Any>(headers), String::class.java)

		val httpStatus = HttpStatus.valueOf(statusCode)
		assertThat(responseEntity.statusCode.value()).isEqualTo(HttpStatus.OK.value())
		assertThat(responseEntity.body).isEqualTo(
			objectMapper.writeValueAsString(
				Result.builder<Any>()
					.code(httpStatus.value())
					.message(httpStatus.reasonPhrase)
					.build()
			)
		)
	}

}
