package com.example.framework.system.entity;

import com.example.framework.system.constants.DictConstants;
import com.example.framework.system.util.DictUtil;
import com.example.framework.testsupport.BaseCase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2024-05-15
 */
class DictItemTest extends BaseCase {

	@SneakyThrows
	@Test
	void test() {
		List<DictItem> dicts = new ArrayList<>();
		dicts.add(of("string", "123"));
		dicts.add(of("int", "123"));
		dicts.add(of("bool", "true"));
		dicts.add(of("decimal", "1.333333333333333333333333"));
		var toList = DictUtil.convert(dicts);
		System.out.println(objectMapper.writeValueAsString(toList));
	}

	DictItem of(String type, String value) {
		var dictItem = new DictItem();
		dictItem.setType(type);
		dictItem.setValue(value);
		dictItem.setCreatedBy("createdBy");
		dictItem.setCreatedDate(LocalDateTime.now());
		dictItem.setLastModifiedBy("lastModifiedBy");
		dictItem.setLastModifiedDate(LocalDateTime.now());
		dictItem.setVersionLock(0);
		return dictItem;
	}

	private final Set<String> invalidMessage = Set.of(
		"must be any of constant [string, int, decimal, bool]",
		"不能为null",
		"不能为空"
	);

	@ValueSource(
		strings = {
			DictConstants.DICT_TYPE_STRING,
			DictConstants.DICT_TYPE_INT,
			DictConstants.DICT_TYPE_DECIMAL,
			DictConstants.DICT_TYPE_BOOL,
		}
	)
	@ParameterizedTest
	void testValid(String type) {
		var dict = of(type, "unknown");
		var result = validate(dict);
		assertThat(result).isEmpty();
	}

	@ValueSource(strings = {"unknown"})
	@ParameterizedTest
	void testInvalid(String type) {
		var dict = of(type, "unknown");
		var result = validate(dict);
		assertThat(result).hasSize(1);
		validateResult(invalidMessage, result);
	}

	@NullAndEmptySource
	@ParameterizedTest
	void testInvalidByNullOrEmptyString(String type) {
		var dict = of(type, "unknown");
		var result = validate(dict);
		assertThat(result).hasSizeBetween(1, 2);
		printResult(result);
		validateResult(invalidMessage, result);
	}
}
