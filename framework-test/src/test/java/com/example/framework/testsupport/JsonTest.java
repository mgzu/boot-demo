package com.example.framework.testsupport;

import com.example.framework.testsupport.entity.JsonEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JsonTest {

	ObjectMapper objectMapper = new ObjectMapper();

	@CsvSource(textBlock =
		"""
			{"ID":"123"},123
			{"id":"123"},123
			{"Id":"123"},123
			{"iD":"123"},123
			"""
	)
	@ParameterizedTest
	void test1(String json, String expected) throws JsonProcessingException {
		JsonEntity entity = objectMapper.readValue(json, JsonEntity.class);
		log.info("json: {}, json2: {}", json, objectMapper.writeValueAsString(entity));
		assertThat(entity.getId()).isEqualTo(expected);
	}

}
