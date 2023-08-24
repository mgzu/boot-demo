package com.example.framework.common.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
	})
	@ParameterizedTest
	void testUnIgnore(String str, boolean expected) {
		assertThat(NumberUtil.isInteger(str, false)).isEqualTo(expected);
	}

}
