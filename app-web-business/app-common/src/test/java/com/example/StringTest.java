package com.example;

import com.example.framework.testsupport.BaseCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author MaGuangZu
 * @since 2021-12-22
 */
@Slf4j
class StringTest extends BaseCase {

	@Test
	void trimWhiteSpaceTest() {
		assertThat(Character.isWhitespace(' ')).isTrue();
		assertThat(Character.isWhitespace(' ')).isFalse();
	}

}
