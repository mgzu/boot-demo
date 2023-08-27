package com.example.framework.common.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-08-24
 */
class NumberUtilTest {

	@CsvSource({
		"123, true",
		"456.00, true",
		"-1.000000, true",
		"-456.00, true",
		"-1.0000000000000000000000000000001, false",
	})
	@ParameterizedTest
	void testIgnore(String str, boolean expected) {
		assertThat(NumberUtil.isInteger(str, true)).isEqualTo(expected);
	}

	@CsvSource({
		"123, true",
		"-1, true",
		"-1.00, false",
		"456, true",
		"-1.0000000000000000000000000000001, false",
	})
	@ParameterizedTest
	void testUnIgnore(String str, boolean expected) {
		assertThat(NumberUtil.isInteger(str, false)).isEqualTo(expected);
	}

	@NullAndEmptySource
	@CsvSource({
		"456d",
		"456f",
		"-1.0000000000.000000000000000000001",
	})
	@ParameterizedTest
	void testInvalid(String str) {
		assertThat(NumberUtil.isInteger(str, false)).isFalse();
	}

}
