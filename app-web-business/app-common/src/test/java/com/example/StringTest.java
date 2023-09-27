package com.example;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.UnicodeUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author MaGuangZu
 * @since 2021-12-22
 */
@Slf4j
class StringTest {

	@Test
	void trimWhiteSpaceTest() {
		assertThat(Character.isWhitespace(' ')).isTrue();
		assertThat(Character.isWhitespace(' ')).isFalse();
	}

}
