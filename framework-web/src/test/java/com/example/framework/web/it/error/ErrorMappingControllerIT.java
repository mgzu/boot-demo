package com.example.framework.web.it.error;

import com.example.framework.testsupport.BaseCase;
import com.example.framework.web.entity.Result;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ErrorMappingControllerIT extends BaseCase {

	@Autowired
	TestRestTemplate template;

	@SneakyThrows
	@CsvSource(
		value = {
			"/failed, 500",
			"/failed2, 500",
		}
	)
	@ParameterizedTest
	void testServiceExceptionMapping(String url, int statusCode) {
		var httpStatus = HttpStatus.valueOf(statusCode);
		var responseEntity = template.getForEntity(url, String.class);

		assertThat(responseEntity.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		assertThat(responseEntity.getBody()).isEqualTo(
			objectMapper.writeValueAsString(
				Result.builder()
					.code(httpStatus.value())
					.message("failed")
					.build()
			)
		);
	}

	@SneakyThrows
	@CsvSource(
		value = {
			"/failed3, 405",
			"/favicon.ico, 404",
		}
	)
	@ParameterizedTest
	void testErrorMappingForJson(String url, int statusCode) {
		var httpStatus = HttpStatus.valueOf(statusCode);
		var responseEntity = template.getForEntity(url, String.class);

		assertThat(responseEntity.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		assertThat(responseEntity.getBody()).isEqualTo(
			objectMapper.writeValueAsString(
				Result.builder()
					.code(httpStatus.value())
					.message(httpStatus.getReasonPhrase())
					.build()
			)
		);
	}

	@SneakyThrows
	@CsvSource(
		value = {
			"/favicon.ico, 404",
		}
	)
	@ParameterizedTest
	void testErrorMappingForOtherResource(String url, int statusCode) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.ACCEPT, "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
		var responseEntity = template.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

		var httpStatus = HttpStatus.valueOf(statusCode);
		assertThat(responseEntity.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		assertThat(responseEntity.getBody()).isEqualTo(
			objectMapper.writeValueAsString(
				Result.builder()
					.code(httpStatus.value())
					.message(httpStatus.getReasonPhrase())
					.build()
			)
		);
	}
}
