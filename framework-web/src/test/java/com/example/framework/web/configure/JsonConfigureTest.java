package com.example.framework.web.configure;

import com.example.framework.common.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JsonConfigureTest {
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void testLocalDateAndLocalDateTimeSerialization() {
		var product = new Product();
		product.setLocalDateTime1(LocalDateTime.now());
		product.setLocalDate1(LocalDate.now());
		Assertions.assertThatNoException().isThrownBy(() -> {
			objectMapper.writeValueAsString(product);
		});
	}

	@Test
	void testLocalDateAndLocalDateTimeSerialization2() {
		var product = new Product();
		product.setLocalDateTime1(LocalDateTime.now());
		product.setLocalDate1(LocalDate.now());
		Assertions.assertThatNoException().isThrownBy(() -> {
			var toJson = JsonUtil.toJson(product);
			System.out.println(toJson);
		});
	}
}
